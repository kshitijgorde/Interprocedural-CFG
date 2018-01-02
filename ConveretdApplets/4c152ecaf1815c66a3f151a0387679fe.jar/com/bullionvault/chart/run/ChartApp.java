// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.run;

import java.util.Hashtable;
import com.bullionvault.chart.c.d;
import java.util.Calendar;
import java.applet.Applet;
import netscape.javascript.JSObject;
import com.bullionvault.chart.a.p;
import javax.swing.SwingUtilities;
import com.bullionvault.chart.g.b;
import com.bullionvault.chart.e.g;
import com.bullionvault.chart.c.k;
import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;
import com.bullionvault.chart.c.j;
import com.bullionvault.chart.resources.Resources;
import com.bullionvault.chart.c.h;
import java.util.Properties;
import com.bullionvault.chart.a.i;
import javax.swing.JApplet;

public class ChartApp extends JApplet
{
    private boolean g;
    public static String a;
    private String h;
    private String i;
    String b;
    String c;
    i d;
    String e;
    String f;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private Properties o;
    private RuntimeException p;
    private String q;
    private String r;
    
    public ChartApp() {
        this.g = true;
        this.h = "value";
        this.i = "en";
        this.b = "Galmarley";
        this.c = "";
        this.k = "menu.series.spotgold";
        this.l = "menu.currency.USD";
        this.m = "menu.style.line";
        this.n = "menu.period.day";
        this.o = new Properties();
        this.q = "false";
    }
    
    public ChartApp(final String[] array) {
        this.g = true;
        this.h = "value";
        this.i = "en";
        this.b = "Galmarley";
        this.c = "";
        this.k = "menu.series.spotgold";
        this.l = "menu.currency.USD";
        this.m = "menu.style.line";
        this.n = "menu.period.day";
        this.o = new Properties();
        this.q = "false";
        this.g = false;
        this.a(array);
        this.e = "www.galmarley.com";
    }
    
    private void a(final String[] array) {
        String substring = null;
        String s = null;
        for (int i = 0; i < array.length; ++i) {
            final String s2;
            if ((s2 = array[i]).charAt(0) == '-') {
                if (substring != null) {
                    com.bullionvault.chart.c.h.e("Setting argument property: " + substring + " -> " + s);
                    ((Hashtable<String, String>)this.o).put(substring.toLowerCase(), s);
                    s = null;
                }
                substring = s2.substring(1);
            }
            else {
                s = s2;
            }
        }
        if (substring != null) {
            com.bullionvault.chart.c.h.e("Setting argument property: " + substring + " -> " + s);
            ((Hashtable<String, String>)this.o).put(substring.toLowerCase(), s);
        }
    }
    
    public void init() {
        super.init();
        System.out.println("Build Version: " + ChartApp.a);
        com.bullionvault.chart.c.h.f("Applet - init()");
        try {
            if (this.g) {
                if (!this.getCodeBase().getHost().equals("")) {
                    this.e = this.getCodeBase().getHost();
                }
                this.a();
            }
            com.bullionvault.chart.c.h.f("Applet - readProperties()");
            Resources.a(this.i = this.b("locale", this.i));
            com.bullionvault.chart.c.j.a();
            this.h = this.b("option_mode", this.h);
            this.k = "menu.series.spotgold";
            this.l = "menu.currency.USD";
            this.m = "menu.style.hlc";
            this.n = "menu.period.6hours";
            this.k = this.c("series", this.k);
            this.l = this.c("currency", this.l);
            this.m = this.c("style", this.m);
            this.n = this.c("period", this.n);
            this.b = this.b("displayMode", this.b);
            this.j = this.b("cookie", this.j);
            this.f = this.b("referrer", this.f);
            this.e = this.b("downloadAddress", this.e);
            this.q = this.c("goRealtime", this.q);
            this.r = this.c("feedUrl", this.r);
            com.bullionvault.chart.c.h.a(Integer.parseInt(this.b("loglevel", com.bullionvault.chart.c.h.a + "")));
            com.bullionvault.chart.c.h.f("Properties: " + this.o.toString());
            if (null == this.o.getProperty("currency")) {
                final String currencyCode = Currency.getInstance(Locale.getDefault()).getCurrencyCode();
                if (Arrays.asList(com.bullionvault.chart.c.j.m).contains(currencyCode)) {
                    this.l = currencyCode;
                }
            }
            com.bullionvault.chart.c.h.f("displayMode: " + this.b);
            this.l = ((null != this.a("currency")) ? this.a("currency") : this.l);
            this.n = ((null != this.a("period")) ? this.a("period") : this.n);
            this.m = ((null != this.a("style")) ? this.a("style") : this.m);
            com.bullionvault.chart.c.h.f("Username=" + this.c);
            com.bullionvault.chart.c.h.f("Referrer=" + this.f);
            com.bullionvault.chart.c.h.f("Currency=" + this.l);
            com.bullionvault.chart.c.h.f("Period=" + this.n);
            com.bullionvault.chart.c.h.f("Style=" + this.m);
            com.bullionvault.chart.c.h.f("Series=" + this.k);
            com.bullionvault.chart.c.k.a();
        }
        catch (RuntimeException p) {
            com.bullionvault.chart.c.h.c("initProperties() - " + p);
            p.printStackTrace();
            this.p = p;
        }
        final j j = new j(this.k, this.l, this.m, this.n);
        if (this.e == null) {
            this.e = "www.galmarley.com";
        }
        com.bullionvault.chart.e.g.a(this);
        try {
            this.c = this.a("username");
            if (this.c != null) {
                com.bullionvault.chart.g.b.a.a(this.c);
            }
        }
        catch (Exception ex2) {
            com.bullionvault.chart.c.h.e("Cookie read fail.");
        }
        try {
            SwingUtilities.invokeAndWait(new com.bullionvault.chart.run.b(this, j));
        }
        catch (Exception ex) {
            System.err.println("createGUI didn't successfully complete");
            System.err.println(ex.getMessage());
            System.err.println(ex.getStackTrace());
            throw new RuntimeException(ex);
        }
    }
    
    private void a() {
        final String[] array = { "Series", "Currency", "Style", "Period", "displayMode", "cookie", "referrer", "locale", "option_mode", "loglevel", "goRealtime", "downloadAddress", "feedUrl" };
        for (int i = 0; i < array.length; ++i) {
            final String parameter;
            if ((parameter = this.getParameter(array[i])) != null) {
                ((Hashtable<String, String>)this.o).put(array[i].toLowerCase(), parameter);
            }
        }
    }
    
    private String b(String lowerCase, final String s) {
        lowerCase = lowerCase.toLowerCase();
        final String property;
        final String s2;
        return ((property = this.o.getProperty(lowerCase)) != null) ? (s2 = property) : s;
    }
    
    private String c(String s, final String s2) {
        final String property;
        if ((property = this.o.getProperty(s)) != null) {
            s = property.toLowerCase();
        }
        if ((s = Resources.c(s)) != null) {
            return s;
        }
        if (property != null) {
            return property;
        }
        return s2;
    }
    
    public void start() {
        com.bullionvault.chart.c.h.f("Applet - start()");
        if (this.p == null) {
            if (this.d != null) {
                this.d.c();
            }
            else {
                this.d = new i(this, this.b, this.e, this.c, this.f, new j(this.k, this.l, this.m, this.n), this.q.equals("true"), this.r);
            }
        }
        if (this.p != null) {
            final i d = this.d;
            final RuntimeException p = this.p;
            final i i = d;
            com.bullionvault.chart.c.h.e("Showing Runtime Exception - " + p);
            p.printStackTrace();
            i.b(Resources.b("error") + " " + p.getMessage());
            new p(i.a(), Resources.b("chart_panel.error"), p.getMessage()).setVisible(true);
        }
    }
    
    public void stop() {
        com.bullionvault.chart.c.h.f("Applet - stop()");
        this.d.d();
        super.stop();
    }
    
    public void destroy() {
        com.bullionvault.chart.c.h.f("Applet - destroy()");
        super.destroy();
    }
    
    public String getAppletInfo() {
        return "Galmarley Charting";
    }
    
    private String b() {
        try {
            final String s;
            if ((s = (String)((JSObject)JSObject.getWindow((Applet)this).getMember("document")).getMember("cookie")).length() > 0) {
                return s;
            }
        }
        catch (Exception ex) {
            com.bullionvault.chart.c.h.e("Did not find ANY cookies.");
        }
        return null;
    }
    
    private String a(final String s) {
        if (null == this.b("version2")) {
            return null;
        }
        return this.b(s);
    }
    
    private String b(final String s) {
        final String b = this.b();
        if (b == null) {
            return null;
        }
        final String string = s + "=";
        if (b.length() > 0) {
            final int index;
            if ((index = b.indexOf(string)) != -1) {
                final int n = index + string.length();
                int n2;
                if ((n2 = b.indexOf(";", n)) == -1) {
                    n2 = b.length();
                }
                return new String(com.bullionvault.chart.c.g.b(b.substring(n, n2)));
            }
            com.bullionvault.chart.c.h.g("Did not read cookie: " + s);
        }
        return null;
    }
    
    public final void a(final String s, String a) {
        a = com.bullionvault.chart.c.g.a(a);
        try {
            final JSObject jsObject = (JSObject)JSObject.getWindow((Applet)this).getMember("document");
            final Calendar instance;
            (instance = Calendar.getInstance()).add(1, 2);
            jsObject.setMember("cookie", (Object)(s + "=" + a + ("; expires=" + instance.getTime().toString())));
        }
        catch (Exception ex) {
            com.bullionvault.chart.c.h.g("Failed to write cookie");
        }
    }
    
    static String a(final ChartApp chartApp) {
        return chartApp.q;
    }
    
    static String b(final ChartApp chartApp) {
        return chartApp.r;
    }
    
    static {
        ChartApp.a = d.a;
    }
}
