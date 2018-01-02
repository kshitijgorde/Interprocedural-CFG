// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.HttpMapClient;

import java.net.MalformedURLException;
import java.net.URLConnection;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.util.Date;
import java.text.SimpleDateFormat;
import COM.NextBus.AdminMap.O;
import java.util.Hashtable;
import COM.NextBus.Predictor2Comm.BusReport;
import java.util.Set;
import COM.NextBus.Predictor2Comm.PathInfo;
import java.util.ArrayList;
import COM.NextBus.Predictor2Comm.RouteInfoBase;
import COM.NextBus.Predictor2Comm.RouteInfo;
import java.util.Enumeration;
import COM.NextBus.DBModel.AdherenceRange;
import COM.NextBus.Predictor2Comm.TimeReport;
import COM.NextBus.Predictor2Comm.TitleInfo;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Random;
import java.util.Map;
import java.util.List;

public final class a
{
    private static String f;
    private static String g;
    private final String h;
    private final boolean i;
    private final String j;
    private final List k;
    private final Map l;
    private final int m;
    private final int n;
    private String o;
    static int a;
    static int b;
    static int c;
    static int d;
    static Random e;
    
    public a(final String h, final boolean i, final String o, final List k, final int m, final int n) {
        this.l = new HashMap();
        this.h = h;
        this.i = i;
        this.j = "Connection";
        this.o = o;
        this.m = m;
        this.n = n;
        this.k = k;
        final Iterator<String> iterator = k.iterator();
        while (iterator.hasNext()) {
            this.l.put(iterator.next(), new j());
        }
    }
    
    private j e(final String s) {
        return this.l.get(s);
    }
    
    public final e a(final String s) {
        final j e;
        if ((e = this.e(s)) == null) {
            return null;
        }
        return e.a();
    }
    
    public final List b(final String s) {
        return this.e(s).a().b();
    }
    
    public final void a() {
        final k k = new k();
        for (final String s : this.k) {
            k.a(new o(s, this.e(s).b(), null));
        }
        final Enumeration a = this.a(COM.NextBus.HttpMapClient.a.f, "/prepare", k).a();
        while (a.hasMoreElements()) {
            final ResponseComponent responseComponent = a.nextElement();
            final j e;
            (e = this.e(responseComponent.a())).c();
            final int c;
            if ((c = responseComponent.c()) != 0) {
                throw new ConnectionException(c, "Server generated error response to prepare request: " + (String)responseComponent.d());
            }
            final ResponseComponent responseComponent2 = responseComponent;
            new e();
            final List list;
            e.a(new e((int)list.get(8), (List)(list = (List)responseComponent2.d()).get(0), list.get(1), list.get(4), (TimeReport)list.get(2), (List)list.get(3), (AdherenceRange)list.get(5), (List)list.get(6), (boolean)list.get(7), (double)list.get(9), (double)list.get(10), (double)list.get(11), (double)list.get(12), (String)list.get(13), (double)list.get(14), (Map)list.get(15), (boolean)list.get(16)));
        }
    }
    
    public final RouteInfo a(final String s, final String s2) {
        return this.e(s).b.get(s2);
    }
    
    public final void c(String s) {
        final k k = new k();
        final HashMap hashMap = new HashMap();
        final j e = this.e(s);
        k.a(new o(s, e.b(), hashMap));
        final Enumeration a = this.a(COM.NextBus.HttpMapClient.a.f, "/routeInfoBase", k).a();
        while (a.hasMoreElements()) {
            final ResponseComponent responseComponent;
            final int c;
            if ((c = (responseComponent = a.nextElement()).c()) != 0) {
                s = (String)responseComponent.d();
                throw new ConnectionException(c, "Server generated error response to route info request: " + s);
            }
            e.a = ((List)responseComponent.d()).get(0);
        }
    }
    
    public final RouteInfoBase b(final String s, final String s2) {
        return this.e(s).a.get(s2);
    }
    
    public final RouteInfo c(String s, final String s2) {
        final j e;
        final RouteInfo routeInfo;
        if ((routeInfo = (e = this.e(s)).b.get(s2)) != null) {
            return routeInfo;
        }
        final a a = this;
        s = s;
        this = a;
        final HashMap<String, ArrayList> hashMap = new HashMap<String, ArrayList>();
        final ArrayList list;
        (list = new ArrayList()).add(s2);
        hashMap.put("r", list);
        final j e2 = this.e(s);
        final k k;
        (k = new k()).a(new o(s, e2.b(), hashMap));
        final ResponseComponent responseComponent;
        final int c;
        if ((c = (responseComponent = this.a(COM.NextBus.HttpMapClient.a.f, "/singleRouteInfo", k).a().nextElement()).c()) == 0) {
            final List list2;
            e2.b.put(s2, (list2 = (List)responseComponent.d()).get(0));
            Object o;
            for (final String s3 : ((Map<String, V>)(o = list2.get(1))).keySet()) {
                e2.c.put(s3, ((Map<K, PathInfo>)o).get((Object)s3));
            }
            return e.b.get(s2);
        }
        throw new ConnectionException(c, (String)responseComponent.d());
    }
    
    public final PathInfo d(final String s, final String s2) {
        return this.e(s).c.get(s2);
    }
    
    public final List d(final String s) {
        final k k = new k();
        for (final String s2 : this.k) {
            final HashMap<String, ArrayList> hashMap = new HashMap<String, ArrayList>();
            final ArrayList list;
            (list = new ArrayList()).add(s);
            hashMap.put("r", list);
            k.a(new o(s2, 0L, hashMap));
        }
        final Enumeration a = this.a(COM.NextBus.HttpMapClient.a.f, "/jobs", k).a();
        final ArrayList<String> list2 = new ArrayList<String>();
        while (a.hasMoreElements()) {
            final ResponseComponent responseComponent;
            final int c;
            if ((c = (responseComponent = a.nextElement()).c()) != 0) {
                throw new ConnectionException(c, "Server generated error response to jobs request: " + (String)responseComponent.d());
            }
            String[] array;
            for (int n = ((array = (String[])responseComponent.d()) == null) ? 0 : array.length, i = 0; i < n; ++i) {
                list2.add(array[i]);
            }
        }
        return list2;
    }
    
    public final void a(final String s, final String s2, final boolean b) {
        final j e;
        if ((e = this.e(s)) == null) {
            return;
        }
        e.a(s2, b);
    }
    
    public final void a(final Map map) {
        for (final String s : this.l.keySet()) {
            final j j = this.l.get(s);
            Set set = null;
            if (map != null) {
                set = map.get(s);
            }
            j.a(set);
        }
    }
    
    public final Map a(final long n, final int n2) {
        final k k = new k();
        for (final String s : this.k) {
            final j e = this.e(s);
            final HashMap<String, ArrayList> hashMap = new HashMap<String, ArrayList>();
            final ArrayList list;
            (list = new ArrayList()).add(Long.toString(n));
            final ArrayList list2;
            (list2 = new ArrayList()).add(Integer.toString(n2));
            hashMap.put("rst", list);
            hashMap.put("rbc", list2);
            k.a(new o(s, e.b(), hashMap));
        }
        return this.a(this.a(COM.NextBus.HttpMapClient.a.f, "/replay", k));
    }
    
    public final List a(final String s, final String s2, final int n) {
        final k k = new k();
        final Iterator<String> iterator = this.k.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(s)) {
                final HashMap<String, ArrayList> hashMap = new HashMap<String, ArrayList>();
                final ArrayList list;
                (list = new ArrayList()).add(s2);
                hashMap.put("b", list);
                final ArrayList list2;
                (list2 = new ArrayList()).add(Integer.toString(22));
                hashMap.put("numReports", list2);
                k.a(new o(s, 0L, hashMap));
                break;
            }
        }
        final Enumeration a = this.a(COM.NextBus.HttpMapClient.a.f, "/vehicleTrails", k).a();
        while (a.hasMoreElements()) {
            final ResponseComponent responseComponent;
            if ((responseComponent = a.nextElement()).a().equals(s)) {
                return (List)responseComponent.d();
            }
        }
        return null;
    }
    
    public final BusReport e(final String s, final String s2) {
        final k k = new k();
        final Iterator<String> iterator = this.k.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(s)) {
                final HashMap<String, ArrayList> hashMap = new HashMap<String, ArrayList>();
                final ArrayList list;
                (list = new ArrayList()).add(s2);
                hashMap.put("b", list);
                k.a(new o(s, 0L, hashMap));
                break;
            }
        }
        final Enumeration a = this.a(COM.NextBus.HttpMapClient.a.f, "/busInfo", k).a();
        while (a.hasMoreElements()) {
            final ResponseComponent responseComponent;
            if ((responseComponent = a.nextElement()).a().equals(s)) {
                final List b;
                if ((b = ((Update)responseComponent.d()).b()).size() > 0) {
                    return b.get(0);
                }
                return null;
            }
        }
        return null;
    }
    
    public final Map b() {
        final k k = new k();
        for (final String s : this.k) {
            final j e = this.e(s);
            final HashMap<String, List> hashMap;
            (hashMap = new HashMap<String, List>()).put("nr", e.f());
            hashMap.put("r", e.e());
            final List d;
            if ((d = e.d()) != null) {
                hashMap.put("sp", d);
            }
            k.a(new o(s, e.b(), hashMap));
        }
        return this.a(this.a(COM.NextBus.HttpMapClient.a.f, "/update", k));
    }
    
    private Hashtable a(final Response response) {
        final Hashtable<String, Update> hashtable = new Hashtable<String, Update>();
        final Enumeration a = response.a();
        while (a.hasMoreElements()) {
            final ResponseComponent responseComponent;
            final int c;
            if ((c = (responseComponent = a.nextElement()).c()) != 0) {
                throw new ConnectionException(c, "Server generated error response during update: " + (String)responseComponent.d());
            }
        }
        final Enumeration a2 = response.a();
        while (a2.hasMoreElements()) {
            final ResponseComponent responseComponent2;
            final String a3 = (responseComponent2 = a2.nextElement()).a();
            final j e = this.e(a3);
            final long b = responseComponent2.b();
            hashtable.put(a3, (Update)responseComponent2.d());
            e.a(b);
        }
        return hashtable;
    }
    
    public final void a(final String s, final String s2, final String s3) {
        final HashMap<String, ArrayList> hashMap = new HashMap<String, ArrayList>();
        final ArrayList list;
        (list = new ArrayList()).add(s2);
        final ArrayList list2;
        (list2 = new ArrayList()).add(s3);
        hashMap.put("b", list);
        hashMap.put("j", list2);
        final k k = new k();
        k.a(new o(s, 0L, hashMap));
        final ResponseComponent responseComponent;
        if ((responseComponent = this.a(COM.NextBus.HttpMapClient.a.g, "/specifyjob", k).a().nextElement()).c() != 0) {
            throw new ConnectionException(responseComponent.c(), (String)responseComponent.d());
        }
    }
    
    public final List f(final String s, final String s2) {
        final HashMap<String, ArrayList> hashMap = new HashMap<String, ArrayList>();
        final ArrayList list;
        (list = new ArrayList()).add(s2);
        hashMap.put("r", list);
        final k k = new k();
        k.a(new o(s, 0L, hashMap));
        final ResponseComponent responseComponent;
        if ((responseComponent = this.a(COM.NextBus.HttpMapClient.a.f, "/runs", k).a().nextElement()).c() != 0) {
            throw new ConnectionException(responseComponent.c(), (String)responseComponent.d());
        }
        return (List)responseComponent.d();
    }
    
    public final String b(final String s, final String s2, final String s3) {
        final HashMap<String, ArrayList> hashMap = new HashMap<String, ArrayList>();
        final ArrayList list;
        (list = new ArrayList()).add(s2);
        hashMap.put("r", list);
        final ArrayList list2;
        (list2 = new ArrayList()).add(s3);
        hashMap.put("run", list2);
        final k k = new k();
        k.a(new o(s, 0L, hashMap));
        final ResponseComponent responseComponent;
        if ((responseComponent = this.a(COM.NextBus.HttpMapClient.a.f, "/jobFromRun", k).a().nextElement()).c() != 0) {
            throw new ConnectionException(responseComponent.c(), (String)responseComponent.d());
        }
        return (String)responseComponent.d();
    }
    
    public final String a(final String s, final String s2, final O o) {
        if (s2 == null) {
            return null;
        }
        final HashMap<String, ArrayList<String>> hashMap = new HashMap<String, ArrayList<String>>();
        final ArrayList<String> list;
        (list = new ArrayList<String>()).add(s2);
        hashMap.put("j", list);
        if (o.O()) {
            final ArrayList<String> list2 = new ArrayList<String>();
            final long e = o.s().e();
            final SimpleDateFormat simpleDateFormat;
            (simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a")).setTimeZone(o.q());
            list2.add(simpleDateFormat.format(new Date(e)));
            hashMap.put("time", list2);
        }
        final k k = new k();
        k.a(new o(s, 0L, hashMap));
        final ResponseComponent responseComponent;
        if ((responseComponent = this.a(COM.NextBus.HttpMapClient.a.f, "/runFromJob", k).a().nextElement()).c() != 0) {
            throw new ConnectionException(responseComponent.c(), (String)responseComponent.d());
        }
        return (String)responseComponent.d();
    }
    
    public final Map g(final String s, final String s2) {
        final HashMap<String, ArrayList> hashMap = new HashMap<String, ArrayList>();
        if (s != null) {
            final ArrayList list;
            (list = new ArrayList()).add(s);
            hashMap.put("login", list);
        }
        final k k = new k();
        k.a(new o(s2, 0L, hashMap));
        final ResponseComponent responseComponent;
        if ((responseComponent = this.a(COM.NextBus.HttpMapClient.a.f, "/getUserPreferences", k).a().nextElement()).c() != 0) {
            throw new ConnectionException(responseComponent.c(), (String)responseComponent.d());
        }
        return (Map)responseComponent.d();
    }
    
    public final int a(final String s, final String s2, final String s3, final List list, final List list2) {
        final HashMap<String, ArrayList> hashMap = new HashMap<String, ArrayList>();
        if (s != null) {
            final ArrayList list3;
            (list3 = new ArrayList()).add(s);
            hashMap.put("login", list3);
        }
        final ArrayList list4;
        (list4 = new ArrayList()).add(s3);
        hashMap.put("configName", list4);
        hashMap.put("n", (ArrayList)list);
        hashMap.put("v", (ArrayList)list2);
        final k k = new k();
        k.a(new o(s2, 0L, hashMap));
        final ResponseComponent responseComponent;
        if ((responseComponent = this.a(COM.NextBus.HttpMapClient.a.f, "/updateUserPreferences", k).a().nextElement()).c() != 0) {
            throw new ConnectionException(responseComponent.c(), (String)responseComponent.d());
        }
        return (int)responseComponent.d();
    }
    
    public final int c(final String s, final String s2, final String s3) {
        final HashMap<String, ArrayList> hashMap = new HashMap<String, ArrayList>();
        if (s != null) {
            final ArrayList list;
            (list = new ArrayList()).add(s);
            hashMap.put("login", list);
        }
        final ArrayList list2;
        (list2 = new ArrayList()).add(s3);
        hashMap.put("configName", list2);
        final k k = new k();
        k.a(new o(s2, 0L, hashMap));
        final ResponseComponent responseComponent;
        if ((responseComponent = this.a(COM.NextBus.HttpMapClient.a.f, "/deleteUserPreferences", k).a().nextElement()).c() != 0) {
            throw new ConnectionException(responseComponent.c(), (String)responseComponent.d());
        }
        return (int)responseComponent.d();
    }
    
    private Response a(String string, final String s, final k k) {
        string = this.h + string;
        if (this.m > 0) {
            final String s2 = string;
            System.err.println("Successes: " + COM.NextBus.HttpMapClient.a.b + ", soft failures: " + COM.NextBus.HttpMapClient.a.c + ", failures: " + COM.NextBus.HttpMapClient.a.d + ", starts: " + COM.NextBus.HttpMapClient.a.a + ".");
            for (int i = 0; i < this.m; ++i) {
                new Thread(new f(this, s2, s, k)).start();
            }
        }
        return this.b(string, s, k);
    }
    
    private Response b(String string, String string2, final k k) {
        string = string + string2 + "?" + k.a();
        string2 = this.j + ": error in _sendRequest(), url=" + string + ": ";
        final byte[] a = this.a(string, false, true);
        Response response;
        try {
            response = (Response)new ObjectInputStream(new ByteArrayInputStream(a)).readObject();
        }
        catch (IOException ex) {
            throw new ConnectionException(ResponseComponent.g, string2 + ex);
        }
        catch (ClassNotFoundException ex2) {
            throw new ConnectionException(ResponseComponent.a, string2 + ex2);
        }
        catch (ClassCastException ex3) {
            throw new ConnectionException(ResponseComponent.a, string2 + ex3);
        }
        return response;
    }
    
    final synchronized byte[] a(final String s, final long ifModifiedSince) {
        try {
            final URLConnection openConnection;
            (openConnection = new URL("http://" + s).openConnection()).setUseCaches(true);
            openConnection.setIfModifiedSince(ifModifiedSince);
            final byte[] array = new byte[4096];
            openConnection.setRequestProperty("Accept", "image/png");
            openConnection.setRequestProperty("User-Agent", "NextBus Map Client");
            final InputStream inputStream = openConnection.getInputStream();
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int read;
            while ((read = inputStream.read(array)) != -1) {
                byteArrayOutputStream.write(array, 0, read);
            }
            byteArrayOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        }
        catch (ThreadDeath threadDeath) {
            throw new ConnectionException(ResponseComponent.g, "When loading tile http://" + s + " Connection.__sendRequestUseCachesCheckContentLengthNoSsl() caught ThreadDeath exception" + " probably because the map server isn't availabe.");
        }
        catch (Throwable t) {
            throw new ConnectionException(ResponseComponent.g, "Connection.__sendRequestUseCachesCheckContentLengthNoSsl() caught " + t.toString() + ".");
        }
    }
    
    private byte[] a(String s, boolean openConnection, boolean inputStream) {
        o = (this.i ? "https" : "http") + "://" + (String)o;
        URL url;
        try {
            url = new URL((String)o);
        }
        catch (MalformedURLException ex) {
            o = this.j + ": error 1 in __sendRequest() making http request: " + ex;
            throw new ConnectionException(ResponseComponent.g, (String)o);
        }
        try {
            openConnection = (boolean)url.openConnection();
        }
        catch (IOException ex2) {
            o = this.j + ": error 2 in __sendRequest() making http request: " + ex2;
            throw new ConnectionException(ResponseComponent.g, (String)o);
        }
        ((URLConnection)openConnection).setUseCaches(false);
        ((URLConnection)openConnection).setDoInput(true);
        ((URLConnection)openConnection).setRequestProperty("JSESSIONID", (this.o == null) ? "null" : this.o);
        inputStream = (boolean)null;
        s = (String)0;
        while (inputStream == null) {
            try {
                inputStream = (boolean)((URLConnection)openConnection).getInputStream();
            }
            catch (IOException ex3) {
                if (++s > 2) {
                    throw new ConnectionException(ResponseComponent.g, this.j + ": error 3 in __sendRequest() getting http response: " + ex3);
                }
                continue;
            }
        }
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            final byte[] array = new byte[4096];
            byteArrayOutputStream = new ByteArrayOutputStream();
            int read;
            while ((read = ((InputStream)inputStream).read(array)) != -1) {
                byteArrayOutputStream.write(array, 0, read);
            }
            byteArrayOutputStream.flush();
            o = byteArrayOutputStream.toByteArray();
            if (inputStream != null) {
                try {
                    ((InputStream)inputStream).close();
                }
                catch (IOException ex5) {}
            }
            try {
                byteArrayOutputStream.close();
            }
            catch (IOException ex6) {}
        }
        catch (IOException ex4) {
            throw new ConnectionException(ResponseComponent.g, this.j + ": error 4 in __sendRequst() getting http response: " + ex4);
        }
        finally {
            if (inputStream != null) {
                try {
                    ((InputStream)inputStream).close();
                }
                catch (IOException ex7) {}
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                }
                catch (IOException ex8) {}
            }
        }
        final a a;
        final String headerField;
        final int index;
        if (a.i && a.o == null && (headerField = ((URLConnection)openConnection).getHeaderField("Cookie")) != null && headerField.length() > 0 && (index = headerField.indexOf(61)) != -1) {
            a.o = headerField.substring(index + 1);
        }
        return (byte[])o;
    }
    
    static {
        COM.NextBus.HttpMapClient.a.f = "/service/map/main";
        COM.NextBus.HttpMapClient.a.g = "/service/map/operations";
        COM.NextBus.HttpMapClient.a.a = 0;
        COM.NextBus.HttpMapClient.a.b = 0;
        COM.NextBus.HttpMapClient.a.c = 0;
        COM.NextBus.HttpMapClient.a.d = 0;
        COM.NextBus.HttpMapClient.a.e = new Random();
    }
}
