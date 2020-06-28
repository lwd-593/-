package com.enzenith.utils.util.ftp;

import com.enzenith.utils.util.DateUtil;


/**
 *  FTPLog
 * @author: Shupeng Lin
 * @date: 2019-11-14  上午 10:43
 **/
public class FTPLog {
    private String host;
    private String operation;
    private int replyCode;
    private String localFile;
    private String remoteFile;
    private String replyCodeDesc;
    private String createTime = DateUtil.currentDateTime();

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getReplyCode() {
        return replyCode;
    }

    public void setReplyCode(int replyCode) {
        replyCode = replyCode;
    }

    public String getLocalFile() {
        return localFile;
    }

    public void setLocalFile(String localFile) {
        this.localFile = localFile;
    }

    public String getRemoteFile() {
        return remoteFile;
    }

    public void setRemoteFile(String remoteFile) {
        this.remoteFile = remoteFile;
    }

    public String getReplyCodeDesc() {
        return replyCodeDesc;
    }

    public void setReplyCodeDesc(String replyCodeDesc) {
        replyCodeDesc = replyCodeDesc;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "FTPLog{" +
                "host='" + host + '\'' +
                ", operation='" + operation + '\'' +
                ", ReplyCode=" + replyCode +
                ", localFile='" + localFile + '\'' +
                ", remoteFile='" + remoteFile + '\'' +
                ", ReplyCodeDesc='" + replyCodeDesc + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
