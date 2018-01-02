import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.util.StringTokenizer;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class dp extends db implements ActionListener
{
    private do p;
    public int d;
    public String[] a;
    public int[] n;
    public int[] v;
    private dl[] i;
    private dl l;
    private String b;
    private boolean c;
    
    public dp(final y y) {
        super(y, "RoomInfo");
        this.a = new String[30];
        this.v = new int[30];
        this.i = new dl[30];
        this.c = false;
        this.c = "true".equals(y.p().getParameter("showallrooms"));
        this.b = " " + y.p().getParameter("rooms") + " ";
        this.b = this.b.toLowerCase();
        this.p = y.p();
        this.n = new int[30];
        for (int i = 0; i < 30; ++i) {
            this.n[i] = 200;
        }
    }
    
    public final void p(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        this.d = 0;
        while (stringTokenizer.hasMoreTokens()) {
            this.a[this.d] = stringTokenizer.nextToken();
            if (!stringTokenizer.hasMoreTokens()) {
                break;
            }
            this.n[this.d] = du.p(stringTokenizer.nextToken(), 10);
            if (this.n[this.d] < 0) {
                this.n[this.d] = 200;
            }
            if (this.c && this.b.indexOf(" " + this.a[this.d].toLowerCase() + " ") == -1) {
                continue;
            }
            ++this.d;
        }
    }
    
    public final void d(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        int n = 0;
        this.d = 0;
        final boolean showing = this.isShowing();
        while (stringTokenizer.hasMoreTokens()) {
            this.a[this.d] = stringTokenizer.nextToken();
            if (!stringTokenizer.hasMoreTokens()) {
                break;
            }
            this.v[this.d] = du.p(stringTokenizer.nextToken(), 10);
            if (this.v[this.d] < 0) {
                break;
            }
            n += this.v[this.d];
            if (this.n[this.d] <= 0) {
                this.n[this.d] = 200;
            }
            if (this.c && this.b.indexOf(" " + this.a[this.d].toLowerCase() + " ") == -1) {
                continue;
            }
            ++this.d;
        }
        if (showing) {
            this.setVisible(false);
        }
        this.removeAll();
        this.setLayout(new GridLayout(this.d + 2, 1));
        for (int i = 0; i < this.d; ++i) {
            if (this.v[i] >= this.n[i] - 5) {
                this.add(this.i[i] = new dl(String.valueOf(this.a[i]) + " (full)"));
            }
            else {
                this.add(this.i[i] = new dl(String.valueOf(this.a[i]) + " (" + this.v[i] + ")"));
            }
            this.i[i].p(this);
        }
        this.add(this.l = new dl("Update!"));
        this.l.p(this);
        this.add(new Label("Total: " + n + " users"));
        this.pack();
        this.b();
        this.l();
        if (showing) {
            this.show();
        }
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final i p = super.p.p();
        final String actionCommand = actionEvent.getActionCommand();
        final Object source = actionEvent.getSource();
        if (actionCommand == "Update!") {
            this.setVisible(false);
            this.p.l("<12>***Updating room info...");
            p.d("ROOMUPDATE");
            return;
        }
        for (int i = 0; i < this.d; ++i) {
            if (source == this.i[i]) {
                if (!super.p.d()) {
                    this.p.l("<4>*** Please unsit first before going to a different room");
                }
                else if (this.v[i] >= this.n[i] - 5 && !super.p.p(p.d())) {
                    this.p.l("<6>*** Selected room is full. Please use a different room or ask a <12>bluenick <6> in that room to invite you in");
                    this.setVisible(false);
                }
                else if (this.v[i] >= 120 * this.n[i] / 100 - 5 && !super.p.p(p.d())) {
                    this.p.l("<4>*** Selected room is full. Only <12>bluenicks <4>can go there");
                    this.setVisible(false);
                }
                else {
                    this.p.l("<12>*** Entering " + this.a[i] + "...");
                    p.d("ENTER " + this.a[i]);
                    this.setVisible(false);
                }
            }
        }
    }
}
