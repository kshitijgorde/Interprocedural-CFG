// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame;

public final class Config
{
    public static final String NETWORK_HOST;
    public static final ResultType RESULT_TYPE;
    
    static {
        NETWORK_HOST = null;
        RESULT_TYPE = ResultType.TRANSACTIONS_AND_STOCK;
    }
    
    public enum ResultType
    {
        TRANSACTIONS_AND_STOCK, 
        STOCK_ONLY;
    }
}
