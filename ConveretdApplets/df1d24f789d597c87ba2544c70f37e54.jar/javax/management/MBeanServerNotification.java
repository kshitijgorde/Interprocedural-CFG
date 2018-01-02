// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

public class MBeanServerNotification extends Notification
{
    private static final long serialVersionUID = 2876477500475969677L;
    public static final String REGISTRATION_NOTIFICATION = "JMX.mbean.registered";
    public static final String UNREGISTRATION_NOTIFICATION = "JMX.mbean.unregistered";
    private ObjectName objectName;
    
    public MBeanServerNotification(final String type, final Object source, final long sequence, final ObjectName objectName) {
        super(type, source, sequence);
        this.objectName = null;
        this.objectName = objectName;
    }
    
    public ObjectName getMBeanName() {
        return this.objectName;
    }
    
    public String toString() {
        final StringBuffer buffer = new StringBuffer(50);
        buffer.append(this.getClass().getName()).append(":");
        buffer.append(" notificationType=").append(this.getType());
        buffer.append(" source=").append(this.getSource());
        buffer.append(" seq-no=").append(this.getSequenceNumber());
        buffer.append(" time=").append(this.getTimeStamp());
        buffer.append(" message=").append(this.getMessage());
        buffer.append(" objectName=").append(this.getMBeanName());
        buffer.append(" userData=").append(this.getUserData());
        return buffer.toString();
    }
}
