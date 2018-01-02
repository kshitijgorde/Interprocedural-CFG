// 
// Decompiled by Procyon v0.5.30
// 

package com.photochannel.easyUploader;

import z.M;
import z.am;
import java.io.IOException;
import z.au;
import z.Z;
import z.aA;
import java.awt.Image;
import java.awt.Dimension;
import java.util.List;
import z.aT;
import z.C;
import z.ad;
import z.aU;
import java.util.Observable;
import z.ah;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import java.security.AccessControlException;
import java.net.MalformedURLException;
import javax.swing.ImageIcon;
import z.bg;
import java.util.TimerTask;
import java.util.Timer;
import java.awt.Component;
import java.awt.LayoutManager;
import z.ay;
import z.af;
import z.R;
import z.ax;
import z.V;
import z.z;
import z.G;
import java.io.File;
import z.ab;
import z.aO;
import z.aS;
import java.awt.Container;
import java.io.FileFilter;
import z.aj;
import z.w;
import java.util.concurrent.LinkedBlockingQueue;
import java.net.URL;
import java.util.Observer;
import java.util.Hashtable;
import java.util.ArrayList;

final class h implements Runnable
{
    private /* synthetic */ ArrayList a;
    private /* synthetic */ Hashtable b;
    private /* synthetic */ long c;
    private /* synthetic */ boolean d;
    private /* synthetic */ b e;
    
    h(final b e, final ArrayList a, final Hashtable b, final long c, final boolean d) {
        this.e = e;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    public final void run() {
        com.photochannel.easyUploader.b.a(this.e, this.a, this.b);
        this.e.v.b();
        this.e.a(com.photochannel.easyUploader.c.c);
        System.out.println("TIME (thumbnail generation) " + (System.currentTimeMillis() - this.c) / 1000L + " sec");
        if (this.d) {
            this.e.j().b();
        }
    }
}
