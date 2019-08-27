import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BubbleSort {
    public void bubbleSort(List<Integer> numbers) {
        for(int i=0 ; i<numbers.size() ; i++) {
            for(int j=i ; j<numbers.size() ; j++) {
                if(numbers.get(j) < numbers.get(i)){
                    swap(numbers, i, j);
                }
            }
        }
    }

    public void swap(List<Integer> numbers, int i, int j) {
        int temp = numbers.get(j);
        numbers.set(j, numbers.get(i));
        numbers.set(i, temp);
    }

    public static void main(String[] args) {
        BubbleSort bubbleSorting = new BubbleSort();
        int max = 100;
        Random random = new Random();
        List<Integer> intList = new ArrayList<>();
        for(int i=0 ; i<10 ; i++) {
            intList.add(random.nextInt(max));
        }
        System.out.println(intList);
        bubbleSorting.bubbleSort(intList);
        System.out.println(intList);
    }
}