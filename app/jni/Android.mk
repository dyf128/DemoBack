LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := ffmpegvideotool
LOCAL_SRC_FILES := videotool.c
LOCAL_LDLIBS := -llog -ljnigraphics -lz
LOCAL_SHARED_LIBRARIES := libavformat libavcodec libswscale libavutil libavfilter libswresample

include $(BUILD_SHARED_LIBRARY)
$(call import-module, ffmpeg/android/arm)