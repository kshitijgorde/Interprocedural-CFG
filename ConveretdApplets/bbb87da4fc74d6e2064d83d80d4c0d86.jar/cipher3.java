// 
// Decompiled by Procyon v0.5.30
// 

public class cipher3 implements cipher
{
    private int getKeySize() {
        final int round = Math.round((float)Math.abs(Math.floor(Math.random() * 17.0)));
        if (round > 7) {
            return round;
        }
        return round + 7;
    }
    
    private int setKey() {
        final int round = Math.round((float)Math.abs(Math.floor(Math.random() * 32.0)));
        if (round > 0) {
            return round;
        }
        return 31;
    }
    
    private static void printKeys(final char[] array) {
        for (int i = 0; i < array.length; ++i) {
            System.out.println("key[" + i + "]=" + (int)new Integer(array[i]) + " > " + Integer.toBinaryString(array[i]));
        }
    }
    
    private String encodeKey(final String s) {
        final char[] array = new char[s.length()];
        s.toLowerCase().getChars(0, s.length(), array, 0);
        for (int i = 0; i < array.length; ++i) {
            if (Character.isLetter(array[i])) {
                array[i] -= '`';
            }
            else {
                array[i] -= '\u0015';
            }
        }
        return new String(array);
    }
    
    private String decodeKey(final String s) {
        final char[] array = new char[s.length()];
        s.getChars(0, s.length(), array, 0);
        for (int i = 0; i < array.length; ++i) {
            if (array[i] < '\u001b') {
                array[i] += '`';
            }
            else {
                array[i] += '\u0015';
            }
        }
        return new String(array);
    }
    
    public String getKey(final String s) {
        final StringBuffer sb = new StringBuffer(s);
        final int keySize = this.getKeySize();
        final char[] array = new char[keySize];
        for (int i = 0; i < keySize; ++i) {
            array[i] = this.checkKey(i, this.setKey(), keySize, sb);
        }
        return this.decodeKey(new String(array));
    }
    
    private char checkKey(final int n, int i, final int n2, final StringBuffer sb) {
        final boolean[] array = new boolean[32];
        final int n3 = i;
        do {
            int j;
            for (j = n; j < sb.length(); j += n2) {
                if ((sb.charAt(j) ^ i) == 0x20) {
                    array[i] = true;
                    break;
                }
                if ((sb.charAt(j) ^ i) == 0x22) {
                    array[i] = true;
                    break;
                }
                if ((sb.charAt(j) ^ i) == 0x27) {
                    array[i] = true;
                    break;
                }
                if ((sb.charAt(j) ^ i) == 0x7F) {
                    array[i] = true;
                    break;
                }
            }
            if (j >= sb.length()) {
                return (char)i;
            }
            do {
                i = ((i < 31) ? (++i) : 1);
            } while (array[i] && i != n3);
        } while (i != n3);
        i = 0;
        return (char)i;
    }
    
    public String encode(final String s, String encodeKey) {
        final StringBuffer sb = new StringBuffer();
        final char[] array = new char[encodeKey.length()];
        encodeKey = this.encodeKey(encodeKey);
        encodeKey.getChars(0, encodeKey.length(), array, 0);
        final int length = array.length;
        final char[] array2 = new char[s.length()];
        s.getChars(0, s.length(), array2, 0);
        for (int i = 0; i < array2.length; ++i) {
            sb.append((char)(array2[i] ^ array[i % length]));
        }
        return sb.toString();
    }
    
    public String decode(final String s, final String s2) {
        return this.encode(s, s2);
    }
}
