// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms;

import com.stonewall.cornerstone.utility.Time;
import com.stonewall.cornerstone.jms.msg.event.TimeEvent;
import javax.jms.MessageListener;
import com.stonewall.cornerstone.jms.msg.event.Topic;
import com.stonewall.cornerstone.jms.msg.event.Event;
import com.stonewall.cornerstone.jms.msg.RegistrySelector;
import com.stonewall.cornerstone.jms.msg.BlindSelector;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.jms.msg.Dispatcher;

public class Timing
{
    static final int Interval = 5;
    static final int threadPriority = 10;
    static final long MaxDrift = 6L;
    private long drift;
    private final Dispatcher dispatcher;
    private TimerThread timerThread;
    private static Timing inst;
    static final Log log;
    
    static {
        Timing.inst = null;
        log = Log.getLog(Timing.class);
    }
    
    public static Timing getInstance() {
        if (Timing.inst == null) {
            Timing.inst = new Timing();
        }
        return Timing.inst;
    }
    
    private Timing() {
        this.drift = 0L;
        this.dispatcher = new Dispatcher("Timing");
    }
    
    public void init() throws Exception {
        this.shutdown();
        switch (this.mode()) {
            case master: {
                (this.timerThread = new TimerThread()).start();
                break;
            }
            case slave: {
                this.dispatcher.register(new BlindSelector("received"), this);
                final Topic topic = new Topic(Event.Type.timesync);
                topic.register(this.dispatcher);
                break;
            }
        }
    }
    
    public void received(final TimeEvent event) {
        this.drift = new Time().getDriftInSeconds(event.getTime());
        Timing.log.debug("Drift: " + this.drift);
        if (Math.abs(this.drift) > 6L) {
            this.excessiveDriftDetected();
        }
    }
    
    void shutdown() {
        this.haltTimer();
        try {
            final Topic topic = new Topic(Event.Type.timesync);
            topic.unregister(this.dispatcher);
        }
        catch (Exception e) {
            Timing.log.error(this.timerThread, e);
        }
    }
    
    long adjust(final long ttl) {
        long result = ttl;
        if (ttl > 0L) {
            final long driftMs = this.drift * 1000L;
            long adjusted = ttl + driftMs;
            if (adjusted <= 0L) {
                Timing.log.warn("Adjusted TTL : " + adjusted + " invalid, adjusted=1 effective TTL=" + Math.abs(driftMs) + 1);
                adjusted = 1L;
            }
            result = adjusted;
        }
        return result;
    }
    
    private long currentTime() {
        return new Time().seconds();
    }
    
    private void timerExpired() {
        final long tm = this.currentTime();
        Timing.log.debug("Broadcasting: " + tm);
        TimeEvent.send(tm);
    }
    
    private void excessiveDriftDetected() {
        if (this.autocorrect()) {
            this.updateSystemTime();
        }
        else {
            Timing.log.warn("Drift: " + this.drift + " not corrected; autocorrection <disabled>.");
        }
    }
    
    private void updateSystemTime() {
        try {
            final Time time = new Time();
            time.set(time.seconds() + this.drift);
            Timing.log.info("System time updated: " + this.signedDrift());
        }
        catch (Exception e) {
            Timing.log.error("Update system time - failed.", e);
        }
    }
    
    private Mode mode() {
        return Mode.valueOf(System.getProperty("cornerstone.jms.timing.mode", "none"));
    }
    
    private boolean autocorrect() {
        return Boolean.parseBoolean(System.getProperty("cornerstone.jms.timing.autocorrect", "false"));
    }
    
    private String signedDrift() {
        final String sign = (this.drift < 0L) ? "" : "+";
        return String.valueOf(sign) + String.valueOf(this.drift);
    }
    
    private void haltTimer() {
        if (this.timerThread != null) {
            this.timerThread.halt();
            this.timerThread = null;
        }
    }
    
    public enum Mode
    {
        master("master", 0), 
        slave("slave", 1), 
        none("none", 2);
        
        private Mode(final String s, final int n) {
        }
    }
    
    class TimerThread extends Thread
    {
        boolean run;
        
        TimerThread() {
            super("jms-timing");
            this.run = true;
            this.setPriority(10);
        }
        
        @Override
        public void run() {
            Timing.log.info("jmsTiming: 5 (seconds) started.");
            try {
                while (this.run) {
                    Thread.sleep(this.interval());
                    Timing.this.timerExpired();
                }
            }
            catch (Exception e) {
                Timing.log.fatal(this, e);
            }
        }
        
        void halt() {
            this.run = false;
        }
        
        long interval() {
            return 5000L;
        }
    }
}
