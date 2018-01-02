import java.awt.Color;
import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

final class Digit
{
    private boolean[] led;
    private int[] pola;
    private int[] polb;
    private int digitNumber;
    private Image digitImage;
    private InfoScreen lwt;
    private Graphics dGraph;
    private Polygon p;
    private static Color ledcolor;
    private int ledred;
    private int ledblue;
    private int ledgreen;
    
    public Digit(final InfoScreen lwt) {
        this.led = new boolean[7];
        this.pola = new int[4];
        this.polb = new int[4];
        this.digitNumber = 99;
        this.lwt = lwt;
        this.digitImage = this.lwt.data.inv.getEmptyImage(15, 25);
        this.dGraph = this.digitImage.getGraphics();
        Digit.ledcolor = new Color(this.lwt.inactiveColor.getRed(), this.lwt.inactiveColor.getGreen(), this.lwt.inactiveColor.getBlue());
        this.ledred = this.lwt.inactiveColor.getRed();
        this.ledgreen = this.lwt.inactiveColor.getGreen();
        this.ledblue = this.lwt.inactiveColor.getBlue();
    }
    
    public Image getDigit(final int digitNumber) {
        this.dGraph.setColor(this.lwt.bgcolor);
        this.dGraph.fillRect(0, 0, 15, 25);
        this.dGraph.setColor(Digit.ledcolor);
        this.updateLeds(this.digitNumber = digitNumber);
        for (int i = 0; i < 7; ++i) {
            if (this.led[i]) {
                this.dGraph.fillPolygon(this.getPolygon(i));
            }
        }
        return this.digitImage;
    }
    
    private Polygon getPolygon(final int n) {
        if (n == 0) {
            this.pola[0] = 2;
            this.pola[1] = 10;
            this.pola[2] = 9;
            this.pola[3] = 3;
            this.polb[0] = 0;
            this.polb[1] = 0;
            this.polb[2] = 2;
            this.polb[3] = 2;
        }
        else if (n == 1) {
            this.pola[0] = 12;
            this.pola[1] = 10;
            this.pola[2] = 10;
            this.pola[3] = 12;
            this.polb[0] = 0;
            this.polb[1] = 1;
            this.polb[2] = 9;
            this.polb[3] = 10;
        }
        else if (n == 2) {
            this.pola[0] = 12;
            this.pola[1] = 10;
            this.pola[2] = 10;
            this.pola[3] = 12;
            this.polb[0] = 11;
            this.polb[1] = 12;
            this.polb[2] = 20;
            this.polb[3] = 21;
        }
        else if (n == 3) {
            this.pola[0] = 2;
            this.pola[1] = 10;
            this.pola[2] = 9;
            this.pola[3] = 3;
            this.polb[0] = 22;
            this.polb[1] = 22;
            this.polb[2] = 24;
            this.polb[3] = 24;
        }
        else if (n == 4) {
            this.pola[0] = 0;
            this.pola[1] = 2;
            this.pola[2] = 2;
            this.pola[3] = 0;
            this.polb[0] = 11;
            this.polb[1] = 12;
            this.polb[2] = 20;
            this.polb[3] = 21;
        }
        else if (n == 5) {
            this.pola[0] = 0;
            this.pola[1] = 2;
            this.pola[2] = 2;
            this.pola[3] = 0;
            this.polb[0] = 1;
            this.polb[1] = 2;
            this.polb[2] = 9;
            this.polb[3] = 10;
        }
        else if (n == 6) {
            this.pola[0] = 1;
            this.pola[1] = 10;
            this.pola[2] = 2;
            this.pola[3] = 9;
            this.polb[0] = 10;
            this.polb[1] = 10;
            this.polb[2] = 11;
            this.polb[3] = 11;
        }
        return this.p = new Polygon(this.pola, this.polb, 4);
    }
    
    public boolean hasChanged(final int n) {
        return this.digitNumber != n;
    }
    
    public void setColor(final Color ledcolor) {
        Digit.ledcolor = ledcolor;
    }
    
    private void updateLeds(final int n) {
        if (n == 1) {
            this.led[0] = false;
            this.led[1] = true;
            this.led[2] = true;
            this.led[3] = false;
            this.led[4] = false;
            this.led[5] = false;
            this.led[6] = false;
        }
        else if (n == 2) {
            this.led[0] = true;
            this.led[1] = true;
            this.led[2] = false;
            this.led[3] = true;
            this.led[4] = true;
            this.led[5] = false;
            this.led[6] = true;
        }
        else if (n == 3) {
            this.led[0] = true;
            this.led[1] = true;
            this.led[2] = true;
            this.led[3] = true;
            this.led[4] = false;
            this.led[5] = false;
            this.led[6] = true;
        }
        else if (n == 4) {
            this.led[0] = false;
            this.led[1] = true;
            this.led[2] = true;
            this.led[3] = false;
            this.led[4] = false;
            this.led[5] = true;
            this.led[6] = true;
        }
        else if (n == 5) {
            this.led[0] = true;
            this.led[1] = false;
            this.led[2] = true;
            this.led[3] = true;
            this.led[4] = false;
            this.led[5] = true;
            this.led[6] = true;
        }
        else if (n == 6) {
            this.led[0] = true;
            this.led[1] = false;
            this.led[2] = true;
            this.led[3] = true;
            this.led[4] = true;
            this.led[5] = true;
            this.led[6] = true;
        }
        else if (n == 7) {
            this.led[0] = true;
            this.led[1] = true;
            this.led[2] = true;
            this.led[3] = false;
            this.led[4] = false;
            this.led[5] = false;
            this.led[6] = false;
        }
        else if (n == 8) {
            this.led[0] = true;
            this.led[1] = true;
            this.led[2] = true;
            this.led[3] = true;
            this.led[4] = true;
            this.led[5] = true;
            this.led[6] = true;
        }
        else if (n == 9) {
            this.led[0] = true;
            this.led[1] = true;
            this.led[2] = true;
            this.led[3] = true;
            this.led[4] = false;
            this.led[5] = true;
            this.led[6] = true;
        }
        else if (n == 0) {
            this.led[0] = true;
            this.led[1] = true;
            this.led[2] = true;
            this.led[3] = true;
            this.led[4] = true;
            this.led[5] = true;
            this.led[6] = false;
        }
        else {
            this.led[0] = false;
            this.led[1] = false;
            this.led[2] = false;
            this.led[3] = false;
            this.led[4] = false;
            this.led[5] = false;
            this.led[6] = false;
        }
    }
}
