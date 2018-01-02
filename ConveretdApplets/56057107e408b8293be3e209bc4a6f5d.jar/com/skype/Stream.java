// 
// Decompiled by Procyon v0.5.30
// 

package com.skype;

import java.util.concurrent.ExecutionException;
import com.skype.connector.ConnectorException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Future;
import com.skype.connector.Connector;
import com.skype.connector.NotificationChecker;
import java.util.ArrayList;
import java.util.List;

public final class Stream extends SkypeObject
{
    private final Application application;
    private final String id;
    private List listeners;
    private SkypeExceptionHandler exceptionHandler;
    
    Stream(final Application newApplication, final String newId) {
        this.listeners = new ArrayList();
        this.application = newApplication;
        this.id = newId;
    }
    
    public int hashCode() {
        return this.getId().hashCode();
    }
    
    public boolean equals(final Object compared) {
        if (compared instanceof Stream) {
            final Stream comparedStream = (Stream)compared;
            return this.getId().equals(comparedStream.getId());
        }
        return false;
    }
    
    public String toString() {
        return this.getId();
    }
    
    public Application getApplication() {
        return this.application;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getFriend() {
        return this.getId().substring(0, this.getId().indexOf(58));
    }
    
    public void write(final String text) throws SkypeException {
        Utils.checkNotNull(text, "text");
        try {
            final NotificationChecker checker = new NotificationChecker() {
                public boolean isTarget(final String message) {
                    if (!message.startsWith("APPLICATION " + Stream.this.getApplication().getName() + " SENDING ")) {
                        return false;
                    }
                    final String data = message.substring(("APPLICATION " + Stream.this.getApplication().getName() + " SENDING ").length());
                    if ("".equals(data)) {
                        return true;
                    }
                    final String[] streams = data.split(" ");
                    for (int i = 0; i < streams.length; ++i) {
                        String stream = streams[i];
                        stream = stream.substring(0, stream.indexOf(61));
                        if (stream.equals(Stream.this.getId())) {
                            return false;
                        }
                    }
                    return true;
                }
            };
            final String header = "ALTER APPLICATION " + this.getApplication().getName() + " WRITE " + this.getId();
            ApplicationListener applicationListener = null;
            try {
                final Future future = Connector.getInstance().waitForEndWithId(String.valueOf(header) + " " + text, header, checker);
                applicationListener = new ApplicationAdapter() {
                    public void disconnected(final Stream stream) throws SkypeException {
                        if (stream == Stream.this) {
                            future.cancel(true);
                        }
                    }
                };
                this.application.addApplicationListener(applicationListener);
                try {
                    Utils.checkError(future.get());
                }
                catch (CancellationException e) {
                    throw new SkypeException("The '" + this.getId() + "' stream is closed.", e);
                }
                catch (ExecutionException e2) {
                    if (e2.getCause() instanceof ConnectorException) {
                        throw (ConnectorException)e2.getCause();
                    }
                    throw new SkypeException("The '" + header + " " + text + "' command failed.", e2);
                }
                catch (InterruptedException e3) {
                    Thread.currentThread().interrupt();
                    throw new SkypeException("The thread is interrupted.", e3);
                }
            }
            finally {
                if (applicationListener != null) {
                    this.application.removeApplicationListener(applicationListener);
                }
            }
            if (applicationListener != null) {
                this.application.removeApplicationListener(applicationListener);
            }
        }
        catch (ConnectorException e4) {
            Utils.convertToSkypeException(e4);
        }
    }
    
    private boolean isClosed() throws SkypeException {
        final Stream[] streams = this.application.getAllStreams();
        for (int i = 0; i < streams.length; ++i) {
            final Stream stream = streams[i];
            if (stream == this) {
                return false;
            }
        }
        return true;
    }
    
    public void send(final String datagram) throws SkypeException {
        Utils.checkNotNull(datagram, "datagram");
        try {
            final String resposeHeader = "ALTER APPLICATION " + this.getApplication().getName() + " DATAGRAM " + this.getId();
            final String command = String.valueOf(resposeHeader) + " " + datagram;
            final String result = Connector.getInstance().execute(command, resposeHeader);
            Utils.checkError(result);
        }
        catch (ConnectorException e) {
            Utils.convertToSkypeException(e);
        }
    }
    
    public void addStreamListener(final StreamListener listener) {
        Utils.checkNotNull("listener", listener);
        this.listeners.add(listener);
    }
    
    public void removeStreamListener(final StreamListener listener) {
        Utils.checkNotNull("listener", listener);
        this.listeners.remove(listener);
    }
    
    void fireTextReceived(final String text) {
        final StreamListener[] tmpListeners = this.listeners.toArray(new StreamListener[0]);
        for (int i = 0; i < tmpListeners.length; ++i) {
            final StreamListener listener = tmpListeners[i];
            try {
                listener.textReceived(text);
            }
            catch (Throwable e) {
                Utils.handleUncaughtException(e, this.exceptionHandler);
            }
        }
    }
    
    void fireDatagramReceived(final String datagram) {
        final StreamListener[] tmpListeners = this.listeners.toArray(new StreamListener[0]);
        for (int i = 0; i < tmpListeners.length; ++i) {
            final StreamListener listener = tmpListeners[i];
            try {
                listener.datagramReceived(datagram);
            }
            catch (Throwable e) {
                Utils.handleUncaughtException(e, this.exceptionHandler);
            }
        }
    }
    
    public void disconnect() throws SkypeException {
        try {
            final String response = Connector.getInstance().execute("ALTER APPLICATION " + this.application.getName() + " DISCONNECT " + this.getId());
            Utils.checkError(response);
        }
        catch (ConnectorException e) {
            Utils.convertToSkypeException(e);
        }
    }
}
