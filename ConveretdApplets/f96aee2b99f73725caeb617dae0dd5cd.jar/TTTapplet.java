import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TTTapplet extends Applet implements TGMouseHandler
{
    private boolean game_Over;
    private char player;
    private char[] squares;
    private char[] win_Combo_States;
    private final int newgame_width = 100;
    private final int newgame_height = 40;
    private final int newgame_X = -285;
    private final int newgame_Y = 170;
    private final int[] square_X_Center;
    private final int[] square_Y_Center;
    private final int[][] win_Combos;
    private int[] win_Combo_Empty;
    private TGCanvas canvas;
    private Turtle turtle;
    
    public TTTapplet() {
        this.game_Over = false;
        this.square_X_Center = new int[] { -100, 0, 100, -100, 0, 100, -100, 0, 100 };
        this.square_Y_Center = new int[] { 100, 100, 100, 0, 0, 0, -100, -100, -100 };
        this.win_Combos = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 1, 4, 7 }, { 2, 5, 8 }, { 3, 6, 9 }, { 1, 5, 9 }, { 3, 5, 7 } };
    }
    
    public void mouseClick() {
        final int mousex = this.canvas.mousex();
        final int mousey = this.canvas.mousey();
        if (this.new_Game(mousex, mousey)) {
            return;
        }
        if (this.game_Over) {
            return;
        }
        final int mouse_Square_Num = this.mouse_Square_Num(mousex, mousey);
        if (this.squares[mouse_Square_Num - 1] == '\0') {
            this.fill_Square(mouse_Square_Num);
            if (!this.game_Over) {
                this.switch_Player();
                this.fill_Square(this.pick_Move());
                this.switch_Player();
            }
        }
    }
    
    private void initialize() {
        this.player = 'X';
        for (int i = 0; i < 9; ++i) {
            this.squares[i] = '\0';
        }
        this.turtle.hideturtle();
        this.turtle.setpensize(3);
        this.drawBoard();
        this.draw_NewGame_Button();
    }
    
    private int first_Empty_Square() {
        for (int i = 0; i < this.squares.length; ++i) {
            if (this.squares[i] == '\0') {
                return i + 1;
            }
        }
        return 0;
    }
    
    private int available_Corner() {
        if (this.squares[0] == '\0') {
            return 1;
        }
        if (this.squares[2] == '\0') {
            return 3;
        }
        if (this.squares[6] == '\0') {
            return 7;
        }
        if (this.squares[8] == '\0') {
            return 9;
        }
        return -1;
    }
    
    private void compute_Win_Combo_Empty() {
        for (int i = 0; i < 8; ++i) {
            int n = 0;
            for (int j = 0; j < 3; ++j) {
                if (this.squares[this.win_Combos[i][j] - 1] == '\0') {
                    n = n * 10 + this.win_Combos[i][j];
                }
            }
            this.win_Combo_Empty[i] = n;
        }
    }
    
    private void compute_Win_Combo_States() {
        for (int i = 0; i < 8; ++i) {
            char c = '\0';
            for (int j = 0; j < 3; ++j) {
                c |= this.squares[this.win_Combos[i][j] - 1];
            }
            this.win_Combo_States[i] = c;
        }
    }
    
    private void drawBoard() {
        this.turtle.pu();
        this.turtle.setxy(-50, -150);
        this.turtle.setheading(0);
        this.turtle.setpc(0);
        this.turtle.pd();
        this.turtle.fd(300);
        this.turtle.pu();
        this.turtle.setxy(50, -150);
        this.turtle.setheading(0);
        this.turtle.pd();
        this.turtle.fd(300);
        this.turtle.pu();
        this.turtle.setxy(-150, -50);
        this.turtle.setheading(90);
        this.turtle.pd();
        this.turtle.fd(300);
        this.turtle.pu();
        this.turtle.setxy(-150, 50);
        this.turtle.setheading(90);
        this.turtle.pd();
        this.turtle.fd(300);
        this.turtle.pu();
    }
    
    private void draw_NewGame_Button() {
        this.turtle.pu();
        this.turtle.setxy(-285, 170);
        this.turtle.setheading(90);
        this.turtle.pd();
        this.turtle.fd(100);
        this.turtle.rt(90);
        this.turtle.fd(40);
        this.turtle.rt(90);
        this.turtle.fd(100);
        this.turtle.rt(90);
        this.turtle.fd(40);
        this.turtle.rt(90);
        this.turtle.pu();
        this.turtle.setxy(-279, 144);
        this.turtle.setLabelHeight(18);
        this.turtle.label("New Game");
    }
    
    private void drawO() {
        this.turtle.setheading(0);
        this.turtle.setpencolor(4);
        this.turtle.penup();
        this.turtle.forward(35);
        this.turtle.right(100);
        this.turtle.pendown();
        for (int i = 1; i <= 18; ++i) {
            this.turtle.forward(12);
            this.turtle.right(20);
        }
        this.turtle.penup();
    }
    
    private void drawX() {
        this.turtle.setheading(45);
        this.turtle.setpencolor(1);
        this.turtle.pendown();
        this.turtle.forward(50);
        this.turtle.back(100);
        this.turtle.forward(50);
        this.turtle.left(90);
        this.turtle.forward(50);
        this.turtle.back(100);
        this.turtle.penup();
    }
    
    private boolean player_Wins() {
        return (this.squares[0] == this.player && this.squares[1] == this.player && this.squares[2] == this.player) || (this.squares[3] == this.player && this.squares[4] == this.player && this.squares[5] == this.player) || (this.squares[6] == this.player && this.squares[7] == this.player && this.squares[8] == this.player) || (this.squares[0] == this.player && this.squares[3] == this.player && this.squares[6] == this.player) || (this.squares[1] == this.player && this.squares[4] == this.player && this.squares[7] == this.player) || (this.squares[2] == this.player && this.squares[5] == this.player && this.squares[8] == this.player) || (this.squares[0] == this.player && this.squares[4] == this.player && this.squares[8] == this.player) || (this.squares[2] == this.player && this.squares[4] == this.player && this.squares[6] == this.player);
    }
    
    void fill_Square(final int n) {
        if (this.squares[n - 1] != '\0') {
            return;
        }
        this.squares[n - 1] = this.player;
        this.turtle.penup();
        this.turtle.setxy(this.square_X_Center[n - 1], this.square_Y_Center[n - 1]);
        if (this.player == 'X') {
            this.drawX();
        }
        else {
            this.drawO();
        }
        if (this.player_Wins()) {
            this.game_Won();
        }
        else if (this.first_Empty_Square() == 0) {
            this.game_Tied();
        }
    }
    
    void game_Tied() {
        this.game_Over = true;
        this.turtle.pu();
        this.turtle.setpc(0);
        this.turtle.setxy(-100, -200);
        this.turtle.setLabelHeight(36);
        this.turtle.label("TIE Game!!!");
    }
    
    void game_Won() {
        this.game_Over = true;
        this.turtle.pu();
        this.turtle.setxy(-70, -200);
        this.turtle.setLabelHeight(36);
        this.turtle.label(this.player + " Wins!!!");
    }
    
    int mouse_Square_Num(final int n, final int n2) {
        if (n < -50) {
            if (n2 > 50) {
                return 1;
            }
            if (n2 > -50) {
                return 4;
            }
            return 7;
        }
        else if (n < 50) {
            if (n2 > 50) {
                return 2;
            }
            if (n2 > -50) {
                return 5;
            }
            return 8;
        }
        else {
            if (n2 > 50) {
                return 3;
            }
            if (n2 > -50) {
                return 6;
            }
            return 9;
        }
    }
    
    int winning_Combo(final int n, final char c) {
        for (int i = n; i < 8; ++i) {
            if (this.win_Combo_States[i] == c && this.win_Combo_Empty[i] > 10) {
                return i;
            }
        }
        return -1;
    }
    
    int fork_Square(final char c) {
        for (int n = this.winning_Combo(0, c); n >= 0 && n < 7; n = this.winning_Combo(n + 1, c)) {
            for (int i = this.win_Combo_Empty[n]; i > 0; i /= 10) {
                for (int j = this.winning_Combo(n + 1, c); j > 0; j = this.winning_Combo(j + 1, c)) {
                    for (int k = this.win_Combo_Empty[j]; k > 0; k /= 10) {
                        if (i % 10 == k % 10) {
                            return i % 10;
                        }
                    }
                }
            }
        }
        return 0;
    }
    
    boolean is_Fork(final char c, final int n) {
        int n2 = 0;
        for (int i = 0; i < 8; ++i) {
            if (this.win_Combo_States[i] == c) {
                for (int j = this.win_Combo_Empty[i]; j > 0; j /= 10) {
                    if (j % 10 == n) {
                        ++n2;
                    }
                }
            }
        }
        return n2 > 1;
    }
    
    int winning_Square(final char c) {
        for (int i = 0; i < 8; ++i) {
            if (this.win_Combo_States[i] == c) {
                final int n = this.win_Combo_Empty[i];
                if (n > 0 && n < 10) {
                    return n;
                }
            }
        }
        return 0;
    }
    
    private int pick_Move() {
        this.compute_Win_Combo_States();
        this.compute_Win_Combo_Empty();
        final int winning_Square = this.winning_Square(this.player);
        if (winning_Square > 0) {
            return winning_Square;
        }
        final int winning_Square2 = this.winning_Square(this.other_Player());
        if (winning_Square2 > 0) {
            return winning_Square2;
        }
        final int fork_Square = this.fork_Square(this.player);
        if (fork_Square > 0) {
            return fork_Square;
        }
        final int winning_Combo = this.winning_Combo(0, this.player);
        if (winning_Combo >= 0) {
            final int n = this.win_Combo_Empty[winning_Combo] % 10;
            if (this.is_Fork(this.other_Player(), n)) {
                return n;
            }
            return this.win_Combo_Empty[winning_Combo] / 10;
        }
        else {
            if (this.squares[4] == '\0') {
                return 5;
            }
            final int available_Corner = this.available_Corner();
            if (available_Corner > 0) {
                return available_Corner;
            }
            return this.first_Empty_Square();
        }
    }
    
    char other_Player() {
        if (this.player == 'X') {
            return 'O';
        }
        return 'X';
    }
    
    void switch_Player() {
        this.player = this.other_Player();
    }
    
    boolean new_Game(final int n, final int n2) {
        if (n < -285 || n > -185) {
            return false;
        }
        if (n2 > 170 || n2 < 130) {
            return false;
        }
        this.canvas.clean();
        this.initialize();
        this.game_Over = false;
        return true;
    }
    
    public void init() {
        this.squares = new char[9];
        this.win_Combo_States = new char[8];
        this.win_Combo_Empty = new int[8];
        this.setLayout(new BorderLayout());
        (this.canvas = new TGCanvas(600, 400)).setBackground(Color.white);
        this.add("Center", this.canvas);
        this.canvas.addMouseHandler(this);
        this.turtle = new Turtle(this.canvas);
        this.initialize();
    }
}
