package iad.zad2;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        int dimensionX = 0;
		int dimensionY = 0;
		int pointCount = 0;
		int neuronCount = 0;
		
		NeutronGas gas = new NeutronGas(dimensionX,dimensionY,pointCount,neuronCount);
        int iterationCount = 1000;
		gas.learn(iterationCount);
    }
}
