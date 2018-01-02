import java.io.File;
import java.security.AccessControlException;
import net.eprevue.easyplanreader.EPReader;
import java.awt.event.AdjustmentEvent;
import java.util.List;
import java.awt.Window;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.AbstractButton;
import java.awt.event.ComponentListener;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.border.Border;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Cursor;
import java.awt.Frame;
import javax.swing.JScrollBar;
import java.awt.event.AdjustmentListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_es extends rp_fG implements AdjustmentListener, rp_ar
{
    rp_aJ a;
    private rp_aH a;
    private rp_aH b;
    private rp_dl a;
    private JScrollBar a;
    private boolean a;
    private rp_f a;
    private static rp_v a;
    private rp_v b;
    private byte[] a;
    private Frame a;
    private int a;
    private rp_v c;
    private static String a;
    
    public rp_es(final Frame a, final rp_aJ a2, final rp_f a3, final rp_v c) {
        super(a2.a().a(0, "pod1"));
        this.a = false;
        this.a = null;
        this.b = null;
        this.a = null;
        this.a = 0;
        this.c = null;
        this.a = a;
        this.a = a2;
        this.a = a3;
        this.c = c;
        if (rp_es.a == null) {
            final Cursor cursor = this.getCursor();
            this.setCursor(Cursor.getPredefinedCursor(3));
            a3.a().a(a2.a());
            this.setCursor(cursor);
            rp_es.a = a3.a();
        }
        if (a2.a()) {
            this.a(a2.a().a(0, "EPI1"), "EP1");
        }
        this.a(a2.a().a(0, "cl"), "C");
        this.a();
        this.b();
    }
    
    public final void a(final JPanel panel) {
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        final JPanel panel2;
        (panel2 = new JPanel()).setLayout(new BoxLayout(panel2, 1));
        final int n = (int)(1.8 * this.a.a("b_move.png").getHeight(null));
        final JPanel panel3 = new JPanel(new BorderLayout(0, 0));
        final JPanel panel4 = new JPanel();
        final AbstractButton a;
        (a = rp_ap.a(this.a, null, null, "folder_up.png", "pod3", "FUP", this, false)).setBorder(null);
        panel4.setBorder(null);
        panel4.add(a);
        panel4.add(Box.createHorizontalStrut(15));
        panel4.add(new JLabel(this.a.a().a(0, "pod2")));
        panel3.add(panel4, "West");
        (this.a = rp_ap.a(this.a, this.a, "FC", "podM", this)).setPreferredSize(new Dimension(200, n));
        panel3.add(this.a, "Center");
        final JPanel panel5;
        (panel5 = new JPanel()).setBorder(null);
        panel5.add(rp_ap.a(this.a, null, null, "b_new.png", "pod4", "FN", this, false));
        panel5.add(rp_ap.a(this.a, null, null, "b_del.png", "pod5", "DEL", this, false));
        panel5.add(rp_ap.a(this.a, null, null, "b_ren.png", "pod6", "REN", this, false));
        panel5.add(rp_ap.a(this.a, null, null, "b_all.png", "pod7", "SA", this, false));
        panel5.add(rp_ap.a(this.a, null, null, "b_none.png", "pod8", "SN", this, false));
        panel5.add(rp_ap.a(this.a, null, null, "b_move.png", "podA", "MOV", this, false));
        (this.b = rp_ap.a(this.a, this.a, "FC2", "podN", this)).setPreferredSize(new Dimension(200, n));
        panel5.add(this.b, "Center");
        panel2.add(panel3);
        panel2.add(panel5);
        panel.add(panel2, "North");
        (this.a = new rp_dl(new rp_by())).setBackground(rp_aJ.c);
        this.a.setPreferredSize(new Dimension(610, 396));
        this.a.a((rp_ar)this);
        this.a.a((rp_es.a == null) ? 0 : rp_es.a.getChildCount(), (Object)null);
        this.a.addComponentListener(new rp_bq(this));
        this.a(rp_es.a, 3);
        this.addComponentListener(new rp_bp(this));
        (this.a = new JScrollBar(1)).addAdjustmentListener(this);
        final JPanel panel6;
        (panel6 = new JPanel(new BorderLayout())).add(this.a, "Center");
        panel6.add(this.a, "East");
        panel.add(panel6, "Center");
    }
    
    private void c() {
        if (this.a != null && this.a != null) {
            this.a.setMinimum(0);
            int maximum = this.a.b() / this.a.a;
            if (this.a.b() % this.a.a > 0) {
                ++maximum;
            }
            this.a.setMaximum(maximum);
            this.a.setBlockIncrement(1);
            this.a.setUnitIncrement(1);
            this.a.setVisibleAmount(this.a.b);
        }
    }
    
    protected final boolean b() {
        return false;
    }
    
    public final boolean e() {
        return this.a;
    }
    
    public final byte[] a() {
        return this.a;
    }
    
    public final rp_v a() {
        return this.b;
    }
    
    private static void a(rp_aH rp_aH) {
        ((DefaultTreeModel)(rp_aH = rp_aH).a.getModel()).reload();
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("FC")) {
            this.a((rp_v)((TreePath)((rp_ef)actionEvent.getSource()).getSelectedItem()).getLastPathComponent(), 2);
            return;
        }
        if (actionEvent.getActionCommand().equals("FC2")) {
            return;
        }
        if (actionEvent.getActionCommand().equals("FUP")) {
            if (null != rp_es.a.getParent()) {
                this.a((rp_v)rp_es.a.getParent(), 3);
            }
            return;
        }
        if (actionEvent.getActionCommand().equals("FN")) {
            final rp_er rp_er;
            (rp_er = new rp_er(this.a, this.a, rp_es.a, rp_bF.a)).setVisible(true);
            final String a;
            if ((a = rp_er.a) != null) {
                rp_es.a.a(new rp_v(a, rp_er.a, -1));
                this.d();
                a(this.a);
                a(this.b);
            }
            return;
        }
        if (actionEvent.getActionCommand().equals("DEL")) {
            final List a2;
            if ((a2 = rp_es.a.a()) != null && a2.size() > 0 && 1 == rp_bd.b(this.a, this.a.a(), "wrn", "pod9", true)) {
                final rp_v[] array = a2.toArray(new rp_v[a2.size()]);
                final String a3;
                if ((a3 = a(a2, this.c, false)) != null) {
                    rp_bd.a(this.a, this.a.a(), "err", a3);
                    return;
                }
                if (this.a.a().a(rp_aw.h, array, null).a()) {
                    for (int i = 0; i < a2.size(); ++i) {
                        a2.get(i).removeFromParent();
                    }
                    this.d();
                    a(this.a);
                    a(this.b);
                }
            }
            return;
        }
        if (actionEvent.getActionCommand().equals("REN")) {
            final List a4;
            if ((a4 = rp_es.a.a()) == null || a4.size() != 1) {
                rp_bd.a(this.a, this.a.a(), "err", "podK");
                return;
            }
            final String a5;
            if ((a5 = a(a4, this.c, true)) != null) {
                rp_bd.a(this.a, this.a.a(), "err", a5);
                return;
            }
            final rp_v rp_v = a4.get(0);
            final rp_er rp_er2;
            (rp_er2 = new rp_er(this.a, this.a, rp_v, rp_bF.b)).setVisible(true);
            final String a6;
            if ((a6 = rp_er2.a) != null) {
                rp_v.a = a6;
                this.d();
                a(this.a);
                a(this.b);
            }
        }
        else {
            if (actionEvent.getActionCommand().equals("SA")) {
                if (rp_es.a != null) {
                    rp_es.a.a(true);
                    this.a.repaint();
                }
                return;
            }
            if (actionEvent.getActionCommand().equals("SN")) {
                if (rp_es.a != null) {
                    rp_es.a.a(false);
                    this.a.repaint();
                }
                return;
            }
            if (actionEvent.getActionCommand().equals("MOV")) {
                System.out.println("Move clicked");
                final List a7;
                if ((a7 = rp_es.a.a()) != null && a7.size() > 0) {
                    final rp_v rp_v2 = (rp_v)((TreePath)this.b.getSelectedItem()).getLastPathComponent();
                    final List<rp_v> list = (List<rp_v>)a7;
                    final rp_v rp_v3 = rp_v2;
                    final List<rp_v> list2 = list;
                    String s = null;
                    final String b = rp_v3.b('/');
                    for (int j = 0; j < list2.size(); ++j) {
                        final rp_v rp_v4;
                        if ((rp_v4 = list2.get(j)).a()) {
                            final String b2 = rp_v4.b('/');
                            if (b.equals(b2)) {
                                s = "podB";
                                break;
                            }
                            if (rp_v4.a() && b.startsWith(b2)) {
                                s = "podC";
                                break;
                            }
                        }
                        else if (b.equals(rp_v4.a('/'))) {
                            s = "podB";
                            break;
                        }
                        if (null != rp_v3.a(rp_v4.a)) {
                            s = "podD";
                            break;
                        }
                    }
                    String a8;
                    if ((a8 = s) == null) {
                        a8 = a(a7, this.c, false);
                    }
                    if (a8 != null) {
                        rp_bd.a(this.a, this.a.a(), "err", a8);
                        return;
                    }
                    if (this.a.a().a(rp_aw.j, a7.toArray(new rp_v[a7.size()]), rp_v2).a()) {
                        for (int k = 0; k < a7.size(); ++k) {
                            rp_v2.a(a7.get(k));
                        }
                        this.d();
                        a(this.a);
                        a(this.b);
                    }
                }
                return;
            }
            if (actionEvent.getActionCommand().equals("EP1") && !this.f()) {
                return;
            }
            super.actionPerformed(actionEvent);
        }
    }
    
    private static String a(final List list, final rp_v rp_v, final boolean b) {
        if (rp_v == null) {
            return null;
        }
        String s = null;
        final String b2 = rp_v.b('/');
        for (int i = 0; i < list.size(); ++i) {
            final rp_v rp_v2 = list.get(i);
            if (!b && rp_v2.a()) {
                final String b3 = rp_v2.b('/');
                if (rp_v2.a() && b2.startsWith(b3)) {
                    s = "podO";
                    break;
                }
            }
            if (!rp_v2.a() && b2.equals(rp_v2.b('/'))) {
                s = "podO";
                break;
            }
        }
        return s;
    }
    
    public final void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.a = adjustmentEvent.getValueIsAdjusting();
        final rp_dl a = this.a;
        final int c = adjustmentEvent.getValue() * this.a.a;
        final rp_dl rp_dl = a;
        a.c = c;
        rp_dl.a(true);
        if (!this.a) {
            final rp_dl a2;
            final int c2 = (a2 = this.a).c;
            for (int min = Math.min(a2.b.size(), a2.c + a2.a()), i = c2; i < min; ++i) {
                final rp_ai rp_ai;
                if ((rp_ai = a2.b.elementAt(i)) != null) {
                    rp_ai.a();
                }
            }
        }
    }
    
    private boolean f() {
        String s = null;
        try {
            final File queryFileFromUser;
            if ((queryFileFromUser = EPReader.queryFileFromUser((Component)rp_au.a(), rp_es.a)) != null) {
                rp_es.a = queryFileFromUser.getPath();
                this.b = new rp_v(queryFileFromUser.getName(), this.a.a().a(0, "podI"));
                System.out.println("Choosen file: " + queryFileFromUser);
                this.a = EPReader.convert(queryFileFromUser);
                System.out.println("Plan data: " + new String(this.a));
                return true;
            }
        }
        catch (AccessControlException ex) {
            rp_bd.a(this.a, this.a.a().a(0, "EPI1"), this.a.a().a(0, "EPI4"), this.a.a().a(0, "OK"));
        }
        catch (NoClassDefFoundError noClassDefFoundError) {
            noClassDefFoundError.printStackTrace();
            s = "EPI5";
        }
        catch (Throwable t) {
            t.printStackTrace();
            s = "EPI3";
        }
        if (s != null) {
            final rp_cs rp_cs;
            (rp_cs = new rp_cs(this, this.a.a().a(), this.a.a().a(0, "wrn"), s)).a("OK", null);
            rp_cs.setVisible(true);
        }
        this.a = null;
        return false;
    }
    
    final void a(final rp_v a, final int n) {
        if (a == null) {
            return;
        }
        final Cursor cursor = this.getCursor();
        this.setCursor(Cursor.getPredefinedCursor(3));
        a.a(this.a.a());
        this.setCursor(cursor);
        rp_es.a = a;
        if (n != 2) {
            this.a.setSelectedItem(new TreePath(a.getPath()));
        }
        rp_es.a.a(false);
        this.d();
        if (this.a != null) {
            this.a.setValue(0);
        }
    }
    
    private void d() {
        this.a.a((rp_es.a == null) ? 0 : rp_es.a.getChildCount(), (Object)null);
        this.c();
    }
    
    final void a(final rp_v b) {
        this.b = b;
        this.a = null;
        this.dispose();
    }
    
    public final void a(final rp_dl rp_dl, final int n, final int n2) {
        for (int n3 = n; n3 < n + n2 && n3 < rp_es.a.getChildCount(); ++n3) {
            rp_dl.a(n3, new rp_fE(this, this.a, (rp_v)rp_es.a.getChildAt(n3)));
        }
    }
    
    static void a(final rp_dl rp_dl) {
        final Dimension a = rp_dl.a();
        rp_dl.a(Math.max(1, a.width / 200));
        rp_dl.b(Math.max(1, a.height / 180));
    }
    
    static {
        rp_es.a = null;
        rp_es.a = null;
    }
}
