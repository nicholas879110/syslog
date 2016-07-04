package com.gome.log.queue.common;

import com.gome.log.entity.SystemLog;
import com.gome.log.queue.LogQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description 普通队列需要通过消费线程进行消耗
 * @Author zhangliewei
 * @Date 2016/7/4
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class LogBlockingQueue implements LogQueue {

    public static BlockingQueue<SystemLog> queue = new LinkedBlockingQueue<SystemLog>(5000);

    /**
     *  添加元素如果队列满则返回false
     * @param o
     */
    public  boolean offer(SystemLog o) {
        return queue.offer(o);
    }

    /**
     * 添加元素如果队列满则抛出异常
     * @param o
     * @return
     */
    public  boolean add(SystemLog o){
        return queue.add(o);
    }

    /**
     * 队列出,若队列为空则返回null
     *
     * @return
     */
    public  SystemLog poll() {
        return queue.poll();
    }



    /**
     * 队列出,若队列为空则抛出异常
     *
     * @return
     */
    public  SystemLog remove() {
        return queue.remove();
    }


    /**
     * 返回队列是否为空
     * @return
     */
    public  synchronized boolean isEmpty(){
        return queue.isEmpty();
    }
}
