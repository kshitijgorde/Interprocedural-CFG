// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import java.util.Vector;

class Ruler
{
    private Vector tabs;
    
    Ruler() {
        this.tabs = new Vector();
    }
    
    public void setTab(final int n, final int n2) {
        if (n >= this.tabs.size()) {
            this.tabs.add(n, new Integer(n2));
        }
        else {
            final int n3 = n2 - this.getTab(n);
            if (n3 > 0) {
                for (int i = n; i < this.tabs.size(); ++i) {
                    this.tabs.set(i, new Integer(this.getTab(i) + n3));
                }
            }
        }
    }
    
    public int getTab(final int n) {
        return this.tabs.get(n);
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(this.getClass().getName() + " {");
        for (int i = 0; i < this.tabs.size(); ++i) {
            sb.append(this.tabs.get(i));
            if (i < this.tabs.size() - 1) {
                sb.append(',');
            }
        }
        sb.append('}');
        return sb.toString();
    }
    
    public static void main(final String[] array) {
        final Ruler ruler = new Ruler();
        ruler.setTab(0, 10);
        ruler.setTab(1, 20);
        ruler.setTab(2, 30);
        System.out.println(ruler);
        ruler.setTab(1, 25);
        System.out.println(ruler);
        System.out.println(ruler.getTab(0));
    }
}
