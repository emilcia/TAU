package pl.edu.pjwstk.lab2;

import java.util.ArrayList;
import java.util.List;

public class VectorImpl implements Vector {

	private List<Double> list = new ArrayList<Double>();
	public Vector add(Vector v) {
		
		if(v.get().size() != this.list.size()){
			throw new IllegalArgumentException();
		}
		for(int i=0; i<this.list.size(); i++){
			Double value = this.list.get(i);
			value+=v.get().get(i);
			this.list.set(i, value);
		}
		return this;
	}

	public Vector addVectors(Vector a, Vector b) {
		Vector c = new VectorImpl();
		List<Double> list = new ArrayList<Double>();
		
		if(a.get().size() != b.get().size()){
			throw new IllegalArgumentException();
		}

		for(int i=0; i<a.get().size(); i++){
			Double value = a.get().get(i);
			value+=b.get().get(i);
			list.add(value);
		}
		c.set(list);
		return c;	
	}

	public void set(List<Double> list) {
		
		if(list.size() == 0){
			throw new IllegalArgumentException();
		}
		this.list = list;
		
	}

	public List<Double> get() {
		return this.list;
	}

	@Override
	public Vector sub(Vector v) {
		
		if(v.get().size() != this.list.size()){
			throw new IllegalArgumentException();
		}
		for(int i=0; i<this.list.size(); i++){
			Double value = this.list.get(i);
			value-=v.get().get(i);
			this.list.set(i, value);
		}
		return this;
	}

}
