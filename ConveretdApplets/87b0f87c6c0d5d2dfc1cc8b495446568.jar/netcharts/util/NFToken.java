// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.io.FileInputStream;
import java.util.Hashtable;
import java.util.Vector;
import java.io.InputStream;

public class NFToken
{
    public static final int QUOTE = 1;
    public static final int WORD = 2;
    public static final int SYMBOL = 3;
    public static final int WHITE = 4;
    public static final int DIGIT = 5;
    public static final int COMMENT = 6;
    public boolean returnQuotes;
    public boolean processEscapes;
    private static int[] a;
    private static int[] b;
    private static boolean c;
    private NFTokenInput d;
    private int[] e;
    private int[] f;
    private StringBuffer g;
    private static final int h = 1;
    private static final int i = 2;
    private static final int j = 3;
    
    public NFToken() {
        this.returnQuotes = true;
        this.processEscapes = true;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = new StringBuffer();
        if (NFUtil.getJDKVersion() < 1.1) {
            this.d = new NFTokenInput();
        }
        else if (NFToken.c) {
            this.d = new NFTokenInput11();
        }
        else {
            this.d = new NFTokenInput();
        }
        this.e = new int[256];
        this.f = new int[256];
        for (int i = 0; i < 256; ++i) {
            this.e[i] = NFToken.a[i];
            this.f[i] = NFToken.b[i];
        }
    }
    
    public void setInput(final String input) {
        this.d.setInput(input);
    }
    
    public void setInput(final StringBuffer input) {
        this.d.setInput(input);
    }
    
    public void setInput(final InputStream input) {
        this.d.setInput(input);
    }
    
    public void setInput(final InputStream inputStream, final boolean b) {
        this.d.setInput(inputStream, b);
    }
    
    public void setInput(final NFTokenInput d) {
        if (this.d != null) {
            this.d.close();
        }
        this.d = d;
    }
    
    public void setInput(final Object o) {
        if (o instanceof String) {
            this.setInput((String)o);
            return;
        }
        if (o instanceof StringBuffer) {
            this.setInput((StringBuffer)o);
            return;
        }
        if (o instanceof InputStream) {
            this.setInput((InputStream)o);
            return;
        }
        if (o instanceof NFTokenInput) {
            this.setInput((NFTokenInput)o);
        }
    }
    
    public void close() {
        if (this.d != null) {
            this.d.close();
        }
    }
    
    public void setCharType(final int n, final int n2, final int n3) {
        if (n2 >= 0 && n2 < 256 && (this.e[n2] = n) == 1) {
            this.f[n2] = n3;
        }
    }
    
    public void setCharRange(final int n, final int n2, final int n3) {
        for (int n4 = n2; n4 <= n3 && n4 < 256; ++n4) {
            this.e[n4] = n;
        }
    }
    
    public StringBuffer nextToken() throws Exception {
        return this.nextToken(null);
    }
    
    public StringBuffer nextToken(StringBuffer g) throws Exception {
        if (g == null) {
            g = this.g;
            g.setLength(0);
        }
        do {
            this.skipWhiteSpace();
        } while (this.skipComment());
        final int nextChar = this.d.nextChar();
        if (nextChar == -1) {
            return null;
        }
        if (nextChar > 255) {
            g.append((char)nextChar);
            return g;
        }
        Label_0180: {
            switch (this.e[nextChar]) {
                case 2: {
                    this.a(nextChar, g);
                    break;
                }
                case 5: {
                    this.b(nextChar, g);
                    break;
                }
                case 1: {
                    this.c(nextChar, g);
                    break;
                }
                default: {
                    switch (nextChar) {
                        case 43:
                        case 45:
                        case 46: {
                            this.b(nextChar, g);
                            break Label_0180;
                        }
                        default: {
                            g.append((char)nextChar);
                            break Label_0180;
                        }
                    }
                    break;
                }
            }
        }
        return g;
    }
    
    private void a(int nextChar, final StringBuffer sb) throws Exception {
        sb.append((char)nextChar);
        while (true) {
            nextChar = this.d.nextChar();
            if (nextChar == -1) {
                return;
            }
            if (nextChar > 255) {
                this.d.pushBack(nextChar);
                return;
            }
            switch (this.e[nextChar]) {
                case 2:
                case 5: {
                    sb.append((char)nextChar);
                    continue;
                }
                default: {
                    this.d.pushBack(nextChar);
                }
            }
        }
    }
    
    private void b(int nextChar, final StringBuffer sb) throws Exception {
        int n = 1;
        int n2 = 0;
        sb.append((char)nextChar);
        if (nextChar == 46) {
            n = 2;
        }
        while (true) {
            nextChar = this.d.nextChar();
            if (nextChar == -1) {
                return;
            }
            if (nextChar < 256 && this.e[nextChar] == 5) {
                sb.append((char)nextChar);
                if (n != 3) {
                    continue;
                }
                ++n2;
            }
            else {
                switch (nextChar) {
                    case 46: {
                        if (n != 1) {
                            this.d.pushBack(nextChar);
                            return;
                        }
                        n = 2;
                        sb.append((char)nextChar);
                        continue;
                    }
                    case 69:
                    case 101: {
                        if (n == 3) {
                            this.d.pushBack(nextChar);
                            return;
                        }
                        n = 3;
                        n2 = 0;
                        sb.append((char)nextChar);
                        continue;
                    }
                    case 43:
                    case 45: {
                        if (n != 3 || n2 > 0) {
                            this.d.pushBack(nextChar);
                            return;
                        }
                        ++n2;
                        sb.append((char)nextChar);
                        continue;
                    }
                    default: {
                        this.d.pushBack(nextChar);
                    }
                }
            }
        }
    }
    
    private void c(final int n, final StringBuffer sb) throws Exception {
        int n2 = 0;
        if (this.returnQuotes) {
            sb.append((char)n);
        }
        while (true) {
            final int nextChar = this.d.nextChar();
            if (nextChar == -1) {
                return;
            }
            if (n2 != 0) {
                switch (nextChar) {
                    case 110: {
                        sb.append('\n');
                        break;
                    }
                    case 114: {
                        sb.append('\r');
                        break;
                    }
                    case 116: {
                        sb.append('\t');
                        break;
                    }
                    case 123: {
                        sb.append('{');
                        break;
                    }
                    case 125: {
                        sb.append('}');
                        break;
                    }
                    case 34: {
                        sb.append('\"');
                        break;
                    }
                    case 39: {
                        sb.append('\'');
                        break;
                    }
                    case 92: {
                        sb.append('\\');
                        break;
                    }
                    case 48: {
                        this.a(sb, 3);
                        break;
                    }
                    case 120: {
                        this.b(sb, 2);
                        break;
                    }
                    case 117: {
                        this.b(sb, 4);
                        break;
                    }
                    default: {
                        sb.append('\\');
                        sb.append((char)nextChar);
                        break;
                    }
                }
                n2 = 0;
            }
            else if (nextChar == 92 && this.processEscapes) {
                n2 = 1;
            }
            else {
                if (nextChar == this.f[n]) {
                    if (this.returnQuotes) {
                        sb.append((char)this.f[n]);
                    }
                    return;
                }
                sb.append((char)nextChar);
            }
        }
    }
    
    private void a(final StringBuffer sb, final int n) throws Exception {
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            final int nextChar = this.d.nextChar();
            if (nextChar == -1) {
                break;
            }
            if (nextChar > 255 || this.e[nextChar] != 5) {
                this.d.pushBack(nextChar);
                break;
            }
            n2 = (n2 << 3) + (nextChar - 48);
        }
        sb.append((char)n2);
    }
    
    private void b(final StringBuffer sb, final int n) throws Exception {
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            final int nextChar = this.d.nextChar();
            if (nextChar == -1) {
                break;
            }
            if (nextChar > 255) {
                this.d.pushBack(nextChar);
                break;
            }
            if (this.e[nextChar] == 5) {
                n2 = (n2 << 4) + (nextChar - 48);
            }
            else if (nextChar >= 97 && nextChar <= 102) {
                n2 = (n2 << 4) + (10 + (nextChar - 97));
            }
            else {
                if (nextChar < 65 || nextChar > 70) {
                    this.d.pushBack(nextChar);
                    break;
                }
                n2 = (n2 << 4) + (10 + (nextChar - 65));
            }
        }
        sb.append((char)n2);
    }
    
    public NFKeyValue nextStatement() throws Exception {
        final StringBuffer nextToken = this.nextToken();
        if (nextToken == null) {
            return null;
        }
        final String string = nextToken.toString();
        final Vector value = new Vector();
        final NFKeyValue nfKeyValue = new NFKeyValue();
        nfKeyValue.key = string;
        nfKeyValue.value = value;
        this.getParamSymbol('=');
        this.getParamVector(value, string.toLowerCase().startsWith("debug"));
        this.getParamSymbol(';');
        return nfKeyValue;
    }
    
    public static boolean isQuoted(final String s) {
        final int length = s.length();
        if (length < 2) {
            return false;
        }
        final char char1 = s.charAt(0);
        if (char1 > '\u00ff' || NFToken.a[char1] != 1) {
            return false;
        }
        final char char2 = s.charAt(length - 1);
        return char2 <= '\u00ff' && char2 == NFToken.b[char1];
    }
    
    public static void setSupportInternationalization(final boolean c) {
        NFToken.c = c;
    }
    
    public static String stripQuotes(final String s) {
        if (!isQuoted(s)) {
            return s;
        }
        return s.substring(1, s.length() - 1);
    }
    
    public static StringBuffer doEscapes(final String s, final StringBuffer sb) {
        return doEscapes(s, sb, true);
    }
    
    public static StringBuffer doEscapes(final String s, StringBuffer sb, final boolean b) {
        if (sb == null) {
            sb = new StringBuffer();
        }
        if (s == null) {
            return sb;
        }
        int length = s.length();
        int i = 0;
        final boolean quoted = isQuoted(s);
        if (b && quoted) {
            sb.append(s.charAt(0));
            i = 1;
            --length;
        }
        while (i < length) {
            final char char1 = s.charAt(i);
            switch (char1) {
                case 13: {
                    sb.append("\\r");
                    break;
                }
                case 10: {
                    sb.append("\\n");
                    break;
                }
                case 9: {
                    sb.append("\\t");
                    break;
                }
                case 34: {
                    sb.append("\\\"");
                    break;
                }
                case 39: {
                    sb.append("\\'");
                    break;
                }
                case 123: {
                    sb.append("\\{");
                    break;
                }
                case 125: {
                    sb.append("\\}");
                    break;
                }
                default: {
                    if (char1 <= '\u00ff') {
                        sb.append(char1);
                        break;
                    }
                    sb.append(NFUtil.sprintf("\\u%04x", new Long(char1)));
                    break;
                }
            }
            ++i;
        }
        if (b && quoted) {
            sb.append(s.charAt(length));
        }
        return sb;
    }
    
    public boolean skipComment() throws Exception {
        final int nextChar = this.d.nextChar();
        if (nextChar == -1) {
            return false;
        }
        if (nextChar > 255 || this.e[nextChar] != 6) {
            this.d.pushBack(nextChar);
            return false;
        }
        while (true) {
            final int nextChar2 = this.d.nextChar();
            if (nextChar2 == -1) {
                return true;
            }
            switch ((char)nextChar2) {
                case '\n':
                case '\r':
                case ';': {
                    return true;
                }
                default: {
                    continue;
                }
            }
        }
    }
    
    public boolean isWhiteSpace(final int n) {
        return n >= 0 && n < 256 && this.e[n] == 4;
    }
    
    public void skipWhiteSpace() throws Exception {
        int nextChar;
        do {
            nextChar = this.d.nextChar();
            if (nextChar == -1) {
                return;
            }
        } while (nextChar <= 255 && this.e[nextChar] == 4);
        this.d.pushBack(nextChar);
    }
    
    public boolean getParamSymbol(final char c) throws Exception {
        this.skipWhiteSpace();
        final int nextChar = this.d.nextChar();
        if (nextChar == -1) {
            return false;
        }
        if ((char)nextChar != c) {
            this.d.pushBack(nextChar);
            return false;
        }
        return true;
    }
    
    public void getAllUntil(final char c, final StringBuffer sb) throws Exception {
        while (true) {
            final int nextChar = this.d.nextChar();
            if (nextChar == -1) {
                break;
            }
            if ((char)nextChar == c) {
                this.d.pushBack(nextChar);
                break;
            }
            sb.append((char)nextChar);
        }
    }
    
    public void getParamVector(final Vector vector, final boolean b) throws Exception {
        this.skipWhiteSpace();
        final int nextChar = this.d.nextChar();
        switch (nextChar) {
            case -1: {
                return;
            }
            case 44: {
                this.d.pushBack(nextChar);
                break;
            }
            case 59: {
                this.d.pushBack(nextChar);
                return;
            }
            case 40: {
                this.a(vector);
                break;
            }
            default: {
                this.d.pushBack(nextChar);
                final StringBuffer nextToken = this.nextToken();
                if (nextToken == null) {
                    return;
                }
                vector.addElement(nextToken.toString());
                break;
            }
        }
        while (true) {
            this.skipWhiteSpace();
            final int nextChar2 = this.d.nextChar();
            if (nextChar2 == -1) {
                return;
            }
            if (nextChar2 != 44) {
                this.d.pushBack(nextChar2);
            }
            this.skipWhiteSpace();
            final int nextChar3 = this.d.nextChar();
            if (nextChar3 != 59) {
                vector.addElement(",");
            }
            switch (nextChar3) {
                case -1: {}
                case 59: {
                    this.d.pushBack(nextChar3);
                }
                case 44: {
                    this.d.pushBack(nextChar3);
                    continue;
                }
                case 40: {
                    this.a(vector);
                    continue;
                }
                default: {
                    this.d.pushBack(nextChar3);
                    final StringBuffer nextToken2 = this.nextToken();
                    if (nextToken2 == null) {
                        return;
                    }
                    vector.addElement(nextToken2.toString());
                    continue;
                }
            }
        }
    }
    
    private void a(final Vector vector) throws Exception {
        vector.addElement("(");
        while (true) {
            this.skipWhiteSpace();
            final int nextChar = this.d.nextChar();
            switch (nextChar) {
                case -1: {}
                case 59: {
                    this.d.pushBack(nextChar);
                }
                case 41: {
                    vector.addElement(")");
                }
                case 44: {
                    vector.addElement(",");
                    continue;
                }
                case 40: {
                    this.a(vector);
                    continue;
                }
                default: {
                    this.d.pushBack(nextChar);
                    final StringBuffer nextToken = this.nextToken();
                    if (nextToken == null) {
                        return;
                    }
                    vector.addElement(nextToken.toString());
                    continue;
                }
            }
        }
    }
    
    public StringBuffer printStatement(StringBuffer sb, final NFKeyValue nfKeyValue, final String s) {
        if (sb == null) {
            sb = new StringBuffer();
        }
        if (s != null) {
            sb.append(s);
        }
        sb.append((String)nfKeyValue.key);
        final Vector vector = (Vector)nfKeyValue.value;
        if (vector.size() > 0) {
            sb.append("=");
            int n = 1;
            int n2 = 0;
            for (int i = 0; i < vector.size(); ++i) {
                final String element = vector.elementAt(i);
                String string;
                if (element instanceof String) {
                    string = element;
                }
                else {
                    string = element.toString();
                }
                if (string.equals("(")) {
                    if (n != 0) {
                        n2 = ((String)nfKeyValue.key).length() + 1;
                        n = 0;
                    }
                    else {
                        sb.append('\n');
                        if (s != null) {
                            sb.append(s);
                        }
                        for (int j = 0; j < n2; ++j) {
                            sb.append(' ');
                        }
                    }
                }
                if (isQuoted(string)) {
                    doEscapes(string, sb);
                }
                else {
                    sb.append(string);
                }
            }
        }
        sb.append(';');
        return sb;
    }
    
    public static int loadTable(final Object o, final Object input) throws Exception {
        int n = 0;
        final NFToken nfToken = new NFToken();
        nfToken.setCharRange(2, 0, 255);
        nfToken.setCharType(6, 35, 0);
        nfToken.setCharType(4, 32, 0);
        nfToken.setCharType(4, 9, 0);
        nfToken.setCharType(4, 13, 0);
        nfToken.setCharType(4, 10, 0);
        nfToken.setCharType(4, 160, 0);
        nfToken.setInput(input);
        final StringBuffer sb = new StringBuffer();
        final StringBuffer sb2 = new StringBuffer();
        while (true) {
            sb.setLength(0);
            sb2.setLength(0);
            final StringBuffer nextToken = nfToken.nextToken(sb);
            if (nextToken == null) {
                return n;
            }
            final StringBuffer nextToken2 = nfToken.nextToken(sb2);
            if (nextToken2 == null) {
                throw new Exception("No value defined for " + (Object)nextToken);
            }
            ++n;
            if (o instanceof Hashtable) {
                ((Hashtable)o).put(nextToken.toString(), nextToken2.toString());
            }
            else {
                if (!(o instanceof Vector)) {
                    throw new Exception("Invalid table type " + o);
                }
                final NFKeyValue nfKeyValue = new NFKeyValue();
                nfKeyValue.key = nextToken.toString();
                nfKeyValue.value = nextToken2.toString();
                ((Vector)o).addElement(nfKeyValue);
            }
        }
    }
    
    public static void main(final String[] array) {
        try {
            System.out.println("file = " + array[0]);
            final FileInputStream input = new FileInputStream(array[0]);
            final StringBuffer sb = new StringBuffer();
            final NFToken nfToken = new NFToken();
            nfToken.setInput(input);
            while (true) {
                final NFKeyValue nextStatement = nfToken.nextStatement();
                if (nextStatement == null) {
                    break;
                }
                sb.setLength(0);
                nfToken.printStatement(sb, nextStatement, null);
                System.out.println(sb);
            }
            input.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    static {
        NFToken.a = null;
        NFToken.b = null;
        NFToken.c = true;
        NFToken.a = new int[256];
        NFToken.b = new int[256];
        for (int i = 0; i < 256; ++i) {
            NFToken.a[i] = 3;
        }
        for (int j = 97; j <= 122; ++j) {
            NFToken.a[j] = 2;
        }
        for (int k = 65; k <= 90; ++k) {
            NFToken.a[k] = 2;
        }
        NFToken.a[95] = 2;
        NFToken.a[36] = 2;
        NFToken.a[32] = 4;
        NFToken.a[160] = 4;
        NFToken.a[9] = 4;
        NFToken.a[13] = 4;
        NFToken.a[10] = 4;
        NFToken.a[39] = 1;
        NFToken.b[39] = 39;
        NFToken.a[34] = 1;
        NFToken.b[34] = 34;
        NFToken.a[123] = 1;
        NFToken.b[123] = 125;
        NFToken.a[48] = 5;
        NFToken.a[49] = 5;
        NFToken.a[50] = 5;
        NFToken.a[51] = 5;
        NFToken.a[52] = 5;
        NFToken.a[53] = 5;
        NFToken.a[54] = 5;
        NFToken.a[55] = 5;
        NFToken.a[56] = 5;
        NFToken.a[57] = 5;
        NFToken.a[35] = 6;
    }
}
