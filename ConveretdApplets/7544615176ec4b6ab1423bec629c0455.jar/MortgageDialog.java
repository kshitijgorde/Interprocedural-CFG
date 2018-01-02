import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Label;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Container;

// 
// Decompiled by Procyon v0.5.30
// 

public class MortgageDialog
{
    Container m_Parent;
    boolean m_fInitialized;
    DialogLayout m_Layout;
    CheckboxGroup group1;
    Checkbox IDC_MONTHLY_TYPE;
    Checkbox IDC_SEMI_MONTHLY_TYPE;
    Checkbox IDC_BI_WEEKLY_TYPE;
    Checkbox IDC_WEEKLY_TYPE;
    Label IDC_MORTGAGE_TYPE_LABEL;
    Button IDC_BUTTON_CALCULATE;
    TextField IDC_PRINCIPAL_TEXTBOX;
    TextField IDC_INTEREST_RATE_TEXTBOX;
    TextField IDC_TERM_TEXTBOX;
    TextField IDC_PAYMENT_TEXTBOX;
    Label IDC_MORTGAGE_SPECIFICS_LABEL;
    Label IDC_PRINCIPAL_LABEL;
    Label IDC_INTEREST_RATE_LABEL;
    Label IDC_TERM_LABEL;
    Label IDC_PAYMENT_LABEL;
    Label IDC_TOTAL_INTEREST_LABEL;
    TextField IDC_TOTAL_INTEREST_TEXTBOX;
    Label IDC_TOTAL_COST_LABEL;
    TextField IDC_TOTAL_COST_TEXTBOX;
    Label IDC_CALCULATE_USING_LABEL;
    CheckboxGroup group2;
    Checkbox IDC_CALCULATE_USING_TERM;
    Checkbox IDC_CALCULATE_USING_PAYMENT;
    
    public MortgageDialog(final Container parent) {
        this.m_Parent = parent;
    }
    
    public boolean CreateControls() {
        if (this.m_fInitialized || this.m_Parent == null) {
            return false;
        }
        if (!(this.m_Parent instanceof Container)) {
            return false;
        }
        final Font OldFnt = this.m_Parent.getFont();
        if (OldFnt != null) {
            final Font NewFnt = new Font(OldFnt.getName(), OldFnt.getStyle(), 8);
            this.m_Parent.setFont(NewFnt);
        }
        this.m_Layout = new DialogLayout(this.m_Parent, 406, 150);
        this.m_Parent.setLayout(this.m_Layout);
        this.m_Parent.addNotify();
        final Dimension size = this.m_Layout.getDialogSize();
        final Insets insets = this.m_Parent.insets();
        this.m_Parent.resize(insets.left + size.width + insets.right, insets.top + size.height + insets.bottom);
        this.group1 = new CheckboxGroup();
        this.IDC_MONTHLY_TYPE = new Checkbox("Monthly", this.group1, false);
        this.m_Parent.add(this.IDC_MONTHLY_TYPE);
        this.m_Layout.setShape(this.IDC_MONTHLY_TYPE, 23, 19, 55, 14);
        this.IDC_SEMI_MONTHLY_TYPE = new Checkbox("Semi-Monthly", this.group1, false);
        this.m_Parent.add(this.IDC_SEMI_MONTHLY_TYPE);
        this.m_Layout.setShape(this.IDC_SEMI_MONTHLY_TYPE, 23, 32, 55, 16);
        this.IDC_BI_WEEKLY_TYPE = new Checkbox("Bi-Weekly", this.group1, false);
        this.m_Parent.add(this.IDC_BI_WEEKLY_TYPE);
        this.m_Layout.setShape(this.IDC_BI_WEEKLY_TYPE, 23, 46, 50, 14);
        this.IDC_WEEKLY_TYPE = new Checkbox("Weekly", this.group1, false);
        this.m_Parent.add(this.IDC_WEEKLY_TYPE);
        this.m_Layout.setShape(this.IDC_WEEKLY_TYPE, 23, 60, 44, 10);
        this.IDC_MORTGAGE_TYPE_LABEL = new Label("Mortgatge Type", 1);
        this.m_Parent.add(this.IDC_MORTGAGE_TYPE_LABEL);
        this.m_Layout.setShape(this.IDC_MORTGAGE_TYPE_LABEL, 25, 7, 55, 10);
        this.IDC_BUTTON_CALCULATE = new Button("Calculate");
        this.m_Parent.add(this.IDC_BUTTON_CALCULATE);
        this.m_Layout.setShape(this.IDC_BUTTON_CALCULATE, 119, 81, 106, 15);
        this.IDC_PRINCIPAL_TEXTBOX = new TextField("");
        this.m_Parent.add(this.IDC_PRINCIPAL_TEXTBOX);
        this.m_Layout.setShape(this.IDC_PRINCIPAL_TEXTBOX, 167, 20, 58, 13);
        this.IDC_INTEREST_RATE_TEXTBOX = new TextField("");
        this.m_Parent.add(this.IDC_INTEREST_RATE_TEXTBOX);
        this.m_Layout.setShape(this.IDC_INTEREST_RATE_TEXTBOX, 167, 34, 58, 14);
        this.IDC_TERM_TEXTBOX = new TextField("");
        this.m_Parent.add(this.IDC_TERM_TEXTBOX);
        this.m_Layout.setShape(this.IDC_TERM_TEXTBOX, 167, 50, 58, 12);
        this.IDC_PAYMENT_TEXTBOX = new TextField("");
        this.m_Parent.add(this.IDC_PAYMENT_TEXTBOX);
        this.m_Layout.setShape(this.IDC_PAYMENT_TEXTBOX, 167, 65, 58, 13);
        this.IDC_MORTGAGE_SPECIFICS_LABEL = new Label("Mortgage Specifics", 0);
        this.m_Parent.add(this.IDC_MORTGAGE_SPECIFICS_LABEL);
        this.m_Layout.setShape(this.IDC_MORTGAGE_SPECIFICS_LABEL, 137, 7, 70, 10);
        this.IDC_PRINCIPAL_LABEL = new Label("Principal:", 0);
        this.m_Parent.add(this.IDC_PRINCIPAL_LABEL);
        this.m_Layout.setShape(this.IDC_PRINCIPAL_LABEL, 117, 22, 44, 11);
        this.IDC_INTEREST_RATE_LABEL = new Label("Interest Rate:", 0);
        this.m_Parent.add(this.IDC_INTEREST_RATE_LABEL);
        this.m_Layout.setShape(this.IDC_INTEREST_RATE_LABEL, 117, 36, 47, 10);
        this.IDC_TERM_LABEL = new Label("Term:", 0);
        this.m_Parent.add(this.IDC_TERM_LABEL);
        this.m_Layout.setShape(this.IDC_TERM_LABEL, 117, 50, 30, 9);
        this.IDC_PAYMENT_LABEL = new Label("Payment:", 0);
        this.m_Parent.add(this.IDC_PAYMENT_LABEL);
        this.m_Layout.setShape(this.IDC_PAYMENT_LABEL, 119, 66, 42, 9);
        this.IDC_TOTAL_INTEREST_LABEL = new Label("Total Interest:", 0);
        this.m_Parent.add(this.IDC_TOTAL_INTEREST_LABEL);
        this.m_Layout.setShape(this.IDC_TOTAL_INTEREST_LABEL, 119, 99, 46, 10);
        this.IDC_TOTAL_INTEREST_TEXTBOX = new TextField("");
        this.m_Parent.add(this.IDC_TOTAL_INTEREST_TEXTBOX);
        this.m_Layout.setShape(this.IDC_TOTAL_INTEREST_TEXTBOX, 167, 97, 57, 15);
        this.IDC_TOTAL_COST_LABEL = new Label("Total Cost:", 0);
        this.m_Parent.add(this.IDC_TOTAL_COST_LABEL);
        this.m_Layout.setShape(this.IDC_TOTAL_COST_LABEL, 117, 115, 45, 10);
        this.IDC_TOTAL_COST_TEXTBOX = new TextField("");
        this.m_Parent.add(this.IDC_TOTAL_COST_TEXTBOX);
        this.m_Layout.setShape(this.IDC_TOTAL_COST_TEXTBOX, 167, 114, 57, 15);
        this.IDC_CALCULATE_USING_LABEL = new Label("Calculate Using", 0);
        this.m_Parent.add(this.IDC_CALCULATE_USING_LABEL);
        this.m_Layout.setShape(this.IDC_CALCULATE_USING_LABEL, 25, 88, 56, 10);
        this.group2 = new CheckboxGroup();
        this.IDC_CALCULATE_USING_TERM = new Checkbox("Term", this.group2, false);
        this.m_Parent.add(this.IDC_CALCULATE_USING_TERM);
        this.m_Layout.setShape(this.IDC_CALCULATE_USING_TERM, 20, 99, 45, 15);
        this.IDC_CALCULATE_USING_PAYMENT = new Checkbox("Payment", this.group2, false);
        this.m_Parent.add(this.IDC_CALCULATE_USING_PAYMENT);
        this.m_Layout.setShape(this.IDC_CALCULATE_USING_PAYMENT, 20, 114, 50, 10);
        return this.m_fInitialized = true;
    }
}
