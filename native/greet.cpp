#include "TestJNI.h"
#include <stdio.h>
JNIEXPORT void JNICALL Java_TestJNI_greetings(JNIEnv *env,jobject jobj) 
{   
    printf("Hello from Visual C++!\n");
    printf("Also print Hello world\n");
    printf("Ankit and Lakshman\n");
    printf("Rakesh\n");
}