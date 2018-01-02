// 
// Decompiled by Procyon v0.5.30
// 

package javaviewer;

import java.util.Date;
import java.text.SimpleDateFormat;

public class ELog
{
    private boolean _$168980;
    private SimpleDateFormat _$168986;
    
    public ELog(final boolean $168980) {
        this._$168980 = $168980;
    }
    
    public void print(final String s) {
        if (this._$168980) {
            this._$168986 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            System.out.print(this._$168986.format(new Date()));
            System.out.print("  ");
            System.out.println(s);
        }
    }
}
