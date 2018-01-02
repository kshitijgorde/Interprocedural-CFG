import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.net.URI;
import java.net.CookieHandler;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_eR extends CookieHandler
{
    private /* synthetic */ CookieHandler a;
    
    rp_eR(final rp_dh rp_dh, final CookieHandler a) {
        this.a = a;
    }
    
    public final Map get(final URI uri, final Map map) {
        return this.a.get(uri, map);
    }
    
    public final void put(final URI uri, final Map map) {
        final HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
        if (map != null) {
            hashMap.putAll(map);
            final List<String> list;
            if ((list = hashMap.get("Set-Cookie")) != null) {
                final ArrayList<String> list2 = new ArrayList<String>();
                final Iterator<String> iterator = list.iterator();
                while (iterator.hasNext()) {
                    list2.add(iterator.next().replace("; HttpOnly", ""));
                }
                hashMap.put("Set-Cookie", list2);
                this.a.put(uri, (Map<String, List<String>>)hashMap);
            }
        }
    }
}
