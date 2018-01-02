// 
// Decompiled by Procyon v0.5.30
// 

package anon.tor.util;

public final class Base16
{
    public static String encode(final byte[] array) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(encodeByte((byte)(0xF & array[i] >> 4)));
            sb.append(encodeByte((byte)(0xF & array[i])));
        }
        return sb.toString();
    }
    
    private static char encodeByte(final byte b) {
        switch (b) {
            case 0: {
                return '0';
            }
            case 1: {
                return '1';
            }
            case 2: {
                return '2';
            }
            case 3: {
                return '3';
            }
            case 4: {
                return '4';
            }
            case 5: {
                return '5';
            }
            case 6: {
                return '6';
            }
            case 7: {
                return '7';
            }
            case 8: {
                return '8';
            }
            case 9: {
                return '9';
            }
            case 10: {
                return 'A';
            }
            case 11: {
                return 'B';
            }
            case 12: {
                return 'C';
            }
            case 13: {
                return 'D';
            }
            case 14: {
                return 'E';
            }
            case 15: {
                return 'F';
            }
            default: {
                return '0';
            }
        }
    }
}
