package frc.robot.commands.gamepad;

//Import the joystick class
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class OI
{
    //Create the joystick
    public Joystick drivePad;
    public Button buttonStart;
    public Button buttonA;
    public Button buttonY;

    public OI()
    {
        //initialize the joystick 
        drivePad = new Joystick(GamepadConstants.DRIVE_USB_PORT);
        buttonStart = new JoystickButton(drivePad, GamepadConstants.START_BUTTON);//.whenPressed( m_menu.GetCmd() );
        buttonA = new JoystickButton(drivePad, GamepadConstants.A_BUTTON);//.whenPressed( ()->{Globals.menuItem--;Globals.menuItem%=4;} );
        buttonY = new JoystickButton(drivePad, GamepadConstants.Y_BUTTON);//.whenPressed( ()->{Globals.menuItem++;Globals.menuItem%=4;});
    }

    /**
     * Drive Controller
     */

        /**
         * @return the y-axis value from the drivePad right joystick
         */
        public double getRightDriveY()
        {
            double joy = drivePad.getRawAxis(GamepadConstants.RIGHT_ANALOG_Y);
            if(Math.abs(joy) < 0.05)
                return 0.0;
            else  
                return joy;
        }

        /**
         * @return the x-axis value from the drivePad right Joystick
         */
        public double getRightDriveX()
        {
            double joy = drivePad.getRawAxis(GamepadConstants.RIGHT_ANALOG_X);
            if(Math.abs(joy) < 0.05)
                return 0.0;
            else
                return joy;
        }

        /**
         * @return the y-axis value from the drivePad left joystick
         */
        public double getLeftDriveY()
        {
            double joy = drivePad.getRawAxis(GamepadConstants.LEFT_ANALOG_Y);
            if(Math.abs(joy) < 0.05)
                return 0.0;
            else  
                return joy;
        }
    
        /**
         * @return the x-axis value from the drivePad left Joystick
         */
        public double getLeftDriveX()
        {
            double joy = drivePad.getRawAxis(GamepadConstants.LEFT_ANALOG_X);
            if(Math.abs(joy) < 0.05)
                return 0.0;
            else
                return joy;
        }


        /**
         * @return a true or false depending on the input
         */
        public boolean getDriveRightTrigger()
        {
            return drivePad.getRawButton(GamepadConstants.RIGHT_TRIGGER);
        }

        /**
         * @return a true or false depending on the input
         */
        public boolean getDriveRightBumper()
        {
            return drivePad.getRawButton(GamepadConstants.RIGHT_BUMPER);
        }

        /**
         * @return a true or false depending on the input
         */
        public boolean getDriveLeftTrigger()
        {
            return drivePad.getRawButton(GamepadConstants.LEFT_TRIGGER);
        }

        /**
         * @return a true or false depending on the input
         */
        public boolean getDriveLeftBumper()
        {
            return drivePad.getRawButton(GamepadConstants.LEFT_BUMPER);
        }

        /**
         * @return a true or false depending on the input
         */
        public boolean getDriveDPadX()
        {
            return drivePad.getRawButton(GamepadConstants.DPAD_X);
        }

        /**
         * @return a true or false depending on the input
         */
        public boolean getDriveDPadY()
        {
            return drivePad.getRawButton(GamepadConstants.DPAD_Y);
        }

        /**
         * @return a true or false depending on the input
         */
        public boolean getDriveXButton()
        {
            return drivePad.getRawButton(GamepadConstants.X_BUTTON);
        }

        /**
         * @return a true or false depending on the input
         */
        public boolean getDriveYButton()
        {
            return drivePad.getRawButton(GamepadConstants.Y_BUTTON);
        }

        /**
         * @return a true or false depending on the input
         */
        public boolean getDriveBButton()
        {
            return drivePad.getRawButton(GamepadConstants.B_BUTTON);
        }

        /**
         * @return a true or false depending on the input
         */
        public boolean getDriveAButton()
        {
            return drivePad.getRawButton(GamepadConstants.A_BUTTON);
        }

        /**
         * @return a true or false depending on the input
         */
        public boolean getDriveBackButton()
        {
            return drivePad.getRawButton(GamepadConstants.BACK_BUTTON);
        }

        /**
         * @return a true or false depending on the input
         */
        public boolean getDriveStartButton()
        {
            return drivePad.getRawButton(GamepadConstants.START_BUTTON);
        }

        /**
         * @return a true or false depending on the input
         */
        public boolean getDriveRightAnalogButton()
        {
            return drivePad.getRawButton(GamepadConstants.RIGHT_ANALOG_BUTTON);
        }

        /**
         * @return a true or false depending on the input
         */
        public boolean getDriveLeftAnalogButton()
        {
            return drivePad.getRawButton(GamepadConstants.LEFT_ANALOG_BUTTON);
        }
        /**
         * 
         * Cycle through all buttons to check if any is pressed
         * @return the number of the gamepad buttons
         */
        public int getDriveButtons() {
            for (int i=1; i<=16; i++) {
                if (drivePad.getRawButton(i)) {
                    return i;
                }
            }
            return -1;
        }
}