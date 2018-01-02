// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.graphs;

import java.awt.Color;
import java.awt.TextField;

class InputPanelItem
{
    static final int INT_RANGE_FIRST = 1;
    static final int REAL_RANGE_FIRST = 2;
    static final int INT_RANGE_SECOND = 3;
    static final int REAL_RANGE_SECOND = 4;
    static final int SINGLE_INT = 5;
    static final int SINGLE_REAL = 6;
    int type;
    TextField inputBox;
    String label;
    double min;
    double max;
    int x;
    int y;
    boolean firstMustBeSmaller;
    
    InputPanelItem(final int type, final String label, final double min, final double max) {
        this.firstMustBeSmaller = true;
        this.type = type;
        this.label = label;
        this.min = min;
        this.max = max;
        (this.inputBox = new TextField(10)).setBackground(Color.white);
    }
}
