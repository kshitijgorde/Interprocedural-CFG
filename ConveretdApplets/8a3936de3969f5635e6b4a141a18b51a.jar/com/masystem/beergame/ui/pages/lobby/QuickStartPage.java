// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.pages.lobby;

import com.masystem.beergame.network.protocol.Game;
import com.masystem.beergame.network.protocol.Response;
import com.masystem.beergame.network.ResponseListener;
import com.masystem.beergame.network.protocol.Player;
import com.masystem.beergame.network.protocol.Status;
import com.masystem.beergame.network.protocol.Command;
import com.masystem.beergame.ui.overlays.ConnectingOverlay;
import com.masystem.beergame.ui.pages.game.GamePage;
import com.masystem.beergame.ConnectionManager;
import com.masystem.beergame.ui.scene.Node;
import com.masystem.beergame.ui.beergamecomponents.BeerGameImage;
import com.masystem.beergame.ui.beergamecomponents.BeerGamePage;

public final class QuickStartPage extends BeerGamePage
{
    private final String gameName;
    private final String playerName;
    private final String playerEmail;
    private final String playerType;
    
    public QuickStartPage(final String gameName, final String playerName, final String playerEmail, final String playerType) {
        this.gameName = gameName;
        this.playerName = playerName;
        this.playerEmail = playerEmail;
        this.playerType = playerType;
    }
    
    @Override
    public final void onSetup() {
        this.addChild(new BeerGameImage("login_bg.png"));
    }
    
    @Override
    public final void onTransitionEnd$6db0a1c1() {
        final Player player;
        (player = ConnectionManager.getPlayer()).setName(this.playerName.trim());
        player.setEmail(this.playerEmail.trim());
        player.setType(this.playerType.trim());
        final ConnectingOverlay connectingOverlay;
        (connectingOverlay = new ConnectingOverlay("Connecting", "Please wait...", false) {
            private boolean isAdmin;
            private /* synthetic */ GamePage val$gamePage = new GamePage(player);
            
            @Override
            protected final void onPerform() {
                if (!QuickStartPage.access$100(QuickStartPage.this, QuickStartPage.this.playerName)) {
                    this.throwResponse(Command.SESSION_LOGIN, Status.ERROR_PLAYER_INVALID_NAME, new Object[0]);
                    return;
                }
                if (!QuickStartPage.access$300(QuickStartPage.this, QuickStartPage.this.gameName)) {
                    this.throwResponse(Command.GAME_JOIN, Status.ERROR_GAME_INVALID_NAME, new Object[0]);
                    return;
                }
                if (Player.Type.fromString(QuickStartPage.this.playerType) == null) {
                    this.throwResponse(Command.GAME_JOIN, Status.ERROR_PLAYER_INVALID_TYPE, new Object[0]);
                    return;
                }
                ConnectionManager.getConnection().joinGame(QuickStartPage.this.gameName, Player.Type.fromString(QuickStartPage.this.playerType), this.getResponseHandler());
            }
            
            @Override
            protected final void onResponse(final Response response) {
                final Command command = response.getCommand();
                final Status status = response.getStatus();
                if (command == Command.GAME_JOIN) {
                    if (status == Status.OK) {
                        this.finish(response);
                        return;
                    }
                    if (status == Status.ERROR_GAME_DOES_NOT_EXIST) {
                        ConnectionManager.getConnection().createGame(QuickStartPage.this.gameName, this.getResponseHandler());
                        return;
                    }
                    this.finish(response);
                }
                else {
                    if (command != Command.GAME_CREATE) {
                        if (status != Status.OK) {
                            this.finish(response);
                        }
                        return;
                    }
                    if (status == Status.OK) {
                        this.isAdmin = true;
                        ConnectionManager.getConnection().joinGame(QuickStartPage.this.gameName, Player.Type.fromString(QuickStartPage.this.playerType), this.getResponseHandler());
                        return;
                    }
                    this.finish(response);
                }
            }
            
            @Override
            protected final void onFinish(final Response response) {
                if (response.getStatus() == Status.OK) {
                    this.val$gamePage.setGame((Game)response.getArgument());
                    this.val$gamePage.setAdmin(this.isAdmin);
                    QuickStartPage.this.gotoNextPage(this.val$gamePage);
                }
            }
            
            @Override
            protected final void onClosed() {
                this.getParent().removeChild(this);
                QuickStartPage.this.gotoPreviousPage(new StartPage());
            }
        }).setId("overlay");
        this.addChild(connectingOverlay);
        connectingOverlay.open();
    }
    
    static /* synthetic */ boolean access$100(final QuickStartPage quickStartPage, final String s) {
        return s != null && s.length() > 0;
    }
    
    static /* synthetic */ boolean access$300(final QuickStartPage quickStartPage, final String s) {
        return s != null && s.length() > 0;
    }
}
