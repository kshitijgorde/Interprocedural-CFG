// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.layout;

public class Margins
{
    public int x0;
    public int y0;
    public int x1;
    public int y1;
    
    public Margins() {
    }
    
    public Margins(final int n) {
        this.x0 = n;
        this.y0 = n;
        this.x1 = n;
        this.y1 = n;
    }
    
    public Margins(final int x0, final int y0, final int x2, final int y2) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x2;
        this.y1 = y2;
    }
    
    public Margins(final String s) {
        this.parse(s);
    }
    
    public Margins(final String s, final int x0, final int y0, final int x2, final int y2) {
        if (!this.parse(s)) {
            this.x0 = x0;
            this.y0 = y0;
            this.x1 = x2;
            this.y1 = y2;
        }
    }
    
    @Override
    public String toString() {
        return String.format("%d, %d, %d, %d", this.x0, this.y0, this.x1, this.y1);
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
            if (index >= 0) {
                this.x0 = Integer.parseInt(trim.substring(n, index).trim());
                final int n2 = index + 1;
                final int index2 = trim.indexOf(44, n2);
                this.y0 = Integer.parseInt(trim.substring(n2, index2).trim());
                final int n3 = index2 + 1;
                final int index3 = trim.indexOf(44, n3);
                this.x1 = Integer.parseInt(trim.substring(n3, index3).trim());
                this.y1 = Integer.parseInt(trim.substring(index3 + 1).trim());
                return true;
            }
            final int int1 = Integer.parseInt(trim);
            this.y1 = int1;
            this.x1 = int1;
            this.y0 = int1;
            this.x0 = int1;
        }
        catch (Exception ex) {}
        return false;
    }
}
