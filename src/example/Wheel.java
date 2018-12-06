package example;

//This import is needed for your code to know what a Spark object is and how to use it!
import edu.wpi.first.wpilibj.Spark;

//Confused about something or need more detail? Ask Hershal or Evan!

//You need to remember this "implements Runnable" part or the code won't work!
//This is going to force you to add a run() method because it is an interface. Don't worry too much about that; it's a concept we'll cover later.
public class Wheel implements Runnable {
	/*
	 * Create sparks here for however many wheels your teams is using (should be at
	 * most 2) Remember that this is private, meaning that it is particular to this
	 * class, aka Wheel.java
	 */
	private Spark myWheel;
	private Spark myWheel2; // Only needed if using a second wheel

	/*
	 * I think you'll be able to figure out the XboxController implementation based
	 * on the comments and your ability to infer and apply information. If you have
	 * any questions, just ask Hershal
	 */
	private XboxController xbox;

	/*
	 * The comment above the constructor is called a javadoc comment, which means it
	 * explains what a function does, any parameters it takes, and the return. Wanna
	 * test it? Hover over Wheel and see what it says!
	 */

	/**
	 * Refresher: This is the constructor, aka the code that is ran whenever an
	 * instance of the Wheel object is made. This is a fancy way of saying this is
	 * the code that runs when you use this object.
	 * 
	 * @param channel The channel that the Spark will be plugged into on the
	 *                roboRIO. Remember that this is a local variable, meaning it
	 *                only exists inside this constructor.
	 */
	public Wheel(int channel) {
		myWheel = new Spark(channel);
		xbox = new XboxController(0);
		startWheel();
	}

	/*
	 * Wait, you have two constructors with the same name! How does that work? Glad
	 * you asked! This is what is known as "overloading." Essentially, this means
	 * that I can have the same method name (or in this case, a constructor) as long
	 * as I have two different sets of parameters. Notice how the first constructor
	 * only has one int parameter while this one has two. As long as you have a
	 * different set of parameters (in type or order; you have to change more than
	 * the name) you can overload it.
	 * 
	 * Why do this? This allows for greater control over the code. For example, in
	 * this case, I have prepared this Wheel object for two scenarios: one with one
	 * wheel and one with two. I can write code specific to both uses and make my
	 * code more robust without requiring any complex steps from other programmers
	 * using this object!
	 */
	/**
	 * This constructor makes a new instance of the Wheel object when using two
	 * Wheels. This means that you need two Sparks, so it requires two channels as
	 * parameters.
	 * 
	 * @param channelOne
	 * @param channelTwo
	 */
	public Wheel(int channelOne, int channelTwo) {
		myWheel = new Spark(channelOne);
		myWheel2 = new Spark(channelTwo);
		xbox = new XboxController(0);
		startWheel();
	}

	/*
	 * Generally, running would be instantiated up top by the Sparks, but it is here
	 * for convenience.
	 */
	private boolean running = false;

	public void startWheel() {
		// Synchronized ensures that, in a multi-threaded project, two threads can't
		// access the same resource at the same time, preventing errors and other issues
		synchronized (this) {
			/*
			 * If running is already true, we do not want to create a second Thread, so we
			 * simply return out of the method and do nothing.
			 * 
			 * Remember, you may see this elsewhere written as:
			 * 
			 * if(running)
			 * 
			 * return;
			 * 
			 * This does the same thing! After an if statement, if there are no braces (aka
			 * { and } ) then it will consider the next line to be part of the conditional.
			 * Be careful about this! Don't accidentally exclude a line of the code from the
			 * conditional because there are no braces; add them!
			 */
			if (running) {
				return;
			}

			running = true;
			/*
			 * This creates a new Thread.
			 * 
			 * The first parameter, "this" refers to this class: Wheel.java
			 * 
			 * The second parameter, "wheelThread", is a String which is used as the
			 * thread's name
			 */
			new Thread(this, "wheelThread").start();
		}
	}

	/**
	 * This method is ran when the Thread is started. Essentially, once line 108 is
	 * ran it goes here
	 */
	@Override
	public void run() {
		while (running) {
			/*
			 * This is where your code to use the Sparks goes!
			 */

			// As an example, this sets the motors to run at half power forever.
			myWheel.set(0.5);
			myWheel2.set(0.5);

			// You can also use the XboxController object we made here. Ex:
			if (xbox.getButton(XboxController.BUTTON_A)) {
				/*
				 * This code is ran whenever the A button on the controller is pressed. Notice
				 * how BUTTON_A was accessed. This is how you access static information. Use the
				 * name of the class itself, then a period, then the static variable or method
				 * you want to access. Confused about this concept? Ask Hershal or Evan!
				 */
			}

			/*
			 * Every thread should have a Thread.sleep which makes it wait for some
			 * specified amount of milliseconds. Please note this is NOT seconds so please
			 * don't write Thread.sleep(1); All Thread.sleep calls have to be surrounded by
			 * a try/catch, which essentially acts as a failsafe if something breaks
			 */
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Pop quiz! What does this do?
	 * 
	 * @return
	 */
	public boolean getRunning() {
		return running;
	}

	/**
	 * You're all smart kids, I think you can figure out what this does!
	 */
	public void stop() {
		myWheel.set(0);
		myWheel2.set(0);
	}

	/**
	 * I'm sorry in advance if you're a devout Christian and I offended you
	 */
	public void summonSatan() {
		// Don't worry about this method
	}

}
