// 
// Decompiled by Procyon v0.5.30
// 

package ActiveNetLib.PointOfSale;

public class POSTax
{
    public static final int number_of_taxes;
    public static final whichTax[] values;
    
    static {
        number_of_taxes = whichTax.values().length;
        values = whichTax.values();
    }
    
    public enum whichTax
    {
        tax1, 
        tax2, 
        tax3, 
        tax4, 
        tax5, 
        tax6, 
        tax7, 
        tax8;
        
        private String index;
        
        private whichTax() {
            this.index = "";
            this.index = String.valueOf(this.ordinal() + 1);
        }
        
        public String index() {
            return this.index;
        }
    }
}
