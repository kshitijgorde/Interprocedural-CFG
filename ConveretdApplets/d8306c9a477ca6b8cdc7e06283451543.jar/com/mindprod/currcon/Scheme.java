// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.currcon;

import com.mindprod.common11.Build;
import java.awt.Color;

public final class Scheme
{
    static final boolean DEBUGGING = false;
    static final Color BACKGROUND_FOR_APPLET;
    static final Color BACKGROUND_FOR_COMPACT_AMOUNT;
    static final Color BACKGROUND_FOR_CURRENCY_CODE;
    static final Color BACKGROUND_FOR_CURRENCY_NAME;
    static final Color BACKGROUND_FOR_CURRENCY_SELECTOR;
    static final Color BACKGROUND_FOR_INVALID;
    static final Color BACKGROUND_FOR_PRECISE_AMOUNT;
    static final Color FOREGROUND_FOR_COMPACT_AMOUNT;
    static final Color FOREGROUND_FOR_CURRENCY_CODE;
    static final Color FOREGROUND_FOR_CURRENCY_NAME;
    static final Color FOREGROUND_FOR_CURRENCY_SELECTOR;
    static final Color FOREGROUND_FOR_INVALID;
    static final Color FOREGROUND_FOR_PRECISE_AMOUNT;
    
    static {
        BACKGROUND_FOR_APPLET = Build.BACKGROUND_FOR_BLENDING;
        BACKGROUND_FOR_COMPACT_AMOUNT = new Color(16187382);
        BACKGROUND_FOR_CURRENCY_CODE = new Color(12964831);
        BACKGROUND_FOR_CURRENCY_NAME = Color.white;
        BACKGROUND_FOR_CURRENCY_SELECTOR = Color.white;
        BACKGROUND_FOR_INVALID = Color.yellow;
        BACKGROUND_FOR_PRECISE_AMOUNT = Build.BACKGROUND_FOR_BLENDING;
        FOREGROUND_FOR_COMPACT_AMOUNT = new Color(1644912);
        FOREGROUND_FOR_CURRENCY_CODE = new Color(9140753);
        FOREGROUND_FOR_CURRENCY_NAME = new Color(9140753);
        FOREGROUND_FOR_CURRENCY_SELECTOR = new Color(9140753);
        FOREGROUND_FOR_INVALID = Color.RED;
        FOREGROUND_FOR_PRECISE_AMOUNT = new Color(1644912);
    }
}
