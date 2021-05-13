/*
 * FreeFallPane.java
 * CS 225 Spring 2021
 * Written by: Calla Robison 
 * Last edited: 5/4/2021
 * Base: to handle the GUI for the free fall Motion portion of the program
 * 
 * Purpose: Sets up textfields, buttons, moving the gui object, and uses file IO
 *  inside the pane.
 *  Attributes: 
 *  		-title:Text 
 *  		-unitsVelocityI:Text 
 *  		-unitsVelocityF:Text 
 *  		-unitsDisplacment:Text 
 *  		-unitsTime:Text 
 *  		-velocityInitialInpFF:TextField -- textfield for initial velocity
 *  		-velocityFinalInpFF:TextField -- textfield for final velocity
 *  		-yDisplacementInp:TextField -- textfield for y displacement
 *  		-timeInpFF:TextField -- textfield for time
 *  		-printedValues:Label -- label for displaying inputed and solved values
 *  		-backBttn:Button 
 *  		-submitBttn:Button 
 *  		-makeGraphBttn:Button 
 *  		-saveProgressBttn:Button 
 *  		-loadProgressBttn:Button 
 *  		-backEndObject:Freefall -- the object that runs all the calculations and movements
 *  		-object:Circle -- a GUI display of the backend object
 *  		-recentEntry:File -- file that stores what the user inputed
 *  		-computerEntry:File -- files that stores the numbers of what the user inputed
 *  		-y:double -- variable for y position of motion object
 *  		-index: int -- counter for the while loop when read the file in load progress
 *  		-maxHeightY:int -- top pane boundary
 *  		-maxBottomY:int -- lower pane boundary
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

public class freeFallPane extends Pane{
	
	private Text title, unitsVelocityI, unitsVelocityF, unitsDisplacement, unitsTime/*, unitsAcceleration*/;
	protected TextField velocityInitialInpFF, velocityFinalInpFF, yDisplacementInp, timeInpFF, accelerationInpFF;
	private Label printedValues;
	protected Button backBttn, submitBttn, makeGraphBttn,  saveProgressBttn, loadProgressBttn;
	protected Freefall backEndObject;
	protected Circle object;
	protected File recentEntry, computerEntry;
	protected double y=0;
	private int index = 0;
	private int maxHeightY = 600;
	private int maxBottomY = 10;
	
	
	//Constructor
	public freeFallPane(){
	
		//TextFields for freefall
		velocityInitialInpFF = new TextField();
		velocityFinalInpFF = new TextField();
		yDisplacementInp = new TextField();
		timeInpFF = new TextField();
		accelerationInpFF = new TextField();
		
		backEndObject = new Freefall();
		object = new Circle(backEndObject.getX()/10, - (backEndObject.getY()/10) + 550, 5, Color.BLUE);
		recentEntry = new File("recentEntryFreeFall.txt");
		computerEntry = new File("computerEntryFreeFall.txt");
		
		setTextFields();
		
		backBttn = new Button("Back");
		submitBttn = new Button("Submit");
		makeGraphBttn = new Button("Make Graph");
		saveProgressBttn = new Button("Save Progress");
		loadProgressBttn = new Button("Load Progress");
		
		backBttn.setLayoutX(10);
		backBttn.setLayoutY(10);
		submitBttn.setLayoutX(30);
		submitBttn.setLayoutY(300);
		makeGraphBttn.setLayoutX(15);
		makeGraphBttn.setLayoutY(500);
		saveProgressBttn.setLayoutX(15);
		saveProgressBttn.setLayoutY(550);
		loadProgressBttn.setLayoutX(100);
		loadProgressBttn.setLayoutY(300);
		
		printedValues = new Label();

//		
		printedValues.setTranslateX(10);
		printedValues.setTranslateY(75);
		Font font = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 18);
		printedValues.setFont(font);

		
		title = new Text(85, 50,"FREEFALL MOTION");
		title.setStyle("-fx-font:45 'Brush Script MT'");
		
		getChildren().addAll(velocityInitialInpFF, velocityFinalInpFF, yDisplacementInp, timeInpFF);
		getChildren().addAll(backBttn, submitBttn, makeGraphBttn, saveProgressBttn, loadProgressBttn);
		getChildren().addAll(title, unitsVelocityI, unitsVelocityF, unitsDisplacement, unitsTime, printedValues/*, unitsAcceleration*/);
	
		
		makeGraphBttn.setVisible(false);
		saveProgressBttn.setVisible(false);
		//velocityFinalInpFF.setVisible(true);
}
	
	
	//Make text fields 
	public void setTextFields() {
		
		
		//Makin Textfields for Freefall 
		velocityInitialInpFF.setPromptText("Enter your initial velocity");
		velocityInitialInpFF.setFocusTraversable(false);
		velocityInitialInpFF.setLayoutX(30);
		velocityInitialInpFF.setLayoutY(100);
		unitsVelocityI = new Text(185, 119, "Meters/second");
				
		velocityFinalInpFF.setPromptText("Enter your final velocity");
		velocityFinalInpFF.setFocusTraversable(false);
		velocityFinalInpFF.setLayoutX(30);
		velocityFinalInpFF.setLayoutY(150);
		unitsVelocityF = new Text(185, 169, "Meters/second");
					
		yDisplacementInp.setPromptText("Enter your y displacement");
		yDisplacementInp.setFocusTraversable(false);
		yDisplacementInp.setLayoutX(30);
		yDisplacementInp.setLayoutY(200);
		unitsDisplacement = new Text(185, 219, "Meters");
					
		timeInpFF.setPromptText("Enter your given time");
		timeInpFF.setFocusTraversable(false);
		timeInpFF.setLayoutX(30);
		timeInpFF.setLayoutY(250);
		unitsTime = new Text(185, 269, "Seconds");
					 
//		accelerationInpFF.setPromptText("Enter your given acceleration");
//		accelerationInpFF.setFocusTraversable(false);
//		accelerationInpFF.setLayoutX(30);
//		accelerationInpFF.setLayoutY(500);
	}
	
	//Takes the inputs of the text fields and parses them into doubles, calls projectileMotion calculator
	public void setTextFieldInputs() {
		
		try {
			//System.out.println("Input 'None' if there is no given value");
			
			backEndObject.velocityIntialStr = velocityInitialInpFF.getText();
			//checkExists(velocity, 0);
			
			
			backEndObject.velocityFinalStr = velocityFinalInpFF.getText();
	 	 	//checkExists(velocity, 0);
			
	 	 	
			backEndObject.yDisplacementStr = yDisplacementInp.getText();
	 	 	//checkExists(xDisplacement, 2);
	 	 	
	 	 	//do {
	 	 	//System.out.println("Enter in your given time");
			backEndObject.timeStr = timeInpFF.getText();
	 	 	//checkExists(time, 3);
	 	 		if(backEndObject.timeStr.equals("none")) {
	 	 			//time = 0;
	 	 			//calculateTime();
	 	 		}	
	 	 	
	 	 		else {
	 	 			backEndObject.time = Double.parseDouble(backEndObject.timeStr);
	 	 		}
	 	 	
	 	 //	}while(calculator.time<0);    //Do while time is less than 0, it will ask again for an input if a negative number is inputted
	 	 	
	 	 	
	 	 	//checkExists(acceleration, 4);
	 	 	
	 	 	try {
	 	 		backEndObject.stringToDouble();
	 	 	}catch(Exception e) {
	 	 		e.printStackTrace();
	 	
	 	 	}
	 	 	
	 	 	System.out.println("intialvelocity = " + backEndObject.velocityIntial + " final velocity = " + backEndObject.velocityFinal 
	 	 			+ " x displacement = " + backEndObject.yDisplacement + " acceleration = " + backEndObject.acceleration + " time = "
	 	 			+ backEndObject.time); //check string to double conversion
	 	 
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
		
		if(backEndObject.velocityIntial < 0) {
			object.relocate(300, 250);
		}
		
		Timeline simulation = new Timeline();
		simulation.setCycleCount(Timeline.INDEFINITE);
		KeyFrame keyframe = new KeyFrame(Duration.millis(100), action -> {
			
		//System.out.println("checking");
		
			
			//move();
			//aaaaaa();
			if(backEndObject.yDisplacement <= 0 || backEndObject.velocityIntial < 0) {
				backEndObject.move();
				y =  -backEndObject.y - 250;
			}
			
			backEndObject.move();
			y = backEndObject.y;

			backEndObject.fillArray();
			object.relocate(300, y);
			

		
		if(backEndObject.incrementTime >= backEndObject.time /*|| y > maxHeightY || y <= maxBottomY */  ) {
			simulation.stop();
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
	
	//Saves inputs into a file FILEIO
	public void saveProgress() {
		 
		try {
			
			FileWriter fw = new FileWriter(recentEntry);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("Initial Velocity: " + backEndObject.velocityIntial + " meters/second");
			bw.append(System.lineSeparator());
			bw.write("Final Velocity: " + backEndObject.velocityFinal + " meters/second");
			bw.append(System.lineSeparator());
			bw.write("Y Displacement: " + backEndObject.yDisplacement + " meters");
			bw.append(System.lineSeparator());
			bw.write("Time:  " + backEndObject.time + " seconds");
			bw.append(System.lineSeparator());
			bw.write("Acceleration " + backEndObject.acceleration + " meters/second^2");
			bw.append(System.lineSeparator());
			
			
			
			bw.close();
			
			fw = new FileWriter(computerEntry);
			bw = new BufferedWriter(fw);
			
			bw.write(""+backEndObject.velocityIntial );
			bw.append(System.lineSeparator());
			bw.write("" + backEndObject.velocityFinal );
			bw.append(System.lineSeparator());
			bw.write("" + backEndObject.yDisplacement );
			bw.append(System.lineSeparator());
			bw.write("" + backEndObject.time );
			bw.append(System.lineSeparator());
			bw.write("" + backEndObject.acceleration );
			bw.append(System.lineSeparator());
			
			
			
			bw.close();
			
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
//		try (PrintWriter out = new PrintWriter(new BufferedWriter(
//	                new FileWriter("myfile.txt", true)))) {
//	            out.println(text1.getText());
//	        } catch (IOException ioe) {
//	            ioe.printStackTrace();
//	        }
	}
	
	//runs most recent saved entry EXCEPTION HANDLING
	public void loadProgress() {
		try {
			
			FileReader fr = new FileReader(computerEntry);
			BufferedReader br = new BufferedReader(fr);
			
			String line;
			
			// read the header line in the file
			
			// print out header line 
			//System.out.println(line);
			//br.readLine();
				
			
			// while line is equal to the next line of the bufferedreader is not equal to null
			// this means read the next line in the file until there are not more line to read
				while (  ( line = br.readLine() ) != null    ) {
					//line = br.readLine();
					try {
						//System.out.println("Input 'None' if there is no given value");
						if(index==0) {
						backEndObject.velocityIntialStr = line;
						System.out.println(line);
						//checkExists(velocity, 0);
						}
						
						if(index==1) {
						backEndObject.velocityFinalStr = line;
						System.out.println(line);
				 	 	//checkExists(velocity, 0);
						}
				 	 	
						if(index==2) {
						backEndObject.yDisplacementStr = line;
						System.out.println(line);
				 	 	//checkExists(xDisplacement, 2);
						}
				 	 	
				 	 	//do {
				 	 	//System.out.println("Enter in your given time");
						if(index==3) {
						backEndObject.timeStr = line;
						System.out.println(line);
				 	 	//checkExists(time, 3);
				 	 		if(backEndObject.timeStr.equals("none")) {
				 	 			//time = 0;
				 	 			//calculateTime();
				 	 		}	
				 	 	
				 	 		else {
				 	 			backEndObject.time = Double.parseDouble(backEndObject.timeStr);
				 	 		}
						}
				 	 	
				 	 //	}while(calculator.time<0);    //Do while time is less than 0, it will ask again for an input if a negative number is inputted
				 	 	
				 	 	if(index==4) {
				 	 		backEndObject.accelerationStr = line;
				 	 	//checkExists(acceleration, 4);
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
		
		
		
		System.out.println("intialvelocity = " + backEndObject.velocityIntial + " final velocity = " + backEndObject.velocityFinal 
 	 			+ " x displacement = " + backEndObject.yDisplacement + " acceleration = " + backEndObject.acceleration + " time = "
 	 			+ backEndObject.time); //check string to double conversion
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
								
						System.out.println("yo error");
							
					}
			
			
			//printedValuesText.setText(values);
			printedValues.setText(values);
			System.out.println(values);
			System.out.println("label should be showng");
		}
		
		else {
			System.out.println(" Values: \n Initial velocity = " + backEndObject.velocityIntial + "\n final velocity = " + backEndObject.velocityFinal 
	 	 			+ "\n y displacement = " + backEndObject.yDisplacement + "\n acceleration = " + backEndObject.acceleration + "\n time = "
	 	 			+ backEndObject.time);
			printedValues.setText(" Values:   \n Initial Velocity = " + backEndObject.velocityIntial + " Meters/second \n Final velocity = " + backEndObject.velocityFinal 
	 	 			+ " Meters/second \n Y displacement = " + backEndObject.yDisplacement + " Meters \n Acceleration = " + backEndObject.acceleration + " Meters/second^2 \n Time = "
	 	 			+ backEndObject.time + " Seconds");
			
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
	
	velocityInitialInpFF.setVisible(false);
	velocityFinalInpFF.setVisible(false);
	yDisplacementInp.setVisible(false);
	timeInpFF.setVisible(false);
	//accelerationInp.setVisible(false);
	submitBttn.setVisible(false);
	loadProgressBttn.setVisible(false);
	
	unitsVelocityI.setVisible(false);
	unitsVelocityF.setVisible(false);
	unitsDisplacement.setVisible(false);
	unitsTime.setVisible(false);
	/*unitsAcceleration.setVisible(false);*/
	submitBttn.setVisible(false);
	loadProgressBttn.setVisible(false);
	
	makeGraphBttn.setVisible(true);
}

	
}