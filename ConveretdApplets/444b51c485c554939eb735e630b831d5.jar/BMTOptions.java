import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.Container;

// 
// Decompiled by Procyon v0.5.30
// 

public class BMTOptions extends Container implements ActionListener, Runnable
{
    BMTron bmt;
    BMTPlayerSetup[] playerSetups;
    BMTButton startGame;
    final Color[] colors;
    Image[] colorImages;
    BMTField field;
    
    BMTOptions(final BMTron bmt) {
        this.playerSetups = new BMTPlayerSetup[] { null, null, null, null };
        this.colors = new Color[] { new Color(255, 0, 0), new Color(0, 0, 255), new Color(0, 255, 0), new Color(255, 255, 0), new Color(255, 0, 255), new Color(0, 255, 255), new Color(255, 255, 255) };
        this.colorImages = new Image[this.colors.length];
        this.bmt = bmt;
        this.createColorImages();
        this.setLayout(null);
        (this.playerSetups[0] = new BMTPlayerSetup(this, true, 5, 0, "Player 1")).setLocation(10, 58);
        (this.playerSetups[1] = new BMTPlayerSetup(this, true, 3, 1, "Player 2")).setLocation(10, 118);
        (this.playerSetups[2] = new BMTPlayerSetup(this, false, 4, 2, "Player 3")).setLocation(10, 178);
        (this.playerSetups[3] = new BMTPlayerSetup(this, false, 5, 3, "Player 4")).setLocation(10, 238);
        (this.startGame = new BMTButton(bmt.skin.start)).setLocation(164, 298);
        this.startGame.addActionListener(this);
        this.add(this.playerSetups[0]);
        this.add(this.playerSetups[1]);
        this.add(this.playerSetups[2]);
        this.add(this.playerSetups[3]);
        this.add(this.startGame);
        (this.field = new BMTField(this)).setVisible(false);
        this.field.setLocation(10, 40);
        this.field.setSize(500, 350);
        bmt.add(this.field);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.setVisible(false);
        this.bmt.setStatus("Loading...", "wait, please", true);
        new Thread(this).start();
    }
    
    public void run() {
        int n = 0;
        for (int i = 0; i <= this.playerSetups.length - 1; ++i) {
            if (this.playerSetups[i].isPlayerEnabled()) {
                ++n;
            }
        }
        final BMTPlayer[] array = new BMTPlayer[n];
        int n2 = 0;
        for (int j = 0; j <= this.playerSetups.length - 1; ++j) {
            if (this.playerSetups[j].isPlayerEnabled()) {
                array[n2] = this.playerSetups[j].getPlayer();
                ++n2;
            }
        }
        this.field.setVisible(true);
        this.field.start(array);
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.bmt.skin.options.getWidth(this);
        final int height = this.bmt.skin.options.getHeight(this);
        for (int i = 0; i < this.getSize().width; i += width) {
            for (int j = 0; j < this.getSize().height; j += height) {
                graphics.drawImage(this.bmt.skin.options, i, j, this);
            }
        }
        super.paint(graphics);
    }
    
    private void createColorImages() {
        for (int i = 0; i <= this.colorImages.length - 1; ++i) {
            this.colorImages[i] = this.bmt.createImage(22, 22);
            final Graphics graphics = this.colorImages[i].getGraphics();
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, 22, 22);
            BMTron.setAA(graphics, false);
            graphics.setColor(this.colors[i]);
            graphics.fillOval(2, 2, 17, 17);
            graphics.setColor(Color.blue);
            graphics.drawOval(2, 2, 17, 17);
        }
    }
    
    public boolean isOpaque() {
        return true;
    }
}
