package com.dragontec.besm.MechBuilder.mech;

import java.util.List;

public interface MechSheet<T> {

	public void setBudget(int mechPoints); //Set the budget for the mech
	public int getTotalBudget(); //Get the total budget without anything subtracted
	public int getRemainingBudget(); //Get the remaining mech point Total - used
	public T getAttribute(String s); //Search MechSheet for an Attribute by a name
	public void putAttribute(T t); //Put an attribute into the mech sheet. Replaces existing version
	public List<T> getAllAttributes(); //Get all attributes from the mech.
	public void deleteAttribute(String s); //Delete an Attribute by its name
		
}
