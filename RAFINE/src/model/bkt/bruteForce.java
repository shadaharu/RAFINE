package model.bkt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import model.io.dataReader;
import model.io.dataReader2;
import model.object.data;
import model.object.data2;
import model.object.skill;

/**
 * The BKT model based on  brute force search method
 * @author Qiuyu Chen(qnchen)
 *
 */
public class bruteForce {
	public static ArrayList<data> list;
	public static ArrayList<data2> list2;
	public static Map<String,skill> rule;
	public static boolean lnminus1_estimation = false;
	public static boolean bounded = true;
	public static boolean LoTbounded = false;
	
	

	public static void main(String[] args) throws IOException {
		String filename = "input/input_1.txt";
		list = dataReader.readIn(filename);
		list2 = dataReader2.readIn(filename);
		orgnizeRule();

		System.out.print("Skill");
		System.out.print("\t");
		System.out.print("Lo");
		System.out.print("\t");
		System.out.print("Guess");
		System.out.print("\t");
		System.out.print("Slip");
		System.out.print("\t");
		System.out.print("Trans");
		System.out.println("\teol");
		Iterator<skill> it = rule.values().iterator();
		for (int i = 0; i < rule.size(); i++) {
			fitModel(it.next());
		}
	}

	/**
	 * Build the rule map
	 */
	public static void orgnizeRule() {
		rule = new HashMap<String,skill>();
		int lth = list.size();
		for (int i = 0 ; i < lth; i++)
		{
//			System.out.println(list.get(i).toString());
			String kc = list.get(i).getSkill();
			int right = (int) list.get(i).getRight();
			String student = list.get(i).getStudent();
			if (!rule.containsKey(kc))
			{
				skill sk = new skill(kc);
				rule.put(kc, sk);
			}
			rule.get(kc).appendRight(right);
			rule.get(kc).appendStudent(student);
		}
	}
	
	
	public static void orgnizeRule2() {
		rule = new HashMap<String,skill>();
		int lth = list2.size();
		for (int i = 0 ; i < lth; i++)
		{
//			System.out.println(list.get(i).toString());
			String kc = list2.get(i).getSkill();
			String student = list2.get(i).getId();
			int incorrect = (int)list2.get(i).getIncorrect();
			int correct = (int)list2.get(i).getCorrect();
			if (!rule.containsKey(kc))
			{
				skill sk = new skill(kc);
				rule.put(kc, sk);
			}
			for (int j = 0; j < incorrect; j++)
			{
				rule.get(kc).appendRight(0);
			}
			for (int j = 0; j < correct; j++)
			{
				rule.get(kc).appendRight(1);
			}
			rule.get(kc).appendStudent(student);
		}
	}
	
	public static void fitModel(skill skill) {
		double SSR = 0.0;
		double BestSSR = 9999999.0;
		double bestLo = 0.01;
		double besttrans = 0.01;
		double bestG = 0.01;
		double bestS = 0.01;
		double topG = 0.99;
		double topS = 0.99;
		double topL0 = 0.99;
		double topT = 0.99;
		// bounded the Lo and T with 0.85 and 0.3
		if (LoTbounded) {
			topL0 = 0.85;
			topT = 0.3;
		}
		// bounded the guess and slip with 0.3 and 0.1
		if (bounded) {
			topG = 0.3;
			topS = 0.1;
		}
		
		
		for (double Lzero = 0.01; Lzero <= topL0; Lzero = Lzero + 0.01) {
			for (double trans = 0.01; trans <= topT; trans = trans + 0.01) {
				for (double G = 0.01; G <= topG; G = G + 0.01) {
					for (double S = 0.01; S <= topS; S = S + 0.01) {
						SSR  = findGOOF(skill,Lzero, trans, G, S);
						if (SSR < BestSSR) {
							BestSSR = SSR;
							bestLo = Lzero;
							besttrans = trans;
							bestG = G;
							bestS = S;
						}
					}
				}
			}
		}
		
//		//repeat
		double startLzero = bestLo;
		double starttrans = besttrans;
		double startG = bestG;
		double startS = bestS;
		for (double Lzero = startLzero - 0.009; ((Lzero <= startLzero + 0.009) && (Lzero <= topL0)); Lzero = Lzero + 0.001)
			for (double G = startG - 0.009; ((G <= startG + 0.009) && (G <= topG)); G = G + 0.001) {
				for (double S = startS - 0.009; ((S <= startS + 0.009) && (S <= topS)); S = S + 0.001) {
					for (double trans = starttrans - 0.009; ((trans <= starttrans + 0.009) && (trans < topT)); trans = trans + 0.001) {
						SSR = findGOOF(skill, Lzero, trans, G, S);
						if (SSR < BestSSR) {
							BestSSR = SSR;
							bestLo = Lzero;
							besttrans = trans;
							bestG = G;
							bestS = S;
						}
					}
				}
			}

		System.out.print(skill.toString());
		System.out.print("\t");
		System.out.print(bestLo);
		System.out.print("\t");
		System.out.print(bestG);
		System.out.print("\t");
		System.out.print(bestS);
		System.out.print("\t");
		System.out.print(besttrans);
		System.out.println("\teol");
	}
	
	public static double findGOOF(skill skill, double Lo, double trans, double guess, double slip) {
		double SSR = 0.0;
		double prevL = 0.0;
		double likelihoodcorrect = 0.0;
		double prevLgivenresult = 0.0;
		double newL = 0.0;
		
		String prestudent = skill.getStudent().get(0);

		for (int i = 0; i < skill.getLength(); i++)
		{
			if (skill.getStudent().get(i).compareTo(prestudent) != 0) {
				prevL = Lo;
				prestudent = skill.getStudent().get(i);
//				System.out.println("Different"); 
			}
			
		    likelihoodcorrect = (prevL * (1.0 - slip)) + ((1.0 - prevL) * guess);
		    SSR += (skill.getRight().get(i) - likelihoodcorrect) * (skill.getRight().get(i) - likelihoodcorrect);
		    if (skill.getRight().get(i) == 1)
		    {
		    	prevLgivenresult = ((prevL * (1.0 - slip)) / ((prevL * (1 - slip)) + ((1.0 - prevL) * (guess))));
		    } else {
		    	prevLgivenresult = ((prevL * (slip)) / ((prevL * (slip)) + ((1.0 - prevL) * (1.0 - guess))));
		    }
		    
		    newL = prevLgivenresult + (1.0 - prevLgivenresult) * trans;
			prevL = newL;
		}
		return SSR;
	}

}
