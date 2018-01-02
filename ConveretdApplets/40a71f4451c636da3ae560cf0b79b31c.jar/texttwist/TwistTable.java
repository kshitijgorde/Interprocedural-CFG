// 
// Decompiled by Procyon v0.5.30
// 

package texttwist;

import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Graphics;
import java.util.Vector;
import sprite.AnimateSprite;
import sprite.Sprite;

public class TwistTable extends Sprite
{
    private AnimateSprite \u00d9;
    private AnimateSprite \u00da;
    private Vector \u00db;
    private int \u00dc;
    private boolean \u00dd;
    
    public void drawSprite(final Graphics graphics) {
        Graphics create = graphics;
        if (this.\u00db == null || !super.m_bVisible) {
            return;
        }
        if (super.m_rClip != null) {
            create = graphics.create();
            create.clipRect(super.m_rClip.x, super.m_rClip.y, super.m_rClip.width, super.m_rClip.height);
        }
        int n = 0;
        int n2 = 0;
        final Vector \u00fb = this.\u00db;
        int ny = super.m_nY;
        final int height = this.\u00d9.getHeight();
        final int width = this.\u00d9.getWidth();
        int n3 = \u00fb.size() - (this.getHeight() - 4) / height;
        if (n3 < 0) {
            ny -= height * n3 / 2;
        }
        for (int i = 0; i < \u00fb.size(); ++i) {
            final String s = \u00fb.elementAt(i);
            final int length = s.length();
            int nx = super.m_nX;
            if (i != 0 && (length > 4 || length != n || n3 <= 0 || n2 == 1)) {
                n2 = 0;
                ny += height;
                if (length != n) {
                    ny += 2;
                }
            }
            else if (i != 0) {
                n2 = 1;
                nx += width * length + 3;
                --n3;
            }
            n = length;
            final char char1 = s.charAt(0);
            if (char1 >= 'A' && char1 <= 'Z') {
                for (int j = 0; j < length; ++j) {
                    final char char2 = s.charAt(j);
                    this.\u00d9.setPosition(nx, ny);
                    this.\u00d9.setFrame(char2 - 'A' + '\u0001');
                    this.\u00d9.drawSprite(graphics);
                    nx += width;
                }
            }
            else if (this.\u00dd) {
                for (int k = 0; k < length; ++k) {
                    final char char3 = s.charAt(k);
                    this.\u00da.setPosition(nx, ny);
                    this.\u00da.setFrame(char3 - 'a' + '\u0001');
                    this.\u00da.drawSprite(graphics);
                    nx += width;
                }
            }
            else {
                for (int l = 0; l < length; ++l) {
                    this.\u00d9.setPosition(nx, ny);
                    this.\u00d9.setFrame(0);
                    this.\u00d9.drawSprite(graphics);
                    nx += width;
                }
            }
        }
        if (super.m_rClip != null) {
            create.dispose();
        }
    }
    
    public TwistTable(final Image image, final Image image2, final int nx, final int ny, final int nWidth, final int nHeight) {
        this.\u00d9 = new AnimateSprite(image, 9, 3, null);
        this.\u00da = new AnimateSprite(image2, 9, 3, null);
        super.m_nX = nx;
        super.m_nY = ny;
        super.m_nWidth = nWidth;
        super.m_nHeight = nHeight;
    }
    
    public boolean foundAll() {
        return this.\u00dc == this.\u00db.size();
    }
    
    public void showAll() {
        this.\u00dd = true;
        this.addToBounding();
    }
    
    public int getNumWords() {
        if (this.\u00db == null) {
            return 0;
        }
        return this.\u00db.size();
    }
    
    public void setWords(final Vector vector) {
        int n = 0;
        int n2 = 0;
        this.\u00dc = 0;
        this.\u00dd = false;
        if (this.\u00db == null) {
            this.\u00db = new Vector();
        }
        else {
            this.\u00db.removeAllElements();
        }
        for (int i = 0; i < vector.size(); ++i) {
            final String s = vector.elementAt(i);
            switch (s.length()) {
                case 3: {
                    ++n;
                    this.\u00db.insertElementAt(s, 0);
                    break;
                }
                case 4: {
                    ++n2;
                    this.\u00db.insertElementAt(s, n);
                    break;
                }
                case 5: {
                    this.\u00db.insertElementAt(s, n2 + n);
                    break;
                }
                case 6: {
                    this.\u00db.addElement(s);
                    break;
                }
            }
        }
        this.addToBounding();
    }
    
    public int guessWord(final String s) {
        final String upperCase = s.toUpperCase();
        if (this.\u00db.indexOf(upperCase) != -1) {
            return -1;
        }
        final int index;
        if ((index = this.\u00db.indexOf(s.toLowerCase())) != -1) {
            this.\u00db.setElementAt(upperCase, index);
            ++this.\u00dc;
            this.addToBounding();
            return 1;
        }
        return 0;
    }
}
