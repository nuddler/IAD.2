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
        System.out.println( "Hello World!" );
        
        int dimensionX = 100;
		int dimensionY = 100;
		int pointCount = 30;
		int neuronCount = 10;
		
		double neighbourRangeMin = 0.01;
		double neighbourRangeMax = 0.5;
		
		int iterationMax = 1000;
		
		double learnFactoryMin = 0.01;
		double learnFactoryMax = 0.5;
		
		NeutronGas gas = new NeutronGas(dimensionX,dimensionY,pointCount,neuronCount,learnFactoryMax, learnFactoryMin, iterationMax, neighbourRangeMin, neighbourRangeMax);
		gas.learn(iterationMax);
		
        System.out.println( "Hello5555555555!" );
    }
    
	public static void createChart(List<Double> points, String title) {

	    final XYSeries series = new XYSeries("Random Data");
	    
	    for (int i=0; i<points.size(); i++) {
			series.add(i+1, points.get(i));
		}

	    XYSeriesCollection dataset = new XYSeriesCollection();
	    dataset.addSeries(series);
	    // Generate the graph
	    //JFreeChart chart = ChartFactory.createXYLineChart(
	    JFreeChart chart = ChartFactory.createScatterPlot(
		    title, // Title
		    "Numer epoki", // x-axis Label
		    "Blad", // y-axis Label
		    dataset, // Dataset
		    PlotOrientation.VERTICAL, // Plot Orientation
		    false, // Show Legend
		    true, // Use tooltips
		    false // Configure chart to generate URLs?
	    );
	    ChartFrame frame1=new ChartFrame("XYArea Chart",chart);
		frame1.setVisible(true);
		frame1.setSize(400,400);
//	    }
	}
}
