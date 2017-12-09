package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.lasarobotics.vision.android.Cameras;
import org.lasarobotics.vision.opmode.VisionOpMode;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

@Autonomous(name = "EchoAutonomous2")
public class EchoAutonomous2 extends LinearOpMode {


    private DcMotor rightDrive, leftDrive;
    private DcMotor pullMotor, hand;
    private Servo pusher;



    @Override
    public void runOpMode() throws InterruptedException {
        initRobot();

        telemetry.addData("Message", "Don't lose too hard.");

        waitForStart();


        leftDrive.setPower(1);
        rightDrive.setPower(-1);
        Thread.sleep(2000);
        stopRobot();

        stop();

    }


    public void initRobot() {
        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        pullMotor = hardwareMap.dcMotor.get("pullMotor");
        hand = hardwareMap.dcMotor.get("hand");
        pusher = hardwareMap.servo.get("pusher");

        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        pullMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hand.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pusher.setPosition(0.5);

    }

    private void stopRobot() {
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }

}
