// 
// Decompiled by Procyon v0.5.30
// 

package com.quotemedia.applet;

import java.net.URLEncoder;
import java.net.URLConnection;
import java.io.DataInputStream;
import java.net.URL;
import java.applet.Applet;

public class CookieUtil
{
    Applet applet;
    
    public CookieUtil(final Applet applet) {
        this.applet = applet;
    }
    
    public String load(final String name) {
        return this.load(name, null, null);
    }
    
    public String load(final String name, final String hostName, final String wmid) {
        String s = "";
        String str = null;
        try {
            final String tur = "http://" + this.applet.getCodeBase().getHost() + "/quotetools/cookiehandler/cookieload.jsp?cookiename=" + name + "&host=" + hostName + "&webmasterId=" + wmid;
            final URL url1 = new URL(tur);
            final URLConnection urlconnection1 = url1.openConnection();
            DataInputStream datainputstream1 = null;
            int i = 0;
            urlconnection1.setDoOutput(true);
            urlconnection1.setDoInput(true);
            urlconnection1.setUseCaches(false);
            datainputstream1 = new DataInputStream(urlconnection1.getInputStream());
            i = 0;
            while ((str = datainputstream1.readLine()) != null && !str.startsWith("</cookiedata>")) {
                if (i != 0) {
                    s += str;
                }
                else {
                    if (!str.startsWith("<cookiedata>")) {
                        continue;
                    }
                    i = 1;
                }
            }
            datainputstream1.close();
            while (s.indexOf("%2C") > -1) {
                s = s.substring(0, s.indexOf("%2C")) + "," + s.substring(s.indexOf("%2C") + 3);
            }
            return s;
        }
        catch (Exception exception1) {
            s = null;
            return s;
        }
    }
    
    public boolean save(final String name, final String value) {
        return this.save(name, value, null, null);
    }
    
    public boolean save(final String name, final String value, final String hostName, final String wmid) {
        final String tsym = URLEncoder.encode("\"" + value.trim() + "\"");
        try {
            final URL url = new URL("http://" + this.applet.getCodeBase().getHost() + "/quotetools/cookiehandler/cookiesave.jsp?cookiedata=" + name + "&cookievalue=" + tsym + "&host=" + hostName + "&webmasterId=" + wmid);
            final URLConnection urlconnection = url.openConnection();
            urlconnection.setDoOutput(true);
            urlconnection.setDoInput(true);
            urlconnection.setUseCaches(true);
            final DataInputStream datainputstream = new DataInputStream(urlconnection.getInputStream());
            while (datainputstream.readLine() != null) {}
            datainputstream.close();
            return true;
        }
        catch (Exception exception) {
            return false;
        }
    }
}
