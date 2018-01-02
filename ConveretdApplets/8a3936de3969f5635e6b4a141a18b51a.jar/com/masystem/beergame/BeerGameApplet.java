// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame;

import javax.swing.RootPaneContainer;
import java.awt.Container;
import com.masystem.beergame.ui.scene.Scene;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.html.parser.ParserDelegator;
import javax.swing.JApplet;

public class BeerGameApplet extends JApplet
{
    private static final long serialVersionUID = 1L;
    
    @Override
    public void init() {
        new ParserDelegator();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public final void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                BeerGameApplet.access$000(BeerGameApplet.this, new BeerGame());
            }
        });
    }
    
    @Override
    public void start() {
    }
    
    @Override
    public void stop() {
        final Thread thread;
        (thread = new Thread(new Runnable(this) {
            @Override
            public final void run() {
                ConnectionManager.getConnection().destroy();
            }
        })).setName("DisconnectThread");
        thread.start();
    }
    
    @Override
    public void destroy() {
        ConnectionManager.getConnection().destroy();
    }
    
    static /* synthetic */ void access$000(BeerGameApplet beerGameApplet, BeerGame beerGame) {
        final BeerGameApplet beerGameApplet2 = beerGameApplet;
        beerGame = beerGame;
        beerGameApplet = beerGameApplet2;
        BeerGame.setup();
        final String host;
        if ((host = beerGameApplet.getCodeBase().getHost()) != null && host.startsWith("http")) {
            ConnectionManager.getProperties().setProperty("network.host", host);
        }
        beerGame.start();
        beerGameApplet.setContentPane(Scene.getCurrentScene().getComponent());
        MouseBlocker.init(beerGameApplet);
        beerGameApplet.setVisible(true);
    }
}
