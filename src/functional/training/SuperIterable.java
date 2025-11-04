package functional.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
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
	
	//1- filter behavior
	public SuperIterable<E> filter(Predicate<E> pred) {
		List<E> results = new ArrayList<>();
		
		for(E e: self) {
			if(pred.test(e)) {
				results.add(e);
			}
		}
		return new SuperIterable<>(results);
	}
	
	//2- for every behavior (already provided by api using forEach that accepts a consumer)
	public void forEvery(Consumer<E> cons) {
		for (E e : self) {
			cons.accept(e);
		}
		
	}
	
	//3- map behavior that accepts Function<E, F> that may map original type of data to other
	//example printing passengers for each car if we are dealing with SuperIterable<Car>
	public <F> SuperIterable<F> map(Function<E, F> op) {
		List<F> results = new ArrayList<F>(); //list of other-type result
		
		//the forEach function accepts a consumer
		self.forEach( (e) -> {
			results.add(op.apply(e));
		});
		
		return new SuperIterable<F>(results);
	}
	
	//Testing
	public static void main(String[] args) {
		//build SuperIterable<E>:
		SuperIterable<String> colors = new SuperIterable<String>(
				Arrays.asList("LightCoral", "pink", "Orange", "Gold", "plum", "Blue", "limeGreen"));
		
		//as strings is iterable => we are able to iterate on it:
		System.out.println("----Printing all values of colors SuperIterable<String>---------------");
		var counter = 1;
		for(String s: colors) {
			System.out.println( counter++ +"> " + s);
		}
		
		System.out.println("---Other way of printing items of the SuperIterable<E>: is to use Consumer<E> passed "
				+ "to forEach:");
		//Consumer<E> passed 
		
		colors.forEach( (c) -> {
			System.out.println("color: " + c);
		});
		
		System.out.println("-------------------------------------------");
		
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
		SuperIterable<String> upperCase = colors.filter(upperCasePredicate);
		
		for(String s: upperCase) {
			System.out.println("> " + s);
		}
		
		//other way of printing values
	//	upperCase.forEvery(s -> System.out.println("> " + s));
		System.out.println("-----------------------------------------");
	//	strings.map( (x) -> x.toUpperCase()).forEach(printValue);
		
		
	}

}
