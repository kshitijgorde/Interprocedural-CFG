// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.pages.lobby;

import com.masystem.beergame.network.protocol.Game;
import com.masystem.beergame.network.protocol.Response;
import com.masystem.beergame.network.ResponseListener;
import com.masystem.beergame.network.protocol.Status;
import com.masystem.beergame.network.protocol.Command;
import com.masystem.beergame.ui.overlays.ConnectingOverlay;
import com.masystem.beergame.ui.pages.game.GamePage;
import com.masystem.beergame.ConnectionManager;
import com.masystem.beergame.ui.graphics.StretchableImage;
import com.masystem.beergame.ui.beergamecomponents.BeerGameTextButton;
import com.masystem.beergame.ui.beergamecomponents.BeerGameDropDown;
import com.masystem.beergame.network.protocol.Player;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.masystem.beergame.ui.beergamecomponents.BeerGameLoginTextField;
import com.masystem.beergame.ui.beergamecomponents.BeerGameTextLabel;
import com.masystem.beergame.ui.component.Panel;
import com.masystem.beergame.ui.scene.Node;
import com.masystem.beergame.ui.beergamecomponents.BeerGameImage;
import com.masystem.beergame.ui.beergamecomponents.BeerGamePage;

public final class NewGamePage extends BeerGamePage
{
    @Override
    public final void onSetup() {
        this.addChild(new BeerGameImage("login_bg.png"));
        final Panel panel = new Panel();
        this.addChild(panel);
        final BeerGameTextLabel beerGameTextLabel = new BeerGameTextLabel("Your name:");
        panel.addChild(beerGameTextLabel);
        final BeerGameLoginTextField beerGameLoginTextField;
        (beerGameLoginTextField = new BeerGameLoginTextField()).setId("userNameField");
        panel.addChild(beerGameLoginTextField);
        beerGameLoginTextField.setSizeRelativeToParent(0.25f, -1.0f);
        beerGameLoginTextField.addActionListener(new ActionListener() {
            @Override
            public final void actionPerformed(final ActionEvent actionEvent) {
                NewGamePage.this.findById("gameNameField").getComponent().requestFocusInWindow();
            }
        });
        final BeerGameTextLabel beerGameTextLabel2 = new BeerGameTextLabel("Game name:");
        panel.addChild(beerGameTextLabel2);
        final BeerGameLoginTextField beerGameLoginTextField2;
        (beerGameLoginTextField2 = new BeerGameLoginTextField()).setId("gameNameField");
        panel.addChild(beerGameLoginTextField2);
        beerGameLoginTextField2.setSizeRelativeTo(beerGameLoginTextField, 1.0f, -1.0f);
        beerGameLoginTextField2.addActionListener(new ActionListener() {
            @Override
            public final void actionPerformed(final ActionEvent actionEvent) {
                NewGamePage.this.findById("playerTypeDropDown").getComponent().requestFocusInWindow();
            }
        });
        final BeerGameTextLabel beerGameTextLabel3 = new BeerGameTextLabel("Position:");
        panel.addChild(beerGameTextLabel3);
        final Player.Type[] values;
        final String[] array = new String[(values = Player.Type.values()).length];
        for (int i = 0; i < values.length; ++i) {
            array[i] = values[i].toString();
        }
        final BeerGameDropDown beerGameDropDown;
        (beerGameDropDown = new BeerGameDropDown(array)).setId("playerTypeDropDown");
        panel.addChild(beerGameDropDown);
        beerGameDropDown.setSizeRelativeTo(beerGameLoginTextField, 1.0f, 1.0f);
        final BeerGameTextButton beerGameTextButton = new BeerGameTextButton("CREATE GAME");
        panel.addChild(beerGameTextButton);
        beerGameTextButton.addActionListener(new ActionListener() {
            @Override
            public final void actionPerformed(final ActionEvent actionEvent) {
                NewGamePage.access$000(NewGamePage.this);
            }
        });
        final BeerGameTextButton beerGameTextButton2;
        (beerGameTextButton2 = new BeerGameTextButton("BACK")).setId("BackButton");
        this.addChild(beerGameTextButton2);
        beerGameTextButton2.addActionListener(new ActionListener() {
            @Override
            public final void actionPerformed(final ActionEvent actionEvent) {
                NewGamePage.this.gotoPreviousPage(new StartPage());
            }
        });
        StretchableImage.vertical(beerGameLoginTextField2, 17, this, 17);
        StretchableImage.horizontal(beerGameLoginTextField2, 17, this, 17, this, 0.03f);
        StretchableImage.vertical(beerGameTextLabel2, 17, beerGameLoginTextField2, 17);
        StretchableImage.horizontal(beerGameTextLabel2, 2, beerGameLoginTextField2, 1);
        StretchableImage.vertical(beerGameLoginTextField, 32, beerGameLoginTextField2, 16, beerGameLoginTextField, 0.2f);
        StretchableImage.horizontal(beerGameLoginTextField, 17, beerGameLoginTextField2, 17);
        StretchableImage.vertical(beerGameTextLabel, 17, beerGameLoginTextField, 17);
        StretchableImage.horizontal(beerGameTextLabel, 2, beerGameLoginTextField, 1);
        StretchableImage.vertical(beerGameDropDown, 16, beerGameLoginTextField2, 32, beerGameDropDown, -0.2f);
        StretchableImage.horizontal(beerGameDropDown, 17, beerGameLoginTextField2, 17);
        StretchableImage.vertical(beerGameTextLabel3, 17, beerGameDropDown, 17);
        StretchableImage.horizontal(beerGameTextLabel3, 2, beerGameDropDown, 1);
        StretchableImage.vertical(beerGameTextButton, 17, this, 17, 1.0f);
        StretchableImage.horizontal(beerGameTextButton, 17, this, 17, this, 0.24f);
        StretchableImage.vertical(beerGameTextButton2, 32, this, 32, this, -0.05f);
        StretchableImage.horizontal(beerGameTextButton2, 2, this, 2, this, -0.04f);
        panel.setPositionRelativeToParent(0.5f, 0.495f);
    }
    
    @Override
    public final void onTransitionEnd$6db0a1c1() {
        this.findById("userNameField").getComponent().requestFocusInWindow();
    }
    
    private static String trimToLength(final String s, final int n) {
        if (s.length() > 30) {
            return s.substring(0, 30).trim();
        }
        return s;
    }
    
    static /* synthetic */ void access$000(NewGamePage newGamePage) {
        newGamePage = newGamePage;
        final Player player = ConnectionManager.getPlayer();
        final String trimToLength = trimToLength(((BeerGameLoginTextField)newGamePage.findById("userNameField")).getText().trim(), 30);
        final String trimToLength2 = trimToLength(((BeerGameLoginTextField)newGamePage.findById("gameNameField")).getText().trim(), 30);
        final String selectedItem = ((BeerGameDropDown)newGamePage.findById("playerTypeDropDown")).getSelectedItem();
        player.setName(trimToLength);
        player.setType(selectedItem);
        final ConnectingOverlay connectingOverlay;
        (connectingOverlay = new ConnectingOverlay("Connecting", "Please wait...", true) {
            private boolean cannotConnect;
            private /* synthetic */ GamePage val$gamePage = new GamePage(player);
            
            @Override
            protected final void onPerform() {
                if (!NewGamePage.access$100(trimToLength)) {
                    this.throwResponse(Command.SESSION_LOGIN, Status.ERROR_PLAYER_INVALID_NAME, new Object[0]);
                    return;
                }
                if (!NewGamePage.access$200(trimToLength2)) {
                    this.throwResponse(Command.SESSION_LOGIN, Status.ERROR_GAME_INVALID_NAME, new Object[0]);
                    return;
                }
                ConnectionManager.getConnection().createGame(trimToLength2, this.getResponseHandler());
            }
            
            @Override
            protected final void onResponse(final Response response) {
                final Command command = response.getCommand();
                final Status status = response.getStatus();
                if (command == Command.GAME_CREATE && status == Status.OK) {
                    ConnectionManager.getConnection().joinGame(trimToLength2, Player.Type.fromString(selectedItem), this.getResponseHandler());
                }
            }
            
            @Override
            protected final void onFinish(final Response response) {
                final Status status;
                if ((status = response.getStatus()) == Status.OK) {
                    this.val$gamePage.setGame((Game)response.getArgument());
                    this.val$gamePage.setAdmin(true);
                    NewGamePage.this.gotoNextPage(this.val$gamePage);
                    return;
                }
                if (status == Status.ERROR_CONNECTION_FAILED) {
                    this.cannotConnect = true;
                    return;
                }
                ConnectionManager.getConnection().logout(null);
            }
            
            @Override
            protected final void onClosed() {
                this.getParent().removeChild(this);
                if (this.cannotConnect) {
                    NewGamePage.this.gotoPreviousPage(new StartPage());
                }
            }
        }).setId("overlay");
        newGamePage.addChild(connectingOverlay);
        connectingOverlay.open();
    }
    
    static /* synthetic */ boolean access$100(String s) {
        return (s = s) != null && s.length() > 0;
    }
    
    static /* synthetic */ boolean access$200(String s) {
        return (s = s) != null && s.length() > 0;
    }
}
