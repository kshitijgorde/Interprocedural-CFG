// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.net.ProtocolException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;

public class CookieModule implements HTTPClientModule, GlobalConstants
{
    private static Hashtable cookie_cntxt_list;
    private static CookiePolicyHandler cookie_handler;
    
    public int requestHandler(final Request request, final Response[] array) {
        request.setHeaders(Util.removeAllValues(request.getHeaders(), "Cookie"));
        final Hashtable list = Util.getList(CookieModule.cookie_cntxt_list, request.getConnection().getContext());
        if (list.size() == 0) {
            return 0;
        }
        final Vector vector = new Vector<String>();
        final Vector<Integer> vector2 = new Vector<Integer>();
        boolean b = false;
        synchronized (list) {
            final Enumeration<Cookie> elements = list.elements();
            Vector vector3 = null;
            while (elements.hasMoreElements()) {
                final Cookie cookie = elements.nextElement();
                if (cookie.hasExpired()) {
                    if (vector3 == null) {
                        vector3 = new Vector<Cookie>();
                    }
                    vector3.addElement(cookie);
                }
                else {
                    if (!cookie.sendWith(request) || (CookieModule.cookie_handler != null && !CookieModule.cookie_handler.sendCookie(cookie, request))) {
                        continue;
                    }
                    int length;
                    int n;
                    for (length = cookie.getPath().length(), n = 0; n < vector2.size() && vector2.elementAt(n) >= length; ++n) {}
                    vector.insertElementAt(cookie.toExternalForm(), n);
                    vector2.insertElementAt(new Integer(length), n);
                    if (!(cookie instanceof Cookie2)) {
                        continue;
                    }
                    b = true;
                }
            }
            if (vector3 != null) {
                for (int i = 0; i < vector3.size(); ++i) {
                    list.remove(vector3.elementAt(i));
                }
            }
        }
        if (!vector.isEmpty()) {
            final StringBuffer sb = new StringBuffer();
            if (b) {
                sb.append("$Version=\"1\"; ");
            }
            sb.append(vector.elementAt(0));
            for (int j = 1; j < vector.size(); ++j) {
                sb.append("; ");
                sb.append(vector.elementAt(j));
            }
            final NVPair[] headers = request.getHeaders();
            NVPair[] headers2 = Util.resizeArray(headers, headers.length + 1);
            headers2[headers2.length - 1] = new NVPair("Cookie", sb.toString());
            if (!b && Util.getIndex(headers2, "Cookie2") == headers2.length) {
                headers2 = Util.addValue(headers2, "Cookie2", "$Version=\"1\"");
            }
            request.setHeaders(headers2);
            if (GlobalConstants.DebugMods) {
                Util.logLine("CookM: Sending cookies '" + (Object)sb + "'");
            }
        }
        return 0;
    }
    
    public void responsePhase1Handler(final Response response, final RoRequest roRequest) throws IOException {
        final String header = response.getHeader("Set-Cookie");
        final String header2 = response.getHeader("Set-Cookie2");
        if (header == null && header2 == null) {
            return;
        }
        response.deleteHeader("Set-Cookie");
        response.deleteHeader("Set-Cookie2");
        if (header != null) {
            this.handleCookie(header, false, roRequest, response);
        }
        if (header2 != null) {
            this.handleCookie(header2, true, roRequest, response);
        }
    }
    
    public int responsePhase2Handler(final Response response, final Request request) {
        return 10;
    }
    
    public void responsePhase3Handler(final Response response, final RoRequest roRequest) {
    }
    
    public void trailerHandler(final Response response, final RoRequest roRequest) throws IOException {
        final String trailer = response.getTrailer("Set-Cookie");
        final String header = response.getHeader("Set-Cookie2");
        if (trailer == null && header == null) {
            return;
        }
        response.deleteTrailer("Set-Cookie");
        response.deleteTrailer("Set-Cookie2");
        if (trailer != null) {
            this.handleCookie(trailer, false, roRequest, response);
        }
        if (header != null) {
            this.handleCookie(header, true, roRequest, response);
        }
    }
    
    private void handleCookie(final String s, final boolean b, final RoRequest roRequest, final Response response) throws ProtocolException {
        Cookie[] array;
        if (b) {
            array = Cookie2.parse(s, roRequest);
        }
        else {
            array = Cookie.parse(s, roRequest);
        }
        if (GlobalConstants.DebugMods) {
            Util.logLine("CookM: Received and parsed " + array.length + " cookies:");
            for (int i = 0; i < array.length; ++i) {
                Util.logLine("CookM: Cookie " + i + ": " + array[i]);
            }
        }
        final Hashtable list = Util.getList(CookieModule.cookie_cntxt_list, roRequest.getConnection().getContext());
        synchronized (list) {
            for (int j = 0; j < array.length; ++j) {
                final Cookie cookie = list.get(array[j]);
                if (cookie != null && array[j].hasExpired()) {
                    list.remove(cookie);
                }
                else if (CookieModule.cookie_handler == null || CookieModule.cookie_handler.acceptCookie(array[j], roRequest, response)) {
                    list.put(array[j], array[j]);
                }
            }
        }
    }
    
    public static void discardAllCookies() {
        synchronized (CookieModule.cookie_cntxt_list) {
            CookieModule.cookie_cntxt_list.clear();
        }
    }
    
    public static void discardAllCookies(final Object o) {
        final Hashtable list = Util.getList(CookieModule.cookie_cntxt_list, o);
        synchronized (list) {
            list.clear();
        }
    }
    
    public static Cookie[] listAllCookies() {
        synchronized (CookieModule.cookie_cntxt_list) {
            Cookie[] resizeArray = new Cookie[0];
            int n = 0;
            final Enumeration<Hashtable<Object, Cookie>> elements = CookieModule.cookie_cntxt_list.elements();
            while (elements.hasMoreElements()) {
                final Hashtable<Object, Cookie> hashtable = elements.nextElement();
                synchronized (hashtable) {
                    resizeArray = Util.resizeArray(resizeArray, n + hashtable.size());
                    final Enumeration<Cookie> elements2 = hashtable.elements();
                    while (elements2.hasMoreElements()) {
                        resizeArray[n++] = elements2.nextElement();
                    }
                }
            }
            return resizeArray;
        }
    }
    
    public static Cookie[] listAllCookies(final Object o) {
        final Hashtable list = Util.getList(CookieModule.cookie_cntxt_list, o);
        synchronized (list) {
            final Cookie[] array = new Cookie[list.size()];
            int n = 0;
            final Enumeration<Cookie> elements = list.elements();
            while (elements.hasMoreElements()) {
                array[n++] = elements.nextElement();
            }
            return array;
        }
    }
    
    public static void addCookie(final Cookie cookie) {
        Util.getList(CookieModule.cookie_cntxt_list, HTTPConnection.getDefaultContext()).put(cookie, cookie);
    }
    
    public static void addCookie(final Cookie cookie, final Object o) {
        Util.getList(CookieModule.cookie_cntxt_list, o).put(cookie, cookie);
    }
    
    public static void removeCookie(final Cookie cookie) {
        Util.getList(CookieModule.cookie_cntxt_list, HTTPConnection.getDefaultContext()).remove(cookie);
    }
    
    public static void removeCookie(final Cookie cookie, final Object o) {
        Util.getList(CookieModule.cookie_cntxt_list, o).remove(cookie);
    }
    
    public static synchronized CookiePolicyHandler setCookiePolicyHandler(final CookiePolicyHandler cookie_handler) {
        final CookiePolicyHandler cookie_handler2 = CookieModule.cookie_handler;
        CookieModule.cookie_handler = cookie_handler;
        return cookie_handler2;
    }
    
    static {
        CookieModule.cookie_cntxt_list = new Hashtable();
        CookieModule.cookie_handler = new DefaultCookiePolicyHandler();
    }
}
