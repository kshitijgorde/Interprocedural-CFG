// 
// Decompiled by Procyon v0.5.30
// 

package paintbrush;

import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Panel;

public class InputField extends Panel
{
    PaintBrush pb;
    TextField tf;
    Label l;
    
    public InputField(final PaintBrush pb) {
        this.pb = pb;
        this.setLayout(new FlowLayout());
        (this.l = new Label("Name of file")).setFont(new Font("Helvetica", 0, 14));
        this.add(this.l);
        this.add(this.tf = new TextField("", 20));
    }
    
    public String getName() {
        return this.tf.getText();
    }
}
