// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import com.eventim.common.transfer.Reply;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.awt.event.ItemEvent;
import java.util.Collection;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import javax.swing.ImageIcon;
import com.eventim.common.transfer.saalplan.SaalplanPromotionDetails;
import com.eventim.common.transfer.saalplan.SaalplanPromotionCodeDetails;
import com.eventim.applet.e;
import java.awt.Font;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Cursor;
import javax.swing.Icon;
import com.eventim.applet.a.i;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JComponent;
import com.eventim.applet.EventimApplet;
import javax.swing.JComboBox;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.event.MouseListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;

public final class l extends ag implements ActionListener, ItemListener, MouseListener
{
    private JTextArea a;
    private JPanel b;
    private JLabel c;
    private JTextField d;
    private List e;
    private boolean f;
    private JLabel g;
    private JLabel h;
    private JLabel i;
    private JComboBox j;
    private JLabel k;
    private EventimApplet l;
    private int m;
    private JComponent n;
    private JPanel o;
    
    public l(final EventimApplet l, final boolean b, final Color color, final Color color2, final Color color3) {
        super(true, false, color, color2, color3);
        this.e = new ArrayList();
        this.l = l;
        (this.b = new JPanel(new BorderLayout())).setBackground(l.d().B());
        this.m = l.e().e();
        final SaalplanPromotionDetails[] a;
        final Object[] array;
        (array = new Object[(a = a(a(l.e().d(), l.e()))).length + 1])[0] = l.d().A().a("select_a_promotion");
        int n = 1;
        int selectedIndex = 0;
        for (int i = 0; i < a.length; ++i) {
            final SaalplanPromotionDetails saalplanPromotionDetails = a[i];
            array[n] = new q(saalplanPromotionDetails);
            if (this.m == saalplanPromotionDetails.getId()) {
                selectedIndex = n;
            }
            ++n;
        }
        (this.j = new JComboBox((E[])array)).setSelectedIndex(selectedIndex);
        this.j.addItemListener(this);
        this.j.setMinimumSize(new Dimension(150, 20));
        this.j.setPreferredSize(new Dimension(150, 20));
        this.j.setMaximumSize(new Dimension(150, 20));
        this.g = new JLabel(l.d().A().a("enter_promotion_code"));
        this.d = new JTextField();
        final ImageIcon a2;
        if ((a2 = com.eventim.applet.a.i.a("apply_promo.gif", l.d())) != null && a2.getIconWidth() > 0) {
            this.n = new JLabel(a2);
        }
        else {
            (this.n = new j(l.d().A().a("submit"), l.d().T() ? Color.white : Color.black, l.d().D(), l.d().C(), l)).setForeground(Color.white);
            this.n.setBackground(l.d().B());
        }
        this.n.setCursor(Cursor.getPredefinedCursor(12));
        this.n.addMouseListener(this);
        final Box horizontalBox;
        (horizontalBox = Box.createHorizontalBox()).add(this.d);
        horizontalBox.add(Box.createHorizontalStrut(1));
        horizontalBox.add(this.n);
        (this.o = new JPanel(new BorderLayout())).add(this.g, "North");
        this.o.add(horizontalBox, "South");
        (this.a = new JTextArea()).setEditable(false);
        this.a.setLineWrap(true);
        this.a.setWrapStyleWord(true);
        this.a.setFont(this.a.getFont().deriveFont(1));
        this.a.setColumns(14);
        final ImageIcon a3;
        if ((a3 = com.eventim.applet.a.i.a("clear_promo.gif", l.d())) != null && a3.getIconWidth() > 0) {
            this.c = new JLabel(a3);
        }
        else {
            this.c = new ah(l.d().A().a("clear_promotion"), l.d().T() ? Color.white : Color.black, l.d().D(), l.d().C(), l);
        }
        this.c.setCursor(Cursor.getPredefinedCursor(12));
        this.c.setBorder(new EmptyBorder(6, 0, 0, 0));
        this.c.addMouseListener(this);
        this.h = new JLabel("");
        this.i = new JLabel("");
        final Font font = this.h.getFont();
        final Font font2 = new Font(font.getName(), font.getStyle(), font.getSize() - 2);
        this.h.setFont(font2);
        this.i.setFont(font2);
        this.h.setForeground(Color.red);
        this.i.setForeground(Color.red);
        this.k = new JLabel("<html><b>" + l.d().A().a("promotion_panel_header") + "</b></html>");
        if (l.d().T()) {
            this.k.setForeground(Color.white);
        }
        this.d().add(this.k);
        this.b().add(this.b);
        this.b().setBackground(l.d().B());
        this.setMinimumSize(new Dimension(160, 120));
        this.setBorder(new EmptyBorder(0, 0, 4, 0));
        if (com.eventim.applet.e.g(this.m)) {
            this.f();
            return;
        }
        if (l.e().f(this.m) && l.e().b() != this.m) {
            this.i();
            return;
        }
        final Iterator<SaalplanPromotionCodeDetails> iterator = (Iterator<SaalplanPromotionCodeDetails>)l.e().b(new Integer(this.m)).iterator();
        while (iterator.hasNext()) {
            this.e.add(iterator.next().getTdlCode());
        }
        this.h();
    }
    
    static EventimApplet a(final l l) {
        return l.l;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.l.setCursor(Cursor.getPredefinedCursor(3));
        this.l.b(this.m, -1);
        this.l.setCursor(Cursor.getDefaultCursor());
    }
    
    private void e() {
        this.l.p();
        this.l.c().clear();
        this.h();
        this.l.b(this.m, -1);
        this.revalidate();
    }
    
    private void f() {
        this.k.setText("<html><b>" + this.l.d().A().a("promotion_panel_header") + "</b></html>");
        this.b.removeAll();
        this.b.add(this.j, "North");
        this.revalidate();
    }
    
    private void g() {
        this.i();
        this.l.b(-1, -1);
    }
    
    private void h() {
        this.k.setText("<html><b>" + this.l.d().A().a("activated_promotion_panel_header") + "</b></html>");
        this.b.removeAll();
        this.a.setText(this.l.e().e(this.m));
        this.b.add(this.a, "North");
        if (!this.e.isEmpty()) {
            final JPanel panel;
            (panel = new JPanel(new GridLayout(0, 1))).setBackground(this.l.d().B());
            final Iterator<Object> iterator = this.e.iterator();
            while (iterator.hasNext()) {
                final JLabel label;
                (label = new JLabel("   " + this.l.d().A().a("code") + ": " + iterator.next())).setBackground(this.l.d().B());
                panel.add(label);
            }
            this.b.add(panel, "Center");
        }
        this.b.add(this.c, "South");
    }
    
    private void i() {
        this.f = true;
        this.k.setText("<html><b>" + this.l.d().A().a("promotion_panel_header") + "</b></html>");
        this.d.setText("");
        this.g.setText(this.l.d().A().a("enter_promotion_code"));
        this.b.removeAll();
        this.b.add(this.j, "North");
        final JPanel panel = new JPanel(new BorderLayout());
        final JPanel panel2;
        (panel2 = new JPanel(new GridLayout(0, 1))).setBackground(this.l.d().B());
        panel2.setBorder(new EmptyBorder(6, 0, 0, 0));
        panel2.add(this.g);
        if (this.h.getText().length() > 0) {
            panel2.add(this.h);
            if (this.i.getText().length() > 0) {
                panel2.add(this.i);
            }
        }
        panel.add(panel2, "North");
        panel.add(this.o, "South");
        panel.setBackground(this.l.d().B());
        this.b.add(panel, "Center");
        this.b.add(this.c, "South");
        this.revalidate();
    }
    
    private static SaalplanPromotionDetails[] a(final Collection collection, final e e) {
        final ArrayList list = new ArrayList<SaalplanPromotionDetails>();
        if (collection != null) {
            for (final SaalplanPromotionDetails saalplanPromotionDetails : collection) {
                if (e.a((int)saalplanPromotionDetails.getId())) {
                    if (e.j(saalplanPromotionDetails.getId())) {
                        if (e.c(saalplanPromotionDetails.getId()).isEmpty()) {
                            continue;
                        }
                        list.add(saalplanPromotionDetails);
                    }
                    else {
                        list.add(saalplanPromotionDetails);
                    }
                }
            }
        }
        return (SaalplanPromotionDetails[])list.toArray(new SaalplanPromotionDetails[list.size()]);
    }
    
    public final boolean a() {
        return this.f;
    }
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        this.l.setCursor(Cursor.getPredefinedCursor(3));
        if (itemEvent.getStateChange() == 1) {
            this.f = false;
            this.h.setText("");
            this.i.setText("");
            if (itemEvent.getItem() instanceof q) {
                this.m = ((q)itemEvent.getItem()).a().getId();
            }
            else {
                this.m = 0;
            }
            this.l.e().m(this.m);
            if (com.eventim.applet.e.g(this.m)) {
                this.l.b(this.m, -1);
                this.f();
            }
            else if (this.l.e().h(this.m)) {
                this.e();
            }
            else if (this.l.e().f(this.m)) {
                this.g();
            }
        }
        this.l.setCursor(Cursor.getDefaultCursor());
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() != this.n) {
            if (mouseEvent.getSource() == this.c) {
                final int m = this.m;
                if (!this.f) {
                    this.l.p();
                    this.l.c().clear();
                }
                else {
                    this.f = false;
                }
                this.e.clear();
                this.j.setSelectedIndex(0);
                this.f();
                this.m = 0;
                this.l.b(this.m, -1);
                if (this.l.e().f(m)) {
                    SwingUtilities.invokeLater(new k(this, m));
                }
            }
            return;
        }
        this.l.setCursor(Cursor.getPredefinedCursor(3));
        this.h.setText("");
        this.i.setText("");
        final String trim;
        boolean b;
        if ((trim = this.d.getText().trim()).length() == 0) {
            this.h.setText(this.l.d().A().a("no_code_entered_1"));
            if (this.l.d().A().b("no_code_entered_2")) {
                this.i.setText(this.l.d().A().a("no_code_entered_2"));
            }
            b = false;
        }
        else {
            this.g.setText(this.l.d().A().a("checking_promotion_code"));
            final Reply a = this.l.a(trim);
            EventimApplet.a("Reply code: " + a.ret + " text: " + a.text, 2);
            if (a.ret > 0) {
                this.e.add(trim);
                this.l.e().a((SaalplanPromotionCodeDetails[])a.objects[0]);
                this.l.e().k(this.m);
                this.l.b(this.m, -1);
                this.h.setText("");
                this.i.setText("");
                b = true;
            }
            else if (a.ret == -2025) {
                this.h.setText(this.l.d().A().a("promotion_code_cannot_be_used_for_sale_1"));
                this.i.setText(this.l.d().A().a("promotion_code_cannot_be_used_for_sale_2"));
                b = false;
            }
            else if (a.ret == -2313) {
                this.h.setText(this.l.d().A().a("invalid_promotion_code_1"));
                this.i.setText(this.l.d().A().a("invalid_promotion_code_2"));
                b = false;
            }
            else {
                this.h.setText(this.l.d().A().a("system_difficulty_1"));
                this.i.setText(this.l.d().A().a("system_difficulty_2"));
                b = false;
            }
        }
        final boolean b2 = b;
        this.l.setCursor(Cursor.getDefaultCursor());
        if (b2) {
            this.f = false;
            this.e();
            return;
        }
        this.g();
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    private static SaalplanPromotionDetails[] a(final SaalplanPromotionDetails[] array) {
        final ArrayList<Object> list = (ArrayList<Object>)new ArrayList<SaalplanPromotionDetails>();
        for (int i = 0; i < array.length; ++i) {
            final SaalplanPromotionDetails saalplanPromotionDetails;
            final String lowerCase = (saalplanPromotionDetails = array[i]).getTdlName().toLowerCase();
            boolean b = false;
            for (int j = 0; j < list.size(); ++j) {
                if (lowerCase.compareTo(list.get(j).getTdlName().toLowerCase()) < 0) {
                    list.add(j, saalplanPromotionDetails);
                    b = true;
                    break;
                }
            }
            if (!b) {
                list.add(saalplanPromotionDetails);
            }
        }
        return list.toArray(new SaalplanPromotionDetails[list.size()]);
    }
}
