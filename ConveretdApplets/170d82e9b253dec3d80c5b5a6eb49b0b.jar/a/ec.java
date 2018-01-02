// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class ec
{
    public static String q(final String s, final String[] array) {
        try {
            final int length = array.length;
            int n = 0;
            int n2;
            if ((n2 = s.indexOf(37)) == -1) {
                return s;
            }
            String s2 = new String("");
            do {
                s2 = s2.concat(s.substring(n, n2));
                final char char1;
                switch (char1 = s.charAt(n2 + 1)) {
                    case '%': {
                        s2 = s2.concat("%");
                        ++n2;
                        break;
                    }
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9': {
                        final char c;
                        if ((c = (char)(char1 - '1')) < length && array[c] != null) {
                            s2 = s2.concat(array[c]);
                        }
                        ++n2;
                        break;
                    }
                    default: {
                        System.out.println("I don't know what to do with " + s);
                        return null;
                    }
                }
                n = n2 + 1;
            } while ((n2 = s.indexOf(37, n)) != -1 && n2 < s.length());
            return s2.concat(s.substring(n));
        }
        catch (Exception ex) {
            System.err.println("format = " + s);
            ex.printStackTrace();
            return "Incorrect format";
        }
    }
    
    public static String q(final String s, final String s2) {
        return q(s, new String[] { s2 });
    }
}
