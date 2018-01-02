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

class I implements a
{
    final /* synthetic */ Display a;
    
    I(final Display a) {
        this.a = a;
    }
    
    public void a() {
        try {
            if (!this.a.m_player.c()) {
                System.out.println("Starting player");
                this.a.m_player.b();
            }
            else if (this.a.m_player.e()) {
                System.out.println("Re-Starting player");
                this.a.m_loadingSec = true;
                this.a.h();
                this.a.m_player.g();
                this.a.m_player = new n(this.a, this.a.m_url, 0L, this.a.m_minBufferedSec);
                if (this.a.m_speedCheckBox != null && this.a.m_speedCheckBox.d()) {
                    this.a.m_player.a(2);
                }
                else if (this.a.m_slowCheckBox != null && this.a.m_slowCheckBox.d()) {
                    this.a.m_player.a(-2);
                }
                this.a.m_player.b();
            }
            else {
                System.out.println("Screen clicked while player paused: " + this.a.m_player.d());
                this.a.m_bufferedPause = false;
                this.a.m_player.a(!this.a.m_player.d());
            }
            this.a.h();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
