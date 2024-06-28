/*
 * Copyright (c) 2018. the original author or authors.
 * Kunlun is licensed under the "LICENSE" file in the project's root directory.
 */

package kunlun.action.support.data.fill;

import kunlun.action.support.AbstractStrategyActionHandler;
import kunlun.data.Array;
import kunlun.util.Assert;
import kunlun.util.CollectionUtils;
import kunlun.util.MapUtils;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractSingleFieldFillHandler
        extends AbstractStrategyActionHandler implements DataFillHandler {

    private String getFirst(Collection<String> coll) {
        if (coll == null) { return null; }
        Iterator<String> iterator = coll.iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }

    @Override
    public void fill(FieldConfig cfg, Map<String, Map<Object, Object>> map, Collection<Map<Object, Object>> data) {
        if (MapUtils.isEmpty(map)) { return; }
        for (Map<Object, Object> dataMap : data) {
            String queryField = getFirst(cfg.getQueryFields());
            String fillField = getFirst(cfg.getFillFields());
            String dataField = getFirst(cfg.getDataFields());
            Object value = dataMap.get(queryField);
            if (value == null) { continue; }
            Map<Object, Object> objMap = map.get(String.valueOf(value));
            if (objMap == null) { continue; }
            Object dataVal = objMap.get(dataField);
            if (dataVal == null) { continue; }
            dataMap.put(fillField, dataVal);
        }
    }

    @Override
    public Object execute(Object input, String strategy, Class<?> clazz) {
        Assert.notNull(input, "Parameter \"input\" must not null. ");
        Assert.isInstanceOf(Config.class, input
                , "Parameter \"input\" must instance of \"Config\". ");
        //
        Config config = (Config) input;
        Collection<? extends FieldConfig> fieldConfigs = config.getFieldConfigs();
        Object data = config.getData();
        // if data is null or field configs is empty, logical end.
        if (data == null) { return null; }
        if (CollectionUtils.isEmpty(fieldConfigs)) { return null; }
        //
        Collection<Map<Object, Object>> dataList = convert(data);
        if (CollectionUtils.isEmpty(dataList)) { return null; }
        // queryField = createId  , projectId
        // fillField  = createName, projectName
        // dataField  = nickName  , name
        // Extract the data to be queried.
        Map<FieldConfig, Collection<Object>> queryFieldMap = new LinkedHashMap<FieldConfig, Collection<Object>>();
        for (Map<Object, Object> dataMap : dataList) {
            for (FieldConfig fieldConfig : fieldConfigs) {
                Object value = dataMap.get(getFirst(fieldConfig.getQueryFields()));
                if (value == null) { continue; }
                Collection<Object> coll = queryFieldMap.get(fieldConfig);
                if (coll == null) {
                    queryFieldMap.put(fieldConfig, coll = new Array());
                }
                coll.add(value);
            }
        }
        // Query data.
        for (Map.Entry<FieldConfig, Collection<Object>> entry : queryFieldMap.entrySet()) {
            Collection<Object> objectList = entry.getValue();
            FieldConfig fieldConfig = entry.getKey();
            // Fill data.
            fill(fieldConfig, acquire(fieldConfig, objectList), dataList);
        }
        return null;
    }

}
