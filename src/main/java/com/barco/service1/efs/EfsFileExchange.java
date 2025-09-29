package com.barco.service1.efs;

import com.barco.service1.util.exception.ExceptionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Nabeel Ahmed
 */
@Component
public class EfsFileExchange extends FileHashing {

    private Logger logger = LogManager.getLogger(EfsFileExchange.class);

    private String basePathTempDire;

    public EfsFileExchange() {}

    /**
     * Method use to make th dir in efs folder
     * @param basePath
     * @return boolean
     * */
    public Boolean makeDir(String basePath) {
        try {
            basePath = this.basePathTempDire.concat(basePath);
            File finalDir = new File(basePath);
            if (!finalDir.exists()) {
                logger.info("Making New Directory at path [{}]", basePath);
                return finalDir.mkdirs();
            } else {
                logger.info("Directory Already Exist At Path [{}]", basePath);
                return true;
            }
        } catch (Exception ex) {
            logger.error(ExceptionUtil.getRootCauseMessage(ex));
        }
        return false;
    }

    /**
     * Method use to save the file in to tem dir
     * @param byteArrayOutputStream
     * @param targetFileName
     * */
    public void saveFile(ByteArrayOutputStream byteArrayOutputStream, String targetFileName) throws Exception {
        if (byteArrayOutputStream != null && byteArrayOutputStream.size() > 0) {
            try (OutputStream outputStream = Files.newOutputStream(Paths.get(this.basePathTempDire.concat(targetFileName)))) {
                byteArrayOutputStream.writeTo(outputStream);
            } finally {
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
            }
            logger.info("File Convert and store into local path");
        }
    }

    /**
     * Method use to get file from the target
     * @param targetFileName
     * @return InputStream
     * */
    public InputStream getFile(String targetFileName) throws Exception {
        return Files.newInputStream(Paths.get(targetFileName));
    }

    /**
     * Method use to delete the direction
     * @param basePath
     * */
    public void deleteDir(String basePath) {
        try {
            File file = new File(basePath);
            if (file.exists()) {
                logger.info("Deleting Directory At Path [{}]", basePath);
                FileUtils.deleteDirectory(file);
            }
        } catch (Exception ex) {
            logger.error(ExceptionUtil.getRootCauseMessage(ex));
        }
    }


    /**
     * Method use to clean the dir
     * @param basePath
     * */
    public void cleanDir(String basePath) {
        try {
            File file = new File(basePath);
            if (file.exists()) {
                logger.info("Cleaning Directory At Path [ " + basePath + " ]");
                FileUtils.cleanDirectory(file);
            }
        } catch (Exception ex) {
            logger.error(ExceptionUtil.getRootCauseMessage(ex));
        }
    }

    public String getBasePathTempDire() {
        return basePathTempDire;
    }

    public void setBasePathTempDire(String basePathTempDire) {
        this.basePathTempDire = basePathTempDire;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}