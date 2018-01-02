// 
// Decompiled by Procyon v0.5.30
// 

package jlog.$OR;

public class $HZB
{
    protected static boolean $TTD;
    
    static {
        $HZB.$TTD = false;
    }
    
    public static void interrupt(final Thread thread) {
        if (!$HZB.$TTD) {
            boolean b = false;
            try {
                thread.interrupt();
                b = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            catch (Exception ex) {}
            finally {
                $HZB.$TTD = (b ^ true);
            }
        }
    }
    
    public static boolean isInterrupted(final Thread thread) {
        boolean interrupted = false;
        if (!$HZB.$TTD) {
            boolean b = false;
            try {
                interrupted = thread.isInterrupted();
                b = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            catch (Exception ex) {}
            finally {
                $HZB.$TTD = (b ^ true);
            }
        }
        return interrupted;
    }
}
