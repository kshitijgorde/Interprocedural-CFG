// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms.msg.event;

import java.util.Iterator;
import org.xmodel.Element;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.jms.msg.JmsMessage;
import java.util.List;
import org.xmodel.log.Log;

public class MailEvent extends Event
{
    static final String Tag;
    static final Log log;
    
    static {
        Tag = Type.mail.getQualifiedName();
        log = Log.getLog(MailEvent.class);
    }
    
    public static void send(final Status s, final List<String> validSent, final List<String> validUnsent, final List<String> invalid) {
        new MailEvent(s, validSent, validUnsent, invalid).send();
    }
    
    public MailEvent(final JmsMessage message, final IModelObject root) throws Exception {
        super(message, root);
    }
    
    public MailEvent(final IModelObject e) {
        super(e, MailEvent.log);
    }
    
    public MailEvent(final Status s, final List<String> validSent, final List<String> validUnsent, final List<String> invalid) {
        super(MailEvent.Tag, MailEvent.log);
        this.setStatus(s);
        this.setValidSent(validSent);
        this.setValidUnsent(validUnsent);
        this.setInvalid(invalid);
    }
    
    public void setStatus(final Status s) {
        final IModelObject statusElement = new Element("evt:status");
        statusElement.setValue(s.name());
        this.root.addChild(statusElement);
    }
    
    public void setValidSent(final List<String> addresses) {
        final IModelObject e = new Element("evt:validSent");
        for (final String s : addresses) {
            final IModelObject o = new Element("evt:address");
            o.setValue(s);
            e.addChild(o);
        }
        this.root.addChild(e);
    }
    
    public void setValidUnsent(final List<String> addresses) {
        final IModelObject e = new Element("evt:validUnsent");
        for (final String s : addresses) {
            final IModelObject o = new Element("evt:address");
            o.setValue(s);
            e.addChild(o);
        }
        this.root.addChild(e);
    }
    
    public void setInvalid(final List<String> addresses) {
        final IModelObject e = new Element("evt:invalid");
        for (final String s : addresses) {
            final IModelObject o = new Element("evt:address");
            o.setValue(s);
            e.addChild(o);
        }
        this.root.addChild(e);
    }
    
    public enum Status
    {
        success("success", 0), 
        fail("fail", 1), 
        partial("partial", 2);
        
        private Status(final String s, final int n) {
        }
    }
}
