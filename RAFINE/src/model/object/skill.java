package model.object;

import java.util.ArrayList;

public class skill implements Comparable<skill>{
	String rule;
	ArrayList<String> student = new ArrayList<String>();
	ArrayList<Integer> right = new ArrayList<Integer>();
	
	public skill (String rule) {
		this.rule = rule;
	}
	
	public void setRule (String rule) {
		this.rule = rule;
	}
	
	public String getRule() {
		return rule;
	}
	
	public void appendRight(int right) {
		this.right.add(right);
	}
	
	public void appendStudent(String student) {
		this.student.add(student);
	}
	
	public int getLength() {
		return right.size();
	}
	
	public ArrayList<String> getStudent()
	{
		return student;
	}
	
	public ArrayList<Integer> getRight()
	{
		return right;
	}

	@Override
	public int compareTo(skill o) {
		return this.rule.compareTo(o.getRule());
	}
	@Override
    public String toString() {
		return rule;
	}

	
}
