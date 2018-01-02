// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.mail;

import java.util.Hashtable;
import javax.mail.Provider;
import javax.mail.Message;
import javax.mail.Transport;
import java.util.Iterator;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Address;
import javax.mail.event.TransportListener;
import javax.mail.event.ConnectionListener;
import java.util.Date;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.List;
import java.util.Properties;
import org.xmodel.log.Log;
import javax.mail.Session;

public class MailSession
{
    private Session session;
    private String host;
    private Authenticator auth;
    private MailListener listener;
    public static Log log;
    
    static {
        MailSession.log = Log.getLog(MailSession.class);
    }
    
    public MailSession() {
        this.listener = new MailListener();
        this.auth = new Authenticator();
        this.host = System.getProperty("cornerstone.mail.host");
        if (this.host == null) {
            MailSession.log.info("Mail host not set.");
        }
        else {
            final Properties props = new Properties();
            ((Hashtable<String, String>)props).put("mail.smtp.host", this.host);
            this.session = Session.getInstance(props, (javax.mail.Authenticator)this.auth);
        }
    }
    
    public boolean send(final MailMessage message, final List<String> to) {
        if (this.session == null) {
            MailSession.log.info("Trying to send mail when session is null");
            return false;
        }
        final InternetAddress[] addresses = new InternetAddress[to.size()];
        int i = 0;
        for (final String s : to) {
            try {
                addresses[i++] = new InternetAddress(s);
            }
            catch (AddressException ae) {
                MailSession.log.error(this, (Throwable)ae);
            }
        }
        Transport transport = null;
        try {
            final Message msg = message.getMessage();
            msg.setSentDate(new Date());
            msg.setHeader("X-Mailer", "smtpsend");
            msg.saveChanges();
            final Provider provider = this.session.getProvider("smtp");
            transport = this.session.getTransport(provider);
            if (this.listener != null) {
                transport.addConnectionListener((ConnectionListener)this.listener);
                transport.addTransportListener((TransportListener)this.listener);
            }
            if (this.auth != null) {
                transport.connect(this.auth.getPasswordAuthentication().getUserName(), this.auth.getPasswordAuthentication().getPassword());
            }
            else {
                transport.connect();
            }
            try {
                InternetAddress[] array;
                for (int length = (array = addresses).length, j = 0; j < length; ++j) {
                    final InternetAddress address = array[j];
                    transport.sendMessage(msg, (Address[])new InternetAddress[] { address });
                }
            }
            catch (SendFailedException sfe) {
                if (this.listener == null) {
                    MailSession.log.error(this, (Throwable)sfe);
                }
            }
            return true;
        }
        catch (Exception e) {
            MailSession.log.error(this, e);
            return false;
        }
        finally {
            if (transport != null) {
                try {
                    transport.close();
                }
                catch (MessagingException e2) {
                    MailSession.log.error(this, (Throwable)e2);
                }
            }
        }
    }
    
    public MailMessage createMessage() {
        return new MailMessage(this.session);
    }
    
    public void setListener(final MailListener listener) {
        this.listener = listener;
    }
    
    public void setDebug(final boolean value) {
        this.session.setDebug(value);
        final Properties props = this.session.getProperties();
        ((Hashtable<String, Boolean>)props).put("mail.debug", value);
    }
}
