// 
// Decompiled by Procyon v0.5.30
// 

package ji.net;

import ji.io.h;
import java.net.HttpURLConnection;
import ji.util.d;
import java.net.URLConnection;

public class bi
{
    static boolean a;
    static String[][] b;
    
    public static String a(final URLConnection urlConnection, final String s) {
        if (urlConnection == null) {
            return "";
        }
        String concat = "Unable to retrieve specific server response code for ".concat(String.valueOf(String.valueOf(urlConnection.getURL())));
        bi.a = false;
        try {
            if (!d.em() && urlConnection instanceof HttpURLConnection) {
                final HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
                final int responseCode = httpURLConnection.getResponseCode();
                String s2 = "<No Description>";
                for (int i = 0; i < bi.b.length; ++i) {
                    if (d.c(bi.b[i][1], -1) == responseCode) {
                        s2 = bi.b[i][0];
                        break;
                    }
                }
                final String value = String.valueOf(String.valueOf(new StringBuffer("Server response: ").append(responseCode).append("(").append(s2).append(")").append("\n").append(httpURLConnection.getResponseMessage())));
                bi.a = true;
                concat = value;
            }
        }
        catch (Exception ex) {
            h.d(s, "httpResponse: ");
            ex.printStackTrace();
        }
        return concat;
    }
    
    public static final boolean a() {
        return bi.a;
    }
    
    static {
        bi.a = false;
        bi.b = new String[][] { { "HTTP_ACCEPTED", "202" }, { "HTTP_BAD_GATEWAY", "502" }, { "HTTP_BAD_METHOD", "405" }, { "HTTP_BAD_REQUEST", "400" }, { "HTTP_CLIENT_TIMEOUT", "408" }, { "HTTP_CONFLICT", "409" }, { "HTTP_CREATED", "201" }, { "HTTP_ENTITY_TOO_LARGE", "413" }, { "HTTP_FORBIDDEN", "403" }, { "HTTP_GATEWAY_TIMEOUT", "504" }, { "HTTP_GONE", "410" }, { "HTTP_INTERNAL_ERROR", "500" }, { "HTTP_LENGTH_REQUIRED", "411" }, { "HTTP_MOVED_PERM", "301" }, { "HTTP_MOVED_TEMP", "302" }, { "HTTP_MULT_CHOICE", "300" }, { "HTTP_NO_CONTENT", "204" }, { "HTTP_NOT_ACCEPTABLE", "406" }, { "HTTP_NOT_AUTHORITATIVE", "203" }, { "HTTP_NOT_FOUND", "404" }, { "HTTP_NOT_IMPLEMENTED", "501" }, { "HTTP_NOT_MODIFIED", "304" }, { "HTTP_OK", "200" }, { "HTTP_PARTIAL", "206" }, { "HTTP_PAYMENT_REQUIRED", "402" }, { "HTTP_PRECON_FAILED", "412" }, { "HTTP_PROXY_AUTH", "407" }, { "HTTP_REQ_TOO_LONG", "414" }, { "HTTP_RESET", "205" }, { "HTTP_SEE_OTHER", "303" }, { "HTTP_UNAUTHORIZED", "401" }, { "HTTP_UNAVAILABLE", "503" }, { "HTTP_UNSUPPORTED_TYPE", "415" }, { "HTTP_USE_PROXY", "305" }, { "HTTP_VERSION", "505" } };
    }
}
