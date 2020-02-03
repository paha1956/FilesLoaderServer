package com.itmo.paul;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MainController {

    DataBaseHandler dbHandler = new DataBaseHandler();

    @GetMapping("/")
    public String getIndex(Model model) {
        return "index";
    }

    @GetMapping("/filesdataprint")
    public String getDataPrintPage(@RequestParam(name = "userName", required = false, defaultValue = "admin") String userName,
                                   @RequestParam(name = "startDate", required = false, defaultValue = "") String startDate,
                                   @RequestParam(name = "startTime", required = false, defaultValue = "") String startTime,
                                   @RequestParam(name = "stopDate", required = false, defaultValue = "") String stopDate,
                                   @RequestParam(name = "stopTime", required = false, defaultValue = "") String stopTime,
                                   Model model) {

        Locale locale = new Locale("ru", "RU");

        List<FileRecord> filesList;
        String requestDateTimeRange;
        boolean errorDataTimeSet = false;
        GregorianCalendar startDateTime = new GregorianCalendar();
        GregorianCalendar stopDateTime = new GregorianCalendar();
        if (startDate.length() == 0 || stopDate.length() == 0) {
            startDateTime.add(Calendar.DATE, -1);
        } else {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                startDateTime.setTime(format.parse(startDate + " " + startTime));
                stopDateTime.setTime(format.parse(stopDate + " " + stopTime));
                if (startDateTime.after(stopDateTime))
                {
                    startDateTime = new GregorianCalendar();
                    startDateTime.add(Calendar.DATE, -1);
                    stopDateTime = new GregorianCalendar();
                    errorDataTimeSet = true;
                }
            } catch (Exception e) {
                startDateTime.add(Calendar.DATE, -1);
                System.out.println("DateTime format error in getDataPrintPage");
            }
        }

        startDate = startDateTime.get(Calendar.DATE) + " " + startDateTime.getDisplayName(Calendar.MONTH, Calendar.LONG, locale) + " " + startDateTime.get(Calendar.YEAR) + "г.";
        startTime = startDateTime.get(Calendar.HOUR_OF_DAY) + "ч " + startDateTime.get(Calendar.MINUTE) + "мин";
        stopDate = stopDateTime.get(Calendar.DATE) + " " + stopDateTime.getDisplayName(Calendar.MONTH, Calendar.LONG, locale) + " " + startDateTime.get(Calendar.YEAR) + "г.";
        stopTime = stopDateTime.get(Calendar.HOUR_OF_DAY) + "ч " + stopDateTime.get(Calendar.MINUTE) + "мин";

        filesList = dbHandler.readRecords(userName, startDateTime, stopDateTime);
        requestDateTimeRange = "Период выборки: с " + startDate + " (" + startTime + ") по " + stopDate + " (" + stopTime + ").";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String defaultStartDate = dateFormat.format(startDateTime.getTime());
        String defaultStartTime = timeFormat.format(startDateTime.getTime());
        String defaultStopDate = dateFormat.format(stopDateTime.getTime());
        String defaultStopTime = timeFormat.format(stopDateTime.getTime());

        model.addAttribute("userName", userName);
        model.addAttribute("filesList", filesList);
        model.addAttribute("listRange", requestDateTimeRange);
        model.addAttribute("defaultStartDate", defaultStartDate);
        model.addAttribute("defaultStartTime", defaultStartTime);
        model.addAttribute("defaultStopDate", defaultStopDate);
        model.addAttribute("defaultStopTime", defaultStopTime);
        model.addAttribute("errorDataTimeSet", errorDataTimeSet);

        return "filesdataprint";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postData(@Valid @RequestBody PostRecord record) {
        dbHandler.writeRecord(record.getUserName(), new FileRecord(record.getFileDate(), record.getFileTime(), record.getFileURL(), record.getFileSize(), record.getLoadTime()));
    }
}


