// 
// Decompiled by Procyon v0.5.30
// 

package com.skype;

import com.skype.connector.ConnectorMessageEvent;
import com.skype.connector.AbstractConnectorListener;
import com.skype.connector.ConnectorException;
import com.skype.connector.Connector;
import java.util.HashMap;
import java.util.Collections;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.List;
import com.skype.connector.ConnectorListener;
import java.util.concurrent.ConcurrentMap;

public final class Application extends SkypeObject
{
    private static final ConcurrentMap applications;
    private final String name;
    private boolean isInitialized;
    private final Object isInitializedFieldMutex;
    private Thread shutdownHookForFinish;
    private final ConnectorListener dataListener;
    private final Object connectMutex;
    private final List listeners;
    private final Map streams;
    private SkypeExceptionHandler exceptionHandler;
    
    static {
        applications = new ConcurrentHashMap();
    }
    
    static Application getInstance(final String id) throws SkypeException {
        final Application newApplication = new Application(id);
        Application application = Application.applications.putIfAbsent(id, newApplication);
        if (application == null) {
            application = newApplication;
        }
        application.initialize();
        return application;
    }
    
    private Application(final String newName) throws SkypeException {
        this.isInitializedFieldMutex = new Object();
        this.shutdownHookForFinish = new ShutdownHookForFinish((ShutdownHookForFinish)null);
        this.dataListener = new DataListener((DataListener)null);
        this.connectMutex = new Object();
        this.listeners = Collections.synchronizedList(new ArrayList<Object>());
        this.streams = new HashMap();
        this.name = newName;
    }
    
    public String toString() {
        return this.getName();
    }
    
    public String getName() {
        return this.name;
    }
    
    void initialize() throws SkypeException {
        try {
            synchronized (this.isInitializedFieldMutex) {
                final String response = Connector.getInstance().execute("CREATE APPLICATION " + this.name);
                this.getAllStreams();
                if (response.startsWith("ERROR ") && !response.startsWith("ERROR 541 ")) {
                    Utils.checkError(response);
                }
                if (!this.isInitialized) {
                    Connector.getInstance().addConnectorListener(this.dataListener, false, true);
                    Runtime.getRuntime().addShutdownHook(this.shutdownHookForFinish);
                    this.isInitialized = true;
                }
            }
            // monitorexit(this.isInitializedFieldMutex)
        }
        catch (ConnectorException e) {
            Utils.convertToSkypeException(e);
        }
    }
    
    public void finish() throws SkypeException {
        try {
            synchronized (this.isInitializedFieldMutex) {
                if (this.isInitialized) {
                    Connector.getInstance().removeConnectorListener(this.dataListener);
                    Runtime.getRuntime().removeShutdownHook(this.shutdownHookForFinish);
                    final String response = Connector.getInstance().execute("DELETE APPLICATION " + this.getName());
                    Utils.checkError(response);
                    this.isInitialized = false;
                }
            }
            // monitorexit(this.isInitializedFieldMutex)
        }
        catch (ConnectorException e) {
            Utils.convertToSkypeException(e);
        }
    }
    
    public Stream[] connect(final String id) throws SkypeException {
        Utils.checkNotNull("ids", id);
        synchronized (this.connectMutex) {
            try {
                final Object wait = new Object();
                final ConnectorListener connectorListener = new AbstractConnectorListener() {
                    public void messageReceived(final ConnectorMessageEvent event) {
                        final String message = event.getMessage();
                        if (message.equals("APPLICATION " + Application.this.getName() + " CONNECTING ")) {
                            synchronized (wait) {
                                wait.notify();
                                // monitorexit(this.val$wait)
                                return;
                            }
                        }
                        if (message.startsWith("APPLICATION " + Application.this.getName() + " STREAMS ")) {
                            final String streamIds = message.substring(("APPLICATION " + Application.this.getName() + " STREAMS ").length());
                            if ("".equals(streamIds)) {
                                return;
                            }
                            final String[] theids = streamIds.split(" ");
                            for (int i = 0; i < theids.length; ++i) {
                                final String streamId = theids[i];
                                if (streamId.startsWith(String.valueOf(id) + ":")) {
                                    synchronized (wait) {
                                        wait.notify();
                                        // monitorexit(this.val$wait)
                                        return;
                                    }
                                }
                            }
                        }
                    }
                };
                try {
                    Connector.getInstance().addConnectorListener(connectorListener);
                    // monitorenter(o = wait)
                    try {
                        if (id != null) {
                            final String command = "ALTER APPLICATION " + this.getName() + " CONNECT " + id;
                            final String result = Connector.getInstance().execute(command, new String[] { command, "APPLICATION " + this.getName() + " CONNECTING ", "ERROR " });
                            Utils.checkError(result);
                        }
                        try {
                            wait.wait();
                        }
                        catch (InterruptedException e) {
                            new SkypeException("The connecting was interrupted.", e);
                        }
                    }
                    // monitorexit(o)
                    finally {}
                    final Stream[] allStreams = this.getAllStreams(id);
                    Connector.getInstance().removeConnectorListener(connectorListener);
                    // monitorexit(this.connectMutex)
                    return allStreams;
                }
                catch (ConnectorException e2) {
                    Utils.convertToSkypeException(e2);
                    Connector.getInstance().removeConnectorListener(connectorListener);
                    // monitorexit(this.connectMutex)
                    return null;
                }
                finally {
                    Connector.getInstance().removeConnectorListener(connectorListener);
                }
            }
            catch (SkypeException e3) {
                final Stream[] streams = this.getAllStreams(id);
                for (int i = 0; i < streams.length; ++i) {
                    final Stream stream = streams[i];
                    try {
                        stream.disconnect();
                    }
                    catch (SkypeException ex) {}
                }
                throw e3;
            }
        }
    }
    
    public Stream[] getAllStreams(final String id) throws SkypeException {
        final List results = new ArrayList();
        final Stream[] streams = this.getAllStreams();
        for (int i = 0; i < streams.length; ++i) {
            final Stream stream = streams[i];
            final String comparedId = stream.getFriend();
            if (comparedId.equals(id)) {
                results.add(stream);
            }
        }
        return results.toArray(new Stream[0]);
    }
    
    public Stream[] getAllStreams() throws SkypeException {
        final String streamIds = Utils.getPropertyWithCommandId("APPLICATION", this.getName(), "STREAMS");
        synchronized (this.streams) {
            this.fireStreamEvents(streamIds);
            if ("".equals(streamIds)) {
                // monitorexit(this.streams)
                return new Stream[0];
            }
            final String[] ids = streamIds.split(" ");
            final Stream[] results = new Stream[ids.length];
            for (int i = 0; i < ids.length; ++i) {
                results[i] = this.streams.get(ids[i]);
            }
            // monitorexit(this.streams)
            return results;
        }
    }
    
    private void fireStreamEvents(final String newStreamIdList) {
        synchronized (this.streams) {
            final String[] newStreamIds = "".equals(newStreamIdList) ? new String[0] : newStreamIdList.split(" ");
            for (int i = 0; i < newStreamIds.length; ++i) {
                final String streamId = newStreamIds[i];
                if (!this.streams.containsKey(streamId)) {
                    final Stream stream = new Stream(this, streamId);
                    this.streams.put(streamId, stream);
                    this.fireConnected(stream);
                }
            }
            final String[] oldStreamIds = (String[])this.streams.keySet().toArray(new String[0]);
            int j = 0;
        Label_0196:
            while (j < oldStreamIds.length) {
                final String oldStreamId = oldStreamIds[j];
                while (true) {
                    for (int k = 0; k < newStreamIds.length; ++k) {
                        final String newStreamId = newStreamIds[k];
                        if (oldStreamId.equals(newStreamId)) {
                            ++j;
                            continue Label_0196;
                        }
                    }
                    final Stream stream2 = this.streams.remove(oldStreamId);
                    this.fireDisconnected(stream2);
                    continue;
                }
            }
        }
        // monitorexit(this.streams)
    }
    
    private void fireConnected(final Stream stream) {
        final ApplicationListener[] myListeners = this.listeners.toArray(new ApplicationListener[0]);
        for (int i = 0; i < myListeners.length; ++i) {
            final ApplicationListener listener = myListeners[i];
            try {
                listener.connected(stream);
            }
            catch (Throwable e) {
                Utils.handleUncaughtException(e, this.exceptionHandler);
            }
        }
    }
    
    private void fireDisconnected(final Stream stream) {
        final ApplicationListener[] myListeners = this.listeners.toArray(new ApplicationListener[0]);
        for (int i = 0; i < myListeners.length; ++i) {
            final ApplicationListener listener = myListeners[i];
            try {
                listener.disconnected(stream);
            }
            catch (Throwable e) {
                Utils.handleUncaughtException(e, this.exceptionHandler);
            }
        }
    }
    
    public void addApplicationListener(final ApplicationListener listener) {
        Utils.checkNotNull("listener", listener);
        this.listeners.add(listener);
    }
    
    public void removeApplicationListener(final ApplicationListener listener) {
        Utils.checkNotNull("listener", listener);
        this.listeners.remove(listener);
    }
    
    private class DataListener extends AbstractConnectorListener
    {
        public void messageReceived(final ConnectorMessageEvent event) {
            final String message = event.getMessage();
            final String streamsHeader = "APPLICATION " + Application.this.getName() + " STREAMS ";
            if (message.startsWith(streamsHeader)) {
                final String streamIds = message.substring(streamsHeader.length());
                Application.this.fireStreamEvents(streamIds);
            }
            final String dataHeader = "APPLICATION " + Application.this.getName() + " ";
            if (message.startsWith(dataHeader)) {
                this.handleData(message.substring(dataHeader.length()));
            }
        }
        
        private void handleData(final String dataResponse) {
            try {
                if (this.isReceivedText(dataResponse)) {
                    final String data = dataResponse.substring("RECEIVED ".length());
                    final String streamId = data.substring(0, data.indexOf(61));
                    final String dataHeader = "ALTER APPLICATION " + Application.this.getName() + " READ " + streamId;
                    final String response = Connector.getInstance().executeWithId(dataHeader, dataHeader);
                    Utils.checkError(response);
                    final String text = response.substring(dataHeader.length() + 1);
                    synchronized (Application.this.streams) {
                        if (Application.this.streams.containsKey(streamId)) {
                            Application.this.streams.get(streamId).fireTextReceived(text);
                        }
                        // monitorexit(Application.access$1(this.this$0))
                        return;
                    }
                }
                if (this.isReceivedDatagram(dataResponse)) {
                    final String data = dataResponse.substring("DATAGRAM ".length());
                    final String streamId = data.substring(0, data.indexOf(32));
                    final String datagram = data.substring(data.indexOf(32) + 1);
                    synchronized (Application.this.streams) {
                        if (Application.this.streams.containsKey(streamId)) {
                            Application.this.streams.get(streamId).fireDatagramReceived(datagram);
                        }
                    }
                    // monitorexit(Application.access$1(this.this$0))
                }
            }
            catch (Exception e) {
                Utils.handleUncaughtException(e, Application.this.exceptionHandler);
            }
        }
        
        private boolean isReceivedText(final String dataResponse) {
            return dataResponse.startsWith("RECEIVED ") && "RECEIVED ".length() < dataResponse.length();
        }
        
        private boolean isReceivedDatagram(final String dataResponse) {
            return dataResponse.startsWith("DATAGRAM ");
        }
    }
    
    private class ShutdownHookForFinish extends Thread
    {
        public void run() {
            try {
                Connector.getInstance().execute("DELETE APPLICATION " + Application.this.getName());
            }
            catch (ConnectorException ex) {}
        }
    }
}
