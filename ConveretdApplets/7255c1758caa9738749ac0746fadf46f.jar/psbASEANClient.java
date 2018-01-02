import java.util.Date;
import java.util.TimeZone;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class psbASEANClient extends Applet implements Runnable
{
    private JavaScriptExecutor jsExecutor;
    private int connectionCount;
    private int daysElapsed;
    private int weeksElapsed;
    private int prevDaysElapsed;
    private int prevWeeksElapsed;
    private int dateInt;
    private int monthInt;
    private int localOffset;
    private float firstDelay;
    private long refreshInterval;
    private long prevTime;
    private boolean first;
    private boolean initial;
    private boolean not_valid;
    private Thread thread;
    private TimeZone localTimeZone;
    
    public psbASEANClient() {
        this.jsExecutor = new JavaScriptExecutor(this);
        this.prevDaysElapsed = 0;
        this.prevWeeksElapsed = 0;
        this.refreshInterval = 900000L;
        this.prevTime = 0L;
        this.first = true;
        this.initial = true;
        this.not_valid = false;
        this.connectionCount = 0;
        this.firstDelay = 0.0f;
    }
    
    public void init() {
    }
    
    public void start() {
        if (this.thread == null) {
            (this.thread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
    }
    
    public void destroy() {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
        TimeZone.setDefault(this.localTimeZone);
    }
    
    private void setTimeDifference(final long n, final long n2) {
        this.jsExecutor.execute("setTimeDifference(" + n + "," + n2 + ")");
    }
    
    public void run() {
        this.first = true;
        this.initial = true;
        this.not_valid = false;
        this.localTimeZone = TimeZone.getDefault();
        this.localOffset = this.localTimeZone.getRawOffset();
        final psbConnect6 psbConnect6 = new psbConnect6(this.getDocumentBase().getHost());
        while (true) {
            psbConnect6.getData();
            if (!psbConnect6.isValid()) {
                break;
            }
            final long n = psbConnect6.accuracy() / 2L;
            final long longValue = new Long(n);
            final Date date = new Date();
            this.setTimeDifference(psbConnect6.value() + n, longValue);
            Thread.currentThread().setPriority(1);
            while (Thread.currentThread() == this.thread) {
                if (new Date().getTime() - date.getTime() > this.refreshInterval) {
                    this.initial = true;
                    break;
                }
                this.initial = false;
                try {
                    Thread.sleep(300L);
                }
                catch (InterruptedException ex) {
                    return;
                }
            }
        }
        this.setTimeDifference(-999L, -999L);
        if (this.localOffset == 0) {
            this.localTimeZone = TimeZone.getDefault();
        }
        this.initial = false;
        this.not_valid = true;
        final long time = new Date().getTime();
        while (true) {
            if (new Date().getTime() - time > 60000L) {
                this.run();
            }
            TimeZone.setDefault(this.localTimeZone);
            try {
                Thread.sleep(30000L);
            }
            catch (InterruptedException ex2) {}
        }
    }
}
