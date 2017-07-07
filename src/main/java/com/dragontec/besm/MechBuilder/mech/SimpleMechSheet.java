package com.dragontec.besm.MechBuilder.mech;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleMechSheet implements MechSheet<SimpleMechAttribute> {

	private int mechpoints;
	Map<String, SimpleMechAttribute> attributes;
	public SimpleMechSheet()
	{
		attributes = new HashMap<String, SimpleMechAttribute>();
	}
	public void setBudget(int mechPoints) {
		// TODO Auto-generated method stub
		this.mechpoints = mechPoints;
	}

	public int getTotalBudget() {
		
		return mechpoints;
	}

	public int getRemainingBudget() {
		 int totalCost = 0;
		 for(SimpleMechAttribute sma: attributes.values())
		 {
			 totalCost += sma.getCost();
		 }
		return mechpoints - totalCost;
	}

	public SimpleMechAttribute getAttribute(String s) {
		return attributes.get(s);
	}

	public void putAttribute(SimpleMechAttribute t) {
		attributes.put(t.getName(), t);
		
	}

	public List<SimpleMechAttribute> getAllAttributes() {
		List<SimpleMechAttribute> lattr = new ArrayList<SimpleMechAttribute>();
		for(SimpleMechAttribute sma: attributes.values()){
			lattr.add(sma);
		}
		return lattr;
	}
	public void deleteAttribute(String s)
	{
		attributes.remove(s);
	}

	

}
