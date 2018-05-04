public class ReverseString {
	static int reverse(int x) {
        int temp=0, prev_dig = 0; 
        int returnVal=0;
        while(x!=0){
            temp = x%10;
            returnVal = returnVal*10 + temp;
            if((returnVal-temp)/10!=prev_dig)
                return 0;
            prev_dig = returnVal;
            x=x/10;
        }
        return returnVal;
        
    }
    
    public static void main(String[] args){
    		int num = 12345;
    		System.out.println(reverse(num));
    		num = 1000000045;
    		System.out.println(reverse(num));
    }
}
