// 
// Decompiled by Procyon v0.5.30
// 

package fi.netclock;

public class Application
{
    private if bVa;
    private static String T = "\ubc6b\ubc4a\ubc41\ubc04\ubc54\ubc45\ubc56\ubc45\ubc49\ubc41\ubc50\ubc41\ubc56\ubc04\ubc4d\ubc57\ubc04\ubc4a\ubc41\ubc41\ubc40\ubc41\ubc40\ubc05\ubc04\ubc67\ubc4b\ubc56\ubc56\ubc41\ubc47\ubc50\ubc04\ubc54\ubc45\ubc56\ubc45\ubc49\ubc41\ubc50\ubc41\ubc56\ubc04\ubc4d\ubc57\ubc04\ubc50\ubc4c\ubc41\ubc04\ubc57\ubc4f\ubc4d\ubc4a\ubc04\ubc42\ubc4d\ubc48\ubc41\ubc04\ubc48\ubc4b\ubc47\ubc45\ubc50\ubc4d\ubc4b\ubc4a\ubc0a";
    private static String U = "\ubc6b\ubc4a\ubc48\ubc5d\ubc04\ubc4b\ubc4a\ubc41\ubc04\ubc54\ubc45\ubc56\ubc45\ubc49\ubc41\ubc50\ubc41\ubc56\ubc04\ubc4d\ubc57\ubc04\ubc54\ubc41\ubc56\ubc49\ubc4d\ubc50\ubc50\ubc41\ubc40\ubc05\ubc04\ubc67\ubc4b\ubc56\ubc56\ubc41\ubc47\ubc50\ubc04\ubc54\ubc45\ubc56\ubc45\ubc49\ubc41\ubc50\ubc41\ubc56\ubc04\ubc4d\ubc57\ubc04\ubc50\ubc4c\ubc41\ubc04\ubc57\ubc4f\ubc4d\ubc4a\ubc04\ubc42\ubc4d\ubc48\ubc41\ubc04\ubc48\ubc4b\ubc47\ubc45\ubc50\ubc4d\ubc4b\ubc4a\ubc0a";
    
    public static void main(final String[] array) {
        if (array.length < 1) {
            System.err.println(Application.T);
            System.exit(1);
        }
        else if (array.length > 1) {
            System.err.println(Application.U);
            System.exit(1);
        }
        new Application().l(array[0]);
    }
    
    public void l(final String s) {
        this.bVa = new if(s);
    }
    
    private static String g(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\ubc24');
        }
        return new String(array);
    }
    
    static {
        Application.T = g(Application.T);
        Application.U = g(Application.U);
    }
}
