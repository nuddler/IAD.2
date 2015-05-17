package iad.zad2;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Start" );
        
        int dimensionX = 100;
		int dimensionY = 100;
		int pointCount = 80;
		int neuronCount = 10;
		
		double neighbourRangeMin = 0.01;
		double neighbourRangeMax = 0.5;
		
		int iterationMax = 100;
		
		double learnFactoryMin = 0.01;
		double learnFactoryMax = 0.5;
		
		NeutronGas gas = new NeutronGas(dimensionX,dimensionY,pointCount,neuronCount,learnFactoryMax, learnFactoryMin, iterationMax, neighbourRangeMin, neighbourRangeMax);
		gas.learn(iterationMax);
		
        System.out.println( "End!" );
        
        showCharts(gas.getCharts(),1000);
    }
    
	public static JFreeChart createChart(List<Point> points,List<Neuron> neurons, String title) {

	    final XYSeries series = new XYSeries("Points");
	    
	    for (int i=0; i<points.size(); i++) {
			series.add(points.get(i).getCoords().get(0),points.get(i).getCoords().get(1));
		}
	    
	    final XYSeries series2 = new XYSeries("Neurons");
	    for (int i=0; i<neurons.size(); i++) {
			series2.add(neurons.get(i).getWeights().get(0),neurons.get(i).getWeights().get(1));
		}

	    XYSeriesCollection dataset = new XYSeriesCollection();
	    dataset.addSeries(series);
	    dataset.addSeries(series2);
	    // Generate the graph
	    JFreeChart chart = ChartFactory.createScatterPlot(
		    title, // Title
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
	
	public static void showCharts(List<JFreeChart> charts,int timeBetweenCharts) {
		for (JFreeChart jFreeChart : charts) {
			ChartFrame frame1 = new ChartFrame("Wykres",jFreeChart);
			frame1.setVisible(true);
			frame1.setSize(800,800);
		    try {
				Thread.sleep(timeBetweenCharts);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		    frame1.setVisible(false);
		}
	}
}
