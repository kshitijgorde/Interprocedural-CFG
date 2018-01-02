// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1event;

import java.util.EventObject;

public class fz extends EventObject
{
    private int a;
    
    public fz(final Object o, final int a) {
        super(o);
        this.a = a;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer("jiNavigationEvent(");
        switch (this.a) {
            case 0: {
                sb.append("FIRST");
                break;
            }
            case 1: {
                sb.append("PREVIOUS");
                break;
            }
            case 2: {
                sb.append("NEXT");
                break;
            }
            case 3: {
                sb.append("LAST");
                break;
            }
            default: {
                sb.append("UNKNOWN");
                break;
            }
        }
        sb.append(")");
        return sb.toString();
    }
    
    public int a() {
        return this.a;
    }
}
