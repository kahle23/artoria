package artoria.beans;

import artoria.converter.TypeConverter;
import artoria.exception.ExceptionUtils;
import artoria.util.ArrayUtils;
import artoria.util.Assert;
import artoria.util.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import static artoria.common.Constants.*;

/**
 * Bean map simple implement by jdk.
 * @author Kahle
 */
public class SimpleBeanMap extends BeanMap {
    private HashMap<Object, Method> readMethods = new HashMap<Object, Method>();
    private HashMap<Object, Method> writeMethods = new HashMap<Object, Method>();
    private Class<?> beanClass;

    public SimpleBeanMap() {
    }

    public SimpleBeanMap(Object bean) {

        this.setBean(bean);
    }

    @Override
    public void setBean(Object bean) {
        super.setBean(bean);
        if (this.beanClass != null && this.beanClass.equals(bean.getClass())) {
            return;
        }
        this.beanClass = bean.getClass();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(this.beanClass);
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            if (descriptors == null) { return; }
            for (PropertyDescriptor descriptor : descriptors) {
                Method readMethod = descriptor.getReadMethod();
                if (readMethod != null) {
                    String name = readMethod.getName();
                    name = name.substring(GET_OR_SET_LENGTH);
                    name = StringUtils.uncapitalize(name);
                    this.readMethods.put(name, readMethod);
                }
                Method writeMethod = descriptor.getWriteMethod();
                if (writeMethod != null) {
                    String name = writeMethod.getName();
                    name = name.substring(GET_OR_SET_LENGTH);
                    name = StringUtils.uncapitalize(name);
                    this.writeMethods.put(name, writeMethod);
                }
            }
        }
        catch (Exception e) {
            throw ExceptionUtils.wrap(e);
        }
    }

    @Override
    protected Object get(Object bean, Object key) {
        Assert.notNull(key, "Parameter \"key\" must not null. ");
        String keyString = key + EMPTY_STRING;
        if (keyString.startsWith(GET)) {
            keyString = keyString.substring(GET_OR_SET_LENGTH);
            keyString = StringUtils.uncapitalize(keyString);
        }
        Method method = this.readMethods.get(keyString);
        if (method == null) { return null; }
        try {
            return method.invoke(bean);
        }
        catch (Exception e) {
            throw ExceptionUtils.wrap(e);
        }
    }

    @Override
    protected Object put(Object bean, Object key, Object value) {
        Assert.notNull(key, "Parameter \"key\" must not null. ");
        String keyString = key + EMPTY_STRING;
        if (keyString.startsWith(SET)) {
            keyString = keyString.substring(GET_OR_SET_LENGTH);
            keyString = StringUtils.uncapitalize(keyString);
        }
        Method method = this.writeMethods.get(keyString);
        if (method == null) { return null; }
        TypeConverter cvt = this.getTypeConverter();
        Class<?>[] types = method.getParameterTypes();
        if (cvt != null && ArrayUtils.isNotEmpty(types)) {
            value = cvt.convert(value, types[0]);
        }
        try {
            // The return always null.
            // If want not null, must invoke getter first.
            return method.invoke(bean, value);
        }
        catch (Exception e) {
            throw ExceptionUtils.wrap(e);
        }
    }

    @Override
    public Set keySet() {
        Set<Object> keys = this.readMethods.keySet();
        return Collections.unmodifiableSet(keys);
    }

}
