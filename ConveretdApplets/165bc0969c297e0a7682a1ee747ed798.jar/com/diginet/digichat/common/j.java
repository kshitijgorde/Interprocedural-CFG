// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.common;

import com.diginet.digichat.util.a5;
import com.diginet.digichat.client.DigiChatAppletAbstract;
import com.esial.util.c;
import java.awt.Color;

public class j extends k
{
    public int a;
    public int b;
    public boolean c;
    public boolean d;
    public String e;
    public String f;
    public String strLong;
    public String strShort;
    public int nStar;
    public long lEntry;
    public Color clrName;
    public Color clrBack;
    
    public String timeStamp() {
        long n;
        if ((n = (System.currentTimeMillis() - this.lEntry) / 1000) < 0L) {
            n = 0L;
        }
        final StringBuffer sb = new StringBuffer();
        if (n > 59) {
            if (n > 3599) {
                if (n > 86399) {
                    sb.append(Integer.toString((int)(n / 86400)));
                    sb.append(' ');
                    sb.append(com.esial.util.c.a("days"));
                    sb.append(' ');
                    n %= 86400;
                }
                sb.append(Integer.toString((int)(n / 3600)));
                sb.append(' ');
                sb.append(com.esial.util.c.a("hours"));
                sb.append(' ');
                n %= 3600;
            }
            sb.append(Integer.toString((int)(n / 60)));
            sb.append(' ');
            sb.append(com.esial.util.c.a("minutes"));
            sb.append(' ');
            n %= 60;
        }
        sb.append(Integer.toString((int)n));
        sb.append(' ');
        sb.append(com.esial.util.c.a("seconds"));
        return sb.toString();
    }
    
    public String timeStamp(final String s) {
        return a5.a("%1 - %2: %3", new String[] { DigiChatAppletAbstract.OEM_DigiChat, com.esial.util.c.a(s), this.timeStamp() });
    }
    
    public j(final int n, final String s) {
        super(n, s);
        this.a = -999;
        this.b = -999;
        this.c = false;
        this.d = false;
        this.nStar = -1;
        this.lEntry = System.currentTimeMillis();
        final Color color = null;
        this.clrBack = color;
        this.clrName = color;
    }
}
