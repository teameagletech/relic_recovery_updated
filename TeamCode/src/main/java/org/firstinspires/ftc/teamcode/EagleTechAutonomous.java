package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

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

@Autonomous(name = "EagleTechAutonomous")
public class EagleTechAutonomous extends VisionOpMode {

    VuforiaLocalizer vuforia;
    VuforiaTrackables relicTrackables;
    VuforiaTrackable relicTemplate;

    DcMotor wm1, wm2;

    RelicRecoveryVuMark patternDirection = RelicRecoveryVuMark.UNKNOWN;

    int ballDirection = 0;

    private final int WIDTH = 600;

    @Override
    public void init() {
        super.init();
        setCamera(Cameras.PRIMARY);
        setFrameSize(new Size(WIDTH, 400));

        // Vuforia setup code
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AViOO0H/////AAAAGdOe07SFvkp9o8ayO1bk4XuDzjkGt9iAYhWO7gNOXYyRgcKIqt/Emv1z47NNWKJrRJahGxnoOUYzDaTvKspZCbeAuvna+XJbdvJoECZ1DDEdo/iwXL55N39Y7Jv6veJKnr4FycQROZGBU+r0Ac/EfMomkWXulsarNQuTMLiHIgikYqf+sfjVx1CO648O3WOtPEfTrfPmJB/rvo2NqG8kLmZ218EhwXgWsEGoqb3e24WJimftXKRXuH/4VzIiQLj8p+K84LurwqJjGnq8q3RRzaUCcgrLnQ1RoqA0FT7+OLIRbMkxJCfHnvqsgeKTzJCcjJX5oJPR2jYubleXblt+VKpQ43t6x5/yLYSbRFy9bYoH";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");

        wm1 = hardwareMap.dcMotor.get("wm1");
        wm2 = hardwareMap.dcMotor.get("wm2");


    }

    @Override
    public void start() {
        relicTrackables.activate();
        resetStartTime();
    }

    @Override
    public void loop() {
        super.loop();
        patternDirection = RelicRecoveryVuMark.from(relicTemplate);


        telemetry.update();

    }

    /*
    @Override
    public Mat frame(Mat rgba, Mat gray) {
        rgba = super.frame(rgba, gray);
        Mat hsv = Mat.zeros(rgba.size(), rgba.type());
        Imgproc.cvtColor(rgba, hsv, Imgproc.COLOR_BGR2HSV);

        Mat lower_red_mask = Mat.zeros(hsv.size(), hsv.type());
        Mat upper_red_mask = Mat.zeros(hsv.size(), hsv.type());
        Core.inRange(hsv, new Scalar(0, 50, 50), new Scalar(10, 255, 255), lower_red_mask);
        Core.inRange(hsv, new Scalar(160, 50, 50), new Scalar(180, 255, 255), upper_red_mask);

        Mat mask = Mat.zeros(lower_red_mask.size(), lower_red_mask.type());
        Core.add(lower_red_mask, upper_red_mask, mask);

        Mat thresh = Mat.zeros(mask.size(), mask.type());
        Imgproc.threshold(mask, thresh, 40, 255, 0);

        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = Mat.zeros(thresh.size(), thresh.type());
        Imgproc.findContours(thresh, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_NONE);
        if (contours.size() != 0) {
            double maxVal = 0;
            int maxValIdx = 0;
            for (int contourIdx = 0; contourIdx < contours.size(); contourIdx++) {
                double contourArea = Imgproc.contourArea(contours.get(contourIdx));
                if (maxVal < contourArea) {
                    maxVal = contourArea;
                    maxValIdx = contourIdx;
                }
            }
            Rect rect = Imgproc.boundingRect(contours.get(maxValIdx));
            if ((rect.x + rect.width)/2 < WIDTH / 2) {
                ballDirection = 1;
            } else {
                ballDirection = 2;
            }
        }

        return rgba;
    } */

    @Override
    public void stop() {
        super.stop();
        relicTrackables.deactivate();
    }

}
