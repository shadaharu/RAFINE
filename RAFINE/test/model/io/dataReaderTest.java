package model.io;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.object.data;

public class dataReaderTest {

	@Test
	public void test() {
		ArrayList<data> list = dataReader.readIn("input/input_1.txt");
		assertEquals(1.0,list.get(0).getNumber(),0);
		assertEquals(list.get(0).getStudent(),"student102");
		assertEquals(35.0,list.get(34).getNumber(),0);
		assertEquals(list.get(35).getStudent(),"student106");
	}

}
