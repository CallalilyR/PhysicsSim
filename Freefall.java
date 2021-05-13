/*
 * Freefall.java
 * CS 225 Spring 2021
 * Written by: Calla Robison 
 * Last edited: 5/4/21
 * Purpose:  to handle the back end portion of moving the object in free fall motion and converting the inputted strings
 * in the freeFallPane class into doubles to be used for other purposes. child of calculator, deals with free fall
 * Attributes: 
 *        -yDisplacementStr:String -- Stores y displacement string user input
 *        -yDisplacement:double -- Stores y displacement after conversion of string to double    
 *
 * Methods:
 *         +calculateYDisplacement():double -- calculates y displacement based on user inputted values
 *         +stringToDouble():void -- converts user inputted string values into doubles
 *         +move():void -- moves free fall object through x positions at .1 time intervals
 *         +calculateVelocityIntial():double -- calculates intial velocity based on user inputted values
 *         +fillArray():void -- fills an two arrays with time increments and incremented x positions to be used in a graph in the main gui
 *         setters and getters for all attributes 
 */

public class Freefall extends Calculator{

	protected String yDisplacementStr;
	protected double yDisplacement;
	
	//Constructor
	public Freefall(){
		super();
		yDisplacement = 0;
		acceleration = g;
//		fillCheckList(6);
//		extendCheckList();
		
		velocityFinalStr = toString(velocityFinal);
		velocityIntialStr = toString(velocityIntial);
		accelerationStr = toString(acceleration);
		incrementTime = 0;
	}
	
	
	//Method used to quick variable inputs through console if needed
	@Override
	public void inputVariables(){
			
		try {
			System.out.println("FreeFall");
			System.out.println("Input 'None' if there is no given value");
			
			System.out.println("Enter in your given intial velocity");
			velocityIntialStr = variableInputs.nextLine();
	 	 	//checkExists(velocity, 0);
			
			System.out.println("Enter in your given final velocity");
			velocityFinalStr = variableInputs.nextLine();
	 	 	//checkExists(velocity, 0);
			
	 	 	System.out.println("Enter in your given y-Displacement");
	 	 	yDisplacementStr = variableInputs.nextLine();
	 	 	//checkExists(yDisplacement, 1);
	 	 	
	 	 	do {
	 	 	System.out.println("Enter in your given time");
	 	 	timeStr = variableInputs.nextLine();
	 	 	//checkExists(time, 3);
	 	 		if(timeStr.equals("none")) {
	 	 			time = 0;
	 	 		}	
	 	 	
	 	 		else {
	 	 			time = Double.parseDouble(timeStr);
	 	 		}
	 	 	
	 	 	}while(time<0);    //Do while time is less than 0, it will ask again for an input if a negative number is inputted
	 	 	
	 	 	
	 	 	stringToDouble();
	 	 	
	 	 	System.out.println(velocityIntial); //check string to double conversion
	 	 
		}catch(Exception e) {
			
		}
	}
	
	public double calculateYDisplacement() {
	
			
			if(velocityIntial < Double.POSITIVE_INFINITY && velocityIntial > Double.NEGATIVE_INFINITY
					&& velocityFinal < Double.POSITIVE_INFINITY && velocityFinal > Double.NEGATIVE_INFINITY
					&& time >= 0 && timeStr != "none") {
				
				yDisplacement = ((velocityFinal+velocityIntial)/2)*time;
			}
			
			else if(velocityIntial < Double.POSITIVE_INFINITY && velocityIntial > Double.NEGATIVE_INFINITY
					&& acceleration == g 
					&& time >= 0 && timeStr != "none")
				
				yDisplacement = (velocityIntial*time) - (0.5*g*Math.pow(time, 2));
			
			else if(velocityIntial < Double.POSITIVE_INFINITY && velocityIntial > Double.NEGATIVE_INFINITY
					&& velocityFinal < Double.POSITIVE_INFINITY && velocityFinal > Double.NEGATIVE_INFINITY
					&& acceleration  == g ) {
				
				yDisplacement = (Math.pow(velocityFinal, 2) - Math.pow(velocityIntial, 2))/-(2*g);
			}
			
			else
				System.out.println("Sorry, you have inputted less than amount of variable s required to solve"
						+ "this problem");
			
			
			return yDisplacement;
		}

	@Override
	public void stringToDouble() {
	
		
		//y displacement conversion
				
		for(int i = 0; i < 2; i++) {
				//intial velocity conversion
				if(velocityIntialStr.equals("none") || velocityIntialStr.equals(null)) {
					velocityIntial = 0;
					calculateVelocityIntial();
				}
				else {	
					velocityIntial = Double.parseDouble(velocityIntialStr);
				}
				
				//final velocity conversion
				if(velocityFinalStr.equals("none") || velocityFinalStr.equals(null)) {
					velocityFinal = 0;
					calculateVelocityFinal();
				}
				else {
					velocityFinal = Double.parseDouble(velocityFinalStr);
				}
				
				if(yDisplacementStr.equals("none")|| yDisplacementStr.equals(null)) {
					yDisplacement = 0;
					calculateYDisplacement();
				}
				else {
					yDisplacement = Double.parseDouble(yDisplacementStr);
				}
				
				if(timeStr.equals("none") /*|| accelerationStr.equals(null)*/) {
					time = 0;
					calculateTime();
				}
				else {
					time = Double.parseDouble(timeStr);
				}
		}
				
				
	}

	@Override
	public void move() {
		
			try {
		
			//If user has intial velocity and acceleration
			//if(isNumeric(velocityIntialStr) && isNumeric(accelerationStr)) {
//			if(isNumeric(velocityFinalStr) == true && isNumeric(velocityIntialStr) == true ) {
//				y = ((velocityFinal+velocityIntial)/2)*incrementTime;
//				incrementTime += .1;
//				System.out.println("first equation");
//				}
//			else {
			y = (velocityIntial*incrementTime) + (0.5*g*Math.pow(incrementTime, 2));;
			incrementTime += .1;
			//}
		} catch(Exception e) {
			
			System.out.println("I cannot solve this problem");
			
		}
			
				//}
			
			System.out.println("Object: y = " + y + " time = " + incrementTime + " seconds");	
			
			
			
		}

	@Override
	public double calculateVelocityIntial() {
		
		if (velocityFinal < Double.POSITIVE_INFINITY && velocityFinal > Double.NEGATIVE_INFINITY 
				&& acceleration  == g 
				&& time >= 0 && timeStr != "none") {
			
			velocityIntial = velocityFinal - (acceleration*time);
			//velocityIntialStr = velocityIntial;
		}
		
		else if (velocityFinal < Double.POSITIVE_INFINITY && velocityFinal > Double.NEGATIVE_INFINITY
				&& yDisplacement < Double.POSITIVE_INFINITY && yDisplacement > Double.NEGATIVE_INFINITY 
				&& time >= 0 ) {
			
			velocityIntial = 2*(yDisplacement/time)-velocityFinal;
		}
		
		else if(yDisplacement < Double.POSITIVE_INFINITY && yDisplacement > Double.NEGATIVE_INFINITY
				&& acceleration == g
				&& time >= 0 ) {
			
			velocityIntial = (yDisplacement/time)-((acceleration*time)/2);
		}
		else if(velocityFinal < Double.POSITIVE_INFINITY && velocityFinal > Double.NEGATIVE_INFINITY
				&& yDisplacement < Double.POSITIVE_INFINITY && yDisplacement > Double.NEGATIVE_INFINITY 
				&& acceleration == g ) {
			
			velocityIntial = Math.sqrt(velocityFinal -(2*acceleration*yDisplacement));
		}
		else {
			System.out.println("Sorry, you have inputted less than amount of variable ]s required to solve"
					+ "this problem");
		}
		
		return velocityIntial;
	}
	
	@Override
	public void fillArray() {
		
		xArray[i] = incrementTime;
		yArray[i] = y;
		System.out.println(xArray[i] + yArray[i]);
		i++;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	//Setters and getters
	public double getyDisplacement() {
		return yDisplacement;
	}
	
	public void setyDisplacement(double yDisplacement) {
		this.yDisplacement = yDisplacement;
	}
	
	
	}