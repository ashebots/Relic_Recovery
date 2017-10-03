package org.firstinspires.ftc.teamcode;

/**
 * Created by rostifar on 9/21/17.
 */


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
    private static final Mode      DEFAULT_MODE        =   Mode.AUTO;
    private static final Direction DEFAULT_DIRECTION   =   Direction.DIRECT;
    private static final double    DEFAULT_TIMESTEP    =   1000;

    //tuning constants.
    private double P, I, D;

    //clamp values for control variables to prevent integrator and differentiation spike
    private double min, max;
    private double lastInput, lastUpdate;
    private double setpoint, output;
    private double iterm;
    private double timestep;

    private boolean inAuto = true;

    //Automatic updates or manual updates
    private Mode mode;

    //Which direction control variable needs to be updated
    private Direction direction;

    public enum Mode {
        MANUAL, AUTO
    }

    public enum Direction {
        DIRECT, REVERSE
    }

    public PIDController(double setpoint, double timestep, double p, double i, double d, double minOutput,
                         double maxOutput, Mode mode, Direction direction) {
        this.timestep   =   timestep;
        this.setpoint   =   setpoint;
        this.min        =   minOutput;
        this.max        =   maxOutput;
        this.mode       =   mode;
        this.P          =   p;
        this.I          =   i;
        this.D          =   d;
        this.direction  =   direction;

        setTunings(p, i, d);
    }

    public PIDController(double setpoint, double timestep, double p, double i, double d, double minOutput,
                         double maxOutput, Mode mode) {
        this(setpoint, timestep, p, i, d, minOutput, maxOutput, mode, DEFAULT_DIRECTION);
    }

    public PIDController(double setpoint, double timestep, double p, double i, double d, double minOutput,
                         double maxOutput) {
        this(setpoint, timestep, p, i, d, minOutput, maxOutput, DEFAULT_MODE, DEFAULT_DIRECTION);
    }

    public PIDController(double setpoint, double p, double i, double d, double minOutput, double maxOutput) {
        this(setpoint, DEFAULT_TIMESTEP, p, i, d, minOutput, maxOutput, DEFAULT_MODE, DEFAULT_DIRECTION);
    }


    public void update(double input) {
        if (!inAuto) return;
        double now = System.currentTimeMillis();
        double dt = now - lastUpdate;

        if (dt < timestep) return;

        double error = setpoint - input;
        iterm +=  (I * error);
        iterm = constrain(max, min, iterm);
        double dInput = input - lastInput;

        output = P * error + iterm - D * dInput;
        output = constrain(max, min, dInput);

        lastInput  = input;
        lastUpdate = now;
    }

    public void setTunings(double p, double i, double d) {
        if (p < 0 || i < 0 || d < 0) return;
        double timeInSec = timestep / 1000;
        P = p;
        I = i * timeInSec;
        D = d / timeInSec;

        if (direction == Direction.REVERSE) {
            P = -P; I = -I; D = -D;
        }
    }

    public void setTimestep(double time) { //in ms
        if (time <= 0) return;
        double ratio = time / timestep;

        I          *=  ratio;
        D          /=  ratio;
        timestep    =   time;
    }

    public void clamp(double min, double max) {
        if (min > max) return;
        this.min = min;
        this.max = max;

        output = constrain(max, min, output);
        iterm  = constrain(max, min, iterm);
    }

    public void setP(double p) {
        P = p;
    }

    public void setI(double i) {
        double timestepSec = timestep / 1000;
        I = i * timestepSec;
    }

    public void setD(double d) {
        double timestepSec = timestep / 1000;
        D = d / timestepSec;
    }

    public void setPID(double p, double i, double d) {
        setP(p); setI(i); setD(d);
    }

    public void setMode(Mode newMode, double input, double output) {
        boolean newAuto = (newMode == Mode.AUTO);
        if (newAuto && !inAuto) initialize(input, output);
        inAuto = newAuto;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    private void initialize(double input, double output) {
        lastInput = input;
        iterm = output;
        constrain(max, min, iterm);
    }

    private static double constrain(double max, double min, double value) {
        if (value > max)        return max;
        else if (value < min)   return min;
        else                    return value;
    }
}