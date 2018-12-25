package com.example.geeknews.app;

import android.os.Environment;

import java.io.File;

/**
 * 项目名：xiaoshixun
 * 包名：  notice.liangxq.com.mymvp.app
 * 文件名：Constants
 * 创建者：liangxq
 * 创建时间：2018/12/12  15:24
 * 描述：TODO
 */
public class Constants {
    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "codeest" + File.separator + "GeekNews";

    public static final String FILE_PROVIDER_AUTHORITY="com.codeest.geeknews.fileprovider";

    //网络缓存的地址
    public static final String PATH_DATA = Myapp.getMyapp().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    //Shared
    public static final String SP_NIGHT_MODE = "night_mode";

    public static final String SP_NO_IMAGE = "no_image";

    public static final String SP_AUTO_CACHE = "auto_cache";

    public static final String SP_CURRENT_ITEM = "current_item";

    public static final String SP_LIKE_POINT = "like_point";

    public static final String SP_VERSION_POINT = "version_point";

    public static final String SP_MANAGER_POINT = "manager_point";
}
