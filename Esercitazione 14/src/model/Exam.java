package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Exam {
	private String courseName;
	private Date examDate;
	private String teacherName;
	private Student student;
	
	public void setStudent(Student s) { student = s; }
	public Student getStudent() { return student; }
	public void setCourseName(String cn) { courseName = cn; }
	public void setTeacherName(String tn) { teacherName = tn; }
	public void setExameDate(Date d) { examDate = d; }
	public String getCourseName() { return courseName; }
	public String getTeacherName() { return teacherName; }
	public Date getExamDate() { return examDate; }
	public Exam() {}
	public Exam(String cn, String tn, Date d){
		courseName = cn;
		teacherName = tn;
		examDate = d;
	}
	public String toString() {
		SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
		return courseName + " - tenuto da " + teacherName + " in data " + formatDate.format(examDate);
	}
}
