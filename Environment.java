/*
 * Environment.java
 * CS 225 Spring 2021
 * Written by: Calla Robison 
 * Last edited: 5/4/2021
 * Base: to handle the GUI for the free fall Motion portion of the program
 * 
 * Purpose: The main gui class that handles the other gui pane classes and the main start method. In charge of 
 * event handlers and making the graphs for each pane.
 *  Attributes: 
 *  		-startScene:Scene
 *  		-graph2DScene:Scene -- Scene for 2D motion graph
 *  		-graphPMScene:Scene -- Scene for projectile motion graph
 *  		-graphFFScene:Scene -- Scene for free fall motion graph
 *  		-topPane:BorderPane -- The main pane the other panes are centered on
 *  		-reg2DMotionPane:Pane  -- Pane that controls 2D motion stuff
 *  		-freeFallPane:Pane -- Pane that controls free fall motion stuff
 *  		-projectilePane:Pane -- Pane that controls projectile otion stuff
 *  		-title:Text 
 *  		-option:Text -- Pick an option text
 *  		-projectileBttn:Button 
 *  		-reg2DMotionBttn:Button 
 *  		-freeFallBttn:Button 
 *  		-newton:Image - picture of newton
 *  		-newtonView:ImageView -- gui view of newton
 *  		
 *
 * Methods:
 *         +transitionReg2DMotionPane():void 
 *         +transitionFreeFallPane():void 
 *         +transitionProjectilePane():void 
 *         +goBack():void -- go back to previous screen
 *         +make2DGraph():void -- makes graph for 2D motion
 *         +makeFFGraph():void -- makes graph for freefall motion
 *         +makePMGraph():void -- makes graph for projectile motion
 *         setters and getters for all attributes 
 * 
 */ 


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;



public class Environment extends Application{

	private Scene startScene, graph2DScene, graphPMScene, graphFFScene;

	private Button projectileBttn, reg2DMotionBttn, freeFallBttn;
	
	private Pane startPane;
	private BorderPane topPane = new BorderPane();
	private reg2DMotionPane reg2DMotionPane = new reg2DMotionPane();
	private freeFallPane freeFallPane = new freeFallPane();
	private projectilePane projectilePane = new projectilePane();
	
	private Text title, option;
	private Image newton;
	private ImageView newtonView;


	//Constructor
	public Environment(){
		
		startPane = new Pane();

		
		projectileBttn = new Button("Projectile Motion");
		reg2DMotionBttn = new Button("Regular 2D Motion");
		freeFallBttn = new Button("Free Fall Motion");
		
		
		projectileBttn.setStyle("-fx-background-color: cadetblue");
		reg2DMotionBttn.setStyle("-fx-background-color: cadetblue");
		freeFallBttn.setStyle("-fx-background-color: cadetblue");
		
//		projectileBttn.setStyle("-fx-font:24 'Brush Script MT'");
//		reg2DMotionBttn.setStyle("-fx-font:24 'Brush Script MT'");
//		freeFallBttn.setStyle("-fx-font:24 'Brush Script MT'");
		
		
		
		projectileBttn.setLayoutX(40);
		projectileBttn.setLayoutY(500);
		reg2DMotionBttn.setLayoutX(240);
		reg2DMotionBttn.setLayoutY(500);
		freeFallBttn.setLayoutX(440);
		freeFallBttn.setLayoutY(500);
		
		title = new Text(75, 100,"PHYSICS SIMULATOR");
		option = new Text(180, 150, "Pick your type of problem below");
		title.setStyle("-fx-font:45 'Brush Script MT'");
		option.setStyle("-fx-font:18 'Times New Roman'");
		
		newton = new Image("newton.png");
		newtonView = new ImageView();
		newtonView.setImage(newton);
		newtonView.setFitWidth(200);
		newtonView.setPreserveRatio(true);
		newtonView.setX(200);
		newtonView.setY(200);
		
		
		
		
		startPane.getChildren().addAll(projectileBttn, reg2DMotionBttn, freeFallBttn,
				title, option, newtonView);
		startPane.setStyle("-fx-background-color: burlywood");
		reg2DMotionPane.setStyle("-fx-background-color: lavender");
		freeFallPane.setStyle("-fx-background-color: lightskyblue");
		projectilePane.setStyle("-fx-background-color: lightsalmon");
		
	}
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		//Pick a method of motion buttons
	
				// Reg 2D motion actions on
				reg2DMotionBttn.setOnAction( e -> transitionReg2DMotionPane() );
				reg2DMotionPane.getBackBttn().setOnAction(e -> goBack());
				reg2DMotionPane.getSubmitBttn().setOnAction(e -> reg2DMotionPane.simulate());
				reg2DMotionPane.getSaveProgressBttn().setOnAction(e -> reg2DMotionPane.saveProgress());
				reg2DMotionPane.getLoadProgressBttn().setOnAction(new EventHandler <ActionEvent>() {
					@Override
					public void handle (ActionEvent e) {
						reg2DMotionPane.loadProgress();	
						reg2DMotionPane.simulate();	
					
					}
				});
				reg2DMotionPane.setOnKeyPressed(e->{
					if(e.getCode()==KeyCode.ENTER) {
						reg2DMotionPane.simulate();
					}
				});
				reg2DMotionPane.getMakeGraphBttn().setOnAction(new EventHandler <ActionEvent>() {
					@Override
					public void handle (ActionEvent e) {
						
						make2DGraph();	
						
						Stage popupStage = new Stage();
						
				       
					    popupStage.setScene(graph2DScene);
					    popupStage.setTitle("Graph");
					    popupStage.show();
					}
				});
				
				
				
				
				
				
				//Freefall motion actions on
				freeFallBttn.setOnAction( e -> transitionFreeFallPane( ));
				freeFallPane.backBttn.setOnAction(e -> goBack());
				freeFallPane.submitBttn.setOnAction(e -> freeFallPane.simulate());
				freeFallPane.saveProgressBttn.setOnAction(e -> freeFallPane.saveProgress());
				freeFallPane.loadProgressBttn.setOnAction(new EventHandler <ActionEvent>() {
					@Override
					public void handle (ActionEvent e) {
						freeFallPane.loadProgress();	
						freeFallPane.simulate();	
						
				       
					    
					}
				});
				freeFallPane.setOnKeyPressed(e->{
					if(e.getCode()==KeyCode.ENTER) {
						 freeFallPane.simulate();
					}
				});
				freeFallPane.makeGraphBttn.setOnAction(new EventHandler <ActionEvent>() {
					@Override
					public void handle (ActionEvent e) {
						makeFFGraph();	
						
						Stage popupStage = new Stage();
						
				       
						popupStage.setScene(graphFFScene);
						popupStage.setTitle("Graph");
						popupStage.show();
					}
				});
				
				
				
				
				
				
				//projectile motion acions on
				projectileBttn.setOnAction( e -> transitionProjectilePane( ));
				projectilePane.backBttn.setOnAction(e -> goBack());
				projectilePane.submitBttn.setOnAction(e -> projectilePane.simulate());
				projectilePane.saveProgressBttn.setOnAction(e -> projectilePane.saveProgress());
				projectilePane.loadProgressBttn.setOnAction(new EventHandler <ActionEvent>() {
					@Override
					public void handle (ActionEvent e) {
						projectilePane.loadProgress();	
						projectilePane.simulate();	
					    
					}
				});
				projectilePane.setOnKeyPressed(e->{
					if(e.getCode()==KeyCode.ENTER) {
						projectilePane.simulate();
					}
				});
				projectilePane.makeGraphBttn.setOnAction(new EventHandler <ActionEvent>() {
					@Override
					public void handle (ActionEvent e) {
						makePMGraph();	
						
						Stage popupStage = new Stage();
				       
						popupStage.setScene(graphPMScene);
						popupStage.setTitle("Graph");
						popupStage.show();
					}
				});
				
		

						
				// Put the start pane in the top pane. Top pane is a borderpane
				topPane.setCenter(startPane);
				
				
	
		
		//Put everything together:
				startScene = new Scene(topPane, 600, 600);
				
				// Configure and display the stage
				primaryStage.setScene(startScene);
				primaryStage.setTitle("Physics Simulator");
				primaryStage.show();
				
				
		
	}
	
	
	//Methods
	public void transitionReg2DMotionPane() {
		topPane.setCenter(reg2DMotionPane);
		reg2DMotionPane.requestFocus();    // Don't forget this part, or your controls may not work!!
	}
	
	public void transitionFreeFallPane() {
		topPane.setCenter(freeFallPane);
		freeFallPane.requestFocus();
	}
	
	public void transitionProjectilePane() {
		topPane.setCenter(projectilePane);
		projectilePane.requestFocus();
	}
	
	public void goBack() {
		topPane.setCenter(startPane);
	}
	
	public void make2DGraph() {
		
		
		final NumberAxis xAxis = new NumberAxis();
	       final NumberAxis yAxis = new NumberAxis();
	        xAxis.setLabel("time");
	        yAxis.setLabel("position");
	       final LineChart<Number,Number> lineChart = 
	               new LineChart<Number,Number>(xAxis,yAxis);
	      
	       lineChart.setTitle("Position-Time Graph");
		
	       XYChart.Series series2 = new XYChart.Series();
	       series2.setName("PT");
	       for(int i = 0; i<reg2DMotionPane.getBackEndObject().xArray.length; i++) {
	           series2.getData().add(new XYChart.Data(reg2DMotionPane.getBackEndObject().xArray[i], reg2DMotionPane.getBackEndObject().yArray[i]));
	       }
	       
	      graph2DScene  = new Scene(lineChart,500,300);       
	       
	       lineChart.getData().addAll(series2);
	       
	      
//	       
//	       graphScene.setOnKeyPressed(e->{
//				if(e.getCode()==KeyCode.BACK_SPACE) {
//					projectilePane.simulate();
//				}
//			});
	       
	      // topPane.setCenter(graphPane);
	       
	       
	       System.out.println("graph");
	       
	      
	}
	
	public void makeFFGraph() {
		final NumberAxis xAxis = new NumberAxis();
	       final NumberAxis yAxis = new NumberAxis();
	        xAxis.setLabel("time");
	       final LineChart<Number,Number> lineChart = 
	               new LineChart<Number,Number>(xAxis,yAxis);
	      
	       lineChart.setTitle("Position-Time Graph");
		
	       XYChart.Series series2 = new XYChart.Series();
	       series2.setName("Position-Time");
	       for(int i = 0; i<freeFallPane.backEndObject.xArray.length; i++) {
	           series2.getData().add(new XYChart.Data(freeFallPane.backEndObject.xArray[i], freeFallPane.backEndObject.yArray[i]));
	       }
	       
	      graphFFScene  = new Scene(lineChart,500,300);      
	      lineChart.getData().addAll(series2);
	       
	       System.out.println("graph");
	       
	      
	}
	
	public void makePMGraph() {
		final NumberAxis xAxis = new NumberAxis();
	       final NumberAxis yAxis = new NumberAxis();
	        xAxis.setLabel("time");
	       final LineChart<Number,Number> lineChart = 
	               new LineChart<Number,Number>(xAxis,yAxis);
	      
	       lineChart.setTitle("Position-Time Graph");
		
	       XYChart.Series series2 = new XYChart.Series();
	       series2.setName("PT");
	       for(int i = 0; i<projectilePane.backEndObject.xArray.length; i++) {
	           series2.getData().add(new XYChart.Data(projectilePane.backEndObject.xArray[i], projectilePane.backEndObject.yArray[i]));
	       }
	       
	      graphPMScene  = new Scene(lineChart,500,300);       
	      lineChart.getData().addAll(series2);
	       
	       
	       System.out.println("graph");
	       
	      
	}
	

	//Main, everything runs through here
	public static void main(String[] args) {

		Environment.launch(args);
		
		
		
	}
	
}