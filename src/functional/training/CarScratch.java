package functional.training;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

public class CarScratch {
	
	
	public static <E> ToIntFunction<E> compareWith(E target, Comparator<E> compare) {
		return (x) -> {
			return compare.compare(target, x);
		};
	}
	public class DateCriteria implements SelectionCriteria<LocalDate> {

		@Override
		public boolean test(LocalDate c) {
			return false;
		}
		
	}

	public static <X> void showAll(List<X> list) {

		int counter = 1;
		for (X obj : list) {
			System.out.print(counter + "- ");
			System.out.println(obj);
			System.out.println("------------------------------------------");
			++counter;
		}

	}
	//where X before return type is type variable enclosed between <>
	public static <X> List<X> getByCriterion(List<X> list, Predicate<X> crit) {
		List<X> selected = new ArrayList<X>();
		
		for (X obj : list) {
			if(crit.test(obj)) {
				selected.add(obj);
			}
		}
		return selected;
	}

	public static void main(String[] args) {

		List<Car> cars = Arrays.asList(Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"),
				Car.withGasColorPassengers(3, "Octarine", "Rincewind", "Ridcully"),
				Car.withGasColorPassengers(9, "Black", "Weatherwax", "Margrat"),
				Car.withGasColorPassengers(7, "Green", "Valentine", "Gillian", "Anne", "Dr. Mahmoud"),
				Car.withGasColorPassengers(6, "Red", "Ender", "Hyrum", "Bonzo"));

	//	showAll(cars); //-->done
		//showing red cars only
//		showAll(selectByCriterion(cars, Car.getRedCarCriterion())); //-->done
		//show cars with gas level 6 and above:
//		showAll(selectByCriterion(cars, Car.getGasLevelCriterion(6)));
		
		LocalDate today = LocalDate.now();
		
		List<LocalDate> dates = Arrays.asList(today, today.plusDays(1), today.plusDays(7), 
				today.minusDays(1));
		
		System.out.println("date today: " + today);
		
		System.out.println("Show all dates:");
	//	showAll(dates);
		
		//System.out.println("-------------------------------------");
		//System.out.println("Show dates greater than today only:");
		//showAll(getByCriterion(dates, (date)-> { return(date.isAfter(today)); }) );
		
		List<String> colors = Arrays.asList("LightCoral", "pink", "Orange", "Gold", "plum", "Blue", "LimeGreen");
	//	System.out.println("Show colors starting with capital letters: ");
		//showAll(getByCriterion(colors, (st)-> {return Character.isUpperCase(st.charAt(0)); }));
		
		//showAll(getByCriterion(cars, Car.getColorCriterion("Red", "Black", "Indigo")));
	/*	System.out.println("Cars before sorting according to passengers count: ");
		showAll(cars);
		
		System.out.println("---------------------------------------------");
		System.out.println("After sorting according to car's passengers count:");
		
		cars.sort(new PassengerCountOrder());
		showAll(cars);*/
		
		
		Predicate<Car> gasLevel7 = Car.GAS_LEVEL_7;
		Predicate<Car> twoPassengersCrit = Car.moreThanTwoPassengersCriterion();
		Predicate<Car> redCarCriteria = Car.getRedCarCriterion();
		
		Predicate<Car> notGasLevel7 = gasLevel7.negate();
		
		//combining behaviors: AND
		Predicate<Car> andCrit = gasLevel7.and(twoPassengersCrit);
		
		// showAll(getByCriterion(cars, andCrit));
		
		//combining behaviors: OR
		Predicate<Car> orCombination = redCarCriteria.or(gasLevel7);
	//	showAll(getByCriterion(cars, orCombination));
		
		Car bert = Car.withGasColorPassengers(5, "Blue");
		
		ToIntFunction<Car> compareWithBert = compareWith(bert, Car.gasComparator);
		
		for (Car car : cars) {
			System.out.println("comparing Car " + car + " with bert car: " + compareWithBert.applyAsInt(car));
		}
		
		
	}

}
