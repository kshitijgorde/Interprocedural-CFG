// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.b.a;

import java.awt.image.ImageObserver;
import java.awt.Shape;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import com.daysofwonder.b.a;

public class b implements a
{
    private Graphics a;
    
    public b(final Graphics a) {
        this.a = a;
    }
    
    public void a(final int n, final float n2) {
    }
    
    public void a() {
    }
    
    public void a(final Font font) {
        this.a.setFont(font);
    }
    
    public Font b() {
        return this.a.getFont();
    }
    
    public void a(final Color color) {
        this.a.setColor(color);
    }
    
    public Color c() {
        return this.a.getColor();
    }
    
    public a a(final int n, final int n2, final int n3, final int n4) {
        return new b(this.a.create(n, n2, n3, n4));
    }
    
    public FontMetrics d() {
        return this.a.getFontMetrics();
    }
    
    public void a(final int n, final int n2) {
        this.a.translate(n, n2);
    }
    
    public void e() {
        this.a.setPaintMode();
    }
    
    public void b(final Color xorMode) {
        this.a.setXORMode(xorMode);
    }
    
    public void b(final int n, final int n2, final int n3, final int n4) {
        this.a.setClip(n, n2, n3, n4);
    }
    
    public Shape f() {
        return this.a.getClip();
    }
    
    public void a(final Shape clip) {
        this.a.setClip(clip);
    }
    
    public void c(final int n, final int n2, final int n3, final int n4) {
        this.a.drawRect(n, n2, n3, n4);
    }
    
    public void d(final int n, final int n2, final int n3, final int n4) {
        this.a.fillRect(n, n2, n3, n4);
    }
    
    public void e(final int n, final int n2, final int n3, final int n4) {
        this.a.drawLine(n, n2, n3, n4);
    }
    
    public void a(final String s, final int n, final int n2) {
        this.a.drawString(s, n, n2);
    }
    
    public boolean a(final com.daysofwonder.b.b b, final int n, final int n2, final ImageObserver imageObserver) {
        return b != null && this.a.drawImage(((com.daysofwonder.b.a.a)b).b(), n, n2, imageObserver);
    }
    
    public boolean a(final com.daysofwonder.b.b b, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final ImageObserver imageObserver) {
        return b != null && this.a.drawImage(((com.daysofwonder.b.a.a)b).b(), n, n2, n3, n4, n5, n6, n7, n8, imageObserver);
    }
    
    public void g() {
        this.a.dispose();
    }
    
    public Object h() {
        return this.a;
    }
}
