// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.HttpMapClient;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class o
{
    private long a;
    private String b;
    private Map c;
    
    public o(final String b, final long a, final Map c) {
        this.b = b;
        this.a = a;
        this.c = c;
    }
    
    public final String toString() {
        return "Last time: " + this.a + ", agency: " + this.b + ", other parameters: " + this.c + ".";
    }
    
    final String a() {
        final StringBuffer sb = new StringBuffer();
        final String b;
        if ((b = this.b) != null) {
            sb.append("a=" + COM.NextBus.HttpMapClient.c.a(b) + "&");
        }
        sb.append("t=" + COM.NextBus.HttpMapClient.c.a(Long.toString(this.a)));
        final Map c;
        if ((c = this.c) != null) {
            for (final String s : c.keySet()) {
                final List list = (List)c.get(s);
                final String a = COM.NextBus.HttpMapClient.c.a(s);
                final Iterator<String> iterator2 = list.iterator();
                while (iterator2.hasNext()) {
                    final String s2;
                    if ((s2 = iterator2.next()) != null) {
                        sb.append("&" + a + "=" + COM.NextBus.HttpMapClient.c.a(s2));
                    }
                }
            }
        }
        return sb.toString();
    }
}
