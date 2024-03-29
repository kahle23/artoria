package artoria.io.file.support;

import artoria.io.FileBase;
import artoria.io.FileEntity;
import artoria.io.util.IOUtils;
import artoria.logging.Logger;
import artoria.logging.LoggerFactory;
import artoria.util.CloseUtils;
import artoria.util.CollectionUtils;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public class LocalFileStorageTest {
    private static Logger log = LoggerFactory.getLogger(LocalFileStorageTest.class);
    private static LocalFileStorage localFileStorage = new LocalFileStorage();
    private static String testPath = ".\\target\\test\\test_file.txt";

    @Test
    public void test1() {
        FileEntityImpl fileEntity = new FileEntityImpl(testPath);
        fileEntity.setInputStream(new ByteArrayInputStream("Hello, world! ".getBytes()));
        Object put = localFileStorage.put(fileEntity);
        log.info("put: {}", put);
        put = localFileStorage.put(testPath, "Hello, Hello, world! ");
        log.info("put: {}", put);
    }

    @Test
    public void test2() throws IOException {
        // Put file.
        FileEntityImpl filePut = new FileEntityImpl(testPath);
        filePut.setInputStream(new ByteArrayInputStream("Hello, world! ".getBytes()));
        Object put = localFileStorage.put(filePut);
        log.info("put: {}", put);
        // Get file and exist file.
        boolean exist = localFileStorage.exist(testPath);
        FileEntity fileGet = localFileStorage.get(testPath);
        InputStream inputStream = fileGet.getInputStream();
        String content = IOUtils.toString(inputStream);
        CloseUtils.closeQuietly(inputStream);
        String name = fileGet.getName();
        String path = fileGet.getPath();
        log.info("exist: {}, name: {}, path: {}, content: {}", exist, name, path, content);
        // Delete file and exist file.
        Boolean delete = localFileStorage.delete(testPath);
        exist = localFileStorage.exist(testPath);
        log.info("exist: {}, delete: {}", exist, delete);
    }

    @Test
    public void test3() {
        Collection<FileBase> list = localFileStorage.list(".\\target");
        if (CollectionUtils.isEmpty(list)) { return; }
        for (FileBase fileBase : list) {
            log.info("file: {}", JSON.toJSONString(fileBase));
        }
    }

}
