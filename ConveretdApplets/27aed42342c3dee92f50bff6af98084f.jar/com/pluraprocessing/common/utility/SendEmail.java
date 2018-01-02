// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.utility;

import java.util.Hashtable;
import java.util.Properties;
import javax.mail.Transport;
import java.util.Date;
import javax.mail.Message;
import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.Session;
import java.util.Iterator;
import java.util.ArrayList;

public class SendEmail
{
    private static String hostName;
    
    static {
        SendEmail.hostName = "127.0.0.1";
    }
    
    public static void send(final ArrayList<String> toList, final String from, final String subject, final String body) throws Exception {
        final StringBuffer toSB = new StringBuffer();
        for (final String temp : toList) {
            toSB.append(temp).append(",");
        }
        send(toSB.toString(), from, subject, body);
    }
    
    public static void send(final String to, final String from, final String subject, final String body) throws Exception {
        final Properties props = System.getProperties();
        ((Hashtable<String, String>)props).put("mail.smtp.localhost", SendEmail.hostName);
        final Session session = Session.getDefaultInstance(props, (Authenticator)null);
        final Message msg = (Message)new MimeMessage(session);
        msg.setFrom((Address)new InternetAddress(from));
        msg.setRecipients(Message.RecipientType.TO, (Address[])InternetAddress.parse(to, false));
        msg.setSubject(subject);
        msg.setText(body);
        msg.setSentDate(new Date());
        Transport.send(msg);
    }
}
