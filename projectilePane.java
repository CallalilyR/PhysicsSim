/*
 * ProjectilePane.java
 * CS 225 Spring 2021
 * Written by: Calla Robison 
 * Last edited: 5/4/2021
 * Base: to handle the GUI for the projectile Motion portion of the program
 * Purpose: Sets up textfields, buttons, moving the gui object, and uses file IO inside the pane.
 *  Attributes: 
 *  		-title:Text 
 *  		-unitsVelocity:Text 
 *  		-unitsHeight:Text 
 *  		-unitsAngle:Text 
 *  		-anglelInp:TextField -- textfield for angle
 *  		-velocityInp:TextField -- textfield for velocity
 *  		-heightInp:TextField -- textfield for height
 *  		-printedValues:Label -- label for displaying inputed and solved values
 *  		-backBttn:Button 
 *  		-submitBttn:Button 
 *  		-makeGraphBttn:Button 
 *  		-saveProgressBttn:Button 
 *  		-loadProgressBttn:Button 
 *  		-backEndObject:ProjectileMotion -- the object that runs all the calculations and movements
 *  		-object:Circle -- a GUI display of the backend object
 *  		-recentEntry:File -- file that stores what the user inputed
 *  		-computerEntry:File -- files that stores the numbers of what the user inputed
 *  		-x:double -- variable for x position of motion object
 *  		-y:double -- variable for y position of motion object
 *  		-index:int -- counter for the while loop when read the file in load progress
 *
 * Methods:
 *         +setTextFields():double -- Make text fields 
 *         +setTextFieldInputs():double -- Takes the inputs of the text fields and parses them into doubles, calls freefall motion calculator
 *         +simulate():double -- Runs simulation, moves gui object
 *         +saveProgress():double -- Saves inputs into a file
 *         +loadProgress():double -- reads input from most recent saved entry
 *         +printValuesGUI():void -- prints values onto gui
 *         +buttonPressed(bool:int):void --When load progress button is pressed
 *         +simulationVisibility():void -- Visibiliy of textfields, text, and buttons
 *         setters and getters for all attributes 
 * 
 */ 




import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;


public class projectilePane extends Pane{
	
	protected TextField angleInp, velocityInp, heightInp;
	private Text title, unitsVelocity, unitsHeight, unitsAngle;
	private Label printedValues;
	protected Button backBttn, submitBttn, makeGraphBttn, saveProgressBttn, loadProgressBttn;
	protected ProjectileMotion backEndObject;
	protected Circle object;
	protected double x = 0;
	protected double y = 0;
	protected File recentEntry, computerEntry;
	int index = 0;
	
	
	//Constructor
	public projectilePane(){
		
		//TextFields for projectile motion
		angleInp = new TextField();
		velocityInp = new TextField();
		heightInp = new TextField();
		
		backEndObject = new ProjectileMotion();
		object = new Circle(backEndObject.getX()/10, - (backEndObject.getY()/10) + 550, 5, Color.BLUE);
		recentEntry = new File("recentEntryProjectileMotion.txt");
		computerEntry = new File("computerEntryProjectileMotion.txt");
		
		setTextFields();
		
		backBttn = new Button("Back");
		submitBttn = new Button("Submit");
		makeGraphBttn = new Button("Make Graph");
		saveProgressBttn = new Button("Save Progress");
		loadProgressBttn = new Button("Load Progress");
		
		backBttn.setLayoutX(10);
		backBttn.setLayoutY(10);
		submitBttn.setLayoutX(30);
		submitBttn.setLayoutY(269);
		makeGraphBttn.setLayoutX(15);
		makeGraphBttn.setLayoutY(500);
		saveProgressBttn.setLayoutX(15);
		saveProgressBttn.setLayoutY(550);
		loadProgressBttn.setLayoutX(100);
		loadProgressBttn.setLayoutY(269);
		
		title = new Text(75, 50,"PROJECTILE MOTION");
		title.setStyle("-fx-font:45 'Brush Script MT'");
		
		printedValues = new Label();
		printedValues.setTranslateX(300);
		printedValues.setTranslateY(100);
		Font font = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 18);
		printedValues.setFont(font);
		
		object.relocate(300, 300);
		
		getChildren().addAll(velocityInp, heightInp, angleInp);
		getChildren().addAll(backBttn, submitBttn, makeGraphBttn, saveProgressBttn, loadProgressBttn/*accelerationInp*/);
		getChildren().addAll(title, unitsVelocity, unitsHeight, unitsAngle, printedValues);
		makeGraphBttn.setVisible(false);
		saveProgressBttn.setVisible(false);
		}
	
	
	
	//Make text fields 
	public void setTextFields(){
		
				
				//making text fields for projectile motioN
				 velocityInp.setPromptText("Enter your given velocity");
				 velocityInp.setFocusTraversable(false);
				 velocityInp.setLayoutX(30);
				 velocityInp.setLayoutY(100);
				 unitsVelocity = new Text(185, 119, "Meters/second");
				 
				 heightInp.setPromptText("Enter your given height");
				 heightInp.setFocusTraversable(false);
				 heightInp.setLayoutX(30);
				 heightInp.setLayoutY(150);
				 unitsHeight = new Text(185, 169, "Meters");
				 
				 angleInp.setPromptText("Enter your given angle");
				 angleInp.setFocusTraversable(false);
				 angleInp.setLayoutX(30);
				 angleInp.setLayoutY(200);
				 unitsAngle = new Text(185, 219, "Degrees");
				 
	}
	
	//Takes the inputs of the text fields and parses them into doubles, calls projectileMotion calculator
	public void setTextFieldInputs() {
		
		try {
			//System.out.println("Input 'None' if there is no given value");
			
			backEndObject.angleStr = angleInp.getText();
			//checkExists(velocity, 0);
			
			
			backEndObject.heightStr = heightInp.getText();
	 	 	//checkExists(velocity, 0);
			
	 	 	
			backEndObject.velocityStr = velocityInp.getText();
	 	 	//checkExists(xDisplacement, 2);
	 	 	
	 	 	
	 	 	try {
	 	 		backEndObject.stringToDouble();
	 	 	}catch(Exception e) {
	 	 		e.printStackTrace();
	 	
	 	 	}
	 	 	
	 	 	System.out.println("velocity = " + backEndObject.velocity + " angle = " + backEndObject.angle 
	 	 			+ " height = " + backEndObject.height); //check string to double conversion
	 	 
			}catch(Exception e) {
			e.printStackTrace();
			}
		
	}
	
	//Runs simulation, moves gui object
	public void simulate() {
		
		if (buttonPressed(index) == false) {
			setTextFieldInputs();
			saveProgressBttn.setVisible(true);
		}
		
		
		simulationVisibility();
		
		getChildren().add(object);
		
		printValuesGUI();
		
		Timeline simulation = new Timeline();
		simulation.setCycleCount(Timeline.INDEFINITE);
		KeyFrame keyframe = new KeyFrame(Duration.millis(100), action -> {
			
		//System.out.println("checking");
		
			
			//move();
			//aaaaaa();
			backEndObject.move();
			x = backEndObject.x;
			y = backEndObject.y;
			//y = calculator.y;
			backEndObject.fillArray();
			object.relocate(x, y);
			

		
		if(backEndObject.y_0 >= backEndObject.height*2) {
			simulation.stop();
			//backEndObject.time = backEndObject.incrementTime;
		}
//		else {
//			simulation.stop();
//		}
				
				
		});
		
		// add keyframe to timeline
		simulation.getKeyFrames().add(keyframe);

		// start the timeline
		simulation.play();   
	}
	
	//Saves inputs into a file
	public void saveProgress() {
		 
		try {
			
			FileWriter fw = new FileWriter(recentEntry);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("Initial Velocity: " + backEndObject.velocity + " meters/second");
			bw.append(System.lineSeparator());
			bw.write("Final Velocity: " + backEndObject.height + " meters");
			bw.append(System.lineSeparator());
			bw.write("X Displacement: " + backEndObject.angle + " degrees");
		

			bw.close();
			
			fw = new FileWriter(computerEntry);
			bw = new BufferedWriter(fw);
			
			bw.write(""+backEndObject.velocity );
			bw.append(System.lineSeparator());
			bw.write("" + backEndObject.height );
			bw.append(System.lineSeparator());
			bw.write("" + backEndObject.angle );
		
			
			
			
			bw.close();
			
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	//runs most recent saved entry
	public void loadProgress() {
		try {
			
			FileReader fr = new FileReader(computerEntry);
			BufferedReader br = new BufferedReader(fr);
			
			String line;
			
				
			
			// while line is equal to the next line of the bufferedreader is not equal to null
			// this means read the next line in the file until there are not more line to read
				while (  ( line = br.readLine() ) != null    ) {
					//line = br.readLine();
					try {
						//System.out.println("Input 'None' if there is no given value");
						if(index==0) {
						backEndObject.velocityStr = line;
						System.out.println(line);
						}
						
						if(index==1) {
						backEndObject.heightStr = line;
						System.out.println(line);
						}
				 	 	
						if(index==2) {
						backEndObject.angleStr = line;
						System.out.println(line);
						}
				 	 	
				 	 	index++;
				 	 	
				 	 	
				 	 
						}catch(Exception e) {
						e.printStackTrace();
						}
				}
				
				br.close();
				
				try {
		 	 		backEndObject.stringToDouble();
		 	 	}catch(Exception e) {
		 	 		e.printStackTrace();
		 	
		 	 	}
							
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		System.out.println("Velocity = " + backEndObject.velocity + " height = " + backEndObject.height 
 	 			+ " angle " + backEndObject.angle); //check string to double conversion
	}
	
	//prints values onto gui
	public void printValuesGUI() {
			
			if (buttonPressed(index)) {
				String values;
				values = "                    Values:\n";
				try {
							
							FileReader fr = new FileReader(recentEntry);
							BufferedReader br = new BufferedReader(fr);
							
							String line;
							
							
							
							index = 0;
							// while line is equal to the next line of the bufferedreader is not equal to null
							// this means read the next line in the file until there are not more line to read
								while (  ( line = br.readLine() ) != null    ) {
									
									values=values+line+"\n";
							
									
		
								}
								
							br.close();
				
								
								
						} catch(Exception e) {
									
							e.printStackTrace();
							System.out.println("yo error");
								
						}
				
				
				//printedValuesText.setText(values);
				printedValues.setText(values);
				System.out.println(values);
				System.out.println("label should be showng");
			}
			
			else {
				System.out.println(" Values: \n Velocity = " + backEndObject.velocity + "\n Height = " + backEndObject.height 
		 	 			+ "\n Angle = " + backEndObject.angle );
				printedValues.setText(" Values: \n Velocity = " + backEndObject.velocity + "\n Height = " + backEndObject.height 
		 	 			+ "\n Angle = " + backEndObject.angle );
				
			}
			
				}
	
	//When load progress button is pressed
	public boolean buttonPressed(int bool) {
		
		if (bool == 0) {
			return false;
		}
		
		else {
			return true;
		}
		
	}
	
	//Visibiliy of texfields, text, and buttons
	public void simulationVisibility() {
		
		velocityInp.setVisible(false);
		heightInp.setVisible(false);
		angleInp.setVisible(false);
		submitBttn.setVisible(false);
		loadProgressBttn.setVisible(false);
		
		unitsVelocity.setVisible(false);
		unitsHeight.setVisible(false);
		unitsAngle.setVisible(false);
		submitBttn.setVisible(false);
		loadProgressBttn.setVisible(false);
		
		makeGraphBttn.setVisible(true);
	}

	
	
}