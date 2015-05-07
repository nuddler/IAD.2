package iad.zad2;

import java.util.List;


public class NeutronGas {

	private List<Point> points;
	private int dimensionX;
	private int dimensionY;
	private int pointCount;
	private int neuronCount;
	private List<Neuron> neurons;

	public NeutronGas(int dimensionX, int dimensionY, int pointCount, int neuronCount) {
		
		this.dimensionX = dimensionX;
		this.dimensionY = dimensionY;
		this.pointCount = pointCount;
		this.neuronCount = neuronCount;
		
		this.points = generatePoints();
		this.neurons = generateNeurons();
		
    }

	private List<Neuron> generateNeurons() {
		return null;
	}

	private List<Point> generatePoints() {
		return null;
    }

	public void learn(int iterationCount) {
		
	    for(int i=0; i < iterationCount; i++) {
	    	doEpoch();
	    }
    }

	private void doEpoch() {
		
		for (Point point : points) {
	        sortNeuron(point);
        }
	    
    }

	
    private void sortNeuron(Point point) {
	    // TODO Auto-generated method stub
	    
    }

	public List<Point> getPoints() {
    	return points;
    }

	
    public void setPoints(List<Point> points) {
    	this.points = points;
    }

	
    public int getDimensionX() {
    	return dimensionX;
    }

	
    public void setDimensionX(int dimensionX) {
    	this.dimensionX = dimensionX;
    }

	
    public int getDimensionY() {
    	return dimensionY;
    }

	
    public void setDimensionY(int dimensionY) {
    	this.dimensionY = dimensionY;
    }

	
    public int getPointCount() {
    	return pointCount;
    }

	
    public void setPointCount(int pointCount) {
    	this.pointCount = pointCount;
    }

	
    public int getNeuronCount() {
    	return neuronCount;
    }

	
    public void setNeuronCount(int neuronCount) {
    	this.neuronCount = neuronCount;
    }

}
