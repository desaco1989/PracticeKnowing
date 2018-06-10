cmake_minimum_required(VERSION 3.4.1)

# set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI})
# set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/../app/libs/${ANDROID_ABI})

# 编译出一个动态库 native-lib，源文件只有 src/main/cpp/caculate/native-lib.cpp
add_library( # Sets the name of the library. 加减乘除
             caculate-lib
             # Sets the library as a shared library.
             SHARED
             # Provides a relative path to your source file(s).
             src/main/cpp/caculate/Caculate.cpp )

add_library( # 生成的.so文件的名称,第二个.so 加密解密
              encryptDecode-lib
             # Sets the library as a shared library.
               SHARED
             # 提供的c++文件 NativeEncryptDecode.cpp encode_decode.c
               src/main/cpp/decrypt_encrypt/NativeEncryptDecode.cpp  src/main/cpp/decrypt_encrypt/encode_decode.c )

 add_library( # 第三个 SO ，JNI的一些属性
               jniAttr-lib
              # Sets the library as a shared library.
                SHARED
              # 提供的c++文件
                src/main/cpp/jni_attr/cplusplus_invoke_java.cpp )

add_library( #   第4个 SO ，C调用Java
               cCallJava-lib
              # Sets the library as a shared library.
                SHARED
              # 提供的c++文件
                src/main/cpp/jni_attr/c_call_java.c)
add_library( # 第5个 SO，JNI异常消息
               native_exception-lib
              # Sets the library as a shared library.
                SHARED
              # 提供的c++文件
                src/main/cpp/c_caculate/native_exception.c src/main/cpp/c_caculate/java_byte_jni.c src/main/cpp/c_caculate/jni_thread3.c )

# 找到预编译库 log_lib 并link到我们的动态库 native-lib中
find_library( # Sets the name of the path variable.
              log-lib
              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )
#########
target_link_libraries( # Specifies the target library.
                       caculate-lib
                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )

target_link_libraries( # Specifies the target library.与log库绑定
                       encryptDecode-lib
                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )

target_link_libraries( # Specifies the target library.与log库绑定
                       jniAttr-lib
                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )

target_link_libraries( # Specifies the target library.与log库绑定
                       cCallJava-lib
                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )

target_link_libraries( # Specifies the target library.与log库绑定
                       native_exception-lib
                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )