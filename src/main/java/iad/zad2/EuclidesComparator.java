package iad.zad2;

import java.util.Comparator;

public class EuclidesComparator implements Comparator<Neuron> {

	
	private Point point;

	public EuclidesComparator(Point point) {
		this.point = point;
    }

	public int compare(Neuron o1, Neuron o2) {
		
		double o1Distance = calculateDistance(o1);
		double o2Distance = calculateDistance(o2);
		
		if(o1Distance > o2Distance) {
			return 1;
		} else if (o1Distance == o2Distance) {
			return 0;
		} else {
			return -1;
		}
	}

	private double calculateDistance(Neuron neuron) {
		
		if(point.getCoords().size() == neuron.getWeights().size() && neuron.getWeights().size() == 2) {
			double xToSquare =  point.getCoords().get(0) - neuron.getWeights().get(0);
			double yToSquare =  point.getCoords().get(1) - neuron.getWeights().get(1);
			
			return Math.sqrt(xToSquare*xToSquare + yToSquare*yToSquare);
		}
		return 0;
    }
}
