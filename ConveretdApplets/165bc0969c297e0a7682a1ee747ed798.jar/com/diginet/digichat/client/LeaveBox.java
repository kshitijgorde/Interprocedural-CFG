// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.CommonStyle;
import java.util.Enumeration;
import com.diginet.digichat.network.v;
import com.diginet.digichat.util.dx;
import java.awt.Event;
import java.awt.Image;
import com.diginet.digichat.util.n;
import com.diginet.digichat.awt.u;
import com.diginet.digichat.awt.a9;
import java.awt.image.ImageObserver;
import com.diginet.digichat.awt.dw;
import com.diginet.digichat.common.Recip;
import java.awt.Insets;
import com.diginet.digichat.awt.bj;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import com.diginet.digichat.util.a5;
import com.esial.util.c;
import com.diginet.digichat.awt.r;
import java.util.Vector;
import java.awt.Component;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.Point;
import com.diginet.digichat.awt.DragListener;
import com.diginet.digichat.awt.DragContainer;
import com.diginet.digichat.awt.ag;

public class LeaveBox extends ag implements DragContainer, DragListener
{
    private boolean fRecip;
    private boolean fTail;
    private int nRecip;
    private int nOffline;
    private int nX;
    private int nY;
    private int insX;
    private int insY;
    private Point pntLayer;
    private TextField txfTail;
    private Choice chcRecip;
    private TextPanel txpMess;
    private Component cmpDrag;
    private Vector vecDrag;
    private i iUser;
    private r rSend;
    private r rClose;
    
    private void enableSend() {
        if (this.fRecip || this.fTail) {
            this.rSend.c();
        }
        else {
            this.rSend.b();
        }
    }
    
    public LeaveBox(final i iUser) {
        super((iUser.y == null) ? null : iUser.y.b(), a5.a(c.a("Offline message to %1."), new String[] { "..." }), true);
        this.setResizable(false);
        this.iUser = iUser;
        this.cmpDrag = null;
        this.vecDrag = new Vector();
        final boolean b = true;
        this.fTail = b;
        this.fRecip = b;
        this.pntLayer = new Point(0, 0);
        if (iUser.isMaster()) {
            this.nRecip = 0;
            this.nOffline = iUser.nOfflineMaster;
        }
        else {
            this.nRecip = 1;
            this.nOffline = iUser.nOfflineGuest;
        }
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Panel panel = new Panel(gridBagLayout);
        panel.setBackground(iUser.cc.c);
        final bj bj = new bj(iUser.cc.v);
        bj.setBackground(iUser.cc.d);
        bj.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(3, 4, 3, 4);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        (this.chcRecip = new Choice()).addItem(c.a("----Recipient----"));
        this.chcRecip.addItem(c.a("All recipients"));
        final n recips;
        (recips = iUser.recips).a(false);
        try {
            for (int b2 = recips.b(), i = this.nRecip; i < b2; ++i) {
                this.chcRecip.addItem(((Recip)recips.d(i)).x());
            }
        }
        finally {
            recips.a();
        }
        gridBagConstraints.fill = 2;
        gridBagLayout.setConstraints(this.chcRecip, gridBagConstraints);
        final Panel panel2 = new Panel(gridBagLayout);
        panel2.add(this.chcRecip);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridwidth = 0;
        (this.txfTail = new TextField(5)).setEditable(false);
        this.txfTail.setText(Integer.toString(this.nOffline));
        gridBagLayout.setConstraints(this.txfTail, gridBagConstraints);
        panel2.add(this.txfTail);
        final Panel panel3 = new Panel(gridBagLayout);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 2;
        gridBagLayout.setConstraints(panel2, gridBagConstraints);
        panel3.add(panel2);
        (this.txpMess = new TextPanel(iUser, this, this, null)).setFont(dw.c);
        gridBagLayout.setConstraints(this.txpMess, gridBagConstraints);
        panel3.add(this.txpMess);
        final Image loadImage;
        if ((loadImage = iUser.loadImage("../PostBox.gif", this, 330)) != null) {
            gridBagConstraints.gridwidth = 1;
        }
        gridBagConstraints.fill = 10;
        gridBagConstraints.fill = 2;
        gridBagLayout.setConstraints(panel3, gridBagConstraints);
        bj.add(panel3);
        if (loadImage != null) {
            final a9 a9;
            (a9 = new a9()).b(loadImage);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.fill = 0;
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(a9, gridBagConstraints);
            bj.add(a9);
        }
        gridBagConstraints.fill = 1;
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        final double n = 1.0;
        gridBagConstraints3.weighty = n;
        gridBagConstraints2.weightx = n;
        gridBagLayout.setConstraints(bj, gridBagConstraints);
        panel.add(bj);
        final Panel panel4 = new Panel(gridBagLayout);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
        final GridBagConstraints gridBagConstraints5 = gridBagConstraints;
        final double n2 = 0.0;
        gridBagConstraints5.weightx = n2;
        gridBagConstraints4.weighty = n2;
        gridBagLayout.setConstraints(this.rClose = new r(c.a("Close")), gridBagConstraints);
        panel4.add(this.rClose);
        gridBagConstraints.gridwidth = 0;
        final u u;
        gridBagLayout.setConstraints(u = new u(this.rSend = new r(c.a("Send"))), gridBagConstraints);
        panel4.add(u);
        gridBagLayout.setConstraints(panel4, gridBagConstraints);
        panel.add(panel4);
        this.setBackground(iUser.cc.c);
        this.setLayout(gridBagLayout);
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        final GridBagConstraints gridBagConstraints6 = gridBagConstraints;
        final GridBagConstraints gridBagConstraints7 = gridBagConstraints;
        final double n3 = 1.0;
        gridBagConstraints7.weightx = n3;
        gridBagConstraints6.weighty = n3;
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        this.pack();
        final Insets insets = this.getInsets();
        final int height = this.getSize().height;
        this.insX = insets.left - 3;
        this.insY = insets.top - 3;
        this.setLayout(null);
        this.resize(400, height);
        panel.setBounds(insets.left, insets.top, 400 - insets.left - insets.right, height - insets.top - insets.bottom);
        this.enableSend();
        this.show();
    }
    
    public final boolean handleEvent(final Event event) {
        Label_0300: {
            switch (event.id) {
                case 502:
                case 505: {
                    if (this.cmpDrag == null) {
                        break;
                    }
                    final Point location = this.cmpDrag.getLocation();
                    final Enumeration<DragListener> elements = (Enumeration<DragListener>)this.vecDrag.elements();
                    while (elements.hasMoreElements()) {
                        elements.nextElement().endDrag(this.cmpDrag, location.x - this.insX, location.y - this.insY);
                    }
                    this.cmpDrag = null;
                    return true;
                }
                case 506: {
                    if (this.cmpDrag == null) {
                        break;
                    }
                    final Point location2 = this.cmpDrag.getLocation();
                    this.cmpDrag.setLocation(location2.x + event.x - this.nX, location2.y + event.y - this.nY);
                    this.nX = event.x;
                    this.nY = event.y;
                    return true;
                }
                case 401: {
                    if (!this.txpMess.isMess(event)) {
                        break Label_0300;
                    }
                    if (event.key == 10 || event.key == dx.a) {
                        this.rSend.e();
                        return true;
                    }
                    if (event.key == 27) {
                        this.txpMess.appendText("\n");
                    }
                    break Label_0300;
                }
                case 402:
                case 403:
                case 404: {
                    final String text;
                    int n;
                    if ((n = this.nOffline - (text = this.txpMess.getText()).length()) < 0) {
                        n = 0;
                        this.txpMess.setText(text.substring(0, this.nOffline));
                    }
                    this.txfTail.setText(Integer.toString(n));
                    this.fTail = (n == this.nOffline);
                    this.enableSend();
                    break;
                }
                case 1001: {
                    if (event.target == this.chcRecip) {
                        final String a = c.a("Offline message to %1.");
                        final String[] array = { null };
                        final int n2 = 0;
                        final boolean fRecip = this.chcRecip.getSelectedIndex() == 0;
                        this.fRecip = fRecip;
                        array[n2] = (fRecip ? "..." : this.chcRecip.getSelectedItem());
                        this.setTitle(a5.a(a, array));
                        this.enableSend();
                        return true;
                    }
                    if (event.target == this.rSend) {
                        final v v = new v(67502338, 1);
                        int w;
                        if ((w = this.chcRecip.getSelectedIndex() - 2) >= 0) {
                            final Recip recip;
                            v.a(0, (recip = (Recip)this.iUser.recips.d(w + this.nRecip)).y());
                            w = recip.w();
                        }
                        v.a(0, 0, this.iUser.i(59));
                        v.a(0, 1, this.iUser.i(62) || this.iUser.i(61));
                        v.a(0, 0, w);
                        v.a(0, 1, (this.iUser.clrName == null) ? 0 : (this.iUser.clrName.getRGB() | 0xFF000000));
                        v.a(0, 2, this.iUser.bp);
                        v.a(0, 3, this.iUser.nStar);
                        v.a(0, 4, System.currentTimeMillis());
                        v.a(0, 6, this.txpMess.getStyle());
                        v.a(0, 0, this.iUser.x());
                        v.a(0, 1, this.txpMess.getText());
                        this.iUser.an(v);
                        this.dispose();
                        return true;
                    }
                    if (event.target == this.rClose) {
                        this.dispose();
                        return true;
                    }
                    break;
                }
            }
        }
        return super.handleEvent(event);
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x10) == 0x0 || !CommonStyle.fAnim) {
            return super.imageUpdate(image, n, n2, n3, n4, n5);
        }
        this.repaint();
        return false;
    }
    
    public void addDragListener(final DragListener dragListener) {
        if (this.vecDrag.indexOf(dragListener) < 0) {
            this.vecDrag.addElement(dragListener);
        }
    }
    
    public void removeDragListener(final DragListener dragListener) {
        this.vecDrag.removeElement(dragListener);
    }
    
    public void addLayer(final Component component, final int n, final int n2) {
        this.add(component, 0);
        component.setLocation(n + this.insX, n2 + this.insY);
    }
    
    public void dragLayer(final Component cmpDrag, final int n, final int n2) {
        this.cmpDrag = cmpDrag;
        final Point location = cmpDrag.getLocation();
        this.nX = location.x + n;
        this.nY = location.y + n2;
        final Enumeration<DragListener> elements = this.vecDrag.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().startDrag(this.cmpDrag, location.x - this.insX, location.y - this.insY);
        }
    }
    
    public void removeLayer(final Component component) {
        this.remove(component);
    }
    
    public Point getPoint() {
        return this.pntLayer;
    }
    
    public void startDrag(final Component component, final int n, final int n2) {
    }
    
    public void endDrag(final Component component, final int x, final int y) {
        this.pntLayer.x = x;
        this.pntLayer.y = y;
    }
    
    public void setInfo(final Component component, final String s) {
    }
}
