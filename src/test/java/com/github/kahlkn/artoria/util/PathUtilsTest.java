package com.github.kahlkn.artoria.util;

import org.junit.Test;

public class PathUtilsTest {

    @Test
    public void testFindJarClasspath() {
        System.out.println(PathUtils.findJarClasspath("logging.properties"));
    }

    @Test
    public void testGetExtension() {
        System.out.println(PathUtils.getExtension("C:\\windows\\system\\123.txt"));
        System.out.println(PathUtils.getExtension("C:\\win.dows\\system"));
        System.out.println(PathUtils.getExtension("C:\\windows\\system\\"));
    }

    @Test
    public void testStripExtension() {
        System.out.println(PathUtils.stripExtension("C:\\windows\\system\\123.txt"));
        System.out.println(PathUtils.stripExtension("C:\\windows\\system"));
        System.out.println(PathUtils.stripExtension("C:\\windows\\system\\"));
    }

    @Test
    public void testNotExistPath() {
        System.out.println(PathUtils.notExistPath("E:\\_cache\\123.csv"));
        System.out.println(PathUtils.notExistPath("E:\\_cache\\123_1.csv"));
        System.out.println(PathUtils.notExistPath("E:\\_cache\\123_2.csv"));
    }

    @Test
    public void testPath() {
        System.out.println(PathUtils.getPackagePath(PathUtilsTest.class));
        System.out.println(PathUtils.getClassFilePath(PathUtilsTest.class));
    }

}