package org.firstinspires.ftc.teamcode;


import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rostifar on 9/30/17.
 */

public class FileIO {
    private static String LOG_FILE_TEMPLATE = "FTC_LOG_";
    private static String LOG_FILE_EXT      = ".txt";

    interface FileCallback {
        void onFileCreation(boolean success);
        void onWriteCompletion(boolean success);
    }

    private static class ConstructFileRunnable implements Runnable {
        private FileCallback    callback;
        private Context         context;
        private String          filename;


        @Override
        public void run() {

        }
    }

    private static class WriteFileRunnable implements Runnable {
        private FileCallback    callback;
        private Context         context;
        private String          filename;
        private String          data;

        public WriteFileRunnable(Context c, String filename, String data, FileCallback callback) {
            this.callback   =   callback;
            this.context    =   c;
            this.filename   =   filename;
            this.data       =   data;
        }

        @Override
        public void run() {
            try {
                OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
                osw.write(data);
                osw.close();
                callback.onWriteCompletion(true);
            } catch (IOException e) {
                callback.onWriteCompletion(false);
                Log.e("Exception", "Write to file failed: " + e.toString());
            }
        }
    }


    public enum FileType {
        Log
    }

    public static FileContext ConstructFile(Context context, FileType type) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        return ConstructFile(context, type, timeStamp);
    }

    public static FileContext ConstructFile(Context context, FileType type, String filename) throws IOException {
        filename = BuildFileName(filename, type);
        boolean success = new File(context.getFilesDir(), filename).createNewFile();
        return new FileContext(filename, success);
    }

    public static void Write(Context c, String filename, String data, FileCallback callback) {
        WriteFileRunnable thread = new WriteFileRunnable(c, filename, data, callback);
        thread.run();
    }

    private static String BuildFileName(String filler, FileType type) {
        String s = "";
        switch (type) {
            case Log:
                return LOG_FILE_TEMPLATE + filler + LOG_FILE_EXT;
        }
        return s;
    }
}