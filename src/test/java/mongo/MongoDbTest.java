package mongo;

import com.mongodb.*;
import com.mongodb.util.JSON;

import java.net.UnknownHostException;
import java.util.Date;

/**
 * @Description
 * @Author zhangliewei
 * @Date 2016/7/1
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class MongoDbTest {
    public static void main(String[] args) {
        try {
            Mongo mg = new Mongo();
            //查询所有的Database
            for (String name : mg.getDatabaseNames()) {
                System.out.println("dbName: " + name);
            }

            DB db = mg.getDB("test");
            //查询所有的聚集集合
            for (String name : db.getCollectionNames()) {
                System.out.println("collectionName: " + name);
            }

            MongoClient mongoClient=new MongoClient();


            DBCollection users = db.getCollection("users");

            //查询所有的数据
            DBCursor cur = users.find();
            while (cur.hasNext()) {
                System.out.println(cur.next());
            }
            System.out.println(cur.count());
            System.out.println(cur.getCursorId());
            System.out.println(JSON.serialize(cur));

            //增加操作
            BasicDBObject doc = new BasicDBObject("_id", "6").append("name", new BasicDBObject("username", "limingnihao").append("nickname", "黎明你好")).append("password", "123456")
                    .append("password", "123456").append("regionName", "北京").append("works", "5").append("birth", new Date());
            WriteResult result = users.insert(doc);
            System.out.println("insert-result: " + result);

            // 2.1查询 - one
            DBObject myDoc = users.findOne();
            System.out.println(myDoc);

            // 2.2 查询 - 数量
            System.out.println(users.getCount());

            // 2.3查询 - 全部
            DBCursor cursor = users.find();
            while (cursor.hasNext()) {
                System.out.println("全部--------" + cursor.next());
            }

            // 2.4查询 - 过滤 - 等于
            BasicDBObject query = new BasicDBObject("_id", "6");
            cursor = users.find(query);
            while (cursor.hasNext()) {
                System.out.println("_id=1--------" + cursor.next());
            }

            // 2.5查询 - 过滤条件 - 不等于
            query = new BasicDBObject("_id", new BasicDBObject("$ne", "1"));
            cursor = users.find(query);
            while (cursor.hasNext()) {
                System.out.println("_id!=1" + cursor.next());
            }

            // 2.6查询 - 过滤条件 - 20 < i <= 30
            query = new BasicDBObject("_id", new BasicDBObject("$gt", "1").append("$lte", "8"));
            cursor = users.find(query);
            while (cursor.hasNext()) {
                System.out.println("1<_id<=8" + cursor.next());
            }


            //edit
            DBObject search = users.findOne(new BasicDBObject("_id", "6"));
            BasicDBObject object = new BasicDBObject().append("$set", new BasicDBObject("passwords", "1211111")).append("$set", new BasicDBObject("birth1", new Date()));
            WriteResult result2 = users.update(search,object);//.update(search, object, true, true);
            System.out.println("update-result: " + result2);

            DBObject myDoc1 = users.findOne();
            System.out.println(myDoc1);

            DBObject search1 = users.findOne(new BasicDBObject("_id", "6"));
            WriteResult result1 = users.remove(search1);
            System.out.println("remove-result: " + result1);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
