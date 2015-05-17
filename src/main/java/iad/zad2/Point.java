package iad.zad2;

import java.util.ArrayList;
import java.util.List;

public class Point {

	private List<Double> coords;

	public Point(double randomX, double randomY) {
		
		coords = new ArrayList<Double>();
		
		coords.add(randomX);
		coords.add(randomY);
    }

	public List<Double> getCoords() {
		return coords;
	}

	public void setCoords(List<Double> coords) {
		this.coords = coords;
	}

	public boolean equals(Point obj) {
		
		if (coords.size() == obj.getCoords().size()) {
			
			for (int i = 0; i < coords.size(); i++) {
				
				if(coords.get(i) != obj.getCoords().get(i)) {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}
}
