import java.awt.Graphics;
import java.util.Random;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.GridBagConstraints;
import java.net.URL;
import java.awt.Choice;
import java.awt.Label;
import java.awt.TextField;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class hardmine extends Applet implements Runnable
{
    TextField Xsize;
    TextField Ysize;
    TextField Mines;
    Label nleft;
    Label Time;
    Label myMinesHeader;
    Label myTimeHeader;
    Label myMessage;
    Label mySecondMessage;
    Label myPlayerNameLabel;
    Choice c;
    int nxButtons;
    int nyButtons;
    int nMines;
    int nl;
    ImgButton[][] it;
    ImgButton restartButton;
    ImgButton firstflagButton;
    ImgButton secondflagButton;
    ImgButtonImage[] Buttons;
    TextField tfPlayerName;
    int itouch;
    int jtouch;
    int ibutton;
    int[] imines;
    int[] jmines;
    int[][] matrix;
    int[][] untouched;
    int bd;
    int sz;
    int lastsz;
    int ntouch;
    long starttime;
    long nowtime;
    boolean firstPaint;
    int xmin;
    int ymin;
    int maxWidth;
    int maxHeight;
    long tdiff;
    URL scoreURL;
    public static final int mine = 9;
    public static final int flag = 11;
    public static final int hidden = 13;
    public static final int question = 10;
    public static final int expmine = 12;
    Thread timer;
    boolean inPlay;
    boolean won;
    String level;
    long seednum;
    String para;
    String playerName;
    
    public hardmine() {
        this.Buttons = new ImgButtonImage[16];
        this.tfPlayerName = new TextField(20);
        this.bd = 4;
        this.sz = 16 + this.bd * 2;
        this.lastsz = 0;
        this.firstPaint = true;
        this.xmin = 0;
        this.ymin = 62 + this.bd * 2;
        this.maxWidth = 50;
        this.maxHeight = 50;
        this.inPlay = true;
        this.won = false;
        this.level = "hard";
        this.seednum = 0L;
    }
    
    public void SubmitScore() {
        try {
            this.getAppletContext().showDocument(new URL("http://porter.desy.de/ms/cgi-bin/uncgi.cgi/Mina.cgi/query?level=" + this.level + "&score=" + this.tdiff + "&playerName=" + this.makeGoodString()));
        }
        catch (Exception ex) {
            this.showStatus("Error " + ex);
        }
    }
    
    public void YouLose() {
        this.myMessage.setText("You lose");
        this.mySecondMessage.setText(" ");
        this.stop();
        this.inPlay = false;
    }
    
    public void YouWin() {
        this.tdiff = this.nowtime - this.starttime;
        this.myMessage.setText("You won in " + this.tdiff + " ms.");
        this.mySecondMessage.setText(" ");
        this.stop();
        this.inPlay = false;
        this.won = true;
    }
    
    void buildConstraints(final GridBagConstraints gridBagConstraints, final int gridx, final int gridy, final int gridwidth, final int gridheight, final int n, final int n2) {
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = gridheight;
        gridBagConstraints.weightx = n;
        gridBagConstraints.weighty = n2;
    }
    
    public int countNeighbors(final int n, final int n2) {
        int n3 = 0;
        if (this.matrix[n][n2] == 9) {
            n3 = 9;
        }
        else {
            for (int i = n - 1; i <= n + 1; ++i) {
                for (int j = n2 - 1; j <= n2 + 1; ++j) {
                    if (i > -1 && i < this.nxButtons && j > -1 && j < this.nyButtons && this.matrix[i][j] == 9) {
                        ++n3;
                    }
                }
            }
        }
        return n3;
    }
    
    public boolean doMouseDown(final Event event, final int n, final int n2) {
        if (n > this.xmin + this.nyButtons * this.sz / 2 - (50 + this.bd * 2) / 2 && n < this.xmin + this.nyButtons * this.sz / 2 + (50 + this.bd * 2) / 2 && n2 < this.ymin && n2 > this.ymin - 62 + this.bd * 2) {
            return this.restartButton.mouseDown(event, n, n2, this);
        }
        if (n > this.xmin && n < this.xmin + (50 + this.bd * 2) && n2 > this.ymin + this.nxButtons * this.sz && n2 < this.ymin + this.nxButtons * this.sz + (25 + this.bd * 2)) {
            return this.firstflagButton.mouseDown(event, n, n2, this);
        }
        if (this.inPlay) {
            int jtouch = (n - this.it[0][0].getX()) / this.it[0][0].getXsize();
            int itouch = (n2 - this.it[0][0].getY()) / this.it[0][0].getYsize();
            if (n2 - this.it[0][0].getY() < 0) {
                itouch = -1;
            }
            if (n - this.it[0][0].getX() < 0) {
                jtouch = -1;
            }
            this.ibutton = 0;
            if (itouch >= 0 && itouch <= this.nxButtons && jtouch >= 0 && jtouch <= this.nyButtons) {
                this.startTimer();
                this.itouch = itouch;
                this.jtouch = jtouch;
                this.ibutton = 1;
                if (event.metaDown()) {
                    this.ibutton = 2;
                }
                return this.it[itouch][jtouch].mouseDown(event, n, n2, this);
            }
        }
        return false;
    }
    
    public boolean doMouseUp(final Event event, final int n, final int n2) {
        boolean mouseUp = false;
        boolean b = false;
        boolean b2 = false;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        if (n > this.xmin + this.nyButtons * this.sz / 2 - (50 + this.bd * 2) / 2 && n < this.xmin + this.nyButtons * this.sz / 2 + (50 + this.bd * 2) / 2 && n2 < this.ymin && n2 > this.ymin - 62 + this.bd * 2) {
            this.won = false;
            this.dosetup();
            this.repaint();
            this.Time.setText("0");
            this.myMessage.setText("          Click in the top          ");
            this.mySecondMessage.setText("          left corner to begin.          ");
            return true;
        }
        if (n > this.xmin && n < this.xmin + (50 + this.bd * 2) && n2 > this.ymin + this.nxButtons * this.sz && n2 < this.ymin + this.nxButtons * this.sz + (25 + this.bd * 2)) {
            if (this.won) {
                this.SubmitScore();
            }
            return this.firstflagButton.mouseUp(event, n, n2, this);
        }
        if (this.inPlay) {
            int n7 = (n - this.it[0][0].getX()) / this.it[0][0].getXsize();
            int n8 = (n2 - this.it[0][0].getY()) / this.it[0][0].getYsize();
            if (n2 - this.it[0][0].getY() < 0) {
                n8 = -1;
            }
            if (n - this.it[0][0].getX() < 0) {
                n7 = -1;
            }
            if (n8 >= 0 && n8 <= this.nxButtons && n7 >= 0 && n7 <= this.nyButtons && this.itouch == n8 && this.jtouch == n7) {
                if (this.ibutton == 1 && this.untouched[n8][n7] == 0) {
                    b = this.youClicked(n8, n7);
                }
                else if (this.ibutton == 2) {
                    switch (this.untouched[n8][n7]) {
                        case 0: {
                            this.it[n8][n7].ChangeImage(this.Buttons[11], this);
                            this.untouched[n8][n7] = 2;
                            ++this.ntouch;
                            --this.nl;
                            break;
                        }
                        case 1: {
                            for (int i = -1; i < 2; ++i) {
                                for (int j = -1; j < 2; ++j) {
                                    if ((i != 0 || j != 0) && n8 + i >= 0 && n7 + j >= 0 && n8 + i < this.nxButtons && n7 + j < this.nyButtons) {
                                        if (this.untouched[n8 + i][n7 + j] == 0 && this.matrix[n8 + i][n7 + j] == 9) {
                                            n5 = n8 + i;
                                            n6 = n7 + j;
                                            b2 = true;
                                        }
                                        if (this.untouched[n8 + i][n7 + j] > 1) {
                                            ++n3;
                                        }
                                        if (this.untouched[n8 + i][n7 + j] != 1) {
                                            ++n4;
                                        }
                                    }
                                }
                            }
                            if (n4 == this.matrix[n8][n7]) {
                                for (int k = -1; k < 2; ++k) {
                                    for (int l = -1; l < 2; ++l) {
                                        if ((k != 0 || l != 0) && n8 + k >= 0 && n7 + l >= 0 && n8 + k < this.nxButtons && n7 + l < this.nyButtons && this.untouched[n8 + k][n7 + l] == 0) {
                                            this.it[n8 + k][n7 + l].ChangeImage(this.Buttons[11], this);
                                            this.untouched[n8 + k][n7 + l] = 2;
                                            ++this.ntouch;
                                            --this.nl;
                                        }
                                    }
                                }
                            }
                            if (n3 == this.matrix[n8][n7]) {
                                if (!b2) {
                                    for (int n9 = -1; n9 < 2; ++n9) {
                                        for (int n10 = -1; n10 < 2; ++n10) {
                                            if ((n9 != 0 || n10 != 0) && n8 + n9 >= 0 && n7 + n10 >= 0 && n8 + n9 < this.nxButtons && n7 + n10 < this.nyButtons && this.matrix[n8 + n9][n7 + n10] < 9 && this.untouched[n8 + n9][n7 + n10] == 0) {
                                                b = this.youClicked(n8 + n9, n7 + n10);
                                            }
                                        }
                                    }
                                }
                                if (b2) {
                                    this.matrix[n5][n6] = 666;
                                    b = true;
                                    this.untouched[n5][n6] = 1;
                                    this.it[n5][n6].ChangeImage(this.Buttons[12], this);
                                    for (int n11 = 0; n11 < this.nMines; ++n11) {
                                        if (this.untouched[this.imines[n11]][this.jmines[n11]] == 0) {
                                            this.it[this.imines[n11]][this.jmines[n11]].ChangeImage(this.Buttons[9], this);
                                        }
                                    }
                                }
                            }
                            this.untouched[n8][n7] = 1;
                            break;
                        }
                        case 2: {
                            this.it[n8][n7].ChangeImage(this.Buttons[13], this);
                            this.untouched[n8][n7] = 0;
                            --this.ntouch;
                            ++this.nl;
                            break;
                        }
                        case 3: {
                            this.it[n8][n7].ChangeImage(this.Buttons[13], this);
                            this.untouched[n8][n7] = 0;
                            --this.ntouch;
                            ++this.nl;
                            break;
                        }
                    }
                }
            }
            if (this.itouch >= 0 && this.itouch <= this.nxButtons && this.jtouch >= 0 && this.jtouch <= this.nyButtons) {
                mouseUp = this.it[this.itouch][this.jtouch].mouseUp(event, n, n2, this);
            }
            this.donleft();
            if (this.ntouch == this.nxButtons * this.nyButtons && this.nl == 0) {
                this.YouWin();
            }
            if (b) {
                this.YouLose();
            }
            return mouseUp;
        }
        return false;
    }
    
    private void doload() {
        if (this.lastsz != this.sz) {
            this.lastsz = this.sz;
            this.Buttons[0] = new ImgButtonImage("images/0.gif", this.sz, this.sz, this.bd, this);
            this.Buttons[1] = new ImgButtonImage("images/1.gif", this.sz, this.sz, this.bd, this);
            this.Buttons[2] = new ImgButtonImage("images/2.gif", this.sz, this.sz, this.bd, this);
            this.Buttons[3] = new ImgButtonImage("images/3.gif", this.sz, this.sz, this.bd, this);
            this.Buttons[4] = new ImgButtonImage("images/4.gif", this.sz, this.sz, this.bd, this);
            this.Buttons[5] = new ImgButtonImage("images/5.gif", this.sz, this.sz, this.bd, this);
            this.Buttons[6] = new ImgButtonImage("images/6.gif", this.sz, this.sz, this.bd, this);
            this.Buttons[7] = new ImgButtonImage("images/7.gif", this.sz, this.sz, this.bd, this);
            this.Buttons[8] = new ImgButtonImage("images/8.gif", this.sz, this.sz, this.bd, this);
            this.Buttons[9] = new ImgButtonImage("images/mine.gif", this.sz, this.sz, this.bd, this);
            this.Buttons[10] = new ImgButtonImage("images/question.gif", this.sz, this.sz, this.bd, this);
            this.Buttons[11] = new ImgButtonImage("images/flag.gif", this.sz, this.sz, this.bd, this);
            this.Buttons[12] = new ImgButtonImage("images/expmine.gif", this.sz, this.sz, this.bd, this);
            this.Buttons[13] = new ImgButtonImage("images/hidden.gif", this.sz, this.sz, this.bd, this);
            this.Buttons[14] = new ImgButtonImage("images/greathead.jpg", 50 + this.bd * 2, 62 + this.bd * 2, this.bd, this);
            this.Buttons[15] = new ImgButtonImage("images/ukmedium.gif", 50 + this.bd * 2, 25 + this.bd * 2, this.bd, this);
        }
    }
    
    public void donleft() {
        this.nleft.setText(" " + this.nl);
    }
    
    public void dosetup() {
        this.doload();
        this.firstPaint = true;
        this.inPlay = true;
        this.nxButtons = 16;
        this.nyButtons = 30;
        this.nMines = 99;
        this.itouch = -1;
        this.jtouch = -1;
        this.ntouch = 0;
        this.ibutton = 0;
        this.nl = this.nMines;
        this.it = new ImgButton[this.nxButtons][this.nyButtons];
        this.restartButton = new ImgButton(this.Buttons[14], this.xmin + this.nyButtons * (this.sz / 2) - (50 + this.bd * 2) / 2, this.ymin - (62 + this.bd * 2), true, this);
        this.firstflagButton = new ImgButton(this.Buttons[15], this.xmin, this.ymin + this.nxButtons * this.sz, true, this);
        this.matrix = new int[this.nxButtons][this.nyButtons];
        this.untouched = new int[this.nxButtons][this.nyButtons];
        this.imines = new int[this.nMines];
        this.jmines = new int[this.nMines];
        for (int i = 0; i < this.nxButtons; ++i) {
            for (int j = 0; j < this.nyButtons; ++j) {
                this.it[i][j] = new ImgButton(this.Buttons[13], this.xmin + j * this.sz, this.ymin + i * this.sz, true, this);
                this.matrix[i][j] = 0;
                this.untouched[i][j] = 0;
            }
        }
        this.layMines();
        this.donleft();
        this.stop();
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 501: {
                return this.doMouseDown(event, event.x, event.y);
            }
            case 502: {
                return this.doMouseUp(event, event.x, event.y);
            }
            default: {
                return false;
            }
        }
    }
    
    public void init() {
        this.para = this.getParameter("playerName");
        if (this.para != null) {
            this.playerName = this.para;
        }
        this.para = this.getParameter("seed");
        if (this.para != null) {
            this.seednum = Long.parseLong(this.para, 10);
        }
        this.setBackground(Color.white);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        this.buildConstraints(gridBagConstraints, 0, 0, 1, 1, 10, 0);
        layout.setConstraints(this.myMinesHeader = new Label("Mines", 1), gridBagConstraints);
        this.add(this.myMinesHeader);
        this.buildConstraints(gridBagConstraints, 1, 0, 1, 1, 80, 0);
        final Canvas canvas = new Canvas();
        layout.setConstraints(canvas, gridBagConstraints);
        this.add(canvas);
        this.buildConstraints(gridBagConstraints, 2, 0, 1, 1, 10, 0);
        layout.setConstraints(this.myTimeHeader = new Label("Time", 1), gridBagConstraints);
        this.add(this.myTimeHeader);
        this.buildConstraints(gridBagConstraints, 0, 1, 1, 1, 10, 0);
        layout.setConstraints(this.nleft = new Label("10", 0), gridBagConstraints);
        this.add(this.nleft);
        this.buildConstraints(gridBagConstraints, 1, 1, 1, 1, 80, 0);
        final Canvas canvas2 = new Canvas();
        layout.setConstraints(canvas2, gridBagConstraints);
        this.add(canvas2);
        this.buildConstraints(gridBagConstraints, 2, 1, 1, 1, 10, 0);
        layout.setConstraints(this.Time = new Label("     0     ", 1), gridBagConstraints);
        this.add(this.Time);
        this.buildConstraints(gridBagConstraints, 0, 2, 1, 1, 35, 95);
        final Canvas canvas3 = new Canvas();
        layout.setConstraints(canvas3, gridBagConstraints);
        this.add(canvas3);
        this.buildConstraints(gridBagConstraints, 1, 2, 1, 1, 30, 95);
        final Canvas canvas4 = new Canvas();
        layout.setConstraints(canvas4, gridBagConstraints);
        this.add(canvas4);
        this.buildConstraints(gridBagConstraints, 2, 2, 1, 1, 35, 95);
        final Canvas canvas5 = new Canvas();
        layout.setConstraints(canvas5, gridBagConstraints);
        this.add(canvas5);
        this.buildConstraints(gridBagConstraints, 0, 3, 1, 1, 25, 5);
        final Canvas canvas6 = new Canvas();
        layout.setConstraints(canvas6, gridBagConstraints);
        this.add(canvas6);
        this.buildConstraints(gridBagConstraints, 1, 3, 1, 1, 30, 5);
        layout.setConstraints(this.myPlayerNameLabel = new Label("Name", 2), gridBagConstraints);
        this.add(this.myPlayerNameLabel);
        this.buildConstraints(gridBagConstraints, 2, 3, 1, 1, 45, 5);
        this.tfPlayerName.setText(String.valueOf(this.playerName));
        layout.setConstraints(this.tfPlayerName, gridBagConstraints);
        this.add(this.tfPlayerName);
        this.buildConstraints(gridBagConstraints, 0, 4, 3, 1, 100, 0);
        layout.setConstraints(this.myMessage = new Label("          Click in the top          ", 1), gridBagConstraints);
        this.add(this.myMessage);
        this.buildConstraints(gridBagConstraints, 1, 4, 1, 1, 0, 0);
        final Canvas canvas7 = new Canvas();
        layout.setConstraints(canvas7, gridBagConstraints);
        this.add(canvas7);
        this.buildConstraints(gridBagConstraints, 2, 4, 1, 1, 0, 0);
        final Canvas canvas8 = new Canvas();
        layout.setConstraints(canvas8, gridBagConstraints);
        this.add(canvas8);
        this.buildConstraints(gridBagConstraints, 0, 5, 3, 1, 100, 0);
        layout.setConstraints(this.mySecondMessage = new Label("          left corner to begin.          ", 1), gridBagConstraints);
        this.add(this.mySecondMessage);
        this.buildConstraints(gridBagConstraints, 1, 5, 1, 1, 0, 0);
        final Canvas canvas9 = new Canvas();
        layout.setConstraints(canvas9, gridBagConstraints);
        this.add(canvas9);
        this.buildConstraints(gridBagConstraints, 2, 5, 1, 1, 0, 0);
        final Canvas canvas10 = new Canvas();
        layout.setConstraints(canvas10, gridBagConstraints);
        this.add(canvas10);
        this.dosetup();
    }
    
    private void layMines() {
        final Random random = new Random();
        for (int i = 0; i < this.nMines; ++i) {
            for (boolean b = false; !b; b = true) {
                final int n = Math.abs(random.nextInt()) % this.nxButtons;
                final int n2 = Math.abs(random.nextInt()) % this.nyButtons;
                if (this.matrix[n][n2] == 0 && n != 0 && n2 != 0) {
                    this.matrix[n][n2] = 9;
                    this.imines[i] = n;
                    this.jmines[i] = n2;
                }
            }
        }
        for (int j = 0; j < this.nxButtons; ++j) {
            for (int k = 0; k < this.nyButtons; ++k) {
                this.matrix[j][k] = this.countNeighbors(j, k);
            }
        }
    }
    
    public String makeGoodString() {
        String s = "";
        final String s2 = " ?()_";
        final char char1 = s2.charAt(0);
        final char char2 = s2.charAt(1);
        final char char3 = s2.charAt(2);
        final char char4 = s2.charAt(3);
        final char char5 = s2.charAt(4);
        this.playerName = this.tfPlayerName.getText();
        this.playerName = this.playerName.trim();
        for (int length = this.playerName.length(), i = 0; i < length; ++i) {
            final char char6 = this.playerName.charAt(i);
            if (char6 == char1) {
                s = String.valueOf(s) + "_s";
            }
            else if (char6 == char2) {
                s = String.valueOf(s) + "_q";
            }
            else if (char6 == char3) {
                s = String.valueOf(s) + "_l";
            }
            else if (char6 == char4) {
                s = String.valueOf(s) + "_r";
            }
            else if (char6 == char5) {
                s = String.valueOf(s) + "_u";
            }
            else {
                s = String.valueOf(s) + this.playerName.charAt(i);
            }
        }
        try {
            System.in.read();
        }
        catch (Exception ex) {}
        return s;
    }
    
    public void paint(final Graphics graphics) {
        if (this.firstPaint) {
            graphics.setColor(this.getBackground());
            graphics.fillRect(this.xmin, this.ymin, this.maxWidth * this.sz, this.maxHeight * this.sz);
            graphics.setColor(this.getForeground());
            this.firstPaint = false;
        }
        for (int i = 0; i < this.nxButtons; ++i) {
            for (int j = 0; j < this.nyButtons; ++j) {
                this.it[i][j].paint(graphics);
            }
        }
        this.restartButton.paint(graphics);
        this.firstflagButton.paint(graphics);
    }
    
    public void recurCheck(final int n, final int n2) {
        if (n > -1 && n < this.nxButtons && n2 > -1 && n2 < this.nyButtons && this.untouched[n][n2] == 0) {
            this.untouched[n][n2] = 1;
            ++this.ntouch;
            this.it[n][n2].ChangeImage(this.Buttons[this.matrix[n][n2]], this);
            if (this.matrix[n][n2] == 0) {
                for (int i = n - 1; i <= n + 1; ++i) {
                    for (int j = n2 - 1; j <= n2 + 1; ++j) {
                        if (i > -1 && i < this.nxButtons && j > -1 && j < this.nyButtons && this.untouched[i][j] == 0) {
                            this.recurCheck(i, j);
                        }
                    }
                }
            }
        }
    }
    
    public void run() {
        while (true) {
            this.nowtime = System.currentTimeMillis();
            this.tdiff = this.nowtime - this.starttime;
            this.Time.setText(String.valueOf(this.tdiff));
            try {
                Thread.sleep(10L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void start() {
    }
    
    public void startTimer() {
        if (this.timer == null) {
            this.starttime = System.currentTimeMillis();
            (this.timer = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.timer != null) {
            this.timer.stop();
            this.nowtime = System.currentTimeMillis();
            this.Time.setText(String.valueOf(this.tdiff));
            this.repaint();
            this.timer = null;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean youClicked(final int n, final int n2) {
        if (this.matrix[n][n2] == 9) {
            this.untouched[n][n2] = 1;
            this.it[n][n2].ChangeImage(this.Buttons[12], this);
            for (int i = 0; i < this.nMines; ++i) {
                if (this.untouched[this.imines[i]][this.jmines[i]] == 0) {
                    this.it[this.imines[i]][this.jmines[i]].ChangeImage(this.Buttons[9], this);
                }
            }
            return true;
        }
        this.recurCheck(n, n2);
        return false;
    }
}
