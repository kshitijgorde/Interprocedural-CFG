// 
// Decompiled by Procyon v0.5.30
// 

package z;

import javax.swing.JComponent;
import java.awt.event.MouseEvent;
import javax.swing.border.Border;
import java.util.Iterator;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.event.ComponentListener;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.util.List;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class bj extends JPanel implements MouseListener
{
    private final String a;
    private final w b;
    private final bg c;
    private ImageIcon d;
    private ArrayList e;
    private Component f;
    private x g;
    private JPanel h;
    private JPanel i;
    private JPanel j;
    private JPanel k;
    private boolean l;
    private boolean m;
    private List n;
    private int o;
    private static /* synthetic */ boolean p;
    
    public bj(final w b, final bg c, final String a, final ImageIcon d) {
        this.e = new ArrayList();
        this.l = false;
        this.m = true;
        this.n = new ArrayList();
        this.o = 0;
        if (!bj.p && b == null) {
            throw new AssertionError();
        }
        if (!bj.p && c == null) {
            throw new AssertionError();
        }
        if (!bj.p && a == null) {
            throw new AssertionError();
        }
        if (!bj.p && d == null) {
            throw new AssertionError();
        }
        this.b = b;
        this.c = c;
        this.a = a;
        this.d = d;
        this.setOpaque(false);
        this.setLayout(new BoxLayout(this, 1));
        this.addComponentListener(new t(this, this));
    }
    
    protected final void a(final ImageIcon imageIcon) {
        if (this.l) {
            return;
        }
        final Iterator<bg> iterator = (Iterator<bg>)this.c.a().iterator();
        while (iterator.hasNext()) {
            final bg bg;
            final String b;
            if ((b = (bg = iterator.next()).b()).equals("photoTile")) {
                final bg bg2 = bg;
                final JPanel panel = new JPanel();
                final Dimension dimension = new Dimension(this.getWidth(), bg2.b("height"));
                panel.setLayout(new aB(dimension));
                panel.setOpaque(false);
                final JPanel panel2;
                (panel2 = new JPanel()).setOpaque(false);
                if (bg2.c("borderStyle").equals("line")) {
                    panel2.setBorder(BorderFactory.createLineBorder(bg2.d("borderColor"), bg2.b("borderThickness")));
                }
                panel.add(panel2);
                final JPanel panel3 = panel;
                final bg bg3 = bg2;
                final JPanel panel4 = panel3;
                if (!bj.p && bg3 == null) {
                    throw new AssertionError();
                }
                if (bg3.g("background")) {
                    final JLabel d;
                    (d = al.d(bg3.f("background"))).setHorizontalAlignment(0);
                    d.setVerticalAlignment(0);
                    panel4.add(d);
                }
                final Component component;
                final Border border;
                final Insets insets = ((border = ((JComponent)(component = panel2)).getBorder()) == null) ? new Insets(0, 0, 0, 0) : border.getBorderInsets(component);
                if (!bj.p && insets.left != insets.right) {
                    throw new AssertionError();
                }
                if (!bj.p && insets.top != insets.bottom) {
                    throw new AssertionError();
                }
                final Insets insets2 = insets;
                final Dimension dimension2;
                final int n = (dimension2 = new Dimension(dimension.width - (insets2.left + insets2.right), dimension.height - (insets2.top + insets2.bottom))).width - 2 * bg2.b("innerPaddingX");
                final int n2 = dimension2.height - 2 * bg2.b("innerPaddingY");
                final int n3 = n;
                final int n4 = n2;
                final bg f = bg2.f("thumbnailImage");
                final JLabel label;
                (label = new JLabel()).setIcon(V.a(imageIcon, new Dimension(n3, n4), Z.b));
                label.setOpaque(true);
                if (f.a("borderStyle") && f.c("borderStyle").equals("line")) {
                    label.setBorder(BorderFactory.createLineBorder(f.d("borderColor"), f.b("borderThickness")));
                }
                final Dimension maximumSize = new Dimension(label.getIcon().getIconWidth(), label.getIcon().getIconHeight());
                label.setPreferredSize(maximumSize);
                label.setMinimumSize(maximumSize);
                label.setMaximumSize(maximumSize);
                final JLabel label2;
                (label2 = label).setOpaque(false);
                final int n5 = dimension2.width - (int)label2.getPreferredSize().getWidth();
                final int n6 = dimension2.height - (int)label2.getPreferredSize().getHeight();
                final JPanel panel5;
                (panel5 = new JPanel()).setOpaque(false);
                panel2.setLayout(new BoxLayout(panel2, 1));
                panel2.add(Box.createVerticalStrut(n6 / 2));
                final int n7 = n6 - n6 / 2;
                panel2.add(panel5);
                panel2.add(Box.createVerticalStrut(n7));
                panel5.setLayout(new BoxLayout(panel5, 0));
                panel5.add(Box.createHorizontalStrut(n5 / 2));
                final int n8 = n5 - n5 / 2;
                panel5.add(label2);
                panel5.add(Box.createHorizontalStrut(n8));
                this.add(panel);
            }
            else if (b.equals("line")) {
                final JPanel c;
                if ((c = this.c(bg)) != null) {
                    this.add(c);
                }
                if (!bg.a("id") || !bg.c("id").equals("progress")) {
                    continue;
                }
                this.k = c;
            }
            else if (b.equals("dataPanel")) {
                this.add(this.h = this.a(bg));
            }
            else if (b.equals("vertGap")) {
                this.add(Box.createVerticalStrut(bg.b("size")));
            }
            else if (b.equals("completedPanel")) {
                this.i = b(bg);
            }
            else {
                if (!b.equals("failedPanel")) {
                    throw new RuntimeException("Unexpected child of thumbnailCell: " + b);
                }
                this.j = b(bg);
            }
        }
        this.add(this.f = Box.createVerticalGlue());
        this.validate();
    }
    
    private JPanel a(final bg bg) {
        final JPanel panel;
        (panel = new JPanel()).setOpaque(false);
        panel.setLayout(new BoxLayout(panel, 1));
        final Iterator<bg> iterator = bg.a().iterator();
        while (iterator.hasNext()) {
            final bg bg2;
            final String b;
            if ((b = (bg2 = iterator.next()).b()).equals("line")) {
                final JPanel c;
                if ((c = this.c(bg2)) == null) {
                    continue;
                }
                panel.add(c);
            }
            else {
                if (!b.equals("vertGap")) {
                    throw new RuntimeException("Unexpected child of thumbnailCell: " + b);
                }
                panel.add(Box.createVerticalStrut(bg2.b("size")));
            }
        }
        return panel;
    }
    
    private static JPanel b(final bg bg) {
        final JPanel panel;
        (panel = new JPanel()).setLayout(new BoxLayout(panel, 0));
        panel.setOpaque(false);
        final Iterator<bg> iterator = bg.a().iterator();
        while (iterator.hasNext()) {
            final bg bg2;
            final String b;
            if ((b = (bg2 = iterator.next()).b()).equals("filler")) {
                panel.add(Box.createHorizontalGlue());
            }
            else if (b.equals("icon")) {
                panel.add(al.d(bg2));
            }
            else {
                if (!b.equals("label")) {
                    throw new RuntimeException("Unexpected child of thumbnailCell: " + b);
                }
                panel.add(al.a(bg2));
            }
        }
        return panel;
    }
    
    private JPanel c(final bg bg) {
        final JPanel panel;
        (panel = new JPanel()).setOpaque(false);
        panel.setLayout(new BoxLayout(panel, 0));
        if (bg.a("hideDuringUpload") && bg.k("hideDuringUpload")) {
            if (!this.isEnabled()) {
                return null;
            }
            this.e.add(panel);
        }
        final Iterator<bg> iterator = bg.a().iterator();
        while (iterator.hasNext()) {
            final bg bg2;
            final String b;
            if ((b = (bg2 = iterator.next()).b()).equals("label")) {
                String s;
                if ((s = bg2.c("caption")).charAt(0) == '#') {
                    if (s.equals("#FILENAME")) {
                        s = au.a(this.a);
                    }
                    else if (s.equals("#FILESIZE")) {
                        s = au.a(this.b.b(this.a));
                    }
                    else if (s.equals("#FILEDATE")) {
                        s = this.b.e(this.a);
                    }
                }
                panel.add(al.b(bg2.c("style"), s));
            }
            else if (b.equals("button")) {
                final K b2;
                (b2 = al.b(bg2)).setPreferredSize(new Dimension(999, 0));
                b2.setMaximumSize(new Dimension(999, 999));
                b2.setName(bg2.c("action"));
                b2.addMouseListener(this);
                b2.setEnabled(this.isEnabled());
                panel.add(b2);
                this.n.add(b2);
            }
            else if (b.equals("icon")) {
                panel.add(al.d(bg2));
            }
            else if (b.equals("gap")) {
                panel.add(Box.createHorizontalStrut(bg2.b("size")));
            }
            else if (b.equals("filler")) {
                panel.add(Box.createHorizontalGlue());
            }
            else {
                if (!b.equals("progressBar")) {
                    throw new RuntimeException("Unexpected child of thumbnailCell/line: " + b);
                }
                panel.add(this.g = al.c(bg2));
            }
        }
        panel.invalidate();
        return panel;
    }
    
    public final void a(final boolean b) {
        this.l = true;
        if (this.i != null && this.j != null) {
            final int height = this.k.getHeight();
            this.remove(this.k);
            if (this.h != null) {
                this.remove(this.h);
            }
            this.remove(this.f);
            if (b) {
                this.i.setPreferredSize(new Dimension(1, height));
                this.add(this.i);
            }
            else {
                this.j.setPreferredSize(new Dimension(1, height));
                this.add(this.j);
            }
            if (this.h != null) {
                this.add(this.h);
            }
            this.add(this.f);
            this.validate();
        }
        this.repaint();
    }
    
    public final void a(final int n) {
        if (!bj.p && (0 > n || n > 100)) {
            throw new AssertionError();
        }
        if (this.g != null) {
            --this.o;
            if (this.o > 0) {
                return;
            }
            this.o = 5;
            this.g.a(n);
            this.g.setVisible(n > 0);
        }
    }
    
    public final void a() {
        if (this.g != null) {
            this.g.a(true);
            this.g.repaint();
        }
    }
    
    public void setEnabled(final boolean b) {
        super.setEnabled(b);
        final Iterator<JLabel> iterator = this.n.iterator();
        while (iterator.hasNext()) {
            iterator.next().setEnabled(b);
        }
        if (!b) {
            final Iterator<JPanel> iterator2 = this.e.iterator();
            while (iterator2.hasNext()) {
                final JPanel panel;
                final Container parent;
                if ((parent = (panel = iterator2.next()).getParent()) != null) {
                    parent.remove(panel);
                    parent.invalidate();
                }
            }
            this.e.clear();
        }
        this.validate();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (!this.isEnabled()) {
            return;
        }
        final String name;
        if ((name = ((Component)mouseEvent.getSource()).getName()).equals("removePhoto")) {
            new q(this).b();
            return;
        }
        if (!name.equals("zoom")) {
            throw new RuntimeException("Unexpected button action: " + name);
        }
        if (this.m) {
            af.a(this.a);
        }
    }
    
    public final void b() {
        this.m = false;
    }
    
    static {
        bj.p = !bj.class.desiredAssertionStatus();
    }
}
