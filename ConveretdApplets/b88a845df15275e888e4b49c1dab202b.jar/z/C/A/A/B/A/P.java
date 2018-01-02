// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.B.A;

public final class P
{
    public static String A(final D d) {
        int i = 27;
        final char[] g = d.g;
        int n = 1;
        final StringBuffer sb = new StringBuffer();
        while (i != 0) {
            i = g[n];
            sb.append(n);
            A(g, n, sb);
            final int b = Q.B(g, n);
            n += Q.N[i];
            sb.append("(" + b + ")");
            n += 2;
            if (i == 9) {
                n += 16;
            }
            else if (i == 35 || i == 36) {
                while (g[n] != '\0') {
                    if (g[n] == '%') {
                        n += 3;
                    }
                    else {
                        n += 2;
                    }
                }
                ++n;
            }
            else if (i == 14) {
                ++n;
                sb.append(" <");
                while (g[n] != '\uffff') {
                    sb.append(g[n]);
                    ++n;
                }
                sb.append(">");
                ++n;
            }
            sb.append('\n');
        }
        if (d.h != null) {
            sb.append("start `" + new String(d.h) + "' ");
        }
        if (d._ != -1) {
            sb.append("stclass `");
            A(g, d._, sb);
            sb.append("' ");
        }
        if ((d.b & 0x3) != 0x0) {
            sb.append("anchored ");
        }
        if ((d.b & 0x4) != 0x0) {
            sb.append("plus ");
        }
        if ((d.b & 0x8) != 0x0) {
            sb.append("implicit ");
        }
        if (d.S != null) {
            sb.append("must have \"" + new String(d.S) + "\" back " + d.V + " ");
        }
        sb.append("minlen " + d.d + '\n');
        return sb.toString();
    }
    
    static void A(final char[] array, final int n, final StringBuffer sb) {
        String s = null;
        sb.append(":");
        switch (array[n]) {
            case '\u0001': {
                s = "BOL";
                break;
            }
            case '\u0002': {
                s = "MBOL";
                break;
            }
            case '\u0003': {
                s = "SBOL";
                break;
            }
            case '\u0004': {
                s = "EOL";
                break;
            }
            case '\u0005': {
                s = "MEOL";
                break;
            }
            case '\u0007': {
                s = "ANY";
                break;
            }
            case '\b': {
                s = "SANY";
                break;
            }
            case '\t': {
                s = "ANYOF";
                break;
            }
            case '#': {
                s = "ANYOFUN";
                break;
            }
            case '$': {
                s = "NANYOFUN";
                break;
            }
            case '\f': {
                s = "BRANCH";
                break;
            }
            case '\u000e': {
                s = "EXACTLY";
                break;
            }
            case '\u000f': {
                s = "NOTHING";
                break;
            }
            case '\r': {
                s = "BACK";
                break;
            }
            case '\0': {
                s = "END";
                break;
            }
            case '\u0012': {
                s = "ALNUM";
                break;
            }
            case '\u0013': {
                s = "NALNUM";
                break;
            }
            case '\u0014': {
                s = "BOUND";
                break;
            }
            case '\u0015': {
                s = "NBOUND";
                break;
            }
            case '\u0016': {
                s = "SPACE";
                break;
            }
            case '\u0017': {
                s = "NSPACE";
                break;
            }
            case '\u0018': {
                s = "DIGIT";
                break;
            }
            case '\u0019': {
                s = "NDIGIT";
                break;
            }
            case '&': {
                s = "ALPHA";
                break;
            }
            case '\'': {
                s = "BLANK";
                break;
            }
            case '(': {
                s = "CNTRL";
                break;
            }
            case ')': {
                s = "GRAPH";
                break;
            }
            case '*': {
                s = "LOWER";
                break;
            }
            case '+': {
                s = "PRINT";
                break;
            }
            case ',': {
                s = "PUNCT";
                break;
            }
            case '-': {
                s = "UPPER";
                break;
            }
            case '.': {
                s = "XDIGIT";
                break;
            }
            case '2': {
                s = "ALNUMC";
                break;
            }
            case '3': {
                s = "ASCII";
                break;
            }
            case '\n': {
                sb.append("CURLY {");
                sb.append((int)Q.D(array, n));
                sb.append(',');
                sb.append((int)Q.A(array, n));
                sb.append('}');
                break;
            }
            case '\u000b': {
                sb.append("CURLYX {");
                sb.append((int)Q.D(array, n));
                sb.append(',');
                sb.append((int)Q.A(array, n));
                sb.append('}');
                break;
            }
            case '\u001a': {
                sb.append("REF");
                sb.append((int)Q.D(array, n));
                break;
            }
            case '\u001b': {
                sb.append("OPEN");
                sb.append((int)Q.D(array, n));
                break;
            }
            case '\u001c': {
                sb.append("CLOSE");
                sb.append((int)Q.D(array, n));
                break;
            }
            case '\u0010': {
                s = "STAR";
                break;
            }
            case '\u0011': {
                s = "PLUS";
                break;
            }
            case '\u001d': {
                s = "MINMOD";
                break;
            }
            case '\u001e': {
                s = "GBOL";
                break;
            }
            case ' ': {
                s = "UNLESSM";
                break;
            }
            case '\u001f': {
                s = "IFMATCH";
                break;
            }
            case '!': {
                s = "SUCCEED";
                break;
            }
            case '\"': {
                s = "WHILEM";
                break;
            }
            default: {
                sb.append("Operator is unrecognized.  Faulty expression code!");
                break;
            }
        }
        if (s != null) {
            sb.append(s);
        }
    }
}
