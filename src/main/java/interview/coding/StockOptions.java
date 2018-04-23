package interview.coding;

public class StockOptions {

    public long maxProfitUnlimitedOptionsUnlimitedInFlight(int[] prices) {
        int highestValueSoFar = Integer.MIN_VALUE;
        long profit = 0;

        for(int i=prices.length - 1; i >= 0; i--) {
            int currentPrice = prices[i];
            highestValueSoFar = Math.max(currentPrice, highestValueSoFar);
            if(highestValueSoFar > currentPrice) {
                profit += highestValueSoFar - currentPrice;
            }
        }

        return profit;
    }

    public long maxProfitUnlimitedOptionsUnlimitedInFlightFiveSecondsInBetween(int[] prices) {
        int highestValueSoFarAtLeastFiveAway = Integer.MIN_VALUE;
        long profit = 0;

        for(int i=prices.length - 6; i >= 0; i--) {
            int currentPrice = prices[i];
            highestValueSoFarAtLeastFiveAway = Math.max(prices[i+5], highestValueSoFarAtLeastFiveAway);
            if(highestValueSoFarAtLeastFiveAway > currentPrice) {
                profit += highestValueSoFarAtLeastFiveAway - currentPrice;
            }
        }

        return profit;
    }

    public long maxProfitOneBuyOneSell(int[] prices) {
        int highestValueSoFar = Integer.MIN_VALUE;
        long profit = 0;

        for(int i=prices.length - 1; i >= 0; i--) {
            int currentPrice = prices[i];
            highestValueSoFar = Math.max(currentPrice, highestValueSoFar);
            if(highestValueSoFar > currentPrice) {
                profit = Math.max(highestValueSoFar - currentPrice, profit);
            }
        }

        return profit;
    }

    public long maxProfitUnlimitedOptionsOneInFlight(int[] prices) {
        long maxProfit = 0;
        int localMinimum = Integer.MAX_VALUE;
        int localMaxium = Integer.MIN_VALUE;

        int i = 0;
        while(i < prices.length - 1) {
            int current = prices[i];

            // find local minimum
            while(current < localMinimum) {
                localMinimum = current;
                i++;

                if(i >= prices.length) {
                    return maxProfit;
                }

                current = prices[i];
            }

            while(current > localMaxium){
                localMaxium = current;
                i++;

                if(i >= prices.length) {
                    return maxProfit + (localMaxium - localMinimum);
                }

                current = prices[i];
            }

            maxProfit += localMaxium - localMinimum;
            localMinimum = Integer.MAX_VALUE;
            localMaxium = Integer.MIN_VALUE;
        }

        return maxProfit;
    }
}
