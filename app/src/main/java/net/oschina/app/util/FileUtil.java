package net.oschina.app.util;

import android.content.Context;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017-09-04.
 */

public class FileUtil {

    /**
     * 写文本文件  在Android系统中，文件保存在 /data/data/package_name/files 目录下
     * @param fileName
     * @param content
     */
    public static void write(Context context,String fileName,String content){
        if(content == null){
            content = "";
        }
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文本文件
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String read(Context context,String fileName){
        try {
            FileInputStream in = context.openFileInput(fileName);
            return readInStream(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String readInStream(InputStream inStream){

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[512];
        int len = -1;

        try {
            while ((len = inStream.read(buffer)) != -1){
                outputStream.write(buffer,0,len);
            }
            outputStream.close();
            inStream.close();
            return outputStream.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File createFile(String folderPath,String fileName){
        File destDir = new File(folderPath);
        if(!destDir.exists()){
            destDir.mkdirs();
        }

        return new File(folderPath,fileName + fileName);
    }

    public static boolean writeFile(byte[] buffer,String folder,String fileName){
        boolean writeSucc = false;
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        String folderPath = "";
        if(sdCardExist){
            folderPath = Environment.getExternalStorageDirectory() + File.separator + folder + File.separator;

        }
        return true;
    }


}
