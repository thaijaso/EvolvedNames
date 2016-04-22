//Jason Thai

public class Main {
	
	public static final int NUM_OF_GENOMES = 100;
	
	public static final double MUTATION_RATE = 0.05;
	
	public static void main(String[] args) {
		//testGenome();
		//testPopulation();
		runSimulation();
		
	}
	
	public static void testGenome() {
		Genome g2 = new Genome("AAA", MUTATION_RATE);
		Genome g3 = new Genome("BBBBB", MUTATION_RATE);
		g2.crossover(g3);
		System.out.println(g2);
		
		Genome g4 = new Genome("AAAA", MUTATION_RATE);
		Genome g5 = new Genome("BBB", MUTATION_RATE);
		System.out.println(g4.fitness());
		System.out.println(g5.fitness());
		
		Genome g6 = new Genome(MUTATION_RATE);
		g6.mutate();
		System.out.println(g6);
		
		Genome g7 = new Genome(g2);
		System.out.println(g7);
	}
	
	public static void testPopulation() {
		Genome g2 = new Genome("AAA", MUTATION_RATE);
		Genome g3 = new Genome("XYZ", MUTATION_RATE);
		Genome g4 = new Genome("ABA", MUTATION_RATE);
		Genome g5 = new Genome("BBBB", MUTATION_RATE);
		Genome g6 = new Genome("BBBBB", MUTATION_RATE);
		
		Population pop = new Population();
		pop.add(g2);
		pop.add(g3);
		pop.add(g4);
		pop.add(g5);
		pop.add(g6);
		pop.day();
		
		System.out.println(pop);
	}
	
	public static void runSimulation() {
		Population pop = new Population(NUM_OF_GENOMES, MUTATION_RATE);
		
		long startTime = System.nanoTime();   
		int generation = 0;
		
		while (pop.getMostFit().fitness() != 0) {
			pop.day();
			System.out.println(pop.getMostFit());
			generation++;
		}
		
		System.out.println("Generations: " + generation);
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1000000;
		
		System.out.println("Running time: " + duration + " milliseconds");
	}
	
}
