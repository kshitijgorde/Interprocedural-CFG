// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.remoteServer;

import com.stonewall.cornerstone.rmi.RMIClient;
import com.stonewall.cornerstone.rmi.RMIException;
import java.util.Iterator;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmodel.log.Log;
import java.util.Map;
import com.stonewall.cornerstone.entity.Server;

public class Channel
{
    private Server server;
    private boolean enabled;
    private Map<Correlation, AsyncRequest> requests;
    static final Log log;
    
    static {
        log = Log.getLog(Channel.class);
    }
    
    public Channel(final Server server) {
        this.enabled = false;
        this.requests = new HashMap<Correlation, AsyncRequest>();
        this.server = server;
    }
    
    public void enable() {
        Channel.log.debug("Enable Channel for queue:" + this.server.getRMIDestination().getValue());
        this.enabled = true;
    }
    
    public void disable() {
        Channel.log.debug("Disable Channel for queue:" + this.server.getRMIDestination().getValue());
        this.enabled = false;
        final List<AsyncRequest> clone = new ArrayList<AsyncRequest>();
        clone.addAll(this.requests.values());
        for (final Request r : clone) {
            final Request removed = this.requests.remove(r.getCorrelation());
            try {
                r.fail("Communication to the remote server has been disabled.");
            }
            catch (Exception e) {
                Channel.log.error(this, e);
            }
        }
    }
    
    public Object send(final Request r) throws Exception {
        Object result = null;
        final Correlation c = r.getCorrelation();
        if (c != null) {
            this.requests.put(c, (AsyncRequest)r);
        }
        try {
            final Object[] objects = r.getParams();
            if (objects == null) {
                result = this.invoke(r.getTarget(), r.shouldWait(), r.getMethod(), new Object[0]);
            }
            else {
                result = this.invoke(r.getTarget(), r.shouldWait(), r.getMethod(), objects);
            }
        }
        catch (Exception e) {
            this.requests.remove(c);
            r.handleException(e);
            throw e;
        }
        return result;
    }
    
    void respond(final Object... objects) throws Exception {
        if (objects == null) {
            this.invoke("~.remoteServer.CommManager", false, "complete", new Object[0]);
        }
        else {
            this.invoke("~.remoteServer.CommManager", false, "complete", objects);
        }
    }
    
    void complete(final Correlation c, final Object... objects) {
        final AsyncRequest req = this.requests.remove(c);
        if (req != null) {
            try {
                final Callback callback = req.getCallback();
                callback.execute(req, objects);
            }
            catch (Exception e) {
                Channel.log.error(this, e);
                req.handleException(e);
            }
        }
    }
    
    private Object invoke(final String target, final boolean wait, final String methodName, final Object... objects) throws Exception {
        if (!this.enabled) {
            throw new RMIException("Communication to Client has not been established.");
        }
        final RMIClient client = this.getClient();
        client.setTarget(target, new Object[0]);
        client.setTTLRatio(0.0);
        if (wait) {
            return client.invoke(methodName, objects);
        }
        client.invokeNoWait(methodName, objects);
        return null;
    }
    
    private RMIClient getClient() {
        return new RMIClient(this.server);
    }
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Channel@");
        builder.append(this.server.getRMIDestination().getValue());
        builder.append("@");
        builder.append(this.enabled);
        return builder.toString();
    }
    
    public String trace() {
        Channel.log.info("Requests:\n{\n");
        final StringBuilder buf = new StringBuilder();
        for (final Request r : this.requests.values()) {
            buf.append(r);
            buf.append("\n");
        }
        buf.append("\n}\n");
        return buf.toString();
    }
}
