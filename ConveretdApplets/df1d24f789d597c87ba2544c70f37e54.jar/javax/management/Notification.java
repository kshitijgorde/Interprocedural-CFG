// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import org.jboss.mx.util.Serialization;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamField;
import java.util.EventObject;

public class Notification extends EventObject
{
    private static final long serialVersionUID;
    private static final ObjectStreamField[] serialPersistentFields;
    private String type;
    private long sequenceNumber;
    private String message;
    private long timeStamp;
    private Object userData;
    private Object mySource;
    
    public Notification(final String type, final Object source, final long sequenceNumber) {
        super(source);
        this.type = null;
        this.sequenceNumber = 0L;
        this.message = null;
        this.timeStamp = System.currentTimeMillis();
        this.userData = null;
        this.mySource = null;
        this.mySource = source;
        this.type = type;
        this.sequenceNumber = sequenceNumber;
        this.timeStamp = System.currentTimeMillis();
    }
    
    public Notification(final String type, final Object source, final long sequenceNumber, final String message) {
        this(type, source, sequenceNumber);
        this.message = message;
        this.timeStamp = System.currentTimeMillis();
    }
    
    public Notification(final String type, final Object source, final long sequenceNumber, final long timeStamp) {
        this(type, source, sequenceNumber);
        this.timeStamp = timeStamp;
    }
    
    public Notification(final String type, final Object source, final long sequenceNumber, final long timeStamp, final String message) {
        this(type, source, sequenceNumber, timeStamp);
        this.message = message;
    }
    
    public Object getSource() {
        return this.mySource;
    }
    
    public void setSource(final Object source) {
        Label_0078: {
            if (source instanceof String) {
                try {
                    super.source = new ObjectName((String)source);
                    break Label_0078;
                }
                catch (MalformedObjectNameException e) {
                    throw new IllegalArgumentException("malformed object name: " + source);
                }
            }
            if (!(source instanceof ObjectName)) {
                throw new IllegalArgumentException("Notification source must be an object name");
            }
            super.source = source;
        }
        this.mySource = super.source;
    }
    
    public long getSequenceNumber() {
        return this.sequenceNumber;
    }
    
    public void setSequenceNumber(final long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
    
    public String getType() {
        return this.type;
    }
    
    public long getTimeStamp() {
        return this.timeStamp;
    }
    
    public void setTimeStamp(final long timeStamp) {
        this.timeStamp = timeStamp;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public Object getUserData() {
        return this.userData;
    }
    
    public void setUserData(final Object userData) {
        this.userData = userData;
    }
    
    public String toString() {
        final StringBuffer tmp = new StringBuffer(this.getClass().getName());
        tmp.append('[');
        tmp.append("source=");
        tmp.append(this.getSource());
        tmp.append(",type=");
        tmp.append(this.getType());
        tmp.append(",sequenceNumber=");
        tmp.append(this.getSequenceNumber());
        tmp.append(",timeStamp=");
        tmp.append(this.getTimeStamp());
        tmp.append(",message=");
        tmp.append(this.getMessage());
        tmp.append(",userData=");
        tmp.append(this.getUserData());
        tmp.append(']');
        return tmp.toString();
    }
    
    private void readObject(final ObjectInputStream ois) throws IOException, ClassNotFoundException {
        final ObjectInputStream.GetField getField = ois.readFields();
        this.message = (String)getField.get("message", null);
        this.sequenceNumber = getField.get("sequenceNumber", 0L);
        this.mySource = getField.get("source", null);
        this.timeStamp = getField.get("timeStamp", 0L);
        this.type = (String)getField.get("type", null);
        this.userData = getField.get("userData", null);
        this.source = this.mySource;
    }
    
    private void writeObject(final ObjectOutputStream oos) throws IOException {
        final ObjectOutputStream.PutField putField = oos.putFields();
        putField.put("message", this.message);
        putField.put("sequenceNumber", this.sequenceNumber);
        putField.put("source", this.mySource);
        putField.put("timeStamp", this.timeStamp);
        putField.put("type", this.type);
        putField.put("userData", this.userData);
        oos.writeFields();
    }
    
    static {
        serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("message", String.class), new ObjectStreamField("sequenceNumber", Long.TYPE), new ObjectStreamField("source", Object.class), new ObjectStreamField("timeStamp", Long.TYPE), new ObjectStreamField("type", String.class), new ObjectStreamField("userData", Object.class) };
        switch (Serialization.version) {
            case 10: {
                serialVersionUID = 1716977971058914352L;
                break;
            }
            default: {
                serialVersionUID = -7516092053498031989L;
                break;
            }
        }
    }
}
