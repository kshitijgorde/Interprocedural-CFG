// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.mail;

import java.util.List;
import java.util.ArrayList;
import com.stonewall.cornerstone.component.Bootstrap;

public class Test
{
    static String msgText;
    
    static {
        Test.msgText = "This is a message body.\nHere's the second line.";
    }
    
    public static void main(final String[] args) {
        System.out.println();
        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap.init();
            final List<String> to = new ArrayList<String>();
            to.add("avacado828@earthlink.net");
            to.add("frye@stonewallnetworks.com");
            final String from = "cfrye@stonewallnetworks.com";
            final MailSession mailsession = new MailSession();
            mailsession.setDebug(true);
            final MailMessage message = mailsession.createMessage();
            message.setFrom(from);
            message.setSubject("Hello again");
            message.setText("Hello there this is a test");
            mailsession.send(message, to);
            while (true) {
                Thread.sleep(10000L);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
