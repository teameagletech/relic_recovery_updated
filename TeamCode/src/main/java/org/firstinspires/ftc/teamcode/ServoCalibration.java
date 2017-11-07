package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by ishanarya on 11/7/17.
 */

@TeleOp(name = "ServoCalibration")
public class ServoCalibration extends OpMode {


    private Servo rightHand, leftHand;

    @Override
    public void init() {
        rightHand = hardwareMap.servo.get("rightHand");
        leftHand = hardwareMap.servo.get("leftHand");
        rightHand.setPosition(0);
        leftHand.setPosition(1);
    }

    @Override
    public void loop() {

        leftHand.setPosition(Math.abs(gamepad1.left_stick_y));
        rightHand.setPosition(Math.abs(gamepad1.right_stick_y));
        telemetry.addData("Right Hand", rightHand.getPosition());
        telemetry.addData("Left Hand", leftHand.getPosition());

    }
}
