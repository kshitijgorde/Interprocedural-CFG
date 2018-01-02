import java.awt.Event;
import java.awt.image.ImageObserver;
import java.util.Vector;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

final class SeekCanvas3 extends Canvas
{
    SeekAWord3ech saw;
    FontMetrics fm;
    int width;
    int height;
    int cellsize;
    int[] mc;
    Graphics bufferG;
    Graphics foundG;
    Image bufferI;
    Image foundI;
    Color loopcolor;
    
    SeekCanvas3(final SeekAWord3ech saw, final int width, final int height) {
        this.loopcolor = Color.blue;
        this.saw = saw;
        this.width = width;
        this.height = height;
        this.mc = new int[4];
        for (int i = 0; i < 4; ++i) {
            this.mc[i] = -1;
        }
    }
    
    public Dimension mininmumSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void setLoopColor(final Color loopcolor) {
        this.loopcolor = loopcolor;
    }
    
    public void drawFoundWords() {
        final char[][] letters = this.saw.letters;
        this.cellsize = this.width / letters[0].length;
        this.foundG.setColor(this.getBackground());
        this.foundG.fillRect(0, 0, this.width, this.height);
        this.foundG.setColor(this.getForeground());
        for (int i = 0; i < letters.length; ++i) {
            for (int j = 0; j < letters[0].length; ++j) {
                this.foundG.drawString(String.valueOf(letters[i][j]), this.cellsize * j + (this.cellsize - this.fm.stringWidth(String.valueOf(letters[i][j]))) / 2, i * this.cellsize + (this.cellsize + this.fm.getMaxAscent()) / 2);
            }
        }
        final Vector found_coords = this.saw.found_coords;
        for (int k = 0; k < found_coords.size(); ++k) {
            this.drawLoop(this.foundG, found_coords.elementAt(k));
        }
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        if (this.bufferI == null) {
            this.bufferI = this.createImage(this.width, this.height);
            (this.bufferG = this.bufferI.getGraphics()).setFont(this.getFont());
            this.foundI = this.createImage(this.width, this.height);
            (this.foundG = this.foundI.getGraphics()).setFont(this.getFont());
            this.fm = this.foundG.getFontMetrics();
            this.drawFoundWords();
        }
        this.bufferG.drawImage(this.foundI, 0, 0, null);
        if (this.mc[0] > -1) {
            this.drawLoop(this.bufferG, this.mc);
        }
        graphics.drawImage(this.bufferI, 0, 0, null);
    }
    
    public void drawLoop(final Graphics graphics, final int[] array) {
        graphics.setColor(this.loopcolor);
        final int n = array[0];
        final int n2 = array[1];
        final int n3 = array[2];
        final int n4 = array[3];
        final int calculateAngle = this.calculateAngle(n, n2, n3, n4);
        final int n5 = (calculateAngle + 90) % 360;
        final int n6 = (calculateAngle + 270) % 360;
        final double n7 = n5 * 3.141592653589793 / 180.0;
        final double n8 = n6 * 3.141592653589793 / 180.0;
        final int n9 = this.cellsize / 2 - 1;
        final int n10 = (int)(n * this.cellsize + this.cellsize / 2 + Math.cos(n7) * n9);
        final int n11 = (int)(n2 * this.cellsize + this.cellsize / 2 - Math.sin(n7) * n9);
        final int n12 = (int)(n3 * this.cellsize + this.cellsize / 2 + Math.cos(n8) * n9);
        final int n13 = (int)(n4 * this.cellsize + this.cellsize / 2 - Math.sin(n8) * n9);
        final int n14 = (int)(n * this.cellsize + this.cellsize / 2 + Math.cos(n8) * n9);
        final int n15 = (int)(n2 * this.cellsize + this.cellsize / 2 - Math.sin(n8) * n9);
        final int n16 = (int)(n3 * this.cellsize + this.cellsize / 2 + Math.cos(n7) * n9);
        final int n17 = (int)(n4 * this.cellsize + this.cellsize / 2 - Math.sin(n7) * n9);
        graphics.drawArc(n * this.cellsize + 1, n2 * this.cellsize + 1, n9 * 2, n9 * 2, n5, 180);
        graphics.drawArc(n3 * this.cellsize + 1, n4 * this.cellsize + 1, n9 * 2, n9 * 2, n6, 180);
        graphics.drawLine(n10, n11, n16, n17);
        graphics.drawLine(n14, n15, n12, n13);
    }
    
    public int calculateAngle(final int n, final int n2, final int n3, final int n4) {
        int n5 = (int)(Math.atan((n4 - n2) / (n - n3)) * 180.0 / 3.141592653589793);
        if (n3 > n) {
            if (n2 == n4) {
                n5 = 0;
            }
            else if (n4 > n2) {
                n5 += 360;
            }
        }
        else {
            n5 += 180;
        }
        return n5;
    }
    
    public void checkCoords(final int[] array) {
        final int abs = Math.abs(array[0] - array[2]);
        final int abs2 = Math.abs(array[1] - array[3]);
        if (abs == 0 || abs2 == 0 || abs == abs2) {
            final int max = Math.max(abs, abs2);
            final char[][] letters = this.saw.letters;
            String s = String.valueOf(letters[array[1]][array[0]]);
            String s2 = String.valueOf(letters[array[3]][array[2]]);
            for (int i = 0; i < max; ++i) {
                s += letters[array[1] + (i + 1) * (array[3] - array[1]) / max][array[0] + (i + 1) * (array[2] - array[0]) / max];
                s2 += letters[array[3] + (i + 1) * (array[1] - array[3]) / max][array[2] + (i + 1) * (array[0] - array[2]) / max];
            }
            int j = 0;
            while (j < this.saw.placed_words) {
                if (s.equals(this.saw.candidates[j]) || s2.equals(this.saw.candidates[j])) {
                    if (!this.saw.word_found[j]) {
                        final int[] array2 = new int[4];
                        for (int k = 0; k < 4; ++k) {
                            array2[k] = array[k];
                        }
                        this.saw.word_found[j] = true;
                        this.saw.found_coords.addElement(array2);
                        final SeekAWord3ech saw = this.saw;
                        if (saw.found_sound != null) {
                            saw.found_sound.play();
                        }
                        this.saw.makeList();
                        this.drawFoundWords();
                        return;
                    }
                    break;
                }
                else {
                    ++j;
                }
            }
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.mc[0] = (this.mc[2] = n / this.cellsize);
        this.mc[1] = (this.mc[3] = n2 / this.cellsize);
        this.repaint();
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        this.mc[2] = n / this.cellsize;
        this.mc[3] = n2 / this.cellsize;
        this.repaint();
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.mc[2] = n / this.cellsize;
        this.mc[3] = n2 / this.cellsize;
        this.checkCoords(this.mc);
        for (int i = 0; i < 4; ++i) {
            this.mc[i] = -1;
        }
        this.repaint();
        return true;
    }
}
