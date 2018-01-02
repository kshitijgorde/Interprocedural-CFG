// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.FontMetrics;
import java.awt.Container;
import java.awt.Panel;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.Frame;
import java.net.URL;
import java.awt.Event;
import java.awt.Image;
import java.awt.Canvas;
import java.awt.TextArea;

public class bc extends X implements aj
{
    protected TextArea d;
    protected Canvas p;
    protected Canvas q;
    protected Canvas r;
    protected be k;
    protected bl b;
    protected int g;
    protected aI b;
    private Canvas a;
    private Canvas b;
    private Canvas c;
    private Canvas d;
    private Canvas e;
    private aa b;
    
    private final Canvas a(final String s, final String s2, final String s3, final String s4) {
        aj a = null;
        if (this.k.c.h() && s != null) {
            final Image a2 = this.k.a(s + s2 + "_button_up.gif", true);
            final Image a3 = this.k.a(s + s2 + "_button_dn.gif", true);
            if (a2 != null && a3 != null) {
                a = o.a(a2, a3, null);
                ((o)a).a(s4, null);
            }
        }
        if (a == null) {
            if (s3 == null) {
                a = new al(70, 20);
            }
            else {
                a = new al(s3, 70, 20);
            }
            ((al)a).a(s4, null);
        }
        return (al)a;
    }
    
    private final Canvas a(final String s, final String s2, final String s3) {
        return this.a(s, s2, s3, 25, 25);
    }
    
    private final Canvas a(final String s, final String s2, final String s3, final int n, final int n2) {
        try {
            aj a;
            if (!this.k.c.h() || s == null) {
                a = new al(n, n2);
                ((al)a).a(this.k.a(s3, false, 20));
            }
            else {
                final Image a2 = this.k.a(s + s2 + "_button_up.gif", true);
                final Image a3 = this.k.a(s + s2 + "_button_dn.gif", true);
                final Image a4 = this.k.a(s + s2 + "_button_disabled.gif", true);
                if (a2 == null || a3 == null || a4 == null) {
                    a = new al(25, 25);
                    ((al)a).a(this.k.a(s3, false, 20));
                }
                else {
                    a = o.a(a2, a3, a4);
                }
            }
            return (al)a;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new Canvas();
        }
    }
    
    public String a(final Object o) {
        if (o == this.d) {
            return aG.a("Type your message here, then hit ENTER or click \"Send\" to send it to all users in the current room.");
        }
        if (o == this.a) {
            return aG.a("Click here to insert emoticons into your message.");
        }
        if (o == this.b) {
            return aG.a("Click here to insert a shortcut word into your message.");
        }
        if (o == this.c) {
            return aG.a("Select text then click here to make it Bold.");
        }
        if (o == this.d) {
            return aG.a("Select text then click here to make it Italic.");
        }
        return null;
    }
    
    public void a(final aN an) {
        this.b.a(an);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 27) {
                    this.d.append("\n");
                    break;
                }
                if (event.modifiers == 2) {
                    if (event.key == 18) {
                        this.d();
                        return true;
                    }
                    if (event.key == 5) {
                        this.g();
                        return true;
                    }
                    if (event.key == 9) {
                        a("i", this.d);
                        return true;
                    }
                    if (event.key == 2) {
                        a("b", this.d);
                        return true;
                    }
                    if (event.key == 1) {
                        a(this.d);
                        return true;
                    }
                    if (event.key == 10) {
                        this.d.append("\n");
                        this.d.setCaretPosition(this.d.getText().length());
                        return true;
                    }
                }
                if (event.key != 10 && event.key != F.e) {
                    break;
                }
                if (this.p instanceof al) {
                    ((al)this.p).e();
                }
                else {
                    ((o)this.p).c();
                }
                return true;
            }
            case 1001: {
                if (event.target == this.a) {
                    this.g();
                    return true;
                }
                if (event.target == this.b) {
                    this.d();
                    return true;
                }
                if (event.target == this.c) {
                    a("b", this.d);
                    return true;
                }
                if (event.target == this.d) {
                    a("i", this.d);
                    return true;
                }
                if (event.target == this.e) {
                    this.b.a();
                    return true;
                }
                if (event.target == this.p) {
                    final String trim = this.d.getText().trim();
                    this.d.setText("");
                    if (trim.length() > 0) {
                        this.k.a(trim, this.g, -1, this.b.e, this.d.getFont().isBold(), this.d.getFont().isItalic());
                        this.b.d();
                    }
                    if (F.a) {
                        this.d.requestFocus();
                    }
                    return true;
                }
                if (event.target == this.q) {
                    final V v = new V(67074, 1);
                    v.a(0, 0, this.k.b());
                    v.j = this.g;
                    this.k.F(v);
                    return true;
                }
                if (event.target == this.r) {
                    break;
                }
                if (event.arg instanceof URL) {
                    this.k.a((URL)event.arg, "_blank");
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void a() {
        this.b.g();
    }
    
    public void dispose() {
        super.dispose();
        this.k.b.b(this.g);
    }
    
    public boolean d() {
        return false;
    }
    
    private void g() {
        if (be.Y) {
            final g g = new g(null, this.k, this.d);
            g.c();
            g.setVisible(true);
        }
    }
    
    private void d() {
        if (be.Z) {
            final r r = new r(null, this.k, this.d);
            r.c();
            r.setVisible(true);
        }
    }
    
    public static void a(final String s, final TextArea textArea) {
        if (be.U) {
            if (s.equalsIgnoreCase("b")) {
                final Font font = textArea.getFont();
                textArea.setFont(new Font(font.getFamily(), (font.isBold() ? 0 : 1) + (font.isItalic() ? 2 : 0), font.getSize()));
            }
            else if (s.equalsIgnoreCase("i")) {
                final Font font2 = textArea.getFont();
                textArea.setFont(new Font(font2.getFamily(), (font2.isBold() ? 1 : 0) + (font2.isItalic() ? 0 : 2), font2.getSize()));
            }
        }
    }
    
    public static void a(final TextArea textArea) {
        textArea.setSelectionStart(0);
        textArea.setSelectionEnd(textArea.getText().length());
    }
    
    public bc(final Frame frame, final be k, final int g, final bl b) {
        this.setBackground(k.c.c);
        this.k = k;
        this.g = g;
        this.b = (aI)k.d.b(g);
        this.setTitle(aC.a(aG.a("Private Conversation with %1"), new String[] { this.b.g() }));
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final n n = new n();
        this.setLayout(layout);
        n.setLayout(layout);
        try {
            this.d = new TextArea("", 2, 10, 3);
        }
        catch (Throwable t) {
            this.d = new TextArea(2, 10);
        }
        (this.b = b).setFont(k.c.b());
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final aX ax = new aX(b);
        layout.setConstraints(ax, gridBagConstraints);
        n.add(ax);
        this.d.setFont(aK.d);
        final aX ax2 = new aX(this.d);
        final Container container;
        (container = new Panel()).setLayout(layout);
        final Panel panel = new Panel(new GridBagLayout());
        if (be.W || be.U || be.Y || be.Z) {
            final String f = k.c.f();
            this.a = this.a(f, "emoticons", "emoticonsIcon.gif");
            this.b = this.a(f, "shortcuts", "shortcutsIcon.gif");
            this.c = this.a(f, "bold", "boldIcon.gif");
            this.d = this.a(f, "italic", "italicIcon.gif");
            this.e = this.a(f, "colors", "colorsIcon.gif", 13, 50);
            (this.b = new aa(this.k, this.d, true)).setSize(1, 1);
            this.b.a(this.k.c.p);
            this.b.a(this.k.r, true);
            final GridBagConstraints gridBagConstraints2;
            (gridBagConstraints2 = new GridBagConstraints()).fill = 0;
            gridBagConstraints2.anchor = 17;
            gridBagConstraints2.insets = new Insets(0, 1, 0, 1);
            if (be.W) {
                gridBagConstraints2.gridwidth = 1;
                gridBagConstraints2.gridheight = 2;
                gridBagConstraints2.weighty = 1.0;
                panel.add(this.e, gridBagConstraints2);
                panel.add(this.b, gridBagConstraints2);
            }
            gridBagConstraints2.weighty = 0.0;
            gridBagConstraints2.gridheight = 1;
            if (be.U) {
                gridBagConstraints2.gridx = 1;
                gridBagConstraints2.gridy = 0;
                panel.add(this.c, gridBagConstraints2);
                gridBagConstraints2.gridx = 1;
                gridBagConstraints2.gridy = 1;
                panel.add(this.d, gridBagConstraints2);
            }
            if (be.Y) {
                gridBagConstraints2.gridx = 2;
                gridBagConstraints2.gridy = 0;
                panel.add(this.a, gridBagConstraints2);
            }
            if (be.Z) {
                gridBagConstraints2.gridx = 2;
                gridBagConstraints2.gridy = 1;
                panel.add(this.b, gridBagConstraints2);
            }
        }
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(ax2, gridBagConstraints);
        container.add(ax2);
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        container.add(panel, gridBagConstraints);
        gridBagConstraints.insets = new Insets((be.U || be.Y || be.Z) ? 0 : 2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        layout.setConstraints(container, gridBagConstraints);
        n.add(container);
        n.setBackground(k.c.k);
        n.setForeground(k.c.j);
        boolean b2 = (!F.b || F.f != 1) && k.c(20) && this.b.c(21) && this.b.b() != k.b();
        if (!this.d()) {
            b2 = false;
        }
        final String f2 = k.c.f();
        final String s = b2 ? "_3" : "_2";
        this.q = this.a(f2, "profile_request" + s, aG.a("Profile"), aC.a(aG.a("Click here to get the profile of %1. This will return information entered by the user, such as his or her real name."), new String[] { this.b.g() }));
        this.p = this.a(f2, "send_small" + s, aG.a("Send"), aG.a("Click here, or press the RETURN or ENTER key, to send your message to all users in the current room."));
        final FontMetrics fontMetrics = this.q.getFontMetrics(aK.f);
        int stringWidth = fontMetrics.stringWidth(aG.a("Send"));
        final int stringWidth2 = fontMetrics.stringWidth(aG.a("Profile"));
        if (stringWidth2 > stringWidth) {
            stringWidth = stringWidth2;
        }
        if (b2) {
            this.r = this.a(f2, "file" + s, aG.a("File Transfer"), aC.a(aG.a("Click here to send a file to %1."), new String[] { this.b.g() }));
        }
        gridBagConstraints.insets = ((this.q instanceof o || this.p instanceof o || this.r instanceof o) ? new Insets(0, 0, 0, 0) : new Insets(2, 3, 2, 3));
        gridBagConstraints.weightx = ((this.p instanceof o) ? 0.0 : 0.2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.gridwidth = (b2 ? -1 : 0);
        if (this.p instanceof al) {
            final P p4 = new P(this.p);
            layout.setConstraints(p4, gridBagConstraints);
            n.add(p4);
        }
        else {
            layout.setConstraints(this.p, gridBagConstraints);
            n.add(this.p);
        }
        gridBagConstraints.gridheight = (b2 ? -1 : 0);
        gridBagConstraints.weightx = ((this.q instanceof o) ? 0.0 : 0.2);
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.q, gridBagConstraints);
        if (this.q instanceof al) {
            this.q.resize(stringWidth2, 20);
        }
        n.add(this.q);
        if (b2) {
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridheight = 0;
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.r, gridBagConstraints);
            n.add(this.r);
        }
        if (this.p instanceof al) {
            this.p.resize(stringWidth, 20);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(n, gridBagConstraints);
        this.add(n);
    }
}
