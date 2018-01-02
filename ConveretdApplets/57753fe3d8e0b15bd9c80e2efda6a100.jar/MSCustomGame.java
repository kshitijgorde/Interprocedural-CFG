import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Choice;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class MSCustomGame extends Panel implements MenuListener
{
    private static final int WIDTH = 320;
    private static final int HEIGHT = 320;
    private Image gbuffer;
    private Graphics gbuf;
    private MoonStar moonStar;
    private Image background;
    private MSButton button;
    private Choice[] choices;
    
    public MSCustomGame(final MoonStar moonStar, final String s, final Image background) {
        this.choices = new Choice[5];
        this.moonStar = moonStar;
        this.background = background;
        this.setLayout(null);
        this.setBackground(moonStar.colors[0]);
        (this.button = new MSButton(this, 0, s)).setBounds(105, 280, 110, 20);
        this.add(this.button);
        for (int i = 0; i < this.choices.length; ++i) {
            this.choices[i] = new Choice();
        }
        for (int j = 1; j <= 10; ++j) {
            this.choices[0].addItem(Integer.toString(j));
        }
        this.choices[0].select(6);
        for (int k = 3; k <= 7; ++k) {
            this.choices[1].addItem(Integer.toString(k));
        }
        this.choices[1].select(1);
        for (int l = 3; l <= 10; ++l) {
            this.choices[2].addItem(Integer.toString(l));
        }
        this.choices[2].select(2);
        for (int n = 5; n <= 15; ++n) {
            this.choices[3].addItem(Integer.toString(n));
            this.choices[4].addItem(Integer.toString(n));
        }
        this.choices[3].select(5);
        this.choices[4].select(10);
        for (int n2 = 0; n2 < 3; ++n2) {
            this.choices[n2].setBounds(135, 60 + n2 * 60, 50, 20);
            this.add(this.choices[n2]);
        }
        this.choices[3].setBounds(105, 240, 50, 20);
        this.add(this.choices[3]);
        this.choices[4].setBounds(165, 240, 50, 20);
        this.add(this.choices[4]);
    }
    
    public void add() {
        this.moonStar.add(this);
        this.repaint();
        this.button.repaint();
    }
    
    public void remove() {
        this.moonStar.remove(this);
    }
    
    public boolean actionPerformed(final int n) {
        this.moonStar.mSMenu.setGameType(this.moonStar.mSNewGame.buttonNames[2], 1);
        this.moonStar.mSController = new MSCustom(this.moonStar, Integer.parseInt(this.choices[3].getSelectedItem()), Integer.parseInt(this.choices[4].getSelectedItem()), (12 - Integer.parseInt(this.choices[0].getSelectedItem())) * 50, Integer.parseInt(this.choices[1].getSelectedItem()), Integer.parseInt(this.choices[2].getSelectedItem()));
        return true;
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        graphics.drawImage(this.background, 0, 0, 320, 320, this);
    }
}
