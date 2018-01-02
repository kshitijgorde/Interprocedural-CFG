// 
// Decompiled by Procyon v0.5.30
// 

package ActiveNetLib.PointOfSale;

import java.math.RoundingMode;
import java.util.List;
import java.math.BigDecimal;
import java.io.Serializable;

public class POSFeeDetail implements Serializable
{
    private static final long serialVersionUID = 201001L;
    public BigDecimal price;
    public BigDecimal fee_portion;
    public BigDecimal[] tax;
    public boolean[] taxable_by;
    public int[] tax_gl_account_id;
    public int gl_account_id;
    public int system_gl_account_package_id;
    public int charge_id;
    public String description;
    public boolean discountable;
    public int discount_number;
    public BigDecimal residual_discount;
    public POSReceiptCustomer buyer;
    public String item_tax_country;
    public String item_tax_state;
    public BigDecimal qty;
    public BigDecimal unit_price;
    
    public POSFeeDetail() {
        this.price = BigDecimal.ZERO;
        this.fee_portion = BigDecimal.ZERO;
        this.tax = new BigDecimal[POSTax.number_of_taxes];
        this.taxable_by = new boolean[POSTax.number_of_taxes];
        this.tax_gl_account_id = new int[POSTax.number_of_taxes];
        this.gl_account_id = 0;
        this.system_gl_account_package_id = 0;
        this.charge_id = 0;
        this.description = null;
        this.discountable = false;
        this.discount_number = 0;
        this.residual_discount = null;
        this.buyer = null;
        this.item_tax_country = null;
        this.item_tax_state = null;
        this.qty = null;
        this.unit_price = null;
        for (final POSTax.whichTax which_tax : POSTax.values) {
            this.tax[which_tax.ordinal()] = BigDecimal.ZERO;
            this.taxable_by[which_tax.ordinal()] = false;
            this.tax_gl_account_id[which_tax.ordinal()] = 0;
        }
    }
    
    public POSFeeDetail(final BigDecimal price, final POSKeyboardButton button, final POSTaxRates tax_rates, final POSReceiptCustomer customer) {
        this(price, button.gl_account_id, button.system_gl_account_package_id, 0, button.product_name, button.taxable_by, tax_rates, customer, button.item_country, button.item_state);
    }
    
    public POSFeeDetail(final BigDecimal price, final POSKeyboardProductFee fee, final POSTaxRates tax_rates, final POSReceiptCustomer customer, final String item_tax_country, final String item_tax_state) {
        this(price, fee.gl_account_id, 0, fee.charge_id, fee.fee_description, fee.taxable_by, tax_rates, customer, item_tax_country, item_tax_state);
    }
    
    public POSFeeDetail(final BigDecimal price, final int gl_account_id, final int system_gl_account_package_id, final int charge_id, final String description, final boolean[] taxable_by, final POSTaxRates tax_rates, final POSReceiptCustomer customer, final String item_tax_country, final String item_tax_state) {
        this();
        this.price = price;
        this.gl_account_id = gl_account_id;
        this.system_gl_account_package_id = system_gl_account_package_id;
        this.charge_id = charge_id;
        this.description = description;
        this.taxable_by = taxable_by;
        this.buyer = customer;
        this.item_tax_country = item_tax_country;
        this.item_tax_state = item_tax_state;
        this.calculateCharges(tax_rates);
    }
    
    public POSFeeDetail(final POSFeeDetail fd, final int discount_quantity, final POSTaxRates tax_rates) {
        this();
        this.price = fd.price;
        this.gl_account_id = fd.gl_account_id;
        this.system_gl_account_package_id = fd.system_gl_account_package_id;
        this.charge_id = fd.charge_id;
        this.description = fd.description;
        this.taxable_by = fd.taxable_by;
        this.buyer = fd.buyer;
        this.item_tax_country = fd.item_tax_country;
        this.item_tax_state = fd.item_tax_state;
        if (discount_quantity > 0 && fd.qty != null && fd.qty.compareTo(BigDecimal.valueOf(discount_quantity)) > 0 && fd.unit_price != null && fd.unit_price.signum() > 0) {
            final BigDecimal amt = fd.unit_price.multiply(BigDecimal.valueOf(discount_quantity)).setScale(2, 4);
            this.price = amt;
        }
        this.calculateCharges(tax_rates);
    }
    
    public BigDecimal tax(final POSTax.whichTax which_tax) {
        return this.tax[which_tax.ordinal()];
    }
    
    public int taxGLAccountID(final POSTax.whichTax which_tax) {
        return this.tax_gl_account_id[which_tax.ordinal()];
    }
    
    public void calculateCharges(final POSTaxRates tax_rates) {
        final BigDecimal[] rate = new BigDecimal[POSTax.number_of_taxes];
        for (final POSTax.whichTax which_tax : POSTax.values) {
            if (tax_rates == null) {
                rate[which_tax.ordinal()] = BigDecimal.ZERO;
            }
            else {
                rate[which_tax.ordinal()] = this.taxRate(tax_rates, which_tax);
                this.tax_gl_account_id[which_tax.ordinal()] = this.taxGLAccountID(tax_rates, which_tax);
            }
        }
        BigDecimal absorb_percentage = BigDecimal.ZERO;
        for (final POSTax.whichTax which_tax2 : POSTax.values) {
            if (this.taxable_by[which_tax2.ordinal()] && tax_rates != null && tax_rates.tax_from_fee[which_tax2.ordinal()]) {
                absorb_percentage = absorb_percentage.add(rate[which_tax2.ordinal()]);
            }
        }
        if (absorb_percentage.signum() != 0) {
            final BigDecimal taxable_portion = this.price.divide(BigDecimal.valueOf(1L).add(absorb_percentage.movePointLeft(2)), 4).setScale(2, 4);
            BigDecimal absorbed_taxes = BigDecimal.ZERO;
            for (final POSTax.whichTax which_tax3 : POSTax.values) {
                if (this.taxable_by[which_tax3.ordinal()] && tax_rates != null && tax_rates.tax_from_fee[which_tax3.ordinal()]) {
                    this.tax[which_tax3.ordinal()] = percentage(taxable_portion, rate[which_tax3.ordinal()]).setScale(2, 4);
                    absorbed_taxes = absorbed_taxes.add(this.tax[which_tax3.ordinal()]);
                }
            }
            this.fee_portion = this.price.subtract(absorbed_taxes);
        }
        else {
            this.fee_portion = this.price;
        }
        for (final POSTax.whichTax which_tax2 : POSTax.values) {
            if (this.taxable_by[which_tax2.ordinal()] && tax_rates != null && !tax_rates.tax_from_fee[which_tax2.ordinal()]) {
                if (tax_rates.tax_brackets[which_tax2.ordinal()] != null) {
                    this.tax[which_tax2.ordinal()] = bracketedTax(tax_rates.tax_brackets[which_tax2.ordinal()], this.fee_portion);
                }
                else {
                    this.tax[which_tax2.ordinal()] = percentage(this.fee_portion, rate[which_tax2.ordinal()]).setScale(2, 4);
                }
            }
        }
    }
    
    public static BigDecimal bracketedTax(final List brackets, BigDecimal charge) {
        if (brackets == null || brackets.isEmpty()) {
            return BigDecimal.ZERO;
        }
        final POSTaxBracket last_bracket = brackets.get(brackets.size() - 1);
        if (last_bracket.to_amount.signum() <= 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal tax = BigDecimal.ZERO;
        final BigDecimal x = charge.divide(last_bracket.to_amount, 0, RoundingMode.DOWN);
        if (x.signum() != 0) {
            tax = x.multiply(last_bracket.tax);
            charge = charge.subtract(x.multiply(last_bracket.to_amount));
        }
        for (int i = 0; i < brackets.size(); ++i) {
            final POSTaxBracket bracket = brackets.get(i);
            if (charge.compareTo(bracket.from_amount) < 0) {
                break;
            }
            if (charge.compareTo(bracket.to_amount) <= 0) {
                tax = tax.add(bracket.tax);
                break;
            }
        }
        return tax;
    }
    
    private BigDecimal taxRate(final POSTaxRates rates, final POSTax.whichTax which_tax) {
        final int origin = rates.state_tax_origin[which_tax.ordinal()];
        final BigDecimal rate = rates.tax_rate[which_tax.ordinal()];
        if (origin == 0) {
            return rate;
        }
        String country = null;
        String state = null;
        if (origin == 1) {
            country = this.buyer.tax_country;
            state = this.buyer.tax_state;
        }
        else if (origin == 2) {
            country = rates.seller_country;
            state = rates.seller_state;
        }
        else {
            country = this.item_tax_country;
            state = this.item_tax_state;
        }
        final BigDecimal state_rate = rates.getStateTaxRate(country, state);
        return (state_rate != null) ? state_rate : rate;
    }
    
    private int taxGLAccountID(final POSTaxRates rates, final POSTax.whichTax which_tax) {
        final int origin = rates.state_tax_origin[which_tax.ordinal()];
        final int tax_gl_account = rates.tax_gl_account_id[which_tax.ordinal()];
        if (origin == 0) {
            return tax_gl_account;
        }
        String country = null;
        String state = null;
        if (origin == 1) {
            country = this.buyer.tax_country;
            state = this.buyer.tax_state;
        }
        else if (origin == 3) {
            country = rates.seller_country;
            state = rates.seller_state;
        }
        else {
            country = this.item_tax_country;
            state = this.item_tax_state;
        }
        final int state_id = rates.getStateTaxGLAcountID(country, state);
        return (state_id != 0) ? state_id : tax_gl_account;
    }
    
    public POSFeeDetail add(final POSFeeDetail fd) {
        this.price = this.price.add(fd.price);
        this.fee_portion = this.fee_portion.add(fd.fee_portion);
        for (final POSTax.whichTax which_tax : POSTax.values) {
            this.tax[which_tax.ordinal()] = this.tax[which_tax.ordinal()].add(fd.tax[which_tax.ordinal()]);
        }
        return this;
    }
    
    public BigDecimal taxes() {
        BigDecimal taxes = BigDecimal.ZERO;
        for (final POSTax.whichTax which_tax : POSTax.values) {
            taxes = taxes.add(this.tax[which_tax.ordinal()]);
        }
        return taxes;
    }
    
    public BigDecimal total() {
        return this.fee_portion.add(this.taxes());
    }
    
    public static BigDecimal percentage(final BigDecimal amount, final BigDecimal percent) {
        if (percent == null) {
            return BigDecimal.ZERO;
        }
        return amount.multiply(percent.movePointLeft(2)).setScale(amount.scale(), 4);
    }
    
    public String toString() {
        final StringBuilder x = new StringBuilder();
        if (this.description != null && this.description.length() > 0) {
            x.append("\r\nDesc=").append(this.description);
        }
        if (this.gl_account_id > 0) {
            x.append("\r\ngl_account=").append(this.gl_account_id);
        }
        if (this.price.signum() != 0) {
            x.append("\r\nprice=").append(this.price);
        }
        if (this.fee_portion.signum() != 0) {
            x.append("\r\nfee=").append(this.fee_portion);
        }
        for (final POSTax.whichTax which_tax : POSTax.values) {
            if (this.tax[which_tax.ordinal()].signum() != 0) {
                x.append("\r\ntax").append(which_tax.index()).append("=").append(this.tax[which_tax.ordinal()]);
            }
        }
        return x.toString();
    }
}
