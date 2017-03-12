package zadanie;

import java.util.List;

public interface Vector {
	
	public Vector add(Vector v);
	public Vector addVectors(Vector a, Vector b);
	public void set(List<Double> list);
	public List get(List<Double> list);
	
}
