// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.Applets;

import java.awt.Font;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Panel;

public class LabeledTextField extends Panel
{
    private static final long serialVersionUID = -6345497249297670020L;
    private TextField txtField;
    private Label lbl;
    
    public LabeledTextField(final String s, final int n, final int n2) {
        this.lbl = new Label(s);
        this.txtField = new TextField(16);
        this.setLayout(new BorderLayout());
        this.lbl.setAlignment(0);
        this.add(this.lbl, "North");
        this.add(this.txtField, "Center");
    }
    
    public final void a(final String text) {
        this.txtField.setText(text);
    }
    
    public final String a() {
        return this.txtField.getText();
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.txtField.setFont(font);
        this.lbl.setFont(font);
    }
}
