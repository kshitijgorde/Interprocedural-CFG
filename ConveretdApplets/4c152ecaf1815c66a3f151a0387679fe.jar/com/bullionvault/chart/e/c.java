// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.e;

import java.util.NoSuchElementException;
import java.util.Hashtable;
import java.util.StringTokenizer;
import com.bullionvault.chart.c.h;
import java.io.DataInputStream;
import java.net.URLConnection;

public final class c
{
    private URLConnection a;
    private DataInputStream b;
    private String c;
    private String d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    
    public c() {
        this.c = "";
        this.d = "";
        this.e = false;
        this.f = true;
        this.g = false;
        this.h = false;
    }
    
    public c(URLConnection a) {
        this.c = "";
        this.d = "";
        this.e = false;
        this.f = true;
        this.g = false;
        this.h = false;
        final c c = this;
        a = a;
        this = c;
        c.a = a;
        (this = this).a.connect();
        final String contentType = this.a.getContentType();
        com.bullionvault.chart.c.h.g("Content-Type: [" + contentType + "]");
        final StringTokenizer stringTokenizer;
        (stringTokenizer = new StringTokenizer(contentType, ";")).nextToken();
        final StringTokenizer stringTokenizer2;
        (stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(), "=")).nextToken();
        this.c = "--" + stringTokenizer2.nextToken();
        this.d = this.c + "--";
        com.bullionvault.chart.c.h.g("Boundary set to [" + this.c + "]");
        this.b = new DataInputStream(this.a.getInputStream());
    }
    
    private static String a(final DataInputStream dataInputStream) {
        String line = null;
        try {
            line = dataInputStream.readLine();
        }
        catch (Exception ex) {
            h.d("MultipartReader - readLine() " + ex);
        }
        return line;
    }
    
    public final f a() {
        f f = new f();
        this.h = false;
        if (this.g) {
            this.c();
        }
        else {
            this.g = true;
        }
        this = this;
        int n = 1;
        String string = null;
        while (n != 0 && this.f && !this.h) {
            final String a = a(this.b);
            com.bullionvault.chart.c.h.g("Content: " + a);
            if (a == null) {
                this.h = true;
                n = 0;
                this.e = true;
                this.f = false;
            }
            else {
                com.bullionvault.chart.c.h.g(a);
                if (a.equals(this.c)) {
                    n = 0;
                    this.h = true;
                }
                else if (a.equals(this.d)) {
                    n = 0;
                    this.h = true;
                    this.e = true;
                }
                else if (string == null) {
                    string = a;
                }
                else {
                    string = string + "\n" + a;
                }
            }
        }
        final String a2;
        if ((a2 = ((string == null) ? null : string)) == null) {
            com.bullionvault.chart.c.h.g("No Content Found in Message Part - sending back null");
            f = null;
        }
        else {
            com.bullionvault.chart.c.h.g("Found some content.");
            f.a = a2;
        }
        com.bullionvault.chart.c.h.g("MultipartReader read() - Returning back");
        return f;
    }
    
    private Hashtable c() {
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        int n = 1;
        while (n != 0 && this.f && !this.e && !this.h) {
            final String a = a(this.b);
            com.bullionvault.chart.c.h.g("Header: " + a);
            if (a == null) {
                n = 0;
                this.h = true;
                this.e = true;
                this.f = false;
            }
            else if (a.equals("")) {
                com.bullionvault.chart.c.h.g("Finished reading Part Headers");
                n = 0;
            }
            else if (a.equals(this.c)) {
                n = 0;
                this.h = true;
            }
            else {
                if (!a.equals(this.d)) {
                    com.bullionvault.chart.c.h.g("Reading Header [" + a + "]");
                    final StringTokenizer stringTokenizer = new StringTokenizer(a, ":");
                    try {
                        hashtable.put(stringTokenizer.nextToken(), stringTokenizer.nextToken());
                        continue;
                    }
                    catch (NoSuchElementException ex) {
                        throw new RuntimeException("Unable to read Header details [" + a + "]");
                    }
                    break;
                }
                n = 0;
                this.h = true;
                this.e = true;
            }
        }
        com.bullionvault.chart.c.h.g("End of Headers");
        return hashtable;
    }
    
    public final void b() {
        if (this.b != null) {
            com.bullionvault.chart.c.h.e("MultipartReader - close() - closing DataInputStream");
            new a(this.b);
        }
    }
}
