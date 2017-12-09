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
    private DcMotor pullMotor, hand;
    private Servo pusher;


    private int pullMotorMaxTurn = 9850;
    private int pullMotorEnd1 = 9000;
    private int pullMotorEnd2 = 8000;
    private int pullMotorStart2 = 2000;
    private int pullMotorStart1 = 1000;
    private int pullMotorMinTurn = 0;

    private int handMaxPosition = 0;
    private int handMinPosition = -150;

    private double speedModifier = 1;

    private boolean backwardsMode = false;

    @Override
    public void init() {

        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        pullMotor = hardwareMap.dcMotor.get("pullMotor");
        hand = hardwareMap.dcMotor.get("hand");
        pusher = hardwareMap.servo.get("pusher");

        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        pullMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hand.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        pusher.setPosition(0.5);

    }

    @Override
    public void loop() {

        // Controller 1

        /**
         * y makes backwardsMode on
         */
        if(gamepad1.y) {
            backwardsMode = true;
        } else if(gamepad1.x) {
            backwardsMode = false;
        }

        if(gamepad1.b) {
            speedModifier = 0.25;
        } else if(gamepad1.a) {
            speedModifier = 1;
        }

        /**
         * Make back forward
         */
        if(backwardsMode) {
            leftDrive.setPower(-gamepad1.right_stick_y * speedModifier);
            rightDrive.setPower(gamepad1.left_stick_y * speedModifier);
        } else {
            leftDrive.setPower(gamepad1.left_stick_y * speedModifier);
            rightDrive.setPower(-gamepad1.right_stick_y * speedModifier);
        }

        /**
         * Flap in front of robot
         */
        if(gamepad1.left_bumper) {
            pusher.setPosition(0);
        } else {
            pusher.setPosition(0.5);
        }

        //########################################################################################################################################################################

        // Controller 2

        int pullMotorPosition = pullMotor.getCurrentPosition();
        if(gamepad2.dpad_up) {
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
        } else if(gamepad2.dpad_down) {
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

        if(gamepad2.right_bumper) {
            closeHand();
        } else {
            openHand();
        }


        if(gamepad2.y) {
            pullMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            pullMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        telemetry.addData("Pull Motor Position", pullMotor.getCurrentPosition());
        telemetry.addData("Hand Position", hand.getCurrentPosition());
        telemetry.addData("Zero Position", pullMotorMinTurn);
    }

    private void openHand() {
        hand.setTargetPosition(handMaxPosition);
        hand.setPower(0.1);
    }

    private void closeHand() {
        hand.setTargetPosition(handMinPosition);
        hand.setPower(-0.1);
    }
}
