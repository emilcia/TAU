
package pl.edu.pjwstk.lab2;

import java.util.List;

public interface Vector {
	
	public Vector add(Vector v);
	public Vector addVectors(Vector a, Vector b);
	public void set(List<Double> list);
	public List<Double> get();
	
}