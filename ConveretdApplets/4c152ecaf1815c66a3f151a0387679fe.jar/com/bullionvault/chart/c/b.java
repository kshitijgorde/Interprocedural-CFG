// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.c;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import java.io.InputStream;
import java.io.IOException;
import javax.xml.parsers.SAXParserFactory;
import java.net.URL;
import org.xml.sax.helpers.DefaultHandler;

final class b extends DefaultHandler
{
    private String a;
    private f b;
    private StringBuilder c;
    private c d;
    private boolean e;
    
    public b(final String a) {
        this.a = a;
        this.c = new StringBuilder();
    }
    
    public final void a() {
        InputStream inputStream = null;
        try {
            inputStream = new URL(this.a).openConnection().getInputStream();
            final SAXParserFactory instance;
            if ((instance = SAXParserFactory.newInstance()) != null) {
                instance.newSAXParser().parse(inputStream, this);
            }
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {
            throw new IOException("Error reading feed");
        }
        finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            catch (Exception ex3) {}
        }
    }
    
    final f b() {
        return this.b;
    }
    
    public final void startElement(final String s, final String s2, final String s3, final Attributes attributes) {
        if (s3.equalsIgnoreCase("channel")) {
            this.b = new f();
            return;
        }
        if (s3.equalsIgnoreCase("item") && this.b != null) {
            this.d = new c();
            final f b = this.b;
            final c d = this.d;
            final f f = b;
            if (b.a == null) {
                f.a = new ArrayList();
            }
            f.a.add(d);
            return;
        }
        if (s3.equalsIgnoreCase("image") && this.b != null) {
            this.e = true;
        }
    }
    
    public final void endElement(final String s, String s2, final String s3) {
        if (this.b == null) {
            return;
        }
        if (s3.equalsIgnoreCase("item")) {
            this.d = null;
        }
        else if (s3.equalsIgnoreCase("image")) {
            this.e = false;
        }
        else if (s3.equalsIgnoreCase("title")) {
            if (this.d != null) {
                this.d.a = this.c.toString().trim();
            }
            else if (this.e) {
                this.c.toString().trim();
            }
            else {
                this.c.toString().trim();
            }
        }
        else if (s3.equalsIgnoreCase("link")) {
            if (this.d != null) {
                this.c.toString().trim();
            }
            else if (this.e) {
                this.c.toString().trim();
            }
            else {
                this.c.toString().trim();
            }
        }
        else if (s3.equalsIgnoreCase("description")) {
            if (this.d != null) {
                this.d.b = this.c.toString().trim();
            }
            else {
                this.c.toString().trim();
            }
        }
        else if (s3.equalsIgnoreCase("url") && this.e) {
            this.c.toString().trim();
        }
        else if (s3.equalsIgnoreCase("language")) {
            this.c.toString().trim();
        }
        else if (s3.equalsIgnoreCase("generator")) {
            this.c.toString().trim();
        }
        else if (s3.equalsIgnoreCase("copyright")) {
            this.c.toString().trim();
        }
        else if (s3.equalsIgnoreCase("pubDate") && this.d != null) {
            this.d.c = this.c.toString().trim();
        }
        else if (s3.equalsIgnoreCase("category") && this.d != null) {
            final f b = this.b;
            final String trim = this.c.toString().trim();
            final c d = this.d;
            s2 = trim;
            final f f = b;
            if (b.b == null) {
                f.b = new HashMap();
            }
            if (!f.b.containsKey(s2)) {
                f.b.put(s2, new ArrayList());
            }
            ((ArrayList<c>)f.b.get(s2)).add(d);
        }
        this.c.setLength(0);
    }
    
    public final void characters(final char[] array, final int n, final int n2) {
        this.c.append(array, n, n2);
    }
}
