package iad.zad2;

import java.util.Collections;

public class NeuronGas extends SelfOrganizingNetwork {

	public NeuronGas(int dimensionX, int dimensionY, int pointCount,
			int neuronCount, double learnFactoryMax, double learnFactoryMin,
			int iterationMax, double neighbourRangeMin, double neighbourRangeMax) {

		super(dimensionX, dimensionY, pointCount, neuronCount, learnFactoryMax,
				learnFactoryMin, iterationMax, neighbourRangeMin,
				neighbourRangeMax);
	}

	@Override
	protected double doEpoch(int i) {
		double epochError = 0;
		for (Point point : points) {

			Collections.sort(neurons, new EuclidesComparator(point));
			epochError += neurons.get(0).calculateError(point);
			
			for (int j = 0; j < neurons.size(); j++) {
				neurons.get(j).adapt(point, j, i);
			}
		}
		
		return epochError * (1.0/points.size()) ;
	}
}
