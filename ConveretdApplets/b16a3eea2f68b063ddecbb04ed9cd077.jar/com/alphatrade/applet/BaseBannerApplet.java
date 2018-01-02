// 
// Decompiled by Procyon v0.5.30
// 

package com.alphatrade.applet;

import java.util.StringTokenizer;
import java.util.Enumeration;
import java.util.Stack;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Date;
import java.io.PrintStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;
import java.awt.Color;
import java.applet.Applet;

public abstract class BaseBannerApplet extends Applet implements Runnable
{
    public static final Color a;
    public static final Color b;
    private static final Vector h;
    public String c;
    public int d;
    private Thread i;
    public long e;
    private boolean j;
    private m k;
    public a f;
    public Vector g;
    private Hashtable l;
    private SimpleDateFormat m;
    
    public BaseBannerApplet() {
        this.c = "beta.alphatrade.com";
        this.d = 80;
        this.f = null;
        this.m = new SimpleDateFormat("yyyy.MM.dd");
    }
    
    public String getAppletInfo() {
        return this.getClass().getName() + ": Copyright (c) 2004 Alphatrade.com";
    }
    
    public void init() {
        System.out.println(this.getAppletInfo());
        this.k = new m();
        this.g = new Vector();
        this.l = new Hashtable(10);
        this.j = true;
        this.e = 1000L;
        (this.i = new Thread(this, "BaseBannerApplet")).start();
        try {
            final String host;
            if ((host = this.getCodeBase().getHost()) != null && host.length() > 1) {
                this.c = host;
            }
            final String parameter;
            if ((parameter = this.getParameter("port")) != null) {
                this.d = Integer.parseInt(parameter);
            }
        }
        catch (Exception ex) {
            System.err.println("LYNX <BASE APPLET> - ERROR SETTING PARAMETERS: " + ex);
            ex.printStackTrace();
        }
        this.f = a.a(this.c, this.d);
        if (this.f == null) {
            this.f = new a(this.c, this.d);
        }
        this.f.a();
        this.b();
    }
    
    private void b() {
        final URL documentBase = this.getDocumentBase();
        try {
            System.out.println("strServerHost = " + this.c);
            System.out.println("serverPort = " + this.d);
            final URLConnection openConnection;
            (openConnection = new URL("http://" + this.c + ":" + this.d + "/com.alphatrade.servlet.registration.FreeAppletRegistration").openConnection()).setDoOutput(true);
            openConnection.setUseCaches(false);
            final PrintStream printStream;
            (printStream = new PrintStream(openConnection.getOutputStream())).print("docBase=" + documentBase.toString() + "&type=" + this.getClass() + "&lastVisited=" + this.m.format(new Date()));
            printStream.flush();
            printStream.close();
            final InputStream inputStream = openConnection.getInputStream();
            final StringBuffer sb = new StringBuffer();
            int read;
            while ((read = inputStream.read()) != -1) {
                sb.append((char)read);
            }
            inputStream.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void a(final Stack stack) {
        try {
            synchronized (this.k) {
                this.k.a(stack);
            }
            this.e();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private Stack c() {
        Stack stack = null;
        synchronized (this.k) {
            if (this.k.a > 0) {
                stack = (Stack)this.k.b();
            }
        }
        return stack;
    }
    
    public void run() {
        int n = 0;
        while (this.j) {
            try {
                Stack c;
                while ((c = this.c()) != null) {
                    this.b(c);
                    ++n;
                }
                if (n > 0) {
                    n = 0;
                    this.a();
                }
                this.repaint();
                this.d();
            }
            catch (Exception ex) {
                this.j = false;
                System.err.println("LYNX <BASE APPLET> - ERROR IN RUN: " + ex);
                ex.printStackTrace();
            }
        }
        this.f.b();
    }
    
    public void destroy() {
        this.j = false;
        this.e();
        super.destroy();
    }
    
    public void stop() {
        this.j = false;
        this.e();
        super.stop();
    }
    
    private void b(final Stack stack) {
        try {
            final String[] array;
            if ((array = this.l.get(stack.pop())) != null) {
                while (!stack.empty()) {
                    final int intValue = (int)stack.pop();
                    final String s = stack.pop();
                    if (intValue < BaseBannerApplet.h.size()) {
                        array[intValue] = s;
                    }
                }
            }
        }
        catch (Exception ex) {
            System.err.println("LYNX <BASE APPLET> - ERROR PROCESSING QUOTES: " + ex);
            ex.printStackTrace();
        }
    }
    
    public abstract void a();
    
    public final void a(final String[] array) {
        final Vector<String> vector = new Vector<String>();
        final Hashtable<Object, String[]> hashtable = new Hashtable<Object, String[]>(50);
        final String[] array2 = new String[BaseBannerApplet.h.size()];
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = "N/A";
        }
        for (int j = 0; j < array.length; ++j) {
            final String s = array[j];
            final String[] array3;
            String[] array4;
            if ((array3 = this.l.remove(s)) != null) {
                array4 = array3;
            }
            else {
                (array4 = array2.clone())[BaseBannerApplet.h.indexOf("Symbol")] = s;
            }
            if (!hashtable.containsKey(s)) {
                vector.addElement(s);
                hashtable.put(s, array4);
            }
        }
        final Enumeration<String> keys = this.l.keys();
        while (keys.hasMoreElements()) {
            this.f.b(this, keys.nextElement());
        }
        this.g.removeAllElements();
        this.l.clear();
        final Enumeration<String> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final String s2 = elements.nextElement();
            final String[] array5 = hashtable.get(s2);
            this.g.addElement(array5);
            this.l.put(s2, array5);
            this.f.a(this, s2);
        }
    }
    
    public static int d(final String s) {
        return BaseBannerApplet.h.indexOf(s);
    }
    
    public static String[] e(final String s) {
        int n = 0;
        final StringTokenizer stringTokenizer;
        final String[] array = new String[(stringTokenizer = new StringTokenizer(s, ",")).countTokens()];
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken;
            if ((nextToken = stringTokenizer.nextToken()).startsWith("\"") && nextToken.endsWith("\"")) {
                array[n] = nextToken.substring(1, nextToken.length() - 1);
            }
            else {
                array[n] = nextToken;
            }
            ++n;
        }
        return array;
    }
    
    private synchronized void d() {
        if (this.k.a <= 0 && this.j) {
            try {
                this.wait(this.e);
            }
            catch (Exception ex) {}
        }
    }
    
    private synchronized void e() {
        try {
            this.notify();
        }
        catch (Exception ex) {}
    }
    
    static {
        a = new Color(38, 44, 100);
        b = new Color(234, 234, 234);
        (h = new Vector()).addElement("Symbol");
        BaseBannerApplet.h.addElement("Last Trade");
        BaseBannerApplet.h.addElement("Change");
        BaseBannerApplet.h.addElement("Bid");
        BaseBannerApplet.h.addElement("Ask");
        BaseBannerApplet.h.addElement("Open");
        BaseBannerApplet.h.addElement("High");
        BaseBannerApplet.h.addElement("Low");
        BaseBannerApplet.h.addElement("Volume");
        BaseBannerApplet.h.addElement("Previous");
        BaseBannerApplet.h.addElement("Dividend");
        BaseBannerApplet.h.addElement("P-E Ratio");
        BaseBannerApplet.h.addElement("Earnings");
        BaseBannerApplet.h.addElement("Year Low");
        BaseBannerApplet.h.addElement("Year High");
        BaseBannerApplet.h.addElement("Ex-Dividend Date");
        BaseBannerApplet.h.addElement("Exchange");
        BaseBannerApplet.h.addElement("Yield");
        BaseBannerApplet.h.addElement("Market Cap");
        BaseBannerApplet.h.addElement("Dividend Date");
        BaseBannerApplet.h.addElement("Time");
        BaseBannerApplet.h.addElement("% Change");
        BaseBannerApplet.h.addElement("Avg Volume");
        BaseBannerApplet.h.addElement("Company");
        BaseBannerApplet.h.addElement("Restriction");
        BaseBannerApplet.h.addElement("Tick");
        BaseBannerApplet.h.addElement("Market ID");
        BaseBannerApplet.h.addElement("Last Tick");
        BaseBannerApplet.h.addElement("Bid Size");
        BaseBannerApplet.h.addElement("Ask Size");
        BaseBannerApplet.h.addElement("Transactions");
        BaseBannerApplet.h.addElement("Last Size");
        BaseBannerApplet.h.addElement("Time Stamp");
    }
}
