// 
// Decompiled by Procyon v0.5.30
// 

package irc.style;

import java.util.Vector;
import java.awt.Dimension;
import irc.SmileyTable;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import irc.IRCConfiguration;

public class CharactersDrawer
{
    private IRCConfiguration _ircConfiguration;
    private char[] _current;
    private int _lineSpacing;
    
    public CharactersDrawer(final IRCConfiguration ircConfiguration) {
        this._ircConfiguration = ircConfiguration;
        this._current = new char[256];
        this._lineSpacing = ircConfiguration.getI("style:linespacing");
    }
    
    private int getBitmapSmileyWidth(final int n, final ImageObserver imageObserver) {
        final Image image = this._ircConfiguration.getSmileyTable().getImage(n);
        if (image == null) {
            return 0;
        }
        return image.getWidth(imageObserver);
    }
    
    private int getBitmapSmileyHeight(final int n, final ImageObserver imageObserver) {
        final Image image = this._ircConfiguration.getSmileyTable().getImage(n);
        if (image == null) {
            return 0;
        }
        return image.getHeight(imageObserver);
    }
    
    private Object drawBitmapSmiley(final Graphics graphics, final FontMetrics fontMetrics, final int n, final int n2, int n3, final ImageObserver imageObserver) {
        final Image image = this._ircConfiguration.getSmileyTable().getImage(n);
        if (image == null) {
            return null;
        }
        n3 -= this.getBitmapSmileyHeight(n, imageObserver);
        n3 += fontMetrics.getDescent();
        graphics.drawImage(image, n2, n3, imageObserver);
        return image;
    }
    
    private String handleSmiley(String string, final String s, final char c) {
        final int index = string.indexOf(s);
        if (index == -1) {
            return string;
        }
        string = string.substring(0, index) + (char)(c + '\ue000') + string.substring(index + s.length());
        return this.handleSmiley(string, s, c);
    }
    
    public String decodeLine(String handleSmiley) {
        final SmileyTable smileyTable = this._ircConfiguration.getSmileyTable();
        for (int size = smileyTable.getSize(), i = 0; i < size; ++i) {
            handleSmiley = this.handleSmiley(handleSmiley, smileyTable.getMatch(i), (char)i);
        }
        return handleSmiley;
    }
    
    public int getWidth(final String s, final FontMetrics fontMetrics, final ImageObserver imageObserver) {
        if (this._current.length < s.length()) {
            this._current = new char[s.length() * 2];
        }
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 >= '\ue000' && char1 <= '\uf8ff') {
                final char c = (char)(char1 - '\ue000');
                final int n3 = n2 + fontMetrics.charsWidth(this._current, 0, n);
                n = 0;
                n2 = n3 + this.getBitmapSmileyWidth(c, imageObserver);
            }
            else {
                this._current[n++] = char1;
            }
        }
        return n2 + fontMetrics.charsWidth(this._current, 0, n);
    }
    
    public int getHeight(final String s, final FontMetrics fontMetrics, final ImageObserver imageObserver) {
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 >= '\ue000' && char1 <= '\uf8ff') {
                final int bitmapSmileyHeight = this.getBitmapSmileyHeight((char)(char1 - '\ue000'), imageObserver);
                if (bitmapSmileyHeight > n) {
                    n = bitmapSmileyHeight;
                }
            }
        }
        final int n2 = fontMetrics.getFont().getSize() + 1;
        if (n2 > n) {
            n = n2;
        }
        return n + this._lineSpacing;
    }
    
    public void getWidthHeight(final String s, final FontMetrics fontMetrics, final Dimension dimension, final ImageObserver imageObserver) {
        if (this._current.length < s.length()) {
            this._current = new char[s.length() * 2];
        }
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 >= '\ue000' && char1 <= '\uf8ff') {
                final char c = (char)(char1 - '\ue000');
                final int n4 = n2 + fontMetrics.charsWidth(this._current, 0, n);
                final int bitmapSmileyHeight = this.getBitmapSmileyHeight(c, imageObserver);
                if (bitmapSmileyHeight > n3) {
                    n3 = bitmapSmileyHeight;
                }
                n2 = n4 + this.getBitmapSmileyWidth(c, imageObserver);
                n = 0;
            }
            else {
                this._current[n++] = char1;
            }
        }
        final int width = n2 + fontMetrics.charsWidth(this._current, 0, n);
        final int n5 = fontMetrics.getFont().getSize() + 1;
        if (n5 > n3) {
            n3 = n5;
        }
        dimension.width = width;
        dimension.height = n3 + this._lineSpacing;
    }
    
    public void draw(final String s, final Graphics graphics, final FontMetrics fontMetrics, int n, final int n2, final ImageObserver imageObserver, Vector vector) {
        if (this._current.length < s.length()) {
            this._current = new char[s.length() * 2];
        }
        int n3 = 0;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 >= '\ue000' && char1 <= '\uf8ff') {
                final char c = (char)(char1 - '\ue000');
                graphics.drawChars(this._current, 0, n3, n, n2);
                n += fontMetrics.charsWidth(this._current, 0, n3);
                n3 = 0;
                final Object drawBitmapSmiley = this.drawBitmapSmiley(graphics, fontMetrics, c, n, n2, imageObserver);
                if (vector == null) {
                    vector = new Vector<Object>();
                }
                vector.insertElementAt(drawBitmapSmiley, vector.size());
                n += this.getBitmapSmileyWidth(c, imageObserver);
            }
            else {
                this._current[n3++] = char1;
            }
        }
        graphics.drawChars(this._current, 0, n3, n, n2);
    }
}
