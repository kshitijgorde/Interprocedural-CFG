import java.awt.event.WindowEvent;
import java.util.Random;
import java.awt.TextArea;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Label;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.WindowListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Puzzle extends Applet implements ActionListener, MouseListener, WindowListener
{
    Panel basePanel;
    Panel p2;
    Panel titlePanel;
    Panel sp;
    Image i1;
    Color bg;
    Cursor btnCursor;
    Cursor tileCursor;
    Label dL1;
    Button btn1;
    Button b1;
    Button about;
    TextField mCount;
    int moveCount;
    int wX;
    int wY;
    int wSize;
    int hSize;
    int xCount;
    int yCount;
    int gw;
    int gh;
    PuzzleTile[] pt;
    int[][] tileID;
    Dimension d;
    Color clrTile;
    Color clrSelA;
    Color clrSelB;
    Color clrBoard;
    boolean showNonMovers;
    
    public Puzzle() {
        this.moveCount = 0;
        this.xCount = 2;
        this.yCount = 2;
        this.clrTile = Color.gray;
        this.clrSelA = Color.green;
        this.clrSelB = Color.red;
        this.clrBoard = new Color(0, 64, 64);
        this.showNonMovers = true;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        this.wSize = this.p2.getBounds().width / this.xCount;
        this.hSize = this.p2.getBounds().height / this.yCount;
        if ("Shuffle".equals(actionCommand)) {
            this.shuffle();
        }
        else if ("Reveal".equals(actionCommand)) {
            this.reveal();
        }
        else if ("?".equals(actionCommand)) {
            this.wX = this.getLocation().x;
            this.wY = this.getLocation().y;
            this.showAbout();
        }
    }
    
    public String getAppletInfo() {
        return "Loads a picture and turns it into a puzzle";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "cols", "integer", "4" }, { "rows", "integer", "4" }, { "image", "string", "me.jpg" }, { "board", "string", "#004f4f" }, { "Select Color1", "string", "#00ff00" }, { "Select Color2", "string", "#ff0000" }, { "Tile Color", "string", "#ffffff" } };
    }
    
    public void init() {
        this.xCount = new Integer(this.getParameter("cols"));
        this.yCount = new Integer(this.getParameter("rows"));
        final String parameter = this.getParameter("image");
        final String parameter2 = this.getParameter("boardColor");
        if (parameter2 != null) {
            try {
                this.clrBoard = Color.decode(parameter2);
            }
            catch (NumberFormatException ex2) {
                System.out.println("Invalid Color String [" + parameter2 + "]");
            }
        }
        final String parameter3 = this.getParameter("tileColor");
        if (parameter3 != null) {
            try {
                this.clrTile = Color.decode(parameter3);
            }
            catch (NumberFormatException ex3) {
                System.out.println("Invalid Color String [" + parameter3 + "]");
            }
        }
        final String parameter4 = this.getParameter("canMoveColor");
        if (parameter4 != null) {
            try {
                this.clrSelA = Color.decode(parameter4);
            }
            catch (NumberFormatException ex4) {
                System.out.println("Invalid Color String [" + parameter4 + "]");
            }
        }
        final String parameter5 = this.getParameter("cannotMoveColor");
        if (parameter5 != null) {
            if (parameter5.equals("off")) {
                this.showNonMovers = false;
            }
            else {
                this.showNonMovers = true;
                try {
                    this.clrSelB = Color.decode(parameter5);
                }
                catch (NumberFormatException ex5) {
                    System.out.println("Invalid Color String [" + parameter5 + "]");
                }
            }
        }
        this.makePuzzle();
        this.p2.setLayout(new GridLayout(this.yCount, this.xCount));
        this.wSize = this.p2.getBounds().width / this.xCount;
        this.hSize = this.p2.getBounds().height / this.yCount;
        this.pt = new PuzzleTile[this.xCount * this.yCount];
        this.tileID = new int[this.yCount][this.xCount];
        try {
            this.i1 = this.getImage(new URL(this.getDocumentBase(), parameter));
        }
        catch (MalformedURLException ex) {
            System.out.println("MalformedURLException: " + ex);
        }
        int n = 1;
        for (int i = 0; i < this.yCount; ++i) {
            for (int j = 0; j < this.xCount; ++j) {
                if (n != this.yCount * this.xCount) {
                    this.pt[n - 1] = new PuzzleTile(this, n, i, j, this.i1, this.xCount, this.yCount, this.clrTile);
                    this.tileID[i][j] = n;
                    this.p2.add(this.pt[n - 1]);
                    this.pt[n - 1].setLocation(this.yCount * this.wSize, this.xCount * this.hSize);
                    this.pt[n - 1].addMouseListener(this);
                    ++n;
                }
            }
        }
    }
    
    public void makePuzzle() {
        this.btnCursor = new Cursor(12);
        this.setBackground(Color.black);
        this.setLayout(new BorderLayout(2, 2));
        this.add("North", this.titlePanel = new Panel());
        this.add("South", this.basePanel = new Panel());
        this.add("Center", this.p2 = new Panel());
        this.basePanel.setLayout(new FlowLayout(0, 2, 2));
        this.p2.setBackground(this.clrBoard);
        this.titlePanel.setBackground(Color.lightGray);
        this.titlePanel.setLayout(new BorderLayout(1, 1));
        final Label label;
        this.titlePanel.add("Center", label = new Label("PUZZLE"));
        this.titlePanel.add("East", this.sp = new Panel());
        this.sp.setLayout(new FlowLayout(1, 10, 10));
        this.sp.add(this.about = new Button("?"));
        this.about.setCursor(this.btnCursor);
        this.about.setFont(new Font("helvetica", 1, 13));
        this.about.setForeground(Color.white);
        this.about.setBackground(Color.darkGray);
        this.about.addActionListener(this);
        label.setFont(new Font("helvetica", 1, 24));
        label.setForeground(new Color(0, 31, 127));
        label.setBackground(Color.lightGray);
        this.basePanel.add(this.btn1 = new Button("Shuffle"));
        this.btn1.setCursor(this.btnCursor);
        this.btn1.addActionListener(this);
        this.basePanel.add(this.btn1 = new Button("Reveal"));
        this.btn1.setCursor(this.btnCursor);
        this.btn1.addActionListener(this);
        this.basePanel.add(this.dL1 = new Label("  Moves"));
        this.dL1.setBackground(Color.black);
        this.dL1.setForeground(Color.white);
        this.basePanel.add(this.mCount = new TextField("  "));
        this.mCount.setEditable(false);
        this.mCount.setBackground(Color.white);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.tileColor(true, mouseEvent);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.tileColor(false, mouseEvent);
    }
    
    public synchronized void mousePressed(final MouseEvent mouseEvent) {
        this.wSize = this.p2.getBounds().width / this.xCount;
        this.hSize = this.p2.getBounds().height / this.yCount;
        mouseEvent.getModifiers();
        if ("PuzzleTile".equals(mouseEvent.getComponent().getClass().getName())) {
            this.setTilePosition(((PuzzleTile)mouseEvent.getComponent()).getTileID());
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void moveTile(final int n, final int n2, final int n3, final int n4) {
        this.wSize = this.p2.getBounds().width / this.xCount;
        this.hSize = this.p2.getBounds().height / this.yCount;
        final int n5 = this.tileID[n][n2];
        this.tileID[n3][n4] = n5;
        this.tileID[n][n2] = 0;
        this.pt[n5 - 1].setLocation(n4 * this.wSize, n3 * this.hSize);
        this.setMoves(this.moveCount + 1);
    }
    
    public void reveal() {
        int n = 1;
        for (int i = 0; i < this.yCount; ++i) {
            for (int j = 0; j < this.xCount; ++j) {
                if (n < this.xCount * this.yCount) {
                    this.tileID[i][j] = n;
                    this.pt[n - 1].setLocation(j * this.wSize, i * this.hSize);
                }
                else {
                    this.tileID[i][j] = 0;
                }
                ++n;
            }
        }
        this.setMoves(0);
    }
    
    public void setMoves(final int moveCount) {
        this.moveCount = moveCount;
        this.mCount.setText(Integer.toString(this.moveCount));
        int n = 0;
        boolean b = false;
        for (int n2 = 0; n2 < this.yCount && !b; ++n2) {
            for (int n3 = 0; n3 < this.xCount && !b; ++n3) {
                if (++n == this.tileID[n2][n3]) {
                    if (n == this.yCount * this.xCount - 1) {
                        this.mCount.setBackground(Color.green);
                        b = true;
                    }
                }
                else {
                    this.mCount.setBackground(Color.white);
                    b = true;
                }
            }
        }
    }
    
    public boolean setTilePosition(final int n) {
        boolean b = false;
        boolean b2 = false;
        for (int n2 = 0; n2 < this.yCount && !b; ++n2) {
            for (int n3 = 0; n3 < this.xCount && !b; ++n3) {
                if (this.tileID[n2][n3] == n) {
                    if (n2 > 0 && this.tileID[n2 - 1][n3] == 0) {
                        this.moveTile(n2, n3, n2 - 1, n3);
                        b2 = true;
                    }
                    else if (n2 < this.yCount - 1 && this.tileID[n2 + 1][n3] == 0) {
                        this.moveTile(n2, n3, n2 + 1, n3);
                        b2 = true;
                    }
                    else if (n3 > 0 && this.tileID[n2][n3 - 1] == 0) {
                        this.moveTile(n2, n3, n2, n3 - 1);
                        b2 = true;
                    }
                    else if (n3 < this.xCount - 1 && this.tileID[n2][n3 + 1] == 0) {
                        this.moveTile(n2, n3, n2, n3 + 1);
                        b2 = true;
                    }
                    else {
                        for (int i = 0; i < this.xCount; ++i) {
                            if (this.tileID[n2][i] == 0) {
                                b2 = true;
                                for (int n4 = (i > n3) ? -1 : 1; i != n3; i += n4) {
                                    final int n5 = i + n4;
                                    this.tileID[n2][i] = this.tileID[n2][n5];
                                    this.tileID[n2][n5] = 0;
                                    this.pt[this.tileID[n2][i] - 1].setLocation(i * this.wSize, n2 * this.hSize);
                                }
                                break;
                            }
                        }
                        if (!b2) {
                            for (int j = 0; j < this.yCount; ++j) {
                                if (this.tileID[j][n3] == 0) {
                                    b2 = true;
                                    for (int n6 = (j > n2) ? -1 : 1; j != n2; j += n6) {
                                        final int n7 = j + n6;
                                        this.tileID[j][n3] = this.tileID[n7][n3];
                                        this.tileID[n7][n3] = 0;
                                        this.pt[this.tileID[j][n3] - 1].setLocation(n3 * this.wSize, j * this.hSize);
                                    }
                                    break;
                                }
                            }
                        }
                        if (b2) {
                            this.setMoves(this.moveCount + 1);
                        }
                    }
                    b = true;
                }
            }
        }
        return b2;
    }
    
    public void showAbout() {
        final Label[] array = new Label[6];
        final Frame frame = new Frame("About");
        frame.setBackground(Color.lightGray);
        frame.setLayout(new FlowLayout(0, 2, 2));
        final Panel panel = new Panel();
        frame.add(panel);
        panel.setLayout(new BorderLayout(1, 1));
        final Label label;
        panel.add("North", label = new Label(" PUZZLE "));
        label.setFont(new Font("helvetica", 1, 32));
        label.setForeground(Color.yellow);
        label.setBackground(Color.black);
        final Panel panel2 = new Panel();
        panel.setBackground(Color.lightGray);
        panel.add("Center", panel2);
        panel2.setLayout(new GridLayout(3, 2, 0, 0));
        panel2.setBackground(Color.lightGray);
        panel2.add(array[0] = new Label("Version"));
        panel2.add(array[3] = new Label("1.1"));
        panel2.add(array[1] = new Label("Author"));
        panel2.add(array[4] = new Label("Steve White"));
        panel2.add(array[2] = new Label("Date"));
        panel2.add(array[5] = new Label("28/02/1999"));
        final TextArea textArea;
        panel.add("South", textArea = new TextArea("", 6, 45, 1));
        textArea.setBackground(Color.white);
        textArea.setFont(new Font("helvetica", 0, 9));
        textArea.setEditable(false);
        textArea.append("MOVING TILES.\n=============\nTiles are moved by placing the mouse over a tile and pressing the LEFT mouse button.\n");
        textArea.append("If you wish to move more than one tile in the same row or column, then click on the first tile that you wish to move.\n\n");
        textArea.append("SCORING.\n=========\n");
        textArea.append("The score is just a count of how many moves you have taken.\n");
        textArea.append("(TIP) This can often be kept to a minimum by moving lines of tiles instead of each tile in a row or column one by one.\n\n");
        textArea.append("OTHER.\n=========\n");
        textArea.append("This applet is FREEWARE and is distributed free of charge for non-commercial use.\n");
        textArea.append("The homepage for this applet is http://home.sol.no/~svw2/puzzles\n");
        for (int i = 0; i < 6; ++i) {
            array[i].setFont(new Font("helvetica", 1, 16));
            array[i].setBackground(Color.lightGray);
            if (i < 3) {
                array[i].setForeground(new Color(0, 64, 0));
            }
        }
        frame.setSize(256, 275);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocation(150, 100);
        frame.addWindowListener(this);
    }
    
    public void shuffle() {
        final Random random = new Random();
        for (int i = 0; i < 100; ++i) {
            if (this.setTilePosition((int)(random.nextDouble() * (this.xCount * this.yCount)))) {}
        }
        this.setMoves(0);
    }
    
    public void tileColor(final boolean b, final MouseEvent mouseEvent) {
        if ("PuzzleTile".equals(mouseEvent.getComponent().getClass().getName())) {
            final PuzzleTile puzzleTile = (PuzzleTile)mouseEvent.getComponent();
            if (b) {
                int n = 0;
                for (int i = 0; i < this.yCount; ++i) {
                    if (n != 0) {
                        break;
                    }
                    for (int n2 = 0; n2 < this.xCount && n == 0; ++n2) {
                        if (this.tileID[i][n2] == puzzleTile.getTileID()) {
                            for (int n3 = 0; n3 < this.yCount && n == 0; ++n3) {
                                if (this.tileID[n3][n2] == 0) {
                                    puzzleTile.setTileBG(this.clrSelA);
                                    n = 1;
                                }
                            }
                            if (n == 0) {
                                for (int n4 = 0; n4 < this.xCount && n == 0; ++n4) {
                                    if (this.tileID[i][n4] == 0) {
                                        puzzleTile.setTileBG(this.clrSelA);
                                        n = 1;
                                    }
                                }
                            }
                            if (n == 0) {
                                if (this.showNonMovers) {
                                    puzzleTile.setTileBG(this.clrSelB);
                                }
                                n = 1;
                            }
                        }
                    }
                }
            }
            else {
                puzzleTile.setTileBG(this.clrTile);
            }
        }
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        windowEvent.getWindow().dispose();
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
}
