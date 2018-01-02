// 
// Decompiled by Procyon v0.5.30
// 

package bpac;

public class b
{
    public static String b(final String paramString) {
        String str = "";
        final String s1 = "0X:kZRYfput?GbPL6irn8qUDO/A1FNxC_dzW=S3v-eIaQsmlV.T4Hg#h52yK&owE%jcB79JM";
        final String s2 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ/.:_-?&=%#";
        for (int i = 0; i < paramString.length(); ++i) {
            final String s3 = paramString.substring(i, i + 1);
            final int j = s1.indexOf(s3);
            if (j > -1) {
                str += s2.substring(j, j + 1);
            }
        }
        return str;
    }
}
