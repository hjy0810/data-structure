package leetcode.debug.dp;

import java.util.Arrays;

/**
 * @author:hjy
 * @date 2023/11/11
 */
public class coinChange322 {
    public static void main(String[] args) {
        int[] coins=new int[]{2};
        int amount=3;
        Solution solution = new Solution();
        int res = solution.coinChange(coins,amount);
        System.out.println(res);
    }
}

//class Solution {
//    public int coinChange(int[] coins, int amount) {
//        int[] dp=new int[amount+1];
//        dp[0]=0;
//        for(int i=1;i<=amount;i++){
//            int res = Integer.MAX_VALUE;
//            for(int j=0;j<coins.length;j++){
//                if(i-coins[j]>=0 && dp[i-coins[j]]<res){
//                    res=dp[i-coins[j]]+1;
//                }
//            }
//            dp[i]=res;
//        }
//        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
//    }
//}

class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp,amount+1);
        dp[0]=0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i-coins[j]>=0){
                    dp[i]=Math.min(dp[i],dp[i-coins[j]]);
                }
            }
        }
        return dp[amount]==(amount+1)?-1:dp[amount];
    }
}