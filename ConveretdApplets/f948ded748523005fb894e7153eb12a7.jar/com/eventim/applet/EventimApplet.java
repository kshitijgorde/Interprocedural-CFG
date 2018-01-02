// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet;

import javax.swing.event.ListSelectionEvent;
import com.eventim.applet.b.ai;
import javax.swing.event.TableModelListener;
import javax.swing.ImageIcon;
import javax.swing.event.TableModelEvent;
import java.util.Enumeration;
import com.eventim.common.transfer.StehplatzReservierung;
import com.eventim.common.transfer.saalplan.GraphDetails;
import com.eventim.common.transfer.saalplan.TextDetails;
import com.eventim.common.transfer.saalplan.PixPoint;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.awt.Insets;
import java.awt.event.AdjustmentListener;
import javax.swing.table.TableCellRenderer;
import com.eventim.applet.b.ad;
import java.awt.event.MouseMotionListener;
import javax.swing.table.TableModel;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import com.eventim.applet.a.i;
import javax.swing.Box;
import javax.swing.UIManager;
import com.eventim.common.transfer.saalplan.SectionDetails;
import java.net.URL;
import java.awt.Shape;
import java.awt.geom.Point2D;
import com.eventim.applet.a.c;
import java.util.Arrays;
import java.util.Collection;
import com.eventim.common.transfer.saalplan.SeatplanObjects;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import com.eventim.common.transfer.saalplan.ReCaptchaDetails;
import com.eventim.common.transfer.saalplan.SaalplanSalesGroupPromotionDetails;
import com.eventim.common.transfer.saalplan.SaalplanPromotionDetails;
import com.eventim.common.transfer.saalplan.SaalplanPromotionCodeDetails;
import com.eventim.common.transfer.saalplan.SaalplanAffiliateDetails;
import com.eventim.common.transfer.saalplan.SaalplanEventreiheDetails;
import com.eventim.common.transfer.saalplan.SaalplanEventDetails;
import com.eventim.applet.b.p;
import com.eventim.common.transfer.saalplan.SaalplanRabattstufeDetails;
import com.eventim.common.transfer.saalplan.SaalplanPlatzkategorieDetails;
import java.util.Hashtable;
import com.eventim.common.transfer.Request;
import com.eventim.common.transfer.Reply;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.Iterator;
import com.eventim.applet.b.o;
import javax.swing.SwingUtilities;
import java.util.Comparator;
import com.eventim.applet.a.f;
import com.eventim.applet.a.b;
import javax.swing.JCheckBox;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import com.eventim.applet.b.t;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import com.eventim.applet.b.ae;
import com.eventim.applet.b.m;
import java.text.DecimalFormat;
import javax.swing.JScrollPane;
import com.eventim.applet.b.n;
import javax.swing.JComponent;
import javax.swing.JLabel;
import com.eventim.applet.b.r;
import com.eventim.applet.b.ag;
import javax.swing.JTable;
import com.eventim.applet.b.e;
import com.eventim.applet.b.u;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.util.Map;
import java.util.Set;
import com.eventim.applet.b.h;
import javax.swing.JApplet;

public class EventimApplet extends JApplet implements h
{
    private static Set a;
    private static Map b;
    private JPanel c;
    private JComboBox d;
    private JTextPane e;
    private JButton f;
    private boolean g;
    private u h;
    private e i;
    private JTable j;
    private ag k;
    private Set l;
    private static Class m;
    private static Class n;
    private int o;
    private Integer p;
    private Set q;
    private Set r;
    private l s;
    private r t;
    private JLabel u;
    private JPanel v;
    private JPanel w;
    private JPanel x;
    private static int y;
    private JComponent z;
    private long A;
    private long B;
    private JTable C;
    private n D;
    private ag E;
    private JScrollPane F;
    private static Map G;
    private DecimalFormat H;
    private com.eventim.applet.b.l I;
    private JPanel J;
    private com.eventim.applet.e K;
    private boolean L;
    private JPanel M;
    private JPanel N;
    private ag O;
    private JScrollPane P;
    private Map Q;
    private m R;
    private ae S;
    private JTextPane T;
    private boolean U;
    private boolean V;
    private boolean W;
    private Set X;
    private List Y;
    private Map Z;
    private long aa;
    private long ab;
    private JLabel ac;
    private int ad;
    private JButton ae;
    private JButton af;
    
    static {
        EventimApplet.y = 2;
        EventimApplet.b = A();
        final HashSet<String> set;
        (set = new HashSet<String>()).add("CZK");
        set.add("SEK");
        EventimApplet.a = Collections.unmodifiableSet((Set<?>)set);
        EventimApplet.G = new HashMap();
    }
    
    public EventimApplet() {
        this.Q = new LinkedHashMap(2);
        this.p = new Integer(0);
        this.V = false;
        this.X = new HashSet();
        this.Y = new ArrayList();
        this.q = new HashSet();
        this.r = new HashSet();
        this.o = 1;
        this.l = new HashSet();
        this.U = false;
        this.L = false;
        this.W = false;
        this.H = new DecimalFormat("#0.00");
        this.g = false;
    }
    
    static List a(final EventimApplet eventimApplet) {
        return eventimApplet.Y;
    }
    
    static l b(final EventimApplet eventimApplet) {
        return eventimApplet.s;
    }
    
    static e c(final EventimApplet eventimApplet) {
        return eventimApplet.i;
    }
    
    static void d(EventimApplet eventimApplet) {
        final t b;
        if ((b = (eventimApplet = eventimApplet).b()) != null) {
            if (eventimApplet.n()) {
                final BufferedImage b2;
                if (eventimApplet.S != null && (b2 = eventimApplet.S.b()) != null) {
                    eventimApplet.t.a(0, eventimApplet.P.getViewport().getViewRect(), b2.getWidth(), b2.getHeight(), b.h());
                }
                return;
            }
            final BufferedImage b3;
            if (eventimApplet.R != null && (b3 = eventimApplet.R.b()) != null) {
                eventimApplet.t.a(r.b, eventimApplet.P.getViewport().getViewRect(), b3.getWidth(), b3.getHeight(), b.h());
            }
        }
    }
    
    static ae a(final EventimApplet eventimApplet, final ae s) {
        return eventimApplet.S = s;
    }
    
    static m e(final EventimApplet eventimApplet) {
        return eventimApplet.R;
    }
    
    static m a(final EventimApplet eventimApplet, final m r) {
        return eventimApplet.R = r;
    }
    
    static void f(final EventimApplet eventimApplet) {
        eventimApplet.B();
    }
    
    static void a(final EventimApplet eventimApplet, final Integer n, final Runnable runnable) {
        eventimApplet.a(n, true, runnable);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.setCursor(Cursor.getPredefinedCursor(3));
        if ("zoom in".equals(actionEvent.getActionCommand())) {
            final t b = this.b();
            this.P.remove(b);
            final double[] c = this.c(b);
            b.a(true);
            this.P.setViewportView(b);
            this.a(c, b);
            this.b(b);
        }
        else if ("zoom out".equals(actionEvent.getActionCommand())) {
            final t b2 = this.b();
            this.P.remove(b2);
            final double[] c2 = this.c(b2);
            b2.a(false);
            this.P.setViewportView(b2);
            this.a(c2, b2);
            this.b(b2);
        }
        else if ("back".equals(actionEvent.getActionCommand())) {
            this.f.setVisible(false);
            this.getLayeredPane().remove(this.f);
            this.a(new Integer(0), null);
        }
        else if ("adj seats chk box".equals(actionEvent.getActionCommand())) {
            if (((JCheckBox)actionEvent.getSource()).isSelected()) {
                this.U = true;
                this.a(this.o = (int)this.d.getSelectedItem(), -1, true);
                this.e.setEnabled(true);
                this.d.setEnabled(true);
            }
            else {
                this.U = false;
                this.a(this.o = 1, -1, true);
                this.e.setEnabled(false);
                this.d.setEnabled(false);
            }
        }
        else if ("num adj seats changed".equals(actionEvent.getActionCommand())) {
            if (this.U) {
                this.a(this.o = (int)this.d.getSelectedItem(), -1, true);
            }
        }
        else if ("message ok".equals(actionEvent.getActionCommand())) {
            this.z.setVisible(false);
            this.getLayeredPane().remove(this.z);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    private void b(final b b) {
        if (this.a(b, true)) {
            if (b.f_()) {
                this.a((f)b);
            }
            this.C();
        }
    }
    
    private void a(final List list) {
        Collections.sort((List<Object>)list, new com.eventim.applet.h());
        boolean b = false;
        for (int i = 0; i < list.size(); ++i) {
            final b b2 = list.get(i);
            if (this.a(b2, false)) {
                b = true;
                if (b2.f_()) {
                    this.a((f)b2);
                }
            }
        }
        if (b) {
            this.C();
        }
    }
    
    private void r() {
        int preferredWidth = SwingUtilities.computeStringWidth(this.getGraphics().getFontMetrics(), this.i.getColumnName(1)) + 6;
        for (int i = 0; i < this.i.getRowCount(); ++i) {
            final int n;
            if ((n = SwingUtilities.computeStringWidth(this.getGraphics().getFontMetrics(), this.i.getValueAt(i, 1).toString()) + 6) > preferredWidth) {
                preferredWidth = n;
            }
        }
        int preferredWidth2 = SwingUtilities.computeStringWidth(this.getGraphics().getFontMetrics(), this.i.getColumnName(4)) + 6;
        final Iterator<o> iterator = (Iterator<o>)this.Y.iterator();
        while (iterator.hasNext()) {
            o o;
            for (int n2 = ((o = iterator.next()).getItemCount() > 1) ? 15 : 2, j = 0; j < o.getItemCount(); ++j) {
                final int n3;
                if ((n3 = SwingUtilities.computeStringWidth(this.getGraphics().getFontMetrics(), o.getItemAt(j).toString()) + n2) > preferredWidth2) {
                    preferredWidth2 = n3;
                }
            }
        }
        if (preferredWidth > 256 || preferredWidth2 > 160) {
            int preferredWidth3 = SwingUtilities.computeStringWidth(this.getGraphics().getFontMetrics(), this.i.getColumnName(2)) + 6;
            for (int k = 0; k < this.i.getRowCount(); ++k) {
                final int n4;
                if ((n4 = SwingUtilities.computeStringWidth(this.getGraphics().getFontMetrics(), this.i.getValueAt(k, 2).toString()) + 6) > preferredWidth3) {
                    preferredWidth3 = n4;
                }
            }
            int preferredWidth4 = SwingUtilities.computeStringWidth(this.getGraphics().getFontMetrics(), this.i.getColumnName(5)) + 6;
            for (int l = 0; l < this.i.getRowCount(); ++l) {
                final int n5;
                if ((n5 = SwingUtilities.computeStringWidth(this.getGraphics().getFontMetrics(), this.i.getValueAt(l, 5).toString()) + 6) > preferredWidth4) {
                    preferredWidth4 = n5;
                }
            }
            final int computeStringWidth = SwingUtilities.computeStringWidth(this.getGraphics().getFontMetrics(), this.i.getColumnName(6));
            this.j.getColumnModel().getColumn(1).setPreferredWidth(preferredWidth);
            this.j.getColumnModel().getColumn(2).setPreferredWidth(preferredWidth3);
            this.j.getColumnModel().getColumn(4).setPreferredWidth(preferredWidth2);
            this.j.getColumnModel().getColumn(5).setPreferredWidth(preferredWidth4);
            this.j.getColumnModel().getColumn(6).setPreferredWidth(computeStringWidth);
        }
    }
    
    private boolean b(final Integer n) {
        final List b = this.i.b();
        for (int i = 0; i < b.size(); ++i) {
            if (!b.get(i).equals(n)) {
                return true;
            }
        }
        return false;
    }
    
    public final void a(final int n, final int n2) {
        final EventimApplet eventimApplet = this;
        final Rectangle bounds = (this = this).P.getViewport().getBounds();
        final BufferedImage b = this.b().b();
        eventimApplet.a(new Point((int)Math.min(Math.max(n - bounds.width / 2, 0.0), Math.max(b.getWidth() - bounds.getWidth(), 0.0)), (int)Math.min(Math.max(n2 - bounds.height / 2, 0.0), Math.max(b.getHeight() - bounds.getHeight(), 0.0))));
    }
    
    private static boolean a(int n, int n2, final int[] array) {
        n = ((n == 0 || k.a(n, array)) ? 1 : 0);
        n2 = ((n2 == 0 || !k.a(n2, array)) ? 1 : 0);
        return n != 0 && n2 != 0;
    }
    
    private int a(final f f, final int n) {
        final List a = this.a(f, true, this.q.contains(new Long(f.e_())));
        boolean b = true;
        for (int i = 0; i < a.size(); ++i) {
            final f f2 = a.get(i);
            if (f.e_() == f2.e_() && f.l() == f2.l() + n) {
                b = false;
                break;
            }
        }
        if (!b) {
            int j = 0;
            while (j < a.size()) {
                final f f3 = a.get(j);
                if (f.e_() == f3.e_() && f.l() == f3.l() + (n << 1)) {
                    if (this.c(f, n << 1) != null) {
                        return -1;
                    }
                    return 1;
                }
                else {
                    ++j;
                }
            }
            return 0;
        }
        return 2;
    }
    
    public final Reply a(final String s) {
        final Hashtable u;
        Request.setCommand(u = u(), 92);
        u.put("SessionId", this.s.H());
        u.put("sprache", this.s.v());
        u.put("EventId", new Integer(this.s.m()));
        u.put("promottion_code", (s == null) ? "" : s);
        u.put("promotion_id", new Integer(this.K.e()));
        Request.setCompressionType(u, 1);
        return this.a(u);
    }
    
    private boolean b(f f, final int n) {
        boolean b = true;
        while (b) {
            final f c;
            if ((c = this.c(f, n)) != null) {
                f = c;
            }
            else {
                b = false;
            }
        }
        return this.a(f, n) == 2;
    }
    
    private static Class b(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    private SaalplanPlatzkategorieDetails[] a(final SaalplanPlatzkategorieDetails[] array, final int[] array2) {
        int n = 0;
        final Hashtable<Integer, SaalplanPlatzkategorieDetails> hashtable = new Hashtable<Integer, SaalplanPlatzkategorieDetails>();
        String string = "";
        for (int i = 0; i < array2.length; ++i) {
            string = string + array2[i] + ", ";
        }
        for (int j = 0; j < array.length; ++j) {
            if (a(array[j].getExclusiveAffiliatePoolId(), array[j].getGesperrteAffiliatePoolId(), array2)) {
                a("Platzkategorie darf angezeigt werden: ExID " + array[j].getExclusiveAffiliatePoolId() + ", GespID " + array[j].getGesperrteAffiliatePoolId() + " (" + string + ")", 0);
                final SaalplanRabattstufeDetails[] a;
                if ((a = this.a(array[j].getRabattstufen(), array2)).length > 0) {
                    for (int k = 0; k < a.length; ++k) {
                        this.K.a(new Integer(a[k].getPrId()), new Long(array[j].getTdlPreisklasseId()));
                    }
                    array[j].setRabattstufen(a);
                    hashtable.put(new Integer(n), array[j]);
                    ++n;
                }
            }
            else {
                a("Platzkategorie darf nicht angezeigt werden: ExID " + array[j].getExclusiveAffiliatePoolId() + ", GespID " + array[j].getGesperrteAffiliatePoolId() + " (" + string + ")", 0);
            }
        }
        final SaalplanPlatzkategorieDetails[] array3 = new SaalplanPlatzkategorieDetails[hashtable.size()];
        for (int l = 0; l < hashtable.size(); ++l) {
            array3[l] = hashtable.get(new Integer(l));
        }
        return array3;
    }
    
    private SaalplanRabattstufeDetails[] a(final SaalplanRabattstufeDetails[] array, final int[] array2) {
        int n = 0;
        final Hashtable<Integer, SaalplanRabattstufeDetails> hashtable = new Hashtable<Integer, SaalplanRabattstufeDetails>();
        String string = "";
        for (int i = 0; i < array2.length; ++i) {
            string = string + array2[i] + ", ";
        }
        for (int j = 0; j < array.length; ++j) {
            if (a(array[j].getExclusiveAffiliatePoolId(), array[j].getGesperrteAffiliatePoolId(), array2)) {
                hashtable.put(new Integer(n), array[j]);
                a("Rabattstufe darf angezeigt werden: ExID " + array[j].getExclusiveAffiliatePoolId() + ", GespID " + array[j].getGesperrteAffiliatePoolId() + " (" + string + ")", 0);
                ++n;
            }
            else {
                a("Rabattstufe darf nicht angezeigt werden: ExID " + array[j].getExclusiveAffiliatePoolId() + ", GespID " + array[j].getGesperrteAffiliatePoolId() + " (" + string + ")", 0);
            }
        }
        final SaalplanRabattstufeDetails[] array3 = new SaalplanRabattstufeDetails[hashtable.size()];
        for (int k = 0; k < hashtable.size(); ++k) {
            array3[k] = hashtable.get(new Integer(k));
        }
        return array3;
    }
    
    private int s() {
        int n = 0;
        final List b = this.i.b();
        for (int i = 0; i < b.size(); ++i) {
            if (com.eventim.applet.e.i(b.get(i))) {
                ++n;
            }
        }
        return n;
    }
    
    private int a(final Long n) {
        int n2 = 0;
        for (int i = 0; i < this.Y.size(); ++i) {
            if (((p)((o)this.Y.get(i)).getSelectedItem()).a().getId() == (long)n) {
                ++n2;
            }
        }
        return n2;
    }
    
    private boolean t() {
        try {
            a("EventDetails holen ... ", 2);
            final Reply x = this.x();
            a("EventDetails geholt. ", 3);
            if (x.ret <= 0) {
                a("Fehler beim Reply (EventDetails) empfangen: " + x.text, 3);
                return false;
            }
            a("R\u00fcckgabe: " + x.objects[0].getClass().getName(), 0);
            this.s.a = (SaalplanEventDetails)x.objects[0];
            final SaalplanEventreiheDetails saalplanEventreiheDetails = (SaalplanEventreiheDetails)x.objects[1];
            final SaalplanAffiliateDetails saalplanAffiliateDetails = (SaalplanAffiliateDetails)x.objects[2];
            final SaalplanPromotionCodeDetails[] array = (SaalplanPromotionCodeDetails[])x.objects[3];
            final SaalplanPromotionDetails[] array2 = (SaalplanPromotionDetails[])x.objects[4];
            final SaalplanSalesGroupPromotionDetails[] array3 = (SaalplanSalesGroupPromotionDetails[])x.objects[5];
            final ReCaptchaDetails reCaptchaDetails = (ReCaptchaDetails)x.objects[6];
            final SaalplanPromotionDetails[] a = a(array2, array3);
            this.s.b(saalplanAffiliateDetails.getTicketPriceDisplayMode());
            if (this.s.a.tdlSeatplanDetails.sectionDetails.length > 1) {
                this.ad = 1;
            }
            else {
                this.ad = 0;
            }
            this.K = new com.eventim.applet.e(this.s.a, a, array3, array, this);
            if (this.s.a != null && this.s.a.getMaxmenge() > 0 && this.s.a.getMaxmenge() < this.s.z()) {
                this.s.a(this.s.a.getMaxmenge());
            }
            if (saalplanEventreiheDetails != null) {
                a("Eventreihendetails: " + saalplanEventreiheDetails.toString(), 0);
                if (saalplanEventreiheDetails.getMaxmenge() > 0 && saalplanEventreiheDetails.getMaxmenge() < this.s.z()) {
                    this.s.a(saalplanEventreiheDetails.getMaxmenge());
                }
            }
            if (saalplanAffiliateDetails != null) {
                a("AffiliateDetails: " + saalplanAffiliateDetails.toString(), 0);
                if (saalplanAffiliateDetails.getMaxmenge() > 0 && saalplanAffiliateDetails.getMaxmenge() < this.s.z()) {
                    this.s.a(saalplanAffiliateDetails.getMaxmenge());
                }
            }
            if (this.s.a != null && this.s.a.getTdlMaxTickets() > 0 && this.s.a.getTdlMaxTickets() < this.s.z()) {
                this.s.a(this.s.a.getTdlMaxTickets());
            }
            this.s.c(Math.min(this.s.z(), this.s.S()));
            this.K.l(this.s.z());
            this.K.n(this.s.a.getTdlMaxTickets());
            final boolean b = (saalplanAffiliateDetails.getCaptchaMode() & 0x2) != 0x0;
            this.s.a(b);
            if (b) {
                this.s.a(reCaptchaDetails);
            }
            a("Reply f\u00fcr EventDetails empfangen: " + x.text, 2);
            final int[] affiliatePoolIds = this.s.a.tdlSeatplanDetails.affiliateDetails.getAffiliatePoolIds();
            String string = "";
            a("AffiliatePool: " + this.s.a.tdlSeatplanDetails.affiliateDetails.getAffiliatePoolList(), 0);
            for (int i = 0; i < affiliatePoolIds.length; ++i) {
                string = string + affiliatePoolIds[i] + ", ";
            }
            if (!a(this.s.a.getExclusiveAffiliatePoolId(), this.s.a.getGesperrteAffiliatePoolId(), affiliatePoolIds)) {
                a("Event darf nicht angezeigt werden: ExID " + this.s.a.getExclusiveAffiliatePoolId() + ", GespID " + this.s.a.getGesperrteAffiliatePoolId() + " (" + string + ")", 0);
                return false;
            }
            a("Event darf angezeigt werden: ExID " + this.s.a.getExclusiveAffiliatePoolId() + ", GespID " + this.s.a.getGesperrteAffiliatePoolId() + " (" + string + ")", 0);
            final SaalplanPlatzkategorieDetails[] a2;
            if ((a2 = this.a(this.s.a.tdlSeatplanDetails.platzkategorieDetails, affiliatePoolIds)).length == 0) {
                a("Es d\u00fcrfen keine Platzkategorien angezeigt werden", 3);
                return false;
            }
            this.K.a(a2);
            a("Es sind Platzkategorien vorhanden", 2);
        }
        catch (Exception ex2) {
            final StringBuffer append = new StringBuffer().append("Fehler beim Reply (EventDetails) empfangen: ");
            final Exception ex = ex2;
            final StringWriter stringWriter = new StringWriter();
            ex.printStackTrace(new PrintWriter(stringWriter));
            a(append.append(stringWriter.toString()).toString(), 4);
            return false;
        }
        this.s.a();
        String s = this.s.P() + ", " + this.s.Q();
        if (SwingUtilities.computeStringWidth(this.ac.getFontMetrics(this.ac.getFont().deriveFont(1)), s) > 425) {
            final String p = this.s.P();
            if (SwingUtilities.computeStringWidth(this.ac.getFontMetrics(this.ac.getFont().deriveFont(1)), p) <= 425) {
                s = p;
            }
            else {
                boolean b2 = false;
                int n = 2;
                while (!b2) {
                    s = p.substring(0, p.length() - n) + "...";
                    if (SwingUtilities.computeStringWidth(this.ac.getFontMetrics(this.ac.getFont().deriveFont(1)), s) <= 425) {
                        b2 = true;
                    }
                    else {
                        n += 2;
                    }
                }
            }
        }
        this.ac.setText("<html><b>" + s + "</b></html>");
        this.M.remove(this.t);
        if (this.ad == 1) {
            this.t = new r(com.eventim.applet.b.r.a, true, this.s.C(), this.s.D(), this.s.t(), this.s.u(), this.s.d(), this);
        }
        else {
            this.t = new r(0, true, this.s.C(), this.s.D(), this.s.t(), this.s.u(), this.s.d(), this);
        }
        this.t.setBackground(this.s.d());
        this.t.setMinimumSize(new Dimension(160, 160));
        this.t.setBorder(new EmptyBorder(0, 0, 4, 0));
        this.M.add(this.t, "North");
        if (this.K.a()) {
            this.I = new com.eventim.applet.b.l(this, true, this.s.C(), this.s.D(), this.s.d());
            this.J.add(this.I, "North");
        }
        (this.E = new ag(true, false, this.s.C(), this.s.D(), this.s.d())).setBackground(this.s.B());
        this.E.b().setBackground(this.s.B());
        (this.C = new g(this)).setBackground(this.s.B());
        this.C.getTableHeader().setReorderingAllowed(false);
        this.C.getTableHeader().setResizingAllowed(false);
        this.C.setAutoResizeMode(4);
        this.a(true);
        final JPanel panel;
        (panel = new JPanel(new BorderLayout())).setBackground(this.s.B());
        final JCheckBox checkBox;
        (checkBox = new JCheckBox()).setSelected(false);
        checkBox.setActionCommand("adj seats chk box");
        checkBox.addActionListener(this);
        checkBox.setOpaque(false);
        checkBox.setBorder(new EmptyBorder(0, 5, 0, 3));
        panel.add(checkBox, "West");
        this.e = new JTextPane();
        final Font font = this.e.getFont();
        this.e.setFont(new Font(font.getName(), font.getStyle(), font.getSize() - 2));
        this.e.setEnabled(false);
        this.e.setEditable(false);
        this.e.setText(this.s.A().a("bereiche_mit_zusammenh\u00e4ngenden_pl\u00e4tzen").replaceAll("[\n\r]", " ").replaceAll(" +", " "));
        panel.add(this.e, "Center");
        final Integer[] array4 = new Integer[this.s.S() - 1];
        for (int j = 0; j < array4.length; ++j) {
            array4[j] = new Integer(j + 2);
        }
        (this.d = new JComboBox(array4)).setActionCommand("num adj seats changed");
        this.d.setEnabled(false);
        final JPanel panel2;
        (panel2 = new JPanel()).setBackground(this.s.B());
        panel2.add(this.d);
        panel.add(panel2, "East");
        final JLabel label = new JLabel("<html><body><b>" + this.s.A().a("Folgende_Auswahl_anzeigen") + "</b></body></html>");
        if (this.s.T()) {
            label.setForeground(Color.white);
        }
        this.E.d().add(label);
        this.E.b().setLayout(new BorderLayout());
        this.F = new com.eventim.applet.n(this, this.C);
        this.F.getViewport().setBackground(this.s.B());
        this.F.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.E.b().add(this.F, "Center");
        this.E.b().add(panel, "South");
        this.E.setMinimumSize(new Dimension(160, 120));
        this.E.setBorder(new EmptyBorder(0, 0, 4, 0));
        this.J.add(this.E, "Center");
        this.d.addActionListener(this);
        return true;
    }
    
    private ag a(final String s, final List list, final boolean b, final boolean b2, final Color foreground) {
        int n = SwingUtilities.computeStringWidth(this.getGraphics().getFontMetrics(), s) + 25;
        boolean b3 = true;
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            final int computeStringWidth;
            if ((computeStringWidth = SwingUtilities.computeStringWidth(this.getGraphics().getFontMetrics(), iterator.next())) > n) {
                n = computeStringWidth;
                b3 = false;
            }
        }
        final int n2 = b3 ? (n + 10) : (n - 4);
        final ag ag;
        (ag = new ag(true, true, this.s.C(), this.s.D(), new Color(1.0f, 1.0f, 1.0f, 1.0f))).setOpaque(false);
        final JLabel label = new JLabel("<html><b>" + s + "</b></html>");
        if (this.s.T()) {
            label.setForeground(Color.white);
        }
        ag.d().add(label);
        ag.d().setPreferredSize(new Dimension(n2, 20));
        ag.d().setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
        final JPanel panel = new JPanel(new BorderLayout());
        final JPanel panel2;
        (panel2 = new JPanel(new GridLayout(0, 1))).setBackground(this.s.B());
        final Iterator<String> iterator2 = list.iterator();
        while (iterator2.hasNext()) {
            final JLabel label2 = new JLabel(iterator2.next());
            if (foreground != null) {
                label2.setForeground(foreground);
            }
            label2.setBackground(this.s.B());
            panel2.add(label2);
        }
        panel.add(panel2, "Center");
        if (b) {
            final JButton button;
            (button = new JButton("<html><b>OK</b></html>")).setActionCommand("message ok");
            button.addActionListener(this);
            button.setPreferredSize(new Dimension(52, 16));
            final JComponent component;
            (component = new JPanel()).add(button);
            component.setBackground(this.s.B());
            component.setBorder(new EmptyBorder(4, 0, 0, 0));
            panel.add(component, "South");
        }
        panel.setBackground(this.s.B());
        ag.b().add(panel);
        ag.b().setBackground(this.s.B());
        final int n3 = (panel2.getFont().getSize() + 2) * list.size() + (b ? 65 : 37);
        final Rectangle bounds;
        ag.setBounds((bounds = this.P.getBounds()).x + bounds.width / 2 - n2 / 2, bounds.y + bounds.height / 2 - n3 / 2, n2, n3);
        ag.d().setOpaque(false);
        ag.c().setOpaque(false);
        return ag;
    }
    
    private ag a(final String s, final List list, final boolean b) {
        return this.a(s, list, b, false, null);
    }
    
    private static Hashtable u() {
        String property = "Zugriff_verweigert";
        String property2 = "Zugriff_verweigert";
        String property3 = "Zugriff_verweigert";
        try {
            property = System.getProperty("os.name");
        }
        catch (Exception ex) {}
        try {
            property3 = System.getProperty("java.version");
        }
        catch (Exception ex2) {}
        try {
            property2 = System.getProperty("java.vendor");
        }
        catch (Exception ex3) {}
        final Hashtable<String, String> hashtable;
        (hashtable = new Hashtable<String, String>()).put("OS", property);
        hashtable.put("JavaVersion", property3);
        hashtable.put("JavaVendor", property2);
        return hashtable;
    }
    
    private t a(final short n, final SaalplanEventDetails saalplanEventDetails, final int n2) {
        final Hashtable u;
        Request.setCommand(u = u(), 2);
        u.put("SessionId", this.s.H());
        u.put("sprache", this.s.v());
        u.put("EventId", String.valueOf(this.s.m()));
        u.put("Section", String.valueOf(n));
        u.put("x1", "-1");
        u.put("y1", "-1");
        u.put("x2", "-1");
        u.put("y2", "-1");
        Request.setCompressionType(u, 1);
        final Reply a;
        if ((a = this.a(u)).ret <= 0) {
            System.err.println("Could not get seatplan objects: " + a.ret + " " + a.text);
            return null;
        }
        final SeatplanObjects seatplanObjects = (SeatplanObjects)a.objects[0];
        this.K.a(seatplanObjects);
        this.a(false);
        final Rectangle a2 = this.a(seatplanObjects);
        final com.eventim.applet.a.g a3;
        a((a3 = com.eventim.applet.a.g.a(seatplanObjects.graphDetails, (n2 == 0) ? "Section overview factory" : "Section detail factory")).b(), saalplanEventDetails);
        this.a(a3);
        a3.a(this.K.a(new Integer(this.K.b())), null);
        a3.b(this.K.b(), -1);
        final f[] a4 = a3.a();
        for (int i = 0; i < a4.length; ++i) {
            this.X.add(new Long(a4[i].e_()));
        }
        final HashSet<Integer> set;
        (set = new HashSet<Integer>(this.K.c())).add(new Integer(0));
        for (final Integer n3 : set) {
            for (int j = this.s.S(); j >= 1; --j) {
                for (int k = 0; k < a4.length; ++k) {
                    if (a4[k].a(n3) == null && a(j, a4[k], a4, n3) != null) {
                        a4[k].a(j, n3);
                    }
                }
            }
        }
        final Dimension dimension = new Dimension(a2.width + (a2.x << 1), a2.height + (a2.y << 1));
        t t;
        if (n2 == 0) {
            if (this.s.J()) {
                t = new a(this, a3, dimension, this);
            }
            else {
                t = new ae(a3, dimension, this);
            }
        }
        else {
            final m m;
            (m = new m(a3, dimension, this)).a(n);
            t = m;
        }
        return t;
    }
    
    private String b(final List list) {
        final StringBuffer sb = new StringBuffer();
        long n = Long.MAX_VALUE;
        long n2 = Long.MIN_VALUE;
        String b_ = null;
        String b_2 = null;
        final Iterator<f> iterator = list.iterator();
        while (iterator.hasNext()) {
            final f f;
            final long l;
            if ((l = (f = iterator.next()).l()) < n) {
                n = l;
                b_ = f.b_();
            }
            if (l > n2) {
                n2 = l;
                b_2 = f.b_();
            }
        }
        final f f2 = list.get(0);
        sb.append((f2.d_() != null) ? f2.d_() : f2.a_());
        sb.append(", ");
        sb.append(this.s.A().a("Reihe"));
        sb.append(' ');
        sb.append(f2.c_());
        sb.append(", ");
        sb.append(this.s.A().a("Pl\u00e4tze"));
        sb.append(' ');
        sb.append(b_);
        sb.append(" - ");
        sb.append(b_2);
        return sb.toString();
    }
    
    private void a(final int n, final int n2, final String[] array) {
        final ArrayList list;
        final String s = (String)(list = new ArrayList(this.b(n, 0, array))).remove(0);
        if (n == 0) {
            this.b(s, list, false);
            this.setEnabled(false);
            return;
        }
        this.b(s, list, true);
    }
    
    public final void a(final int n) {
        this.a(n, 0, (String[])null);
    }
    
    private void b(final String s, final List list, final boolean b) {
        this.B();
        this.z = this.a(s, list, b);
        this.getLayeredPane().add(this.z, new Integer(2), 0);
    }
    
    private void a(final Integer n, final Runnable runnable) {
        this.B();
        if (this.h != null) {
            this.h.setVisible(false);
            this.getLayeredPane().remove(this.h);
        }
        if (this.Q.containsKey(n)) {
            this.a(n, false, runnable);
            return;
        }
        this.c(this.s.A().a("loading_section_data"));
        SwingUtilities.invokeLater(new com.eventim.applet.r(this, n, runnable));
    }
    
    private void c(final String s) {
        this.B();
        final ArrayList<Object> list = (ArrayList<Object>)new ArrayList<String>(Arrays.asList(s.split("\n")));
        this.z = this.a((String)list.remove(0), list, false, false, this.s.R());
        this.getLayeredPane().add(this.z, new Integer(2), 0);
    }
    
    private boolean a(final b b, final boolean b2) {
        final int a;
        if ((a = this.i.a(b)) >= 0 && !b.h()) {
            if (b2) {
                this.b(a);
                return false;
            }
            return true;
        }
        else {
            final Integer n = new Integer(this.K.b());
            final SaalplanRabattstufeDetails[] b3;
            final SaalplanRabattstufeDetails saalplanRabattstufeDetails = ((b3 = this.K.b(b.e_(), n)) != null && b3.length > 0) ? b3[0] : null;
            if (this.b(n)) {
                a("You cannot order tickets from more than one promotion at a time.", 4);
                this.a(-13, 0, (String[])null);
                return false;
            }
            if (this.i.getRowCount() > 0 && b.e_() != this.i.a()) {
                a("All tickets for the order must have the same price category!!", 4);
                this.a(-5, 0, (String[])null);
                return false;
            }
            final int maxmenge;
            if ((maxmenge = this.K.a(b.e_()).getMaxmenge()) > 0 && this.i.getRowCount() >= maxmenge) {
                a("Too many tickets for this price category! You can order a maxmimum of " + maxmenge + " tickets.", 4);
                this.a(-4, 0, (String[])null);
                return false;
            }
            if (saalplanRabattstufeDetails != null && this.i.getRowCount() > 0) {
                final int a2 = this.K.a(saalplanRabattstufeDetails.getId(), this.i.a(), n);
                final Long n2 = new Long(saalplanRabattstufeDetails.getId());
                final int n3 = a2;
                if (n3 != 0 && this.a(n2) >= n3) {
                    a("Too many tickets for this ticket type!! You can order a maxmimum of " + a2 + " tickets.", 4);
                    this.a(-7, 0, (String[])null);
                    return false;
                }
            }
            if (com.eventim.applet.e.i(n) && this.K.a(this.s(), n)) {
                System.err.println("Too many tickets for this promotion!! You can order a maxmimum of " + this.K.b((int)n) + " tickets.");
                this.a(-9, 0, (String[])null);
                return false;
            }
            if (this.K.f(n) && this.K.b(this.s(), n)) {
                System.err.println("Too many tickets for the promotion code(s) entered!! You can order a maxmimum of " + this.K.c((int)n) + " tickets.");
                this.a(-10, 0, (String[])null);
                return false;
            }
            if (b.h()) {
                final c c = (c)b;
                int n4 = 0;
                for (int i = 0; i < this.i.getRowCount(); ++i) {
                    final b a3;
                    if ((a3 = this.i.a(i)).h() && c.k() == ((c)a3).k()) {
                        ++n4;
                    }
                }
                final Integer n5;
                if (n4 == (((n5 = ((c.i() == null) ? null : c.i().get(n))) == null) ? 0 : n5)) {
                    this.a(-2, 0, (String[])null);
                    return false;
                }
            }
            this.Y.add(new o(b3));
            this.i.a(b, new Integer(this.K.b()), this.p);
            this.r();
            return true;
        }
    }
    
    private void a(final Integer p3, final boolean b, final Runnable runnable) {
        final t viewportView = this.Q.get(p3);
        if (p3 > 0) {
            this.R = (m)viewportView;
        }
        this.p = p3;
        this.P.setViewportView(viewportView);
        viewportView.b(this.P.getViewport().getSize());
        if (b || (this.ad == 1 && p3 != 0)) {
            viewportView.a(viewportView.d());
        }
        this.a(false);
        viewportView.b(this.K.b(), true);
        this.b(viewportView);
        final BufferedImage a = viewportView.a(new Dimension(128, 128));
        if (p3 == 0 && this.ad == 1) {
            this.t.b(a);
            this.t.a(0);
        }
        else {
            this.t.a(a);
            this.t.a(com.eventim.applet.b.r.b);
        }
        if (this.ad == 1) {
            if (p3 > 0) {
                this.getLayeredPane().remove(this.w);
                this.getLayeredPane().add(this.f, new Integer(2), 0);
                this.f.setVisible(true);
            }
            else {
                final Rectangle bounds = this.P.getViewport().getBounds();
                this.w.setBounds(10, bounds.y + bounds.height - 26, 140, 30);
                this.getLayeredPane().add(this.w, new Integer(2), 0);
            }
        }
        if (runnable != null) {
            runnable.run();
        }
    }
    
    public final void a() {
        final Hashtable u;
        Request.setCommand(u = u(), 3);
        u.put("SessionId", this.s.H());
        u.put("sprache", this.s.v());
        u.put("Plattform", String.valueOf(this.s.a.tdlSeatplanDetails.affiliateDetails.getPlattform()));
        u.put("EventId", String.valueOf(this.s.m()));
        u.put("PkId", String.valueOf(this.s.a(new Long(this.i.a()))));
        u.put("verkaufsart", new Byte(this.s.a.tdlSeatplanDetails.verkaufsart));
        u.put("promotion_id", new Integer(this.K.b()));
        u.put("captchaId", this.s.f());
        u.put("captchaAnswer", this.s.e());
        u.put("captchaPsig", this.s.h());
        this.b(u);
        Request.setCompressionType(u, 0);
        final Reply a = this.a(u);
        this.setCursor(Cursor.getDefaultCursor());
        if (a.ret <= 0) {
            this.a(a.ret, 0, (String[])null);
            a("Could not process your order: " + a.ret + " " + a.text, 2);
            return;
        }
        a("Order OK: " + a.ret + " " + a.text, 2);
        if (!this.v()) {
            this.a(0, 0, (String[])null);
        }
    }
    
    private static List a(final int n, final f f, final f[] array, final int n2) {
        if (f == null || !f.b(n2)) {
            return null;
        }
        final String c_ = f.c_();
        final long e_ = f.e_();
        final long j = f.j();
        final long l;
        final long n3 = (l = f.l()) - n;
        final long n4 = l + n;
        final HashMap<Long, f> hashMap = new HashMap<Long, f>();
        for (int i = 0; i < array.length; ++i) {
            final f f2 = array[i];
            if (e_ == f2.e_() && j == f2.j() && (f2.l() >= n3 || f2.l() <= n4) && c_.equals(f2.c_()) && f2.b(n2)) {
                hashMap.put(new Long(f2.l()), f2);
            }
        }
        int n5 = 1;
        int n6 = 1;
        final ArrayList<f> list;
        (list = new ArrayList<f>()).add(f);
        for (int n7 = 1; n7 <= n && (n6 != 0 || n5 != 0); ++n7) {
            if (n5 != 0 && list.size() < n) {
                final Long n8 = new Long(l + n7);
                if (hashMap.containsKey(n8)) {
                    list.add(hashMap.get(n8));
                }
                else {
                    n5 = 0;
                }
            }
            if (n6 != 0 && list.size() < n) {
                final Long n9 = new Long(l - n7);
                if (hashMap.containsKey(n9)) {
                    list.add(hashMap.get(n9));
                }
                else {
                    n6 = 0;
                }
            }
        }
        if (list.size() == n) {
            return list;
        }
        return null;
    }
    
    private b b(final Point point) {
        Point point2 = point;
        final Dimension size = this.P.getViewport().getSize();
        final t b2;
        final BufferedImage b = (b2 = this.b()).b();
        final Dimension dimension = new Dimension(b.getWidth(), b.getHeight());
        if (size.width > dimension.width) {
            point2 = new Point(point2.x - (size.width - dimension.width) / 2, point2.y);
        }
        if (size.height > dimension.height) {
            point2 = new Point(point2.x, point2.y - (size.height - dimension.height) / 2);
        }
        if (b2.g() > 0) {
            point2 = new Point((int)Math.round(point2.x / b2.f()), (int)Math.round(point2.y / b2.f()));
        }
        final c[] c = b2.e().c();
    Label_0298:
        while (true) {
            for (int i = 0; i < c.length; ++i) {
                if (c[i].b(this.K.b())) {
                    final com.eventim.applet.a.k[] l = c[i].l();
                    for (int j = 0; j < l.length; ++j) {
                        final Shape g_;
                        if ((g_ = l[j].g_()) != null && g_.contains(point2) && c[i].m()) {
                            final c c3;
                            final c c2 = c3 = c[i];
                            break Label_0298;
                        }
                    }
                    continue;
                    c c3 = null;
                    b c4 = c3;
                    c c2 = null;
                    if (c2 == null) {
                        c4 = this.c(point);
                    }
                    return c4;
                }
            }
            c c3;
            final c c2 = c3 = null;
            continue Label_0298;
        }
    }
    
    private f c(Point point) {
        final Dimension size = this.P.getViewport().getSize();
        final t b2;
        final BufferedImage b = (b2 = this.b()).b();
        final Dimension dimension = new Dimension(b.getWidth(), b.getHeight());
        if (size.width > dimension.width) {
            point = new Point(point.x - (size.width - dimension.width) / 2, point.y);
        }
        if (size.height > dimension.height) {
            point = new Point(point.x, point.y - (size.height - dimension.height) / 2);
        }
        if (b2.g() > 0) {
            point = new Point((int)Math.round(point.x / b2.f()), (int)Math.round(point.y / b2.f()));
        }
        final f[] a = b2.e().a();
        for (int i = 0; i < a.length; ++i) {
            final Shape g_;
            if (a[i].b(this.K.b()) && (g_ = a[i].g_()) != null && g_.getBounds().contains(point) && a[i].m()) {
                return a[i];
            }
        }
        return null;
    }
    
    private com.eventim.applet.a.p d(Point point) {
        final Dimension size = this.P.getViewport().getSize();
        final t b2;
        final BufferedImage b = (b2 = this.b()).b();
        final Dimension dimension = new Dimension(b.getWidth(), b.getHeight());
        if (size.width > dimension.width) {
            point = new Point(point.x - (size.width - dimension.width) / 2, point.y);
        }
        if (size.height > dimension.height) {
            point = new Point(point.x, point.y - (size.height - dimension.height) / 2);
        }
        if (b2.g() > 0) {
            point = new Point((int)Math.round(point.x / b2.f()), (int)Math.round(point.y / b2.f()));
        }
        final com.eventim.applet.a.p[] b3 = b2.e().b();
        for (int i = 0; i < b3.length; ++i) {
            final Shape g_;
            if ((g_ = b3[i].g_()) != null && g_.contains(point) && b3[i].j()) {
                return b3[i];
            }
        }
        return null;
    }
    
    private boolean v() {
        try {
            String s;
            if (!(s = this.s.p()).endsWith("&")) {
                s += "&";
            }
            a(s + " (" + this.s.o() + ")", 2);
            if (this.s.o().length() > 0) {
                this.getAppletContext().showDocument(new URL(s), this.s.o());
            }
            else {
                this.getAppletContext().showDocument(new URL(s));
            }
            return true;
        }
        catch (Exception ex) {
            a("Fehler forward: " + ex.toString(), 3);
            return false;
        }
    }
    
    private int b(final long n) {
        int n2 = 0;
        final SectionDetails[] sectionDetails = this.s.a.tdlSeatplanDetails.sectionDetails;
        for (int i = 0; i < sectionDetails.length; ++i) {
            final SectionDetails sectionDetails2 = sectionDetails[i];
            final Integer n3 = new Integer(this.K.b());
            final Hashtable<Object, Integer> hashtable;
            if (sectionDetails2.anzahlPlaetzeProTdlPkIdProPrId != null && sectionDetails2.anzahlPlaetzeProTdlPkIdProPrId.containsKey(n3) && (hashtable = sectionDetails2.anzahlPlaetzeProTdlPkIdProPrId.get(n3)) != null && hashtable.containsKey(new Long(n))) {
                n2 += hashtable.get(new Long(n));
            }
        }
        return n2;
    }
    
    private double[] c(final t t) {
        final Rectangle viewRect;
        final double centerX = (viewRect = this.P.getViewport().getViewRect()).getCenterX();
        final double centerY = viewRect.getCenterY();
        final int width = t.b().getWidth();
        final int height = t.b().getHeight();
        final double n = (width < viewRect.width) ? (centerX - (viewRect.width - width) / 2) : centerX;
        final double n2 = (height < viewRect.height) ? (centerY - (viewRect.height - height) / 2) : centerY;
        final double n3 = (centerX == 0.0) ? 0.0 : (n / width);
        final double n4 = (centerY == 0.0) ? 0.0 : (n2 / height);
        this.P.setEnabled(false);
        return new double[] { n3, n4 };
    }
    
    private String[] w() {
        for (int i = 0; i < this.i.getRowCount(); ++i) {
            final SaalplanRabattstufeDetails a = ((p)this.Y.get(i).getSelectedItem()).a();
            final int c = this.K.c(a.getId(), this.i.a(i).e_());
            final int a2;
            if ((a2 = this.a(new Long(a.getId()))) % c != 0) {
                return new String[] { a.getName(), String.valueOf(a2), String.valueOf(c) };
            }
        }
        return null;
    }
    
    public final t b() {
        return this.Q.get(this.p);
    }
    
    public final Set c() {
        return this.r;
    }
    
    private List b(int n, final int n2, final String[] array) {
        if (n == -11200) {
            n = -30;
            this.h.a();
        }
        final String string = "errorMessage_" + n + ((n2 > 0) ? ("_" + n2) : "");
        final String a;
        if ((a = this.s.A().a(string)) != null && !a.trim().equals("")) {
            return Arrays.asList(com.eventim.applet.k.a(this.s.A().a(string, array), "\n"));
        }
        final ArrayList list;
        (list = new ArrayList((Collection<? extends E>)Arrays.asList(com.eventim.applet.k.a(this.s.A().a("errorMessage_default", array), "\n")))).add(0, this.s.A().a("default_server_message_header"));
        return list;
    }
    
    private Reply x() {
        final Hashtable u;
        Request.setCommand(u = u(), 1);
        u.put("SessionId", this.s.H());
        u.put("sprache", this.s.v());
        u.put("EventId", String.valueOf(this.s.m()));
        try {
            u.put("Affiliate", this.s.H().substring(0, 3).toUpperCase());
        }
        catch (Exception ex) {
            u.put("Affiliate", "");
        }
        Request.setCompressionType(u, 1);
        return this.a(u);
    }
    
    public final l d() {
        return this.s;
    }
    
    private String[] y() {
        for (int i = 0; i < this.i.getRowCount(); ++i) {
            final SaalplanRabattstufeDetails a = ((p)this.Y.get(i).getSelectedItem()).a();
            final int b = this.K.b(a.getId(), this.i.a(i).e_(), this.i.b().get(i));
            final int a2;
            if ((a2 = this.a(new Long(a.getId()))) < b) {
                return new String[] { a.getName(), String.valueOf(a2), String.valueOf(b) };
            }
        }
        return null;
    }
    
    private f c(final f f, final int n) {
        for (int i = 0; i < this.i.getRowCount(); ++i) {
            final b a;
            if (!(a = this.i.a(i)).h()) {
                final f f2 = (f)a;
                if (f.j() == f2.j() && f.e_() == f2.e_() && f.c_().compareTo(f2.c_()) == 0 && f.l() == f2.l() + n) {
                    return f2;
                }
            }
        }
        return null;
    }
    
    public static Integer a(final long n) {
        return EventimApplet.G.get(new Long(n));
    }
    
    public final com.eventim.applet.e e() {
        return this.K;
    }
    
    public final Reply f() {
        final Hashtable u;
        Request.setCommand(u = u(), 110);
        u.put("SessionId", this.s.H());
        u.put("sprache", this.s.v());
        u.put("Plattform", String.valueOf(this.s.a.tdlSeatplanDetails.affiliateDetails.getPlattform()));
        u.put("EventId", new Integer(this.s.m()));
        Request.setCompressionType(u, 0);
        return this.a(u);
    }
    
    private List a(final f f, final boolean b, final boolean b2) {
        final ArrayList list = new ArrayList<f>();
        final f[] a = this.R.e().a();
        for (int i = 0; i < a.length; ++i) {
            final f f2;
            if ((f2 = a[i]) != null && f2.c_() != null && f2.j() == f.j() && f2.c_().compareTo(f.c_()) == 0 && this.K.a(f2) && (!b2 || this.q.contains(new Long(f2.e_()))) && (!b2 || this.K.a(f2.i(), f2.e_()))) {
                list.add(f2);
            }
        }
        a("Reihenl\u00e4nge: " + list.size(), 0);
        return list;
    }
    
    private Reply a(final Hashtable request) {
        this.setEnabled(false);
        final Reply sendRequest;
        final Integer n;
        if ((sendRequest = Request.sendRequest(this.s.q(), this.s.E(), this.s.G(), request, 0 != 0)).infos != null && (n = sendRequest.infos.get("LogPrioritySaalplanApplet")) != null) {
            EventimApplet.y = n;
        }
        this.setEnabled(true);
        return sendRequest;
    }
    
    public final BufferedImage g() {
        this.R.a(this.K.b(), this.q, this.o);
        return this.R.a(new Dimension(128, 128));
    }
    
    public final BufferedImage h() {
        this.S.a(this.K.b(), this.q, this.o);
        return this.S.a(new Dimension(128, 128));
    }
    
    public final String a(final SaalplanRabattstufeDetails saalplanRabattstufeDetails, final int n) {
        final String upperCase = this.s.i().toUpperCase();
        final String s = EventimApplet.b.get(upperCase);
        final String format = this.H.format(this.b(saalplanRabattstufeDetails, n));
        if (s == null || !this.s.I()) {
            return format + " " + upperCase;
        }
        if (EventimApplet.a.contains(upperCase)) {
            return format + " " + s;
        }
        return s + " " + format;
    }
    
    private double b(final SaalplanRabattstufeDetails saalplanRabattstufeDetails, final int n) {
        if (EventimApplet.y == 0) {
            a("For ticket type '" + saalplanRabattstufeDetails.getName() + "' (ID: " + saalplanRabattstufeDetails.getId() + ", TDL ID: " + saalplanRabattstufeDetails.getTdlRabattstufeId() + "):", 0);
            a("Original applet price: vkpreis (" + saalplanRabattstufeDetails.getVkpreis() + ") - exclFees (" + saalplanRabattstufeDetails.getExclFees() + ") + affilate ticketgebuehr (" + this.s.a.tdlSeatplanDetails.affiliateDetails.getTicketgebuehr() + ") = " + (saalplanRabattstufeDetails.getVkpreis() - saalplanRabattstufeDetails.getExclFees() + this.s.a.tdlSeatplanDetails.affiliateDetails.getTicketgebuehr()), 0);
            a("Mode 0 price: vkpreis (" + saalplanRabattstufeDetails.getVkpreis() + ") + affiliate ticketgebuer (" + this.s.a.tdlSeatplanDetails.affiliateDetails.getTicketgebuehr() + ") = " + (saalplanRabattstufeDetails.getVkpreis() + this.s.a.tdlSeatplanDetails.affiliateDetails.getTicketgebuehr()), 0);
            a("Mode 1 price: vkpreis (" + saalplanRabattstufeDetails.getVkpreis() + ") - exclFees (" + saalplanRabattstufeDetails.getExclFees() + ") - ticketgebuehr (" + saalplanRabattstufeDetails.getTicketgebuehr() + ") - vvgebuegr (" + saalplanRabattstufeDetails.getVvgebuehr() + ") = " + (saalplanRabattstufeDetails.getVkpreis() - saalplanRabattstufeDetails.getExclFees() - saalplanRabattstufeDetails.getTicketgebuehr() - saalplanRabattstufeDetails.getVvgebuehr()), 0);
            a("Ticket price display mode is " + n + ".", 0);
        }
        if (n == 0) {
            return saalplanRabattstufeDetails.getVkpreis() + this.s.a.tdlSeatplanDetails.affiliateDetails.getTicketgebuehr();
        }
        return saalplanRabattstufeDetails.getVkpreis() - saalplanRabattstufeDetails.getExclFees() - saalplanRabattstufeDetails.getTicketgebuehr() - saalplanRabattstufeDetails.getVvgebuehr();
    }
    
    public final List i() {
        return this.Y;
    }
    
    public final Map j() {
        return this.Z;
    }
    
    private void a(final int o, final int n, final boolean b) {
        this.o = o;
        final t b2;
        (b2 = this.b()).a(o, b);
        if (this.S != null && this.S != b2) {
            this.S.a(o, b);
        }
        if (b) {
            this.C();
        }
    }
    
    private boolean z() {
        for (int i = 0; i < this.i.getRowCount(); ++i) {
            final SaalplanRabattstufeDetails a = ((p)this.Y.get(i).getSelectedItem()).a();
            final int c = this.K.c(a.getId(), this.i.a(i).e_());
            final int a2 = this.a(new Long(a.getId()));
            if (c == 0) {
                return true;
            }
            if (a2 % c != 0) {
                return false;
            }
        }
        return true;
    }
    
    private void a(final f f) {
        final long currentTimeMillis = System.currentTimeMillis();
        if (f != null) {
            this.l.add(f);
            f.a(true);
            this.R.a(f);
        }
        this.aa += System.currentTimeMillis() - currentTimeMillis;
        ++this.B;
    }
    
    public final void a(final b b) {
        if (this.m()) {
            if (b.e() != (short)(Object)this.p) {
                this.a(new Integer(b.e()), new com.eventim.applet.f(this, b));
            }
            else {
                this.R.a(b);
            }
        }
        if (this.n()) {
            this.S.a(b.e());
        }
    }
    
    public void init() {
        try {
            UIManager.setLookAndFeel("de.muntjak.tinylookandfeel.TinyLookAndFeel");
        }
        catch (Exception ex) {
            a("Failed to set Look and Feel: " + ex, 3);
        }
        super.init();
        this.s = new l(this);
        com.eventim.applet.a.c.a(this.s.A().a("freie") + " " + this.s.A().a("Platzwahl"));
        this.N = new JPanel(new BorderLayout());
        (this.x = new JPanel(new BorderLayout())).setBackground(this.s.d());
        final Box horizontalBox = Box.createHorizontalBox();
        final String a = this.s.A().a("zur\u00fcck");
        (this.f = new JButton("< " + a)).setFont(new Font(this.f.getFont().getName(), this.f.getFont().getStyle() | 0x1, this.f.getFont().getSize()));
        this.f.setActionCommand("back");
        this.f.setBounds(10, 30, 36 + (a.length() << 3), 14);
        this.f.setRequestFocusEnabled(false);
        (this.ac = new JLabel()).setOpaque(false);
        this.ac.setForeground(this.s.y());
        (this.u = new JLabel(com.eventim.applet.a.i.a("infoZeichen.png", this.s))).addMouseListener(new com.eventim.applet.t(this));
        this.u.setBorder(new EmptyBorder(0, 8, 0, 8));
        horizontalBox.add(this.u);
        (this.ae = new JButton("Zoom +")).setFont(new Font(this.ae.getFont().getName(), this.ae.getFont().getStyle() | 0x1, this.ae.getFont().getSize()));
        this.ae.setMaximumSize(new Dimension(84, 14));
        this.ae.setActionCommand("zoom in");
        this.ae.setRequestFocusEnabled(false);
        (this.af = new JButton("Zoom -")).setFont(new Font(this.af.getFont().getName(), this.af.getFont().getStyle() | 0x1, this.af.getFont().getSize()));
        this.af.setMaximumSize(new Dimension(84, 14));
        this.af.setActionCommand("zoom out");
        this.af.setRequestFocusEnabled(false);
        horizontalBox.add(this.af);
        horizontalBox.add(this.ae);
        (this.P = new JScrollPane()).setBackground(this.s.B());
        this.P.getViewport().setBackground(this.s.B());
        this.P.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.P.setPreferredSize(new Dimension(632, 460));
        this.O = new ag(false, false, this.s.w(), this.s.x(), this.s.d());
        this.O.d().setLayout(new BorderLayout());
        this.O.d().add(this.ac, "Center");
        this.O.d().add(horizontalBox, "East");
        this.O.d().setBorder(new EmptyBorder(0, 6, 0, 6));
        this.O.d().setForeground(Color.white);
        this.O.b().setLayout(new BorderLayout());
        this.O.b().add(this.P, "Center");
        this.O.setBackground(this.s.B());
        this.O.b().setBackground(this.s.B());
        this.x.add(this.O, "Center");
        (this.k = new ag(true, false, this.s.C(), this.s.D(), this.s.d())).setBackground(this.s.d());
        this.i = new e(this);
        (this.j = new com.eventim.applet.m(this, this.i)).addMouseListener(new w(this));
        this.j.addMouseMotionListener(new com.eventim.applet.p());
        this.j.getTableHeader().setReorderingAllowed(false);
        this.j.getTableHeader().setResizingAllowed(false);
        this.j.setAutoResizeMode(4);
        this.j.getTableHeader().setDefaultRenderer(new ad());
        this.j.getTableHeader().setBackground(this.s.C());
        this.j.setShowGrid(false);
        this.j.setIntercellSpacing(new Dimension(0, 3));
        this.j.setRowHeight(26);
        this.j.getColumnModel().getColumn(0).setPreferredWidth(24);
        this.j.getColumnModel().getColumn(1).setPreferredWidth(256);
        this.j.getColumnModel().getColumn(2).setPreferredWidth(92);
        this.j.getColumnModel().getColumn(3).setPreferredWidth(36);
        this.j.getColumnModel().getColumn(4).setPreferredWidth(160);
        this.j.getColumnModel().getColumn(5).setPreferredWidth(92);
        this.j.getColumnModel().getColumn(6).setPreferredWidth(64);
        final JLabel label = new JLabel("<html><b>" + this.s.A().a("Ihre_pers\u00f6nliche_Platzauswahl") + "</b></html>");
        if (this.s.T()) {
            label.setForeground(Color.white);
        }
        this.k.d().add(label);
        this.k.b().setLayout(new BorderLayout());
        final q q;
        (q = new q(this, this.j)).getViewport().setBackground(this.s.B());
        q.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.k.b().add(q, "Center");
        this.k.b().setMinimumSize(new Dimension(400, 96));
        this.k.b().setPreferredSize(new Dimension(632, 96));
        this.k.setBackground(this.s.B());
        this.k.b().setBackground(this.s.B());
        this.x.add(this.k, "South");
        (this.M = new JPanel(new BorderLayout())).setPreferredSize(new Dimension(177, 600));
        this.M.setBackground(this.s.d());
        if (this.ad == 1) {
            this.t = new r(com.eventim.applet.b.r.a, true, this.s.C(), this.s.D(), this.s.t(), this.s.u(), this.s.d(), this);
        }
        else {
            this.t = new r(0, true, this.s.C(), this.s.D(), this.s.t(), this.s.u(), this.s.d(), this);
        }
        this.t.setBackground(this.s.d());
        this.t.setMinimumSize(new Dimension(160, 160));
        (this.J = new JPanel(new BorderLayout())).setBackground(this.s.d());
        (this.c = new JPanel()).setBackground(this.s.d());
        this.c.setMinimumSize(new Dimension(160, 32));
        this.c.setMaximumSize(new Dimension(160, 32));
        this.c.add(new com.eventim.applet.b.c(this.s.A().a("In_den_Warenkorb"), com.eventim.applet.a.i.a("wakoUndPfeil.png", this.s), this.s.d(), this));
        this.M.add(this.t, "North");
        this.M.add(this.J, "Center");
        this.M.add(this.c, "South");
        this.N.add(this.x, "Center");
        this.N.add(this.M, "East");
        this.getContentPane().add(this.N);
        this.x.setBorder(new EmptyBorder(4, 4, 4, 0));
        this.M.setBorder(new EmptyBorder(4, 4, 4, 4));
        this.O.setBorder(new EmptyBorder(0, 0, 4, 0));
        this.t.setBorder(new EmptyBorder(0, 0, 4, 0));
        this.P.getHorizontalScrollBar().addAdjustmentListener(new s(this));
        this.P.getVerticalScrollBar().addAdjustmentListener(new com.eventim.applet.o(this));
        this.f.addActionListener(this);
        this.ae.addActionListener(this);
        this.af.addActionListener(this);
        this.w = new com.eventim.applet.b.w(this.s.A().a("freie_pl\u00e4tze"), this.s.A().a("nicht_verf\u00fcgbar"), new Color(0.3f, 0.9f, 0.3f, 0.9f), new Color(0.9f, 0.9f, 0.9f, 0.9f));
        (this.v = new j()).setOpaque(false);
        this.v.addMouseListener(new com.eventim.applet.i(this));
        (this.T = new JTextPane()).setText(this.s.A().a("activate_promotion_1") + "\n" + this.s.A().a("activate_promotion_2") + "\n" + this.s.A().a("activate_promotion_3") + "\n" + this.s.A().a("activate_promotion_4") + "\n" + this.s.A().a("activate_promotion_5"));
        this.T.setEditable(false);
        this.T.setMargin(new Insets(6, 6, 6, 6));
        this.setFocusable(false);
    }
    
    private static Map A() {
        final HashMap<String, Object> hashMap;
        (hashMap = new HashMap<String, Object>()).put("EUR", "\u20ac");
        hashMap.put("GBP", "£");
        hashMap.put("USD", "$");
        hashMap.put("SEK", "kr");
        try {
            hashMap.put("CZK", new String(new byte[] { 75, -60, -115 }, "UTF-8"));
        }
        catch (Exception ex) {}
        return Collections.unmodifiableMap((Map<?, ?>)hashMap);
    }
    
    public final boolean k() {
        return this.I != null && this.I.a();
    }
    
    public static boolean l() {
        return System.getProperty("os.name").toLowerCase().equals("mac os x");
    }
    
    public final boolean m() {
        return this.ad == 0 || this.p > 0;
    }
    
    public final boolean n() {
        return this.ad == 1 && this.p == 0;
    }
    
    public final void a(final Integer p) {
        this.p = p;
        final Iterator<Map.Entry<Integer, V>> iterator = this.Q.entrySet().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getKey() != 0) {
                iterator.remove();
                break;
            }
        }
        if (p != 0) {
            new StringBuffer().append("section_").append(p);
        }
        final short shortValue = (short)(Object)p;
        final SaalplanEventDetails a = this.s.a;
        final int intValue = p;
        final int ad = this.ad;
        final int n = intValue;
        final t a2 = this.a(shortValue, a, (ad == 1) ? ((n == 0) ? 0 : 1) : 1);
        this.Q.put(this.p, a2);
        final t t;
        (t = a2).addMouseListener(this);
        t.addMouseMotionListener(this);
        a(a2, this.l);
    }
    
    public static void a(final String s, final int n) {
        if (EventimApplet.y <= n) {
            System.out.println(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss:SSS", new Locale("de", "DE")).format(new Date()) + ": " + s);
        }
    }
    
    private static void a(final t t, final Set set) {
        final long[] array = new long[set.size()];
        int n = 0;
        final Iterator<f> iterator = set.iterator();
        while (iterator.hasNext()) {
            array[n] = iterator.next().k();
            ++n;
        }
        Arrays.sort(array);
        final f[] a;
        if ((a = t.e().a()) != null) {
            for (int i = 0; i < a.length; ++i) {
                final f f = a[i];
                if (Arrays.binarySearch(array, f.k()) >= 0) {
                    f.a(true);
                }
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.g) {
            return;
        }
        this.setCursor(Cursor.getPredefinedCursor(3));
        if (!this.n()) {
            final b b;
            if ((b = this.b(mouseEvent.getPoint())) != null) {
                if (b.f_()) {
                    final f f = (f)b;
                    if (this.o == 1) {
                        this.b(f);
                    }
                    else {
                        this.a(a(this.o, f, this.b().e().a(), this.K.b()));
                    }
                }
                else {
                    final c c = (c)b;
                    for (int i = 0; i < this.o; ++i) {
                        this.b(c);
                    }
                }
            }
            this.setCursor(Cursor.getDefaultCursor());
            return;
        }
        final com.eventim.applet.a.p d;
        if ((d = this.d(mouseEvent.getPoint())) != null) {
            this.a(new Integer(d.i()), new com.eventim.applet.u(this));
            return;
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (!this.n()) {
            final long currentTimeMillis = System.currentTimeMillis();
            final b b;
            if ((b = this.b(mouseEvent.getPoint())) != null) {
                final t b2;
                (b2 = this.b()).setCursor(Cursor.getPredefinedCursor(12));
                if (b.f_()) {
                    final f f = (f)b;
                    if (this.o == 1) {
                        if (f.b(this.K.b()) && !this.l.contains(f)) {
                            final t t = b2;
                            final f f2 = f;
                            final StringBuffer sb;
                            (sb = new StringBuffer()).append((f2.d_() != null) ? f2.d_() : f2.a_());
                            sb.append(", ");
                            sb.append(this.s.A().a("Reihe"));
                            sb.append(' ');
                            sb.append(f2.c_());
                            sb.append(", ");
                            sb.append(this.s.A().a("Platz"));
                            sb.append(' ');
                            sb.append(f2.b_());
                            t.setToolTipText(sb.toString());
                            this.R.b(f);
                        }
                    }
                    else {
                        final List a;
                        if ((a = a(this.o, f, b2.e().a(), this.K.b())) != null) {
                            final ArrayList list = new ArrayList<f>();
                            for (int i = 0; i < a.size(); ++i) {
                                final f f3 = a.get(i);
                                if (f.b(this.K.b()) && !this.l.contains(f3)) {
                                    list.add(f3);
                                }
                            }
                            if (!list.isEmpty()) {
                                b2.setToolTipText(this.b(list));
                                b2.setCursor(Cursor.getPredefinedCursor(12));
                                this.R.a(list);
                            }
                        }
                    }
                }
            }
            else {
                this.R.a();
                this.R.setToolTipText(null);
                this.R.setCursor(Cursor.getDefaultCursor());
            }
            this.ab += System.currentTimeMillis() - currentTimeMillis;
            ++this.A;
            return;
        }
        final com.eventim.applet.a.p d;
        if ((d = this.d(mouseEvent.getPoint())) != null) {
            this.Z = (Map)d.h().get(new Integer(this.K.b()));
            final t b3;
            (b3 = this.b()).setToolTipText(this.s.A().a("Freie_Pl\u00e4tze_in_Block") + " " + this.s.a.tdlSeatplanDetails.sectionDetails[d.i()].name);
            b3.setCursor(Cursor.getPredefinedCursor(12));
            return;
        }
        this.Z = null;
        final t b4;
        (b4 = this.b()).setToolTipText(null);
        b4.setCursor(Cursor.getDefaultCursor());
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public final void o() {
        if (this.i.getRowCount() < 1) {
            a("Your shopping cart is empty!", 4);
            this.a(-1, 0, (String[])null);
            return;
        }
        boolean b = false;
        Label_0206: {
            if (this.s.a.getSaalplanJackenplatz() == 0) {
                a("Kein Jackenplatztest", 0);
                b = true;
            }
            else {
                for (int i = 0; i < this.i.getRowCount(); ++i) {
                    final b a;
                    if (!(a = this.i.a(i)).h()) {
                        final f f = (f)a;
                        boolean b2 = false;
                        Label_0127: {
                            if (this.c(f, 1) == null) {
                                final int a2;
                                if ((a2 = this.a(f, 1)) == -1) {
                                    b2 = false;
                                    break Label_0127;
                                }
                                if (a2 == 0) {
                                    b2 = this.b(f, -1);
                                    break Label_0127;
                                }
                            }
                            b2 = true;
                        }
                        boolean b3 = false;
                        Label_0172: {
                            if (this.c(f, -1) == null) {
                                final int a3;
                                if ((a3 = this.a(f, -1)) == -1) {
                                    b3 = false;
                                    break Label_0172;
                                }
                                if (a3 == 0) {
                                    b3 = this.b(f, 1);
                                    break Label_0172;
                                }
                            }
                            b3 = true;
                        }
                        if (!b3 || !b2) {
                            b = false;
                            break Label_0206;
                        }
                    }
                }
                a("Kein Jackenplatz", 1);
                b = true;
            }
        }
        if (!b) {
            a("Please do not leave gaps in the seats.", 4);
            this.a(-6, 0, (String[])null);
            return;
        }
        int j = 0;
        while (true) {
            while (j < this.i.getRowCount()) {
                final SaalplanRabattstufeDetails a4 = ((p)this.Y.get(j).getSelectedItem()).a();
                if (this.a(new Long(a4.getId())) < this.K.b(a4.getId(), this.i.a(j).e_(), this.i.b().get(j))) {
                    final boolean b4 = false;
                    if (!b4) {
                        final String[] y = this.y();
                        a("You have selected " + y[1] + " tickets for ticket type " + y[0] + ", but a minimum of " + y[2] + " is required.", 4);
                        this.a(-20, 0, y);
                        return;
                    }
                    if (com.eventim.applet.e.i(this.K.b()) && this.s() < this.K.d(this.K.b())) {
                        final String[] array;
                        (array = new String[3])[0] = this.K.e(this.K.b());
                        array[1] = String.valueOf(this.s());
                        array[2] = String.valueOf(this.K.d(this.K.b()));
                        final String[] array2 = array;
                        a("You have selected " + array2[1] + " tickets as part of the " + array2[0] + " promotion, but a minimum of " + array2[2] + " is required.", 4);
                        this.a(-22, 0, array2);
                        return;
                    }
                    if (!this.z()) {
                        final String[] w = this.w();
                        a("You have selected " + w[1] + " tickets for ticket type " + w[0] + ", but a multiple of " + w[2] + " is required.", 4);
                        this.a(-21, 0, w);
                        return;
                    }
                    if (this.s.O()) {
                        this.h = new u(this.L, this.s.C(), this.s.D(), new Color(1.0f, 1.0f, 1.0f, 1.0f), this);
                        this.L = true;
                        final Rectangle bounds;
                        this.h.setBounds((bounds = this.P.getBounds()).x + bounds.width / 2 - 160, bounds.y + bounds.height / 2 - 115, 320, 230);
                        this.getLayeredPane().add(this.h, new Integer(1), 0);
                        return;
                    }
                    this.setCursor(Cursor.getPredefinedCursor(3));
                    this.c(this.s.A().a("adding_seats_to_cart"));
                    SwingUtilities.invokeLater(new v(this));
                    return;
                }
                else {
                    ++j;
                }
            }
            final boolean b4 = true;
            continue;
        }
    }
    
    public final void b(final int n, final int n2) {
        (this = this).K.k(n);
        final t b;
        if ((b = this.b()) != null) {
            b.b(n, false);
        }
        this.a(false);
        if (b != null) {
            b.b(n, true);
        }
        if (this.S != null && this.S != b) {
            this.S.b(n, true);
        }
        this.C();
        this.g = (n < 0);
    }
    
    private void a(final double[] array, final t t) {
        final double n = array[0];
        final double n2 = array[1];
        final int width = t.b().getWidth();
        final int height = t.b().getHeight();
        final Rectangle viewRect = this.P.getViewport().getViewRect();
        this.a((int)Math.round(((width < viewRect.width) ? viewRect.width : width) * n), (int)Math.round(((height < viewRect.height) ? viewRect.height : height) * n2));
        this.P.setEnabled(true);
    }
    
    public final void p() {
        while (this.i.getRowCount() > 0) {
            final Integer b = this.i.b(0);
            this.Y.remove(0);
            final b c;
            if ((c = this.i.c(0)).f_()) {
                final f f;
                (f = (f)c).a(false);
                if (b.equals(this.p)) {
                    this.R.a(f, f.b(), f.a(), 1);
                }
                this.l.remove(f);
            }
        }
        this.C();
    }
    
    private static SaalplanPromotionDetails[] a(final SaalplanPromotionDetails[] array, final SaalplanSalesGroupPromotionDetails[] array2) {
        if (array == null) {
            return null;
        }
        final HashSet set = new HashSet<Integer>();
        for (int i = 0; i < array.length; ++i) {
            if (com.eventim.applet.e.b((long)array[i].getTdlFlag())) {
                boolean b = false;
                if (array2 != null) {
                    final int intValue = array[i].getId();
                    for (int j = 0; j < array2.length; ++j) {
                        if (array2[j].getPrId() == intValue) {
                            b = true;
                            break;
                        }
                    }
                }
                if (!b) {
                    set.add(array[i].getId());
                }
            }
        }
        if (set.isEmpty()) {
            return array;
        }
        final SaalplanPromotionDetails[] array3 = new SaalplanPromotionDetails[array.length - set.size()];
        int n = 0;
        for (int k = 0; k < array.length; ++k) {
            if (!set.contains(array[k].getId())) {
                array3[n] = array[k];
                ++n;
            }
        }
        return array3;
    }
    
    public final void b(final int n) {
        final Integer b = this.i.b(n);
        this.Y.remove(n);
        final b c;
        if ((c = this.i.c(n)).f_()) {
            final f f;
            (f = (f)c).a(false);
            if (b.equals(this.p)) {
                this.R.a(f, f.b(), f.a(), 1);
            }
            this.l.remove(f);
        }
        if (this.i.getRowCount() > 0) {
            this.r();
        }
        this.C();
    }
    
    private void B() {
        if (this.z != null) {
            this.z.setVisible(false);
            this.getLayeredPane().remove(this.z);
        }
    }
    
    public final Reply c(final int n) {
        final Hashtable u;
        Request.setCommand(u = u(), 95);
        u.put("SessionId", this.s.H());
        u.put("sprache", this.s.v());
        u.put("EventId", new Integer(this.s.m()));
        u.put("promotion_id", new Integer(n));
        Request.setCompressionType(u, 1);
        return this.a(u);
    }
    
    public final void a(final t viewportView) {
        this.P.remove(viewportView);
        this.P.setViewportView(viewportView);
    }
    
    private Rectangle a(final SeatplanObjects seatplanObjects) {
        final ArrayList list = new ArrayList();
        for (int i = 0; i < seatplanObjects.graphDetails.length; ++i) {
            if (seatplanObjects.graphDetails[i] != null && seatplanObjects.graphDetails[i].coords != null) {
                list.addAll(Arrays.asList(seatplanObjects.graphDetails[i].coords));
            }
        }
        final PixPoint[] array;
        final Rectangle a;
        final Rectangle rectangle = a = com.eventim.applet.a.n.a(array = (PixPoint[])list.toArray(new PixPoint[list.size()]));
        if ((this.ad == 0) ? (a.width * a.height > 5580000) : (a.width * a.height > 2790000)) {
            final Rectangle rectangle2 = rectangle;
            final double sqrt = Math.sqrt(((this.ad == 0) ? 5580000 : 2790000) / (rectangle2.width * rectangle2.height));
            for (int j = 0; j < seatplanObjects.graphDetails.length; ++j) {
                final GraphDetails graphDetails;
                if ((graphDetails = seatplanObjects.graphDetails[j]) != null) {
                    if (graphDetails.coords != null) {
                        if (graphDetails.isSitz()) {
                            final PixPoint[] coords = graphDetails.coords;
                            final double n = sqrt;
                            final PixPoint[] array2 = coords;
                            if (coords != null && array2.length >= 2) {
                                int n2;
                                if ((n2 = (int)Math.round(Math.min(Math.abs(array2[1].x - array2[0].x), Math.abs(array2[1].y - array2[0].y)) * n)) % 2 == 1) {
                                    if (n2 < 8) {
                                        ++n2;
                                    }
                                    else {
                                        --n2;
                                    }
                                }
                                array2[0].x = (short)Math.round(array2[0].x * n);
                                array2[0].y = (short)Math.round(array2[0].y * n);
                                array2[1].x = (short)(array2[0].x + n2);
                                array2[1].y = (short)(array2[0].y + n2);
                            }
                        }
                        else {
                            for (int k = 0; k < graphDetails.coords.length; ++k) {
                                graphDetails.coords[k].x = (short)Math.round(graphDetails.coords[k].x * sqrt);
                                graphDetails.coords[k].y = (short)Math.round(graphDetails.coords[k].y * sqrt);
                            }
                        }
                    }
                    if (graphDetails.isText() || graphDetails.isReiheBez()) {
                        final TextDetails textDetails = (TextDetails)seatplanObjects.graphDetails[j];
                        textDetails.size = (short)Math.floor(textDetails.size * sqrt);
                    }
                }
            }
            return com.eventim.applet.a.n.a(array);
        }
        return rectangle;
    }
    
    private void a(final Set q, final Set set, final boolean b) {
        this.q = q;
        final t b2;
        if ((b2 = this.b()) != null) {
            this.a(this.o, -1, false);
            b2.a(q, b);
            if (this.S != null && this.S != b2) {
                this.S.a(q, b);
            }
            if (b) {
                this.C();
            }
        }
    }
    
    private void a(final com.eventim.applet.a.g g) {
        final f[] a;
        if ((a = g.a()) != null) {
            for (int i = 0; i < a.length; ++i) {
                final f f;
                (f = a[i]).b(this.K.b(f.i(), f.e_()));
            }
        }
        final c[] c;
        if ((c = g.c()) != null) {
            for (int j = 0; j < c.length; ++j) {
                final c c2;
                (c2 = c[j]).b(this.K.b(c2.j(), c2.e_()));
            }
        }
    }
    
    private void b(final Hashtable hashtable) {
        int n = 0;
        final Hashtable<Long, Hashtable<Object, Integer>> hashtable2 = new Hashtable<Long, Hashtable<Object, Integer>>();
        final Hashtable<Object, Integer> hashtable3 = new Hashtable<Object, Integer>();
        for (int i = 0; i < this.i.getRowCount(); ++i) {
            final b a = this.i.a(i);
            final SaalplanRabattstufeDetails a2 = ((p)this.Y.get(i).getSelectedItem()).a();
            if (a.h()) {
                final c c = (c)a;
                if (hashtable2.get(new Long(c.k())) == null) {
                    final Hashtable<Object, Integer> hashtable4;
                    (hashtable4 = new Hashtable<Object, Integer>()).put(a2.getId(), new Integer(1));
                    hashtable2.put(new Long(c.k()), hashtable4);
                    ++n;
                }
                else {
                    final Hashtable<Object, Integer> hashtable5;
                    if ((hashtable5 = hashtable2.get(new Long(c.k()))).get(a2.getId()) == null) {
                        hashtable5.put(a2.getId(), new Integer(1));
                        ++n;
                    }
                    else {
                        int intValue = hashtable5.get(a2.getId());
                        ++intValue;
                        hashtable5.put(a2.getId(), new Integer(intValue));
                    }
                }
            }
            if (a.f_()) {
                hashtable3.put(new Long(((f)a).k()), a2.getId());
            }
        }
        final StehplatzReservierung[] array = new StehplatzReservierung[n];
        int n2 = 0;
        final Enumeration<Long> keys = hashtable2.keys();
        while (keys.hasMoreElements()) {
            final Long n3 = keys.nextElement();
            final Hashtable<Object, Integer> hashtable6;
            final Enumeration<Object> keys2 = (hashtable6 = hashtable2.get(n3)).keys();
            while (keys2.hasMoreElements()) {
                final Integer n4 = keys2.nextElement();
                array[n2] = new StehplatzReservierung();
                array[n2].stehId = n3;
                array[n2].webRabattstufeId = n4;
                array[n2].anzahl = hashtable6.get(n4);
                ++n2;
            }
        }
        hashtable.put("sitzId2rabattstufeId", hashtable3);
        hashtable.put("stehplatzreservieruengen", array);
    }
    
    private static void a(final com.eventim.applet.a.p[] array, final SaalplanEventDetails saalplanEventDetails) {
        final HashMap<Short, SectionDetails> hashMap = (HashMap<Short, SectionDetails>)new HashMap<Object, SectionDetails>();
        for (int i = 0; i < saalplanEventDetails.tdlSeatplanDetails.sectionDetails.length; ++i) {
            final SectionDetails sectionDetails = saalplanEventDetails.tdlSeatplanDetails.sectionDetails[i];
            hashMap.put(new Short(sectionDetails.id), sectionDetails);
        }
        for (int j = 0; j < array.length; ++j) {
            final com.eventim.applet.a.p p2 = array[j];
            final SectionDetails sectionDetails2 = hashMap.get(new Short(p2.i()));
            p2.a(sectionDetails2.anzahlPlaetzeProTdlPkIdProPrId);
            p2.c(sectionDetails2.anzFreiProPrId);
            p2.b(sectionDetails2.anzahlZusammenhaengendProTdlPkIdProPrId);
        }
    }
    
    public final void a(final Point viewPosition) {
        if (viewPosition != null) {
            this.P.getViewport().setViewPosition(viewPosition);
        }
    }
    
    public void start() {
        if (!this.W) {
            if (!this.t()) {
                this.a(0, 0, (String[])null);
                return;
            }
            final boolean f = this.K.f();
            if (this.ad == 1) {
                this.a(this.p, new com.eventim.applet.c(this, f));
            }
            else {
                this.a(this.p, new d(this, f));
            }
            this.W = true;
        }
    }
    
    public void tableChanged(final TableModelEvent tableModelEvent) {
        this.setCursor(Cursor.getPredefinedCursor(3));
        this.a(((n)tableModelEvent.getSource()).b(), null, true);
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    public final void q() {
        if (this.V) {
            this.V = false;
            this.v.setVisible(false);
            this.getLayeredPane().remove(this.v);
            this.setEnabled(true);
            this.v.removeAll();
            return;
        }
        this.V = true;
        final Dimension size = this.getSize();
        final ArrayList<Object> list = (ArrayList<Object>)new ArrayList<String>(this.b(-101, (this.ad == 1 && this.p == 0) ? 2 : 1, null));
        final ag a;
        (a = this.a(list.remove(0), list, false)).setBounds(14, 14, a.getWidth(), a.getHeight());
        this.v.add(a);
        final ArrayList<Object> list2 = (ArrayList<Object>)new ArrayList<String>(this.b(-101, (this.ad == 0) ? 3 : ((this.p == 0) ? 4 : 6), null));
        final ag a2;
        (a2 = this.a(list2.remove(0), list2, false)).setBounds(size.width - a2.getWidth() - 14, 14, a2.getWidth(), a2.getHeight());
        this.v.add(a2);
        final ArrayList<Object> list3 = (ArrayList<Object>)new ArrayList<String>(this.b(-101, 8, null));
        final ag a3;
        (a3 = this.a(list3.remove(0), list3, false)).setBounds(14, size.height - a3.getHeight() - 14, a3.getWidth(), a3.getHeight());
        this.v.add(a3);
        final ArrayList<Object> list4 = (ArrayList<Object>)new ArrayList<String>(this.b(-101, 9, null));
        final ag a4;
        (a4 = this.a(list4.remove(0), list4, false)).setBounds(size.width - a4.getWidth() - 14, size.height - a4.getHeight() - 14, a4.getWidth(), a4.getHeight());
        this.v.add(a4);
        final ArrayList<Object> list5 = (ArrayList<Object>)new ArrayList<String>(this.b(-101, 7, null));
        final ag a5 = this.a(list5.remove(0), list5, false);
        a5.setBounds(size.width - a5.getWidth() - 14, 14 + this.t.getHeight() + (size.height - a4.getHeight() - 14 - (14 + this.t.getHeight())) / 2 - a5.getHeight() / 2, a5.getWidth(), a5.getHeight());
        this.v.add(a5);
        this.v.setBounds(0, 0, this.N.getWidth(), this.N.getHeight());
        this.getLayeredPane().add(this.v, new Integer(3), 0);
        this.v.setVisible(true);
        this.P.setEnabled(false);
        this.k.setEnabled(false);
        if (this.I != null) {
            this.I.setEnabled(false);
        }
        if (this.E != null) {
            this.E.setEnabled(false);
        }
        this.c.setEnabled(false);
    }
    
    private void C() {
        if (this.S != null) {
            this.t.b(this.S.a(new Dimension(128, 128)));
            this.t.a();
        }
        if (this.R != null) {
            this.t.a(this.R.a(new Dimension(128, 128)));
            this.t.a();
        }
    }
    
    private void a(final boolean b) {
        final int b2;
        if ((b2 = this.K.b()) < 0) {
            return;
        }
        final Set a;
        final Long[] array = new Long[(a = this.K.a(new Integer(b2))).size()];
        final Boolean[] array2 = new Boolean[a.size()];
        final ImageIcon[] array3 = new ImageIcon[a.size()];
        final String[] array4 = new String[a.size()];
        final String[] array5 = new String[a.size()];
        final double[] array6 = new double[a.size()];
        final HashSet set = new HashSet<Object>();
        int n = 0;
        final Iterator<Long> iterator = a.iterator();
        while (iterator.hasNext()) {
            final SaalplanPlatzkategorieDetails a2 = this.K.a(iterator.next());
            int n3;
            int n2;
            if (this.s.c()) {
                n2 = (n3 = this.b((long)a2.getTdlPreisklasseId()));
            }
            else {
                final long n4 = a2.getTdlPreisklasseId();
                final int intValue = this.p;
                final long n5 = n4;
                if (intValue == 0) {
                    n2 = (n3 = this.b(n5));
                }
                else {
                    int n6 = 0;
                    if (intValue > 0 && intValue < this.s.a.tdlSeatplanDetails.sectionDetails.length) {
                        final SectionDetails sectionDetails = this.s.a.tdlSeatplanDetails.sectionDetails[intValue];
                        final Integer n7 = new Integer(this.K.b());
                        final Hashtable<Object, Integer> hashtable;
                        if (sectionDetails.anzahlPlaetzeProTdlPkIdProPrId != null && sectionDetails.anzahlPlaetzeProTdlPkIdProPrId.containsKey(n7) && (hashtable = sectionDetails.anzahlPlaetzeProTdlPkIdProPrId.get(n7)) != null && hashtable.containsKey(new Long(n5))) {
                            n6 = 0 + hashtable.get(new Long(n5));
                        }
                    }
                    n2 = (n3 = n6);
                }
            }
            final int n8 = n3;
            if (n2 <= 0) {
                set.add(new Integer(n));
            }
            array[n] = new Long(a2.getTdlPreisklasseId());
            EventimApplet.G.put(array[n], new Integer(n));
            array2[n] = (n8 > 0);
            array3[n] = com.eventim.applet.a.i.a(new Integer(n));
            final SaalplanRabattstufeDetails a3 = this.K.a((long)a2.getTdlPreisklasseId(), this.K.b());
            array4[n] = this.a(a3, this.s.N());
            array6[n] = this.b(a3, this.s.N());
            array5[n] = ((n8 <= 50) ? String.valueOf(n8) : "50+");
            ++n;
        }
        if (this.s.M()) {
            final Integer[] array7 = new Integer[a.size()];
            for (int i = 0; i < array7.length; ++i) {
                array7[i] = new Integer(i);
            }
            Arrays.sort(array7, new com.eventim.applet.b(array6));
            final Long[] array8 = new Long[a.size()];
            final Boolean[] array9 = new Boolean[a.size()];
            final ImageIcon[] array10 = new ImageIcon[a.size()];
            final String[] array11 = new String[a.size()];
            final String[] array12 = new String[a.size()];
            final HashSet<Integer> set2 = new HashSet<Integer>();
            for (int j = 0; j < a.size(); ++j) {
                array8[j] = array[array7[j]];
                EventimApplet.G.put(array8[j], new Integer(j));
                array9[j] = array2[array7[j]];
                array10[j] = com.eventim.applet.a.i.a(new Integer(j));
                array11[j] = array4[array7[j]];
                array12[j] = array5[array7[j]];
                if (set.contains(array7[j])) {
                    set2.add(new Integer(j));
                }
            }
            this.D = new n(array8, array9, array10, array11, array12, set2, this);
            this.q.addAll(Arrays.asList(array8));
            final Iterator<Object> iterator2 = set2.iterator();
            while (iterator2.hasNext()) {
                this.q.remove(array8[iterator2.next()]);
            }
            this.q.removeAll(this.r);
        }
        else {
            this.D = new n(array, array2, array3, array4, array5, set, this);
            this.q.addAll(Arrays.asList(array));
            final Iterator<Integer> iterator3 = (Iterator<Integer>)set.iterator();
            while (iterator3.hasNext()) {
                this.q.remove(array[iterator3.next()]);
            }
            this.q.removeAll(this.r);
        }
        if (a.isEmpty() && b2 == 0) {
            if (this.F != null) {
                this.E.b().remove(this.F);
            }
            this.E.b().add(this.T, "Center");
        }
        else {
            this.E.b().remove(this.T);
            if (this.F != null) {
                this.E.b().add(this.F, "Center");
            }
        }
        this.D.addTableModelListener(this);
        this.C.setModel(this.D);
        this.C.getTableHeader().setDefaultRenderer(new ad());
        this.C.getTableHeader().setBackground(this.s.C());
        this.C.setShowGrid(false);
        this.C.setIntercellSpacing(new Dimension(0, 3));
        this.C.setRowHeight(20);
        if (!a.isEmpty()) {
            this.C.getColumnModel().getColumn(0).setPreferredWidth(24);
            this.C.getColumnModel().getColumn(1).setPreferredWidth(20);
            if (this.s.J()) {
                this.C.getColumnModel().getColumn(3).setPreferredWidth(39);
            }
            this.C.setDefaultRenderer((EventimApplet.m == null) ? (EventimApplet.m = b("java.lang.Boolean")) : EventimApplet.m, new ai(this.C.getDefaultRenderer((EventimApplet.m == null) ? (EventimApplet.m = b("java.lang.Boolean")) : EventimApplet.m), this.D));
            this.C.setDefaultRenderer((EventimApplet.n == null) ? (EventimApplet.n = b("java.lang.String")) : EventimApplet.n, new ai(this.C.getDefaultRenderer((EventimApplet.n == null) ? (EventimApplet.n = b("java.lang.String")) : EventimApplet.n), this.D));
        }
        this.C.setRowSelectionAllowed(false);
        this.C.setColumnSelectionAllowed(false);
        this.C.setSelectionBackground(this.s.B());
        this.D.a(this.q);
        this.a(this.q, null, b);
    }
    
    public final void b(final t t) {
        this.ae.setEnabled(t.g() > 0);
        this.af.setEnabled(t.g() < t.d());
    }
    
    public void valueChanged(final ListSelectionEvent listSelectionEvent) {
    }
}
