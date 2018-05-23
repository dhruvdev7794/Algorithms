import java.util.HashMap;

public class LongestIncreasingSubsequence {
	public void lengthOfLIS(int[] nums) {
		if(nums.length == 0){
			System.out.println(0);
	    }
        HashMap<Integer, Integer> map = new HashMap<>(1);
        int max = 1;
        for(int i = 0 ; i < nums.length ; i++){
            map.put(nums[i], max);
            max = 1;
            for(int j = 0 ; j < i; j++){
                if(nums[i]>nums[j]){
                    max = Math.max(max, map.get(nums[j])+1);
                }
            }
            map.put(nums[i],  max);
        }
        max = 1;
        for (Integer value : map.values()) {
            if(max<value){
                max = value;
            }
        }

        System.out.println(max);
    }
    
    public static void main(String[] args) {
    		LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
    		int nums[] = {10,9,2,5,3,7,101,18};
    		lis.lengthOfLIS(nums); // Answer : 4 => [2, 3, 7, 101] OR [2, 3, 7, 18]
    }
}
