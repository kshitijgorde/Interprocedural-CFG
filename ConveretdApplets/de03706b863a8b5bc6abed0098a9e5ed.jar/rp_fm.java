import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.net.URI;
import java.net.CookieHandler;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_fm extends CookieHandler
{
    private rp_ad a;
    private rp_dx a;
    
    public rp_fm() {
        this(null, null);
    }
    
    private rp_fm(final rp_dx rp_dx, final rp_ad rp_ad) {
        this.a = null;
        this.a = rp_ad.a;
        this.a = new rp_dx();
    }
    
    public final Map get(final URI uri, final Map map) {
        if (uri == null || map == null) {
            throw new IllegalArgumentException("Argument is null");
        }
        final HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
        if (this.a == null) {
            return Collections.unmodifiableMap((Map<?, ?>)hashMap);
        }
        final ArrayList<rp_gh> list = new ArrayList<rp_gh>();
        for (final rp_gh rp_gh : this.a.a(uri)) {
            final String path = uri.getPath();
            final String e = rp_gh.e();
            final String s = path;
            if (path == e || (s != null && e != null && s.startsWith(e))) {
                list.add(rp_gh);
            }
        }
        hashMap.put("Cookie", a(list));
        return Collections.unmodifiableMap((Map<?, ?>)hashMap);
    }
    
    public final void put(final URI uri, final Map map) {
        if (uri == null || map == null) {
            throw new IllegalArgumentException("Argument is null");
        }
        if (this.a == null) {
            return;
        }
        final Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            final String s;
            if ((s = iterator.next()) != null && (s.equalsIgnoreCase("Set-Cookie2") || s.equalsIgnoreCase("Set-Cookie"))) {
                for (final String s2 : (List)map.get(s)) {
                    try {
                        for (final rp_gh rp_gh : rp_gh.a(s2)) {
                            if (this.a(uri, rp_gh)) {
                                this.a.a(uri, rp_gh);
                            }
                        }
                    }
                    catch (IllegalArgumentException ex) {}
                }
            }
        }
    }
    
    private boolean a(final URI uri, final rp_gh rp_gh) {
        try {
            return this.a.a(uri, rp_gh);
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    private static List a(final List list) {
        Collections.sort((List<Object>)list, new rp_fV());
        final ArrayList<String> list2 = new ArrayList<String>();
        for (final rp_gh rp_gh : list) {
            if (list.indexOf(rp_gh) == 0 && rp_gh.a() > 0) {
                list2.add("$Version=\"1\"");
            }
            list2.add(rp_gh.toString());
        }
        return list2;
    }
}
