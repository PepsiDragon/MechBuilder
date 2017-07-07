package com.dragontec.besm.main;



import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
public class Driver extends Application{

	public static void main(String[] args) {
		
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/Window.fxml"));
	    
        Scene scene = new Scene(root, 600, 641);
    
        stage.setTitle("Mech Builder");
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                stop();
            }
        });
        stage.show();
		
	}
	@Override
	public void stop(){
		
	}

}
