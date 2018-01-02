// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.TextListener;
import java.awt.event.ComponentListener;
import java.awt.TextArea;

public class vl extends vk
{
    private TextArea a;
    
    public vl() {
        (this.a = new TextArea("", 10, 30, 1)).addComponentListener(new vaz());
        this.a.addTextListener(new va0(25000, 5000));
        this.setLayout(new BorderLayout());
        this.add(this.a);
    }
    
    public void setBackground(final Color color) {
        super.setBackground(color);
        this.a.setBackground(color);
    }
    
    public void setForeground(final Color color) {
        super.setForeground(color);
        this.a.setForeground(color);
    }
    
    public void setFont(final Font font) {
        this.a.setFont(font);
    }
    
    public boolean a() {
        return this.a.isEditable();
    }
    
    public void a(final boolean editable) {
        this.a.setEditable(editable);
    }
    
    public void a(final String s) {
        this.a.append(String.valueOf((this.a.getText().length() > 0) ? "\n" : "") + s);
    }
}
