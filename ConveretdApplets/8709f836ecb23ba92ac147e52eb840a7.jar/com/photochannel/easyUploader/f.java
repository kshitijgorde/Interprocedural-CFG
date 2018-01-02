// 
// Decompiled by Procyon v0.5.30
// 

package com.photochannel.easyUploader;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;
import z.aV;
import java.io.PrintStream;
import java.io.OutputStream;

public class f extends OutputStream
{
    private StringBuffer a;
    private OutputStream b;
    private PrintStream c;
    private PrintStream d;
    private boolean e;
    private static /* synthetic */ boolean f;
    
    public f(final String s) {
        this.a = new StringBuffer();
        this.e = false;
        if (s == null) {
            this.b = System.out;
        }
        else if (s.equals("SILENT")) {
            this.b = new aV();
        }
        else {
            try {
                this.b = new FileOutputStream(new File(s));
            }
            catch (FileNotFoundException ex2) {
                final FileNotFoundException ex = ex2;
                ex2.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
        this.c = System.out;
        this.d = System.err;
        System.setOut(new PrintStream(this));
        System.setErr(new PrintStream(this));
    }
    
    public void write(final int n) {
        if (!com.photochannel.easyUploader.f.f && this.e) {
            throw new AssertionError();
        }
        this.b.write(n);
        this.a.append(n);
    }
    
    public void write(final byte[] array) {
        if (!com.photochannel.easyUploader.f.f && this.e) {
            throw new AssertionError();
        }
        this.b.write(array);
        this.a.append(new String(array));
    }
    
    public void write(final byte[] array, final int n, final int n2) {
        if (!com.photochannel.easyUploader.f.f && this.e) {
            throw new AssertionError();
        }
        this.b.write(array, n, n2);
        this.a.append(new String(array, n, n2));
    }
    
    public final String a() {
        if (!com.photochannel.easyUploader.f.f && this.e) {
            throw new AssertionError();
        }
        this.e = true;
        System.setOut(this.c);
        System.setErr(this.d);
        if (this.b instanceof FileOutputStream) {
            try {
                ((FileOutputStream)this.b).flush();
                ((FileOutputStream)this.b).close();
                this.b = null;
            }
            catch (IOException ex) {}
        }
        final String string = this.a.toString();
        this.a = null;
        return string;
    }
    
    static {
        com.photochannel.easyUploader.f.f = !f.class.desiredAssertionStatus();
    }
}
