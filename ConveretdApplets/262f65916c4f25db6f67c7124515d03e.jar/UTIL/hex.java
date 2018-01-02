// 
// Decompiled by Procyon v0.5.30
// 

package UTIL;

public class hex
{
    public String cH2S(final String s) {
        final String[] split = s.split("\\.");
        String string = new String();
        for (int i = 0; i < split.length; ++i) {
            string += (char)Integer.parseInt(split[i], 16);
        }
        return string;
    }
    
    public String H2HS(int[] array) {
        array = array;
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            if (i == array.length - 1) {
                sb.append(Integer.toHexString(array[i]));
            }
            else {
                sb.append(Integer.toHexString(array[i] & 0xFF));
                sb.append(".");
            }
        }
        return sb.toString();
    }
    
    public int[] HS2H(String s) {
        final String[] split;
        final int[] array = new int[(split = (s = s).split("\\.")).length];
        for (int i = 0; i < split.length; ++i) {
            array[i] = Integer.parseInt(split[i], 16);
        }
        return array;
    }
}
