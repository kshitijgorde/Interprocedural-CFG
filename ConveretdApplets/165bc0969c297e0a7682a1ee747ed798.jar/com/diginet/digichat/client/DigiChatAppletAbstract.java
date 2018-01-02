// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.esial.util.c;
import java.applet.Applet;

public abstract class DigiChatAppletAbstract extends Applet
{
    public static final String strVers = "DigiPlus 2010 Version 9.0";
    public static final String strHome = "http://www.digi-plus.com";
    public static String OEM_DigiChat;
    public static String langName;
    public static int initialWindowHeight;
    public static int initialWindowWidth;
    public static int preferredTheme;
    public static DigiChatAppletAbstract applet;
    public static q login;
    public static boolean embedded;
    public static boolean lite;
    public static int preferredPort;
    public static String fontOverride1;
    public static String fontOverride1b;
    public static String fontOverride2;
    public static String fontOverride2b;
    public static String altHost;
    public i chatUser;
    
    public abstract void reset();
    
    public abstract void init();
    
    public abstract void stop();
    
    static {
        DigiChatAppletAbstract.OEM_DigiChat = c.a("DigiPlus");
        DigiChatAppletAbstract.initialWindowHeight = 0;
        DigiChatAppletAbstract.initialWindowWidth = 0;
        DigiChatAppletAbstract.preferredTheme = -1;
        DigiChatAppletAbstract.applet = null;
        DigiChatAppletAbstract.login = null;
        DigiChatAppletAbstract.embedded = false;
        DigiChatAppletAbstract.lite = false;
        DigiChatAppletAbstract.preferredPort = 0;
        DigiChatAppletAbstract.fontOverride1 = null;
        DigiChatAppletAbstract.fontOverride1b = null;
        DigiChatAppletAbstract.fontOverride2 = null;
        DigiChatAppletAbstract.fontOverride2b = null;
        DigiChatAppletAbstract.altHost = null;
    }
}
