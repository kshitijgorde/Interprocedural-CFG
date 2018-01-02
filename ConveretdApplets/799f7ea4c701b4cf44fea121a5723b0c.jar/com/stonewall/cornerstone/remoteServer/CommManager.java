// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.remoteServer;

import java.util.Iterator;
import com.stonewall.cornerstone.entity.Alarm;
import com.stonewall.cornerstone.jms.msg.event.AlarmEvent;
import com.stonewall.cornerstone.entity.Server;
import com.stonewall.cornerstone.entity.PolicyServer;
import javax.jms.MessageListener;
import com.stonewall.cornerstone.jms.msg.event.Topic;
import com.stonewall.cornerstone.jms.msg.event.Event;
import com.stonewall.cornerstone.component.ComponentServer;
import java.util.HashMap;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.jms.msg.Dispatcher;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import com.stonewall.cornerstone.component.Component;

public class CommManager implements Component
{
    protected final ReentrantLock lock;
    private Map<String, Channel> channels;
    protected Dispatcher dispatcher;
    protected static final Log log;
    
    static {
        log = Log.getLog(CommManager.class);
    }
    
    public CommManager() {
        this.lock = new ReentrantLock();
        this.channels = new HashMap<String, Channel>();
    }
    
    @Override
    public void init(final ComponentServer container) throws Exception {
        if (this.dispatcher != null) {
            final Topic topic = new Topic(Event.Type.alarm);
            topic.register(this.dispatcher);
        }
        final PolicyServer s = new PolicyServer();
        final Channel c = new Channel(s);
        c.enable();
        this.channels.put(null, c);
    }
    
    @Override
    public void shutdown() {
        try {
            if (this.dispatcher != null) {
                final Topic topic = new Topic(Event.Type.alarm);
                topic.unregister(this.dispatcher);
            }
            this.dispatcher = null;
        }
        catch (Exception e) {
            CommManager.log.error(this, e);
        }
    }
    
    public void setDispatcher(final Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }
    
    public void handleEvent(final AlarmEvent e) {
        this.lock.lock();
        try {
            final Alarm alarm = e.getAlarm();
            final Channel channel = this.channels.get(alarm.getAlarmedReference().getId());
            if (channel == null) {
                return;
            }
            if (alarm.isOpen()) {
                channel.disable();
            }
            else {
                channel.enable();
            }
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    @Override
    public void trace() {
        CommManager.log.info("Channels:\n{\n");
        final StringBuilder buf = new StringBuilder();
        for (final Channel channel : this.channels.values()) {
            buf.append(channel.trace());
            buf.append("\n");
        }
        CommManager.log.info(buf.toString());
    }
    
    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder();
        for (final Channel channel : this.channels.values()) {
            buf.append(channel);
            buf.append("\n");
        }
        return buf.toString();
    }
    
    public void addServer(final Server server) throws Exception {
        this.lock.lock();
        try {
            CommManager.log.debug("Adding remote server:" + server.getId());
            Channel c = this.getChannel(server.getId());
            if (c != null) {
                return;
            }
            c = new Channel(server);
            if (server.getAlarmSeverity().equals(Alarm.Severity.none)) {
                c.enable();
            }
            this.channels.put(server.getId(), c);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public void removeServer(final String id) {
        this.lock.lock();
        try {
            CommManager.log.debug("Removing remote server:" + id);
            this.channels.remove(id);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    public Object send(final Request request) throws Exception {
        CommManager.log.debug("Sending request:" + request);
        final Channel c = this.getChannel(request.getTo());
        return c.send(request);
    }
    
    public void complete(final Correlation c, final Object... objects) {
        CommManager.log.debug("Receive response for correlation:" + c);
        final Channel chan = this.getChannel(c.getToId());
        if (chan != null) {
            chan.complete(c, objects);
        }
    }
    
    public void respond(final Correlation c, final Object... objects) throws Exception {
        CommManager.log.debug("Sending response:" + c);
        final Channel chan = this.getChannel(c.getFromId());
        chan.respond(c, objects);
    }
    
    public boolean hasServer(final String id) {
        final Channel c = this.getChannel(id);
        return c != null;
    }
    
    private Channel getChannel(final String id) {
        this.lock.lock();
        try {
            return this.channels.get(id);
        }
        finally {
            this.lock.unlock();
        }
    }
}
