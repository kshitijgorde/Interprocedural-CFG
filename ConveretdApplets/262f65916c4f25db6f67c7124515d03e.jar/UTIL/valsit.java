// 
// Decompiled by Procyon v0.5.30
// 

package UTIL;

public class valsit
{
    private hex a;
    
    public boolean vs(final String s) {
        final String[] array = { "66.69.6c.65.3a", "68.74.74.70.3a.2f.2f.61.70.69.2e.6b.72.79.70.74.6f.6e.77.61.72.65" };
        this.a = new hex();
        for (int i = 0; i < array.length; ++i) {
            if (s.substring(0, s.indexOf("/")).equals(this.a.cH2S(array[i]))) {
                return true;
            }
            if (s.substring(0, s.indexOf(".com")).equals(this.a.cH2S(array[i]))) {
                return true;
            }
        }
        System.out.println("Error: Please contact an Admin from I-Console.com; you will need their permission to run this on your site.");
        return false;
    }
    
    public boolean vsIconsole(final String s) {
        final String[] array = { "66.69.6c.65.3a", "68.74.74.70.3a.2f.2f.61.70.69.2e.6b.72.79.70.74.6f.6e.77.61.72.65" };
        this.a = new hex();
        for (int i = 0; i < array.length; ++i) {
            if (s.substring(0, s.indexOf("/")).equals(this.a.cH2S(array[i]))) {
                return false;
            }
            if (s.substring(0, s.indexOf(".com")).equals(this.a.cH2S(array[i]))) {
                return true;
            }
        }
        System.out.println("Error: Please contact an Admin from I-Console.com; you will need their permission to run this on your site.");
        return false;
    }
}
