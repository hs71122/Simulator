package org.cdapsimulator.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
public class User {

	@Id
	private String id;
	private String userId;
	private String password;
	private String firstName;
	private String lastName;
	private String address;
	private Date dateOfBirth;
	private String contactNo;
	private CurrentLocation currentLocation;
	private List<UserFriendRequest> friendRequests; 
	private ProfileTrust profileTrust;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	public List<UserFriendRequest> getFriendRequests() {
		return friendRequests;
	}
	public void setFriendRequests(List<UserFriendRequest> friendRequests) {
		this.friendRequests = friendRequests;
	}
	public CurrentLocation getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(CurrentLocation currentLocation) {
		this.currentLocation = currentLocation;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public ProfileTrust getProfileTrust() {
		return profileTrust;
	}
	public void setProfileTrust(ProfileTrust profileTrust) {
		this.profileTrust = profileTrust;
	}
	
}
