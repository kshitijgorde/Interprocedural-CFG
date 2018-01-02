// 
// Decompiled by Procyon v0.5.30
// 

package org.joni.constants;

public interface Reduce
{
    public static final ReduceType[][] REDUCE_TABLE = { { ReduceType.DEL, ReduceType.A, ReduceType.A, ReduceType.QQ, ReduceType.AQ, ReduceType.ASIS }, { ReduceType.DEL, ReduceType.DEL, ReduceType.DEL, ReduceType.P_QQ, ReduceType.P_QQ, ReduceType.DEL }, { ReduceType.A, ReduceType.A, ReduceType.DEL, ReduceType.ASIS, ReduceType.P_QQ, ReduceType.DEL }, { ReduceType.DEL, ReduceType.AQ, ReduceType.AQ, ReduceType.DEL, ReduceType.AQ, ReduceType.AQ }, { ReduceType.DEL, ReduceType.DEL, ReduceType.DEL, ReduceType.DEL, ReduceType.DEL, ReduceType.DEL }, { ReduceType.ASIS, ReduceType.PQ_Q, ReduceType.DEL, ReduceType.AQ, ReduceType.AQ, ReduceType.DEL } };
    public static final String[] PopularQStr = { "?", "*", "+", "??", "*?", "+?" };
    public static final String[] ReduceQStr = { "", "", "*", "*?", "??", "+ and ??", "+? and ?" };
    
    public enum ReduceType
    {
        ASIS, 
        DEL, 
        A, 
        AQ, 
        QQ, 
        P_QQ, 
        PQ_Q;
    }
}
