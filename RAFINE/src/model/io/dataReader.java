package model.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;

import model.object.Time;
import model.object.data;

public class dataReader {
	
	public dataReader()
	{
		//Constructor
	}
	
	public static ArrayList<data> readIn (String filename) {
		ArrayList<data> list = new ArrayList<data>();
		StreamTokenizer st = null;
		try {
			st = new StreamTokenizer(new FileReader(filename));
			st.wordChars(95, 95);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
		
		//Read pass the title
		for (int i = 0; i < 7; i++)
		{
			try {
				st.nextToken();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		int tokenType = 0;
		do {
			data read = new data();
			// number
			try {
				tokenType = st.nextToken();
			} catch (IOException e) {
				e.printStackTrace();
			}
			read.setNumber(st.nval);
			// lesson
			try {
				tokenType = st.nextToken();
			} catch (IOException e) {
				e.printStackTrace();
			}
			read.setLesson(st.sval);
			//student
			try {
				tokenType = st.nextToken();
			} catch (IOException e) {
				e.printStackTrace();
			}
			read.setStudent(st.sval);
			//skill
			try {
				tokenType = st.nextToken();
			} catch (IOException e) {
				e.printStackTrace();
			}
			read.setSkill(st.sval);
			//cell
			try {
				tokenType = st.nextToken();
			} catch (IOException e) {
				e.printStackTrace();
			}
			read.setSCell(st.sval);
			//right
			try {
				tokenType = st.nextToken();
			} catch (IOException e) {
				e.printStackTrace();
			}
			read.setRight(st.nval);
			//eol
			try {
				tokenType = st.nextToken();
			} catch (IOException e) {
				e.printStackTrace();
			}
			read.setEol(st.sval);
			
			if (tokenType != StreamTokenizer.TT_EOF)
			{
				list.add(read);
			}
		}while (tokenType != StreamTokenizer.TT_EOF);
		
		return list;
		
	}
	
	public static Time processTime(String time){
		int minute = Integer.parseInt(time.substring(0, 2));
		int seconds = Integer.parseInt(time.substring(3, 5));
		Time t = new Time(0,minute,seconds);
		// hour minute second
		return t;
	}
	public static String processRule (String rule) {
		String rl = rule.substring(0, rule.indexOf(";"));
		return rl;
	}
	public static int[] processOpportunity(String op) {
		int[] opp = new int[2];
		if (op.contains("~")) {
			
		} else {
			opp[0] = Integer.parseInt(op);
		}
		return null;
	}

}
