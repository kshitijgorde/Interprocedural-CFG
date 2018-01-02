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

public class ai extends ac implements aO
{
    protected TextArea b;
    protected Canvas a;
    protected Canvas b;
    protected Canvas c;
    protected as a;
    protected bt b;
    protected int o;
    protected F b;
    private Canvas d;
    private Canvas e;
    private Canvas f;
    private Canvas g;
    private Canvas h;
    private bp c;
    
    private final Canvas a(final String s, final String s2, final String s3, final String s4) {
        aO a = null;
        if (this.a.b.f() && s != null) {
            final Image a2 = this.a.a(s + s2 + "_button_up.gif", true);
            final Image a3 = this.a.a(s + s2 + "_button_dn.gif", true);
            if (a2 != null && a3 != null) {
                a = aB.a(a2, a3, null);
                ((aB)a).a(s4, null);
            }
        }
        if (a == null) {
            if (s3 == null) {
                a = new ax(70, 20);
            }
            else {
                a = new ax(s3, 70, 20);
            }
            ((ax)a).a(s4, null);
        }
        return (ax)a;
    }
    
    private final Canvas a(final String s, final String s2, final String s3) {
        return this.a(s, s2, s3, 25, 25);
    }
    
    private final Canvas a(final String s, final String s2, final String s3, final int n, final int n2) {
        try {
            aO a;
            if (!this.a.b.f() || s == null) {
                a = new ax(n, n2);
                ((ax)a).a(this.a.a(s3, false, 20));
            }
            else {
                final Image a2 = this.a.a(s + s2 + "_button_up.gif", true);
                final Image a3 = this.a.a(s + s2 + "_button_dn.gif", true);
                final Image a4 = this.a.a(s + s2 + "_button_disabled.gif", true);
                if (a2 == null || a3 == null || a4 == null) {
                    a = new ax(25, 25);
                    ((ax)a).a(this.a.a(s3, false, 20));
                }
                else {
                    a = aB.a(a2, a3, a4);
                }
            }
            return (ax)a;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new Canvas();
        }
    }
    
    public String a(final Object o) {
        if (o == this.b) {
            return ar.b("Type your message here, then hit ENTER or click \"Send\" to send it to all users in the current room.");
        }
        if (o == this.d) {
            return ar.b("Click here to insert emoticons into your message.");
        }
        if (o == this.e) {
            return ar.b("Click here to insert a shortcut word into your message.");
        }
        if (o == this.f) {
            return ar.b("Select text then click here to make it Bold.");
        }
        if (o == this.g) {
            return ar.b("Select text then click here to make it Italic.");
        }
        return null;
    }
    
    public void b(final bv bv) {
        this.b.b(bv);
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
                        this.m();
                        return true;
                    }
                    if (event.key == 5) {
                        this.l();
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
                if (event.key != 10 && event.key != bs.c) {
                    break;
                }
                if (this.a instanceof ax) {
                    ((ax)this.a).l();
                }
                else {
                    ((aB)this.a).f();
                }
                return true;
            }
            case 1001: {
                if (event.target == this.d) {
                    this.l();
                    return true;
                }
                if (event.target == this.e) {
                    this.m();
                    return true;
                }
                if (event.target == this.f) {
                    a("b", this.b);
                    return true;
                }
                if (event.target == this.g) {
                    a("i", this.b);
                    return true;
                }
                if (event.target == this.h) {
                    this.c.c();
                    return true;
                }
                if (event.target == this.a) {
                    final String trim = this.b.getText().trim();
                    this.b.setText("");
                    if (trim.length() > 0) {
                        this.a.a(trim, this.o, -1, this.c.ax, this.b.getFont().isBold(), this.b.getFont().isItalic());
                        this.b.l();
                    }
                    if (bs.h) {
                        this.b.requestFocus();
                    }
                    return true;
                }
                if (event.target == this.b) {
                    final aJ aj = new aJ(67074, 1);
                    aj.a(0, 0, this.a.e());
                    aj.j = this.o;
                    this.a.q(aj);
                    return true;
                }
                if (event.target == this.c) {
                    break;
                }
                if (event.arg instanceof URL) {
                    this.a.a((URL)event.arg, "_blank");
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void f() {
        this.b.k();
    }
    
    public void dispose() {
        super.dispose();
        this.a.c.c(this.o);
    }
    
    public boolean c() {
        return false;
    }
    
    private void l() {
        if (as.S) {
            final k k = new k(null, this.a, this.b);
            k.f();
            k.setVisible(true);
        }
    }
    
    private void m() {
        if (as.T) {
            final w w = new w(null, this.a, this.b);
            w.f();
            w.setVisible(true);
        }
    }
    
    public static void a(final String s, final TextArea textArea) {
        if (as.O) {
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
    
    public ai(final Frame frame, final as a, final int o, final bt b) {
        this.setBackground(a.b.a);
        this.a = a;
        this.o = o;
        this.b = (F)a.e.b(o);
        this.setTitle(H.a(ar.b("Private Conversation with %1"), new String[] { this.b.d() }));
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final aC ac = new aC();
        this.setLayout(layout);
        ac.setLayout(layout);
        try {
            this.b = new TextArea("", 2, 10, 3);
        }
        catch (Throwable t) {
            this.b = new TextArea(2, 10);
        }
        (this.b = b).setFont(a.b.b());
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final aw aw = new aw(b);
        layout.setConstraints(aw, gridBagConstraints);
        ac.add(aw);
        this.b.setFont(ay.e);
        final aw aw2 = new aw(this.b);
        final Container container;
        (container = new Panel()).setLayout(layout);
        final Panel panel = new Panel(new GridBagLayout());
        if (as.Q || as.O || as.S || as.T) {
            final String e = a.b.e();
            this.d = this.a(e, "emoticons", "emoticonsIcon.gif");
            this.e = this.a(e, "shortcuts", "shortcutsIcon.gif");
            this.f = this.a(e, "bold", "boldIcon.gif");
            this.g = this.a(e, "italic", "italicIcon.gif");
            this.h = this.a(e, "colors", "colorsIcon.gif", 13, 50);
            (this.c = new bp(this.a, this.b, true)).setSize(1, 1);
            this.c.b(this.a.b.r);
            this.c.a(this.a.s, true);
            final GridBagConstraints gridBagConstraints2;
            (gridBagConstraints2 = new GridBagConstraints()).fill = 0;
            gridBagConstraints2.anchor = 17;
            gridBagConstraints2.insets = new Insets(0, 1, 0, 1);
            if (as.Q) {
                gridBagConstraints2.gridwidth = 1;
                gridBagConstraints2.gridheight = 2;
                gridBagConstraints2.weighty = 1.0;
                panel.add(this.h, gridBagConstraints2);
                panel.add(this.c, gridBagConstraints2);
            }
            gridBagConstraints2.weighty = 0.0;
            gridBagConstraints2.gridheight = 1;
            if (as.O) {
                gridBagConstraints2.gridx = 1;
                gridBagConstraints2.gridy = 0;
                panel.add(this.f, gridBagConstraints2);
                gridBagConstraints2.gridx = 1;
                gridBagConstraints2.gridy = 1;
                panel.add(this.g, gridBagConstraints2);
            }
            if (as.S) {
                gridBagConstraints2.gridx = 2;
                gridBagConstraints2.gridy = 0;
                panel.add(this.d, gridBagConstraints2);
            }
            if (as.T) {
                gridBagConstraints2.gridx = 2;
                gridBagConstraints2.gridy = 1;
                panel.add(this.e, gridBagConstraints2);
            }
        }
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(aw2, gridBagConstraints);
        container.add(aw2);
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        container.add(panel, gridBagConstraints);
        gridBagConstraints.insets = new Insets((as.O || as.S || as.T) ? 0 : 2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        layout.setConstraints(container, gridBagConstraints);
        ac.add(container);
        ac.setBackground(a.b.g);
        ac.setForeground(a.b.f);
        boolean b2 = (!bs.d || bs.t != 1) && a.a(20) && this.b.a(21) && this.b.e() != a.e();
        if (!this.c()) {
            b2 = false;
        }
        final String e2 = a.b.e();
        final String s = b2 ? "_3" : "_2";
        this.b = this.a(e2, "profile_request" + s, ar.b("Profile"), H.a(ar.b("Click here to get the profile of %1. This will return information entered by the user, such as his or her real name."), new String[] { this.b.d() }));
        this.a = this.a(e2, "send_small" + s, ar.b("Send"), ar.b("Click here, or press the RETURN or ENTER key, to send your message to all users in the current room."));
        final FontMetrics fontMetrics = this.b.getFontMetrics(ay.a);
        int stringWidth = fontMetrics.stringWidth(ar.b("Send"));
        final int stringWidth2 = fontMetrics.stringWidth(ar.b("Profile"));
        if (stringWidth2 > stringWidth) {
            stringWidth = stringWidth2;
        }
        if (b2) {
            this.c = this.a(e2, "file" + s, ar.b("File Transfer"), H.a(ar.b("Click here to send a file to %1."), new String[] { this.b.d() }));
        }
        gridBagConstraints.insets = ((this.b instanceof aB || this.a instanceof aB || this.c instanceof aB) ? new Insets(0, 0, 0, 0) : new Insets(2, 3, 2, 3));
        gridBagConstraints.weightx = ((this.a instanceof aB) ? 0.0 : 0.2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.gridwidth = (b2 ? -1 : 0);
        if (this.a instanceof ax) {
            final au au = new au(this.a);
            layout.setConstraints(au, gridBagConstraints);
            ac.add(au);
        }
        else {
            layout.setConstraints(this.a, gridBagConstraints);
            ac.add(this.a);
        }
        gridBagConstraints.gridheight = (b2 ? -1 : 0);
        gridBagConstraints.weightx = ((this.b instanceof aB) ? 0.0 : 0.2);
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.b, gridBagConstraints);
        if (this.b instanceof ax) {
            this.b.resize(stringWidth2, 20);
        }
        ac.add(this.b);
        if (b2) {
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridheight = 0;
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.c, gridBagConstraints);
            ac.add(this.c);
        }
        if (this.a instanceof ax) {
            this.a.resize(stringWidth, 20);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(ac, gridBagConstraints);
        this.add(ac);
    }
}
