// 
// Decompiled by Procyon v0.5.30
// 

package com.smartmoney.util;

import java.net.URL;
import java.applet.AppletContext;
import java.net.URLConnection;

public class CookieUtil
{
    private static String cookieHeader;
    
    public static void setCookieHeader(final String cookieHeader) {
        CookieUtil.cookieHeader = cookieHeader;
    }
    
    public static void setCookieHeader(final URLConnection urlConnection, final boolean b) {
        if (CookieUtil.cookieHeader != null) {
            urlConnection.setRequestProperty("Cookie", CookieUtil.cookieHeader);
        }
        if (b) {
            readNewHeader(urlConnection);
        }
    }
    
    public static void setCookieHeader(final URLConnection urlConnection) {
        setCookieHeader(urlConnection, true);
    }
    
    public static void readNewHeader(final URLConnection urlConnection) {
        if (CookieUtil.cookieHeader != null) {
            final String headerField = urlConnection.getHeaderField("setcookieheader");
            if (headerField != null) {
                CookieUtil.cookieHeader = headerField;
                System.out.println("");
            }
        }
    }
    
    public static void checkHTTPCode(final URLConnection urlConnection, final AppletContext appletContext) {
        System.out.println("checkHTTPCode");
        try {
            urlConnection.getInputStream();
            String s = urlConnection.getHeaderField("redirecturl");
            System.out.println("redirecturl= " + s);
            System.out.println("Date= " + urlConnection.getHeaderField("Date"));
            if (s != null) {
                if (s.indexOf("https://") != -1) {
                    s = "http:" + s.substring(s.indexOf("https") + 1, s.length());
                }
                System.out.println(s);
                appletContext.showDocument(new URL(s), "_self");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    static {
        CookieUtil.cookieHeader = null;
    }
}
