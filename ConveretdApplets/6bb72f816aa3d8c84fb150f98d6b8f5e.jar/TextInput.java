// 
// Decompiled by Procyon v0.5.30
// 

final class TextInput
{
    private static final boolean aBoolean630 = true;
    private static final char[] aCharArray631;
    private static final Stream stream;
    private static final char[] validChars;
    
    public static String method525(final int n, final Stream stream) {
        int n2 = 0;
        int n3 = -1;
        for (int i = 0; i < n; ++i) {
            final int unsignedByte = stream.readUnsignedByte();
            final int n4 = unsignedByte >> 4 & 0xF;
            if (n3 == -1) {
                if (n4 < 13) {
                    TextInput.aCharArray631[n2++] = TextInput.validChars[n4];
                }
                else {
                    n3 = n4;
                }
            }
            else {
                TextInput.aCharArray631[n2++] = TextInput.validChars[(n3 << 4) + n4 - 195];
                n3 = -1;
            }
            final int n5 = unsignedByte & 0xF;
            if (n3 == -1) {
                if (n5 < 13) {
                    TextInput.aCharArray631[n2++] = TextInput.validChars[n5];
                }
                else {
                    n3 = n5;
                }
            }
            else {
                TextInput.aCharArray631[n2++] = TextInput.validChars[(n3 << 4) + n5 - 195];
                n3 = -1;
            }
        }
        int n6 = 1;
        for (int j = 0; j < n2; ++j) {
            final char c = TextInput.aCharArray631[j];
            if (n6 != 0 && c >= 'a' && c <= 'z') {
                final char[] aCharArray631 = TextInput.aCharArray631;
                final int n7 = j;
                aCharArray631[n7] += '\uffe0';
                n6 = 0;
            }
            if (c == '.' || c == '!' || c == '?') {
                n6 = 1;
            }
        }
        return new String(TextInput.aCharArray631, 0, n2);
    }
    
    public static void method526(String s, final Stream stream) {
        if (s.length() > 80) {
            s = s.substring(0, 80);
        }
        s = s.toLowerCase();
        int n = -1;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            int n2 = 0;
            for (int j = 0; j < TextInput.validChars.length; ++j) {
                if (char1 == TextInput.validChars[j]) {
                    n2 = j;
                    break;
                }
            }
            if (n2 > 12) {
                n2 += 195;
            }
            if (n == -1) {
                if (n2 < 13) {
                    n = n2;
                }
                else {
                    stream.writeWordBigEndian(n2);
                }
            }
            else if (n2 < 13) {
                stream.writeWordBigEndian((n << 4) + n2);
                n = -1;
            }
            else {
                stream.writeWordBigEndian((n << 4) + (n2 >> 4));
                n = (n2 & 0xF);
            }
        }
        if (n != -1) {
            stream.writeWordBigEndian(n << 4);
        }
    }
    
    public static String processText(final String s) {
        TextInput.stream.currentOffset = 0;
        method526(s, TextInput.stream);
        final int currentOffset = TextInput.stream.currentOffset;
        TextInput.stream.currentOffset = 0;
        return method525(currentOffset, TextInput.stream);
    }
    
    static {
        aCharArray631 = new char[100];
        stream = new Stream(new byte[100]);
        validChars = new char[] { ' ', 'e', 't', 'a', 'o', 'i', 'h', 'n', 's', 'r', 'd', 'l', 'u', 'm', 'w', 'c', 'y', 'f', 'g', 'p', 'b', 'v', 'k', 'x', 'j', 'q', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' ', '!', '?', '.', ',', ':', ';', '(', ')', '-', '&', '*', '\\', '\'', '#', '+', '=', '£', '$', '%', '\"', '[', ']' };
    }
}
