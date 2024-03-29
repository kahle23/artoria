package artoria.data.bean;

import artoria.convert.ConversionProvider;
import artoria.convert.ConversionUtils;
import artoria.util.Assert;

/**
 * The bean map factory simple implement by jdk.
 * @author Kahle
 */
public class SimpleBeanMapFactory implements BeanMapFactory {
    private final ConversionProvider conversionProvider;

    public SimpleBeanMapFactory() {

        this(ConversionUtils.getConversionProvider());
    }

    public SimpleBeanMapFactory(ConversionProvider conversionProvider) {
        Assert.notNull(conversionProvider, "Parameter \"conversionProvider\" must not null. ");
        this.conversionProvider = conversionProvider;
    }

    @Override
    public BeanMap getInstance(Object bean) {
        SimpleBeanMap beanMap = new SimpleBeanMap(conversionProvider);
        if (bean != null) { beanMap.setBean(bean); }
        return beanMap;
    }

}
