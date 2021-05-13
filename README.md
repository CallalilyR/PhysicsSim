# PhysicsSim
INTRODUCTION: 
The project is a physics simulator, and it was created to help struggling students better understand the physical sciences through visually demonstrating the uses of kinematic equations and how they apply to reality. Reading purely technical physics textbooks while writing down a bunch of equations, letters, and symbols makes it difficult to fully understand the concepts trying to be learned. So, this program will aid in clearing up confusion by taking in the user’s problem through the values they were given and visually represent what the question is asking through graphs and a simulated environment that can be edited based on what the user inputs. Like the background setting of where the simulation will take place, the shape of the objects in the problem.

PROBLEM DESCRIPTION:
The program will build a simulation that will simulate regular 2D motion, free fall, and projectile motion physics problems that contain a constant acceleration and ignore worldly forces such as air resistance. The goal of this program is to help the user using the program to visualize the problem they are working on. The program will allow users to input their knowns (know variables in the problem) such as acceleration, final velocity, initial velocity, change in distance/displacement, and time. Moreover, the program will solve for the unknown values in regular 2D motion and free fall problems but will only simulate projectile motion problems if the user knows and inputs all the required knowns being height, angles, and velocity. Although, if the user solves the problem not on the program and knows all the variables listed above then they will be able to input all their information for any of the problem option previously states to fully simulate their problem. This feature will assist in letting the user test their values and visually see if their solved values, in their senses, logically correlate to their problem. Moreover, the equations used to operate the program will be the 4 main kinematic equations in the x and y directions listed below.
V(final velocity) = V0(initial velocity) + a(acceleration)*t(time)
deltaX or deltaY(change in displacement) = ((V+V0)/2) * t
deltaX or deltaY = V0*t + 0.5*a*t^2
V^2 = V0^2 + 2*a*deltaX or deltaY
(Khan Academy, What are the kinematic formulas? (article))

Moreover, inputting certain variables will affect the position and motion of the objects through a graphical user interface (GUI). Based on the user’s discretion of the main object, a randomized shape will be presented in the GUI to represent the main object of the user’s problem. In the GUI simulation, it will show the main object moving in accordance with the given knowns, neglecting the missing variable(s) the user is attempting to solve. In addition, to improve comprehension efficiency, the program will offer the user environment options such as water, hills, stick figure characters, etc. to add to the GUI. These techniques have been successfully used in other physics simulators made by other students, universities, and companies.

USER STORIES:
As a user, I want to be able to input the values given
As a developer, I want to be able to calculate the complete motion of the main object
As a developer, I want to be able to see the number values of the movement of the object in question at each time interval
As a developer, I want to be able to simulate freefall, 2D Motion, and projectile motion problems
As a user, I want to be able to use the physics simulator through a graphical user interface (GUI).
As a developer, I want to represent the objects as shapes
As a developer, I want to be able to provide a simulation while displaying the variables calculated and given
As a developer, I want to be able see to the movement of the object in question at each time interval
As a user, I want to be able to go back to a previous screen
As a user, I want to be able edit the environment to better represent my problem
As a user, I want to be able to see a graph representation of my problem
As a developer, I want to provide the user an opportunity to save their simulations generated

PROBLEM SOLUTION:
There are seven classes, Environment.java, reg2DMotionPane.java, projectilePane.java, freeFallPane.java, Calculator.java,  FreeFall.java, and ProjectileMotion.java. The environment class is the main GUI (graphical user interface) class that contains the main method to run the program. Its main purpose is to handle the other GUI pane classes while also overseeing the event handlers and graphs for each pane. Event handlers being, assigned actions to button presses in the program. In addition, the main tasks of the reg2DMotionPane, projectilePane, and freeFallPane classes are to control the GUI portion of the simulation in relation to creating the text fields (user input boxes) and printed text on the screen to make the program more user friendly and while also making objects, such as a circle move across the screen. Wherein the purpose of the calculator and its children classes, ProjectileMotion and FreeFall are to handle the back end portion of a moving GUI object by utilizing methods and attributes that calculate unknown variables, x and y position at 0.5 or 0.1 time intervals, and converting user inputted strings into numerical values. They do this by using those inputted values in the GUI pane classes and translating the user input into a numerical value the computer can understand and solve for other variables needed to calculate the position of an object at any time interval. 

REFERENCES:
“What Are the Kinematic Formulas? (Article).” Khan Academy, Khan Academy, www.khanacademy.org/science/physics/one-dimensional-motion/kinematic-formulas/a/what-are-the-kinematic-formulas. 

APPENDICES:
none
