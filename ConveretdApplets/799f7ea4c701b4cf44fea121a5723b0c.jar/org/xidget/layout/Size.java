// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.layout;

public class Size
{
    public int width;
    public int height;
    
    public Size() {
    }
    
    public Size(final int width, final int height) {
        this.width = width;
        this.height = height;
    }
    
    public Size(final String s) {
        this.parse(s);
    }
    
    public Size(final String s, final int width, final int height) {
        if (!this.parse(s)) {
            this.width = width;
            this.height = height;
        }
    }
    
    @Override
    public String toString() {
        return String.format("%d, %d", this.width, this.height);
    }
    
    public boolean parse(String trim) {
        try {
            if (trim == null) {
                return false;
            }
            trim = trim.trim();
            if (trim.length() == 0) {
                return false;
            }
            final int n = 0;
            final int index = trim.indexOf(44);
            if (index > 0) {
                this.width = Integer.parseInt(trim.substring(n, index).trim());
                this.height = Integer.parseInt(trim.substring(index + 1).trim());
                return true;
            }
        }
        catch (Exception ex) {}
        return false;
    }
}
