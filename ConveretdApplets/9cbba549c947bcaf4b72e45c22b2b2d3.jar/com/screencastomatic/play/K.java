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
import java.awt.Point;
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
import java.awt.Rectangle;
import com.screencastomatic.play.stream.b;
import java.awt.Composite;
import java.awt.Font;
import com.screencastomatic.play.stream.i;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Container;
import com.screencastomatic.play.a.n;

class K implements Runnable
{
    final /* synthetic */ int a;
    final /* synthetic */ Display b;
    
    K(final Display b, final int a) {
        this.b = b;
        this.a = a;
    }
    
    public void run() {
        this.b.m_loadingSec = true;
        this.b.h();
        this.b.m_showingPositionMS = -1L;
        if (this.b.m_player != null) {
            this.b.m_player.g();
        }
        System.out.println("Setting position sec: " + this.a);
        try {
            this.b.m_player = new n(this.b, this.b.m_url, this.a * 1000, this.b.m_minBufferedSec);
            if (this.b.m_speedCheckBox != null && this.b.m_speedCheckBox.d()) {
                this.b.m_player.a(2);
            }
            else if (this.b.m_slowCheckBox != null && this.b.m_slowCheckBox.d()) {
                this.b.m_player.a(-2);
            }
            this.b.m_player.b();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.b.h();
    }
}
