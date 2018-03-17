package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Exam {
	private String courseName;
	private Date examDate;
	private String teacherName;
	private int examVote;
	private Student student;

	public void setCourseName(String cn) { courseName = cn; }
	public void setExameDate(Date d) { examDate = d; }
	public void setTeacherName(String tn) { teacherName = tn; }
	public void setExamVote(int v) {examVote = v; }
	public void setStudent(Student s) { student = s; }

	public String getCourseName() { return courseName; }
	public Date getExamDate() { return examDate; }
	public String getTeacherName() { return teacherName; }
	public int getExamVote() { return examVote; }
	public Student getStudent() { return student; }

	public Exam() {}

	public Exam(String cn, String tn, Date d, int v){
		courseName = cn;
		teacherName = tn;
		examDate = d;
		examVote = v;
	}

	public String toString() {
		SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
		return courseName + " - tenuto da " + teacherName + " in data " + formatDate.format(examDate);
	}
}
