package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by ishanarya on 11/7/17.
 */

@TeleOp(name = "MotorCalibration")
public class MotorCalibration extends OpMode {

    private DcMotor pullMotor;

    //Max motor turn = 7776

    @Override
    public void init() {
        pullMotor = hardwareMap.dcMotor.get("pullMotor");
    }

    @Override
    public void loop() {

        if(gamepad1.dpad_up) {
            pullMotor.setPower(0.5);
        } else if(gamepad1.dpad_down) {
            pullMotor.setPower(-0.5);
        } else {
            pullMotor.setPower(0);
        }

        telemetry.addData("Pull Motor Position", pullMotor.getCurrentPosition());
    }
}
