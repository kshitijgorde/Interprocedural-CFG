// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.mail;

import javax.mail.Multipart;
import javax.mail.BodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.MessagingException;
import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import org.xmodel.log.Log;
import javax.mail.Message;

public class MailMessage
{
    private Message message;
    private String text;
    private String filename;
    Log log;
    
    MailMessage(final Session session) {
        this.log = MailSession.log;
        this.message = (Message)new MimeMessage(session);
    }
    
    public void setFrom(final String from) {
        try {
            this.message.addFrom((Address[])new InternetAddress[] { new InternetAddress(from) });
        }
        catch (Exception e) {
            this.log.error(this, e);
        }
    }
    
    public void setSubject(final String subject) {
        try {
            this.message.setSubject(subject);
        }
        catch (MessagingException e) {
            this.log.error(this, (Throwable)e);
        }
    }
    
    public void setText(final String text) {
        this.text = text;
    }
    
    public void setAttachment(final String filename) {
        this.filename = filename;
    }
    
    Message getMessage() {
        try {
            if (this.text != null && this.filename != null) {
                final MimeBodyPart part1 = new MimeBodyPart();
                part1.setText(this.text);
                final MimeBodyPart part2 = new MimeBodyPart();
                part2.attachFile(this.filename);
                final MimeMultipart multi = new MimeMultipart();
                multi.addBodyPart((BodyPart)part1);
                multi.addBodyPart((BodyPart)part2);
                this.message.setContent((Multipart)multi);
            }
            else {
                this.message.setText(this.text);
            }
        }
        catch (Exception e) {
            this.log.error(this, e);
        }
        return this.message;
    }
}
