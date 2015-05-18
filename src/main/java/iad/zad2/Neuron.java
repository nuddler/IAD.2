package iad.zad2;

import java.util.ArrayList;
import java.util.List;

public class Neuron {

	private List<Double> weights;

	private List<Double> perviousWeights;

	private double learnFactoryMax;

	private double learnFactoryMin;

	private double iterationMax;

	private double neighbourRangeMin;

	private double neighbourRangeMax;

	public Neuron(double learnFactoryMax, double learnFactoryMin, int iterationMax, double neighbourRangeMin, double neighbourRangeMax) {

		this.learnFactoryMax = learnFactoryMax;
		this.learnFactoryMin = learnFactoryMin;
		this.iterationMax = iterationMax;
		this.neighbourRangeMin = neighbourRangeMin;
		this.neighbourRangeMax = neighbourRangeMax;

	}

	public void adapt(Point point, int position, int iteration) {

		perviousWeights = new ArrayList<Double>(weights);

		for (int i = 0; i < weights.size(); i++) {
			Double newWeight = weights.get(i) + learnFactory(iteration) * neighbourFunction(position, iteration)
			        * (point.getCoords().get(i) - weights.get(i));
			weights.set(i, newWeight);
		}
	}

	public void adaptKohoen(Point point, double distance, int iteration) {
		System.out.println(distance);
		perviousWeights = new ArrayList<Double>(weights);

		for (int i = 0; i < weights.size(); i++) {
			Double newWeight = weights.get(i) + learnFactory(iteration) * neighbourFunctionKohen(distance, iteration)
			        * (point.getCoords().get(i) - weights.get(i));
			weights.set(i, newWeight);
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

		double flag = (distance >= neighbourRange(iteration)) ? 0D : 1D;

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

}
