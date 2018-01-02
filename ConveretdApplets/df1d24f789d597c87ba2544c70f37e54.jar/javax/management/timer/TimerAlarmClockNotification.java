// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.timer;

import javax.management.Notification;

public class TimerAlarmClockNotification extends Notification
{
    private static final long serialVersionUID = -4841061275673620641L;
    
    public TimerAlarmClockNotification(final TimerAlarmClock source) {
        super("", source, 0L);
    }
}
