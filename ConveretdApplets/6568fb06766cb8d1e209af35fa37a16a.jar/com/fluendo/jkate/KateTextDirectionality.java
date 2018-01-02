// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jkate;

public class KateTextDirectionality
{
    public static final KateTextDirectionality kate_l2r_t2b;
    public static final KateTextDirectionality kate_r2l_t2b;
    public static final KateTextDirectionality kate_t2b_r2l;
    public static final KateTextDirectionality kate_t2b_l2r;
    private static final KateTextDirectionality[] list;
    
    public static KateTextDirectionality CreateTextDirectionality(final int n) throws KateException {
        if (n < 0 || n >= KateTextDirectionality.list.length) {
            throw new KateException("Text directionality " + n + " out of bounds");
        }
        return KateTextDirectionality.list[n];
    }
    
    static {
        kate_l2r_t2b = new KateTextDirectionality();
        kate_r2l_t2b = new KateTextDirectionality();
        kate_t2b_r2l = new KateTextDirectionality();
        kate_t2b_l2r = new KateTextDirectionality();
        list = new KateTextDirectionality[] { KateTextDirectionality.kate_l2r_t2b, KateTextDirectionality.kate_r2l_t2b, KateTextDirectionality.kate_t2b_r2l, KateTextDirectionality.kate_t2b_l2r };
    }
}
