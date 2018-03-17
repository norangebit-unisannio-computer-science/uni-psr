package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Appello {
    private String examName;
    private Date examDate;
    private String sede;

    public Appello(String examName, Date examDate, String sede) {
        this.examName = examName;
        this.examDate = examDate;
        this.sede = sede;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public static Appello read(Scanner sc){
        String name, sede, data;
        Date date;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        if(!sc.hasNextLine()) return null;
        name = sc.nextLine();
        if(!sc.hasNextLine()) return null;
        data = sc.nextLine();
        if(!sc.hasNextLine()) return null;
        sede = sc.nextLine();
        try {
            date = sdf.parse(data);
        } catch (ParseException e) {
            return null;
        }

        return new Appello(name, date, sede);
    }

}
