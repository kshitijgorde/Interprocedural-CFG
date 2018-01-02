// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.g;

import java.net.URLConnection;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import com.bullionvault.chart.e.i;
import java.net.MalformedURLException;
import com.bullionvault.chart.e.g;
import java.net.URL;
import com.bullionvault.chart.c.h;

public class a
{
    int a;
    String b;
    String c;
    String d;
    String e;
    
    public void a(final String c, final String e) {
        h.e("AccessControllerGalmarley - init()");
        this.c = c;
        new StringBuffer().append(c).append("/ACCESS_CODE").toString();
        this.d = c + "/REGISTER";
        this.e = e;
    }
    
    public int a(final String s) {
        h.e("AccessControllerGalmarley - login()");
        if (s == null || s.equals("")) {
            h.d("AccessControllerGalmarley - No username specified to log in.");
            this.b = "";
            this.a(0);
        }
        else if (!this.b.equals(s) || this.a != 2) {
            this.c(s);
        }
        return this.a;
    }
    
    public String a() {
        return this.c;
    }
    
    public int b() {
        return this.a;
    }
    
    public void b(final String b) {
        this.b = b;
    }
    
    public String c() {
        return this.b;
    }
    
    public void d() {
        try {
            final URL url = new URL("http", new URL(this.d).getHost(), "/ChartApp/loginAttempt.html");
            h.e("AccessController - loginAttempt [" + url.toString() + "]");
            new g(url.toString(), 6, 2000).a();
        }
        catch (MalformedURLException ex) {
            h.d("Malformed loginAttempt URL");
        }
        catch (i i) {
            h.d("Unable to do loginAttempt");
        }
    }
    
    public a() {
        this.a = 0;
        this.b = "";
        this.c = "";
        this.d = "";
        this.e = "";
    }
    
    public void c(String headerField) {
        h.e("AccessControllerGalmarley -  registerUser() - Registering new user...");
        this.b = headerField;
        String string = null;
        try {
            string = this.d + "?email=" + URLEncoder.encode(this.b, "UTF-8") + "&referrer=" + this.e;
        }
        catch (UnsupportedEncodingException ex) {
            new RuntimeException(ex);
        }
        h.e("AccessControllerGalmarley -  registerUser() - Registration URL: " + string);
        final g g;
        (g = new g(string, 3, 50000)).a("username", headerField);
        final URLConnection a = g.a();
        h.e("AccessControllerGalmarley - registerUser() - Response Code was [" + g.b() + "]");
        headerField = a.getHeaderField("X-Status");
        h.e("AccessControllerGalmarley - userStatus = " + headerField);
        if (headerField == null) {
            h.e("New Username Registered.");
            return;
        }
        if (headerField.equals("PREREGISTERED")) {
            h.e("AccessControllerGalmarley - PREREGISTERED - The user has pre-registered, and we are sending an email.");
            this.a(3);
            return;
        }
        if (headerField.equals("ALREADY PREREGISTERED")) {
            h.e("AccessControllerGalmarley - ALREADY PREREGISTERED - User Awaiting Full Registration.");
            this.a(1);
            return;
        }
        if (headerField.equals("REGISTERED")) {
            h.e("AccessControllerGalmarley - REGISTERED - The user is now registered.");
            this.a(2);
            return;
        }
        if (headerField.equals("ALREADY REGISTERED")) {
            h.e("AccessControllerGalmarley - ALREADY REGISTERED - The user was already registered.");
            this.a(2);
            return;
        }
        throw new RuntimeException("Unknown user login status: " + headerField);
    }
    
    public void a(final int a) {
        this.a = a;
        h.e("AccessControllerGalmarley - AccessControllerGalmarley, set status = [" + this.a + "]");
    }
}
