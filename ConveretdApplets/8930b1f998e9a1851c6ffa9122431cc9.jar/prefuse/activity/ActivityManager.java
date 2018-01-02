// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.activity;

import prefuse.util.PrefuseConfig;
import java.util.ArrayList;

public class ActivityManager extends Thread
{
    private static ActivityManager s_instance;
    private ArrayList m_activities;
    private ArrayList m_tmp;
    private long m_nextTime;
    private boolean m_run;
    
    private static synchronized ActivityManager getInstance() {
        if (ActivityManager.s_instance == null || !ActivityManager.s_instance.isAlive()) {
            ActivityManager.s_instance = new ActivityManager();
        }
        return ActivityManager.s_instance;
    }
    
    private ActivityManager() {
        super("prefuse_ActivityManager");
        this.m_activities = new ArrayList();
        this.m_tmp = new ArrayList();
        this.m_nextTime = Long.MAX_VALUE;
        final int int1 = PrefuseConfig.getInt("activity.threadPriority");
        if (int1 >= 1 && int1 <= 10) {
            this.setPriority(int1);
        }
        this.setDaemon(true);
        this.start();
    }
    
    public static void stopThread() {
        final ActivityManager s_instance;
        synchronized (ActivityManager.class) {
            s_instance = ActivityManager.s_instance;
        }
        if (s_instance != null) {
            s_instance._stop();
        }
    }
    
    static void schedule(final Activity activity) {
        getInstance()._schedule(activity, activity.getStartTime());
    }
    
    static void scheduleNow(final Activity activity) {
        getInstance()._schedule(activity, System.currentTimeMillis());
    }
    
    static void scheduleAt(final Activity activity, final long n) {
        getInstance()._schedule(activity, n);
    }
    
    static void scheduleAfter(final Activity activity, final Activity activity2) {
        getInstance()._scheduleAfter(activity, activity2);
    }
    
    static void alwaysScheduleAfter(final Activity activity, final Activity activity2) {
        getInstance()._alwaysScheduleAfter(activity, activity2);
    }
    
    static void removeActivity(final Activity activity) {
        getInstance()._removeActivity(activity);
    }
    
    public static int activityCount() {
        return getInstance()._activityCount();
    }
    
    private synchronized void _stop() {
        while (this.m_activities.size() > 0) {
            this.m_activities.get(this.m_activities.size() - 1).cancel();
        }
        this._setRunning(false);
        this.notify();
    }
    
    private void _schedule(final Activity activity, final long n) {
        if (activity.isScheduled()) {
            return;
        }
        activity.setStartTime(n);
        synchronized (this) {
            this.m_activities.add(activity);
            activity.setScheduled(true);
            if (n < this.m_nextTime) {
                this.m_nextTime = n;
                this.notify();
            }
        }
    }
    
    private void _scheduleAfter(final Activity activity, final Activity activity2) {
        activity.addActivityListener(new ScheduleAfterActivity(activity2, true));
    }
    
    private void _alwaysScheduleAfter(final Activity activity, final Activity activity2) {
        activity.addActivityListener(new ScheduleAfterActivity(activity2, false));
    }
    
    private boolean _removeActivity(final Activity activity) {
        final boolean remove;
        synchronized (this) {
            remove = this.m_activities.remove(activity);
            if (remove && this.m_activities.size() == 0) {
                this.m_nextTime = Long.MAX_VALUE;
            }
        }
        if (remove) {
            activity.setScheduled(false);
        }
        return remove;
    }
    
    private synchronized int _activityCount() {
        return this.m_activities.size();
    }
    
    private synchronized void _setRunning(final boolean run) {
        this.m_run = run;
    }
    
    private synchronized boolean _keepRunning() {
        return this.m_run;
    }
    
    public void run() {
        this._setRunning(true);
        while (this._keepRunning()) {
            if (this._activityCount() > 0) {
                final long currentTimeMillis = System.currentTimeMillis();
                long n = -1L;
                synchronized (this) {
                    for (int i = 0; i < this.m_activities.size(); ++i) {
                        final Activity activity = this.m_activities.get(i);
                        this.m_tmp.add(activity);
                        if (currentTimeMillis >= activity.getStopTime()) {
                            this.m_activities.remove(i--);
                            activity.setScheduled(false);
                        }
                    }
                    if (this.m_activities.size() == 0) {
                        this.m_nextTime = Long.MAX_VALUE;
                    }
                }
                for (int j = 0; j < this.m_tmp.size(); ++j) {
                    final long runActivity = this.m_tmp.get(j).runActivity(currentTimeMillis);
                    n = ((runActivity < 0L) ? n : ((n < 0L) ? runActivity : Math.min(n, runActivity)));
                }
                this.m_tmp.clear();
                if (n == -1L) {
                    continue;
                }
                try {
                    synchronized (this) {
                        this.wait(n);
                    }
                }
                catch (InterruptedException ex) {}
            }
            else {
                try {
                    synchronized (this) {
                        this.wait();
                    }
                }
                catch (InterruptedException ex2) {}
            }
        }
    }
    
    public class ScheduleAfterActivity extends ActivityAdapter
    {
        Activity after;
        boolean remove;
        
        public ScheduleAfterActivity(final Activity after, final boolean remove) {
            this.after = after;
            this.remove = remove;
        }
        
        public void activityFinished(final Activity activity) {
            if (this.remove) {
                activity.removeActivityListener(this);
            }
            ActivityManager.scheduleNow(this.after);
        }
        
        public void activityCancelled(final Activity activity) {
            if (this.remove) {
                activity.removeActivityListener(this);
            }
        }
    }
}
