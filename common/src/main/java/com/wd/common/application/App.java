package com.wd.common.application;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.drawee.BuildConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.wd.common.exceptionhandler.CrashHandler;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/4/004<p>
 * <p>更改时间：2019/12/4/004<p>
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);

        CrashHandler.getInstance().init(this);

        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }

        ARouter.init(this);
    }
}
