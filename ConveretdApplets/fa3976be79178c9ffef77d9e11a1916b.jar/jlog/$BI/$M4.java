// 
// Decompiled by Procyon v0.5.30
// 

package jlog.$BI;

import java.util.Enumeration;
import java.util.Vector;
import java.io.PrintStream;
import jlog.$H4;

public class $M4 implements $H4
{
    public static PrintStream $SID;
    private static boolean init;
    private static $Z2C $TID;
    private static Vector $UID;
    static Object $WID;
    
    public static void $D_(final Throwable t) {
        if (t instanceof Error) {
            throw (Error)t;
        }
        if (t instanceof RuntimeException) {
            throw (RuntimeException)t;
        }
        throw new RuntimeException(t.getMessage());
    }
    
    public static void $F3C(final $Z2C $z2C) {
        $M4.$UID.addElement($z2C);
        $z2C.$U3C(true);
    }
    
    public static String $M3C() {
        final String[] array = { "java.version", "java.vendor", "java.vendor.url", "java.class.version", "os.name", "os.arch", "os.version" };
        final StringBuffer sb = new StringBuffer("\nSystem.properties\n");
        int i = 0;
        while (i < array.length) {
            final String s = array[i++];
            sb.append("\t");
            sb.append(s);
            sb.append('=');
            sb.append(System.getProperty(s));
            sb.append('\n');
        }
        return sb.toString();
    }
    
    public static void $UHD(final $Z2C $z2C) {
        $M4.$UID.removeElement($z2C);
        $z2C.$U3C(false);
    }
    
    public static void $VID() {
        if ($M4.$TID != null) {
            $UHD($M4.$TID);
            $M4.$TID = null;
        }
    }
    
    static {
        $M4.$SID = System.err;
        $M4.init = false;
        $M4.$TID = null;
        $M4.$UID = new Vector();
        $M4.$WID = new Object();
    }
    
    public static void print(final Object o) {
        print(o, null);
    }
    
    public static void print(final Object o, final Object o2) {
        if (o instanceof ThreadDeath) {
            throw (ThreadDeath)o;
        }
        synchronized ($M4.$WID) {
            if (!$M4.init) {
                $M4.init = true;
                print("properties", $M3C());
            }
            final PrintStream $sid = $M4.$SID;
            if ($sid != null) {
                $sid.println("Debug:" + o + ((o2 != null) ? ("\n\t" + o2) : ""));
                if (o instanceof Throwable) {
                    ((Throwable)o).printStackTrace($sid);
                }
                $sid.flush();
            }
            final Enumeration<$Z2C> elements = ((Vector)$M4.$UID.clone()).elements();
            while (elements.hasMoreElements()) {
                final $Z2C $z2C = elements.nextElement();
                if (o2 != null) {
                    $z2C.$BI(o, o2);
                }
                else {
                    $z2C.$BI(o);
                }
            }
        }
        // monitorexit($M4.$WID)
        if (o instanceof Error) {
            throw (Error)o;
        }
    }
}
