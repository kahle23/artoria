package artoria.codec;

import artoria.logging.Logger;
import artoria.logging.LoggerFactory;
import org.junit.Test;

import java.nio.charset.Charset;

import static artoria.common.Constants.*;
import static java.lang.Boolean.TRUE;
import static org.junit.Assert.*;

public class Base64Test {
    private static Logger log = LoggerFactory.getLogger(Base64Test.class);
    private static Charset encoding = Charset.forName(UTF_8);
    private String data =
            "-->> 行路难！行路难！多歧路，今安在？ <<--\n" +
            "-->> 长风破浪会有时，直挂云帆济沧海。 <<--";
    private byte[] dataBytes = data.getBytes(encoding);
    private Base64 mimeBase64 = new Base64(TRUE, MINUS_ONE, null);
    private Base64 urlBase64 = new Base64(TRUE);
    private Base64 base64 = new Base64();

    @Test
    public void test1() {
        String encode = base64.encodeToString(dataBytes);
        assertTrue(encode.contains(PLUS));
        assertTrue(encode.contains(SLASH));
        log.info("Encode string: {}{}", NEWLINE, encode);
        byte[] decode = base64.decodeFromString(encode);
        String decodeStr = new String(decode, encoding);
        assertEquals(data, decodeStr);
        log.info("Decode string: {}{}", NEWLINE, decodeStr);
    }

    @Test
    public void test2() {
        String encode = urlBase64.encodeToString(dataBytes);
        assertFalse(encode.contains(PLUS));
        assertFalse(encode.contains(SLASH));
        assertTrue(encode.contains(MINUS));
        assertTrue(encode.contains(UNDERLINE));
        log.info("Encode string: {}{}", NEWLINE, encode);
        byte[] decode = urlBase64.decodeFromString(encode);
        String decodeStr = new String(decode, encoding);
        assertEquals(data, decodeStr);
        log.info("Decode string: {}{}", NEWLINE, decodeStr);
    }

    @Test
    public void test3() {
        String encode = mimeBase64.encodeToString(dataBytes);
        assertTrue(encode.contains(PLUS));
        assertTrue(encode.contains(SLASH));
        assertTrue(encode.contains("\r\n"));
        log.info("Encode string: {}{}", NEWLINE, encode);
        byte[] decode = mimeBase64.decodeFromString(encode);
        String decodeStr = new String(decode, encoding);
        assertEquals(data, decodeStr);
        log.info("Decode string: {}{}", NEWLINE, decodeStr);
    }

}
