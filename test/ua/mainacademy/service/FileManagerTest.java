package ua.mainacademy.service;

import org.junit.jupiter.api.Test;
import ua.mainacademy.model.ConnectionInfo;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    @Test
    void filterLines() {
        long dateFilterFrom = 2L;
        long dateFilterTo = 4L;

        ConnectionInfo connectionInfoFirst = new ConnectionInfo(1, (long) (1), "123");
        FileManager.writeConnectionInfoToFile(connectionInfoFirst, "test.txt", false);
        for (int i = 2; i <= 5; i++) {
            ConnectionInfo connectionInfoElse = new ConnectionInfo(i, (long) (i), "123");
            FileManager.writeConnectionInfoToFile(connectionInfoElse, "test.txt", true);
        }

        List<ConnectionInfo> elements = FileManager.readConnectionInfoFromFile("test.txt");
        assertEquals(5, elements.size());

        FileManager.filterLines("test.txt", dateFilterFrom, dateFilterTo);

        elements = FileManager.readConnectionInfoFromFile("test.txt");
        assertEquals(3, elements.size());

    }
}