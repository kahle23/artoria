package artoria.convert.support;

import artoria.convert.ConversionService;
import artoria.util.StringUtils;

import java.math.BigDecimal;

public class StringToNumberConverter extends AbstractClassConverter {

    public StringToNumberConverter() {

        super(String.class, Number.class);
    }

    public StringToNumberConverter(ConversionService conversionService) {

        super(conversionService, String.class, Number.class);
    }

    @Override
    protected Object convert(Object source, Class<?> sourceClass, Class<?> targetClass) {
        String numString = (String) source;
        // If it is a blank string, it can indicate that the number is null.
        // However, if the target data type is of a non-wrapper type, a null pointer will be generated.
        // So the error caused by returning a blank string is more appropriate.
        if (StringUtils.isBlank(numString)) { return source; }
        numString = numString.trim();
        BigDecimal decimal = new BigDecimal(numString);
        return getConversionService().convert(decimal, targetClass);
    }

}
