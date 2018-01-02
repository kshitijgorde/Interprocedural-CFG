// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.network.server;

import com.masystem.beergame.network.protocol.Status;
import com.masystem.beergame.network.protocol.Response;
import com.masystem.beergame.network.protocol.Game;
import java.util.Iterator;
import com.masystem.beergame.network.protocol.Command;
import com.masystem.beergame.debug.Log;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import javax.swing.Timer;
import com.masystem.beergame.network.protocol.Request;
import java.util.List;
import com.masystem.beergame.network.client.NetworkClient;
import com.masystem.beergame.network.ResponseListener;
import com.masystem.beergame.network.RequestListener;
import com.masystem.beergame.network.protocol.Player;

public class GhostPlayer extends Player implements RequestListener, ResponseListener
{
    private static final long serialVersionUID = -3530839447545074367L;
    protected final NetworkClient client;
    private List<Request> requestQueue;
    private String gameName;
    protected Timer timer;
    private int retryNbr;
    private boolean ready;
    protected int ghostOutgoingOrder;
    protected boolean madeTurn;
    
    private static final String generateName() {
        return " ghost" + (int)(Math.random() * 2.147483647E9);
    }
    
    public GhostPlayer(final String gameName, final Type type, final int n) {
        super(generateName(), null);
        this.gameName = gameName;
        this.requestQueue = new ArrayList<Request>();
        this.setType(type);
        (this.client = new NetworkClient()).setRequestListener(this);
        this.client.connect(new InetSocketAddress("localhost", n), this);
        (this.timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                synchronized (GhostPlayer.this.timer) {
                    GhostPlayer.this.madeTurn = true;
                    Log.debug(GhostPlayer.this.getType() + " GhostPlayer.makeTurn(): outgoingOrder: " + GhostPlayer.this.ghostOutgoingOrder);
                    GhostPlayer.this.client.request(new Request(Command.GAME_MAKE_TURN, new Object[] { GhostPlayer.this.ghostOutgoingOrder }));
                }
            }
        })).setRepeats(false);
    }
    
    private void setReady() {
        synchronized (this) {
            this.ready = true;
            final Iterator<Request> iterator = this.requestQueue.iterator();
            while (iterator.hasNext()) {
                this.onRequest(iterator.next());
            }
        }
    }
    
    private int getNbrRealPlayers() {
        final Game joinedGame = this.getJoinedGame();
        int n = 0;
        Type[] values;
        for (int length = (values = Type.values()).length, i = 0; i < length; ++i) {
            final Player player;
            if ((player = joinedGame.getPlayer(values[i])) != null && !player.getName().startsWith(" ghost")) {
                ++n;
            }
        }
        return n;
    }
    
    private boolean areRealPlayersDone$68202358() {
        final Game joinedGame = this.getJoinedGame();
        Type[] values;
        for (int length = (values = Type.values()).length, i = 0; i < length; ++i) {
            final Player player;
            if ((player = joinedGame.getPlayer(values[i])) != null && !player.getName().startsWith(" ghost") && !player.isReadyForNextTurn()) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public final void onRequest(final Request request) {
        if (request.getCommand() == Command.SESSION_PING) {
            this.client.request(new Request(Command.SESSION_PING));
            return;
        }
        synchronized (this) {
            if (!this.ready) {
                this.requestQueue.add(request);
                return;
            }
        }
        Log.verbose("Request Server -> Ghost " + this.getType() + ": " + request);
        final Game joinedGame = this.getJoinedGame();
        final Enum enum1;
        switch (enum1) {
            case GAME_JOIN: {
                joinedGame.addPlayer((Player)request.getArgument());
            }
            case GAME_LEAVE: {
                joinedGame.removePlayer(joinedGame.getPlayer((String)request.getArgument()));
                if (this.getNbrRealPlayers() == 0) {
                    Log.debug("Ghost " + this.getType() + " leaving game.");
                    this.timer.stop();
                    this.client.disconnect(false);
                }
            }
            case GAME_MAKE_TURN: {
                joinedGame.getPlayer((String)request.getArgument()).setReadyForNextTurn(true);
                if (this.areRealPlayersDone$68202358()) {
                    synchronized (this.timer) {
                        if (!this.madeTurn) {
                            this.timer.setInitialDelay(0);
                            this.timer.restart();
                        }
                        break;
                    }
                }
            }
            case GAME_NEXT_TURN: {
                final Iterator<Player> iterator = joinedGame.getPlayerList().iterator();
                while (iterator.hasNext()) {
                    iterator.next().setReadyForNextTurn(false);
                }
                final Object[] arguments = request.getArguments();
                final int intValue = (int)arguments[0];
                final int intValue2 = (int)arguments[1];
                final int intValue3 = (int)arguments[2];
                final int intValue4 = (int)arguments[3];
                final int intValue5 = (int)arguments[4];
                final int intValue6 = (int)arguments[5];
                final int intValue7 = (int)arguments[6];
                final int n = intValue6;
                final int n2 = intValue5;
                final int n3 = intValue4;
                final int ghostOutgoingOrder = intValue3;
                Log.debug(this.getType() + " GhostPlayer.nextTurn(): week: " + intValue + ", stock: " + intValue2 + ", inc order: " + ghostOutgoingOrder + ", inc stock: " + n3 + ", outgoingStock: " + n2 + ", result: " + n + ", supply chain result: " + intValue7);
                this.ghostOutgoingOrder = ghostOutgoingOrder;
                synchronized (this.timer) {
                    this.madeTurn = false;
                    this.timer.setInitialDelay(10000 + this.getDelay());
                    this.timer.restart();
                    break;
                }
            }
            case GAME_DESTROY: {}
            case ADMIN_GAME_FINISH: {
                Log.debug("Ghost " + this.getType() + " leaving game.");
                this.timer.stop();
                this.client.disconnect(false);
                break;
            }
        }
    }
    
    @Override
    public final void onResponse(final Response response) {
        Log.verbose("Response Server -> Ghost " + this.getType() + ": " + response);
        final Status status = response.getStatus();
        final Command command = response.getCommand();
        if (status == Status.OK) {
            this.retryNbr = 0;
            switch (command) {
                case SESSION_CONNECT: {
                    this.request(new Request(Command.SESSION_LOGIN, new Object[] { this.getName(), this.getEmail() }));
                }
                case SESSION_LOGIN: {
                    this.request(new Request(Command.GAME_JOIN, new Object[] { this.gameName, this.getType() }));
                }
                case GAME_JOIN: {
                    final Game joinedGame = (Game)response.getArgument();
                    this.setJoinedGame(joinedGame);
                    joinedGame.addPlayer(this);
                    this.setReady();
                    break;
                }
            }
            return;
        }
        if (++this.retryNbr <= 3 && command == Command.SESSION_LOGIN) {
            this.setName(generateName());
            this.request(new Request(Command.SESSION_LOGIN, new Object[] { this.getName(), this.getEmail() }));
        }
    }
    
    private int getDelay() {
        final Type type = this.getType();
        int n = 0;
        final Game joinedGame = this.getJoinedGame();
        Type[] values;
        Type type2;
        for (int length = (values = Type.values()).length, n2 = 0; n2 < length && (type2 = values[n2]) != type; ++n2) {
            final Player player;
            if ((player = joinedGame.getPlayer(type2)) != null && player.getName().startsWith(" ghost")) {
                n += 3000;
            }
        }
        return n + (int)(Math.random() * 2.0 * 1000.0);
    }
    
    private void request(final Request request) {
        Log.verbose("Request Ghost " + this.getType() + " -> Server: " + request);
        this.client.request(request, this);
    }
}
