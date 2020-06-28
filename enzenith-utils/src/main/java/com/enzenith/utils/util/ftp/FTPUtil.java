package com.enzenith.utils.util.ftp;


import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * FTP工具类
 * @author: Shupeng Lin
 * @date: 2019-11-14  上午 10:43
 **/
 interface FTPUtil {

    /**
     * 判断远程文件是否存在
     * @param fileName      文件名称
     * @return: boolean
     * @author: Shupeng Lin
     * @date: 2019-11-14  上午 11:36
     **/
    boolean isExists(String fileName);


    /**
     * 下载远程文件
     * @param fileName      文件名称
     * @return: boolean
     * @author: Shupeng Lin
     * @date: 2019-11-14  上午 11:37
     **/
    boolean downLoad(String fileName);

    /**
     * 下载远程目录
     * @param directory     目录名称
     * @return: boolean
     * @author: Shupeng Lin
     * @date: 2019-11-14  上午 11:37
     **/
     boolean downLoadDir(String directory);


    /**
     * 删除远程文件
     * @param fileName      文件名称
     * @return: boolean
     * @author: Shupeng Lin
     * @date: 2019-11-14  上午 11:38
     **/
     boolean deleteFile(String fileName);


    /**
     * 删除远程目录
     * @param directory     目录名称
     * @return: boolean
     * @author: Shupeng Lin
     * @date: 2019-11-14  上午 11:38
     **/
     boolean deleteDir(String directory);


    /**
     * 上传本地文件到远程目录
     * @param fileName      本地文件名
     * @param remoteFileName    远程文件名
     * @param isDelete      是否删除
     * @return: boolean
     * @author: Shupeng Lin
     * @date: 2019-11-14  上午 11:38
     **/
     boolean putFile(String fileName, String remoteFileName, boolean isDelete);


    /**
     * 上传本地文件到远程目录
     * @param file      本地文件
     * @param remoteFileName    远程文件名
     * @param isDelete      是否删除
     * @return: boolean
     * @author: Shupeng Lin
     * @date: 2019-11-14  上午 11:39
     **/
     boolean putFile(File file, String remoteFileName, boolean isDelete);


    /**
     * 上传本地目录到远程
     * @param fileName      本地文件名
     * @param remoteDir     远程目录
     * @return: boolean
     * @author: Shupeng Lin
     * @date: 2019-11-14  上午 11:40
     **/
     boolean putDir(String fileName, String remoteDir);


    /**
     * 上传本地目录到远程
     * @param file      本地文件对象
     * @param remoteDir     远程目录
     * @return: boolean
     * @author: Shupeng Lin
     * @date: 2019-11-14  上午 11:40
     **/
     boolean putDir(File file, String remoteDir);


    /**
     * 创建目录
     * @param destory
     * @return: boolean
     * @author: Shupeng Lin
     * @date: 2019-11-14  上午 11:40
     **/
     boolean mkDir(String destory);



    /**
     * 获取远程文件列表
     * @param directory     文件目录
     * @return: java.util.List<java.lang.String>
     * @author: Shupeng Lin
     * @date: 2019-11-14  上午 11:41
     **/
     List<String> listFile(String directory);


    /**
     * 获取远程文件夹的目录结构
     * @param direcotyr     文件目录
     * @return: java.util.LinkedList<java.lang.String>
     * @author: Shupeng Lin
     * @date: 2019-11-14  上午 11:41
     **/
     LinkedList<String> listDir(String direcotyr);


    /**
     * 获取远程文件属性以Map形式返回
     * @param directory     文件目录
     * @return: java.util.Map<java.lang.String,com.enzenith.utils.util.ftp.FileAttr>
     * @author: Shupeng Lin
     * @date: 2019-11-14  上午 11:41
     **/
     Map<String, FileAttr> listFileAttr(String directory);


    /**
     * 改变FTP连接的工作目录
     * @param directory     文件目录
     * @return: boolean
     * @author: Shupeng Lin
     * @date: 2019-11-14  上午 11:41
     **/
     boolean changeWorkDir(String directory);


    /**
     * 获取当前连接的工作目录
     * @return: java.lang.String
     * @author: Shupeng Lin
     * @date: 2019-11-14  上午 11:41
     **/
     String getWorkDir();

    /**
     * 重命名文件
     * @param oldName       旧名称
     * @param newName       新名称
     * @return: boolean
     * @author: Shupeng Lin
     * @date: 2019-11-14  上午 11:42
     **/
     boolean changName(String oldName, String newName);


    /**
     * 返回FTPCliend对象(已经打开连接)
     * @return: org.apache.commons.net.ftp.FTPClient
     * @author: Shupeng Lin
     * @date: 2019-11-14  上午 11:43
     **/
     FTPClient client();


    /**
     * 释放所有的资源
     * @return: void
     * @author: Shupeng Lin
     * @date: 2019-11-14  上午 11:43
     **/
     void destory();
}
