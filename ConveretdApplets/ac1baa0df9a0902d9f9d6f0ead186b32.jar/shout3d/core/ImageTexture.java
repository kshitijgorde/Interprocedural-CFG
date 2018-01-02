// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

import java.applet.Applet;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageTexture extends Texture implements FieldObserver
{
    static final String a = "STOP IMMEDIATELY!";
    static final String b = "Copyright (c) 1997-1999 Shout Interactive, Inc.";
    static final String c = "Contents of this file are property of Shout Interactive, Inc.";
    static final String d = "If you are reading this text, then you are in direct violation";
    static final String e = "of the terms of use and the terms of the license agreement.";
    static final String f = "YOU MUST CEASE YOUR EXAMINATION OF THIS FILE AND DESTROY THIS FILE IMMEDIATELY.";
    public final StringArrayField url;
    public final BooleanField hasAlphaTexture;
    public final BooleanField repeatS;
    public final BooleanField repeatT;
    protected i g;
    private static final short h = 1;
    private static final short i = 2;
    private static final short j = 4;
    private short k;
    private URL l;
    private boolean m;
    
    public short a() {
        if (this.g != null) {
            return this.g.l;
        }
        return 0;
    }
    
    public short b() {
        if (this.g != null) {
            return this.g.k;
        }
        return 0;
    }
    
    public ImageTexture() {
        this(null);
    }
    
    public ImageTexture(final Shout3DViewer viewer) {
        this.url = new StringArrayField(this, "url", 27, null);
        this.hasAlphaTexture = new BooleanField(this, "hasAlphaTexture", 0, false);
        this.repeatS = new BooleanField(this, "repeatS", 0, true);
        this.repeatT = new BooleanField(this, "repeatT", 0, true);
        this.k = -1;
        this.m = false;
        this.setViewer(viewer);
        this.url.addFieldObserver(this, new Integer(1));
        this.repeatS.addFieldObserver(this, new Integer(2));
        this.repeatT.addFieldObserver(this, new Integer(4));
    }
    
    public void setViewer(final Shout3DViewer viewer) {
        super.setViewer(viewer);
    }
    
    public int[][] c() {
        if (this.g != null) {
            return this.g.e;
        }
        return new int[0][0];
    }
    
    public void onFieldChange(final Field field, final Object o) {
        this.k |= (short)(int)o;
    }
    
    private void d() {
        if (this.k == 0) {
            return;
        }
        if ((this.k & 0x1) == 0x0) {
            return;
        }
        if (this.e() == null) {
            return;
        }
        final Object b = super.c.a().b(this.e());
        if (b == null) {
            if (this.hasAlphaTexture.getValue()) {
                this.g = new i(super.c, this.e(), true);
            }
            else {
                this.g = new i(super.c, this.e());
            }
            this.g.c();
            this.m = true;
            return;
        }
        this.g = (i)b;
    }
    
    public void b(final g g) {
        if (this.g != null && this.g.r) {
            return;
        }
        this.d();
        final Appearance bc = g.bc;
        if (bc != null) {
            bc.a(this);
        }
        this.k = 0;
    }
    
    private void a(final URL url) {
        try {
            String s = null;
            if (this.url != null && this.url.a != null) {
                for (int i = 0; i < this.url.a.length; ++i) {
                    if (this.url.a[i] != null && !this.url.a[0].equals("")) {
                        s = this.url.a[i];
                        break;
                    }
                }
            }
            if (s == null) {
                return;
            }
            try {
                if (s.startsWith("http") || s.startsWith("HTTP")) {
                    this.l = new URL(s);
                    return;
                }
                this.l = new URL(url.toString() + s);
            }
            catch (MalformedURLException ex) {
                System.err.println(ex);
            }
        }
        catch (Exception ex2) {
            System.err.println(ex2);
        }
    }
    
    protected void finalize() throws Throwable {
        this.url.removeFieldObserver(this);
        this.repeatS.removeFieldObserver(this);
        this.repeatT.removeFieldObserver(this);
        super.finalize();
    }
    
    private URL e() {
        if ((this.l == null || (this.k & 0x1) != 0x0) && super.c != null) {
            if (super.c.a().b() != null) {
                this.a(super.c.a().b());
            }
            else if (super.c.b() instanceof Applet) {
                this.a(((Applet)super.c.b()).getCodeBase());
            }
        }
        return this.l;
    }
}
