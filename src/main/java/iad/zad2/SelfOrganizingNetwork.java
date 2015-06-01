/**
 * 
 */
package iad.zad2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.jfree.chart.JFreeChart;

import com.gif4j.light.GifEncoder;
import com.gif4j.light.GifFrame;
import com.gif4j.light.GifImage;

/**
 * @author bartek
 *
 */
public abstract class SelfOrganizingNetwork {
	
	private int dimensionX;
	private int dimensionY;
	private int pointCount;
	private int neuronCount;
	protected double pmin;
	
	protected List<Point> points;
	protected List<Neuron> neurons;
	protected List<JFreeChart> charts;
	private List<BufferedImage> chartsBufferd;
	private GifImage image;

	public SelfOrganizingNetwork(int dimensionX, int dimensionY, int pointCount,
			int neuronCount, double learnFactoryMax, double learnFactoryMin,
			int iterationMax, double neighbourRangeMin, double neighbourRangeMax,double pmin) {

		this.dimensionX = dimensionX;
		this.dimensionY = dimensionY;
		this.pointCount = pointCount;
		this.neuronCount = neuronCount;
		this.pmin = pmin;
		//generatePoints2();
		points = App.loadPoints();
		pointCount = points.size();
		generateNeurons(neighbourRangeMax, neighbourRangeMin, iterationMax,
				learnFactoryMin, learnFactoryMax);
		
		image = new GifImage(800, 800);
        image.setDefaultDelay(80);
        image.setLoopNumber(0);

	}

	private void generateNeurons(double neighbourRangeMax,
			double neighbourRangeMin, int iterationMax, double learnFactoryMin,
			double learnFactoryMax) {
		Random random = new Random();
		neurons = new ArrayList<Neuron>();

		for (int i = 0; i < neuronCount; i++) {
			double randomX = (random.nextInt(dimensionX * 100)) / 100D;
			double randomY = (random.nextInt(dimensionY * 100)) / 100D;

			ArrayList<Double> weightsList = new ArrayList<Double>();
			weightsList.add(randomX);
			weightsList.add(randomY);

			Neuron neuron = new Neuron(learnFactoryMax, learnFactoryMin,
					iterationMax, neighbourRangeMin, neighbourRangeMax);
			neuron.setWeights(weightsList);

			if (!neurons.contains(neuron)) {
				neurons.add(neuron);
			} else {
				i--;
			}
		}
	}

	private void generatePoints() {
		Random random = new Random();
		points = new ArrayList<Point>();

		for (int i = 0; i < pointCount; i++) {
			double randomX = (random.nextInt(dimensionX * 100)) / 100D;
			double randomY = (random.nextInt(dimensionY * 100)) / 100D;

			Point point = new Point(randomX, randomY);

			if (!points.contains(point)) {
				points.add(point);
			} else {
				i--;
			}
		}
	}

	protected int getNotTiredWinner() {
		int winner = -1;
		for (int k = 0; neurons.size() > k; k++) {
			double p = neurons.get(k).getP();
			if (p > pmin && winner == -1) {
				winner = k;
				neurons.get(winner).setP(p - pmin);
			} else {
				neurons.get(k).setP(p + 1.0 / neuronCount);
			}
		}
		return winner;
	}
	
	private void generatePoints2() {
		Random random = new Random();
		points = new ArrayList<Point>();

		for (int i = 0; i < pointCount / 4; i++) {
			double randomX = (random.nextInt(30 * 100)) / 100D;
			double randomY = (random.nextInt(30 * 100)) / 100D;

			Point point = new Point(randomX, randomY);

			if (!points.contains(point)) {
				points.add(point);
			} else {
				i--;
			}
		}

		for (int i = 0; i < pointCount / 4; i++) {
			double randomX = dimensionX - (random.nextInt(30 * 100)) / 100D;
			double randomY = dimensionY - (random.nextInt(30 * 100)) / 100D;

			Point point = new Point(randomX, randomY);

			if (!points.contains(point)) {
				points.add(point);
			} else {
				i--;
			}
		}

		for (int i = 0; i < pointCount / 4; i++) {
			double randomX = (random.nextInt(30 * 100)) / 100D;
			double randomY = dimensionY - (random.nextInt(30 * 100)) / 100D;

			Point point = new Point(randomX, randomY);

			if (!points.contains(point)) {
				points.add(point);
			} else {
				i--;
			}
		}

		for (int i = 0; i < pointCount / 4; i++) {
			double randomX = dimensionX - (random.nextInt(30 * 100)) / 100D;
			double randomY = (random.nextInt(30 * 100)) / 100D;

			Point point = new Point(randomX, randomY);

			if (!points.contains(point)) {
				points.add(point);
			} else {
				i--;
			}
		}

	}
	
	public List<Double> learn(int iterationCount) throws Exception{
		charts = new ArrayList<JFreeChart>();
		List<Double> errors = new ArrayList<Double>();
		
		for (int i = 0; i < iterationCount; i++) {
			errors.add(doEpoch(i));
			JFreeChart chart = App.createChart(points, getNeurons(),"Po epoce nr:" + (i + 1));
			
			BufferedImage image1 = chart.createBufferedImage(800, 800);
			
			image.addGifFrame(new GifFrame(image1));
			
			charts.add(chart);
		}
		GifEncoder.encode(image, new File("out.gif"));
		return errors;
	}
	
	protected abstract double doEpoch(int i);

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

	public List<Neuron> getNeurons() {
		return neurons;
	}

	public void setNeurons(List<Neuron> neurons) {
		this.neurons = neurons;
	}

	public List<JFreeChart> getCharts() {
		return charts;
	}

	public void setCharts(List<JFreeChart> charts) {
		this.charts = charts;
	}

	public List<BufferedImage> getChartsBufferd() {
		return chartsBufferd;
	}

	public void setChartsBufferd(List<BufferedImage> chartsBufferd) {
		this.chartsBufferd = chartsBufferd;
	}

	public GifImage getImage() {
		return image;
	}

	public void setImage(GifImage image) {
		this.image = image;
	}
}
