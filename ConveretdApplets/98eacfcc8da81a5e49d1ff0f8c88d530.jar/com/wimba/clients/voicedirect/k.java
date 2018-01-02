// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.voicedirect;

import com.hw.client.util.a;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import VT_6_1_0_11.h;
import java.awt.Color;
import java.awt.Component;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.ListModel;
import com.hw.client.util.c;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.ImageIcon;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;

public final class k extends JScrollPane implements ActionListener, MouseListener
{
    private ImageIcon a;
    private ImageIcon b;
    private ImageIcon c;
    private JPopupMenu d;
    private JMenuItem e;
    private m f;
    private e g;
    private JList h;
    private d i;
    
    public k(final e g) {
        this.g = g;
        this.a = com.hw.client.util.c.a("/images/voicedirect/list_icon_speaker.gif");
        this.b = com.hw.client.util.c.a("/images/voicedirect/list_icon_requester.gif");
        this.c = com.hw.client.util.c.a("/images/voicedirect/list_icon_participant.gif");
        this.i = new d();
        (this.h = new JList(this.i)).setBorder(new EmptyBorder(5, 5, 5, 5));
        this.h.setCellRenderer(new i(this));
        this.h.addMouseListener(this);
        this.d = new JPopupMenu();
        (this.e = new JMenuItem("Give microphone")).addActionListener(this);
        this.d.add(this.e);
        this.add(this.d);
        this.setBackground(Color.white);
        this.setViewportView(this.h);
        this.setHorizontalScrollBarPolicy(31);
        this.setBorder(VT_6_1_0_11.h.g());
    }
    
    public final d a() {
        return this.i;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.e) {
            this.g.a(this.f.a());
        }
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        com.hw.client.util.a.b("MemberList.mousePressed");
        if (!this.g.b()) {
            return;
        }
        if ((mouseEvent.getModifiers() & 0x4) != 0x0 || (mouseEvent.getModifiers() & 0x4) != 0x0 || (mouseEvent.getModifiers() & 0x8) != 0x0) {
            this.f = (m)this.i.getElementAt(this.h.locationToIndex(mouseEvent.getPoint()));
            if (this.f != null) {
                this.e.setEnabled(this.f.b());
                this.d.show(this, mouseEvent.getX(), mouseEvent.getY());
            }
        }
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
    
    static ImageIcon a(final k k) {
        return k.a;
    }
    
    static ImageIcon b(final k k) {
        return k.b;
    }
    
    static ImageIcon c(final k k) {
        return k.c;
    }
}
