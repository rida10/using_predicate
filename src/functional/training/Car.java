package functional.training;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/*
 * @Author: rida safwan
 * testing 123
 * again
 */
public class Car {

	private final int gasLevel;

	private final String color;

	private final List<String> passengers;

	private final List<String> trunkContents;

	//private constructor
	private Car(int gasLevel, String color, List<String> passengers, List<String> trunkContents) {
		super();
		this.gasLevel = gasLevel;
		this.color = color;
		this.passengers = passengers;
		this.trunkContents = trunkContents;
	}
	
	//factory method
	public static Comparator<Car> getGasComparator() {
		return gasComparator;
	}
	
	public static Comparator<Car> gasComparator = (Car c1, Car c2)-> {
		return c1.gasLevel - c2.gasLevel;
	};
	
	
	/* before lambda expression
	 private static class CarGasComparator implements Comparator<Car> {
	 
	 @Override public int compare(Car c1, Car c2) { return c1.gasLevel -
	 c2.gasLevel; }
	 
	 }
	 */
	 
	
	// static factory method: from name we are conveying the meaning of the arguments.
	//this is one reason why people love static factories.
	//starting from java8 -> most apis are making exclusive use of static factories
	//instead of public constructors wiht some exceptions
	public static Car withGasColorPassengers(int gas, String color, String... passengers) {
		List<String> p = Collections.unmodifiableList(Arrays.asList(passengers));

		Car self = new Car(gas, color, p, null);
		return self;
	}

	// factory pattern: this factory is a singleton always return RED_CAR_CRITERION
	public static Predicate<Car> getRedCarCriterion() {
		return RED_CAR_CRITERION;
	}

	private static final Predicate<Car> RED_CAR_CRITERION = (c) -> {
		if (c.getColor() == "Red") {
			return true;
		}
		return false;
	};
	
	public static Predicate<Car> GAS_LEVEL_7 = (c) -> {
		if(c.gasLevel >=7) {
			return true;
		}
		return false;
	};
	
	// factory pattern: this factory is not a singleton
	public static Predicate<Car> getGasLevelCriterion(int gasLevel) {
		return (c) -> {
			if (c.getGasLevel() >= gasLevel) {
				return true;
			}
			return false;
		};
	}
	
	//it was like this:
	
	/*public GasLevelCriteria getGasLevelCrit(int threshold) {
		return new GasLevelCriteria(threshold);
	}
	
	private class GasLevelCriteria implements Predicate<Car> {
		private int threshold;
		
		public GasLevelCriteria(int gasLevel) {
			this.threshold = gasLevel;
		}

		@Override
		public boolean test(Car car) {
			if(car.getGasLevel() >= gasLevel) {
				return true;
			}
			return false;
		}
		
	}*/
	
	// factory pattern: this factory is not a singleton
	public static Predicate<Car> getColorCriterion(String... colors) {
		return (c) -> {
			for (String color : colors) {
				if (c.color != null && c.color.toLowerCase().equals(color.toLowerCase())) {
					return true;
				}
			}
			return false;
		};
	}

	public static Predicate<Car> moreThanTwoPassengersCriterion() {
		return (c) -> { return (c.getPassengers().size() > 2); };
	}

	public int getGasLevel() {
		return gasLevel;
	}

	public String getColor() {
		return color;
	}

	public List<String> getPassengers() {
		return passengers;
	}

	public List<String> getTrunkContents() {
		return trunkContents;
	}

	@Override
	public String toString() {
		return "Car {gasLevel=" + gasLevel + ", color=" + color + ", passengers=" + passengers + ", trunkContents="
				+ trunkContents + "}";
	}

}
