// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.net.URLEncoder;
import java.io.PrintWriter;
import nanoxml.XMLElement;
import javax.xml.xpath.XPathExpressionException;
import java.net.HttpURLConnection;
import java.util.Iterator;
import java.util.Hashtable;
import java.util.ArrayList;
import java.net.URL;

public class aG
{
    private static String a;
    private static String b;
    private final String c;
    private final String d;
    private final URL e;
    private URL f;
    private String g;
    private static /* synthetic */ boolean h;
    
    public aG(final String c, final String d, final URL e, final URL f) {
        if (!aG.h && (c == null || c.length() <= 0)) {
            throw new AssertionError();
        }
        if (!aG.h && (d == null || d.length() <= 0)) {
            throw new AssertionError();
        }
        if (!aG.h && e == null) {
            throw new AssertionError();
        }
        if (!aG.h && f == null) {
            throw new AssertionError();
        }
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
    
    public final void a(final URL url, final ArrayList list, final String s, String d, final boolean b) {
        final Object[] a = this.a(url, this.c, this.d, s, d, list, b);
        if (!aG.h && a.length != 4) {
            throw new AssertionError();
        }
        this.g = (String)a[0];
        System.out.println("makeBatchInitRequest: batch=" + this.g + ", status " + (int)a[1]);
        final Hashtable hashtable = (Hashtable)a[2];
        final Hashtable hashtable2 = (Hashtable)a[3];
        final Iterator<aD> iterator = list.iterator();
        while (iterator.hasNext()) {
            final aD ad;
            d = ap.d((ad = iterator.next()).a);
            System.out.println("<<< " + ad.a);
            ad.d = hashtable.get(d);
            System.out.println(">>> " + hashtable2.get(d));
            ad.e = new URL(hashtable2.get(d));
        }
    }
    
    private Object[] a(final URL url, final String s, String content, final String s2, final String s3, final ArrayList list, final boolean b) {
        if (!aG.h && list.size() <= 0) {
            throw new AssertionError();
        }
        final HttpURLConnection httpURLConnection;
        (httpURLConnection = (HttpURLConnection)url.openConnection()).setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        a(s, content, s2, s3, list, b, httpURLConnection);
        final String a;
        if ((a = ap.a(httpURLConnection.getInputStream(), "Cp1252")) == null) {
            throw new u();
        }
        final String a2 = ap.a(a);
        try {
            final String replaceAll = a2.replaceAll("\r\n", "; ");
            System.out.println("SERVER SAYS (batch_init):  " + replaceAll);
            final XMLElement a4;
            final int a3 = a(a4 = aa.a(ap.d(replaceAll)));
            switch (a3) {
                case 0: {
                    final XMLElement xmlElement = a4;
                    if (!aG.h && xmlElement == null) {
                        throw new AssertionError();
                    }
                    content = aa.c(xmlElement, "//uploadBatchID").getContent();
                    final Hashtable hashtable = new Hashtable();
                    final Hashtable hashtable2 = new Hashtable();
                    this.a(a4, hashtable, hashtable2, list);
                    return new Object[] { content, a3, hashtable, hashtable2 };
                }
                default: {
                    throw new u("Unexpected status code from batch_init: " + a3);
                }
            }
        }
        catch (XPathExpressionException ex2) {
            final XPathExpressionException ex = ex2;
            ex2.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
    
    private static void a(final String s, String s2, String string, final String s3, final ArrayList list, final boolean b, final HttpURLConnection httpURLConnection) {
        final PrintWriter printWriter;
        (printWriter = new PrintWriter(httpURLConnection.getOutputStream())).print("appContextID=" + s);
        printWriter.print("&userID=" + s2);
        if (string != null && string.length() > 0) {
            printWriter.print("&albumID=" + URLEncoder.encode(string, "Cp1252"));
        }
        if (s3 != null && s3.length() > 0) {
            printWriter.print("&albumName=" + URLEncoder.encode(s3, "Cp1252"));
        }
        printWriter.print("&uploadTypeID=4");
        printWriter.print("&isResized=" + (b ? "1" : "0"));
        printWriter.print("&num=" + list.size());
        for (int i = 0; i < list.size(); ++i) {
            if (!au.d(s2 = list.get(i).a) && !au.h(s2)) {
                s2 += ".jpg";
            }
            string = String.format("&localPath%d=", i) + URLEncoder.encode(s2, "Cp1252");
            System.out.println("@@@@ " + string);
            printWriter.print(String.format("&localPath%d=", i) + URLEncoder.encode(s2, "Cp1252"));
            printWriter.print(String.format("&fileSize%d=", i) + list.get(i).b / 1024L);
        }
        printWriter.println();
        printWriter.close();
    }
    
    private void a(final XMLElement xmlElement, final Hashtable hashtable, final Hashtable hashtable2, final ArrayList list) {
        if (!aG.h && xmlElement == null) {
            throw new AssertionError();
        }
        final List a = aa.a(xmlElement, "//localPath");
        final List a2 = aa.a(xmlElement, "//uploadFileID");
        final List a3 = aa.a(xmlElement, "//uploadURL");
        final List a4 = aa.a(xmlElement, "//FileStatus/code");
        if (a.size() != a2.size() || a.size() != a3.size() || a.size() != a4.size()) {
            throw new RuntimeException("Invalid response from server");
        }
        for (int i = 0; i < a.size(); ++i) {
            String substring = a.get(i);
            if (!a4.get(i).equals("0")) {
                throw new u("Invalid response from server -- file status not 0: " + au.a(substring));
            }
            if (!a(list, substring)) {
                if (!aG.h && !substring.endsWith(".jpg")) {
                    throw new AssertionError();
                }
                substring = substring.substring(0, substring.length() - 4);
            }
            final String s = a2.get(i);
            final String s2 = a3.get(i);
            hashtable.put(substring, s);
            hashtable2.put(substring, s2);
        }
    }
    
    private static boolean a(final ArrayList list, final String s) {
        final Iterator<aD> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().a.equals(s)) {
                return true;
            }
        }
        return false;
    }
    
    public static int a(XMLElement c) {
        if (!aG.h && c == null) {
            throw new AssertionError();
        }
        return Integer.parseInt((c = aa.c(c, "//Status/code")).getContent());
    }
    
    public final String a(final String s, final String s2, final boolean b, final int n, final int n2, final boolean b2) {
        if (!aG.h && (s == null || s.length() <= 0)) {
            throw new AssertionError();
        }
        if (!aG.h && s2 == null) {
            throw new AssertionError();
        }
        if (!aG.h && (this.c == null || this.c.length() <= 0)) {
            throw new AssertionError();
        }
        if (!aG.h && (this.d == null || this.d.length() <= 0)) {
            throw new AssertionError();
        }
        if (!aG.h && (this.g == null || this.g.length() <= 0)) {
            throw new AssertionError();
        }
        final StringBuffer sb;
        (sb = new StringBuffer()).append(aG.a);
        sb.append(String.format("Content-Disposition: form-data; name=\"%s\"\r\n\r\n", "appContextID"));
        sb.append(this.c + "\r\n");
        sb.append(aG.a);
        sb.append(String.format("Content-Disposition: form-data; name=\"%s\"\r\n\r\n", "userID"));
        sb.append(this.d + "\r\n");
        sb.append(aG.a);
        sb.append(String.format("Content-Disposition: form-data; name=\"%s\"\r\n\r\n", "uploadBatchID"));
        sb.append(this.g + "\r\n");
        sb.append(aG.a);
        sb.append(String.format("Content-Disposition: form-data; name=\"%s\"\r\n\r\n", "uploadFileID"));
        sb.append(s + "\r\n");
        sb.append(aG.a);
        sb.append(String.format("Content-Disposition: form-data; name=\"%s\"\r\n\r\n", "downscaleAtServer"));
        sb.append((b ? "1" : "0") + "\r\n");
        sb.append(aG.a);
        sb.append(String.format("Content-Disposition: form-data; name=\"%s\"\r\n\r\n", "originalHeight"));
        sb.append(n + "\r\n");
        sb.append(aG.a);
        sb.append(String.format("Content-Disposition: form-data; name=\"%s\"\r\n\r\n", "originalWidth"));
        sb.append(n2 + "\r\n");
        sb.append(aG.a);
        sb.append(String.format("Content-Disposition: form-data; name=\"%s\"\r\n\r\n", "downscaleAtClient"));
        sb.append((b2 ? "1" : "0") + "\r\n");
        sb.append(aG.a);
        sb.append(String.format("content-disposition: form-data; name=\"%s\"; filename=\"%s\"\r\n", "filedata", s2));
        sb.append(String.format("Content-Type: %s\r\n", "image/jpeg"));
        sb.append("Content-Transfer-Encoding: binary\r\n\r\n");
        return sb.toString();
    }
    
    public static String a() {
        return String.format("multipart/form-data, boundary=%s", "AaB03x314159t0ddwuZHere94dEAdBeeF6942");
    }
    
    public static String b() {
        return "\r\n" + aG.b;
    }
    
    public final String c() {
        return this.g;
    }
    
    public final String d() {
        return this.d;
    }
    
    public final void a(String string, final Exception ex) {
        try {
            string = "appContextID=" + this.c + "&" + "uploadBatchID" + "=" + this.g + "&" + "uploadFileID" + "=" + string + "&" + "msg" + "=" + ex.toString();
            System.out.println("reporting error to server @ " + this.e);
            final HttpURLConnection httpURLConnection;
            (httpURLConnection = (HttpURLConnection)this.e.openConnection()).setRequestMethod("POST");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setReadTimeout(5000);
            final OutputStreamWriter outputStreamWriter;
            (outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream())).write(string);
            outputStreamWriter.flush();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            while (bufferedReader.readLine() != null) {}
            bufferedReader.close();
            outputStreamWriter.close();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }
    
    public final void a(String string) {
        if (this.f == null) {
            System.out.println("** NOT sending log to server because I don't know the receiver page URL **");
            return;
        }
        try {
            string = "appContextID=" + this.c + "&" + "uploadBatchID" + "=" + this.g + "&" + "userID" + "=" + this.d + "&" + "logText" + "=" + URLEncoder.encode(string, "UTF-8");
            final HttpURLConnection httpURLConnection;
            (httpURLConnection = (HttpURLConnection)this.f.openConnection()).setRequestMethod("POST");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setReadTimeout(5000);
            final OutputStreamWriter outputStreamWriter;
            (outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream())).write(string);
            outputStreamWriter.flush();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            while (bufferedReader.readLine() != null) {}
            bufferedReader.close();
            outputStreamWriter.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    static {
        aG.h = !aG.class.desiredAssertionStatus();
        aG.a = String.format("--%s\r\n", "AaB03x314159t0ddwuZHere94dEAdBeeF6942");
        aG.b = String.format("--%s--\r\n", "AaB03x314159t0ddwuZHere94dEAdBeeF6942");
    }
}
