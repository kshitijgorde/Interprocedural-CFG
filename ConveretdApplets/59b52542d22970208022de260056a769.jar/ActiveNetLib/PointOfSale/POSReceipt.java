// 
// Decompiled by Procyon v0.5.30
// 

package ActiveNetLib.PointOfSale;

import java.util.Iterator;
import java.math.BigDecimal;
import java.awt.Toolkit;
import java.util.Vector;
import java.io.Serializable;

public class POSReceipt implements Serializable
{
    private static final long serialVersionUID = 201001L;
    public static final int tender_finish = 0;
    public static final int tender_save = 1;
    public static final int tender_alternate = 2;
    public static final int tender_update = 3;
    public static final int user_options_no_refund = 1;
    public static final int user_options_no_open_cash_drawer = 16;
    public static final int user_options_no_void_receipt = 256;
    public int rno;
    public Vector<POSReceiptItem> receipt_items;
    public boolean dirty;
    public boolean open_cash_drawer;
    public String caption;
    public String receipt_text;
    public boolean short_description;
    public String receipt_number;
    public int receipt_header_id;
    public boolean print_pos_receipt;
    public boolean user_confirm_print_pos_receipt;
    public boolean print_win_receipt;
    public int next_discount_number;
    public POSReceiptCustomer receipt_customer;
    public String POSProducts_disclaimers;
    
    public POSReceipt(final POSReceiptCustomer receipt_customer) {
        this.rno = -1;
        this.receipt_items = null;
        this.dirty = false;
        this.open_cash_drawer = false;
        this.caption = null;
        this.receipt_text = null;
        this.short_description = false;
        this.receipt_number = null;
        this.receipt_header_id = 0;
        this.print_pos_receipt = false;
        this.user_confirm_print_pos_receipt = false;
        this.print_win_receipt = false;
        this.next_discount_number = 1;
        this.receipt_customer = null;
        this.POSProducts_disclaimers = null;
        this.receipt_customer = receipt_customer;
        this.receipt_items = new Vector<POSReceiptItem>();
    }
    
    public void addEntry(final POSReceiptItem item) {
        this.receipt_items.add(item);
        this.dirty = true;
    }
    
    public void addEntry(final POSReceiptItem item, final int index) {
        if (index >= 0) {
            this.receipt_items.add(index, item);
        }
        else {
            this.receipt_items.add(item);
        }
        this.dirty = true;
    }
    
    public void addEntry(final POSReceiptItem item, final POSTaxRates tax_rates) {
        this.addEntry(item, tax_rates, -1);
    }
    
    public void addEntry(final POSReceiptItem item, final POSTaxRates tax_rates, final int index) {
        if (item.button.percentage_discount) {
            item.fee_summary = new POSFeeDetail();
            if (item.button.discount_prior_item_only || item.button.discount_parent_uid_only) {
                POSReceiptItem pri = null;
                if (item.button.discount_prior_item_only) {
                    if (this.receipt_items.size() > 0) {
                        pri = this.receipt_items.get(this.receipt_items.size() - 1);
                    }
                }
                else if (item.button.discount_parent_uid_only) {
                    for (final POSReceiptItem ri : this.receipt_items) {
                        if (!ri.isDiscount() && !ri.isPayment() && ri.uid == item.parent_uid) {
                            pri = ri;
                            break;
                        }
                    }
                }
                if (pri == null || (pri.button != null && !pri.button.discountable)) {
                    if (pri == null) {
                        System.err.println("No prior item to which to apply percentage discount");
                    }
                    else {
                        System.err.println("Prior item isn't discountable");
                    }
                    Toolkit.getDefaultToolkit().beep();
                    return;
                }
                this.addEntry(item, index);
                final POSFeeDetail detail = pri.addPercentageDiscount(item, this.next_discount_number, tax_rates, this.receipt_customer, 0, true);
                if (detail != null) {
                    item.fee_summary = item.fee_summary.add(detail);
                }
            }
            else {
                this.addEntry(item, index);
                for (int i = 0; i < this.receipt_items.size(); ++i) {
                    final POSFeeDetail detail = this.receipt_items.get(i).addPercentageDiscount(item, this.next_discount_number, tax_rates, this.receipt_customer, 0, true);
                    if (detail != null) {
                        item.fee_summary = item.fee_summary.add(detail);
                    }
                }
            }
            item.fee_summary.discount_number = this.next_discount_number++;
        }
        else {
            this.addEntry(item, index);
        }
        if (item.button.discountable) {
            for (final POSReceiptItem pri2 : this.receipt_items) {
                if (pri2.isServerEntry()) {
                    continue;
                }
                if (pri2.isPayment()) {
                    continue;
                }
                if (!pri2.button.percentage_discount) {
                    continue;
                }
                if (pri2.button.discount_prior_item_only) {
                    continue;
                }
                if (pri2.button.discount_parent_uid_only) {
                    continue;
                }
                final POSFeeDetail detail2 = item.addPercentageDiscount(pri2, pri2.fee_summary.discount_number, tax_rates, this.receipt_customer, 0, true);
                if (detail2 == null) {
                    continue;
                }
                pri2.fee_summary = pri2.fee_summary.add(detail2);
            }
        }
        for (int i = 0; i < this.receipt_items.size(); ++i) {
            final POSReceiptItem discount = this.receipt_items.get(i);
            if (!discount.isServerEntry()) {
                if (!discount.isPayment()) {
                    if (discount.button.fixed_discount) {
                        BigDecimal total = BigDecimal.ZERO;
                        int count = 0;
                        for (int j = 0; j < this.receipt_items.size(); ++j) {
                            final POSReceiptItem pri3 = this.receipt_items.get(j);
                            if (!pri3.isDiscount()) {
                                if (!pri3.isPayment()) {
                                    pri3.removeDiscount(discount.fee_summary.discount_number);
                                    ++count;
                                    total = total.add(pri3.fee_summary.total());
                                }
                            }
                        }
                        discount.fee_summary.residual_discount = discount.fee_summary.total().negate();
                        if (discount.button.discount_prior_item_only || discount.button.discount_parent_uid_only) {
                            POSReceiptItem pri4 = null;
                            if (discount.button.discount_prior_item_only) {
                                if (i == 0) {
                                    Toolkit.getDefaultToolkit().beep();
                                    this.receipt_items.removeElement(item);
                                    return;
                                }
                                pri4 = this.receipt_items.get(i - 1);
                                if (pri4.isDiscount() || pri4.isPayment()) {
                                    System.err.println("Prior item is a discount or a payment -- cannot be discounted");
                                    System.err.println("PRI: " + pri4.isDiscount() + ", " + pri4.isPayment() + ", " + pri4.button.product_name + ", " + this.receipt_items.size());
                                    Toolkit.getDefaultToolkit().beep();
                                    this.receipt_items.removeElement(item);
                                    return;
                                }
                            }
                            else if (discount.button.discount_parent_uid_only) {
                                for (final POSReceiptItem ri2 : this.receipt_items) {
                                    if (!ri2.isDiscount()) {
                                        if (ri2.isPayment()) {
                                            continue;
                                        }
                                        if (ri2.uid != discount.parent_uid) {
                                            continue;
                                        }
                                        if (ri2.button == null) {
                                            continue;
                                        }
                                        if (!ri2.button.discountable) {
                                            continue;
                                        }
                                        pri4 = ri2;
                                        break;
                                    }
                                }
                                if (pri4 == null) {
                                    continue;
                                }
                            }
                            final BigDecimal this_charge = pri4.fee_summary.total();
                            BigDecimal this_discount = (discount.fee_summary.residual_discount.signum() > 0) ? discount.fee_summary.residual_discount.min(this_charge) : discount.fee_summary.residual_discount.max(this_charge);
                            final BigDecimal discounted_charge = this_charge.add(pri4.totalDiscount());
                            if (discounted_charge.subtract(this_discount).signum() != discounted_charge.signum()) {
                                this_discount = discounted_charge;
                            }
                            if (this_discount.signum() != 0) {
                                final POSFeeDetail detail3 = pri4.addFixedDiscount(discount, this_discount, this.next_discount_number, tax_rates, this.receipt_customer, 0, true);
                                if (detail3 != null) {
                                    discount.fee_summary.residual_discount = discount.fee_summary.residual_discount.add(detail3.total());
                                }
                            }
                        }
                        else {
                            for (int j = 0; j < this.receipt_items.size(); ++j) {
                                final POSReceiptItem pri3 = this.receipt_items.get(j);
                                if (!pri3.isDiscount()) {
                                    if (!pri3.isPayment()) {
                                        BigDecimal this_discount = null;
                                        final BigDecimal this_charge2 = pri3.fee_summary.total();
                                        if (count <= 1) {
                                            this_discount = discount.fee_summary.residual_discount.min(this_charge2);
                                        }
                                        else {
                                            this_discount = this_charge2.multiply(discount.fee_summary.total().negate()).divide((total.signum() == 0) ? BigDecimal.valueOf(1L) : total, 2, 4);
                                        }
                                        --count;
                                        final BigDecimal discounted_charge2 = this_charge2.add(pri3.totalDiscount());
                                        if (discounted_charge2.subtract(this_discount).signum() < 0) {
                                            this_discount = discounted_charge2;
                                        }
                                        if (this_discount.signum() > 0) {
                                            final POSFeeDetail detail4 = pri3.addFixedDiscount(discount, this_discount, this.next_discount_number, tax_rates, this.receipt_customer, 0, true);
                                            if (detail4 != null) {
                                                discount.fee_summary.residual_discount = discount.fee_summary.residual_discount.add(detail4.total());
                                                if (discount.fee_summary.residual_discount.signum() == 0) {
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        discount.fee_summary.discount_number = this.next_discount_number++;
                    }
                }
            }
        }
    }
    
    public void deleteEntry(final POSTaxRates tax_rates) {
        this.deleteEntry(tax_rates, this.receipt_items.size() - 1);
        this.dirty = true;
    }
    
    public void deleteEntry(final POSTaxRates tax_rates, final int index) {
        if (this.receipt_items.isEmpty()) {
            System.err.println("No receipt items to delete");
            Toolkit.getDefaultToolkit().beep();
            return;
        }
        final POSReceiptItem item = this.receipt_items.get(index);
        this.receipt_items.removeElement(item);
        this.dirty = true;
        if (item.button != null && item.button.percentage_discount) {
            for (int i = 0; i < this.receipt_items.size(); ++i) {
                final POSReceiptItem pri = this.receipt_items.get(i);
                pri.removeDiscount(item.fee_summary.discount_number);
            }
        }
        this.calculatePOSDiscounts(tax_rates);
    }
    
    public BigDecimal reducePayments() {
        final BigDecimal total = this.charges().total();
        BigDecimal change_due = (total.signum() < 0) ? BigDecimal.valueOf(0L) : this.payments().subtract(total);
        change_due = change_due.min(this.cashPayments());
        if (change_due.signum() <= 0) {
            return BigDecimal.valueOf(0L);
        }
        BigDecimal reduction = change_due;
        for (int i = 0; i < this.receipt_items.size(); ++i) {
            final POSReceiptItem item = this.receipt_items.get(i);
            if (item.isPayment()) {
                if (item.type == 4) {
                    final BigDecimal this_reduction = item.payment_amount.min(reduction);
                    item.payment_amount = item.payment_amount.subtract(this_reduction);
                    reduction = reduction.subtract(this_reduction);
                    if (reduction.signum() == 0) {
                        break;
                    }
                }
            }
        }
        return change_due;
    }
    
    public POSFeeDetail charges() {
        POSFeeDetail val = new POSFeeDetail();
        for (int i = 0; i < this.receipt_items.size(); ++i) {
            final POSReceiptItem item = this.receipt_items.get(i);
            if (!item.isPayment()) {
                final POSFeeDetail fee_summary = item.fee_summary;
                if (fee_summary != null) {
                    val = val.add(fee_summary);
                }
                if (item.refund_summary != null) {
                    val = val.add(item.refund_summary);
                }
            }
        }
        return val;
    }
    
    public BigDecimal payments() {
        BigDecimal val = BigDecimal.valueOf(0L);
        for (int i = 0; i < this.receipt_items.size(); ++i) {
            final POSReceiptItem item = this.receipt_items.get(i);
            if (item.isPayment()) {
                val = val.add(item.payment_amount);
            }
        }
        return val;
    }
    
    public BigDecimal cashPayments() {
        BigDecimal val = BigDecimal.valueOf(0L);
        for (int i = 0; i < this.receipt_items.size(); ++i) {
            final POSReceiptItem item = this.receipt_items.get(i);
            if (item.isPayment()) {
                if (item.type == 4) {
                    val = val.add(item.payment_amount);
                }
            }
        }
        return val;
    }
    
    public int sellQuantity(final int pos_product_id) {
        int qty = 0;
        for (int i = 0; i < this.receipt_items.size(); ++i) {
            final POSReceiptItem item = this.receipt_items.get(i);
            if (!item.isServerEntry()) {
                if (!item.isDiscount()) {
                    if (!item.isPayment()) {
                        if (item.button.button_value == pos_product_id) {
                            if (item.fee_summary.total().signum() >= 0) {
                                qty += item.quantity;
                            }
                        }
                    }
                }
            }
        }
        return qty;
    }
    
    public int refundQuantity(final int pos_product_id) {
        int qty = 0;
        for (int i = 0; i < this.receipt_items.size(); ++i) {
            final POSReceiptItem item = this.receipt_items.get(i);
            if (!item.isServerEntry()) {
                if (!item.isDiscount()) {
                    if (!item.isPayment()) {
                        if (item.button.button_value == pos_product_id) {
                            if (item.fee_summary.total().signum() < 0) {
                                qty += item.quantity;
                            }
                        }
                    }
                }
            }
        }
        return qty;
    }
    
    public void calculatePOSDiscounts(final POSTaxRates tax_rates) {
        for (int i = 0; i < this.receipt_items.size(); ++i) {
            final POSReceiptItem item = this.receipt_items.get(i);
            if (item.discounts != null) {
                int j;
                for (int count = j = item.discounts.size() - 1; j >= 0; --j) {
                    final POSReceiptItemDiscount prid = item.discounts.get(j);
                    if (prid.always_recalculate) {
                        item.discounts.remove(j);
                    }
                }
            }
        }
        this.next_discount_number = 1;
        for (int i = 0; i < this.receipt_items.size(); ++i) {
            final POSReceiptItem item = this.receipt_items.get(i);
            if (!item.isServerEntry()) {
                if (!item.isPayment()) {
                    if (item.button.percentage_discount) {
                        item.fee_summary = new POSFeeDetail();
                        if (item.button.discount_prior_item_only || item.button.discount_parent_uid_only) {
                            POSReceiptItem pri = null;
                            if (item.button.discount_prior_item_only) {
                                if (i == 0) {
                                    continue;
                                }
                                pri = this.receipt_items.get(i - 1);
                            }
                            else if (item.button.discount_parent_uid_only) {
                                for (final POSReceiptItem ri : this.receipt_items) {
                                    if (!ri.isDiscount() && !ri.isPayment() && ri.uid == item.parent_uid) {
                                        pri = ri;
                                        break;
                                    }
                                }
                            }
                            if (pri == null) {
                                continue;
                            }
                            final POSFeeDetail detail = pri.addPercentageDiscount(item, this.next_discount_number, tax_rates, this.receipt_customer, 0, true);
                            if (detail != null) {
                                item.fee_summary = item.fee_summary.add(detail);
                            }
                        }
                        else {
                            for (int k = 0; k < this.receipt_items.size(); ++k) {
                                final POSReceiptItem pri2 = this.receipt_items.get(k);
                                if (!pri2.isFeeSurcharge()) {
                                    final POSFeeDetail detail2 = pri2.addPercentageDiscount(item, this.next_discount_number, tax_rates, this.receipt_customer, 0, true);
                                    if (detail2 != null) {
                                        item.fee_summary = item.fee_summary.add(detail2);
                                    }
                                }
                            }
                        }
                        item.fee_summary.discount_number = this.next_discount_number++;
                    }
                }
            }
        }
        for (int i = 0; i < this.receipt_items.size(); ++i) {
            final POSReceiptItem discount = this.receipt_items.get(i);
            if (!discount.isServerEntry()) {
                if (!discount.isPayment()) {
                    if (discount.button.fixed_discount) {
                        BigDecimal total = BigDecimal.valueOf(0L);
                        int count2 = 0;
                        for (int l = 0; l < this.receipt_items.size(); ++l) {
                            final POSReceiptItem pri3 = this.receipt_items.get(l);
                            if (!pri3.isDiscount()) {
                                if (!pri3.isPayment()) {
                                    if (!pri3.isFeeSurcharge()) {
                                        pri3.removeDiscount(discount.fee_summary.discount_number);
                                        ++count2;
                                        total = total.add(pri3.fee_summary.total());
                                    }
                                }
                            }
                        }
                        discount.fee_summary.residual_discount = discount.fee_summary.total().negate();
                        if (discount.button.discount_prior_item_only || discount.button.discount_parent_uid_only) {
                            POSReceiptItem pri4 = null;
                            if (discount.button.discount_prior_item_only) {
                                if (i == 0) {
                                    continue;
                                }
                                pri4 = this.receipt_items.get(i - 1);
                            }
                            else if (discount.button.discount_parent_uid_only) {
                                for (final POSReceiptItem ri2 : this.receipt_items) {
                                    if (!ri2.isDiscount()) {
                                        if (ri2.isPayment()) {
                                            continue;
                                        }
                                        if (ri2.uid != discount.parent_uid) {
                                            continue;
                                        }
                                        if (ri2.button == null) {
                                            continue;
                                        }
                                        if (!ri2.button.discountable) {
                                            continue;
                                        }
                                        pri4 = ri2;
                                        break;
                                    }
                                }
                            }
                            if (pri4 == null || pri4.isDiscount() || pri4.isPayment()) {
                                continue;
                            }
                            if (pri4.isFeeSurcharge()) {
                                continue;
                            }
                            final BigDecimal this_charge = pri4.fee_summary.total();
                            BigDecimal this_discount = (discount.fee_summary.residual_discount.signum() > 0) ? discount.fee_summary.residual_discount.min(this_charge) : discount.fee_summary.residual_discount.max(this_charge);
                            final BigDecimal discounted_charge = this_charge.add(pri4.totalDiscount());
                            if (discounted_charge.subtract(this_discount).signum() != discounted_charge.signum()) {
                                this_discount = discounted_charge;
                            }
                            if (this_discount.signum() != 0) {
                                final POSFeeDetail detail3 = pri4.addFixedDiscount(discount, this_discount, this.next_discount_number, tax_rates, this.receipt_customer, 0, true);
                                if (detail3 != null) {
                                    discount.fee_summary.residual_discount = discount.fee_summary.residual_discount.add(detail3.total());
                                }
                            }
                        }
                        else {
                            for (int l = 0; l < this.receipt_items.size(); ++l) {
                                final POSReceiptItem pri3 = this.receipt_items.get(l);
                                if (!pri3.isFeeSurcharge()) {
                                    if (!pri3.isDiscount()) {
                                        if (!pri3.isPayment()) {
                                            BigDecimal this_discount = null;
                                            final BigDecimal this_charge2 = pri3.fee_summary.total();
                                            if (count2 <= 1) {
                                                this_discount = discount.fee_summary.residual_discount.min(this_charge2);
                                            }
                                            else {
                                                this_discount = this_charge2.multiply(discount.fee_summary.total().negate()).divide((total.signum() == 0) ? BigDecimal.valueOf(1L) : total, 2, 4);
                                            }
                                            --count2;
                                            final BigDecimal discounted_charge2 = this_charge2.add(pri3.totalDiscount());
                                            if (discounted_charge2.subtract(this_discount).signum() < 0) {
                                                this_discount = discounted_charge2;
                                            }
                                            if (this_discount.signum() > 0) {
                                                final POSFeeDetail detail4 = pri3.addFixedDiscount(discount, this_discount, this.next_discount_number, tax_rates, this.receipt_customer, 0, true);
                                                if (detail4 != null) {
                                                    discount.fee_summary.residual_discount = discount.fee_summary.residual_discount.add(detail4.total());
                                                    if (discount.fee_summary.residual_discount.signum() == 0) {
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        discount.fee_summary.discount_number = this.next_discount_number++;
                    }
                }
            }
        }
    }
}
