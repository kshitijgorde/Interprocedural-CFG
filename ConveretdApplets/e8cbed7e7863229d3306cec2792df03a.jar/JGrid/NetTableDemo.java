// 
// Decompiled by Procyon v0.5.30
// 

package JGrid;

import java.net.URL;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.StringTokenizer;
import java.awt.Label;
import java.applet.Applet;

public class NetTableDemo extends Applet implements a, Runnable
{
    Label a;
    e do;
    Object[][] if;
    Thread for;
    int int;
    
    public void init() {
        final int intValue = new Integer(this.getParameter("columns"));
        final int intValue2 = new Integer(this.getParameter("rows"));
        final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter("headers"), ",");
        final String[] array = new String[intValue];
        for (int i = 0; i < intValue; ++i) {
            array[i] = stringTokenizer.nextToken();
        }
        (this.do = new e(array, true, 2)).a(this);
        int n = 0;
        for (String s = this.getParameter("feature" + n); s != null; s = this.getParameter("feature" + n)) {
            this.do.a(s.substring(0, s.indexOf(124)), s.substring(s.indexOf(124) + 1));
            ++n;
        }
        final StringTokenizer stringTokenizer2 = new StringTokenizer(this.getParameter("colWidths"), ",");
        final int[] array2 = new int[intValue];
        for (int j = 0; j < intValue; ++j) {
            array2[j] = new Integer(stringTokenizer2.nextToken());
        }
        this.do.a(array2);
        this.if = new Object[intValue2][intValue];
        for (int k = 1; k <= intValue2; ++k) {
            final StringTokenizer stringTokenizer3 = new StringTokenizer(this.getParameter("data" + k), "|");
            for (int l = 0; l < intValue; ++l) {
                this.if[k - 1][l] = stringTokenizer3.nextToken();
            }
        }
        this.do.a(this.if, true);
        this.setLayout(new BorderLayout());
        this.add("Center", this.do);
        this.add("South", this.a);
        this.show();
        (this.for = new Thread(this)).setPriority(1);
        this.for.start();
        c.void[0] = this.getImage(this.getCodeBase(), "up.gif");
        c.void[1] = this.getImage(this.getCodeBase(), "dn.gif");
        JNewsCell.case = this.getImage(this.getCodeBase(), "new.gif");
    }
    
    public void destroy() {
        this.for.stop();
    }
    
    public void run() {
        while (true) {
            try {
                while (true) {
                    Thread.sleep(300L);
                    ++this.int;
                    if (this.int % 2 == 0) {
                        for (int i = 1; i < 9; ++i) {
                            if (i != 8) {
                                for (int j = this.if.length - 1; j >= 0; --j) {
                                    final int n = (Math.random() > 0.5) ? -1 : 1;
                                    double n2;
                                    if (i == 7) {
                                        n2 = Math.random() * n;
                                    }
                                    else {
                                        n2 = new Double(this.if[j][i].toString()) + Math.random() * n;
                                    }
                                    this.if[j][i] = new Double(n2).toString();
                                }
                            }
                        }
                    }
                    ++d.int;
                    this.do.a(this.if, false);
                    this.do.repaint();
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
    }
    
    public void clickedCell(final int n, final int n2, final e e) {
        final d do1 = e.do(n, n2);
        this.a.setText("clicked on cell (" + n + "," + n2 + ") http=" + do1.a);
        if (do1.a != null) {
            String s = "chart";
            if (n2 == 10) {
                s = "news";
            }
            try {
                this.getAppletContext().showDocument(new URL(do1.a), s);
            }
            catch (Exception ex) {}
        }
    }
    
    public void clickedColumn(final int n, final e e) {
        this.a.setText("clicked on column " + n);
    }
    
    public NetTableDemo() {
        this.a = new Label();
    }
}
