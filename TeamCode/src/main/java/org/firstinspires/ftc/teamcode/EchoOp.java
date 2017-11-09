package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by ishanarya on 11/6/17.
 */

@TeleOp(name = "EchoOp")
public class EchoOp extends OpMode {

    private DcMotor rightDrive, leftDrive;
    private DcMotor pullMotor;
    private Servo rightHand, leftHand;

    private double rightHandClosedPosition = 0.518;
    private double leftHandClosedPosition = 0.523;
    private int pullMotorMaxTurn = 8000;
    private int pullMotorEnd1 = 7600;
    private int pullMotorEnd2 = 7000;
    private int pullMotorStart2 = 1000;
    private int pullMotorStart1 = 400;
    private int pullMotorMinTurn = 0;

    @Override
    public void init() {

        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        pullMotor = hardwareMap.dcMotor.get("pullMotor");

        rightHand = hardwareMap.servo.get("rightHand");
        leftHand = hardwareMap.servo.get("leftHand");
        rightHand.setPosition(0);
        leftHand.setPosition(1);

    }

    @Override
    public void loop() {

        leftDrive.setPower(gamepad1.left_stick_y);
        rightDrive.setPower(-gamepad1.right_stick_y);

        if(gamepad1.left_bumper) {
            rightHand.setPosition(rightHandClosedPosition);
            leftHand.setPosition(leftHandClosedPosition);
        } else {
            rightHand.setPosition(0);
            leftHand.setPosition(1);
        }

        int pullMotorPosition = pullMotor.getCurrentPosition();
        if(gamepad1.dpad_up) {
            if(pullMotorPosition < pullMotorStart1) {
                pullMotor.setPower(0.25);
            } else if(pullMotorPosition < pullMotorStart2) {
                pullMotor.setPower(0.5);
            } else if(pullMotorPosition < pullMotorEnd2) {
                pullMotor.setPower(1);
            } else if(pullMotorPosition < pullMotorEnd1) {
                pullMotor.setPower(0.5);
            } else if(pullMotorPosition < pullMotorMaxTurn) {
                pullMotor.setPower(0.25);
            } else {
                pullMotor.setPower(0);
            }
        } else if(gamepad1.dpad_down) {
            if(pullMotorPosition > pullMotorEnd1) {
                pullMotor.setPower(-0.25);
            } else if(pullMotorPosition > pullMotorEnd2) {
                pullMotor.setPower(-0.5);
            } else if(pullMotorPosition > pullMotorStart2) {
                pullMotor.setPower(-1);
            } else if(pullMotorPosition > pullMotorStart1) {
                pullMotor.setPower(-0.5);
            } else if(pullMotorPosition > pullMotorMinTurn) {
                pullMotor.setPower(-0.25);
            } else {
                pullMotor.setPower(0);
            }
        } else {
            pullMotor.setPower(0);
        }

        if(gamepad1.y) {
            pullMotor.resetDeviceConfigurationForOpMode();
        }

        telemetry.addData("Pull Motor Position", pullMotor.getCurrentPosition());
    }
}
