// 
// Decompiled by Procyon v0.5.30
// 

package irc.managers;

import java.util.Enumeration;
import irc.com.utils.MySQL;
import irc.com.utils.MD5Nick;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import irc.main;
import irc.EIRC;

public class CONF
{
    private static EIRC eirc;
    
    public static String GetMessage() {
        final StringBuilder sb = new StringBuilder();
        CONF.eirc.getAccueil();
        final File file = new File(sb.append(main.homeapp).append("\\conf").toString());
        if (file.exists()) {
            try {
                final String line;
                if ((line = new BufferedReader(new FileReader(file)).readLine()) != null) {
                    return line;
                }
            }
            catch (IOException ex) {}
        }
        return "";
    }
    
    public static String GetMessaged(final String s) {
        final MySQL mySQL = new MySQL("http://" + main.http + ".chat-land.org/modules/sun/messaged/get.php?a=" + s.toLowerCase() + "&b=" + MD5Nick.getEncodedNick(MD5Nick.getEncodedNick(s.toLowerCase())).substring(3, 10));
        mySQL.execute();
        final String return1 = mySQL.getReturn();
        mySQL.reset();
        return return1.trim();
    }
    
    public static String GetMperso(final String s) {
        final MySQL mySQL = new MySQL("http://" + main.http + ".chat-land.org/modules/sun/mperso/get.php");
        mySQL.addParam("a", s.toLowerCase());
        mySQL.addParam("b", MD5Nick.getEncodedNick(MD5Nick.getEncodedNick(s.toLowerCase())).substring(3, 10));
        mySQL.execute();
        String return1 = mySQL.getReturn();
        mySQL.reset();
        if (return1 == null) {
            return1 = "";
        }
        return return1;
    }
    
    public static String GetSound() {
        final StringBuilder sb = new StringBuilder();
        CONF.eirc.getAccueil();
        final File file = new File(sb.append(main.homeapp).append("\\Sound").toString());
        if (file.exists()) {
            try {
                final String line;
                if ((line = new BufferedReader(new FileReader(file)).readLine()) != null) {
                    return line;
                }
            }
            catch (IOException ex) {}
        }
        return "";
    }
    
    public static void init(final EIRC eirc) {
        CONF.eirc = eirc;
    }
    
    public static void SetMessage(final String message) {
        final SaveFile saveFile2;
        final SaveFile saveFile = saveFile2 = new SaveFile();
        final StringBuilder sb = new StringBuilder();
        CONF.eirc.getAccueil();
        saveFile2.setFilePath(sb.append(main.homeapp).append("\\conf").toString());
        saveFile.setMessage(message);
        saveFile.start();
    }
    
    public static void SetMessaged(final String s, final String s2, final String s3) {
        final MySQL mySQL = new MySQL("http://" + main.http + ".chat-land.org/modules/sun/messaged/set.php");
        mySQL.addParam("a", s.toLowerCase());
        mySQL.addParam("b", MD5Nick.getEncodedNick(MD5Nick.getEncodedNick(s.toLowerCase())).substring(3, 10));
        mySQL.addParam("c", s2);
        mySQL.addParam("d", s3);
        mySQL.execute();
    }
    
    public static void SetMperso(final String s, final String s2) {
        if (s2.toLowerCase().indexOf("www") != -1 || s2.toLowerCase().indexOf("http") != -1 || s2.indexOf("@") != -1 || s2.toLowerCase().indexOf(".com") != -1 || s2.toLowerCase().indexOf(".fr") != -1 || s2.toLowerCase().indexOf(".org") != -1 || s2.toLowerCase().indexOf(".tn") != -1 || s2.toLowerCase().indexOf(".net") != -1) {
            return;
        }
        final String string = "http://" + main.http + ".chat-land.org/modules/sun/mperso/set.php?a=" + s.toLowerCase() + "&b=" + MD5Nick.getEncodedNick(MD5Nick.getEncodedNick(s.toLowerCase())).substring(3, 10) + "&c=" + s2.trim().replaceAll(" ", "%20") + "&d=" + MD5Nick.getEncodedNick(MD5Nick.getEncodedNick(s2)).substring(3, 10);
        final MonThread monThread = new MonThread();
        monThread.setLien(string);
        monThread.start();
        CONF.eirc.sendMessage("PRIVMSG", new String[] { "#message-perso-spam", s2 });
        final String[] array = { "", "[mp]" };
        final Enumeration<String> keys = (Enumeration<String>)CONF.eirc.getPrivates().privates.keys();
        while (keys.hasMoreElements()) {
            array[0] = keys.nextElement();
            CONF.eirc.sendMessage("NOTICE", array);
        }
    }
    
    public static void SetSound(final String message) {
        final SaveFile saveFile2;
        final SaveFile saveFile = saveFile2 = new SaveFile();
        final StringBuilder sb = new StringBuilder();
        CONF.eirc.getAccueil();
        saveFile2.setFilePath(sb.append(main.homeapp).append("\\Sound").toString());
        saveFile.setMessage(message);
        saveFile.start();
    }
}
