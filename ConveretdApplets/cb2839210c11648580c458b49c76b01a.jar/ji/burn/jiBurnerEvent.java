// 
// Decompiled by Procyon v0.5.30
// 

package ji.burn;

import ji.util.d;
import java.util.EventObject;

public class jiBurnerEvent extends EventObject
{
    String a;
    int b;
    
    public jiBurnerEvent(final Object o, final String a, final int b) {
        super(o);
        this.a = "";
        this.b = 0;
        this.a = a;
        this.b = b;
    }
    
    public final String getText() {
        return this.a;
    }
    
    public final int getPercent() {
        return this.b;
    }
    
    public final String toString() {
        String a = this.a;
        if (d.by(a)) {
            a = "";
        }
        return String.valueOf(String.valueOf(new StringBuffer("jiBurnerEvent(\"").append(a).append("\"/percent=").append(this.b).append(")")));
    }
    
    public final Object getOject() {
        return null;
    }
    
    public final void releaseResources() {
    }
}
