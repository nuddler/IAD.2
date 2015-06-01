package iad.zad2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Neuron {

	private List<Double> weights;

	private List<Double> perviousWeights;

	private double learnFactoryMax;

	private double learnFactoryMin;

	private double iterationMax;

	private double neighbourRangeMin;

	private double neighbourRangeMax;
	
	private double p;

	private int deadCounter;

	public Neuron(double learnFactoryMax, double learnFactoryMin, int iterationMax, double neighbourRangeMin, double neighbourRangeMax) {

		this.learnFactoryMax = learnFactoryMax;
		this.learnFactoryMin = learnFactoryMin;
		this.iterationMax = iterationMax;
		this.neighbourRangeMin = neighbourRangeMin;
		this.neighbourRangeMax = neighbourRangeMax;
		this.p = 1;

	}

	public void adapt(Point point, int position, int iteration) {

		perviousWeights = new ArrayList<Double>(weights);

		for (int i = 0; i < weights.size(); i++) {
			Double newWeight = weights.get(i) + learnFactory(iteration) * neighbourFunction(position, iteration)
			        * (point.getCoords().get(i) - weights.get(i));
			weights.set(i, newWeight);
		}
		
		corectDeadCounter();
	}

	public void adaptKohoen(Point point, double distance, int iteration) {
		//System.out.println(distance);
		perviousWeights = new ArrayList<Double>(weights);
		
		for (int i = 0; i < weights.size(); i++) {
			Double newWeight = weights.get(i) + learnFactory(iteration) * neighbourFunctionKohen(distance, iteration)
			        * (point.getCoords().get(i) - weights.get(i));
			weights.set(i, newWeight);
		}
		
		corectDeadCounter();
	}

	private void corectDeadCounter() {
		if(perviousWeights.equals(weights)) {
			deadCounter++;
		} else {
			deadCounter = 0;
		}
	}

	

	private double learnFactory(int iteration) {

		double tmp = (learnFactoryMin / learnFactoryMax);
		double power = iteration / iterationMax;

		double learnFactory = learnFactoryMax * Math.pow(tmp, power);

		return learnFactory;
	}

	private double neighbourFunction(int position, int iteration) {

		double toExp = -position / neighbourRange(iteration);

		return Math.exp(toExp);
	}
	
	private double neighbourFunctionKohen(double distance, int iteration) {

		double flag = (distance > neighbourRange(iteration)) ? 0D : 1D;

		return flag;
	}

	private double neighbourRange(int iteration) {

		double tmp = (neighbourRangeMin / neighbourRangeMax);
		double power = iteration / iterationMax;

		double neighbourRange = neighbourRangeMax * Math.pow(tmp, power);
		return neighbourRange;
	}

	public boolean equals(Neuron obj) {

		if (weights.size() == obj.getWeights().size()) {

			for (int i = 0; i < weights.size(); i++) {

				if (weights.get(i) != obj.getWeights().get(i)) {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}

	public List<Double> getWeights() {
		return weights;
	}

	public void setWeights(List<Double> weights) {
		this.weights = weights;
	}

	public List<Double> getPerviousWeights() {
		return perviousWeights;
	}

	public void setPerviousWeights(List<Double> perviousWeights) {
		this.perviousWeights = perviousWeights;
	}

	public double calculateError(Point point) {
		if(point.getCoords().size() == weights.size()) {
			double error = 0;
			for (int i = 0; i < weights.size(); i++) {
				error += Math.abs(weights.get(i) - point.getCoords().get(i));
			}
			return error;
		}
		return 0;
	}
	public double getP() {
		return p;
	}

	public void setP(double p) {
		this.p = p;
	}

	public int getDeadCounter() {
		return deadCounter;
	}

	public void setDeadCounter(int deadCounter) {
		this.deadCounter = deadCounter;
	}

	public void relocate(int dimensionX, int dimensionY) {
		Random random = new Random();
		double randomX = (random.nextInt(dimensionX * 100)) / 100D;
		double randomY = (random.nextInt(dimensionY * 100)) / 100D;

		ArrayList<Double> weightsList = new ArrayList<Double>();
		weightsList.add(randomX);
		weightsList.add(randomY);
		setWeights(weightsList);
	}

}
