package net.oschina.app;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 应用程序配置类
 * 用于保存用户相关信息及设置
 * Created by Administrator on 2017-09-02.
 */

public class AppConfig {
    private final static String APP_CONFIG = "config";
    public static final String KEY_LOAD_IMAGE = "KEY_LOAD_IMAGE";
    public static final String KEY_NOTIFICATION_DISABLE_WHEN_EXIT = "KEY_NOTIFICATION_DISABLE_WHEN_EXIT";
    public static final String KEY_CACHE_UPDATE = "KEY_CACHE_UPDATE";
    public static final String KEY_DOUBLE_CLICK_EXIT = "KEY_DOUBLE_CLICK_EXIT";

    //默认存放图片的路径
    public final static String DEFAULT_SAVE_IMAGE_PATH = Environment.getExternalStorageDirectory()
            + File.separator
            + "OSChina"
            + File.separator + "osc_img" + File.separator;
    //默认存放文件下载的路径
    public final static String default_save_file_path = Environment.getExternalStorageDirectory()
            + File.separator
            + "OSChina"
            + File.separator + "download" + File.separator;

    private Context mContext;
    private static AppConfig appConfig;

    public static AppConfig getAppConfig(Context context){
        if(appConfig == null){
            appConfig = new AppConfig();
            appConfig.mContext = context;
        }
        return appConfig;
    }

    public String get(String key){
        Properties props = get();
        return (props != null)?props.getProperty(key) : null;
    }

    public Properties get(){
        FileInputStream fis = null;
        Properties props = new Properties();
        //读取app_config目录下的config
        File dirConfig = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
        try {
            fis = new FileInputStream(dirConfig.getPath() + File.separator + APP_CONFIG);
            props.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(fis != null){
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return props;
    }

    private void setProps(Properties p){
        FileOutputStream fos = null;
        //把config建在(自定义)app_config的目录下
        File dirConf = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
        File conf = new File(dirConf, APP_CONFIG);
        try {
            fos = new FileOutputStream(conf);
            p.store(fos,null);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(fos != null){
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void set(String key,String value){
        Properties props = get();
        props.setProperty(key,value);
        setProps(props);
    }

    public void remove(String... key){
        Properties props = get();
        for (String k : key){
            props.remove(k);
        }
        setProps(props);
    }




}
