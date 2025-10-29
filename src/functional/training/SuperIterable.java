package functional.training;

import java.util.Arrays;
import java.util.Iterator;

/*
 * Here we are wrapping an iterable in another iterable
 */
public class SuperIterable<E> implements Iterable<E>{

	private Iterable<E> self;
	
	public SuperIterable(Iterable s) {
		self = s;
	}
	
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return self.iterator();
	}
	
	
	public static void main(String[] args) {
		//build SuperIterable<E>:
		SuperIterable<String> strings = new SuperIterable<String>(
				Arrays.asList("LightCoral", "pink", "Orange", "Gold", "plum", "Blue", "LimeGreen"));
		
		//as strings is iterable => we are able to iterate on it:
		for(String s: strings) {
			System.out.println("> " + s);
		}
	}

}
