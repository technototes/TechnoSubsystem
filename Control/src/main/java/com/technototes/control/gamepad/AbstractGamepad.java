package com.technototes.control.gamepad;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.technototes.control.Periodic;

//a class to base gamepads off of
public abstract class AbstractGamepad<T extends GamepadButton, U extends GamepadAxis> implements Periodic {
    //normal gamepad
    private Gamepad gamepad;
    //buttons
    public T a, b, x, y, start, back, leftBumper, rightBumper,
            dpadUp, dpadDown, dpadLeft, dpadRight, leftStickButton, rightStickButton;
    //axis
    public U leftTrigger, rightTrigger, leftStickX, leftStickY, rightStickX, rightStickY;
    //sticks
    public GamepadStick<U, T> leftStick, rightStick;
    //dpad
    public GamepadDpad<T> dpad;
    //periodics to run
    private Periodic[] periodics;

    public AbstractGamepad(Gamepad g){
        gamepad = g;
        try {
            setComponents(g);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        leftStick = new GamepadStick<U, T>(leftStickX, leftStickY, leftStickButton);
        rightStick = new GamepadStick<U, T>(rightStickX, rightStickY, rightStickButton);
        dpad = new GamepadDpad<T>(dpadUp, dpadDown, dpadLeft, dpadRight);
        periodics = new Periodic[]{a, b, x, y, start, back, leftBumper, rightBumper,
                leftTrigger, rightTrigger, leftStick, rightStick, dpad};
    }
    //to actually instantiate the objects
    public void setComponents(Gamepad g) throws InstantiationException, IllegalAccessException {
        //classes to instantiate
        Class<T> buttonClass = (Class<T>) new GamepadButton().getClass();
        Class<U> axisClass = (Class<U>) new GamepadAxis().getClass();

        //buttons
        a = (T) buttonClass.newInstance().setSupplier(() -> g.a);
        b = (T) buttonClass.newInstance().setSupplier(() -> g.b);
        x = (T) buttonClass.newInstance().setSupplier(() -> g.x);
        y = (T) buttonClass.newInstance().setSupplier(() -> g.y);
        start = (T) buttonClass.newInstance().setSupplier(() -> g.start);
        back = (T) buttonClass.newInstance().setSupplier(() -> g.back);

        //bumpers
        leftBumper = (T) buttonClass.newInstance().setSupplier(() -> g.left_bumper);
        rightBumper = (T) buttonClass.newInstance().setSupplier(() -> g.right_bumper);

        //dpad
        dpadUp = (T) buttonClass.newInstance().setSupplier(() -> g.dpad_up);
        dpadDown = (T) buttonClass.newInstance().setSupplier(() -> g.dpad_down);
        dpadLeft = (T) buttonClass.newInstance().setSupplier(() -> g.dpad_left);
        dpadRight = (T) buttonClass.newInstance().setSupplier(() -> g.dpad_right);

        //left stick
        leftStickX = (U) axisClass.newInstance().setSupplier(() -> g.left_stick_x);
        leftStickY = (U) axisClass.newInstance().setSupplier(() -> g.left_stick_y);
        leftStickButton = (T) buttonClass.newInstance().setSupplier(() -> g.left_stick_button);

        //right stick
        rightStickX = (U) axisClass.newInstance().setSupplier(() -> g.right_stick_x);
        rightStickY = (U) axisClass.newInstance().setSupplier(() -> g.right_stick_y);
        rightStickButton = (T) buttonClass.newInstance().setSupplier(() -> g.right_stick_button);

        //triggers
        leftTrigger = (U) axisClass.newInstance().setSupplier(() -> g.left_trigger);
        rightTrigger = (U) axisClass.newInstance().setSupplier(() -> g.right_trigger);

    }

    //enums
    public enum Button{
        A, B, X, Y, START, BACK, LEFT_BUMPER, RIGHT_BUMPER, LEFT_STICK_BUTTON, RIGHT_STICK_BUTTON;
    }

    public enum Axis{
        LEFT_STICK_X, LEFT_STICK_Y, RIGHT_STICK_X, RIGHT_STICK_Y, LEFT_TRIGGER, RIGHT_TRIGGER;
    }


    public T getButton(Button bu){
        switch (bu){
            case A:
                return a;
            case B:
                return b;
            case X:
                return x;
            case Y:
                return y;
            case BACK:
                return back;
            case START:
                return start;
            case LEFT_BUMPER:
                return leftBumper;
            case RIGHT_BUMPER:
                return rightBumper;
            case LEFT_STICK_BUTTON:
                return leftStickButton;
            case RIGHT_STICK_BUTTON:
                return rightStickButton;
            default:
                return null;
        }
    }

    public U getAxis(Axis as){
        switch (as){
            case LEFT_STICK_X:
                return leftStickX;
            case LEFT_STICK_Y:
                return leftStickY;
            case RIGHT_STICK_X:
                return rightStickX;
            case RIGHT_STICK_Y:
                return rightStickY;
            case LEFT_TRIGGER:
                return leftTrigger;
            case RIGHT_TRIGGER:
                return rightTrigger;
            default:
                return null;
        }
    }


    public boolean getButtonAsBoolean(Button bu){
        return getButton(bu).getAsBoolean();
    }
    public double getAxisAsDouble(Axis as){
        return getAxis(as).getAsDouble();
    }
    public boolean getAxisAsBoolean(Axis as){
        return getAxis(as).getAsBoolean();
    }

    public GamepadStick<U, T> getLeftStick(){
        return leftStick;
    }

    public GamepadStick<U, T> getRightStick(){
        return rightStick;
    }

    public GamepadDpad<T> getDpad(){
        return dpad;
    }

    @Override
    public void periodic() {
        for(Periodic p : periodics){
            p.periodic();
        }
    }

    public Gamepad getGamepad(){
        return gamepad;
    }
}
