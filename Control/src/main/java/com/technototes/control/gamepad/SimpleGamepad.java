package com.technototes.control.gamepad;

import com.qualcomm.robotcore.hardware.Gamepad;

public class SimpleGamepad extends AbstractGamepad<GamepadButton, GamepadAxis> {
    public SimpleGamepad(Gamepad g) {
        super(g);
    }

}
