// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.io.InputStream;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;

final class dl extends Thread implements bo
{
    private Vector a;
    private boolean a;
    private dh a;
    
    dl(final dh a) {
        this.a = new Vector();
        this.a = a;
    }
    
    final synchronized void a(final cf cf) {
        this.a.addElement(cf);
        this.notify();
    }
    
    public final void run() {
        while (!this.a) {
            final cf cf;
            synchronized (this) {
                while (this.a.size() == 0 && !this.a) {
                    try {
                        this.wait();
                    }
                    catch (InterruptedException ex2) {}
                }
                if (this.a) {
                    return;
                }
                cf = this.a.elementAt(0);
                this.a.removeElementAt(0);
            }
            try {
                URL url;
                if (cf.a) {
                    final URL codeBase = this.a.getCodeBase();
                    url = new URL(codeBase.getProtocol(), codeBase.getHost(), codeBase.getPort(), cf.b);
                }
                else {
                    url = new URL(cf.b);
                }
                final URLConnection openConnection = url.openConnection();
                final StringBuffer sb = new StringBuffer();
                InputStream inputStream;
                if (cf.b.endsWith(".gz")) {
                    inputStream = new eq(openConnection.getInputStream());
                }
                else {
                    inputStream = openConnection.getInputStream();
                }
                int read;
                while ((read = inputStream.read()) != -1) {
                    sb.append((char)read);
                }
                inputStream.close();
                cf.a = new String(sb);
            }
            catch (IOException a) {
                cf.a = a;
            }
            catch (SecurityException ex) {
                if (this.a.getParameter("signedcab") != null) {
                    String string = "unknown";
                    try {
                        string = InetAddress.getByName(this.a.getCodeBase().getHost()).toString();
                    }
                    catch (UnknownHostException ex3) {}
                    try {
                        this.a.getAppletContext().showDocument(new URL(this.a.getDocumentBase().toExternalForm() + "&nosignedcab=yes&exception=yes&ip=" + string), "_self");
                        return;
                    }
                    catch (MalformedURLException ex4) {
                        this.a.a.a("Exception 201 loading text: " + ex.toString());
                        return;
                    }
                }
                this.a.a.a("Exception 202 loading text: " + ex.toString());
                return;
            }
            catch (Throwable t) {
                this.a.a.a("Exception 203 loading text: " + t.toString());
                return;
            }
            this.a.a.a(this.a, 5, cf);
        }
    }
    
    public final void a(final int n, final Object o) {
        this.a.a((Throwable)o);
    }
    
    public final synchronized void a() {
        this.a = true;
        this.notify();
    }
}
