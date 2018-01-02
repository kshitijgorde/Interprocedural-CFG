import java.io.FileInputStream;
import java.util.Hashtable;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class AppletParams
{
    String app;
    
    public AppletParams(final InputStream inputStream) {
        final byte[] array = { 0 };
        final StringBuffer sb = new StringBuffer();
        while (true) {
            try {
                if (inputStream.read(array) == -1) {
                    break;
                }
            }
            catch (Exception ex) {
                break;
            }
            if (array[0] > 31) {
                sb.append((char)array[0]);
            }
        }
        final String string = sb.toString();
        final int index = string.toLowerCase().indexOf("<applet");
        final int index2 = string.toLowerCase().indexOf("</applet");
        if (index < 0 || index2 < 0) {
            System.out.println("unable to find applet tages");
        }
        this.app = string.substring(index, index2);
    }
    
    public AppletParams(final String app) {
        this.app = app;
    }
    
    public boolean tokenIt(final Hashtable hashtable, final boolean b) {
        int i = 0;
        int n = 0;
        String s = " ";
        do {
            final int index = this.app.indexOf("=", i);
            if (index < 0) {
                return true;
            }
            int n2 = index;
            do {
                --n2;
            } while (n2 != 0 && this.app.charAt(n2) != ' ');
            int n3 = index + 1;
            int n4 = index;
            int n5 = 0;
            while (true) {
                ++n4;
                if (this.app.charAt(n4) == '\"') {
                    if (n5 != 0) {
                        break;
                    }
                    n3 = n4 + 1;
                    n5 = 1;
                }
                else {
                    if (this.app.charAt(n4) == ' ' && n5 == 0) {
                        break;
                    }
                    if (this.app.charAt(n4) == '>' && n5 == 0) {
                        break;
                    }
                    continue;
                }
            }
            final String trim = this.app.substring(n2, index).trim();
            final String trim2 = this.app.substring(n3, n4).trim();
            if (trim.compareTo("name") == 0) {
                if (n != 0) {
                    System.out.println("Unable to find 'value=' for name=" + trim);
                    return false;
                }
                n = 1;
                s = trim2;
            }
            else if (n != 0) {
                if (trim.compareTo("value") != 0) {
                    System.out.println("Unable to find 'value=' for name=" + trim);
                    return false;
                }
                if (b) {
                    System.out.println(s + " = " + trim2);
                }
                hashtable.put(s, trim2);
                n = 0;
            }
            else {
                if (b) {
                    System.out.println(trim + " = " + trim2);
                }
                hashtable.put(trim, trim2);
            }
            i = n4 + 1;
        } while (i <= this.app.length());
        System.out.println("Index i=" + i + " app.length=" + this.app.length());
        return false;
    }
    
    public static void main(final String[] array) {
        final Hashtable hashtable = new Hashtable();
        try {
            System.out.println("Status from tokenIt=" + new AppletParams(new FileInputStream(array[0])).tokenIt(hashtable, true));
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
