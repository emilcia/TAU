package zadanie;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.JUnit4;


	@RunWith(JUnit4.class)
	public class VectorTest {
		
		@Test
		public void Test() {
		
		Vector v1 = new VectorMockImpl();
		Vector v2 = new VectorMockImpl();
		Vector v3 = new VectorMockImpl();
		
		List<Double> list1 = new ArrayList<Double>();
		
		v1.set(list1);
		
		assertEquals(5, v1.addVectors(v2, v3));
			
		}
	}

