// 
// Decompiled by Procyon v0.5.30
// 

package ActiveNetLib.PointOfSale;

import java.text.NumberFormat;
import java.util.List;
import java.math.BigDecimal;
import java.io.Serializable;

public class POSKeyboardButton implements Serializable
{
    private static final long serialVersionUID = 201001L;
    public static final int button_type_none = 0;
    public static final int button_type_link = 1;
    public static final int button_type_product = 2;
    public static final int button_type_digit = 3;
    public static final int button_type_function = 4;
    public static final int product_option_none = 0;
    public static final int product_option_single_button_sale = 1;
    public static final int product_option_prompt_for_price = 2;
    public static final int digit_option_0 = 0;
    public static final int digit_option_1 = 1;
    public static final int digit_option_2 = 2;
    public static final int digit_option_3 = 3;
    public static final int digit_option_4 = 4;
    public static final int digit_option_5 = 5;
    public static final int digit_option_6 = 6;
    public static final int digit_option_7 = 7;
    public static final int digit_option_8 = 8;
    public static final int digit_option_9 = 9;
    public static final int digit_option_5d = 10;
    public static final int digit_option_10d = 11;
    public static final int digit_option_20d = 12;
    public static final int digit_option_00 = 13;
    public static final int digit_option_decimal = 14;
    public static final int digit_option_backspace = 15;
    public static final String[] digit_tool_tips;
    public static final int function_option_tender = 0;
    public static final int function_option_alternate_receipt = 1;
    public static final int function_option_change_price = 2;
    public static final int function_option_delete_item = 3;
    public static final int function_option_void_receipt = 4;
    public static final int function_option_payment_memo = 5;
    public static final int function_option_payment_charge = 6;
    public static final int function_option_payment_check = 7;
    public static final int function_option_payment_cash = 8;
    public static final int function_option_open_cash_drawer = 9;
    public static final int function_option_select_customer = 10;
    public static final int function_option_refund = 11;
    public static final int function_product_search = 12;
    public static final int function_option_print_daily_close = 13;
    public static final int function_option_delete_selected_items = 14;
    public static final int function_option_payment_from_account = 15;
    public static final int function_option_scan_customer = 16;
    public static final int function_option_payment_other_payment_type = 17;
    public static final int function_option_commission_allocation = 18;
    public static final String[] function_tool_tips;
    public int back_color;
    public int fore_color;
    public int button_index;
    public int button_type;
    public int button_value;
    public String caption;
    public boolean font_bold;
    public boolean font_italic;
    public String font_name;
    public int font_size;
    public boolean same_across_all_layouts;
    public boolean visible;
    public int product_option;
    public boolean discount_prior_item_only;
    public boolean discount_parent_uid_only;
    public boolean prompt_for_customer_notes;
    public String product_name;
    public String product_description;
    public int uploaded_file_id;
    public int gl_account_id;
    public int system_gl_account_package_id;
    public int default_qty;
    public BigDecimal fee_amount;
    public boolean[] taxable_by;
    public boolean discountable;
    public boolean fixed_discount;
    public boolean percentage_discount;
    public BigDecimal discount_percent;
    public boolean fee_amount_is_percentage;
    public int site_id;
    public String item_country;
    public String item_state;
    public List<POSKeyboardProductFee> fees;
    public boolean log_usage;
    public boolean no_receipt;
    public boolean track_inventory;
    public boolean promotional_item;
    public List<POSFeature> features;
    public boolean apply_individual_fee;
    public int parent_product_id;
    public List<POSKeyboardButton> linking_products;
    public List<POSKeyboardButton> discount_products;
    public int pos_membership_discount_key;
    public int discount_product_qty_min;
    public int discount_product_qty_max;
    
    public POSKeyboardButton() {
        this.back_color = 0;
        this.fore_color = 0;
        this.button_index = 0;
        this.button_type = 0;
        this.button_value = 0;
        this.caption = "";
        this.font_bold = false;
        this.font_italic = false;
        this.font_name = "";
        this.font_size = 0;
        this.same_across_all_layouts = false;
        this.visible = true;
        this.product_option = 0;
        this.discount_prior_item_only = false;
        this.discount_parent_uid_only = false;
        this.prompt_for_customer_notes = false;
        this.product_name = null;
        this.product_description = null;
        this.uploaded_file_id = 0;
        this.gl_account_id = 0;
        this.system_gl_account_package_id = 0;
        this.default_qty = 0;
        this.fee_amount = null;
        this.taxable_by = new boolean[POSTax.number_of_taxes];
        this.discountable = false;
        this.fixed_discount = false;
        this.percentage_discount = false;
        this.discount_percent = null;
        this.fee_amount_is_percentage = false;
        this.site_id = 0;
        this.item_country = null;
        this.item_state = null;
        this.fees = null;
        this.log_usage = false;
        this.no_receipt = false;
        this.track_inventory = false;
        this.promotional_item = false;
        this.features = null;
        this.apply_individual_fee = false;
        this.parent_product_id = 0;
        this.linking_products = null;
        this.discount_products = null;
        this.pos_membership_discount_key = 0;
        this.discount_product_qty_min = 0;
        this.discount_product_qty_max = 0;
    }
    
    public String productToolTip(final String code_base) {
        final StringBuilder html = new StringBuilder("<html>").append(this.product_name);
        if (this.uploaded_file_id > 0) {
            html.append("&nbsp;<img src=\"").append(code_base).append("/downloadFile.sdi?uploadedfile_id=").append(this.uploaded_file_id).append("\" border=\"0\" width=\"100\" height=\"100\"><br>");
        }
        if (this.product_description != null && this.product_description.length() > 0) {
            html.append("<br>").append(this.product_description);
            if (!this.fixed_discount && !this.percentage_discount && this.fee_amount != null && this.fee_amount.signum() != 0) {
                html.append("<br>").append(NumberFormat.getCurrencyInstance().format(this.fee_amount));
            }
        }
        else if (!this.fixed_discount && !this.percentage_discount && this.fee_amount != null && this.fee_amount.signum() != 0) {
            html.append(", ").append(NumberFormat.getCurrencyInstance().format(this.fee_amount));
        }
        return html.append("</html>").toString();
    }
    
    public String toString() {
        if (this.product_description != null && this.product_description.length() > 0) {
            return this.product_name + ": " + this.product_description;
        }
        return this.product_name;
    }
    
    static {
        digit_tool_tips = new String[] { "Enter digit 0", "Enter digit 1", "Enter digit 2", "Enter digit 3", "Enter digit 4", "Enter digit 5", "Enter digit 6", "Enter digit 7", "Enter digit 8", "Enter digit 9", "Enter $5", "Enter $10", "Enter $20", "Enter double-zero", "Enter decimal point", "Backspace" };
        function_tool_tips = new String[] { "Tender items", "Alternate receipt", "Change price of next product", "Delete last item", "Void receipt", "Enter credit memo payment", "Enter credit card payment", "Enter check payment", "Enter cash payment", "Open the cash drawer", "Select the customer", "Refund item", "Product Search", "Print daily close report", "Delete selected items", "Enter from account payment", "Scan customer", "Enter other payment type payment", "Commission allocation" };
    }
}
