import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class squares extends Applet implements Runnable
{
    Thread animation;
    Graphics offscreen;
    Image image;
    String message;
    int width;
    int height;
    public int x;
    public int y;
    public int rows;
    public int cols;
    public int cx;
    public int cy;
    public int i;
    public int j;
    public int any;
    float computers_move;
    float user_boxes;
    float comp_boxes;
    float bw;
    float bh;
    float bh1;
    float dh;
    float dw;
    int no_safe_lines_left;
    int[][] line;
    int[][] cline;
    int[][] box;
    int[][] cbox;
    int cursor;
    int cursorFlag;
    int totalboxes;
    int gameover;
    int trading;
    int swap;
    int t;
    
    public void init() {
        this.line = new int[48][24];
        this.cline = new int[48][24];
        this.box = new int[48][24];
        this.cbox = new int[48][24];
        this.setBackground(Color.black);
        this.width = this.bounds().width;
        this.height = this.bounds().height;
        this.initGrid();
        this.image = this.createImage(this.width, this.height);
        this.offscreen = this.image.getGraphics();
    }
    
    public void initGrid() {
        this.x = 0;
        this.y = 0;
        this.cursor = 0;
        this.cursorFlag = 1;
        this.totalboxes = 0;
        this.gameover = 0;
        this.x = 0;
        this.y = 0;
        this.computers_move = 0.0f;
        this.user_boxes = 0.0f;
        this.comp_boxes = 0.0f;
        this.trading = 0;
        for (int i = 0; i < 48; ++i) {
            for (int j = 0; j < 24; ++j) {
                this.line[i][j] = 0;
                this.cline[i][j] = 0;
                this.box[i][j] = 0;
                this.cbox[i][j] = 0;
            }
        }
    }
    
    public void run() {
        while (true) {
            this.repaint();
            this.user_boxes = 0.0f;
            this.comp_boxes = 0.0f;
            this.totalboxes = 0;
            for (int i = 0; i < this.rows * 2 - 2; i += 2) {
                for (int j = 0; j < this.cols - 1; ++j) {
                    if (this.box[i][j] == 1) {
                        ++this.user_boxes;
                        ++this.totalboxes;
                    }
                    if (this.box[i][j] == 2) {
                        ++this.comp_boxes;
                        ++this.totalboxes;
                    }
                }
            }
            if (this.totalboxes == (this.rows - 1) * (this.cols - 1)) {
                this.endgame();
            }
            if (this.gameover == 0) {
                if (this.computers_move == 1.0f) {
                    this.message = "Computers move";
                }
                else {
                    this.message = "Your move, Arrow keys=move and SPACE=select line, F=fill channel.";
                }
                while (this.computers_move != 0.0f) {
                    this.computermove();
                }
            }
            try {
                Thread.sleep(200L);
            }
            catch (Exception ex) {}
        }
    }
    
    public void paint(final Graphics graphics) {
        this.offscreen.setColor(Color.blue);
        this.offscreen.fillRect(0, 0, this.width, this.height);
        this.dw = this.width / (this.cols - 1) / 6;
        this.bw = (this.width - this.dw) / (this.cols - 1);
        this.dh = (this.height - 30) / (this.rows - 1) / 6;
        this.bh = (this.height - 30 - this.dh) / (this.rows - 1);
        for (int i = 0; i < this.rows * 2 - 2; i += 2) {
            for (int j = 0; j < this.cols - 1; ++j) {
                switch (this.box[i][j]) {
                    case 0: {
                        this.offscreen.setColor(Color.blue);
                        break;
                    }
                    case 1: {
                        this.offscreen.setColor(Color.green);
                        break;
                    }
                    case 2: {
                        this.offscreen.setColor(new Color(0, 90, 20));
                        break;
                    }
                    default: {
                        this.offscreen.setColor(Color.black);
                        break;
                    }
                }
                this.offscreen.fillRect((int)(j * this.bw + this.dw), (int)(i / 2 * this.bh + this.dh), (int)(this.bw - this.dw) + 1, (int)(this.bh - this.dh) + 1);
            }
        }
        for (int k = 0; k < this.rows * 2; k += 2) {
            for (int l = 0; l < this.cols - 1; ++l) {
                switch (this.line[k][l]) {
                    case 0: {
                        this.offscreen.setColor(Color.blue);
                        break;
                    }
                    case 1: {
                        this.offscreen.setColor(Color.white);
                        break;
                    }
                    case 2: {
                        this.offscreen.setColor(Color.white);
                        break;
                    }
                    default: {
                        this.offscreen.setColor(Color.gray);
                        break;
                    }
                }
                if (k / 2 < this.rows && l < this.cols - 1) {
                    this.offscreen.fillRect((int)(l * this.bw + this.dw), (int)(k / 2 * this.bh), (int)(this.bw - this.dw), (int)this.dh);
                }
            }
            for (int n = 0; n < this.cols; ++n) {
                switch (this.line[k + 1][n]) {
                    case 0: {
                        this.offscreen.setColor(Color.blue);
                        break;
                    }
                    case 1: {
                        this.offscreen.setColor(Color.white);
                        break;
                    }
                    case 2: {
                        this.offscreen.setColor(Color.white);
                        break;
                    }
                    default: {
                        this.offscreen.setColor(Color.gray);
                        break;
                    }
                }
                if (k / 2 < this.rows - 1) {
                    this.offscreen.fillRect((int)(n * this.bw), (int)(k / 2 * this.bh + this.dh), (int)this.dw, (int)(this.bh - this.dh));
                }
            }
        }
        if ((this.cursor += 2) == 12) {
            if (this.cursorFlag == -1) {
                this.cursorFlag = 1;
            }
            else {
                this.cursorFlag = -1;
                this.offscreen.setColor(Color.red);
                if (this.y % 2 != 0) {
                    this.offscreen.fillRect((int)(this.x * this.bw), (int)(this.y / 2 * this.bh + this.dh), (int)this.dw, (int)(this.bh - this.dh));
                }
                else {
                    this.offscreen.fillRect((int)(this.x * this.bw + this.dw), (int)(this.y / 2 * this.bh), (int)(this.bw - this.dw), (int)this.dh);
                }
            }
            this.cursor = 4;
        }
        this.offscreen.setColor(Color.black);
        for (int n2 = 0; n2 < this.rows; ++n2) {
            for (int n3 = 0; n3 < this.cols; ++n3) {
                this.offscreen.fillOval((int)(n3 * this.bw), (int)(n2 * this.bh), (int)this.dw, (int)this.dh);
            }
        }
        this.offscreen.setColor(Color.white);
        this.offscreen.drawString("Grid(g/G): " + (this.cols - 1) + "x" + (this.rows - 1), 300, 382);
        this.offscreen.drawString(this.message, 0, 396);
        this.offscreen.drawString("Boxes: You=" + (int)this.user_boxes + " " + Math.round(this.user_boxes / ((this.rows - 1) * (this.cols - 1)) * 100.0f) + "%" + "  Computer=" + (int)this.comp_boxes + " " + Math.round(this.comp_boxes / ((this.rows - 1) * (this.cols - 1)) * 100.0f) + "%", 0, 382);
        graphics.drawImage(this.image, 0, 0, this);
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (this.computers_move == 0.0f && this.trading == 0) {
            if ((n == 99 || n == 67) && this.rows < 20 && this.cols < 20) {
                this.message = "OK let's continue on a bigger grid.";
                ++this.rows;
                ++this.cols;
                this.gameover = 0;
            }
            if (n == 103 && this.rows > 4 && this.cols > 4) {
                for (int i = 0; i <= this.rows * 2 - 1; i += 2) {
                    this.line[i][this.cols - 2] = 0;
                    this.line[i + 1][this.cols - 1] = 0;
                    this.box[i][this.cols - 2] = 0;
                }
                for (int j = 0; j < this.cols; ++j) {
                    System.out.println("rows=" + this.rows + " cols=" + this.cols + " j=" + j);
                    this.line[this.rows * 2 - 3][j] = 0;
                    this.line[this.rows * 2 - 2][j] = 0;
                    this.box[this.rows * 2 - 4][j] = 0;
                }
                --this.rows;
                --this.cols;
                this.x = 0;
                this.y = 0;
                this.gameover = 0;
            }
            if (n == 71 && this.rows < 21 && this.cols < 21) {
                ++this.rows;
                ++this.cols;
                this.gameover = 0;
            }
            if ((n == 102 || n == 70) && this.line[this.y][this.x] == 0) {
                this.line[this.y][this.x] = 1;
                this.cline[this.y][this.x] = 1;
                if (this.anyboxes(1) == 0) {
                    this.computers_move = 1.0f;
                }
                else {
                    this.computermove();
                    Label_0454: {
                        break Label_0454;
                        int k;
                        do {
                            this.computermove();
                            k = this.anyboxes(1);
                            this.any = k;
                        } while (k != 0);
                    }
                    if (this.gameover == 0) {
                        this.line[this.cy][this.cx] = 0;
                    }
                }
            }
            if (n == 32 && this.line[this.y][this.x] == 0) {
                this.line[this.y][this.x] = 1;
                this.cline[this.y][this.x] = 1;
                if (this.anyboxes(1) == 0) {
                    this.computers_move = 1.0f;
                }
            }
            if (n == 80 || n == 112) {
                this.initGrid();
            }
            switch (n) {
                case 1007: {
                    if ((this.x < this.cols - 2 && this.y % 2 == 0) || (this.x < this.cols - 1 && this.y % 2 != 0)) {
                        ++this.x;
                        this.cursor = 10;
                        this.cursorFlag = 1;
                        break;
                    }
                }
                case 1006: {
                    if (this.x > 0) {
                        --this.x;
                        this.cursor = 10;
                        this.cursorFlag = 1;
                        break;
                    }
                }
                case 1004: {
                    if (this.y > 0) {
                        --this.y;
                        if (this.x == this.cols - 1 && this.y % 2 == 0) {
                            --this.x;
                        }
                        this.cursor = 10;
                        this.cursorFlag = 1;
                        break;
                    }
                }
                case 1005: {
                    if (this.y < this.rows * 2 - 2) {
                        ++this.y;
                        if (this.x == this.cols - 1 && this.y % 2 == 0) {
                            --this.x;
                        }
                        this.cursor = 10;
                        this.cursorFlag = 1;
                        break;
                    }
                    break;
                }
            }
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.y = (int)(n2 / (this.bh / 2.0f));
        if (this.y > this.rows * 2 - 2) {
            this.y = this.rows * 2 - 2;
        }
        if (this.y % 2 == 0) {
            this.x = (int)(n / (this.bw / 2.0f)) / 2;
        }
        if (this.y % 2 == 1) {
            this.x = (int)((n + this.bw / 2.0f) / (this.bw / 2.0f)) / 2;
        }
        this.cursor = 10;
        this.cursorFlag = 1;
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.line[this.y][this.x] == 0) {
            this.line[this.y][this.x] = 1;
            this.cline[this.y][this.x] = 1;
            if (this.anyboxes(1) == 0) {
                this.computers_move = 1.0f;
            }
        }
        return true;
    }
    
    public void start() {
        this.animation = new Thread(this);
        if (this.animation != null) {
            this.animation.start();
        }
    }
    
    public void stop() {
        if (this.animation != null) {
            this.animation.stop();
            this.animation = null;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public int anyboxes(final int n) {
        int n2 = 0;
        for (int i = 0; i < this.rows * 2 - 3; i += 2) {
            for (int j = 0; j < this.cols - 1; ++j) {
                if (this.box[i][j] == 0 && this.line[i][j] != 0 && this.line[i + 1][j] != 0 && this.line[i + 1][j + 1] != 0 && this.line[i + 2][j] != 0) {
                    ++n2;
                    this.box[i][j] = n;
                    this.cbox[i][j] = n;
                }
            }
        }
        this.repaint();
        return n2;
    }
    
    public void endgame() {
        if (this.user_boxes > this.comp_boxes) {
            this.message = " You Won! P=play again, C=continue.";
        }
        if (this.user_boxes < this.comp_boxes) {
            this.message = " I Won! P=play again, C=continue.";
        }
        if (this.user_boxes == this.comp_boxes) {
            this.message = " We tied! P=play again, C=continue.";
        }
        this.gameover = 1;
        this.line[this.cy][this.cx] = 1;
    }
    
    public int anycboxes() {
        int n = 0;
        for (int i = 0; i < this.rows * 2 - 3; i += 2) {
            for (int j = 0; j < this.cols - 1; ++j) {
                if (this.cbox[i][j] == 0 && this.cline[i][j] != 0 && this.cline[i + 1][j] != 0 && this.cline[i + 1][j + 1] != 0 && this.cline[i + 2][j] != 0) {
                    ++n;
                    if (this.computers_move == 1.0f) {
                        this.cbox[i][j] = 2;
                    }
                    else {
                        this.cbox[i][j] = 1;
                    }
                }
            }
        }
        return n;
    }
    
    public void setcbox() {
        for (int i = 0; i < this.rows * 2 - 1; i += 2) {
            for (int j = 0; j < this.cols; ++j) {
                this.cbox[i][j] = this.box[i][j];
                this.cline[i][j] = this.line[i][j];
                this.cline[i + 1][j] = this.line[i + 1][j];
            }
        }
    }
    
    public int computermove() {
        int cx = 0;
        int cy = 0;
        int n = 0;
        this.reset_last_move_in_display();
        this.i = 0;
        while (this.i < this.rows * 2 - 3) {
            this.j = 0;
            while (this.j < this.cols - 1) {
                if (this.box[this.i][this.j] == 0 && this.find3get4thIn_box() != 0) {
                    n = 1;
                    break;
                }
                ++this.j;
            }
            if (n != 0) {
                break;
            }
            this.i += 2;
        }
        if (n == 0) {
            this.i = 0;
            while (this.i < this.rows * 2 - 3) {
                this.j = 0;
                while (this.j < this.cols - 1) {
                    if (this.box[this.i][this.j] == 0 && this.count_lines() == 1 && this.check_square_for_ok_line() != 0) {
                        n = 1;
                        break;
                    }
                    ++this.j;
                }
                if (n != 0) {
                    break;
                }
                this.i += 2;
            }
        }
        if (n == 0) {
            this.i = 0;
            while (this.i < this.rows * 2 - 3) {
                this.j = 0;
                while (this.j < this.cols - 1) {
                    if (this.box[this.i][this.j] == 0 && this.count_lines() == 0 && this.check_square_for_ok_line() != 0) {
                        n = 1;
                        break;
                    }
                    ++this.j;
                }
                if (n != 0) {
                    break;
                }
                this.i += 2;
            }
        }
        if (n == 0) {
            int n2 = 10000;
            for (int i = 0; i < this.rows * 2 - 1; ++i) {
                int cols;
                if (i % 2 == 0) {
                    cols = this.cols - 1;
                }
                else {
                    cols = this.cols;
                }
                for (int j = 0; j < cols; ++j) {
                    this.setcbox();
                    if (this.cline[i][j] == 0) {
                        this.cline[i][j] = 2;
                        final int find_consequence = this.find_consequence();
                        if (find_consequence < n2) {
                            cx = j;
                            cy = i;
                            n2 = find_consequence;
                        }
                    }
                }
            }
            this.cx = cx;
            this.cy = cy;
            this.no_safe_lines_left = 1;
            n = 1;
        }
        if (this.computers_move == 0.0f) {
            this.line[this.cy][this.cx] = 1;
        }
        else {
            this.line[this.cy][this.cx] = 2;
            if (this.anyboxes(2) == 0) {
                this.computers_move = 0.0f;
            }
        }
        return n;
    }
    
    public int count_lines() {
        int n = 0;
        if (this.line[this.i][this.j] != 0) {
            ++n;
        }
        if (this.line[this.i + 1][this.j] != 0) {
            ++n;
        }
        if (this.line[this.i + 1][this.j + 1] != 0) {
            ++n;
        }
        if (this.line[this.i + 2][this.j] != 0) {
            ++n;
        }
        return n;
    }
    
    public int find3get4thIn_box() {
        int n = 0;
        if (this.line[this.i][this.j] != 0) {
            ++n;
        }
        else {
            this.cy = this.i;
            this.cx = this.j;
        }
        if (this.line[this.i + 1][this.j] != 0) {
            ++n;
        }
        else {
            this.cy = this.i + 1;
            this.cx = this.j;
        }
        if (this.line[this.i + 1][this.j + 1] != 0) {
            ++n;
        }
        else {
            this.cy = this.i + 1;
            this.cx = this.j + 1;
        }
        if (this.line[this.i + 2][this.j] != 0) {
            ++n;
        }
        else {
            this.cy = this.i + 2;
            this.cx = this.j;
        }
        if (n == 3) {
            return 1;
        }
        return 0;
    }
    
    public int find3get4thIn_cbox() {
        int n = 0;
        for (int i = 0; i < this.rows * 2 - 3; i += 2) {
            for (int j = 0; j < this.cols - 1; ++j) {
                int n2 = 0;
                if (this.cline[i][j] != 0) {
                    ++n2;
                }
                else {
                    this.cy = i;
                    this.cx = j;
                }
                if (this.cline[i + 1][j] != 0) {
                    ++n2;
                }
                else {
                    this.cy = i + 1;
                    this.cx = j;
                }
                if (this.cline[i + 1][j + 1] != 0) {
                    ++n2;
                }
                else {
                    this.cy = i + 1;
                    this.cx = j + 1;
                }
                if (this.cline[i + 2][j] != 0) {
                    ++n2;
                }
                else {
                    this.cy = i + 2;
                    this.cx = j;
                }
                if (n2 == 3) {
                    n = 3;
                }
                if (n != 0) {
                    break;
                }
            }
            if (n != 0) {
                break;
            }
        }
        return n;
    }
    
    public int check_square_for_ok_line() {
        this.setcbox();
        if (this.cline[this.i + 1][this.j] == 0) {
            this.cline[this.i + 1][this.j] = 2;
            if (this.givingAway3sides() == 0) {
                this.cx = this.j;
                this.cy = this.i + 1;
                return 1;
            }
            this.cline[this.i + 1][this.j] = 0;
        }
        if (this.cline[this.i + 1][this.j + 1] == 0) {
            this.cline[this.i + 1][this.j + 1] = 2;
            if (this.givingAway3sides() == 0) {
                this.cx = this.j + 1;
                this.cy = this.i + 1;
                return 2;
            }
            this.cline[this.i + 1][this.j + 1] = 0;
        }
        if (this.cline[this.i][this.j] == 0) {
            this.cline[this.i][this.j] = 2;
            if (this.givingAway3sides() == 0) {
                this.cx = this.j;
                this.cy = this.i;
                return 3;
            }
            this.cline[this.i][this.j] = 0;
        }
        if (this.cline[this.i + 2][this.j] == 0) {
            this.cline[this.i + 2][this.j] = 2;
            if (this.givingAway3sides() == 0) {
                this.cx = this.j;
                this.cy = this.i + 2;
                return 4;
            }
            this.cline[this.i + 2][this.j] = 0;
        }
        return 0;
    }
    
    public int givingAway3sides() {
        for (int i = 0; i < this.rows * 2 - 3; i += 2) {
            for (int j = 0; j < this.cols - 1; ++j) {
                int n = 0;
                if (this.cline[i][j] != 0) {
                    ++n;
                }
                if (this.cline[i + 1][j] != 0) {
                    ++n;
                }
                if (this.cline[i + 1][j + 1] != 0) {
                    ++n;
                }
                if (this.cline[i + 2][j] != 0) {
                    ++n;
                }
                if (n == 3) {
                    return -1;
                }
            }
        }
        return 0;
    }
    
    public void reset_last_move_in_display() {
        for (int i = 0; i < 48; ++i) {
            for (int j = 0; j < 24; ++j) {
                if (this.line[i][j] == 3) {
                    this.line[i][j] = 2;
                }
            }
        }
    }
    
    public int find_consequence() {
        if (this.find3get4thIn_cbox() == 0) {
            return 0;
        }
        this.cline[this.cy][this.cx] = 2;
        return this.find_consequence() + this.anycboxes();
    }
    
    public squares() {
        this.message = "Hello.";
        this.rows = 10;
        this.cols = 10;
        this.no_safe_lines_left = 1;
        this.cursorFlag = 1;
    }
}
