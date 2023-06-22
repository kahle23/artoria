package artoria.aop;

import artoria.core.Handler;

import java.util.Map;

/**
 * The proxy handler for generate proxy object.
 * @author Kahle
 */
public interface ProxyHandler extends Handler {

    /**
     * Get the common properties of the settings.
     * @return The common properties that is set
     */
    Map<Object, Object> getCommonProperties();

    /**
     * Set common properties for the proxy handler.
     * @param properties The properties to be set
     */
    void setCommonProperties(Map<?, ?> properties);

    /**
     * Get proxy object instance.
     * @param originalClass Be proxied object class
     * @param interceptor Method interceptor
     * @return Proxy object
     */
    Object proxy(Class<?> originalClass, Interceptor interceptor);

}
