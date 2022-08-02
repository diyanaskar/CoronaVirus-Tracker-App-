package io.javamain.Coronavirustracker;

public class LocationStatsModel {

	public String state;
	public String country;
	public int LatestTotalCases;
	public int diffFromPrevDay;
	
	
	public int getDiffFromPrevDay() {
		return diffFromPrevDay;
	}
	public void setDiffFromPrevDay(int diffFromPrevDay) {
		this.diffFromPrevDay = diffFromPrevDay;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatestTotalCases() {
		return LatestTotalCases;
	}
	public void setLatestTotalCases(int latestTotalCases) {
		LatestTotalCases = latestTotalCases;
	}
	@Override
	public String toString() {
		return "LocationStatsModel [state=" + state + ", country=" + country + ", LatestTotalCases=" + LatestTotalCases
				+ "]";
	}
	
	
}
