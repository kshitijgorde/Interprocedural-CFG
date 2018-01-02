// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Toolkit;
import java.util.Hashtable;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class i implements MouseListener
{
    final /* synthetic */ aI a;
    
    i(final aI a) {
        this.a = a;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this.a.c.contains(mouseEvent.getPoint())) {
            this.a.a(mouseEvent);
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (this.a.c.contains(mouseEvent.getPoint())) {
            this.a.b(mouseEvent);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.a.c.contains(mouseEvent.getPoint())) {
            this.a.f(mouseEvent);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
}
