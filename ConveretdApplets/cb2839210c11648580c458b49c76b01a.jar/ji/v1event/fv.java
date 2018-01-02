// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1event;

import java.util.EventObject;

public class fv extends EventObject
{
    private int a;
    private long b;
    private int c;
    private boolean d;
    private int e;
    
    public String toString() {
        final String s = "jiPageEvent(";
        String s2 = null;
        switch (this.a) {
            case 1: {
                s2 = String.valueOf(String.valueOf(s)).concat("CHANGED");
                break;
            }
            case 2: {
                s2 = String.valueOf(String.valueOf(s)).concat("SELECTED");
                break;
            }
            case 3: {
                s2 = String.valueOf(String.valueOf(s)).concat("THUMB_CLICKED");
                break;
            }
            case 7: {
                s2 = String.valueOf(String.valueOf(s)).concat("THUMB_CLICKED_DURING_PAINT");
                break;
            }
            case 4: {
                s2 = String.valueOf(String.valueOf(s)).concat("SHOW_SELECTED");
                break;
            }
            case 5: {
                s2 = String.valueOf(String.valueOf(s)).concat("SINGLE_SELECT");
                break;
            }
            case 6: {
                s2 = String.valueOf(String.valueOf(s)).concat("MULTI_SELECT");
                break;
            }
            case 8: {
                s2 = String.valueOf(String.valueOf(s)).concat("TEXT_THUMB_CLICKED");
                break;
            }
            case 9: {
                s2 = String.valueOf(String.valueOf(s)).concat("SELECTED_ALWAYS");
                break;
            }
            case 10: {
                s2 = String.valueOf(String.valueOf(s)).concat("TEXT_FIND_CLICKED");
                break;
            }
            case 11: {
                s2 = String.valueOf(String.valueOf(s)).concat("NEWWINDOW_DBLCLICK");
                break;
            }
            default: {
                s2 = String.valueOf(String.valueOf(s)).concat("UNKNOWN");
                break;
            }
        }
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append(", ").append(this.c).append(", ").append(this.e).append(")")));
    }
    
    public fv(final Object o, final int a, final int c, final int e) {
        super(o);
        this.a = 0;
        this.b = 0L;
        this.c = 0;
        this.d = false;
        this.e = 0;
        this.a = a;
        this.c = c;
        this.e = e;
    }
    
    public fv(final Object o, final int a, final long b, final int e) {
        super(o);
        this.a = 0;
        this.b = 0L;
        this.c = 0;
        this.d = false;
        this.e = 0;
        this.a = a;
        this.b = b;
        this.e = e;
    }
    
    public long a() {
        return this.b;
    }
    
    public int b() {
        return this.a;
    }
    
    public int c() {
        return this.c;
    }
    
    public boolean d() {
        return this.d;
    }
    
    public int e() {
        return this.e;
    }
}
