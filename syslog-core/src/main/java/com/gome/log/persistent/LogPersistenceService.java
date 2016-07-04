package com.gome.log.persistent;

import com.gome.log.entity.SystemLog;

import java.util.List;

/**
 * 用户可自定义接口实现日志持久化
 * Created by zhangliewei on 2016/6/8.
 */
public interface LogPersistenceService {

    void addLog(SystemLog systemLog);

    void addLog(List<SystemLog> systemLogList);

    void update(SystemLog systemLog);

    void update(List<SystemLog> systemLogList);

    void delete(String id);

    void delete(List<String> ids);

//    List<SystemLog> queryLogs(Conditions );

}
