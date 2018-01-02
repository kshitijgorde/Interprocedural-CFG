// 
// Decompiled by Procyon v0.5.30
// 

package com.agentcy.applet.matrixcode;

import java.awt.Graphics2D;
import java.awt.Component;
import java.awt.MediaTracker;
import java.applet.Applet;
import java.awt.Image;
import java.awt.image.ImageObserver;

class CharacterPainter implements ImageObserver
{
    private final int _numOfCharacters = 27;
    private int _charHeight;
    private int _charWidth;
    private Image _image;
    static /* synthetic */ Class class$com$agentcy$applet$matrixcode$MatrixCodeSimulation;
    
    CharacterPainter(final Applet applet) {
        this._image = this.getDemoImage(applet);
        int i;
        do {
            i = this._image.getWidth(this);
        } while (i == -1);
        int j;
        do {
            j = this._image.getHeight(this);
        } while (j == -1);
        this._charWidth = i / 2;
        this._charHeight = j / 27;
    }
    
    private Image getDemoImage(final Applet applet) {
        final Image image = applet.getToolkit().getImage(((CharacterPainter.class$com$agentcy$applet$matrixcode$MatrixCodeSimulation == null) ? (CharacterPainter.class$com$agentcy$applet$matrixcode$MatrixCodeSimulation = class$("com.agentcy.applet.matrixcode.MatrixCodeSimulation")) : CharacterPainter.class$com$agentcy$applet$matrixcode$MatrixCodeSimulation).getResource("small_matrix_font.png"));
        try {
            final MediaTracker mediaTracker = new MediaTracker(applet);
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForID(0);
        }
        catch (Exception ex) {}
        return image;
    }
    
    public void paintCharacter(final int n, final Graphics2D graphics2D, final int n2, final int n3, final boolean b) {
        final int n4 = n2 * this._charWidth;
        final int n5 = n3 * this._charHeight;
        final int n6 = b ? this._charWidth : 0;
        final int n7 = n * this._charHeight;
        graphics2D.drawImage(this._image, n4, n5, n4 + this._charWidth, n5 + this._charHeight, n6, n7, n6 + this._charWidth, n7 + this._charHeight, this);
    }
    
    public void fadeCharacter() {
    }
    
    public void eraseCharacter(final Graphics2D graphics2D, final int n, final int n2) {
        graphics2D.clearRect(n * this._charWidth, n2 * this._charHeight, this._charWidth, this._charHeight);
    }
    
    public int getNumOfCharacters() {
        return 27;
    }
    
    public int getCharWidth() {
        return this._charWidth;
    }
    
    public int getCharHeight() {
        return this._charHeight;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        return false;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
