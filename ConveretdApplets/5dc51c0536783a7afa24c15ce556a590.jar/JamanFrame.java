import java.awt.Event;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Button;
import java.awt.Label;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class JamanFrame extends Frame
{
    public Game theGame;
    public static Label copyrightLabel;
    private Button newButton;
    private Button pauseButton;
    private Button quitButton;
    private Font infoFont;
    private Font cprFont;
    private Button hideButton;
    
    public JamanFrame() {
        this.theGame = new Game();
        this.newButton = new Button("New");
        this.pauseButton = new Button("Pause");
        this.quitButton = new Button("Hide");
        this.infoFont = new Font("SanSerif", 1, 14);
        this.cprFont = new Font("Serif", 2, 10);
        this.setTitle("JAMAN - version Beta");
        JamanFrame.copyrightLabel.setFont(this.cprFont);
        this.theGame.scoreLabel.setFont(this.infoFont);
        this.theGame.highScoreLabel.setFont(this.infoFont);
        this.theGame.levelLabel.setFont(this.infoFont);
        this.theGame.numJamanLabel.setFont(this.infoFont);
        final Panel panel = new Panel(new GridLayout(2, 2));
        panel.add(this.theGame.scoreLabel);
        panel.add(this.theGame.highScoreLabel);
        panel.add(this.theGame.levelLabel);
        panel.add(this.theGame.numJamanLabel);
        final Panel panel2 = new Panel(new GridLayout(2, 3));
        panel2.add(this.newButton);
        panel2.add(this.pauseButton);
        panel2.add(this.quitButton);
        panel2.add(this.theGame.mapChoice);
        panel2.add(this.theGame.numJamanChoice);
        panel2.add(this.theGame.soundCheckbox);
        final Panel panel3 = new Panel(new GridLayout(1, 2, 5, 5));
        panel3.add(panel);
        panel3.add(panel2);
        final Panel panel4 = new Panel(new BorderLayout());
        panel4.add("Center", panel3);
        panel4.add("South", JamanFrame.copyrightLabel);
        this.setLayout(new BorderLayout());
        this.add("South", panel4);
        this.add("Center", this.theGame);
    }
    
    public void init(final Button hideButton) {
        this.theGame.start();
        this.hideButton = hideButton;
    }
    
    public boolean action(final Event event, final Object o) {
        if (o.equals("New")) {
            this.theGame.newGame();
            return true;
        }
        if (o.equals("Pause")) {
            this.theGame.pauseGame = true;
            this.pauseButton.setLabel("Resume");
            return true;
        }
        if (o.equals("Resume")) {
            this.theGame.pauseGame = false;
            this.pauseButton.setLabel("Pause");
            return true;
        }
        if (o.equals("Hide")) {
            this.theGame.pauseGame = true;
            this.pauseButton.setLabel("Resume");
            this.hideButton.setLabel("Play Game");
            this.hide();
            this.theGame.setHideGame(true);
            return true;
        }
        if (event.target == this.theGame.soundCheckbox) {
            this.theGame.setSound();
        }
        return false;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.key == 1007) {
            this.theGame.KeyRight();
            this.pauseButton.setLabel("Pause");
        }
        else if (event.key == 1006) {
            this.theGame.KeyLeft();
            this.pauseButton.setLabel("Pause");
        }
        else if (event.key == 1004) {
            this.theGame.KeyUp();
            this.pauseButton.setLabel("Pause");
        }
        else if (event.key == 1005) {
            this.theGame.KeyDown();
            this.pauseButton.setLabel("Pause");
        }
        if (event.id == 201) {
            System.exit(0);
        }
        return super.handleEvent(event);
    }
    
    static {
        JamanFrame.copyrightLabel = new Label(" Jaman, by Pham Hong Nguyen, 2000 - http://www.geocities.com/SiliconValley/grid/6544");
    }
}
