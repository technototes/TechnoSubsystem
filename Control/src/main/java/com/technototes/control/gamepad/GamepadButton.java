package com.technototes.control.gamepad;

import com.technototes.control.Periodic;

import java.util.function.BooleanSupplier;

public class GamepadButton implements BooleanSupplier, Periodic {
    private BooleanSupplier booleanSupplier;
    public boolean pressed = false;
    public boolean toggle = false;
    public boolean recentAction = false;
    public boolean pastState = false;
    public GamepadButton(BooleanSupplier b){
        booleanSupplier = b;
    }
    public GamepadButton(){
        booleanSupplier = () -> false;
    }
    public GamepadButton setSupplier(BooleanSupplier b){
        booleanSupplier = b;
        return this;
    }
    @Override
    public void periodic(){
        periodic(booleanSupplier.getAsBoolean());
    }

    public void periodic(boolean currentState){
        recentAction = pastState != currentState;
        pastState = currentState;
        pressed = currentState;
        toggle = (recentAction && pastState) ? !toggle : toggle;
    }

    public boolean isJustActivated(){
        return pressed && recentAction;
    }

    public boolean isJustDeactivated(){
        return !pressed && recentAction;
    }

    public boolean isActivated(){
        return pressed;
    }

    public boolean isDeactivated(){
        return !pressed;
    }

    public boolean isJustToggled(){
        return toggle && recentAction && pressed;
    }

    public boolean isJustInverseToggled(){
        return !toggle && recentAction && pressed;
    }

    public boolean isToggled(){
        return toggle;
    }

    public boolean isInverseToggled(){
        return !toggle;
    }

    @Override
    public boolean getAsBoolean() {
        return isActivated();
    }
}
