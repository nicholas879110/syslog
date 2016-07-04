package com.gome.log.persistent.mongo;

import com.gome.log.entity.SystemLog;
import com.gome.log.persistent.LogPersistenceService;
import com.gome.log.persistent.mongo.constant.MongoDBConstant;
import com.gome.log.persistent.mongo.manager.MongoManager;
import com.gome.log.util.JsonUtils;
import com.mongodb.*;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author zhangliewei
 * @Date 2016/7/4
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class MongoPersistent implements LogPersistenceService {

    private static final Logger logger= LoggerFactory.getLogger(MongoPersistent.class);

    @Override
    public void addLog(SystemLog systemLog) {
        if(systemLog!=null){
            DB db=MongoManager.getDB(MongoDBConstant.SYSLOG_DB);
            DBCollection logs = db.getCollection(MongoDBConstant.SYSLOG_DB_COLLECTIONS);
            DBObject object=convertToDbObject(systemLog);
            WriteResult result = logs.insert(object);
            logger.debug("insert-result:{}" , result);
        }
    }

    @Override
    public void addLog(List<SystemLog> systemLogList) {
        if(systemLogList!=null&&systemLogList.size()>0){
            List<DBObject> list=new ArrayList<DBObject>(systemLogList.size());
            for (SystemLog systemLog:systemLogList){
                list.add(convertToDbObject(systemLog));
            }
            DB db=MongoManager.getDB(MongoDBConstant.SYSLOG_DB);
            DBCollection logs = db.getCollection(MongoDBConstant.SYSLOG_DB_COLLECTIONS);
            WriteResult result = logs.insert(list);
            logger.debug("insert-result:{}" , result);
        }


    }

    @Override
    public void update(SystemLog systemLog) {
        DB db=MongoManager.getDB(MongoDBConstant.SYSLOG_DB);
        DBCollection logs = db.getCollection(MongoDBConstant.SYSLOG_DB_COLLECTIONS);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(systemLog.getId()));

        DBObject dbObject=convertToDbObject(systemLog);

        DBObject updateSetValue=new BasicDBObject("$set",dbObject);
        logs.update(query, updateSetValue);
    }

    @Override
    public void update(List<SystemLog> systemLogList) {
        for (SystemLog systemLog:systemLogList){
            this.update(systemLog);
        }
    }

    @Override
    public void delete(String id) {
        DB db=MongoManager.getDB(MongoDBConstant.SYSLOG_DB);
        DBCollection logs = db.getCollection(MongoDBConstant.SYSLOG_DB_COLLECTIONS);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));
        logs.remove(query);
    }

    @Override
    public void delete(List<String> ids) {
        for (String id:ids){
            this.delete(id);
        }
    }

    private SystemLog convertToSystemlog(DBObject dbObject){
        if(dbObject==null){
            return null;
        }
        String r=dbObject.toString();
        if(StringUtils.isBlank(r)){
            return null;
        }
        SystemLog systemLog= JsonUtils.toObject(r,SystemLog.class);
        ObjectId ID=(ObjectId)dbObject.get("_id");
        systemLog.setId(ID.toString());
        return systemLog;
    }

    /**
     * 日志查询不需要ID
     * @param systemLog
     * @return
     */
    private DBObject convertToDbObject(SystemLog systemLog){
        if (systemLog==null){
            return null;
        }
        BasicDBObject doc=new BasicDBObject()
                .append("_id",new ObjectId())
                .append("biz", systemLog.getBiz() == null ? "" : systemLog.getBiz())
                .append("clazz", systemLog.getClazz() == null ? "" : systemLog.getClazz())
                .append("method",systemLog.getMethod()==null?"":systemLog.getMethod())
                .append("params",systemLog.getParams()==null?"":systemLog.getParams())
                .append("returnObj",systemLog.getReturnObj()==null?"":systemLog.getReturnObj())
                .append("exception",systemLog.getException()==null?"":systemLog.getException())
                .append("startDate",systemLog.getStartDate()==null?null:systemLog.getStartDate())
                .append("endDate", systemLog.getEndDate() == null ? null : systemLog.getEndDate())
                .append("wasteTime", systemLog.getWasteTime());
        return doc;
    }

    public static void main(String[] args) {
        ObjectId id=new ObjectId();
        System.out.println(id.toString());

    }
}
