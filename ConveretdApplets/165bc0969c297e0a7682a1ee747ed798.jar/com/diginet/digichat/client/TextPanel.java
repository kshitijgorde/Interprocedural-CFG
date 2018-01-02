// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Font;
import com.diginet.digichat.awt.a6;
import com.diginet.digichat.util.a5;
import com.esial.util.c;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Color;
import com.diginet.digichat.awt.GridPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.LayoutManager;
import com.diginet.digichat.awt.SignCheck;
import com.diginet.digichat.awt.r;
import java.awt.Image;
import com.diginet.digichat.awt.bk;
import java.awt.Point;
import java.awt.Component;
import com.diginet.digichat.network.v;
import com.diginet.digichat.awt.bv;
import com.diginet.digichat.awt.CheckButton;
import java.awt.Canvas;
import com.diginet.digichat.awt.InternalFrame;
import com.diginet.digichat.awt.DragListener;
import com.diginet.digichat.awt.DragContainer;
import com.diginet.digichat.awt.t;
import java.awt.TextArea;
import com.diginet.digichat.util.s;
import java.awt.Panel;

public class TextPanel extends Panel implements s
{
    private int nRepl;
    private int nSmile;
    private int nStyles;
    private String strDesc;
    private TextArea txaMess;
    private t tFocus;
    private i iUser;
    private DragContainer dcParent;
    private DragListener dlParent;
    private StylesPanel grdStyles;
    private InternalFrame infStyles;
    private Canvas btnSign;
    private Canvas btnSmiles;
    private Canvas btnShorts;
    
    private boolean getChecked(final Canvas canvas) {
        return (canvas instanceof CheckButton) ? ((CheckButton)canvas).getChecked() : ((bv)canvas).a();
    }
    
    public void setButtons() {
        this.btnSmiles.setVisible(v.a(this.iUser.lPlus, 19));
        this.btnShorts.setVisible(v.a(this.iUser.lPlus, 20) && this.iUser.b7);
        final int nStyles;
        if (this.nStyles != (nStyles = (((this.iUser.lPlus & 0x70000) == 0x0) ? -1 : this.iUser.nStyles))) {
            if (nStyles == 2 || nStyles == -1) {
                this.btnSign.hide();
                if (nStyles == -1 == (this.grdStyles.getParent() == this)) {
                    if (nStyles == 2) {
                        this.add(this.grdStyles, 1);
                    }
                    else {
                        this.remove(this.grdStyles);
                    }
                    this.invalidate();
                    this.validate();
                }
                this.btnSmiles.setBounds(0, 0, 26, 26);
                this.btnShorts.setBounds(0, 26, 26, 26);
                if (this.nStyles == 1) {
                    this.dcParent.removeDragListener(this.dlParent);
                    this.dcParent.removeLayer(this.infStyles);
                }
            }
            else if (this.nStyles == 2 || this.nStyles == -1) {
                this.setChecked(this.btnSign, false);
                this.btnSign.show();
                this.btnSmiles.setBounds(0, 0, 26, 21);
                this.btnShorts.setBounds(0, 31, 26, 21);
                this.remove(this.grdStyles);
                this.txaMess.invalidate();
                this.validate();
            }
            else if (this.getChecked(this.btnSign)) {
                if (this.iUser.nStyles == 0) {
                    this.dcParent.removeDragListener(this.dlParent);
                    this.dcParent.removeLayer(this.infStyles);
                    this.add(this.grdStyles, 1);
                }
                else {
                    final Point point = this.dlParent.getPoint();
                    this.dcParent.addLayer(this.infStyles, point.x, point.y);
                    this.dcParent.addDragListener(this.dlParent);
                    this.infStyles.add(this.grdStyles);
                }
                this.invalidate();
                this.validate();
            }
            this.nStyles = nStyles;
        }
        this.grdStyles.setButtons();
    }
    
    public boolean incShorts() {
        ++this.nRepl;
        return this.spentShorts();
    }
    
    public boolean incSmiles() {
        ++this.nSmile;
        return this.spentSmiles();
    }
    
    private Canvas createButton(final String s, final String s2, final int n, final int n2, final int n3) {
        final Image a;
        final Image a2;
        if (this.iUser.cc.l() && (a = this.iUser.a(String.valueOf(String.valueOf(this.iUser.cc.f()).concat(String.valueOf(s))).concat(String.valueOf("_button_up.gif")), true)) != null && (a2 = this.iUser.a(String.valueOf(String.valueOf(this.iUser.cc.f()).concat(String.valueOf(s))).concat(String.valueOf("_button_dn.gif")), true)) != null) {
            return bk.a(a, a2, null);
        }
        final r r;
        (r = new r()).resize(n, n2, false);
        r.a(this.iUser.a(s2, false, n3));
        return r;
    }
    
    private Canvas createButton(final String s, final String s2, final int n, final int n2) {
        return this.createButton(s, s2, n, 26, n2);
    }
    
    private Canvas createCheck(final String s, final String s2, final int n, final int n2) {
        final Image a;
        final Image a2;
        if (this.iUser.cc.l() && (a = this.iUser.a(String.valueOf(String.valueOf(this.iUser.cc.f()).concat(String.valueOf(s))).concat(String.valueOf("_unchecked.gif")), true)) != null && (a2 = this.iUser.a(String.valueOf(String.valueOf(this.iUser.cc.f()).concat(String.valueOf(s))).concat(String.valueOf("_checked.gif")), true)) != null) {
            return new bv(a, a2);
        }
        final CheckButton checkButton;
        (checkButton = new CheckButton(26, n)).resize(26, n, false);
        checkButton.a(this.iUser.a(s2, false, n2));
        return checkButton;
    }
    
    private Canvas createDwell(final String s, final String s2, final int n, final int n2) {
        final Image a;
        final Image a2;
        if (this.iUser.cc.l() && (a = this.iUser.a(String.valueOf(String.valueOf(this.iUser.cc.f()).concat(String.valueOf(s))).concat(String.valueOf("_unchecked.gif")), true)) != null && (a2 = this.iUser.a(String.valueOf(String.valueOf(this.iUser.cc.f()).concat(String.valueOf(s))).concat(String.valueOf("_checked.gif")), true)) != null) {
            return new bv(a, a2);
        }
        final SignCheck signCheck;
        (signCheck = new SignCheck()).resize(26, n, false);
        return signCheck;
    }
    
    private Canvas createCheck(final String s, final String s2, final int n) {
        return this.createCheck(s, s2, 26, n);
    }
    
    private Panel createColumn(final Canvas canvas, final Canvas canvas2, final Canvas canvas3) {
        final Panel panel = new Panel(null);
        final Dimension preferredSize = canvas.preferredSize();
        int width = preferredSize.width;
        int height = preferredSize.height;
        final int n = width;
        int width2 = 0;
        final int n2 = height;
        int n3 = height;
        if (canvas2 != null) {
            final Dimension preferredSize2 = canvas2.preferredSize();
            if (width < (width2 = preferredSize2.width)) {
                width = width2;
            }
            height = (n3 = height + preferredSize2.height);
        }
        final Dimension preferredSize3 = canvas3.preferredSize();
        final int width3;
        if (width < (width3 = preferredSize3.width)) {
            width = width3;
        }
        panel.setSize(width + 3, height + preferredSize3.height);
        panel.add(canvas);
        canvas.setLocation(3 + (width - n >> 1), 0);
        if (canvas2 != null) {
            panel.add(canvas2);
            canvas2.setLocation(3 + (width - width2 >> 1), n2);
        }
        panel.add(canvas3);
        canvas3.setLocation(3 + (width - width3 >> 1), n3);
        return panel;
    }
    
    private Panel createColumn(final Canvas canvas, final Canvas canvas2) {
        return this.createColumn(canvas, null, canvas2);
    }
    
    private void resizeColumn(final Panel panel, final Canvas canvas, final Canvas canvas2, final Canvas canvas3) {
        final Dimension preferredSize = canvas.preferredSize();
        int width = preferredSize.width;
        int height = preferredSize.height;
        final int n = width;
        int width2 = 0;
        if (canvas2 != null) {
            final Dimension preferredSize2 = canvas2.preferredSize();
            if (width < (width2 = preferredSize2.width)) {
                width = width2;
            }
            height += preferredSize2.height;
        }
        final Dimension preferredSize3 = canvas3.preferredSize();
        final int width3;
        if (width < (width3 = preferredSize3.width)) {
            width = width3;
        }
        final int n2 = height + preferredSize3.height;
        panel.setSize(width + 3, n2);
        panel.add(canvas);
        canvas.setLocation(3 + (width - n >> 1), 0);
        if (canvas2 != null) {
            panel.add(canvas2);
            canvas2.setLocation(3 + (width - width2 >> 1), n2);
        }
        panel.add(canvas3);
        canvas3.setLocation(3 + (width - width3 >> 1), (canvas2 == null) ? n2 : (n2 << 1));
    }
    
    public TextPanel(final i iUser, final DragContainer dcParent, final DragListener dlParent, final String strDesc) {
        this.iUser = iUser;
        this.strDesc = strDesc;
        this.dcParent = dcParent;
        this.dlParent = dlParent;
        final boolean b = false;
        this.nSmile = (b ? 1 : 0);
        this.nRepl = (b ? 1 : 0);
        final GridBagConstraints gridBagConstraints3;
        final GridBagConstraints gridBagConstraints2;
        final GridBagConstraints gridBagConstraints = gridBagConstraints2 = (gridBagConstraints3 = new GridBagConstraints());
        final boolean b2 = true;
        gridBagConstraints2.gridheight = (b2 ? 1 : 0);
        gridBagConstraints3.gridwidth = (b2 ? 1 : 0);
        final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
        final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
        final double n = 1.0;
        gridBagConstraints5.weighty = n;
        gridBagConstraints4.weightx = n;
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        try {
            this.txaMess = new TextArea("", 2, 10, 3);
        }
        catch (Throwable t) {
            this.txaMess = new TextArea(2, 10);
        }
        this.tFocus = new t(this.txaMess);
        gridBagConstraints.fill = 1;
        layout.setConstraints(this.tFocus, gridBagConstraints);
        this.add(this.tFocus);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 3;
        layout.setConstraints(this.grdStyles = new StylesPanel(iUser, this), gridBagConstraints);
        final Color foreground;
        if ((foreground = this.txaMess.getForeground()) == null) {
            this.setForeground(this.iUser.cc.k);
        }
        else {
            this.grdStyles.setForeground(foreground);
        }
        final Color background;
        if ((background = this.txaMess.getBackground()) == null) {
            this.setBackground(this.iUser.cc.m);
        }
        else {
            this.grdStyles.setBackground(background);
        }
        final GridPanel gridPanel = new GridPanel(1, 3);
        gridPanel.add(this.btnSmiles = this.createButton("smiles", "smilesTextIcon.gif", 26, 21, 124), 0, 0);
        gridPanel.add(this.btnSign = this.createDwell("show", "showPlusIcon.gif", 10, 125), 0, 1);
        gridPanel.add(this.btnShorts = this.createButton("shorts", "shortsTextIcon.gif", 26, 21, 126), 0, 2);
        layout.setConstraints(gridPanel, gridBagConstraints);
        this.add(gridPanel);
        (this.infStyles = new InternalFrame()).setBackground(this.iUser.cc.d);
        this.nStyles = 0;
    }
    
    private Frame getFrame() {
        return (this.dcParent instanceof Frame) ? this.dcParent : new Frame();
    }
    
    private boolean spentShorts() {
        return this.iUser.nRepl != 0 && this.nRepl >= this.iUser.nRepl;
    }
    
    private boolean spentSmiles() {
        return this.iUser.nSmile != 0 && this.nSmile >= this.iUser.nSmile;
    }
    
    public void addNotify() {
        super.addNotify();
        this.setButtons();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001) {
            if (event.target == this.btnSign) {
                if (this.getChecked(this.btnSign)) {
                    if (this.nStyles == 0) {
                        this.add(this.grdStyles, 1);
                        this.invalidate();
                        this.validate();
                    }
                    else {
                        final Point point = this.dlParent.getPoint();
                        this.dcParent.addLayer(this.infStyles, point.x, point.y);
                        this.dcParent.addDragListener(this.dlParent);
                        this.infStyles.add(this.grdStyles);
                    }
                }
                else if (this.nStyles == 0) {
                    this.remove(this.grdStyles);
                    this.txaMess.invalidate();
                    this.validate();
                }
                else {
                    this.dcParent.removeDragListener(this.dlParent);
                    this.dcParent.removeLayer(this.infStyles);
                }
                return true;
            }
            if (event.target == this.btnShorts) {
                if (this.spentShorts()) {
                    new a6(this.getFrame(), c.a("Note"), a5.a(c.a("You could do only %1 replacements in one message."), new String[] { Integer.toString(this.iUser.nRepl) }), this.iUser).setVisible(true);
                }
                else {
                    new ShortsBox(this.iUser, this);
                }
                return true;
            }
            if (event.target == this.btnSmiles) {
                if (this.spentSmiles()) {
                    new a6(this.getFrame(), c.a("Note"), a5.a(c.a("You could use only %1 emoticons in one message."), new String[] { Integer.toString(this.iUser.nSmile) }), this.iUser).setVisible(true);
                }
                else {
                    new SmilesBox((h)this.iUser, this);
                }
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public boolean isMess(final Event event) {
        return event.target == this.txaMess;
    }
    
    public void appendText(final String s) {
        this.txaMess.appendText(s);
        this.txaMess.setCaretPosition(this.txaMess.getCaretPosition() + s.length());
    }
    
    public void appendElem(final String s) {
        final String text;
        final int length;
        if ((length = (text = this.txaMess.getText()).length()) > 0 && !Character.isSpaceChar(text.charAt(length - 1))) {
            this.txaMess.appendText(" ");
        }
        this.txaMess.appendText(s);
        this.txaMess.appendText(" ");
    }
    
    public String getText() {
        return this.txaMess.getText();
    }
    
    public void setText(final String text) {
        this.txaMess.setText(text);
        this.txaMess.setCaretPosition(text.length());
    }
    
    public String getMessage() {
        final String trim = this.txaMess.getText().trim();
        this.txaMess.setText("");
        final boolean b = false;
        this.nSmile = (b ? 1 : 0);
        this.nRepl = (b ? 1 : 0);
        return trim;
    }
    
    public void requestFocus() {
        this.txaMess.requestFocus();
        final int length = this.txaMess.getText().length();
        this.txaMess.select(length, length);
    }
    
    private void setChecked(final Canvas canvas, final boolean checked) {
        if (canvas instanceof CheckButton) {
            ((CheckButton)canvas).setChecked(checked);
        }
        else {
            ((bv)canvas).a(checked);
        }
    }
    
    public void setFont(final Font font) {
        this.txaMess.setFont(font);
        this.grdStyles.setFont(font);
    }
    
    public Font getFont() {
        return this.txaMess.getFont();
    }
    
    void setFontImp(final Font font) {
        this.txaMess.setFont(font);
    }
    
    public void setBackground(final Color color) {
        this.txaMess.setBackground(color);
        this.grdStyles.setBackground(color);
    }
    
    public void setForeground(final Color color) {
        this.txaMess.setForeground(color);
        this.grdStyles.setForeground(color);
    }
    
    public String a(final Object o) {
        return c.a((o == this.tFocus) ? this.strDesc : ((o == this.btnSmiles) ? "Click here to select smile for text of message." : ((o == this.btnShorts) ? "Click here to select shortcut for text of message." : ((o == this.btnSign) ? (this.getChecked(this.btnSign) ? "Click here to hide styles panel." : "Click here to show styles panel.") : null))));
    }
    
    public long getStyle() {
        return this.grdStyles.getStyle();
    }
}
