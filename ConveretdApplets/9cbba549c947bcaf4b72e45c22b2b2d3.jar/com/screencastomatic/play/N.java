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
import java.awt.image.BufferedImage;
import java.awt.Container;
import java.awt.Image;

class N implements Runnable
{
    final /* synthetic */ Image a;
    final /* synthetic */ Display b;
    
    N(final Display b, final Image a) {
        this.b = b;
        this.a = a;
    }
    
    public void run() {
        if (this.a != null) {
            this.b.m_image = this.a;
            this.b.m_updateFullImage = true;
        }
        this.b.h();
    }
}