// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.network.protocol;

import com.masystem.beergame.resource.Properties;

public class Costs
{
    private static int costStock;
    private static int costBacklog;
    private static int costPurchase;
    private static int profitSell;
    
    public static void init(final Properties properties) {
        Costs.costStock = properties.getIntegerProperty("cost.stock");
        Costs.costBacklog = properties.getIntegerProperty("cost.backlog");
        Costs.costPurchase = properties.getIntegerProperty("cost.purchase");
        Costs.profitSell = properties.getIntegerProperty("profit.sell");
    }
    
    public static int getStockCost(final int n) {
        if (n < 0) {
            return -n * Costs.costBacklog;
        }
        return n * Costs.costStock;
    }
    
    public static int getPurchaseCost(final int n) {
        return n * Costs.costPurchase;
    }
    
    public static int getSellRevenue(final int n) {
        return n * Costs.profitSell;
    }
}
