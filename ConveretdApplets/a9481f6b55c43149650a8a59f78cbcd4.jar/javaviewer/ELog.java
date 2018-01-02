// 
// Decompiled by Procyon v0.5.30
// 

package javaviewer;

import java.util.Date;
import java.text.SimpleDateFormat;

public class ELog
{
    private boolean _$1589;
    private SimpleDateFormat _$1591;
    
    public ELog(final boolean $1589) {
        this._$1589 = $1589;
    }
    
    public void print(final String s) {
        if (this._$1589) {
            this._$1591 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            System.out.print(this._$1591.format(new Date()));
            System.out.print("  ");
            System.out.println(s);
        }
    }
}
