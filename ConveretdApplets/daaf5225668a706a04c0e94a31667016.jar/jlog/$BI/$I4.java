// 
// Decompiled by Procyon v0.5.30
// 

package jlog.$BI;

public class $I4 extends Error
{
    private static void $KHD(final String s) {
        $I4 $i4;
        if (s != null) {
            $i4 = new $I4("ASSERTION FAILED: \"" + s + "\"");
        }
        else {
            $i4 = new $I4("ASSERTION FAILED");
        }
        System.out.print("\n");
        $i4.printStackTrace();
        System.out.print("\nERROR " + $i4.getMessage() + "\n");
        System.err.flush();
        System.out.flush();
        throw $i4;
    }
    
    public static void $QQ(final int n) {
        if (n == 0) {
            $KHD("( intexpr == 0 )");
        }
    }
    
    public static void $QQ(final int n, final String s) {
        if (n == 0) {
            $KHD(String.valueOf(s) + " ( intexpr == 0 )");
        }
    }
    
    public static void $QQ(final Object o) {
        if (o == null) {
            $KHD("( obj == null )");
        }
    }
    
    public static void $QQ(final Object o, final String s) {
        if (o == null) {
            $KHD(String.valueOf(s) + " ( obj == null )");
        }
    }
    
    public static void $QQ(final boolean b) {
        if (!b) {
            $KHD("( boolexpr == false )");
        }
    }
    
    public static void $QQ(final boolean b, final String s) {
        if (!b) {
            $KHD(String.valueOf(s) + " ( boolexpr == false )");
        }
    }
    
    $I4(final String s) {
        super(s);
    }
}
