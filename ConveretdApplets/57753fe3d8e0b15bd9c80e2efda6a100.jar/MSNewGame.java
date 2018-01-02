import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class MSNewGame extends Panel implements MenuListener
{
    private static final int WIDTH = 320;
    private static final int HEIGHT = 320;
    private MoonStar moonStar;
    private Image background;
    private MSButton[] buttons;
    private int state;
    public String[] buttonNames;
    
    public MSNewGame(final MoonStar moonStar, final String[] buttonNames, final Image background) {
        this.state = 0;
        this.moonStar = moonStar;
        this.background = background;
        this.buttonNames = buttonNames;
        this.setLayout(null);
        this.setBackground(moonStar.colors[0]);
        this.buttons = new MSButton[buttonNames.length];
        for (int i = 0; i < buttonNames.length; ++i) {
            (this.buttons[i] = new MSButton(this, i, buttonNames[i])).setBounds(10, 10 + i * 105, 110, 20);
            this.add(this.buttons[i]);
        }
    }
    
    public void add() {
        this.moonStar.add(this);
        this.repaint();
        for (int i = 0; i < this.buttons.length; ++i) {
            this.buttons[i].repaint();
        }
    }
    
    public void remove() {
        this.moonStar.remove(this);
    }
    
    public boolean actionPerformed(final int n) {
        switch (n) {
            case 0: {
                this.moonStar.mSMenu.setGameType(this.buttonNames[1], 1);
                this.moonStar.mSController = new MSArcade(this.moonStar);
                return true;
            }
            case 1: {
                this.moonStar.mSMenu.setGameType(this.buttonNames[0], 0);
                this.moonStar.mSController = this.moonStar.mSGame.startCampaign();
                return true;
            }
            case 2: {
                this.moonStar.mSMenu.actionPerformed(7);
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        graphics.drawImage(this.background, 0, 0, 320, 320, this);
    }
}
