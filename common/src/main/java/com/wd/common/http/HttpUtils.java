package com.wd.common.http;

/**
 * <p>文件描述：<p>
 * <p>作者：吴新仲<p>
 * <p>创建时间：2019/12/4/004<p>
 * <p>更改时间：2019/12/4/004<p>
 */
public class HttpUtils {
    private static final HttpUtils ourInstance = new HttpUtils();

    public static HttpUtils getInstance() {
        return ourInstance;
    }

    private HttpUtils() {
    }


}
