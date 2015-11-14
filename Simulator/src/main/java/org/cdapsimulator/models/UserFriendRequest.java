package org.cdapsimulator.models;


public class UserFriendRequest {

	private String friendRequestId;
	private int requestType;//REQUESTER : 0 | ACCEPTER : 1
	
	public String getFriendRequestId() {
		return friendRequestId;
	}
	public void setFriendRequestId(String friendRequestId) {
		this.friendRequestId = friendRequestId;
	}
	public int getRequestType() {
		return requestType;
	}
	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}
	
	
	
}
