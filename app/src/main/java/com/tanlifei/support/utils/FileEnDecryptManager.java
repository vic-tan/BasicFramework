package com.tanlifei.support.utils;

import android.content.Context;

import com.support.utils.Logger;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;


/**
 * Created by IntelliJ IDEA.
 * Author:Eric
 * Date:2015/1/14
 * Desc:加密解密管理类
 * 加密算法 : 将文件的数据流的每个字节与加密解密key对应字符做异或运算.
 * 解密算法 : 已经加密的文件再执行一次对文件的数据流的每个字节与加密解密key对应字符做异或运算.
 * this method can decrypt or doEncrypt a large file in 100 milliseconds,just have a try and see
 */
public class FileEnDecryptManager {

    private String key = "tanlifei"; // 加密解密key(Encrypt or decrypt key)
    private final int REVERSE_LENGTH = 56;// 加解密长度(Encryption length)

    private FileEnDecryptManager() {
    }

    private static FileEnDecryptManager instance = null;

    public static FileEnDecryptManager getInstance() {
        synchronized (FileEnDecryptManager.class) {
            if (instance == null)
                instance = new FileEnDecryptManager();
        }
        return instance;
    }


    /**
     * 加密入口
     *
     * @param fileUrl 文件绝对路径
     * @return
     */
    public boolean doEncrypt(Context mContext, String fileUrl) {
        try {
            if (encrypt(mContext, fileUrl, true)) {
                // 可在此处保存加密状态到数据库或文件(you can save state into db or file)
                Logger.d(" 加密 encrypt succeed");
                return true;
            } else {
                Logger.d("加密 encrypt failed");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    /**
     * 加解密
     *
     * @param strFile 源文件绝对路径
     * @return
     */
    private boolean encrypt(Context mContext, String strFile, boolean isEncrypt) {
        int len = REVERSE_LENGTH;
        try {
            File f = new File(strFile);
            if (f.exists()) {
                long start = System.currentTimeMillis();
                RandomAccessFile raf = new RandomAccessFile(f, "rw");
                long totalLen = raf.length();

                if (totalLen < REVERSE_LENGTH)
                    len = (int) totalLen;

                FileChannel channel = raf.getChannel();
                MappedByteBuffer buffer = channel.map(
                        FileChannel.MapMode.READ_WRITE, 0, REVERSE_LENGTH);
                byte tmp;
                for (int i = 0; i < len; ++i) {
                    byte rawByte = buffer.get(i);
                    if (i <= key.length() - 1) {
                        tmp = (byte) (rawByte ^ key.charAt(i)); // 异或运算(XOR operation)
                    } else {
                        tmp = (byte) (rawByte ^ i);
                    }
                    buffer.put(i, tmp);
                }
                buffer.force();
                buffer.clear();
                channel.close();
                raf.close();
                if (!isEncrypt) {
                    SPUtils.getInstance(mContext).put("last_decrypt_file", strFile);
                }
                long end = System.currentTimeMillis();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 解密入口
     *
     * @param fileUrl 源文件绝对路径
     */
    public boolean doDecrypt(Context mContext, String fileUrl) {
        try {
            String save = SPUtils.getInstance(mContext).getString("last_decrypt_file");//是否有记录
            if (StringUtils.isEmpty(save)) {//第一次加密
                return decrypt(mContext, fileUrl);
            } else {//上一次加密
                if (StringUtils.isEquals(fileUrl, save)) {//上次已解密过是本文件
                    return true;
                } else {//最后一次解密记录不是本文件，则先加密上一次解密文件，在解密本文件
                    if (encrypt(mContext, save, true)) {
                        return decrypt(mContext, fileUrl);
                    } else {
                        if (encrypt(mContext, save, true)) {
                            return decrypt(mContext, fileUrl);
                        } else {
                            return decrypt(mContext, fileUrl);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean decrypt(Context mContext, String fileUrl) {
        if (encrypt(mContext, fileUrl, false)) {
            // 可在此处保存加密状态到数据库或文件(you can save state into db or file)
            Logger.d("解密 decrypt succeed");
            return true;
        } else {
            Logger.d("解密 decrypt failed");
            return false;
        }
    }

}