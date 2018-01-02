// 
// Decompiled by Procyon v0.5.30
// 

package ActiveNetLib.PointOfSale;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.io.Serializable;

public class POSTaxRates implements Serializable
{
    private static final long serialVersionUID = 201001L;
    public static final int state_tax_none = 0;
    public static final int state_tax_from_customer = 1;
    public static final int state_tax_from_seller_site = 2;
    public static final int state_tax_from_revenue_site = 3;
    public BigDecimal[] tax_rate;
    public int[] tax_gl_account_id;
    public String[] tax_name;
    public boolean[] tax_from_fee;
    public int[] state_tax_origin;
    public List[] tax_brackets;
    public String seller_state;
    public String seller_country;
    private List<POSStateTax> state_taxes;
    
    public POSTaxRates() {
        this.tax_rate = new BigDecimal[POSTax.number_of_taxes];
        this.tax_gl_account_id = new int[POSTax.number_of_taxes];
        this.tax_name = new String[POSTax.number_of_taxes];
        this.tax_from_fee = new boolean[POSTax.number_of_taxes];
        this.state_tax_origin = new int[POSTax.number_of_taxes];
        this.tax_brackets = new List[POSTax.number_of_taxes];
        this.seller_state = null;
        this.seller_country = null;
        this.state_taxes = null;
    }
    
    public void addStateTax(final String country, final String state, final int gl_account_id, final BigDecimal tax_rate) {
        if (this.state_taxes == null) {
            this.state_taxes = new ArrayList<POSStateTax>();
        }
        this.state_taxes.add(new POSStateTax(country, state, gl_account_id, tax_rate));
    }
    
    public BigDecimal getStateTaxRate(String country, final String state) {
        if (country != null && country.length() == 0) {
            country = null;
        }
        if (this.state_taxes != null) {
            for (final POSStateTax tax : this.state_taxes) {
                if (country != null) {
                    if (tax.country == null) {
                        continue;
                    }
                    if (!country.equalsIgnoreCase(tax.country)) {
                        continue;
                    }
                }
                if (state != null) {
                    if (tax.state == null) {
                        continue;
                    }
                    if (!state.equalsIgnoreCase(tax.state)) {
                        continue;
                    }
                }
                return tax.tax_rate;
            }
        }
        return null;
    }
    
    public int getStateTaxGLAcountID(final String country, final String state) {
        if (this.state_taxes != null) {
            for (final POSStateTax tax : this.state_taxes) {
                if (country != null) {
                    if (tax.country == null) {
                        continue;
                    }
                    if (!country.equalsIgnoreCase(tax.country)) {
                        continue;
                    }
                }
                if (state != null) {
                    if (tax.state == null) {
                        continue;
                    }
                    if (!state.equalsIgnoreCase(tax.state)) {
                        continue;
                    }
                }
                return tax.gl_account_id;
            }
        }
        return 0;
    }
    
    class POSStateTax implements Serializable
    {
        private static final long serialVersionUID = 201001L;
        String country;
        String state;
        int gl_account_id;
        BigDecimal tax_rate;
        
        POSStateTax(final String country, final String state, final int gl_account_id, final BigDecimal tax_rate) {
            this.country = null;
            this.state = null;
            this.gl_account_id = 0;
            this.tax_rate = null;
            this.country = country;
            this.state = state;
            this.gl_account_id = gl_account_id;
            this.tax_rate = tax_rate;
        }
    }
}
