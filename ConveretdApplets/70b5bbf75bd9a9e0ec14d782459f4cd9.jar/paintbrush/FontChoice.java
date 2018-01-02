// 
// Decompiled by Procyon v0.5.30
// 

package paintbrush;

import java.awt.event.ItemEvent;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Choice;
import java.awt.event.ItemListener;
import java.awt.Panel;

public class FontChoice extends Panel implements ItemListener
{
    PaintCanvas canvas;
    Choice name;
    Choice style;
    Choice size;
    int[] istyles;
    String[] styles;
    int[] sizes;
    
    public FontChoice(final PaintCanvas canvas) {
        this.istyles = new int[] { 0, 1, 2 };
        this.styles = new String[] { "Normal", "Bold", "Italic" };
        this.sizes = new int[] { 10, 14, 18, 22, 26, 30, 34, 38, 42, 46, 50, 54, 58, 62, 66, 70 };
        this.canvas = canvas;
        this.setBackground(new Color(212, 210, 204));
        this.setLayout(new FlowLayout());
        this.name = new Choice();
        this.addNameItems();
        this.style = new Choice();
        this.addStyleItems();
        this.size = new Choice();
        this.addSizeItems();
        this.name.addItemListener(this);
        this.style.addItemListener(this);
        this.size.addItemListener(this);
        this.add(this.name);
        this.add(this.style);
        this.add(this.size);
        for (int i = 0; i < this.name.countItems(); ++i) {
            this.name.select(i);
            if (this.name.getSelectedItem() == "Serif") {
                break;
            }
        }
        this.size.select(4);
        canvas.setFont(new Font(this.name.getSelectedItem(), this.istyles[this.style.getSelectedIndex()], this.sizes[this.size.getSelectedIndex()]));
    }
    
    private void addNameItems() {
        final String[] fonts = Toolkit.getDefaultToolkit().getFontList();
        for (int i = 0; i < fonts.length; ++i) {
            this.name.addItem(fonts[i]);
        }
    }
    
    private void addSizeItems() {
        for (int i = 0; i < this.sizes.length; ++i) {
            this.size.addItem(String.valueOf(this.sizes[i]));
        }
    }
    
    private void addStyleItems() {
        for (int i = 0; i < this.styles.length; ++i) {
            this.style.addItem(this.styles[i]);
        }
    }
    
    public void itemStateChanged(final ItemEvent e) {
        this.canvas.setFont(new Font(this.name.getSelectedItem(), this.istyles[this.style.getSelectedIndex()], this.sizes[this.size.getSelectedIndex()]));
    }
    
    public void setEnabled(final boolean b) {
        this.name.setEnabled(b);
        this.style.setEnabled(b);
        this.size.setEnabled(b);
    }
}
