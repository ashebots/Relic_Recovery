package org.firstinspires.ftc.teamcode;

/**
 * Created by rostifar on 9/30/17.
 */

public class FileContext {
    private String  name;
    private boolean success;

    public FileContext(String name, boolean success) {
        this.name    =  name;
        this.success =  success;
    }

    public String getFileName() {
        return name;
    }

    public boolean initSuccessful() {
        return success;
    }
}
