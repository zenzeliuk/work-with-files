package ua.mainacademy.service;

import ua.mainacademy.model.ConnectionInfo;

public class ThreadService extends Thread{

    private String fileName;
    private ConnectionInfo connectionInfo;

    public ThreadService(String fileName, ConnectionInfo connectionInfo) {
        this.fileName = fileName;
        this.connectionInfo = connectionInfo;

    }

    @Override
    public void run() {
        FileManager.writeConnectionInfoToFile(connectionInfo, fileName, true);
    }
}
