import java.awt.Graphics;
import java.awt.Point;
import java.net.URL;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

class SG1_Skin
{
    String report;
    int w;
    int h;
    boolean externalGraphics;
    Font buttonFont;
    Font textFont;
    Color tfc;
    Color sfc;
    Color fgcolor;
    Color cursorCol;
    Color sbc;
    Rectangle[] area;
    URL url;
    String asound;
    String tsound;
    Point pb1;
    Point pb2;
    Point pb3;
    Point pb4;
    String pic0;
    String pic1;
    String pic2;
    String pic3;
    String pic4;
    
    public SG1_Skin(final Color color, final URL url) {
        this.report = "ok";
        this.w = 436;
        this.h = 270;
        this.externalGraphics = true;
        this.buttonFont = new Font("Arial", 1, 12);
        this.textFont = new Font("Arial CE", 1, 10);
        this.tfc = new Color(150, 150, 150);
        this.sfc = new Color(90, 180, 250);
        this.fgcolor = new Color(255, 255, 255);
        this.cursorCol = new Color(55, 175, 155);
        this.sbc = new Color(0, 0, 0);
        this.area = new Rectangle[6];
        this.asound = "acc.au";
        this.tsound = "tip.au";
        this.pb1 = new Point(40, 18);
        this.pb2 = new Point(31, 18);
        this.pb3 = new Point(40, 18);
        this.pb4 = new Point(38, 18);
        this.pic0 = "blue_base.gif";
        this.pic1 = "blue_gumb1.jpg";
        this.pic2 = "blue_gumb2.jpg";
        this.pic3 = "blue_gumb1.jpg";
        this.pic4 = "blue_gumb2.jpg";
        this.area[0] = new Rectangle(36, 19, 250, 165);
        this.area[1] = new Rectangle(45, 213, 235, 19);
        this.area[2] = new Rectangle(308, 41, 104, 25);
        this.area[3] = new Rectangle(308, 87, 104, 25);
        this.area[4] = new Rectangle(308, 132, 104, 25);
        this.area[5] = new Rectangle(308, 193, 104, 25);
        this.url = url;
    }
    
    public void getImage(final Graphics gb, final int noi) {
        gb.setFont(this.buttonFont);
        if (noi == 0) {
            gb.setColor(new Color(8, 48, 134));
            gb.fill3DRect(0, 0, this.w, this.h, true);
            gb.setColor(new Color(0, 50, 150));
            gb.drawRect(2, 2, this.w - 4, this.h - 4);
        }
        else if (noi == 1) {
            gb.setColor(new Color(48, 48, 48));
            gb.fill3DRect(0, 0, this.area[3].width, this.area[3].height, true);
        }
        else if (noi == 2) {
            gb.setColor(new Color(100, 150, 150));
            gb.fill3DRect(0, 0, this.area[2].width, this.area[2].height, true);
        }
        else if (noi == 3) {
            gb.setColor(new Color(48, 48, 48));
            gb.fill3DRect(0, 0, this.area[2].width, this.area[5].height, true);
            gb.setColor(new Color(255, 250, 250));
            gb.drawString("Send", 20, 30);
        }
        else if (noi == 4) {
            gb.setColor(new Color(100, 150, 150));
            gb.fill3DRect(0, 0, this.area[2].width, this.area[5].height, true);
            gb.setColor(new Color(255, 250, 250));
            gb.drawString("Send", 20, 30);
        }
    }
}
