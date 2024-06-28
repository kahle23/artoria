/*
 * Copyright (c) 2018. the original author or authors.
 * Kunlun is licensed under the "LICENSE" file in the project's root directory.
 */

package kunlun.action.support.data.fill;

import kunlun.core.Handler;

import java.util.Collection;
import java.util.Map;

/**
 *
 * 1. Convert data to map
 * 2. Extract the data to be queried
 * 3. Query data
 * 4. Fill data
 *
 * queryField = createId  , projectId
 * fillField  = createName, projectName
 * dataField  = nickName  , name
 *
 * @author Kahle
 */
public interface DataFillHandler extends Handler {

    /**
     * The query field is the same type of data.
     *
     * cfg if createId, query by account id.
     * cfg if projectId, query by project id.
     *
     * @param cfg
     * @param coll
     * @return
     */
    Map<String, Map<Object, Object>> acquire(FieldConfig cfg, Collection<?> coll);

    Collection<Map<Object, Object>> convert(Object data);

    void fill(FieldConfig cfg, Map<String, Map<Object, Object>> map, Collection<Map<Object, Object>> data);

    interface Config {

        Collection<? extends FieldConfig> getFieldConfigs();

        Object getData();

    }

    interface FieldConfig {

        /**
         *
         * "Collection" is used to support multi-data query scenarios.
         * @return
         */
        Collection<String> getQueryFields();

        /**
         *
         * Support multi-field filling scenario, and the following "dataFields" one-to-one mapping.
         * @return
         */
        Collection<String> getFillFields();

        Collection<String> getDataFields();

    }

}
