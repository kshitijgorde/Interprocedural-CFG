import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.awt.image.ImageObserver;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JFindIt extends Applet implements Runnable
{
    Thread Faden;
    Image offscreenImage;
    Graphics osg;
    TextField Eingabefeld;
    Font Schrift;
    SmiGUI_c SmiGUI;
    boolean busy;
    int mouseX;
    int mouseY;
    boolean mousePressed;
    int Zustand;
    int AppletW;
    int AppletH;
    int Border;
    int SuchflaecheW;
    int yMsg;
    Color FarbeHintergrund;
    Color FarbeRahmen;
    Color FarbeText;
    Color FarbeButton;
    Color FarbeHover;
    Color FarbeClick;
    int Score;
    int iLevel;
    int nFehler;
    int nJoker;
    int nJokerMax;
    int nGefunden;
    long ZeitStart;
    long ZeitJetzt;
    int nLevel;
    int[] tMax;
    int[] size;
    int[] nFarben;
    int nCols;
    byte[][] SpielfeldLinks;
    byte[][] SpielfeldRechts;
    Color[] Farbe;
    int nFarbenMax;
    int dFarbMin;
    int wBalken;
    boolean useDatabase;
    
    public JFindIt() {
        this.Schrift = new Font("SansSerif", 0, 13);
        this.busy = true;
        this.Zustand = 0;
        this.Border = 20;
        this.FarbeHintergrund = new Color(180, 180, 180);
        this.FarbeRahmen = Color.black;
        this.FarbeText = Color.black;
        this.FarbeButton = new Color(200, 200, 200);
        this.FarbeHover = new Color(220, 220, 220);
        this.FarbeClick = Color.white;
        this.Score = 0;
        this.iLevel = 0;
        this.nFehler = 5;
        this.nJoker = 1;
        this.nJokerMax = 1;
        this.nGefunden = 0;
        this.nLevel = 30;
        this.tMax = new int[] { 0, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
        this.size = new int[] { 0, 30, 30, 24, 24, 20, 20, 16, 16, 15, 15, 12, 12, 10, 10, 10, 10, 8, 8, 8, 8, 8, 8, 5, 5, 5, 5, 5, 5, 5, 5 };
        this.nFarben = new int[] { 0, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 4, 5, 2, 3, 4, 5, 6, 7, 2, 3, 4, 5, 6, 7, 8, 9 };
        this.nFarbenMax = 9;
        this.dFarbMin = 160;
        this.useDatabase = false;
    }
    
    public void init() {
        if (this.getParameter("useDatabase").equals("y")) {
            this.useDatabase = true;
        }
        else {
            this.useDatabase = false;
        }
        this.AppletW = this.size().width;
        this.AppletH = this.size().height;
        this.SuchflaecheW = (this.AppletW - 3 * this.Border) / 2;
        this.yMsg = 2 * this.Border + this.SuchflaecheW + 40;
        this.offscreenImage = this.createImage(this.AppletW, this.AppletH);
        (this.osg = this.offscreenImage.getGraphics()).setFont(this.Schrift);
        this.SmiGUI = new SmiGUI_c(2);
        this.SmiGUI.Button[0].define(this.AppletW / 2 - 45, this.AppletH / 2 + 20, 90, 18, "Okay!");
        if (!this.useDatabase) {
            this.SmiGUI.Button[0].visible = false;
        }
        this.SmiGUI.Button[1].define(this.AppletW / 2 - 50, 2 * this.Border + this.SuchflaecheW, 100, 18, "Joker");
        this.SmiGUI.Button[1].visible = false;
        this.SmiGUI.ZeichneBG(this.osg);
        this.SmiGUI.Zeichnen(this.osg);
        if (this.useDatabase) {
            this.drawStringC("Enter your name:", this.AppletH / 2 - 23);
        }
        if (this.useDatabase) {
            (this.Eingabefeld = new TextField("anonym", 30)).setFont(this.Schrift);
            this.Eingabefeld.setBounds(this.AppletW / 2 - 60, this.AppletH / 2 - 15, 120, 20);
            this.setLayout(null);
            this.add(this.Eingabefeld);
        }
        this.SpielfeldLinks = new byte[48][48];
        this.SpielfeldRechts = new byte[48][48];
        (this.Farbe = new Color[this.nFarbenMax])[0] = new Color(0, 0, 0);
        this.Farbe[1] = new Color(255, 255, 255);
        this.Farbe[2] = new Color(128, 128, 128);
        this.Farbe[3] = new Color(220, 0, 0);
        this.Farbe[4] = new Color(255, 210, 0);
        this.Farbe[5] = new Color(0, 200, 0);
        this.Farbe[6] = new Color(0, 255, 255);
        this.Farbe[7] = new Color(0, 0, 255);
        this.Farbe[8] = new Color(255, 0, 255);
        this.addMouseListener(new MouseEventHandler());
        this.addMouseMotionListener(new MouseMotionEventHandler());
        this.Zustand = 1;
    }
    
    public void paint(final Graphics graphics) {
        if (this.Zustand >= 1) {
            graphics.drawImage(this.offscreenImage, 0, 0, this);
        }
    }
    
    public void run() {
        int i = 0;
        int j = 0;
        while (this.Zustand == 1 && this.useDatabase) {
            try {
                Thread.sleep(20L);
            }
            catch (InterruptedException ex3) {}
        }
        this.Zustand = 2;
        if (this.useDatabase) {
            this.remove(this.Eingabefeld);
        }
        try {
            Thread.sleep(20L);
        }
        catch (InterruptedException ex4) {}
        this.SmiGUI.Button[0].visible = false;
        while (this.busy) {
            this.iLevel = 0;
            this.nJoker = this.nJokerMax;
            boolean b = true;
            this.Score = 0;
            while (b) {
                ++this.iLevel;
                this.nGefunden = 0;
                this.SmiGUI.Button[1].visible = true;
                this.SmiGUI.Button[1].Text = "Let's go!";
                this.SmiGUI.ZeichneBG(this.osg);
                this.SmiGUI.Zeichnen(this.osg);
                this.drawStringC("Are You ready for level " + this.iLevel + "?", this.yMsg);
                if (this.iLevel == 1) {
                    this.drawStringC("You have to find the 5 differences between the 2 pictures and click them", this.yMsg + 16);
                    this.drawStringC("with your mouse. Use the Joker-Button if You can't find the last one!", this.yMsg + 32);
                    if (!this.useDatabase) {
                        this.drawStringC("You can find this game with highscore and many other applets on www.eigelb.at", this.yMsg + 48);
                    }
                }
                this.repaint();
                for (int k = 0; k < 100; ++k) {
                    int l;
                    int n;
                    do {
                        l = (int)(Math.random() * this.nFarbenMax);
                        n = (int)(Math.random() * this.nFarbenMax);
                    } while (l == n);
                    final Color color = this.Farbe[l];
                    this.Farbe[l] = this.Farbe[n];
                    this.Farbe[n] = color;
                }
                this.nCols = this.SuchflaecheW / this.size[this.iLevel];
                int n2;
                for (n2 = 0; n2 < this.nCols; ++n2) {
                    for (i = 0; i < this.nCols; ++i) {
                        this.SpielfeldLinks[n2][i] = (byte)(Math.random() * this.nFarben[this.iLevel]);
                        this.SpielfeldRechts[n2][i] = this.SpielfeldLinks[n2][i];
                    }
                }
                for (int n3 = 0; n3 < this.nFehler; ++n3) {
                    for (j = 0; j == 0; j = 1) {
                        n2 = (int)(Math.random() * this.nCols);
                        i = (int)(Math.random() * this.nCols);
                        if (this.SpielfeldLinks[n2][i] == this.SpielfeldRechts[n2][i]) {}
                    }
                    while (this.SpielfeldRechts[n2][i] == this.SpielfeldLinks[n2][i]) {
                        this.SpielfeldRechts[n2][i] = (byte)(Math.random() * this.nFarben[this.iLevel]);
                    }
                }
                while (this.Zustand == 2) {
                    try {
                        Thread.sleep(20L);
                    }
                    catch (InterruptedException ex5) {}
                }
                if (this.nJoker > 0) {
                    this.SmiGUI.Button[1].visible = true;
                    this.SmiGUI.Button[1].Text = "Joker!";
                }
                else {
                    this.SmiGUI.Button[1].visible = false;
                }
                this.SmiGUI.ZeichneBG(this.osg);
                this.SmiGUI.Zeichnen(this.osg);
                this.osg.setColor(this.FarbeRahmen);
                this.osg.drawRect(this.Border - 1, this.Border - 1, this.SuchflaecheW + 1, this.SuchflaecheW + 1);
                this.osg.drawRect(2 * this.Border + this.SuchflaecheW - 1, this.Border - 1, this.SuchflaecheW + 1, this.SuchflaecheW + 1);
                for (int n4 = 0; n4 < this.nCols; ++n4) {
                    for (i = 0; i < this.nCols; ++i) {
                        this.osg.setColor(this.Farbe[this.SpielfeldLinks[n4][i]]);
                        this.osg.fillRect(this.Border + n4 * this.size[this.iLevel], this.Border + i * this.size[this.iLevel], this.size[this.iLevel], this.size[this.iLevel]);
                        if (this.SpielfeldLinks[n4][i] != this.SpielfeldRechts[n4][i]) {
                            this.osg.setColor(this.Farbe[this.SpielfeldRechts[n4][i]]);
                        }
                        this.osg.fillRect(2 * this.Border + this.SuchflaecheW + n4 * this.size[this.iLevel], this.Border + i * this.size[this.iLevel], this.size[this.iLevel], this.size[this.iLevel]);
                    }
                }
                this.osg.setColor(this.FarbeRahmen);
                this.osg.fillRect(0, this.AppletH - 22, this.AppletW, 1);
                this.repaint();
                this.ZeitStart = System.currentTimeMillis();
                while (this.Zustand == 3) {
                    this.ZeitJetzt = System.currentTimeMillis();
                    this.wBalken = this.AppletW - 2 - (int)((this.ZeitJetzt - this.ZeitStart) / 1000.0 * ((this.AppletW - 2.0) / this.tMax[this.iLevel]));
                    if (this.wBalken > this.AppletW / 2) {
                        this.osg.setColor(Color.green);
                    }
                    else if (this.wBalken > this.AppletW / 4) {
                        this.osg.setColor(Color.yellow);
                    }
                    else {
                        this.osg.setColor(Color.red);
                    }
                    this.osg.fillRect(1, this.AppletH - 21, this.wBalken, 20);
                    this.osg.setColor(this.FarbeHintergrund);
                    this.osg.fillRect(1 + this.wBalken, this.AppletH - 21, this.AppletW - 2 - this.wBalken, 20);
                    if (this.ZeitJetzt - this.ZeitStart > 1000 * this.tMax[this.iLevel]) {
                        this.Zustand = 5;
                        j = 0;
                        break;
                    }
                    if (this.nGefunden == this.nFehler) {
                        this.Zustand = 4;
                        j = 1;
                        break;
                    }
                    this.repaint();
                    try {
                        Thread.sleep(20L);
                    }
                    catch (InterruptedException ex6) {}
                }
                if (j != 0) {
                    this.osg.setColor(this.FarbeHintergrund);
                    this.osg.fillRect(2 * this.Border + this.SuchflaecheW - 1, this.Border - 1, 2 + this.SuchflaecheW, this.SuchflaecheW + 2);
                    this.drawStringC("Level " + this.iLevel + " accomplished! Score: " + this.Score, this.yMsg);
                    this.SmiGUI.Button[1].Text = "Continue";
                    this.SmiGUI.Button[1].visible = true;
                    this.SmiGUI.Zeichnen(this.osg);
                    this.repaint();
                    while (this.Zustand == 4) {
                        try {
                            Thread.sleep(20L);
                        }
                        catch (InterruptedException ex7) {}
                    }
                }
                else {
                    this.osg.setColor(this.FarbeHintergrund);
                    this.osg.fillRect(1, this.AppletH - 22, this.AppletW - 2, 1);
                    this.drawStringC("Game Over in Level " + this.iLevel + "! Score: " + this.Score, this.yMsg);
                    b = false;
                    this.SmiGUI.Button[1].Text = "Try Again";
                    this.SmiGUI.Button[1].visible = true;
                    this.SmiGUI.Zeichnen(this.osg);
                    for (int n5 = 0; n5 < this.nCols; ++n5) {
                        for (i = 0; i < this.nCols; ++i) {
                            if (this.SpielfeldLinks[n5][i] != this.SpielfeldRechts[n5][i]) {
                                this.zeichneMarkierung(n5, i, false);
                            }
                        }
                    }
                    if (this.useDatabase) {
                        this.drawStringC("Submitting result to highscore...", this.yMsg + 16);
                    }
                    else {
                        this.drawStringC("To play JFindIt with a highscore, visit www.eigelb.at !", this.yMsg + 16);
                    }
                    this.repaint();
                    if (this.useDatabase) {
                        String line = "";
                        String line2 = "";
                        String line3 = "";
                        try {
                            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(this.getDocumentBase(), "../PHP/Store.php?Name=" + URLEncoder.encode(this.Eingabefeld.getText()) + "&Score=" + this.Score + "&Level=" + this.iLevel).openStream()));
                            line = bufferedReader.readLine();
                            line2 = bufferedReader.readLine();
                            line3 = bufferedReader.readLine();
                            bufferedReader.close();
                        }
                        catch (MalformedURLException ex) {
                            System.out.println("MalformedURLException: " + ex);
                        }
                        catch (IOException ex2) {
                            System.out.println("IOException: " + ex2);
                        }
                        if (!line.equals("OKAY!")) {
                            this.drawStringC("Error connecting to server! Sorry!", this.yMsg + 32);
                        }
                        else {
                            this.drawStringC(line2, this.yMsg + 32);
                            this.drawStringC(line3, this.yMsg + 48);
                        }
                        this.repaint();
                    }
                    while (this.Zustand == 5) {
                        try {
                            Thread.sleep(20L);
                        }
                        catch (InterruptedException ex8) {}
                    }
                }
            }
        }
    }
    
    public void drawStringC(final String s, final int n) {
        this.osg.setColor(this.FarbeRahmen);
        this.osg.drawString(s, this.AppletW / 2 - this.getFontMetrics(this.Schrift).stringWidth(s) / 2, n);
    }
    
    public void zeichneMarkierung(final int n, final int n2, final boolean b) {
        boolean b2 = false;
        int n3 = this.size[this.iLevel] / 2;
        if (!b) {
            n3 /= 2;
        }
        for (int i = 0; i < n3; ++i) {
            b2 = !b2;
            if (b2) {
                this.osg.setColor(Color.white);
            }
            else {
                this.osg.setColor(Color.black);
            }
            this.osg.drawRect(i + this.Border + n * this.size[this.iLevel], i + this.Border + n2 * this.size[this.iLevel], this.size[this.iLevel] - 2 * i - 1, this.size[this.iLevel] - 2 * i - 1);
            this.osg.drawRect(i + this.SuchflaecheW + 2 * this.Border + n * this.size[this.iLevel], i + this.Border + n2 * this.size[this.iLevel], this.size[this.iLevel] - 2 * i - 1, this.size[this.iLevel] - 2 * i - 1);
        }
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
    
    class MouseEventHandler extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            JFindIt.this.mousePressed = true;
            JFindIt.this.SmiGUI.handle();
            if (JFindIt.this.Zustand == 3) {
                int n;
                if (JFindIt.this.mouseX < 2 * JFindIt.this.Border + JFindIt.this.SuchflaecheW) {
                    n = (JFindIt.this.mouseX - JFindIt.this.Border) / JFindIt.this.size[JFindIt.this.iLevel];
                }
                else {
                    n = (JFindIt.this.mouseX - 2 * JFindIt.this.Border - JFindIt.this.SuchflaecheW) / JFindIt.this.size[JFindIt.this.iLevel];
                }
                final int n2 = (JFindIt.this.mouseY - JFindIt.this.Border) / JFindIt.this.size[JFindIt.this.iLevel];
                if (n < JFindIt.this.nCols && n2 < JFindIt.this.nCols && JFindIt.this.mouseX >= JFindIt.this.Border && JFindIt.this.mouseY >= JFindIt.this.Border) {
                    if (JFindIt.this.SpielfeldLinks[n][n2] != JFindIt.this.SpielfeldRechts[n][n2]) {
                        final JFindIt this$0 = JFindIt.this;
                        ++this$0.nGefunden;
                        JFindIt.this.SpielfeldRechts[n][n2] = JFindIt.this.SpielfeldLinks[n][n2];
                        JFindIt.this.zeichneMarkierung(n, n2, true);
                        final JFindIt this$2 = JFindIt.this;
                        this$2.Score += JFindIt.this.iLevel * JFindIt.this.wBalken;
                        JFindIt.this.repaint();
                    }
                    else {
                        final JFindIt this$3 = JFindIt.this;
                        this$3.ZeitStart -= (JFindIt.this.tMax[JFindIt.this.iLevel] * 1000 - (JFindIt.this.ZeitJetzt - JFindIt.this.ZeitStart)) / 4L;
                    }
                }
            }
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            JFindIt.this.mousePressed = false;
            JFindIt.this.SmiGUI.handle();
        }
    }
    
    class MouseMotionEventHandler extends MouseMotionAdapter
    {
        public void mouseMoved(final MouseEvent mouseEvent) {
            JFindIt.this.mouseX = mouseEvent.getX();
            JFindIt.this.mouseY = mouseEvent.getY();
            JFindIt.this.SmiGUI.handle();
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            this.mouseMoved(mouseEvent);
        }
    }
    
    class SmiGUI_c
    {
        int nButtons;
        Button_c[] Button;
        int i;
        
        SmiGUI_c(final int nButtons) {
            this.nButtons = nButtons;
            this.Button = new Button_c[this.nButtons];
            this.i = 0;
            while (this.i < this.nButtons) {
                this.Button[this.i] = new Button_c(this.i);
                ++this.i;
            }
        }
        
        void handle() {
            this.i = 0;
            while (this.i < this.nButtons) {
                if (this.Button[this.i].visible) {
                    this.Button[this.i].handle();
                }
                ++this.i;
            }
        }
        
        void ZeichneBG(final Graphics graphics) {
            JFindIt.this.osg.setColor(JFindIt.this.FarbeHintergrund);
            JFindIt.this.osg.fillRect(0, 0, JFindIt.this.AppletW, JFindIt.this.AppletH);
            JFindIt.this.osg.setColor(JFindIt.this.FarbeRahmen);
            JFindIt.this.osg.drawRect(0, 0, JFindIt.this.AppletW - 1, JFindIt.this.AppletH - 1);
        }
        
        void Zeichnen(final Graphics graphics) {
            this.i = 0;
            while (this.i < this.nButtons) {
                if (this.Button[this.i].visible) {
                    this.Button[this.i].Zeichnen(graphics);
                }
                ++this.i;
            }
        }
        
        class Button_c
        {
            int x;
            int y;
            int w;
            int h;
            int i;
            String Text;
            boolean mouseover;
            boolean pressed;
            boolean active;
            boolean visible;
            boolean mouseoverOld;
            boolean pressedOld;
            
            Button_c(final int i) {
                this.mouseover = false;
                this.pressed = false;
                this.active = false;
                this.visible = true;
                this.mouseoverOld = false;
                this.pressedOld = false;
                this.i = i;
            }
            
            void define(final int x, final int y, final int w, final int h, final String text) {
                this.x = x;
                this.y = y;
                this.w = w;
                this.h = h;
                this.Text = text;
            }
            
            void Zeichnen(final Graphics graphics) {
                if (this.mouseover & !this.pressed) {
                    graphics.setColor(JFindIt.this.FarbeHover);
                }
                else if (this.pressed) {
                    graphics.setColor(JFindIt.this.FarbeClick);
                }
                else {
                    graphics.setColor(JFindIt.this.FarbeButton);
                }
                graphics.fillRect(this.x + 1, this.y + 1, this.w - 1, this.h - 1);
                graphics.setColor(JFindIt.this.FarbeRahmen);
                graphics.drawRect(this.x, this.y, this.w, this.h);
                graphics.drawString(this.Text, this.x + this.w / 2 - JFindIt.this.getFontMetrics(JFindIt.this.Schrift).stringWidth(this.Text) / 2, this.y + this.h / 2 + 5);
            }
            
            void handle() {
                this.mouseoverOld = this.mouseover;
                this.pressedOld = this.pressed;
                if (JFindIt.this.mouseX >= this.x && JFindIt.this.mouseX < this.x + this.w && JFindIt.this.mouseY > this.y && JFindIt.this.mouseY < this.y + this.h) {
                    if (JFindIt.this.mousePressed) {
                        this.pressed = true;
                    }
                    else {
                        if (this.pressed) {
                            this.active = true;
                            this.pressed = false;
                        }
                        this.mouseover = true;
                    }
                }
                else {
                    this.mouseover = false;
                    this.pressed = false;
                }
                if (this.pressedOld != this.pressed || this.mouseover != this.mouseoverOld) {
                    this.Zeichnen(JFindIt.this.osg);
                    JFindIt.this.repaint();
                }
                if (this.active) {
                    if (this.i == 0) {
                        JFindIt.this.Zustand = 2;
                        this.active = false;
                    }
                    else if (this.i == 1 && JFindIt.this.Zustand == 2) {
                        JFindIt.this.Zustand = 3;
                        this.active = false;
                    }
                    else if (this.i == 1 && JFindIt.this.Zustand == 3 && JFindIt.this.nGefunden < JFindIt.this.nFehler) {
                        int n = 0;
                        final JFindIt access$000 = JFindIt.this;
                        ++access$000.nGefunden;
                        final JFindIt access$2 = JFindIt.this;
                        --access$2.nJoker;
                        for (int i = 0; i < JFindIt.this.nCols; ++i) {
                            for (int j = 0; j < JFindIt.this.nCols; ++j) {
                                if (n == 0 && JFindIt.this.SpielfeldLinks[i][j] != JFindIt.this.SpielfeldRechts[i][j]) {
                                    JFindIt.this.SpielfeldLinks[i][j] = JFindIt.this.SpielfeldRechts[i][j];
                                    JFindIt.this.zeichneMarkierung(i, j, true);
                                    n = 1;
                                    break;
                                }
                            }
                            if (n != 0) {
                                break;
                            }
                        }
                        if (JFindIt.this.nJoker <= 0) {
                            JFindIt.this.SmiGUI.Button[1].visible = false;
                            JFindIt.this.osg.setColor(JFindIt.this.FarbeHintergrund);
                            JFindIt.this.osg.fillRect(JFindIt.this.SmiGUI.Button[this.i].x, JFindIt.this.SmiGUI.Button[this.i].y, JFindIt.this.SmiGUI.Button[this.i].w + 1, JFindIt.this.SmiGUI.Button[this.i].h + 1);
                            JFindIt.this.repaint();
                        }
                        this.active = false;
                    }
                    else if (this.i == 1 && (JFindIt.this.Zustand == 5 || JFindIt.this.Zustand == 4)) {
                        JFindIt.this.Zustand = 2;
                        this.active = false;
                    }
                }
            }
        }
    }
}
