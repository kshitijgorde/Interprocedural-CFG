// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

class SashFormData
{
    long weight;
    
    String getName() {
        final String name = this.getClass().getName();
        final int lastIndex = name.lastIndexOf(46);
        if (lastIndex == -1) {
            return name;
        }
        return name.substring(lastIndex + 1, name.length());
    }
    
    public String toString() {
        return String.valueOf(this.getName()) + " {weight=" + this.weight + "}";
    }
}
