import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

package br.com.bb.aapj.bbteclado;

import com.ms.com.StdCOMClassObject;
import java.awt.event.TextEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.Cursor;
import netscape.javascript.JSObject;
import java.awt.image.ImageObserver;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.awt.Label;
import java.awt.Component;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Toolkit;
import java.awt.TextField;
import java.awt.Font;
import java.util.Random;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.event.TextListener;
import java.awt.event.ComponentListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

public final class b extends MouseAdapter
{
    public final /* synthetic */ CampoTeclado a;
    
    public b(final CampoTeclado a) {
        this.a = a;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (this.a.d(x, y)) {
            final int b = this.a.g(x, y);
            if (b != -1 && CampoTeclado.a(this.a) < 8) {
                CampoTeclado.b(this.a)[CampoTeclado.a(this.a)] = b;
                final CampoTeclado a = this.a;
                CampoTeclado.a(a, CampoTeclado.a(a) + 1);
                this.a.repaint();
            }
            CampoTeclado.c(this.a, true);
            CampoTeclado.a(this.a, true);
            CampoTeclado.d(this.a, true);
            CampoTeclado.e(this.a, false);
            CampoTeclado.b(this.a, true);
            this.a.repaint();
        }
        else if (this.a.b(x, y)) {
            final int d = this.a.c(x, y);
            if (d == 1 && CampoTeclado.c(this.a) < 4) {
                CampoTeclado.b(this.a, (CampoTeclado.c(this.a) < 4) ? (CampoTeclado.c(this.a) + 1) : 4);
                this.a.a(CampoTeclado.c(this.a));
                CampoTeclado.d(this.a, CampoTeclado.c(this.a));
                CampoTeclado.b(this.a, true);
                CampoTeclado.e(this.a, false);
                this.a.repaint();
            }
            else if (d == 2 && CampoTeclado.c(this.a) > 0) {
                CampoTeclado.b(this.a, (CampoTeclado.c(this.a) > 0) ? (CampoTeclado.c(this.a) - 1) : 0);
                this.a.a(CampoTeclado.c(this.a));
                CampoTeclado.d(this.a, CampoTeclado.c(this.a));
                CampoTeclado.b(this.a, true);
                CampoTeclado.e(this.a, false);
                this.a.repaint();
            }
        }
        else if (this.a.f(x, y)) {
            if (CampoTeclado.a(this.a) > 0) {
                final CampoTeclado a2 = this.a;
                CampoTeclado.a(a2, CampoTeclado.a(a2) - 1);
                CampoTeclado.b(this.a)[CampoTeclado.a(this.a)] = -1;
                CampoTeclado.e(this.a, false);
                this.a.repaint();
            }
        }
        else if (this.a.a(x, y)) {
            CampoTeclado.f(this.a, true);
            CampoTeclado.g(this.a, !CampoTeclado.d(this.a));
            CampoTeclado.e(this.a, false);
            this.a.repaint();
        }
        else if (this.a.e(x, y)) {
            CampoTeclado.d(this.a, true);
            CampoTeclado.e(this.a, false);
            this.a.repaint();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (CampoTeclado.e(this.a)) {
            new Thread() {
                public final /* synthetic */ CampoTeclado a = this.a;
                
                public void run() {
                    try {
                        Thread.sleep(350L);
                    }
                    catch (InterruptedException ex) {}
                    CampoTeclado.a(((d)this).a, false);
                    CampoTeclado.b(((d)this).a, true);
                    ((d)this).a.repaint();
                }
            }.start();
        }
    }
}
