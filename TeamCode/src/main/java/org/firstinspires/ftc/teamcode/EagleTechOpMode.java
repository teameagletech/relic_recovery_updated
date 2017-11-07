package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by ishanarya on 11/6/17.
 */

@TeleOp(name = "EagleTechOpMode")
public class EagleTechOpMode extends OpMode {

    private DcMotor rightDrive, leftDrive;
    private DcMotor pullMotor;
    private Servo rightHand, leftHand;

    @Override
    public void init() {

        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        pullMotor = hardwareMap.dcMotor.get("pullMotor");

        rightHand = hardwareMap.servo.get("rightHand");
        leftHand = hardwareMap.servo.get("leftHand");
        rightHand.setPosition(0.5);
        leftHand.setPosition(1);

    }

    @Override
    public void loop() {

        leftDrive.setPower(gamepad1.left_stick_y);
        rightDrive.setPower(-gamepad1.right_stick_y);

        if(gamepad1.left_bumper) {
            rightHand.setPosition(0.9);
            leftHand.setPosition(0.1);
        } else {
            rightHand.setPosition(0.1);
            leftHand.setPosition(1);
        }
        if(gamepad1.dpad_up) {
            pullMotor.setPower(0.5);
        } else if(gamepad1.dpad_down) {
            pullMotor.setPower(-0.5);
        } else {
            pullMotor.setPower(0);
        }
    }
}
