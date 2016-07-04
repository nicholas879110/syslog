package com.gome.log.queue;

import com.gome.log.entity.SystemLog;

/**
 * @Description
 * @Author zhangliewei
 * @Date 2016/7/4
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public interface LogQueue {

    /**
     *  添加元素如果队列满则返回false
     * @param o
     */
    public  boolean offer(SystemLog o);

    /**
     * 添加元素如果队列满则抛出异常
     * @param o
     * @return
     */
    public  boolean add(SystemLog o);

    /**
     * 队列出,若队列为空则返回null
     *
     * @return
     */
    public  SystemLog poll();



    /**
     * 队列出,若队列为空则抛出异常
     *
     * @return
     */
    public  SystemLog remove();


    /**
     * 返回队列是否为空
     * @return
     */
    public  boolean isEmpty();
}
