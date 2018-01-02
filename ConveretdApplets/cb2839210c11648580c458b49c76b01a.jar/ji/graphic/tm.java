// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.GridLayout;
import java.util.StringTokenizer;
import java.awt.Label;
import java.awt.Insets;
import ji.v1base.b5;
import java.awt.Color;
import ji.v1event.ob;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.awt.BorderLayout;
import java.awt.event.KeyListener;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import ji.util.e;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionListener;
import java.awt.Rectangle;
import java.awt.Component;
import ji.v1event.ah;
import ji.v1base.jiPanel;
import ji.v1base.bn;
import ji.v1base.bl;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import ji.util.d;
import ji.io.h;
import ji.util.i;

class tm implements Runnable
{
    private final /* synthetic */ bk.tl a;
    
    tm(final bk.tl a) {
        this.a = a;
    }
    
    public void run() {
        this.a.b();
        try {
            if (!this.a.c.z) {
                if (i.c(124)) {
                    h.d(this.a.c.t, "Shutdown dialog: showing dialog in ".concat(String.valueOf(String.valueOf(Thread.currentThread().getName()))));
                }
                this.a.c.k();
                if (i.c(124)) {
                    h.d(this.a.c.t, "Shutdown dialog: finished showing dialog in ".concat(String.valueOf(String.valueOf(Thread.currentThread().getName()))));
                }
            }
            else if (i.c(124)) {
                h.d(this.a.c.t, "Shutdown dialog: not showing dialog - resources already released");
            }
            synchronized (this.a) {
                this.a.a = true;
                this.a.notify();
            }
            // monitorexit(this.a)
        }
        catch (Exception ex) {
            d.a(ex);
        }
        if (i.c(124)) {
            h.d(this.a.c.t, "Shutdown dialog: closed listener sub-thread finished");
        }
    }
}
