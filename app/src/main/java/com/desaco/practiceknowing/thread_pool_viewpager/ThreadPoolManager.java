package com.desaco.practiceknowing.thread_pool_viewpager;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by desaco on 2018/4/16.
 * <p>
 * 线程池结合Callable、Future和FutureTask使用
 */
public class ThreadPoolManager {
    private ThreadPoolManager() {
    }

    private static ThreadPool mThreadPool;
    private ThreadPoolExecutor mExecutor;
    private int corePoolSize;
    private int maximumPoolSize;
    private long keepAliveTime;

    // 获取单例的线程池对象
    public static ThreadPool getThreadPoolInstance() {
        if (mThreadPool == null) {
            synchronized (ThreadPoolManager.class) {
                if (mThreadPool == null) {
                    int cpuNum = Runtime.getRuntime().availableProcessors();// 获取处理器数量
                    int threadNum = cpuNum * 2 + 1;// 根据cpu数量,计算出合理的线程并发数
//                    System.out.println("cpu num:" + cpuNum);
                    mThreadPool = new ThreadPool(threadNum, threadNum, 0L);// TODO Build设计模式
                }
            }
        }
        return mThreadPool;
    }

    // TODO Build设计模式
    public static class ThreadPool {
        private ThreadPoolExecutor mExecutor;
        private int corePoolSize;
        private int maximumPoolSize;
        private long keepAliveTime;

        private ThreadPool(int corePoolSize, int maximumPoolSize,
                           long keepAliveTime) {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.keepAliveTime = keepAliveTime;
        }

        public void executeRunnable(Runnable runnable) {
            if (runnable == null) {
                return;
            }

            if (mExecutor == null) {
                mExecutor = new ThreadPoolExecutor(corePoolSize,// 核心线程数
                        maximumPoolSize, // 最大线程数
                        keepAliveTime, // 闲置线程存活时间
                        TimeUnit.MILLISECONDS,// 时间单位
                        new LinkedBlockingDeque<Runnable>(),// 线程队列
                        Executors.defaultThreadFactory(),// 线程工厂
                        new ThreadPoolExecutor.AbortPolicy()// 队列已满,而且当前线程数已经超过最大线程数时的异常处理策略
                );
            }
//            Future<Integer> future = mExecutor.submit(runnable);

            mExecutor.execute(runnable);
        }

        // 从线程队列中移除对象
        public void cancel(Runnable runnable) {
            if (mExecutor != null) {
                mExecutor.getQueue().remove(runnable);
            }
        }
    }

    public void futureExcute(Callable<Object> calTask) {
        //提交任务并获取执行结果
        Future<Object> future = mExecutor.submit(calTask);
        try {
            if (future.get() != null) {
                //输出获取到的结果
                System.out.println("future.get()-->" + future.get());
            } else {
                //输出获取到的结果
                System.out.println("future.get()未获取到结果");
            }
        } catch (Exception ex) {

        }
    }

    public void futureTaskExcute(Callable<Object> calTask) {
        //提交任务并获取执行结果
        FutureTask<Object> futureTask = new FutureTask<>(calTask);
        //执行任务
        mExecutor.submit(futureTask);
        try {
            if (futureTask.get() != null) {
                //输出获取到的结果
                System.out.println("futureTask.get()-->" + futureTask.get());
            } else {
                //输出获取到的结果
                System.out.println("futureTask.get()未获取到结果");
            }
        } catch (Exception ex) {

        }


    }

}
