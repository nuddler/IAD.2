package iad.zad2;


public class Kohonen extends SelfOrganizingNetwork {

	public Kohonen(int dimensionX, int dimensionY, int pointCount,
			int neuronCount, double learnFactoryMax, double learnFactoryMin,
			int iterationMax, double neighbourRangeMin, double neighbourRangeMax) {

		super(dimensionX, dimensionY, pointCount, neuronCount, learnFactoryMax,
				learnFactoryMin, iterationMax, neighbourRangeMin,
				neighbourRangeMax);
	}

	@Override
	protected void doEpoch(int i) {
		for (Point point : points) {

			for (int j = 0; j < neurons.size(); j++) {
				neurons.get(j).adaptKohoen(point, calculateDistance(neurons.get(j), point), i);
			}
		}
	}
	
	private double calculateDistance(Neuron neuron, Point point) {
		
		if(point.getCoords().size() == neuron.getWeights().size() && neuron.getWeights().size() == 2) {
			double xToSquare =  point.getCoords().get(0) - neuron.getWeights().get(0);
			double yToSquare =  point.getCoords().get(1) - neuron.getWeights().get(1);
			
			return Math.sqrt(xToSquare*xToSquare + yToSquare*yToSquare);
		}
		return 0;
    }
}
