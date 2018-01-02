// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1event;

import java.util.EventObject;

public class n4 extends EventObject
{
    private int a;
    private boolean b;
    
    public String toString() {
        final String s = "jiDocumentEvent(";
        String s2 = null;
        switch (this.a) {
            case 10: {
                s2 = String.valueOf(String.valueOf(s)).concat("PAGE_COUNT_CHANGED");
                break;
            }
            case 11: {
                s2 = String.valueOf(String.valueOf(s)).concat("PAGE_COUNT_LAST_PAGE");
                break;
            }
            default: {
                s2 = String.valueOf(String.valueOf(s)).concat("UNKNOWN");
                break;
            }
        }
        return String.valueOf(String.valueOf(s2)).concat(")");
    }
    
    public n4(final Object o, final int a) {
        super(o);
        this.a = 0;
        this.b = false;
        this.a = a;
    }
    
    public int a() {
        return this.a;
    }
}
