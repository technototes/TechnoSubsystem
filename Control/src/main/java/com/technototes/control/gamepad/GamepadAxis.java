package com.technototes.control.gamepad;

import com.technototes.control.Periodic;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class GamepadAxis extends GamepadButton implements DoubleSupplier, Periodic {
    public static final double defaultTriggerThreshold = 0.5;
    public double triggerThreshold;
    public DoubleSupplier doubleSupplier;
    public GamepadAxis(DoubleSupplier d){
        new GamepadAxis(d, defaultTriggerThreshold);
    }
    public GamepadAxis(DoubleSupplier d, double t){
        super(() -> Math.abs(d.getAsDouble())>t);
        doubleSupplier = d;
        triggerThreshold = t;
    }
    public GamepadAxis(){
        triggerThreshold = defaultTriggerThreshold;
    }

    public GamepadButton setSupplier(DoubleSupplier d) {
        super.setSupplier(() -> Math.abs(d.getAsDouble())>triggerThreshold);
        doubleSupplier = d;
        return this;
    }

    @Override
    public double getAsDouble() {
        return doubleSupplier.getAsDouble();
    }

    @Override
    public void periodic() {
        super.periodic();
    }
}
