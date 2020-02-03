package com.itmo.paul;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class FileRecord {

    private GregorianCalendar fileDateTime;
    private String fileDate;
    private String fileTime;
    private String fileURL;
    private long size;
    private String stringSize;
    private long loadTime;
    private String stringLoadTime;
    private long loadSpeed;
    private String stringLoadSpeed;

    public FileRecord(String fileDate, String fileTime, String fileURL, long size, long loadTime) {

        GregorianCalendar fileDateTime = (GregorianCalendar) Calendar.getInstance();;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            fileDateTime.setTime(format.parse(fileDate + " " + fileTime));

            this.fileDateTime = fileDateTime;
            this.fileDate = fileDate;
            this.fileTime = fileTime;
        } catch (Exception e) {
            this.fileDateTime = new GregorianCalendar();
            this.fileDate = this.fileDateTime.get(Calendar.DATE) + "." + this.fileDateTime.get(Calendar.MONTH) + "." + this.fileDateTime.get(Calendar.YEAR);
            this.fileTime = this.fileDateTime.get(Calendar.HOUR) + ":" + this.fileDateTime.get(Calendar.MINUTE) + ":" + this.fileDateTime.get(Calendar.SECOND);
            System.out.println("DateTime format error in FileRecord");
        }

        this.fileURL = fileURL;
        this.size = size;
        this.stringSize = sizeConverter(size) + "Байт";
        this.loadTime = loadTime / 1000;
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        this.stringLoadTime = timeFormat.format(new Date(loadTime));
        if (loadTime > 0) {
            this.loadSpeed = size * 8 * 1000 / loadTime;
        } else {
            this.loadSpeed = 0;
        }
        this.stringLoadSpeed = sizeConverter(this.loadSpeed) + "бит/c";
    }

    public GregorianCalendar getFileDateTime() { return fileDateTime; }
    public String getFileDate() {
        return fileDate;
    }
    public String getFileTime() {
        return fileTime;
    }
    public String getFileURL() { return fileURL; }
    public long getSize() { return size; }
    public String getStringSize() { return stringSize; }
    public long getLoadTime() { return loadTime; }
    public String getStringLoadTime() { return stringLoadTime; }
    public long getLoadSpeed() {
        return loadSpeed;
    }
    public String getStringLoadSpeed() { return stringLoadSpeed; }

    /**
     * Метод для приведения объёма файла к сокращённому формату (байты - в килобайты, мегабайты и гигабайты)
     *
     * @param size - размер файла, байт
     * @return строка, содержащая приведённый к сокращённому формату размер файла
     */
    private String sizeConverter(long size) {
        if (size < 0) return "-1 ";
        String[] lb = {" ", " к", " М", " Г"};
        int index = 0;
        double dSize = (double) size;
        while (dSize >= 1024.0 && index < lb.length - 1) {
            dSize = dSize / 1024.0;
            index++;
        }
        String formattedDouble = new DecimalFormat("#0.00").format(dSize);
        return formattedDouble + lb[index];
    }
}
