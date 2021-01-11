package ua.mainacademy;

import ua.mainacademy.model.ConnectionInfo;
import ua.mainacademy.service.FileManager;
import ua.mainacademy.service.ThreadService;

import java.util.Date;

public class ApplicationThread {
    public static void main(String[] args) {

        String file = "WorkThread.txt";

        FileManager.delete(file);

        for (int i = 0; i < 2000; i++) {
            ConnectionInfo connectionInfo = new ConnectionInfo(i, new Date().getTime(), ("123.123.123." + i));
            Thread thread = new ThreadService(file, connectionInfo);
            thread.start();
        }

    }
}
