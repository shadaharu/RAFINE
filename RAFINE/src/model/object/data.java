package model.object;

public class data {
	double number;
	String lesson;
	String student;
	String skill;
	String cell;
	double right;
	String eol;
	

	public data(double number, String lesson, String student, String skill, String cell, double right, String eol) {
		this.number = number;
		this.lesson = lesson;
		this.student = student;
		this.skill = skill;
		this.cell = cell;
		this.right = right;
		this.eol = eol;
	}
	
	public data()
	{
		
	}
	
	public void setNumber(double number) {
		this.number = number;
	}
	
	public double getNumber() {
		return number;
	}
	
	public void setLesson(String lesson) {
		this.lesson = lesson;
	}
	
	public String getLesson() {
		return lesson;
	}
	
	public void setStudent(String student) {
		this.student = student;
	}
	
	public String getStudent() {
		return student;
	}
	
	public void setSkill(String skill) {
		this.skill = skill;
	}
	
	public String getSkill() {
		return skill;
	}
	
	public void setSCell(String cell) {
		this.cell = cell;
	}
	
	public String getCell() {
		return cell;
	}
	
	public void setRight(double right) {
		this.right = right;
	}
	
	public double getRight() {
		return right;
	}
	
	public void setEol(String eol) {
		this.eol = eol;
	}
	
	public String getEol() {
		return eol;
	}
	
	public String toString(){
		return number + " " + lesson + " " + student + " " + skill + " " + cell + " " + right + " " + eol;
	}

}
