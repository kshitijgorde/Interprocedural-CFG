// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms.msg.event;

import com.stonewall.cornerstone.utility.Namespaces;
import org.xmodel.xml.IXmlIO;
import com.stonewall.cornerstone.utility.ModelBuilder;
import java.text.SimpleDateFormat;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.jms.msg.JmsMessage;
import com.stonewall.cornerstone.utility.Transaction;
import java.util.Date;
import com.stonewall.cornerstone.security.User;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.utility.IdentityFactory;
import com.stonewall.cornerstone.jms.msg.Message;

public class Event extends Message
{
    static final ThreadLocal<String> correlation;
    protected static IdentityFactory identityFactory;
    private Log log;
    
    static {
        correlation = new ThreadLocal<String>();
        Event.identityFactory = new IdentityFactory();
    }
    
    public Event(final String rootTag, final Log log) {
        super(rootTag);
        final User user = User.getCurrent();
        final String userid = user.getId();
        this.setId(Event.identityFactory.next());
        this.setTimestamp(new Date().getTime());
        this.setUserid(userid);
        final Transaction t = Transaction.getCurrent();
        if (t != null) {
            setThreadCorrelation(t.getId());
        }
        this.setCorrelation(Event.correlation.get());
        this.log = log;
    }
    
    public Event(final JmsMessage message, final IModelObject root) throws Exception {
        super(message, root);
    }
    
    public Event(final IModelObject e, final Log log) {
        super(e);
        this.log = log;
    }
    
    public String getId() {
        return this.root.getID();
    }
    
    public void setId(final String value) {
        this.root.setAttribute("id", value);
    }
    
    public void setId() {
        this.setId(Event.identityFactory.next());
    }
    
    public String getUserid() {
        return Xlate.get(this.root.getFirstChild("evt:userid"), (String)null);
    }
    
    public void setUserid(final String value) {
        final IModelObject e = this.root.getCreateChild("evt:userid");
        e.setValue(value);
    }
    
    public Type getType() {
        return Type.valueOf(this.root);
    }
    
    public void send() {
        final Topic topic = this.getTopic();
        this.log.debug("Event sent: " + this);
        super._send(topic.getDestination());
    }
    
    protected Topic getTopic() {
        return new Topic(this.getType());
    }
    
    public long getTimestamp() {
        return new Long(Xlate.get(this.root.getFirstChild("evt:timestamp"), (String)null));
    }
    
    public void setTimestamp(final long value) {
        final IModelObject e = this.root.getCreateChild("evt:timestamp");
        final Date date = new Date(value);
        final SimpleDateFormat sdf = new SimpleDateFormat();
        e.setAttribute("view", sdf.format(date));
        e.setValue(String.valueOf(value));
    }
    
    public String getViewableTS() {
        return Xlate.get(this.root.getFirstChild("evt:timestamp"), "view", (String)null);
    }
    
    public static void setThreadCorrelation(final String c) {
        Event.correlation.set(c);
    }
    
    public static String getThreadCorrelation() {
        return Event.correlation.get();
    }
    
    public String getCorrelation() {
        return Xlate.get(this.root.getFirstChild("evt:correlation"), (String)null);
    }
    
    public void setCorrelation(final String value) {
        final IModelObject e = this.root.getCreateChild("evt:correlation");
        e.setValue(value);
    }
    
    @Override
    public String toString() {
        final ModelBuilder builder = new ModelBuilder();
        return builder.writeModel(this.getRoot(), IXmlIO.Style.printable);
    }
    
    public Enum getAction() {
        return Action.unknown;
    }
    
    public Enum getStatus() {
        return Status.unknown;
    }
    
    public enum Action
    {
        unknown("unknown", 0);
        
        private Action(final String s, final int n) {
        }
    }
    
    public enum Status
    {
        unknown("unknown", 0);
        
        private Status(final String s, final int n) {
        }
    }
    
    public enum Type
    {
        alarm("alarm", 0, "Alarm"), 
        db("db", 1, "Database"), 
        security("security", 2, "Security"), 
        report("report", 3, "Report"), 
        process("process", 4, "Process"), 
        service("service", 5, "Service"), 
        mail("mail", 6, "Mail"), 
        timesync("timesync", 7, "Time Sync"), 
        heartbeat("heartbeat", 8, "Heartbeat");
        
        private String display;
        
        private Type(final String s, final int n, final String display) {
            this.display = display;
        }
        
        public String getDisplayValue() {
            return this.display;
        }
        
        public String getQualifiedName() {
            return Namespaces.evtns.getQualifiedName(this.name());
        }
        
        public static Type valueOf(final IModelObject o) {
            final String tag = o.getType();
            return valueOf(Namespaces.evtns.getUnqualifiedName(tag));
        }
    }
}
