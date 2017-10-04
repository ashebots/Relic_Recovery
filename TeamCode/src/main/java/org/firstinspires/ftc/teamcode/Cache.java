package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import java.util.List;

/**
 * Created by rostifar on 10/3/17.
 */

public class Cache {
    private static Cache instance;
    private ComponentMap componentMap;
    private List<String> idPool;
    private OpMode enabledOpMode;

    public static Cache instance() {
        if (instance == null) instance = new Cache();
        return instance;
    }

    public Cache() {
        componentMap = new ComponentMap();
    }

    public ComponentMap getComponentMap() {
        return componentMap;
    }
}
