package com.yuan.mongodb;

import java.net.UnknownHostException;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoTest {

	public static void main(String[] args) throws UnknownHostException {
		MongoCollection<Document> mongodb = getCollection();
		insertData(mongodb);
		queryData(mongodb);
		delData(mongodb);
	}

	@SuppressWarnings("resource")
	public static MongoCollection<Document> getCollection() throws UnknownHostException {
		// 实例化Mongo对象，
		MongoClient mongoClient = new MongoClient();
		// 连接名为yourdb的数据库，假如数据库不存在的话，mongodb会自动建立
		MongoDatabase db = mongoClient.getDatabase("test");
		// 从Mongodb中获得名为myData的数据集合，如果该数据集合不存在，Mongodb会为其新建立
		MongoCollection<Document> collection = db.getCollection("myData");
		return collection;
	}

	public static void insertData(MongoCollection<Document> collection) {
		// 创建要保存的document
		Document document = new Document();
		document.put("name", "yuaneg");
		// 将新建立的document保存到collection中去
		collection.insertOne(document);

	}

	public static void queryData(MongoCollection<Document> collection) {
		// 创建要查询的document
		Document document = new Document();
		// 查询条件 空的时候查询所有
		document.put("name", "yuaneg");
		// 使用collection的find方法查找document
		FindIterable<Document> cursor = collection.find(document);
		// 循环输出结果
		for (Document d : cursor) {
			System.out.println(d.toJson());
		}
	}
	
	public static void delData(MongoCollection<Document> collection){
		//collection.deleteMany(new Document());
	}
	
}
