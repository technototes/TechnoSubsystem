package com.technototes.control.gamepad;

public class GamepadDpad<T extends GamepadButton> implements Stick {
    public T up, down, left, right;
    public GamepadDpad(T u, T d, T l, T r) {
        up = u;
        down = d;
        left = l;
        right = r;
    }


    @Override
    public double getXAxis() {
        return (right.getAsBoolean() ? (left.getAsBoolean() ? 0 : 1) : (left.getAsBoolean() ? -1 : 0));
    }

    @Override
    public double getYAxis() {
        return (up.getAsBoolean() ? (down.getAsBoolean() ? 0 : 1) : (down.getAsBoolean() ? -1 : 0));
    }

    @Override
    public void periodic() {
        up.periodic();
        down.periodic();
        left.periodic();
        right.periodic();
    }
}
