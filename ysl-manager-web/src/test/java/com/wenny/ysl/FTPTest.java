package com.wenny.ysl;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;
import utils.FtpUtil;

import java.io.File;
import java.io.FileInputStream;

public class FTPTest {

    @Test
    public void testFtpClient() throws Exception{
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("127.0.0.1",21);
        ftpClient.login("wenny","950219");
        FileInputStream inputStream = new FileInputStream(new File("/Users/wenny/Downloads/S.jpg"));
        ftpClient.changeWorkingDirectory("/Users/wenny/Pictures");
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        ftpClient.storeFile("hello1.jpg",inputStream);
        ftpClient.logout();
    }

    @Test
    public void testFtpUtil() throws Exception{
        FileInputStream inputStream = new FileInputStream(new File("/Users/wenny/Downloads/S.jpg"));
        FtpUtil.uploadFile("127.0.0.1",21,"wenny","950219","/Users/wenny/Pictures","/2018/01/28","hello.jpg",inputStream);
    }
}
