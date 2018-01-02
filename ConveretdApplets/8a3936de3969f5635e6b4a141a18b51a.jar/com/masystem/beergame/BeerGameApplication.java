// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.RootPaneContainer;
import java.awt.Container;
import com.masystem.beergame.ui.scene.Scene;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JFrame;

public class BeerGameApplication extends JFrame
{
    private static final long serialVersionUID = 1L;
    
    public static void main(final String[] array) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public final void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                BeerGame beerGame;
                if (array != null && array.length != 0) {
                    beerGame = new BeerGame(BeerGameApplication.getArgument(0, array), BeerGameApplication.getArgument(1, array), BeerGameApplication.getArgument(2, array), BeerGameApplication.getArgument(3, array));
                }
                else {
                    beerGame = new BeerGame();
                }
                new BeerGameApplication("MA-system BeerGame", beerGame);
            }
        });
    }
    
    protected static String getArgument(final int n, final String[] array) {
        if (array != null && array.length > n) {
            return array[n];
        }
        return null;
    }
    
    public BeerGameApplication(final String s, BeerGame beerGame) {
        super(s);
        beerGame = beerGame;
        BeerGame.setup();
        beerGame.start();
        this.setResizable(false);
        this.setDefaultCloseOperation(2);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public final void windowClosed(final WindowEvent windowEvent) {
                final Thread thread;
                (thread = new Thread(new Runnable(this) {
                    @Override
                    public final void run() {
                        ConnectionManager.getConnection().destroy();
                    }
                })).setName("DisconnectThread");
                final Thread thread2;
                (thread2 = new Thread(new Runnable(this) {
                    @Override
                    public final void run() {
                        try {
                            Thread.sleep(150000000L);
                        }
                        catch (InterruptedException ex) {}
                        System.exit(0);
                    }
                })).setName("BackupExitThread");
                thread2.setDaemon(true);
                thread.start();
                thread2.start();
            }
        });
        this.setContentPane(Scene.getCurrentScene().getComponent());
        this.pack();
        MouseBlocker.init(this);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - this.getWidth()) / 2, (screenSize.height - this.getHeight()) / 2);
        this.setVisible(true);
    }
}
