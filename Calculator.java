/*
 * Calculator.java
 * CS 225 Spring 2021
 * Written by: Calla Robison 
 * Last edited: 5/4/21
 * Purpose: Parent of ProjectileMotion.java and Freefall.java and to handle the back end portion of moving the object 
 * in 2D motion, hold like methods and attributes that are used in the children classes ProjectileMotion.java and 
 * Freefall.java, and converting the inputted strings in the regular2DMotion class into doubles to be used for other 
 * purposes. And main calculator for other user inputted variables. 
 * Attributes: 
 *        -velocityIntial:double -- Stores velocity after conversion of string to double      
 *        -velocityFinal:double -- Stores height after conversion of string to double    
 *		  -xDisplacement:double --  Stores y displacement after conversion of string to double    
 *		  -time:double -- Stores time after conversion of string to double  
 *		  -acceleration:double -- Stores acceleration after conversion of string to double  
 *		  -velocityIntialStr:String -- Stores intial velocity string user input   
 *		  -velocityFinalStr:String -- Stores final velocity string user input 
 *		  -xDisplacementStr:String -- Stores x displacement string user input 
 *		  -timeStr:String -- Stores time string user input
 *		  -accelerationStr:String -- Stores acceleration string user input
 *		  -x:double -- variable for x position of motion object
 *		  -y:double -- variable for y position of motion object
 *		  -x_0:double -- variable for intial x position of object
 *		  -y_0:double -- variable for intial y position of object
 *		  -incrementTime:double -- time increment counter
 *		  -xArray[]:double -- Stores x position of object at each time interval
 *		  -yArray[]:double -- Stores y position of object at each time interval
 *		  -i:int -- integer counter for for loops
 *
 * Methods:
 *         +calculateVelocityIntial():double -- calculates intial velocity based on user inputed values
 *         +calculateVelocityFinal():double -- calculates intial velocity based on user inputed values
 *         +calculateTime():double -- calculates time based on user inputed values
 *         +calculateYDisplacement():double -- calculates x displacement based on user inputted values
 *         +calculateAcceleration():double -- calculates acceleration based on user inputed values
 *         +stringToDouble():void -- converts user inputed string values into doubles
 *         +move():void -- moves free fall object through x positions at .1 time intervals
 *         +fillArray():void -- fills an two arrays with time increments and incremented x positions to be used in a graph in the main gui
 *         +isNumeric(variable:String):boolean -- convert string to a number for a boolean check. If it is a number then it will return true
 *         +toString(variable:double):String -- convert double variables to strings to be able to use isNumeric boolean method 
 *         setters and getters for all attributes 
 *//*Calculates the needed inputs to run the simulation from user input*/


import java.util.Scanner; 

public class Calculator{
	

	protected double velocityIntial, velocityFinal, xDisplacement, time, acceleration;
	protected String velocityIntialStr, velocityFinalStr, xDisplacementStr, timeStr, accelerationStr;
	
	protected double x, y, x_0, y_0, incrementTime;

	protected double g = 9.8;
	
	protected double xArray[];
	protected double yArray[];
	protected int i;
	 
	protected Scanner variableInputs;
	
	//constructor
	public Calculator() {
		setVelocityIntial(0);
		setVelocityFinal(0);
		xDisplacement = 0;
		time = 0;
		acceleration = 0;
		i = 0;
		xArray = new double[5000];
		yArray = new double[5000];
		
		x = 0;
		y = 0;
	    x_0 = 0; //(initial x)
		y_0 = 0; //initial y
		//incrementTime = 0;
		
		velocityFinalStr = toString(velocityFinal);
		velocityIntialStr = toString(velocityIntial);
		accelerationStr = toString(acceleration);
		incrementTime = 0;
		
		variableInputs = new Scanner(System.in);
	}
	
	
	//methods
	//Method used to quick variable inputs through console if needed
	public void inputVariables(){
		
		try {
		System.out.println("Input 'None' if there is no given value");
		
		System.out.println("Enter in your given intial velocity");
		velocityIntialStr = variableInputs.nextLine();
 	 	//checkExists(velocity, 0);
		
		System.out.println("Enter in your given final velocity");
		velocityFinalStr = variableInputs.nextLine();
 	 	//checkExists(velocity, 0);
		
 	 	
 	 	System.out.println("Enter in your given x-Displacement");
 	 	xDisplacementStr = variableInputs.nextLine();
 	 	//checkExists(xDisplacement, 2);
 	 	
 	 	do {
 	 	System.out.println("Enter in your given time");
 	 	timeStr = variableInputs.nextLine();
 	 	//checkExists(time, 3);
 	 		if(timeStr.equals("none")) {
 	 			//time = 0;
 	 			//calculateTime();
 	 		}	
 	 	
 	 		else {
 	 			time = Double.parseDouble(timeStr);
 	 		}
 	 	
 	 	}while(time<0);    //Do while time is less than 0, it will ask again for an input if a negative number is inputted
 	 	
 	 	
 	 	System.out.println("Enter in your given acceleration");
 	 	accelerationStr = variableInputs.nextLine();
 	 	//checkExists(acceleration, 4);
 	 	
 	 	try {
 	 	stringToDouble();
 	 	}catch(Exception e) {
 	 		e.printStackTrace();
 	
 	 	}
 	 	
 	 	System.out.println("intialvelocity = " + velocityIntial + " final velocity = " + velocityFinal 
 	 			+ " x displacement = " + xDisplacement + " acceleration = " + acceleration + " time = "
 	 			+ time); //check string to double conversion
 	 
		}catch(Exception e) {
		e.printStackTrace();
		}
	}
	
	public void stringToDouble() {
		
		for(int i = 0; i < 2; i++) {
		//intial velocity conversion
		if(velocityIntialStr.equals("none") /*|| velocityIntialStr.equals(null)*/) {
			velocityIntial = 0;
			calculateVelocityIntial();
		}
		else {	
			velocityIntial = Double.parseDouble(velocityIntialStr);
		}
		
		//final velocity conversion
		if(velocityFinalStr.equals("none") /*|| velocityFinalStr.equals(null)*/) {
			velocityFinal = 0;
			calculateVelocityFinal();
		}
		else {
			velocityFinal = Double.parseDouble(velocityFinalStr);
		}
		
		
		//x displacement conversion
		if(xDisplacementStr.equals("none") /*|| xDisplacementStr.equals(null)*/){
			xDisplacement = 0;
			calculateXDisplacement();
		}
		else {
			xDisplacement = Double.parseDouble(xDisplacementStr);
		}
		//time = Double.parseDouble(timeStr);
		//calculateTime();
		
		//acceleration conversion
		if(accelerationStr.equals("none") /*|| accelerationStr.equals(null)*/) {
			acceleration = 0;
			calculateAcceleration();
		}
		else {
			acceleration = Double.parseDouble(accelerationStr);
		}
		
		if(timeStr.equals("none") /*|| accelerationStr.equals(null)*/) {
			time = 0;
			calculateTime();
		}
		else {
			time = Double.parseDouble(timeStr);
		}
		
//		if(velocityIntialStr.equals("none") /*|| velocityIntialStr.equals(null)*/) {
//			velocityIntial = 0;
//			calculateVelocityIntial();
//		}
//		else {	
//			velocityIntial = Double.parseDouble(velocityIntialStr);
//		}
	}
		
		
		
		
	}
	
	//Want to see the object move in time intervals of 5 seconds up untill the time calculated or inputted
	public void move() {
		
			
		try {
			
			if(isNumeric(velocityFinalStr) == true && isNumeric(velocityIntialStr) == true ) {
			x = ((velocityFinal+velocityIntial)/2)*incrementTime;
			incrementTime += .5;
			System.out.println("first equation");
			}
			else /*if( isNumeric(velocityIntialStr) && isNumeric(accelerationStr) ) */ {
				x = (velocityIntial*incrementTime) + (0.5*acceleration*Math.pow(time, 2));
				incrementTime += .5;
				System.out.println("2nd equation");
			}
			
			
		} catch(Exception e) {
			
			System.out.println("I cannot solve this problem");
			
		}
	
		
		System.out.println("Object: x = " + x + " time = " + incrementTime + " seconds");	
		
		
		
	}
	
	public void fillArray() {
		
			xArray[i] = incrementTime;
			yArray[i] = x;
			System.out.println("array x: " + xArray[i] + " array y: " + yArray[i]);
			i++;
			
			//System.out.println("array x: " + xArray[i] + " array y: " + yArray[i] );
			
			
	}
	
	//convert string to a number for a boolean check. If it is a number then it will return true
	public boolean isNumeric(String variable) {
	    
		//ToStringDemo(variable);
		
		if (variable == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(variable);
	    } catch (NumberFormatException nfe) {
	    	System.out.println("No number inputted for one of the text fields.");
	        return false;
	    }
	    return true;
	}
	
	/*convert double variables to strings to be able to use isNumeric boolean method to check
	 * if the string is a number to use those variables to increment the time in the move method
	 */
	public String toString(double variable) {
	   
	        String s = Double.toString(variable);
	        return s;
	    
	}
	
	public double calculateVelocityIntial() {
		
		if (velocityFinal < Double.POSITIVE_INFINITY && velocityFinal > Double.NEGATIVE_INFINITY 
				&& acceleration  < Double.POSITIVE_INFINITY && acceleration > Double.NEGATIVE_INFINITY 
				&& time >= 0 && timeStr != "none") {
			
			velocityIntial = velocityFinal - (acceleration*time);
			System.out.println("solved initial velocity " + velocityIntial);
			//velocityIntialStr = velocityIntial;
		}
		
		else if (velocityFinal < Double.POSITIVE_INFINITY && velocityFinal > Double.NEGATIVE_INFINITY
				&& xDisplacement < Double.POSITIVE_INFINITY && xDisplacement > Double.NEGATIVE_INFINITY 
				&& time >= 0 ) {
			
			velocityIntial = 2*(xDisplacement/time)-velocityFinal;
		}
		
		else if(xDisplacement < Double.POSITIVE_INFINITY && xDisplacement > Double.NEGATIVE_INFINITY
				&& acceleration  < Double.POSITIVE_INFINITY && acceleration > Double.NEGATIVE_INFINITY
				&& time >= 0 ) {
			
			velocityIntial = (xDisplacement/time)-((acceleration*time)/2);
		}
		else if(velocityFinal < Double.POSITIVE_INFINITY && velocityFinal > Double.NEGATIVE_INFINITY
				&& xDisplacement < Double.POSITIVE_INFINITY && xDisplacement > Double.NEGATIVE_INFINITY 
				&& acceleration  < Double.POSITIVE_INFINITY && acceleration > Double.NEGATIVE_INFINITY ) {
			
			velocityIntial = Math.sqrt(velocityFinal -(2*acceleration*xDisplacement));
		}
		
		else {
			System.out.println("Sorry, you have inputted less than amount of variable ]s required to solve"
					+ "this problem");
		}
		
		return velocityIntial;
	}
	
	
	public double calculateVelocityFinal() {
		
		if (velocityIntial < Double.POSITIVE_INFINITY && velocityIntial > Double.NEGATIVE_INFINITY 
				&& acceleration  < Double.POSITIVE_INFINITY && acceleration > Double.NEGATIVE_INFINITY 
				&& time >= 0) {
			
			velocityFinal = velocityIntial + (acceleration*time);
		}
		
		else if (velocityIntial < Double.POSITIVE_INFINITY && velocityIntial > Double.NEGATIVE_INFINITY
				&& xDisplacement < Double.POSITIVE_INFINITY && xDisplacement > Double.NEGATIVE_INFINITY 
				&& time >= 0 ) {
			
			velocityFinal = 2*(xDisplacement/time)-velocityIntial; //check this equation again
		}
		
		else if (velocityIntial < Double.POSITIVE_INFINITY && velocityIntial > Double.NEGATIVE_INFINITY
				&& xDisplacement < Double.POSITIVE_INFINITY && xDisplacement > Double.NEGATIVE_INFINITY 
				&& acceleration  < Double.POSITIVE_INFINITY && acceleration > Double.NEGATIVE_INFINITY ){
			
			velocityFinal = Math.sqrt(velocityIntial + (2*acceleration*xDisplacement));
		}
		
		else
			System.out.println("Sorry, you have inputted less than amount of variable ]s required to solve"
					+ "this problem");
		
		return velocityFinal;
	}
	
	
	public double calculateTime() {
		
		if (velocityFinal < Double.POSITIVE_INFINITY && velocityFinal > Double.NEGATIVE_INFINITY 
				&& acceleration  < Double.POSITIVE_INFINITY && acceleration > Double.NEGATIVE_INFINITY 
				&& velocityIntial < Double.POSITIVE_INFINITY && velocityIntial > Double.NEGATIVE_INFINITY ) {
			
			time = (velocityFinal - velocityIntial)/acceleration;
		}
			
		else if(velocityIntial == 0 &&  xDisplacement < Double.POSITIVE_INFINITY && xDisplacement > Double.NEGATIVE_INFINITY
				&& acceleration  < Double.POSITIVE_INFINITY && acceleration > Double.NEGATIVE_INFINITY)	{
			
			time = Math.sqrt((2*xDisplacement)/acceleration); //check this equation
				
			}
		
		else if (velocityIntial < Double.POSITIVE_INFINITY && velocityIntial > Double.NEGATIVE_INFINITY
				&& velocityFinal < Double.POSITIVE_INFINITY && velocityFinal > Double.NEGATIVE_INFINITY 
				&& xDisplacement < Double.POSITIVE_INFINITY && xDisplacement > Double.NEGATIVE_INFINITY) {
			
			time = (xDisplacement)/((velocityFinal + velocityIntial)/2); //check this equation
				
		}
		
		else
			System.out.println("Sorry, you have inputted less than amount of variable ]s required to solve"
					+ "this problem");
		
		return time;
	}
	
	
	public double calculateXDisplacement() {
		
		if(velocityIntial < Double.POSITIVE_INFINITY && velocityIntial > Double.NEGATIVE_INFINITY
				&& velocityFinal < Double.POSITIVE_INFINITY && velocityFinal > Double.NEGATIVE_INFINITY
				&& time >= 0 && timeStr != "none") {
			
			xDisplacement = ((velocityFinal+velocityIntial)/2)*time;
		}
		
		else if(velocityIntial < Double.POSITIVE_INFINITY && velocityIntial > Double.NEGATIVE_INFINITY
				&& acceleration  < Double.POSITIVE_INFINITY && acceleration > Double.NEGATIVE_INFINITY 
				&& time >= 0 && timeStr != "none")
			
			xDisplacement = (velocityIntial*time) + (0.5*acceleration*Math.pow(time, 2));
		
		else if(velocityIntial < Double.POSITIVE_INFINITY && velocityIntial > Double.NEGATIVE_INFINITY
				&& velocityFinal < Double.POSITIVE_INFINITY && velocityFinal > Double.NEGATIVE_INFINITY
				&& acceleration  < Double.POSITIVE_INFINITY && acceleration > Double.NEGATIVE_INFINITY ) {
			
			xDisplacement = (Math.pow(velocityFinal, 2) - Math.pow(velocityIntial, 2))/(2*acceleration);
		}
		
		else
			System.out.println("Sorry, you have inputted less than amount of variable's required to solve"
					+ "this problem");
		
		return xDisplacement;
	}
	
	
	public double calculateAcceleration() {
		
		if(velocityIntial < Double.POSITIVE_INFINITY && velocityIntial > Double.NEGATIVE_INFINITY
				&& velocityFinal < Double.POSITIVE_INFINITY && velocityFinal > Double.NEGATIVE_INFINITY
				&& time >= 0 && timeStr != "none") {
		
			acceleration = (velocityFinal - velocityIntial)/time;
		}
		
		else if (velocityIntial < Double.POSITIVE_INFINITY && velocityIntial > Double.NEGATIVE_INFINITY
				&& xDisplacement < Double.POSITIVE_INFINITY && xDisplacement > Double.NEGATIVE_INFINITY 
				&& time >= 0 ) {
		
			acceleration = (2*(xDisplacement - (velocityIntial*time)))/Math.pow(time, 2);
		}
		
		else if (time > 0 && velocityIntial == 0 
				&& xDisplacement < Double.POSITIVE_INFINITY && xDisplacement > Double.NEGATIVE_INFINITY ) {
			
			acceleration = (2*xDisplacement)/Math.pow(time, 2);
		}
		
		else
			System.out.println("Sorry, you have inputted less than amount of variable ]s required to solve"
					+ "this problem");
		
		return acceleration;
	}
	
	
	
	
	
	
	
	
	
	
	
	//Setters and getters

	public double getxDisplacement() {
		return xDisplacement;
	}

	public void setxDisplacement(double xDisplacement) {
		this.xDisplacement = xDisplacement;
	}

	public double getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}
	
	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public double getVelocityIntial() {
		return velocityIntial;
	}

	public void setVelocityIntial(double velocityIntial) {
		this.velocityIntial = velocityIntial;
	}

	public double getVelocityFinal() {
		return velocityFinal;
	}

	public void setVelocityFinal(double velocityFinal) {
		this.velocityFinal = velocityFinal;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX_0() {
		return x_0;
	}

	public void setX_0(double x_0) {
		this.x_0 = x_0;
	}

	public double getY_0() {
		return y_0;
	}

	public void setY_0(double y_0) {
		this.y_0 = y_0;
	}

	public double getIncrementTime() {
		return incrementTime;
	}

	public void setIncrementTime(double incrementTime) {
		this.incrementTime = incrementTime;
	}
	
//	public double getX() {
//		return x;
//	}
//
//	public void setX() {
//		this.x = x;
//	}
}