// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui;

import java.util.EventListener;
import java.awt.AWTEventMulticaster;

public class TreeChangedEventMulticaster extends AWTEventMulticaster implements TreeChangedListener
{
    protected TreeChangedEventMulticaster(final TreeChangedListener treeChangedListener, final TreeChangedListener treeChangedListener2) {
        super(treeChangedListener, treeChangedListener2);
    }
    
    public static TreeChangedListener add(final TreeChangedListener treeChangedListener, final TreeChangedListener treeChangedListener2) {
        if (treeChangedListener == null) {
            return treeChangedListener2;
        }
        if (treeChangedListener2 == null) {
            return treeChangedListener;
        }
        return new TreeChangedEventMulticaster(treeChangedListener, treeChangedListener2);
    }
    
    public void onTreeChanged(final TreeChangedEvent treeChangedEvent) {
        ((TreeChangedListener)super.a).onTreeChanged(treeChangedEvent);
        ((TreeChangedListener)super.b).onTreeChanged(treeChangedEvent);
    }
    
    public static TreeChangedListener remove(final TreeChangedListener treeChangedListener, final TreeChangedListener treeChangedListener2) {
        return (TreeChangedListener)AWTEventMulticaster.removeInternal(treeChangedListener, treeChangedListener2);
    }
}
