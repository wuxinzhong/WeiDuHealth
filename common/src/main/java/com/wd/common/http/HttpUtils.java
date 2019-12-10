package com.wd.common.http;

import com.wd.common.constant.Constant;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <p>文件描述：网络工具类 <p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/4/004<p>
 * <p>更改时间：2019/12/4/004<p>
 */
public class HttpUtils {
    //单例
    private static final HttpUtils ourInstance = new HttpUtils();
    private Constant mConstant;

    public static HttpUtils getInstance() {
        return ourInstance;
    }

    private HttpUtils() {
    }


    //获取数据
    public void getData() {
        //拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        HttpLoggingInterceptor interceptor = loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient build = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit build1 = new Retrofit.Builder()
                .client(build)
//                .baseUrl("http://172.17.8.100/health/")//内网接口
                .baseUrl("http://mobile.bwstudent.com/health/")//外网接口
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mConstant = build1.create(Constant.class);
    }

    public Constant getConstant() {
        getData();
        return mConstant;
    }
}
