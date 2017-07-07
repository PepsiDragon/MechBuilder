package com.dragontec.besm.MechBuilder.gui;





import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.dragontec.besm.MechBuilder.mech.MechSheet;
import com.dragontec.besm.MechBuilder.mech.SimpleMechAttribute;
import com.dragontec.besm.MechBuilder.mech.SimpleMechSheet;
import com.dragontec.besm.MechBuilder.mech.SimpleMechSheetIO;

import static com.dragontec.util.StreamCloserUtil.closeStream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

@SuppressWarnings("restriction")
public class GuiController  {

	@FXML private Label MessageLabel;
	@FXML private TextField NameField;
	@FXML private TextField EffectField;
	@FXML private TextField DescriptionField;
	@FXML private TextField CostField;
	@FXML private TextField MechPoints;
	@FXML private TextField MechPointsLeft;
	@FXML private TableColumn<SimpleMechAttribute, String> NameColumn;
	@FXML private TableColumn<SimpleMechAttribute, String> EffectColumn;
	@FXML private TableColumn<SimpleMechAttribute, String> DescriptionColumn;
	@FXML private TableColumn<SimpleMechAttribute, Integer> CostColumn;
	@FXML private TableView<SimpleMechAttribute> SheetTable;
	private MechSheet<SimpleMechAttribute> sheet;
	public GuiController(){
		sheet = new SimpleMechSheet();
		/*System.out.println(NameColumn);
		System.out.println(EffectColumn);
		System.out.println(DescriptionColumn);
		System.out.println(CostColumn);*/
		
		}
	
	@FXML protected void updateMechPoints(ActionEvent event)
	{
		
		try{
			int mp = Integer.parseInt(MechPoints.getText());
			sheet.setBudget(mp);
			MechPointsLeft.setText("" + sheet.getRemainingBudget());
		}catch(NumberFormatException e)
		{
			MessageLabel.setText("Current Mech Points Invalid");
		}
		
	}
	
	@FXML protected void putAttribute(ActionEvent event)
	{
		
		try{
			String name = NameField.getText();
			String effect = EffectField.getText();
			String descr = DescriptionField.getText();
			int cost = Integer.parseInt(CostField.getText());
			SimpleMechAttribute sma = new SimpleMechAttribute();
			sma.setName(name);
			sma.setEffect(effect);
			sma.setDescription(descr);
			sma.setCost(cost);
			sheet.putAttribute(sma);
			updateTable();
		}catch(NumberFormatException e){
			MessageLabel.setText("Invalid Input for cost");
		}
	}
	@FXML protected void SaveMech(ActionEvent event)
	{
		java.awt.FileDialog dialog = new java.awt.FileDialog((java.awt.Frame) null);
		dialog.setTitle("Save File");
		dialog.setVisible(true);
		File[] files = dialog.getFiles();
		if(files.length > 0)
		{
			BufferedWriter out = null;
			try {
				out = new BufferedWriter(new FileWriter(files[0]));
				SimpleMechSheetIO.write(sheet, out);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				MessageLabel.setText("Error saving File");
			}finally
			{
				closeStream(out);
				
			}
		}
		
		
	}
	@FXML protected void LoadMech(ActionEvent event)
	{
		java.awt.FileDialog dialog = new java.awt.FileDialog((java.awt.Frame) null);
		dialog.setTitle("Load File");
		dialog.setVisible(true);
		File[] files = dialog.getFiles();
		if(files.length > 0)
		{
			try {
				sheet = SimpleMechSheetIO.load(files[0]);
				MechPoints.setText("" + sheet.getTotalBudget());
				updateTable();
			} catch (ParserConfigurationException e) {
				MessageLabel.setText("Error loading File");
			} catch (SAXException e) {
				MessageLabel.setText("Error loading File");
			} catch (IOException e) {
				MessageLabel.setText("Error loading File");
			}
		}
		
	}
	@FXML protected void DeleteAttribute(ActionEvent event)
	{
		String name = NameField.getText();
		sheet.deleteAttribute(name);
		updateTable();
	}
	private void updateTable()
	{
		NameColumn.setCellValueFactory(new PropertyValueFactory("name"));
		EffectColumn.setCellValueFactory(new PropertyValueFactory("effect"));
		DescriptionColumn.setCellValueFactory(new PropertyValueFactory("description"));
		CostColumn.setCellValueFactory(new PropertyValueFactory("cost"));
		ObservableList<SimpleMechAttribute> olsma =FXCollections.observableList(sheet.getAllAttributes());
		SheetTable.setItems(olsma);
	}
}
