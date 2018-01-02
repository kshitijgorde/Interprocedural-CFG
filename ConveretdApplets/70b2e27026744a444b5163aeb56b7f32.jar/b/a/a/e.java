// 
// Decompiled by Procyon v0.5.30
// 

package b.a.a;

import java.util.Vector;
import b.a.d.c;
import b.a.d.d;
import java.text.DecimalFormatSymbols;
import b.a.d.a;

public class e
{
    private static double a;
    private static double b;
    private static boolean c;
    
    public static double a(final double n) {
        return Math.atan(n) / 3.141592653589793 * 180.0;
    }
    
    public static double b(final double n) {
        return Math.cos(n / 180.0 * 3.141592653589793);
    }
    
    public static int a(final int n, final int n2) {
        if (n2 < 0) {
            return -a(n, -n2);
        }
        if (n < 0) {
            return (n - n2 + 1) / n2;
        }
        return n / n2;
    }
    
    public static double a(final double n, final double n2, final double n3, final double n4, final double n5) {
        if (n == n3) {
            return n4;
        }
        return n4 + (n2 - n) * (n5 - n4) / (n3 - n);
    }
    
    public static int c(final double n) {
        return (int)Math.floor(n);
    }
    
    public static int d(final double n) {
        return (int)Math.round(n);
    }
    
    public static int b(final int n, final int n2) {
        final int n3 = n % n2;
        if ((n3 < 0 && n2 > 0) || (n3 > 0 && n2 < 0)) {
            return n2 + n3;
        }
        return n3;
    }
    
    public static double a(final double n, final double n2) {
        return n - Math.floor(n / n2) * n2;
    }
    
    public static double b(final double n, final double n2) {
        double n3 = n - Math.floor(n / n2) * n2;
        if (n3 >= n2 / 2.0) {
            n3 -= n2;
        }
        return n3;
    }
    
    public static double e(final double n) {
        return Math.floor(n + 0.5);
    }
    
    public static double f(final double n) {
        if (n < 0.0) {
            return -1.0;
        }
        if (n > 0.0) {
            return 1.0;
        }
        return 0.0;
    }
    
    public static double g(final double n) {
        return Math.sin(n / 180.0 * 3.141592653589793);
    }
    
    public static double h(final double n) {
        return n * n;
    }
    
    public static double i(final double n) {
        return Math.tan(n / 180.0 * 3.141592653589793);
    }
    
    public static double j(final double n) {
        return n * 180.0 / 3.141592653589793;
    }
    
    public static double k(final double n) {
        return n * 3.141592653589793 / 180.0;
    }
    
    public static double l(final double n) {
        return c(n, 0.01);
    }
    
    public static double c(final double n, final double n2) {
        if (n < -1.0 - n2) {
            if (e.c) {
                System.err.println("Value out of range: " + n + " < -1.0");
                Thread.dumpStack();
            }
            return -1.0;
        }
        if (n > 1.0 + n2) {
            if (e.c) {
                System.err.println("Value out of range: " + n + " > 1.0");
                Thread.dumpStack();
            }
            return 1.0;
        }
        if (n < -1.0) {
            return -1.0;
        }
        if (n > 1.0) {
            return 1.0;
        }
        return n;
    }
    
    public static String a(final String s, final double n) {
        return a(s, n, b.a.d.a.b());
    }
    
    public static String a(final String s, double abs, final DecimalFormatSymbols decimalFormatSymbols) {
        if (s == null || s.length() == 0) {
            return null;
        }
        final StringBuffer sb = new StringBuffer(s.length());
        String s2 = "";
        String s3 = "";
        boolean b = false;
        boolean b2 = false;
        boolean b3 = false;
        boolean b4 = false;
        boolean b5 = false;
        boolean b6 = false;
        boolean b7 = false;
        int n = 0;
        int n2 = 0;
        int length = -1;
        int n3 = -1;
        int n4 = 0;
        int n5 = 0;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            switch (n4) {
                case 0: {
                    if ("#$*&0.-+,>".indexOf(char1) >= 0) {
                        --i;
                        n4 = 1;
                        break;
                    }
                    if (char1 != '/') {
                        ++n5;
                        s2 += char1;
                        break;
                    }
                    ++n5;
                    if (i + 1 < s.length()) {
                        ++i;
                        ++n5;
                        s2 += s.charAt(i);
                        break;
                    }
                    break;
                }
                case 1: {
                    switch (char1) {
                        case '#':
                        case '0': {
                            sb.append(char1);
                            ++n;
                            continue;
                        }
                        case '$': {
                            b = true;
                            sb.append('#');
                            ++n;
                            continue;
                        }
                        case '*': {
                            b3 = true;
                            sb.append('#');
                            ++n;
                            continue;
                        }
                        case '&': {
                            b4 = true;
                            sb.append('#');
                            ++n;
                            continue;
                        }
                        case '.': {
                            sb.append('.');
                            length = i - n5;
                            n4 = 2;
                            continue;
                        }
                        case '+': {
                            sb.append('+');
                            n3 = i - n5;
                            b6 = true;
                            continue;
                        }
                        case '-': {
                            sb.append('-');
                            n3 = i - n5;
                            ++n;
                            continue;
                        }
                        case ',': {
                            sb.append(',');
                            continue;
                        }
                        case '>': {
                            b7 = true;
                            ++n5;
                            continue;
                        }
                        default: {
                            length = i - n5;
                            s3 += char1;
                            n4 = 3;
                            continue;
                        }
                    }
                    break;
                }
                case 2: {
                    switch (char1) {
                        case '#':
                        case '0': {
                            sb.append(char1);
                            ++n2;
                            continue;
                        }
                        case '$': {
                            b = true;
                            sb.append('#');
                            ++n2;
                            continue;
                        }
                        case '&': {
                            b5 = true;
                            sb.append('#');
                            ++n2;
                            continue;
                        }
                        case '+': {
                            sb.append('+');
                            n3 = i - n5;
                            b6 = true;
                            continue;
                        }
                        case '-': {
                            sb.append('-');
                            n3 = i - n5;
                            continue;
                        }
                        case ' ': {
                            sb.append(' ');
                            continue;
                        }
                        case '>': {
                            b7 = true;
                            ++n5;
                            continue;
                        }
                        case '/': {
                            n4 = 3;
                            continue;
                        }
                        default: {
                            s3 += char1;
                            n4 = 3;
                            continue;
                        }
                    }
                    break;
                }
                case 3: {
                    s3 += char1;
                    break;
                }
            }
        }
        if (length < 0) {
            length = sb.length();
        }
        if (n3 > 0 && n3 == length - 1) {
            b2 = true;
            sb.setCharAt(n3, '0');
        }
        if (!b5 && Math.abs(abs) < 1.0E20) {
            double n6 = 1.0;
            for (int j = 0; j < n2; ++j) {
                n6 *= 10.0;
            }
            abs = e(abs * n6) / n6;
        }
        final double f = f(abs);
        abs = Math.abs(abs);
        if (!b4 && f < 0.0 && !b6 && n3 < length) {
            --n;
        }
        String s4 = "" + abs;
        final int index;
        if ((index = s4.indexOf(69)) > 0) {
            final int a = d.a((Object)s4.substring(index + 1));
            final String string = s4.substring(0, 1) + s4.substring(2, index);
            if (a < 0) {
                s4 = "0." + b.a.d.c.a('0', -a - 1) + string;
            }
            else if (string.length() > a + 1) {
                s4 = string.substring(0, a + 1) + "." + string.substring(a + 1);
            }
            else {
                s4 = string + b.a.d.c.a('0', a - string.length() + 1);
            }
        }
        final int index2 = s4.indexOf(46);
        String substring;
        String s5;
        if (index2 < 0) {
            substring = s4;
            s5 = "";
        }
        else {
            substring = s4.substring(0, index2);
            s5 = s4.substring(index2 + 1);
        }
        if (!b7 && substring.length() > n) {
            return s2 + b.a.d.c.a('#', sb.length()) + s3;
        }
        if (!b5) {
            if (s5.length() > n2) {
                s5 = s5.substring(0, n2);
            }
            else if (s5.length() < n2) {
                s5 += b.a.d.c.a('0', n2 - s5.length());
            }
        }
        for (int n7 = length - 1, k = substring.length() - 1; k >= 0; --k, --n7) {
            final char char2 = substring.charAt(k);
            if (n7 < 0) {
                sb.insert(0, char2);
                ++length;
                if (n3 >= length) {
                    ++n3;
                }
            }
            else {
                final char char3 = sb.charAt(n7);
                if (char3 == '0' || char3 == '#') {
                    sb.setCharAt(n7, char2);
                }
                else {
                    ++k;
                }
            }
        }
        int l = length + 1;
        int n8 = 0;
        while (l < sb.length()) {
            final char char4 = sb.charAt(l);
            if ((char4 == '#' || char4 == '0') && n8 < s5.length()) {
                sb.setCharAt(l, s5.charAt(n8));
                ++n8;
            }
            else if (char4 == '#' && n8 >= s5.length()) {
                sb.setCharAt(l, ' ');
            }
            ++l;
        }
        int n9 = 0;
        for (int n10 = 0; n10 < length; ++n10) {
            final char char5 = sb.charAt(n10);
            if ('0' <= char5 && char5 <= '9') {
                n9 = n10;
                break;
            }
            if (b3) {
                sb.setCharAt(n10, '*');
            }
            else {
                sb.setCharAt(n10, ' ');
            }
        }
        if (b) {
            if (n9 == 0) {
                sb.insert(0, '$');
            }
            else {
                sb.setCharAt(n9 - 1, '$');
                --n9;
            }
        }
        if (n3 >= 0 || f < 0.0) {
            final int n11 = (f < 0.0) ? 45 : (b6 ? 43 : 32);
            if (b2 || n3 < 0) {
                if (n9 == 0) {
                    sb.insert(0, (char)n11);
                }
                else {
                    sb.setCharAt(n9 - 1, (char)n11);
                }
            }
            else {
                sb.setCharAt(n3, (char)n11);
            }
        }
        for (int n12 = 0; n12 < sb.length(); ++n12) {
            final char char6 = sb.charAt(n12);
            if (char6 == ',') {
                sb.setCharAt(n12, decimalFormatSymbols.getGroupingSeparator());
            }
            else if (char6 == '.') {
                sb.setCharAt(n12, decimalFormatSymbols.getDecimalSeparator());
            }
        }
        String s6;
        for (s6 = sb.toString(); b4 && s6.startsWith(" "); s6 = s6.substring(1)) {}
        while (b5 && (s6.endsWith(" ") || s6.endsWith("0"))) {
            s6 = s6.substring(0, s6.length() - 1);
        }
        if (b5 && s6.endsWith(".")) {
            s6 = s6.substring(0, s6.length() - 1);
        }
        return s2 + s6 + s3;
    }
    
    public static int a(final String s) {
        int index = -1;
        while ((index = s.indexOf(46, index + 1)) >= 0 && index != 0 && s.charAt(index - 1) == '/') {}
        if (index < 0) {
            return 0;
        }
        int n = 0;
        for (int i = index + 1; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if ("#$*&0.-+,>".indexOf(char1) < 0) {
                break;
            }
            if (char1 == '#' || char1 == '0' || char1 == '$') {
                ++n;
            }
            else if (char1 == '&') {
                return 20;
            }
        }
        return n;
    }
    
    public static String[] b(final String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        final Vector vector = new Vector<String>();
        boolean b = false;
        String string = "";
        boolean b2 = false;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            final boolean b3 = "#$*&0.-+,>".indexOf(char1) >= 0 && !b2;
            if ((!b && b3) || (b && !b3)) {
                vector.addElement(string);
                string = "";
                b = !b;
            }
            string += char1;
            b2 = (char1 == '/' && !b2);
        }
        if (string.length() > 0) {
            vector.addElement(string);
        }
        if (vector.size() < 2) {
            return null;
        }
        vector.setElementAt(vector.elementAt(0).toString() + (Object)vector.elementAt(1), 1);
        if (vector.size() % 2 == 0) {
            vector.addElement("");
        }
        final String[] array = new String[(vector.size() - 1) / 2];
        for (int j = 0; j < array.length; ++j) {
            array[j] = vector.elementAt(j * 2 + 1).toString() + (Object)vector.elementAt(j * 2 + 2);
        }
        return array;
    }
    
    static {
        e.a = Math.log(2.0);
        e.b = Math.log(10.0);
        e.c = false;
    }
}
