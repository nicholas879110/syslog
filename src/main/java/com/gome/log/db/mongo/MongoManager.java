//package com.gome.log.db.mongo;
//
//import com.mongodb.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.net.UnknownHostException;
//
///**
// * @Description
// * @Author zhangliewei
// * @Date 2016/7/1
// * @Copyright(c) gome inc Gome Co.,LTD
// */
//public class MongoManager {
//
//    private static Logger logger= LoggerFactory.getLogger(MongoManager.class);
//
//    private static Mongo mongo;
//    private static DBCollection coll;
//    private static DB db;
//
//    static{
//        try {
//            MongoOptions options = new MongoOptions();
//            options.autoConnectRetry = true;
//            options.connectionsPerHost = 1000;
//            options.maxWaitTime = 5000;
//            options.socketTimeout = 0;
//            options.connectTimeout = 15000;
//            options.threadsAllowedToBlockForConnectionMultiplier = 5000;
//            //事实上，Mongo实例代表了一个数据库连接池，即使在多线程的环境中，一个Mongo实例对我们来说已经足够了
//            mongo = new Mongo(new ServerAddress(DBMongoConfig.getHost(),DBMongoConfig.getPort()),options);
//            //mongo = new Mongo(DBMongoConfig.getHost(),DBMongoConfig.getPort());
//            // or, to connect to a replica set, supply a seed list of members
//            // Mongo m = new Mongo(Arrays.asList(new ServerAddress("localhost",
//            // 27017),
//            // new ServerAddress("localhost", 27018),
//            // new ServerAddress("localhost", 27019)));
//
//            // 注意Mongo已经实现了连接池，并且是线程安全的。
//            // 大部分用户使用mongodb都在安全内网下，但如果将mongodb设为安全验证模式，就需要在客户端提供用户名和密码：
//            // boolean auth = db.authenticate(myUserName, myPassword);
//        } catch (UnknownHostException e) {
//            logger.error("get mongo instance failed,please check mongo db config!",e);
//        }
//    }
//
//    public static DB getDB(){
//        if(db==null){
//            db = mongo.getDB(MongoDBConstant.DEFAULT_DB);
//        }
//        return db;
//    }
//
//    public static DB getDb(String name){
//        if(db==null){
//            db = mongo.getDB(name);
//        }
//        return db;
//    }
//
//    public static Mongo getMong(){
//        return mongo;
//    }
//
//    public static DBCollection getColl(String collname){
//        return getDB().getCollection(collname);
//    }
//
//
//}
