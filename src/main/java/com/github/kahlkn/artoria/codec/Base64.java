package com.github.kahlkn.artoria.codec;

import com.github.kahlkn.artoria.util.ArrayUtils;
import com.github.kahlkn.artoria.util.Assert;
import com.github.kahlkn.artoria.util.ClassUtils;
import com.github.kahlkn.artoria.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.github.kahlkn.artoria.util.Const.*;

/**
 * Base64 encode and decode tools.
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
    private static final Base64Delegate DELEGATE;

    private static Logger log = LoggerFactory.getLogger(Base64.class);

    static {
        Base64Delegate delegateToUse = null;
        ClassLoader classLoader = Base64.class.getClassLoader();
        // Apache Commons Codec present on the classpath?
        if (ClassUtils.isPresent(COMMONS_CODEC_BASE64, classLoader)) {
            log.info("Use base64 class: {}", COMMONS_CODEC_BASE64);
            delegateToUse = new CommonsCodecBase64Delegate();
        }
        // JDK 8's java.util.Base64 class present?
        else if (ClassUtils.isPresent(JAVA_UTIL_BASE64, classLoader)) {
            log.info("Use base64 class: {}", JAVA_UTIL_BASE64);
            delegateToUse = new Java8Base64Delegate();
        }
        // maybe all jdk is ok?
        else if (ClassUtils.isPresent(JAVAX_XML_DATATYPE_CONVERTER, classLoader)) {
            log.info("Use base64 class: {}", JAVAX_XML_DATATYPE_CONVERTER);
            delegateToUse = new Java7Base64Delegate();
        }
        DELEGATE = delegateToUse;
    }

    private static void assertDelegateAvailable() {
        Assert.notNull(DELEGATE, "Neither Java 8, Java 7, Java 6 " +
                "nor Apache Commons Codec found - Base64 encoding between byte arrays not supported");
    }

    public static byte[] encode(byte[] src) {
        Base64.assertDelegateAvailable();
        return DELEGATE.encode(src);
    }

    public static byte[] decode(byte[] src) {
        Base64.assertDelegateAvailable();
        return DELEGATE.decode(src);
    }

    public static byte[] encodeUrlSafe(byte[] src) {
        Base64.assertDelegateAvailable();
        return DELEGATE.encodeUrlSafe(src);
    }

    public static byte[] decodeUrlSafe(byte[] src) {
        Base64.assertDelegateAvailable();
        return DELEGATE.decodeUrlSafe(src);
    }

    public static boolean isUrlSafeString(String base64) {
        Assert.notNull(base64, "Parameter \"base64\" must not null. ");
        Matcher matcher = BASE64_URL_SAFE.matcher(base64);
        return matcher.matches();
    }

    public static boolean isUrlUnsafeString(String base64) {
        Assert.notNull(base64, "Parameter \"base64\" must not null. ");
        Matcher matcher = BASE64_URL_UNSAFE.matcher(base64);
        return matcher.matches();
    }

    public static String encodeToString(byte[] src) {
        byte[] encode = Base64.encode(src);
        Charset charset = Charset.forName(DEFAULT_CHARSET_NAME);
        return new String(encode, charset);
    }

    public static String encodeToString(byte[] src, String charset) {
        Assert.notBlank(charset, "Parameter \"charset\" must not blank. ");
        Charset encoding = Charset.forName(charset);
        byte[] encode = Base64.encode(src);
        return new String(encode, encoding);
    }

    public static byte[] decodeFromString(String source) {
        Assert.notNull(source, "Parameter \"source\" must not null. ");
        Charset charset = Charset.forName(DEFAULT_CHARSET_NAME);
        byte[] srcBytes = source.getBytes(charset);
        return Base64.decode(srcBytes);
    }

    public static byte[] decodeFromString(String source, String charset) {
        Assert.notNull(source, "Parameter \"source\" must not null. ");
        Assert.notBlank(charset, "Parameter \"charset\" must not blank. ");
        Charset encoding = Charset.forName(charset);
        byte[] srcBytes = source.getBytes(encoding);
        return Base64.decode(srcBytes);
    }

    public static String encodeToUrlSafeString(byte[] src) {
        byte[] urlSafe = Base64.encodeUrlSafe(src);
        Charset charset = Charset.forName(DEFAULT_CHARSET_NAME);
        return new String(urlSafe, charset);
    }

    public static String encodeToUrlSafeString(byte[] src, String charset) {
        Assert.notBlank(charset, "Parameter \"charset\" must not blank. ");
        Charset encoding = Charset.forName(charset);
        byte[] urlSafe = Base64.encodeUrlSafe(src);
        return new String(urlSafe, encoding);
    }

    public static byte[] decodeFromUrlSafeString(String source) {
        Assert.notNull(source, "Parameter \"source\" must not null. ");
        Charset charset = Charset.forName(DEFAULT_CHARSET_NAME);
        byte[] srcBytes = source.getBytes(charset);
        return Base64.decodeUrlSafe(srcBytes);
    }

    public static byte[] decodeFromUrlSafeString(String source, String charset) {
        Assert.notNull(source, "Parameter \"source\" must not null. ");
        Assert.notBlank(charset, "Parameter \"charset\" must not blank. ");
        Charset encoding = Charset.forName(charset);
        byte[] srcBytes = source.getBytes(encoding);
        return Base64.decodeUrlSafe(srcBytes);
    }

    interface Base64Delegate {

        /**
         * Base64 encode
         * @param source something will encode
         * @return something encoded
         */
        byte[] encode(byte[] source);

        /**
         * Base64 decode
         * @param source something will decode
         * @return something encoded
         */
        byte[] decode(byte[] source);

        /**
         * Base64 url safe encode
         * @param source something will url safe encode
         * @return something encoded
         */
        byte[] encodeUrlSafe(byte[] source);

        /**
         * Base64 url safe decode
         * @param source something will url safe decode
         * @return something encoded
         */
        byte[] decodeUrlSafe(byte[] source);

    }

    static class Java7Base64Delegate implements Base64Delegate {

        @Override
        public byte[] encode(byte[] source) {
            if (ArrayUtils.isEmpty(source)) { return source; }
            return DatatypeConverter.printBase64Binary(source).getBytes();
        }

        @Override
        public byte[] decode(byte[] source) {
            if (ArrayUtils.isEmpty(source)) { return source; }
            return DatatypeConverter.parseBase64Binary(new String(source));
        }

        @Override
        public byte[] encodeUrlSafe(byte[] source) {
            if (ArrayUtils.isEmpty(source)) { return source; }
            String s = DatatypeConverter.printBase64Binary(source);
            s = StringUtils.replace(s, PLUS, MINUS);
            s = StringUtils.replace(s, SLASH, UNDERLINE);
            return s.getBytes();
        }

        @Override
        public byte[] decodeUrlSafe(byte[] source) {
            if (ArrayUtils.isEmpty(source)) { return source; }
            String s = new String(source);
            s = StringUtils.replace(s, MINUS, PLUS);
            s = StringUtils.replace(s, UNDERLINE, SLASH);
            return DatatypeConverter.parseBase64Binary(s);
        }

    }

    static class Java8Base64Delegate implements Base64Delegate {

        @Override
        public byte[] encode(byte[] source) {
            if (ArrayUtils.isEmpty(source)) { return source; }
            return java.util.Base64.getEncoder().encode(source);
        }

        @Override
        public byte[] decode(byte[] source) {
            if (ArrayUtils.isEmpty(source)) { return source; }
            return java.util.Base64.getDecoder().decode(source);
        }

        @Override
        public byte[] encodeUrlSafe(byte[] source) {
            if (ArrayUtils.isEmpty(source)) { return source; }
            return java.util.Base64.getUrlEncoder().encode(source);
        }

        @Override
        public byte[] decodeUrlSafe(byte[] source) {
            if (ArrayUtils.isEmpty(source)) { return source; }
            return java.util.Base64.getUrlDecoder().decode(source);
        }

    }

    static class CommonsCodecBase64Delegate implements Base64Delegate {

        private final org.apache.commons.codec.binary.Base64 base64 =
                new org.apache.commons.codec.binary.Base64();

        private final org.apache.commons.codec.binary.Base64 base64UrlSafe =
                new org.apache.commons.codec.binary.Base64(0, null, true);

        @Override
        public byte[] encode(byte[] source) {
            return this.base64.encode(source);
        }

        @Override
        public byte[] decode(byte[] source) {
            return this.base64.decode(source);
        }

        @Override
        public byte[] encodeUrlSafe(byte[] source) {
            return this.base64UrlSafe.encode(source);
        }

        @Override
        public byte[] decodeUrlSafe(byte[] source) {
            return this.base64UrlSafe.decode(source);
        }

    }

}