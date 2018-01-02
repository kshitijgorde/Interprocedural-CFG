// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.image.ImageObserver;
import com.diginet.digichat.client.DigiChatAppletAbstract;
import com.diginet.digichat.util.a5;
import com.esial.util.c;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Event;
import java.awt.Cursor;
import java.awt.Container;
import java.awt.FontMetrics;
import java.awt.Window;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Font;
import java.util.Vector;
import com.diginet.digichat.client.h;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Point;
import com.diginet.digichat.util.s;
import java.awt.Canvas;

public class ScrollLine extends Canvas implements Runnable, s
{
    public static final int FRAME_NONE = 0;
    public static final int FRAME_RAISED = 1;
    public static final int FRAME_SUNKEN = 2;
    private boolean fRun;
    private boolean fPress;
    private int X;
    private int iSelect;
    private int iPress;
    private int nLeft;
    private int nRight;
    private Point pntCursor;
    private Dimension dimBuf;
    private Image imgBuf;
    private Graphics gBuf;
    private h h1;
    private Thread trdRun;
    private Object objSync;
    
    public ScrollLine(final h h1) {
        this.h1 = h1;
        this.gBuf = null;
        this.trdRun = null;
        this.dimBuf = null;
        this.imgBuf = null;
        final boolean b = false;
        this.fPress = b;
        this.fRun = b;
        this.pntCursor = new Point(-1, -1);
        this.objSync = new Object();
        this.iSelect = -1;
        this.setScrolls();
    }
    
    public void setScrolls() {
        boolean b = false;
        this.iSelect = -1;
        boolean visible;
        if (this.h1.nScrolls == 0) {
            visible = false;
            this.fRun = false;
            this.gBuf = null;
            this.imgBuf = null;
            this.dimBuf = null;
        }
        else {
            int nHeight = 0;
            visible = true;
            final Vector vector = new Vector<Integer>();
            for (int i = 0; i < this.h1.scrolls.b(); ++i) {
                final ScrollItem scrollItem;
                if ((scrollItem = (ScrollItem)this.h1.scrolls.d(i)).i(29) && scrollItem.i(this.h1.isMaster() ? 17 : 18)) {
                    if (scrollItem.fntText == null) {
                        scrollItem.fntText = ((scrollItem.strFont == null) ? this.h1.cc.b() : new Font(scrollItem.strFont, scrollItem.nStyle, scrollItem.nSize));
                    }
                    final FontMetrics fontMetrics;
                    final String x;
                    scrollItem.nWidth = (fontMetrics = this.getFontMetrics(scrollItem.fntText)).stringWidth(x = scrollItem.x());
                    scrollItem.yText = fontMetrics.getAscent();
                    if ((scrollItem.nHeight = fontMetrics.getHeight()) > nHeight) {
                        nHeight = scrollItem.nHeight;
                    }
                    vector.removeAllElements();
                    int index = 0;
                    int n;
                    while (index < x.length() && (n = (index = x.indexOf("://", index))) >= 0) {
                        while (n > 0 && !Character.isSpace(x.charAt(n - 1))) {
                            --n;
                        }
                        while (++index < x.length() && !Character.isSpace(x.charAt(index))) {}
                        try {
                            new URL(x.substring(n, index));
                            vector.addElement(new Integer(n));
                            vector.addElement(new Integer(index));
                        }
                        catch (MalformedURLException ex) {}
                    }
                    if (vector.size() > 0) {
                        if (vector.elementAt(vector.size() - 1) < x.length()) {
                            vector.addElement(new Integer(x.length()));
                        }
                        scrollItem.nLimits = new int[vector.size()];
                        for (int j = 0; j < vector.size(); ++j) {
                            scrollItem.nLimits[j] = vector.elementAt(j);
                        }
                    }
                    else {
                        scrollItem.nLimits = null;
                    }
                }
            }
            final boolean b2 = false;
            this.nRight = (b2 ? 1 : 0);
            this.nLeft = (b2 ? 1 : 0);
            if (this.h1.nFrame == 1 || this.h1.nFrame == 2) {
                nHeight += 4;
                this.nLeft = 2;
                this.nRight = -2;
            }
            final Dimension size;
            if ((size = this.size()).height != nHeight) {
                b = true;
                size.height = nHeight;
                this.resize(size);
            }
            this.nRight += size.width;
            this.X = (this.h1.fRight ? this.nRight : this.nLeft);
            this.fRun = true;
            if (this.trdRun == null) {
                (this.trdRun = new Thread(this)).start();
            }
        }
        synchronized (this.objSync) {
            this.objSync.notify();
        }
        // monitorexit(this.objSync)
        Container container;
        if ((container = this.getParent()) != null) {
            if (container.isVisible() != visible) {
                b = true;
                container.setVisible(visible);
            }
            if (b) {
                Container container2;
                do {
                    (container2 = container).invalidate();
                } while (!(container instanceof Window) && (container = container.getParent()) != null);
                container2.validate();
            }
        }
    }
    
    private void setSelect(int n) {
        if (this.fPress && n != this.iPress) {
            n = (this.iPress = -1);
        }
        if (this.iSelect != n) {
            final int iSelect = n;
            this.iSelect = iSelect;
            this.setCursor(Cursor.getPredefinedCursor((iSelect < 0) ? 0 : 12));
            this.postEvent(new Event(this, 7689, this.a(null)));
        }
    }
    
    public void run() {
        while (this.fRun) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.gBuf != null) {
                synchronized (this.imgBuf) {
                    int select = -1;
                    int x;
                    int n = x = this.X;
                    final Rectangle rectangle = new Rectangle();
                    this.gBuf.setColor(this.h1.cc.clrScrlBack);
                    this.gBuf.fillRect(0, 0, this.dimBuf.width, this.dimBuf.height);
                    for (int i = 0; i < this.h1.scrolls.b(); ++i) {
                        final ScrollItem scrollItem;
                        if ((scrollItem = (ScrollItem)this.h1.scrolls.d(i)).i(29) && scrollItem.i(this.h1.isMaster() ? 17 : 18)) {
                            final ScrollItem scrollItem2 = scrollItem;
                            final int x3;
                            int x2;
                            n = (x2 = (x3 = (this.h1.fRight ? x : (x - scrollItem.nWidth))));
                            scrollItem2.x = x3;
                            if (scrollItem.clrBack != null) {
                                this.gBuf.setColor(scrollItem.clrBack);
                                this.gBuf.fillRect(n, 0, scrollItem.nWidth, this.dimBuf.height);
                            }
                            boolean b = false;
                            final String x4 = scrollItem.x();
                            this.gBuf.setFont(scrollItem.fntText);
                            final FontMetrics fontMetrics = this.getFontMetrics(scrollItem.fntText);
                            final Color color = (scrollItem.clrText == null) ? this.h1.cc.clrScrlText : scrollItem.clrText;
                            int n2 = 0;
                            int n3 = 0;
                            int n4 = (scrollItem.nLimits == null) ? x4.length() : scrollItem.nLimits[0];
                            final Rectangle rectangle2 = rectangle;
                            final int height = this.dimBuf.height;
                            final Rectangle rectangle3 = rectangle;
                            final int nHeight = scrollItem.nHeight;
                            rectangle3.height = nHeight;
                            final int y = height - nHeight >> 1;
                            rectangle2.y = y;
                            final int n5 = y;
                            while (true) {
                                if (n4 > n3) {
                                    final String substring;
                                    final int stringWidth = fontMetrics.stringWidth(substring = x4.substring(n3, n4));
                                    if (b) {
                                        this.gBuf.setColor(Color.blue);
                                        final int n6 = n5 + scrollItem.nHeight - 1;
                                        this.gBuf.drawLine(x2, n6, x2 + stringWidth, n6);
                                        if (select < 0) {
                                            rectangle.x = x2;
                                            rectangle.width = stringWidth;
                                            if (rectangle.contains(this.pntCursor)) {
                                                select = (n2 - 1 << 16 | i);
                                            }
                                        }
                                    }
                                    else {
                                        this.gBuf.setColor(color);
                                    }
                                    this.gBuf.drawString(substring, x2, scrollItem.yText + n5);
                                    x2 += stringWidth;
                                }
                                if ((n3 = n4) >= x4.length()) {
                                    break;
                                }
                                b = !b;
                                n4 = scrollItem.nLimits[++n2];
                            }
                            if (this.h1.fOne || (this.h1.fRight ? ((x = x2 + this.h1.nInter) > this.nRight) : ((x = n - this.h1.nInter) < this.nLeft))) {
                                break;
                            }
                        }
                    }
                    switch (this.h1.nFrame) {
                        case 1: {
                            final int n7 = this.dimBuf.width - 1;
                            final int n8 = this.dimBuf.height - 1;
                            this.gBuf.setColor(Color.black);
                            this.gBuf.drawLine(0, 0, 0, n8 - 2);
                            this.gBuf.drawLine(0, n8 - 2, 2, n8);
                            this.gBuf.drawLine(2, n8, n7 - 2, n8);
                            this.gBuf.drawLine(n7 - 2, n8, n7, n8 - 2);
                            this.gBuf.drawLine(n7, n8 - 2, n7, 0);
                            this.gBuf.setColor(this.h1.cc.clrScrlBack.brighter());
                            this.gBuf.drawLine(1, 0, 1, n8 - 2);
                            this.gBuf.setColor(this.h1.cc.clrScrlBack.darker());
                            this.gBuf.drawLine(2, n8 - 1, n7 - 2, n8 - 1);
                            this.gBuf.drawLine(n7 - 1, n8 - 2, n7 - 1, 0);
                            this.gBuf.setColor(Color.white);
                            this.gBuf.drawLine(n7 - 2, 1, 1, 1);
                            this.gBuf.setColor(Color.black);
                            this.gBuf.drawLine(n7 - 1, 0, 1, 0);
                            break;
                        }
                        case 2: {
                            final int n9 = this.dimBuf.width - 1;
                            final int n10 = this.dimBuf.height - 1;
                            this.gBuf.setColor(Color.black);
                            this.gBuf.drawLine(2, 1, n9 - 2, 1);
                            this.gBuf.drawLine(n9 - 1, 2, n9 - 1, n10 - 2);
                            this.gBuf.drawLine(n9 - 2, n10 - 1, 2, n10 - 1);
                            this.gBuf.drawLine(1, n10 - 2, 1, 2);
                            this.gBuf.setColor(this.h1.cc.clrScrlBack.brighter());
                            this.gBuf.drawLine(2, n10, n9 - 2, n10);
                            this.gBuf.drawLine(n9 - 1, n10 - 1, n9, n10 - 2);
                            this.gBuf.drawLine(n9, n10 - 3, n9, 2);
                            this.gBuf.setColor(this.h1.cc.clrScrlBack.darker());
                            this.gBuf.drawLine(n9 - 1, 1, n9 - 2, 0);
                            this.gBuf.drawLine(n9 - 3, 0, 2, 0);
                            this.gBuf.drawLine(1, 1, 0, 2);
                            this.gBuf.drawLine(0, 3, 0, n10 - 3);
                            this.gBuf.drawLine(0, n10 - 2, 1, n10 - 1);
                            break;
                        }
                    }
                    this.setSelect(select);
                    if (this.h1.fRight) {
                        if (x < this.nLeft + this.h1.nInter) {
                            this.X = this.nRight;
                        }
                        else {
                            this.X -= this.h1.nStep;
                        }
                    }
                    else if (n > this.nRight) {
                        this.X = this.nLeft;
                    }
                    else {
                        this.X += this.h1.nStep;
                    }
                }
                // monitorexit(this.imgBuf)
            }
            this.repaint();
            final long n11;
            if ((n11 = currentTimeMillis + (this.h1.nDelay - System.currentTimeMillis())) > 0) {
                synchronized (this.objSync) {
                    Label_1301: {
                        try {
                            this.objSync.wait(n11);
                            break Label_1301;
                        }
                        catch (InterruptedException ex) {
                        }
                        // monitorexit(this.objSync)
                    }
                }
            }
        }
        this.trdRun = null;
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 503:
            case 504:
            case 506: {
                int select = -1;
                final Rectangle rectangle = new Rectangle();
                this.pntCursor.x = event.x;
                this.pntCursor.y = event.y;
                this.fPress = (this.fPress && event.id == 506);
            Label_0331:
                for (int i = 0; i < this.h1.scrolls.b(); ++i) {
                    final ScrollItem scrollItem;
                    final int[] nLimits;
                    if ((nLimits = (scrollItem = (ScrollItem)this.h1.scrolls.d(i)).nLimits) != null) {
                        final FontMetrics fontMetrics = this.getFontMetrics(scrollItem.fntText);
                        final String x;
                        rectangle.x = scrollItem.x + fontMetrics.stringWidth((x = scrollItem.x()).substring(0, nLimits[0]));
                        final Rectangle rectangle2 = rectangle;
                        final int height = this.dimBuf.height;
                        final Rectangle rectangle3 = rectangle;
                        final int nHeight = scrollItem.nHeight;
                        rectangle3.height = nHeight;
                        rectangle2.y = height - nHeight >> 1;
                        int n = 0;
                        while (true) {
                            final int n2;
                            rectangle.width = fontMetrics.stringWidth(x.substring(nLimits[n], n2 = nLimits[n + 1]));
                            if (rectangle.contains(event.x, event.y)) {
                                select = (n << 16 | i);
                                break Label_0331;
                            }
                            n += 2;
                            if (n >= nLimits.length - 1) {
                                break;
                            }
                            final Rectangle rectangle4 = rectangle;
                            rectangle4.x += rectangle.width + fontMetrics.stringWidth(x.substring(n2, nLimits[n]));
                        }
                    }
                }
                this.setSelect(select);
                break;
            }
            case 505: {
                final Point pntCursor = this.pntCursor;
                final int n3 = -1;
                pntCursor.x = n3;
                this.iSelect = n3;
                this.fPress = false;
                this.setSelect(-2);
                break;
            }
            case 501: {
                this.fPress = true;
                this.iPress = this.iSelect;
                break;
            }
            case 502: {
                if (!this.fPress) {
                    break;
                }
                this.fPress = false;
                final ScrollItem scrollItem2;
                final int[] nLimits2;
                final int n4;
                if (this.iSelect >= 0 && this.iSelect == this.iPress && (scrollItem2 = (ScrollItem)this.h1.scrolls.d(this.iPress & 0x7FFF)) != null && (nLimits2 = scrollItem2.nLimits) != null && (n4 = this.iPress >> 16) < nLimits2.length - 1) {
                    this.setSelect(-2);
                    try {
                        this.h1.a(new URL(scrollItem2.x().substring(nLimits2[n4], nLimits2[n4 + 1])), "_blank");
                    }
                    catch (MalformedURLException ex) {}
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public String a(final Object o) {
        final ScrollItem scrollItem;
        final int[] nLimits;
        final int n;
        return (this.iSelect < 0 || (scrollItem = (ScrollItem)this.h1.scrolls.d(this.iSelect & 0x7FFF)) == null || (nLimits = scrollItem.nLimits) == null || (n = this.iSelect >> 16) > nLimits.length - 2) ? c.a("Site administration information is displayed here.") : a5.a(c.a("Click here to visit %1."), new String[] { scrollItem.x().substring(nLimits[n], nLimits[n + 1]) });
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size;
        if (!(size = this.size()).equals(this.dimBuf)) {
            if (this.fRun) {
                this.dimBuf = new Dimension(size);
                final Image image;
                final Graphics graphics2;
                (graphics2 = (image = DigiChatAppletAbstract.applet.createImage(this.dimBuf.width, this.dimBuf.height)).getGraphics()).setColor(this.h1.cc.clrScrlBack);
                if (this.h1.nFrame == 1 || this.h1.nFrame == 2) {
                    this.nLeft = 2;
                    this.nRight = this.dimBuf.width - 2;
                }
                else {
                    this.nLeft = 0;
                    this.nRight = this.dimBuf.width;
                }
                this.X = (this.h1.fRight ? this.nRight : this.nLeft);
                graphics2.fillRect(0, 0, this.dimBuf.width, this.dimBuf.height);
                if (this.imgBuf != null) {
                    graphics2.drawImage(this.imgBuf, 0, 0, null);
                }
                this.imgBuf = image;
                this.gBuf = graphics2;
            }
            else {
                this.gBuf = null;
                this.dimBuf = null;
                this.imgBuf = null;
            }
        }
        if (this.imgBuf != null) {
            synchronized (this.imgBuf) {
                graphics.drawImage(this.imgBuf, 0, 0, null);
            }
            // monitorexit(this.imgBuf)
        }
    }
}
