package com.desaco.practiceknowing.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by desaco on 2018/6/3.
 */

public class LockUtils {
    public void reentrantLockApi() {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.tryLock();
        reentrantLock.unlock();
    }

    public void lockApi() {
        Lock lock = new Lock() {
            @Override
            public void lock() {

            }

            @Override
            public void lockInterruptibly() throws InterruptedException {

            }

            @Override
            public boolean tryLock() {
                return false;
            }

            @Override
            public boolean tryLock(long l, @NonNull TimeUnit timeUnit) throws InterruptedException {
                return false;
            }

            @Override
            public void unlock() {

            }

            @NonNull
            @Override
            public Condition newCondition() {
                return null;
            }
        };
        lock.lock();
        lock.unlock();

    }

    public  synchronized void  synchronizedApi() {
        Log.e("desaco","synchronized test");
    }
}
