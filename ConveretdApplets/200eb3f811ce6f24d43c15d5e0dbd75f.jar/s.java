import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class s
{
    private static w a;
    private static f b;
    
    public static t a(final Applet applet, final String s, final en[] array, final String[] array2) {
        if (s.b.j()) {
            s.b.h("getValueContainer with " + s.b.a(applet, s, array, array2));
        }
        final t t = new t(s);
        if (applet.getParameter("DOCUMENT_URL") != null) {
            final w a = new w(applet.getParameter("DOCUMENT_URL"));
            y.a(a);
            if (a.m("KEEP_STATIC")) {
                if (a.a("KEEP_STATIC", (v)null).equalsIgnoreCase("true")) {
                    s.a = a;
                }
                else {
                    s.a = null;
                }
            }
            else if (s.a != null) {
                t.a(s.a);
                if (s.b.k()) {
                    s.b.i("depth " + t.size() + " - appended static URL Getter " + s.a);
                }
            }
            t.a(a);
            if (s.b.k()) {
                s.b.i("depth " + t.size() + " - appended URL Getter " + a);
            }
        }
        final af af = new af(applet);
        t.a(af);
        y.a(t);
        if (s.b.k()) {
            s.b.i("depth " + t.size() + " - appended AppletGetter " + af);
        }
        if (t.a("MASTER_PROP_FILE") != null) {
            final ah a2 = ag.a(t.a("MASTER_PROP_FILE"), t, false, false);
            t.a(a2);
            if (s.b.k()) {
                s.b.i("depth " + t.size() + " - appended MASTER_PROP_FILE " + a2);
            }
        }
        if (array2 != null && t.c("LOAD_CONFIG_FILES") != null && t.c("LOAD_CONFIG_FILES")) {
            for (int i = 0; i < array2.length; ++i) {
                t.a(ag.a(t.a(array2[i]), t, false));
                if (s.b.k()) {
                    s.b.i("depth " + t.size() + " - inserted propFile " + array2[i]);
                }
            }
        }
        final ah ah = new ah("HARD_CODED");
        if (t.m("APPLET_CHOST_LIST")) {
            ah.put("CHOST_LIST", t.a("APPLET_CHOST_LIST"));
        }
        else {
            ah.put("CHOST_LIST", a(applet));
        }
        if (t.m("APPLET_CPORT_LIST")) {
            ah.put("CPORT_LIST", t.a("APPLET_CPORT_LIST"));
        }
        else {
            ah.put("CPORT_LIST", b(applet));
        }
        if (t.m("APPLET_VHOST_LIST")) {
            ah.put("VHOST_LIST", t.a("APPLET_VHOST_LIST"));
        }
        else {
            ah.put("VHOST_LIST", a(applet));
        }
        if (t.m("APPLET_URI_PREFIX")) {
            ah.put("URI_PREFIX", t.a("APPLET_URI_PREFIX"));
        }
        else {
            ah.put("URI_PREFIX", d(applet));
        }
        if (t.m("APPLET_PROTOCOL_LIST")) {
            ah.put("PROTOCOL_LIST", t.a("APPLET_PROTOCOL_LIST"));
        }
        else {
            ah.put("PROTOCOL_LIST", c(applet));
        }
        if (t.a("XID") != null) {
            ah.put("AUTH_ID_NAME", "XID");
            ah.put("AUTH_ID_VALUE", t.a("XID"));
        }
        else if (t.a("ZID") != null) {
            ah.put("AUTH_ID_NAME", "ZID");
            ah.put("AUTH_ID_VALUE", t.a("ZID"));
        }
        ah.put("XID_AUTO_REFRESH", "false");
        ah.put("NO_XID_REQUEST", "true");
        if (ah.get("PROTOCOL_LIST") != null && ah.get("PROTOCOL_LIST").equalsIgnoreCase("https")) {
            if (!t.m("APPLET_CPORT_LIST")) {
                ah.put("CPORT_LIST", "443");
            }
            ah.put("PUSH_MODE", "poll");
        }
        t.a(ah);
        if (s.b.k()) {
            s.b.i("depth " + t.size() + " appended HashPropertyGetter " + ah);
        }
        if (array != null) {
            for (int j = 0; j < array.length; ++j) {
                t.a(array[j]);
            }
        }
        t.a(ap.a());
        t.a(aq.a());
        if (s.b.i()) {
            s.b.g("performing property based log setup");
        }
        y.a(t);
        if (s.b.k()) {
            s.b.i("creating ImmutableValueContainer with size " + t.size());
        }
        final ar ar = new ar(s, t);
        return new ar(s, t);
    }
    
    private static String a(final Applet applet) {
        String s = applet.getCodeBase().toString();
        final int index = s.indexOf("://");
        if (index != -1) {
            s = s.substring(index + 3);
        }
        final int index2 = s.indexOf("/");
        if (index2 != -1) {
            s = s.substring(0, index2);
        }
        final int index3 = s.indexOf(":");
        if (index3 != -1) {
            s = s.substring(0, index3);
        }
        return s;
    }
    
    private static String b(final Applet applet) {
        String s = applet.getCodeBase().toString();
        final int index = s.indexOf("://");
        if (index != -1) {
            s = s.substring(index + 3);
        }
        final int index2 = s.indexOf("/");
        if (index2 != -1) {
            s = s.substring(0, index2);
        }
        final int index3 = s.indexOf(":");
        if (index3 != -1) {
            s = s.substring(index3 + 1);
        }
        try {
            Integer.parseInt(s);
            return s;
        }
        catch (NumberFormatException ex) {
            return "80";
        }
    }
    
    private static String c(final Applet applet) {
        return applet.getCodeBase().getProtocol();
    }
    
    private static String d(final Applet applet) {
        String s = applet.getCodeBase().toString();
        final int index = s.indexOf("://");
        if (index != -1) {
            s = s.substring(index + 3);
        }
        final int index2 = s.indexOf("/");
        if (index2 != -1) {
            s = s.substring(index2);
        }
        String s2;
        if (s.endsWith("/")) {
            s2 = s + "goo";
        }
        else {
            s2 = s + "/goo";
        }
        return s2;
    }
    
    static {
        s.a = null;
        s.b = f.a("AVG");
    }
}
