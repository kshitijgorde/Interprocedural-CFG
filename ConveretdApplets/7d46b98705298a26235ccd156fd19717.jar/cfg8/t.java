// 
// Decompiled by Procyon v0.5.30
// 

package cfg8;

import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Color;

final class t
{
    protected static final String[] a;
    protected static final int[] b;
    protected static final String[] c;
    protected static final int[] d;
    private static String[] z;
    
    public static int HalfOfInt(final int n) {
        int n2 = n;
        int n4;
        final int n3 = n4 = 0;
        if (RotationImageFilter.a == 0) {
            if (n == n3) {
                return 0;
            }
            n2 = n;
            n4 = 2;
        }
        return n2 / n4;
    }
    
    public static double HalfOfDouble(final double n) {
        double n2 = n;
        final double n3 = 0.0;
        if (RotationImageFilter.a == 0) {
            if (n == n3) {
                return 0.0;
            }
            n2 = n;
        }
        return n2 / n3;
    }
    
    public static double DivisionOf(final double n, final double n2) {
        final int a = RotationImageFilter.a;
        final double n3 = dcmpl(n, 0.0);
        final double n5;
        if (a == 0) {
            if (n3 == 0) {
                return 0.0;
            }
            final double n4 = n2;
            n5 = 0.0;
            if (a != 0) {
                return n4 / n5;
            }
            final double n6 = dcmpl(n2, n5);
        }
        if (n3 == 0) {
            return 0.0;
        }
        final double n4 = n;
        return n4 / n5;
        n7 = 0.0;
        return n7;
    }
    
    protected static String a(final String s) {
        final int a = RotationImageFilter.a;
        String s2 = "";
        int i = 0;
        while (i < s.length()) {
            final String s3 = s;
            if (a != 0) {
                return s3;
            }
            final char char1 = s.charAt(i);
            Label_0116: {
                Label_0082: {
                    if (a == 0) {
                        switch (char1) {
                            case 32: {
                                s2 = String.valueOf(s2).concat(String.valueOf(t.z[7]));
                                break;
                            }
                            case 38: {
                                break Label_0082;
                            }
                        }
                    }
                    if (a == 0) {
                        break Label_0116;
                    }
                }
                s2 = String.valueOf(s2).concat(String.valueOf(t.z[8]));
                if (a == 0) {
                    break Label_0116;
                }
                s2 = String.valueOf(s2).concat(String.valueOf(char1));
            }
            ++i;
            if (a != 0) {
                break;
            }
        }
        return s2;
    }
    
    protected static Color b(final String s) {
        if (s.length() < 1) {
            return null;
        }
        Color decode;
        try {
            decode = Color.decode(s);
        }
        catch (Exception ex) {
            return null;
        }
        return decode;
    }
    
    protected static String a(final Color color) {
        if (color == null) {
            return t.z[1];
        }
        return String.valueOf(t.z[0]).concat(String.valueOf(Integer.toHexString(color.getRGB()).substring(2)));
    }
    
    protected static double a(final double n, final double n2, final int n3) {
        double n4 = n2;
        final double n5 = 0.0;
        if (RotationImageFilter.a == 0) {
            if (n2 == n5) {
                return 0.0;
            }
            n4 = n;
        }
        return a(n4 * n5, n3);
    }
    
    protected static double a(final double n, final int n2) {
        final int a = RotationImageFilter.a;
        double n3 = n;
        final double n4 = 0.0;
        if (a == 0) {
            if (n == n4) {
                return 0.0;
            }
            n3 = 10.0;
            final double n5 = n2;
        }
        final double pow = Math.pow(n3, n4);
        double floor = n;
        final double n6 = 0.0;
        if (a == 0) {
            if (n < n6) {
                return Math.ceil(n * pow - 0.5) / pow;
            }
            floor = Math.floor(n * pow + 0.5);
        }
        return floor / n6;
    }
    
    protected static long a(final Date date) {
        final int a = RotationImageFilter.a;
        final int date2 = date.getDate();
        int n = date.getMonth() + 1;
        final int n3;
        int n2 = n3 = date.getYear() + 1900;
        final int n4 = 99;
        if (a == 0 && n3 <= n4) {
            n2 += 1900;
            goto Label_0043;
        }
        Label_0062: {
            if (n3 > n4) {
                n -= 3;
                if (a == 0) {
                    break Label_0062;
                }
            }
            n += 9;
            --n2;
        }
        final long n5 = n2 / 100;
        return (146097L * n5 >> 2) + (1461 * (n2 - 100 * n5) >> 2) + (153 * n + 2) / 5 + date2 + 1721119L;
    }
    
    protected static Date a(final long n) {
        final int a = RotationImageFilter.a;
        long n2 = n;
        final long n3 = 0;
        if (a == 0) {
            if (n == n3) {
                return null;
            }
            n2 = n;
        }
        final long n4 = n2 - n3;
        final int n5 = (int)(((n4 << 2) - 1) / 146097L);
        final long n6 = (n4 << 2) - 1 - 146097L * n5 >> 2;
        final long n7 = ((n6 << 2) + 3) / 1461;
        final long n8 = (n6 << 2) + 3 - 1461 * n7 + 4 >> 2;
        int n9 = (int)(5 * n8 - 3) / 153;
        final int n10 = (int)((5 * n8 - 3 - 153 * n9 + 5) / 5);
        int n11 = (int)(100 * n5 + n7);
        if (a == 0) {
            if (n9 < 10) {
                n9 += 3;
                if (a == 0) {
                    return new Date(n11 - 1900, n9 - 1, n10);
                }
            }
            n9 -= 9;
        }
        ++n11;
        return new Date(n11 - 1900, n9 - 1, n10);
    }
    
    protected static int b(final Date date) {
        final int a = RotationImageFilter.a;
        final int n2;
        final int n = n2 = date.getYear() + 1900;
        final int n3 = 1582;
        if (a == 0) {
            if (n2 < n3) {
                return 0;
            }
            final int n4 = 4 + (n - 1582) + c(n);
        }
        final int n5 = n2 % n3;
        final int month = date.getMonth();
        int n6 = 0;
        int i = 0;
        while (true) {
            while (i < month) {
                n6 += a(1 + i, n);
                ++i;
                if (a == 0) {
                    if (a != 0) {
                        break;
                    }
                    continue;
                }
                else {
                    final int n7 = n6;
                    if (a == 0 && n7 > 7) {
                        return 1 + n6 / 7;
                    }
                    return n7;
                }
            }
            n6 = n6 + date.getDate() - n5;
            continue;
        }
    }
    
    protected static int a(final int n, final int n2) {
        final int a = RotationImageFilter.a;
        int n3 = n;
        if (a == 0) {
            Label_0023: {
                if (n >= 1) {
                    int n4 = n;
                    if (a == 0) {
                        if (n > 12) {
                            break Label_0023;
                        }
                        n4 = 12;
                    }
                    final int[] array = new int[n4];
                    array[0] = 31;
                    array[1] = 28;
                    array[2] = 31;
                    array[3] = 30;
                    array[4] = 31;
                    array[5] = 30;
                    array[7] = (array[6] = 31);
                    array[8] = 30;
                    array[9] = 31;
                    array[10] = 30;
                    array[11] = 31;
                    final int[] array2 = array;
                    int b = n;
                    if (a == 0) {
                        if (n == 2) {
                            b = n2;
                            if (a != 0) {
                                return b;
                            }
                            if (n2 != 0) {
                                final boolean b2 = (b = (b(n2) ? 1 : 0)) != 0;
                                if (a != 0) {
                                    return b;
                                }
                                if (b2) {
                                    return 29;
                                }
                            }
                        }
                        b = array2[n - 1];
                    }
                    return b;
                }
            }
            n3 = 0;
        }
        return n3;
    }
    
    protected static int a(final int n) {
        int n2 = n;
        final int n3 = 1582;
        if (RotationImageFilter.a == 0) {
            if (n < n3) {
                return -1;
            }
            n2 = 4 + (n - 1582) + c(n);
        }
        return n2 % n3;
    }
    
    protected static boolean b(final int n) {
        final int a = RotationImageFilter.a;
        final int n2 = n % 100;
        final boolean b = false;
        if (a == 0) {
            if (n2 == (b ? 1 : 0)) {
                boolean b2;
                final int n3 = (b2 = (n % 400 != 0)) ? 1 : 0;
                if (a == 0) {
                    if (n3 == 0) {
                        b2 = true;
                    }
                    else {
                        b2 = false;
                    }
                }
                return b2;
            }
            final int n5;
            final int n4 = n5 = n % 4;
            if (a != 0) {
                return n4 != 0;
            }
        }
        int n4;
        if (n2 == (b ? 1 : 0)) {
            n4 = 1;
        }
        else {
            n4 = 0;
        }
        return n4 != 0;
    }
    
    protected static int c(final int n) {
        int n2 = n;
        final int n3 = 1582;
        if (RotationImageFilter.a == 0) {
            if (n < n3) {
                return -1;
            }
            n2 = n - 1581;
        }
        return n2 / n3 - (n - 1501) / 100 + (n - 1201) / 400;
    }
    
    protected static String a(final Date date, final int n) {
        final int a = RotationImageFilter.a;
        if (date == null) {
            return "";
        }
        Label_0078: {
            switch (n) {
                case 0: {
                    final String s = t.z[3];
                    if (a != 0) {
                        break Label_0078;
                    }
                    return new SimpleDateFormat(s).format(date);
                }
                case 1: {
                    final String s = t.z[6];
                    if (a != 0) {
                        break Label_0078;
                    }
                    return new SimpleDateFormat(s).format(date);
                }
                case 2: {
                    final String s = t.z[5];
                    if (a != 0) {
                        break Label_0078;
                    }
                    return new SimpleDateFormat(s).format(date);
                }
                case 3: {
                    final String s = t.z[4];
                    if (a != 0) {
                        break;
                    }
                    return new SimpleDateFormat(s).format(date);
                }
            }
        }
        return "";
    }
    
    protected static Date a(final String s, final int n) {
        final int a = RotationImageFilter.a;
        final int length = s.length();
        if (a == 0 && length < 5) {
            return null;
        }
        String s2 = null;
        Label_0084: {
            Label_0071: {
                switch (length) {
                    case 0: {
                        s2 = t.z[3];
                        if (a != 0) {
                            break Label_0071;
                        }
                        break Label_0084;
                    }
                    case 1: {
                        s2 = t.z[6];
                        if (a != 0) {
                            break Label_0071;
                        }
                        break Label_0084;
                    }
                    case 2: {
                        s2 = t.z[5];
                        if (a != 0) {
                            break;
                        }
                        break Label_0084;
                    }
                }
            }
            return null;
        }
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(s2);
        try {
            return simpleDateFormat.parse(s);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    protected static String b(final long n) {
        return Long.toString(n);
    }
    
    protected static String d(final int n) {
        return Integer.toString(n);
    }
    
    protected static long c(final String s) {
        final int a = RotationImageFilter.a;
        final int length = s.length();
        Label_0035: {
            if (a == 0) {
                if (length < 1) {
                    return 0L;
                }
                final String s2 = s;
                if (a != 0) {
                    break Label_0035;
                }
                s.equals("0");
            }
            Label_0034: {
                if (length == 0) {
                    break Label_0034;
                }
                return 0L;
                try {
                    final String s2 = s;
                    return Long.valueOf(s2);
                }
                catch (Exception ex) {
                    return 0L;
                }
            }
        }
    }
    
    protected static int d(final String s) {
        final int a = RotationImageFilter.a;
        final int length = s.length();
        if (a != 0 || length < 1) {
            return length;
        }
        int n;
        final boolean b = (n = (s.equals("0") ? 1 : 0)) != 0;
        if (a != 0) {
            return n;
        }
        if (b) {
            goto Label_0029;
        }
        try {
            n = Integer.valueOf(s);
            return n;
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    protected static double e(final String s) {
        final int a = RotationImageFilter.a;
        final int length = s.length();
        Label_0035: {
            if (a == 0) {
                if (length < 1) {
                    return 0.0;
                }
                final String s2 = s;
                if (a != 0) {
                    break Label_0035;
                }
                s.equals("0");
            }
            Label_0034: {
                if (length == 0) {
                    break Label_0034;
                }
                return 0.0;
                try {
                    final String s2 = s;
                    return Double.valueOf(s2);
                }
                catch (Exception ex) {
                    return 0.0;
                }
            }
        }
    }
    
    protected static double f(final String s) {
        final int a = RotationImageFilter.a;
        final int length = s.length();
        if (a == 0) {
            if (length < 1) {
                return 0.0;
            }
            s.equals("0");
        }
        if (length == 0) {
            try {
                final StringTokenizer stringTokenizer = new StringTokenizer(s, t.z[2]);
                double n = 0.0;
                final int countTokens = stringTokenizer.countTokens();
                final int n2 = 1;
                int d = 0;
                Label_0086: {
                    if (a == 0) {
                        if (countTokens <= n2) {
                            return e(s);
                        }
                        final int countTokens2;
                        d = (countTokens2 = stringTokenizer.countTokens());
                        if (a != 0) {
                            break Label_0086;
                        }
                    }
                    if (countTokens == n2) {
                        n = e(stringTokenizer.nextToken());
                    }
                    d = d(stringTokenizer.nextToken());
                }
                final int n3 = d;
                final int d2;
                final int n4 = d2 = d(stringTokenizer.nextToken());
                final int n5 = 0;
                if ((a != 0 || d2 > n5) && d2 > n5) {
                    return n + 1.0 / n4 * n3;
                }
                if (a == 0) {
                    return n;
                }
                n = e(s);
                return n;
            }
            catch (Exception ex) {
                return 0.0;
            }
        }
        return 0.0;
    }
    
    protected static double a(final String s, final boolean b) {
        return a(s, b, 0);
    }
    
    protected static double b(final String s, final boolean b) {
        return a(s, b, 1);
    }
    
    protected static double a(final String s, final boolean b, final int n) {
        final int a = RotationImageFilter.a;
        final int length = s.length();
        if (a == 0) {
            if (length < 1) {
                return 0.0;
            }
            s.equals("0");
        }
        if (length == 0) {
            double n2 = 0.0;
            try {
                final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
                String nextToken;
                String concat;
                int i;
                int digit;
                String s2 = null;
                int length2;
                int n3;
                double n4 = 0.0;
                double n5;
                double n6;
                Label_0141_Outer:Label_0159_Outer:
                while (stringTokenizer.hasMoreTokens()) {
                    nextToken = stringTokenizer.nextToken();
                    concat = "";
                    i = nextToken.length() - 1;
                    while (true) {
                        while (true) {
                            while (i > 0) {
                                digit = (Character.isDigit(nextToken.charAt(i)) ? 1 : 0);
                                if (a == 0 && a == 0) {
                                    if (digit != 0) {
                                        break;
                                    }
                                    concat = String.valueOf(concat).concat(String.valueOf(nextToken.charAt(i--)));
                                    if (a != 0) {
                                        break;
                                    }
                                    continue Label_0141_Outer;
                                }
                                else {
                                    Label_0167: {
                                        if (digit >= 0) {
                                            f(nextToken);
                                            if (a == 0) {
                                                break Label_0167;
                                            }
                                        }
                                        Double.valueOf(s2);
                                    }
                                    length2 = concat.length();
                                    n3 = 0;
                                    if (a == 0 && length2 <= n3) {
                                        goto Label_0224;
                                    }
                                    Label_0207: {
                                        if (length2 == n3) {
                                            n4 = c(concat, b);
                                            if (a == 0) {
                                                break Label_0207;
                                            }
                                        }
                                        n4 = d(concat, b);
                                    }
                                    n5 = n4;
                                    n6 = 1.0;
                                    if (a == 0 && n5 != n6) {
                                        n2 *= n4;
                                        goto Label_0224;
                                    }
                                    n2 = n5 + n6;
                                    if (a != 0) {
                                        break Label_0141_Outer;
                                    }
                                    continue Label_0141_Outer;
                                }
                            }
                            s2 = nextToken;
                            if (a == 0) {
                                s2.indexOf(47);
                                continue Label_0159_Outer;
                            }
                            break;
                        }
                        continue;
                    }
                }
                return n2;
            }
            catch (Exception ex) {
                return 0.0;
            }
        }
        return 0.0;
    }
    
    protected static double c(final String s, final boolean b) {
        final int a = RotationImageFilter.a;
        int n = 0;
        int i = 0;
        while (i < 12) {
            final int equalsIgnoreCase;
            final int n2 = equalsIgnoreCase = (t.a[i].equalsIgnoreCase(s) ? 1 : 0);
            if (a != 0) {
                return a(equalsIgnoreCase, b);
            }
            Label_0048: {
                if (a == 0) {
                    if (n2 == 0) {
                        break Label_0048;
                    }
                    final int n3 = t.d[i];
                }
                n = n2;
                if (a == 0) {
                    break;
                }
            }
            ++i;
            if (a != 0) {
                break;
            }
        }
        int equalsIgnoreCase = n;
        return a(equalsIgnoreCase, b);
    }
    
    protected static double a(final int n, final boolean b) {
        final int a = RotationImageFilter.a;
        int n2 = b ? 1 : 0;
        if (a == 0) {
            if (b) {
                switch (n) {
                    case 2: {
                        return 1000.0;
                    }
                    case 3: {
                        return 10.0;
                    }
                    case 4: {
                        return 1.0;
                    }
                    case 6: {
                        return 914.4;
                    }
                    case 7: {
                        return 304.8;
                    }
                    case 8: {
                        return 25.4;
                    }
                    default: {
                        if (a != 0) {
                            break;
                        }
                        return 1.0;
                    }
                }
            }
            n2 = n;
        }
        switch (n2) {
            case 2: {
                return 39.37007874015748;
            }
            case 3: {
                return 0.3937007874015748;
            }
            case 4: {
                return 0.03937007874015748;
            }
            case 6: {
                return 36.0;
            }
            case 7: {
                return 12.0;
            }
            case 8: {
                return 1.0;
            }
            default: {
                return 1.0;
            }
        }
    }
    
    protected static double d(final String s, final boolean b) {
        final int a = RotationImageFilter.a;
        int n = 0;
        int i = 0;
        while (i < 22) {
            final int equalsIgnoreCase;
            final int n2 = equalsIgnoreCase = (t.c[i].equalsIgnoreCase(s) ? 1 : 0);
            if (a != 0) {
                return b(equalsIgnoreCase, b);
            }
            Label_0048: {
                if (a == 0) {
                    if (n2 == 0) {
                        break Label_0048;
                    }
                    final int n3 = t.d[i];
                }
                n = n2;
                if (a == 0) {
                    break;
                }
            }
            ++i;
            if (a != 0) {
                break;
            }
        }
        int equalsIgnoreCase = n;
        return b(equalsIgnoreCase, b);
    }
    
    protected static double b(final int n, final boolean b) {
        final int a = RotationImageFilter.a;
        int n2 = b ? 1 : 0;
        if (a == 0) {
            if (b) {
                switch (n) {
                    case 1: {
                        return 1000.0;
                    }
                    case 2: {
                        return 10.0;
                    }
                    case 3: {
                        return 1.0;
                    }
                    case 4: {
                        return 4546.092;
                    }
                    case 5: {
                        return 946.3529;
                    }
                    case 6: {
                        return 568.261;
                    }
                    case 7: {
                        return 28.41307;
                    }
                    default: {
                        if (a != 0) {
                            break;
                        }
                        return 1.0;
                    }
                }
            }
            n2 = n;
        }
        switch (n2) {
            case 1: {
                return 33.81402;
            }
            case 2: {
                return 0.3381402;
            }
            case 3: {
                return 0.03381402;
            }
            case 4: {
                return 128.0;
            }
            case 5: {
                return 32.0;
            }
            case 6: {
                return 16.0;
            }
            case 7: {
                return 1.0;
            }
            default: {
                return 1.0;
            }
        }
    }
    
    protected static void a(final Graphics graphics, final String s, final Rectangle rectangle, final FontMetrics fontMetrics) {
        final int a = RotationImageFilter.a;
        FontMetrics fontMetrics2 = fontMetrics;
        if (a == 0) {
            if (fontMetrics == null) {
                return;
            }
            fontMetrics2 = fontMetrics;
        }
        int n = fontMetrics2.stringWidth(s);
        final int height = fontMetrics.getHeight();
        final int n2 = n;
        final int width = rectangle.width;
        if (a == 0) {
            if (n2 < width) {
                graphics.drawString(s, rectangle.x, rectangle.y + rectangle.height / 2 + height / 2 - fontMetrics.getDescent());
                return;
            }
            final int x = rectangle.x;
        }
        int n3 = n2 + width;
        int n4 = rectangle.y + height;
        String s2 = "";
        int n5 = 0;
        int i = 0;
        while (true) {
            while (i < s.length()) {
                final char char1 = s.charAt(i);
                final int length;
                final int n7;
                final int n6 = n7 = (length = char1);
                final int n9;
                int width2;
                final int n8 = width2 = (n9 = 10);
                if (a != 0) {
                    if (length > n9) {
                        graphics.drawString(s2, n3, n4);
                    }
                    return;
                }
                if (a == 0 && n6 != n8) {
                    s2 = String.valueOf(s2).concat(String.valueOf(char1));
                    n = fontMetrics.stringWidth(s2);
                    goto Label_0158;
                }
                Label_0296: {
                    final int n10;
                    final int n11;
                    Label_0206: {
                        if (a == 0) {
                            if (n6 != n8) {
                                n10 = i;
                                n11 = s.length() - 1;
                                if (a != 0) {
                                    break Label_0206;
                                }
                                if (n10 != n11) {
                                    final int n12 = n;
                                    final int width3 = rectangle.width;
                                    if (a != 0) {
                                        break Label_0206;
                                    }
                                    if (n12 <= width3) {
                                        break Label_0296;
                                    }
                                }
                            }
                            width2 = rectangle.width;
                        }
                    }
                    Label_0240: {
                        if (n10 > n11) {
                            graphics.drawString(s.substring(n5, i), n3, n4);
                            if (a == 0) {
                                break Label_0240;
                            }
                        }
                        graphics.drawString(s2, n3, n4);
                    }
                    n5 = i;
                    s2 = "";
                    final int n13 = n;
                    final int width4 = rectangle.width;
                    if (a == 0) {
                        if (n13 > width4) {
                            s2 = String.valueOf(s2).concat(String.valueOf(s.charAt(i)));
                        }
                        n4 += height;
                        final int x2 = rectangle.x;
                    }
                    n3 = n13 + width4;
                }
                ++i;
                if (a != 0) {
                    break;
                }
            }
            int length = s2.length();
            int n9 = 0;
            continue;
        }
    }
    
    static double b(final String s, final int n) {
        final int i = RotationImageFilter.a;
        char c = '\0';
        int n2 = 0;
        do {
            int j = 0;
            int n3 = 0;
        Label_0071_Outer:
            while (j <= n3) {
                while (true) {
                    while (c < s.length()) {
                        String s2 = s;
                        if (i == 0) {
                            j = (Character.isDigit(s.charAt(c)) ? 1 : 0);
                            n3 = 0;
                            if (i != 0) {
                                continue Label_0071_Outer;
                            }
                            if (j != n3) {
                                break;
                            }
                            s2 = s;
                            if (i == 0) {
                                if (s.charAt(c) == '.') {
                                    break;
                                }
                                ++c;
                                if (i != 0) {
                                    break;
                                }
                                continue Label_0071_Outer;
                            }
                        }
                        String concat = s2;
                    Label_0144:
                        while (true) {
                            while (c < s.length()) {
                                String value = s;
                                final char c2 = c;
                                Label_0129: {
                                    if (i == 0) {
                                        int n4;
                                        final boolean b = (n4 = (Character.isDigit(s.charAt(c2)) ? 1 : 0)) != 0;
                                        int n6;
                                        final int n5 = n6 = 1;
                                        if (i == 0) {
                                            if ((b ? 1 : 0) != n5) {
                                                final char c3 = (char)(n4 = s.charAt(c));
                                                final int n7 = n6 = 46;
                                                if (i != 0) {
                                                    break Label_0144;
                                                }
                                                if (c3 != n7) {
                                                    break;
                                                }
                                            }
                                            value = String.valueOf(concat);
                                            s.charAt(c++);
                                            break Label_0129;
                                        }
                                        if (n4 == n6) {
                                            return e(concat);
                                        }
                                        ++n2;
                                        continue Label_0071_Outer;
                                    }
                                }
                                concat = value.concat(String.valueOf(c2));
                                if (i != 0) {
                                    break;
                                }
                            }
                            int n4 = n2;
                            int n6 = n;
                            continue Label_0144;
                        }
                    }
                    String s2 = "";
                    continue;
                }
            }
            break;
        } while (i == 0);
        return 0.0;
    }
    
    static {
        t.z = new String[] { z(z("='")), z(z("='Up}k9U")), z(z("-p")), z(z("i;\u001c[V\"&Job")), z(z("t&Jo5@\u0012\u001dr\u007f")), z(z("t&Jo4@\u0012\u001cr\u007f")), z(z("@\u0012\u001cr\u007f\"&Job")), z(z("(m\u0003")), z(z("(m\u0005")) };
        a = new String[] { "\"", z(z("d1")), z(z("d1@")), "'", z(z("k+")), z(z("t;")), z(z("t;@")), z(z("t-W")), z(z("t-We")), z(z("`2")), z(z("n2")), "m" };
        b = new int[] { 8, 8, 8, 7, 7, 6, 6, 6, 6, 4, 3, 2 };
        c = new String[] { "l", z(z("a,")), z(z("a+")), z(z("a+@")), z(z("a+A")), z(z("a+Ae")), z(z("`3")), z(z("`3@")), z(z("`3Gd")), z(z("`3Gdh")), z(z("n3")), z(z("n3@")), z(z("n3Gd")), z(z("n3Gdh")), z(z("j3")), z(z("j3@")), z(z("}+")), z(z("}+@")), z(z("|+")), z(z("|-G")), z(z("k3")), z(z("k3\\l")) };
        d = new int[] { 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 2, 2, 2, 2, 4, 4, 5, 5, 6, 6, 7, 7 };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        int i;
        do {
            i = charArray.length;
            if (i < 2) {
                continue;
            }
            return charArray;
        } while (i == 0);
        final int n = 0;
        charArray[n] ^= '\u001b';
        return charArray;
    }
    
    private static String z(final char[] array) {
        int length;
        int n2;
        final int n = n2 = (length = array.length);
        int n3 = 0;
        while (true) {
            Label_0086: {
                if (n > 1) {
                    break Label_0086;
                }
                length = (n2 = n3);
                do {
                    final char c = array[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = '\r';
                            break;
                        }
                        case 1: {
                            c2 = '_';
                            break;
                        }
                        case 2: {
                            c2 = '3';
                            break;
                        }
                        case 3: {
                            c2 = '\u0016';
                            break;
                        }
                        default: {
                            c2 = '\u001b';
                            break;
                        }
                    }
                    array[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                return new String(array).intern();
            }
            continue;
        }
    }
}
