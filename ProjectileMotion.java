
/*
 * ProjectileMotion.java
 * CS 225 Spring 2021
 * Written by: Calla Robison 
 * Last edited: 5/4/2021
 * Base: Child of calculator, deals with projectile motion
 * 
 * Purpose: to handle the back end portion of moving the object in projectile motion and converting the inputted strings
 * in the projectilePane class into doubles to be used for other purposes. 
 * Attributes: 
 *        -angle:double -- Stores angle after conversion of string to double    
 *        -height:double -- Stores height after conversion of string to double    
 *		  -velocity:double -- Stores velocity after conversion of string to double    
 *		  -velocityStr:String -- Stores velocity string user input   
 *		  -heightStr:String -- Stores height string user input 
 *		  -angleStr:String -- Stores angle string user input 
 *		  -x:double -- variable for x position of projectile motion object
 *		  -y:double -- variable for y position of projectile motion object
 *
 *
 * Methods:
 *         +stringToDouble():void -- converts user inputted string values into doubles
 *         +move():void -- moves projectile motion object through the x positions at .1 time intervals
 *         +fillArray():void -- fills an two arrays with time increments and incremented x positions to be used in a graph in the main gui
 *         setters and getters for all attributes 
 */ 


public class ProjectileMotion extends Calculator{
	
	protected double angle, height, velocity;
	protected String velocityStr, angleStr, heightStr, velocityXStr, velocityYStr;
	protected double x = 0;
	protected double y = 0;
	
	//Constructor
	public ProjectileMotion(){
		super();
	
		this.y = y_0;
		this.angle = Math.toRadians(angle);
		height = 0;

		incrementTime = 0;
		
	}
	

	
	//Method used to quick variable inpus through console if needed
	@Override
	public void inputVariables() {
		
		try {
		System.out.println("Projectile Motion");
		System.out.println("Input 'None' if there is no given value");
		
		System.out.println("Enter in your given velocity");
		velocityStr = variableInputs.nextLine();
		

		System.out.println("Enter in your given angle");
		angleStr = variableInputs.nextLine();
 	 	
		System.out.println("Enter in your given height");
		heightStr = variableInputs.nextLine();
		
		do {
	 	 	System.out.println("Enter in your given time");
	 	 	timeStr = variableInputs.nextLine();
	 	 	//checkExists(time, 3);
	 	 		if(timeStr.equals("none")) {
	 	 			time = 0;
	 	 			calculateTime();
	 	 		}	
	 	 	
	 	 		else {
	 	 			time = Double.parseDouble(timeStr);
	 	 		}
	 	 	
	 	 	}while(time<0);    //Do while time is less than 0, it will ask again for an input if a negative number is inputted

		stringToDouble();
	}catch(Exception e) {
		e.printStackTrace();
	}
	}
	
	public void move() {
		
		
		//int intialXPosition = 0
		
		//just put the equations from the hw doc in here
		
		
		
		//do {
			
			
		
			/*Check these equations*/
			x = (velocity * incrementTime * Math.cos(Math.toRadians(angle))) + x_0;
			y = (velocity * incrementTime *Math.sin(Math.toRadians(angle))) - (0.5 * g * Math.pow(incrementTime, 2)) + y_0;
			
			System.out.println("Object: x = " + x + " y =  " + y + " time = " + incrementTime + " seconds");
			
			incrementTime += .1;
		
		//}while (y >= 0);
		
	}
	
	@Override
	public void fillArray() {
		
		xArray[i] = incrementTime;
		yArray[i] = y;
		System.out.println(xArray[i] + yArray[i]);
		i++;
		
		
}
	
	@Override
	public void stringToDouble() {
		//intial velocity conversion
			if(velocityStr.equals("none") || velocityStr.equals(null)) {
				velocity = 0;
			}
			else {	
				velocity = Double.parseDouble(velocityStr);
			}
			
			if(heightStr.equals("none") || heightStr.equals(null)
					) {
				height = 0;
			}
			else {
				height = Double.parseDouble(heightStr);
			}
			
			if(angleStr.equals("none") || heightStr.equals(null)
					) {
				angle = 0;
			}
			else {
				angle = Double.parseDouble(angleStr);
			}
	}
	
	
	
	
	
	
	
	//Setters and Getters
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

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}
}
