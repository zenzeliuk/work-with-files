package ua.mainacademy;

import ua.mainacademy.model.ConnectionInfo;
import ua.mainacademy.service.FileManager;

import java.util.Date;

public class ApplicationThread {
    public static void main(String[] args) {

        String file = "WorkThread.txt";

        FileManager.delete(file);

        for (int i = 0; i < 200; i++) {
            ConnectionInfo connectionInfo = new ConnectionInfo(i, new Date().getTime(), ("123.123.123." + i));
            FileManager.writeConnectionInfoToFile(connectionInfo, file, true);
        }

    }
}
