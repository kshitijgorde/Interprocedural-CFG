// 
// Decompiled by Procyon v0.5.30
// 

package org.bm.bmtron;

import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.Container;

public class SettingsScreen extends Container implements ActionListener, Runnable
{
    Game game;
    PlayerSetup[] playerSetups;
    Button startGame;
    final Color[] colors;
    Image[] colorImages;
    
    SettingsScreen(final Game game) {
        this.playerSetups = new PlayerSetup[] { null, null, null, null };
        this.colors = new Color[] { new Color(255, 0, 0), new Color(0, 0, 255), new Color(0, 255, 0), new Color(255, 255, 0), new Color(255, 0, 255), new Color(0, 255, 255), new Color(255, 255, 255), new Color(127, 127, 255), new Color(127, 255, 127), new Color(255, 127, 127) };
        this.colorImages = new Image[this.colors.length];
        this.game = game;
        this.createColorImages();
        this.setLayout(null);
        (this.playerSetups[0] = new PlayerSetup(this, true, 5, 0, "Player 1")).setLocation(10, 58);
        (this.playerSetups[1] = new PlayerSetup(this, true, 3, 1, "Player 2")).setLocation(10, 118);
        (this.playerSetups[2] = new PlayerSetup(this, false, 4, 2, "Player 3")).setLocation(10, 178);
        (this.playerSetups[3] = new PlayerSetup(this, false, 6, 3, "Player 4")).setLocation(10, 238);
        (this.startGame = new Button(game.skin.start, game)).setLocation(164, 298);
        this.startGame.addActionListener(this);
        this.add(this.playerSetups[0]);
        this.add(this.playerSetups[1]);
        this.add(this.playerSetups[2]);
        this.add(this.playerSetups[3]);
        this.add(this.startGame);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.setVisible(false);
        this.game.setStatus("Loading...", "wait, please", true);
        new Thread(this).start();
    }
    
    public void run() {
        int n = 0;
        for (int i = 0; i <= this.playerSetups.length - 1; ++i) {
            if (this.playerSetups[i].isPlayerEnabled()) {
                ++n;
            }
        }
        final Player[] array = new Player[n];
        int n2 = 0;
        for (int j = 0; j <= this.playerSetups.length - 1; ++j) {
            if (this.playerSetups[j].isPlayerEnabled()) {
                array[n2] = this.playerSetups[j].getPlayer();
                ++n2;
            }
        }
        this.game.field.setVisible(true);
        this.game.field.requestFocus();
        this.game.field.start(array);
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.game.skin.settings.getWidth(this);
        final int height = this.game.skin.settings.getHeight(this);
        for (int i = 0; i < this.getSize().width; i += width) {
            for (int j = 0; j < this.getSize().height; j += height) {
                graphics.drawImage(this.game.skin.settings, i, j, this);
            }
        }
        super.paint(graphics);
    }
    
    private void createColorImages() {
        final MediaTracker mediaTracker = new MediaTracker(this.game);
        for (int i = 0; i <= this.colorImages.length - 1; ++i) {
            mediaTracker.addImage(this.colorImages[i] = this.game.createImage(new FilteredImageSource(this.game.skin.colorPreview.getSource(), new PlayerColorFilter(this.colors[i]))).getScaledInstance(22, 22, 2), 0);
        }
        try {
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
    }
    
    public boolean isOpaque() {
        return true;
    }
}
