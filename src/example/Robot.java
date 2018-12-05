package example;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */

/**
 * This is the Robot class, essentially the "main hub" of the project.
 * 
 * If the organization/path the code follows is confusing, here's a quick
 * rundown: -robotInit() is ran when the robot is turned on
 * 
 * -When autonomous is enabled from the DriverStation, the code runs
 * autonomousInit()
 * 
 * -autonomousInit() instantiates the Wheel objects.
 *
 * -This takes the code to the Wheel constructor(s) which make the Sparks and
 * run startWheel()
 * 
 * -startWheel() creates the wheelThread and starts it
 * 
 * -The code goes to run(), which continues to loop and set the Sparks according
 * to the code
 * 
 * -Once autonomous is disabled, the code runs disabledInit() which calls
 * wheel.stop()
 * 
 * -wheel.stop() sets the Sparks to 0 speed and stops the motor(s)
 *
 */
public class Robot extends IterativeRobot {
	// This line tells Java that a Wheel object named wheel exists, but it does NOT
	// actually make the object!!!
	Wheel wheel;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		// This can be left empty
	}

	/**
	 * This function is run when autonomous mode is first started up and should be
	 * used for any autonomous initialization code
	 * 
	 * This code starts the wheelThread when auto is enabled
	 */
	@Override
	public void autonomousInit() {
		// This actually instantiates (or "creates") a wheel object with channel 1
		wheel = new Wheel(1);

		// This line works because of the method overloading from Wheel.java
		@SuppressWarnings("unused")
		Wheel wheel2 = new Wheel(2, 3);
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		// This can be left empty
	}

	/**
	 * This function is run when teleop mode is first started up and should be used
	 * for any teleop initialization code.
	 */
	@Override
	public void teleopInit() {
		// This can be left empty
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		// This can be left empty
	}

	/**
	 * This function is run when test mode is first started up and should be used
	 * for any test initialization code.
	 */
	@Override
	public void testInit() {
		// This can be left empty
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		// This can be left empty
	}

	/**
	 * This function is run when a mode is initially disabled and should be used for
	 * any disabling code.
	 */
	@Override
	public void disabledInit() {
		wheel.stop();
	}

	/**
	 * This function is called periodically when a mode is disabled.
	 */
	@Override
	public void disabledPeriodic() {
		wheel.stop(); // I'm not 100% sure if this needs to be here, but better to be safe than sorry
	}
}