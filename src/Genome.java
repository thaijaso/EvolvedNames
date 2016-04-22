//Jason Thai

import java.util.Comparator;
import java.util.Random;

public class Genome implements Comparator<Genome>{
	
	public static final char[] SET = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
										'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
										'Y', 'Z', '_', '-', '\''};
	
	public static Random RAND = new Random();
	
	private static final String TARGET = "CHRISTOPHER_PAUL_MARIOTT";
	//CHRISTOPHER_PAUL_MARRIOTT
	
	private StringBuilder myName;
	
	private double myMutationRate;
	
	public Genome() {
		
	}
	
	public Genome(double mutationRate) {
		myName = new StringBuilder();
		myName.append('A');
		myMutationRate = mutationRate;
	}
	
	public Genome(Genome gene) {
		myName = new StringBuilder(gene.myName.toString());
		myMutationRate = gene.myMutationRate;
	}
	
	//For testing
	public Genome(String name, double mutationRate) {
		myName = new StringBuilder();
		
		for (int i = 0; i < name.length(); i++) {
			char ch = name.charAt(i);
			myName.append(ch);
		}
		
		myMutationRate = mutationRate;
	}
	
	public void mutate() {
		double randToMutate = Math.random();	// 0 - 1
		
		//add
		if (randToMutate < myMutationRate) {
			int randInt = RAND.nextInt(SET.length);
			char randChar = SET[randInt];
				
			randInt = RAND.nextInt(myName.length() + 1);
			myName.insert(randInt, randChar);	
		}
		
		randToMutate = Math.random();
		
		//delete
		if (randToMutate < myMutationRate) {
			
			if (myName.length() >= 2) {
				int randInt = RAND.nextInt(myName.length());
				myName.deleteCharAt(randInt);
			}
		}
		
		//replace
		for (int i = 0; i < myName.length(); i++) {
			
			randToMutate = Math.random();
			
			if (randToMutate < myMutationRate) {
				int randInt = RAND.nextInt(SET.length);
				char randChar = SET[randInt];
				myName.setCharAt(i, randChar);
			}
		}
	}
	
	public void crossover(Genome other) {
		Genome parent;
		Genome longest = this;
		StringBuilder sb = new StringBuilder();
		
		if (myName.length() < other.myName.length()) {
			longest = other;
		}
		
		for (int i = 0; i < longest.myName.length(); i++) {
			
			double coinFlip = Math.random();
			
			if (coinFlip <= .5) {
				parent = other;
			} else {
				parent = this;
			}
				
			if (parent.myName.length() > i) {
				sb.append(parent.myName.charAt(i));
			} else {
				break;
			}	
		}
		
		myName = sb;
	}
	
	public Integer fitness() {
		int myNameLength = myName.length();
		int targetNameLength = TARGET.length();
		
		int max = Math.max(myNameLength, targetNameLength);
		int fitness = Math.abs(targetNameLength - myNameLength);
		
		if (targetNameLength > myNameLength) {
			if (!myName.toString().equals(TARGET)) {
				
				for (int i = 0; i < max; i++) {
					char charTarget = TARGET.charAt(i);
					int charTargetIndex = myName.toString().indexOf(charTarget, i);
					
					if (charTargetIndex != i) {
						fitness++;
					}
				}
			}
		} else {
			if (!TARGET.equals(myName.toString())) {
				
				for (int i = 0; i < max; i++) {
					char nameTarget = myName.charAt(i);
					int nameTargetIndex = TARGET.indexOf(nameTarget, i);
					
					if (nameTargetIndex != i) {
						fitness++;
					}
				}
			}
		}
		
		
		return fitness;
	}
	
	//------------------------  Wagner-Fischer Algorithm  -----------------//////////
//	public Integer fitness() {
//		
//
//				int n = myName.length();
//				int m = TARGET.length();
//				int[][] D = new int[n + 1][m + 1];
//				
//				for(int i = 0; i < m + 1; i++) {
//					D[0][i] = i;
//				}
//		
//				for(int i = 0; i < n + 1; i++) {
//					D[i][0] = i;
//				}
//		
//				for(int i = 1; i <= n; i++) {
//					for(int j = 1; j <= m; j++) {	
//						if(myName.charAt(i - 1) == TARGET.charAt(j - 1)) {
//							D [i][j]= D [i - 1][j - 1];
//						} else {
//							D [i][j] = Math.min(Math.min(D [i - 1][j] + 1 , D[i][j - 1 ] + 1 ) , D[i - 1][j - 1 ]+ 1);
//						}
//					}
//				}
//		
//				return D[n][m] + (int)(Math.abs(n - m) + 1) / 2;
//	}
	//------------------------  extra credit method, Wagner-Fischer algorithm  -----------------//////////
	
	@Override
	public int compare(Genome g1, Genome g2) {
		return g1.fitness() - g2.fitness();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(\"");
		sb.append(myName.toString());
		sb.append("\", ");
		sb.append(fitness());
		sb.append(")");
		return sb.toString();
	}
}
