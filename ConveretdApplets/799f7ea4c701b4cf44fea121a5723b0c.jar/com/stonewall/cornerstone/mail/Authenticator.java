// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.mail;

import javax.mail.PasswordAuthentication;

public class Authenticator extends javax.mail.Authenticator
{
    protected PasswordAuthentication getPasswordAuthentication() {
        final String username = System.getProperty("cornerstone.mail.username");
        final String password = System.getProperty("cornerstone.mail.password");
        return new PasswordAuthentication(username, password);
    }
}
