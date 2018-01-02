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
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.awt.RenderingHints;
import java.awt.EventQueue;
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
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

class A implements ComponentListener
{
    final /* synthetic */ Display a;
    
    A(final Display a) {
        this.a = a;
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.a.m_mouseListener.a(this.a.m_playPauseRect);
        this.a.m_playPauseRect = new Rectangle(0, 0, this.a.getWidth(), this.a.getHeight());
        this.a.m_mouseListener.a(this.a.m_playPauseRect, this.a.m_playPauseEvent);
        this.a.m_mouseListener.a(this.a.m_fullscreenRect);
        this.a.m_fullscreenRect = new Rectangle(this.a.getWidth() - this.a.m_popoutButton.getWidth() - 10, 10, this.a.m_popoutButton.getWidth(), this.a.m_popoutButton.getHeight());
        this.a.m_mouseListener.a(this.a.m_fullscreenRect, this.a.m_fullscreenEvent);
        if (this.a.m_notesCheckBox != null) {
            this.a.m_mouseListener.a(this.a.m_notesCheckBox.f());
            this.a.m_notesCheckBox = null;
        }
        if (this.a.m_mouseCheckBox != null) {
            this.a.m_mouseListener.a(this.a.m_mouseCheckBox.f());
            this.a.m_mouseCheckBox = null;
        }
        if (this.a.m_clicksCheckBox != null) {
            this.a.m_mouseListener.a(this.a.m_clicksCheckBox.f());
            this.a.m_clicksCheckBox = null;
        }
        if (this.a.m_haloCheckBox != null) {
            this.a.m_mouseListener.a(this.a.m_haloCheckBox.f());
            this.a.m_haloCheckBox = null;
        }
        if (this.a.m_speedCheckBox != null) {
            this.a.m_mouseListener.a(this.a.m_speedCheckBox.f());
            this.a.m_speedCheckBox = null;
        }
        if (this.a.m_slowCheckBox != null) {
            this.a.m_mouseListener.a(this.a.m_slowCheckBox.f());
            this.a.m_slowCheckBox = null;
        }
        this.a.m_captionsMap = h.a((Graphics2D)this.a.getGraphics(), this.a.getWidth(), this.a.m_loadedCaptionsMap);
        this.a.m_image = this.a.a(this.a.m_fullImage);
        this.a.m_updateFullImage = true;
        this.a.m_updatedZoom = true;
        this.a.h();
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
}
