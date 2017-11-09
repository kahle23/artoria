package saber.codec;

import saber.util.Assert;
import saber.util.Reflect;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.Charset;
import java.util.regex.Pattern;

/**
 * @author Kahle
 * @see org.apache.commons.codec.binary.Base64
 * @see java.util.Base64
 * @see DatatypeConverter#printBase64Binary
 * @see DatatypeConverter#parseBase64Binary
 */
public class Base64 {
    private static final String JAVAX_XML_DATATYPE_CONVERTER = "javax.xml.bind.DatatypeConverter";
    private static final String COMMONS_CODEC_BASE64 = "org.apache.commons.codec.binary.Base64";
    private static final String JAVA_UTIL_BASE64 = "java.util.Base64";
    private static final Pattern BASE64_URL_SAFE = Pattern.compile("^[a-zA-Z0-9-_]+={0,2}$");
    private static final Pattern BASE64_URL_UNSAFE = Pattern.compile("^[a-zA-Z0-9+/]+={0,2}$");
    private static final Charset DEFAULT_CHARSET = Charset.defaultCharset();
    private static final Base64Delegate DELEGATE;

    static {
        Base64Delegate delegateToUse = null;
        ClassLoader classLoader = Base64.class.getClassLoader();
        // Apache Commons Codec present on the classpath?
		if (Reflect.isPresent(COMMONS_CODEC_BASE64, classLoader)) {
            delegateToUse = new CommonsCodecBase64Delegate();
        }
        // JDK 8's java.util.Base64 class present?
        else if (Reflect.isPresent(JAVA_UTIL_BASE64, classLoader)) {
            delegateToUse = new Java8Base64Delegate();
        }
        // maybe all jdk is ok?
        else if (Reflect.isPresent(JAVAX_XML_DATATYPE_CONVERTER, classLoader)) {
            delegateToUse = new Java67Base64Delegate();
        }
        DELEGATE = delegateToUse;
    }

    private static void assertDelegateAvailable() {
        Assert.state(DELEGATE != null,
                "Neither Java 8, Java 7, Java 6 nor Apache Commons Codec found - Base64 encoding between byte arrays not supported");
    }

    public static boolean isBase64UrlSafe(String base64) {
        return BASE64_URL_SAFE.matcher(base64).matches();
    }

    public static boolean isBase64UrlUnsafe(String base64) {
        return BASE64_URL_UNSAFE.matcher(base64).matches();
    }

    public static byte[] encode(byte[] src) {
        assertDelegateAvailable();
        return DELEGATE.encode(src);
    }

    public static byte[] decode(byte[] src) {
        assertDelegateAvailable();
        return DELEGATE.decode(src);
    }

    public static byte[] encodeUrlSafe(byte[] src) {
        assertDelegateAvailable();
        return DELEGATE.encodeUrlSafe(src);
    }

    public static byte[] decodeUrlSafe(byte[] src) {
        assertDelegateAvailable();
        return DELEGATE.decodeUrlSafe(src);
    }

    public static String encodeToString(byte[] src) {
        return new String(encode(src), DEFAULT_CHARSET);
    }

    public static String encodeToString(byte[] src, String charset) {
        return new String(encode(src), Charset.forName(charset));
    }

    public static byte[] decodeFromString(String src) {
        return decode(src.getBytes(DEFAULT_CHARSET));
    }

    public static byte[] decodeFromString(String src, String charset) {
        return decode(src.getBytes(Charset.forName(charset)));
    }

    public static String encodeToUrlSafeString(byte[] src) {
        return new String(encodeUrlSafe(src), DEFAULT_CHARSET);
    }

    public static String encodeToUrlSafeString(byte[] src, String charset) {
        return new String(encodeUrlSafe(src), Charset.forName(charset));
    }

    public static byte[] decodeFromUrlSafeString(String src) {
        return decodeUrlSafe(src.getBytes(DEFAULT_CHARSET));
    }

    public static byte[] decodeFromUrlSafeString(String src, String charset) {
        return decodeUrlSafe(src.getBytes(Charset.forName(charset)));
    }

    interface Base64Delegate {

        /**
         * Base64 encode
         * @param src something will encode
         * @return something encoded
         */
        byte[] encode(byte[] src);

        /**
         * Base64 decode
         * @param src something will decode
         * @return something encoded
         */
        byte[] decode(byte[] src);

        /**
         * Base64 url safe encode
         * @param src something will url safe encode
         * @return something encoded
         */
        byte[] encodeUrlSafe(byte[] src);

        /**
         * Base64 url safe decode
         * @param src something will url safe decode
         * @return something encoded
         */
        byte[] decodeUrlSafe(byte[] src);

    }

    static class Java67Base64Delegate implements Base64Delegate {

        @Override
        public byte[] encode(byte[] src) {
            if (src == null || src.length == 0) {
                return src;
            }
            return DatatypeConverter.printBase64Binary(src).getBytes();
        }

        @Override
        public byte[] decode(byte[] src) {
            if (src == null || src.length == 0) {
                return src;
            }
            return DatatypeConverter.parseBase64Binary(new String(src));
        }

        @Override
        public byte[] encodeUrlSafe(byte[] src) {
            if (src == null || src.length == 0) {
                return src;
            }
            String s = DatatypeConverter.printBase64Binary(src);
            s = s.replaceAll("\\+", "-");
            s = s.replaceAll("/", "_");
            return s.getBytes();
        }

        @Override
        public byte[] decodeUrlSafe(byte[] src) {
            if (src == null || src.length == 0) {
                return src;
            }
            String s = new String(src);
            s = s.replaceAll("-", "+");
            s = s.replaceAll("_", "/");
            return DatatypeConverter.parseBase64Binary(s);
        }

    }

    static class Java8Base64Delegate implements Base64Delegate {

        @Override
        public byte[] encode(byte[] src) {
            if (src == null || src.length == 0) {
                return src;
            }
            return java.util.Base64.getEncoder().encode(src);
        }

        @Override
        public byte[] decode(byte[] src) {
            if (src == null || src.length == 0) {
                return src;
            }
            return java.util.Base64.getDecoder().decode(src);
        }

        @Override
        public byte[] encodeUrlSafe(byte[] src) {
            if (src == null || src.length == 0) {
                return src;
            }
            return java.util.Base64.getUrlEncoder().encode(src);
        }

        @Override
        public byte[] decodeUrlSafe(byte[] src) {
            if (src == null || src.length == 0) {
                return src;
            }
            return java.util.Base64.getUrlDecoder().decode(src);
        }

    }

    static class CommonsCodecBase64Delegate implements Base64Delegate {

        private final org.apache.commons.codec.binary.Base64 base64 =
                new org.apache.commons.codec.binary.Base64();

        private final org.apache.commons.codec.binary.Base64 base64UrlSafe =
                new org.apache.commons.codec.binary.Base64(0, null, true);

        @Override
        public byte[] encode(byte[] src) {
            return this.base64.encode(src);
        }

        @Override
        public byte[] decode(byte[] src) {
            return this.base64.decode(src);
        }

        @Override
        public byte[] encodeUrlSafe(byte[] src) {
            return this.base64UrlSafe.encode(src);
        }

        @Override
        public byte[] decodeUrlSafe(byte[] src) {
            return this.base64UrlSafe.decode(src);
        }

    }

}
