import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import com.guyhaas.Intro_to_Programming.TurtleGraphics;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MMapplet extends Applet implements MouseListener
{
    static final int black = 0;
    static final int blue = 1;
    static final int green = 2;
    static final int red = 4;
    static final int yellow = 6;
    static final int white = 7;
    static final int purple = 13;
    static final int orange = 14;
    static final int gray = 15;
    static final int choiceBoxSize = 25;
    static final int choicesLeftX = -100;
    static final int choicesTopY = 150;
    static final int redChoiceY = 150;
    static final int orangeChoiceY = 125;
    static final int yellowChoiceY = 100;
    static final int greenChoiceY = 75;
    static final int blueChoiceY = 50;
    static final int purpleChoiceY = 25;
    static final int choicesBottomY = 1;
    static final int[] colorChoices;
    static final int checkBoxWidth = 12;
    static final int checkBoxHeight = 12;
    static final int dupsCheckBoxX = -130;
    static final int dupsCheckBoxY = -25;
    static final int buttonWidth = 85;
    static final int buttonHeight = 25;
    static final int clearcolorX = -130;
    static final int clearcolorY = -65;
    static final int guessX = -130;
    static final int guessY = -95;
    static final int newgameX = -130;
    static final int newgameY = -125;
    static final int maxGuesses = 10;
    static final int guessBoxSize = 20;
    static final int guessBoxSpacing = 5;
    static final int guessBoxesX = -15;
    static final int guessBoxesY = 150;
    static final int exactCountX = 100;
    static final int inexactCountX = 120;
    static final int answerBoxSize = 15;
    static final int answerBoxSpacing = 3;
    static final int answerBoxesX = 15;
    static final int answerBoxesY = -125;
    boolean allowDups;
    boolean gameOver;
    int choiceNumber;
    int guessNumber;
    int[] guessChoices;
    int[] answer;
    TurtleGraphics tgObj;
    
    public MMapplet() {
        this.allowDups = false;
        this.guessChoices = new int[4];
        this.answer = new int[4];
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.myMouseClickHandler(this.tgObj.toTurtleSpaceX(mouseEvent.getX()), this.tgObj.toTurtleSpaceY(mouseEvent.getY()));
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
        this.tgObj.penup();
        this.tgObj.setxy(n, n2);
        this.tgObj.setheading(90);
        this.tgObj.pendown();
        this.tgObj.forward(n3);
        this.tgObj.right(90);
        this.tgObj.forward(n4);
        this.tgObj.right(90);
        this.tgObj.forward(n3);
        this.tgObj.right(90);
        this.tgObj.forward(n4);
        this.tgObj.penup();
    }
    
    void drawButton(final int n, final int n2, final String s) {
        this.tgObj.setpensize(2);
        this.tgObj.setpencolor(0);
        this.drawBox(n, n2, 85, 25);
        this.tgObj.setxy(n + 4, n2 - 25 + 8);
        this.tgObj.setfontsize(15);
        this.tgObj.label(s);
    }
    
    void drawCheckmark(final int n, final int n2, final int n3, final int n4) {
        this.tgObj.setpensize(5);
        this.tgObj.setpencolor(0);
        this.tgObj.penup();
        this.tgObj.setxy(n + 1, n2 - n4 / 2);
        this.tgObj.pendown();
        this.tgObj.setxy(n + n3 / 3, n2 - (n4 - 1));
        this.tgObj.setxy(n + (n3 - 1), n2 - 1);
    }
    
    void drawColorChoices() {
        for (int i = 0; i < 6; ++i) {
            this.tgObj.setpensize(1);
            this.tgObj.setpencolor(0);
            final int n = 150 - i * 25;
            this.drawBox(-100, n, 25, 25);
            this.fillBox(-99, n - 1, 23, 24, MMapplet.colorChoices[i]);
        }
    }
    
    void drawClearColorButton() {
        this.drawButton(-130, -65, "Clear Color");
    }
    
    void drawDupsCheckbox() {
        this.tgObj.setpensize(1);
        this.tgObj.setpencolor(0);
        this.drawBox(-130, -25, 12, 12);
        this.tgObj.setxy(-113, -37);
        this.tgObj.setfontsize(14);
        this.tgObj.label("Allow Dups");
        if (this.allowDups) {
            this.drawCheckmark(-130, -25, 12, 12);
        }
    }
    
    void drawGuessBoxes() {
        this.tgObj.setpensize(1);
        this.tgObj.setpencolor(0);
        final int n = 150 - this.guessNumber * 25;
        for (int i = 0; i < 4; ++i) {
            this.drawBox(-15 + i * 25, n, 20, 20);
        }
    }
    
    void drawGuessButton() {
        this.drawButton(-130, -95, "Guess");
    }
    
    void drawNewGameButton() {
        this.drawButton(-130, -125, "New Game");
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
        this.tgObj.setpensize(1);
        this.tgObj.setpencolor(n5);
        this.tgObj.setheading(90);
        for (int i = 0; i < n4; ++i) {
            this.tgObj.penup();
            this.tgObj.setxy(n, n2 - i);
            this.tgObj.pendown();
            this.tgObj.forward(n3);
        }
    }
    
    void gameLost() {
        this.tgObj.penup();
        this.tgObj.setxy(-15, -120);
        this.tgObj.setfontsize(14);
        this.tgObj.setpencolor(0);
        this.tgObj.label("Sorry!  Answer was:");
        this.tgObj.setpensize(1);
        for (int i = 0; i < 4; ++i) {
            final int n = 15 + i * 18;
            this.tgObj.setpencolor(0);
            this.drawBox(n, -125, 15, 15);
            this.fillBox(n + 1, -126, 13, 14, this.answer[i]);
        }
        this.gameOver = true;
    }
    
    void gameWon() {
        this.tgObj.penup();
        this.tgObj.setxy(0, -150);
        this.tgObj.setfontsize(24);
        this.tgObj.label("You Win");
        this.gameOver = true;
    }
    
    void initialize() {
        this.tgObj.hideturtle();
        this.drawColorChoices();
        this.drawDupsCheckbox();
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
            int n = MMapplet.colorChoices[(int)(Math.random() * MMapplet.colorChoices.length)];
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
        final int n2 = 150 - this.guessNumber * 25;
        this.tgObj.penup();
        this.tgObj.setxy(100, n2 - 20);
        this.tgObj.pendown();
        this.tgObj.setfontsize(24);
        this.tgObj.setpencolor(0);
        this.tgObj.label(Integer.toString(n));
    }
    
    void showInexactMatches(final int n) {
        final int n2 = 150 - this.guessNumber * 25;
        this.tgObj.penup();
        this.tgObj.setxy(120, n2 - 20);
        this.tgObj.pendown();
        this.tgObj.setfontsize(24);
        this.tgObj.setpencolor(15);
        this.tgObj.label(Integer.toString(n));
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
        if (n < -130 || n > -45) {
            return false;
        }
        if (n2 > -65 || n2 < -90) {
            return false;
        }
        if (this.choiceNumber > 0) {
            --this.choiceNumber;
            n = -15 + this.choiceNumber * 25;
            n2 = 150 - this.guessNumber * 25;
            this.fillBox(n + 1, n2 - 1, 18, 19, 7);
        }
        return true;
    }
    
    boolean tryColorChoice(int n, int n2) {
        if (n < -100 || n >= -75) {
            return false;
        }
        if (n2 > 150 || n2 < 1) {
            return false;
        }
        if (this.choiceNumber == 4) {
            return true;
        }
        final int n3 = MMapplet.colorChoices[Math.abs(n2 - 150) / 25];
        n = -15 + this.choiceNumber * 25;
        n2 = 150 - this.guessNumber * 25;
        this.fillBox(n + 1, n2 - 1, 18, 19, n3);
        this.guessChoices[this.choiceNumber] = n3;
        ++this.choiceNumber;
        return true;
    }
    
    boolean tryGuess(final int n, final int n2) {
        if (n < -130 || n > -45) {
            return false;
        }
        if (n2 > -95 || n2 < -120) {
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
        if (n < -130 || n > -45) {
            return false;
        }
        if (n2 > -125 || n2 < -150) {
            return false;
        }
        this.tgObj.clean();
        this.initialize();
        return true;
    }
    
    public void myMouseClickHandler(final int n, final int n2) {
        if (this.tryAllowDupsBox(n, n2)) {
            return;
        }
        if (this.tryNewGame(n, n2)) {
            return;
        }
        if (this.gameOver) {
            return;
        }
        if (this.tryClearColor(n, n2)) {
            return;
        }
        if (this.tryGuess(n, n2)) {
            return;
        }
        this.tryColorChoice(n, n2);
    }
    
    public void init() {
        this.setLayout(new BorderLayout());
        (this.tgObj = new TurtleGraphics(400, 400)).setBackground(Color.white);
        this.add("Center", this.tgObj);
        this.tgObj.addMouseListener(this);
        this.initialize();
    }
    
    static {
        colorChoices = new int[] { 4, 14, 6, 2, 1, 13 };
    }
}
