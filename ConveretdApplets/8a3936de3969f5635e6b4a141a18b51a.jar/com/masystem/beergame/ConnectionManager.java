// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame;

import java.util.Iterator;
import com.masystem.beergame.network.protocol.Status;
import com.masystem.beergame.network.protocol.Response;
import com.masystem.beergame.debug.Log;
import java.net.SocketAddress;
import com.masystem.beergame.resource.ResourceManager;
import org.apache.mina.core.future.ConnectFuture;
import com.masystem.beergame.resource.Properties;
import java.net.InetSocketAddress;
import com.masystem.beergame.network.protocol.Request;
import com.masystem.beergame.network.protocol.Command;
import com.masystem.beergame.network.ResponseListener;
import java.util.LinkedList;
import com.masystem.beergame.network.RequestListener;
import java.util.List;
import com.masystem.beergame.network.protocol.Player;
import org.apache.mina.core.future.IoFuture;
import com.masystem.beergame.network.client.NetworkClient;

public class ConnectionManager
{
    protected NetworkClient client;
    protected IoFuture future;
    protected int nextTaskIndex;
    protected Runnable currentTask;
    protected int currentState;
    protected Player player;
    private List<Runnable> taskList;
    protected boolean aborted;
    private final List<RequestListener> requestListeners;
    
    public ConnectionManager(final Player player) {
        this.player = player;
        this.taskList = new LinkedList<Runnable>();
        this.requestListeners = new LinkedList<RequestListener>();
        (this.client = new NetworkClient()).setRequestListener(new RequestHandler());
    }
    
    public final void addRequestListener(final RequestListener requestListener) {
        synchronized (this.requestListeners) {
            this.requestListeners.add(requestListener);
        }
    }
    
    public final void removeRequestListener(final RequestListener requestListener) {
        synchronized (this.requestListeners) {
            this.requestListeners.remove(requestListener);
        }
    }
    
    public final boolean isConnected() {
        return this.client.isConnected();
    }
    
    private synchronized void abort() {
        this.aborted = true;
        this.clearTasks();
    }
    
    private synchronized void restore() {
        this.aborted = false;
    }
    
    public final synchronized void destroy() {
        this.abort();
        this.close();
    }
    
    public final synchronized void disconnect(final ResponseListener responseListener) {
        this.abort();
        if (this.client.isConnected()) {
            this.addTask(new Task(new Request(Command.SESSION_DISCONNECT), new ResponseHandler(null), 0), false, true);
        }
        this.restore();
    }
    
    public final synchronized void logout(final ResponseListener responseListener) {
        this.abort();
        this.addTask(new Task(new Request(Command.SESSION_LOGOUT), new ResponseHandler(null), 0), false, true);
        this.restore();
    }
    
    protected final void connect(final ResponseListener responseListener, final boolean b) {
        final Properties properties;
        this.addTask(new Request(Command.SESSION_CONNECT, new Object[] { new InetSocketAddress((properties = getProperties()).getProperty("network.host"), properties.getIntegerProperty("network.port")) }), responseListener, 0, true, false);
    }
    
    protected final void login(final ResponseListener responseListener, final boolean b) {
        this.addTask(new Request(Command.SESSION_LOGIN, new Object[] { this.player.getName(), this.player.getEmail() }), responseListener, 1, true, false);
    }
    
    protected final synchronized void close() {
        if (this.future instanceof ConnectFuture) {
            ((ConnectFuture)this.future).cancel();
        }
        if (this.client != null) {
            this.client.disconnect(true);
        }
    }
    
    public final void getGameList(final ResponseListener responseListener) {
        this.addTask(new Request(Command.LOBBY_GET_GAMELIST), responseListener, 1, false, false);
    }
    
    public final void getPlayerTypeList(final String s, final ResponseListener responseListener) {
        this.addTask(new Request(Command.LOBBY_GET_PLAYERTYPELIST, new Object[] { s }), responseListener, 1, false, false);
    }
    
    public final void createGame(final String s, final ResponseListener responseListener) {
        this.addTask(new Request(Command.GAME_CREATE, new Object[] { s }), responseListener, 2, false, false);
    }
    
    public final void joinGame(final String s, final Player.Type type, final ResponseListener responseListener) {
        this.addTask(new Request(Command.GAME_JOIN, new Object[] { s, type }), responseListener, 2, false, false);
    }
    
    public final void gameRequest(final Request request, final ResponseListener responseListener) {
        this.addTask(new Task(request, new ResponseHandler(responseListener), 3), false, false);
    }
    
    private void addTask(final Request request, final ResponseListener responseListener, final int n, final boolean b, final boolean b2) {
        this.addTask(new Task(request, new ResponseHandler(responseListener), n), b, false);
    }
    
    private void addTask(final Runnable runnable, final boolean b, final boolean b2) {
        synchronized (this) {
            if (!this.aborted || b2) {
                if (b) {
                    this.taskList.add(this.nextTaskIndex, runnable);
                }
                else {
                    this.taskList.add(runnable);
                }
            }
        }
        this.performNextTask();
    }
    
    public final synchronized void clearTasks() {
        this.currentTask = null;
        this.nextTaskIndex = 0;
        this.taskList.clear();
    }
    
    public final synchronized boolean hasMoreTasks() {
        return this.getNbrRemainingTasks() > 0;
    }
    
    private synchronized int getNbrRemainingTasks() {
        return this.taskList.size() - this.nextTaskIndex;
    }
    
    private synchronized Runnable getNextTask() {
        if (this.hasMoreTasks()) {
            return this.taskList.get(this.nextTaskIndex);
        }
        return null;
    }
    
    protected final void performNextTask() {
        synchronized (this) {
            if (this.currentTask != null) {
                return;
            }
            if (!this.hasMoreTasks()) {
                this.clearTasks();
                return;
            }
            if (!this.client.isConnected()) {
                this.currentState = 0;
            }
            this.currentTask = this.getNextTask();
        }
        this.currentTask.run();
    }
    
    public ConnectionManager() {
    }
    
    public static Properties getProperties() {
        return (Properties)ResourceManager.getResource("Properties");
    }
    
    public static Player getPlayer() {
        return (Player)ResourceManager.getResource("Player");
    }
    
    public static ConnectionManager getConnection() {
        return (ConnectionManager)ResourceManager.getResource("ConnectionManager");
    }
    
    public final class Task implements Runnable
    {
        private Request request;
        private ResponseHandler responseHandler;
        private int minimumState;
        
        public Task(final Request request, final ResponseHandler responseHandler, final int minimumState) {
            this.request = request;
            this.responseHandler = responseHandler;
            this.minimumState = minimumState;
        }
        
        @Override
        public final String toString() {
            return "Task [ request: " + this.request + ", minimumState: " + this.minimumState + " ]";
        }
        
        @Override
        public final void run() {
            if (ConnectionManager.this.currentState >= this.minimumState) {
                if (this.request.getCommand() == Command.SESSION_DISCONNECT) {
                    ConnectionManager.this.close();
                    ConnectionManager.this.currentTask = null;
                    final ConnectionManager this$0 = ConnectionManager.this;
                    ++this$0.nextTaskIndex;
                    ConnectionManager.this.performNextTask();
                    return;
                }
                if (this.request.getCommand() == Command.SESSION_CONNECT) {
                    ConnectionManager.this.future = ConnectionManager.this.client.connect((SocketAddress)this.request.getArgument(), this.responseHandler);
                    return;
                }
                Log.verbose("Request " + ConnectionManager.this.player.getName() + " (" + ConnectionManager.this.player.getType() + ") -> Server: " + this.request + "   " + this.responseHandler);
                ConnectionManager.this.future = ConnectionManager.this.client.request(this.request, this.responseHandler);
            }
            else {
                ConnectionManager.this.currentTask = null;
                if (this.minimumState == 2) {
                    ConnectionManager.this.login(this.responseHandler.responseListener, true);
                    return;
                }
                if (this.minimumState == 1) {
                    ConnectionManager.this.connect(this.responseHandler.responseListener, true);
                }
            }
        }
    }
    
    public final class ResponseHandler implements ResponseListener
    {
        private final ResponseListener responseListener;
        
        public ResponseHandler(final ResponseListener responseListener) {
            this.responseListener = responseListener;
        }
        
        @Override
        public final void onResponse(final Response response) {
            synchronized (ConnectionManager.this) {
                final Status status = response.getStatus();
                final Command command = response.getCommand();
                if (ConnectionManager.this.aborted && command != Command.SESSION_LOGOUT && command != Command.SESSION_DISCONNECT) {
                    return;
                }
                if (status == Status.OK) {
                    switch (command) {
                        case SESSION_CONNECT: {
                            ConnectionManager.this.currentState = 1;
                            break;
                        }
                        case SESSION_LOGIN: {
                            ConnectionManager.this.currentState = 2;
                            break;
                        }
                        case GAME_JOIN: {
                            ConnectionManager.this.currentState = 3;
                            break;
                        }
                        case SESSION_LOGOUT: {
                            ConnectionManager.this.currentState = 1;
                            break;
                        }
                        case SESSION_DISCONNECT: {
                            ConnectionManager.this.currentState = 0;
                            break;
                        }
                    }
                    ConnectionManager.this.currentTask = null;
                    final ConnectionManager this$0 = ConnectionManager.this;
                    ++this$0.nextTaskIndex;
                    if (this.responseListener != null) {
                        this.responseListener.onResponse(response);
                    }
                    ConnectionManager.this.performNextTask();
                }
                else {
                    ConnectionManager.this.clearTasks();
                    if (this.responseListener != null) {
                        this.responseListener.onResponse(response);
                    }
                }
            }
        }
    }
    
    public final class RequestHandler implements RequestListener
    {
        @Override
        public final void onRequest(final Request request) {
            final Command command;
            if ((command = request.getCommand()) == Command.SESSION_PING) {
                ConnectionManager.this.client.request(new Request(Command.SESSION_PING));
                return;
            }
            synchronized (ConnectionManager.this.requestListeners) {
                final Iterator<RequestListener> iterator = ConnectionManager.this.requestListeners.iterator();
                while (iterator.hasNext()) {
                    iterator.next().onRequest(request);
                }
            }
            switch (command) {
                case SESSION_DISCONNECT: {
                    ConnectionManager.this.currentState = 0;
                    break;
                }
            }
        }
    }
}
