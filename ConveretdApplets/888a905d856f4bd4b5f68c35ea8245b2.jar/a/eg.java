// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Label;
import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.TextField;
import java.awt.Checkbox;

public final class eg extends cV
{
    private Checkbox q;
    private Checkbox w;
    private Checkbox e;
    private Checkbox r;
    private TextField q;
    private TextField w;
    private TextField e;
    private TextField r;
    private TextField t;
    private TextField y;
    private TextField u;
    private TextField i;
    private TextField o;
    private TextField p;
    private dH q;
    
    public final void q() {
        String trim = this.q.getText().trim();
        final String trim2 = this.w.getText().trim();
        final String trim3 = this.e.getText().trim();
        final String trim4 = this.r.getText().trim();
        final String trim5 = this.t.getText().trim();
        final String trim6 = this.y.getText().trim();
        final String trim7 = this.u.getText().trim();
        final String trim8 = this.i.getText().trim();
        final String text = this.o.getText();
        final String text2 = this.p.getText();
        try {
            if (trim.equalsIgnoreCase("http://")) {
                trim = "";
            }
            new URL(trim);
        }
        catch (MalformedURLException ex) {
            throw new Q(be.w("Please enter a valid URL, including the protocol (e.g., http://, ftp://, mailto:)."));
        }
        try {
            if (trim2.length() != 0) {
                new URL(trim2);
            }
        }
        catch (MalformedURLException ex2) {
            throw new Q(be.w("Please enter a valid URL for the Buddy List CGI Script, including the protocol (e.g., http://, ftp://, mailto:)."));
        }
        int int1;
        try {
            int1 = Integer.parseInt(trim3);
        }
        catch (NumberFormatException ex3) {
            this.e.requestFocus();
            this.e.selectAll();
            throw new Q(be.w("The timeout you entered is not valid.  Please re-enter this information."));
        }
        int int2;
        try {
            int2 = Integer.parseInt(trim4);
        }
        catch (NumberFormatException ex4) {
            this.r.requestFocus();
            this.r.selectAll();
            throw new Q(be.w("The banner rotation time you entered is not valid.  Please re-enter this information."));
        }
        int int3;
        try {
            int3 = Integer.parseInt(trim5);
        }
        catch (NumberFormatException ex5) {
            this.t.requestFocus();
            this.t.selectAll();
            throw new Q(be.w("The max bots count you entered is not valid.  Please re-enter this information."));
        }
        int int4;
        try {
            int4 = Integer.parseInt(trim6);
        }
        catch (NumberFormatException ex6) {
            this.y.requestFocus();
            this.y.selectAll();
            throw new Q(be.w("The number of flooding messages you entered is not valid.  Please re-enter this information."));
        }
        int int5;
        try {
            int5 = Integer.parseInt(trim7);
        }
        catch (NumberFormatException ex7) {
            this.u.requestFocus();
            this.u.selectAll();
            throw new Q(be.w("The time for the flooding messages you entered is not valid.  Please re-enter this information."));
        }
        int int6;
        try {
            int6 = Integer.parseInt(text);
        }
        catch (NumberFormatException ex8) {
            this.o.requestFocus();
            this.o.selectAll();
            throw new Q(be.w("The number of flooding messages you entered is not valid.  Please re-enter this information."));
        }
        int int7;
        try {
            int7 = Integer.parseInt(text2);
        }
        catch (NumberFormatException ex9) {
            this.p.requestFocus();
            this.p.selectAll();
            throw new Q(be.w("The time for the flooding messages you entered is not valid.  Please re-enter this information."));
        }
        final long q = dI.q(dI.q(dI.q(dI.q(((cT)this.q).p, 60, this.q.getState()), 58, this.w.getState()), 54, this.e.getState()), 55, this.r.getState());
        final dI di;
        (di = new dI(67843, 1)).q(0, 0, trim);
        di.q(0, 1, trim2);
        di.q(0, 2, trim8);
        di.q(0, 0, int1);
        di.q(0, 1, int2);
        di.q(0, 2, int3);
        di.q(0, 4, int4);
        di.q(0, 5, int5);
        di.q(0, 6, int6);
        di.q(0, 7, int7);
        di.q(0, q);
        di.w = -1;
        di.q = -1;
        this.q.o(di);
    }
    
    public final void w() {
        if (((cT)this.q).p != null) {
            this.q.setText(((cT)this.q).p);
        }
        else {
            this.q.setText("http://");
        }
        if (((cT)this.q).s != null) {
            this.w.setText(((cT)this.q).s);
        }
        this.q.setState(dI.q(((cT)this.q).p, 60));
        this.w.setState(dI.q(((cT)this.q).p, 58));
        this.e.setState(dI.q(((cT)this.q).p, 54));
        this.r.setState(dI.q(((cT)this.q).p, 55));
        this.e.setText(new Integer(((cT)this.q).j).toString());
        this.r.setText(new Integer(((cT)this.q).K).toString());
        this.t.setText(new Integer(((cT)this.q).k).toString());
        this.y.setText(new Integer(((cT)this.q).l).toString());
        this.u.setText(new Integer(((cT)this.q).z).toString());
        this.i.setText(dN.q);
        this.o.setText("" + ((cT)this.q).x);
        this.p.setText("" + ((cT)this.q).c);
    }
    
    public final String q(final Object o) {
        if (o == this.q) {
            return be.w("Check this box if you wish to make a transcript of all chat messages.  Logging messages may slow down the server.");
        }
        if (o == this.w) {
            return be.w("Check this box if you wish to enable Emoticons.");
        }
        if (o == this.q) {
            return be.w("Enter a URL to link to when a user clicks on your chat site logo.");
        }
        if (o == this.w) {
            return be.w("Enter the URL of the Buddy List CGI script.  Leave it blank if you are not using this feature.");
        }
        if (o == this.e) {
            return be.w("Enter a user inactivity timeout in minutes. To disable the timeout, enter 0.");
        }
        return null;
    }
    
    public eg(final ap q) {
        super(be.w("Options"));
        this.q = new Checkbox(be.w("Keep Chat Transcript"));
        this.w = new Checkbox(be.w("Enable Emoticons"));
        this.e = new Checkbox(be.w("Ban IP of flooding users"));
        this.r = new Checkbox(be.w("Ban Host of flooding users"));
        this.q = new TextField(25);
        this.w = new TextField(25);
        this.e = new TextField(5);
        this.r = new TextField(5);
        this.t = new TextField(5);
        this.y = new TextField(5);
        this.u = new TextField(5);
        this.o = new TextField(5);
        this.p = new TextField(5);
        this.i = new TextField(25);
        this.q = q;
        if (!bC.e()) {
            this.q(be.w("Right menu text:"), this.i);
        }
        this.q(be.w("Logo Link URL:"), this.q);
        this.q(be.w("Timeout in minutes: (0 to disable)"), this.e);
        this.q(be.w("Banner rotation in seconds"), this.r);
        this.q(be.w("Maximum bots allowed"), this.t);
        this.q(be.w("Flooding messages: (0 to disable)"), new Component[] { this.y, new Label("per"), this.u, new Label("seconds") });
        this.q(be.w("Extra flooding messages: (0 to disable)"), new Component[] { this.o, new Label("per"), this.p, new Label("seconds") });
        this.q("", this.e);
        this.q("", this.r);
        this.q("", this.w);
    }
}
