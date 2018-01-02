// 
// Decompiled by Procyon v0.5.30
// 

final class Censor
{
    private static int[] anIntArray620;
    private static char[][] aCharArrayArray621;
    private static byte[][][] aByteArrayArrayArray622;
    private static char[][] aCharArrayArray623;
    private static char[][] aCharArrayArray624;
    private static int[] anIntArray625;
    private static final String[] exceptions;
    
    public static void loadConfig(final StreamLoader streamLoader) {
        readValues(new Stream(streamLoader.getDataForName("fragmentsenc.txt")), new Stream(streamLoader.getDataForName("badenc.txt")), new Stream(streamLoader.getDataForName("domainenc.txt")), new Stream(streamLoader.getDataForName("tldlist.txt")));
    }
    
    private static void readValues(final Stream stream, final Stream stream2, final Stream stream3, final Stream stream4) {
        readBadEnc(stream2);
        readDomainEnc(stream3);
        readFragmentsEnc(stream);
        readTldList(stream4);
    }
    
    private static void readTldList(final Stream stream) {
        final int dWord = stream.readDWord();
        Censor.aCharArrayArray624 = new char[dWord][];
        Censor.anIntArray625 = new int[dWord];
        for (int i = 0; i < dWord; ++i) {
            Censor.anIntArray625[i] = stream.readUnsignedByte();
            final char[] array = new char[stream.readUnsignedByte()];
            for (int j = 0; j < array.length; ++j) {
                array[j] = (char)stream.readUnsignedByte();
            }
            Censor.aCharArrayArray624[i] = array;
        }
    }
    
    private static void readBadEnc(final Stream stream) {
        final int dWord = stream.readDWord();
        Censor.aCharArrayArray621 = new char[dWord][];
        Censor.aByteArrayArrayArray622 = new byte[dWord][][];
        method493(stream, Censor.aCharArrayArray621, Censor.aByteArrayArrayArray622);
    }
    
    private static void readDomainEnc(final Stream stream) {
        method494(Censor.aCharArrayArray623 = new char[stream.readDWord()][], stream);
    }
    
    private static void readFragmentsEnc(final Stream stream) {
        Censor.anIntArray620 = new int[stream.readDWord()];
        for (int i = 0; i < Censor.anIntArray620.length; ++i) {
            Censor.anIntArray620[i] = stream.readUnsignedWord();
        }
    }
    
    private static void method493(final Stream stream, final char[][] array, final byte[][][] array2) {
        for (int i = 0; i < array.length; ++i) {
            final char[] array3 = new char[stream.readUnsignedByte()];
            for (int j = 0; j < array3.length; ++j) {
                array3[j] = (char)stream.readUnsignedByte();
            }
            array[i] = array3;
            final byte[][] array4 = new byte[stream.readUnsignedByte()][2];
            for (int k = 0; k < array4.length; ++k) {
                array4[k][0] = (byte)stream.readUnsignedByte();
                array4[k][1] = (byte)stream.readUnsignedByte();
            }
            if (array4.length > 0) {
                array2[i] = array4;
            }
        }
    }
    
    private static void method494(final char[][] array, final Stream stream) {
        for (int i = 0; i < array.length; ++i) {
            final char[] array2 = new char[stream.readUnsignedByte()];
            for (int j = 0; j < array2.length; ++j) {
                array2[j] = (char)stream.readUnsignedByte();
            }
            array[i] = array2;
        }
    }
    
    private static void method495(final char[] array) {
        int n = 0;
        for (int i = 0; i < array.length; ++i) {
            if (method496(array[i])) {
                array[n] = array[i];
            }
            else {
                array[n] = ' ';
            }
            if (n == 0 || array[n] != ' ' || array[n - 1] != ' ') {
                ++n;
            }
        }
        for (int j = n; j < array.length; ++j) {
            array[j] = ' ';
        }
    }
    
    private static boolean method496(final char c) {
        return (c >= ' ' && c <= '\u007f') || c == ' ' || c == '\n' || c == '\t' || c == '£' || c == '\u20ac';
    }
    
    public static String doCensor(final String s) {
        System.currentTimeMillis();
        final char[] charArray = s.toCharArray();
        method495(charArray);
        final String trim = new String(charArray).trim();
        final char[] charArray2 = trim.toLowerCase().toCharArray();
        final String lowerCase = trim.toLowerCase();
        method505(charArray2);
        method500(charArray2);
        method501(charArray2);
        method514(charArray2);
        for (int i = 0; i < Censor.exceptions.length; ++i) {
            int index = -1;
            while ((index = lowerCase.indexOf(Censor.exceptions[i], index + 1)) != -1) {
                final char[] charArray3 = Censor.exceptions[i].toCharArray();
                System.arraycopy(charArray3, 0, charArray2, index, charArray3.length);
            }
        }
        method498(trim.toCharArray(), charArray2);
        method499(charArray2);
        System.currentTimeMillis();
        return new String(charArray2).trim();
    }
    
    private static void method498(final char[] array, final char[] array2) {
        for (int i = 0; i < array.length; ++i) {
            if (array2[i] != '*' && isUpperCaseLetter(array[i])) {
                array2[i] = array[i];
            }
        }
    }
    
    private static void method499(final char[] array) {
        int n = 1;
        for (int i = 0; i < array.length; ++i) {
            final char c = array[i];
            if (isLetter(c)) {
                if (n != 0) {
                    if (isLowerCaseLetter(c)) {
                        n = 0;
                    }
                }
                else if (isUpperCaseLetter(c)) {
                    array[i] = (char)(c + 'a' - 'A');
                }
            }
            else {
                n = 1;
            }
        }
    }
    
    private static void method500(final char[] array) {
        for (int i = 0; i < 2; ++i) {
            for (int j = Censor.aCharArrayArray621.length - 1; j >= 0; --j) {
                method509(Censor.aByteArrayArrayArray622[j], array, Censor.aCharArrayArray621[j]);
            }
        }
    }
    
    private static void method501(final char[] array) {
        final char[] array2 = array.clone();
        method509(null, array2, new char[] { '(', 'a', ')' });
        final char[] array3 = array.clone();
        method509(null, array3, new char[] { 'd', 'o', 't' });
        for (int i = Censor.aCharArrayArray623.length - 1; i >= 0; --i) {
            method502(array, Censor.aCharArrayArray623[i], array3, array2);
        }
    }
    
    private static void method502(final char[] array, final char[] array2, final char[] array3, final char[] array4) {
        if (array2.length > array.length) {
            return;
        }
        int n2;
        for (int i = 0; i <= array.length - array2.length; i += n2) {
            int j = i;
            int n = 0;
            n2 = 1;
            while (j < array.length) {
                final char c = array[j];
                char c2 = '\0';
                if (j + 1 < array.length) {
                    c2 = array[j + 1];
                }
                final int method511;
                if (n < array2.length && (method511 = method511(c, array2[n], c2)) > 0) {
                    j += method511;
                    ++n;
                }
                else {
                    if (n == 0) {
                        break;
                    }
                    final int method512;
                    if ((method512 = method511(c, array2[n - 1], c2)) > 0) {
                        j += method512;
                        if (n != 1) {
                            continue;
                        }
                        ++n2;
                    }
                    else {
                        if (n >= array2.length) {
                            break;
                        }
                        if (!method517(c)) {
                            break;
                        }
                        ++j;
                    }
                }
            }
            if (n >= array2.length) {
                boolean b = false;
                final int method513 = method503(array, array4, i);
                final int method514 = method504(array3, j - 1, array);
                if (method513 > 2 || method514 > 2) {
                    b = true;
                }
                if (b) {
                    for (int k = i; k < j; ++k) {
                        array[k] = '*';
                    }
                }
            }
        }
    }
    
    private static int method503(final char[] array, final char[] array2, final int n) {
        if (n == 0) {
            return 2;
        }
        for (int n2 = n - 1; n2 >= 0 && method517(array[n2]); --n2) {
            if (array[n2] == '@') {
                return 3;
            }
        }
        int n3 = 0;
        for (int n4 = n - 1; n4 >= 0 && method517(array2[n4]); --n4) {
            if (array2[n4] == '*') {
                ++n3;
            }
        }
        if (n3 >= 3) {
            return 4;
        }
        return method517(array[n - 1]) ? 1 : 0;
    }
    
    private static int method504(final char[] array, final int n, final char[] array2) {
        if (n + 1 == array2.length) {
            return 2;
        }
        for (int n2 = n + 1; n2 < array2.length && method517(array2[n2]); ++n2) {
            if (array2[n2] == '.' || array2[n2] == ',') {
                return 3;
            }
        }
        int n3 = 0;
        for (int n4 = n + 1; n4 < array2.length && method517(array[n4]); ++n4) {
            if (array[n4] == '*') {
                ++n3;
            }
        }
        if (n3 >= 3) {
            return 4;
        }
        return method517(array2[n + 1]) ? 1 : 0;
    }
    
    private static void method505(final char[] array) {
        final char[] array2 = array.clone();
        method509(null, array2, new char[] { 'd', 'o', 't' });
        final char[] array3 = array.clone();
        method509(null, array3, new char[] { 's', 'l', 'a', 's', 'h' });
        for (int i = 0; i < Censor.aCharArrayArray624.length; ++i) {
            method506(array3, Censor.aCharArrayArray624[i], Censor.anIntArray625[i], array2, array);
        }
    }
    
    private static void method506(final char[] array, final char[] array2, final int n, final char[] array3, final char[] array4) {
        if (array2.length > array4.length) {
            return;
        }
        int n3;
        for (int i = 0; i <= array4.length - array2.length; i += n3) {
            int j = i;
            int n2 = 0;
            n3 = 1;
            while (j < array4.length) {
                final char c = array4[j];
                char c2 = '\0';
                if (j + 1 < array4.length) {
                    c2 = array4[j + 1];
                }
                final int method511;
                if (n2 < array2.length && (method511 = method511(c, array2[n2], c2)) > 0) {
                    j += method511;
                    ++n2;
                }
                else {
                    if (n2 == 0) {
                        break;
                    }
                    final int method512;
                    if ((method512 = method511(c, array2[n2 - 1], c2)) > 0) {
                        j += method512;
                        if (n2 != 1) {
                            continue;
                        }
                        ++n3;
                    }
                    else {
                        if (n2 >= array2.length) {
                            break;
                        }
                        if (!method517(c)) {
                            break;
                        }
                        ++j;
                    }
                }
            }
            if (n2 >= array2.length) {
                boolean b = false;
                final int method513 = method507(array4, i, array3);
                final int method514 = method508(array4, array, j - 1);
                if (n == 1 && method513 > 0 && method514 > 0) {
                    b = true;
                }
                if (n == 2 && ((method513 > 2 && method514 > 0) || (method513 > 0 && method514 > 2))) {
                    b = true;
                }
                if (n == 3 && method513 > 0 && method514 > 2) {
                    b = true;
                }
                final boolean b2 = n == 3 && method513 > 2 && method514 > 0;
                if (b) {
                    int n4 = i;
                    int n5 = j - 1;
                    if (method513 > 2) {
                        if (method513 == 4) {
                            int n6 = 0;
                            for (int k = n4 - 1; k >= 0; --k) {
                                if (n6 != 0) {
                                    if (array3[k] != '*') {
                                        break;
                                    }
                                    n4 = k;
                                }
                                else if (array3[k] == '*') {
                                    n4 = k;
                                    n6 = 1;
                                }
                            }
                        }
                        int n7 = 0;
                        for (int l = n4 - 1; l >= 0; --l) {
                            if (n7 != 0) {
                                if (method517(array4[l])) {
                                    break;
                                }
                                n4 = l;
                            }
                            else if (!method517(array4[l])) {
                                n7 = 1;
                                n4 = l;
                            }
                        }
                    }
                    if (method514 > 2) {
                        if (method514 == 4) {
                            int n8 = 0;
                            for (int n9 = n5 + 1; n9 < array4.length; ++n9) {
                                if (n8 != 0) {
                                    if (array[n9] != '*') {
                                        break;
                                    }
                                    n5 = n9;
                                }
                                else if (array[n9] == '*') {
                                    n5 = n9;
                                    n8 = 1;
                                }
                            }
                        }
                        int n10 = 0;
                        for (int n11 = n5 + 1; n11 < array4.length; ++n11) {
                            if (n10 != 0) {
                                if (method517(array4[n11])) {
                                    break;
                                }
                                n5 = n11;
                            }
                            else if (!method517(array4[n11])) {
                                n10 = 1;
                                n5 = n11;
                            }
                        }
                    }
                    for (int n12 = n4; n12 <= n5; ++n12) {
                        array4[n12] = '*';
                    }
                }
            }
        }
    }
    
    private static int method507(final char[] array, final int n, final char[] array2) {
        if (n == 0) {
            return 2;
        }
        for (int n2 = n - 1; n2 >= 0 && method517(array[n2]); --n2) {
            if (array[n2] == ',' || array[n2] == '.') {
                return 3;
            }
        }
        int n3 = 0;
        for (int n4 = n - 1; n4 >= 0 && method517(array2[n4]); --n4) {
            if (array2[n4] == '*') {
                ++n3;
            }
        }
        if (n3 >= 3) {
            return 4;
        }
        return method517(array[n - 1]) ? 1 : 0;
    }
    
    private static int method508(final char[] array, final char[] array2, final int n) {
        if (n + 1 == array.length) {
            return 2;
        }
        for (int n2 = n + 1; n2 < array.length && method517(array[n2]); ++n2) {
            if (array[n2] == '\\' || array[n2] == '/') {
                return 3;
            }
        }
        int n3 = 0;
        for (int n4 = n + 1; n4 < array.length && method517(array2[n4]); ++n4) {
            if (array2[n4] == '*') {
                ++n3;
            }
        }
        if (n3 >= 5) {
            return 4;
        }
        return method517(array[n + 1]) ? 1 : 0;
    }
    
    private static void method509(final byte[][] array, final char[] array2, final char[] array3) {
        if (array3.length > array2.length) {
            return;
        }
        int n4;
        for (int i = 0; i <= array2.length - array3.length; i += n4) {
            int n = i;
            int n2 = 0;
            int n3 = 0;
            n4 = 1;
            boolean b = false;
            int n5 = 0;
            int n6 = 0;
            while (n < array2.length && (n5 == 0 || n6 == 0)) {
                final char c = array2[n];
                char c2 = '\0';
                if (n + 1 < array2.length) {
                    c2 = array2[n + 1];
                }
                final int method512;
                if (n2 < array3.length && (method512 = method512(c2, c, array3[n2])) > 0) {
                    if (method512 == 1 && isDigit(c)) {
                        n5 = 1;
                    }
                    if (method512 == 2 && (isDigit(c) || isDigit(c2))) {
                        n5 = 1;
                    }
                    n += method512;
                    ++n2;
                }
                else {
                    if (n2 == 0) {
                        break;
                    }
                    final int method513;
                    if ((method513 = method512(c2, c, array3[n2 - 1])) > 0) {
                        n += method513;
                        if (n2 != 1) {
                            continue;
                        }
                        ++n4;
                    }
                    else {
                        if (n2 >= array3.length) {
                            break;
                        }
                        if (!method518(c)) {
                            break;
                        }
                        if (method517(c) && c != '\'') {
                            b = true;
                        }
                        if (isDigit(c)) {
                            n6 = 1;
                        }
                        ++n;
                        if (++n3 * 100 / (n - i) > 90) {
                            break;
                        }
                        continue;
                    }
                }
            }
            if (n2 >= array3.length && (n5 == 0 || n6 == 0)) {
                boolean b2 = true;
                if (!b) {
                    char c3 = ' ';
                    if (i - 1 >= 0) {
                        c3 = array2[i - 1];
                    }
                    char c4 = ' ';
                    if (n < array2.length) {
                        c4 = array2[n];
                    }
                    final byte method514 = method513(c3);
                    final byte method515 = method513(c4);
                    if (array != null && method510(method514, array, method515)) {
                        b2 = false;
                    }
                }
                else {
                    boolean b3 = false;
                    boolean b4 = false;
                    if (i - 1 < 0 || (method517(array2[i - 1]) && array2[i - 1] != '\'')) {
                        b3 = true;
                    }
                    if (n >= array2.length || (method517(array2[n]) && array2[n] != '\'')) {
                        b4 = true;
                    }
                    if (!b3 || !b4) {
                        int n7 = 0;
                        int n8 = i - 2;
                        if (b3) {
                            n8 = i;
                        }
                        while (n7 == 0 && n8 < n) {
                            if (n8 >= 0 && (!method517(array2[n8]) || array2[n8] == '\'')) {
                                final char[] array4 = new char[3];
                                int n9;
                                for (n9 = 0; n9 < 3 && n8 + n9 < array2.length && (!method517(array2[n8 + n9]) || array2[n8 + n9] == '\''); ++n9) {
                                    array4[n9] = array2[n8 + n9];
                                }
                                boolean b5 = true;
                                if (n9 == 0) {
                                    b5 = false;
                                }
                                if (n9 < 3 && n8 - 1 >= 0 && (!method517(array2[n8 - 1]) || array2[n8 - 1] == '\'')) {
                                    b5 = false;
                                }
                                if (b5 && !method523(array4)) {
                                    n7 = 1;
                                }
                            }
                            ++n8;
                        }
                        if (n7 == 0) {
                            b2 = false;
                        }
                    }
                }
                if (b2) {
                    int n10 = 0;
                    int n11 = 0;
                    int n12 = -1;
                    for (int j = i; j < n; ++j) {
                        if (isDigit(array2[j])) {
                            ++n10;
                        }
                        else if (isLetter(array2[j])) {
                            ++n11;
                            n12 = j;
                        }
                    }
                    if (n12 > -1) {
                        n10 -= n - 1 - n12;
                    }
                    if (n10 <= n11) {
                        for (int k = i; k < n; ++k) {
                            array2[k] = '*';
                        }
                    }
                    else {
                        n4 = 1;
                    }
                }
            }
        }
    }
    
    private static boolean method510(final byte b, final byte[][] array, final byte b2) {
        int n = 0;
        if (array[n][0] == b && array[n][1] == b2) {
            return true;
        }
        int n2 = array.length - 1;
        if (array[n2][0] == b && array[n2][1] == b2) {
            return true;
        }
        do {
            final int n3 = (n + n2) / 2;
            if (array[n3][0] == b && array[n3][1] == b2) {
                return true;
            }
            if (b < array[n3][0] || (b == array[n3][0] && b2 < array[n3][1])) {
                n2 = n3;
            }
            else {
                n = n3;
            }
        } while (n != n2 && n + 1 != n2);
        return false;
    }
    
    private static int method511(final char c, final char c2, final char c3) {
        if (c2 == c) {
            return 1;
        }
        if (c2 == 'o' && c == '0') {
            return 1;
        }
        if (c2 == 'o' && c == '(' && c3 == ')') {
            return 2;
        }
        return ((c2 == 'c' && (c == '(' || c == '<' || c == '[')) || (c2 == 'e' && c == '\u20ac') || (c2 == 's' && c == '$') || (c2 == 'l' && c == 'i')) ? 1 : 0;
    }
    
    private static int method512(final char c, final char c2, final char c3) {
        if (c3 == c2) {
            return 1;
        }
        if (c3 >= 'a' && c3 <= 'm') {
            if (c3 == 'a') {
                if (c2 == '4' || c2 == '@' || c2 == '^') {
                    return 1;
                }
                return (c2 != '/' || c != '\\') ? 0 : 2;
            }
            else if (c3 == 'b') {
                if (c2 == '6' || c2 == '8') {
                    return 1;
                }
                return ((c2 != '1' || c != '3') && (c2 != 'i' || c != '3')) ? 0 : 2;
            }
            else {
                if (c3 == 'c') {
                    return (c2 == '(' || c2 == '<' || c2 == '{' || c2 == '[') ? 1 : 0;
                }
                if (c3 == 'd') {
                    return ((c2 != '[' || c != ')') && (c2 != 'i' || c != ')')) ? 0 : 2;
                }
                if (c3 == 'e') {
                    return (c2 == '3' || c2 == '\u20ac') ? 1 : 0;
                }
                if (c3 == 'f') {
                    if (c2 == 'p' && c == 'h') {
                        return 2;
                    }
                    return (c2 == '£') ? 1 : 0;
                }
                else {
                    if (c3 == 'g') {
                        return (c2 == '9' || c2 == '6' || c2 == 'q') ? 1 : 0;
                    }
                    if (c3 == 'h') {
                        return (c2 == '#') ? 1 : 0;
                    }
                    if (c3 == 'i') {
                        return (c2 == 'y' || c2 == 'l' || c2 == 'j' || c2 == '1' || c2 == '!' || c2 == ':' || c2 == ';' || c2 == '|') ? 1 : 0;
                    }
                    if (c3 == 'j') {
                        return 0;
                    }
                    if (c3 == 'k') {
                        return 0;
                    }
                    if (c3 == 'l') {
                        return (c2 == '1' || c2 == '|' || c2 == 'i') ? 1 : 0;
                    }
                    if (c3 == 'm') {
                        return 0;
                    }
                }
            }
        }
        if (c3 >= 'n' && c3 <= 'z') {
            if (c3 == 'n') {
                return 0;
            }
            if (c3 == 'o') {
                if (c2 == '0' || c2 == '*') {
                    return 1;
                }
                return ((c2 != '(' || c != ')') && (c2 != '[' || c != ']') && (c2 != '{' || c != '}') && (c2 != '<' || c != '>')) ? 0 : 2;
            }
            else {
                if (c3 == 'p') {
                    return 0;
                }
                if (c3 == 'q') {
                    return 0;
                }
                if (c3 == 'r') {
                    return 0;
                }
                if (c3 == 's') {
                    return (c2 == '5' || c2 == 'z' || c2 == '$' || c2 == '2') ? 1 : 0;
                }
                if (c3 == 't') {
                    return (c2 == '7' || c2 == '+') ? 1 : 0;
                }
                if (c3 == 'u') {
                    if (c2 == 'v') {
                        return 1;
                    }
                    return ((c2 != '\\' || c != '/') && (c2 != '\\' || c != '|') && (c2 != '|' || c != '/')) ? 0 : 2;
                }
                else {
                    if (c3 == 'v') {
                        return ((c2 != '\\' || c != '/') && (c2 != '\\' || c != '|') && (c2 != '|' || c != '/')) ? 0 : 2;
                    }
                    if (c3 == 'w') {
                        return (c2 != 'v' || c != 'v') ? 0 : 2;
                    }
                    if (c3 == 'x') {
                        return ((c2 != ')' || c != '(') && (c2 != '}' || c != '{') && (c2 != ']' || c != '[') && (c2 != '>' || c != '<')) ? 0 : 2;
                    }
                    if (c3 == 'y') {
                        return 0;
                    }
                    if (c3 == 'z') {
                        return 0;
                    }
                }
            }
        }
        if (c3 >= '0' && c3 <= '9') {
            if (c3 != '0') {
                return (c3 == '1' && c2 == 'l') ? 1 : 0;
            }
            if (c2 == 'o' || c2 == 'O') {
                return 1;
            }
            return ((c2 != '(' || c != ')') && (c2 != '{' || c != '}') && (c2 != '[' || c != ']')) ? 0 : 2;
        }
        else {
            if (c3 == ',') {
                return (c2 == '.') ? 1 : 0;
            }
            if (c3 == '.') {
                return (c2 == ',') ? 1 : 0;
            }
            return (c3 == '!' && c2 == 'i') ? 1 : 0;
        }
    }
    
    private static byte method513(final char c) {
        if (c >= 'a' && c <= 'z') {
            return (byte)(c - 'a' + '\u0001');
        }
        if (c == '\'') {
            return 28;
        }
        if (c >= '0' && c <= '9') {
            return (byte)(c - '0' + '\u001d');
        }
        return 27;
    }
    
    private static void method514(final char[] array) {
        int method516 = 0;
        int n = 0;
        int n2 = 0;
        int method517;
        while ((method517 = method515(array, method516)) != -1) {
            int n3 = 0;
            for (int n4 = method516; n4 >= 0 && n4 < method517 && n3 == 0; ++n4) {
                if (!method517(array[n4]) && !method518(array[n4])) {
                    n3 = 1;
                }
            }
            if (n3 != 0) {
                n = 0;
            }
            if (n == 0) {
                n2 = method517;
            }
            method516 = method516(array, method517);
            int n5 = 0;
            for (int i = method517; i < method516; ++i) {
                n5 = n5 * 10 + array[i] - 48;
            }
            if (n5 > 255 || method516 - method517 > 8) {
                n = 0;
            }
            else {
                ++n;
            }
            if (n == 4) {
                for (int j = n2; j < method516; ++j) {
                    array[j] = '*';
                }
                n = 0;
            }
        }
    }
    
    private static int method515(final char[] array, final int n) {
        for (int n2 = n; n2 < array.length && n2 >= 0; ++n2) {
            if (array[n2] >= '0' && array[n2] <= '9') {
                return n2;
            }
        }
        return -1;
    }
    
    private static int method516(final char[] array, final int n) {
        for (int n2 = n; n2 < array.length && n2 >= 0; ++n2) {
            if (array[n2] < '0' || array[n2] > '9') {
                return n2;
            }
        }
        return array.length;
    }
    
    private static boolean method517(final char c) {
        return !isLetter(c) && !isDigit(c);
    }
    
    private static boolean method518(final char c) {
        return c < 'a' || c > 'z' || c == 'v' || c == 'x' || c == 'j' || c == 'q' || c == 'z';
    }
    
    private static boolean isLetter(final char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
    
    private static boolean isDigit(final char c) {
        return c >= '0' && c <= '9';
    }
    
    private static boolean isLowerCaseLetter(final char c) {
        return c >= 'a' && c <= 'z';
    }
    
    private static boolean isUpperCaseLetter(final char c) {
        return c >= 'A' && c <= 'Z';
    }
    
    private static boolean method523(final char[] array) {
        boolean b = true;
        for (int i = 0; i < array.length; ++i) {
            if (!isDigit(array[i]) && array[i] != '\0') {
                b = false;
            }
        }
        if (b) {
            return true;
        }
        final int method524 = method524(array);
        int n = 0;
        int n2 = Censor.anIntArray620.length - 1;
        if (method524 == Censor.anIntArray620[n] || method524 == Censor.anIntArray620[n2]) {
            return true;
        }
        do {
            final int n3 = (n + n2) / 2;
            if (method524 == Censor.anIntArray620[n3]) {
                return true;
            }
            if (method524 < Censor.anIntArray620[n3]) {
                n2 = n3;
            }
            else {
                n = n3;
            }
        } while (n != n2 && n + 1 != n2);
        return false;
    }
    
    private static int method524(final char[] array) {
        if (array.length > 6) {
            return 0;
        }
        int n = 0;
        for (int i = 0; i < array.length; ++i) {
            final char c = array[array.length - i - 1];
            if (c >= 'a' && c <= 'z') {
                n = n * 38 + (c - 'a' + '\u0001');
            }
            else if (c == '\'') {
                n = n * 38 + 27;
            }
            else if (c >= '0' && c <= '9') {
                n = n * 38 + (c - '0' + '\u001c');
            }
            else if (c != '\0') {
                return 0;
            }
        }
        return n;
    }
    
    static {
        exceptions = new String[] { "cook", "cook's", "cooks", "seeks", "sheet", "woop", "woops", "faq", "noob", "noobs" };
    }
}
