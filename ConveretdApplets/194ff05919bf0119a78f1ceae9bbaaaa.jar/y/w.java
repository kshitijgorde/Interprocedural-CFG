// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.util.Date;
import java.text.DateFormat;

public final class w
{
    private e a;
    private Object a;
    Throwable a;
    private long a;
    private DateFormat a;
    
    public w(final e a, final Object a2, final Throwable a3) {
        this.a = a;
        this.a = a2;
        this.a = a3;
        this.a = System.currentTimeMillis();
        this.a = DateFormat.getTimeInstance(1);
    }
    
    public final String toString() {
        final StringBuffer sb = new StringBuffer(this.a.toString());
        final Date date = new Date(this.a);
        sb.append(": ");
        sb.append(this.a.format(date));
        sb.append(": ");
        if (null != this.a) {
            sb.append(this.a.toString());
        }
        else {
            sb.append("null");
        }
        if (null != this.a) {
            sb.append(this.a.toString());
        }
        return sb.toString();
    }
}
