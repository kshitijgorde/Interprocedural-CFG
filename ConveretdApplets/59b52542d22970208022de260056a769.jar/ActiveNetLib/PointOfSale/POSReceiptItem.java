// 
// Decompiled by Procyon v0.5.30
// 

package ActiveNetLib.PointOfSale;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.math.BigDecimal;
import java.io.Serializable;

public class POSReceiptItem implements Serializable
{
    private static final long serialVersionUID = 201001L;
    public static final int type_server_entry = 1;
    public static final int type_product = 2;
    public static final int type_card_payment = 3;
    public static final int type_cash_payment = 4;
    public static final int type_memo_payment = 5;
    public static final int type_check_payment = 6;
    public static final int type_plan_payment = 7;
    public static final int type_other_payment = 8;
    public static final int type_cash_refund = 9;
    public static final int type_from_account = 10;
    public int type;
    public int reno;
    public int internal_reno;
    public int quantity;
    public int refund_to_inventory_qty;
    public String description;
    public POSKeyboardButton button;
    public String server_description;
    public String receipt_notes;
    public String staff_notes;
    public String customer_notes;
    public int fee_surcharge_type;
    public BigDecimal discount_percent;
    public POSFeeDetail fee_summary;
    public Vector<POSFeeDetail> fee_details;
    public Vector<POSReceiptItemDiscount> discounts;
    public POSFeeDetail refund_summary;
    public BigDecimal payment_amount;
    public String id_number;
    public String check_number;
    public String card_number;
    public String card_expiration;
    public String other_number;
    public String authorization_number;
    public int card_type_id;
    public POSReceiptCustomer customer;
    public int parent_product_id;
    public long product_group_uid;
    public long uid;
    public long parent_uid;
    public int discount_quantity;
    
    public POSReceiptItem(final int type) {
        this.type = 0;
        this.reno = 0;
        this.internal_reno = 0;
        this.quantity = 0;
        this.refund_to_inventory_qty = -1;
        this.description = null;
        this.button = null;
        this.server_description = null;
        this.receipt_notes = null;
        this.staff_notes = null;
        this.customer_notes = null;
        this.fee_surcharge_type = 0;
        this.discount_percent = null;
        this.fee_summary = null;
        this.fee_details = null;
        this.discounts = null;
        this.refund_summary = null;
        this.payment_amount = null;
        this.id_number = null;
        this.check_number = null;
        this.card_number = null;
        this.card_expiration = null;
        this.other_number = null;
        this.authorization_number = null;
        this.card_type_id = 0;
        this.customer = null;
        this.parent_product_id = 0;
        this.product_group_uid = new Date().getTime() + (int)(Math.random() * 1000.0);
        this.uid = new Date().getTime() + (int)(Math.random() * 1000.0);
        this.parent_uid = 0L;
        this.discount_quantity = 0;
        this.type = type;
    }
    
    public static POSReceiptItem cardPayment(final POSReceiptCustomer customer, final BigDecimal payment_amount, final String card_number, final String id_number, final String expiration) {
        if (payment_amount.signum() <= 0) {
            return null;
        }
        final POSReceiptItem ri = new POSReceiptItem(3);
        ri.customer = customer;
        ri.payment_amount = payment_amount;
        ri.card_number = card_number;
        ri.id_number = id_number;
        ri.card_expiration = expiration;
        ri.description = "Credit Card Payment";
        return ri;
    }
    
    public static POSReceiptItem checkPayment(final POSReceiptCustomer customer, final BigDecimal payment_amount, final String check_number, final String id_number) {
        if (payment_amount.signum() <= 0) {
            return null;
        }
        final POSReceiptItem ri = new POSReceiptItem(6);
        ri.customer = customer;
        ri.payment_amount = payment_amount;
        ri.check_number = check_number;
        ri.id_number = id_number;
        ri.description = "Check Payment";
        return ri;
    }
    
    public static POSReceiptItem memoPayment(final POSReceiptCustomer customer, final BigDecimal payment_amount, final String memo_number, final String id_number) {
        if (payment_amount.signum() <= 0) {
            return null;
        }
        final POSReceiptItem ri = new POSReceiptItem(5);
        ri.customer = customer;
        ri.payment_amount = payment_amount;
        ri.other_number = memo_number;
        ri.id_number = id_number;
        ri.description = "Credit Memo Payment";
        return ri;
    }
    
    public static POSReceiptItem otherPayment(final POSReceiptCustomer customer, final BigDecimal payment_amount, final String other_number, final int id, final String authorization, final String card_name) {
        if (payment_amount.signum() <= 0) {
            return null;
        }
        final POSReceiptItem ri = new POSReceiptItem(8);
        ri.customer = customer;
        ri.payment_amount = payment_amount;
        ri.other_number = other_number;
        ri.card_type_id = id;
        ri.authorization_number = authorization;
        ri.description = card_name;
        return ri;
    }
    
    public static POSReceiptItem cashPayment(final POSReceiptCustomer customer, final BigDecimal payment_amount) {
        if (payment_amount.signum() <= 0) {
            return null;
        }
        final POSReceiptItem ri = new POSReceiptItem(4);
        ri.customer = customer;
        ri.payment_amount = payment_amount;
        ri.description = "Cash Payment";
        return ri;
    }
    
    public static POSReceiptItem cashRefund(final POSReceiptCustomer customer, final BigDecimal payment_amount) {
        final POSReceiptItem ri = new POSReceiptItem(9);
        ri.customer = customer;
        ri.payment_amount = payment_amount;
        ri.description = "Cash Refund";
        return ri;
    }
    
    public static POSReceiptItem fromAccountPayment(final POSReceiptCustomer customer, final BigDecimal payment_amount) {
        final POSReceiptItem ri = new POSReceiptItem(10);
        ri.customer = customer;
        ri.payment_amount = payment_amount;
        ri.description = "From Account";
        return ri;
    }
    
    public POSFeeDetail addPercentageDiscount(final POSReceiptItem discount, final int discount_number, final POSTaxRates tax_rates, final POSReceiptCustomer customer, final int coupon_id, final boolean always_recalculate) {
        if (this.discounts == null) {
            this.discounts = new Vector<POSReceiptItemDiscount>();
        }
        final POSFeeDetail summary = new POSFeeDetail();
        if (this.fee_details == null) {
            return summary;
        }
        final boolean is_partial_discount = discount.button.pos_membership_discount_key > 0 && discount.discount_quantity > 0;
        POSFeeDetail discountable_sum = new POSFeeDetail();
        final List<POSFeeDetail> fee_detail_list = new ArrayList<POSFeeDetail>();
        for (final POSFeeDetail detail : this.fee_details) {
            if (!detail.discountable) {
                fee_detail_list.add(detail);
            }
            else if (is_partial_discount) {
                final POSFeeDetail fd = new POSFeeDetail(detail, discount.discount_quantity, tax_rates);
                fd.discountable = detail.discountable;
                discountable_sum = discountable_sum.add(fd);
                fee_detail_list.add(fd);
            }
            else {
                discountable_sum = discountable_sum.add(detail);
                fee_detail_list.add(detail);
            }
        }
        for (final POSReceiptItemDiscount item_discount : this.discounts) {
            discountable_sum = discountable_sum.add(item_discount.details);
        }
        BigDecimal total_price = discountable_sum.price;
        for (int i = 0; i < fee_detail_list.size(); ++i) {
            final POSFeeDetail detail2 = fee_detail_list.get(i);
            if (detail2.discountable) {
                final POSReceiptItemDiscount prid = new POSReceiptItemDiscount();
                prid.discount_number = discount_number;
                prid.percentage = discount.discount_percent;
                if (discount.button != null) {
                    prid.pos_product_id = discount.button.button_value;
                }
                BigDecimal this_discount = POSFeeDetail.percentage(detail2.price, prid.percentage);
                final int total_price_sign = total_price.signum();
                total_price = total_price.subtract(this_discount);
                if (total_price.signum() != total_price_sign) {
                    this_discount = this_discount.add(total_price);
                    total_price = BigDecimal.ZERO;
                }
                if (total_price.signum() == 0) {
                    prid.details = new POSFeeDetail();
                    prid.details.charge_id = discount.button.gl_account_id;
                    prid.details.description = discount.button.product_name;
                    prid.details.price = discountable_sum.price.negate();
                    prid.details.fee_portion = discountable_sum.fee_portion.negate();
                    for (final POSTax.whichTax which_tax : POSTax.values) {
                        prid.details.tax[which_tax.ordinal()] = discountable_sum.tax[which_tax.ordinal()].negate();
                        prid.details.tax_gl_account_id[which_tax.ordinal()] = discountable_sum.tax_gl_account_id[which_tax.ordinal()];
                        prid.details.taxable_by[which_tax.ordinal()] = discountable_sum.taxable_by[which_tax.ordinal()];
                    }
                    prid.details.buyer = customer;
                    prid.details.item_tax_country = detail2.item_tax_country;
                    prid.details.item_tax_state = detail2.item_tax_state;
                }
                else {
                    final boolean[] taxable_by_list = new boolean[POSTax.number_of_taxes];
                    for (final POSTax.whichTax which_tax2 : POSTax.values) {
                        taxable_by_list[which_tax2.ordinal()] = (discount.button.taxable_by[which_tax2.ordinal()] && detail2.taxable_by[which_tax2.ordinal()]);
                    }
                    prid.details = new POSFeeDetail(this_discount.negate(), discount.button.gl_account_id, 0, 0, discount.button.product_name, taxable_by_list, tax_rates, customer, detail2.item_tax_country, detail2.item_tax_state);
                }
                if (discount.button.gl_account_id == 0) {
                    prid.details.gl_account_id = detail2.gl_account_id;
                    prid.details.charge_id = detail2.charge_id;
                    this.discounts.add(prid);
                }
                summary.add(prid.details);
                discountable_sum = discountable_sum.add(prid.details);
            }
        }
        if (summary.price.signum() != 0 && discount.button.gl_account_id != 0) {
            final POSReceiptItemDiscount prid2 = new POSReceiptItemDiscount();
            prid2.always_recalculate = always_recalculate;
            prid2.discount_number = discount_number;
            prid2.percentage = discount.discount_percent;
            if (discount.button != null) {
                prid2.pos_product_id = discount.button.button_value;
            }
            prid2.coupon_id = coupon_id;
            prid2.details = summary;
            prid2.details.gl_account_id = discount.button.gl_account_id;
            prid2.details.description = discount.button.product_name;
            this.discounts.add(prid2);
        }
        return summary;
    }
    
    public POSFeeDetail addFixedDiscount(final POSReceiptItem discount, BigDecimal this_discount, final int discount_number, final POSTaxRates tax_rates, final POSReceiptCustomer customer, final int coupon_id, final boolean always_recalculate) {
        if (this.discounts == null) {
            this.discounts = new Vector<POSReceiptItemDiscount>();
        }
        if (coupon_id > 0) {
            this_discount = this_discount.min(this.fee_summary.fee_portion);
        }
        final POSReceiptItemDiscount prid = new POSReceiptItemDiscount();
        prid.always_recalculate = always_recalculate;
        prid.discount_number = discount_number;
        prid.percentage = BigDecimal.valueOf(0L);
        if (discount.button != null) {
            prid.pos_product_id = discount.button.button_value;
        }
        prid.coupon_id = coupon_id;
        prid.details = new POSFeeDetail(this_discount.negate(), discount.button, null, customer);
        this.discounts.add(prid);
        return prid.details;
    }
    
    public void removeDiscount(final int discount_number) {
        if (this.discounts == null) {
            return;
        }
        for (int i = this.discounts.size() - 1; i >= 0; --i) {
            final POSReceiptItemDiscount prid = this.discounts.get(i);
            if (prid.discount_number == discount_number) {
                this.discounts.removeElementAt(i);
            }
        }
        if (this.discounts.isEmpty()) {
            this.discounts = null;
        }
    }
    
    public POSFeeDetail discountDetails(final int discount_number) {
        if (this.discounts == null) {
            return null;
        }
        for (int i = 0; i < this.discounts.size(); ++i) {
            final POSReceiptItemDiscount prid = this.discounts.get(i);
            if (prid.discount_number == discount_number) {
                return prid.details;
            }
        }
        return null;
    }
    
    public static POSReceiptItem newProductSale(final int quantity, final BigDecimal fee_amount, final POSKeyboardButton button, final POSTaxRates tax_rates, final POSReceiptCustomer customer, final String customer_notes) {
        final POSReceiptItem pri = new POSReceiptItem(2);
        pri.button = button;
        pri.quantity = ((button.fixed_discount || button.percentage_discount) ? 0 : quantity);
        pri.discount_quantity = (((button.fixed_discount || button.percentage_discount) && button.pos_membership_discount_key > 0) ? quantity : 0);
        pri.description = button.product_name;
        pri.customer = customer;
        pri.customer_notes = customer_notes;
        if (button.percentage_discount) {
            pri.discount_percent = fee_amount;
            pri.fee_summary = new POSFeeDetail();
            return pri;
        }
        final BigDecimal unit_price = (fee_amount == null) ? BigDecimal.valueOf(0L) : fee_amount;
        final BigDecimal qty = BigDecimal.valueOf((quantity == 0) ? 1L : quantity);
        final BigDecimal price = unit_price.multiply(qty).setScale(2, 4);
        pri.fee_details = new Vector<POSFeeDetail>();
        if (button.fees == null || button.fees.isEmpty()) {
            pri.fee_summary = new POSFeeDetail();
            final POSFeeDetail fd = new POSFeeDetail(button.fixed_discount ? price.negate() : price, button, tax_rates, customer);
            fd.discountable = button.discountable;
            if (!button.fee_amount_is_percentage && !pri.isDiscount() && !pri.isPayment()) {
                fd.qty = qty;
                fd.unit_price = unit_price;
            }
            pri.fee_details.add(fd);
            pri.fee_summary = pri.fee_summary.add(fd);
        }
        else {
            pri.fee_summary = new POSFeeDetail();
            BigDecimal price_remaining = price;
            for (int i = 0; i < button.fees.size(); ++i) {
                final POSKeyboardProductFee f = button.fees.get(i);
                BigDecimal next_price = null;
                if (button.fee_amount_is_percentage) {
                    if (i < button.fees.size() - 1) {
                        next_price = POSFeeDetail.percentage(price, f.fee_amount).setScale(2, 4);
                    }
                    else {
                        next_price = price_remaining;
                    }
                    price_remaining = price_remaining.subtract(next_price);
                }
                else {
                    next_price = f.fee_amount.multiply(qty).setScale(2, 4);
                    if (unit_price.signum() < 0 && !button.fixed_discount) {
                        next_price = next_price.negate();
                    }
                }
                final POSFeeDetail fd2 = new POSFeeDetail(button.fixed_discount ? next_price.negate() : next_price, f, tax_rates, customer, button.item_country, button.item_state);
                fd2.discountable = button.discountable;
                if (!button.fee_amount_is_percentage && !pri.isDiscount() && !pri.isPayment()) {
                    fd2.qty = qty;
                    fd2.unit_price = f.fee_amount;
                }
                pri.fee_details.add(fd2);
                pri.fee_summary = pri.fee_summary.add(fd2);
            }
        }
        return pri;
    }
    
    public boolean isPayment() {
        return this.type == 4 || this.type == 3 || this.type == 6 || this.type == 5 || this.type == 7 || this.type == 8 || this.type == 9 || this.type == 10;
    }
    
    public boolean isCashPayment() {
        return this.isPayment() && this.type != 7;
    }
    
    public boolean isCCPayment() {
        return this.type == 3;
    }
    
    public boolean isServerEntry() {
        return this.type == 1;
    }
    
    public boolean isDiscount() {
        return this.button != null && (this.button.fixed_discount || this.button.percentage_discount);
    }
    
    public boolean isFeeSurcharge() {
        return this.fee_surcharge_type != 0;
    }
    
    public boolean isFromAccountPayment() {
        return this.type == 10;
    }
    
    public void recalculateCharges(final POSTaxRates tax_rates) {
        if (this.fee_details != null) {
            this.fee_summary = new POSFeeDetail();
            for (final POSFeeDetail detail : this.fee_details) {
                detail.calculateCharges(tax_rates);
                this.fee_summary = this.fee_summary.add(detail);
            }
        }
    }
    
    public BigDecimal totalDiscount() {
        BigDecimal total = BigDecimal.valueOf(0L);
        for (int i = 0; this.discounts != null && i < this.discounts.size(); ++i) {
            final POSReceiptItemDiscount disc = this.discounts.get(i);
            total = total.add(disc.details.total());
        }
        return total;
    }
    
    public boolean discountableByMembershipDiscount() {
        return !this.isServerEntry() && !this.isDiscount() && !this.isPayment() && this.button != null && this.button.discountable && this.button.discount_products != null && this.button.discount_products.size() > 0;
    }
    
    public static boolean masterCard(final String cc) {
        return cc.length() == 16 && (cc.startsWith("51") || cc.startsWith("52") || cc.startsWith("53") || cc.startsWith("54") || cc.startsWith("55"));
    }
    
    public static boolean amexCard(final String cc) {
        return cc.length() == 15 && (cc.startsWith("34") || cc.startsWith("37"));
    }
    
    public static boolean visaCard(final String cc) {
        return (cc.length() == 13 || cc.length() == 16) && cc.startsWith("4");
    }
    
    public static boolean dinersCard(final String cc) {
        return cc.length() == 14 && (cc.startsWith("300") || cc.startsWith("301") || cc.startsWith("302") || cc.startsWith("303") || cc.startsWith("304") || cc.startsWith("305") || cc.startsWith("36") || cc.startsWith("38"));
    }
    
    public static boolean discoverCard(final String cc) {
        return cc.length() == 16 && cc.startsWith("6011");
    }
    
    public static boolean enrouteCard(final String cc) {
        return cc.length() == 15 && (cc.startsWith("2014") || cc.startsWith("2149"));
    }
    
    public static boolean jcbCard(final String cc) {
        return (cc.length() == 16 && cc.startsWith("3")) || (cc.length() == 15 && (cc.startsWith("2131") || cc.startsWith("1800")));
    }
}
