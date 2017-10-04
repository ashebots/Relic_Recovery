package org.firstinspires.ftc.teamcode;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by rostifar on 10/3/17.
 */

public abstract class Component {
    final protected State DEFAULT_STATE = State.ENABLED;

    //One-to-one map of components which listen for state changes on component.
    protected HashMap<String, Listener> listeners;

    //Every single component is provided a unique id upon registration with the ComponentMap.
    //Differentiates every component in the system.
    protected String id;

    //Whether object is enabled or disabled
    protected State state;

    //If logging is enabled the component can write updates to log file. Should only be enabled
    //for debugging purposes.
    protected boolean loggingEnabled = false;

    protected enum State {
        ENABLED, DISABLED
    }

    //Default template for all listeners of a component. Every listener is notified when a component's state is changed.
    public interface Listener {
        void onInit(Component c);
        void onUpdate(Component c);
        void onEnable(Component c);
        void onDisable(Component c);
    }

    public Component(final String id) {
        this.state  =   DEFAULT_STATE;
        this.id     =   Cache.instance().getComponentMap().register(this);
    }

    //Adds desired component c as a listener to the current component.
    //Local listeners are managed using a one-to-one mapping between a listener's id and a listener object instance.
    public void addListener(Component c, Listener l) {
        listeners.put(c.toString(), l);
    }

    //Functions the same as @addListener(Component c, Listener l) except allows non-component listeners to be added.
    public void addListener(Listener l, String id) {
        listeners.put(id, l);
    }

    //Called every update loop in OpMode
    public void update() {}

    //Enables an disabled component
    public void enable() {
        state = State.ENABLED;
    }

    //Disables an enabled component. Note: Disabled components do not receive updates.
    public void disable() {
        state = State.DISABLED;
    }

    //Permanently disables component for lifetime of application. Warning this method is irreversible.
    public void kill() {}

    //Writes current status of Component to logfile.
    public abstract void writeStatus();

    @Override
    public String toString() {
        return id;
    }
}
