import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

// define a filter interface
interface Filter {
    List<Integer> apply(List<Integer> numbers);
}

// filter to get even numbers
class EvenNumberFilter implements Filter {
    public List<Integer> apply(List<Integer> numbers) {
        List<Integer> evenNumbers = new ArrayList<>();
        
        // use a for loop to filter even numbers
        for (int n : numbers) {
            if (n % 2 == 0) {  // check if the number is even
                evenNumbers.add(n); // add to the result list
            }
        }
        
        return evenNumbers; // return the filtered list
    }
}

public class FilterPatternExample {
    public static void main(String[] args) {
        // lst of no.
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // create filter obj
        Filter evenFilter = new EvenNumberFilter();

        // apply filter to get only even numbers
        List<Integer> evenNumbers = evenFilter.apply(numbers);

        System.out.println("Even Numbers: " + evenNumbers);
    }
}