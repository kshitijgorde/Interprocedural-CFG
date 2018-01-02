// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.pages.game;

import com.masystem.beergame.network.protocol.Status;
import com.masystem.beergame.network.protocol.Response;
import com.masystem.beergame.ui.pages.lobby.ResultsPage;
import com.masystem.beergame.network.protocol.Results;
import com.masystem.beergame.ui.pages.lobby.StartPage;
import javax.swing.SwingUtilities;
import java.util.ArrayList;
import java.util.List;
import com.masystem.beergame.network.protocol.Costs;
import javax.swing.Timer;
import com.masystem.beergame.ui.overlays.QuickOverlay;
import com.masystem.beergame.Config;
import com.masystem.beergame.debug.Log;
import com.masystem.beergame.ui.animation.AlphaAnimation;
import com.masystem.beergame.ui.animation.NodeAnimation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.masystem.beergame.ui.beergamecomponents.BeerGameTextButton;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import com.masystem.beergame.ui.beergamecomponents.BeerGameTextLabel;
import com.masystem.beergame.ui.scene.Node;
import com.masystem.beergame.ui.beergamecomponents.BeerGameImage;
import java.util.Iterator;
import com.masystem.beergame.network.ResponseListener;
import com.masystem.beergame.network.protocol.Request;
import com.masystem.beergame.network.protocol.Command;
import com.masystem.beergame.network.RequestListener;
import com.masystem.beergame.ConnectionManager;
import com.masystem.beergame.network.protocol.Game;
import com.masystem.beergame.network.protocol.Player;
import java.awt.Color;
import com.masystem.beergame.ui.beergamecomponents.BeerGamePage;

public class GamePage extends BeerGamePage
{
    protected static final Color DARK_RED;
    protected Player localPlayer;
    protected LocalPlayerView localPlayerView;
    protected NotificationLabel notifier;
    protected Game game;
    private ResponseHandler responseHandler;
    protected RequestHandler requestHandler;
    protected int prevResult;
    protected int prevSupplyChainResult;
    protected boolean isAdmin;
    
    public GamePage(final Player player) {
        this(null, player);
    }
    
    private GamePage(final Game game, Player localPlayer) {
        this.game = null;
        localPlayer = localPlayer;
        this.localPlayer = localPlayer;
        this.responseHandler = new ResponseHandler();
        this.requestHandler = new RequestHandler();
        ConnectionManager.getConnection().addRequestListener(this.requestHandler);
    }
    
    public final void setGame(final Game game) {
        this.game = game;
    }
    
    public final Player getLocalPlayer() {
        return this.localPlayer;
    }
    
    public final void setAdmin(final boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    public final LocalPlayerView getLocalPlayerView() {
        return this.localPlayerView;
    }
    
    public final PlayerView getPlayerView(final Player player) {
        final Player.Type type = player.getType();
        if (this.localPlayer.getType() == type) {
            return this.localPlayerView;
        }
        return (PlayerView)this.getById(getPlayerTypeId(type));
    }
    
    public final void makeTurn(final int n) {
        this.localPlayerView.disableInput();
        final Iterator<Player> iterator = this.game.getPlayerList().iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().isReadyForNextTurn()) {
                this.notifier.setMessage("Waiting for other players...");
                break;
            }
        }
        ((OrderEnvelope)this.getById(String.format("envelope-%s", getPlayerTypeId(this.localPlayer.getType())))).animate(0);
        ConnectionManager.getConnection().gameRequest(new Request(Command.GAME_MAKE_TURN, new Object[] { n }), this.responseHandler);
    }
    
    @Override
    public final void onSetup() {
        final Player.Type type;
        final boolean b;
        BeerGameImage beerGameImage;
        if (!(b = ((type = this.localPlayer.getType()) == Player.Type.RETAILER || type == Player.Type.WHOLESALER))) {
            beerGameImage = new BeerGameImage("game_bg_top.png");
        }
        else {
            beerGameImage = new BeerGameImage("game_bg_bottom.png");
        }
        beerGameImage.setOrder(-100);
        this.addChild(beerGameImage);
        final BeerGameTextLabel beerGameTextLabel = new BeerGameTextLabel();
        this.addChild(beerGameTextLabel);
        beerGameTextLabel.setId("week");
        beerGameTextLabel.setFont("InGameBigFont");
        beerGameTextLabel.setHorizontalAlignment(0);
        beerGameTextLabel.setSizeRelativeToParent(0.038f, -1.0f);
        beerGameTextLabel.setPositionRelativeToParent(0.338f, 0.053f);
        final BeerGameTextLabel beerGameTextLabel2 = new BeerGameTextLabel();
        this.addChild(beerGameTextLabel2);
        beerGameTextLabel2.setId("supplyChainResult");
        beerGameTextLabel2.setFont("InGameBigFont");
        beerGameTextLabel2.setHorizontalAlignment(2);
        beerGameTextLabel2.setSizeRelativeToParent(0.135f, -1.0f);
        beerGameTextLabel2.setPositionRelativeToParent(0.595f, 0.05f);
        beerGameTextLabel2.setVisible(true);
        Player.Type[] values;
        for (int length = (values = Player.Type.values()).length, i = 0; i < length; ++i) {
            final Player.Type type2;
            final String playerTypeId = getPlayerTypeId(type2 = values[i]);
            if (this.localPlayer.getType() == type2) {
                (this.localPlayerView = new LocalPlayerView(this.localPlayer, playerTypeId)).setOrder(-1);
                this.addChild(this.localPlayerView);
                final float[] coords = LocalPlayerView.getCoords(type2);
                this.localPlayerView.setPositionRelativeToParent(coords[0], coords[1]);
                final BeerGameImage beerGameImage2;
                (beerGameImage2 = new BeerGameImage(String.format("local_incoming.png", this.id))).setOrder(2);
                this.addChild(beerGameImage2);
                beerGameImage2.setId("incoming");
                beerGameImage2.setPositionRelativeTo(this.localPlayerView, 0.81f, 0.24f);
                final BeerGameImage beerGameImage3;
                (beerGameImage3 = new BeerGameImage("order_envelope_tilted.png")).setId("closedEnvelope-2");
                beerGameImage3.setOrder(1);
                this.addChild(beerGameImage3);
                beerGameImage3.setPositionRelativeTo(this.localPlayerView, 0.85f, 0.11f);
                final BeerGameImage beerGameImage4;
                (beerGameImage4 = new BeerGameImage("order_envelope_tilted.png")).setId("closedEnvelope-1");
                beerGameImage4.setOrder(1);
                this.addChild(beerGameImage4);
                beerGameImage4.setPositionRelativeTo(this.localPlayerView, 0.81f, 0.14f);
                final IncomingEnvelope incomingEnvelope;
                (incomingEnvelope = new IncomingEnvelope()).setId("openEnvelope");
                incomingEnvelope.setOrder(1);
                this.addChild(incomingEnvelope);
                incomingEnvelope.setPositionRelativeTo(this.localPlayerView, 0.81f, 0.14f);
                incomingEnvelope.setVisible(false);
            }
            else {
                final RemotePlayerView remotePlayerView;
                (remotePlayerView = new RemotePlayerView(playerTypeId)).getGhostView().setOrder(1);
                this.addChild(remotePlayerView.getGhostView());
                remotePlayerView.getClickForAutoView().setOrder(1);
                remotePlayerView.getClickForAutoView().setVisible(this.isAdmin);
                this.addChild(remotePlayerView.getClickForAutoView());
                remotePlayerView.setOrder(2);
                this.addChild(remotePlayerView);
                remotePlayerView.setVisible(false);
                final float[] coords2 = RemotePlayerView.getCoords(type2);
                remotePlayerView.getGhostView().setPositionRelativeToParent(coords2[0], coords2[1]);
                remotePlayerView.getClickForAutoView().setPositionRelativeToParent(coords2[0] - 0.007f, coords2[1] + 0.022f);
                remotePlayerView.setPositionRelativeToParent(coords2[0], coords2[1]);
                remotePlayerView.getClickForAutoView().getComponent().addMouseListener(new MouseAdapter() {
                    @Override
                    public final void mousePressed(final MouseEvent mouseEvent) {
                        remotePlayerView.getClickForAutoView().setVisible(false);
                        ConnectionManager.getConnection().gameRequest(new Request(Command.ADMIN_ADD_GHOST_PLAYER, new Object[] { GamePage.this.game.getName(), type2 }), null);
                    }
                });
            }
        }
        final Points points;
        (points = new Points(0)).setId("pointsIncoming");
        points.setOrder(10);
        this.addChild(points);
        final Points points2;
        (points2 = new Points(0)).setId("pointsOutgoing");
        points2.setOrder(10);
        this.addChild(points2);
        final Points points3;
        (points3 = new Points()).setId("pointsCashflow");
        points3.setOrder(10);
        this.addChild(points3);
        Player.Type[] values2;
        for (int length2 = (values2 = Player.Type.values()).length, j = 0; j < length2; ++j) {
            final Player.Type type3 = values2[j];
            final BeerTruck beerTruck;
            (beerTruck = new BeerTruck(type3, b ? 1 : 0)).setId(String.format("truck-%s", getPlayerTypeId(type3)));
            beerTruck.setOrder(-2);
            this.addChild(beerTruck);
        }
        Player.Type[] values3;
        for (int length3 = (values3 = Player.Type.values()).length, k = 0; k < length3; ++k) {
            final Player.Type type4 = values3[k];
            final OrderEnvelope orderEnvelope;
            (orderEnvelope = new OrderEnvelope(type4, this.localPlayer.getType())).setId(String.format("envelope-%s", getPlayerTypeId(type4)));
            orderEnvelope.setOrder(0);
            this.addChild(orderEnvelope);
        }
        final OrderEnvelope orderEnvelope2;
        (orderEnvelope2 = new OrderEnvelope(null, this.localPlayer.getType())).setId("envelopeCustomer");
        orderEnvelope2.setOrder(0);
        this.addChild(orderEnvelope2);
        final BeerGameTextButton beerGameTextButton;
        (beerGameTextButton = new BeerGameTextButton("FINISH GAME")).setId("finishGame");
        beerGameTextButton.setTextColor(new Color(6225920));
        beerGameTextButton.setOrder(-10);
        this.addChild(beerGameTextButton);
        beerGameTextButton.setPositionRelativeToParent(0.84f, 0.035f);
        beerGameTextButton.setVisible(false);
        beerGameTextButton.addActionListener(new ActionListener(this) {
            @Override
            public final void actionPerformed(final ActionEvent actionEvent) {
                ConnectionManager.getConnection().gameRequest(new Request(Command.ADMIN_GAME_FINISH), null);
            }
        });
        this.addChild(this.notifier = new NotificationLabel());
        this.notifier.setPositionRelativeToParent(0.5f, 0.959f);
        this.notifier.setSizeRelativeToParent(0.41f, -1.0f);
        this.notifier.setHorizontalAlignment(0);
    }
    
    @Override
    public final void onTransitionEnd$6db0a1c1() {
        this.waitForPlayersToJoin();
        int n = 0;
        final Iterator<Player> iterator = this.game.getPlayerList().iterator();
        while (iterator.hasNext()) {
            if (this.activatePlayerView(iterator.next(), n)) {
                n += 500;
            }
        }
        this.requestHandler.setReady();
    }
    
    protected final boolean activatePlayerView(final Player player, final int n) {
        final PlayerView playerView;
        (playerView = this.getPlayerView(player)).updateData(player);
        boolean b = false;
        if (playerView instanceof RemotePlayerView) {
            ((RemotePlayerView)playerView).runJoinAnimation(n);
            b = true;
        }
        return b;
    }
    
    protected final boolean deactivatePlayerView$641c2ae3(final Player player) {
        final PlayerView playerView;
        (playerView = this.getPlayerView(player)).updateData(player);
        if (playerView instanceof RemotePlayerView) {
            ((RemotePlayerView)playerView).runLeaveAnimation();
            this.waitForPlayersToJoin();
            return true;
        }
        return false;
    }
    
    protected static String getPlayerTypeId(final Player.Type type) {
        switch (type) {
            case PRODUCER: {
                return "producer";
            }
            case DISTRIBUTOR: {
                return "distributor";
            }
            case WHOLESALER: {
                return "wholesaler";
            }
            default: {
                return "retailer";
            }
        }
    }
    
    private void waitForPlayersToJoin() {
        this.notifier.setMessage("Waiting for players to join...");
        this.localPlayerView.disableInput();
    }
    
    private void hideAnimations() {
        Player.Type[] values;
        for (int length = (values = Player.Type.values()).length, i = 0; i < length; ++i) {
            final Player.Type type = values[i];
            final OrderEnvelope orderEnvelope;
            final NodeAnimation animation;
            if ((animation = (orderEnvelope = (OrderEnvelope)this.getById(String.format("envelope-%s", getPlayerTypeId(type)))).getAnimation()) != null) {
                if (animation.isWaiting()) {
                    animation.cancel();
                }
                else if (animation.isRunning()) {
                    this.createHideAnimation(orderEnvelope).start();
                }
            }
            final BeerTruck beerTruck;
            final NodeAnimation animation2;
            if ((animation2 = (beerTruck = (BeerTruck)this.getById(String.format("truck-%s", getPlayerTypeId(type)))).getAnimation()) != null) {
                if (animation2.isWaiting()) {
                    animation2.cancel();
                }
                else if (animation2.isRunning()) {
                    this.createHideAnimation(beerTruck).start();
                }
            }
        }
        final OrderEnvelope orderEnvelope2;
        final NodeAnimation animation3;
        if ((animation3 = (orderEnvelope2 = (OrderEnvelope)this.getById("envelopeCustomer")).getAnimation()) != null) {
            if (animation3.isWaiting()) {
                animation3.cancel();
            }
            else if (animation3.isRunning()) {
                this.createHideAnimation(orderEnvelope2).start();
            }
        }
        final Points points;
        final NodeAnimation animation4;
        if ((animation4 = (points = (Points)this.getById("pointsIncoming")).getAnimation()) != null && animation4.isRunning()) {
            animation4.cancel();
            this.createHideAnimation(points).start();
        }
        final Points points2;
        final NodeAnimation animation5;
        if ((animation5 = (points2 = (Points)this.getById("pointsOutgoing")).getAnimation()) != null && animation5.isRunning()) {
            animation5.cancel();
            this.createHideAnimation(points2).start();
        }
        final Points points3;
        final NodeAnimation animation6;
        if ((animation6 = (points3 = (Points)this.getById("pointsCashflow")).getAnimation()) != null && animation6.isRunning()) {
            animation6.cancel();
            this.createHideAnimation(points3).start();
        }
    }
    
    public final NodeAnimation createShowAnimation(final Node node) {
        final AlphaAnimation alphaAnimation;
        (alphaAnimation = new AlphaAnimation(this, node, 0.0f, 1.0f) {
            @Override
            protected final void startAnimation() {
                node.setAlpha(0.0f);
                node.setVisible(true);
            }
        }).setDuration((int)(500.0f * node.getAlpha()));
        return alphaAnimation;
    }
    
    public final NodeAnimation createHideAnimation(final Node node) {
        final AlphaAnimation alphaAnimation;
        (alphaAnimation = new AlphaAnimation(this, node, node.getAlpha(), 0.0f) {
            @Override
            protected final void endAnimation() {
                node.setVisible(false);
                node.setAlpha(1.0f);
            }
        }).setDuration((int)(500.0f * node.getAlpha()));
        return alphaAnimation;
    }
    
    static /* synthetic */ void access$100(GamePage gamePage, int week, int n, int n2, int n3, int n4, int n5, int n6) {
        final GamePage gamePage2 = gamePage;
        final int n7 = week;
        final int n8 = n;
        final int n9 = n2;
        final int n10 = n3;
        final int n11 = n4;
        final int n12 = n5;
        n6 = n6;
        n5 = n12;
        n4 = n11;
        n3 = n10;
        n2 = n9;
        n = n8;
        week = n7;
        gamePage = gamePage2;
        Log.verbose("GamePage.nextTurn(): week: " + week + ", stock: " + n + ", inc order: " + n2 + ", inc stock: " + n3 + ", outgoingStock: " + n4 + ", result: " + n5 + ", supply chain result: " + n6);
        gamePage.game.setWeek(week);
        if (Config.RESULT_TYPE == Config.ResultType.TRANSACTIONS_AND_STOCK) {
            n5 = n5;
            n6 = n6;
        }
        else {
            n5 = -n5;
            n6 = -n6;
        }
        final Iterator<Player> iterator = gamePage.game.getPlayerList().iterator();
        while (iterator.hasNext()) {
            iterator.next().setReadyForNextTurn(false);
        }
        gamePage.hideAnimations();
        gamePage.createHideAnimation(gamePage.getById("openEnvelope")).start();
        final Timer timer;
        (timer = new Timer(750, new ActionListener() {
            @Override
            public final void actionPerformed(final ActionEvent actionEvent) {
                final QuickOverlay quickOverlay;
                (quickOverlay = new QuickOverlay("Week " + week, "Good luck!") {
                    @Override
                    protected final void onClosing() {
                        ((IncomingEnvelope)GamePage.this.getById("openEnvelope")).setIncomingOrder(n2);
                        for (final Player player : GamePage.this.game.getPlayerList()) {
                            GamePage.this.getPlayerView(player).updateData(player);
                        }
                        Player.Type[] values;
                        for (int length = (values = Player.Type.values()).length, i = 0; i < length; ++i) {
                            ((OrderEnvelope)GamePage.this.getById(String.format("envelope-%s", GamePage.getPlayerTypeId(values[i])))).setVisible(false);
                        }
                        GamePage.this.getById("closedEnvelope-1").setVisible(true);
                        final BeerGameTextLabel beerGameTextLabel;
                        (beerGameTextLabel = (BeerGameTextLabel)GamePage.this.findById("supplyChainResult")).setText(GamePage.this.prevSupplyChainResult);
                        beerGameTextLabel.setTextColor((GamePage.this.prevSupplyChainResult < 0) ? GamePage.DARK_RED : Color.BLACK);
                        GamePage.this.localPlayerView.setStock(n - n3 + n2);
                        GamePage.this.localPlayerView.setResult(GamePage.this.prevResult);
                        GamePage.this.localPlayerView.clearInput();
                        GamePage.this.localPlayerView.hideReport();
                        GamePage.access$200(GamePage.this, week, n, n3, n4);
                        ((BeerGameTextLabel)GamePage.this.findById("week")).setText(week);
                        if (week > 2) {
                            GamePage.this.getById("finishGame").setVisible(GamePage.this.isAdmin);
                        }
                    }
                    
                    @Override
                    protected final void onClosed() {
                        GamePage.this.notifier.setMessage("Please place your order!");
                        GamePage.this.localPlayerView.enableInput();
                        final Node byId;
                        if ((byId = GamePage.this.getById("closedEnvelope-1")).isVisible()) {
                            GamePage.this.createHideAnimation(byId).start();
                        }
                        final Node byId2;
                        if (!(byId2 = GamePage.this.getById("openEnvelope")).isVisible()) {
                            GamePage.this.createShowAnimation(byId2).start();
                        }
                        new TruckAnimator(GamePage.this, n3, n2, n, n5 - GamePage.this.prevResult, n6) {
                            @Override
                            protected final void onFinished() {
                                ((OrderEnvelope)GamePage.this.getById("envelopeCustomer")).animate(1000);
                            }
                        }.start();
                        GamePage.this.prevResult = n5;
                        GamePage.this.prevSupplyChainResult = n6;
                    }
                }).setOrder(100);
                GamePage.this.addChild(quickOverlay);
                quickOverlay.open();
            }
        })).setRepeats(false);
        timer.start();
    }
    
    static /* synthetic */ void access$200(GamePage gamePage, int n, int n2, int n3, int n4) {
        final GamePage gamePage2 = gamePage;
        final int n5 = n;
        final int n6 = n2;
        final int n7 = n3;
        n4 = n4;
        n3 = n7;
        n2 = n6;
        n = n5;
        gamePage = gamePage2;
        gamePage.localPlayerView.setReport(n, n2, Costs.getStockCost(n2), n3, Costs.getPurchaseCost(n3), n4, Costs.getSellRevenue(n4));
    }
    
    static {
        DARK_RED = new Color(8323072);
    }
    
    public final class RequestHandler implements RequestListener
    {
        private List<Request> requestQueue;
        private boolean gameReady;
        private boolean gameEnded;
        
        public RequestHandler() {
            this.requestQueue = new ArrayList<Request>();
        }
        
        public final void setReady() {
            synchronized (this) {
                this.gameReady = true;
                final Iterator<Request> iterator = this.requestQueue.iterator();
                while (iterator.hasNext()) {
                    GamePage.this.requestHandler.onRequest(iterator.next());
                }
            }
        }
        
        @Override
        public final void onRequest(final Request request) {
            if (this.gameEnded) {
                return;
            }
            synchronized (this) {
                if (!this.gameReady) {
                    this.requestQueue.add(request);
                    return;
                }
            }
            final Request request2;
            final Command command;
            if ((command = request2.getCommand()) != Command.SESSION_PING) {
                Log.verbose("Request Server -> " + GamePage.this.localPlayer.getName() + " (" + GamePage.this.localPlayer.getType() + "): " + request2);
            }
            switch (command) {
                case GAME_JOIN: {
                    SwingUtilities.invokeLater(new Runnable() {
                        private /* synthetic */ Player val$player = (Player)request2.getArgument();
                        
                        @Override
                        public final void run() {
                            GamePage.this.game.addPlayer(this.val$player);
                            GamePage.this.activatePlayerView(this.val$player, 0);
                        }
                    });
                }
                case GAME_LEAVE: {
                    SwingUtilities.invokeLater(new Runnable() {
                        private /* synthetic */ Player val$player = GamePage.this.game.getPlayer((String)request2.getArgument());
                        
                        @Override
                        public final void run() {
                            GamePage.this.game.removePlayer(this.val$player);
                            GamePage.this.deactivatePlayerView$641c2ae3(this.val$player);
                            if (GamePage.this.game.getWeek() != 0) {
                                RequestHandler.access$002(RequestHandler.this, true);
                                final QuickOverlay quickOverlay;
                                (quickOverlay = new QuickOverlay(this.val$player.getName() + " disconnected", "Game will exit.") {
                                    @Override
                                    protected final void onClosed() {
                                        ConnectionManager.getConnection().removeRequestListener(RequestHandler.this);
                                        ConnectionManager.getPlayer().setJoinedGame(null);
                                        GamePage.this.gotoPreviousPage(new StartPage());
                                    }
                                }).setOrder(100);
                                GamePage.this.addChild(quickOverlay);
                                quickOverlay.open();
                            }
                        }
                    });
                }
                case GAME_MAKE_TURN: {
                    final Player player;
                    (player = GamePage.this.game.getPlayer((String)request2.getArgument())).setReadyForNextTurn(true);
                    GamePage.this.getPlayerView(player).updateData(player);
                    ((OrderEnvelope)GamePage.this.getById(String.format("envelope-%s", GamePage.getPlayerTypeId(player.getType())))).animate(0);
                }
                case GAME_NEXT_TURN: {
                    final Object[] arguments = request2.getArguments();
                    GamePage.access$100(GamePage.this, (int)arguments[0], (int)arguments[1], (int)arguments[2], (int)arguments[3], (int)arguments[4], (int)arguments[5], (int)arguments[6]);
                }
                case GAME_DESTROY: {
                    this.gameEnded = true;
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public final void run() {
                            ConnectionManager.getConnection().removeRequestListener(RequestHandler.this);
                            ConnectionManager.getConnection().logout(null);
                            ConnectionManager.getPlayer().setJoinedGame(null);
                        }
                    });
                }
                case ADMIN_GAME_FINISH: {
                    this.gameEnded = true;
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public final void run() {
                            ConnectionManager.getConnection().removeRequestListener(RequestHandler.this);
                            ConnectionManager.getConnection().logout(null);
                            ConnectionManager.getPlayer().setJoinedGame(null);
                            GamePage.this.gotoNextPage(new ResultsPage((Results)request2.getArgument(), GamePage.this.localPlayer.getType()));
                        }
                    });
                }
                case ADMIN_ACCESS_GRANTED: {
                    Player.Type[] values;
                    for (int length = (values = Player.Type.values()).length, i = 0; i < length; ++i) {
                        final Player.Type type;
                        final String playerTypeId = GamePage.getPlayerTypeId(type = values[i]);
                        if (GamePage.this.localPlayer.getType() != type) {
                            GamePage.this.createShowAnimation(new RemotePlayerView(playerTypeId).getClickForAutoView()).start();
                        }
                    }
                }
                case EXCEPTION: {
                    this.gameEnded = true;
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public final void run() {
                            ConnectionManager.getConnection().removeRequestListener(RequestHandler.this);
                            ConnectionManager.getConnection().logout(null);
                            ConnectionManager.getPlayer().setJoinedGame(null);
                            final QuickOverlay quickOverlay;
                            (quickOverlay = new QuickOverlay("Lost connection with server", "Game will exit.") {
                                @Override
                                protected final void onClosed() {
                                    GamePage.this.gotoPreviousPage(new StartPage());
                                }
                            }).setOrder(100);
                            GamePage.this.addChild(quickOverlay);
                            quickOverlay.open();
                        }
                    });
                    break;
                }
            }
        }
        
        static /* synthetic */ boolean access$002(final RequestHandler requestHandler, final boolean b) {
            return requestHandler.gameEnded = true;
        }
    }
    
    public final class ResponseHandler implements ResponseListener
    {
        protected ResponseHandler(final GamePage gamePage) {
        }
        
        @Override
        public final void onResponse(final Response response) {
            if (response.getStatus() != Status.OK) {
                Log.error((String)response.getArgument());
            }
        }
    }
}
