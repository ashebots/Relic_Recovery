package org.firstinspires.ftc.teamcode;

/**
 * Created by rostifar on 9/21/17.
 */

import java.sql.Time;

/**
 *
 * PID Controller (https://en.wikipedia.org/wiki/PID_controller#cite_note-3)
 *
 * Abstract
 * --------
 * + input: error. Sigma (P, I, D) - > u(t)
 *
 * Control Terms
 * --------------
 * + Continuously calculates error e(t), where e(t) = setpoint - process variable
 * + Applies correction using a proportional, integral, and derivative.
 * + Controller attempts to minimize error over time by adjusting control variable u(t).
 *
 * P
 * ---
 * + In layman terms, the proportion accounts for the current error at time t.
 *
 * I
 * ---
 * + Using past error terms to adjust the control variable. As the error
 * declines, the proportional effect decreases while the integral term grows.
 *
 * D
 * ---
 * + Used to estimate future lapses in error rate using rate-of-change to predict trends. Larger
 * the r.o.c, the greater the dampening effect.
 *
 * Tuning
 * ------
 * + K tuning constants must be set for each individual application.
 **/

public class PIDController {
    //tuning constants.
    private double kp;
    private double ki;
    private double kd;

    private double setpoint;
    private double lastUpdate;
    private double lastError;
    private double lastInput;
    private double errorSum;
    private double output;
    private double iterm;

    private double timestep = 1000;



    public PIDController(double setpoint, double timestep, double kp, double ki, double kd) {
        this.timestep = timestep;
        this.setpoint = setpoint;
        setKp(kp);
        setKi(ki);
        setKd(kd);
    }

    public PIDController(double setpoint) {
        this.setpoint = setpoint;
    }

    public void update(double input) {
        double now = System.currentTimeMillis();

        if (now - lastUpdate < timestep) return;
        double error    =   setpoint - input;
        iterm           +=  (ki * error);
        double dInput   =   input - lastError;
        output          =   kp * error + iterm - kd * dInput;

        lastInput       =   input;
        lastUpdate      =   now;
    }


    public void setTunings(double Kp, double Ki, double Kd) {
        double timeInSec = timestep / 1000;
        kp = Kp;
        ki = Ki * timeInSec;
        kd = Kd / timeInSec;
    }

    public void setTimestep(double time) { //in ms
        if (time <= 0) return;
        double ratio = time / timestep;

        ki          *=  ratio;
        kd          /=  ratio;
        timestep    =   time;
    }

    public void setKp(double Kp) {
        kp = Kp;
    }

    public void setKi(double Ki) {
        ki = Ki;
    }

    public void setKd(double Kd) {
        kd = Kd;
    }
}
