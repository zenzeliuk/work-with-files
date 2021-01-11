package ua.mainacademy.service;

import ua.mainacademy.model.ConnectionInfo;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String MAIN_DIR = System.getProperty("user.dir");
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String FILES_DIR = MAIN_DIR + SEPARATOR + "files";

    public static void writeConnectionInfoToFile(ConnectionInfo connectionInfo, String fileName, boolean append) {
        checkFilesDir();
        String filePath = FILES_DIR + SEPARATOR + fileName;
        try (FileWriter fileWriter = new FileWriter(filePath, append)) {
            fileWriter.write(connectionInfo.toString() + "\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkFilesDir() {
        File file = new File(FILES_DIR);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public static void filterLines(String fileName, long timeFrom, long timeTo) {
        List<ConnectionInfo> connectionInfoList = readConnectionInfoFromFile(fileName);
        boolean append = false;
        for (ConnectionInfo connectionInfo : connectionInfoList) {
            if (connectionInfo.getTime() >= timeFrom && connectionInfo.getTime() <= timeTo) {
                writeConnectionInfoToFile(connectionInfo, fileName, append);
                append = true;
            }
        }
    }

    public static void delete(String fileName) {
        String filePath = FILES_DIR + SEPARATOR + fileName;
        File file = new File(filePath);
        file.delete();
    }


    public static List<ConnectionInfo> readConnectionInfoFromFile(String fileName) {
        List<ConnectionInfo> result = new ArrayList<>();
        String filePath = FILES_DIR + SEPARATOR + fileName;
        if (isNotExist(filePath)) {
            throw new RuntimeException("Sorry, can not handle reading");
        }
        try (FileReader fileReader = new FileReader(filePath)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] elements = line.split(" ");
                ConnectionInfo connectionInfo = new ConnectionInfo(
                        Integer.valueOf(elements[0]),
                        Long.valueOf(elements[1]),
                        elements[2]
                );
                result.add(connectionInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static boolean isNotExist(String filePath) {
        File file = new File(filePath);
        return !file.exists();
    }

    public static void writeBytesToFile(byte[] bytes, String fileName) {
        checkFilesDir();
        String filePath = FILES_DIR + SEPARATOR + fileName;
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] readBytesFromFile(String fileName) {
        String filePath = FILES_DIR + SEPARATOR + fileName;
        if (isNotExist(filePath)) {
            throw new RuntimeException("Sorry, can not handle reading");
        }
        File file = new File(filePath);
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

}
