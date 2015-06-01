package iad.zad2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.gif4j.light.GifEncoder;
import com.gif4j.light.GifFrame;
import com.gif4j.light.GifImage;

/**
 * 
 * Zadanie 2 z IAD
 *
 */
public class App {

	public static void main(String[] args) {

		System.out.println("Start");

		int dimensionX = 10;
		int dimensionY = 10;
		int pointCount = 100;

		int neuronCount = 200;
		
		int iterationMax = 100;
		
		double learnFactoryMin = 0.01;
		double learnFactoryMax = 0.5;
		
		double neighbourRangeMin = 0.01;
		double neighbourRangeMax = 2.7;

		double pmin = 0.75;

		SelfOrganizingNetwork selfOrganizingNetwork = new Kohonen(dimensionX,
				dimensionY, pointCount, neuronCount, learnFactoryMax,
				learnFactoryMin, iterationMax, neighbourRangeMin,
				neighbourRangeMax,pmin);
		
		List<Double> errors = null;
		try {
			errors = selfOrganizingNetwork.learn(iterationMax);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		createChart(errors, "Wykres błędu");

		System.out.println("End!");
		JOptionPane.showMessageDialog(null, "Done");
	}

	public static JFreeChart createChart(List<Point> points,
			List<Neuron> neurons, String title) {

		final XYSeries series = new XYSeries("Points");

		for (int i = 0; i < points.size(); i++) {
			series.add(points.get(i).getCoords().get(0), points.get(i).getCoords().get(1));
		}

		final XYSeries series2 = new XYSeries("Neurons");
		for (int i = 0; i < neurons.size(); i++) {
			series2.add(neurons.get(i).getWeights().get(0), neurons.get(i).getWeights().get(1));
		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series2);
		dataset.addSeries(series);
		// Generate the graph
		JFreeChart chart = ChartFactory.createScatterPlot(title, // Title
				"Oś x", // x-axis Label
				"Oś y", // y-axis Label
				dataset, // Dataset
				PlotOrientation.VERTICAL, // Plot Orientation
				true, // Show Legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
				);

		return chart;
	}

	public static void showCharts(List<JFreeChart> charts) {
		System.out.println("Creating charts");
		ArrayList<BufferedImage> list = new ArrayList<BufferedImage>();
		for (JFreeChart jFreeChart : charts) {

			BufferedImage image = jFreeChart.createBufferedImage(800, 800);
			list.add(image);
		}
		System.out.println("Creating charts - done");
		createGif(list);
	}

	public static void createGif(ArrayList<BufferedImage> charts) {
		System.out.println("Creating gif");
		GifImage image = new GifImage(900, 900);
		image.setDefaultDelay(80);
		image.setLoopNumber(0);
		for (int i = 0; i < charts.size(); i++) {
			try {
				image.addGifFrame(new GifFrame(charts.get(i)));
			} catch (InterruptedException ex) {
				ex.getMessage();
			}
		}
		try {
			GifEncoder.encode(image, new File("out.gif"));
		} catch (IOException ex) {
			ex.getMessage();
		}
		System.out.println("Creating gif - DONE");
	}
    
	@SuppressWarnings("resource")
	public static List<Point> loadPoints() {

		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("ressource/attract.txt"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		List<Point> points = new ArrayList<Point>();
		while (scanner.hasNextLine()) {
			String nextLine = scanner.nextLine();
			String[] split = nextLine.split(",");
			Point e = new Point(Double.parseDouble(split[0]),Double.parseDouble(split[1]));
			points.add(e);
		}

		return points;
	}
	
	public static void createChart(List<Double> errors, String title) {

	    final XYSeries series = new XYSeries("Bład");
	    
	    for (int i=0; i<errors.size(); i++) {
			series.add(i+1, errors.get(i));
		}

	    XYSeriesCollection dataset = new XYSeriesCollection();
	    dataset.addSeries(series);
	    // Generate the graph
	    JFreeChart chart = ChartFactory.createXYLineChart(
		    title, // Title
		    "Numer epoki", // x-axis Label
		    "Błąd", // y-axis Label
		    dataset, // Dataset
		    PlotOrientation.VERTICAL, // Plot Orientation
		    false, // Show Legend
		    true, // Use tooltips
		    false // Configure chart to generate URLs?
	    );
	    ChartFrame frame1=new ChartFrame("XYArea Chart",chart);
		frame1.setVisible(true);
		frame1.setSize(400,400);
	}
}
