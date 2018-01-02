import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.awt.event.ItemListener;
import java.io.BufferedInputStream;
import java.awt.Toolkit;
import java.awt.MediaTracker;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Label;
import java.util.Vector;
import java.awt.Button;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.Panel;
import java.awt.Font;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rush extends Applet implements MouseListener, MouseMotionListener
{
    String bdHblf;
    int BdHblf;
    int bDHblf;
    int BDHblf;
    Image bdhBlf;
    Image BdhBlf;
    Graphics bDhBlf;
    Graphics BDhBlf;
    boolean bdHBlf;
    AudioClip BdHBlf;
    AudioClip bDHBlf;
    Font BDHBlf;
    Font bdhbLf;
    Font BdhbLf;
    Font bDhbLf;
    Image[] BDhbLf;
    Image bdHbLf;
    boolean[] BdHbLf;
    boolean[] bDHbLf;
    int BDHbLf;
    Bil bdhBLf;
    Bil BdhBLf;
    Bil bDhBLf;
    int BDhBLf;
    int bdHBLf;
    int BdHBLf;
    int bDHBLf;
    int BDHBLf;
    int bdhblF;
    int BdhblF;
    int bDhblF;
    int BDhblF;
    Panel bdHblF;
    Panel BdHblF;
    Panel bDHblF;
    TextField BDHblF;
    Choice bdhBlF;
    Button BdhBlF;
    Button bDhBlF;
    Vector BDhBlF;
    Vector bdHBlF;
    Panel BdHBlF;
    Label bDHBlF;
    TextField BDHBlF;
    Button bdhbLF;
    
    public rush() {
        this.bdHblf = "";
        this.BdHblf = 1;
        this.bDHblf = 0;
        this.BDHblf = 200;
        this.bdHBlf = false;
        this.BDHBlf = new Font("Helvetica", 0, 11);
        this.bdhbLf = new Font("Helvetica", 1, 11);
        this.BdhbLf = new Font("Helvetica", 1, 17);
        this.bDhbLf = new Font("Helvetica", 1, 11);
        this.BDhbLf = new Image[5];
        this.BdHbLf = new boolean[12];
        this.bDHbLf = new boolean[] { false, false, false, false };
        this.BDHbLf = 1;
        this.bdHblF = new Panel();
        this.BdHblF = new Panel();
        this.bDHblF = new Panel();
        this.BdhBlF = new Button();
        this.bDhBlF = new Button();
        this.BDhBlF = new Vector();
        this.bdHBlF = new Vector();
        this.BdHBlF = new Panel();
        this.bDHBlF = new Label();
        this.BDHBlF = new TextField();
        this.bdhbLF = new Button();
    }
    
    public void init() {
        final String parameter = this.getParameter("highScoreID");
        if (parameter != null) {
            this.BDHblf = Integer.parseInt(parameter);
        }
        if (this.BDHblf < 200 || this.BDHblf > 299) {
            this.BDHblf = 200;
        }
        final String parameter2 = this.getParameter("highScore");
        if (parameter2 != null) {
            this.BdHblf = Integer.parseInt(parameter2);
        }
        this.BdHBlF.setLayout(null);
        this.add(this.BdHBlF);
        this.BdHBlF.setBackground(Color.lightGray);
        this.BdHBlF.setBounds(100, 100, 200, 100);
        this.bDHBlF.setText("YOU MADE A HIGHSCORE!");
        this.BdHBlF.add(this.bDHBlF);
        this.bDHBlF.setBounds(38, 10, 160, 24);
        this.BdHBlF.add(this.BDHBlF);
        this.BDHBlF.setBounds(20, 40, 160, 20);
        this.bdhbLF.setLabel("Done");
        this.BdHBlF.add(this.bdhbLF);
        this.bdhbLF.setBackground(Color.lightGray);
        this.bdhbLF.setBounds(37, 70, 125, 24);
        this.BdHBlF.setVisible(false);
        this.bdhbLF.addActionListener(new DLk(this));
        this.showStatus("Loading Graphics");
        this.BdHBlf = this.getAudioClip(this.getCodeBase(), "click.au");
        this.bDHBlf = this.getAudioClip(this.getCodeBase(), "tire.au");
        try {
            final MediaTracker mediaTracker = new MediaTracker(this);
            final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
            for (int i = 0; i < 5; ++i) {
                final BufferedInputStream bufferedInputStream = new BufferedInputStream(this.getClass().getResourceAsStream(i + ".gif"));
                final byte[] array = new byte[10000];
                bufferedInputStream.read(array, 0, 10000);
                mediaTracker.addImage(this.BDhbLf[i] = defaultToolkit.createImage(array), 0);
            }
            final BufferedInputStream bufferedInputStream2 = new BufferedInputStream(this.getClass().getResourceAsStream("back.gif"));
            final byte[] array2 = new byte[10000];
            bufferedInputStream2.read(array2, 0, 10000);
            mediaTracker.addImage(this.bdHbLf = defaultToolkit.createImage(array2), 0);
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {
            System.out.println("Error loading images e: " + ex.toString());
        }
        this.BdHBlf.play();
        this.bdhBLf = new Bil(0, 0, true, this.BDhbLf[2], 75, 35);
        this.bdHBlF.addElement(this.bdhBLf);
        for (int j = 0; j < 7; ++j) {
            this.bdHBlF.addElement(new Bil(0, 0, true, this.BDhbLf[0], 75, 35));
        }
        for (int k = 0; k < 6; ++k) {
            this.bdHBlF.addElement(new Bil(0, 0, false, this.BDhbLf[1], 35, 75));
        }
        for (int l = 0; l < 4; ++l) {
            this.bdHBlF.addElement(new Bil(0, 0, true, this.BDhbLf[3], 115, 35));
        }
        for (int n = 0; n < 4; ++n) {
            this.bdHBlF.addElement(new Bil(0, 0, false, this.BDhbLf[4], 35, 115));
        }
        this.setBackground(Color.lightGray);
        this.bdhBlf = this.createImage(400, 300);
        this.BdhBlf = this.createImage(210, 200);
        this.bDhBlf = this.bdhBlf.getGraphics();
        this.BDhBlf = this.BdhBlf.getGraphics();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.BDHblF = new TextField(20);
        this.bdhBlF = new Choice();
        int n2 = -1;
        final String[] array3 = { "Beginner", "Intermediate", "Advanced", "Expert" };
        for (int n3 = 0; n3 < 12; ++n3) {
            if (n3 % 3 == 0) {
                ++n2;
            }
            this.bdhBlF.add("Level " + (n3 + 1) + " " + array3[n2]);
        }
        this.BDHblF.setEditable(false);
        this.BDHblF.setFont(this.bdhbLf);
        this.bdhBlF.setFont(this.BDHBlf);
        this.setLayout(null);
        this.add(this.bdHblF, null);
        this.bdHblF.setBounds(255, 8, 135, 50);
        this.bdHblF.setLayout(null);
        this.bdHblF.add(this.bdhBlF);
        this.bdHblF.add(this.BDHblF);
        this.add(this.BdHblF, null);
        this.BdHblF.setBounds(255, 235, 135, 30);
        this.BdHblF.setLayout(null);
        this.add(this.bDHblF, null);
        this.bDHblF.setBounds(100, 200, 40, 20);
        this.bDHblF.setLayout(null);
        this.BdhBlF.setBounds(10, 0, 126, 20);
        this.BdhBlF.setLabel("About");
        this.BdhBlF.addActionListener(new Dlk(this));
        this.bDhBlF.setBounds(0, 0, 40, 20);
        this.bDhBlF.setLabel("Ok");
        this.bDhBlF.addActionListener(new dLk(this));
        this.BdHblF.add(this.BdhBlF);
        this.bDHblF.add(this.bDhBlF);
        this.bDHblF.setVisible(false);
        this.BDHblF.setBounds(5, 5, 127, 20);
        this.bdhBlF.setBounds(5, 30, 127, 20);
        this.bdhBlF.addItemListener(new dlK(this));
        this.LTHt(1);
    }
    
    public void paint(final Graphics graphics) {
        this.bDhBlf.setColor(Color.lightGray);
        this.bDhBlf.fillRect(0, 0, 245, 280);
        this.bDhBlf.setColor(Color.darkGray);
        this.bDhBlf.drawRect(3, 10, 248, 245);
        this.bDhBlf.drawRect(4, 11, 246, 243);
        this.bDhBlf.setColor(Color.lightGray);
        this.bDhBlf.drawLine(80, 10, 124, 10);
        this.bDhBlf.drawLine(80, 11, 124, 11);
        final int n = 5;
        final int n2 = 5;
        int n3 = 8;
        int n4 = 15;
        this.bDhBlf.setColor(Color.darkGray);
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 6; ++j) {
                this.bDhBlf.drawRoundRect(n3, n4, n2 + 30, n + 30, 4, 4);
                n3 += 40;
            }
            n4 += 40;
            n3 = 8;
        }
        for (int k = 0; k < this.BDhBlF.size(); ++k) {
            this.bDhBLf = (Bil)this.BDhBlF.elementAt(k);
            this.bDhBlf.drawImage(this.bDhBLf.getImage(), this.bDhBLf.getX(), this.bDhBLf.getY(), this);
        }
        this.bDhBlf.setFont(this.BdhbLf);
        this.bDhBlf.drawString("Help the red", 277, 207);
        this.bDhBlf.drawString("beetle to exit.", 274, 222);
        this.bDhBlf.setFont(this.bdhbLf);
        this.bDhBlf.drawString("EXIT", 95, 12);
        this.bDhBlf.setFont(this.bDhbLf);
        this.bDhBlf.setColor(Color.red);
        this.bDhBlf.drawImage(this.bdHbLf, 275, 80, this);
        this.bDhBlf.drawString("www.G5.dk", 290, 133);
        this.bDhBlf.drawString("Rush Hour", 292, 82);
        if (!this.bdHBlf) {
            graphics.drawImage(this.bdhBlf, 0, 0, this);
        }
        else {
            this.BDhBlf.setColor(Color.white);
            this.BDhBlf.fillRect(0, 0, 210, 200);
            this.BDhBlf.setColor(Color.black);
            this.BDhBlf.drawRect(0, 0, 209, 199);
            this.BDhBlf.drawRect(1, 1, 207, 197);
            this.BDhBlf.setFont(this.bdhbLf);
            this.BDhBlf.drawString("Rush Hour", 73, 20);
            this.BDhBlf.drawString("To enter highscore - Complete:", 18, 82);
            this.BDhBlf.setFont(this.BDHBlf);
            this.BDhBlf.drawString("© 1998 Binary Arts Corp.", 38, 36);
            this.BDhBlf.drawString("If you want more levels and more fun", 18, 54);
            this.BDhBlf.drawString("buy the brilliant game!", 18, 66);
            this.BDhBlf.drawString("Beginner:", 27, 95);
            this.BDhBlf.drawString("-  pts.", 95, 95);
            this.BDhBlf.drawString("Intermediate:", 27, 106);
            this.BDhBlf.drawString("2 pts.", 95, 106);
            this.BDhBlf.drawString("Advanced:", 27, 117);
            this.BDhBlf.drawString("3 pts.", 95, 117);
            this.BDhBlf.drawString("Expert:", 27, 128);
            this.BDhBlf.drawString("4 pts.", 95, 128);
            this.BDhBlf.drawString("Programmed by:", 65, 150);
            this.BDhBlf.drawString("Karsten Mandrup Nielsen", 41, 162);
            graphics.drawImage(this.BdhBlf, 20, 28, this);
        }
    }
    
    private void ltht(final ActionEvent actionEvent) {
        this.bdHBlf = true;
        this.bdhBlF.setEnabled(false);
        this.BdhBlF.setEnabled(false);
        this.bDHblF.setVisible(true);
        this.repaint();
    }
    
    private void Ltht(final ActionEvent actionEvent) {
        this.bdHBlf = false;
        this.bdhBlF.setEnabled(true);
        this.BdhBlF.setEnabled(true);
        this.bDHblF.setVisible(false);
        this.repaint();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.BDhBLf = x;
        this.bdHBLf = y;
        for (int n = 1, n2 = 0; n2 < this.BDhBlF.size() && n == 1; ++n2) {
            this.bDhBLf = (Bil)this.BDhBlF.elementAt(n2);
            if (x > this.bDhBLf.getX() && x < this.bDhBLf.getMaxX() && y > this.bDhBLf.getY() && y < this.bDhBLf.getMaxY()) {
                n = 0;
                this.BdhBLf = this.bDhBLf;
            }
            else {
                this.BdhBLf = null;
            }
        }
        if (this.BdhBLf != null) {
            this.BdHBLf = this.BdhBLf.getY();
            this.bDHBLf = this.BdhBLf.getX();
            if (this.BdhBLf.NS()) {
                this.BDHBLf = this.lTht(this.bDHBLf, this.BdHBLf, this.BdhBLf);
                this.bdhblF = this.LTht(this.bDHBLf, this.BdHBLf, this.BdhBLf);
            }
            if (!this.BdhBLf.NS()) {
                this.BdhblF = this.LtHt(this.bDHBLf, this.BdHBLf, this.BdhBLf);
                this.bDhblF = this.ltHt(this.bDHBLf, this.BdHBLf, this.BdhBLf);
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.BdhBLf != null) {
            if (!this.BdhBLf.NS()) {
                final int x = this.BdhBLf.getX();
                int x2;
                if (x < 24) {
                    x2 = 8;
                }
                else if (x < 64) {
                    x2 = 48;
                }
                else if (x < 104) {
                    x2 = 88;
                }
                else if (x < 144) {
                    x2 = 128;
                }
                else if (x < 184) {
                    x2 = 168;
                }
                else {
                    x2 = 208;
                }
                this.BdhBLf.setX(x2);
            }
            else {
                final int y = this.BdhBLf.getY();
                int y2;
                if (y < 34) {
                    y2 = 15;
                }
                else if (y < 74) {
                    y2 = 55;
                }
                else if (y < 114) {
                    y2 = 95;
                }
                else if (y < 154) {
                    y2 = 135;
                }
                else if (y < 194) {
                    y2 = 175;
                }
                else {
                    y2 = 215;
                }
                this.BdhBLf.setY(y2);
            }
            this.BdHBlf.play();
            if (this.bdhBLf.getY() < 17 && this.BdhBLf == this.bdhBLf) {
                this.BDHblF.setText("   Level " + this.BDHbLf + " Complete!");
                this.bDHBlf.play();
                this.bdhBlF.remove(this.BDHbLf - 1);
                this.bdhBlF.insert("Completed!", this.BDHbLf - 1);
                this.BdHbLf[this.BDHbLf - 1] = true;
                if (this.BdHblf == 1) {
                    if (this.BdHbLf[3] && this.BdHbLf[4] && this.BdHbLf[5] && !this.bDHbLf[1]) {
                        this.bDHbLf[1] = true;
                        this.bDHblf = 2;
                        this.indtastNavn();
                    }
                    else if (this.BdHbLf[6] && this.BdHbLf[7] && this.BdHbLf[8] && !this.bDHbLf[2]) {
                        this.bDHbLf[2] = true;
                        this.bDHblf = 3;
                        this.indtastNavn();
                    }
                    else if (this.BdHbLf[9] && this.BdHbLf[10] && this.BdHbLf[11] && !this.bDHbLf[3]) {
                        this.bDHbLf[3] = true;
                        this.bDHblf = 4;
                        this.indtastNavn();
                    }
                }
            }
            this.repaint();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.showStatus("Rush Hour - Binary Arts iD " + this.BDHblf);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (this.BdhBLf.NS()) {
            final int y2 = this.BdHBLf + (0 - this.bdHBLf + y);
            this.BdhBLf.setY(y2);
            final int maxY = this.BdhBLf.getMaxY();
            if (y2 < this.bdhblF) {
                this.BdhBLf.setY(this.bdhblF);
            }
            if (maxY > this.BDHBLf) {
                this.BdhBLf.setY(this.BDHBLf - this.BdhBLf.getHeight());
            }
        }
        else {
            final int x2 = this.bDHBLf + (0 - this.BDhBLf + x);
            this.BdhBLf.setX(x2);
            final int maxX = this.BdhBLf.getMaxX();
            if (x2 < this.bDhblF) {
                this.BdhBLf.setX(this.bDhblF);
            }
            if (maxX > this.BdhblF) {
                this.BdhBLf.setX(this.BdhblF - this.BdhBLf.getWidth());
            }
        }
        this.repaint();
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private int lTht(int n, final int n2, final Bil bil) {
        n += 5;
        int n3 = 250;
        boolean b = true;
        boolean b2 = true;
        for (int n4 = n2 + (bil.getHeight() + 13); n4 < 250 && b2; n4 += 40) {
            for (int n5 = 0; n5 < this.BDhBlF.size() && b; ++n5) {
                this.bDhBLf = (Bil)this.BDhBlF.elementAt(n5);
                if (this.bDhBLf != bil && n > this.bDhBLf.getX() && n < this.bDhBLf.getMaxX() && n4 > this.bDhBLf.getY() && n4 < this.bDhBLf.getMaxY()) {
                    b = false;
                    b2 = false;
                    n3 = n4 - 13;
                }
                else {
                    n3 = 250;
                }
            }
        }
        return n3;
    }
    
    private int LTht(int n, final int n2, final Bil bil) {
        n += 5;
        int n3 = 15;
        boolean b = true;
        boolean b2 = true;
        for (int n4 = n2 - 8; n4 > 15 && b2; n4 -= 40) {
            for (int n5 = 0; n5 < this.BDhBlF.size() && b; ++n5) {
                this.bDhBLf = (Bil)this.BDhBlF.elementAt(n5);
                if (this.bDhBLf != bil && n > this.bDhBLf.getX() && n < this.bDhBLf.getMaxX() && n4 > this.bDhBLf.getY() && n4 < this.bDhBLf.getMaxY()) {
                    b = false;
                    b2 = false;
                    n3 = n4 + 8;
                }
                else {
                    n3 = 15;
                }
            }
        }
        return n3;
    }
    
    private int ltHt(final int n, int n2, final Bil bil) {
        n2 += 5;
        int n3 = 5;
        boolean b = true;
        boolean b2 = true;
        for (int n4 = n - 8; n4 > 4 && b2; n4 -= 40) {
            for (int n5 = 0; n5 < this.BDhBlF.size() && b; ++n5) {
                this.bDhBLf = (Bil)this.BDhBlF.elementAt(n5);
                if (this.bDhBLf != bil && n4 > this.bDhBLf.getX() && n4 < this.bDhBLf.getMaxX() && n2 > this.bDhBLf.getY() && n2 < this.bDhBLf.getMaxY()) {
                    b = false;
                    b2 = false;
                    n3 = n4 + 8;
                }
                else {
                    n3 = 5;
                }
            }
        }
        return n3;
    }
    
    private int LtHt(final int n, int n2, final Bil bil) {
        n2 += 5;
        int n3 = 240;
        boolean b = true;
        boolean b2 = true;
        for (int n4 = n + (bil.getWidth() + 13); n4 < 239 && b2; n4 += 40) {
            for (int n5 = 0; n5 < this.BDhBlF.size() && b; ++n5) {
                this.bDhBLf = (Bil)this.BDhBlF.elementAt(n5);
                if (this.bDhBLf != bil && n4 > this.bDhBLf.getX() && n4 < this.bDhBLf.getMaxX() && n2 > this.bDhBLf.getY() && n2 < this.bDhBLf.getMaxY()) {
                    b = false;
                    b2 = false;
                    n3 = n4 - 13;
                }
                else {
                    n3 = 240;
                }
            }
        }
        return n3;
    }
    
    public void a() {
        if (this.BdHblf == 1) {
            try {
                final String string = this.getDocumentBase().toString();
                this.getAppletContext().showDocument(new URL("http://www.G5.dk/highscore/score.asp" + "?n=" + this.bdHblf + "&s=" + this.bDHblf + "&i=" + this.BDHblf + "&c=" + ((this.bDHblf * 3 + 3) * 2 + (this.BDHblf + 5)) + "&c2=" + (this.bdHblf.length() + string.length()) + "&u=" + string), "_blank");
            }
            catch (Exception ex) {}
        }
    }
    
    private void lTHt(final int n) {
        final int n2 = n - 1;
        this.BDhBlF.removeAllElements();
        final int[] array = { 8, 6, 12, 8, 12, 8, 7, 13, 12, 11, 12, 12 };
        final byte[] array2 = { 21, 5, 25, 35, 12, 1, 14, 32, 21, 18, 22, 29, 4, 15, 27, 1, 2, 13, 3, 5, 14, 23, 25, 16, 9, 34, 21, 19, 5, 22, 4, 12, 13, 31, 15, 2, 5, 16, 25, 30, 3, 9, 19, 23, 27, 33, 15, 7, 16, 8, 10, 19, 27, 17, 21, 25, 19, 6, 16, 13, 32, 21, 1, 5, 6, 25, 26, 9, 17, 29, 19, 16, 2, 33, 27, 2, 10, 18, 30, 13, 28, 34, 11, 19, 4, 20, 21, 2, 10, 25, 29, 14, 33, 1, 6, 3, 22, 27, 1, 10, 22, 23, 11, 17, 25, 34, 24, 4, 19, 27, 10, 17, 18, 28, 14, 19, 21, 29, 35, 1, 3 };
        final int[] array3 = { 0, 1, 2, 8, 14, 18, 19, 20, 0, 1, 2, 8, 18, 19, 0, 1, 2, 3, 8, 9, 10, 11, 12, 14, 18, 19, 0, 1, 8, 9, 14, 15, 18, 19, 0, 1, 2, 3, 4, 5, 8, 9, 10, 11, 12, 13, 0, 1, 2, 8, 9, 10, 11, 14, 0, 1, 8, 14, 15, 18, 19, 0, 1, 2, 3, 4, 5, 8, 9, 10, 11, 14, 18, 19, 0, 1, 2, 3, 4, 8, 9, 10, 14, 15, 18, 19, 0, 1, 2, 3, 4, 8, 9, 14, 15, 18, 19, 0, 1, 2, 3, 4, 8, 9, 10, 11, 14, 18, 19, 0, 1, 2, 3, 4, 8, 9, 10, 11, 12, 14, 18 };
        int n3 = 0;
        for (int i = 0; i < n2; ++i) {
            n3 += array[i];
        }
        for (int j = 0; j < array[n2]; ++j) {
            (this.bDhBLf = (Bil)this.bdHBlF.elementAt(array3[j + n3])).setNum(array2[j + n3]);
            this.BDhBlF.addElement(this.bDhBLf);
        }
        this.BDhBlF.trimToSize();
    }
    
    private void LTHt(final int bdHbLf) {
        this.BDHblF.setText(" ");
        this.lTHt(bdHbLf);
        this.BDHbLf = bdHbLf;
        this.repaint();
    }
    
    public void indtastNavn() {
        this.BDHBlF.setColumns(15);
        this.bdhBlF.setEnabled(false);
        this.BdHBlF.setBackground(Color.gray);
        this.BdHBlF.setVisible(true);
        this.bDHBlF.setText("HIGHSCORE! : " + this.bDHblf + " Point");
        this.paint(this.bDhBlf);
        this.BDHBlF.requestFocus();
    }
    
    void bdhblf(final ActionEvent actionEvent) {
        this.bdHblf = this.BDHBlF.getText();
        if (this.bdHblf.length() > 14) {
            this.bdHblf = this.bdHblf.substring(0, 14);
        }
        if (this.bdHblf.length() == 0) {
            this.bdHblf = "Mr. X";
        }
        this.BdHBlF.setVisible(false);
        this.bdhBlF.setEnabled(true);
        this.requestFocus();
        this.repaint();
        this.a();
    }
    
    static void Bdhblf(final rush rush, final ActionEvent actionEvent) {
        rush.ltht(actionEvent);
    }
    
    static void bDhblf(final rush rush, final ActionEvent actionEvent) {
        rush.Ltht(actionEvent);
    }
    
    static void BDhblf(final rush rush, final int n) {
        rush.LTHt(n);
    }
}
