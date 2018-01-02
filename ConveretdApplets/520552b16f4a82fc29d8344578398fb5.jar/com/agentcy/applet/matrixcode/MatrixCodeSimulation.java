// 
// Decompiled by Procyon v0.5.30
// 

package com.agentcy.applet.matrixcode;

import java.awt.event.WindowListener;
import javax.swing.JFrame;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.applet.Applet;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.swing.JApplet;

public class MatrixCodeSimulation extends JApplet implements ImageObserver, Runnable
{
    private Thread anim;
    private int x;
    private BufferedImage _bimg;
    private CharacterPainter _charPainter;
    private int _numOfCharacters;
    private int _numOfColumns;
    private int _charHeight;
    private int _charWidth;
    private int _charNum;
    private final int _delay = 40;
    private LineOfCode[] _lineArray;
    
    public MatrixCodeSimulation() {
        this._charNum = 0;
    }
    
    public void init() {
        this.setBackground(Color.black);
        this._charPainter = new CharacterPainter(this);
        this._numOfCharacters = this._charPainter.getNumOfCharacters();
        this._charHeight = this._charPainter.getCharHeight();
        this._charWidth = this._charPainter.getCharWidth();
        final Dimension size = this.getSize();
        this._numOfColumns = size.width / this._charWidth;
        this._lineArray = new LineOfCode[this._numOfColumns];
        for (int i = 0; i < this._numOfColumns; ++i) {
            this._lineArray[i] = new LineOfCode(i, this._charPainter, size.height / this._charHeight);
        }
    }
    
    public void updateBuffer(final int n, final int n2, final Graphics2D graphics2D) {
        for (int i = 0; i < this._numOfColumns; ++i) {
            this._lineArray[i].paint(graphics2D);
        }
    }
    
    public Graphics2D createGraphics2D(final int n, final int n2) {
        if (this._bimg == null || this._bimg.getWidth() != n || this._bimg.getHeight() != n2) {
            this._bimg = (BufferedImage)this.createImage(n, n2);
        }
        final Graphics2D graphics = this._bimg.createGraphics();
        graphics.setBackground(this.getBackground());
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        return graphics;
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        final Graphics2D graphics2D = this.createGraphics2D(size.width, size.height);
        this.updateBuffer(size.width, size.height, graphics2D);
        graphics2D.dispose();
        graphics.drawImage(this._bimg, 0, 0, this);
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        return false;
    }
    
    public static void main(final String[] array) {
        new MatrixCodeSimulation().init();
        new JFrame("Matrix Code Simulation").addWindowListener((WindowListener)new MatrixCodeSimulation.MatrixCodeSimulation$1());
    }
    
    public void start() {
        (this.anim = new Thread(this)).start();
    }
    
    public synchronized void stop() {
        this.anim = null;
        this.notify();
    }
    
    public synchronized void run() {
        while (Thread.currentThread() == this.anim) {
            this.paint(this.getGraphics());
            try {
                this.wait(40L);
            }
            catch (InterruptedException ex) {}
        }
    }
}
