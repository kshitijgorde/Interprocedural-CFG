// 
// Decompiled by Procyon v0.5.30
// 

package count;

import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.Panel;

public class CounterPanel extends Panel implements ActionListener
{
    private ChatCounter pMain;
    private Label counterLabel;
    
    public CounterPanel(final ChatCounter pMain) {
        this.pMain = pMain;
        this.buildGUI();
    }
    
    public void display(final String text) {
        this.changeFont();
        this.counterLabel.setText(text);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
    }
    
    private void changeFont() {
        final Font font = this.getFont();
        String s = this.pMain.getParm("ui.FontName");
        if (s == null && font != null) {
            s = font.getName();
        }
        if (s == null) {
            return;
        }
        String parm = this.pMain.getParm("ui.FontSize");
        int n = 12;
        if (parm != null) {
            try {
                n = Integer.parseInt(parm);
            }
            catch (NumberFormatException ex) {
                parm = null;
            }
        }
        if (parm == null && font != null) {
            n = font.getSize();
        }
        final Font font2 = new Font(s, 0, n);
        if (font2 != null) {
            this.counterLabel.setFont(font2);
        }
    }
    
    private void buildGUI() {
        final Label label = this.getLabel();
        this.setLayout(new BorderLayout(1, 1));
        this.add("Center", label);
        this.counterLabel = label;
    }
    
    private Label getLabel() {
        final String parm = this.pMain.getParm("ui.fg");
        final String parm2 = this.pMain.getParm("ui.bg");
        Color color = null;
        if (parm != null) {
            color = this.pMain.parseColor(parm, Color.black);
        }
        Color color2 = null;
        if (parm2 != null) {
            color2 = this.pMain.parseColor(parm2, Color.white);
        }
        final Label label = new Label("   ", 1);
        if (color != null) {
            label.setForeground(color);
        }
        if (color2 != null) {
            label.setBackground(color2);
        }
        return label;
    }
}
