#include <stdio.h>
#include <stdlib.h>
#include "com_dmeos_test_androidart_utils_VideoTool.h"
#include "include/libavutil/avutil.h"
#include "include/libavcodec/avcodec.h"
#include "include/libavformat/avformat.h"

JNIEXPORT jstring JNICALL Java_com_dmeos_test_androidart_utils_VideoTool_getStringFromNative
        (JNIEnv *env, jobject obj) {
    char info[10000] = { 0 };
    sprintf(info, "%s\n", avcodec_configuration());
    return (*env)->NewStringUTF(env, info);
}
