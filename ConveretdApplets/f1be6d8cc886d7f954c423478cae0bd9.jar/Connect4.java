import java.awt.Event;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Panel;
import java.awt.Graphics;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Connect4 extends Applet
{
    public static final int WAITING_FOR_MOVE = 0;
    public static final int ANALYZING = 1;
    public static final int RESTART = 2;
    public static final int BORDER_WIDTH = 5;
    Connect4Kernel c4k;
    int currField;
    int level;
    int[] bestMove;
    int res;
    int[] value;
    int changeCnt;
    boolean levelEverDecreased;
    int lastCompArrowColumn;
    int lastHumanArrowColumn;
    boolean lastHumanArrowEmpty;
    int currFontSize;
    Font font;
    StringBuffer currStatus;
    Graphics g;
    Panel panel;
    int mode;
    
    public void init() {
        this.c4k = new Connect4Kernel(this);
        this.bestMove[0] = this.c4k.Field(0, 0);
        this.value[0] = 0;
        this.level = 4;
        this.levelEverDecreased = false;
        this.changeCnt = 0;
        this.lastHumanArrowColumn = -1;
        this.lastCompArrowColumn = -1;
        this.lastHumanArrowEmpty = true;
        this.currStatus = new StringBuffer("Start your game!");
        if (this.g == null) {
            this.g = this.getGraphics();
        }
        this.SetMode(0);
    }
    
    public void SetMode(final int mode) {
        this.mode = mode;
    }
    
    public boolean IsMode(final int n) {
        return this.mode == n;
    }
    
    public void DrawStatus(final String s) {
        final Dimension size = this.size();
        final int n = (size.width - 10) / 7;
        final int currFontSize = size.height / 8;
        this.g.setColor(Color.white);
        this.g.fillRect(5, 7 * currFontSize + 1, 7 * n, currFontSize - 5 - 1);
        if (this.currFontSize != currFontSize) {
            this.currFontSize = currFontSize;
            this.font = new Font("Dialog", 0, currFontSize * 5 / 10);
        }
        this.g.setFont(this.font);
        this.currStatus = new StringBuffer(s);
        this.g.setColor(Color.black);
        this.g.drawString(this.currStatus.toString(), 15, 7 * currFontSize + currFontSize * 5 / 8);
        for (int n2 = 4; n2 < 9 && n2 <= this.level; ++n2) {
            this.g.setColor(Color.green);
            this.g.fillRect(7 * n - 5 - 20, 8 * currFontSize - 5 - (n2 - 3) * 4 - 1, 22, 3);
        }
    }
    
    public void DrawChip(final int n, final int n2) {
        final Dimension size = this.size();
        final int n3 = (size.width - 10) / 7;
        final int n4 = size.height / 8;
        final int n5 = 5 + n3 * n + n3 / 8;
        final int n6 = size.height - (n2 + 2) * n4 + n4 / 8;
        final int n7 = n3 * 3 / 4;
        final int n8 = n4 * 3 / 4;
        Color color = null;
        switch (this.c4k.FieldState(n2, n)) {
            case 1: {
                color = Color.green;
                break;
            }
            case -1: {
                color = Color.red;
                break;
            }
            default: {
                color = Color.white;
                break;
            }
        }
        this.g.setColor(color);
        this.g.fillOval(n5, n6, n7, n8);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        final int n = (size.width - 10) / 7;
        final int n2 = size.height / 8;
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, 7 * n + 10 - 1, n2 * 8 - 1);
        graphics.setColor(Color.blue.darker().darker());
        graphics.fillRect(5, n2, n * 7, n2 * 6);
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 7; ++j) {
                this.DrawChip(j, i);
            }
        }
        this.DrawStatus(this.currStatus.toString());
        graphics.setColor(Color.gray);
        for (int k = 0; k < 5; ++k) {
            graphics.drawLine(k, k, k, n2 * 8 - k - 1);
            graphics.drawLine(k + 1, k, n * 7 + 10 - 1, k);
        }
        graphics.setColor(Color.darkGray);
        for (int l = 0; l < 5; ++l) {
            graphics.drawLine(n * 7 - l + 10 - 1, l + 1, n * 7 - l + 10 - 1, n2 * 8 - l - 1);
            graphics.drawLine(l, n2 * 8 - l - 1, n * 7 + 10 - 1, n2 * 8 - l - 1);
        }
    }
    
    public int DoMove(final int n, final int n2) {
        final int doMove = this.c4k.DoMove(n, n2);
        this.DrawChip(n2, this.c4k.Line(doMove));
        if (n == -1) {
            this.lastCompArrowColumn = -1;
        }
        else {
            this.lastHumanArrowColumn = -1;
        }
        this.DrawArrows(n2, n);
        return doMove;
    }
    
    private void AdaptLevel() {
        if (this.c4k.Cnt() < 6000) {
            if (++this.changeCnt == 2) {
                ++this.level;
                this.changeCnt = 0;
            }
        }
        else {
            if (this.c4k.Cnt() > 40000) {
                --this.level;
                this.changeCnt = 0;
                this.levelEverDecreased = true;
                return;
            }
            this.changeCnt = 0;
        }
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.IsMode(2)) {
            this.init();
            this.repaint();
            return true;
        }
        this.SetMode(1);
        Label_0319: {
            if (this.c4k.GameOver(this.bestMove[0], this.value) == 0) {
                final int n3 = (n - 5) * 7 / this.size().width;
                if (n3 >= 0) {
                    if (n3 <= 6) {
                        if (this.c4k.IsEmpty(n3)) {
                            this.currField = this.DoMove(-1, n3);
                            switch (this.res = this.c4k.GameOver(this.currField, this.value)) {
                                case -1: {
                                    this.DrawStatus("It ends in a tie! (click to restart)");
                                    this.SetMode(2);
                                    break;
                                }
                                case 1: {
                                    this.DrawStatus("You won! (click to restart)");
                                    this.SetMode(2);
                                    break;
                                }
                                case 0: {
                                    this.res = this.c4k.BestMove(1, this.bestMove, this.level);
                                    this.currField = this.DoMove(1, this.bestMove[0]);
                                    this.AdaptLevel();
                                    switch (this.res) {
                                        case -1: {
                                            this.DrawStatus("Your move!");
                                            break Label_0319;
                                        }
                                        case 1: {
                                            if (this.c4k.GameOver(this.currField, this.value) != 0) {
                                                this.DrawStatus("I won! (click to restart)");
                                                this.SetMode(2);
                                                break Label_0319;
                                            }
                                            if (this.levelEverDecreased) {
                                                this.DrawStatus("Your move!");
                                                break Label_0319;
                                            }
                                            this.DrawStatus("I'm going to win!");
                                            break Label_0319;
                                        }
                                        default: {
                                            this.DrawStatus("Your move!");
                                            break Label_0319;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (this.IsMode(1)) {
            this.SetMode(0);
        }
        return true;
    }
    
    void DrawArrow(final Graphics graphics, final int n, final int n2, final int n3, final Color color, final boolean b) {
        final int[] array = new int[8];
        final int[] array2 = new int[8];
        graphics.setColor(color);
        array[0] = 5 + n3 * n + n / 2;
        array2[0] = n2 * 3 / 4;
        array[1] = array[0] + n / 5;
        array2[1] = array2[0] - n2 / 4;
        array[2] = array[0] + n / 10;
        array2[2] = array2[1];
        array[3] = array[2];
        array2[3] = array2[0] - n2 / 2;
        array[4] = array[0] - n / 10;
        array2[4] = array2[3];
        array[5] = array[4];
        array2[5] = array2[1];
        array[6] = array[0] - n / 5;
        array2[6] = array2[1];
        array[7] = array[0];
        array2[7] = array2[0];
        if (b) {
            graphics.fillPolygon(array, array2, 8);
            return;
        }
        graphics.drawPolygon(array, array2, 8);
    }
    
    void DrawArrows(final int n, final int n2) {
        if (n >= 0 && n < 7) {
            final boolean isEmpty = this.c4k.IsEmpty(n);
            final Dimension size = this.size();
            final int n3 = (size.width - 10) / 7;
            final int n4 = size.height / 8;
            this.g.setColor(Color.white);
            this.g.fillRect(5, 5, 7 * n3, n4 - 5);
            if (n2 == -1) {
                this.lastHumanArrowColumn = n;
                this.lastHumanArrowEmpty = isEmpty;
            }
            else if (n2 == 1) {
                this.lastCompArrowColumn = n;
            }
            else {
                this.lastHumanArrowColumn = -1;
                this.lastCompArrowColumn = -1;
            }
            if (this.lastHumanArrowColumn != -1) {
                Color color;
                if (this.lastHumanArrowEmpty) {
                    color = Color.red;
                }
                else {
                    color = Color.gray;
                }
                this.DrawArrow(this.g, n3, n4, this.lastHumanArrowColumn, color, true);
            }
            if (this.lastCompArrowColumn != -1) {
                this.DrawArrow(this.g, n3, n4, this.lastCompArrowColumn, Color.green, this.lastCompArrowColumn != this.lastHumanArrowColumn);
            }
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.IsMode(0)) {
            final int n3 = (n - 5) * 7 / this.size().width;
            if (n3 != this.lastHumanArrowColumn) {
                this.DrawArrows(n3, -1);
            }
        }
        return true;
    }
    
    public String getAppletInfo() {
        return "Connect4 - Java (beta) Applet by Sven Wiebus, Dec. 1995";
    }
    
    public Connect4() {
        this.bestMove = new int[1];
        this.value = new int[1];
        this.levelEverDecreased = false;
        this.lastCompArrowColumn = -1;
        this.lastHumanArrowColumn = -1;
        this.lastHumanArrowEmpty = false;
        this.mode = -1;
    }
}
