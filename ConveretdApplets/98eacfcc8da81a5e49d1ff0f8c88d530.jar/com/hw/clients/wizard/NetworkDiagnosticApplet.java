// 
// Decompiled by Procyon v0.5.30
// 

package com.hw.clients.wizard;

import VT_6_1_0_11.cM;
import VT_6_1_0_11.aj;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import com.hw.client.util.a;
import java.applet.AppletContext;
import java.net.URL;
import java.applet.Applet;

public class NetworkDiagnosticApplet extends Applet implements Runnable
{
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private URL f;
    private AppletContext g;
    private StringBuffer h;
    
    public NetworkDiagnosticApplet() {
        this.g = null;
    }
    
    public void init() {
        com.hw.client.util.a.a(this, 1);
        com.hw.client.util.a.d("\n\n****** Network Diagnostic Applet starting, Copyright Wimba 2004-2006\n****** Version: 1.1.4, Revision: $Revision: 78931 $, build: ${DSTAMP}-${TSTAMP}");
        super.init();
        this.a = this.getParameter("wizard.server.url");
        if (this.a == null) {
            this.a = this.getDocumentBase().toString();
            com.hw.client.util.a.b("wizard.server.url empty, using " + this.a);
        }
        com.hw.client.util.a.b("Test URL is: " + this.a);
        this.b = this.getParameter("wizard.url.pass");
        if (this.b == null) {
            com.hw.client.util.a.e("No wizard.url.pass URL provided.");
        }
        this.d = this.getParameter("wizard.target.pass");
        if (this.d == null) {
            com.hw.client.util.a.e("No wizard.target.pass URL provided.");
        }
        this.c = this.getParameter("wizard.url.fail");
        if (this.c == null) {
            com.hw.client.util.a.e("No wizard.url.fail URL provided.");
        }
        this.e = this.getParameter("wizard.target.fail");
        if (this.e == null) {
            com.hw.client.util.a.e("No wizard.target.fail URL provided.");
        }
        this.f = this.getDocumentBase();
        this.h = new StringBuffer();
        com.hw.client.util.a.d("** Network diagnostic initialized");
    }
    
    public void start() {
        super.start();
        this.g = this.getAppletContext();
        new Thread(this, "TEST_THREAD").start();
        com.hw.client.util.a.d("\n\n****** Network diagnostic Applet started");
    }
    
    public void destroy() {
        com.hw.client.util.a.d("\n\n****** Network diagnostic Applet destroy");
        super.destroy();
        com.hw.client.util.a.d("** Network diagnostic destroyed");
    }
    
    private void a() {
        if (this.h.length() > 0) {
            this.c += this.h.toString();
        }
        try {
            this.g.showDocument(new URL(this.f, this.c), this.e);
        }
        catch (MalformedURLException ex) {
            com.hw.client.util.a.b("Malformed URL", ex);
        }
    }
    
    public void run() {
        if ("TEST_THREAD".equals(Thread.currentThread().getName())) {
            URL url;
            try {
                url = new URL(this.a);
            }
            catch (MalformedURLException ex2) {
                com.hw.client.util.a.d("NetworkDiagnosticApplet.runTest: URL of the server cannot be understood by the applet:" + this.a);
                this.a();
                return;
            }
            final boolean b = this.b(url);
            final boolean a = this.a(url);
            if (b || a) {
                if (this.h.length() > 0) {
                    this.b += this.h.toString();
                }
                try {
                    com.hw.client.util.a.b("result sent at " + this.b + " in frame " + this.d);
                    this.g.showDocument(new URL(this.f, this.b), this.d);
                    return;
                }
                catch (MalformedURLException ex) {
                    com.hw.client.util.a.b("Malformed URL", ex);
                    return;
                }
            }
            this.a();
        }
    }
    
    private boolean a(final URL url) {
        HttpURLConnection httpURLConnection;
        try {
            httpURLConnection = (HttpURLConnection)url.openConnection();
        }
        catch (IOException ex) {
            com.hw.client.util.a.d("NetworkDiagnosticApplet.javaUrlConnectionTest: Cannot connect to URL=" + this.a + ", exc=:" + ex);
            return false;
        }
        com.hw.client.util.a.c("Get connection to " + this.a + "...");
        this.h.append('&').append("get_nonperstnt").append('=');
        int responseCode;
        String responseMessage;
        try {
            httpURLConnection.connect();
            responseCode = httpURLConnection.getResponseCode();
            responseMessage = httpURLConnection.getResponseMessage();
            com.hw.client.util.a.c("Java connection got " + responseCode + " " + responseMessage);
        }
        catch (IOException ex2) {
            com.hw.client.util.a.d("NetworkDiagnosticApplet.javaUrlConnectionTest: Error connecting to URL=" + this.a + ", exc=:" + ex2);
            this.h.append("fail");
            return false;
        }
        if (responseCode < 200 || responseCode > 300) {
            com.hw.client.util.a.c(url + " got:" + responseCode + responseMessage);
            this.h.append("fail");
            return false;
        }
        this.h.append("ok");
        HttpURLConnection httpURLConnection2;
        try {
            (httpURLConnection2 = (HttpURLConnection)url.openConnection()).setRequestMethod("POST");
        }
        catch (IOException ex3) {
            com.hw.client.util.a.d("NetworkDiagnosticApplet.javaUrlConnectionTest: Cannot connect to URL=" + this.a + ", exc=:" + ex3);
            return false;
        }
        com.hw.client.util.a.c("Post connection to " + this.a + "...");
        this.h.append('&').append("post_nonperstnt").append('=');
        int responseCode2;
        String responseMessage2;
        try {
            httpURLConnection2.connect();
            responseCode2 = httpURLConnection2.getResponseCode();
            responseMessage2 = httpURLConnection2.getResponseMessage();
            com.hw.client.util.a.c("Java connection got " + responseCode2 + " " + responseMessage2);
        }
        catch (IOException ex4) {
            com.hw.client.util.a.d("NetworkDiagnosticApplet.javaUrlConnectionTest: Error connecting to URL=" + this.a + ", exc=:" + ex4);
            this.h.append("fail");
            return false;
        }
        if (responseCode2 < 200 || responseCode2 > 300) {
            com.hw.client.util.a.c(url + " got:" + responseCode2 + responseMessage2);
            this.h.append("fail");
            return false;
        }
        this.h.append("ok");
        return true;
    }
    
    private boolean b(final URL url) {
        final aj aj;
        (aj = new aj(url.getHost(), url.getPort())).a(20000);
        com.hw.client.util.a.c("Get connection to " + this.a + "...");
        this.h.append('&').append("get_persistent").append('=');
        int a;
        String b;
        try {
            final cM a2;
            a = (a2 = aj.a(url.getFile())).a();
            b = a2.b();
            com.hw.client.util.a.c("Internal connection got " + a + " " + b);
        }
        catch (Exception ex) {
            com.hw.client.util.a.b("NetworkDiagnosticApplet.internalUrlConnectionTest: ", ex);
            this.h.append("fail");
            return false;
        }
        if (a < 200 || a > 300) {
            com.hw.client.util.a.c(url + " got:" + a + b);
            this.h.append("fail");
            return false;
        }
        this.h.append("ok");
        final aj aj2;
        (aj2 = new aj(url.getHost(), url.getPort())).a(10000);
        com.hw.client.util.a.c("Post connection to " + this.a + "...");
        this.h.append('&').append("post_persistent").append('=');
        int a3;
        String b3;
        try {
            final cM b2;
            a3 = (b2 = aj2.b(url.getFile())).a();
            b3 = b2.b();
            com.hw.client.util.a.c("Internal connection got " + a3 + " " + b3);
        }
        catch (Exception ex2) {
            com.hw.client.util.a.b("NetworkDiagnosticApplet.internalUrlConnectionTest: ", ex2);
            this.h.append("fail");
            return false;
        }
        if (a3 < 200 || a3 > 300) {
            com.hw.client.util.a.c(url + " got:" + a3 + b3);
            this.h.append("fail");
            return false;
        }
        this.h.append("ok");
        return true;
    }
}
