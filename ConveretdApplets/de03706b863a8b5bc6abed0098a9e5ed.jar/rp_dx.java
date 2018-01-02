import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.HashMap;
import java.util.ArrayList;
import java.net.URI;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Map;
import java.util.List;

// 
// Decompiled by Procyon v0.5.30
// 

public class rp_dx
{
    List a;
    Map a;
    Map b;
    ReentrantLock a;
    
    public void a(final URI uri, final rp_gh rp_gh) {
        if (rp_gh == null) {
            throw new NullPointerException("cookie is null");
        }
        this.a.lock();
        try {
            this.a.remove(rp_gh);
            if (rp_gh.a() != 0L) {
                this.a.add(rp_gh);
                if (rp_gh.d() != null) {
                    a(this.a, rp_gh.d(), rp_gh);
                }
                a(this.b, a(uri), rp_gh);
            }
        }
        finally {
            this.a.unlock();
        }
    }
    
    public List a(final URI uri) {
        if (uri == null) {
            throw new NullPointerException("uri is null");
        }
        final ArrayList list = new ArrayList();
        final boolean equalsIgnoreCase = "https".equalsIgnoreCase(uri.getScheme());
        this.a.lock();
        try {
            this.a(list, this.a, uri.getHost(), equalsIgnoreCase);
            this.a(list, this.b, a(uri), equalsIgnoreCase);
        }
        finally {
            this.a.unlock();
        }
        return list;
    }
    
    public rp_dx() {
        this.a = null;
        this.a = null;
        this.b = null;
        this.a = null;
        this.a = new ArrayList();
        this.a = new HashMap();
        this.b = new HashMap();
        this.a = new ReentrantLock(false);
    }
    
    void a(final List list, final Map map, final String s, final boolean b) {
        final ArrayList<rp_gh> list2 = new ArrayList<rp_gh>();
        final Iterator<Map.Entry<String, V>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            final Object o;
            final String s2 = ((Map.Entry<String, List<rp_gh>>)(o = iterator.next())).getKey();
            final List<rp_gh> list3;
            final Iterator<rp_gh> iterator2 = (list3 = ((Map.Entry<K, List<rp_gh>>)o).getValue()).iterator();
            while (iterator2.hasNext()) {
                final rp_gh rp_gh;
                Label_0327: {
                    if ((rp_gh = iterator2.next()).a() == 0) {
                        final String s3;
                        boolean b2;
                        if ((s3 = s2) == null || s == null) {
                            b2 = false;
                        }
                        else {
                            final boolean equalsIgnoreCase = ".local".equalsIgnoreCase(s3);
                            int n;
                            if ((n = s3.indexOf(46)) == 0) {
                                n = s3.indexOf(46, 1);
                            }
                            if (!equalsIgnoreCase && (n == -1 || n == s3.length() - 1)) {
                                b2 = false;
                            }
                            else if (s.indexOf(46) == -1 && equalsIgnoreCase) {
                                b2 = true;
                            }
                            else {
                                final int n2;
                                if ((n2 = s.length() - s3.length()) == 0) {
                                    b2 = s.equalsIgnoreCase(s3);
                                }
                                else if (n2 > 0) {
                                    s.substring(0, n2);
                                    b2 = s.substring(n2).equalsIgnoreCase(s3);
                                }
                                else {
                                    b2 = (n2 == -1 && (s3.charAt(0) == '.' && s.equalsIgnoreCase(s3.substring(1))));
                                }
                            }
                        }
                        if (b2) {
                            break Label_0327;
                        }
                    }
                    if (rp_gh.a() != 1 || !rp_gh.a(s2, s)) {
                        continue;
                    }
                }
                if (this.a.indexOf(rp_gh) != -1) {
                    if (!rp_gh.a()) {
                        if ((!b && rp_gh.b()) || list.contains(rp_gh)) {
                            continue;
                        }
                        list.add(rp_gh);
                    }
                    else {
                        list2.add(rp_gh);
                    }
                }
                else {
                    list2.add(rp_gh);
                }
            }
            for (final rp_gh rp_gh2 : list2) {
                list3.remove(rp_gh2);
                this.a.remove(rp_gh2);
            }
            list2.clear();
        }
    }
    
    void a(final List list, final Map map, final Comparable comparable, final boolean b) {
        for (final Object next : map.keySet()) {
            final List list2;
            if (comparable.compareTo(next) == 0 && (list2 = (List)map.get(next)) != null) {
                final Iterator<rp_gh> iterator2 = list2.iterator();
                while (iterator2.hasNext()) {
                    final rp_gh rp_gh = iterator2.next();
                    if (this.a.indexOf(rp_gh) != -1) {
                        if (!rp_gh.a()) {
                            if ((!b && rp_gh.b()) || list.contains(rp_gh)) {
                                continue;
                            }
                            list.add(rp_gh);
                        }
                        else {
                            iterator2.remove();
                            this.a.remove(rp_gh);
                        }
                    }
                    else {
                        iterator2.remove();
                    }
                }
            }
        }
    }
    
    static void a(final Map map, final Object o, final rp_gh rp_gh) {
        if (o != null) {
            final List<rp_gh> list;
            if ((list = map.get(o)) != null) {
                list.remove(rp_gh);
                list.add(rp_gh);
                return;
            }
            final ArrayList<rp_gh> list2;
            (list2 = new ArrayList<rp_gh>()).add(rp_gh);
            map.put(o, list2);
        }
    }
    
    static URI a(final URI uri) {
        URI uri2;
        try {
            uri2 = new URI("http", uri.getHost(), null, null, null);
        }
        catch (URISyntaxException ex) {
            uri2 = uri;
        }
        return uri2;
    }
}
