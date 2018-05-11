package com.desaco.practiceknowing.thread_pool_viewpager;

/**
 * Created by desaco on 2018/4/16.
 */

public interface ICallback<T> {
    void onSuccess(Class<T> clazz);//成功回调

    void onFail(Class<T> clazz);//失败后回调
}
