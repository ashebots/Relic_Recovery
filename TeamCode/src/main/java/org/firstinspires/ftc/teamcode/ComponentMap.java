package org.firstinspires.ftc.teamcode;

import java.util.HashMap;

/**
 * Created by rostifar on 10/3/17.
 */

public class ComponentMap {
    private HashMap<String, Component> map;

    public ComponentMap() {
        map = new HashMap<>();
    }

    public String register(Component c) {
        return "";
    }

    public boolean containsId(String id) {
        return map.containsKey(id);
    }

    public boolean containsComponent(Component c) {
        return map.containsValue(c);
    }
}
