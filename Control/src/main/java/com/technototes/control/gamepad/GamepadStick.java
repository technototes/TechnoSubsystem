package com.technototes.control.gamepad;

import com.technototes.control.Periodic;

public class GamepadStick<T extends GamepadAxis, U extends GamepadButton> implements Stick {
    public T xAxis, yAxis;
    public U stickButton;
    public GamepadStick(T x, T y, U b){
        xAxis = x;
        yAxis = y;
        stickButton = b;
    }

    @Override
    public void periodic() {
        xAxis.periodic();
        yAxis.periodic();
        stickButton.periodic();
    }

    @Override
    public double getXAxis() {
        return xAxis.getAsDouble();
    }

    @Override
    public double getYAxis() {
        return yAxis.getAsDouble();
    }
}
