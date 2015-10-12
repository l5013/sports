package com.lal.sp.util;

import java.util.Comparator;

import com.lal.sp.bean.SportsMatch;

public class DistanceComparator implements Comparator<SportsMatch>{
	
	private Double lng;
	private Double lat;
	
	
	public DistanceComparator(Double lng, Double lat) {
		super();
		this.lng = lng;
		this.lat = lat;
	}


	@Override
	public int compare(SportsMatch o1, SportsMatch o2) {
		
		Double distance1 = LLSquarePointUtil.Distance(lng, lat, o1.getLongitude(), o1.getLatitude());
		Double distance2 = LLSquarePointUtil.Distance(lng, lat, o2.getLongitude(), o2.getLatitude());
		
		return distance1.compareTo(distance2);
	}

}
