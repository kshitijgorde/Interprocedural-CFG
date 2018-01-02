// 
// Decompiled by Procyon v0.5.30
// 

package equis.metastock;

import java.util.StringTokenizer;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.net.URL;
import java.awt.Event;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Label;
import java.awt.List;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Panel;

public class k extends Panel
{
    private TextField a;
    private Button b;
    private List c;
    private Label d;
    private String e;
    private final String f;
    static MS4Java g;
    static boolean h;
    static boolean i;
    
    public k(final MS4Java g, final int n, final int n2, final boolean i, final Color background, final String e) {
        this.f = "             " + MS4Java.bf[56];
        this.setBackground(background);
        k.g = g;
        k.h = (k.g != null);
        k.i = i;
        if (e.length() == 0) {
            this.e = "beta.moneynet.com";
        }
        else {
            this.e = e;
        }
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(6, 0, 6, 0);
        (this.d = new Label(this.f)).setFont(new Font("Dialog", 0, 12));
        layout.setConstraints(this.d, gridBagConstraints);
        this.add(this.d);
        layout.setConstraints(this.a = new TextField(20), gridBagConstraints);
        this.add(this.a);
        (this.b = new Button(MS4Java.bf[57])).setFont(new Font("Dialog", 0, 11));
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        if (n2 > 20 || n2 < 1) {
            this.c = new List();
        }
        else {
            this.c = new List(n2);
        }
        this.c.setBackground(Color.white);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.c, gridBagConstraints);
        this.add(this.c);
        this.a();
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 701:
            case 1001: {
                if (event.target == this.c) {
                    this.b();
                    return true;
                }
                break;
            }
            case 401: {
                if (event.target == this.c) {
                    if (event.key == 10) {
                        this.b();
                        return true;
                    }
                    break;
                }
                else {
                    if (event.target == this.a && event.key == 10) {
                        this.b(this.a.getText());
                        return true;
                    }
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    private void a() {
        this.a.show();
        this.b.show();
        this.d.setText(this.f);
        this.a.requestFocus();
        if (k.h) {
            MS4Java.a(MS4Java.k).setCursor(0);
        }
    }
    
    private void a(final String text) {
        if (k.h) {
            MS4Java.a(MS4Java.k).setCursor(3);
        }
        this.a.hide();
        this.b.hide();
        this.c.clear();
        this.d.setText(text);
    }
    
    private void b(String replace) {
        this.a("FindSymbol( " + replace + " )", false);
        int n = 0;
        this.a(String.valueOf(MS4Java.bf[58]) + " \"" + replace + "...\"");
        InputStream openStream;
        try {
            replace = replace.replace(' ', '+');
            final URL url = new URL(String.valueOf(this.e) + "?NAME=" + replace + "&COUNT=75&SKIP=0&ANYWHERE=N");
            this.a(url.toString(), false);
            openStream = url.openStream();
        }
        catch (Exception ex) {
            final String string = "Search exception \"" + ex.getMessage() + "\"" + "\n" + "Please report to support@equis.com";
            if (k.i) {
                ex.printStackTrace();
            }
            MS4Java.e.a(string);
            this.a();
            return;
        }
        if (MS4Java.k == null) {
            this.a("Null applet", false);
        }
        final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(openStream, 4000));
        try {
            this.a(MS4Java.bf[59]);
            while (true) {
                final String line = dataInputStream.readLine();
                if (k.i) {
                    this.a("Host -> " + line, false);
                }
                if (line == null) {
                    break;
                }
                if (n == 0) {
                    if (line.indexOf("Symbol:") == 0) {
                        line.substring(7, line.length());
                    }
                    else if (line.indexOf("Meta:") != 0) {
                        this.a("Internal error:\"Symbol:\" or \"Meta:\" prefixes missing in iState 0", true);
                        continue;
                    }
                    ++n;
                }
                else if (n == 1) {
                    if (line.length() > 1) {
                        continue;
                    }
                    ++n;
                    this.c.clear();
                }
                else {
                    if (n != 2 || line.indexOf("Symbol:") != 0) {
                        continue;
                    }
                    final StringTokenizer stringTokenizer = new StringTokenizer(line.substring(7, line.length()), ",");
                    final String nextToken = stringTokenizer.nextToken();
                    final String nextToken2 = stringTokenizer.nextToken();
                    stringTokenizer.nextToken();
                    this.a(nextToken2, nextToken);
                }
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            MS4Java.e.a("Search Error:GetData exception: " + ex2.toString());
        }
        try {
            openStream.close();
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
            MS4Java.e.a("Search Error:GetData exception: " + ex3.toString());
        }
        this.a();
    }
    
    public void a(final String s, final String s2) {
        this.c.addItem(String.valueOf(s) + " (" + s2 + ")");
    }
    
    private void b() {
        this.a("JFindDlg::LoadSelectedSymbol()", false);
        if (k.h) {
            final String selectedItem = this.c.getSelectedItem();
            final String substring = selectedItem.substring(selectedItem.lastIndexOf(" (") + 2, selectedItem.lastIndexOf(")"));
            try {
                null.LoadData(substring);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                MS4Java.e.a("JFindDlg::LoadSelectedSymbol() Error:GetData execption: " + ex.toString());
            }
        }
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.b) {
            this.b(this.a.getText());
        }
        return true;
    }
    
    private void a(final String s, final boolean b) {
        if (k.i || b) {
            MS4Java.a("JFind -> " + s);
        }
    }
    
    static {
        k.g = null;
    }
}
