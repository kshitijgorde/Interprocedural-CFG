// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Collection;
import java.awt.FontMetrics;
import java.awt.geom.Area;
import java.util.Iterator;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.awt.RenderingHints;
import java.awt.EventQueue;
import java.awt.event.ComponentListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.util.TreeMap;
import java.util.Map;
import com.screencastomatic.play.stream.b;
import java.awt.Composite;
import java.awt.Font;
import com.screencastomatic.play.stream.i;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Container;
import java.io.IOException;
import java.awt.Rectangle;
import java.awt.Point;

class E implements e
{
    Point a;
    final /* synthetic */ Rectangle b;
    final /* synthetic */ long c;
    final /* synthetic */ Display d;
    
    E(final Display d, final Rectangle b, final long c) {
        this.d = d;
        this.b = b;
        this.c = c;
    }
    
    public void a(final Point a) {
        this.a = a;
        this.d.m_isDragging = true;
        if (this.d.m_player.c() && !this.d.m_player.d()) {
            if (!this.d.m_player.e()) {
                this.d.m_player.a(true);
            }
            this.d.m_dragDidPause = true;
        }
    }
    
    public void b(final Point point) {
        int width = point.x - this.b.x;
        if (width < 0) {
            width = 0;
        }
        if (width > this.b.width) {
            width = this.b.width;
        }
        this.d.m_showingPositionMS = width * this.c / this.b.width;
        this.d.h();
    }
    
    public void c(final Point point) {
        if (this.a.equals(point)) {
            this.b(point);
        }
        this.d.m_isDragging = false;
        this.d.k();
        if (this.d.m_dragDidPause) {
            try {
                this.d.m_player.b();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
            this.d.m_dragDidPause = false;
        }
    }
}
