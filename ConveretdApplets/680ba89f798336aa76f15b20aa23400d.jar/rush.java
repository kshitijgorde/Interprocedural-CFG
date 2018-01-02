import java.awt.event.ItemEvent;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
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
import java.awt.MediaTracker;
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
    String userName;
    int highscore;
    int score;
    int id;
    Image workspace;
    Image abI;
    Graphics offscreen;
    Graphics ab;
    boolean abB;
    AudioClip click;
    AudioClip tire;
    Font inputF;
    Font outputF;
    Font overskrift;
    Font ww;
    Image[] biler;
    Image backP;
    boolean[] completedLevels;
    boolean[] send;
    int levelNr;
    Bil b0;
    Bil aktiv;
    Bil bil;
    int mousePressedX;
    int mousePressedY;
    int aktivY;
    int aktivX;
    int maxTmpY;
    int minTmpY;
    int maxTmpX;
    int minTmpX;
    int antalBiler;
    MediaTracker mt;
    Panel outputPanel;
    Panel aboutPanel;
    Panel okPanel;
    TextField output;
    Choice input;
    Button about;
    Button ok;
    Vector VBiler;
    Vector alleBiler;
    Panel panel2;
    Label label1;
    TextField textField1;
    Button button1;
    
    public rush() {
        this.userName = "";
        this.highscore = 1;
        this.score = 0;
        this.id = 200;
        this.abB = false;
        this.inputF = new Font("Helvetica", 0, 11);
        this.outputF = new Font("Helvetica", 1, 11);
        this.overskrift = new Font("Helvetica", 1, 17);
        this.ww = new Font("Helvetica", 1, 11);
        this.biler = new Image[5];
        this.completedLevels = new boolean[12];
        this.send = new boolean[] { false, false, false, false };
        this.levelNr = 1;
        this.outputPanel = new Panel();
        this.aboutPanel = new Panel();
        this.okPanel = new Panel();
        this.about = new Button();
        this.ok = new Button();
        this.VBiler = new Vector();
        this.alleBiler = new Vector();
        this.panel2 = new Panel();
        this.label1 = new Label();
        this.textField1 = new TextField();
        this.button1 = new Button();
    }
    
    public void init() {
        final String parameter = this.getParameter("highScoreID");
        if (parameter != null) {
            this.id = Integer.parseInt(parameter);
        }
        if (this.id < 200 || this.id > 299) {
            this.id = 200;
        }
        final String parameter2 = this.getParameter("highScore");
        if (parameter2 != null) {
            this.highscore = Integer.parseInt(parameter2);
        }
        this.panel2.setLayout(null);
        this.add(this.panel2);
        this.panel2.setBackground(Color.lightGray);
        this.panel2.setBounds(100, 100, 200, 100);
        this.label1.setText("YOU MADE A HIGHSCORE!");
        this.panel2.add(this.label1);
        this.label1.setBounds(38, 10, 160, 24);
        this.panel2.add(this.textField1);
        this.textField1.setBounds(20, 40, 160, 20);
        this.button1.setLabel("Done");
        this.panel2.add(this.button1);
        this.button1.setBackground(Color.lightGray);
        this.button1.setBounds(37, 70, 125, 24);
        this.panel2.setVisible(false);
        this.button1.addActionListener(new SymAction());
        this.click = this.getAudioClip(this.getCodeBase(), "click.au");
        this.tire = this.getAudioClip(this.getCodeBase(), "tire.au");
        this.mt = new MediaTracker(this);
        for (int i = 0; i < 5; ++i) {
            this.biler[i] = this.getImage(this.getDocumentBase(), i + ".gif");
            this.mt.addImage(this.biler[i], 0);
        }
        this.backP = this.getImage(this.getDocumentBase(), "back.gif");
        this.mt.addImage(this.backP, 0);
        this.showStatus("Loading Graphics");
        try {
            this.mt.waitForID(0);
        }
        catch (InterruptedException ex) {}
        this.click.play();
        this.b0 = new Bil(0, 0, true, this.biler[2], 75, 35);
        this.alleBiler.addElement(this.b0);
        for (int j = 0; j < 7; ++j) {
            this.alleBiler.addElement(new Bil(0, 0, true, this.biler[0], 75, 35));
        }
        for (int k = 0; k < 6; ++k) {
            this.alleBiler.addElement(new Bil(0, 0, false, this.biler[1], 35, 75));
        }
        for (int l = 0; l < 4; ++l) {
            this.alleBiler.addElement(new Bil(0, 0, true, this.biler[3], 115, 35));
        }
        for (int n = 0; n < 4; ++n) {
            this.alleBiler.addElement(new Bil(0, 0, false, this.biler[4], 35, 115));
        }
        this.setBackground(Color.lightGray);
        this.workspace = this.createImage(400, 300);
        this.abI = this.createImage(210, 200);
        this.offscreen = this.workspace.getGraphics();
        this.ab = this.abI.getGraphics();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.output = new TextField(20);
        this.input = new Choice();
        int n2 = -1;
        final String[] array = { "Beginner", "Intermediate", "Advanced", "Expert" };
        for (int n3 = 0; n3 < 12; ++n3) {
            if (n3 % 3 == 0) {
                ++n2;
            }
            this.input.add("Level " + (n3 + 1) + " " + array[n2]);
        }
        this.output.setEditable(false);
        this.output.setFont(this.outputF);
        this.input.setFont(this.inputF);
        this.setLayout(null);
        this.add(this.outputPanel, null);
        this.outputPanel.setBounds(255, 8, 135, 50);
        this.outputPanel.setLayout(null);
        this.outputPanel.add(this.input);
        this.outputPanel.add(this.output);
        this.add(this.aboutPanel, null);
        this.aboutPanel.setBounds(255, 235, 135, 30);
        this.aboutPanel.setLayout(null);
        this.add(this.okPanel, null);
        this.okPanel.setBounds(100, 200, 40, 20);
        this.okPanel.setLayout(null);
        this.about.setBounds(10, 0, 126, 20);
        this.about.setLabel("About");
        this.about.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                rush.this.about_actionPerformed(actionEvent);
            }
        });
        this.ok.setBounds(0, 0, 40, 20);
        this.ok.setLabel("Ok");
        this.ok.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                rush.this.ok_actionPerformed(actionEvent);
            }
        });
        this.aboutPanel.add(this.about);
        this.okPanel.add(this.ok);
        this.okPanel.setVisible(false);
        this.output.setBounds(5, 5, 127, 20);
        this.input.setBounds(5, 30, 127, 20);
        this.input.addItemListener(new inputChoiseHandler());
        this.level(1);
    }
    
    public void paint(final Graphics graphics) {
        this.offscreen.setColor(Color.lightGray);
        this.offscreen.fillRect(0, 0, 245, 280);
        this.offscreen.setColor(Color.darkGray);
        this.offscreen.drawRect(3, 10, 248, 245);
        this.offscreen.drawRect(4, 11, 246, 243);
        this.offscreen.setColor(Color.lightGray);
        this.offscreen.drawLine(80, 10, 124, 10);
        this.offscreen.drawLine(80, 11, 124, 11);
        final int n = 5;
        final int n2 = 5;
        int n3 = 8;
        int n4 = 15;
        this.offscreen.setColor(Color.darkGray);
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 6; ++j) {
                this.offscreen.drawRoundRect(n3, n4, n2 + 30, n + 30, 4, 4);
                n3 += 40;
            }
            n4 += 40;
            n3 = 8;
        }
        for (int k = 0; k < this.VBiler.size(); ++k) {
            this.bil = (Bil)this.VBiler.elementAt(k);
            this.offscreen.drawImage(this.bil.getImage(), this.bil.getX(), this.bil.getY(), this);
        }
        this.offscreen.setFont(this.overskrift);
        this.offscreen.drawString("Help the red", 277, 207);
        this.offscreen.drawString("beetle to exit.", 274, 222);
        this.offscreen.setFont(this.outputF);
        this.offscreen.drawString("EXIT", 95, 12);
        this.offscreen.setFont(this.ww);
        this.offscreen.setColor(Color.red);
        this.offscreen.drawImage(this.backP, 275, 80, this);
        this.offscreen.drawString("www.G5.dk", 290, 133);
        this.offscreen.drawString("Rush Hour", 292, 82);
        if (!this.abB) {
            graphics.drawImage(this.workspace, 0, 0, this);
        }
        else {
            this.ab.setColor(Color.white);
            this.ab.fillRect(0, 0, 210, 200);
            this.ab.setColor(Color.black);
            this.ab.drawRect(0, 0, 209, 199);
            this.ab.drawRect(1, 1, 207, 197);
            this.ab.setFont(this.outputF);
            this.ab.drawString("Rush Hour", 73, 20);
            this.ab.drawString("To enter highscore - Complete:", 18, 82);
            this.ab.setFont(this.inputF);
            this.ab.drawString("© 1998 Binary Arts Corp.", 38, 36);
            this.ab.drawString("If you want more levels and more fun", 18, 54);
            this.ab.drawString("buy the brilliant game!", 18, 66);
            this.ab.drawString("Beginner:", 27, 95);
            this.ab.drawString("1 pts.", 95, 95);
            this.ab.drawString("Intermediate:", 27, 106);
            this.ab.drawString("2 pts.", 95, 106);
            this.ab.drawString("Advanced:", 27, 117);
            this.ab.drawString("3 pts.", 95, 117);
            this.ab.drawString("Expert:", 27, 128);
            this.ab.drawString("4 pts.", 95, 128);
            this.ab.drawString("Programmed by:", 65, 150);
            this.ab.drawString("Karsten Mandrup Nielsen", 41, 162);
            graphics.drawImage(this.abI, 20, 28, this);
        }
    }
    
    private void about_actionPerformed(final ActionEvent actionEvent) {
        this.abB = true;
        this.input.setEnabled(false);
        this.about.setEnabled(false);
        this.okPanel.setVisible(true);
        this.repaint();
    }
    
    private void ok_actionPerformed(final ActionEvent actionEvent) {
        this.abB = false;
        this.input.setEnabled(true);
        this.about.setEnabled(true);
        this.okPanel.setVisible(false);
        this.repaint();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.mousePressedX = x;
        this.mousePressedY = y;
        for (int n = 1, n2 = 0; n2 < this.VBiler.size() && n == 1; ++n2) {
            this.bil = (Bil)this.VBiler.elementAt(n2);
            if (x > this.bil.getX() && x < this.bil.getMaxX() && y > this.bil.getY() && y < this.bil.getMaxY()) {
                n = 0;
                this.aktiv = this.bil;
            }
            else {
                this.aktiv = null;
            }
        }
        if (this.aktiv != null) {
            this.aktivY = this.aktiv.getY();
            this.aktivX = this.aktiv.getX();
            if (this.aktiv.NS()) {
                this.maxTmpY = this.maxTmpY(this.aktivX, this.aktivY, this.aktiv);
                this.minTmpY = this.minTmpY(this.aktivX, this.aktivY, this.aktiv);
            }
            if (!this.aktiv.NS()) {
                this.maxTmpX = this.maxTmpX(this.aktivX, this.aktivY, this.aktiv);
                this.minTmpX = this.minTmpX(this.aktivX, this.aktivY, this.aktiv);
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.aktiv != null) {
            if (!this.aktiv.NS()) {
                final int x = this.aktiv.getX();
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
                this.aktiv.setX(x2);
            }
            else {
                final int y = this.aktiv.getY();
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
                this.aktiv.setY(y2);
            }
            this.click.play();
            if (this.b0.getY() < 17 && this.aktiv == this.b0) {
                this.output.setText("   Level " + this.levelNr + " Complete!");
                this.tire.play();
                this.input.remove(this.levelNr - 1);
                this.input.insert("Completed!", this.levelNr - 1);
                this.completedLevels[this.levelNr - 1] = true;
                if (this.highscore == 1) {
                    if (this.completedLevels[0] && this.completedLevels[1] && this.completedLevels[2] && !this.send[0]) {
                        this.send[0] = true;
                        this.score = 1;
                        this.indtastNavn();
                    }
                    else if (this.completedLevels[3] && this.completedLevels[4] && this.completedLevels[5] && !this.send[1]) {
                        this.send[1] = true;
                        this.score = 2;
                        this.indtastNavn();
                    }
                    else if (this.completedLevels[6] && this.completedLevels[7] && this.completedLevels[8] && !this.send[2]) {
                        this.send[2] = true;
                        this.score = 3;
                        this.indtastNavn();
                    }
                    else if (this.completedLevels[9] && this.completedLevels[10] && this.completedLevels[11] && !this.send[3]) {
                        this.send[3] = true;
                        this.score = 4;
                        this.indtastNavn();
                    }
                }
            }
            this.repaint();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.showStatus("Rush Hour - Binary Arts iD " + this.id);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (this.aktiv.NS()) {
            final int y2 = this.aktivY + (0 - this.mousePressedY + y);
            this.aktiv.setY(y2);
            final int maxY = this.aktiv.getMaxY();
            if (y2 < this.minTmpY) {
                this.aktiv.setY(this.minTmpY);
            }
            if (maxY > this.maxTmpY) {
                this.aktiv.setY(this.maxTmpY - this.aktiv.getHeight());
            }
        }
        else {
            final int x2 = this.aktivX + (0 - this.mousePressedX + x);
            this.aktiv.setX(x2);
            final int maxX = this.aktiv.getMaxX();
            if (x2 < this.minTmpX) {
                this.aktiv.setX(this.minTmpX);
            }
            if (maxX > this.maxTmpX) {
                this.aktiv.setX(this.maxTmpX - this.aktiv.getWidth());
            }
        }
        this.repaint();
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private int maxTmpY(int n, final int n2, final Bil bil) {
        n += 5;
        int n3 = 250;
        boolean b = true;
        boolean b2 = true;
        for (int n4 = n2 + (bil.getHeight() + 13); n4 < 250 && b2; n4 += 40) {
            for (int n5 = 0; n5 < this.VBiler.size() && b; ++n5) {
                this.bil = (Bil)this.VBiler.elementAt(n5);
                if (this.bil != bil && n > this.bil.getX() && n < this.bil.getMaxX() && n4 > this.bil.getY() && n4 < this.bil.getMaxY()) {
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
    
    private int minTmpY(int n, final int n2, final Bil bil) {
        n += 5;
        int n3 = 15;
        boolean b = true;
        boolean b2 = true;
        for (int n4 = n2 - 8; n4 > 15 && b2; n4 -= 40) {
            for (int n5 = 0; n5 < this.VBiler.size() && b; ++n5) {
                this.bil = (Bil)this.VBiler.elementAt(n5);
                if (this.bil != bil && n > this.bil.getX() && n < this.bil.getMaxX() && n4 > this.bil.getY() && n4 < this.bil.getMaxY()) {
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
    
    private int minTmpX(final int n, int n2, final Bil bil) {
        n2 += 5;
        int n3 = 5;
        boolean b = true;
        boolean b2 = true;
        for (int n4 = n - 8; n4 > 4 && b2; n4 -= 40) {
            for (int n5 = 0; n5 < this.VBiler.size() && b; ++n5) {
                this.bil = (Bil)this.VBiler.elementAt(n5);
                if (this.bil != bil && n4 > this.bil.getX() && n4 < this.bil.getMaxX() && n2 > this.bil.getY() && n2 < this.bil.getMaxY()) {
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
    
    private int maxTmpX(final int n, int n2, final Bil bil) {
        n2 += 5;
        int n3 = 240;
        boolean b = true;
        boolean b2 = true;
        for (int n4 = n + (bil.getWidth() + 13); n4 < 239 && b2; n4 += 40) {
            for (int n5 = 0; n5 < this.VBiler.size() && b; ++n5) {
                this.bil = (Bil)this.VBiler.elementAt(n5);
                if (this.bil != bil && n4 > this.bil.getX() && n4 < this.bil.getMaxX() && n2 > this.bil.getY() && n2 < this.bil.getMaxY()) {
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
    
    private void levelSetup(final int n) {
        final int n2 = n - 1;
        this.VBiler.removeAllElements();
        final int[] array = { 8, 6, 12, 8, 12, 8, 7, 13, 12, 11, 12, 12 };
        final byte[] array2 = { 21, 5, 25, 35, 12, 1, 14, 32, 21, 18, 22, 29, 4, 15, 27, 1, 2, 13, 3, 5, 14, 23, 25, 16, 9, 34, 21, 19, 5, 22, 4, 12, 13, 31, 15, 2, 5, 16, 25, 30, 3, 9, 19, 23, 27, 33, 15, 7, 16, 8, 10, 19, 27, 17, 21, 25, 19, 6, 16, 13, 32, 21, 1, 5, 6, 25, 26, 9, 17, 29, 19, 16, 2, 33, 27, 2, 10, 18, 30, 13, 28, 34, 11, 19, 4, 20, 21, 2, 10, 25, 29, 14, 33, 1, 6, 3, 22, 27, 1, 10, 22, 23, 11, 17, 25, 34, 24, 4, 19, 27, 10, 17, 18, 28, 14, 19, 21, 29, 35, 1, 3 };
        final int[] array3 = { 0, 1, 2, 8, 14, 18, 19, 20, 0, 1, 2, 8, 18, 19, 0, 1, 2, 3, 8, 9, 10, 11, 12, 14, 18, 19, 0, 1, 8, 9, 14, 15, 18, 19, 0, 1, 2, 3, 4, 5, 8, 9, 10, 11, 12, 13, 0, 1, 2, 8, 9, 10, 11, 14, 0, 1, 8, 14, 15, 18, 19, 0, 1, 2, 3, 4, 5, 8, 9, 10, 11, 14, 18, 19, 0, 1, 2, 3, 4, 8, 9, 10, 14, 15, 18, 19, 0, 1, 2, 3, 4, 8, 9, 14, 15, 18, 19, 0, 1, 2, 3, 4, 8, 9, 10, 11, 14, 18, 19, 0, 1, 2, 3, 4, 8, 9, 10, 11, 12, 14, 18 };
        int n3 = 0;
        for (int i = 0; i < n2; ++i) {
            n3 += array[i];
        }
        for (int j = 0; j < array[n2]; ++j) {
            (this.bil = (Bil)this.alleBiler.elementAt(array3[j + n3])).setNum(array2[j + n3]);
            this.VBiler.addElement(this.bil);
        }
        this.VBiler.trimToSize();
    }
    
    private void level(final int levelNr) {
        this.output.setText(" ");
        this.levelSetup(levelNr);
        this.levelNr = levelNr;
        this.repaint();
    }
    
    public void indtastNavn() {
        this.textField1.setColumns(15);
        this.input.setEnabled(false);
        this.panel2.setBackground(Color.gray);
        this.panel2.setVisible(true);
        this.label1.setText("HIGHSCORE! : " + this.score + " Point");
        this.paint(this.offscreen);
        this.textField1.requestFocus();
    }
    
    void button1_ActionPerformed(final ActionEvent actionEvent) {
        this.userName = this.textField1.getText();
        if (this.userName.length() > 14) {
            this.userName = this.userName.substring(0, 14);
        }
        if (this.userName.length() == 0) {
            this.userName = "Mr. X";
        }
        this.panel2.setVisible(false);
        this.input.setEnabled(true);
        this.requestFocus();
        this.repaint();
        this.sendHighscore();
    }
    
    public void sendHighscore() {
        if (this.highscore == 1) {
            try {
                final String string = this.getDocumentBase().toString();
                this.getAppletContext().showDocument(new URL("http://www.G5.dk/highscore/score.asp" + "?n=" + this.userName + "&s=" + this.score + "&i=" + this.id + "&c=" + ((this.score * 3 + 3) * 2 + (this.id + 5)) + "&c2=" + (this.userName.length() + string.length()) + "&u=" + string), "_blank");
            }
            catch (Exception ex) {}
        }
    }
    
    class inputChoiseHandler implements ItemListener
    {
        public void itemStateChanged(final ItemEvent itemEvent) {
            if (rush.this.input.getSelectedItem().compareTo("Completed!") != 0) {
                rush.this.level(rush.this.input.getSelectedIndex() + 1);
            }
        }
    }
    
    class SymAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            if (actionEvent.getSource() == rush.this.button1) {
                rush.this.button1_ActionPerformed(actionEvent);
            }
        }
    }
}
