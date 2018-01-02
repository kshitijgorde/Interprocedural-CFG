import java.util.StringTokenizer;
import javax.swing.Icon;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.util.Locale;
import java.awt.event.FocusListener;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ComponentAdapter;
import javax.swing.text.Document;
import javax.swing.JTextArea;
import ActiveNetLib.PointOfSale.POSCustomerScanType;
import ActiveNetLib.PointOfSale.POSSite;
import ActiveNetLib.PointOfSale.POSProductSubclass;
import ActiveNetLib.PointOfSale.POSProductDepartment;
import ActiveNetLib.PointOfSale.POSProductClass;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;
import javax.swing.Action;
import javax.swing.LayoutStyle;
import javax.swing.GroupLayout;
import javax.swing.ListModel;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.JList;
import javax.swing.JDialog;
import javax.swing.DefaultListSelectionModel;
import java.awt.event.ComponentEvent;
import java.util.Collection;
import java.awt.event.ComponentListener;
import javax.swing.SwingUtilities;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import javax.swing.JComboBox;
import java.awt.event.WindowAdapter;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.text.DateFormat;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.StringReader;
import java.net.URLDecoder;
import java.util.Calendar;
import javax.swing.JPasswordField;
import java.io.PrintStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.awt.Cursor;
import java.io.InputStream;
import java.net.URLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.StreamCorruptedException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import javax.swing.table.TableColumn;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.TableModel;
import java.net.URLEncoder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.JSplitPane;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.lang.reflect.InvocationTargetException;
import java.io.File;
import javax.swing.text.html.parser.ParserDelegator;
import java.awt.Toolkit;
import java.awt.event.WindowListener;
import java.awt.Window;
import java.awt.Color;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import ActiveNetLib.PointOfSale.POSReceiptItem;
import java.awt.Component;
import javax.swing.JOptionPane;
import ActiveNetLib.PointOfSale.POSFeeDetail;
import ActiveNetLib.PointOfSale.POSTax;
import java.math.BigDecimal;
import ActiveNetLib.PointOfSale.POSTaxRates;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JPanel;
import ActiveNetLib.PointOfSale.POSKeyboardButton;
import ActiveNetLib.PointOfSale.POSReceiptCustomer;
import ActiveNetLib.PointOfSale.POSReceipt;
import ActiveNetLib.PointOfSale.POSOtherPaymentType;
import java.util.ArrayList;
import ActiveNetLib.PointOfSale.POSKeyboard;
import java.util.Vector;
import java.net.URL;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

package java.lang;

import java.io.Serializable;

public class PointOfSale extends JApplet
{
    private static final long serialVersionUID = 201001L;
    private static final int font_small = 0;
    private static final int font_medium = 1;
    private static final int font_large = 2;
    private static final int prompt_type_cash = 0;
    private static final int prompt_type_charge = 1;
    private static final int prompt_type_check = 2;
    private static final int prompt_type_memo = 3;
    private static final int prompt_type_price = 4;
    private static final int prompt_type_quantity = 5;
    private static final int prompt_type_fixed_discount = 6;
    private static final int prompt_type_percentage_discount = 7;
    private static final int prompt_type_receipt_finished = 8;
    private static final int prompt_type_cash_refund = 9;
    private static final int prompt_type_from_account = 10;
    private static final int prompt_type_others = 11;
    private static final String[] titles;
    private static final int user_options_no_refund = 1;
    private static final int user_options_no_open_cash_drawer = 16;
    private static final int user_options_no_void_receipt = 256;
    private static final int receipt_width = 230;
    private static final int receipt_height = 500;
    private static final int[] table_column_preferred_widths;
    private static final int field_type_numeric = 0;
    private static final int field_type_currency = 1;
    private URL code_base;
    private String secure_preface;
    private boolean mac_os_x;
    private static Vector<POSKeyboard> keyboards;
    private static int keyboard_revision;
    private static ArrayList departments;
    private static ArrayList classes;
    private static ArrayList subclasses;
    private static ArrayList sites;
    private static ArrayList avail_customer_scan_types;
    private static ArrayList<POSOtherPaymentType> other_payment_types;
    private static boolean searching_customers;
    POSReceipt pos_receipt;
    POSReceiptCustomer default_customer;
    String customer_notes;
    POSKeyboardButton customer_note_button;
    int com_port;
    int pole_display_com_port;
    boolean enable_pole_display;
    JPanel receipt_area;
    JTable receipt_table;
    ReceiptTableModel receipt_table_model;
    JLabel customer_field;
    JLabel refund_label;
    JLabel subtotal_label;
    JTextField subtotal_field;
    JLabel grand_total_label;
    JTextField grand_total_field;
    JLabel change_label;
    JTextField change_field;
    JLabel upc_name_label;
    JTextField upc_name_field;
    JLabel payment_label;
    JTextField payment_field;
    POSPanel pos_panel;
    PromptPanel current_prompt;
    PromptPanel cash_prompt;
    PromptPanel charge_prompt;
    PromptPanel check_prompt;
    PromptPanel memo_prompt;
    PromptPanel price_prompt;
    PromptPanel quantity_prompt;
    PromptPanel fixed_discount_prompt;
    PromptPanel percentage_discount_prompt;
    PromptPanel receipt_finished_prompt;
    PromptPanel cash_refund_prompt;
    PromptPanel charge_refund_prompt;
    PromptPanel from_account_prompt;
    PromptPanel other_payment_prompt;
    boolean price_change_pending;
    boolean refund_mode;
    boolean refund_inventory_qty;
    boolean refund_customer_note_required;
    boolean refund_receipt;
    POSKeyboardButton price_change_button;
    int pos_group_id;
    int site_id;
    String site_name;
    String site_address1;
    String site_address2;
    String site_city;
    String site_state;
    String site_zip;
    String site_phone;
    String pop_cash_drawer;
    String pop_drawer_url;
    String auto_cut;
    int eject_lines;
    String small_font;
    String medium_font;
    String large_font;
    int small_font_char_count;
    int medium_font_char_count;
    int large_font_char_count;
    private static POSPrinter pole_display_printer;
    String message_for_pole_display;
    String cursor_first_line;
    String cursor_second_line;
    String clear_display;
    String scroll_text_first_line;
    String scroll_text_second_line;
    String display_clock;
    String character_between_hours_and_minutes;
    JLabel[] tax_label;
    JTextField[] tax_field;
    POSTaxRates tax_rates;
    String receipt_number;
    int receipt_header_id;
    BigDecimal last_change_due;
    String user_name;
    String org_name;
    String org_address1;
    String org_address2;
    String org_city;
    String org_state;
    String org_zip;
    String org_phone;
    boolean cc_visa;
    boolean cc_mc;
    boolean cc_amex;
    boolean cc_diners;
    boolean cc_discover;
    boolean cc_jcb;
    boolean allow_payment_with_cash;
    boolean allow_payment_by_memo;
    boolean allow_refund_with_cash;
    long user_options;
    boolean pass_cash_drawer_profile_check;
    boolean pass_void_receipt_profile_check;
    int membership_discount_customer_id;
    boolean tabbed_admin_ui;
    String front_desk_label;
    String topmost_menu_id;
    long last_logon_check;
    
    public PointOfSale() {
        this.code_base = null;
        this.secure_preface = null;
        this.mac_os_x = false;
        this.pos_receipt = null;
        this.default_customer = null;
        this.customer_notes = null;
        this.customer_note_button = null;
        this.com_port = 0;
        this.pole_display_com_port = 0;
        this.enable_pole_display = false;
        this.receipt_area = null;
        this.receipt_table = null;
        this.receipt_table_model = null;
        this.customer_field = null;
        this.refund_label = null;
        this.subtotal_label = null;
        this.subtotal_field = null;
        this.grand_total_label = null;
        this.grand_total_field = null;
        this.change_label = null;
        this.change_field = null;
        this.upc_name_label = null;
        this.upc_name_field = null;
        this.payment_label = null;
        this.payment_field = null;
        this.pos_panel = null;
        this.current_prompt = null;
        this.cash_prompt = null;
        this.charge_prompt = null;
        this.check_prompt = null;
        this.memo_prompt = null;
        this.price_prompt = null;
        this.quantity_prompt = null;
        this.fixed_discount_prompt = null;
        this.percentage_discount_prompt = null;
        this.receipt_finished_prompt = null;
        this.cash_refund_prompt = null;
        this.charge_refund_prompt = null;
        this.from_account_prompt = null;
        this.other_payment_prompt = null;
        this.price_change_pending = false;
        this.refund_mode = false;
        this.refund_inventory_qty = false;
        this.refund_customer_note_required = false;
        this.refund_receipt = false;
        this.price_change_button = null;
        this.pos_group_id = 0;
        this.site_id = 0;
        this.site_name = null;
        this.site_address1 = null;
        this.site_address2 = null;
        this.site_city = null;
        this.site_state = null;
        this.site_zip = null;
        this.site_phone = null;
        this.pop_cash_drawer = null;
        this.pop_drawer_url = null;
        this.auto_cut = null;
        this.eject_lines = 0;
        this.small_font = null;
        this.medium_font = null;
        this.large_font = null;
        this.small_font_char_count = 0;
        this.medium_font_char_count = 0;
        this.large_font_char_count = 0;
        this.message_for_pole_display = null;
        this.cursor_first_line = null;
        this.cursor_second_line = null;
        this.clear_display = null;
        this.scroll_text_first_line = null;
        this.scroll_text_second_line = null;
        this.display_clock = null;
        this.character_between_hours_and_minutes = null;
        this.tax_label = new JLabel[POSTax.number_of_taxes];
        this.tax_field = new JTextField[POSTax.number_of_taxes];
        this.tax_rates = null;
        this.receipt_number = null;
        this.receipt_header_id = 0;
        this.last_change_due = BigDecimal.valueOf(0L);
        this.user_name = null;
        this.org_name = null;
        this.org_address1 = null;
        this.org_address2 = null;
        this.org_city = null;
        this.org_state = null;
        this.org_zip = null;
        this.org_phone = null;
        this.cc_visa = false;
        this.cc_mc = false;
        this.cc_amex = false;
        this.cc_diners = false;
        this.cc_discover = false;
        this.cc_jcb = false;
        this.allow_payment_with_cash = true;
        this.allow_payment_by_memo = true;
        this.allow_refund_with_cash = true;
        this.user_options = 0L;
        this.pass_cash_drawer_profile_check = false;
        this.pass_void_receipt_profile_check = false;
        this.membership_discount_customer_id = 0;
        this.tabbed_admin_ui = false;
        this.front_desk_label = "Front Desk";
        this.topmost_menu_id = "tab_pos";
        this.last_logon_check = 0L;
    }
    
    private void showPaymentPanel(final PromptPanel p, final int type) {
        final POSFeeDetail detail = this.pos_receipt.charges();
        final BigDecimal total = detail.total();
        if (total.signum() <= 0 && 8 != type) {
            beep();
            return;
        }
        this.hidePrompt();
        this.current_prompt = p;
        final BigDecimal amount_tendered = total.subtract(this.pos_receipt.payments());
        this.current_prompt.setInitialFocus(this.refund_receipt ? amount_tendered.negate() : amount_tendered);
    }
    
    private void handleProductButton(final POSKeyboardButton button) {
        if ((button.prompt_for_customer_notes || (this.refund_customer_note_required && this.refund_mode)) && this.customer_notes == null) {
            this.customer_note_button = button;
            this.pos_panel.customer_notes_dialog.showDialog();
            return;
        }
        int qty = 1;
        if (button.fixed_discount || button.percentage_discount) {
            this.hidePrompt();
        }
        else {
            qty = button.default_qty;
            if (this.current_prompt == this.quantity_prompt) {
                if (this.quantity_prompt.qty_field.getText().length() > 0) {
                    qty = this.getInt(this.quantity_prompt.qty_field.getText());
                }
                this.hidePrompt();
            }
        }
        int refund_to_inventory_qty = -1;
        this.refund_inventory_qty = false;
        if (button.track_inventory && this.refund_mode) {
            if (JOptionPane.showConfirmDialog(this.pos_panel, "Return this item to inventory?", "Confirm Inventory Count", 0, 3) == 0) {
                refund_to_inventory_qty = qty;
                this.refund_inventory_qty = true;
            }
            else {
                refund_to_inventory_qty = 0;
            }
        }
        if (this.price_change_pending || button.product_option == 2) {
            if (!button.percentage_discount && !button.fixed_discount && button.fees != null && button.fees.size() > 1 && !button.fee_amount_is_percentage) {
                beep();
                this.price_change_pending = false;
                this.price_change_button = null;
                return;
            }
            this.price_change_pending = true;
            this.price_change_button = button;
            BigDecimal amount = button.fee_amount;
            if (button.fixed_discount) {
                this.current_prompt = this.fixed_discount_prompt;
            }
            else if (button.percentage_discount) {
                this.current_prompt = this.percentage_discount_prompt;
                amount = button.discount_percent.setScale(2, 4);
            }
            else {
                this.current_prompt = this.price_prompt;
            }
            this.current_prompt.setInitialFocus(amount, qty);
        }
        else {
            if (button.product_option == 1) {
                final POSReceipt pr = new POSReceipt(this.default_customer);
                POSReceiptItem item = POSReceiptItem.newProductSale(qty, button.fee_amount, button, this.tax_rates, pr.receipt_customer, this.customer_notes);
                pr.addEntry(item, this.tax_rates);
                BigDecimal total = item.fee_summary.total();
                total = total.subtract(this.addMembershipDiscounts(pr, item, item.fee_summary.total(), qty, false));
                if (button.linking_products != null && button.linking_products.size() > 0) {
                    for (final POSKeyboardButton linking_pr : button.linking_products) {
                        BigDecimal linking_amount = linking_pr.fee_amount;
                        if (!button.apply_individual_fee) {
                            linking_amount = BigDecimal.ZERO;
                        }
                        final int linking_qty = qty * linking_pr.default_qty;
                        final POSReceiptItem linking_item = POSReceiptItem.newProductSale(linking_qty, linking_amount, linking_pr, this.tax_rates, this.pos_receipt.receipt_customer, this.customer_notes);
                        linking_item.parent_product_id = button.button_value;
                        linking_item.product_group_uid = item.product_group_uid;
                        pr.addEntry(linking_item, this.tax_rates);
                        total = total.add(linking_item.fee_summary.total());
                        total = total.subtract(this.addMembershipDiscounts(pr, linking_item, linking_item.fee_summary.total(), linking_qty, false));
                    }
                }
                item = POSReceiptItem.cashPayment(pr.receipt_customer, total);
                pr.addEntry(item);
                this.tenderReceipt(pr, 0);
                this.receipt_finished_prompt.showReceiptFinished();
                return;
            }
            BigDecimal amount = button.percentage_discount ? button.discount_percent : button.fee_amount;
            if (this.refund_mode) {
                amount = amount.negate();
            }
            POSReceiptItem item = POSReceiptItem.newProductSale(qty, amount, button, this.tax_rates, this.pos_receipt.receipt_customer, this.customer_notes);
            item.refund_to_inventory_qty = refund_to_inventory_qty;
            this.pos_receipt.addEntry(item, this.tax_rates);
            this.receipt_table_model.rowInserted(this.enable_pole_display);
            this.addMembershipDiscounts(this.pos_receipt, item, amount, qty, this.refund_mode);
            if (button.linking_products != null && button.linking_products.size() > 0) {
                for (final POSKeyboardButton linking_pr2 : button.linking_products) {
                    BigDecimal linking_amount2 = linking_pr2.fee_amount;
                    if (!button.apply_individual_fee) {
                        linking_amount2 = BigDecimal.ZERO;
                    }
                    if (this.refund_mode) {
                        linking_amount2 = linking_amount2.negate();
                    }
                    final int linking_qty2 = qty * linking_pr2.default_qty;
                    final POSReceiptItem linking_item2 = POSReceiptItem.newProductSale(linking_qty2, linking_amount2, linking_pr2, this.tax_rates, this.pos_receipt.receipt_customer, this.customer_notes);
                    linking_item2.refund_to_inventory_qty = refund_to_inventory_qty;
                    if (linking_pr2.track_inventory && this.refund_mode) {
                        if (this.refund_inventory_qty) {
                            linking_item2.refund_to_inventory_qty = refund_to_inventory_qty * linking_pr2.default_qty;
                        }
                        else {
                            linking_item2.refund_to_inventory_qty = 0;
                        }
                    }
                    linking_item2.parent_product_id = button.button_value;
                    linking_item2.product_group_uid = item.product_group_uid;
                    this.pos_receipt.addEntry(linking_item2, this.tax_rates);
                    this.receipt_table_model.rowInserted(false);
                    this.addMembershipDiscounts(this.pos_receipt, linking_item2, linking_amount2, linking_qty2, this.refund_mode);
                }
            }
            this.refund_mode = false;
            this.showTotals();
        }
    }
    
    private BigDecimal addMembershipDiscounts(final POSReceipt receipt, final POSReceiptItem item_to_discount, final BigDecimal amount, final int sell_qty, final boolean is_refunding) {
        final POSKeyboardButton button = item_to_discount.button;
        final int receipt_product_qty_total = is_refunding ? receipt.refundQuantity(button.button_value) : receipt.sellQuantity(button.button_value);
        return this.addMembershipDiscountsAt(receipt, item_to_discount, amount, sell_qty, -1, receipt_product_qty_total, is_refunding);
    }
    
    private BigDecimal addMembershipDiscountsAt(final POSReceipt receipt, final POSReceiptItem item_to_discount, final BigDecimal amount, final int sell_qty, int index, final int receipt_product_qty_total, final boolean is_refunding) {
        BigDecimal total_discount = BigDecimal.ZERO;
        if (amount == null || amount.signum() == 0 || item_to_discount == null || !item_to_discount.discountableByMembershipDiscount()) {
            return total_discount;
        }
        this.membership_discount_customer_id = receipt.receipt_customer.customer_id;
        if (receipt.receipt_customer.membership_discount_keys == null) {
            return total_discount;
        }
        final POSKeyboardButton button = item_to_discount.button;
        BigDecimal max_item_fixed_discount_to_apply = amount;
        for (final POSKeyboardButton discount_pr : button.discount_products) {
            if (is_refunding) {
                if (max_item_fixed_discount_to_apply.signum() >= 0) {
                    break;
                }
            }
            else if (max_item_fixed_discount_to_apply.signum() <= 0) {
                break;
            }
            if (!receipt.receipt_customer.membership_discount_keys.contains(discount_pr.pos_membership_discount_key)) {
                continue;
            }
            if (discount_pr.discount_product_qty_max > 0 && receipt_product_qty_total - sell_qty >= discount_pr.discount_product_qty_max) {
                continue;
            }
            if (receipt_product_qty_total < discount_pr.discount_product_qty_min) {
                continue;
            }
            int qty_to_discount = sell_qty;
            if (discount_pr.discount_product_qty_min > 0 || discount_pr.discount_product_qty_max > 0) {
                final int qty_already_discounted = (discount_pr.discount_product_qty_min == 0) ? (receipt_product_qty_total - sell_qty) : Math.max(0, receipt_product_qty_total - sell_qty - (discount_pr.discount_product_qty_min - 1));
                if (discount_pr.discount_product_qty_min > 0) {
                    qty_to_discount = Math.min(qty_to_discount, receipt_product_qty_total - discount_pr.discount_product_qty_min + 1);
                }
                if (discount_pr.discount_product_qty_max > 0) {
                    if (discount_pr.discount_product_qty_min > 0) {
                        qty_to_discount = Math.min(qty_to_discount, discount_pr.discount_product_qty_max - discount_pr.discount_product_qty_min + 1 - qty_already_discounted);
                    }
                    else {
                        qty_to_discount = Math.min(qty_to_discount, discount_pr.discount_product_qty_max - qty_already_discounted);
                    }
                }
            }
            final BigDecimal discount_amount = discount_pr.fixed_discount ? (is_refunding ? max_item_fixed_discount_to_apply.max(discount_pr.fee_amount.negate()) : max_item_fixed_discount_to_apply.min(discount_pr.fee_amount)) : discount_pr.discount_percent;
            final POSReceiptCustomer c = (item_to_discount.customer != null && item_to_discount.customer.customer_id != this.default_customer.customer_id) ? item_to_discount.customer : receipt.receipt_customer;
            final POSReceiptItem discount_item = POSReceiptItem.newProductSale(qty_to_discount, discount_amount, discount_pr, this.tax_rates, c, this.customer_notes);
            discount_item.parent_uid = item_to_discount.uid;
            discount_item.parent_product_id = button.button_value;
            if (index >= 0) {
                receipt.addEntry(discount_item, this.tax_rates, index);
            }
            else {
                receipt.addEntry(discount_item, this.tax_rates);
            }
            this.receipt_table_model.rowInserted(false, index);
            if (index >= 0) {
                ++index;
            }
            total_discount = total_discount.add(is_refunding ? discount_item.fee_summary.total().negate() : discount_item.fee_summary.total());
            if (!discount_pr.fixed_discount) {
                continue;
            }
            max_item_fixed_discount_to_apply = (is_refunding ? max_item_fixed_discount_to_apply.subtract(discount_pr.fee_amount) : max_item_fixed_discount_to_apply.add(discount_pr.fee_amount));
        }
        return total_discount;
    }
    
    private void refreshMembershipDiscounts() {
        if (this.pos_receipt.receipt_items.isEmpty()) {
            return;
        }
        for (int i = this.pos_receipt.receipt_items.size() - 1; i >= 0; --i) {
            final POSReceiptItem item = this.pos_receipt.receipt_items.get(i);
            if (item.button != null) {
                if (item.button.pos_membership_discount_key > 0) {
                    this.pos_receipt.deleteEntry(this.tax_rates, i);
                    this.receipt_table_model.rowDeleted(i);
                }
            }
        }
        final Map<Integer, Integer> receipt_product_qty_totals = new HashMap<Integer, Integer>();
        for (int j = 0; j < this.pos_receipt.receipt_items.size(); ++j) {
            final POSReceiptItem item2 = this.pos_receipt.receipt_items.get(j);
            if (item2.discountableByMembershipDiscount()) {
                int receipt_product_qty_total = 0;
                try {
                    receipt_product_qty_total = receipt_product_qty_totals.get(item2.button.button_value);
                }
                catch (NullPointerException npe) {
                    receipt_product_qty_totals.put(item2.button.button_value, 0);
                }
                receipt_product_qty_total += item2.quantity;
                this.addMembershipDiscountsAt(this.pos_receipt, item2, item2.fee_summary.total(), item2.quantity, j + 1, receipt_product_qty_total, this.refund_receipt);
                receipt_product_qty_totals.put(item2.button.button_value, receipt_product_qty_total);
            }
        }
        this.membership_discount_customer_id = this.pos_receipt.receipt_customer.customer_id;
        this.tenderReceipt(this.pos_receipt, (this.pos_receipt.rno > 0) ? 1 : 3);
        this.showTotals();
    }
    
    private void showTotals() {
        final POSFeeDetail detail = this.pos_receipt.charges();
        for (final POSTax.whichTax which_tax : POSTax.values) {
            this.showField(detail.tax[which_tax.ordinal()], this.tax_label[which_tax.ordinal()], this.tax_field[which_tax.ordinal()]);
        }
        boolean show_subtotal = false;
        for (final POSTax.whichTax which_tax2 : POSTax.values) {
            if (detail.tax[which_tax2.ordinal()].signum() != 0) {
                show_subtotal = true;
                break;
            }
        }
        this.showField(show_subtotal, detail.fee_portion, this.subtotal_label, this.subtotal_field);
        final BigDecimal grand_total = detail.total();
        if (grand_total.signum() != 0) {
            if (grand_total.signum() < 0) {
                if (!this.grand_total_label.getText().equalsIgnoreCase("refund")) {
                    this.grand_total_label.setText("Refund");
                    this.grand_total_field.setBackground(new Color(255, 153, 153));
                }
            }
            else if (!this.grand_total_label.getText().equalsIgnoreCase("total")) {
                this.grand_total_label.setText("Charges");
                this.grand_total_field.setBackground(Color.white);
            }
        }
        this.refund_receipt = (grand_total.signum() < 0);
        this.showField(grand_total, this.grand_total_label, this.grand_total_field);
        this.refund_label.setVisible(this.refund_receipt);
        final BigDecimal payments = this.pos_receipt.payments();
        this.showField(payments, this.payment_label, this.payment_field);
        BigDecimal change_due = (grand_total.signum() < 0) ? BigDecimal.valueOf(0L) : this.pos_receipt.payments().subtract(grand_total);
        change_due = change_due.min(this.pos_receipt.cashPayments()).max(BigDecimal.valueOf(0L));
        this.showField(change_due, this.change_label, this.change_field);
        this.upc_name_field.setText("");
        this.upc_name_field.requestFocus();
    }
    
    private void showField(final BigDecimal value, final JLabel label, final JTextField field) {
        this.showField(value.signum() != 0, value, label, field);
    }
    
    private void showField(final boolean show, final BigDecimal value, final JLabel label, final JTextField field) {
        if (show) {
            field.setText(value.toString());
            if (field.isVisible()) {
                return;
            }
            label.setVisible(true);
            field.setVisible(true);
        }
        else {
            if (!field.isVisible()) {
                return;
            }
            label.setVisible(false);
            field.setVisible(false);
        }
    }
    
    public void setInitialFocusComponent(final Window window, final Component component) {
        window.addWindowListener(new InitialFocusSetter(component));
    }
    
    private String colorValue(final int color) {
        String s;
        for (s = Integer.toString(color, 16); s.length() < 6; s = "0" + s) {}
        return "#" + s;
    }
    
    public int getInt(final String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        try {
            if (s.startsWith("#")) {
                return Integer.parseInt(s.substring(1), 16);
            }
            return Integer.parseInt(s);
        }
        catch (NumberFormatException e) {
            System.err.println("NumberFormatException in getInt: " + e.getMessage());
            return 0;
        }
    }
    
    public BigDecimal getDecimal(String s, final int scale) {
        if (s == null || s.length() == 0) {
            return BigDecimal.valueOf(0L);
        }
        if (s.length() > 2 && s.startsWith("(") && s.endsWith(")")) {
            s = "-" + s.substring(1, s.length() - 1);
        }
        BigDecimal bd = null;
        try {
            bd = new BigDecimal(s);
        }
        catch (NumberFormatException e) {
            System.err.println("NumberFormatException in getDecimal: " + e.getMessage());
        }
        if (bd.scale() == scale) {
            return bd;
        }
        return bd.setScale(scale, 4);
    }
    
    public boolean getBoolean(final String s) {
        return s != null && s.length() > 0;
    }
    
    public void hidePrompt() {
        if (this.current_prompt != null) {
            this.current_prompt.setVisible(false);
            this.other_payment_prompt.payment_type_list.setSelectedIndex(-1);
            this.current_prompt = null;
        }
    }
    
    public void stop() {
        System.err.println("stop ()");
        this.tenderReceipt(this.pos_receipt, 1);
    }
    
    public void start() {
        System.err.println("start ()");
    }
    
    public static void beep() {
        Toolkit.getDefaultToolkit().beep();
    }
    
    public void init() {
        System.err.println("init ()");
        final ParserDelegator workaround = new ParserDelegator();
        this.code_base = this.getCodeBase();
        System.err.println("code_base=" + this.code_base);
        this.secure_preface = this.getParameter("secure_preface");
        System.err.println("secure_preface=" + this.secure_preface);
        this.tabbed_admin_ui = this.getBoolean(this.getParameter("tabbed_admin_ui"));
        this.front_desk_label = this.getParameter("front_desk_label");
        this.topmost_menu_id = this.getParameter("topmost_menu_id");
        if (this.topmost_menu_id == null || this.topmost_menu_id.length() == 0) {
            this.topmost_menu_id = "tab_frontdesk";
        }
        final String os_name = System.getProperty("os.name");
        System.err.println("The os name is " + os_name);
        this.mac_os_x = os_name.toLowerCase().startsWith("mac os x");
        final String java_home = System.getProperty("java.home");
        System.err.println("The Java home directory is: " + java_home);
        Label_0683: {
            if (!this.mac_os_x) {
                final File jar_file = new File(java_home + "\\lib\\ext\\comm.jar");
                final File dll_file = new File(java_home + "\\bin\\win32com.dll");
                final File prop_file = new File(java_home + "\\lib\\javax.comm.properties");
                this.com_port = this.getInt(this.getParameter("com_port"));
                this.enable_pole_display = this.getBoolean(this.getParameter("enable_pole_display"));
                if (this.enable_pole_display) {
                    this.pole_display_com_port = this.getInt(this.getParameter("pole_display_com_port"));
                }
                if ((this.com_port > 0 || this.pole_display_com_port > 0) && (!jar_file.exists() || !dll_file.exists() || !prop_file.exists())) {
                    if (!jar_file.exists() && !this.installFile("comm.jar", jar_file)) {
                        this.goBack();
                        return;
                    }
                    if (!dll_file.exists() && !this.installFile("win32com.dll", dll_file)) {
                        this.goBack();
                        return;
                    }
                    if (!prop_file.exists() && !this.installFile("javax.comm.properties", prop_file)) {
                        this.goBack();
                        return;
                    }
                    this.showException("The required software has been downloaded. To finish the installation, please close your browser now. Reopen  your browser to sell POS items.");
                    this.goBack();
                    return;
                }
                else {
                    if (this.com_port <= 0) {
                        if (this.pole_display_com_port <= 0) {
                            break Label_0683;
                        }
                    }
                    try {
                        final Class<?> driver = Class.forName("com.sun.comm.Win32Driver");
                        driver.getMethod("initialize", (Class<?>[])null).invoke(driver.newInstance(), (Object[])null);
                    }
                    catch (Exception e) {
                        if (this.com_port > 0) {
                            this.showException("Unable to initialize the Comm Port driver.", "POS Receipts will not be printed.");
                            this.com_port = 0;
                            System.err.println("Exception initializing the Comm port driver: " + e.toString());
                        }
                        else {
                            this.showException("Unable to initialize the Pole Display Comm Port driver.", "Pole Display will not be display.");
                            this.pole_display_com_port = 0;
                            System.err.println("Exception initializing the Pole Display Comm port driver: " + e.toString());
                        }
                        if (e instanceof InvocationTargetException) {
                            System.err.println(((InvocationTargetException)e).getTargetException().toString());
                        }
                    }
                }
            }
        }
        this.pop_cash_drawer = this.getParameter("popcashdrawer");
        this.pop_drawer_url = this.getParameter("pop_drawer_url");
        if (this.pop_drawer_url != null && this.pop_drawer_url.length() > 0) {
            this.openCashDrawer();
            try {
                this.getAppletContext().showDocument(new URL(this.secure_preface + "/" + this.pop_drawer_url), "_self");
            }
            catch (Exception e2) {
                System.err.println("Exception in init: " + e2.toString());
            }
            return;
        }
        if (this.enable_pole_display) {
            this.message_for_pole_display = this.getParameter("message_for_pole_display");
            this.cursor_first_line = this.getParameter("cursor_first_line");
            this.cursor_second_line = this.getParameter("cursor_second_line");
            this.clear_display = this.getParameter("clear_display");
            this.scroll_text_first_line = this.getParameter("scroll_text_first_line");
            this.scroll_text_second_line = this.getParameter("scroll_text_second_line");
            this.display_clock = this.getParameter("display_clock");
            this.character_between_hours_and_minutes = this.getParameter("character_between_hours_and_minutes");
            this.initPoleDisplayPrinter();
            this.displayPoleDisplayMessage(this.message_for_pole_display, true, false, null, false, true);
        }
        this.saveKeyboardRevision(this.getInt(this.getParameter("keyboard_revision")));
        this.pos_group_id = this.getInt(this.getParameter("posgroup_id"));
        this.site_id = this.getInt(this.getParameter("site_id"));
        this.site_name = this.getParameter("sitename");
        this.site_address1 = this.getParameter("address1");
        this.site_address2 = this.getParameter("address2");
        this.site_city = this.getParameter("city");
        this.site_state = this.getParameter("site_state");
        this.site_zip = this.getParameter("zipcode");
        this.site_phone = this.getParameter("p1");
        this.tax_rates = this.getTaxRates(this.site_id);
        this.user_name = this.getParameter("user_name");
        this.org_name = this.getParameter("org_name");
        this.org_address1 = this.getParameter("org_address1");
        this.org_address2 = this.getParameter("org_address2");
        this.org_city = this.getParameter("org_city");
        this.org_state = this.getParameter("org_state");
        this.org_zip = this.getParameter("org_zip");
        this.org_phone = this.getParameter("org_phone");
        this.small_font = this.getParameter("smallfont");
        this.medium_font = this.getParameter("mediumfont");
        this.large_font = this.getParameter("largefont");
        this.small_font_char_count = this.getInt(this.getParameter("smallfontcharcount"));
        this.medium_font_char_count = this.getInt(this.getParameter("mediumfontcharcount"));
        this.large_font_char_count = this.getInt(this.getParameter("largefontcharcount"));
        this.auto_cut = this.getParameter("autocut");
        this.eject_lines = this.getInt(this.getParameter("ejectlines"));
        this.cc_visa = this.getBoolean(this.getParameter("cc_visa"));
        this.cc_mc = this.getBoolean(this.getParameter("cc_mc"));
        this.cc_amex = this.getBoolean(this.getParameter("cc_amex"));
        this.cc_diners = this.getBoolean(this.getParameter("cc_diners"));
        this.cc_discover = this.getBoolean(this.getParameter("cc_discover"));
        this.cc_jcb = this.getBoolean(this.getParameter("cc_jcb"));
        this.user_options = Long.valueOf(this.getParameter("user_options"));
        final int print_receipt_header_id = this.getInt(this.getParameter("print_receipt_header_id"));
        if (print_receipt_header_id > 0) {
            this.printSavedReceipt(print_receipt_header_id);
            try {
                this.getAppletContext().showDocument(new URL(this.secure_preface + "/showReceipt.sdi?receiptheader_id=" + print_receipt_header_id + "&from_pos=1&topmost_menu_id=" + this.topmost_menu_id + "&menu_id=" + this.topmost_menu_id), "_self");
            }
            catch (Exception e3) {
                System.err.println("Exception in init: " + e3.toString());
            }
            return;
        }
        System.err.println("Create the panels");
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(2, 2, 2, 2);
        this.pos_panel = new POSPanel();
        this.default_customer = new POSReceiptCustomer();
        this.default_customer.customer_id = this.getInt(this.getParameter("cash_customer_id"));
        this.default_customer.name = this.getParameter("cash_customer_name");
        this.default_customer.tax_country = this.getParameter("cash_customer_tax_country");
        this.default_customer.tax_state = this.getParameter("cash_customer_tax_state");
        final String membership_discount_keys = this.getParameter("cash_customer_membership_discounts");
        if (membership_discount_keys != null && membership_discount_keys.length() > 0) {
            final ArrayList<Integer> keys = new ArrayList<Integer>();
            final String[] sa = membership_discount_keys.split(",\\s*");
            for (int i = 0; i < sa.length; ++i) {
                try {
                    keys.add(Integer.parseInt(sa[i]));
                }
                catch (Exception ex) {}
            }
            this.default_customer.membership_discount_keys = keys;
        }
        this.pos_receipt = null;
        final int rno = this.getInt(this.getParameter("rno"));
        if (rno > 0) {
            this.pos_receipt = this.fetchReceipt(rno);
        }
        if (this.pos_receipt == null) {
            this.pos_receipt = new POSReceipt(this.default_customer);
        }
        (this.receipt_area = new JPanel()).setPreferredSize(new Dimension(230, 500));
        final JSplitPane split_pane = new JSplitPane(1, true, this.receipt_area, this.pos_panel);
        split_pane.setDividerSize(2);
        this.getContentPane().add(split_pane);
        final POSKeyboard kb = this.fetchKeyboard(this.pos_group_id);
        this.pos_panel.constructKeyboard(kb);
        int next_row = 0;
        this.receipt_area.setLayout(new GridBagLayout());
        this.receipt_finished_prompt = new PromptPanel(8);
        add(this.receipt_area, this.receipt_finished_prompt, constraints, 0, next_row++, 2, 1, 2, 11, 0, 0);
        this.receipt_finished_prompt.setVisible(false);
        final BigDecimal balance = this.getBalance();
        this.customer_field = new JLabel(this.pos_receipt.receipt_customer.name + ((balance.signum() > 0) ? (" (" + NumberFormat.getCurrencyInstance().format(balance) + ")") : ""), 4);
        add(this.receipt_area, this.customer_field, constraints, 0, next_row++, 2, 1, 2, 18, 200, 0);
        this.refund_label = new JLabel(">> Refund <<", 0);
        add(this.receipt_area, this.refund_label, constraints, 1, next_row++, 1, 1, 2, 18, 100, 0);
        this.refund_label.setVisible(false);
        this.upc_name_label = new JLabel("UPC / name search", 4);
        add(this.receipt_area, this.upc_name_label, constraints, 0, next_row, 1, 1, 2, 12, 0, 0);
        (this.upc_name_field = new JTextField("Scan UPC")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                if (PointOfSale.this.upc_name_field.getText().equals("Scan UPC")) {
                    PointOfSale.this.upc_name_field.setText("");
                }
                PointOfSale.this.pos_panel.product_search_dialog.resetFields();
                try {
                    if (PointOfSale.this.upc_name_field.getText().length() == 0) {
                        PointOfSale.this.pos_panel.product_search_dialog.showDialog();
                    }
                    else {
                        String params = "upc=" + URLEncoder.encode(extractUPCCode(PointOfSale.this.upc_name_field.getText()), "UTF-8");
                        params += "&no_free_merchandise=1";
                        final ArrayList products = PointOfSale.this.searchProducts(params);
                        if (products.isEmpty()) {
                            PointOfSale.this.pos_panel.product_search_dialog.upc_field.setText(extractUPCCode(PointOfSale.this.upc_name_field.getText()));
                            PointOfSale.this.pos_panel.product_search_dialog.error_msg.setText("Entered value did not match UPC or name");
                            PointOfSale.this.pos_panel.product_search_dialog.showDialog();
                        }
                        else if (products.size() == 1) {
                            PointOfSale.this.handleProductButton(products.get(0));
                        }
                        else {
                            PointOfSale.this.pos_panel.product_search_dialog.upc_field.setText(extractUPCCode(PointOfSale.this.upc_name_field.getText()));
                            PointOfSale.this.pos_panel.product_list_dialog.showDialog(products);
                        }
                    }
                }
                catch (Exception e) {
                    PointOfSale.this.pos_panel.product_search_dialog.upc_field.setText(extractUPCCode(PointOfSale.this.upc_name_field.getText()));
                    PointOfSale.this.pos_panel.product_search_dialog.error_msg.setText("Exception: " + e.getMessage());
                    PointOfSale.this.pos_panel.product_search_dialog.showDialog();
                }
            }
        });
        add(this.receipt_area, this.upc_name_field, constraints, 1, next_row++, 1, 1, 2, 18, 100, 0);
        this.receipt_table_model = new ReceiptTableModel();
        this.receipt_table = new JTable(this.receipt_table_model);
        for (int j = 0; j < this.receipt_table.getColumnCount(); ++j) {
            final TableColumn column = this.receipt_table.getColumnModel().getColumn(j);
            column.setPreferredWidth(PointOfSale.table_column_preferred_widths[j]);
        }
        final JScrollPane jsp = new JScrollPane(this.receipt_table, 20, 31);
        add(this.receipt_area, jsp, constraints, 0, next_row++, 2, 1, 1, 11, 100, 100);
        this.cash_prompt = new PromptPanel(0);
        add(this.receipt_area, this.cash_prompt, constraints, 0, next_row++, 2, 1, 2, 11, 0, 0);
        this.cash_prompt.setVisible(false);
        this.other_payment_prompt = new PromptPanel(11);
        add(this.receipt_area, this.other_payment_prompt, constraints, 0, next_row++, 2, 1, 2, 11, 0, 0);
        this.other_payment_prompt.setVisible(false);
        this.from_account_prompt = new PromptPanel(10);
        add(this.receipt_area, this.from_account_prompt, constraints, 0, next_row++, 2, 1, 2, 11, 0, 0);
        this.from_account_prompt.setVisible(false);
        this.charge_prompt = new PromptPanel(1);
        add(this.receipt_area, this.charge_prompt, constraints, 0, next_row++, 2, 1, 2, 11, 0, 0);
        this.charge_prompt.setVisible(false);
        this.cash_refund_prompt = new PromptPanel(9);
        add(this.receipt_area, this.cash_refund_prompt, constraints, 0, next_row++, 2, 1, 2, 11, 0, 0);
        this.cash_refund_prompt.setVisible(false);
        this.memo_prompt = new PromptPanel(3);
        add(this.receipt_area, this.memo_prompt, constraints, 0, next_row++, 2, 1, 2, 11, 0, 0);
        this.memo_prompt.setVisible(false);
        this.check_prompt = new PromptPanel(2);
        add(this.receipt_area, this.check_prompt, constraints, 0, next_row++, 2, 1, 2, 11, 0, 0);
        this.check_prompt.setVisible(false);
        this.price_prompt = new PromptPanel(4);
        add(this.receipt_area, this.price_prompt, constraints, 0, next_row++, 2, 1, 2, 11, 0, 0);
        this.price_prompt.setVisible(false);
        this.fixed_discount_prompt = new PromptPanel(6);
        add(this.receipt_area, this.fixed_discount_prompt, constraints, 0, next_row++, 2, 1, 2, 11, 0, 0);
        this.fixed_discount_prompt.setVisible(false);
        this.percentage_discount_prompt = new PromptPanel(7);
        add(this.receipt_area, this.percentage_discount_prompt, constraints, 0, next_row++, 2, 1, 2, 11, 0, 0);
        this.percentage_discount_prompt.setVisible(false);
        this.quantity_prompt = new PromptPanel(5);
        add(this.receipt_area, this.quantity_prompt, constraints, 0, next_row++, 2, 1, 2, 11, 0, 0);
        this.quantity_prompt.setVisible(false);
        this.subtotal_label = new JLabel("Subtotal", 4);
        add(this.receipt_area, this.subtotal_label, constraints, 0, next_row, 1, 1, 2, 12, 0, 0);
        (this.subtotal_field = new JTextField("")).setHorizontalAlignment(4);
        add(this.receipt_area, this.subtotal_field, constraints, 1, next_row++, 1, 1, 2, 18, 100, 0);
        this.subtotal_label.setVisible(false);
        this.subtotal_field.setVisible(false);
        for (final POSTax.whichTax which_tax : POSTax.values) {
            this.tax_label[which_tax.ordinal()] = new JLabel(this.tax_rates.tax_name[which_tax.ordinal()], 4);
            add(this.receipt_area, this.tax_label[which_tax.ordinal()], constraints, 0, next_row, 1, 1, 2, 12, 0, 0);
            (this.tax_field[which_tax.ordinal()] = new JTextField("")).setHorizontalAlignment(4);
            add(this.receipt_area, this.tax_field[which_tax.ordinal()], constraints, 1, next_row++, 1, 1, 2, 18, 100, 0);
            this.tax_label[which_tax.ordinal()].setVisible(false);
            this.tax_field[which_tax.ordinal()].setVisible(false);
        }
        this.grand_total_label = new JLabel("Charges", 4);
        add(this.receipt_area, this.grand_total_label, constraints, 0, next_row, 1, 1, 2, 12, 0, 0);
        (this.grand_total_field = new JTextField("")).setHorizontalAlignment(4);
        add(this.receipt_area, this.grand_total_field, constraints, 1, next_row++, 1, 1, 2, 18, 100, 0);
        this.grand_total_label.setVisible(false);
        this.grand_total_field.setVisible(false);
        this.payment_label = new JLabel("Payments", 4);
        add(this.receipt_area, this.payment_label, constraints, 0, next_row, 1, 1, 2, 12, 0, 0);
        (this.payment_field = new JTextField("")).setHorizontalAlignment(4);
        add(this.receipt_area, this.payment_field, constraints, 1, next_row++, 1, 1, 2, 18, 100, 0);
        this.payment_label.setVisible(false);
        this.payment_field.setVisible(false);
        this.change_label = new JLabel("Change", 4);
        add(this.receipt_area, this.change_label, constraints, 0, next_row, 1, 1, 2, 12, 0, 0);
        (this.change_field = new JTextField("")).setHorizontalAlignment(4);
        add(this.receipt_area, this.change_field, constraints, 1, next_row++, 1, 1, 2, 18, 100, 0);
        this.change_label.setVisible(false);
        this.change_field.setVisible(false);
        final JButton return_to_menu_button = new JButton("Return to Menu");
        return_to_menu_button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                PointOfSale.this.tenderReceipt(PointOfSale.this.pos_receipt, (PointOfSale.this.pos_receipt.rno > 0) ? 1 : 3);
                try {
                    final String function = URLEncoder.encode("showPendingReceipt.sdi?rno=" + PointOfSale.this.pos_receipt.rno, "UTF-8");
                    if (PointOfSale.this.tabbed_admin_ui) {
                        PointOfSale.this.getAppletContext().showDocument(new URL(PointOfSale.this.secure_preface + "/updateTabContext.sdi?initial_function=" + function + "&topmost_menu_id=tab_frontdesk&menu_id=tab_frontdesk&load_menus=true"), "_self");
                    }
                    else {
                        PointOfSale.this.getAppletContext().showDocument(new URL(PointOfSale.this.secure_preface + "/adminMain.sdi?topmost_menu_id=tab_frontdesk&menu_id=tab_frontdesk&initial_function=" + function), "_top");
                    }
                }
                catch (Exception ex) {}
            }
        });
        add(this.receipt_area, return_to_menu_button, constraints, 0, next_row++, 2, 1, 0, 10, 0, 0);
        this.refreshMembershipDiscounts();
        this.showTotals();
        this.printDefinedReport(this.getParameter("daily_close_output"));
        final String pass_scan_output = this.getParameter("pass_scan_output");
        this.printDefinedReport(pass_scan_output);
        this.upc_name_field.selectAll();
        this.upc_name_field.requestFocus();
        this.areWeLoggedIn();
        System.err.println("Init Completed");
        if (pass_scan_output != null && pass_scan_output.length() > 0) {
            try {
                final String function = URLEncoder.encode("SelectPass.sdi?class_name=Pass&topmost_menu_id=" + this.topmost_menu_id + "&menu_id=" + this.topmost_menu_id, "UTF-8");
                if (this.tabbed_admin_ui) {
                    this.getAppletContext().showDocument(new URL(this.secure_preface + "/updateTabContext.sdi?initial_function=" + function + "&topmost_menu_id=" + this.topmost_menu_id + "&menu_id=" + this.topmost_menu_id), "_self");
                }
                else {
                    this.getAppletContext().showDocument(new URL(this.secure_preface + "/adminMain.sdi?topmost_menu_id=" + this.topmost_menu_id + "&menu_id=" + this.topmost_menu_id + "&initial_function=" + function), "_top");
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    public void destroy() {
        if (PointOfSale.pole_display_printer != null) {
            if (this.enable_pole_display) {
                this.displayPoleDisplayMessage(this.message_for_pole_display, true, false, null, false, true);
            }
            PointOfSale.pole_display_printer.close();
            PointOfSale.pole_display_printer = null;
        }
    }
    
    private void goBack() {
        try {
            final String function = URLEncoder.encode("adminempty.sdi?topmost_menu_id=" + this.topmost_menu_id + "&menu_id=" + this.topmost_menu_id, "UTF-8");
            if (this.tabbed_admin_ui) {
                this.getAppletContext().showDocument(new URL(this.secure_preface + "/updateTabContext.sdi?initial_function=" + function + "&topmost_menu_id=" + this.topmost_menu_id + "&menu_id=" + this.topmost_menu_id), "_self");
            }
            else {
                this.getAppletContext().showDocument(new URL(this.secure_preface + "/adminFrames.sdi?initial_function=" + function), "_self");
            }
        }
        catch (Exception ex) {}
    }
    
    private static String extractUPCCode(final String original_code) {
        String upc_code = original_code;
        if (original_code.length() > 2 && original_code.indexOf("^-") >= 0) {
            final String new_s = original_code.substring(original_code.indexOf("^-"));
            final int end_index = new_s.indexOf("=");
            if (end_index > 0) {
                upc_code = new_s.substring(2, end_index);
            }
        }
        return upc_code;
    }
    
    static void add(final Container p, final Component component, final GridBagConstraints constraints, final int x, final int y, final int cx, final int cy, final int fill, final int anchor, final int weightx, final int weighty) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = cx;
        constraints.gridheight = cy;
        constraints.fill = fill;
        constraints.anchor = anchor;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        p.add(component, constraints);
    }
    
    void logMembershipUsage(final int pos_product_id) {
        try {
            final URL source_url = new URL(this.secure_preface + "/logPOSProductMembershipUsage.sdi?posproduct_id=" + pos_product_id);
            final HttpURLConnection connection = (HttpURLConnection)source_url.openConnection();
            connection.connect();
            final int response_code = connection.getResponseCode();
        }
        catch (Exception e) {
            System.err.println("Exception in logMembershipUsage: " + e.toString());
        }
    }
    
    synchronized void saveKeyboardRevision(final int revision_number) {
        if (PointOfSale.keyboard_revision != revision_number) {
            PointOfSale.keyboards.setSize(0);
        }
        PointOfSale.keyboard_revision = revision_number;
    }
    
    synchronized POSKeyboard fetchKeyboard(final int pos_group_id) {
        for (final POSKeyboard kb : PointOfSale.keyboards) {
            if (kb.pos_group_id == pos_group_id) {
                return kb;
            }
        }
        final POSKeyboard kb2 = (POSKeyboard)this.fetchObject("/fetchKeyboard.sdi?posgroup_id=" + pos_group_id);
        if (kb2 != null) {
            PointOfSale.keyboards.add(kb2);
            System.err.println("Fetched keyboard");
        }
        return kb2;
    }
    
    void areWeLoggedIn() {
        if (this.last_logon_check == 0L) {
            this.last_logon_check = System.currentTimeMillis();
            return;
        }
        if (System.currentTimeMillis() - this.last_logon_check < 900000L) {
            return;
        }
        final Object o = this.fetchObject("/areWeLoggedIn.sdi");
        if (o instanceof Boolean) {
            if (o) {
                this.last_logon_check = System.currentTimeMillis();
                return;
            }
        }
        try {
            this.getAppletContext().showDocument(new URL(this.secure_preface + "/adminLogin.sdi"), "_self");
        }
        catch (Exception e) {}
    }
    
    POSTaxRates getTaxRates(final int site_id) {
        final Object o = this.fetchObject("/getPOSTaxRates.sdi?site_id=" + site_id);
        return (o instanceof POSTaxRates) ? ((POSTaxRates)o) : null;
    }
    
    ArrayList searchProducts(final String params) {
        final Object o = this.fetchObject("/searchPOSProducts.sdi?" + params);
        return (o instanceof ArrayList) ? ((ArrayList)o) : null;
    }
    
    ArrayList fetchDepartments() {
        final Object o = this.fetchObject("/getPOSProductDepartments.sdi");
        return (o instanceof ArrayList) ? ((ArrayList)o) : null;
    }
    
    ArrayList fetchClasses() {
        final Object o = this.fetchObject("/getPOSProductClasses.sdi");
        return (o instanceof ArrayList) ? ((ArrayList)o) : null;
    }
    
    ArrayList fetchSubclasses() {
        final Object o = this.fetchObject("/getPOSProductSubclasses.sdi");
        return (o instanceof ArrayList) ? ((ArrayList)o) : null;
    }
    
    ArrayList fetchSites() {
        final Object o = this.fetchObject("/getPOSSites.sdi");
        return (o instanceof ArrayList) ? ((ArrayList)o) : null;
    }
    
    ArrayList fetchCustomerScanTypes() {
        final Object o = this.fetchObject("/getPOSCustomerScanTypes.sdi");
        return (o instanceof ArrayList) ? ((ArrayList)o) : null;
    }
    
    POSReceiptCustomer getCustomer(final String params) {
        final Object o = this.fetchObject("/getPOSCustomer.sdi?" + params + "&rno=" + this.pos_receipt.rno);
        return (o instanceof POSReceiptCustomer) ? ((POSReceiptCustomer)o) : null;
    }
    
    POSReceipt fetchReceipt(final int rno) {
        final Object o = this.fetchObject("/fetchPOSReceipt.sdi?rno=" + rno);
        return (o instanceof POSReceipt) ? ((POSReceipt)o) : null;
    }
    
    ArrayList<POSOtherPaymentType> fetchOtherPaymentTypes() {
        final Object o = this.fetchObject("/getPOSOtherPayments.sdi");
        return (ArrayList<POSOtherPaymentType>)((o instanceof ArrayList) ? ((ArrayList)o) : null);
    }
    
    Object fetchObject(final String url) {
        Object obj = null;
        try {
            final URL source_url = new URL(this.secure_preface + url);
            final URLConnection connection = source_url.openConnection();
            final InputStream is = connection.getInputStream();
            final ObjectInputStream ois = new ObjectInputStream(is);
            obj = ois.readObject();
            ois.close();
        }
        catch (InvalidClassException e) {
            this.showException(e.getMessage(), "This usually occurs when the server is running a version of the software which is not compatible with what you are running. Try exiting your browser and selecting POS sell again. If that doesn't work, please contact your system administrator.");
            System.err.println("Classes are invalid");
            return null;
        }
        catch (StreamCorruptedException e2) {
            this.showException(e2.getMessage(), "This usually occurs when the server is running a version of the software which is not compatible with what you are running. Try exiting your browser and selecting POS sell again. If that doesn't work, please contact your system administrator.");
            System.err.println("Stream corrupted");
            return null;
        }
        catch (MalformedURLException e3) {
            this.showException(e3.getMessage());
            System.err.println("MalformedURLException in fetchObject: " + e3.getMessage());
            this.goBack();
            return null;
        }
        catch (IOException e4) {
            this.showException(e4.getMessage());
            System.err.println("IOException in fetchObject: " + e4.getMessage());
            this.goBack();
            return null;
        }
        catch (ClassNotFoundException e5) {
            this.showException(e5.getMessage());
            System.err.println("ClassNotFoundException in fetchObject: " + e5.getMessage());
            this.goBack();
            return null;
        }
        catch (Exception e6) {
            this.showException(e6.getMessage());
            System.err.println("Exception in fetchObject: " + e6.getMessage());
            this.goBack();
            return null;
        }
        return obj;
    }
    
    private void showException(final String msg) {
        this.showException(msg, null);
    }
    
    private void showException(String msg, final String details) {
        if (msg == null) {
            msg = "Exception returned no error message";
        }
        if (details == null) {
            JOptionPane.showMessageDialog(this.pos_panel, "<html>" + wrappedHTMLString(msg) + "</html>", "Exception", 0);
        }
        else {
            JOptionPane.showMessageDialog(this.pos_panel, "<html>" + wrappedHTMLString(msg) + "<br><br>" + wrappedHTMLString(details) + "</html>", "Exception", 0);
        }
    }
    
    private static String wrappedHTMLString(final String s) {
        if (s == null) {
            return "";
        }
        final String[] splits = s.split("\\s");
        final StringBuilder sb = new StringBuilder();
        int length = 0;
        final int line_width = 80;
        for (int i = 0; i < splits.length; ++i) {
            if (sb.length() == 0) {
                sb.append(splits[i]);
                length = splits[i].length();
            }
            else if (length + 1 + splits[i].length() > line_width) {
                sb.append("<br>");
                sb.append(splits[i]);
                length = splits[i].length();
            }
            else {
                sb.append(" ").append(splits[i]);
                length += splits[i].length() + 1;
            }
        }
        return sb.toString();
    }
    
    private boolean tenderReceipt(POSReceipt receipt, final int tender_type) {
        if (receipt == null) {
            return true;
        }
        if ((tender_type == 1 || (tender_type == 3 && receipt.rno > 0)) && !receipt.dirty) {
            return true;
        }
        if (tender_type == 0) {
            StringBuilder disclaimers = new StringBuilder();
            ArrayList<Integer> POS_disclaimer_ids = new ArrayList<Integer>();
            final ArrayList<Integer> Linked_POS_ids = new ArrayList<Integer>();
            for (int i = 0; i < receipt.receipt_items.size(); ++i) {
                if (receipt.receipt_items.get(i).button != null) {
                    if (receipt.receipt_items.get(i).button.button_type == 2 || Linked_POS_ids.contains(receipt.receipt_items.get(i).button.button_value)) {
                        int disclaimer_id = -1;
                        String str_disclaimer = "";
                        String disclaimer_text = "";
                        str_disclaimer = (String)this.fetchObject("/getDisclaimerThroughProductID.sdi?product_ID=" + receipt.receipt_items.get(i).button.button_value);
                        if (str_disclaimer.indexOf(",") > 0) {
                            disclaimer_id = new Integer(str_disclaimer.substring(0, str_disclaimer.indexOf(",")));
                            disclaimer_text = str_disclaimer.substring(str_disclaimer.indexOf(",") + 1);
                        }
                        if (disclaimer_id > 0 && !POS_disclaimer_ids.contains(disclaimer_id)) {
                            disclaimers = this.addDisclaimers(disclaimer_text, disclaimers, POS_disclaimer_ids);
                        }
                        POS_disclaimer_ids = this.addDisclaimerID(disclaimer_id, POS_disclaimer_ids);
                        if (disclaimer_id > 0) {
                            this.markAsterisks(receipt.receipt_items.get(i), disclaimer_id, POS_disclaimer_ids);
                        }
                        if (receipt.receipt_items.get(i).button.linking_products != null && receipt.receipt_items.get(i).button.linking_products.size() > 0) {
                            for (final POSKeyboardButton button : receipt.receipt_items.get(i).button.linking_products) {
                                Linked_POS_ids.add(button.button_value);
                            }
                        }
                    }
                }
            }
            receipt.POSProducts_disclaimers = disclaimers.toString();
        }
        final Cursor previous_cursor = this.getCursor();
        this.setCursor(Cursor.getPredefinedCursor(3));
        try {
            this.last_change_due = receipt.reducePayments();
            ByteArrayOutputStream output = null;
            try {
                output = new ByteArrayOutputStream();
                final ObjectOutputStream oos = new ObjectOutputStream(output);
                oos.writeObject(receipt);
                output.close();
            }
            catch (Exception e) {
                this.showException(e.toString());
                System.err.println("Exception in PointOfSale.tenderReceipt: " + e.toString());
                e.printStackTrace();
                return false;
            }
            final byte[] data_bytes = output.toByteArray();
            final String boundary = this.getBoundary(data_bytes);
            try {
                final URL url = new URL(this.secure_preface + "/tenderPOSReceipt.sdi");
                final HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
                conn.setUseCaches(false);
                conn.setDefaultUseCaches(false);
                final DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                final PrintStream ps = new PrintStream(dos);
                ps.print("--" + boundary + "--\r\n");
                ps.print("Content-Disposition: form-data; name=\"tendertype\"\r\n");
                ps.print("\r\n");
                ps.print(Integer.toString(tender_type) + "\r\n");
                ps.print("--" + boundary + "\r\n");
                ps.print("Content-Disposition: form-data; name=\"posreceipt\"\r\n");
                ps.print("Content-Type: x-java-serialized-object\r\n");
                ps.print("\r\n");
                ps.write(data_bytes);
                ps.print("\r\n");
                ps.print("--" + boundary + "--\r\n");
                ps.flush();
                ps.close();
                String error_msg = null;
                Object new_receipt = null;
                if (tender_type != 1) {
                    InputStream is = null;
                    try {
                        is = conn.getInputStream();
                        final ObjectInputStream ois = new ObjectInputStream(is);
                        new_receipt = ois.readObject();
                        is.close();
                    }
                    catch (StreamCorruptedException e4) {
                        this.goBack();
                        return false;
                    }
                    catch (Exception e2) {
                        final int response_code = conn.getResponseCode();
                        if (response_code != 402) {
                            error_msg = e2.toString();
                            System.err.println("Failed to get new receipt: " + error_msg);
                        }
                    }
                    finally {
                        if (is != null) {
                            is.close();
                        }
                    }
                }
                if (new_receipt != null && !(new_receipt instanceof POSReceipt)) {
                    this.goBack();
                    return false;
                }
                final int response_code2 = conn.getResponseCode();
                if (error_msg != null || (response_code2 != 200 && response_code2 != 202)) {
                    if (error_msg == null) {
                        error_msg = conn.getResponseMessage();
                    }
                    this.showException("Cannot tender receipt. Server returned error: " + error_msg);
                    return false;
                }
                receipt.dirty = false;
                switch (tender_type) {
                    case 0: {
                        receipt = (POSReceipt)new_receipt;
                        this.receipt_number = receipt.receipt_number;
                        this.receipt_header_id = receipt.receipt_header_id;
                        if (receipt.print_pos_receipt && receipt.user_confirm_print_pos_receipt && JOptionPane.showConfirmDialog(this.pos_panel, "Do you want to print this receipt?", "Print POS Receipt", 0, 3) != 0) {
                            receipt.print_pos_receipt = false;
                        }
                        if (receipt.print_pos_receipt) {
                            this.printReceipt(receipt);
                        }
                        else if (receipt.open_cash_drawer) {
                            this.openCashDrawer();
                        }
                        if (receipt.print_win_receipt) {
                            final String function = URLEncoder.encode("showReceipt.sdi?receiptheader_id=" + this.receipt_header_id + "&from_pos=1&topmost_menu_id=" + this.topmost_menu_id + "&menu_id=" + this.topmost_menu_id, "UTF-8");
                            if (this.tabbed_admin_ui) {
                                this.getAppletContext().showDocument(new URL(this.secure_preface + "/updateTabContext.sdi?initial_function=" + function + "&topmost_menu_id=" + this.topmost_menu_id + "&menu_id=" + this.topmost_menu_id), "_self");
                            }
                            else {
                                this.getAppletContext().showDocument(new URL(this.secure_preface + "/adminFrames.sdi?initial_function=" + function), "_self");
                            }
                        }
                        return true;
                    }
                    case 2: {
                        this.pos_receipt = (POSReceipt)new_receipt;
                        this.receipt_table.revalidate();
                        this.receipt_table.repaint();
                        this.resetCustomerField(this.getBalance());
                        this.showTotals();
                        return true;
                    }
                    case 3: {
                        this.pos_receipt = (POSReceipt)new_receipt;
                        this.showTotals();
                        return true;
                    }
                    default: {
                        return false;
                    }
                }
            }
            catch (Exception e3) {
                System.err.println("MalformedURLException in tenderReceipt: " + e3.getMessage());
                e3.printStackTrace();
                this.showException(e3.getMessage());
                return false;
            }
        }
        finally {
            this.setCursor(previous_cursor);
        }
    }
    
    public void openCashDrawer() {
        if (this.mac_os_x) {
            return;
        }
        if (!this.pass_cash_drawer_profile_check && (this.user_options & 0x10L) != 0x0L) {
            final JTextField tf_user_name = new JTextField(10);
            final JPasswordField tf_password = new JPasswordField(10);
            if (!this.getValidateUser(tf_user_name, tf_password)) {
                return;
            }
            final Object o = this.fetchObject("/validateUserProfile.sdi?profile_id=438&uname=" + tf_user_name.getText() + "&psw=" + new String(tf_password.getPassword()));
            if (o != null && o instanceof String) {
                this.showException((String)o, "Cannot open cash drawer.");
                return;
            }
            this.pass_cash_drawer_profile_check = true;
        }
        if (this.com_port == 0) {
            this.showException("Missing POS COM port in workstation record.", "Cannot open cash drawer.");
            return;
        }
        String warn = "";
        if (this.pop_drawer_url != null && this.pop_drawer_url.indexOf("Pass") > 0) {
            warn = "door";
        }
        else {
            warn = "cash drawer";
        }
        final POSPrinter printer = new POSPrinter(this.com_port);
        if (printer.out == null) {
            this.showException("Cannot open COM port " + this.com_port + ".", "Not able to open " + warn);
            return;
        }
        printer.openCashDrawer();
        printer.close();
    }
    
    public void initPoleDisplayPrinter() {
        if (PointOfSale.pole_display_printer != null) {
            return;
        }
        if (this.mac_os_x) {
            return;
        }
        if (this.pole_display_com_port == 0) {
            this.showException("Missing Pole display's comm port in workstation record.", "Pole display will not be display.");
            return;
        }
        PointOfSale.pole_display_printer = new POSPrinter(this.pole_display_com_port);
        if (PointOfSale.pole_display_printer.out == null) {
            PointOfSale.pole_display_printer = null;
            this.showException("Cannot open port COM" + this.pole_display_com_port + ".", "Not able to display pole message.");
        }
    }
    
    public void displayPoleDisplayMessage(final String first_line_message, final boolean first_line_scroll, final boolean first_line_display_clock, final String second_line_message, final boolean second_line_scroll, final boolean second_line_display_clock) {
        if (PointOfSale.pole_display_printer == null) {
            return;
        }
        if (this.clear_display != null && this.clear_display.length() > 0) {
            PointOfSale.pole_display_printer.printLine(this.clear_display);
        }
        else {
            PointOfSale.pole_display_printer.printLine(this.cursor_first_line);
            PointOfSale.pole_display_printer.printLine(this.cursor_second_line);
        }
        if ((first_line_message != null && first_line_message.length() > 0) || first_line_display_clock) {
            final StringBuilder commands_and_messages = new StringBuilder();
            if (first_line_scroll) {
                commands_and_messages.append(this.scroll_text_first_line).append(first_line_message);
            }
            else if (first_line_display_clock) {
                final Calendar calendar = Calendar.getInstance();
                commands_and_messages.append(this.display_clock).append((calendar.get(10) == 0) ? "12" : formatToTwoDigits(calendar.get(10))).append(this.character_between_hours_and_minutes).append(formatToTwoDigits(calendar.get(12)));
            }
            else {
                commands_and_messages.append(this.cursor_first_line).append(first_line_message);
            }
            PointOfSale.pole_display_printer.printLine(commands_and_messages.toString());
        }
        if ((second_line_message != null && second_line_message.length() > 0) || second_line_display_clock) {
            final StringBuilder commands_and_messages = new StringBuilder();
            if (second_line_scroll) {
                commands_and_messages.append(this.scroll_text_second_line).append(second_line_message);
            }
            else if (second_line_display_clock) {
                final Calendar calendar = Calendar.getInstance();
                commands_and_messages.append(this.display_clock).append((calendar.get(10) == 0) ? "12" : formatToTwoDigits(calendar.get(10))).append(this.character_between_hours_and_minutes).append(formatToTwoDigits(calendar.get(12)));
            }
            else {
                commands_and_messages.append(this.cursor_second_line).append(second_line_message);
            }
            PointOfSale.pole_display_printer.printLine(commands_and_messages.toString());
        }
    }
    
    public static String formatToTwoDigits(final int time) {
        final StringBuilder time_string = new StringBuilder();
        if (String.valueOf(time).length() == 1) {
            time_string.append("0").append(time);
        }
        else {
            time_string.append(time);
        }
        return time_string.toString();
    }
    
    private boolean getValidateUser(final JTextField tf_user_name, final JPasswordField tf_password) {
        tf_user_name.setText("Username");
        tf_password.setText("Password");
        final int r = JOptionPane.showConfirmDialog(this.pos_panel, new Object[] { new JLabel("Needs authority to continue", 2), tf_user_name, tf_password }, "Login Window", 2, 3);
        return r == 0;
    }
    
    public void printSavedReceipt(final int receipt_header_id) {
        final Object receipt = this.fetchObject("/getPOSReceipt.sdi?receiptheader_id=" + receipt_header_id);
        if (receipt != null && receipt instanceof POSReceipt) {
            final POSReceipt r = (POSReceipt)receipt;
            this.receipt_number = r.receipt_number;
            this.printReceipt((POSReceipt)receipt);
        }
    }
    
    public void printDefinedReport(String defined_content) {
        if (defined_content == null || defined_content.length() == 0) {
            return;
        }
        if (this.mac_os_x) {
            return;
        }
        if (this.com_port == 0) {
            this.showException("Missing comm port in workstation record.", "POS Report will not be printed.");
            return;
        }
        final POSPrinter printer = new POSPrinter(this.com_port);
        if (printer.out == null) {
            this.showException("Cannot open port COM" + this.com_port + ".", "Report will not be printed");
            System.err.println("Cannot open printer at port COM" + this.com_port);
            return;
        }
        try {
            defined_content = URLDecoder.decode(defined_content, "UTF-8");
            final BufferedReader br = new BufferedReader(new StringReader(defined_content));
            try {
                String line = null;
                while ((line = br.readLine()) != null) {
                    if (line.contains("&QUOT;")) {
                        line = line.replace("&QUOT;", "'");
                    }
                    if (line.length() < 1) {
                        printer.blankLines(1);
                    }
                    else {
                        printer.printLine(line);
                    }
                }
            }
            catch (Exception e) {
                System.err.println("Exception in printDailyClose: " + e.toString());
                try {
                    br.close();
                }
                catch (Exception x) {
                    System.err.println("Exception in printDailyClose(BufferedReader): " + x.toString());
                }
            }
            finally {
                try {
                    br.close();
                }
                catch (Exception x2) {
                    System.err.println("Exception in printDailyClose(BufferedReader): " + x2.toString());
                }
            }
            printer.blankLines(this.eject_lines);
            printer.autoCut();
        }
        catch (Exception e2) {
            if (e2 instanceof InvocationTargetException) {
                System.err.println(((InvocationTargetException)e2).getTargetException().toString());
            }
            else {
                System.err.println(e2.toString());
            }
        }
        finally {
            printer.close();
        }
    }
    
    public void printReceipt(final POSReceipt receipt) {
        if (this.mac_os_x) {
            return;
        }
        if (this.com_port == 0) {
            this.showException("Missing POS printer COM port in workstation record.", "POS Receipts will not be printed.");
            return;
        }
        final POSPrinter printer = new POSPrinter(this.com_port);
        if (printer.out == null) {
            this.showException("Cannot open COM port " + this.com_port + ".", "POS Receipts will not be printed");
            System.err.println("Cannot open printer at COM port " + this.com_port);
            return;
        }
        try {
            if (receipt.open_cash_drawer) {
                printer.openCashDrawer();
            }
            System.err.println("printReceipt () #" + this.receipt_number);
            final Object o = this.fetchObject("/isReceiptPrinted.sdi?receiptheader_id=" + receipt.receipt_header_id + "&from_pos=1");
            boolean receipt_printed = false;
            if (o instanceof Boolean) {
                receipt_printed = (boolean)o;
            }
            final POSFeeDetail detail = receipt.charges();
            printer.changeFont(2);
            printer.printCenter(receipt.caption);
            printer.blankLines(1);
            printer.changeFont(0);
            printer.printLine(receipt.caption + " #" + this.receipt_number);
            printer.printLine(DateFormat.getDateTimeInstance(3, 3).format(new Date()) + " by " + this.user_name);
            if (receipt_printed) {
                printer.printLine("(Duplicate POS Receipt)");
            }
            printer.printLine(this.site_name);
            printer.printLine(this.site_address1);
            printer.printLine(this.site_address2);
            if (this.site_city.length() > 0) {
                printer.printLine(this.site_city + ", " + this.site_state + " " + this.site_zip);
            }
            printer.printLine(this.site_phone);
            printer.printDashedLine();
            final NumberFormat nf = NumberFormat.getCurrencyInstance();
            for (int i = 0; i < receipt.receipt_items.size(); ++i) {
                final POSReceiptItem ri = receipt.receipt_items.get(i);
                if (!ri.isPayment()) {
                    printer.printLeftAndRight(ri.description, nf.format(ri.fee_summary.fee_portion));
                    if (ri.isServerEntry()) {
                        printer.print(ri.server_description);
                    }
                    if (!receipt.short_description) {
                        printer.printNotes(2, ri.customer_notes);
                        printer.printNotes(2, ri.receipt_notes);
                    }
                    if (!ri.isServerEntry() && ri.customer != null) {
                        printer.printIndentedLine(2, ri.customer.name);
                    }
                }
            }
            printer.blankLines(1);
            boolean are_taxes = false;
            for (final POSTax.whichTax which_tax : POSTax.values) {
                if (detail.tax[which_tax.ordinal()].signum() != 0) {
                    are_taxes = true;
                    break;
                }
            }
            if (are_taxes) {
                printer.printLeftAndRight("Purchases", nf.format(detail.fee_portion));
                for (final POSTax.whichTax which_tax : POSTax.values) {
                    if (detail.tax[which_tax.ordinal()].signum() != 0) {
                        printer.printLeftAndRight(this.tax_rates.tax_name[which_tax.ordinal()], nf.format(detail.tax[which_tax.ordinal()]));
                    }
                }
            }
            printer.printLeftAndRight("Total Charges", nf.format(detail.total()));
            printer.printDashedLine();
            boolean printed_payments = false;
            POSReceiptCustomer customer = null;
            BigDecimal payments = BigDecimal.valueOf(0L);
            for (int j = 0; j < receipt.receipt_items.size(); ++j) {
                final POSReceiptItem ri2 = receipt.receipt_items.get(j);
                if (ri2.isPayment()) {
                    boolean show_payer = false;
                    if (customer == null) {
                        show_payer = (ri2.customer != null);
                    }
                    else {
                        show_payer = (ri2.customer != null || customer.customer_id != ri2.customer.customer_id);
                    }
                    if (show_payer) {
                        printer.changeFont(1);
                        printer.printLine("Payer");
                        printer.changeFont(0);
                        customer = ri2.customer;
                        if (customer == null) {
                            printer.printLine("Drop-In Customer");
                        }
                        else {
                            printer.printLine(customer.name);
                            printer.printLine(customer.address1);
                            printer.printLine(customer.address2);
                            printer.printLine(customer.city + ", " + customer.state + " " + customer.zip);
                        }
                    }
                    printer.printLeftAndRight(ri2.description, nf.format(ri2.payment_amount));
                    if (ri2.isCashPayment()) {
                        payments = payments.add(ri2.payment_amount);
                    }
                    printed_payments = true;
                }
            }
            if (printed_payments) {
                printer.printDashedLine();
            }
            final BigDecimal amount_due = detail.total().subtract(payments);
            if (amount_due.signum() > 0) {
                printer.printLeftAndRight("Payments", nf.format(payments));
                printer.printLeftAndRight("Amount Due", nf.format(amount_due));
                printer.printDashedLine();
            }
            for (int k = 0; k < receipt.receipt_items.size(); ++k) {
                final POSReceiptItem ri3 = receipt.receipt_items.get(k);
                if (ri3.isCCPayment()) {
                    printer.printSignatureLine();
                    printer.printLine(ri3.id_number);
                    final String cc = ri3.card_number;
                    if (cc != null) {
                        printer.printLine((cc.length() <= 4) ? cc : cc.substring(cc.length() - 4, cc.length()));
                    }
                }
            }
            if (receipt.POSProducts_disclaimers != null && receipt.POSProducts_disclaimers.length() > 0) {
                printer.printNotes(0, receipt.POSProducts_disclaimers);
                printer.printDashedLine();
            }
            printer.printNotes(0, receipt.receipt_text);
            printer.blankLines(this.eject_lines);
            printer.autoCut();
            if (!receipt_printed) {
                this.fetchObject("/setReceiptPrinted.sdi?receiptheader_id=" + receipt.receipt_header_id + "&from_pos=1");
            }
        }
        finally {
            printer.close();
        }
    }
    
    private String getBoundary(final byte[] b) {
        String boundary;
        boolean found;
        do {
            boundary = "_=_NextPart_001_" + Math.random();
            final byte[] boundary_bytes = boundary.getBytes();
            found = false;
            for (int count = Math.max(Math.min(b.length, b.length - boundary_bytes.length), 0), i = 0; i < count; ++i) {
                if (b[i] == boundary_bytes[0]) {
                    boolean matched = true;
                    for (int j = 1; j < boundary_bytes.length; ++j) {
                        if (b[i + j] != boundary_bytes[j]) {
                            matched = false;
                            break;
                        }
                    }
                    if (matched) {
                        found = true;
                        break;
                    }
                }
            }
        } while (found);
        return boundary;
    }
    
    private boolean installFile(final String source_file_name, final File dest_file) {
        try {
            final URL source_url = new URL(this.code_base, source_file_name);
            System.err.println("Installing file " + source_url.toString() + " to " + dest_file.getCanonicalPath());
            dest_file.getParentFile().mkdirs();
            final URLConnection connection = source_url.openConnection();
            final InputStream is = connection.getInputStream();
            final FileOutputStream fos = new FileOutputStream(dest_file);
            final byte[] buff = new byte[8192];
            final BufferedInputStream in = new BufferedInputStream(is, buff.length);
            final BufferedOutputStream out = new BufferedOutputStream(fos, buff.length);
            int count = 0;
            int i;
            while ((i = in.read(buff, 0, buff.length)) != -1) {
                out.write(buff, 0, i);
                count += i;
            }
            this.showStatus(Integer.toString(count) + " bytes copied ...");
            in.close();
            out.close();
            return true;
        }
        catch (Exception e) {
            try {
                this.showException("Exception while installing file " + dest_file.getCanonicalPath() + ": " + e.toString());
            }
            catch (Exception x) {
                this.showException("Exception while installing a file: " + e.toString());
            }
            System.err.println("Exception in installFile: " + e.toString());
            e.printStackTrace();
            return false;
        }
    }
    
    BigDecimal getCreditAvailable() {
        if (this.pos_receipt.receipt_customer.customer_id == this.default_customer.customer_id) {
            return BigDecimal.ZERO;
        }
        BigDecimal credit_available = this.pos_receipt.receipt_customer.credit_available;
        if (credit_available != null) {
            return credit_available;
        }
        final Object o = this.fetchObject("/getCreditAvailable.sdi?customer_id=" + this.pos_receipt.receipt_customer.customer_id);
        if (o instanceof BigDecimal) {
            credit_available = (BigDecimal)o;
            this.pos_receipt.receipt_customer.credit_available = credit_available;
        }
        return (credit_available != null) ? credit_available : BigDecimal.ZERO;
    }
    
    BigDecimal getBalance() {
        final BigDecimal total_credit = this.getCreditAvailable();
        return total_credit.subtract(this.creditUsed());
    }
    
    void resetCustomerField(final BigDecimal credit_available) {
        if (this.customer_field == null) {
            return;
        }
        this.customer_field.setText(this.pos_receipt.receipt_customer.name + ((credit_available.signum() > 0) ? (" (" + NumberFormat.getCurrencyInstance().format(credit_available) + ")") : ""));
        if (this.membership_discount_customer_id != this.pos_receipt.receipt_customer.customer_id) {
            this.refreshMembershipDiscounts();
        }
    }
    
    BigDecimal creditUsed() {
        if (this.pos_receipt == null || this.pos_receipt.receipt_items == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal amount = BigDecimal.ZERO;
        for (final POSReceiptItem item : this.pos_receipt.receipt_items) {
            if (item.isFromAccountPayment()) {
                amount = amount.add(item.payment_amount);
            }
        }
        return amount;
    }
    
    private ArrayList<Integer> addDisclaimerID(final int disclaimer_id, final ArrayList<Integer> POS_disclaimer_ids) {
        if (disclaimer_id > 0 && !POS_disclaimer_ids.contains(disclaimer_id)) {
            POS_disclaimer_ids.add(disclaimer_id);
        }
        return POS_disclaimer_ids;
    }
    
    private StringBuilder addDisclaimers(final String disclaimer_text, final StringBuilder disclaimers, final ArrayList POS_disclaimer_ids) {
        if (disclaimers.length() != 0) {
            disclaimers.append("<br>");
        }
        for (int j = 0; j <= POS_disclaimer_ids.size(); ++j) {
            disclaimers.append("*");
        }
        disclaimers.append(disclaimer_text);
        return disclaimers;
    }
    
    private void markAsterisks(final POSReceiptItem item, final int disclaimer_id, final ArrayList POS_disclaimer_ids) {
        for (int disclaimer_index = POS_disclaimer_ids.indexOf(disclaimer_id), i = 0; i <= disclaimer_index; ++i) {
            item.description += "*";
        }
    }
    
    private void deleteRelatedItems(final POSReceiptItem deleted_item) {
        for (int i = this.pos_receipt.receipt_items.size() - 1; i >= 0; --i) {
            final POSReceiptItem sub_item = this.pos_receipt.receipt_items.get(i);
            for (final POSKeyboardButton kb : deleted_item.button.discount_products) {
                if (kb.pos_membership_discount_key <= 0) {
                    continue;
                }
                if (sub_item.button.pos_membership_discount_key != kb.pos_membership_discount_key || sub_item.parent_uid != deleted_item.uid) {
                    continue;
                }
                this.pos_receipt.deleteEntry(this.tax_rates, i);
                this.receipt_table_model.rowDeleted(i);
                if (sub_item.button.discount_products != null && sub_item.button.discount_products.size() > 0) {
                    this.deleteRelatedItems(sub_item);
                    break;
                }
                break;
            }
        }
    }
    
    static {
        titles = new String[] { "Cash Payment", "Credit Card Payment", "Check Payment", "Credit Memo Payment", "Enter Price", "Enter Quantity", "Enter Discount", "Enter Percentage", "Completed Receipt", "Cash Refund", "Credit Available", "Other Payment Type" };
        table_column_preferred_widths = new int[] { 30, 140, 60 };
        PointOfSale.keyboards = new Vector<POSKeyboard>();
        PointOfSale.keyboard_revision = 0;
        PointOfSale.departments = null;
        PointOfSale.classes = null;
        PointOfSale.subclasses = null;
        PointOfSale.sites = null;
        PointOfSale.avail_customer_scan_types = null;
        PointOfSale.other_payment_types = null;
        PointOfSale.searching_customers = false;
        PointOfSale.pole_display_printer = null;
    }
    
    private class ButtonTypeFunction implements ActionListener
    {
        public void actionPerformed(final ActionEvent event) {
            PointOfSale.this.areWeLoggedIn();
            if (PointOfSale.this.current_prompt == PointOfSale.this.receipt_finished_prompt) {
                PointOfSale.this.hidePrompt();
            }
            final Object o = event.getSource();
            if (!(o instanceof POSButton)) {
                return;
            }
            final POSButton pos_button = (POSButton)o;
            final POSKeyboardButton button = pos_button.button;
            switch (button.button_value) {
                case 0: {
                    if (PointOfSale.this.current_prompt != null) {
                        PointOfSale.this.current_prompt.clickOk();
                    }
                    if (PointOfSale.this.tenderReceipt(PointOfSale.this.pos_receipt, 0)) {
                        PointOfSale.this.receipt_finished_prompt.showReceiptFinished();
                        PointOfSale.this.pos_receipt = new POSReceipt(PointOfSale.this.default_customer);
                        PointOfSale.this.receipt_table.revalidate();
                        PointOfSale.this.receipt_table.repaint();
                        PointOfSale.this.showTotals();
                        break;
                    }
                    break;
                }
                case 1: {
                    PointOfSale.this.tenderReceipt(PointOfSale.this.pos_receipt, 2);
                    break;
                }
                case 2: {
                    PointOfSale.this.price_change_pending = true;
                    PointOfSale.this.hidePrompt();
                    break;
                }
                case 3: {
                    PointOfSale.this.hidePrompt();
                    if (PointOfSale.this.pos_receipt.receipt_items.isEmpty()) {
                        PointOfSale.beep();
                        break;
                    }
                    final POSReceiptItem item = PointOfSale.this.pos_receipt.receipt_items.get(PointOfSale.this.pos_receipt.receipt_items.size() - 1);
                    if (item.isServerEntry()) {
                        if (item.isFeeSurcharge()) {
                            JOptionPane.showConfirmDialog(PointOfSale.this.pos_panel, "Cannot delete \"" + item.description + "\" here. Please return to Front Desk menu to delete.", "Delete not allowed", -1, 1);
                            break;
                        }
                        if (JOptionPane.showConfirmDialog(PointOfSale.this.pos_panel, "Are you sure you want to delete \"" + item.description + "\"?", "Confirm Delete", 0, 3) != 0) {
                            break;
                        }
                    }
                    PointOfSale.this.pos_receipt.deleteEntry(PointOfSale.this.tax_rates);
                    PointOfSale.this.receipt_table_model.rowDeleted();
                    if (PointOfSale.this.enable_pole_display) {
                        PointOfSale.this.displayPoleDisplayMessage("Delete " + item.description, false, false, "Qty:" + item.quantity + "    $" + item.fee_summary.total(), false, false);
                    }
                    PointOfSale.this.refund_mode = false;
                    if (item.parent_product_id > 0) {
                        for (int i = PointOfSale.this.pos_receipt.receipt_items.size() - 1; i >= 0; --i) {
                            final POSReceiptItem sub_item = PointOfSale.this.pos_receipt.receipt_items.get(i);
                            if (sub_item.product_group_uid == item.product_group_uid) {
                                if (sub_item.parent_product_id == item.parent_product_id || sub_item.button.button_value == item.parent_product_id) {
                                    PointOfSale.this.pos_receipt.deleteEntry(PointOfSale.this.tax_rates);
                                    PointOfSale.this.receipt_table_model.rowDeleted();
                                }
                            }
                        }
                    }
                    if (item.isServerEntry()) {
                        PointOfSale.this.tenderReceipt(PointOfSale.this.pos_receipt, 3);
                        break;
                    }
                    if (item.isFromAccountPayment()) {
                        PointOfSale.this.resetCustomerField(PointOfSale.this.getBalance());
                    }
                    PointOfSale.this.showTotals();
                    break;
                }
                case 4: {
                    int count = PointOfSale.this.pos_receipt.receipt_items.size();
                    if (count == 0) {
                        PointOfSale.beep();
                        break;
                    }
                    if (!PointOfSale.this.pass_void_receipt_profile_check && (PointOfSale.this.user_options & 0x100L) != 0x0L) {
                        final JTextField tf_user_name = new JTextField(10);
                        final JPasswordField tf_password = new JPasswordField(10);
                        if (!PointOfSale.this.getValidateUser(tf_user_name, tf_password)) {
                            return;
                        }
                        final Object obj = PointOfSale.this.fetchObject("/validateUserProfile.sdi?profile_id=490&uname=" + tf_user_name.getText() + "&psw=" + new String(tf_password.getPassword()));
                        if (obj != null && obj instanceof String) {
                            PointOfSale.this.showException((String)obj, "Cannot void receipt.");
                            return;
                        }
                        PointOfSale.this.pass_void_receipt_profile_check = true;
                    }
                    if (JOptionPane.showConfirmDialog(PointOfSale.this.pos_panel, "Are you sure you want to void this receipt?", "Confirm Void", 0, 3) != 0) {
                        break;
                    }
                    while (--count >= 0) {
                        PointOfSale.this.pos_receipt.deleteEntry(PointOfSale.this.tax_rates);
                        PointOfSale.this.receipt_table_model.rowDeleted();
                    }
                    PointOfSale.this.showTotals();
                    if (PointOfSale.this.enable_pole_display) {
                        PointOfSale.this.displayPoleDisplayMessage(PointOfSale.this.message_for_pole_display, true, false, null, false, true);
                        break;
                    }
                    break;
                }
                case 5: {
                    if (PointOfSale.this.enable_pole_display) {
                        PointOfSale.this.displayPoleDisplayMessage("Total", false, false, "$" + PointOfSale.this.pos_receipt.charges().total(), false, false);
                    }
                    PointOfSale.this.showPaymentPanel(PointOfSale.this.memo_prompt, button.button_value);
                    break;
                }
                case 6: {
                    if (PointOfSale.this.enable_pole_display) {
                        PointOfSale.this.displayPoleDisplayMessage("Total", false, false, "$" + PointOfSale.this.pos_receipt.charges().total(), false, false);
                    }
                    PointOfSale.this.showPaymentPanel(PointOfSale.this.charge_prompt, button.button_value);
                    break;
                }
                case 7: {
                    if (PointOfSale.this.enable_pole_display) {
                        PointOfSale.this.displayPoleDisplayMessage("Total", false, false, "$" + PointOfSale.this.pos_receipt.charges().total(), false, false);
                    }
                    PointOfSale.this.showPaymentPanel(PointOfSale.this.check_prompt, button.button_value);
                    break;
                }
                case 8: {
                    if (!PointOfSale.this.refund_receipt && !PointOfSale.this.allow_payment_with_cash) {
                        JOptionPane.showConfirmDialog(PointOfSale.this.pos_panel, "Cash payments not allowed", "Payment Type", -1, 0);
                        break;
                    }
                    if (PointOfSale.this.refund_receipt && !PointOfSale.this.allow_refund_with_cash) {
                        JOptionPane.showConfirmDialog(PointOfSale.this.pos_panel, "Cash refunds not allowed", "Payment Type", -1, 0);
                        break;
                    }
                    if (PointOfSale.this.enable_pole_display) {
                        PointOfSale.this.displayPoleDisplayMessage("Total", false, false, "$" + PointOfSale.this.pos_receipt.charges().total(), false, false);
                    }
                    PointOfSale.this.showPaymentPanel(PointOfSale.this.refund_receipt ? PointOfSale.this.cash_refund_prompt : PointOfSale.this.cash_prompt, button.button_value);
                    break;
                }
                case 15: {
                    if (PointOfSale.this.getBalance().signum() <= 0) {
                        String error = null;
                        if (PointOfSale.this.pos_receipt.receipt_customer.customer_id == PointOfSale.this.default_customer.customer_id) {
                            error = "No customer selected.";
                        }
                        else {
                            error = "Insufficient Funds.";
                        }
                        PointOfSale.this.showException(error, "Cannot add from account payment.");
                        break;
                    }
                    String title = PointOfSale.titles[10];
                    final BigDecimal balance = PointOfSale.this.getBalance();
                    PointOfSale.this.resetCustomerField(balance);
                    if (balance.signum() > 0) {
                        title = NumberFormat.getCurrencyInstance().format(balance) + " " + title;
                    }
                    PointOfSale.this.from_account_prompt.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title));
                    PointOfSale.this.from_account_prompt.amount_field.setText("");
                    PointOfSale.this.showPaymentPanel(PointOfSale.this.from_account_prompt, button.button_value);
                    break;
                }
                case 17: {
                    final String title = PointOfSale.titles[11];
                    if (PointOfSale.this.enable_pole_display) {
                        PointOfSale.this.displayPoleDisplayMessage("Total", false, false, "$" + PointOfSale.this.pos_receipt.charges().total(), false, false);
                    }
                    PointOfSale.this.other_payment_prompt.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title));
                    PointOfSale.this.other_payment_prompt.amount_field.setText("");
                    PointOfSale.this.showPaymentPanel(PointOfSale.this.other_payment_prompt, button.button_value);
                    break;
                }
                case 9: {
                    PointOfSale.this.openCashDrawer();
                    break;
                }
                case 10: {
                    PointOfSale.this.tenderReceipt(PointOfSale.this.pos_receipt, (PointOfSale.this.pos_receipt.rno > 0) ? 1 : 3);
                    try {
                        PointOfSale.this.getAppletContext().showDocument(new URL(PointOfSale.this.secure_preface + "/selectPOSCustomer.sdi?rno=" + PointOfSale.this.pos_receipt.rno), "_self");
                    }
                    catch (Exception e) {}
                    break;
                }
                case 16: {
                    PointOfSale.this.pos_panel.customer_search_dialog.resetFields();
                    PointOfSale.this.pos_panel.customer_search_dialog.showDialog();
                    break;
                }
                case 11: {
                    PointOfSale.this.refund_mode = !PointOfSale.this.refund_mode;
                    PointOfSale.this.refund_customer_note_required = button.prompt_for_customer_notes;
                    break;
                }
                case 12: {
                    PointOfSale.this.pos_panel.product_search_dialog.resetFields();
                    PointOfSale.this.pos_panel.product_search_dialog.showDialog();
                    break;
                }
                case 13: {
                    if (PointOfSale.this.pos_receipt.receipt_items.size() > 0) {
                        JOptionPane.showConfirmDialog(PointOfSale.this.pos_panel, "Current receipt must be finished off before printing", "Receipt in Process", -1, 1);
                        break;
                    }
                    if (PointOfSale.this.com_port == 0) {
                        JOptionPane.showConfirmDialog(PointOfSale.this.pos_panel, "Missing POS COM port in workstation record. POS close report will not be printed.", "Missing Comm Port", -1, 0);
                        break;
                    }
                    try {
                        PointOfSale.this.getAppletContext().showDocument(new URL(PointOfSale.this.secure_preface + "/showFilters.sdi?rptno=211&pos_printer_output=true&pos_printer_width=" + PointOfSale.this.small_font_char_count), "_self");
                    }
                    catch (Exception ex) {}
                    break;
                }
                case 14: {
                    PointOfSale.this.hidePrompt();
                    final int[] row_indices = PointOfSale.this.receipt_table.getSelectedRows();
                    if (row_indices.length == 0) {
                        PointOfSale.beep();
                        break;
                    }
                    final ArrayList<POSReceiptItem> deleted_items = new ArrayList<POSReceiptItem>();
                    boolean deleted_server_items = false;
                    for (int j = row_indices.length - 1; j >= 0; --j) {
                        final int selected_index = row_indices[j];
                        final POSReceiptItem rcpt_item = PointOfSale.this.pos_receipt.receipt_items.get(selected_index);
                        boolean delete_item = true;
                        if (rcpt_item.isServerEntry()) {
                            if (rcpt_item.isFeeSurcharge()) {
                                JOptionPane.showConfirmDialog(PointOfSale.this.pos_panel, "Cannot delete \"" + rcpt_item.description + "\" here. Please return to Front Desk menu to delete.", "Delete not allowed", -1, 1);
                                delete_item = false;
                            }
                            else if (JOptionPane.showConfirmDialog(PointOfSale.this.pos_panel, "Are you sure you want to delete \"" + rcpt_item.description + "\"?", "Confirm Delete", 0, 3) != 0) {
                                delete_item = false;
                            }
                        }
                        if (delete_item) {
                            if (rcpt_item.isServerEntry()) {
                                deleted_server_items = true;
                            }
                            PointOfSale.this.pos_receipt.deleteEntry(PointOfSale.this.tax_rates, selected_index);
                            PointOfSale.this.receipt_table_model.rowDeleted(selected_index);
                            deleted_items.add(rcpt_item);
                        }
                    }
                    PointOfSale.this.receipt_table.clearSelection();
                    PointOfSale.this.refund_mode = false;
                    for (final POSReceiptItem deleted_item : deleted_items) {
                        if (deleted_item.parent_product_id == 0 && deleted_item.button.discount_products != null && deleted_item.button.discount_products.size() > 0) {
                            PointOfSale.this.deleteRelatedItems(deleted_item);
                        }
                        if ((deleted_item.parent_product_id > 0 && deleted_item.button.pos_membership_discount_key <= 0) || (deleted_item.button.linking_products != null && deleted_item.button.linking_products.size() > 0)) {
                            for (int k = PointOfSale.this.pos_receipt.receipt_items.size() - 1; k >= 0; --k) {
                                final POSReceiptItem sub_item2 = PointOfSale.this.pos_receipt.receipt_items.get(k);
                                if (deleted_item.product_group_uid == sub_item2.product_group_uid) {
                                    if (deleted_item.parent_product_id > 0 && (sub_item2.parent_product_id == deleted_item.parent_product_id || sub_item2.button.button_value == deleted_item.parent_product_id)) {
                                        PointOfSale.this.pos_receipt.deleteEntry(PointOfSale.this.tax_rates, k);
                                        PointOfSale.this.receipt_table_model.rowDeleted(k);
                                    }
                                    if (deleted_item.button.linking_products != null && deleted_item.button.linking_products.size() > 0 && sub_item2.parent_product_id == deleted_item.button.button_value) {
                                        PointOfSale.this.pos_receipt.deleteEntry(PointOfSale.this.tax_rates, k);
                                        PointOfSale.this.receipt_table_model.rowDeleted(k);
                                    }
                                }
                            }
                        }
                    }
                    if (deleted_items.size() > 0 && deleted_items.get(0).button.pos_membership_discount_key <= 0) {
                        PointOfSale.this.refreshMembershipDiscounts();
                    }
                    if (deleted_server_items) {
                        PointOfSale.this.tenderReceipt(PointOfSale.this.pos_receipt, 3);
                        break;
                    }
                    PointOfSale.this.showTotals();
                    break;
                }
                case 18: {
                    if (PointOfSale.this.pos_receipt.receipt_items == null || PointOfSale.this.pos_receipt.receipt_items.isEmpty()) {
                        PointOfSale.this.showException("Cannot allocate commission scheme without product");
                        break;
                    }
                    PointOfSale.this.tenderReceipt(PointOfSale.this.pos_receipt, (PointOfSale.this.pos_receipt.rno > 0) ? 1 : 3);
                    try {
                        PointOfSale.this.getAppletContext().showDocument(new URL(PointOfSale.this.secure_preface + "/showPOSCommissionAllocations.sdi?rno=" + PointOfSale.this.pos_receipt.rno), "_self");
                    }
                    catch (Exception e2) {}
                    break;
                }
                default: {
                    PointOfSale.beep();
                    break;
                }
            }
        }
    }
    
    private class ButtonTypeProduct implements ActionListener
    {
        public void actionPerformed(final ActionEvent event) {
            PointOfSale.this.areWeLoggedIn();
            if (PointOfSale.this.current_prompt == PointOfSale.this.receipt_finished_prompt) {
                PointOfSale.this.hidePrompt();
            }
            if (PointOfSale.this.current_prompt != null && PointOfSale.this.current_prompt != PointOfSale.this.quantity_prompt) {
                PointOfSale.beep();
                return;
            }
            final Object o = event.getSource();
            if (!(o instanceof POSButton)) {
                return;
            }
            final POSButton pos_button = (POSButton)o;
            final POSKeyboardButton button = pos_button.button;
            if (button.log_usage && button.no_receipt) {
                PointOfSale.this.logMembershipUsage(button.button_value);
                return;
            }
            PointOfSale.this.customer_notes = null;
            PointOfSale.this.handleProductButton(button);
        }
    }
    
    private class ButtonTypeDigit implements ActionListener
    {
        public void actionPerformed(final ActionEvent event) {
            if (PointOfSale.this.current_prompt == PointOfSale.this.receipt_finished_prompt) {
                PointOfSale.this.hidePrompt();
            }
            final Object o = event.getSource();
            if (!(o instanceof POSButton)) {
                return;
            }
            final POSButton pos_button = (POSButton)o;
            final POSKeyboardButton button = pos_button.button;
            switch (button.button_value) {
                case 0: {
                    this.insertDigit("0");
                    break;
                }
                case 1: {
                    this.insertDigit("1");
                    break;
                }
                case 2: {
                    this.insertDigit("2");
                    break;
                }
                case 3: {
                    this.insertDigit("3");
                    break;
                }
                case 4: {
                    this.insertDigit("4");
                    break;
                }
                case 5: {
                    this.insertDigit("5");
                    break;
                }
                case 6: {
                    this.insertDigit("6");
                    break;
                }
                case 7: {
                    this.insertDigit("7");
                    break;
                }
                case 8: {
                    this.insertDigit("8");
                    break;
                }
                case 9: {
                    this.insertDigit("9");
                    break;
                }
                case 10: {
                    if (PointOfSale.this.current_prompt != null) {
                        PointOfSale.this.current_prompt.addCurrency(new BigDecimal("5.00"));
                        break;
                    }
                    break;
                }
                case 11: {
                    if (PointOfSale.this.current_prompt != null) {
                        PointOfSale.this.current_prompt.addCurrency(new BigDecimal("10.00"));
                        break;
                    }
                    break;
                }
                case 12: {
                    if (PointOfSale.this.current_prompt != null) {
                        PointOfSale.this.current_prompt.addCurrency(new BigDecimal("20.00"));
                        break;
                    }
                    break;
                }
                case 13: {
                    this.insertDigit("00");
                    break;
                }
                case 14: {
                    if (PointOfSale.this.current_prompt != null) {
                        PointOfSale.this.current_prompt.addDecimal();
                        break;
                    }
                    break;
                }
                case 15: {
                    if (PointOfSale.this.current_prompt != null) {
                        PointOfSale.this.current_prompt.backspace();
                        break;
                    }
                    break;
                }
            }
            if (PointOfSale.this.current_prompt != null) {
                PointOfSale.this.current_prompt.currentField().requestFocusInWindow();
            }
        }
        
        private void insertDigit(final String digit) {
            if (PointOfSale.this.current_prompt == null) {
                (PointOfSale.this.current_prompt = PointOfSale.this.quantity_prompt).setInitialFocus(BigDecimal.valueOf(1L));
            }
            PointOfSale.this.current_prompt.insertDigit(digit);
        }
    }
    
    private class ButtonTypeLink implements ActionListener
    {
        public void actionPerformed(final ActionEvent event) {
            final Object o = event.getSource();
            if (!(o instanceof POSButton)) {
                return;
            }
            final POSButton pos_button = (POSButton)o;
            final POSKeyboardButton button = pos_button.button;
            final POSKeyboard kb = PointOfSale.this.fetchKeyboard(button.button_value);
            PointOfSale.this.pos_panel.constructKeyboard(kb);
            PointOfSale.this.pos_panel.validate();
            PointOfSale.this.upc_name_field.setText("");
            PointOfSale.this.upc_name_field.requestFocus();
        }
    }
    
    class InitialFocusSetter extends WindowAdapter
    {
        private Component component;
        
        public InitialFocusSetter(final Component component) {
            this.component = ((component instanceof JComboBox) ? ((JComboBox)component).getEditor().getEditorComponent() : component);
        }
        
        public void windowActivated(final WindowEvent e) {
            this.component.requestFocus();
            e.getWindow().removeWindowListener(this);
        }
    }
    
    class POSPanel extends JPanel
    {
        private static final long serialVersionUID = 201001L;
        private Vector<POSKeyboardButton> buttons;
        CustomerNotesDialog customer_notes_dialog;
        ProductSearchDialog product_search_dialog;
        ProductListDialog product_list_dialog;
        CustomerSearchDialog customer_search_dialog;
        
        public POSPanel() {
            this.buttons = null;
            this.customer_notes_dialog = null;
            this.product_search_dialog = null;
            this.product_list_dialog = null;
            this.customer_search_dialog = null;
            this.setLayout(null);
            this.setBackground(Color.white);
            final ComponentListener cl = new ComponentAction();
            this.addComponentListener(cl);
            final Frame f = (Frame)SwingUtilities.getAncestorOfClass(Frame.class, this);
            this.customer_notes_dialog = new CustomerNotesDialog(f);
            this.product_search_dialog = new ProductSearchDialog(f);
            this.product_list_dialog = new ProductListDialog(f);
            this.customer_search_dialog = new CustomerSearchDialog(f);
            if (PointOfSale.departments == null) {
                PointOfSale.departments = PointOfSale.this.fetchDepartments();
            }
            if (PointOfSale.classes == null) {
                PointOfSale.classes = PointOfSale.this.fetchClasses();
            }
            if (PointOfSale.subclasses == null) {
                PointOfSale.subclasses = PointOfSale.this.fetchSubclasses();
            }
            if (PointOfSale.sites == null) {
                PointOfSale.sites = PointOfSale.this.fetchSites();
            }
            if (PointOfSale.avail_customer_scan_types == null) {
                PointOfSale.avail_customer_scan_types = PointOfSale.this.fetchCustomerScanTypes();
            }
            if (PointOfSale.other_payment_types == null) {
                PointOfSale.other_payment_types = PointOfSale.this.fetchOtherPaymentTypes();
            }
        }
        
        public void constructKeyboard(final POSKeyboard keyboard) {
            if (keyboard == null) {
                return;
            }
            if (this.buttons == null) {
                this.buttons = new Vector<POSKeyboardButton>(keyboard.buttons);
            }
            else {
                for (int i = this.buttons.size() - 1; i >= 0; --i) {
                    final POSKeyboardButton button = this.buttons.get(i);
                    if (!button.same_across_all_layouts) {
                        if (i < keyboard.buttons.size()) {
                            this.buttons.set(i, keyboard.buttons.get(i));
                        }
                    }
                }
            }
            final Component[] components = this.getComponents();
            for (int j = 0; j < components.length; ++j) {
                if (components[j] instanceof POSButton) {
                    this.remove(components[j]);
                }
            }
            for (int j = 0; j < this.buttons.size(); ++j) {
                final POSKeyboardButton b = this.buttons.get(j);
                if (b.visible) {
                    if (b.button_type != 0) {
                        if (b.button_type == 4) {
                            if (b.button_value == 11 && (PointOfSale.this.user_options & 0x1L) != 0x0L) {
                                continue;
                            }
                            if (b.button_value == 8 && !PointOfSale.this.allow_payment_with_cash && !PointOfSale.this.allow_refund_with_cash) {
                                continue;
                            }
                            if (b.button_value == 5 && !PointOfSale.this.allow_payment_by_memo) {
                                continue;
                            }
                        }
                        final POSButton component = new POSButton(b);
                        if (component.col < 9 && j + 1 < this.buttons.size()) {
                            final POSKeyboardButton adjacent = this.buttons.get(j + 1);
                            if (b.button_type == adjacent.button_type && b.button_value == adjacent.button_value) {
                                component.col_span = 2;
                                adjacent.visible = false;
                            }
                        }
                        if (component.row < 9 && j + 9 < this.buttons.size()) {
                            final POSKeyboardButton below = this.buttons.get(j + 9);
                            if (b.button_type == below.button_type && b.button_value == below.button_value) {
                                component.row_span = 2;
                                below.visible = false;
                                if (component.col < 9 && component.col_span == 2) {
                                    final POSKeyboardButton adjacent2 = this.buttons.get(j + 10);
                                    adjacent2.visible = false;
                                }
                            }
                        }
                        this.add(component);
                    }
                }
            }
            final ComponentEvent ce = new ComponentEvent(this, 101);
            final ComponentListener[] listeners = this.getComponentListeners();
            for (int k = 0; k < listeners.length; ++k) {
                listeners[k].componentResized(ce);
            }
            this.revalidate();
            this.repaint();
        }
        
        class SingleSelectionModel extends DefaultListSelectionModel
        {
            private static final long serialVersionUID = 201001L;
            
            public SingleSelectionModel() {
                this.setSelectionMode(0);
            }
            
            public void setSelectionInterval(final int index0, final int index1) {
                final int oldIndex = this.getMinSelectionIndex();
                super.setSelectionInterval(index0, index1);
                final int newIndex = this.getMinSelectionIndex();
                if (oldIndex != newIndex) {
                    this.updateSingleSelection(oldIndex, newIndex);
                }
            }
            
            public void updateSingleSelection(final int oldIndex, final int newIndex) {
            }
        }
        
        class ProductListDialog extends JDialog
        {
            private static final long serialVersionUID = 201001L;
            JList product_list;
            JScrollPane product_scroll_pane;
            JButton cancel_button;
            JButton search_again_button;
            ArrayList products;
            
            ProductListDialog(final Frame owner) {
                super(owner, "Product List", true);
                this.product_list = new JList();
                this.product_scroll_pane = new JScrollPane();
                this.cancel_button = new JButton();
                this.search_again_button = new JButton();
                this.products = null;
                final Action enter_action = new AbstractAction() {
                    private static final long serialVersionUID = 201001L;
                    
                    public void actionPerformed(final ActionEvent evt) {
                        ProductListDialog.this.products = null;
                        ProductListDialog.this.setVisible(false);
                        POSPanel.this.product_search_dialog.showDialog();
                    }
                };
                final KeyStroke enter = KeyStroke.getKeyStroke(10, 0, false);
                this.getRootPane().getInputMap(2).put(enter, "ENTER");
                this.getRootPane().getActionMap().put("ENTER", enter_action);
                final Action escape_action = new AbstractAction() {
                    private static final long serialVersionUID = 201001L;
                    
                    public void actionPerformed(final ActionEvent evt) {
                        ProductListDialog.this.products = null;
                        ProductListDialog.this.setVisible(false);
                    }
                };
                final KeyStroke escape = KeyStroke.getKeyStroke(27, 0, false);
                this.getRootPane().getInputMap(2).put(escape, "ESCAPE");
                this.getRootPane().getActionMap().put("ESCAPE", escape_action);
                this.search_again_button.setText("Search Again");
                this.search_again_button.addActionListener(new ActionListener() {
                    public void actionPerformed(final ActionEvent evt) {
                        ProductListDialog.this.products = null;
                        ProductListDialog.this.setVisible(false);
                        POSPanel.this.product_search_dialog.showDialog();
                    }
                });
                this.cancel_button.setText("Cancel");
                this.cancel_button.addActionListener(new ActionListener() {
                    public void actionPerformed(final ActionEvent evt) {
                        ProductListDialog.this.products = null;
                        ProductListDialog.this.setVisible(false);
                    }
                });
                final ListSelectionModel selection_model = new SingleSelectionModel() {
                    private static final long serialVersionUID = 201001L;
                    
                    public void updateSingleSelection(final int old_index, final int new_index) {
                        final ListModel m = ProductListDialog.this.product_list.getModel();
                        if (ProductListDialog.this.products != null && new_index >= 0 && new_index < ProductListDialog.this.products.size()) {
                            PointOfSale.this.handleProductButton(ProductListDialog.this.products.get(new_index));
                            ProductListDialog.this.products = null;
                            ProductListDialog.this.setVisible(false);
                        }
                    }
                };
                this.product_list.setSelectionModel(selection_model);
                this.product_scroll_pane.setViewportView(this.product_list);
                final GroupLayout layout = new GroupLayout(this.getContentPane());
                this.getContentPane().setLayout(layout);
                layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(61, 61, 61).addComponent(this.search_again_button).addGap(27, 27, 27).addComponent(this.cancel_button)).addGroup(layout.createSequentialGroup().addGap(21, 21, 21).addComponent(this.product_scroll_pane, -2, 342, -2))).addContainerGap(23, 32767)));
                layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(24, 24, 24).addComponent(this.product_scroll_pane, -2, 220, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 29, 32767).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.cancel_button).addComponent(this.search_again_button)).addContainerGap()));
                this.pack();
            }
            
            public void showDialog(ArrayList products) {
                this.products = products;
                if (products != null) {
                    this.product_list.setListData(products.toArray());
                }
                else {
                    this.product_list.setListData(new Object[0]);
                    products = new ArrayList();
                }
                final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                final Dimension windowSize = this.getSize();
                this.setLocation(Math.max(0, (screenSize.width - windowSize.width) / 2), Math.max(0, (screenSize.height - windowSize.height) / 2));
                PointOfSale.this.setInitialFocusComponent(this, this.product_list);
                this.setVisible(true);
            }
        }
        
        class ProductSearchDialog extends JDialog
        {
            private static final long serialVersionUID = 201001L;
            JLabel error_msg;
            JLabel upc_label;
            JTextField upc_field;
            JLabel product_name_label;
            JTextField product_name_field;
            JLabel department_label;
            JComboBox department_list;
            JLabel class_label;
            JComboBox class_list;
            JLabel subclass_label;
            JComboBox subclass_list;
            JLabel site_label;
            JComboBox site_list;
            JButton search_button;
            JButton cancel_button;
            
            ProductSearchDialog(final Frame owner) {
                super(owner, "Product Search", true);
                this.error_msg = new JLabel();
                this.upc_label = new JLabel();
                this.upc_field = new JTextField();
                this.product_name_label = new JLabel();
                this.product_name_field = new JTextField();
                this.department_label = new JLabel();
                this.department_list = new JComboBox();
                this.class_label = new JLabel();
                this.class_list = new JComboBox();
                this.subclass_label = new JLabel();
                this.subclass_list = new JComboBox();
                this.site_label = new JLabel();
                this.site_list = new JComboBox();
                this.search_button = new JButton();
                this.cancel_button = new JButton();
                final Action enter_action = new AbstractAction() {
                    private static final long serialVersionUID = 201001L;
                    
                    public void actionPerformed(final ActionEvent evt) {
                        ProductSearchDialog.this.search_buttonActionPerformed(evt);
                    }
                };
                final KeyStroke enter = KeyStroke.getKeyStroke(10, 0, false);
                this.getRootPane().getInputMap(2).put(enter, "ENTER");
                this.getRootPane().getActionMap().put("ENTER", enter_action);
                final Action escape_action = new AbstractAction() {
                    private static final long serialVersionUID = 201001L;
                    
                    public void actionPerformed(final ActionEvent evt) {
                        ProductSearchDialog.this.setVisible(false);
                    }
                };
                final KeyStroke escape = KeyStroke.getKeyStroke(27, 0, false);
                this.getRootPane().getInputMap(2).put(escape, "ESCAPE");
                this.getRootPane().getActionMap().put("ESCAPE", escape_action);
                this.error_msg.setForeground(Color.red);
                this.upc_label.setText("UPC");
                this.product_name_label.setText("Product Name");
                this.department_label.setText("Department");
                this.department_list.setModel(new DefaultComboBoxModel());
                this.department_list.addActionListener(new ActionListener() {
                    public void actionPerformed(final ActionEvent evt) {
                        ProductSearchDialog.this.department_listActionPerformed(evt);
                    }
                });
                this.class_label.setText("Class");
                this.class_list.setModel(new DefaultComboBoxModel());
                this.class_list.addActionListener(new ActionListener() {
                    public void actionPerformed(final ActionEvent evt) {
                        ProductSearchDialog.this.class_listActionPerformed(evt);
                    }
                });
                this.subclass_label.setText("Subclass");
                this.subclass_list.setModel(new DefaultComboBoxModel());
                this.site_label.setText("Site");
                this.site_list.setModel(new DefaultComboBoxModel());
                this.search_button.setText("Search");
                this.search_button.addActionListener(new ActionListener() {
                    public void actionPerformed(final ActionEvent evt) {
                        ProductSearchDialog.this.search_buttonActionPerformed(evt);
                    }
                });
                this.cancel_button.setText("Cancel");
                this.cancel_button.addActionListener(new ActionListener() {
                    public void actionPerformed(final ActionEvent evt) {
                        ProductSearchDialog.this.setVisible(false);
                    }
                });
                final GroupLayout layout = new GroupLayout(this.getContentPane());
                this.getContentPane().setLayout(layout);
                layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(23, 23, 23).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.subclass_label).addComponent(this.class_label).addComponent(this.upc_label).addComponent(this.product_name_label).addComponent(this.site_label).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.search_button).addComponent(this.department_label))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.site_list, 0, 180, 32767).addComponent(this.upc_field).addComponent(this.department_list, 0, 180, 32767).addComponent(this.product_name_field).addComponent(this.class_list, 0, 180, 32767).addComponent(this.subclass_list, 0, 180, 32767)).addGap(111, 111, 111)).addGroup(layout.createSequentialGroup().addGap(148, 148, 148).addComponent(this.cancel_button).addContainerGap(88, 32767)).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.error_msg, -2, 271, -2).addContainerGap(22, 32767)));
                layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(4, 4, 4).addComponent(this.error_msg).addGap(16, 16, 16).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.upc_field, -2, -1, -2).addComponent(this.upc_label)).addGap(16, 16, 16).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.product_name_field, -2, -1, -2).addComponent(this.product_name_label)).addGap(16, 16, 16).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.department_list, -2, -1, -2).addComponent(this.department_label)).addGap(16, 16, 16).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.class_list, -2, -1, -2).addComponent(this.class_label)).addGap(16, 16, 16).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.subclass_list, -2, -1, -2).addComponent(this.subclass_label)).addGap(16, 16, 16).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.site_list, -2, -1, -2).addComponent(this.site_label)).addGap(16, 16, 16).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.cancel_button).addComponent(this.search_button)).addContainerGap(25, 32767)));
                this.pack();
            }
            
            void department_listActionPerformed(final ActionEvent evt) {
                this.class_list.removeAllItems();
                this.subclass_list.removeAllItems();
                final POSProductDepartment selected_dept = this.selectedDepartment();
                if (selected_dept == null) {
                    return;
                }
                this.class_list.addItem("");
                for (int i = 0; PointOfSale.classes != null && i < PointOfSale.classes.size(); ++i) {
                    final POSProductClass c = PointOfSale.classes.get(i);
                    if (c.product_department_id == selected_dept.product_department_id) {
                        this.class_list.addItem(c);
                    }
                }
            }
            
            void class_listActionPerformed(final ActionEvent evt) {
                this.subclass_list.removeAllItems();
                final POSProductClass selected_class = this.selectedClass();
                if (selected_class == null) {
                    return;
                }
                this.subclass_list.addItem("");
                for (int i = 0; PointOfSale.subclasses != null && i < PointOfSale.subclasses.size(); ++i) {
                    final POSProductSubclass c = PointOfSale.subclasses.get(i);
                    if (c.product_class_id == selected_class.product_class_id) {
                        this.subclass_list.addItem(c);
                    }
                }
            }
            
            void search_buttonActionPerformed(final ActionEvent evt) {
                this.error_msg.setText("");
                final StringBuilder params = new StringBuilder();
                try {
                    if (this.upc_field.getText().length() > 0) {
                        if (params.length() > 0) {
                            params.append("&");
                        }
                        params.append("upc=").append(URLEncoder.encode(extractUPCCode(this.upc_field.getText()), "UTF-8"));
                    }
                    if (this.product_name_field.getText().length() > 0) {
                        if (params.length() > 0) {
                            params.append("&");
                        }
                        params.append("productname=").append(URLEncoder.encode(this.product_name_field.getText(), "UTF-8"));
                    }
                    final POSProductDepartment dept = this.selectedDepartment();
                    if (dept != null) {
                        if (params.length() > 0) {
                            params.append("&");
                        }
                        params.append("productdepartment_id=").append(dept.product_department_id);
                    }
                    final POSProductClass c = this.selectedClass();
                    if (c != null) {
                        if (params.length() > 0) {
                            params.append("&");
                        }
                        params.append("productclass_id=").append(c.product_class_id);
                    }
                    final POSProductSubclass sc = this.selectedSubclass();
                    if (sc != null) {
                        if (params.length() > 0) {
                            params.append("&");
                        }
                        params.append("productsubclass_id=").append(sc.product_subclass_id);
                    }
                    final POSSite site = this.selectedSite();
                    if (site != null) {
                        if (params.length() > 0) {
                            params.append("&");
                        }
                        params.append("site_id=").append(site.site_id);
                    }
                }
                catch (Exception e) {
                    this.error_msg.setText("Exception: " + e.getMessage());
                    return;
                }
                if (params.length() == 0) {
                    this.error_msg.setText("Please specify some search values");
                    return;
                }
                params.append("&no_free_merchandise=1");
                final ArrayList products = PointOfSale.this.searchProducts(params.toString());
                if (products.isEmpty()) {
                    this.error_msg.setText("No items matched the search values");
                }
                else if (products.size() == 1) {
                    this.setVisible(false);
                    PointOfSale.this.handleProductButton(products.get(0));
                }
                else {
                    this.setVisible(false);
                    POSPanel.this.product_list_dialog.showDialog(products);
                }
            }
            
            POSSite selectedSite() {
                final int index = this.site_list.getSelectedIndex();
                if (index <= 0) {
                    return null;
                }
                return PointOfSale.sites.get(index - 1);
            }
            
            POSProductDepartment selectedDepartment() {
                final int index = this.department_list.getSelectedIndex();
                if (index <= 0) {
                    return null;
                }
                return PointOfSale.departments.get(index - 1);
            }
            
            POSProductClass selectedClass() {
                final POSProductDepartment selected_dept = this.selectedDepartment();
                if (selected_dept == null) {
                    return null;
                }
                final int index = this.class_list.getSelectedIndex();
                if (index <= 0) {
                    return null;
                }
                final ArrayList<POSProductClass> class_subset = new ArrayList<POSProductClass>();
                for (int i = 0; PointOfSale.classes != null && i < PointOfSale.classes.size(); ++i) {
                    final POSProductClass c = PointOfSale.classes.get(i);
                    if (c.product_department_id == selected_dept.product_department_id) {
                        class_subset.add(c);
                    }
                }
                return class_subset.get(index - 1);
            }
            
            POSProductSubclass selectedSubclass() {
                final POSProductClass selected_class = this.selectedClass();
                if (selected_class == null) {
                    return null;
                }
                final int index = this.subclass_list.getSelectedIndex();
                if (index <= 0) {
                    return null;
                }
                final ArrayList<POSProductSubclass> subclass_subset = new ArrayList<POSProductSubclass>();
                for (int i = 0; PointOfSale.subclasses != null && i < PointOfSale.subclasses.size(); ++i) {
                    final POSProductSubclass c = PointOfSale.subclasses.get(i);
                    if (c.product_class_id == selected_class.product_class_id) {
                        subclass_subset.add(c);
                    }
                }
                return subclass_subset.get(index - 1);
            }
            
            public void resetFields() {
                this.error_msg.setText("");
                this.department_list.removeAllItems();
                this.department_list.addItem("");
                for (int i = 0; PointOfSale.departments != null && i < PointOfSale.departments.size(); ++i) {
                    this.department_list.addItem(PointOfSale.departments.get(i));
                }
                this.upc_field.setText("");
                this.product_name_field.setText("");
                this.class_list.removeAllItems();
                this.subclass_list.removeAllItems();
                this.site_list.removeAllItems();
                if (PointOfSale.sites != null) {
                    if (PointOfSale.sites.size() == 1) {
                        this.site_list.addItem(PointOfSale.sites.get(0));
                    }
                    else {
                        this.site_list.addItem("");
                        for (int i = 0; i < PointOfSale.sites.size(); ++i) {
                            this.site_list.addItem(PointOfSale.sites.get(i));
                        }
                    }
                }
            }
            
            public void showDialog() {
                final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                final Dimension windowSize = this.getSize();
                this.setLocation(Math.max(0, (screenSize.width - windowSize.width) / 2), Math.max(0, (screenSize.height - windowSize.height) / 2));
                PointOfSale.this.setInitialFocusComponent(this, this.upc_field);
                this.setVisible(true);
            }
        }
        
        class CustomerSearchDialog extends JDialog
        {
            private static final long serialVersionUID = 201001L;
            JLabel error_msg;
            JLabel help_text;
            JLabel customer_id_entryfield_label;
            JTextField customer_id_entryfield;
            JLabel customer_scan_type_label;
            JComboBox customer_scan_type_list;
            JButton search_button;
            JButton cancel_button;
            
            CustomerSearchDialog(final Frame owner) {
                super(owner, "Set POS Customer", true);
                this.error_msg = new JLabel();
                this.help_text = new JLabel();
                this.customer_id_entryfield_label = new JLabel();
                this.customer_id_entryfield = new JTextField();
                this.customer_scan_type_label = new JLabel();
                this.customer_scan_type_list = new JComboBox();
                this.search_button = new JButton();
                this.cancel_button = new JButton();
                final Action enter_action = new AbstractAction() {
                    private static final long serialVersionUID = 201001L;
                    
                    public void actionPerformed(final ActionEvent evt) {
                        CustomerSearchDialog.this.search_buttonActionPerformed(evt);
                    }
                };
                final KeyStroke enter = KeyStroke.getKeyStroke(10, 0, false);
                this.getRootPane().getInputMap(2).put(enter, "ENTER");
                this.getRootPane().getActionMap().put("ENTER", enter_action);
                final Action escape_action = new AbstractAction() {
                    private static final long serialVersionUID = 201001L;
                    
                    public void actionPerformed(final ActionEvent evt) {
                        CustomerSearchDialog.this.setVisible(false);
                    }
                };
                final KeyStroke escape = KeyStroke.getKeyStroke(27, 0, false);
                this.getRootPane().getInputMap(2).put(escape, "ESCAPE");
                this.getRootPane().getActionMap().put("ESCAPE", escape_action);
                this.error_msg.setForeground(Color.red);
                this.help_text.setText("Swipe member card or manually enter ID number");
                this.customer_scan_type_label.setText("Input Type");
                this.customer_scan_type_list.setModel(new DefaultComboBoxModel());
                this.customer_scan_type_list.addActionListener(new ActionListener() {
                    public void actionPerformed(final ActionEvent evt) {
                        CustomerSearchDialog.this.customerScanType_listActionPerformed(evt);
                    }
                });
                this.customer_id_entryfield_label.setText("ID");
                this.search_button.setText("Search");
                this.search_button.addActionListener(new ActionListener() {
                    public void actionPerformed(final ActionEvent evt) {
                        CustomerSearchDialog.this.search_buttonActionPerformed(evt);
                    }
                });
                this.cancel_button.setText("Cancel");
                this.cancel_button.addActionListener(new ActionListener() {
                    public void actionPerformed(final ActionEvent evt) {
                        CustomerSearchDialog.this.setVisible(false);
                    }
                });
                final GroupLayout layout = new GroupLayout(this.getContentPane());
                this.getContentPane().setLayout(layout);
                layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(23, 23, 23).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.customer_scan_type_label).addComponent(this.customer_id_entryfield_label).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.search_button))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.customer_scan_type_list, 0, 180, 32767).addComponent(this.customer_id_entryfield)).addGap(111, 111, 111)).addGroup(layout.createSequentialGroup().addGap(148, 148, 148).addComponent(this.cancel_button).addContainerGap(88, 32767)).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.error_msg, -2, 400, -2).addContainerGap(10, 32767)).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.help_text, -2, 400, -2).addContainerGap(22, 32767)));
                layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(4, 4, 4).addComponent(this.error_msg).addGap(16, 16, 16).addGroup(layout.createSequentialGroup().addComponent(this.help_text).addGap(16, 16, 16).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.customer_scan_type_list, -2, -1, -2).addComponent(this.customer_scan_type_label)).addGap(16, 16, 16).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.customer_id_entryfield, -2, -1, -2).addComponent(this.customer_id_entryfield_label)).addGap(16, 16, 16).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.cancel_button).addComponent(this.search_button)).addContainerGap(25, 32767))));
                this.pack();
            }
            
            POSCustomerScanType selectedCustomerScanType() {
                final int index = this.customer_scan_type_list.getSelectedIndex();
                return PointOfSale.avail_customer_scan_types.get(index);
            }
            
            void customerScanType_listActionPerformed(final ActionEvent evt) {
                this.customer_id_entryfield.requestFocus();
            }
            
            void search_buttonActionPerformed(final ActionEvent evt) {
                final StringBuilder params = new StringBuilder();
                POSCustomerScanType scantype = null;
                String input_value = null;
                try {
                    scantype = this.selectedCustomerScanType();
                    if (scantype == null || scantype.scan_type_id == 0) {
                        this.error_msg.setText("Please select input type");
                        this.customer_scan_type_list.requestFocus();
                        return;
                    }
                    if (params.length() > 0) {
                        params.append("&");
                    }
                    params.append("scantype=").append(scantype.scan_type_id);
                    input_value = this.customer_id_entryfield.getText();
                    if (input_value.length() == 0) {
                        this.error_msg.setText("Please enter ID or swipe card now");
                        this.customer_id_entryfield.requestFocus();
                        return;
                    }
                    if (params.length() > 0) {
                        params.append("&");
                    }
                    params.append("search_id=").append(URLEncoder.encode(extractUPCCode(input_value), "UTF-8"));
                }
                catch (Exception e) {
                    this.error_msg.setText("Exception: " + e.getMessage());
                    return;
                }
                if (params.length() == 0) {
                    return;
                }
                if (PointOfSale.searching_customers) {
                    return;
                }
                this.error_msg.setText("");
                PointOfSale.searching_customers = true;
                this.search_button.setEnabled(false);
                this.cancel_button.setEnabled(false);
                final POSReceiptCustomer customer = PointOfSale.this.getCustomer(params.toString());
                this.search_button.setEnabled(true);
                this.cancel_button.setEnabled(true);
                PointOfSale.searching_customers = false;
                if (customer == null || customer.customer_id <= 0) {
                    this.error_msg.setText(scantype.scan_type_name + " " + input_value + " not found");
                }
                else if (customer.err != null && customer.err.length() > 0) {
                    this.error_msg.setText("<html>" + customer.err + "</html>");
                }
                else {
                    this.setVisible(false);
                    if (customer.credit_available == null) {
                        customer.credit_available = BigDecimal.ZERO;
                    }
                    PointOfSale.this.pos_receipt.receipt_customer = customer;
                    if (PointOfSale.this.pos_receipt.receipt_items != null) {
                        for (final POSReceiptItem ri : PointOfSale.this.pos_receipt.receipt_items) {
                            ri.customer = customer;
                            PointOfSale.this.pos_receipt.dirty = true;
                        }
                    }
                    PointOfSale.this.resetCustomerField(customer.credit_available);
                }
            }
            
            public void resetFields() {
                this.error_msg.setText("");
                this.customer_scan_type_list.removeAllItems();
                for (int i = 0; PointOfSale.avail_customer_scan_types != null && i < PointOfSale.avail_customer_scan_types.size(); ++i) {
                    this.customer_scan_type_list.addItem(PointOfSale.avail_customer_scan_types.get(i).scan_type_name);
                }
                this.customer_id_entryfield.setText("");
            }
            
            public void showDialog() {
                final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                final Dimension windowSize = this.getSize();
                this.setLocation(Math.max(0, (screenSize.width - windowSize.width) / 2), Math.max(0, (screenSize.height - windowSize.height) / 2));
                PointOfSale.this.setInitialFocusComponent(this, this.customer_id_entryfield);
                this.setVisible(true);
            }
        }
        
        class CustomerNotesDialog extends JDialog
        {
            private static final long serialVersionUID = 201001L;
            JTextArea entry_field;
            
            CustomerNotesDialog(final Frame owner) {
                super(owner, "Customer Notes", true);
                this.entry_field = null;
                final Container c = this.getContentPane();
                (this.entry_field = new JTextArea(null, "", 6, 10)).setLineWrap(true);
                this.entry_field.setWrapStyleWord(true);
                c.add(new JScrollPane(this.entry_field), "Center");
                final JPanel p = new JPanel();
                final JButton ok = new JButton("OK");
                ok.addActionListener(new ActionListener() {
                    public void actionPerformed(final ActionEvent ae) {
                        PointOfSale.this.customer_notes = CustomerNotesDialog.this.entry_field.getText();
                        CustomerNotesDialog.this.setVisible(false);
                        PointOfSale.this.handleProductButton(PointOfSale.this.customer_note_button);
                    }
                });
                final JButton cancel = new JButton("Cancel");
                cancel.addActionListener(new ActionListener() {
                    public void actionPerformed(final ActionEvent ae) {
                        PointOfSale.this.customer_notes = "";
                        CustomerNotesDialog.this.setVisible(false);
                        PointOfSale.this.handleProductButton(PointOfSale.this.customer_note_button);
                    }
                });
                p.add(ok);
                p.add(cancel);
                c.add(p, "South");
                this.setSize(300, 200);
            }
            
            public void showDialog() {
                final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                final Dimension windowSize = this.getSize();
                this.setLocation(Math.max(0, (screenSize.width - windowSize.width) / 2), Math.max(0, (screenSize.height - windowSize.height) / 2));
                this.entry_field.setText("");
                PointOfSale.this.setInitialFocusComponent(this, this.entry_field);
                this.setVisible(true);
            }
        }
    }
    
    private class ComponentAction extends ComponentAdapter
    {
        private static final long serialVersionUID = 201001L;
        
        public void componentResized(final ComponentEvent e) {
            final Component c = e.getComponent();
            final Dimension d = c.getSize();
            if (d.width == 0 || d.height == 0) {
                return;
            }
            final POSPanel p = (POSPanel)c;
            final Insets insets = p.getInsets();
            final int totalCellW = d.width / 9 - (insets.left + insets.right);
            final int totalCellH = d.height / 9 - (insets.top + insets.bottom);
            final int cellW = (d.width - 20) / 9;
            final int cellH = (d.height - 20) / 9;
            final Component[] components = p.getComponents();
            for (int i = 0; i < components.length; ++i) {
                if (components[i] instanceof POSButton) {
                    final POSButton b = (POSButton)components[i];
                    final int x = insets.left + totalCellW * (b.col - 1) + 2;
                    final int y = insets.top + totalCellH * (b.row - 1) + 2;
                    final int w = cellW * b.col_span - 2;
                    final int h = cellH * b.row_span - 2;
                    b.setBounds(x, y, w, h);
                }
            }
        }
    }
    
    class POSButton extends JButton
    {
        private static final long serialVersionUID = 201001L;
        int row;
        int row_span;
        int col;
        int col_span;
        POSKeyboardButton button;
        
        POSButton(final POSKeyboardButton button) {
            this.row = 0;
            this.row_span = 1;
            this.col = 0;
            this.col_span = 1;
            this.button = null;
            this.row = button.button_index / 10;
            this.col = button.button_index % 10;
            this.button = button;
            switch (button.button_type) {
                case 1: {
                    this.addActionListener(new ButtonTypeLink());
                    this.setToolTipText("Link to another button set");
                    break;
                }
                case 2: {
                    this.addActionListener(new ButtonTypeProduct());
                    this.setToolTipText(button.productToolTip(PointOfSale.this.secure_preface));
                    break;
                }
                case 3: {
                    this.addActionListener(new ButtonTypeDigit());
                    this.setToolTipText(POSKeyboardButton.digit_tool_tips[button.button_value]);
                    break;
                }
                case 4: {
                    this.addActionListener(new ButtonTypeFunction());
                    this.setToolTipText(POSKeyboardButton.function_tool_tips[button.button_value]);
                    break;
                }
            }
            this.setBackground(new Color(button.back_color));
            final StringBuilder label = new StringBuilder();
            label.append("<html><center style=\"");
            if (button.font_name.length() > 0) {
                label.append("font-family:").append(button.font_name).append(";");
            }
            if (button.fore_color != 0) {
                label.append("color:").append(PointOfSale.this.colorValue(button.fore_color)).append(";");
            }
            if (button.font_size != 0) {
                label.append("font-size:").append(button.font_size + (button.font_size + 2) / 3).append("pt;");
            }
            label.append("font-style:").append(button.font_italic ? "italic" : "normal").append(";");
            label.append("font-weight:").append(button.font_bold ? "bold" : "normal").append(";");
            label.append("\">").append(button.caption).append("</center>").append("</html>");
            this.setText(label.toString());
            this.setCursor(Cursor.getPredefinedCursor(12));
        }
    }
    
    class ReceiptTableModel extends AbstractTableModel
    {
        private static final long serialVersionUID = 201001L;
        
        public int getRowCount() {
            return PointOfSale.this.pos_receipt.receipt_items.size();
        }
        
        public int getColumnCount() {
            return 3;
        }
        
        public String getColumnName(final int col) {
            switch (col) {
                case 0: {
                    return "Qty";
                }
                case 1: {
                    return "Desc";
                }
                case 2: {
                    return "Price";
                }
                default: {
                    return "";
                }
            }
        }
        
        public boolean isCellEditable(final int row, final int col) {
            return false;
        }
        
        public Object getValueAt(final int row, final int col) {
            if (row >= PointOfSale.this.pos_receipt.receipt_items.size()) {
                return null;
            }
            final POSReceiptItem ri = PointOfSale.this.pos_receipt.receipt_items.get(row);
            switch (col) {
                case 0: {
                    return (ri.quantity == 0) ? "" : Integer.toString(ri.quantity);
                }
                case 1: {
                    return ri.description;
                }
                case 2: {
                    if (ri.isPayment()) {
                        return ri.payment_amount.negate();
                    }
                    if (ri.refund_summary == null) {
                        return ri.fee_summary.fee_portion;
                    }
                    return ri.fee_summary.fee_portion.add(ri.refund_summary.fee_portion);
                }
                default: {
                    return "";
                }
            }
        }
        
        public void rowInserted(final boolean display_message) {
            this.rowInserted(display_message, PointOfSale.this.pos_receipt.receipt_items.size() - 1);
        }
        
        public void rowInserted(final boolean display_message, int index) {
            if (index < 0) {
                index = PointOfSale.this.pos_receipt.receipt_items.size() - 1;
            }
            if (display_message) {
                final POSReceiptItem pos_ri = PointOfSale.this.pos_receipt.receipt_items.get(index);
                final BigDecimal amount = pos_ri.fee_summary.total();
                PointOfSale.this.displayPoleDisplayMessage(pos_ri.description, false, false, "Qty:" + pos_ri.quantity + "    $" + amount, false, false);
            }
            this.fireTableRowsInserted(index, index);
        }
        
        public void rowDeleted() {
            this.rowDeleted(PointOfSale.this.pos_receipt.receipt_items.size());
        }
        
        public void rowDeleted(final int index) {
            this.fireTableRowsDeleted(index, index);
            if (this.getRowCount() == 0) {
                for (int i = 0; i < PointOfSale.this.receipt_table.getColumnCount(); ++i) {
                    final TableColumn column = PointOfSale.this.receipt_table.getColumnModel().getColumn(i);
                    column.setPreferredWidth(PointOfSale.table_column_preferred_widths[i]);
                }
            }
        }
    }
    
    class PromptPanel extends JPanel implements ActionListener, FocusListener
    {
        private static final long serialVersionUID = 201001L;
        int prompt_type;
        JTextField amount_field;
        JTextField id_number_field;
        JTextField memo_number_field;
        JTextField check_number_field;
        JTextField card_number_field;
        JTextField exp_month_field;
        JTextField exp_year_field;
        JTextField qty_field;
        JComboBox payment_type_list;
        JTextField payment_type_field;
        JTextField authoriztion_field;
        JLabel receipt_number_field;
        JLabel change_due_field;
        JLabel error_field;
        private JTextField current_field;
        
        public PromptPanel(final int prompt_type) {
            this.prompt_type = -1;
            this.amount_field = null;
            this.id_number_field = null;
            this.memo_number_field = null;
            this.check_number_field = null;
            this.card_number_field = null;
            this.exp_month_field = null;
            this.exp_year_field = null;
            this.qty_field = null;
            this.payment_type_list = null;
            this.payment_type_field = null;
            this.authoriztion_field = null;
            this.receipt_number_field = null;
            this.change_due_field = null;
            this.error_field = null;
            this.current_field = null;
            this.prompt_type = prompt_type;
            this.setLayout(new GridBagLayout());
            this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), PointOfSale.titles[prompt_type]));
            final GridBagConstraints constraints = new GridBagConstraints();
            constraints.insets = new Insets(2, 2, 2, 2);
            OKAction ok_action = null;
            CancelAction cancel_action = null;
            if (prompt_type != 5 && prompt_type != 8) {
                ok_action = new OKAction();
                cancel_action = new CancelAction();
            }
            int next_row = 0;
            (this.error_field = new JLabel()).setForeground(Color.red);
            PointOfSale.add(this, this.error_field, constraints, 0, next_row++, 2, 1, 2, 13, 100, 0);
            switch (prompt_type) {
                case 0:
                case 10: {
                    this.amount_field = this.nextField("Amount", next_row++, ok_action, cancel_action, constraints);
                    break;
                }
                case 9: {
                    (this.amount_field = this.nextField("Amount", next_row++, ok_action, cancel_action, constraints)).setEditable(false);
                    break;
                }
                case 4: {
                    this.amount_field = this.nextField("Amount", next_row++, ok_action, cancel_action, constraints);
                    this.qty_field = this.nextField("Qty", next_row++, ok_action, cancel_action, constraints);
                    break;
                }
                case 6: {
                    this.amount_field = this.nextField("Discount", next_row++, ok_action, cancel_action, constraints);
                    break;
                }
                case 7: {
                    this.amount_field = this.nextField("Percentage", next_row++, ok_action, cancel_action, constraints);
                    break;
                }
                case 5: {
                    this.qty_field = this.nextField("Qty", next_row++, ok_action, cancel_action, constraints);
                    break;
                }
                case 2: {
                    this.amount_field = this.nextField("Amount", next_row++, ok_action, cancel_action, constraints);
                    this.check_number_field = this.nextField("Check #", next_row++, ok_action, cancel_action, constraints);
                    this.id_number_field = this.nextField("ID #", next_row++, ok_action, cancel_action, constraints);
                    break;
                }
                case 3: {
                    this.amount_field = this.nextField("Amount", next_row++, ok_action, cancel_action, constraints);
                    this.memo_number_field = this.nextField("Memo #", next_row++, ok_action, cancel_action, constraints);
                    this.id_number_field = this.nextField("ID #", next_row++, ok_action, cancel_action, constraints);
                    break;
                }
                case 1: {
                    this.amount_field = this.nextField("Amount", next_row++, ok_action, cancel_action, constraints);
                    this.card_number_field = this.nextField("Card Number", next_row++, ok_action, cancel_action, constraints);
                    this.exp_month_field = this.nextField("Exp Month", next_row++, ok_action, cancel_action, constraints);
                    this.exp_year_field = this.nextField("Exp Year", next_row++, ok_action, cancel_action, constraints);
                    this.id_number_field = this.nextField("ID #", next_row++, ok_action, cancel_action, constraints);
                    break;
                }
                case 8: {
                    this.receipt_number_field = this.nextStaticField("Receipt #", next_row++, constraints);
                    this.change_due_field = this.nextStaticField("Change Due", next_row++, constraints);
                    final JButton show_button = new JButton("Show Long Form Receipt");
                    show_button.addActionListener(this);
                    show_button.setActionCommand("show");
                    PointOfSale.add(this, show_button, constraints, 0, next_row++, 2, 1, 0, 10, 100, 100);
                    final JButton print_button = new JButton("Print POS Receipt");
                    print_button.addActionListener(this);
                    print_button.setActionCommand("print");
                    PointOfSale.add(this, print_button, constraints, 0, next_row++, 2, 1, 0, 10, 100, 100);
                    break;
                }
                case 11: {
                    this.amount_field = this.nextField("Amount", next_row++, ok_action, cancel_action, constraints);
                    (this.payment_type_list = this.nextList("Payment Type", PointOfSale.other_payment_types.toArray(), next_row++, ok_action, cancel_action, constraints)).setSelectedIndex(-1);
                    this.payment_type_field = this.nextField("Payment Type #", next_row++, ok_action, cancel_action, constraints);
                    this.authoriztion_field = this.nextField("Authoriztion #", next_row++, ok_action, cancel_action, constraints);
                    break;
                }
            }
            if (prompt_type != 5 && prompt_type != 8) {
                final JButton ok_button = new JButton("OK");
                ok_button.addActionListener(this);
                ok_button.setActionCommand("ok");
                PointOfSale.add(this, ok_button, constraints, 0, next_row, 1, 1, 1, 17, 100, 100);
                final JButton cancel_button = new JButton("Cancel");
                cancel_button.addActionListener(this);
                cancel_button.setActionCommand("cancel");
                PointOfSale.add(this, cancel_button, constraints, 1, next_row++, 1, 1, 1, 17, 100, 100);
            }
        }
        
        private JTextField nextField(final String label, final int row, final OKAction ok_action, final CancelAction cancel_action, final GridBagConstraints constraints) {
            final JLabel l = new JLabel(label, 4);
            PointOfSale.add(this, l, constraints, 0, row, 1, 1, 2, 12, 0, 0);
            final JTextField field = new JTextField("");
            field.addFocusListener(this);
            if (ok_action != null) {
                field.getInputMap().put(KeyStroke.getKeyStroke(10, 0), "ok");
                field.getActionMap().put("ok", ok_action);
            }
            if (cancel_action != null) {
                field.getInputMap().put(KeyStroke.getKeyStroke(27, 0), "cancel");
                field.getActionMap().put("cancel", cancel_action);
            }
            PointOfSale.add(this, field, constraints, 1, row, 1, 1, 2, 18, 100, 0);
            return field;
        }
        
        private JComboBox nextList(final String label, final Object[] options, final int row, final OKAction ok_action, final CancelAction cancel_action, final GridBagConstraints constraints) {
            final JLabel l = new JLabel(label, 4);
            PointOfSale.add(this, l, constraints, 0, row, 1, 1, 2, 12, 0, 0);
            final JComboBox field = new JComboBox((E[])options);
            field.addFocusListener(this);
            if (ok_action != null) {
                field.getInputMap().put(KeyStroke.getKeyStroke(10, 0), "ok");
                field.getActionMap().put("ok", ok_action);
            }
            if (cancel_action != null) {
                field.getInputMap().put(KeyStroke.getKeyStroke(27, 0), "cancel");
                field.getActionMap().put("cancel", cancel_action);
            }
            PointOfSale.add(this, field, constraints, 1, row, 1, 1, 2, 18, 100, 0);
            return field;
        }
        
        private JLabel nextStaticField(final String label, final int row, final GridBagConstraints constraints) {
            final JLabel l = new JLabel(label, 4);
            PointOfSale.add(this, l, constraints, 0, row, 1, 1, 2, 12, 0, 0);
            final JLabel field = new JLabel("");
            PointOfSale.add(this, field, constraints, 1, row, 1, 1, 2, 18, 100, 0);
            return field;
        }
        
        public void showReceiptFinished() {
            PointOfSale.this.hidePrompt();
            this.receipt_number_field.setText(PointOfSale.this.receipt_number);
            this.change_due_field.setText(NumberFormat.getCurrencyInstance().format(PointOfSale.this.last_change_due));
            this.setVisible(true);
            PointOfSale.this.receipt_area.validate();
            PointOfSale.this.current_prompt = this;
        }
        
        public void clickOk() {
            final BigDecimal amount_tendered = PointOfSale.this.getDecimal(this.amount_field.getText(), 2);
            this.error_field.setText("");
            POSReceiptItem ri = null;
            switch (this.prompt_type) {
                case 0: {
                    ri = POSReceiptItem.cashPayment(PointOfSale.this.pos_receipt.receipt_customer, amount_tendered);
                    if (PointOfSale.this.enable_pole_display) {
                        PointOfSale.this.displayPoleDisplayMessage("Payment", false, false, "$" + amount_tendered, false, false);
                        break;
                    }
                    break;
                }
                case 9: {
                    ri = POSReceiptItem.cashRefund(PointOfSale.this.pos_receipt.receipt_customer, amount_tendered.negate());
                    if (PointOfSale.this.enable_pole_display) {
                        PointOfSale.this.displayPoleDisplayMessage("Payment", false, false, "$" + amount_tendered.negate(), false, false);
                        break;
                    }
                    break;
                }
                case 2: {
                    final String check_number = this.validateCheckNumber();
                    if (check_number == null) {
                        PointOfSale.beep();
                        return;
                    }
                    ri = POSReceiptItem.checkPayment(PointOfSale.this.pos_receipt.receipt_customer, amount_tendered, this.check_number_field.getText(), this.id_number_field.getText());
                    if (PointOfSale.this.enable_pole_display) {
                        PointOfSale.this.displayPoleDisplayMessage("Payment", false, false, "$" + amount_tendered, false, false);
                        break;
                    }
                    break;
                }
                case 3: {
                    ri = POSReceiptItem.memoPayment(PointOfSale.this.pos_receipt.receipt_customer, amount_tendered, this.memo_number_field.getText(), this.id_number_field.getText());
                    if (PointOfSale.this.enable_pole_display) {
                        PointOfSale.this.displayPoleDisplayMessage("Payment", false, false, "$" + amount_tendered, false, false);
                        break;
                    }
                    break;
                }
                case 1: {
                    final String expiration = this.validateExpiration();
                    if (expiration == null) {
                        PointOfSale.beep();
                        return;
                    }
                    final String card_number = this.validateCardNumber();
                    if (card_number == null) {
                        PointOfSale.beep();
                        return;
                    }
                    this.id_number_field.setText(this.id_number_field.getText().replace(card_number, ""));
                    ri = POSReceiptItem.cardPayment(PointOfSale.this.pos_receipt.receipt_customer, amount_tendered, card_number, this.id_number_field.getText(), expiration);
                    if (PointOfSale.this.enable_pole_display) {
                        PointOfSale.this.displayPoleDisplayMessage("Payment", false, false, "$" + amount_tendered, false, false);
                        break;
                    }
                    break;
                }
                case 11: {
                    final POSOtherPaymentType c = (POSOtherPaymentType)this.payment_type_list.getSelectedItem();
                    ri = POSReceiptItem.otherPayment(PointOfSale.this.pos_receipt.receipt_customer, amount_tendered, this.payment_type_field.getText(), c.payment_type_id, this.authoriztion_field.getText(), c.payment_type);
                    if (PointOfSale.this.enable_pole_display) {
                        PointOfSale.this.displayPoleDisplayMessage("Payment", false, false, "$" + amount_tendered, false, false);
                        break;
                    }
                    break;
                }
                case 4:
                case 6:
                case 7: {
                    int qty = 1;
                    if (this.qty_field != null && this.qty_field.getText().length() > 0) {
                        qty = PointOfSale.this.getInt(this.qty_field.getText());
                        if (qty <= 0) {
                            this.error_field.setText("Quantity must be positive.");
                            return;
                        }
                    }
                    BigDecimal price = PointOfSale.this.price_change_button.percentage_discount ? PointOfSale.this.price_change_button.discount_percent : PointOfSale.this.price_change_button.fee_amount;
                    if (this.amount_field.getText().length() > 0) {
                        price = PointOfSale.this.getDecimal(this.amount_field.getText(), 2);
                        if (price.signum() < 0) {
                            this.error_field.setText("Price must be zero/positive.");
                            return;
                        }
                    }
                    if (PointOfSale.this.price_change_button.product_option == 1) {
                        final POSReceipt pr = new POSReceipt(PointOfSale.this.default_customer);
                        POSReceiptItem item = POSReceiptItem.newProductSale(qty, price, PointOfSale.this.price_change_button, PointOfSale.this.tax_rates, pr.receipt_customer, PointOfSale.this.customer_notes);
                        pr.addEntry(item, PointOfSale.this.tax_rates);
                        BigDecimal total = item.fee_summary.total();
                        total = total.subtract(PointOfSale.this.addMembershipDiscounts(pr, item, item.fee_summary.total(), qty, this.prompt_type == 9));
                        if (PointOfSale.this.price_change_button.linking_products != null && PointOfSale.this.price_change_button.linking_products.size() > 0) {
                            for (final POSKeyboardButton linking_pr : PointOfSale.this.price_change_button.linking_products) {
                                BigDecimal linking_amount = linking_pr.fee_amount;
                                if (!PointOfSale.this.price_change_button.apply_individual_fee) {
                                    linking_amount = BigDecimal.ZERO;
                                }
                                final int linking_qty = qty * linking_pr.default_qty;
                                final POSReceiptItem linking_item = POSReceiptItem.newProductSale(linking_qty, linking_amount, linking_pr, PointOfSale.this.tax_rates, PointOfSale.this.pos_receipt.receipt_customer, PointOfSale.this.customer_notes);
                                linking_item.parent_product_id = PointOfSale.this.price_change_button.button_value;
                                linking_item.product_group_uid = item.product_group_uid;
                                pr.addEntry(linking_item, PointOfSale.this.tax_rates);
                                total = total.add(linking_item.fee_summary.total());
                                total = total.subtract(PointOfSale.this.addMembershipDiscounts(pr, linking_item, linking_item.fee_summary.total(), linking_qty, this.prompt_type == 9));
                            }
                        }
                        item = POSReceiptItem.cashPayment(pr.receipt_customer, total);
                        pr.addEntry(item);
                        PointOfSale.this.tenderReceipt(pr, 0);
                        PointOfSale.this.receipt_finished_prompt.showReceiptFinished();
                        PointOfSale.this.price_change_pending = false;
                        PointOfSale.this.price_change_button = null;
                        return;
                    }
                    final POSReceiptItem item2 = POSReceiptItem.newProductSale(qty, PointOfSale.this.refund_mode ? price.negate() : price, PointOfSale.this.price_change_button, PointOfSale.this.tax_rates, PointOfSale.this.pos_receipt.receipt_customer, PointOfSale.this.customer_notes);
                    if (PointOfSale.this.refund_mode && PointOfSale.this.price_change_button.track_inventory) {
                        if (PointOfSale.this.refund_inventory_qty) {
                            item2.refund_to_inventory_qty = qty;
                        }
                        else {
                            item2.refund_to_inventory_qty = 0;
                        }
                    }
                    PointOfSale.this.pos_receipt.addEntry(item2, PointOfSale.this.tax_rates);
                    PointOfSale.this.receipt_table_model.rowInserted(PointOfSale.this.enable_pole_display);
                    PointOfSale.this.addMembershipDiscounts(PointOfSale.this.pos_receipt, item2, price, qty, PointOfSale.this.refund_mode);
                    if (PointOfSale.this.price_change_button.linking_products != null && PointOfSale.this.price_change_button.linking_products.size() > 0) {
                        for (final POSKeyboardButton linking_pr2 : PointOfSale.this.price_change_button.linking_products) {
                            BigDecimal linking_amount2 = linking_pr2.fee_amount;
                            if (!PointOfSale.this.price_change_button.apply_individual_fee) {
                                linking_amount2 = BigDecimal.ZERO;
                            }
                            if (PointOfSale.this.refund_mode) {
                                linking_amount2 = linking_amount2.negate();
                            }
                            final int linking_qty2 = qty * linking_pr2.default_qty;
                            final POSReceiptItem linking_item2 = POSReceiptItem.newProductSale(linking_qty2, linking_amount2, linking_pr2, PointOfSale.this.tax_rates, PointOfSale.this.pos_receipt.receipt_customer, PointOfSale.this.customer_notes);
                            linking_item2.parent_product_id = PointOfSale.this.price_change_button.button_value;
                            linking_item2.product_group_uid = item2.product_group_uid;
                            if (PointOfSale.this.refund_mode && linking_pr2.track_inventory) {
                                if (PointOfSale.this.refund_inventory_qty) {
                                    linking_item2.refund_to_inventory_qty = linking_qty2;
                                }
                                else {
                                    linking_item2.refund_to_inventory_qty = 0;
                                }
                            }
                            PointOfSale.this.pos_receipt.addEntry(linking_item2, PointOfSale.this.tax_rates);
                            PointOfSale.this.receipt_table_model.rowInserted(false);
                            PointOfSale.this.addMembershipDiscounts(PointOfSale.this.pos_receipt, linking_item2, linking_item2.fee_summary.total(), linking_qty2, PointOfSale.this.refund_mode);
                        }
                    }
                    PointOfSale.this.price_change_pending = false;
                    PointOfSale.this.price_change_button = null;
                    PointOfSale.this.refund_mode = false;
                    break;
                }
                case 10: {
                    if (amount_tendered.signum() <= 0) {
                        break;
                    }
                    final BigDecimal balance = PointOfSale.this.getBalance();
                    PointOfSale.this.resetCustomerField(balance);
                    if (balance.signum() <= 0) {
                        this.error_field.setText("No credit available.");
                        return;
                    }
                    if (balance.compareTo(amount_tendered) < 0) {
                        this.error_field.setText("The credit available is " + NumberFormat.getCurrencyInstance().format(balance) + ".");
                        return;
                    }
                    ri = POSReceiptItem.fromAccountPayment(PointOfSale.this.pos_receipt.receipt_customer, amount_tendered);
                    PointOfSale.this.resetCustomerField(balance.subtract(amount_tendered));
                    break;
                }
            }
            if (ri != null) {
                PointOfSale.this.pos_receipt.addEntry(ri);
            }
            PointOfSale.this.hidePrompt();
            PointOfSale.this.showTotals();
        }
        
        private String validateCardNumber() {
            final String cc = this.card_number_field.getText();
            if (cc.length() == 0) {
                this.error_field.setText("Missing card number");
                return null;
            }
            if (POSReceiptItem.enrouteCard(cc)) {
                return cc;
            }
            if (!this.validateChecksum(cc)) {
                this.error_field.setText("Invalid card number");
                return null;
            }
            if (POSReceiptItem.masterCard(cc) && PointOfSale.this.cc_mc) {
                return cc;
            }
            if (POSReceiptItem.visaCard(cc) && PointOfSale.this.cc_visa) {
                return cc;
            }
            if (POSReceiptItem.amexCard(cc) && PointOfSale.this.cc_amex) {
                return cc;
            }
            if (POSReceiptItem.dinersCard(cc) && PointOfSale.this.cc_diners) {
                return cc;
            }
            if (POSReceiptItem.discoverCard(cc) && PointOfSale.this.cc_discover) {
                return cc;
            }
            if (POSReceiptItem.jcbCard(cc) && PointOfSale.this.cc_jcb) {
                return cc;
            }
            this.error_field.setText("Do not recognize card type");
            return null;
        }
        
        private boolean validateChecksum(final String cc) {
            int check_sum = 0;
            boolean odd_toggle = false;
            final int length = cc.length();
            for (int i = length - 1; i >= 0; --i, odd_toggle = !odd_toggle) {
                final int digit = Character.digit(cc.charAt(i), 10);
                if (odd_toggle) {
                    if (digit * 2 > 9) {
                        check_sum += 1 + digit * 2 % 10;
                    }
                    else {
                        check_sum += digit * 2;
                    }
                }
                else {
                    check_sum += digit;
                }
            }
            return check_sum % 10 == 0;
        }
        
        private String validateCheckNumber() {
            final String s = this.check_number_field.getText();
            if (s == null || s.length() == 0) {
                this.error_field.setText("Missing check number");
                return null;
            }
            return s;
        }
        
        private String validateExpiration() {
            final int cc_exp_month = PointOfSale.this.getInt(this.exp_month_field.getText());
            if (cc_exp_month == 0) {
                this.error_field.setText("Missing expiration month");
                return null;
            }
            int cc_exp_year = PointOfSale.this.getInt(this.exp_year_field.getText());
            if (cc_exp_year == 0) {
                this.error_field.setText("Missing expiration year");
                return null;
            }
            final Calendar cal = Calendar.getInstance();
            try {
                cal.setTime(DateFormat.getDateInstance(3, Locale.US).parse(cc_exp_month + "/1/" + ((cc_exp_year < 10) ? ("0" + cc_exp_year) : cc_exp_year)));
            }
            catch (ParseException e) {
                this.error_field.setText("Invalid expiration");
                return null;
            }
            cc_exp_year = cal.get(1);
            cal.add(2, 1);
            if (!cal.getTime().after(new Date())) {
                this.error_field.setText("Card is expired: " + DateFormat.getDateInstance(2).format(cal.getTime()));
                return null;
            }
            String s = Integer.toString(cc_exp_month);
            if (s.length() == 1) {
                s = "0" + s;
            }
            return s + "/" + Integer.toString(cc_exp_year);
        }
        
        public void actionPerformed(final ActionEvent ae) {
            if (ae.getActionCommand().equals("ok")) {
                this.clickOk();
            }
            else if (ae.getActionCommand().equals("cancel")) {
                PointOfSale.this.price_change_pending = false;
                PointOfSale.this.hidePrompt();
            }
            else if (ae.getActionCommand().equals("show")) {
                if (PointOfSale.this.receipt_header_id == 0) {
                    PointOfSale.beep();
                }
                else {
                    try {
                        final String function = URLEncoder.encode("showReceipt.sdi?receiptheader_id=" + PointOfSale.this.receipt_header_id + "&from_pos=1&topmost_menu_id=" + PointOfSale.this.topmost_menu_id + "&menu_id=" + PointOfSale.this.topmost_menu_id, "UTF-8");
                        if (PointOfSale.this.tabbed_admin_ui) {
                            PointOfSale.this.getAppletContext().showDocument(new URL(PointOfSale.this.secure_preface + "/updateTabContext.sdi?initial_function=" + function + "&topmost_menu_id=" + PointOfSale.this.topmost_menu_id + "&menu_id=" + PointOfSale.this.topmost_menu_id), "_self");
                        }
                        else {
                            PointOfSale.this.getAppletContext().showDocument(new URL(PointOfSale.this.secure_preface + "/adminFrames.sdi?initial_function=" + function), "_self");
                        }
                    }
                    catch (Exception e) {
                        PointOfSale.beep();
                    }
                }
            }
            else if (ae.getActionCommand().equals("print")) {
                if (PointOfSale.this.receipt_header_id > 0) {
                    PointOfSale.this.printSavedReceipt(PointOfSale.this.receipt_header_id);
                }
                else {
                    PointOfSale.beep();
                }
            }
        }
        
        public void focusLost(final FocusEvent fe) {
        }
        
        public void focusGained(final FocusEvent fe) {
            final Component c = fe.getComponent();
            if (c instanceof JTextField) {
                this.current_field = (JTextField)c;
            }
        }
        
        public JTextField currentField() {
            return (this.current_field == null) ? ((this.amount_field == null) ? this.qty_field : this.amount_field) : this.current_field;
        }
        
        public int currentFieldType() {
            return (this.current_field != null && this.current_field == this.amount_field) ? 1 : 0;
        }
        
        public void setInitialFocus(final BigDecimal amount) {
            this.setInitialFocus(amount, 1);
        }
        
        public void setInitialFocus(final BigDecimal amount, final int qty) {
            this.error_field.setText("");
            if (this.id_number_field != null) {
                this.id_number_field.setText("");
            }
            JTextField focus_field = null;
            switch (this.prompt_type) {
                case 0:
                case 9: {
                    this.amount_field.setText(amount.toString());
                    focus_field = this.amount_field;
                    break;
                }
                case 10: {
                    final BigDecimal balance = PointOfSale.this.getBalance();
                    PointOfSale.this.resetCustomerField(balance);
                    this.amount_field.setText(((balance.compareTo(amount) < 0) ? balance : amount).toString());
                    focus_field = this.amount_field;
                    break;
                }
                case 2: {
                    this.amount_field.setText(amount.toString());
                    focus_field = this.check_number_field;
                    this.check_number_field.setText("");
                    break;
                }
                case 11: {
                    this.amount_field.setText(amount.toString());
                    focus_field = this.payment_type_field;
                    this.payment_type_field.setText("");
                    break;
                }
                case 3: {
                    this.amount_field.setText(amount.toString());
                    focus_field = this.memo_number_field;
                    this.memo_number_field.setText("");
                    break;
                }
                case 1: {
                    this.amount_field.setText(amount.toString());
                    this.exp_month_field.setText("");
                    this.exp_year_field.setText("");
                    this.card_number_field.setText("");
                    focus_field = this.card_number_field;
                    final String initial_value = "Card Number";
                    final Object o = JOptionPane.showInputDialog(null, "Swipe Card Now or Enter Card Number", "Card Number", 3, null, null, initial_value);
                    if (o == null || !(o instanceof String) || o.equals(initial_value)) {
                        break;
                    }
                    this.parseEntry((String)o);
                    if (this.card_number_field.getText().length() <= 0) {
                        break;
                    }
                    if (this.exp_month_field.getText().length() == 0) {
                        focus_field = this.exp_month_field;
                        break;
                    }
                    focus_field = this.id_number_field;
                    break;
                }
                case 4: {
                    this.amount_field.setText(amount.toString());
                    focus_field = this.amount_field;
                    this.qty_field.setText(Integer.toString(qty));
                    break;
                }
                case 6:
                case 7: {
                    this.amount_field.setText(amount.toString());
                    focus_field = this.amount_field;
                    break;
                }
                case 5: {
                    this.qty_field.setText("");
                    focus_field = this.qty_field;
                    break;
                }
            }
            this.setVisible(true);
            PointOfSale.this.receipt_area.validate();
            if (focus_field != null) {
                focus_field.requestFocusInWindow();
                focus_field.selectAll();
            }
        }
        
        private void parseEntry(final String s) {
            if (s.indexOf("^") >= 0) {
                final StringTokenizer st = new StringTokenizer(s, "^");
                String card_number = "";
                if (st.hasMoreTokens()) {
                    for (card_number = st.nextToken(); card_number.length() > 0 && !Character.isDigit(card_number.charAt(0)); card_number = card_number.substring(1)) {}
                    this.card_number_field.setText(card_number);
                }
                if (st.hasMoreTokens()) {
                    final String x = st.nextToken().trim();
                    x.replace(card_number, "");
                    this.id_number_field.setText(x);
                }
                if (st.hasMoreTokens()) {
                    final String x = st.nextToken();
                    if (x.length() >= 2) {
                        this.exp_year_field.setText("20" + x.substring(0, 2));
                    }
                    if (x.length() >= 4) {
                        this.exp_month_field.setText(x.substring(2, 4));
                    }
                }
                return;
            }
            if (s.indexOf("=") >= 0) {
                final StringTokenizer st = new StringTokenizer(s, "=");
                if (st.hasMoreTokens()) {
                    String x2;
                    for (x2 = st.nextToken(); x2.length() > 0 && !Character.isDigit(x2.charAt(0)); x2 = x2.substring(1)) {}
                    this.card_number_field.setText(x2);
                }
                if (st.hasMoreTokens()) {
                    String x2 = st.nextToken();
                    if (st.hasMoreTokens()) {
                        x2 = st.nextToken();
                    }
                    if (x2.length() >= 2) {
                        this.exp_year_field.setText("20" + x2.substring(0, 2));
                    }
                    if (x2.length() >= 4) {
                        this.exp_month_field.setText(x2.substring(2, 4));
                    }
                }
                return;
            }
            this.card_number_field.setText(s);
        }
        
        void addCurrency(final BigDecimal value) {
            if (this.currentFieldType() != 1) {
                PointOfSale.beep();
                return;
            }
            this.currentField().replaceSelection("");
            final String text = this.currentField().getText();
            try {
                final BigDecimal bd = (text.length() > 0) ? new BigDecimal(text) : BigDecimal.valueOf(0L);
                this.currentField().setText(bd.add(value).setScale(2, 4).toString());
            }
            catch (NumberFormatException e) {
                PointOfSale.beep();
            }
        }
        
        void addDecimal() {
            if (this.currentFieldType() != 1) {
                PointOfSale.beep();
                return;
            }
            this.currentField().replaceSelection("");
            final String text = this.currentField().getText();
            if (text.indexOf(".") >= 0) {
                PointOfSale.beep();
                return;
            }
            this.currentField().setText(text + ".");
        }
        
        void insertDigit(final String digit) {
            this.currentField().replaceSelection("");
            String text = this.currentField().getText();
            if (this.currentFieldType() == 0) {
                text += digit;
                if (text.length() > 9) {
                    text = text.substring(text.length() - 9);
                }
                this.currentField().setText(text);
            }
            else if (this.currentFieldType() == 1) {
                if (text.length() == 1 && text.equals(".")) {
                    this.currentField().setText(text + digit);
                    return;
                }
                try {
                    final BigDecimal bd = (text.length() > 0) ? new BigDecimal(text) : BigDecimal.valueOf(0L);
                    if (bd.scale() > 0 && bd.scale() + digit.length() > 2) {
                        PointOfSale.beep();
                        return;
                    }
                    this.currentField().setText(text + digit);
                }
                catch (NumberFormatException e) {
                    PointOfSale.beep();
                }
            }
            else {
                PointOfSale.beep();
            }
        }
        
        void backspace() {
            this.currentField().replaceSelection("");
            final String text = this.currentField().getText();
            if (text.length() > 0) {
                if (this == PointOfSale.this.quantity_prompt && text.length() == 1) {
                    PointOfSale.this.hidePrompt();
                }
                else {
                    this.currentField().setText(text.substring(0, text.length() - 1));
                }
            }
        }
        
        private class OKAction extends AbstractAction
        {
            private static final long serialVersionUID = 201001L;
            
            public void actionPerformed(final ActionEvent ae) {
                PromptPanel.this.clickOk();
            }
        }
        
        private class CancelAction extends AbstractAction
        {
            private static final long serialVersionUID = 201001L;
            
            public void actionPerformed(final ActionEvent ae) {
                PointOfSale.this.price_change_pending = false;
                PointOfSale.this.hidePrompt();
            }
        }
    }
    
    class POSPrinter
    {
        PrintStream out;
        int line_width;
        int current_font;
        Class<?> comm_port_identifier_class;
        Class<?> serial_port_class;
        Object serial_port;
        
        POSPrinter(final int com_port) {
            this.out = null;
            this.line_width = 0;
            this.current_font = -1;
            this.comm_port_identifier_class = null;
            this.serial_port_class = null;
            this.serial_port = null;
            try {
                this.comm_port_identifier_class = Class.forName("javax.comm.CommPortIdentifier");
                this.serial_port_class = Class.forName("javax.comm.SerialPort");
                final Object[] args1 = { "COM" + com_port };
                final Class[] proto_args1 = { Class.forName("java.lang.String") };
                final Object port_id = this.comm_port_identifier_class.getMethod("getPortIdentifier", (Class<?>[])proto_args1).invoke(null, args1);
                if (port_id == null) {
                    System.err.println("Cannot find requested COM port");
                    return;
                }
                final Object[] args2 = { "POSPrint", new Integer(2000) };
                final Class[] proto_args2 = { Class.forName("java.lang.String"), Integer.TYPE };
                this.serial_port = this.comm_port_identifier_class.getMethod("open", (Class<?>[])proto_args2).invoke(port_id, args2);
                if (this.serial_port == null) {
                    System.err.println("Cannot open requested COM port");
                    return;
                }
                final OutputStream os = (OutputStream)this.serial_port_class.getMethod("getOutputStream", (Class<?>[])null).invoke(this.serial_port, (Object[])null);
                this.out = new PrintStream(os);
            }
            catch (Exception e) {
                if (e instanceof InvocationTargetException) {
                    System.err.println(((InvocationTargetException)e).getTargetException().toString());
                }
                else {
                    System.err.println(e.toString());
                }
            }
        }
        
        private void close() {
            try {
                this.out.flush();
                if (this.out != System.err) {
                    this.out.close();
                }
                this.serial_port_class.getMethod("close", (Class<?>[])null).invoke(this.serial_port, (Object[])null);
            }
            catch (Exception e) {
                System.err.println("Exception in close: " + e.toString());
            }
        }
        
        private void printCenter(final String s) {
            if (s == null || s.length() == 0) {
                return;
            }
            try {
                final int indent = (this.line_width - s.length()) / 2;
                this.out.println(this.spaces(indent) + s);
            }
            catch (Exception e) {
                System.err.println("Exception in printCenter: " + e.toString());
            }
        }
        
        private void printRight(final String s) {
            if (s == null || s.length() == 0) {
                return;
            }
            try {
                final int indent = this.line_width - s.length();
                this.out.println(this.spaces(indent) + s);
            }
            catch (Exception e) {
                System.err.println("Exception in printRight: " + e.toString());
            }
        }
        
        private void print(final String s) {
            if (s == null || s.length() == 0) {
                return;
            }
            try {
                this.out.print(s);
            }
            catch (Exception e) {
                System.err.println("Exception in print: " + e.toString());
            }
        }
        
        private void printSignatureLine() {
            this.blankLines(2);
            try {
                final StringBuilder sb = new StringBuilder("X");
                int count = this.line_width - 1;
                while (--count >= 0) {
                    sb.append("-");
                }
                this.printLine(sb.toString());
            }
            catch (Exception e) {
                System.err.println("Exception in printSignatureLine: " + e.toString());
            }
        }
        
        private void printDashedLine() {
            this.blankLines(2);
            try {
                final StringBuilder sb = new StringBuilder();
                int count = this.line_width - 1;
                while (--count >= 0) {
                    sb.append("-");
                }
                this.printLine(sb.toString());
            }
            catch (Exception e) {
                System.err.println("Exception in printDashedLine: " + e.toString());
            }
        }
        
        private void printRepeatedChar(final String s) {
            try {
                final StringBuilder sb = new StringBuilder();
                int count = this.line_width - 1;
                while (--count >= 0) {
                    sb.append(s);
                }
                this.printLine(sb.toString());
            }
            catch (Exception e) {
                System.err.println("Exception in printRepeatedChar: " + e.toString());
            }
        }
        
        private void printIndentedLine(final int indent, final String s) {
            if (s == null || s.trim().length() == 0) {
                return;
            }
            try {
                this.printLine(this.spaces(indent) + s);
            }
            catch (Exception e) {
                System.err.println("Exception in printIndentedLine: " + e.toString());
            }
        }
        
        private void printNotes(final int indent, String s) {
            if (s == null || s.length() == 0) {
                return;
            }
            this.blankLines(1);
            try {
                int index;
                while ((index = s.indexOf("<br>")) >= 0) {
                    final String part = s.substring(0, index);
                    s = s.substring(index + 4);
                    this.printWrapped(indent, part);
                }
                if (s.length() > 0) {
                    this.printWrapped(indent, s);
                }
            }
            catch (Exception e) {
                System.err.println("Exception in printNotes: " + e.toString());
            }
        }
        
        private void printWrapped(final int indent, final String s) {
            if (s.length() == 0) {
                this.out.println();
                return;
            }
            final String[] splits = s.split("\\s");
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < splits.length; ++i) {
                if (sb.length() == 0) {
                    sb.append(this.spaces(indent)).append(splits[i]);
                }
                else if (sb.length() + 1 + splits[i].length() > this.line_width) {
                    this.out.println(sb.toString());
                    sb.setLength(0);
                    sb.append(this.spaces(indent)).append(splits[i]);
                }
                else {
                    sb.append(" ").append(splits[i]);
                }
            }
            if (sb.length() > 0) {
                this.out.println(sb.toString());
            }
        }
        
        private void printLine(final String s) {
            if (s == null || s.trim().length() == 0) {
                return;
            }
            try {
                this.out.println(s);
            }
            catch (Exception e) {
                System.err.println("Exception in printLine: " + e.toString());
            }
        }
        
        private void printThreeColumns(final String left, final String center, final String right) {
            try {
                int max_column_width;
                int large_column_width;
                for (max_column_width = this.line_width / 4, large_column_width = max_column_width * 2; large_column_width + 2 * max_column_width < this.line_width; ++large_column_width) {}
                final StringBuilder sb = new StringBuilder();
                sb.append((left.length() > max_column_width) ? left.subSequence(0, max_column_width) : (left + this.spaces(max_column_width - left.length())));
                sb.append((center.length() > large_column_width) ? center.subSequence(0, large_column_width) : (center + this.spaces(large_column_width - center.length())));
                sb.append((right.length() > max_column_width) ? right.subSequence(0, max_column_width) : (this.spaces(max_column_width - right.length()) + right));
                this.out.println(sb.toString());
            }
            catch (Exception e) {
                System.err.println("Exception in printThreeColumns: " + e.toString());
                e.printStackTrace();
            }
        }
        
        private void printLeftAndRight(final String left, final String right) {
            try {
                final int width = left.length() + right.length();
                if (width >= this.line_width) {
                    final int left_width = this.line_width - right.length() - 4;
                    if (left_width > 0) {
                        this.out.println(left.substring(0, left_width) + "... " + right);
                    }
                }
                else {
                    this.out.println(left + this.spaces(this.line_width - width) + right);
                }
            }
            catch (Exception e) {
                System.err.println("Exception in printLeftAndRight: " + e.toString());
                e.printStackTrace();
            }
        }
        
        private String spaces(int count) {
            final StringBuilder sb = new StringBuilder();
            while (--count >= 0) {
                sb.append(" ");
            }
            return sb.toString();
        }
        
        private void blankLines(int count) {
            try {
                while (--count >= 0) {
                    this.out.println();
                }
            }
            catch (Exception e) {
                System.err.println("Exception in centerLine: " + e.toString());
            }
        }
        
        public void autoCut() {
            this.outputCodeSet(this.out, PointOfSale.this.auto_cut);
        }
        
        public void openCashDrawer() {
            System.err.println("openCashDrawer () #" + PointOfSale.this.receipt_number);
            this.outputCodeSet(this.out, PointOfSale.this.pop_cash_drawer);
        }
        
        private void changeFont(final int font) {
            if (font == this.current_font) {
                return;
            }
            switch (this.current_font = font) {
                case 0: {
                    this.outputCodeSet(this.out, PointOfSale.this.small_font);
                    this.line_width = PointOfSale.this.small_font_char_count;
                    break;
                }
                case 1: {
                    this.outputCodeSet(this.out, PointOfSale.this.medium_font);
                    this.line_width = PointOfSale.this.medium_font_char_count;
                    break;
                }
                case 2: {
                    this.outputCodeSet(this.out, PointOfSale.this.large_font);
                    this.line_width = PointOfSale.this.large_font_char_count;
                    break;
                }
            }
        }
        
        private void outputCodeSet(final PrintStream out, final String bytes) {
            final StringTokenizer st = new StringTokenizer(bytes, ",");
            try {
                while (st.hasMoreTokens()) {
                    final Byte b = Byte.decode(st.nextToken());
                    out.write(b);
                }
            }
            catch (Exception e) {
                System.err.println("Exception in outputCodeSet: " + e.toString());
            }
        }
    }
}
