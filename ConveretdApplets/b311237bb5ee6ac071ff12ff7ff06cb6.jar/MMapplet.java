import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MMapplet extends Applet implements TGMouseHandler
{
    static final int CHOICE_BOX_SIZE = 25;
    static final int CHOICES_LEFT_X = -100;
    static final int CHOICES_TOP_Y = 140;
    static final int RED_CHOICE_Y = 140;
    static final int ORANGE_CHOICE_Y = 115;
    static final int YELLOW_CHOICE_Y = 90;
    static final int GREEN_CHOICE_Y = 65;
    static final int BLUE_CHOICE_Y = 40;
    static final int VIOLET_CHOICE_Y = 15;
    static final int CHOICES_BOTTOM_Y = -9;
    static final int[] COLOR_CHOICES;
    static final int CHECK_BOX_WIDTH = 12;
    static final int CHECK_BOX_HEIGHT = 12;
    static final int DUPS_CHECK_BOX_X = -130;
    static final int DUPS_CHECK_BOX_Y = -25;
    static final int BUTTON_WIDTH = 100;
    static final int BUTTON_HEIGHT = 25;
    static final int CLEAR_COLOR_X = -137;
    static final int CLEAR_COLOR_Y = -30;
    static final int GUESS_X = -137;
    static final int GUESS_Y = -60;
    static final int NEW_GAME_X = -137;
    static final int NEW_GAME_Y = -90;
    static final int MAX_GUESSES = 10;
    static final int GUESS_BOX_SIZE = 20;
    static final int GUESS_BOX_SPACING = 5;
    static final int GUESS_BOXES_X = -15;
    static final int GUESS_BOXES_Y = 140;
    static final int EXACT_COUNT_X = 100;
    static final int INEXACT_COUNT_X = 120;
    static final int ANSWER_BOX_SIZE = 15;
    static final int ANSWER_BOX_SPACING = 3;
    static final int ANSWER_BOXES_X = 12;
    static final int ANSWER_BOXES_Y = -130;
    boolean allowDups;
    boolean gameOver;
    int choiceNumber;
    int guessNumber;
    int[] guessChoices;
    int[] answer;
    TGCanvas canvas;
    Turtle turtle;
    
    public MMapplet() {
        this.allowDups = false;
        this.guessChoices = new int[4];
        this.answer = new int[4];
    }
    
    int anyMatches() {
        int n = 0;
        final int[] array = this.answer.clone();
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (this.guessChoices[i] == array[j]) {
                    ++n;
                    array[j] = -1;
                    break;
                }
            }
        }
        return n;
    }
    
    void drawBox(final int n, final int n2, final int n3, final int n4) {
        this.turtle.penup();
        this.turtle.setxy(n, n2);
        this.turtle.setheading(90);
        this.turtle.pendown();
        this.turtle.forward(n3);
        this.turtle.right(90);
        this.turtle.forward(n4);
        this.turtle.right(90);
        this.turtle.forward(n3);
        this.turtle.right(90);
        this.turtle.forward(n4);
        this.turtle.penup();
    }
    
    void drawButton(final int n, final int n2, final String s) {
        this.turtle.setpensize(2);
        this.turtle.setpencolor(0);
        this.drawBox(n, n2, 100, 25);
        this.turtle.setxy(n + 4, n2 - 25 + 8);
        this.turtle.setlabelheight(14);
        this.turtle.label(s);
    }
    
    void drawCheckmark(final int n, final int n2, final int n3, final int n4) {
        this.turtle.setpensize(5);
        this.turtle.setpencolor(0);
        this.turtle.penup();
        this.turtle.setxy(n + 1, n2 - n4 / 2);
        this.turtle.pendown();
        this.turtle.setxy(n + n3 / 3, n2 - (n4 - 1));
        this.turtle.setxy(n + (n3 - 1), n2 - 1);
    }
    
    void drawColorChoices() {
        for (int i = 0; i < 6; ++i) {
            this.turtle.setpensize(1);
            this.turtle.setpencolor(0);
            final int n = 140 - i * 25;
            this.drawBox(-100, n, 25, 25);
            this.fillBox(-99, n - 1, 23, 24, MMapplet.COLOR_CHOICES[i]);
        }
    }
    
    void drawClearColorButton() {
        this.drawButton(-137, -30, "ClearChoice");
    }
    
    void drawDupsCheckbox() {
        this.turtle.setpensize(1);
        this.turtle.setpencolor(0);
        this.drawBox(-130, -25, 12, 12);
        this.turtle.setxy(-113, -37);
        this.turtle.setlabelheight(12);
        this.turtle.label("Allow Dups");
        if (this.allowDups) {
            this.drawCheckmark(-130, -25, 12, 12);
        }
    }
    
    void drawGuessBoxes() {
        this.turtle.setpensize(1);
        this.turtle.setpencolor(0);
        final int n = 140 - this.guessNumber * 25;
        for (int i = 0; i < 4; ++i) {
            this.drawBox(-15 + i * 25, n, 20, 20);
        }
    }
    
    void drawGuessButton() {
        this.drawButton(-137, -60, "CheckGuess");
    }
    
    void drawNewGameButton() {
        this.drawButton(-137, -90, "NewGame");
    }
    
    int exactMatches() {
        int n = 0;
        for (int i = 0; i < 4; ++i) {
            if (this.guessChoices[i] == this.answer[i]) {
                ++n;
            }
        }
        return n;
    }
    
    void fillBox(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.turtle.setpensize(1);
        this.turtle.setpencolor(n5);
        this.turtle.setheading(90);
        for (int i = 0; i < n4; ++i) {
            this.turtle.penup();
            this.turtle.setxy(n, n2 - i);
            this.turtle.pendown();
            this.turtle.forward(n3);
        }
    }
    
    void gameLost() {
        this.turtle.penup();
        this.turtle.setxy(-26, -123);
        this.turtle.setlabelheight(15);
        this.turtle.setpencolor(0);
        this.turtle.label("Sorry! Answer was:");
        this.turtle.setpensize(1);
        for (int i = 0; i < 4; ++i) {
            final int n = 12 + i * 18;
            this.turtle.setpencolor(0);
            this.drawBox(n, -130, 15, 15);
            this.fillBox(n + 1, -131, 13, 14, this.answer[i]);
        }
        this.gameOver = true;
    }
    
    void gameWon() {
        this.turtle.penup();
        this.turtle.setxy(-52, -140);
        this.turtle.setlabelheight(24);
        this.turtle.setpencolor(0);
        this.turtle.label("You Win!");
        this.gameOver = true;
    }
    
    void initialize() {
        this.turtle.hideturtle();
        this.drawColorChoices();
        this.drawClearColorButton();
        this.drawGuessButton();
        this.drawNewGameButton();
        this.makeAnswer();
        this.guessNumber = 0;
        this.choiceNumber = 0;
        for (int i = 0; i < 4; ++i) {
            this.guessChoices[i] = -1;
        }
        this.drawGuessBoxes();
        this.gameOver = false;
    }
    
    void makeAnswer() {
        for (int i = 0; i < 4; ++i) {
            int n = MMapplet.COLOR_CHOICES[(int)(Math.random() * MMapplet.COLOR_CHOICES.length)];
            if (!this.allowDups) {
                for (int j = 0; j < i; ++j) {
                    if (n == this.answer[j]) {
                        n = -1;
                        break;
                    }
                }
            }
            if (n != -1) {
                this.answer[i] = n;
            }
        }
    }
    
    void showExactMatches(final int n) {
        final int n2 = 140 - this.guessNumber * 25;
        this.turtle.penup();
        this.turtle.setxy(100, n2 - 20);
        this.turtle.pendown();
        this.turtle.setlabelheight(25);
        this.turtle.setpencolor(0);
        this.turtle.label(Integer.toString(n));
    }
    
    void showInexactMatches(final int n) {
        final int n2 = 140 - this.guessNumber * 25;
        this.turtle.penup();
        this.turtle.setxy(120, n2 - 20);
        this.turtle.pendown();
        this.turtle.setlabelheight(25);
        this.turtle.setpencolor(15);
        this.turtle.label(Integer.toString(n));
    }
    
    boolean tryAllowDupsBox(final int n, final int n2) {
        if (n < -130 || n > -118) {
            return false;
        }
        if (n2 > -25 || n2 < -37) {
            return false;
        }
        if (this.allowDups) {
            this.fillBox(-129, -26, 10, 11, 7);
            this.allowDups = false;
        }
        else {
            this.drawCheckmark(-130, -25, 12, 12);
            this.allowDups = true;
        }
        return true;
    }
    
    boolean tryClearColor(int n, int n2) {
        if (n < -137 || n > -37) {
            return false;
        }
        if (n2 > -30 || n2 < -55) {
            return false;
        }
        if (this.choiceNumber > 0) {
            --this.choiceNumber;
            n = -15 + this.choiceNumber * 25;
            n2 = 140 - this.guessNumber * 25;
            this.fillBox(n + 1, n2 - 1, 18, 19, 7);
        }
        return true;
    }
    
    boolean tryColorChoice(int n, int n2) {
        if (n < -100 || n >= -75) {
            return false;
        }
        if (n2 > 140 || n2 < -9) {
            return false;
        }
        if (this.choiceNumber == 4) {
            return true;
        }
        final int n3 = MMapplet.COLOR_CHOICES[Math.abs(n2 - 140) / 25];
        n = -15 + this.choiceNumber * 25;
        n2 = 140 - this.guessNumber * 25;
        this.fillBox(n + 1, n2 - 1, 18, 19, n3);
        this.guessChoices[this.choiceNumber] = n3;
        ++this.choiceNumber;
        return true;
    }
    
    boolean tryGuess(final int n, final int n2) {
        if (n < -137 || n > -37) {
            return false;
        }
        if (n2 > -60 || n2 < -85) {
            return false;
        }
        if (this.choiceNumber < 4) {
            return true;
        }
        final int exactMatches = this.exactMatches();
        this.showExactMatches(exactMatches);
        if (exactMatches == 4) {
            this.gameWon();
            return true;
        }
        this.showInexactMatches(this.anyMatches() - exactMatches);
        ++this.guessNumber;
        if (this.guessNumber == 10) {
            this.gameLost();
            return true;
        }
        this.drawGuessBoxes();
        this.choiceNumber = 0;
        return true;
    }
    
    boolean tryNewGame(final int n, final int n2) {
        if (n < -137 || n >= -37) {
            return false;
        }
        if (n2 > -90 || n2 <= -115) {
            return false;
        }
        this.canvas.clean();
        this.initialize();
        return true;
    }
    
    public void mouseClicked() {
        final int mousex = this.canvas.mousex();
        final int mousey = this.canvas.mousey();
        if (this.tryNewGame(mousex, mousey)) {
            return;
        }
        if (this.gameOver) {
            return;
        }
        if (this.tryClearColor(mousex, mousey)) {
            return;
        }
        if (this.tryGuess(mousex, mousey)) {
            return;
        }
        this.tryColorChoice(mousex, mousey);
    }
    
    public void mouseMoved() {
    }
    
    public void init() {
        System.out.println("init:");
        this.setLayout(new BorderLayout());
        this.add("Center", this.canvas = new TGCanvas(400, 400));
        this.canvas.addMouseHandler(this);
        this.turtle = new Turtle(this.canvas);
    }
    
    public void start() {
        System.out.println("start:");
        this.initialize();
        this.repaint();
    }
    
    public void stop() {
        System.out.println("stop:");
    }
    
    public void destroy() {
        System.out.println("destroy:");
    }
    
    static {
        COLOR_CHOICES = new int[] { 4, 14, 6, 2, 1, 13 };
    }
}
