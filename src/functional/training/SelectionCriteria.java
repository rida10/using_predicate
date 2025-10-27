package functional.training;

@FunctionalInterface
public interface SelectionCriteria<X> {

	boolean test(X obj);

	/**
	 * starting from java 8 , we are allowed to define static methods in interfaces
	 * complete with their implementations.
	 */
	/*
	 * static <X> SelectionCriteria<X> negate(SelectionCriteria<X> crit) { return x
	 * -> !crit.test(x); }
	 * 
	 * static <X> SelectionCriteria<X> andCrit(SelectionCriteria<X> crit1,
	 * SelectionCriteria<X> crit2) { return x -> crit1.test(x) && crit2.test(x); }
	 * 
	 * static <X> SelectionCriteria<X> orCriteria(SelectionCriteria<X> crit1,
	 * SelectionCriteria<X> crit2) { return x -> crit1.test(x) || crit2.test(x); }
	 */

	//in default methods no need for type variable <X> because it's the same for the type variable 
	//defined in the interface as a whole: public interface SelectionCriteria<X>
	//which is not the case for static versions
	default SelectionCriteria<X> negate() {
		return x -> !this.test(x);
	}

	default SelectionCriteria<X> andCrit( SelectionCriteria<X> crit2) {
		return x -> this.test(x) && crit2.test(x);
	}

	default SelectionCriteria<X> orCriteria( SelectionCriteria<X> crit2) {
		return x -> this.test(x) || crit2.test(x);
	}
}
