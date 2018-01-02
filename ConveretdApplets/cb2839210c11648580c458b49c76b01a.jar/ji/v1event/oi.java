// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1event;

import ji.graphic.cv;
import java.util.EventObject;

public class oi extends EventObject
{
    int a;
    boolean b;
    int c;
    
    public String toString() {
        return String.valueOf(String.valueOf(new StringBuffer("jiToolbarEvent(").append(this.a(this.a)).append(", ").append(this.c).append(", ").append(((cv)this.getSource()).a()).append(")")));
    }
    
    private String a(final int n) {
        switch (n) {
            case 1: {
                return "FOLD";
            }
            case 2: {
                return "UNFOLD";
            }
            case 3: {
                return "EXPAND";
            }
            case 4: {
                return "COLLAPSE";
            }
            case 5: {
                return "COLLAPSE_IMMEDIATE";
            }
            default: {
                return "Unknown";
            }
        }
    }
    
    public oi(final cv cv, final int a, final int c) {
        super(cv);
        this.a = 0;
        this.b = false;
        this.c = 0;
        this.a = a;
        this.c = c;
    }
    
    public int a() {
        return this.a;
    }
    
    public int b() {
        return this.c;
    }
}
