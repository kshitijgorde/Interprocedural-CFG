// 
// Decompiled by Procyon v0.5.30
// 

package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;

public class FormatUtils
{
    private static final double LOG_10;
    
    public static void appendPaddedInteger(final StringBuffer sb, int n, int i) {
        if (n < 0) {
            sb.append('-');
            if (n == Integer.MIN_VALUE) {
                while (i > 10) {
                    sb.append('0');
                    --i;
                }
                sb.append("2147483648");
                return;
            }
            n = -n;
        }
        if (n < 10) {
            while (i > 1) {
                sb.append('0');
                --i;
            }
            sb.append((char)(n + 48));
        }
        else if (n < 100) {
            while (i > 2) {
                sb.append('0');
                --i;
            }
            final int n2 = (n + 1) * 13421772 >> 27;
            sb.append((char)(n2 + 48));
            sb.append((char)(n - (n2 << 3) - (n2 << 1) + 48));
        }
        else {
            int n3;
            if (n < 1000) {
                n3 = 3;
            }
            else if (n < 10000) {
                n3 = 4;
            }
            else {
                n3 = (int)(Math.log(n) / FormatUtils.LOG_10) + 1;
            }
            while (i > n3) {
                sb.append('0');
                --i;
            }
            sb.append(Integer.toString(n));
        }
    }
    
    public static void appendPaddedInteger(final StringBuffer sb, long n, int i) {
        final int n2 = (int)n;
        if (n2 == n) {
            appendPaddedInteger(sb, n2, i);
        }
        else if (i <= 19) {
            sb.append(Long.toString(n));
        }
        else {
            if (n < 0L) {
                sb.append('-');
                if (n == Long.MIN_VALUE) {
                    while (i > 19) {
                        sb.append('0');
                        --i;
                    }
                    sb.append("9223372036854775808");
                    return;
                }
                n = -n;
            }
            while (i > (int)(Math.log(n) / FormatUtils.LOG_10) + 1) {
                sb.append('0');
                --i;
            }
            sb.append(Long.toString(n));
        }
    }
    
    public static void writePaddedInteger(final Writer writer, int n, int i) throws IOException {
        if (n < 0) {
            writer.write(45);
            if (n == Integer.MIN_VALUE) {
                while (i > 10) {
                    writer.write(48);
                    --i;
                }
                writer.write("2147483648");
                return;
            }
            n = -n;
        }
        if (n < 10) {
            while (i > 1) {
                writer.write(48);
                --i;
            }
            writer.write(n + 48);
        }
        else if (n < 100) {
            while (i > 2) {
                writer.write(48);
                --i;
            }
            final int n2 = (n + 1) * 13421772 >> 27;
            writer.write(n2 + 48);
            writer.write(n - (n2 << 3) - (n2 << 1) + 48);
        }
        else {
            int n3;
            if (n < 1000) {
                n3 = 3;
            }
            else if (n < 10000) {
                n3 = 4;
            }
            else {
                n3 = (int)(Math.log(n) / FormatUtils.LOG_10) + 1;
            }
            while (i > n3) {
                writer.write(48);
                --i;
            }
            writer.write(Integer.toString(n));
        }
    }
    
    public static void writePaddedInteger(final Writer writer, long n, int i) throws IOException {
        final int n2 = (int)n;
        if (n2 == n) {
            writePaddedInteger(writer, n2, i);
        }
        else if (i <= 19) {
            writer.write(Long.toString(n));
        }
        else {
            if (n < 0L) {
                writer.write(45);
                if (n == Long.MIN_VALUE) {
                    while (i > 19) {
                        writer.write(48);
                        --i;
                    }
                    writer.write("9223372036854775808");
                    return;
                }
                n = -n;
            }
            while (i > (int)(Math.log(n) / FormatUtils.LOG_10) + 1) {
                writer.write(48);
                --i;
            }
            writer.write(Long.toString(n));
        }
    }
    
    public static void appendUnpaddedInteger(final StringBuffer sb, int n) {
        if (n < 0) {
            sb.append('-');
            if (n == Integer.MIN_VALUE) {
                sb.append("2147483648");
                return;
            }
            n = -n;
        }
        if (n < 10) {
            sb.append((char)(n + 48));
        }
        else if (n < 100) {
            final int n2 = (n + 1) * 13421772 >> 27;
            sb.append((char)(n2 + 48));
            sb.append((char)(n - (n2 << 3) - (n2 << 1) + 48));
        }
        else {
            sb.append(Integer.toString(n));
        }
    }
    
    public static void appendUnpaddedInteger(final StringBuffer sb, final long n) {
        final int n2 = (int)n;
        if (n2 == n) {
            appendUnpaddedInteger(sb, n2);
        }
        else {
            sb.append(Long.toString(n));
        }
    }
    
    public static void writeUnpaddedInteger(final Writer writer, int n) throws IOException {
        if (n < 0) {
            writer.write(45);
            if (n == Integer.MIN_VALUE) {
                writer.write("2147483648");
                return;
            }
            n = -n;
        }
        if (n < 10) {
            writer.write(n + 48);
        }
        else if (n < 100) {
            final int n2 = (n + 1) * 13421772 >> 27;
            writer.write(n2 + 48);
            writer.write(n - (n2 << 3) - (n2 << 1) + 48);
        }
        else {
            writer.write(Integer.toString(n));
        }
    }
    
    public static void writeUnpaddedInteger(final Writer writer, final long n) throws IOException {
        final int n2 = (int)n;
        if (n2 == n) {
            writeUnpaddedInteger(writer, n2);
        }
        else {
            writer.write(Long.toString(n));
        }
    }
    
    public static int calculateDigitCount(final long n) {
        if (n >= 0L) {
            return (n < 10L) ? 1 : ((n < 100L) ? 2 : ((n < 1000L) ? 3 : ((n < 10000L) ? 4 : ((int)(Math.log(n) / FormatUtils.LOG_10) + 1))));
        }
        if (n != Long.MIN_VALUE) {
            return calculateDigitCount(-n) + 1;
        }
        return 20;
    }
    
    static int parseTwoDigits(final String s, final int n) {
        final char c = (char)(s.charAt(n) - '0');
        return (c << 3) + (c << 1) + s.charAt(n + 1) - '0';
    }
    
    static String createErrorMessage(final String s, final int n) {
        final int n2 = n + 32;
        String concat;
        if (s.length() <= n2 + 3) {
            concat = s;
        }
        else {
            concat = s.substring(0, n2).concat("...");
        }
        if (n <= 0) {
            return "Invalid format: \"" + concat + '\"';
        }
        if (n >= s.length()) {
            return "Invalid format: \"" + concat + "\" is too short";
        }
        return "Invalid format: \"" + concat + "\" is malformed at \"" + concat.substring(n) + '\"';
    }
    
    static {
        LOG_10 = Math.log(10.0);
    }
}
