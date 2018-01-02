// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame;

import com.masystem.beergame.ui.pages.lobby.QuickStartPage;
import com.masystem.beergame.ui.beergamecomponents.BeerGamePage;
import com.masystem.beergame.ui.pages.lobby.StartPage;
import com.masystem.beergame.ui.graphics.GraphicsTools;
import com.masystem.beergame.ui.scene.Scene;
import java.awt.Font;
import com.masystem.beergame.network.protocol.Player;
import com.masystem.beergame.network.protocol.Costs;
import com.masystem.beergame.resource.ResourceManager;
import com.masystem.beergame.resource.Properties;

public final class BeerGame
{
    private String gameName;
    private String playerName;
    private String playerEmail;
    private String playerType;
    
    public BeerGame() {
        this(null, null, null, null);
    }
    
    public BeerGame(final String gameName, final String playerName, final String playerEmail, final String playerType) {
        this.gameName = gameName;
        this.playerName = playerName;
        this.playerEmail = playerEmail;
        this.playerType = playerType;
    }
    
    public static void setup() {
        ResourceManager.addResource("Properties", new Properties("beergame.properties"));
        Costs.init(ConnectionManager.getProperties());
        final Player player = new Player();
        ResourceManager.addResource("Player", player);
        ResourceManager.addResource("ConnectionManager", new ConnectionManager(player));
        final Font font = new Font("Default", 1, 14);
        ResourceManager.addResource("DefaultFont", font);
        ResourceManager.addResource("BigFont", font.deriveFont(1, (int)(font.getSize() * 1.2f / 2.0f) << 1));
        ResourceManager.addResource("SmallFont", font.deriveFont(0, font.getSize()));
        ResourceManager.addResource("InGameBigFont", font.deriveFont(1, (int)(font.getSize() * 1.4f / 2.0f) << 1));
        ResourceManager.addResource("InGameReportFont", font.deriveFont(0, 12.0f));
        ResourceManager.addResource("InGameOrderFont", font.deriveFont(2, (int)(font.getSize() * 2.0f / 2.0f) << 1));
        ResourceManager.addResource("OverlayBigFont", font.deriveFont(1, (int)(font.getSize() * 3.0f / 2.0f) << 1));
        ResourceManager.addResource("OverlaySmallFont", font.deriveFont(1, (int)(font.getSize() * 1.7f / 2.0f) << 1));
        final Scene scene;
        Scene.setCurrentScene(scene = new Scene(800, 600));
        GraphicsTools.allocateScratch((int)(2.0f * scene.getWidth() / 8.0f * scene.getHeight() / 8.0f));
    }
    
    public final void start() {
        if (this.playerName == null) {
            BeerGamePage.openPage$2a8e827b(new StartPage());
            return;
        }
        BeerGamePage.openPage$2a8e827b(new QuickStartPage(this.gameName, this.playerName, this.playerEmail, this.playerType));
    }
}
