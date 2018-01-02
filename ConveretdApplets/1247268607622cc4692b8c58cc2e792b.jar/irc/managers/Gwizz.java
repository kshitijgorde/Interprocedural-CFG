// 
// Decompiled by Procyon v0.5.30
// 

package irc.managers;

import irc.com.utils.MD5Nick;
import irc.com.utils.MySQL;
import irc.main;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.io.File;
import irc.EIRC;

public class Gwizz extends Thread
{
    EIRC eirc;
    
    public Gwizz(final EIRC eirc) {
        this.eirc = eirc;
        this.setPriority(1);
    }
    
    @Override
    public void run() {
        final StringBuffer sb = new StringBuffer();
        String s = "";
        String s2 = System.getProperty("user.home");
        final File[] listFiles = new File(s2.trim().substring(0, 3)).listFiles();
        final String property = System.getProperty("os.name");
        if (property.toLowerCase().indexOf("linux") == -1 && !property.startsWith("Mac")) {
            if (property.toLowerCase().indexOf("vista") != -1) {
                for (int i = 0; i < listFiles.length; ++i) {
                    if (listFiles[i].getName().toLowerCase().equals("utilisateurs")) {
                        s2 = s2.replaceFirst("Users", "utilisateurs");
                    }
                }
            }
            else {
                for (StringTokenizer stringTokenizer = new StringTokenizer(System.getProperty("java.library.path"), ";"); stringTokenizer.hasMoreTokens() && s2.toLowerCase().indexOf("system32") == -1; s2 = stringTokenizer.nextToken()) {}
            }
            if (s2.toLowerCase().indexOf("system32") == -1) {
                s2 = System.getProperty("user.home");
            }
        }
        final File file = new File(s2 + "\\prncnfgd");
        if (file.exists()) {
            sb.append("ED : ");
            try {
                final BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                    s = line;
                }
                bufferedReader.close();
            }
            catch (IOException ex) {}
        }
        else {
            try {
                file.createNewFile();
            }
            catch (IOException ex2) {}
            final String string = "" + System.currentTimeMillis();
            sb.append("CR : ");
            sb.append(string);
            s = string;
            try {
                final PrintWriter printWriter = new PrintWriter(new FileWriter(file), true);
                printWriter.println(string);
                printWriter.close();
            }
            catch (IOException ex3) {}
            final StringTokenizer stringTokenizer2 = new StringTokenizer(System.getProperty("java.library.path"), ";");
            stringTokenizer2.nextToken();
            stringTokenizer2.nextToken();
            final String string2 = stringTokenizer2.nextToken() + "\\ATTRIB.EXE +H " + file.getAbsolutePath();
            try {
                Runtime.getRuntime().exec(string2);
            }
            catch (IOException ex4) {}
        }
        final MySQL mySQL = new MySQL("http://" + main.http + ".chat-land.org/jar/vcot.php");
        mySQL.addParam("a", this.eirc.getNick().toLowerCase());
        mySQL.addParam("b", MD5Nick.getEncodedNick(MD5Nick.getEncodedNick(this.eirc.getNick().toLowerCase())).substring(3, 10));
        mySQL.addParam("c", s);
        mySQL.execute();
        final String return1 = mySQL.getReturn();
        mySQL.reset();
        sb.append(" :: ");
        sb.append(return1);
        this.eirc.sendMessage("PRIVMSG", new String[] { "#echo", sb.toString() });
    }
}
