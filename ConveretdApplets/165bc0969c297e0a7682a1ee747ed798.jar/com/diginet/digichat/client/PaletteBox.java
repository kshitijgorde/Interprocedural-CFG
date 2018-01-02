// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Event;
import java.awt.Color;
import com.esial.util.c;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.util.Vector;
import java.awt.Frame;
import com.diginet.digichat.awt.bj;
import com.diginet.digichat.awt.r;
import java.awt.Label;
import com.diginet.digichat.awt.ag;

public class PaletteBox extends ag
{
    private int nResult;
    private Label lblR;
    private Label lblG;
    private Label lblB;
    private PalettePanel pnlPalette;
    private r btnCancel;
    private r btnOK;
    private bj bjColor;
    
    public PaletteBox(final Frame frame, final i i, final Vector palette) {
        super(frame, true);
        this.setBackground(i.cc.c);
        final GridBagLayout gridBagLayout;
        this.setLayout(gridBagLayout = new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        final double n = 1.0;
        gridBagConstraints3.weightx = n;
        gridBagConstraints2.weighty = n;
        gridBagLayout.setConstraints(this.pnlPalette = new PalettePanel(i), gridBagConstraints);
        this.pnlPalette.setPalette(palette);
        this.add(this.pnlPalette);
        final Panel panel = new Panel(new GridLayout(0, 1));
        panel.add(this.lblR = new Label("R: 000"));
        panel.add(this.lblG = new Label("G: 000"));
        panel.add(this.lblB = new Label("B: 000"));
        panel.add(this.bjColor = new bj());
        gridBagConstraints.insets = new Insets(5, 0, 5, 5);
        gridBagConstraints.fill = 3;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        final Panel panel2 = new Panel(new GridLayout(1, 0));
        panel2.add(this.btnCancel = new r(c.a("Cancel")));
        panel2.add(this.btnOK = new r(c.a("OK")));
        gridBagConstraints.fill = 0;
        gridBagConstraints.insets = new Insets(0, 5, 5, 5);
        gridBagConstraints.anchor = 13;
        final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
        final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
        final double n2 = 0.0;
        gridBagConstraints5.weightx = n2;
        gridBagConstraints4.weighty = n2;
        gridBagLayout.setConstraints(panel2, gridBagConstraints);
        this.add(panel2);
        this.pack();
    }
    
    public int getResult(final String title, final Color color) {
        this.lblR.setText(String.valueOf("R: ").concat(String.valueOf(color.getRed())));
        this.lblG.setText(String.valueOf("G: ").concat(String.valueOf(color.getGreen())));
        this.lblB.setText(String.valueOf("B: ").concat(String.valueOf(color.getBlue())));
        if (this.pnlPalette.setSelected(color) < 0) {
            this.btnOK.c();
        }
        this.bjColor.setBackground(color);
        this.setTitle(title);
        this.nResult = 0;
        this.show();
        return this.nResult;
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 701: {
                this.btnOK.b();
                return true;
            }
            case 702: {
                this.btnOK.c();
                return true;
            }
            case 1001: {
                if (event.target == this.btnCancel) {
                    this.dispose();
                    return true;
                }
                final int selected;
                if ((selected = this.pnlPalette.getSelected()) >= 0 && (event.target == this.btnOK || event.target == this.pnlPalette)) {
                    this.nResult = (this.pnlPalette.getColor(selected).getRGB() | 0xFF000000);
                    this.dispose();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
}
