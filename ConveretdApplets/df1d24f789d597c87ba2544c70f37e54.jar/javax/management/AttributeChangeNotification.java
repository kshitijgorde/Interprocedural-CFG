// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

public class AttributeChangeNotification extends Notification
{
    private static final long serialVersionUID = 535176054565814134L;
    public static final String ATTRIBUTE_CHANGE = "jmx.attribute.change";
    private String attributeName;
    private String attributeType;
    private Object oldValue;
    private Object newValue;
    
    public AttributeChangeNotification(final Object source, final long sequenceNumber, final long timeStamp, final String msg, final String attributeName, final String attributeType, final Object oldValue, final Object newValue) {
        super("jmx.attribute.change", source, sequenceNumber, timeStamp, msg);
        this.attributeName = null;
        this.attributeType = null;
        this.oldValue = null;
        this.newValue = null;
        this.attributeName = attributeName;
        this.attributeType = attributeType;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }
    
    public String getAttributeName() {
        return this.attributeName;
    }
    
    public String getAttributeType() {
        return this.attributeType;
    }
    
    public Object getOldValue() {
        return this.oldValue;
    }
    
    public Object getNewValue() {
        return this.newValue;
    }
    
    public String toString() {
        final StringBuffer buffer = new StringBuffer(50);
        buffer.append(this.getClass().getName()).append(":");
        buffer.append(" source=").append(this.getSource());
        buffer.append(" seq-no=").append(this.getSequenceNumber());
        buffer.append(" time=").append(this.getTimeStamp());
        buffer.append(" message=").append(this.getMessage());
        buffer.append(" attributeName=").append(this.getAttributeName());
        buffer.append(" attributeType=").append(this.getAttributeType());
        buffer.append(" oldValue=").append(this.getOldValue());
        buffer.append(" newValue=").append(this.getNewValue());
        buffer.append(" notificationType=").append(this.getType());
        buffer.append(" userData=").append(this.getUserData());
        return buffer.toString();
    }
}
