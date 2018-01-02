// 
// Decompiled by Procyon v0.5.30
// 

package com.magelang.tabsplitter;

import java.awt.Component;
import java.util.EventObject;

public class TabSelectionEvent extends EventObject
{
    private Object visibleComponent;
    private int physicalTab;
    private String selectedName;
    private int[] visibleComponentNum;
    
    public int[] visibleComponentNum() {
        return this.visibleComponentNum;
    }
    
    public String toString() {
        final String property = System.getProperty("line.separator");
        String s = "";
        for (int i = 0; i < this.visibleComponentNum.length; ++i) {
            if (i == 0) {
                s = String.valueOf(s) + this.visibleComponentNum[i];
            }
            else {
                s = String.valueOf(s) + "," + this.visibleComponentNum[i];
            }
        }
        String s2 = "";
        String s3;
        if (this.visibleComponent instanceof Component) {
            s3 = this.visibleComponent.toString();
        }
        else {
            for (int j = 0; j < ((Object[])this.visibleComponent).length; ++j) {
                if (j == 0) {
                    s2 = String.valueOf(s2) + ((Object[])this.visibleComponent)[j];
                }
                else {
                    s2 = String.valueOf(s2) + "," + property + ((Object[])this.visibleComponent)[j];
                }
            }
            s3 = String.valueOf("{") + s2 + "}";
        }
        return String.valueOf(this.getClass().getName()) + " [" + this.physicalTab + ", \"" + this.selectedName + "\", {" + s + "}, " + property + s3 + "]";
    }
    
    public TabSelectionEvent(final Object o, final Object visibleComponent, final int physicalTab, final String selectedName, final int[] visibleComponentNum) {
        super(o);
        this.visibleComponent = visibleComponent;
        this.physicalTab = physicalTab;
        this.selectedName = selectedName;
        this.visibleComponentNum = visibleComponentNum;
    }
    
    public int getPhysicalTab() {
        return this.physicalTab;
    }
    
    public String getSelectedName() {
        return this.selectedName;
    }
    
    public Object getVisibleComponent() {
        return this.visibleComponent;
    }
}
