// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.timer;

import javax.management.Notification;

public class TimerNotification extends Notification
{
    private Integer notificationID;
    private static final long serialVersionUID = 1798492029603825750L;
    
    public TimerNotification(final String type, final Object source, final long sequenceNumber, final long timeStamp, final String message, final Integer id) {
        super(type, source, sequenceNumber, timeStamp, message);
        this.notificationID = id;
    }
    
    public Integer getNotificationID() {
        return this.notificationID;
    }
    
    public String toString() {
        final StringBuffer buffer = new StringBuffer(100);
        buffer.append(this.getClass().getName()).append(":");
        buffer.append(" type=").append(this.getType());
        buffer.append(" source=").append(this.getSource());
        buffer.append(" sequence=").append(this.getSequenceNumber());
        buffer.append(" time=").append(this.getTimeStamp());
        buffer.append(" message=").append(this.getMessage());
        buffer.append(" id=").append(this.getNotificationID());
        buffer.append(" userData=").append(this.getUserData());
        return buffer.toString();
    }
}
