package ua.mainacademy;

import ua.mainacademy.model.ConnectionInfo;
import ua.mainacademy.service.FileManager;

import java.util.Date;
import java.util.logging.Logger;

public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {

        long dateFilter = new Date().getTime() - (long) (1000 * 60 * 60 * 24 * 3); // Three days (ms * s * m * h * d)


        for (int i = 1; i <= 5; i++){
            ConnectionInfo connectionInfo = new ConnectionInfo(i, new Date().getTime(), "123.123.123.123");
            FileManager.writeConnectionInfoToFile(connectionInfo, "connections.txt", true);
        }

        LOGGER.info("All connections = " + FileManager.readConnectionInfoFromFile("connections.txt"));

        FileManager.filterLines("connections.txt", dateFilter, new Date().getTime());

        LOGGER.info("Connections after filter = " + FileManager.readConnectionInfoFromFile("connections.txt"));


        byte[] myBytes = FileManager.readBytesFromFile("picture.bmp");
        FileManager.writeBytesToFile(myBytes, "myBytes");



    }
}
