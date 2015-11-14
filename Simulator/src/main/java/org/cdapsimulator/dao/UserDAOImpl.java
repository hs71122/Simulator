package org.cdapsimulator.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;
import org.cdapsimulator.models.User;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;

public class UserDAOImpl implements UserDAO {

	private MongoOperations mongoOperation;

	public void setMongoOperation(MongoOperations mongoOperations) {
		this.mongoOperation = mongoOperations;
	}

	public void create(User user) {
		// TODO Auto-generated method stub
		mongoOperation.insert(user);
	}

	public User findByUserId(String userId) throws Exception {
		// TODO Auto-generated method stub
		Query query = new Query(new Criteria().andOperator(Criteria.where(
				"userId").is(userId)));
		User user = this.mongoOperation.findOne(query, User.class);
		return user;
	}

	public User findByUserIdAndPassword(String userId, String password) {
		// TODO Auto-generated method stub
		List<Criteria> criterias = new ArrayList<Criteria>();
		criterias.add(Criteria.where("userId").is(userId));
		criterias.add(Criteria.where("password").is(password));

		Query query = new Query(new Criteria().andOperator(criterias
				.toArray(new Criteria[criterias.size()])));
		User user = this.mongoOperation.findOne(query, User.class);
		return user;

	}

	public JSONObject findByUserIdJSON(String userId) throws Exception {
		// TODO Auto-generated method stub
		DBCollection dbCollection = mongoOperation.getCollection(User.class
				.getSimpleName());
		BasicDBObject searchQuery = new BasicDBObject()
				.append("userId", userId);

		/**
		 * same as... BasicDBObject searchQuery = new BasicDBObject();
		 * searchQuery.put("userId", userId);
		 * 
		 * or
		 * 
		 * BasicDBObject searchQuery = new BasicDBObject("userId", userI);
		 * 
		 * put is little speedy than others
		 */

		DBCursor cursor = dbCollection.find(searchQuery, new BasicDBObject());

		JSONObject result = null;

		if (cursor.hasNext()) {

			result = new JSONObject(cursor.next().toString());

		}

		return result;
	}

	public void update(User user) {
		// TODO Auto-generated method stub
		this.mongoOperation.save(user);
	}

	public void deleteByUserId(String userId) {
		// TODO Auto-generated method stub
		Query query = new Query(Criteria.where("userId").is(userId));
		mongoOperation.remove(query);
	}

	public boolean addFeedBackForUser(String userId, JSONObject feedBackData)
			throws Exception {
		// TODO Auto-generated method stub
		DBCollection dbCollection = mongoOperation.getCollection(User.class
				.getSimpleName());

		BasicDBObject feedbacks = new BasicDBObject();
		feedbacks.put("feedbacks",
				(DBObject) JSON.parse(feedBackData.toString()));

		/**
		 * if feedbacks field not already in the document don't worry new field
		 * add then push values $push use to update jsonarry fields
		 * 
		 */
		BasicDBObject updateDocument = new BasicDBObject();
		updateDocument.put("$push", feedbacks);

		BasicDBObject searchQuery = new BasicDBObject()
				.append("userId", userId);

		dbCollection.update(searchQuery, updateDocument);

		return true;
	}

	public boolean addFBAccountForUser(String userId, JSONObject fbAccount)
			throws Exception {
		// TODO Auto-generated method stub

		System.out.println("/n---------/nStore FB Account for : " + userId);
		DBCollection dbCollection = mongoOperation.getCollection(User.class
				.getSimpleName());

		/**
		 * JSON object insert into fbAccount field
		 */
		BasicDBObject fbAccountDBObject = new BasicDBObject();
		fbAccountDBObject.put("fbAccount",
				(DBObject) JSON.parse(fbAccount.toString()));

		/**
		 * $set used to update
		 */
		BasicDBObject updateDocument = new BasicDBObject();
		updateDocument.put("$set", fbAccountDBObject);

		BasicDBObject searchQuery = new BasicDBObject()
				.append("userId", userId);

		dbCollection.update(searchQuery, updateDocument);

		return true;
	}

	public boolean addAppReference(String userId, JSONObject appRef)
			throws Exception {
		// TODO Auto-generated method stub
		DBCollection dbCollection = mongoOperation.getCollection(User.class
				.getSimpleName());

		BasicDBObject feedbacks = new BasicDBObject();
		feedbacks.put("fbAccount.appRef",
				(DBObject) JSON.parse(appRef.toString()));

		BasicDBObject updateDocument = new BasicDBObject();
		updateDocument.put("$push", feedbacks);
		//$push used for add to JSONObjects into JSONArray field

		BasicDBObject searchQuery = new BasicDBObject()
				.append("userId", userId);

		dbCollection.update(searchQuery, updateDocument);

		return true;
	}

	public JSONArray findUserByName(String searchName) throws Exception {
		// TODO Auto-generated method stub

		DBCollection dbCollection = mongoOperation.getCollection(User.class
				.getSimpleName());

		/**
		 * { <field>: { $regex: <pattern>, $options: <options> } } options -
		 * i(Case insensitivity to match upper and lower cases. For an example)
		 */

		/**
		 * pattern = ^KEYWORD.* ^KEYWORD -> Finds KEYWORD that must match at the
		 * beginning of the line. .* -> . any character , * -> must in 0 or more
		 */
		BasicDBObject pattern = new BasicDBObject();
		pattern.put("$regex", "^" + searchName + ".*");
		pattern.put("$options", "i");

		BasicDBObject regexQuery = new BasicDBObject();
		regexQuery.put("firstName", pattern);

		DBCursor cursor = dbCollection.find(regexQuery, new BasicDBObject());

		JSONArray users = new JSONArray();

		while (cursor.hasNext()) {

			users.put(new JSONObject(cursor.next().toString()));

		}

		return users;
	}

	public boolean updateFeedBacksTrustLevel(String userId, double trustLevel)
			throws Exception {
		// TODO Auto-generated method stub

		DBCollection dbCollection = mongoOperation.getCollection(User.class
				.getSimpleName());

		BasicDBObject profileTrust = new BasicDBObject();
		profileTrust.put("profileTrust.feedBacks",trustLevel);
		profileTrust.put("profileTrust.trustLevel",trustLevel);

		BasicDBObject updateDocument = new BasicDBObject();
		updateDocument.put("$set", profileTrust);

		BasicDBObject searchQuery = new BasicDBObject()
				.append("userId", userId);

		dbCollection.update(searchQuery, updateDocument);

		return true;
	}

}
