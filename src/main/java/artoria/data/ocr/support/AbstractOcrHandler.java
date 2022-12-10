package artoria.data.ocr.support;

import artoria.data.ocr.OcrHandler;
import artoria.util.Assert;

import java.io.*;
import java.util.Collections;
import java.util.Map;

/**
 * The abstract ocr handler.
 * @author Kahle
 */
public abstract class AbstractOcrHandler implements OcrHandler {
    private Map<Object, Object> commonProperties = Collections.emptyMap();

    protected InputStream convertToStream(Object data) throws IOException {
        if (data instanceof File) {
            return new FileInputStream((File) data);
        }
        else if (data instanceof InputStream) {
            return (InputStream) data;
        }
        else if (data instanceof byte[]) {
            return new ByteArrayInputStream((byte[]) data);
        }
        else {
            throw new IllegalArgumentException("Parameter \"data\" is not supported. ");
        }
    }

    @Override
    public Map<Object, Object> getCommonProperties() {

        return commonProperties;
    }

    @Override
    public void setCommonProperties(Map<?, ?> properties) {
        Assert.notNull(properties, "Parameter \"properties\" must not null. ");
        this.commonProperties = Collections.unmodifiableMap(properties);
    }

}
