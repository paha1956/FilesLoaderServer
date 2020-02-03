package com.itmo.paul;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class DataBaseHandler {

    private HashMap<String, ArrayList<FileRecord>> usersList = new HashMap<>();

    public void writeRecord(String userName, FileRecord fileRecord) {
        ArrayList<FileRecord> listRecords;
        if (!usersList.containsKey(userName)) {
            listRecords = new ArrayList<>();
        } else {
            listRecords = usersList.get(userName);
        }

        listRecords.add(fileRecord);
        usersList.put(userName, listRecords);
    }

    public ArrayList<FileRecord> readRecords(String userName, GregorianCalendar startDateTime, GregorianCalendar stopDateTime) {
        if (!usersList.containsKey(userName)) {
            return null;
        }
        if (!startDateTime.isSet(Calendar.YEAR) || !stopDateTime.isSet(Calendar.YEAR)) {
            return null;
        }

        return usersList.get(userName);
    }
}
