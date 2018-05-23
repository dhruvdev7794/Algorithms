import java.util.Arrays;

public class CoinChange {
	public void coinChangeSol(int[] coins, int amount) {
        int max = amount + 1;             
        int[] dp = new int[amount + 1];  
        Arrays.fill(dp, max);  
        dp[0] = 0;   
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        System.out.println(dp[amount] > amount ? -1 : dp[amount]);
    }
	
	public static void main(String[] args) {
		CoinChange coinChange = new CoinChange();
		int [] coins = {1, 2, 5};
		int amount = 11;
		coinChange.coinChangeSol(coins, amount); // Answer => 3 :[5+5+1]
		
		coins = new int[2];
		amount = 3;
		coinChange.coinChangeSol(coins, amount); // Answer => -1 : no coin
	}
}
