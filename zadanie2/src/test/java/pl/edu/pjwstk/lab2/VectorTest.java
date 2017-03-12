package pl.edu.pjwstk.lab2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.JUnit4;

import pl.edu.pjwstk.lab2.Vector;
import pl.edu.pjwstk.lab2.VectorImpl;

	@RunWith(JUnit4.class)
	public class VectorTest {
		
		@Test
		public void addVectorsTest() {
		
			Vector v1 = new VectorImpl();
			Vector v2 = new VectorImpl();
			Vector v3 = new VectorImpl();
			
			List<Double> list1 = new ArrayList<Double>();
			List<Double> list2 = new ArrayList<Double>();
			List<Double> list3 = new ArrayList<Double>();


			list1.add(2.2);
			list1.add(4.3);
			
			list2.add(3.3);
			list2.add(4.3);
			
			list3.add(5.5);
			list3.add(8.6);
			
			v1.set(list1);
			v2.set(list2);
			v3.set(list3);

			assertEquals(v3.get(), v1.addVectors(v2,v1).get());	
			
		}
		@Test
		public void subTest() {
		
			Vector v1 = new VectorImpl();
			Vector v2 = new VectorImpl();
			Vector v3 = new VectorImpl();
			
			List<Double> list1 = new ArrayList<Double>();
			List<Double> list2 = new ArrayList<Double>();
			List<Double> list3 = new ArrayList<Double>();


			list1.add(4.2);
			list1.add(4.3);
			
			list2.add(3.1);
			list2.add(4.3);
			
			list3.add(1.1);
			list3.add(0.0);
			
			v1.set(list1);
			v2.set(list2);
			v3.set(list3);

			assertEquals(v3.get(), v1.sub(v2).get());	
			
		}
		@Test
		public void addTest() {
			
		Vector v1 = new VectorImpl();
		Vector v2 = new VectorImpl();
		Vector v3 = new VectorImpl();
		
		List<Double> list1 = new ArrayList<Double>();
		List<Double> list2 = new ArrayList<Double>();
		List<Double> list3 = new ArrayList<Double>();

		list1.add(2.2);
		list1.add(4.3);
		
		list2.add(3.3);
		list2.add(4.3);
		
		list3.add(5.5);
		list3.add(8.6);
		
		v1.set(list1);
		v2.set(list2);
		v3.set(list3);

		assertEquals(v3.get(), v1.add(v2).get());	
		}
		@Test(expected = IllegalArgumentException.class)
		public void setTest() {
			
		Vector v1 = new VectorImpl();
		
		List<Double> list1 = new ArrayList<Double>();

		v1.set(list1);
			
		}
		@Test(expected = IllegalArgumentException.class)
		public void vectorLengthTest() {
			
		Vector v1 = new VectorImpl();
		Vector v2 = new VectorImpl();
		
		List<Double> list1 = new ArrayList<Double>();
		List<Double> list2 = new ArrayList<Double>();

		list1.add(2.2);
		list1.add(4.3);
		
		list2.add(3.3);
		
		v1.set(list1);
		v2.set(list2);

		v1.add(v2);
		}
	}