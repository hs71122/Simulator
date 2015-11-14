package org.cdapsimulator.models;

public class FBReference {

	private String refId; //fb-id of reference person
	private String refName;//fb-name of reference person
	private int recommendationState;//state weather ref person approve or not [0 - not approve / 1 - approve] 
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	public String getRefName() {
		return refName;
	}
	public void setRefName(String refName) {
		this.refName = refName;
	}
	public int getRecommendationState() {
		return recommendationState;
	}
	public void setRecommendationState(int recommendationState) {
		this.recommendationState = recommendationState;
	}
	
	
}
