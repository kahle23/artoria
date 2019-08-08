package artoria.file;

import artoria.util.Assert;
import artoria.util.ClassLoaderUtils;
import artoria.util.CloseUtils;

import java.io.*;

import static artoria.common.Constants.CLASSPATH;

/**
 * Abstract file entity.
 * @author Kahle
 */
public abstract class AbstractFileEntity implements FileEntity {
    private String fileName;

    @Override
    public String getName() {

        return fileName;
    }

    @Override
    public void setName(String fileName) {
        Assert.notBlank(fileName, "Parameter \"fileName\" must not blank. ");
        this.fileName = fileName;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.write(byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return new ByteArrayInputStream(byteArray);
    }

    public void writeToFile(File file) throws IOException {
        Assert.notNull(file, "Parameter \"file\" must not null. ");
        File parentFile = file.getParentFile();
        if (!parentFile.exists() && !parentFile.mkdirs()) {
            throw new IllegalStateException(
                    "The parent directory for the parameter \"file\" does not exist and creation failed. "
            );
        }
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            this.write(outputStream);
        }
        finally {
            CloseUtils.closeQuietly(outputStream);
        }
    }

    public long readFromFile(File file) throws IOException {
        Assert.notNull(file, "Parameter \"file\" must not null. ");
        if (!file.exists() || file.isDirectory()) {
            throw new IllegalStateException("Parameter \"file\" must exist and is file. ");
        }
        this.setName(file.getName());
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return this.read(inputStream);
        }
        finally {
            CloseUtils.closeQuietly(inputStream);
        }
    }

    public void writeToClasspath(String subPath) throws IOException {
        Assert.notBlank(subPath, "Parameter \"subPath\" must not blank. ");
        Assert.notBlank(CLASSPATH, "Cannot get the classpath. ");
        File file = new File(CLASSPATH, subPath);
        this.writeToFile(file);
    }

    public long readFromClasspath(String subPath) throws IOException {
        File subPathFile = new File(subPath);
        this.setName(subPathFile.getName());
        InputStream inputStream = null;
        try {
            inputStream = ClassLoaderUtils.getResourceAsStream(subPath, getClass());
            Assert.notNull(inputStream
                    , "Parameter \"subPath\" not found in classpath. ");
            return this.read(inputStream);
        }
        finally {
            CloseUtils.closeQuietly(inputStream);
        }
    }

}
