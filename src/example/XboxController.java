package example;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Wrapper class for Joystick that adds useful methods as well as rumble!
 * 
 * @author FRC Team 2849 URSA MAJOR 2016 Season
 */
//This "extends Joystick" part is important! Read the explanation about inheritance in the constructor for more information
public class XboxController extends Joystick implements Runnable {

	/*
	 * These are finals. This means that they are data types which NEVER change. As
	 * such, they are defined at the very top and the naming convention is all caps
	 * with underscores instead of spaces.
	 * 
	 * Each final refers to a different button on the Xbox controller. Using a
	 * specific final is an easy way for the user to read what the code is doing
	 * while allowing the code to recognize what button is actually being use in any
	 * given function.
	 */
	public static final int BUTTON_A = 1;
	public static final int BUTTON_B = 2;
	public static final int BUTTON_X = 3;
	public static final int BUTTON_Y = 4;
	public static final int BUTTON_LEFTBUMPER = 5;
	public static final int BUTTON_RIGHTBUMPER = 6;
	public static final int BUTTON_BACK = 7;
	public static final int BUTTON_START = 8;

	// These refer to clicking down on the left or right control stick
	public static final int BUTTON_LEFTSTICK = 9;
	public static final int BUTTON_RIGHTSTICK = 10;

	public static final int AXIS_LEFTSTICK_X = 0;
	public static final int AXIS_LEFTSTICK_Y = 1;
	public static final int AXIS_LEFTTRIGGER = 2;
	public static final int AXIS_RIGHTTRIGGER = 3;
	public static final int AXIS_RIGHTSTICK_X = 4;
	public static final int AXIS_RIGHTSTICK_Y = 5;

	// POV refers to the D-Pad
	public static final int POV_NONE = -1;
	public static final int POV_UP = 0;
	public static final int POV_RIGHT = 90;
	public static final int POV_DOWN = 180;
	public static final int POV_LEFT = 270;

	private boolean running = false;

	private long rumbleStopTime = 0;

	/*
	 * These Latches are defined as Objects in the bottom of this java file. Since
	 * the Latch object is only used in this file and is so short, it is better to
	 * simply add another class in this same file for that object. Remember, a class
	 * and a java file are not the same thing. One file can have multiple classes.
	 * For convenience, it may make more sense to imagine that the Latch class is in
	 * it's own file, similar to how we coded objects during training.
	 * 
	 * Additionally, these lines create ARRAYS of Latches, not just a Latch object.
	 * Think of an array as a group of multiple data types. While generally used
	 * with primitives such as ints or doubles, they can also be used with objects
	 * as seen here. When created without defining any values, all values in the
	 * array are 0, empty, or null depending on the data type.
	 * 
	 * We know that these are arrays because they contain [] in the object
	 * declaration. Normally, an object is defined as Object myObject = new
	 * Object(); However, when making an array of objects, it is defined as Objecy
	 * myObject[] = new Object[SIZE];
	 * 
	 * Comprehension check! What are the sizes of buttonLatch and axisLatch?
	 */
	Latch buttonLatch[] = new Latch[10];
	Latch axisLatch[] = new Latch[6];

	public XboxController(int port) {
		/*
		 * The keyword super is brand new to us! Let's talk a bit about what it means
		 * and what it does. To understand the "super" keyword, we must first talk about
		 * inheritance. Inheritance is the concept that one Object class may "inherit"
		 * features (such as fields, methods, even variables) from a "parent" class.
		 * This is normally done so that one class may build on another without adding
		 * unneccessary features to the original.
		 * 
		 * For example, a class which defines a Bike object may construct an object
		 * based on the bike's gear and speed, and possess methods to speed up or slow
		 * down. If I wanted to now make a MountainBike object, I COULD rewrite all of
		 * the code I wrote for the Bike object, OR I can have MountainBike inherit
		 * those features from the Bike object. Then, in my MountainBike class I can add
		 * a method to set my seat height; a method that is unique to my MountainBike
		 * class. Since Mountain Bikes are still Bikes, they possess all features of
		 * Bikes, however they also contain their own features unique to MountainBikes.
		 * For another example, I coud have a Vehicle class be the parent class, and
		 * then have a Truck class inherit features from the Vehicle class. All Trucks
		 * are Vehicles, and all MountainBikes are Bikes, but not vice versa.
		 * 
		 * To actually inherit methods, you simply need to use the "extends" keyword in
		 * the header for the class (Scroll up if you're unsure what I mean). Please
		 * note that this is a DIFFERENT keyword than "implements." We will discuss the
		 * specific differences between the two once we discuss interfaces, but for now
		 * just remember that "extends" is for inheritance.
		 * 
		 * Now that the XboxController class extends "Joystick" (which is a pre-written
		 * class that comes in our FRC plugins) we can add our own methods onto it
		 * (which are unique to XboxController) so that it can better suit our needs. We
		 * still get the benefits of the Joystick class, but we can add onto it. Instead
		 * of rewriting the constructor, we can just re-use the Joystick constructor by
		 * calling "super(port)" which takes a parameter port and uses the Joystick
		 * constructor to create our XboxController object.
		 * 
		 * Type "super." in eclipse and take a look at all the different methods we have
		 * access to through the Joystick class! Want to read the Joystick class code?
		 * Click on Joystick up at the top and press F3.
		 * 
		 */
		super(port);

		// We've seen this code before! If you don't know/don't remember, look at
		// Wheel.java!
		Thread rumbleThread = new Thread(this, "rumbleThread");
		rumbleThread.start();

		/*
		 * These are called "for each" loops. Essentially, it is used to quickly and
		 * easily loop through an entire array of some object so that some action can be
		 * performed for each object. In this case, the code says that "for each Latch
		 * (which we are locally naming num1) in the array of Latches named buttonLatch,
		 * perform this set of tasks." For this case, the set of tasks is simply making
		 * a new Latch() for every instance of num1 in the array.
		 */
		for (Latch num1 : buttonLatch) {
			num1 = new Latch();
		}
		for (Latch num2 : axisLatch) {
			num2 = new Latch();
		}
	}

	/**
	 * Starts the controller rumbling for a set amount of time
	 * 
	 * @param rumbleTime time for the controller to rumble in milliseconds
	 */
	public void rumbleFor(int rumbleTime) {
		rumbleStopTime = System.currentTimeMillis() + rumbleTime;
	}

	/**
	 * Stops the controller from rumbling
	 */
	public void stopRumble() {
		rumbleStopTime = System.currentTimeMillis();
	}

	/**
	 * Gets the value of a button
	 * 
	 * @param buttonNumber the button whose value is to be read
	 * @return the button's value
	 */
	public boolean getButton(int buttonNumber) {
		return super.getRawButton(buttonNumber);
	}

	/**
	 * Gets the value of an axis
	 * 
	 * @param axisNumber the axis whose value is to be read
	 * @return the axis' value
	 */
	public double getAxis(int axisNumber) {
		return this.getRawAxis(axisNumber);
	}

	/**
	 * Checks if an axis is greater than (NOT equal to) a threshold
	 * 
	 * @param axisNumber  axis whose value is to be compared
	 * @param greaterThan value to compare the axis to
	 * @return true if the axis is greater than the threshold, false otherwise
	 */
	public boolean getAxisGreaterThan(int axisNumber, double greaterThan) {
		return this.getRawAxis(axisNumber) > greaterThan;
	}

	/**
	 * Checks if an axis is less than (NOT equal to) a threshold
	 * 
	 * @param axisNumber axis whose value is to be compared
	 * @param lessThan   value to compare the axis to
	 * @return true if the axis is less than the threshold, false otherwise
	 */
	public boolean getAxisLessThan(int axisNumber, double lessThan) {
		return this.getRawAxis(axisNumber) < lessThan;
	}

	public double getSquaredAxis(int axisNumber) {
		double rawInput = this.getAxis(axisNumber);
		return rawInput * Math.abs(rawInput);
	}

	/**
	 * Checks if a specified POV is pressed
	 * 
	 * @param dPadNumber the POV value to be checked
	 * @return true if the specified POV is pressed, false otherwise
	 */
	public boolean getDPad(int dPadNumber) {
		return this.getPOV(0) == dPadNumber;
	}

	/**
	 * Ensures that a button input is only received once by the code
	 * 
	 * @param indexnum
	 * @return
	 */
	public boolean getSingleButtonPress(int indexnum) {
		return buttonLatch[indexnum - 1].buttonPress(getButton(indexnum));
	}

	public boolean getSingleAxisPress(int indexnum) {
		return axisLatch[indexnum - 1].buttonPress(getAxisGreaterThan(indexnum, 0.25));
	}

	/**
	 * Started on object init, runs in background and monitors rumble
	 */

	/*
	 * Comprehension check! Are you able to understand everything going on in this
	 * run method?
	 * 
	 * Questions to ask yourself: Why are we using a while loop instead of a for
	 * loop? What does the if statement check and execute? What does the try/catch
	 * block and the Thread.sleep do? Why do I need it?
	 */

	public void run() {
		while (running) {
			if (System.currentTimeMillis() - rumbleStopTime < 0) {
				this.setRumble(RumbleType.kLeftRumble, 1);
				this.setRumble(RumbleType.kRightRumble, 1);
			} else {
				this.setRumble(RumbleType.kLeftRumble, 0);
				this.setRumble(RumbleType.kRightRumble, 0);
			}
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This class is used to ensure that a button is only registered once per click.
	 * 
	 * Rising edge detector
	 * 
	 * @author kingeinstein
	 *
	 */

	public class Latch {
		private boolean lastInput = false;

		// TODO empty constructor
		public Latch() {

		}

		/**
		 * Ensures that a button is only registered once per button press.
		 * 
		 * @param button Boolean value for whether or not a button is pressed
		 * @return True if registering a new press; False if the press was already
		 *         registered
		 */
		public boolean buttonPress(boolean button) {
			/*
			 * On the first call, lastInput is false and button will be true (meaning the
			 * button was pressed). Then, press is set to true, because the expression
			 * "!(false) && true" is the same as "true && true," which returns true.
			 * lastInput is then set to button, which is true, and press (which is also
			 * true) is returned.
			 * 
			 * Now, if buttonPress() is called again while the user is still holding the
			 * button, then this function runs again. This time, press equals
			 * "!(true) && true," which is the same as "false && true," which returns false.
			 * Since button is false, lastInput now equals false and press (which is also
			 * false) is then returned.
			 * 
			 * Once the user releases the controller button, then the boolean button is
			 * false. Thus, press equals "!(false) && false," which is false. lastInput is
			 * set to false, and false is returned, so this cycle can once again repeat
			 * whenever another button is pressed.
			 */
			boolean press = !lastInput && button;
			lastInput = button;
			return press;

		}
	}

}