import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Button;
import java.applet.AudioClip;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class pingpong extends Applet implements Runnable, MouseListener, MouseMotionListener
{
    String TMP;
    String userName;
    int id;
    int highscore;
    Thread wizax;
    static AudioClip click;
    AudioClip loose;
    final int SKAERMFARVE = 255;
    final int SCREENX = 400;
    final int SCREENY = 350;
    final int OBJEKTSFARVE = 255;
    final int BATLAENGDE = 36;
    Bold bold;
    Button startGame;
    Panel inputPanel;
    int m1X;
    int m1Y;
    int batY;
    int win;
    int hits;
    Image workspace;
    Graphics offscreen;
    boolean displayCredits;
    Font inputF;
    Font inputF2;
    Font adress;
    long nextTime;
    Panel panel2;
    Label label1;
    TextField textField1;
    Button button1;
    
    public pingpong() {
        this.userName = "";
        this.id = 0;
        this.highscore = 1;
        this.bold = new Bold(400, 350);
        this.startGame = new Button();
        this.inputPanel = new Panel();
        this.batY = 60;
        this.win = 0;
        this.hits = 0;
        this.displayCredits = true;
        this.inputF = new Font("Helvetica", 0, 11);
        this.inputF2 = new Font("Helvetica", 1, 18);
        this.adress = new Font("Helvetica", 1, 14);
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
        if (this.id > 99 || this.id < 0) {
            this.id = 0;
        }
        final String parameter2 = this.getParameter("highScore");
        if (parameter2 != null) {
            this.highscore = Integer.parseInt(parameter2);
        }
        pingpong.click = this.getAudioClip(this.getCodeBase(), "click.au");
        this.loose = this.getAudioClip(this.getCodeBase(), "loose.au");
        pingpong.click.play();
        this.setLayout(null);
        this.add(this.inputPanel, null);
        this.inputPanel.setBounds(137, 20, 150, 20);
        this.inputPanel.setLayout(null);
        this.inputPanel.setBackground(Color.black);
        this.inputPanel.add(this.startGame);
        this.startGame.setBounds(2, 0, 126, 20);
        this.startGame.setLabel("Start Game");
        this.startGame.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                pingpong.this.startGame_actionPerformed(actionEvent);
            }
        });
        this.workspace = this.createImage(400, 350);
        this.offscreen = this.workspace.getGraphics();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setLayout(null);
        this.setSize(426, 129);
        this.panel2.setLayout(null);
        this.add(this.panel2);
        this.panel2.setBackground(Color.lightGray);
        this.panel2.setBounds(100, 100, 200, 100);
        this.label1.setText("Name/handle (for the highscore)");
        this.panel2.add(this.label1);
        this.label1.setBounds(20, 10, 160, 24);
        this.panel2.add(this.textField1);
        this.textField1.setBounds(20, 40, 160, 20);
        this.button1.setLabel("Done");
        this.panel2.add(this.button1);
        this.button1.setBackground(Color.lightGray);
        this.button1.setBounds(37, 70, 125, 24);
        this.panel2.setVisible(false);
        this.button1.addActionListener(new SymAction());
    }
    
    private void startGame_actionPerformed(final ActionEvent actionEvent) {
        this.bold.setSpeed(2);
        this.win = 0;
        this.hits = 0;
        this.displayCredits = false;
        this.startGame.setEnabled(false);
    }
    
    public void paint(final Graphics graphics) {
        this.offscreen.setColor(Color.black);
        this.offscreen.fillRect(0, 0, 400, 350);
        this.offscreen.setColor(Color.lightGray);
        this.offscreen.drawLine(0, 50, 400, 50);
        this.offscreen.drawLine(0, 51, 400, 51);
        this.offscreen.drawLine(0, 345, 400, 345);
        this.offscreen.drawLine(0, 344, 400, 344);
        this.offscreen.drawLine(200, 50, 200, 345);
        this.offscreen.drawLine(388, this.batY, 388, this.batY + 36);
        this.offscreen.drawLine(387, this.batY, 387, this.batY + 36);
        this.offscreen.drawLine(386, this.batY, 386, this.batY + 36);
        this.offscreen.drawLine(12, this.getBatY(), 12, this.getBatY() + 36);
        this.offscreen.drawLine(13, this.getBatY(), 13, this.getBatY() + 36);
        this.offscreen.drawLine(14, this.getBatY(), 14, this.getBatY() + 36);
        this.offscreen.fillRect(this.bold.getX(), this.bold.getY(), 5, 5);
        this.offscreen.drawRect(365, 32, 30, 14);
        this.offscreen.drawString("" + this.hits, 370, 44);
        this.offscreen.drawString("Score", 366, 29);
        if (this.displayCredits) {
            this.offscreen.setFont(this.inputF);
            this.offscreen.setFont(this.inputF2);
            this.offscreen.drawString("PingPong", 60, 90);
            this.offscreen.setFont(this.inputF);
            this.offscreen.drawString("Coded by:", 76, 110);
            this.offscreen.drawString("Karsten Mandrup Nielsen", 45, 123);
            this.offscreen.drawString("Hello to:", 82, 154);
            this.offscreen.drawString("The rest of G5", 64, 166);
            this.offscreen.setFont(this.adress);
            this.offscreen.drawString("www.G5.dk", 255, 330);
            this.offscreen.setFont(this.inputF);
        }
        this.offscreen.drawString("" + this.userName, 8, 45);
        graphics.drawImage(this.workspace, 0, 0, this);
    }
    
    public void start() {
        if (this.wizax == null) {
            (this.wizax = new Thread(this)).start();
        }
    }
    
    public void run() {
        while (true) {
            this.vent(10);
            if (!this.displayCredits) {
                if (this.bold.getX() > 400) {
                    this.win = 1;
                    this.loose.play();
                    this.startGame.setEnabled(true);
                    this.displayCredits = true;
                    if (this.highscore == 1 && this.hits > 5) {
                        if (this.userName.length() == 0) {
                            this.indtastNavn();
                        }
                        else {
                            this.sendHighscore();
                        }
                    }
                    this.bold.setX(198);
                    this.bold.setY(200);
                    this.bold.setSpeed(0);
                    this.bold.setHeading(1);
                    this.bold.beregnNext();
                    this.startGame.setEnabled(true);
                }
                else if (this.bold.getX() < 15) {
                    this.bold.headingSkiftX1();
                }
                if (this.win == 0) {
                    if (this.bold.getX() + 5 >= 386 && this.bold.getX() + 5 <= 392) {
                        if (this.bold.getY() + 3 >= this.batY && this.bold.getY() <= this.batY + 36) {
                            if (this.bold.getY() >= this.batY + 6 && this.bold.getY() <= this.batY + 36 - 6) {
                                this.bold.headingSkiftX1();
                            }
                            else {
                                this.bold.headingSkiftX2();
                            }
                            ++this.hits;
                        }
                        if (this.hits % 5 == 0) {
                            this.bold.setSpeed(this.bold.getSpeed() + 1);
                        }
                    }
                    this.bold.move();
                }
                this.repaint();
            }
        }
    }
    
    final void kontrol() {
        if (this.m1Y < 51) {
            this.batY = 51;
        }
        else if (this.m1Y > 308) {
            this.batY = 308;
        }
        else {
            this.batY = this.m1Y;
        }
    }
    
    final int getBatY() {
        if (this.bold.getY() - 15 < 52) {
            return 52;
        }
        if (this.bold.getY() - 15 > 307) {
            return 307;
        }
        return this.bold.getY() - 15;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    void vent(final int n) {
        try {
            Thread.sleep(Math.max(3L, this.nextTime - System.currentTimeMillis()));
        }
        catch (InterruptedException ex) {}
        this.nextTime = System.currentTimeMillis() + n;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.showStatus("PingPong - www.G5.dk");
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.m1Y = mouseEvent.getY();
        if (this.m1Y < 52) {
            this.batY = 52;
        }
        else if (this.m1Y > 307) {
            this.batY = 307;
        }
        else {
            this.batY = this.m1Y;
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.m1Y = mouseEvent.getY();
        if (this.m1Y < 52) {
            this.batY = 52;
        }
        else if (this.m1Y > 307) {
            this.batY = 307;
        }
        else {
            this.batY = this.m1Y;
        }
    }
    
    public static void playSound() {
        pingpong.click.play();
    }
    
    public void indtastNavn() {
        this.textField1.setColumns(15);
        this.startGame.setEnabled(false);
        this.panel2.setVisible(true);
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
        this.sendHighscore();
    }
    
    public void sendHighscore() {
        if (this.highscore == 1) {
            try {
                final String string = this.getDocumentBase().toString();
                final String string2 = "http://www.G5.dk/highscore/score.asp" + "?n=" + this.userName + "&s=" + this.hits + "&i=" + this.id + "&c=" + ((this.hits * 3 + 3) * 2 + (this.id + 5)) + "&c2=" + (this.userName.length() + string.length()) + "&u=" + string;
                this.TMP = string2;
                this.getAppletContext().showDocument(new URL(string2), "_blank");
            }
            catch (Exception ex) {}
        }
    }
    
    class SymAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            if (actionEvent.getSource() == pingpong.this.button1) {
                pingpong.this.button1_ActionPerformed(actionEvent);
            }
        }
    }
}
