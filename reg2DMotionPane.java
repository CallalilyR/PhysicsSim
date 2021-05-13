/*
 * reg2DMotionPane.java
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
 *  		-velocityInitialInp:TextField -- textfield for initial velocity
 *  		-velocityFinalInp:TextField -- textfield for final velocity
 *  		-yDisplacementInp:TextField -- textfield for x displacement
 *  		-timeInp:TextField -- textfield for time
 *  		-printedValues:Label -- label for displaying inputed and solved values
 *  		-backBttn:Button 
 *  		-submitBttn:Button 
 *  		-makeGraphBttn:Button 
 *  		-saveProgressBttn:Button 
 *  		-loadProgressBttn:Button 
 *  		-backEndObject:Calculator -- the object that runs all the calculations and movements
 *  		-object:Circle -- a GUI display of the backend object
 *  		-recentEntry:File -- file that stores what the user inputed
 *  		-computerEntry:File -- files that stores the numbers of what the user inputed
 *  		-x:double -- variable for y position of motion object
 *  		-index: int -- counter for the while loop when read the file in load progress
 *  		-maxWidthLeft:int -- top pane boundary
 *  		-maxWidthRight:int -- lower pane boundary
 *
 * Methods:
 *         +setTextFields():double -- Make text fields 
 *         +setTextFieldInputs():double -- Takes the inputs of the text fields and parses them into doubles, calls freefall motion calculator
 *         +simulate():void -- Runs simulation, moves gui object
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



public class reg2DMotionPane extends Pane{
	
	private TextField velocityInitialInp, velocityFinalInp, xDisplacementInp, timeInp, accelerationInp;
	private Text title, unitsVelocityI, unitsVelocityF, unitsDisplacement, unitsTime, unitsAcceleration;
	private Label printedValues; 
	private Button backBttn, submitBttn, makeGraphBttn, saveProgressBttn, loadProgressBttn;
	private Calculator backEndObject;
	private Circle object;
	private File recentEntry, computerEntry;
	private int maxWidthLeft = 0;
	private int maxWidthRight = 600;
	private double x = 0;
	private int index = 0;
	
	

	
	
	//Constructor
	public reg2DMotionPane() {
		
		backEndObject = new Calculator();
		object = new Circle(backEndObject.getX()/10, - (backEndObject.getY()/10) + 600, 5, Color.BLUE);
		recentEntry = new File("recentEntry2DMotion.txt");
		computerEntry = new File("computerEntry2DMotion.txt");
		
		
		//TextFields for regular 2D motion
		velocityInitialInp = new TextField();
		velocityFinalInp = new TextField();
		xDisplacementInp = new TextField();
		timeInp = new TextField();
		accelerationInp = new TextField();
		
		
		setTextFields();
		
		backBttn = new Button("Back");
		submitBttn = new Button("Submit");
		makeGraphBttn = new Button("Make Graph");
		saveProgressBttn = new Button("Save Progress");
		loadProgressBttn = new Button("Load Progress");
		
		backBttn.setLayoutX(10);
		backBttn.setLayoutY(10);
		submitBttn.setLayoutX(30);
		submitBttn.setLayoutY(350);
		makeGraphBttn.setLayoutX(15);
		makeGraphBttn.setLayoutY(500);
		saveProgressBttn.setLayoutX(15);
		saveProgressBttn.setLayoutY(550);
		loadProgressBttn.setLayoutX(100);
		loadProgressBttn.setLayoutY(350);
		
		printedValues = new Label();

		printedValues.setTranslateX(300);
		printedValues.setTranslateY(100);
		Font font = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 18);
		printedValues.setFont(font);
		//printedValues.setFill(Color.AQUA);	
		
		title = new Text(75, 50,"REGULAR 2D MOTION");
		title.setStyle("-fx-font:45 'Brush Script MT'");
		
		
		getChildren().addAll(velocityInitialInp, velocityFinalInp, xDisplacementInp, timeInp, accelerationInp);
		getChildren().addAll(backBttn, submitBttn, makeGraphBttn, saveProgressBttn, loadProgressBttn);
		getChildren().addAll(title, unitsVelocityI, unitsVelocityF, unitsDisplacement, unitsTime, unitsAcceleration);
		getChildren().addAll(printedValues);		
		
		
		makeGraphBttn.setVisible(false);
		saveProgressBttn.setVisible(false);
		
	}
	
	
	
	public void setTextFields() {
	
	
		
		//Making text fields for reg 2d motion
			velocityInitialInp.setPromptText("Enter your initial velocity");
			velocityInitialInp.setFocusTraversable(false);
			velocityInitialInp.setLayoutX(30);
			velocityInitialInp.setLayoutY(100);
			unitsVelocityI = new Text(185, 119, "Meters/second");
			
			velocityFinalInp.setPromptText("Enter your final velocity");
			velocityFinalInp.setFocusTraversable(false);
			velocityFinalInp.setLayoutX(30);
			velocityFinalInp.setLayoutY(150);
			unitsVelocityF = new Text(185, 169, "Meters/second");
			
			xDisplacementInp.setPromptText("Enter your x displacement");
			xDisplacementInp.setFocusTraversable(false);
			xDisplacementInp.setLayoutX(30);
			xDisplacementInp.setLayoutY(200);
			unitsDisplacement = new Text(185, 219, "Meters");
			
			 timeInp.setPromptText("Enter your given time");
			 timeInp.setFocusTraversable(false);
			 timeInp.setLayoutX(30);
			 timeInp.setLayoutY(250);
			 unitsTime = new Text(185, 269, "Seconds");
			 
			 accelerationInp.setPromptText("Enter your given acceleration");
			 accelerationInp.setFocusTraversable(false);
			 accelerationInp.setLayoutX(30);
			 accelerationInp.setLayoutY(300);
			 unitsAcceleration = new Text(185, 319, "Meters/second^2");
	
	
	}
	
	public void setTextFieldInputs() {

		
		try {
			//System.out.println("Input 'None' if there is no given value");
			
			backEndObject.velocityIntialStr = velocityInitialInp.getText();
			
			backEndObject.velocityFinalStr = velocityFinalInp.getText();
	 	 	
			backEndObject.xDisplacementStr = xDisplacementInp.getText();
	 	 	
			backEndObject.timeStr = timeInp.getText();
	 	 	
	 	 		if(backEndObject.timeStr.equals("none")) {
	 	 			//time = 0;
	 	 			//calculateTime();
	 	 		}	
	 	 	
	 	 		else {
	 	 			backEndObject.time = Double.parseDouble(backEndObject.timeStr);
	 	 		}
	 	 	
	 	 //	}while(calculator.time<0);    //Do while time is less than 0, it will ask again for an input if a negative number is inputted
	 	 	
	 	 	
	 	 		backEndObject.accelerationStr = accelerationInp.getText();
	 	 	
	 	 	try {
	 	 		backEndObject.stringToDouble();
	 	 	}catch(Exception e) {
	 	 		e.printStackTrace();
	 	
	 	 	}
	 	 	
	 	 	System.out.println("intialvelocity = " + backEndObject.velocityIntial + " final velocity = " + backEndObject.velocityFinal 
	 	 			+ " x displacement = " + backEndObject.xDisplacement + " acceleration = " + backEndObject.acceleration + " time = "
	 	 			+ backEndObject.time); //check string to double conversion
	 	 
			}catch(Exception e) {
			e.printStackTrace();
			}
		
	}

	public void saveProgress() {
		 
		try {
			
			FileWriter fw = new FileWriter(recentEntry);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("Initial Velocity: " + backEndObject.velocityIntial + " meters/second");
			bw.append(System.lineSeparator());
			bw.write("Final Velocity: " + backEndObject.velocityFinal + " meters/second");
			bw.append(System.lineSeparator());
			bw.write("X Displacement: " + backEndObject.xDisplacement + " meters");
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
			bw.write("" + backEndObject.xDisplacement );
			bw.append(System.lineSeparator());
			bw.write("" + backEndObject.time );
			bw.append(System.lineSeparator());
			bw.write("" + backEndObject.acceleration );
			bw.append(System.lineSeparator());
			
			
			
			bw.close();
			
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
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
						backEndObject.xDisplacementStr = line;
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
 	 			+ " x displacement = " + backEndObject.xDisplacement + " acceleration = " + backEndObject.acceleration + " time = "
 	 			+ backEndObject.time); //check string to double conversion
	}
	
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
	 	 			+ "\n x displacement = " + backEndObject.xDisplacement + "\n acceleration = " + backEndObject.acceleration + "\n time = "
	 	 			+ backEndObject.time);
			printedValues.setText(" Values:   \n Initial Velocity = " + backEndObject.velocityIntial + " Meters/second \n Final velocity = " + backEndObject.velocityFinal 
	 	 			+ " Meters/second \n X displacement = " + backEndObject.xDisplacement + " Meters \n Acceleration = " + backEndObject.acceleration + " Meters/second^2 \n Time = "
	 	 			+ backEndObject.time + " Seconds");
			
		}
		
			}
	
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
		
			
			if(backEndObject.xDisplacement <= 0 || backEndObject.velocityIntial <= 0) {
				backEndObject.move();
				x = -backEndObject.x;
			}
			
			backEndObject.move();
			x = backEndObject.x;
			//y = calculator.y;
			
			backEndObject.fillArray();
			 
			
			object.relocate(x, 300);
		
			
		
		if(backEndObject.incrementTime >= backEndObject.time || x < maxWidthLeft || x >= maxWidthRight/*|| backEndObject.isNumeric(backEndObject.velocityIntialStr 
				+ backEndObject.velocityFinalStr )== false*/) {
			simulation.stop();
		}

				
				
		});
		
		// add keyframe to timeline
		simulation.getKeyFrames().add(keyframe);

		// start the timeline
		simulation.play();   
		
		
		    
	}
	
	public boolean buttonPressed(int bool) {
		
		if (bool == 0) {
			return false;
		}
		
		else {
			return true;
		}
		
	}
	
	public void simulationVisibility() {
		
		velocityInitialInp.setVisible(false);
		velocityFinalInp.setVisible(false);
		xDisplacementInp.setVisible(false);
		timeInp.setVisible(false);
		accelerationInp.setVisible(false);
		submitBttn.setVisible(false);
		loadProgressBttn.setVisible(false);
		
		unitsVelocityI.setVisible(false);
		unitsVelocityF.setVisible(false);
		unitsDisplacement.setVisible(false);
		unitsTime.setVisible(false);
		unitsAcceleration.setVisible(false);
		submitBttn.setVisible(false);
		loadProgressBttn.setVisible(false);
		
		makeGraphBttn.setVisible(true);
	}

	
	
	
	
	
	
	
	
	//Setters and getters
	public Button getBackBttn() {
		return backBttn;
	}

	public void setBackBttn(Button backBttn) {
		this.backBttn = backBttn;
	}

	public Button getSubmitBttn() {
		return submitBttn;
	}

	public void setSubmitBttn(Button submitBttn) {
		this.submitBttn = submitBttn;
	}

	public Button getMakeGraphBttn() {
		return makeGraphBttn;
	}

	public void setMakeGraphBttn(Button makeGraphBttn) {
		this.makeGraphBttn = makeGraphBttn;
	}

	public Button getSaveProgressBttn() {
		return saveProgressBttn;
	}

	public void setSaveProgressBttn(Button saveProgressBttn) {
		this.saveProgressBttn = saveProgressBttn;
	}

	public Button getLoadProgressBttn() {
		return loadProgressBttn;
	}

	public void setLoadProgressBttn(Button loadProgressBttn) {
		this.loadProgressBttn = loadProgressBttn;
	}

	public Calculator getBackEndObject() {
		return backEndObject;
	}

	public void setBackEndObject(Calculator backEndObject) {
		this.backEndObject = backEndObject;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public File getRecentEntry() {
		return recentEntry;
	}

	public void setRecentEntry(File recentEntry) {
		this.recentEntry = recentEntry;
	}

	public File getComputerEntry() {
		return computerEntry;
	}

	public void setComputerEntry(File computerEntry) {
		this.computerEntry = computerEntry;
	}
	
	
}







































//while the hare hasn't reached the ground, move it
//if((calculator.getY() >= (calculator.getX()/100)) && (calculator.getX() < maxWidthX) && calculator.getY() < maxHeightY) {
	
//	if(calculator.incrementTime <= calculator.time) {
//	
//	calculator.velocityFinalStr = calculator.toString(calculator.velocityFinal);
//	calculator.velocityIntialStr = calculator.toString(calculator.velocityIntial);
//	calculator.accelerationStr = calculator.toString(calculator.acceleration);
//	calculator.incrementTime = 0;
//	
//	object.setVisible(true);
//	
//	
//	
//	//if user has intial and final velocity
//	if(calculator.isNumeric(calculator.velocityFinalStr) == true && calculator.isNumeric(calculator.velocityIntialStr) == true ) {
//	calculator.x = ((calculator.velocityFinal+calculator.velocityIntial)/2)*calculator.incrementTime;
//	calculator.incrementTime += .5;
//	object.relocate(calculator.getX()/10, - calculator.getY()/10 + 590);
//	
//		}
//	
//	//If user has intial velocity and acceleration
//	else if(calculator.isNumeric(calculator.velocityIntialStr) == true && calculator.isNumeric(calculator.accelerationStr)== true) {
//		
//	calculator.x = (calculator.velocityIntial*calculator.incrementTime) + (0.5*calculator.acceleration*Math.pow(calculator.time, 2));
//	calculator.incrementTime += .5;
//	object.relocate(calculator.getX()/10, - calculator.getY()/10 + 590);
//	
//		}
//	
//	System.out.println("Object: x = " + calculator.x + " time = " + calculator.incrementTime + " seconds");	
//	
//	}
	//try{
	
	//object.relocate(calculator.getX()/10, - calculator.getY()/10 + 240);
//	if (m.hare.getTime()%1.5 == 0) {
//		m.hare.boost();
		//System.out.println("burn fuel");
		
//	}
	
	//System.out.println("X: " + m.hare.getX() + "\t\t\tY: " + m.hare.getY());
	//}