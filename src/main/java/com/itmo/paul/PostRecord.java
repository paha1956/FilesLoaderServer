package com.itmo.paul;

import javax.validation.constraints.NotNull;

public class PostRecord {

    @NotNull
    private String userName;

    @NotNull
    private String fileDate;

    @NotNull
    private String fileTime;

    @NotNull
    private String fileURL;

    @NotNull
    private long fileSize;

    @NotNull
    private long loadTime;

    public String getUserName() {
        return userName;
    }
    public String getFileDate() {
        return fileDate;
    }
    public String getFileTime() {
        return fileTime;
    }
    public String getFileURL() {
        return fileURL;
    }
    public long getFileSize() {
        return fileSize;
    }
    public long getLoadTime() { return loadTime; }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setFileDate(String fileDate) {
        this.fileDate = fileDate;
    }
    public void setFileTime(String fileTime) {
        this.fileTime = fileTime;
    }
    public void setFileName(String fileURL) {
        this.fileURL = fileURL;
    }
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
    public void setLoadTime(long loadTime) {
        this.loadTime = loadTime;
    }
}
