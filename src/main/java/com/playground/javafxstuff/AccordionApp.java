package com.playground.javafxstuff;

import com.playground.javafxstuff.components.CustomAccordion;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AccordionApp extends Application {
	
    public static void main(String...args)
    {    	
    	launch(args);
    }
    
	@Override
	public void start(Stage primaryStage) throws Exception {
    	BorderPane root = new BorderPane();
    	CustomAccordion customAccordion = new CustomAccordion();
    	
    	customAccordion.getPanes().add(createTitledPane("One"));
    	customAccordion.getPanes().add(createTitledPane("Two"));
    	customAccordion.getPanes().add(createTitledPane("Three"));
    	
    	root.setTop(customAccordion);    	
    	Scene scene = new Scene(root, 200, 200);
    	primaryStage.setScene(scene);
    	primaryStage.show();
	}
	
	private TitledPane createTitledPane(String label) {
		TitledPane titledPane = new TitledPane();
		titledPane.setText(label);
		
		Button button = createButton(titledPane);
		titledPane.setContent(button);
		return titledPane;
	}
	
	private Button createButton(TitledPane titledPane) {
		Button button = new Button(titledPane.getText());
		button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			System.err.println(String.format("Button on title pane %s clicked.", titledPane.getText()));
			
		});
		return button;
	}
}
