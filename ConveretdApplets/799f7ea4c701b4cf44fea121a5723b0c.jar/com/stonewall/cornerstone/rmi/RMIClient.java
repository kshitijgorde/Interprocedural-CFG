// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.rmi;

import com.stonewall.cornerstone.component.ComponentServer;
import javax.jms.Destination;
import com.stonewall.cornerstone.jms.msg.JmsMessage;
import org.xmodel.IModelObject;
import java.util.Collections;
import org.xmodel.IModelObjectFactory;
import org.xmodel.ElementFactory;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.jms.msg.MessageBuilder;
import com.stonewall.cornerstone.thread.ExceptionListener;
import com.stonewall.cornerstone.entity.Server;

public class RMIClient
{
    private RMIRequest request;
    private Server server;
    private long overrideTimeout;
    protected Target target;
    private double ttlRatio;
    private ExceptionListener exceptionListener;
    private DataFactory dataFactory;
    private MessageBuilder builder;
    private static final long defaultTimeout;
    static final Log log;
    
    static {
        defaultTimeout = Long.getLong("cornerstone.rmi.timeout", 0L);
        log = Log.getLog(RMIClient.class);
    }
    
    public RMIClient(final Server server) {
        this(server, new TypedDataAdaptor(), new ElementFactory());
    }
    
    public RMIClient(final Server server, final IDataAdaptor adaptor, final IModelObjectFactory objectFactory) {
        this.overrideTimeout = -1L;
        this.ttlRatio = 0.0;
        this.server = server;
        (this.builder = new MessageBuilder()).setFactory(objectFactory);
        this.dataFactory = new DataFactory(adaptor);
        this.initRequest();
    }
    
    private void initRequest() {
        this.request = new RMIRequest(this.server.getRMIDestination());
    }
    
    public void setTimeout(final long timeout) {
        if (timeout < 0L) {
            throw new IllegalArgumentException("Timeout must be greater than 0");
        }
        this.overrideTimeout = timeout;
    }
    
    public long getTimeout() {
        if (this.overrideTimeout != -1L) {
            return this.overrideTimeout;
        }
        return RMIClient.defaultTimeout;
    }
    
    public void setTTLRatio(final double ratio) {
        if (ratio < 0.0) {
            throw new IllegalArgumentException("Time to live ratio must be greater than or equal to 0");
        }
        this.ttlRatio = ratio;
    }
    
    public long getTimeToLive() {
        return (long)(Object)new Double(this.getTimeout() * this.ttlRatio);
    }
    
    public void setTarget(final String className, final Object... params) {
        this.target = new Target(this.dataFactory, className, params);
    }
    
    public void setTarget(final Class c, final Object... params) {
        this.setTarget(c.getName(), params);
    }
    
    public Object invoke(final String methodName, final Object... params) throws Exception {
        final Method method = new Method(this.dataFactory, methodName, params);
        if (this.isLocal()) {
            final IModelObject result = this.executeLocally(method, true);
            if (result == null) {
                return null;
            }
            return this.dataFactory.createObjects(Collections.singletonList(result)).get(0);
        }
        else {
            this.request.setTarget(this.target.getRoot());
            this.request.setMethod(method.getRoot());
            RMIReply reply = null;
            try {
                this.request.setTimeToLive(this.getTimeToLive());
                final JmsMessage message = this.request.sendAndWait(this.getTimeout());
                if (message == null) {
                    throw new RMIException("RMI receive timed out");
                }
                final IModelObject root = this.builder.buildModel(message);
                reply = new RMIReply(message, root);
                final Exception exception = reply.getException();
                if (exception != null) {
                    throw exception;
                }
                final IModelObject result2 = reply.getReturnValue();
                if (result2 == null) {
                    return null;
                }
                return this.dataFactory.createObjects(Collections.singletonList(result2)).get(0);
            }
            catch (Exception e) {
                throw new RMIException(e);
            }
            finally {
                this.initRequest();
            }
        }
    }
    
    public void invokeNoWait(final String methodName, final Object... params) throws Exception {
        final Method method = new Method(this.dataFactory, methodName, params);
        if (this.isLocal()) {
            this.executeLocally(method, false);
            return;
        }
        this.request.setTarget(this.target.getRoot());
        this.request.setMethod(method.getRoot());
        try {
            this.request.send(null);
        }
        catch (Exception e) {
            throw new RMIException(e);
        }
        finally {
            this.initRequest();
        }
        this.initRequest();
    }
    
    private IModelObject executeLocally(final Method method, final boolean wait) throws Exception {
        final RMIComponent component = (RMIComponent)ComponentServer.getInstance().getComponent("~.rmi.RMIComponent");
        return component.invokeLocal(this.exceptionListener, this.target, method, wait);
    }
    
    @Override
    public String toString() {
        return this.request.toString();
    }
    
    public boolean isLocal() {
        return ComponentServer.getInstance() != null && this.server.isLocal(ComponentServer.getInstance());
    }
    
    public void setExceptionListener(final ExceptionListener listener) {
        this.exceptionListener = listener;
    }
}
