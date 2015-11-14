package org.cdapsimulator.dao;

import org.json.JSONArray;
import org.json.JSONObject;
import org.cdapsimulator.models.User;

public interface UserDAO {

	/**
	 * Insert new User details
	 * @param user
	 * @throws Exception
	 */
	public void create(User user) throws Exception;
	
	/**
	 * Find User by userId and password 
	 * @param userId
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public User findByUserIdAndPassword(String userId,String password) throws Exception;
	
	/**
	 * Find User by userId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public User findByUserId(String userId) throws Exception;
	
	/**
	 * Find User by userId for JSON result
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public JSONObject findByUserIdJSON(String userId) throws Exception;
	
	/**
	 * Update User details
	 * @param user
	 * @throws Exception
	 */
	public void update(User user) throws Exception;
	
	/**
	 * Delete User details by userId
	 * @param userId
	 * @throws Exception
	 */
	public void deleteByUserId(String userId) throws Exception;
	
	/**
	 * add feed back for the user.This feed back is a JSONObject
	 * @param userId
	 * @param feedBackData
	 * @return
	 */
	public boolean addFeedBackForUser(String userId,JSONObject feedBackData) throws Exception;
	
	/**
	 * add fb account
	 * @param userId
	 * @param fbAccount
	 * @return
	 * @throws Exception
	 */
	public boolean addFBAccountForUser(String userId,JSONObject fbAccount) throws Exception;
	
	/**
	 * add app Reference 
	 * @param userId
	 * @param appRef
	 * @return
	 * @throws Exception
	 */
	public boolean addAppReference(String userId,JSONObject appRef)throws Exception;
	
	/**
	 * findUserByKeyWord
	 * @param keyWork
	 * @return
	 * @throws Exception
	 */
	public JSONArray findUserByName(String searchName)throws Exception; 
	
	/**
	 * Update the trust level according to feedbacks
	 * @param userId
	 * @param trustLevel
	 * @return
	 * @throws Exception
	 */
	public boolean updateFeedBacksTrustLevel(String userId,double trustLevel)throws Exception; 
}
