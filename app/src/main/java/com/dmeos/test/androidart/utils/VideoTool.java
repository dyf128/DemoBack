package com.dmeos.test.androidart.utils;


public class VideoTool {
    public VideoTool(){}

    static {
        System.loadLibrary("avcodec-57");
        System.loadLibrary("avformat-57");
        System.loadLibrary("avutil-55");
        System.loadLibrary("swscale-4");
        System.loadLibrary("avfilter-6");
        System.loadLibrary("swresample-2");
        System.loadLibrary("ffmpegvideotool");
    }

    public native String getStringFromNative();
}
