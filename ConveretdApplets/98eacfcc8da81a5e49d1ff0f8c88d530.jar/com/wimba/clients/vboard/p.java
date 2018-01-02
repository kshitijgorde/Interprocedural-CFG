// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.vboard;

import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceDragEvent;
import java.io.IOException;
import java.awt.dnd.InvalidDnDOperationException;
import java.awt.datatransfer.Transferable;
import VT_6_1_0_11.r;
import java.awt.dnd.DragSource;
import java.io.FileWriter;
import java.io.File;
import VT_6_1_0_11.ca;
import com.hw.client.util.a;
import java.awt.dnd.DragGestureEvent;
import javax.swing.Icon;
import com.hw.client.util.c;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JSplitPane;
import javax.swing.border.Border;
import javax.swing.JScrollPane;
import javax.swing.text.html.parser.ParserDelegator;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.Component;
import javax.swing.JSeparator;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import VT_6_1_0_11.bJ;
import VT_6_1_0_11.l;
import javax.swing.JCheckBoxMenuItem;
import VT_6_1_0_11.du;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import VT_6_1_0_11.aL;
import VT_6_1_0_11.dz;
import javax.swing.JButton;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DragGestureListener;
import VT_6_1_0_11.U;

public final class p extends U implements DragGestureListener, DragSourceListener
{
    public JButton a;
    public JButton b;
    public JButton k;
    public JButton l;
    public JButton m;
    public JButton n;
    public JButton o;
    public JButton p;
    public JButton q;
    public JButton r;
    public JButton s;
    private dz t;
    private aL u;
    private h v;
    private JComboBox w;
    private JPanel z;
    private du A;
    private JCheckBoxMenuItem B;
    private JCheckBoxMenuItem C;
    
    public p(final n n, final du a) {
        super(n);
        this.A = a;
        this.a("/images/common/Wlogo-W_only-32_26.png");
        if (n.D()) {
            this.b(this.c);
        }
        else if (n.G()) {
            this.b(this.e);
        }
        else {
            this.b(this.d);
        }
        this.g().a();
        this.n();
        this.m();
    }
    
    protected final bJ h() {
        return new bJ(this.t());
    }
    
    protected final JButton i() {
        String toolTipText = null;
        this.j = super.i();
        if (this.t()) {
            toolTipText = this.c("btn_options_tooltip_podcaster");
        }
        else if (this.s()) {
            toolTipText = this.c("btn_options_tooltip_board");
        }
        else if (this.u()) {
            toolTipText = this.c("btn_options_tooltip_presentation");
        }
        this.j.setToolTipText(toolTipText);
        return this.j;
    }
    
    protected final JPopupMenu m() {
        final JPopupMenu m = super.m();
        (this.B = (JCheckBoxMenuItem)m.add(new JCheckBoxMenuItem(this.h.e("options_play_on_click")))).setSelected(false);
        (this.C = (JCheckBoxMenuItem)m.add(new JCheckBoxMenuItem(this.h.e("options_continuous_play")))).setSelected(false);
        if (this.s() || this.u()) {
            m.add(new JSeparator());
            final JMenuItem add;
            (add = m.add(new JMenuItem(this.h.e("options_expand_all")))).addActionListener(this.i);
            add.setActionCommand("ACTION_EXPAND_ALL");
            final JMenuItem add2;
            (add2 = m.add(new JMenuItem(this.h.e("options_collapse_all")))).addActionListener(this.i);
            add2.setActionCommand("ACTION_COLLAPSE_ALL");
            final JMenuItem add3;
            (add3 = m.add(new JMenuItem(this.h.e("options_refresh")))).addActionListener(this.i);
            add3.setActionCommand("ACTION_REFRESH");
            if (this.u()) {
                m.add(new JSeparator());
                final JMenuItem add4;
                (add4 = m.add(new JMenuItem(this.h.e("options_import")))).addActionListener(this.i);
                add4.setActionCommand("ACTION_IMPORT");
                final JMenuItem add5;
                (add5 = m.add(new JMenuItem(this.h.e("options_export")))).addActionListener(this.i);
                add5.setActionCommand("ACTION_EXPORT");
            }
        }
        return m;
    }
    
    protected final JComponent a() {
        this.t = new dz(this.h, this.A);
        new ParserDelegator();
        (this.u = new aL((n)this.h)).setBorder(VT_6_1_0_11.h.i());
        final JScrollPane topComponent;
        (topComponent = new JScrollPane(this.u)).setBorder(null);
        (this.v = new h(this.t, this.h.a(), this.u())).setBorder(VT_6_1_0_11.h.i());
        final JSplitPane splitPane;
        (splitPane = new JSplitPane(0)).setOpaque(false);
        splitPane.setForeground(VT_6_1_0_11.h.b());
        splitPane.setBackground(VT_6_1_0_11.h.b());
        splitPane.setTopComponent(topComponent);
        splitPane.setBottomComponent(this.v);
        splitPane.setDividerSize(8);
        splitPane.setBorder(null);
        final Dimension dimension = new Dimension(100, 50);
        this.v.setMinimumSize(dimension);
        topComponent.setMinimumSize(dimension);
        splitPane.setDividerLocation(230);
        if (!this.s()) {
            return splitPane;
        }
        (this.w = new JComboBox()).addActionListener(this.i);
        this.w.setActionCommand("ACTION_FILTER");
        (this.z = new JPanel(new BorderLayout())).setPreferredSize(new Dimension(500, 28));
        this.z.setBorder(null);
        this.z.setOpaque(false);
        this.z.add(new JLabel(this.h.a().getString("participants")), "West");
        this.z.add(this.w, "Center");
        final JPanel panel;
        (panel = new JPanel()).setLayout(new GridBagLayout());
        panel.add(this.z, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, 11, 1, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(splitPane, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
        return panel;
    }
    
    public final boolean c() {
        return this.B != null && this.B.isSelected();
    }
    
    public final boolean d() {
        return this.C != null && this.C.isSelected();
    }
    
    public final aL e() {
        return this.u;
    }
    
    public final dz o() {
        return this.t;
    }
    
    public final h p() {
        return this.v;
    }
    
    public final JComboBox q() {
        return this.w;
    }
    
    public final JPanel r() {
        return this.z;
    }
    
    protected final void b() {
        String s;
        String s2;
        String s3;
        String s4;
        if (this.s() || this.t()) {
            s = "/images/vboard/btn_new.png";
            s2 = "/images/vboard/btn_new_rollover.png";
            s3 = "/images/vboard/btn_new_disabled.png";
            s4 = "/images/vboard/btn_new_pressed.png";
        }
        else {
            s = "/images/presentation/btn_add.png";
            s2 = "/images/presentation/btn_add_rollover.png";
            s3 = "/images/presentation/btn_add_disabled.png";
            s4 = "/images/presentation/btn_add_pressed.png";
        }
        (this.a = VT_6_1_0_11.h.a(s, this.h.e("btn_new"), this.h.e("btn_new_tooltip"), "ACTION_NEW")).setDisabledIcon(com.hw.client.util.c.a(s3));
        this.a.setRolloverIcon(com.hw.client.util.c.a(s2));
        if (this.s()) {
            final String s5 = "/images/vboard/btn_reply.png";
            s2 = "/images/vboard/btn_reply_rollover.png";
            s3 = "/images/vboard/btn_reply_disabled.png";
            s4 = "/images/vboard/btn_reply_pressed.png";
            this.b = VT_6_1_0_11.h.a(s5, this.h.e("btn_reply"), this.h.e("btn_reply_tooltip"), "ACTION_REPLY");
        }
        else if (this.u()) {
            final String s6 = "/images/presentation/btn_comment.png";
            s2 = "/images/presentation/btn_comment_rollover.png";
            s3 = "/images/presentation/btn_comment_disabled.png";
            s4 = "/images/presentation/btn_comment_pressed.png";
            this.b = VT_6_1_0_11.h.a(s6, this.h.e("btn_comment"), this.h.e("btn_comment_tooltip"), "ACTION_REPLY");
        }
        else {
            (this.b = VT_6_1_0_11.h.a("/images/vboard/btn_reply.png", this.h.e("btn_reply"), this.h.e("btn_reply_tooltip"), "ACTION_REPLY")).setEnabled(false);
        }
        this.b.setDisabledIcon(com.hw.client.util.c.a(s3));
        this.b.setRolloverIcon(com.hw.client.util.c.a(s2));
        this.b.setPressedIcon(com.hw.client.util.c.a(s4));
        (this.k = VT_6_1_0_11.h.a("/images/vboard/btn_delete.png", this.h.e("btn_delete"), this.h.e("btn_delete_tooltip"), "ACTION_DELETE")).setDisabledIcon(com.hw.client.util.c.a("/images/vboard/btn_delete_disabled.png"));
        this.k.setRolloverIcon(com.hw.client.util.c.a("/images/vboard/btn_delete_rollover.png"));
        this.k.setPressedIcon(com.hw.client.util.c.a("/images/vboard/btn_delete_pressed.png"));
        (this.m = VT_6_1_0_11.h.a("/images/vboard/btn_forward.png", this.h.e("btn_forward"), this.h.e("btn_forward_tooltip"), "ACTION_FORWARD")).setDisabledIcon(com.hw.client.util.c.a("/images/vboard/btn_forward_disabled.png"));
        this.m.setRolloverIcon(com.hw.client.util.c.a("/images/vboard/btn_forward_rollover.png"));
        this.m.setPressedIcon(com.hw.client.util.c.a("/images/vboard/btn_forward_pressed.png"));
        String s7;
        String s8;
        String s9;
        String s10;
        if (this.s() || this.t()) {
            s7 = "/images/vboard/btn_edit.png";
            s8 = "/images/vboard/btn_edit_rollover.png";
            s9 = "/images/vboard/btn_edit_disabled.png";
            s10 = "/images/vboard/btn_edit_pressed.png";
        }
        else {
            s7 = "/images/presentation/btn_edit.png";
            s8 = "/images/presentation/btn_edit_rollover.png";
            s9 = "/images/presentation/btn_edit_disabled.png";
            s10 = "/images/presentation/btn_edit_pressed.png";
        }
        (this.l = VT_6_1_0_11.h.a(s7, this.h.e("btn_edit"), this.h.e("btn_edit_tooltip"), "ACTION_EDIT")).setDisabledIcon(com.hw.client.util.c.a(s9));
        this.l.setRolloverIcon(com.hw.client.util.c.a(s8));
        this.l.setPressedIcon(com.hw.client.util.c.a(s10));
        (this.n = VT_6_1_0_11.h.a("/images/vboard/btn_import.png", this.h.e("btn_import"), this.h.e("btn_import_tooltip"), "ACTION_IMPORT")).setDisabledIcon(com.hw.client.util.c.a("/images/vboard/btn_import_disabled.png"));
        this.n.setRolloverIcon(com.hw.client.util.c.a("/images/vboard/btn_import_rollover.png"));
        this.n.setPressedIcon(com.hw.client.util.c.a("/images/vboard/btn_import_pressed.png"));
        (this.o = VT_6_1_0_11.h.a("/images/vboard/btn_export.png", this.h.e("btn_export"), this.h.e("btn_export_tooltip"), "ACTION_EXPORT")).setDisabledIcon(com.hw.client.util.c.a("/images/vboard/btn_export_disabled.png"));
        this.o.setRolloverIcon(com.hw.client.util.c.a("/images/vboard/btn_export_rollover.png"));
        this.o.setPressedIcon(com.hw.client.util.c.a("/images/vboard/btn_export_pressed.png"));
        (this.p = VT_6_1_0_11.h.a("/images/vboard/btn_publish.png", this.h.e("btn_publish"), this.h.e("btn_publish_tooltip"), "ACTION_PUBLISH")).setDisabledIcon(com.hw.client.util.c.a("/images/vboard/btn_publish_disabled.png"));
        this.p.setRolloverIcon(com.hw.client.util.c.a("/images/vboard/btn_publish_rollover.png"));
        this.p.setPressedIcon(com.hw.client.util.c.a("/images/vboard/btn_publish_pressed.png"));
        (this.q = VT_6_1_0_11.h.a("/images/podcaster/btn_rss.png", this.h.e("btn_rss"), this.h.e("btn_rss_tooltip"), "ACTION_RSS")).setRolloverIcon(com.hw.client.util.c.a("/images/podcaster/btn_rss_rollover.png"));
        this.q.setPressedIcon(com.hw.client.util.c.a("/images/podcaster/btn_rss_pressed.png"));
        this.q.setDisabledIcon(com.hw.client.util.c.a("/images/podcaster/btn_rss_disabled.png"));
        (this.r = VT_6_1_0_11.h.a("/images/podcaster/btn_1_click.png", this.h.e("btn_subscribe"), this.h.e("btn_subscribe_tooltip"), "ACTION_SUBSCRIBE", this, this)).setRolloverIcon(com.hw.client.util.c.a("/images/podcaster/btn_1_click_rollover.png"));
        this.r.setPressedIcon(com.hw.client.util.c.a("/images/podcaster/btn_1_click_pressed.png"));
        this.r.setDisabledIcon(com.hw.client.util.c.a("/images/podcaster/btn_1_click_disabled.png"));
        (this.s = VT_6_1_0_11.h.a("/images/podcaster/btn_help.png", this.h.e("btn_rss_help"), this.h.e("btn_rss_help_tooltip"), "ACTION_RSS_HELP")).setRolloverIcon(com.hw.client.util.c.a("/images/podcaster/btn_help_rollover.png"));
        this.s.setPressedIcon(com.hw.client.util.c.a("/images/podcaster/btn_help_pressed.png"));
        this.s.setDisabledIcon(com.hw.client.util.c.a("/images/podcaster/btn_help_disabled.png"));
    }
    
    private boolean s() {
        return ((n)this.h).D();
    }
    
    private boolean t() {
        return ((n)this.h).G();
    }
    
    private boolean u() {
        return ((n)this.h).H();
    }
    
    public final void dragGestureRecognized(final DragGestureEvent dragGestureEvent) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VboardContentPane.dragGestureRecognized");
        }
        if (dragGestureEvent.getComponent() == this.r) {
            try {
                final String a = ca.a(((n)this.h).Q().toExternalForm(), "&", "&amp;");
                final String string = this.h.a().getString("options_audio_device_loading");
                final String string2 = this.h.a().getString("options_audio_device_loading");
                final String s = string;
                final String s2 = a;
                final File tempFile = File.createTempFile("myPodcast", ".pcast");
                final FileWriter fileWriter;
                (fileWriter = new FileWriter(tempFile)).write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<!DOCTYPE pcast PUBLIC \"-//Apple Computer//DTD PCAST 1.0//EN\" \"http://www.itunes.com/DTDs/pcast-1.0.dtd\">\n<pcast version=\"1.0\">\n\t<channel>\n\t\t<link rel=\"feed\" type=\"application/rss+xml\" href=\"" + s2 + "\"/>\n" + "\t\t<title>" + s + "</title>\n" + "\t\t<subtitle>" + string2 + "</subtitle>\n" + "\t\t</channel>\n" + "</pcast>\n");
                fileWriter.close();
                tempFile.deleteOnExit();
                dragGestureEvent.startDrag(DragSource.DefaultCopyNoDrop, new r(tempFile), this);
                ((n)this.h).c(true);
                this.r.doClick();
            }
            catch (InvalidDnDOperationException ex) {
                com.hw.client.util.a.e("Can't perform drag-and-drop action , cause : " + ex.getMessage());
            }
            catch (IOException ex2) {
                com.hw.client.util.a.e("Can't create podcast file : " + ex2.getMessage());
            }
        }
    }
    
    public final void dragEnter(final DragSourceDragEvent dragSourceDragEvent) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VboardContentPane.dragEnter");
        }
        if (dragSourceDragEvent.getDropAction() == 1) {
            dragSourceDragEvent.getDragSourceContext().setCursor(DragSource.DefaultCopyDrop);
            return;
        }
        dragSourceDragEvent.getDragSourceContext().setCursor(DragSource.DefaultCopyNoDrop);
    }
    
    public final void dragOver(final DragSourceDragEvent dragSourceDragEvent) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VboardContentPane.dragOver");
        }
    }
    
    public final void dropActionChanged(final DragSourceDragEvent dragSourceDragEvent) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VboardContentPane.dropActionChanged");
        }
        if (dragSourceDragEvent.getDropAction() == 1) {
            dragSourceDragEvent.getDragSourceContext().setCursor(DragSource.DefaultCopyDrop);
            return;
        }
        dragSourceDragEvent.getDragSourceContext().setCursor(DragSource.DefaultCopyNoDrop);
    }
    
    public final void dragExit(final DragSourceEvent dragSourceEvent) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VboardContentPane.dragExit");
        }
    }
    
    public final void dragDropEnd(final DragSourceDropEvent dragSourceDropEvent) {
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VboardContentPane.dragDropEnd");
        }
    }
}
