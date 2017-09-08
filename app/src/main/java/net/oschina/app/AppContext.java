package net.oschina.app;

import net.oschina.app.base.BaseApplication;

import java.util.Properties;

/**
 * 全局应用程序类
 * 用于保存和调用全局应用配置及访问网络数据
 * Created by Administrator on 2017-09-02.
 */

public class AppContext extends BaseApplication {
    public static final int page_size = 20;//默认分页大小
    private static AppContext instanse;

    @Override
    public void onCreate() {
        super.onCreate();
        instanse = this;
    }

    /**
     * 获取当前app运行的AppContext
     *
     * @return
     */
    public static AppContext getInstanse() {
        return instanse;
    }

    public Properties getProperties() {
        return AppConfig.getAppConfig(this).get();
    }

    public void setProperty(String key, String vlaue) {
        AppConfig.getAppConfig(this).set(key, vlaue);
    }

    /**
     * 获取cookie时传AppConfig.CONF_COOKIE
     *
     * @param key
     * @return
     */
    public String getProperty(String key) {
        return AppConfig.getAppConfig(this).get(key);
    }

    public void removeProperty(String... key) {
        AppConfig.getAppConfig(this).remove(key);
    }

    /**
     * 清除App缓存
     */
    public void clearAppCache(){



    }

}
