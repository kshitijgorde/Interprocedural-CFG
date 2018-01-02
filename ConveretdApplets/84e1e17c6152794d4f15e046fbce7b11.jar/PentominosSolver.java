import java.awt.Event;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Button;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class PentominosSolver extends Applet implements Runnable
{
    Button clearBttn;
    Button pauseBttn;
    Button goBttn;
    Button stepBttn;
    Label message;
    int[] board;
    PentominosBoardCanvas boardcanvas;
    Graphics g;
    boolean[] used;
    int numused;
    Thread GameThread;
    int squaresClicked;
    boolean oneStepOnly;
    long lastYieldTime;
    final int[][] pieces;
    
    public static void main(final String[] array) {
        final Frame frame = new Frame("Pentominos Solver");
        final PentominosSolver pentominosSolver = new PentominosSolver();
        pentominosSolver.init();
        pentominosSolver.start();
        frame.add("Center", pentominosSolver);
        frame.resize(250, 300);
        frame.show();
    }
    
    public void init() {
        this.board = new int[100];
        this.used = new boolean[13];
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        this.setLayout(new BorderLayout());
        this.add("South", panel2);
        this.add("North", panel);
        this.clearBttn = new Button("Clear");
        this.pauseBttn = new Button("Pause");
        this.goBttn = new Button("Go");
        this.stepBttn = new Button("Step");
        panel2.add(this.clearBttn);
        panel2.add(this.pauseBttn);
        panel2.add(this.goBttn);
        panel2.add(this.stepBttn);
        panel.add(this.message = new Label("Click Four Square"));
        this.add("Center", this.boardcanvas = new PentominosBoardCanvas(this.board, this));
        for (int i = 0; i < 100; ++i) {
            this.board[i] = -1;
        }
        for (int j = 1; j < 9; ++j) {
            for (int k = 1; k < 9; ++k) {
                this.board[k * 10 + j] = 0;
            }
        }
        this.stepBttn.disable();
        this.goBttn.disable();
        this.pauseBttn.disable();
    }
    
    boolean putPiece(final int n, final int n2) {
        final int n3 = this.pieces[n][0];
        if (this.board[n2] != 0) {
            return false;
        }
        for (int i = 1; i <= 4; ++i) {
            if (this.board[n2 + this.pieces[n][i]] != 0) {
                return false;
            }
        }
        this.boardcanvas.putSquare(this.g, n3, n2);
        for (int j = 1; j <= 4; ++j) {
            this.boardcanvas.putSquare(this.g, n3, n2 + this.pieces[n][j]);
        }
        return true;
    }
    
    void removePiece(final int n, final int n2) {
        this.boardcanvas.clearSquare(this.g, n2);
        for (int i = 1; i <= 4; ++i) {
            this.boardcanvas.clearSquare(this.g, n2 + this.pieces[n][i]);
        }
    }
    
    void play(final int n) {
        for (int i = 0; i < 63; ++i) {
            if (!this.used[this.pieces[i][0]] && this.putPiece(i, n)) {
                this.used[this.pieces[i][0]] = true;
                ++this.numused;
                if (this.numused == 12 || this.oneStepOnly) {
                    if (this.oneStepOnly) {
                        this.oneStepOnly = false;
                    }
                    else {
                        this.stepBttn.enable();
                        this.goBttn.enable();
                        this.pauseBttn.disable();
                    }
                    this.GameThread.suspend();
                }
                else if (System.currentTimeMillis() - this.lastYieldTime >= 100L) {
                    Thread.yield();
                    this.lastYieldTime = System.currentTimeMillis();
                }
                if (this.numused < 12) {
                    int n2;
                    for (n2 = n; this.board[n2] != 0; ++n2) {}
                    this.play(n2);
                }
                this.removePiece(i, n);
                --this.numused;
                this.used[this.pieces[i][0]] = false;
            }
        }
    }
    
    public void run() {
        this.g = this.boardcanvas.getGraphics();
        this.message.setText("Solving...");
        for (int i = 1; i <= 12; ++i) {
            this.used[i] = false;
        }
        this.numused = 0;
        int n;
        for (n = 11; this.board[n] == -1; ++n) {}
        this.lastYieldTime = System.currentTimeMillis();
        this.play(n);
        this.stepBttn.disable();
        this.goBttn.disable();
        this.pauseBttn.disable();
        this.message.setText("No More Solutions");
    }
    
    void doClear() {
        if (this.GameThread != null) {
            this.GameThread.stop();
            this.GameThread = null;
            this.g = null;
        }
        if (this.squaresClicked > 0) {
            for (int i = 0; i < 100; ++i) {
                this.board[i] = -1;
            }
            for (int j = 1; j < 9; ++j) {
                for (int k = 1; k < 9; ++k) {
                    this.board[k * 10 + j] = 0;
                }
            }
            this.boardcanvas.repaint();
            this.squaresClicked = 0;
        }
        this.stepBttn.disable();
        this.goBttn.disable();
        this.pauseBttn.disable();
        this.message.setText("Click Four Squares");
    }
    
    void doBoardClick(final int n) {
        if (this.GameThread == null && this.squaresClicked < 4) {
            if (this.board[n] == 0) {
                ++this.squaresClicked;
                this.boardcanvas.blackenSquare(this.boardcanvas.getGraphics(), n);
            }
            if (this.squaresClicked == 4) {
                this.GameThread = new Thread(this);
                final int priority = this.GameThread.getPriority() - 1;
                if (priority >= 1) {
                    this.GameThread.setPriority(priority);
                }
                this.GameThread.start();
                this.pauseBttn.enable();
            }
        }
    }
    
    void doPause() {
        this.oneStepOnly = true;
        this.stepBttn.enable();
        this.goBttn.enable();
        this.pauseBttn.disable();
    }
    
    void doStep() {
        this.oneStepOnly = true;
        this.GameThread.resume();
    }
    
    void doGo() {
        this.stepBttn.disable();
        this.goBttn.disable();
        this.pauseBttn.enable();
        this.lastYieldTime = System.currentTimeMillis();
        this.GameThread.resume();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            if ("Clear".equals(o)) {
                this.doClear();
            }
            else if ("Pause".equals(o)) {
                this.doPause();
            }
            else if ("Go".equals(o)) {
                this.doGo();
            }
            else if ("Step".equals(o)) {
                this.doStep();
            }
            return true;
        }
        return false;
    }
    
    public PentominosSolver() {
        this.oneStepOnly = false;
        this.pieces = new int[][] { { 1, 1, 2, 3, 4 }, { 1, 10, 20, 30, 40 }, { 2, 9, 10, 11, 20 }, { 3, 1, 10, 19, 20 }, { 3, 10, 11, 12, 22 }, { 3, 1, 11, 21, 22 }, { 3, 8, 9, 10, 18 }, { 4, 10, 20, 21, 22 }, { 4, 1, 2, 10, 20 }, { 4, 10, 18, 19, 20 }, { 4, 1, 2, 12, 22 }, { 5, 1, 2, 11, 21 }, { 5, 8, 9, 10, 20 }, { 5, 10, 19, 20, 21 }, { 5, 10, 11, 12, 20 }, { 6, 10, 11, 21, 22 }, { 6, 9, 10, 18, 19 }, { 6, 1, 11, 12, 22 }, { 6, 1, 9, 10, 19 }, { 7, 1, 2, 10, 12 }, { 7, 1, 11, 20, 21 }, { 7, 2, 10, 11, 12 }, { 7, 1, 10, 20, 21 }, { 8, 10, 11, 12, 13 }, { 8, 10, 20, 29, 30 }, { 8, 1, 2, 3, 13 }, { 8, 1, 10, 20, 30 }, { 8, 1, 11, 21, 31 }, { 8, 1, 2, 3, 10 }, { 8, 10, 20, 30, 31 }, { 8, 7, 8, 9, 10 }, { 9, 1, 8, 9, 10 }, { 9, 10, 11, 21, 31 }, { 9, 1, 2, 9, 10 }, { 9, 10, 20, 21, 31 }, { 9, 1, 11, 12, 13 }, { 9, 10, 19, 20, 29 }, { 9, 1, 2, 12, 13 }, { 9, 9, 10, 19, 29 }, { 10, 8, 9, 10, 11 }, { 10, 9, 10, 20, 30 }, { 10, 1, 2, 3, 11 }, { 10, 10, 20, 21, 30 }, { 10, 1, 2, 3, 12 }, { 10, 10, 11, 20, 30 }, { 10, 9, 10, 11, 12 }, { 10, 10, 19, 20, 30 }, { 11, 9, 10, 11, 21 }, { 11, 1, 9, 10, 20 }, { 11, 10, 11, 12, 21 }, { 11, 10, 11, 19, 20 }, { 11, 8, 9, 10, 19 }, { 11, 1, 11, 12, 21 }, { 11, 9, 10, 11, 19 }, { 11, 9, 10, 20, 21 }, { 12, 1, 10, 11, 21 }, { 12, 1, 2, 10, 11 }, { 12, 10, 11, 20, 21 }, { 12, 1, 9, 10, 11 }, { 12, 1, 10, 11, 12 }, { 12, 9, 10, 19, 20 }, { 12, 1, 2, 11, 12 }, { 12, 1, 10, 11, 20 } };
    }
}
