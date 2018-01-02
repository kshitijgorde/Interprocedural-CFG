// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.List;
import java.util.Collections;
import java.util.Vector;
import java.awt.Dimension;
import java.util.Iterator;
import java.util.Enumeration;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.Hashtable;
import java.util.Observer;
import java.util.Observable;
import java.io.IOException;
import java.util.ArrayList;
import java.net.URL;

final class N extends Thread
{
    private /* synthetic */ URL a;
    private /* synthetic */ URL b;
    private /* synthetic */ URL c;
    private /* synthetic */ ArrayList d;
    private /* synthetic */ String e;
    private /* synthetic */ String f;
    private /* synthetic */ String g;
    private /* synthetic */ String h;
    private /* synthetic */ w i;
    
    N(final w i, final URL a, final URL b, final URL c, final ArrayList d, final String e, final String f, final String g, final String h) {
        this.i = i;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
    }
    
    public final void run() {
        try {
            this.i.c.a(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i.g);
        }
        catch (IOException ex) {
            ex.printStackTrace();
            this.i.b();
        }
        catch (u u) {
            u.printStackTrace();
            this.i.b();
        }
    }
}
