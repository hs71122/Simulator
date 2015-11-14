package org.cdapsimulator.models;

import java.util.List;

public class FBUserProfile {

	private String userId;//fb-user id
	private String name;//fb name
	private String profilePicture;//fb profile picture url 
	private int numOfPositiveComments;//num of positive from whole comments
	private int numOfNegativeComments;//num of negative from whole comments
	private double overallSentimentScore;//overall SentimentScore for all comment [-1 to 1]
	private String overallSentimentText;//overall SentimentText for all comment [positive/negative/neutral]
	private List<FBReference> references;//references list
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	public int getNumOfPositiveComments() {
		return numOfPositiveComments;
	}
	public void setNumOfPositiveComments(int numOfPositiveComments) {
		this.numOfPositiveComments = numOfPositiveComments;
	}
	public int getNumOfNegativeComments() {
		return numOfNegativeComments;
	}
	public void setNumOfNegativeComments(int numOfNegativeComments) {
		this.numOfNegativeComments = numOfNegativeComments;
	}
	public double getOverallSentimentScore() {
		return overallSentimentScore;
	}
	public void setOverallSentimentScore(double overallSentimentScore) {
		this.overallSentimentScore = overallSentimentScore;
	}
	public String getOverallSentimentText() {
		return overallSentimentText;
	}
	public void setOverallSentimentText(String overallSentimentText) {
		this.overallSentimentText = overallSentimentText;
	}
	public List<FBReference> getReferences() {
		return references;
	}
	public void setReferences(List<FBReference> references) {
		this.references = references;
	}
	
	
}
