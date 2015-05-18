package iad.zad2;

import java.util.Collections;

public class NeutronGas extends SelfOrganizingNetwork {

	public NeutronGas(int dimensionX, int dimensionY, int pointCount,
			int neuronCount, double learnFactoryMax, double learnFactoryMin,
			int iterationMax, double neighbourRangeMin, double neighbourRangeMax) {

		super(dimensionX, dimensionY, pointCount, neuronCount, learnFactoryMax,
				learnFactoryMin, iterationMax, neighbourRangeMin,
				neighbourRangeMax);
	}

	@Override
	protected void doEpoch(int i) {
		for (Point point : points) {

			Collections.sort(neurons, new EuclidesComparator(point));

			for (int j = 0; j < neurons.size(); j++) {
				neurons.get(j).adapt(point, j, i);
			}
		}
	}
}
