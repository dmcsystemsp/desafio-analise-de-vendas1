package entities;

import java.util.List;
import java.util.function.Predicate;

public class SaleServices {
	public double filteredSum(List<Sale> list, Predicate<Sale> criteria) {
		double sum = 0.0;
		for (Sale s : list) {
			if (criteria.test(s)) {
				
				 if (s.getMonth() == 1 || s.getMonth() ==7){
					 sum += s.getTotal();
				 }
				
			}
		}
		return sum;
	}
}
