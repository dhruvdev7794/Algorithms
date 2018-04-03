public class Sorting {
	
	// insertion Sort
	public void insertionSort(int arr[]) {
		int n = arr.length;
		for(int i = 1 ; i < n ; ++i) {
			int key = arr[i];
			int j = i-1;
			
			while(j >= 0 && arr[j] > key) {
				arr[j+1] = arr[j];
				j = j-1;
			}
			arr[j+1] = key;
		}
	}
	
	// Selection Sort
	public void selectionSort(int arr[]) {
		int min_key = 0;
		int j = 0;
		while (arr.length != j) {
			for(int i = j+1 ; i < arr.length ; i++) {
				if(arr[i] < arr[min_key])
					min_key = i;
			}
			int temp = arr[min_key];
			arr[min_key] = arr[j];
			arr[j] = temp;
			
			j += 1;
			min_key = j;
		}
	}
	
	// Merge Sort
	public void mergeSort(int arr[], int l, int r) {
		if (l < r) {
			int m = (l+r)/2;
			mergeSort(arr, l , m);
			mergeSort(arr, m+1, r);
			merge(arr, l, m, r);
		}
	}
	
	public void merge(int arr[], int l, int m, int r) {
		// size of two arrays
		int n1 = m - l + 1;
		int n2 = r - m;
		// create left and right arrays
		int lArr[] = new int[n1];
		int rArr[] = new int[n2];
		for(int i = 0 ; i < n1 ; i++) {
			lArr[i] = arr[l+i];
		}
		for (int i = 0; i< n2 ; i ++) {
			rArr[i] = arr[m + i + 1];
		}
		
		int i = 0 , j = 0;
		int k = l;
		
		while (i < n1 && j < n2) {
			if (lArr[i]<=rArr[j]) {
				arr[k] = lArr[i];
				i += 1;
			}
			else {
				arr[k] = rArr[j];
				j += 1;
			}
			k += 1;
		}
		// Copy remaining stuff
		// from left array
		while (i < n1) {
			arr[k] = lArr[i];
			i += 1;
			k += 1;
		}
		// from right array
		while (j < n2) {
			arr[k] = rArr[j];
			j += 1;
			k += 1;
		}
		
	}
	
	
	static void printArr(int arr[]) {
		for (int i = 0 ; i < arr.length ; ++i) {
			System.out.print(arr[i]+"  ");
		}
		System.out.println();
	}
	
	
	
	public static void main(String[] args) {
		int arr1[] = {12, 54, 13, 9, 56, 23};
		Sorting insertionSort = new Sorting();
		insertionSort.insertionSort(arr1);
		printArr(arr1);
		
		int arr2[] = {12, 54, 13, 9, 56, 23, 45, 12};
		Sorting selectionSort = new Sorting();
		selectionSort.selectionSort(arr2);
		printArr(arr2);
		
		int arr3[] = {12, 54, 13, 9, 56, 23, 99, 23, 43};
		Sorting mergeSort = new Sorting();
		mergeSort.mergeSort(arr3, 0, arr3.length-1);
		printArr(arr3);
	}
	
//  9  12  13  23  54  56  
// 	9  12  12  13  23  45  54  56  
// 	9  12  13  23  23  43  54  56  99  

}
