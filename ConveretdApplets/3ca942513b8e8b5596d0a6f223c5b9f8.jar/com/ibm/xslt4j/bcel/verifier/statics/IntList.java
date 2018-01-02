// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.verifier.statics;

import java.util.ArrayList;

public class IntList
{
    private ArrayList theList;
    
    IntList() {
        this.theList = new ArrayList();
    }
    
    void add(final int i) {
        this.theList.add(new Integer(i));
    }
    
    boolean contains(final int i) {
        final Integer[] ints = new Integer[this.theList.size()];
        this.theList.toArray(ints);
        for (int j = 0; j < ints.length; ++j) {
            if (i == ints[j]) {
                return true;
            }
        }
        return false;
    }
}
