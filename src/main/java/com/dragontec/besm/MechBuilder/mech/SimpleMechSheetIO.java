package com.dragontec.besm.MechBuilder.mech;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class SimpleMechSheetIO {
	public static void write(MechSheet<SimpleMechAttribute> sheet, BufferedWriter out) throws IOException
	{
		int totalBudget = sheet.getTotalBudget();
		int remainingBudget = sheet.getRemainingBudget();
		int spentBudget = totalBudget - remainingBudget;
		List<SimpleMechAttribute> attributes = sheet.getAllAttributes();
		
		out.write("<MechSheet>");
		out.write("<Budget>");
		out.write("<TotalBudget>" + totalBudget + "</TotalBudget>");
		out.write("<RemainingBudget>" + remainingBudget + "</RemainingBudget>");
		out.write("<SpentBudget>" + spentBudget + "</SpentBudget>");
		out.write("</Budget>");
		out.write("<Attributes>");
		for(SimpleMechAttribute sma: attributes)
		{
			out.write("<Attribute>");
			out.write("<name>" + sma.getName() + "</name>");
			out.write("<effect>" + sma.getEffect() + "</effect>");
			out.write("<description>" + sma.getDescription() + "</description>");
			out.write("<cost>" + sma.getCost() + "</cost>");
			out.write("</Attribute>");
		}
		out.write("</Attributes>");
		out.write("</MechSheet>");
		
	}
	public static MechSheet<SimpleMechAttribute> load(File file) throws ParserConfigurationException, SAXException, IOException
	{
		MechSheet<SimpleMechAttribute> sheet = new SimpleMechSheet();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);
		
		String stbudget = doc.getElementsByTagName("TotalBudget").item(0).getTextContent();
		int totalBudget = Integer.parseInt(stbudget);
		sheet.setBudget(totalBudget);
		NodeList nl = doc.getElementsByTagName("Attribute");
		for(int i = 0; i < nl.getLength(); i++)
		{
			NodeList cl = nl.item(i).getChildNodes();
			String name = cl.item(0).getTextContent();
			String effect = cl.item(1).getTextContent();
			String description = cl.item(2).getTextContent();
			int cost = Integer.parseInt(cl.item(3).getTextContent());
			SimpleMechAttribute sma = new SimpleMechAttribute();
			sma.setName(name);
			sma.setEffect(effect);
			sma.setDescription(description);
			sma.setCost(cost);
			sheet.putAttribute(sma);
		}
		return sheet;
	}
}
