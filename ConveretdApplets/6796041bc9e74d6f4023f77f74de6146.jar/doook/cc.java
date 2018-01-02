// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Label;
import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.TextField;
import java.awt.Checkbox;

public class cc extends o
{
    private Checkbox A;
    private Checkbox g;
    private Checkbox h;
    private Checkbox i;
    private TextField e;
    private TextField g;
    private TextField h;
    private TextField B;
    private TextField C;
    private TextField D;
    private TextField K;
    private u k;
    
    public void c() {
        final String trim = this.e.getText().trim();
        final String trim2 = this.g.getText().trim();
        final String trim3 = this.h.getText().trim();
        final String trim4 = this.B.getText().trim();
        final String trim5 = this.C.getText().trim();
        final String trim6 = this.D.getText().trim();
        final String trim7 = this.K.getText().trim();
        try {
            new URL(trim);
        }
        catch (MalformedURLException ex) {
            throw new aq(ao.e("Please enter a valid URL, including the protocol (e.g., http://, ftp://, mailto:)."));
        }
        int int1;
        try {
            int1 = Integer.parseInt(trim2);
        }
        catch (NumberFormatException ex2) {
            this.g.requestFocus();
            this.g.selectAll();
            throw new aq(ao.e("The timeout you entered is not valid.  Please re-enter this information."));
        }
        int int2;
        try {
            int2 = Integer.parseInt(trim3);
        }
        catch (NumberFormatException ex3) {
            this.h.requestFocus();
            this.h.selectAll();
            throw new aq(ao.e("The banner rotation time you entered is not valid.  Please re-enter this information."));
        }
        int int3;
        try {
            int3 = Integer.parseInt(trim4);
        }
        catch (NumberFormatException ex4) {
            this.B.requestFocus();
            this.B.selectAll();
            throw new aq(ao.e("The max bots count you entered is not valid.  Please re-enter this information."));
        }
        int int4;
        try {
            int4 = Integer.parseInt(trim5);
        }
        catch (NumberFormatException ex5) {
            this.C.requestFocus();
            this.C.selectAll();
            throw new aq(ao.e("The flooding message count you entered is not valid.  Please re-enter this information."));
        }
        int int5;
        try {
            int5 = Integer.parseInt(trim6);
        }
        catch (NumberFormatException ex6) {
            this.D.requestFocus();
            this.D.selectAll();
            throw new aq(ao.e("The number of flooding messages you entered is not valid.  Please re-enter this information."));
        }
        int int6;
        try {
            int6 = Integer.parseInt(trim7);
        }
        catch (NumberFormatException ex7) {
            this.K.requestFocus();
            this.K.selectAll();
            throw new aq(ao.e("The time for the flooding messages you entered is not valid.  Please re-enter this information."));
        }
        final long a = cD.a(cD.a(cD.a(cD.a(this.k.c, 60, this.A.getState()), 58, this.g.getState()), 54, this.h.getState()), 55, this.i.getState());
        final cD cd = new cD(67843, 1);
        cd.a(0, 0, trim);
        cd.a(0, 0, int1);
        cd.a(0, 1, int2);
        cd.a(0, 2, int3);
        cd.a(0, 3, int4);
        cd.a(0, 4, int5);
        cd.a(0, 5, int6);
        cd.a(0, a);
        cd.j = -1;
        cd.o = -1;
        this.k.o(cd);
    }
    
    public void d() {
        if (this.k.x != null) {
            this.e.setText(this.k.x);
        }
        this.A.setState(cD.a(this.k.c, 60));
        this.g.setState(cD.a(this.k.c, 58));
        this.h.setState(cD.a(this.k.c, 54));
        this.i.setState(cD.a(this.k.c, 55));
        this.g.setText(new Integer(this.k.G).toString());
        this.h.setText(new Integer(this.k.H).toString());
        this.B.setText(new Integer(this.k.I).toString());
        this.C.setText(new Integer(this.k.J).toString());
        this.D.setText(new Integer(this.k.K).toString());
        this.K.setText(new Integer(this.k.L).toString());
    }
    
    public String a(final Object o) {
        if (o == this.A) {
            return ao.e("Check this box if you wish to make a transcript of all chat messages.  Logging messages may slow down the server.");
        }
        if (o == this.g) {
            return ao.e("Check this box if you wish to enable Emoticons.");
        }
        if (o == this.e) {
            return ao.e("Enter a URL to link to when a user clicks on your chat site logo.");
        }
        if (o == this.g) {
            return ao.e("Enter a user inactivity timeout in minutes. To disable the timeout, enter 0.");
        }
        return null;
    }
    
    public cc(final u k) {
        super(ao.e("Options"), k);
        this.A = new Checkbox(ao.e("Keep Chat Transcript"));
        this.g = new Checkbox(ao.e("Enable Emoticons"));
        this.h = new Checkbox(ao.e("Ban IP of flooding users"));
        this.i = new Checkbox(ao.e("Ban Host of flooding users"));
        this.e = new TextField(25);
        this.g = new TextField(5);
        this.h = new TextField(5);
        this.B = new TextField(5);
        this.C = new TextField(5);
        this.D = new TextField(5);
        this.K = new TextField(5);
        this.k = k;
        this.a(ao.e("Logo Link URL:"), this.e);
        this.a(ao.e("Timeout in minutes: (0 to disable)"), this.g);
        this.a(ao.e("Banner rotation in seconds"), this.h);
        this.a(ao.e("Maximum bots allowed"), this.B);
        this.a(ao.e("Flooding message count: (0 to disable)"), this.C);
        this.a(ao.e("Flooding messages: (0 to disable)"), new Component[] { this.D, new Label("per"), this.K, new Label("seconds") });
        this.a("", this.h);
        this.a("", this.i);
        this.a("", this.g);
    }
}
