package com.enzenith.utils.util.ftp;

import java.util.Date;


/**
 * FTP文件熟悉
 * @author: Shupeng Lin
 * @date: 2019-11-14  上午 10:40
 **/
public class FileAttr {
    private String fileName;
    private Date modifyTime;
    private Long size;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getModifyTime() {
        return new Date(modifyTime.getTime());
    }

    public void setModifyTime(Date modifyTime) {
        modifyTime = modifyTime;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
