// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.notification;

import org.jboss.util.threadpool.BasicThreadPool;
import javax.management.Notification;
import javax.management.NotificationListener;
import org.jboss.util.threadpool.ThreadPool;
import org.jboss.logging.Logger;
import org.jboss.mx.util.JBossNotificationBroadcasterSupport;

public class AsynchNotificationBroadcasterSupport extends JBossNotificationBroadcasterSupport
{
    private static Logger log;
    private static ThreadPool defaultPool;
    private static long defaultNotificationTimeout;
    private long notificationTimeout;
    private ThreadPool pool;
    
    public static synchronized void setDefaultThreadPool(final ThreadPool tp) {
        AsynchNotificationBroadcasterSupport.defaultPool = tp;
    }
    
    public static long getDefaultNotificationTimeout() {
        return AsynchNotificationBroadcasterSupport.defaultNotificationTimeout;
    }
    
    public static void setDefaultNotificationTimeout(final long defaultNotificationTimeout) {
        AsynchNotificationBroadcasterSupport.defaultNotificationTimeout = defaultNotificationTimeout;
    }
    
    public AsynchNotificationBroadcasterSupport() {
        this(AsynchNotificationBroadcasterSupport.defaultNotificationTimeout, AsynchNotificationBroadcasterSupport.defaultPool);
    }
    
    public AsynchNotificationBroadcasterSupport(final long notificationTimeout) {
        this(notificationTimeout, AsynchNotificationBroadcasterSupport.defaultPool);
    }
    
    public AsynchNotificationBroadcasterSupport(final long notificationTimeout, final ThreadPool pool) {
        this.notificationTimeout = notificationTimeout;
        this.pool = pool;
    }
    
    public long getNotificationTimeout() {
        return this.notificationTimeout;
    }
    
    public void setNotificationTimeout(final long notificationTimeout) {
        this.notificationTimeout = notificationTimeout;
    }
    
    public ThreadPool getThreadPool() {
        return this.pool;
    }
    
    public void setThreadPool(final ThreadPool pool) {
        this.pool = pool;
    }
    
    public void handleNotification(final NotificationListener listener, final Notification notification, final Object handback) {
        final AsynchNotifier notifier = new AsynchNotifier(listener, notification, handback);
        this.pool.run(notifier, 0L, this.notificationTimeout);
    }
    
    protected void stopThreadPool(final boolean immeadiate) {
        if (this.pool != AsynchNotificationBroadcasterSupport.defaultPool) {
            this.pool.stop(immeadiate);
        }
    }
    
    static {
        AsynchNotificationBroadcasterSupport.log = Logger.getLogger(AsynchNotifier.class);
        AsynchNotificationBroadcasterSupport.defaultPool = new BasicThreadPool("AsynchNotificationBroadcasterSupport");
    }
    
    public class AsynchNotifier implements Runnable
    {
        NotificationListener listener;
        Notification notification;
        Object handback;
        
        public AsynchNotifier(final NotificationListener listener, final Notification notification, final Object handback) {
            this.listener = listener;
            this.notification = notification;
            this.handback = handback;
        }
        
        public void run() {
            try {
                this.listener.handleNotification(this.notification, this.handback);
            }
            catch (Throwable throwable) {
                AsynchNotificationBroadcasterSupport.log.error("Error processing notification=" + this.notification + " listener=" + this.listener + " handback=" + this.handback, throwable);
            }
        }
    }
}
