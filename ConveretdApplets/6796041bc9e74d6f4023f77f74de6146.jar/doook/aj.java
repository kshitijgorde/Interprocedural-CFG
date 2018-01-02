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

public class aj extends aO implements aB
{
    protected TextArea b;
    protected Canvas e;
    protected Canvas f;
    protected Canvas g;
    protected t g;
    protected ay a;
    protected int ai;
    protected cG b;
    private Canvas n;
    private Canvas o;
    private Canvas p;
    private Canvas q;
    private Canvas r;
    private aX c;
    
    private final Canvas a(final String s, final String s2, final String s3, final String s4) {
        aB a = null;
        if (this.g.a.p() && s != null) {
            final Image a2 = this.g.a(s + s2 + "_button_up.gif", true);
            final Image a3 = this.g.a(s + s2 + "_button_dn.gif", true);
            if (a2 != null && a3 != null) {
                a = cz.a(a2, a3, null);
                ((cz)a).a(s4, null);
            }
        }
        if (a == null) {
            if (s3 == null) {
                a = new aS(70, 20);
            }
            else {
                a = new aS(s3, 70, 20);
            }
            ((aS)a).a(s4, null);
        }
        return (aS)a;
    }
    
    private final Canvas a(final String s, final String s2, final String s3) {
        return this.a(s, s2, s3, 25, 25);
    }
    
    private final Canvas a(final String s, final String s2, final String s3, final int n, final int n2) {
        try {
            aB a;
            if (!this.g.a.p() || s == null) {
                a = new aS(n, n2);
                ((aS)a).a(this.g.a(s3, false, 20));
            }
            else {
                final Image a2 = this.g.a(s + s2 + "_button_up.gif", true);
                final Image a3 = this.g.a(s + s2 + "_button_dn.gif", true);
                final Image a4 = this.g.a(s + s2 + "_button_disabled.gif", true);
                if (a2 == null || a3 == null || a4 == null) {
                    a = new aS(n, n2);
                    ((aS)a).a(this.g.a(s3, false, 20));
                }
                else {
                    a = cz.a(a2, a3, a4);
                }
            }
            return (aS)a;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new Canvas();
        }
    }
    
    public String a(final Object o) {
        if (o == this.b) {
            return ao.e("Type your message here, then hit ENTER or click \"Send\" to send it to all users in the current room.");
        }
        if (o == this.n) {
            return ao.e("Click here to insert emoticons into your message.");
        }
        if (o == this.o) {
            return ao.e("Click here to insert a shortcut word into your message.");
        }
        if (o == this.r) {
            return ao.e("Click here and select your message color.");
        }
        if (o == this.p) {
            return ao.e("Click here to make text Bold.");
        }
        if (o == this.q) {
            return ao.e("Click here to make text Italic.");
        }
        return null;
    }
    
    public void a(final Z z) {
        this.a.a(z);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 27) {
                    this.b.append("\n");
                    break;
                }
                if (event.modifiers == 2) {
                    if (event.key == 18) {
                        this.p();
                        return true;
                    }
                    if (event.key == 5) {
                        this.s();
                        return true;
                    }
                    if (event.key == 9) {
                        a("i", this.b);
                        return true;
                    }
                    if (event.key == 2) {
                        a("b", this.b);
                        return true;
                    }
                    if (event.key == 1) {
                        a(this.b);
                        return true;
                    }
                    if (event.key == 10) {
                        this.b.append("\n");
                        this.b.setCaretPosition(this.b.getText().length());
                        return true;
                    }
                }
                if (event.key != 10 && event.key != doook.f.g) {
                    break;
                }
                if (this.e instanceof aS) {
                    ((aS)this.e).s();
                }
                else {
                    ((cz)this.e).a();
                }
                return true;
            }
            case 1001: {
                if (event.target == this.n) {
                    this.s();
                    return true;
                }
                if (event.target == this.o) {
                    this.p();
                    return true;
                }
                if (event.target == this.p) {
                    a("b", this.b);
                    return true;
                }
                if (event.target == this.q) {
                    a("i", this.b);
                    return true;
                }
                if (event.target == this.r) {
                    this.c.c();
                    return true;
                }
                if (event.target == this.e) {
                    final String trim = this.b.getText().trim();
                    this.b.setText("");
                    if (trim.length() > 0) {
                        this.g.a(trim, this.ai, -1, this.c.aA, this.b.getFont().isBold(), this.b.getFont().isItalic());
                        this.a.s();
                    }
                    if (doook.f.h) {
                        this.b.requestFocus();
                    }
                    return true;
                }
                if (event.target == this.f) {
                    final cD cd = new cD(67074, 1);
                    cd.a(0, 0, this.g.h());
                    cd.j = this.ai;
                    this.g.o(cd);
                    return true;
                }
                if (event.target == this.g) {
                    break;
                }
                if (event.arg instanceof URL) {
                    this.g.a((URL)event.arg, "_blank");
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void a() {
        this.a.b();
    }
    
    public void dispose() {
        super.dispose();
        this.g.a.c(this.ai);
    }
    
    public boolean c() {
        return false;
    }
    
    private void s() {
        if (t.Q) {
            final ck ck = new ck(null, this.g, this.b);
            ck.d();
            ck.setVisible(true);
        }
    }
    
    private void p() {
        if (t.R) {
            final cq cq = new cq(null, this.g, this.b);
            cq.d();
            cq.setVisible(true);
        }
    }
    
    public static void a(final String s, final TextArea textArea) {
        if (t.N) {
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
    
    public aj(final Frame frame, final t g, final int ai, final ay a) {
        this.setBackground(g.a.a);
        this.g = g;
        this.ai = ai;
        this.b = (cG)g.c.b(ai);
        this.setTitle(am.a(ao.e("Private Conversation with %1"), new String[] { this.b.f() }));
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final cA ca = new cA();
        this.setLayout(layout);
        ca.setLayout(layout);
        try {
            this.b = new TextArea("", 2, 10, 3);
        }
        catch (Throwable t) {
            this.b = new TextArea(2, 10);
        }
        (this.a = a).setFont(g.a.b());
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final aR ar = new aR(a);
        layout.setConstraints(ar, gridBagConstraints);
        ca.add(ar);
        this.b.setFont(bL.f);
        final aR ar2 = new aR(this.b);
        final Container container;
        (container = new Panel()).setLayout(layout);
        final Panel panel = new Panel(new GridBagLayout());
        if (t.P || t.N || t.Q || t.R) {
            final String e = g.a.e();
            this.n = this.a(e, "emoticons", "emoticonsIcon.gif");
            this.o = this.a(e, "shortcuts", "shortcutsIcon.gif");
            this.p = this.a(e, "bold", "boldIcon.gif");
            this.q = this.a(e, "italic", "italicIcon.gif");
            this.r = this.a(e, "colors", "colorsIcon.gif", 13, 50);
            (this.c = new aX(g, this.b, true)).setSize(1, 1);
            this.c.a(g.a.o);
            this.c.aB = 13;
            this.c.a(this.g.k, true);
            final GridBagConstraints gridBagConstraints2;
            (gridBagConstraints2 = new GridBagConstraints()).fill = 0;
            gridBagConstraints2.anchor = 17;
            gridBagConstraints2.insets = new Insets(0, 1, 0, 1);
            if (t.P) {
                gridBagConstraints2.gridwidth = 1;
                gridBagConstraints2.gridheight = 2;
                gridBagConstraints2.weighty = 1.0;
                panel.add(this.r, gridBagConstraints2);
                panel.add(this.c, gridBagConstraints2);
            }
            gridBagConstraints2.weighty = 0.0;
            gridBagConstraints2.gridheight = 1;
            if (t.N) {
                gridBagConstraints2.gridx = 1;
                gridBagConstraints2.gridy = 0;
                panel.add(this.p, gridBagConstraints2);
                gridBagConstraints2.gridx = 1;
                gridBagConstraints2.gridy = 1;
                panel.add(this.q, gridBagConstraints2);
            }
            if (t.Q) {
                gridBagConstraints2.gridx = 2;
                gridBagConstraints2.gridy = 0;
                panel.add(this.n, gridBagConstraints2);
            }
            if (t.R) {
                gridBagConstraints2.gridx = 2;
                gridBagConstraints2.gridy = 1;
                panel.add(this.o, gridBagConstraints2);
            }
        }
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(ar2, gridBagConstraints);
        container.add(ar2);
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        container.add(panel, gridBagConstraints);
        gridBagConstraints.insets = new Insets((t.N || t.Q || t.R) ? 0 : 2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        layout.setConstraints(container, gridBagConstraints);
        ca.add(container);
        ca.setBackground(g.a.g);
        ca.setForeground(g.a.f);
        boolean b = (!doook.f.d || doook.f.h != 1) && g.d(20) && this.b.d(21) && this.b.h() != g.h();
        if (!this.c()) {
            b = false;
        }
        final String e2 = g.a.e();
        final String s = b ? "_3" : "_2";
        this.f = this.a(e2, "profile_request" + s, ao.e("Profile"), am.a(ao.e("Click here to get the profile of %1. This will return information entered by the user, such as his or her real name."), new String[] { this.b.f() }));
        this.e = this.a(e2, "send_small" + s, ao.e("Send"), ao.e("Click here, or press the RETURN or ENTER key, to send your message to all users in the current room."));
        final FontMetrics fontMetrics = this.f.getFontMetrics(bL.a);
        int stringWidth = fontMetrics.stringWidth(ao.e("Send"));
        final int stringWidth2 = fontMetrics.stringWidth(ao.e("Profile"));
        if (stringWidth2 > stringWidth) {
            stringWidth = stringWidth2;
        }
        if (b) {
            this.g = this.a(e2, "file" + s, ao.e("File Transfer"), am.a(ao.e("Click here to send a file to %1."), new String[] { this.b.f() }));
        }
        gridBagConstraints.insets = ((this.f instanceof cz || this.e instanceof cz || this.g instanceof cz) ? new Insets(0, 0, 0, 0) : new Insets(2, 3, 2, 3));
        gridBagConstraints.weightx = ((this.e instanceof cz) ? 0.0 : 0.2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.gridwidth = (b ? -1 : 0);
        if (this.e instanceof aS) {
            final aQ aq = new aQ(this.e);
            layout.setConstraints(aq, gridBagConstraints);
            ca.add(aq);
        }
        else {
            layout.setConstraints(this.e, gridBagConstraints);
            ca.add(this.e);
        }
        gridBagConstraints.gridheight = (b ? -1 : 0);
        gridBagConstraints.weightx = ((this.f instanceof cz) ? 0.0 : 0.2);
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.f, gridBagConstraints);
        if (this.f instanceof aS) {
            this.f.resize(stringWidth2, 20);
        }
        ca.add(this.f);
        if (b) {
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridheight = 0;
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.g, gridBagConstraints);
            ca.add(this.g);
        }
        if (this.e instanceof aS) {
            this.e.resize(stringWidth, 20);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(ca, gridBagConstraints);
        this.add(ca);
    }
}
