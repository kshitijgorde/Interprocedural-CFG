// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.pages.lobby;

import com.masystem.beergame.network.protocol.Game;
import com.masystem.beergame.ui.pages.game.GamePage;
import java.util.Iterator;
import java.util.Collection;
import com.masystem.beergame.network.protocol.Command;
import com.masystem.beergame.network.protocol.Request;
import java.util.TimerTask;
import com.masystem.beergame.network.protocol.Status;
import com.masystem.beergame.network.protocol.Response;
import com.masystem.beergame.network.ResponseListener;
import com.masystem.beergame.ConnectionManager;
import com.masystem.beergame.ui.overlays.ConnectingOverlay;
import com.masystem.beergame.ui.graphics.StretchableImage;
import com.masystem.beergame.ui.beergamecomponents.BeerGameTextButton;
import com.masystem.beergame.network.protocol.Player;
import com.masystem.beergame.ui.beergamecomponents.BeerGameDropDown;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.masystem.beergame.ui.beergamecomponents.BeerGameLoginTextField;
import com.masystem.beergame.ui.beergamecomponents.BeerGameTextLabel;
import com.masystem.beergame.ui.component.Panel;
import com.masystem.beergame.ui.scene.Node;
import com.masystem.beergame.ui.beergamecomponents.BeerGameImage;
import java.util.Timer;
import com.masystem.beergame.ui.beergamecomponents.BeerGamePage;

public final class JoinGamePage extends BeerGamePage
{
    protected Timer timer;
    
    @Override
    public final void onSetup() {
        this.timer = new Timer(true);
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
                JoinGamePage.this.findById("gameDropDown").getComponent().requestFocusInWindow();
            }
        });
        final BeerGameTextLabel beerGameTextLabel2 = new BeerGameTextLabel("Game:");
        panel.addChild(beerGameTextLabel2);
        final BeerGameDropDown beerGameDropDown;
        (beerGameDropDown = new BeerGameDropDown(new String[] { "No games available" })).setId("gameDropDown");
        panel.addChild(beerGameDropDown);
        beerGameDropDown.setSizeRelativeTo(beerGameLoginTextField, 1.0f, 1.0f);
        beerGameDropDown.addActionListener(new ActionListener() {
            @Override
            public final void actionPerformed(final ActionEvent actionEvent) {
                JoinGamePage.this.getPlayerTypeListInBackground();
            }
        });
        final BeerGameTextLabel beerGameTextLabel3 = new BeerGameTextLabel("Position:");
        panel.addChild(beerGameTextLabel3);
        final Player.Type[] values;
        final String[] array = new String[(values = Player.Type.values()).length];
        for (int i = 0; i < values.length; ++i) {
            array[i] = values[i].toString();
        }
        final BeerGameDropDown beerGameDropDown2;
        (beerGameDropDown2 = new BeerGameDropDown(new String[] { "No positions available" })).setId("playerTypeDropDown");
        panel.addChild(beerGameDropDown2);
        beerGameDropDown2.setSizeRelativeTo(beerGameLoginTextField, 1.0f, 1.0f);
        final BeerGameTextButton beerGameTextButton = new BeerGameTextButton("JOIN GAME");
        panel.addChild(beerGameTextButton);
        beerGameTextButton.addActionListener(new ActionListener() {
            @Override
            public final void actionPerformed(final ActionEvent actionEvent) {
                JoinGamePage.access$100(JoinGamePage.this);
            }
        });
        final BeerGameTextButton beerGameTextButton2;
        (beerGameTextButton2 = new BeerGameTextButton("BACK")).setId("BackButton");
        this.addChild(beerGameTextButton2);
        beerGameTextButton2.addActionListener(new ActionListener() {
            @Override
            public final void actionPerformed(final ActionEvent actionEvent) {
                JoinGamePage.this.gotoPreviousPage(new StartPage());
            }
        });
        StretchableImage.vertical(beerGameDropDown, 17, this, 17);
        StretchableImage.horizontal(beerGameDropDown, 17, this, 17, this, 0.03f);
        StretchableImage.vertical(beerGameTextLabel2, 17, beerGameDropDown, 17);
        StretchableImage.horizontal(beerGameTextLabel2, 2, beerGameDropDown, 1);
        StretchableImage.vertical(beerGameLoginTextField, 32, beerGameDropDown, 16, beerGameLoginTextField, 0.2f);
        StretchableImage.horizontal(beerGameLoginTextField, 17, beerGameDropDown, 17);
        StretchableImage.vertical(beerGameTextLabel, 17, beerGameLoginTextField, 17);
        StretchableImage.horizontal(beerGameTextLabel, 2, beerGameLoginTextField, 1);
        StretchableImage.vertical(beerGameDropDown2, 16, beerGameDropDown, 32, beerGameDropDown2, -0.2f);
        StretchableImage.horizontal(beerGameDropDown2, 17, beerGameDropDown, 17);
        StretchableImage.vertical(beerGameTextLabel3, 17, beerGameDropDown2, 17);
        StretchableImage.horizontal(beerGameTextLabel3, 2, beerGameDropDown2, 1);
        StretchableImage.vertical(beerGameTextButton, 17, this, 17, 1.0f);
        StretchableImage.horizontal(beerGameTextButton, 17, this, 17, this, 0.24f);
        StretchableImage.vertical(beerGameTextButton2, 32, this, 32, this, -0.05f);
        StretchableImage.horizontal(beerGameTextButton2, 2, this, 2, this, -0.04f);
        panel.setPositionRelativeToParent(0.5f, 0.495f);
    }
    
    @Override
    public final void onTearDown() {
        this.timer.cancel();
    }
    
    @Override
    public final void onTransitionEnd$6db0a1c1() {
        this.findById("userNameField").getComponent().requestFocusInWindow();
        final ConnectingOverlay connectingOverlay;
        (connectingOverlay = new ConnectingOverlay("Loading game list", "Please wait...", true) {
            @Override
            protected final void onPerform() {
                ConnectionManager.getConnection().getGameList(this.getResponseHandler());
            }
            
            @Override
            protected final void onResponse(final Response response) {
            }
            
            @Override
            protected final void onFinish(final Response response) {
                if (response.getStatus() == Status.OK) {
                    JoinGamePage.access$400(JoinGamePage.this, response);
                    JoinGamePage.this.timer.schedule(new TimerTask() {
                        @Override
                        public final void run() {
                            JoinGamePage.access$500(JoinGamePage.this);
                        }
                    }, 5000L, 5000L);
                    this.close();
                }
            }
            
            @Override
            protected final void onClosed() {
                if (!ConnectionManager.getConnection().isConnected()) {
                    JoinGamePage.this.gotoPreviousPage(new StartPage());
                }
            }
        }).setId("overlay");
        this.addChild(connectingOverlay);
        connectingOverlay.open();
    }
    
    private static boolean isGameNameLegal(final String s) {
        return s != null && s.length() > 0 && !s.equals("No games available") && !s.equals(" -- Select game -- ");
    }
    
    private void getPlayerTypeListInBackground() {
        final String selectedItem;
        if (!isGameNameLegal(selectedItem = ((BeerGameDropDown)this.findById("gameDropDown")).getSelectedItem())) {
            this.updatePlayerTypeList(new Response(Request.ID_NONE, Command.LOBBY_GET_PLAYERTYPELIST, Status.ERROR_GAME_MUST_BE_SELECTED, new Object[0]));
            return;
        }
        ConnectionManager.getConnection().getPlayerTypeList(selectedItem, new ResponseListener() {
            @Override
            public final void onResponse(final Response response) {
                JoinGamePage.this.updatePlayerTypeList(response);
            }
        });
    }
    
    private void updatePlayerTypeList(final Response response) {
        final BeerGameDropDown beerGameDropDown = (BeerGameDropDown)this.findById("playerTypeDropDown");
        if (response.getStatus() == Status.OK) {
            final Collection collection = (Collection)response.getArgument();
            if (hasListChanged(beerGameDropDown, 0, collection)) {
                final String selectedItem = beerGameDropDown.getSelectedItem();
                beerGameDropDown.removeAllItems();
                if (collection.size() != 0) {
                    final Iterator<?> iterator = collection.iterator();
                    while (iterator.hasNext()) {
                        beerGameDropDown.addItem(((Player.Type)iterator.next()).toString());
                    }
                }
                else {
                    beerGameDropDown.addItem("No positions available");
                }
                beerGameDropDown.setSelectedItem(selectedItem);
            }
            return;
        }
        beerGameDropDown.removeAllItems();
        beerGameDropDown.addItem("No positions available");
    }
    
    private static boolean hasListChanged(final BeerGameDropDown beerGameDropDown, final int n, final Collection<?> collection) {
        if (collection.size() != beerGameDropDown.getNbrItems() - n) {
            return true;
        }
        final Iterator<?> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().equals(beerGameDropDown.getItemAt(n))) {
                return true;
            }
        }
        return false;
    }
    
    static /* synthetic */ void access$100(JoinGamePage joinGamePage) {
        joinGamePage = joinGamePage;
        final Player player = ConnectionManager.getPlayer();
        final String selectedItem = ((BeerGameDropDown)joinGamePage.findById("gameDropDown")).getSelectedItem();
        final String trim;
        final String name = ((trim = ((BeerGameLoginTextField)joinGamePage.findById("userNameField")).getText().trim()).length() > 30) ? trim.substring(0, 30).trim() : trim;
        final String selectedItem2 = ((BeerGameDropDown)joinGamePage.findById("playerTypeDropDown")).getSelectedItem();
        player.setName(name);
        player.setType(selectedItem2);
        final ConnectingOverlay connectingOverlay = new ConnectingOverlay("Connecting", "Please wait...", true) {
            private /* synthetic */ GamePage val$gamePage = new GamePage(player);
            
            @Override
            protected final void onPerform() {
                if (!JoinGamePage.access$200(name)) {
                    this.throwResponse(Command.SESSION_LOGIN, Status.ERROR_PLAYER_INVALID_NAME, new Object[0]);
                    return;
                }
                if (!isGameNameLegal(selectedItem)) {
                    this.throwResponse(Command.GAME_JOIN, Status.ERROR_GAME_MUST_BE_SELECTED, new Object[0]);
                    return;
                }
                if (Player.Type.fromString(selectedItem2) == null) {
                    this.throwResponse(Command.GAME_JOIN, Status.ERROR_PLAYER_INVALID_TYPE, new Object[0]);
                    return;
                }
                ConnectionManager.getConnection().joinGame(selectedItem, Player.Type.fromString(selectedItem2), this.getResponseHandler());
            }
            
            @Override
            protected final void onResponse(final Response response) {
            }
            
            @Override
            protected final void onFinish(final Response response) {
                if (response.getStatus() == Status.OK) {
                    this.val$gamePage.setGame((Game)response.getArgument());
                    JoinGamePage.this.gotoNextPage(this.val$gamePage);
                    return;
                }
                ConnectionManager.getConnection().logout(null);
            }
            
            @Override
            protected final void onClosed() {
                this.getParent().removeChild(this);
                if (!ConnectionManager.getConnection().isConnected()) {
                    JoinGamePage.this.gotoPreviousPage(new StartPage());
                }
            }
        };
        joinGamePage.addChild(connectingOverlay);
        connectingOverlay.open();
    }
    
    static /* synthetic */ boolean access$200(String s) {
        return (s = s) != null && s.length() > 0;
    }
    
    static /* synthetic */ void access$400(JoinGamePage joinGamePage, Response response) {
        final JoinGamePage joinGamePage2 = joinGamePage;
        response = response;
        joinGamePage = joinGamePage2;
        final BeerGameDropDown beerGameDropDown = (BeerGameDropDown)joinGamePage2.findById("gameDropDown");
        boolean b = true;
        if (response.getStatus() == Status.OK) {
            final Collection collection = (Collection)response.getArgument();
            if (hasListChanged(beerGameDropDown, 1, collection)) {
                final String selectedItem = beerGameDropDown.getSelectedItem();
                beerGameDropDown.removeAllItems();
                if (collection.size() != 0) {
                    beerGameDropDown.addItem(" -- Select game -- ");
                    final Iterator<?> iterator = collection.iterator();
                    while (iterator.hasNext()) {
                        beerGameDropDown.addItem((String)iterator.next());
                    }
                }
                else {
                    beerGameDropDown.addItem("No games available");
                }
                beerGameDropDown.setSelectedItem(selectedItem);
                b = false;
            }
        }
        else {
            beerGameDropDown.removeAllItems();
            beerGameDropDown.addItem("No games available");
        }
        if (b) {
            joinGamePage.getPlayerTypeListInBackground();
        }
    }
    
    static /* synthetic */ void access$500(JoinGamePage joinGamePage) {
        joinGamePage = joinGamePage;
        ConnectionManager.getConnection().getGameList(new ResponseListener() {
            @Override
            public final void onResponse(final Response response) {
                JoinGamePage.access$400(JoinGamePage.this, response);
            }
        });
    }
}
