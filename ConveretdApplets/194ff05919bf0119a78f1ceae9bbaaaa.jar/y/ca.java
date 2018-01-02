// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.net.URLConnection;
import java.io.InputStream;
import java.io.IOException;
import com.sun.jimi.core.Jimi;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Vector;

public final class ca implements Runnable, cz
{
    private String a;
    private String b;
    private String c;
    private String d;
    private Vector a;
    private String e;
    private String f;
    private boolean a;
    private String g;
    private ew a;
    
    public ca(final String c, final String b, final String a, final String d, final String e, final String f, final boolean a2, final String g) {
        this.c = c;
        this.b = b;
        this.d = d;
        this.a = a;
        this.a = new Vector();
        this.e = e;
        this.f = f;
        this.a = a2;
        this.g = g;
        this.a = ew.a();
    }
    
    public final void a() {
        this.a.a("AdsManger.onUserEvent()");
        if (0L == this.a()) {
            this.b();
        }
    }
    
    public final void a(final l l) {
        l.a = 0L;
        synchronized (this.a) {
            this.a.insertElementAt(l, 0);
        }
        final ca ca;
        ca.b();
    }
    
    private void b() {
        final Thread thread;
        (thread = new Thread(this)).start();
        this.a.a("Starting new ad thread: " + thread.getName());
    }
    
    public final boolean a(final l l) {
        synchronized (this.a) {
            this.a.removeElement(l);
        }
        return (boolean)this;
    }
    
    private boolean a(final bg bg) {
        boolean b = false;
        InputStream inputStream = null;
        try {
            final l a = bg.a;
            final StringBuffer sb = new StringBuffer();
            if (this.a) {
                sb.append(this.c);
                sb.append("/games/");
                sb.append(this.e);
            }
            else {
                sb.append(this.b);
            }
            sb.append("/ads/adreq?page=");
            sb.append(a.c);
            sb.append("&location=");
            sb.append(a.a);
            sb.append("&adid=");
            sb.append(a.b);
            sb.append("&intl=");
            sb.append(this.e);
            sb.append("&clientType=");
            sb.append(this.f);
            sb.append("&countpageview=");
            if (0 == a.a) {
                sb.append('y');
            }
            else {
                sb.append('n');
            }
            sb.append("&v=");
            sb.append("2.0.0");
            this.a.a("Requesting:" + sb.toString());
            final URLConnection openConnection;
            (openConnection = new URL(sb.toString()).openConnection()).connect();
            this.a.a("ResponseStatus=" + openConnection.getHeaderField(0));
            inputStream = openConnection.getInputStream();
            final BufferedReader bufferedReader;
            final String line = (bufferedReader = new BufferedReader(new InputStreamReader(inputStream))).readLine();
            final String line2 = bufferedReader.readLine();
            final String line3 = bufferedReader.readLine();
            final String line4 = bufferedReader.readLine();
            final String line5 = bufferedReader.readLine();
            final int int1 = Integer.parseInt(line3);
            final int int2 = Integer.parseInt(line4);
            inputStream.close();
            inputStream = null;
            this.a.a("mediaUrl=" + line);
            this.a.a("onClickUrl=" + line2);
            this.a.a("nImageWidth=" + int1);
            this.a.a("nImageHeight=" + int2);
            this.a.a("cscUrl=" + line5);
            StringBuffer sb2;
            if (line5.length() > 0) {
                if (this.a) {
                    (sb2 = new StringBuffer(this.c)).append("/csc/");
                    sb2.append(this.e);
                    if (0 == line5.indexOf(this.d)) {
                        sb2.append(line5.substring(this.d.length()));
                    }
                    else {
                        int index = line5.indexOf(".com/");
                        if (-1 != index) {
                            index += 4;
                            sb2.append(line5.substring(index));
                        }
                        else {
                            sb2.append(line5.substring(this.d.length()));
                        }
                    }
                }
                else {
                    sb2 = new StringBuffer(line5);
                }
            }
            else {
                sb2 = new StringBuffer();
            }
            bg.c = line;
            bg.a = int1;
            bg.b = int2;
            bg.b = line2;
            bg.a = sb2.toString();
            sb.setLength(0);
            if (this.a) {
                sb.append(this.c);
                if (0 == line.indexOf(this.a)) {
                    sb.append(line.substring(this.a.length()));
                }
                else {
                    int index2 = line.indexOf(".com/");
                    if (-1 != index2) {
                        index2 += 4;
                        sb.append(line.substring(index2));
                    }
                    else {
                        sb.append(line.substring(this.a.length()));
                    }
                }
            }
            else {
                sb.append(line);
            }
            this.a.a("Requesting:" + sb.toString());
            bg.a = Jimi.getImage(new URL(sb.toString()), 2);
            b = true;
        }
        catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex2) {
            ex2.printStackTrace();
        }
        finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                }
                catch (IOException ex3) {}
            }
        }
        return b;
    }
    
    private void c() {
        synchronized (this.a) {
            if (this.a.size() > 1) {
                final Object[] array = new Object[this.a.size()];
                this.a.copyInto(array);
                for (int i = 1; i < array.length; ++i) {
                    l l;
                    long a;
                    int n;
                    for (a = (l = (l)array[i]).a(), n = i - 1; n >= 0 && ((l)array[n]).a() > a; --n) {
                        array[n + 1] = array[n];
                    }
                    array[n + 1] = l;
                }
                this.a.a("Ad locations sorted by time to show:");
                this.a.removeAllElements();
                for (int j = 0; j < array.length; ++j) {
                    this.a.addElement(array[j]);
                    this.a.a("time to show:" + ((l)array[j]).a());
                }
            }
        }
    }
    
    private long a() {
        long a;
        synchronized (this.a) {
            if (this.a.size() > 0) {
                a = this.a.elementAt(0).a();
            }
            else {
                a = 60000L;
            }
        }
        return a;
    }
    
    public final void run() {
        this.a.a("Entering AdsManager.run()");
        try {
            l l = null;
            synchronized (this.a) {
                if (this.a.size() > 0) {
                    l = this.a.elementAt(0);
                    this.a.removeElementAt(0);
                }
            }
            if (null != l) {
                try {
                    final cu a = l.a;
                    final bg bg = new bg(l);
                    if (l.a) {
                        if (this.a(bg)) {
                            if (a.a(bg)) {
                                final String a2;
                                if ((a2 = bg.a).length() > 0) {
                                    try {
                                        final URLConnection openConnection = new URL(a2).openConnection();
                                        this.a.a("urlConnection is a:" + openConnection.toString());
                                        openConnection.setRequestProperty("YahooRemoteIP", this.g);
                                        this.a.a("Set request property:YahooRemoteIP:" + this.g);
                                        openConnection.connect();
                                        this.a.a("Got response:");
                                        this.a.a(openConnection.getHeaderField(0));
                                        this.a.a("From CSC url=" + bg.a);
                                    }
                                    catch (IOException ex2) {
                                        final IOException ex = ex2;
                                        ex2.printStackTrace();
                                        this.a.b("IOException trying to connect to:" + a2, ex);
                                    }
                                }
                                else {
                                    this.a.a("Empty csc url.");
                                }
                                final l i = l;
                                ++i.a;
                            }
                        }
                        else {
                            this.a.a("No ad for:" + l.toString());
                        }
                    }
                    else {
                        a.a(bg);
                    }
                }
                finally {
                    l.a = System.currentTimeMillis();
                    synchronized (this.a) {
                        this.a.addElement(l);
                        this.c();
                    }
                    final ca ca;
                    ca.a.a("Next ad fetch in " + ca.a.elementAt(0).a() + " msecs");
                }
            }
        }
        catch (Exception ex3) {
            this.a.b("Exception in AdsManager.run", ex3);
        }
        this.a.a("Exiting AdsManager.run()");
    }
}
