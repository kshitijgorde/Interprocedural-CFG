// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.mail;

import javax.mail.Address;
import java.util.List;
import java.util.ArrayList;
import com.stonewall.cornerstone.jms.msg.event.MailEvent;
import javax.mail.event.TransportEvent;
import javax.mail.event.ConnectionEvent;
import org.xmodel.log.Log;
import javax.mail.event.TransportListener;
import javax.mail.event.ConnectionListener;

public class MailListener implements ConnectionListener, TransportListener
{
    Log log;
    
    public MailListener() {
        this.log = MailSession.log;
    }
    
    public void closed(final ConnectionEvent event) {
        this.log.debug("Mail connection closed");
    }
    
    public void disconnected(final ConnectionEvent event) {
        this.log.debug("Mail connection disconnected");
    }
    
    public void opened(final ConnectionEvent event) {
        this.log.debug("Mail connection opened");
    }
    
    public void messageDelivered(final TransportEvent event) {
        this.sendEvent(MailEvent.Status.success, event);
    }
    
    public void messageNotDelivered(final TransportEvent event) {
        this.sendEvent(MailEvent.Status.fail, event);
    }
    
    public void messagePartiallyDelivered(final TransportEvent event) {
        this.sendEvent(MailEvent.Status.partial, event);
    }
    
    private void sendEvent(final MailEvent.Status s, final TransportEvent event) {
        final List<String> validSent = new ArrayList<String>();
        Address[] array = event.getValidSentAddresses();
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                validSent.add(array[i].toString());
            }
        }
        final List<String> validUnsent = new ArrayList<String>();
        array = event.getValidUnsentAddresses();
        if (array != null) {
            for (int j = 0; j < array.length; ++j) {
                validUnsent.add(array[j].toString());
            }
        }
        final List<String> invalid = new ArrayList<String>();
        array = event.getInvalidAddresses();
        if (array != null) {
            for (int k = 0; k < array.length; ++k) {
                invalid.add(array[k].toString());
            }
        }
        final MailEvent me = new MailEvent(s, validSent, validUnsent, invalid);
        me.send();
    }
}
