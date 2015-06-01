package iad.zad2;

import java.util.Collections;

public class NeuronGas extends SelfOrganizingNetwork {

	public NeuronGas(int dimensionX, int dimensionY, int pointCount,
			int neuronCount, double learnFactoryMax, double learnFactoryMin,
			int iterationMax, double neighbourRangeMin, double neighbourRangeMax,double pmin) {

		super(dimensionX, dimensionY, pointCount, neuronCount, learnFactoryMax,
				learnFactoryMin, iterationMax, neighbourRangeMin,
				neighbourRangeMax,pmin);
	}

	@Override
	protected double doEpoch(int i) {
		double epochError = 0;
		for (Point point : points) {

			Collections.sort(neurons, new EuclidesComparator(point));
			epochError += neurons.get(0).calculateError(point);
			int winner = getNotTiredWinner();
			
			for (int j = 0; j < neurons.size(); j++) {
				neurons.get(j).adapt(point, j, i);
				
				if(neurons.get(j).getDeadCounter() > 5 * points.size()) {
					neurons.get(j).relocate(12, 12);
				}
			}
		}
		
		return epochError * (1.0/points.size()) ;
	}
}
