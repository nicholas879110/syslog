package com.gome.log.interceptor;

import com.gome.log.aop.LogService;
import com.gome.log.entity.SystemLog;
import com.gome.log.queue.LogQueue;
import com.gome.log.util.JsonUtils;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.sql.Date;
import java.util.Calendar;
import java.util.UUID;

/**
 * Created by zhangliewei on 2016/6/8.
 */
public class SystemLogInterceptor implements MethodInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(SystemLogInterceptor.class);

    private LogQueue logQueue;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object result=null;

        if (invocation.getMethod().isAnnotationPresent(LogService.class)) {
            Date startDate=new Date(Calendar.getInstance().getTime().getTime());
            //参数类型列列表
            Type[] pt =  invocation.getMethod().getParameterTypes();
            //参数值
            Object[] object = invocation.getArguments();
            //参数的参数化的类型,里面的带有实际的参数类型
            Type[] types=invocation.getMethod().getGenericParameterTypes();
            StringBuffer buffer=new StringBuffer("");
            for (int i=0;i<types.length;i++){
                Type type=types[i];
                //处理参数
                buffer.append(type).append(":").append(JsonUtils.toJson(object[i]));
                if(i<types.length-1){
                    buffer.append(",");
                }
            }

            logger.debug("LOG clazz:[{}],method:[{}],params:[{}]",
                    invocation.getMethod().getDeclaringClass().getSimpleName(),
                    invocation.getMethod().getName(),buffer.toString());

            Throwable throwable=null;
            try {
                result=invocation.proceed();
            } catch (Throwable e) {
                throwable=e;
            }
            Date endDate=new Date(Calendar.getInstance().getTime().getTime());

            SystemLog systemLog=new SystemLog();
            systemLog.setId(UUID.randomUUID().toString().replace("-",""));
            LogService logService=invocation.getMethod().getAnnotation(LogService.class);
            systemLog.setBiz(logService.biz());
            systemLog.setClazz(invocation.getMethod().getDeclaringClass().getSimpleName());
            systemLog.setMethod(invocation.getMethod().getName());
            systemLog.setParams(buffer.toString());
            systemLog.setStartDate(startDate);
            systemLog.setEndDate(endDate);
            systemLog.setWasteTime(endDate.getTime()-startDate.getTime() );
            systemLog.setReturnObj(JsonUtils.toJson(result));
            if(throwable!=null){
                systemLog.setException(throwable.getMessage()+">>"
                        + throwable.getCause());
            }
            logger.debug("LOG [{}]", JsonUtils.toJson(systemLog));
            logQueue.offer(systemLog);
            if(throwable!=null){
                throw new Throwable(throwable);
            }
            return result;
        }

        return invocation.proceed();
    }

    public LogQueue getLogQueue() {
        return logQueue;
    }

    public void setLogQueue(LogQueue logQueue) {
        this.logQueue = logQueue;
    }
}
