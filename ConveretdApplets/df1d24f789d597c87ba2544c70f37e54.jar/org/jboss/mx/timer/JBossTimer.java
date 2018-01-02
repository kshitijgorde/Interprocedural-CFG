// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.timer;

import org.jboss.mx.util.SchedulableRunnable;
import javax.management.Notification;
import javax.management.timer.TimerNotification;
import javax.management.MBeanServer;
import javax.management.InstanceNotFoundException;
import java.util.Iterator;
import java.util.Collection;
import java.util.Vector;
import java.util.Date;
import org.jboss.mx.util.RunnableScheduler;
import java.util.HashMap;
import javax.management.ObjectName;
import org.jboss.logging.Logger;
import javax.management.MBeanRegistration;
import javax.management.NotificationBroadcasterSupport;

public class JBossTimer extends NotificationBroadcasterSupport implements JBossTimerMBean, MBeanRegistration
{
    private static Logger log;
    public static final long ONE_SECOND = 1000L;
    public static final long ONE_MINUTE = 60000L;
    public static final long ONE_HOUR = 3600000L;
    public static final long ONE_DAY = 86400000L;
    public static final long ONE_WEEK = 604800000L;
    private static final int SEND_NO = 0;
    private static final int SEND_START = 1;
    private static final int SEND_NORMAL = 2;
    int nextId;
    long sequenceNumber;
    boolean sendPastNotifications;
    boolean active;
    ObjectName objectName;
    HashMap notifications;
    private RunnableScheduler scheduler;
    
    public JBossTimer() {
        this.nextId = 0;
        this.sequenceNumber = 0L;
        this.sendPastNotifications = false;
        this.active = false;
        this.notifications = new HashMap();
        this.scheduler = new RunnableScheduler();
    }
    
    public Integer addNotification(final String type, final String message, final Object userData, final Date date) throws IllegalArgumentException {
        return this.addNotification(type, message, userData, date, 0L);
    }
    
    public Integer addNotification(final String type, final String message, final Object userData, final Date date, final long period) throws IllegalArgumentException {
        return this.addNotification(type, message, userData, date, period, 0L);
    }
    
    public Integer addNotification(final String type, final String message, final Object userData, final Date date, final long period, final long occurences) throws IllegalArgumentException {
        return this.addNotification(type, message, userData, date, period, occurences, false);
    }
    
    public Integer addNotification(final String type, final String message, final Object userData, final Date date, final long period, final long nbOccurences, final boolean fixedRate) throws IllegalArgumentException {
        int newId = 0;
        newId = ++this.nextId;
        final Integer id = new Integer(newId);
        final RegisteredNotification rn = new RegisteredNotification(id, type, message, userData, date, period, nbOccurences, fixedRate);
        synchronized (this.notifications) {
            this.notifications.put(id, rn);
            rn.setNextRun(rn.nextDate);
            rn.setScheduler(this.scheduler);
        }
        return id;
    }
    
    public Vector getAllNotificationIDs() {
        synchronized (this.notifications) {
            return new Vector(this.notifications.keySet());
        }
    }
    
    public Date getDate(final Integer id) {
        final RegisteredNotification rn = this.notifications.get(id);
        if (rn == null) {
            return null;
        }
        return new Date(rn.startDate);
    }
    
    public int getNbNotifications() {
        return this.notifications.size();
    }
    
    public Long getNbOccurences(final Integer id) {
        final RegisteredNotification rn = this.notifications.get(id);
        if (rn == null) {
            return null;
        }
        return new Long(rn.occurences);
    }
    
    public Boolean getFixedRate(final Integer id) {
        final RegisteredNotification rn = this.notifications.get(id);
        if (rn == null) {
            return null;
        }
        return new Boolean(rn.fixedRate);
    }
    
    public Vector getNotificationIDs(final String type) {
        final Vector result = new Vector();
        synchronized (this.notifications) {
            for (final RegisteredNotification rn : this.notifications.values()) {
                if (rn.type.equals(type)) {
                    result.add(rn.id);
                }
            }
        }
        return result;
    }
    
    public String getNotificationMessage(final Integer id) {
        final RegisteredNotification rn = this.notifications.get(id);
        if (rn == null) {
            return null;
        }
        return rn.message;
    }
    
    public String getNotificationType(final Integer id) {
        final RegisteredNotification rn = this.notifications.get(id);
        if (rn == null) {
            return null;
        }
        return rn.type;
    }
    
    public Object getNotificationUserData(final Integer id) {
        final RegisteredNotification rn = this.notifications.get(id);
        if (rn == null) {
            return null;
        }
        return rn.userData;
    }
    
    public Long getPeriod(final Integer id) {
        final RegisteredNotification rn = this.notifications.get(id);
        if (rn == null) {
            return null;
        }
        return new Long(rn.period);
    }
    
    public boolean getSendPastNotifications() {
        return this.sendPastNotifications;
    }
    
    public boolean isActive() {
        return this.active;
    }
    
    public boolean isEmpty() {
        return this.notifications.isEmpty();
    }
    
    public void removeAllNotifications() {
        synchronized (this.notifications) {
            final Iterator iterator = this.notifications.values().iterator();
            while (iterator.hasNext()) {
                final RegisteredNotification rn = iterator.next();
                rn.setScheduler(null);
                iterator.remove();
            }
        }
        synchronized (this) {
            this.nextId = 0;
        }
    }
    
    public void removeNotification(final Integer id) throws InstanceNotFoundException {
        JBossTimer.log.debug("removeNotification: " + this.objectName + ",id=" + id);
        synchronized (this.notifications) {
            final RegisteredNotification rn = this.notifications.get(id);
            if (rn == null) {
                throw new InstanceNotFoundException("No notification id : " + id.toString());
            }
            rn.setScheduler(null);
            this.notifications.remove(id);
        }
    }
    
    public void removeNotifications(final String type) throws InstanceNotFoundException {
        boolean found = false;
        JBossTimer.log.debug("removeNotifications: " + this.objectName + ",type=" + type);
        synchronized (this.notifications) {
            final Iterator iterator = this.notifications.values().iterator();
            while (iterator.hasNext()) {
                final RegisteredNotification rn = iterator.next();
                if (rn.type.equals(type)) {
                    rn.setScheduler(null);
                    iterator.remove();
                    found = true;
                }
            }
        }
        if (!found) {
            throw new InstanceNotFoundException("Nothing registered for type: " + type);
        }
    }
    
    public void setSendPastNotifications(final boolean value) {
        JBossTimer.log.debug("setSendPastNotifications: " + this.objectName + ",value=" + value);
        this.sendPastNotifications = value;
    }
    
    public synchronized void start() {
        if (this.active) {
            return;
        }
        this.active = true;
        JBossTimer.log.debug("start: " + this.objectName + " at " + new Date());
        synchronized (this.notifications) {
            for (final RegisteredNotification rn : this.notifications.values()) {
                if (this.sendPastNotifications) {
                    rn.sendType = 1;
                }
                else {
                    rn.sendType = 0;
                }
                this.sendNotifications(rn);
                rn.sendType = 2;
            }
        }
        this.scheduler.start();
    }
    
    public synchronized void stop() {
        if (!this.active) {
            return;
        }
        JBossTimer.log.debug("stop: " + this.objectName + ",now=" + new Date());
        this.active = false;
        this.scheduler.stop();
    }
    
    public ObjectName preRegister(final MBeanServer server, final ObjectName objectName) throws Exception {
        return this.objectName = objectName;
    }
    
    public void postRegister(final Boolean registrationDone) {
    }
    
    public void preDeregister() throws Exception {
        this.stop();
    }
    
    public void postDeregister() {
    }
    
    private void sendNotifications(final RegisteredNotification rn) {
        while (this.isActive() && rn.nextDate != 0L && rn.nextDate <= System.currentTimeMillis()) {
            if (rn.sendType != 0) {
                long seq = 0L;
                synchronized (this) {
                    final long sequenceNumber = this.sequenceNumber + 1L;
                    this.sequenceNumber = sequenceNumber;
                    seq = sequenceNumber;
                }
                JBossTimer.log.debug("sendNotification: " + rn);
                final TimerNotification tn = new TimerNotification(rn.type, this.objectName, seq, rn.nextDate, rn.message, rn.id);
                tn.setUserData(rn.userData);
                this.sendNotification(tn);
            }
            do {
                if (!rn.calcNextDate()) {
                    synchronized (this.notifications) {
                        JBossTimer.log.debug("remove: " + rn);
                        this.notifications.remove(rn.id);
                    }
                }
                if (this.isActive() && rn.sendType != 1 && rn.nextDate != 0L && rn.occurences == 0L) {
                    continue;
                }
                break;
            } while (rn.nextDate < System.currentTimeMillis());
        }
        if (rn.nextDate != 0L) {
            rn.setNextRun(rn.nextDate);
        }
    }
    
    static {
        JBossTimer.log = Logger.getLogger(JBossTimer.class);
    }
    
    private class RegisteredNotification extends SchedulableRunnable
    {
        public Integer id;
        public String type;
        public String message;
        public Object userData;
        public long startDate;
        public long period;
        public long occurences;
        public boolean fixedRate;
        public int sendType;
        public long nextDate;
        
        public RegisteredNotification(final Integer id, final String type, final String message, final Object userData, final Date startDate, final long period, final long occurences, final boolean fixedRate) throws IllegalArgumentException {
            this.sendType = 2;
            this.nextDate = 0L;
            if (startDate == null) {
                throw new IllegalArgumentException("Null Date");
            }
            if (period < 0L) {
                throw new IllegalArgumentException("Negative Period");
            }
            if (occurences < 0L) {
                throw new IllegalArgumentException("Negative Occurences");
            }
            this.startDate = startDate.getTime();
            if (startDate.getTime() < System.currentTimeMillis()) {
                JBossTimer.log.debug("startDate [" + startDate + "] in the past, set to now");
                this.startDate = System.currentTimeMillis();
            }
            this.id = id;
            this.type = type;
            this.message = message;
            this.userData = userData;
            this.period = period;
            this.occurences = occurences;
            this.fixedRate = fixedRate;
            this.nextDate = this.startDate;
            final String msgStr = "new " + this.toString();
            JBossTimer.log.debug(msgStr);
        }
        
        boolean calcNextDate() {
            if (this.period == 0L) {
                this.nextDate = 0L;
                return false;
            }
            if (this.occurences != 0L) {
                final long occurences = this.occurences - 1L;
                this.occurences = occurences;
                if (occurences == 0L) {
                    this.nextDate = 0L;
                    return false;
                }
            }
            if (this.fixedRate) {
                this.nextDate += this.period;
            }
            else {
                this.nextDate = System.currentTimeMillis() + this.period;
            }
            return true;
        }
        
        public void doRun() {
            JBossTimer.this.sendNotifications(this);
        }
        
        public String toString() {
            return " RegisteredNotification: [timer=" + JBossTimer.this.objectName + ",id=" + this.id + ",startDate=" + new Date(this.startDate) + ",period=" + this.period + ",occurences=" + this.occurences + ",fixedRate=" + this.fixedRate + ",nextDate=" + new Date(this.nextDate) + "]";
        }
    }
}
