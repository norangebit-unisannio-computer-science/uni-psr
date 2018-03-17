package model;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
	private List<Exam> exams = new ArrayList<Exam>();
	public List<Exam> getExams() { return exams; }
	public void setExam(Exam ex) {
		exams.add(ex);
	}
	public Exam getExam(int i){
		return exams.get(i);
	}

	public void rmExam(int i){
		exams.remove(i);
	}
	public float getMedia(){
		int i = 0;
		float sum = 0;
		for (Exam e: exams){
			sum+=e.getExamVote();
			i++;
		}
		return sum/i;
	}
}
