// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1event;

import java.util.EventObject;

public class ob extends EventObject
{
    private int a;
    private String b;
    private boolean c;
    
    public String toString() {
        if (this.b == null) {
            return String.valueOf(String.valueOf(new StringBuffer("jiClipEvent(").append(this.a(this.a)).append("/").append(", ").append(this.getSource()).append(")")));
        }
        return String.valueOf(String.valueOf(new StringBuffer("jiClipEvent(").append(this.a(this.a)).append("/").append(this.b).append(", ").append(this.getSource()).append(")")));
    }
    
    private String a(final int n) {
        switch (n) {
            case 1: {
                return "COPY_TEXT_TO_CLIPBOARD";
            }
            case 2: {
                return "GET_TEXT_FROM_CLIPBOARD";
            }
            default: {
                return "Unknown";
            }
        }
    }
    
    public ob(final Object o, final int a) {
        super(o);
        this.a = 0;
        this.b = null;
        this.c = false;
        this.a = a;
    }
    
    public ob(final Object o, final int a, final String b) {
        super(o);
        this.a = 0;
        this.b = null;
        this.c = false;
        this.a = a;
        this.b = b;
    }
    
    public int a() {
        return this.a;
    }
    
    public String b() {
        return this.b;
    }
    
    public boolean c() {
        return this.c;
    }
}
