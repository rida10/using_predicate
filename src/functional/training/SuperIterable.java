package functional.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

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
	
	public SuperIterable<E> filter(Predicate<E> pred) {
		List<E> results = new ArrayList<>();
		
		for(E e: self) {
			if(pred.test(e)) {
				results.add(e);
			}
		}
		return new SuperIterable<>(results);
	}
	
	public void forEvery(Consumer<E> cons) {
		for (E e : self) {
			cons.accept(e);
		}
	}
	
	
	public static void main(String[] args) {
		//build SuperIterable<E>:
		SuperIterable<String> strings = new SuperIterable<String>(
				Arrays.asList("LightCoral", "pink", "Orange", "Gold", "plum", "Blue", "limeGreen"));
		
		//as strings is iterable => we are able to iterate on it:
		for(String s: strings) {
			System.out.println("> " + s);
		}
		Predicate<String> upperCasePredicate = (s) -> {
			if(Character.isUpperCase(s.charAt(0))) {
				return true;
			}
			return false;
		};
		
		Consumer<String> printValue = (s) -> {
			System.out.println("> " + s);
		};
		
		System.out.println("-------------------------------------------------");
		SuperIterable<String> upperCase = strings.filter(upperCasePredicate);
		
		for(String s: upperCase) {
			System.out.println("> " + s);
		}
		//other way of printing values
		upperCase.forEvery(s -> System.out.println("> " + s));
		
	}

}
