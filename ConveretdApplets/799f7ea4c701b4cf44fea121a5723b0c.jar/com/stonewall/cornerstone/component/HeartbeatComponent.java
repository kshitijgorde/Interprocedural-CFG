// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.component;

import org.xmodel.IModelObject;
import javax.jms.Message;
import com.stonewall.cornerstone.jms.msg.JmsMessage;
import com.stonewall.cornerstone.jms.msg.event.Topic;
import com.stonewall.cornerstone.jms.msg.event.Event;
import javax.jms.MessageConsumer;
import java.util.List;
import java.util.ArrayList;
import com.stonewall.cornerstone.jms.msg.event.HeartbeatEvent;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.jms.msg.MessageBuilder;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class HeartbeatComponent implements Component
{
    private final ReentrantLock lock;
    private TimerThread timer;
    private final Map<ComponentServer.Type, ConsumerThread> consumers;
    private final Map<String, Statistic> statistics;
    private final int timeout;
    private MessageBuilder builder;
    static final int Interval = 3000;
    static final int threadPriority = 10;
    static final Log log;
    
    static {
        log = Log.getLog(HeartbeatComponent.class);
    }
    
    public HeartbeatComponent() {
        this(15000);
    }
    
    public HeartbeatComponent(final int timeout) {
        this.lock = new ReentrantLock();
        this.timer = null;
        this.consumers = new ConcurrentHashMap<ComponentServer.Type, ConsumerThread>();
        this.statistics = new ConcurrentHashMap<String, Statistic>();
        if (timeout <= 3000) {
            final String msg = "Timeout: " + timeout + " must be > then " + 3000;
            throw new IllegalArgumentException(msg);
        }
        this.timeout = timeout;
        this.builder = new MessageBuilder();
    }
    
    @Override
    public void init(final ComponentServer container) throws Exception {
        this.init();
    }
    
    public void init() throws Exception {
        this.startTimer();
    }
    
    @Override
    public void shutdown() {
        this.lock.lock();
        try {
            this.stopTimer();
            for (final ConsumerThread c : this.consumers.values()) {
                c.halt();
            }
            this.consumers.clear();
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    @Override
    public void trace() {
        HeartbeatComponent.log.warn("Not-Implemented");
    }
    
    public void received(final HeartbeatEvent e) {
        this.updateStatistics(e.getComponentType(), e.getServerId());
    }
    
    public void updateStatistics(final ComponentServer.Type t, final String id) {
        Statistic cleared = null;
        final long current = System.currentTimeMillis();
        this.lock.lock();
        try {
            Statistic statistic = this.find(id);
            if (statistic == null) {
                statistic = new Statistic(t, id);
                statistic.lastHeartbeat = current;
                this.put(statistic);
            }
            else {
                statistic.lastHeartbeat = current;
            }
            if (statistic.alarmed) {
                statistic.alarmed = false;
                cleared = new Statistic(statistic);
            }
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
        if (cleared != null) {
            this.alarmCleared(cleared);
        }
    }
    
    protected void monitor(final ComponentServer.Type t, final String... ids) throws Exception {
        this.lock.lock();
        try {
            this.monitor(t);
            for (final String id : ids) {
                this.put(new Statistic(t, id));
                HeartbeatComponent.log.info("Monitoring: " + t + "." + id);
            }
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    protected void cancel(final String id) {
        this.lock.lock();
        try {
            this.statistics.remove(id);
            HeartbeatComponent.log.info("Monitoring: " + id + " - cancelled");
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    protected void monitor(final ComponentServer.Type t) throws Exception {
        ConsumerThread c = this.consumers.get(t);
        if (c == null) {
            c = new ConsumerThread(t);
            this.consumers.put(t, c);
            c.start();
        }
    }
    
    protected void alarmRaised(final Statistic s) {
        HeartbeatComponent.log.info("Alarm raised " + s.type + "." + s.id);
    }
    
    protected void alarmCleared(final Statistic s) {
        HeartbeatComponent.log.info("Alarm cleared " + s.type + "." + s.id);
    }
    
    private void startTimer() {
        this.stopTimer();
        (this.timer = new TimerThread()).start();
        HeartbeatComponent.log.info("Heartbeat started : 3000 (ms)");
    }
    
    private void stopTimer() {
        if (this.timer != null) {
            this.timer.halt();
            this.timer = null;
        }
    }
    
    private void sendHeartbeat() {
        final ComponentServer.Type t = this.type();
        final String id = this.id();
        HeartbeatEvent.send(t, id);
        HeartbeatComponent.log.debug("Heartbeat sent - " + t + "." + id);
    }
    
    private void timerExpired() {
        this.sendHeartbeat();
        this.validate();
    }
    
    private void validate() {
        final List<Statistic> alarmed = new ArrayList<Statistic>();
        this.lock.lock();
        try {
            final long current = System.currentTimeMillis();
            for (final Statistic s : this.statistics.values()) {
                if (s.alarmed) {
                    continue;
                }
                if (current - s.lastHeartbeat <= this.timeout) {
                    continue;
                }
                s.alarmed = true;
                alarmed.add(new Statistic(s));
            }
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
        for (final Statistic s2 : alarmed) {
            this.alarmRaised(s2);
        }
    }
    
    private String id() {
        return System.getProperty("cornerstone.server.id", "all");
    }
    
    private ComponentServer.Type type() {
        return ComponentServer.Type.valueOf(System.getProperty("cornerstone.server.type"));
    }
    
    private void put(final Statistic s) {
        this.statistics.put(s.id, s);
    }
    
    private Statistic find(final String id) {
        return this.statistics.get(id);
    }
    
    class TimerThread extends Thread
    {
        boolean run;
        
        TimerThread() {
            super("heartbeat-timer");
            this.run = true;
            this.setPriority(10);
        }
        
        @Override
        public void run() {
            try {
                while (this.run) {
                    Thread.sleep(3000L);
                    HeartbeatComponent.this.timerExpired();
                }
            }
            catch (Exception e) {
                HeartbeatComponent.log.fatal(this, e);
            }
        }
        
        void halt() {
            this.run = false;
        }
    }
    
    class ConsumerThread extends Thread
    {
        boolean run;
        final MessageConsumer jmsConsumer;
        
        ConsumerThread(final ComponentServer.Type type) throws Exception {
            super("heartbeat-consumer");
            this.run = true;
            this.setPriority(10);
            final Topic topic = new Topic(Event.Type.heartbeat, type.name());
            this.jmsConsumer = topic.getConsumer();
        }
        
        @Override
        public void run() {
            while (this.run) {
                try {
                    final Message m = this.jmsConsumer.receive(3000L);
                    final JmsMessage jmsg = new JmsMessage(m);
                    if (m == null) {
                        continue;
                    }
                    final IModelObject root = HeartbeatComponent.this.builder.buildModel(jmsg);
                    HeartbeatComponent.this.received(new HeartbeatEvent(jmsg, root));
                }
                catch (Exception e) {
                    HeartbeatComponent.log.warn(this.jmsConsumer, e);
                    this.pause(100L);
                }
            }
        }
        
        void pause(final long ms) {
            try {
                Thread.sleep(ms);
            }
            catch (Exception e) {
                HeartbeatComponent.log.error(this, e);
            }
        }
        
        void halt() {
            this.run = false;
        }
    }
    
    protected class Statistic
    {
        public final ComponentServer.Type type;
        public final String id;
        long lastHeartbeat;
        boolean alarmed;
        
        Statistic(final ComponentServer.Type type, final String id) {
            this.lastHeartbeat = 0L;
            this.alarmed = false;
            this.type = type;
            this.id = id;
        }
        
        Statistic(final HeartbeatComponent heartbeatComponent, final Statistic s) {
            this(heartbeatComponent, s.type, s.id);
            this.lastHeartbeat = s.lastHeartbeat;
            this.alarmed = s.alarmed;
        }
    }
}
