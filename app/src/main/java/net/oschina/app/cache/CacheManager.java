package net.oschina.app.cache;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Administrator on 2017-09-04.
 */

public class CacheManager {
    //wifi缓存时间为5分钟
    private static long wifi_cache_time = 5 * 60 *1000;
    //其他网络环境为1小时
    private static long other_cache_time = 60 * 60 * 1000;

    /** 保存对象
     *  @param context
     *  @return
     */
    public static boolean saveObject(Context context, Serializable ser,String file){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = context.openFileOutput(file,Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(ser);
            oos.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                fos.close();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteObject(Context context,String fileName){
        String filePath = context.getFilesDir().getPath() + "/" + fileName;
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
        }
    }

    public static Serializable readObject(Context context,String file){
//        if(isEx){}
    }

    public static boolean isExistDataCache(Context context,String cacheFile){
        if(context == null){
            return false;
        }
        boolean exist = false;
        File data = context.getFileStreamPath(cacheFile);
        if(data.exists()){
            exist = true;
        }
        return exist;
    }

    public static boolean isCacheDataFailure(Context context,String cacheFile){
        File data = context.getFileStreamPath(cacheFile);
        if(!data.exists()){
            return false;
        }
        long existTime = System.currentTimeMillis() - data.lastModified();
        boolean failure = false;
//        if(TDev){}

    }




}
