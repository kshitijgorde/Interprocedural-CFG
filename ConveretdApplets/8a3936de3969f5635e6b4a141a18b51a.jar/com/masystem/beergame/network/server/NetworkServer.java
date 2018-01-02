// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.network.server;

import com.masystem.beergame.network.protocol.Results;
import org.apache.mina.core.future.WriteFuture;
import com.masystem.beergame.network.protocol.Response;
import java.util.Iterator;
import com.masystem.beergame.network.protocol.Game;
import java.util.LinkedList;
import com.masystem.beergame.network.protocol.Status;
import com.masystem.beergame.network.protocol.Command;
import com.masystem.beergame.network.protocol.Request;
import com.masystem.beergame.network.protocol.Player;
import org.apache.mina.core.session.IoSession;
import java.io.IOException;
import com.masystem.beergame.debug.Log;
import java.util.TimerTask;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import com.masystem.beergame.network.protocol.Costs;
import com.masystem.beergame.resource.Properties;
import java.util.Timer;
import org.apache.mina.core.service.IoHandlerAdapter;

public class NetworkServer extends IoHandlerAdapter
{
    private final SessionList sessionList;
    private final GameList gameList;
    private final Timer timer;
    private int port;
    
    public NetworkServer() {
        this.sessionList = new SessionList();
        this.gameList = new GameList();
        this.timer = new Timer();
        final Properties properties;
        Costs.init(properties = new Properties("beergame.properties"));
        this.start(properties.getIntegerProperty("network.port"));
    }
    
    private void start(final int port) {
        this.port = port;
        final NioSocketAcceptor nioSocketAcceptor;
        (nioSocketAcceptor = new NioSocketAcceptor()).getFilterChain().addLast("codec", new ProtocolCodecFilter(new ProtocolCodecFactory()));
        int n = 1;
        int i = 0;
        while (i == 0) {
            try {
                nioSocketAcceptor.setHandler(this);
                nioSocketAcceptor.bind(new InetSocketAddress(port));
                i = 1;
                this.timer.schedule(new KeepAliveChecker(), 5000L, 5000L);
                Log.info("Server started. Listening on port " + port);
                continue;
            }
            catch (IOException ex) {
                Log.warn("Server could not be started: " + ex.toString());
                Log.info("Will retry in 5 seconds (Attempt " + n + " / " + 10000 + ").");
                if (n < 10000) {
                    ++n;
                    try {
                        Thread.sleep(5000L);
                    }
                    catch (InterruptedException ex2) {}
                    continue;
                }
                Log.error("Server failed to start.");
                return;
            }
            break;
        }
    }
    
    @Override
    public final void messageReceived(final IoSession ioSession, final Object o) {
        final Player player = (Player)ioSession.getAttribute("player");
        final Request request;
        final int id = (request = (Request)o).getId();
        final Command command = request.getCommand();
        final Object[] arguments = request.getArguments();
        ioSession.setAttribute("requestId", id);
        ioSession.setAttribute("requestCommand", command);
        if (command != Command.SESSION_PING) {
            Log.verbose("NetworkServer received message: " + o + " from " + ((player == null) ? ioSession : player.getName()));
        }
        if (player == null && command != Command.SESSION_PING && command != Command.SESSION_LOGIN && command != Command.LOBBY_GET_GAMELIST && command != Command.LOBBY_GET_PLAYERTYPELIST) {
            this.respond(ioSession, id, command, Status.ERROR_PLAYER_NOT_LOGGED_IN, new Object[0]);
            return;
        }
        try {
            switch (command) {
                case SESSION_PING: {
                    updateTimestamp(ioSession);
                }
                case SESSION_LOGIN: {
                    if (player != null) {
                        this.respond(ioSession, id, command, Status.ERROR_PLAYER_ALREADY_LOGGED_IN, player.getName());
                        return;
                    }
                    final String s = (String)arguments[0];
                    final String s2 = (String)arguments[1];
                    if (s == null || s.length() == 0) {
                        this.respond(ioSession, id, command, Status.ERROR_PLAYER_INVALID_NAME, new Object[0]);
                        return;
                    }
                    final IoSession session;
                    final Player player2;
                    if ((player2 = (((session = this.getSession(s)) != null) ? getPlayer(session) : null)) != null) {
                        this.respond(ioSession, id, command, Status.ERROR_PLAYER_ALREADY_EXISTS, player2.getName());
                        return;
                    }
                    ioSession.setAttribute("player", new Player(s, s2));
                    updateTimestamp(ioSession);
                    this.sessionList.add(ioSession, s);
                    this.respond(ioSession, id, command, Status.OK, new Object[0]);
                }
                case SESSION_LOGOUT: {
                    if (player.getJoinedGame() != null) {
                        this.throwRequest(ioSession, Command.GAME_LEAVE, new Object[0]);
                    }
                    this.sessionList.remove(player.getName());
                    ioSession.setAttribute("player", null);
                    this.respond(ioSession, id, command, Status.OK, new Object[0]);
                }
                case SESSION_DISCONNECT: {
                    ioSession.close(false);
                }
                case LOBBY_GET_GAMELIST: {
                    this.respond(ioSession, id, command, Status.OK, this.gameList.getGameList());
                }
                case LOBBY_GET_PLAYERTYPELIST: {
                    final String s3;
                    if ((s3 = (String)request.getArgument()) == null || s3.length() == 0) {
                        this.respond(ioSession, id, command, Status.ERROR_GAME_INVALID_NAME, new Object[0]);
                        return;
                    }
                    final Game game;
                    if ((game = this.gameList.getGame(s3)) == null) {
                        this.respond(ioSession, id, command, Status.ERROR_GAME_DOES_NOT_EXIST, s3);
                        return;
                    }
                    final LinkedList<Player.Type> list;
                    (list = new LinkedList<Player.Type>()).add(Player.Type.RETAILER);
                    list.add(Player.Type.WHOLESALER);
                    list.add(Player.Type.DISTRIBUTOR);
                    list.add(Player.Type.PRODUCER);
                    final Iterator<Player> iterator = game.getPlayerList().iterator();
                    while (iterator.hasNext()) {
                        list.remove(iterator.next().getType());
                    }
                    this.respond(ioSession, id, command, Status.OK, list);
                }
                case GAME_CREATE: {
                    final String s4;
                    if ((s4 = (String)request.getArgument()) == null || s4.length() == 0) {
                        this.respond(ioSession, id, command, Status.ERROR_GAME_INVALID_NAME, new Object[0]);
                        return;
                    }
                    final Game game2;
                    if ((game2 = this.gameList.getGame(s4)) != null) {
                        this.respond(ioSession, id, command, Status.ERROR_GAME_ALREADY_EXISTS, game2.getName());
                        return;
                    }
                    this.gameList.createGame(s4);
                    this.broadcastToLobby(new Request(Command.GAME_CREATE, new Object[] { s4 }), ioSession);
                    ioSession.setAttribute("isAdmin");
                    request(ioSession, new Request(Command.ADMIN_ACCESS_GRANTED));
                    this.respond(ioSession, id, command, Status.OK, s4);
                }
                case GAME_JOIN: {
                    final String s5 = (String)arguments[0];
                    final Player.Type type = (Player.Type)arguments[1];
                    if (s5 == null || s5.length() == 0) {
                        this.respond(ioSession, id, command, Status.ERROR_GAME_INVALID_NAME, new Object[0]);
                        return;
                    }
                    final Game game3;
                    if ((game3 = this.gameList.getGame(s5)) == null) {
                        this.respond(ioSession, id, command, Status.ERROR_GAME_DOES_NOT_EXIST, s5);
                        return;
                    }
                    if (type == null) {
                        this.respond(ioSession, id, command, Status.ERROR_PLAYER_INVALID_TYPE, new Object[0]);
                        return;
                    }
                    if (game3.getPlayer(type) != null) {
                        this.respond(ioSession, id, command, Status.ERROR_GAME_ALREADY_HAS_PLAYER_TYPE, type);
                        return;
                    }
                    if (game3.getWeek() != 0) {
                        this.respond(ioSession, id, command, Status.ERROR_GAME_DOES_NOT_EXIST, s5);
                        return;
                    }
                    player.reset();
                    player.setType(type);
                    player.setJoinedGame(game3);
                    game3.addPlayer(player);
                    this.broadcastToGame(game3, new Request(Command.GAME_JOIN, new Object[] { player }), ioSession);
                    this.respond(ioSession, id, command, Status.OK, game3);
                    if (game3.getWeek() == 0) {
                        synchronized (game3) {
                            if (game3.isReadyForStart()) {
                                this.nextTurn(game3);
                            }
                            break;
                        }
                    }
                }
                case GAME_LEAVE: {
                    final Game joinedGame;
                    if ((joinedGame = player.getJoinedGame()) == null) {
                        this.respond(ioSession, id, command, Status.ERROR_PLAYER_NOT_IN_GAME, new Object[0]);
                        return;
                    }
                    player.getJoinedGame().removePlayer(player);
                    player.setJoinedGame(null);
                    this.broadcastToGame(joinedGame, new Request(Command.GAME_LEAVE, new Object[] { player.getName() }), ioSession);
                    this.respond(ioSession, id, command, Status.OK, new Object[0]);
                    if (joinedGame.getNbrPlayers() == 0) {
                        final String name = joinedGame.getName();
                        if (this.gameList.containsGame(name)) {
                            this.broadcastToLobby(new Request(Command.GAME_DESTROY, new Object[] { name }));
                            this.gameList.destroyGame(name);
                        }
                        break;
                    }
                    if (joinedGame.getWeek() != 0) {
                        final String name2 = joinedGame.getName();
                        if (this.gameList.containsGame(name2)) {
                            this.broadcastToGame(joinedGame, new Request(Command.GAME_DESTROY));
                            this.broadcastToLobby(new Request(Command.GAME_DESTROY, new Object[] { name2 }));
                            this.gameList.destroyGame(name2);
                        }
                    }
                }
                case GAME_MAKE_TURN: {
                    final Game joinedGame2 = player.getJoinedGame();
                    player.setOutgoingOrder((int)request.getArgument(), joinedGame2.getWeek());
                    player.setReadyForNextTurn(true);
                    this.broadcastToGame(joinedGame2, new Request(Command.GAME_MAKE_TURN, new Object[] { player.getName() }), ioSession);
                    this.respond(ioSession, id, command, Status.OK, new Object[0]);
                    synchronized (joinedGame2) {
                        if (joinedGame2.isReadyForNextTurn()) {
                            this.nextTurn(joinedGame2);
                        }
                        break;
                    }
                }
                case GAME_CHAT: {
                    final String s6;
                    if ((s6 = (String)request.getArgument()) == null || s6.length() == 0) {
                        this.respond(ioSession, id, command, Status.ERROR_COMMAND_ILLEGAL, new Object[0]);
                        return;
                    }
                    this.broadcastToGame(player.getJoinedGame(), new Request(Command.GAME_CHAT, new Object[] { player.getName() + ": " + s6 }), ioSession);
                    this.respond(ioSession, id, command, Status.OK, s6);
                }
                case ADMIN_GAME_FINISH: {
                    if (!ioSession.containsAttribute("isAdmin")) {
                        this.respond(ioSession, id, command, Status.ERROR_NOT_AUTHORIZED, new Object[0]);
                        return;
                    }
                    this.finishGame(player.getJoinedGame());
                    this.respond(ioSession, id, command, Status.OK, new Object[0]);
                }
                case ADMIN_ADD_GHOST_PLAYER: {
                    if (!ioSession.containsAttribute("isAdmin")) {
                        this.respond(ioSession, id, command, Status.ERROR_NOT_AUTHORIZED, new Object[0]);
                        return;
                    }
                    new GhostPlayer((String)arguments[0], (Player.Type)arguments[1], this.port);
                    this.respond(ioSession, id, command, Status.OK, new Object[0]);
                }
                default: {
                    Log.warn("Unhandled command: " + command);
                    this.respond(ioSession, id, command, Status.ERROR_COMMAND_UNKNOWN, command);
                    break;
                }
            }
        }
        catch (RuntimeException ex) {
            Log.error(ex + ", command: " + command + ", argument: " + request.getArgument(), ex);
            this.respond(ioSession, id, command, Status.ERROR_UNKNOWN, ex.toString());
        }
    }
    
    private static void updateTimestamp(final IoSession ioSession) {
        ioSession.setAttribute("timestamp", System.currentTimeMillis());
    }
    
    @Override
    public final void sessionClosed(final IoSession ioSession) throws Exception {
        this.throwRequest(ioSession, Command.SESSION_LOGOUT, new Object[0]);
        request(ioSession, new Request(Command.SESSION_DISCONNECT, new Object[0]));
    }
    
    private void throwRequest(final IoSession ioSession, final Command command, final Object... array) {
        this.messageReceived(ioSession, new Request(command, array));
    }
    
    @Override
    public final void exceptionCaught(final IoSession ioSession, final Throwable t) {
        if (!(t instanceof IOException)) {
            Log.error("NetworkServer.exceptionCaught(): " + t + ": " + ioSession.getAttribute("requestCommand", null));
            this.respond(ioSession, (int)ioSession.getAttribute("requestId", -1), (Command)ioSession.getAttribute("requestCommand", null), Status.ERROR_UNKNOWN, new Object[0]);
            Log.error(t.getMessage(), t);
        }
        ioSession.close(true);
    }
    
    private void respond(IoSession ioSession, final int n, final Command command, final Status status, final Object... array) {
        final IoSession ioSession2 = ioSession;
        final Response response = new Response(n, command, status, array);
        ioSession = ioSession2;
        if (ioSession2.isConnected()) {
            ioSession.write(response);
        }
    }
    
    private static WriteFuture request(final IoSession ioSession, final Request request) {
        if (ioSession.isConnected()) {
            return ioSession.write(request);
        }
        return null;
    }
    
    public final void broadcastToAll(final Request request, final IoSession ioSession) {
        synchronized (this.sessionList) {
            final Iterator<IoSession> iterator = this.sessionList.getSessions().iterator();
            while (iterator.hasNext()) {
                final IoSession ioSession2;
                if ((ioSession2 = iterator.next()) != null) {
                    request(ioSession2, request);
                }
            }
        }
    }
    
    private void broadcastToGame(final Game game, final Request request) {
        this.broadcastToGame(game, request, null);
    }
    
    private void broadcastToGame(final Game game, final Request request, final IoSession ioSession) {
        synchronized (game) {
            final Iterator<String> iterator = game.getPlayerNameList().iterator();
            while (iterator.hasNext()) {
                final IoSession session;
                if ((session = this.getSession(iterator.next())) != ioSession) {
                    request(session, request);
                }
            }
        }
    }
    
    private void broadcastToLobby(final Request request) {
        this.broadcastToLobby(request, null);
    }
    
    private void broadcastToLobby(final Request request, final IoSession ioSession) {
        synchronized (this.sessionList) {
            final Iterator<IoSession> iterator = this.sessionList.getSessions().iterator();
            while (iterator.hasNext()) {
                final IoSession ioSession2;
                if ((ioSession2 = iterator.next()) != ioSession && (getPlayer(ioSession2) == null || getPlayer(ioSession2).getJoinedGame() == null)) {
                    request(ioSession2, request);
                }
            }
        }
    }
    
    private void nextTurn(final Game game) {
        synchronized (game) {
            int n = 0;
            game.setWeek(game.getWeek() + 1);
            final int week = game.getWeek();
            final Iterator<String> iterator = game.getPlayerNameList().iterator();
            while (iterator.hasNext()) {
                final Player player;
                (player = game.getPlayer(iterator.next())).setReadyForNextTurn(false);
                player.setStock(game.getStock(player, week), week);
                player.setIncomingStock(game.getIncomingStock(player, week), week);
                player.setIncomingOrder(game.getIncomingOrder(player, week), week);
                player.setOutgoingStock(game.getOutgoingStock(player, week), week);
                player.setResult(game.getResult(player, week), week);
                player.newHistoryState();
                n += player.getResult(week);
            }
            if (week < 52) {
                for (final String s : game.getPlayerNameList()) {
                    final Player player2 = game.getPlayer(s);
                    request(this.getSession(s), new Request(Command.GAME_NEXT_TURN, new Object[] { week, player2.getStock(week), player2.getIncomingOrder(week), player2.getIncomingStock(week), player2.getOutgoingStock(week), player2.getResult(week), n }));
                }
            }
            else {
                this.finishGame(game);
            }
        }
    }
    
    private void finishGame(final Game game) {
        this.broadcastToGame(game, new Request(Command.ADMIN_GAME_FINISH, new Object[] { new Results(game.getPlayer(Player.Type.RETAILER).getHistoryStates(), game.getPlayer(Player.Type.WHOLESALER).getHistoryStates(), game.getPlayer(Player.Type.DISTRIBUTOR).getHistoryStates(), game.getPlayer(Player.Type.PRODUCER).getHistoryStates()) }), null);
    }
    
    private IoSession getSession(final String s) {
        return this.sessionList.get(s);
    }
    
    private static Player getPlayer(final IoSession ioSession) {
        return (Player)ioSession.getAttribute("player");
    }
    
    class KeepAliveChecker extends TimerTask
    {
        private KeepAliveChecker(final byte b) {
        }
        
        @Override
        public void run() {
            NetworkServer.this.broadcastToAll(new Request(Command.SESSION_PING), null);
            final long currentTimeMillis = System.currentTimeMillis();
            synchronized (NetworkServer.this.sessionList) {
                final Iterator<IoSession> iterator = NetworkServer.this.sessionList.getSessions().iterator();
                while (iterator.hasNext()) {
                    final IoSession ioSession;
                    final String name;
                    if ((name = ((Player)(ioSession = iterator.next()).getAttribute("player")).getName()).startsWith(" ghost")) {
                        if (currentTimeMillis - (long)ioSession.getAttribute("timestamp", 0) <= 7200000L) {
                            continue;
                        }
                        Log.debug("KeepAliveChecker " + name + " time out: " + (currentTimeMillis - (long)ioSession.getAttribute("timestamp", 0)) + " ms since last keep-alive. Longer than maximum time " + 7200000L + " ms");
                        ioSession.close(true);
                    }
                    else {
                        if (currentTimeMillis - (long)ioSession.getAttribute("timestamp", 0) <= 60000L) {
                            continue;
                        }
                        Log.debug("KeepAliveChecker " + name + " time out: " + (currentTimeMillis - (long)ioSession.getAttribute("timestamp", 0)) + " ms since last keep-alive. Longer than maximum time " + 60000L + " ms");
                        ioSession.close(true);
                    }
                }
            }
        }
    }
}
