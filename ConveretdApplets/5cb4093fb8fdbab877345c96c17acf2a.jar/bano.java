import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseMotionAdapter;
import java.net.MalformedURLException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.ImageObserver;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.io.InputStream;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class bano extends Applet implements Runnable
{
    Thread Faden;
    Image offscreenImage;
    Graphics offscreenGraphics;
    boolean busy;
    boolean ready;
    boolean mousePressed;
    int mouseX;
    int mouseY;
    int mouseXOld;
    int mouseYOld;
    Image img;
    Color backgr;
    Color kasten0;
    Color kasten1;
    Color kasten2;
    Color kasten3;
    Color kasten4;
    Color wincolor;
    boolean homepagelink;
    URL homepage;
    int ifor;
    int jfor;
    boolean gewonnen;
    DataInputStream dis;
    InputStream is;
    int xLevelArray1;
    int yLevelArray1;
    char[][][] HistorySave2;
    int HistoryCount;
    int HistoryCountRedo;
    int LevelAnzahl;
    int LevelAktiv;
    char[][] LevelArray1;
    char[][] LevelArray2;
    CLevel Level;
    CButton[] Knopf;
    int ButtonXa;
    int ButtonYa;
    CPusher Pusher;
    CHistory History;
    CHistoryButtons[] HistoryButton;
    String[] Levels;
    String[] Levels2;
    int AppletW;
    int AppletH;
    double t;
    double dt;
    
    public bano() {
        this.busy = true;
        this.ready = false;
        this.mousePressed = false;
        this.mouseX = 0;
        this.mouseY = 0;
        this.mouseXOld = 0;
        this.mouseYOld = 0;
        this.gewonnen = false;
        this.Level = new CLevel();
        this.Levels = new String[20];
        this.Levels2 = new String[20];
        this.t = 0.0;
        this.dt = 3.0E-6;
    }
    
    public void init() {
        this.Levels[0] = "1111111111111111111n1111100011111111111n1111100011111111111n1111100011111111111n1110000001111111111n1110101101111111111n1000101101111100221n1000000000000000221n1111101110101100221n1111100000111111111n1111111111111111111";
        this.Levels[1] = "11111111111111n12200100000111n12200100000001n12200101111001n12200000011001n12200101000011n11111101100001n11100000000001n11100001000001n11111111111111";
        this.Levels[2] = "11111111111111111n11111111100000011n11111111100100111n11111111100000111n11111111110000111n11111111100010111n12222001100000001n11222000000000001n12222001111111111n11111111111111111";
        this.Levels[3] = "1111111111111111111n1111111111110022221n1111111111110022221n1000010000000022221n1000010000010022221n1000000000010022221n1000010000011111111n1000010000011111111n1101111111111111111n1000010000111111111n1000000000111111111n1000010000011111111n1000010000111111111n1111111111111111111";
        this.Levels[4] = "11111111111111111n11111111100011111n11111111101011001n11111111100000001n11111111101110001n12222001100000111n12222000000000111n12222001100000011n11111111100000111n11111111100000011n11111111111011011n11111111111000011n11111111111111111";
        this.Levels[5] = "111111111111n122001111011n122001110001n122000000001n122001010001n122111010001n111100010001n111100010001n111100000001n111100110001n111111111111";
        this.Levels[6] = "1111111111111n1111111100011n1101001100001n1000000000001n1000001110001n1110111110111n1000011102211n1000000022211n1000011122211n1000011122211n1001111111111n1111111111111";
        this.Levels[7] = "11111111111111111n11111111111002221n11111111111002221n11111110000002021n11111110011002221n11111111011002221n11111111011111111n11111100000111111n11111100000011111n11000100000010001n10000000000000001n11111100000011111n11111100000011111n11111111111111111";
        this.Levels2[0] = "0000000000000000000n0000000000000000000n0000030000000000000n0000000300000000000n0000030300000000000n0000000000000000000n0000000000000000000n0030030000000000000n0000000000040000000n0000000000000000000n0000000000000000000";
        this.Levels2[1] = "00000000000000n00000000000000n00000003003000n00000030000000n00000004000000n00000000003000n00000000030300n00003003030300n00000000000000n00000000000000";
        this.Levels2[2] = "00000000000000000n00000000000000400n00000000003030000n00000000003003000n00000000003030000n00000000003000000n00000000003003000n00000000030030000n00000000000000000n00000000000000000";
        this.Levels2[3] = "0000000000000000000n0000000000000000000n0000000000000000000n0000000030300000000n0033303003000000000n0003000003000000000n0033003030300000000n0003000000000000000n0000000000000000000n0000000000000000000n0000003000000000000n0003303300400000000n0000000000000000000n0000000000000000000";
        this.Levels2[4] = "00000000000000000n00000000000000000n00000000000300000n00000000000000300n00000000000000000n00000000003003000n00000000030330000n00000000030030400n00000000000300000n00000000003030000n00000000000000000n00000000000000000n00000000000000000";
        this.Levels2[5] = "000000000000n000000000400n000000000000n000000003300n000000000300n000000000300n000003003000n000000300300n000003003000n000000000000n000000000000";
        this.Levels2[6] = "0000000000000n0000000000000n0000040003300n0000030000000n0003000000000n0000000003000n0030000000000n0030303000000n0000000000000n0033000000000n0000000000000n0000000000000";
        this.Levels2[7] = "00000000000000000n00000000000000000n00000000000000000n00000000000000000n00000000000000000n00000000000000000n00000000000000000n00000003330000000n00000000303000000n00000030300000000n04030030000300300n00000003303000000n00000000000000000n00000000000000000";
        this.addMouseListener(new MouseEventHandler());
        this.addMouseMotionListener(new MouseMotionEventHandler());
        this.addKeyListener(new KeyEventHandler());
        this.LevelArray1 = new char[100][100];
        this.LevelArray2 = new char[100][100];
        this.AppletW = this.size().width;
        this.AppletH = this.size().height;
        this.backgr = new Color(45, 62, 71);
        this.kasten0 = new Color(120, 120, 120);
        this.kasten1 = new Color(28, 39, 45);
        this.kasten2 = new Color(122, 147, 42);
        this.kasten3 = new Color(145, 118, 42);
        this.kasten4 = new Color(198, 198, 198);
        this.wincolor = new Color(50, 50, 255);
        this.LevelAnzahl = 8;
        this.LevelAktiv = 0;
        this.Knopf = new CButton[this.LevelAnzahl];
        this.Pusher = new CPusher(0, 0);
        this.HistorySave2 = new char[75][50][50];
        this.History = new CHistory(49);
        this.HistoryCount = 49;
        this.HistoryCountRedo = 49;
        (this.HistoryButton = new CHistoryButtons[2])[0] = new CHistoryButtons(false);
        this.HistoryButton[1] = new CHistoryButtons(false);
        this.homepagelink = false;
        for (int i = 0; i < this.LevelAnzahl; ++i) {
            this.ButtonXa = i * 18 + 20;
            this.ButtonYa = 20;
            this.Knopf[i] = new CButton(false, this.ButtonXa, this.ButtonYa);
        }
        this.Knopf[0].status = true;
    }
    
    public void start() {
        if (this.Faden == null) {
            (this.Faden = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.Faden != null) {
            this.Faden.stop();
            this.Faden = null;
            this.busy = false;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.ready) {
            this.offscreenGraphics.setColor(this.backgr);
            this.offscreenGraphics.fillRect(0, 0, this.AppletW, this.AppletH);
            this.Level.LevelPaint(this.offscreenGraphics);
            for (int i = 0; i < this.LevelAnzahl; ++i) {
                this.Knopf[i].ButtonPaint(this.offscreenGraphics, i);
            }
            if (this.gewonnen) {
                this.offscreenGraphics.setColor(this.wincolor);
                this.offscreenGraphics.fillRect(this.AppletW / 2 - 115, this.AppletH / 2 - 60, 230, 120);
                this.offscreenGraphics.setColor(Color.black);
                this.offscreenGraphics.drawRect(this.AppletW / 2 - 115, this.AppletH / 2 - 60, 230, 120);
                this.offscreenGraphics.drawRect(this.AppletW / 2 - 114, this.AppletH / 2 - 59, 228, 118);
                this.offscreenGraphics.drawString("You managed it!", this.AppletW / 2 - 35, this.AppletH / 2 - 13);
                this.offscreenGraphics.drawString("Click on the blue area", this.AppletW / 2 - 100, this.AppletH / 2 + 10);
                this.offscreenGraphics.drawString("to reach the next level.", this.AppletW / 2 - 100, this.AppletH / 2 + 23);
            }
            this.HistoryButton[0].HistoryButtonPaint(this.offscreenGraphics, 0);
            this.HistoryButton[1].HistoryButtonPaint(this.offscreenGraphics, 1);
            this.offscreenGraphics.setColor(this.kasten0);
            this.offscreenGraphics.drawString("coded by Rebecca Tsukalas", 10, this.AppletH - 25);
            if (this.homepagelink) {
                this.offscreenGraphics.setColor(this.kasten2);
                this.offscreenGraphics.drawString("www.murmelsee.at", 10, this.AppletH - 10);
            }
            else {
                this.offscreenGraphics.setColor(this.kasten4);
                this.offscreenGraphics.drawString("www.murmelsee.at", 10, this.AppletH - 10);
            }
            graphics.drawImage(this.offscreenImage, 0, 0, this);
        }
    }
    
    public void LoadLevel() {
        this.xLevelArray1 = 0;
        this.yLevelArray1 = 0;
        this.ifor = 0;
        while (this.ifor < this.Levels[this.LevelAktiv].length()) {
            if (this.Levels[this.LevelAktiv].charAt(this.ifor) == 'n') {
                this.xLevelArray1 = 0;
                ++this.yLevelArray1;
            }
            else {
                this.LevelArray1[this.xLevelArray1][this.yLevelArray1] = this.Levels[this.LevelAktiv].charAt(this.ifor);
                ++this.xLevelArray1;
            }
            ++this.ifor;
        }
        this.xLevelArray1 = 0;
        this.yLevelArray1 = 0;
        this.ifor = 0;
        while (this.ifor < this.Levels[this.LevelAktiv].length()) {
            if (this.Levels2[this.LevelAktiv].charAt(this.ifor) == 'n') {
                this.xLevelArray1 = 0;
                ++this.yLevelArray1;
            }
            else {
                this.LevelArray2[this.xLevelArray1][this.yLevelArray1] = this.Levels2[this.LevelAktiv].charAt(this.ifor);
                ++this.xLevelArray1;
            }
            ++this.ifor;
        }
        --this.xLevelArray1;
        this.PusherCoords();
        this.HistoryCount = 49;
        this.HistoryCountRedo = 49;
        this.History.HistoryStatus = 49;
        this.History.History_save();
    }
    
    public void PusherCoords() {
        for (int i = 0; i <= this.yLevelArray1; ++i) {
            for (int j = 0; j <= this.xLevelArray1; ++j) {
                if (this.LevelArray2[j][i] == '4') {
                    this.Pusher.PusherX = j;
                    this.Pusher.PusherY = i;
                }
            }
        }
    }
    
    public void ZielCheck() {
        int n = 0;
        this.ifor = 0;
        while (this.ifor < this.yLevelArray1) {
            this.jfor = 0;
            while (this.jfor < this.xLevelArray1) {
                if (this.LevelArray1[this.jfor][this.ifor] == '2' && this.LevelArray2[this.jfor][this.ifor] != '3') {
                    ++n;
                }
                ++this.jfor;
            }
            ++this.ifor;
        }
        if (n == 0) {
            this.gewonnen = true;
        }
    }
    
    public void run() {
        this.offscreenImage = this.createImage(this.AppletW, this.AppletH);
        this.offscreenGraphics = this.offscreenImage.getGraphics();
        this.ready = true;
        this.LoadLevel();
        while (this.busy) {
            this.t += this.dt;
            this.repaint();
            try {
                Thread.sleep(20L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    class CPusher
    {
        int PusherX;
        int PusherY;
        
        CPusher(final int pusherX, final int pusherY) {
            this.PusherX = pusherX;
            this.PusherY = pusherY;
        }
        
        void MoveUp() {
            if ((bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY - 1] != '3' || bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY - 2] != '3') && bano.this.LevelArray1[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY - 1] != '1') {
                if (bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY - 1] != '3' || bano.this.LevelArray1[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY - 2] != '1') {
                    if ((bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY - 1] == '3' && bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY - 2] == '0' && bano.this.LevelArray1[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY - 2] == '0') || (bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY - 1] == '3' && bano.this.LevelArray1[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY - 2] == '2')) {
                        bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY] = '0';
                        --bano.this.Pusher.PusherY;
                        bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY] = '4';
                        bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY - 1] = '3';
                        bano.this.ZielCheck();
                    }
                    else {
                        bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY] = '0';
                        --bano.this.Pusher.PusherY;
                        bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY] = '4';
                    }
                    bano.this.History.History_save();
                }
            }
        }
        
        void MoveDown() {
            if ((bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY + 1] != '3' || bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY + 2] != '3') && bano.this.LevelArray1[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY + 1] != '1') {
                if (bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY + 1] != '3' || bano.this.LevelArray1[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY + 2] != '1') {
                    if ((bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY + 1] == '3' && bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY + 2] == '0' && bano.this.LevelArray1[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY + 2] == '0') || (bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY + 1] == '3' && bano.this.LevelArray1[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY + 2] == '2')) {
                        bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY] = '0';
                        ++bano.this.Pusher.PusherY;
                        bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY] = '4';
                        bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY + 1] = '3';
                        bano.this.ZielCheck();
                    }
                    else {
                        bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY] = '0';
                        ++bano.this.Pusher.PusherY;
                        bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY] = '4';
                    }
                    bano.this.History.History_save();
                }
            }
        }
        
        void MoveRight() {
            if ((bano.this.LevelArray2[bano.this.Pusher.PusherX + 1][bano.this.Pusher.PusherY] != '3' || bano.this.LevelArray2[bano.this.Pusher.PusherX + 2][bano.this.Pusher.PusherY] != '3') && bano.this.LevelArray1[bano.this.Pusher.PusherX + 1][bano.this.Pusher.PusherY] != '1') {
                if (bano.this.LevelArray2[bano.this.Pusher.PusherX + 1][bano.this.Pusher.PusherY] != '3' || bano.this.LevelArray1[bano.this.Pusher.PusherX + 2][bano.this.Pusher.PusherY] != '1') {
                    if ((bano.this.LevelArray2[bano.this.Pusher.PusherX + 1][bano.this.Pusher.PusherY] == '3' && bano.this.LevelArray2[bano.this.Pusher.PusherX + 2][bano.this.Pusher.PusherY] == '0' && bano.this.LevelArray1[bano.this.Pusher.PusherX + 2][bano.this.Pusher.PusherY] == '0') || (bano.this.LevelArray2[bano.this.Pusher.PusherX + 1][bano.this.Pusher.PusherY] == '3' && bano.this.LevelArray1[bano.this.Pusher.PusherX + 2][bano.this.Pusher.PusherY] == '2')) {
                        bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY] = '0';
                        ++bano.this.Pusher.PusherX;
                        bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY] = '4';
                        bano.this.LevelArray2[bano.this.Pusher.PusherX + 1][bano.this.Pusher.PusherY] = '3';
                        bano.this.ZielCheck();
                    }
                    else {
                        bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY] = '0';
                        ++bano.this.Pusher.PusherX;
                        bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY] = '4';
                    }
                    bano.this.History.History_save();
                }
            }
        }
        
        void MoveLeft() {
            if ((bano.this.LevelArray2[bano.this.Pusher.PusherX - 1][bano.this.Pusher.PusherY] != '3' || bano.this.LevelArray2[bano.this.Pusher.PusherX - 2][bano.this.Pusher.PusherY] != '3') && bano.this.LevelArray1[bano.this.Pusher.PusherX - 1][bano.this.Pusher.PusherY] != '1') {
                if (bano.this.LevelArray2[bano.this.Pusher.PusherX - 1][bano.this.Pusher.PusherY] != '3' || bano.this.LevelArray1[bano.this.Pusher.PusherX - 2][bano.this.Pusher.PusherY] != '1') {
                    if ((bano.this.LevelArray2[bano.this.Pusher.PusherX - 1][bano.this.Pusher.PusherY] == '3' && bano.this.LevelArray2[bano.this.Pusher.PusherX - 2][bano.this.Pusher.PusherY] == '0' && bano.this.LevelArray1[bano.this.Pusher.PusherX - 2][bano.this.Pusher.PusherY] == '0') || (bano.this.LevelArray2[bano.this.Pusher.PusherX - 1][bano.this.Pusher.PusherY] == '3' && bano.this.LevelArray1[bano.this.Pusher.PusherX - 2][bano.this.Pusher.PusherY] == '2')) {
                        bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY] = '0';
                        --bano.this.Pusher.PusherX;
                        bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY] = '4';
                        bano.this.LevelArray2[bano.this.Pusher.PusherX - 1][bano.this.Pusher.PusherY] = '3';
                        bano.this.ZielCheck();
                    }
                    else {
                        bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY] = '0';
                        --bano.this.Pusher.PusherX;
                        bano.this.LevelArray2[bano.this.Pusher.PusherX][bano.this.Pusher.PusherY] = '4';
                    }
                    bano.this.History.History_save();
                }
            }
        }
    }
    
    class CLevel
    {
        void LevelPaint(final Graphics graphics) {
            for (int i = 0; i <= bano.this.yLevelArray1; ++i) {
                for (int j = 0; j <= bano.this.xLevelArray1; ++j) {
                    if (bano.this.LevelArray1[j][i] == '0') {
                        graphics.setColor(bano.this.kasten0);
                        graphics.drawRect((bano.this.AppletW - (bano.this.xLevelArray1 + 1) * 18) / 2 + j * 18, (bano.this.AppletH - (bano.this.yLevelArray1 + 1) * 18) / 2 + i * 18, 15, 15);
                    }
                    if (bano.this.LevelArray1[j][i] == '1') {
                        graphics.setColor(bano.this.kasten1);
                        graphics.fillRect((bano.this.AppletW - (bano.this.xLevelArray1 + 1) * 18) / 2 + j * 18, (bano.this.AppletH - (bano.this.yLevelArray1 + 1) * 18) / 2 + i * 18, 15, 15);
                    }
                    if (bano.this.LevelArray1[j][i] == '2') {
                        graphics.setColor(bano.this.kasten2);
                        graphics.fillRect((bano.this.AppletW - (bano.this.xLevelArray1 + 1) * 18) / 2 + j * 18, (bano.this.AppletH - (bano.this.yLevelArray1 + 1) * 18) / 2 + i * 18, 15, 15);
                    }
                }
            }
            for (int k = 0; k <= bano.this.yLevelArray1; ++k) {
                for (int l = 0; l <= bano.this.xLevelArray1; ++l) {
                    if (bano.this.LevelArray2[l][k] == '3') {
                        graphics.setColor(bano.this.kasten3);
                        graphics.fillRect((bano.this.AppletW - (bano.this.xLevelArray1 + 1) * 18) / 2 + l * 18, (bano.this.AppletH - (bano.this.yLevelArray1 + 1) * 18) / 2 + k * 18, 15, 15);
                    }
                    if (bano.this.LevelArray2[l][k] == '4') {
                        graphics.setColor(bano.this.kasten4);
                        graphics.fillRect((bano.this.AppletW - (bano.this.xLevelArray1 + 1) * 18) / 2 + l * 18, (bano.this.AppletH - (bano.this.yLevelArray1 + 1) * 18) / 2 + k * 18, 15, 15);
                    }
                    if (bano.this.LevelArray2[l][k] == '0') {}
                }
            }
        }
    }
    
    class CButton
    {
        boolean status;
        int ButtonX;
        int ButtonY;
        
        CButton(final boolean status, final int buttonX, final int buttonY) {
            this.status = status;
            this.ButtonX = buttonX;
            this.ButtonY = buttonY;
        }
        
        void ButtonPaint(final Graphics graphics, final int n) {
            if (!bano.this.Knopf[n].status) {
                graphics.setColor(bano.this.kasten0);
                graphics.drawRect(bano.this.Knopf[n].ButtonX, bano.this.Knopf[n].ButtonY, 15, 15);
            }
            else {
                graphics.setColor(bano.this.kasten0);
                graphics.drawRect(bano.this.Knopf[n].ButtonX, bano.this.Knopf[n].ButtonY, 15, 15);
                graphics.setColor(bano.this.kasten4);
                graphics.drawRect(bano.this.Knopf[n].ButtonX + 1, bano.this.Knopf[n].ButtonY + 1, 13, 13);
            }
        }
    }
    
    class CHistoryButtons
    {
        boolean KnopfMouseover;
        
        CHistoryButtons(final boolean knopfMouseover) {
            this.KnopfMouseover = knopfMouseover;
        }
        
        void HistoryButtonPaint(final Graphics graphics, final int n) {
            if (n == 0) {
                if (!bano.this.HistoryButton[n].KnopfMouseover) {
                    graphics.setColor(bano.this.kasten0);
                    graphics.drawRect(20, 40, 15, 15);
                    graphics.drawLine(25, 47, 30, 44);
                    graphics.drawLine(25, 47, 30, 50);
                }
                else {
                    graphics.setColor(bano.this.kasten4);
                    graphics.drawRect(20, 40, 15, 15);
                    graphics.drawLine(25, 47, 30, 44);
                    graphics.drawLine(25, 47, 30, 50);
                }
            }
            else if (!bano.this.HistoryButton[n].KnopfMouseover) {
                graphics.setColor(bano.this.kasten0);
                graphics.drawRect(40, 40, 15, 15);
                graphics.drawLine(50, 47, 45, 44);
                graphics.drawLine(50, 47, 45, 50);
            }
            else {
                graphics.setColor(bano.this.kasten4);
                graphics.drawRect(40, 40, 15, 15);
                graphics.drawLine(50, 47, 45, 44);
                graphics.drawLine(50, 47, 45, 50);
            }
        }
    }
    
    class CHistory
    {
        int HistoryStatus;
        
        CHistory(final int historyStatus) {
            this.HistoryStatus = 49;
            this.HistoryStatus = historyStatus;
        }
        
        void History_save() {
            if (this.HistoryStatus != 49) {
                ++this.HistoryStatus;
                bano.this.HistoryCountRedo = this.HistoryStatus;
                bano.this.ifor = 0;
                while (bano.this.ifor < bano.this.yLevelArray1) {
                    bano.this.jfor = 0;
                    while (bano.this.jfor < bano.this.xLevelArray1) {
                        bano.this.HistorySave2[this.HistoryStatus][bano.this.jfor][bano.this.ifor] = bano.this.LevelArray2[bano.this.jfor][bano.this.ifor];
                        final bano this$0 = bano.this;
                        ++this$0.jfor;
                    }
                    final bano this$2 = bano.this;
                    ++this$2.ifor;
                }
            }
            else {
                for (int i = bano.this.HistoryCount; i < this.HistoryStatus; ++i) {
                    bano.this.ifor = 0;
                    while (bano.this.ifor < bano.this.yLevelArray1) {
                        bano.this.jfor = 0;
                        while (bano.this.jfor < bano.this.xLevelArray1) {
                            bano.this.HistorySave2[i][bano.this.jfor][bano.this.ifor] = bano.this.HistorySave2[i + 1][bano.this.jfor][bano.this.ifor];
                            final bano this$3 = bano.this;
                            ++this$3.jfor;
                        }
                        final bano this$4 = bano.this;
                        ++this$4.ifor;
                    }
                }
                bano.this.ifor = 0;
                while (bano.this.ifor < bano.this.yLevelArray1) {
                    bano.this.jfor = 0;
                    while (bano.this.jfor < bano.this.xLevelArray1) {
                        bano.this.HistorySave2[49][bano.this.jfor][bano.this.ifor] = bano.this.LevelArray2[bano.this.jfor][bano.this.ifor];
                        final bano this$5 = bano.this;
                        ++this$5.jfor;
                    }
                    final bano this$6 = bano.this;
                    ++this$6.ifor;
                }
                final bano this$7 = bano.this;
                --this$7.HistoryCount;
                if (bano.this.HistoryCount < 0) {
                    bano.this.HistoryCount = 0;
                }
            }
        }
        
        void History_back() {
            if (this.HistoryStatus > bano.this.HistoryCount + 1 && this.HistoryStatus < 50) {
                --this.HistoryStatus;
                bano.this.ifor = 0;
                while (bano.this.ifor < bano.this.yLevelArray1) {
                    bano.this.jfor = 0;
                    while (bano.this.jfor < bano.this.xLevelArray1) {
                        bano.this.LevelArray2[bano.this.jfor][bano.this.ifor] = bano.this.HistorySave2[this.HistoryStatus][bano.this.jfor][bano.this.ifor];
                        final bano this$0 = bano.this;
                        ++this$0.jfor;
                    }
                    final bano this$2 = bano.this;
                    ++this$2.ifor;
                }
            }
            bano.this.PusherCoords();
        }
        
        void History_forward() {
            if (bano.this.HistoryCountRedo > this.HistoryStatus && this.HistoryStatus < 50) {
                ++this.HistoryStatus;
                bano.this.ifor = 0;
                while (bano.this.ifor < bano.this.yLevelArray1) {
                    bano.this.jfor = 0;
                    while (bano.this.jfor < bano.this.xLevelArray1) {
                        bano.this.LevelArray2[bano.this.jfor][bano.this.ifor] = bano.this.HistorySave2[this.HistoryStatus][bano.this.jfor][bano.this.ifor];
                        final bano this$0 = bano.this;
                        ++this$0.jfor;
                    }
                    final bano this$2 = bano.this;
                    ++this$2.ifor;
                }
                bano.this.PusherCoords();
            }
        }
    }
    
    class MouseEventHandler extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            bano.this.mouseX = mouseEvent.getX();
            bano.this.mouseY = mouseEvent.getY();
            bano.this.mousePressed = true;
            if (bano.this.homepagelink) {
                try {
                    bano.this.getAppletContext().showDocument(new URL("http://www.murmelsee.at"), "_blank");
                }
                catch (MalformedURLException ex) {}
            }
            if (bano.this.gewonnen) {
                if (bano.this.mouseX > bano.this.AppletW / 2 - 115 && bano.this.mouseX < bano.this.AppletW / 2 - 115 + 230 && bano.this.mouseY > bano.this.AppletH / 2 - 60 && bano.this.mouseY < bano.this.AppletH / 2 - 60 + 120) {
                    if (bano.this.LevelAktiv == bano.this.LevelAnzahl) {
                        bano.this.LoadLevel();
                        bano.this.gewonnen = false;
                    }
                    else {
                        bano.this.Knopf[bano.this.LevelAktiv].status = false;
                        final bano this$0 = bano.this;
                        ++this$0.LevelAktiv;
                        bano.this.Knopf[bano.this.LevelAktiv].status = true;
                        bano.this.LoadLevel();
                        bano.this.gewonnen = false;
                    }
                }
            }
            else {
                for (int i = 0; i < bano.this.LevelAnzahl; ++i) {
                    if (bano.this.mouseX >= bano.this.Knopf[i].ButtonX && bano.this.mouseY >= bano.this.Knopf[i].ButtonY && bano.this.mouseX <= bano.this.Knopf[i].ButtonX + 15 && bano.this.mouseY <= bano.this.Knopf[i].ButtonY + 15) {
                        for (int j = 0; j < bano.this.LevelAnzahl; ++j) {
                            bano.this.Knopf[j].status = false;
                        }
                        bano.this.Knopf[i].status = true;
                        bano.this.LevelAktiv = i;
                        bano.this.LoadLevel();
                    }
                }
                if (bano.this.mouseX > 20 && bano.this.mouseX < 35 && bano.this.mouseY > 40 && bano.this.mouseY < 55 && bano.this.mousePressed) {
                    bano.this.HistoryButton[0].KnopfMouseover = true;
                    bano.this.History.History_back();
                }
                if (bano.this.mouseX > 40 && bano.this.mouseX < 55 && bano.this.mouseY > 40 && bano.this.mouseY < 55 && bano.this.mousePressed) {
                    bano.this.HistoryButton[1].KnopfMouseover = true;
                    bano.this.History.History_forward();
                }
            }
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            bano.this.mousePressed = false;
            bano.this.HistoryButton[0].KnopfMouseover = false;
            bano.this.HistoryButton[1].KnopfMouseover = false;
        }
    }
    
    class MouseMotionEventHandler extends MouseMotionAdapter
    {
        public void mouseDragged(final MouseEvent mouseEvent) {
            bano.this.mouseX = mouseEvent.getX();
            bano.this.mouseY = mouseEvent.getY();
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
            bano.this.mouseX = mouseEvent.getX();
            bano.this.mouseY = mouseEvent.getY();
            if (bano.this.mouseX > 10 && bano.this.mouseX < 10 + bano.this.getFontMetrics(bano.this.getFont()).stringWidth("www.murmelsee.at") && bano.this.mouseY > bano.this.AppletH - 20 && bano.this.mouseY < bano.this.AppletH - 5) {
                bano.this.homepagelink = true;
            }
            else {
                bano.this.homepagelink = false;
            }
        }
    }
    
    class KeyEventHandler extends KeyAdapter
    {
        public void keyPressed(final KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == 40 && !bano.this.gewonnen) {
                bano.this.Pusher.MoveDown();
            }
            if (keyEvent.getKeyCode() == 38 && !bano.this.gewonnen) {
                bano.this.Pusher.MoveUp();
            }
            if (keyEvent.getKeyCode() == 39 && !bano.this.gewonnen) {
                bano.this.Pusher.MoveRight();
            }
            if (keyEvent.getKeyCode() == 37 && !bano.this.gewonnen) {
                bano.this.Pusher.MoveLeft();
            }
        }
    }
}
