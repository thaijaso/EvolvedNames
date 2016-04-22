//Jason Thai

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Population {
	
	public static final Random RAND = new Random();
	
	private Genome mostFit;
	
	private List<Genome> myGenomes = new ArrayList<Genome>();
	
	public Population() {
		
	}
	
	public Population(Integer numGenomes, Double mutationRate) {
		 for (int i = 0; i < numGenomes; i++) {
			 Genome genome = new Genome(mutationRate);
			 myGenomes.add(genome);
		 }
		 
		 mostFit = myGenomes.get(0);
	}
	
	public void day() {	
		setMostFit();
		deleteLeastFit();	
		createNewGenomes();	
	}
	
	private void setMostFit() {
		if (mostFit == null) {
			mostFit = myGenomes.get(0);
		}
		
		Collections.sort(myGenomes, new Genome());
		mostFit = myGenomes.get(0);
	}
	
	public Genome getMostFit() {
		return mostFit;
	}
	
	private void deleteLeastFit() {
		myGenomes = myGenomes.subList(0, myGenomes.size() / 2);
	}
	
	private void createNewGenomes() {
		double coinFlip = Math.random();
		List<Genome> offsprings = new ArrayList<Genome>();
		
		for (int i = 0; i < myGenomes.size(); i++) {	
			
			int randInt = RAND.nextInt(myGenomes.size());
			Genome randGenome = myGenomes.get(randInt);
			Genome clone = new Genome(randGenome);
			
			if (coinFlip <= .5) {
				clone.mutate();
				offsprings.add(clone);
			} else {
				int randInt2 = RAND.nextInt(myGenomes.size());
				Genome randGenome2 = myGenomes.get(randInt2);
				clone.crossover(randGenome2);
				clone.mutate();
				offsprings.add(clone);
			}
		}
				
		myGenomes.addAll(offsprings);
	}
	
	//for testing
	public void add(Genome genome) {
		myGenomes.add(genome);
	}
	
	public String toString() {
		return myGenomes.toString();
	}
}
