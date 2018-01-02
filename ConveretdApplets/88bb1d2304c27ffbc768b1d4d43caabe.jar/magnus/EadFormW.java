// 
// Decompiled by Procyon v0.5.30
// 

package magnus;

import java.awt.Event;
import java.awt.Component;
import java.awt.Font;
import java.awt.Label;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.TextField;
import java.applet.Applet;

public class EadFormW extends Applet implements Runnable
{
    TextField long;
    TextField c;
    TextArea null;
    String[] e;
    String b;
    String void;
    double[] d;
    double[] f;
    double do;
    double[] try;
    int case;
    int[] a;
    int[] char;
    Button int;
    Button g;
    Checkbox else;
    Checkbox byte;
    Thread goto;
    boolean new;
    boolean for;
    boolean if;
    
    public EadFormW() {
        this.case = 60;
        this.a = new int[] { 0, 6, 1, 8, 7, 14, 15, 16, 9, 17, 35, 53, 50, 5, 82, 81, 56, 49, 46, 78, 76, 47, 40, 34, 30, 29, 28, 27, 26, 24, 22, 20, 19, 13, 12, 11, 58, 80, 79, 77, 75, 74, 73, 72, 71, 70, 69, 68, 67, 66, 65, 64, 63, 62, 61, 60, 59, 57, 55, 54, 52, 51, 48, 45, 44, 43, 42, 41, 39, 38, 37, 36, 33, 32, 31, 25, 23, 21, 18, 10, 4, 3, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 2 };
        this.char = new int[] { 0, 1, 0, 1, 2, 3, 4, 3, 2, 1, 0, 1, 2, 3, 4, 3, 2, 1, 0, 1, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 4, 3, 2, 1, 0, 1, 2, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 4, 3, 2, 1, 0 };
        this.for = true;
        this.if = true;
    }
    
    public void init() {
        System.out.println("Magnus Chemistry");
        System.out.println("================");
        System.out.println("(c) J M Goodman, 1999-2006");
        System.out.println("Cambridge University");
        System.out.println("All rights reserved");
        System.out.println("");
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.fill = 0;
        this.new = false;
        final Label label = new Label("Molecular Formula Search", 1);
        label.setFont(new Font("TimesRoman", 1, 20));
        layout.setConstraints(label, EadUtil.a(gridBagConstraints, 0, 0, 4, 1));
        this.add(label);
        final Label label2 = new Label("(c) J M Goodman", 1);
        label2.setFont(new Font("TimesRoman", 2, 12));
        layout.setConstraints(label2, EadUtil.a(gridBagConstraints, 4, 0, 1, 1));
        this.add(label2);
        final Label label3 = new Label("Enter HRMS weight: ");
        label3.setFont(new Font("TimesRoman", 0, 16));
        layout.setConstraints(label3, EadUtil.a(gridBagConstraints, 0, 1, 2, 1));
        this.add(label3);
        layout.setConstraints(this.long = new TextField(25), EadUtil.a(gridBagConstraints, 2, 1, 3, 1));
        this.add(this.long);
        layout.setConstraints(this.else = new Checkbox("Chemical check"), EadUtil.a(gridBagConstraints, 0, 2, 2, 1));
        this.else.setState(true);
        this.add(this.else);
        layout.setConstraints(this.byte = new Checkbox("C,H,N,O only"), EadUtil.a(gridBagConstraints, 2, 2, 2, 1));
        this.byte.setState(true);
        this.add(this.byte);
        layout.setConstraints(this.int = new Button("Find Formula"), EadUtil.a(gridBagConstraints, 0, 3, 2, 1));
        this.add(this.int);
        layout.setConstraints(this.g = new Button("Stop"), EadUtil.a(gridBagConstraints, 2, 3, 2, 1));
        this.add(this.g);
        layout.setConstraints(this.c = new TextField(40), EadUtil.a(gridBagConstraints, 0, 4, 5, 1));
        this.add(this.c);
        layout.setConstraints(this.null = new TextArea(20, 50), EadUtil.a(gridBagConstraints, 0, 5, 5, 15));
        this.add(this.null);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            final String s = (String)o;
            if (s == "Find Formula") {
                this.start();
            }
            else if (s == "Stop") {
                this.stop();
            }
        }
        else if (event.target instanceof Checkbox) {
            this.for = this.else.getState();
            this.if = this.byte.getState();
        }
        return true;
    }
    
    public void run() {
        this.a();
    }
    
    public void start() {
        if (!this.new && this.long.getText().length() > 0) {
            this.new = true;
            this.c.setText("Starting calculation");
            (this.goto = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.new) {
            this.goto.stop();
            this.new = false;
            this.c.setText("Best match: " + this.e[0]);
        }
    }
    
    void a() {
        this.c.setText("Investigating CH");
        this.e = new String[this.case];
        this.d = new double[this.case];
        this.f = new double[this.case];
        for (int i = 0; i < this.case; ++i) {
            this.d[i] = Double.POSITIVE_INFINITY;
        }
        this.do = Double.valueOf(this.long.getText()) + 5.48579896E-4;
        final double do1 = this.do;
        this.try = new double[magnus.a.do.length + 1];
        final EadStruct eadStruct = new EadStruct(magnus.a.do.length);
        for (int j = 1; j < magnus.a.do.length; ++j) {
            int n;
            if (j < this.a.length) {
                n = this.a[j];
            }
            else {
                n = j;
            }
            if (magnus.a.do[n].length > 0) {
                this.try[j] = magnus.a.a(n, "MIon");
            }
            else {
                this.try[j] = -1.0;
            }
            eadStruct.for[j] = n;
        }
        eadStruct.f = 2;
        this.b = "Elements investigated:\nC H ";
        this.c.setText("Investigating CH");
        this.a(eadStruct, do1);
        this.b = "Elements investigated:\nC H ";
        int length;
        if (this.if) {
            length = 5;
        }
        else {
            length = magnus.a.do.length;
        }
        for (int k = 3; k < length; ++k) {
            final EadStruct eadStruct2 = eadStruct;
            ++eadStruct2.f;
            int n2;
            if (k < this.a.length) {
                n2 = this.a[k];
            }
            else {
                n2 = k;
            }
            this.c.setText("Investigating: " + magnus.a.if[n2]);
            if (magnus.a.do[n2].length > 0) {
                eadStruct.try[k] = (int)(do1 / this.try[k]);
                if (this.for && n2 < this.char.length && this.char[n2] == 0 && eadStruct.try[k] > 1) {
                    eadStruct.try[k] = 1;
                }
                while (eadStruct.try[k] > 0) {
                    if (k > 3) {
                        this.a(eadStruct, do1 - this.try[k] * eadStruct.try[k], k - 1);
                    }
                    else {
                        this.a(eadStruct, do1 - this.try[k] * eadStruct.try[k]);
                    }
                    final int[] try1 = eadStruct.try;
                    final int n3 = k;
                    --try1[n3];
                }
            }
            this.b = this.b + magnus.a.if[n2] + " ";
            if (k == 18 * (k / 18)) {
                this.b += "\n";
            }
            this.null.setText(this.b + this.void);
        }
        this.c.setText("Best match: " + this.e[0]);
        this.null.setText(this.b + this.void);
        this.new = false;
    }
    
    void a(final EadStruct eadStruct, final double n, final int n2) {
        eadStruct.try[n2] = (int)(n / this.try[n2]);
        while (eadStruct.try[n2] >= 0) {
            if (n2 > 3) {
                this.a(eadStruct, n - this.try[n2] * eadStruct.try[n2], n2 - 1);
            }
            else {
                this.a(eadStruct, n - this.try[n2] * eadStruct.try[n2]);
            }
            final int[] try1 = eadStruct.try;
            --try1[n2];
        }
    }
    
    void a(final EadStruct eadStruct, final double n) {
        eadStruct.try[1] = (int)(1.0E-8 + n / magnus.a.do[6][0]);
        while (eadStruct.try[1] >= 0) {
            eadStruct.try[2] = (int)((n - this.try[1] * eadStruct.try[1]) / this.try[2]);
            this.if(eadStruct);
            final int[] try1 = eadStruct.try;
            final int n2 = 2;
            ++try1[n2];
            this.if(eadStruct);
            final int[] try2 = eadStruct.try;
            final int n3 = 1;
            --try2[n3];
        }
    }
    
    boolean do(final EadStruct eadStruct) {
        int n = 0;
        int n2 = 0;
        for (int i = 1; i <= eadStruct.f; ++i) {
            if (eadStruct.try[i] > 0) {
                if (eadStruct.for[i] < this.char.length) {
                    if (this.char[eadStruct.for[i]] == 1) {
                        n += eadStruct.try[i];
                    }
                    else if (this.char[eadStruct.for[i]] > 2) {
                        n2 += eadStruct.try[i] * (this.char[eadStruct.for[i]] - 2);
                    }
                }
                else {
                    n2 += eadStruct.try[i] * 2;
                }
            }
        }
        return n2 + 4 > n;
    }
    
    void if(final EadStruct eadStruct) {
        if (this.for && !this.do(eadStruct)) {
            return;
        }
        double a = this.a(eadStruct);
        double abs = Math.abs(this.do - a);
        if (abs < this.d[this.case - 1]) {
            this.void = "\n\n";
            eadStruct.setupMolForm();
            String char1 = eadStruct.char;
            for (int i = 0; i < this.case; ++i) {
                if (abs < this.d[i]) {
                    final double n = this.d[i];
                    this.d[i] = abs;
                    abs = n;
                    final double n2 = this.f[i];
                    this.f[i] = a;
                    a = n2;
                    final String s = this.e[i];
                    this.e[i] = char1;
                    char1 = s;
                }
                if (this.e[i] != null) {
                    this.void = this.void + Float.toString((float)(this.f[i] - 5.48579896E-4)) + "  " + Float.toString((float)this.d[i]) + "  " + this.e[i] + "\n";
                }
            }
            this.null.setText(this.b + this.void);
        }
    }
    
    double a(final EadStruct eadStruct) {
        double n = 0.0;
        for (int i = 1; i <= eadStruct.f; ++i) {
            if (this.try[i] > 0.0) {
                n += this.try[i] * eadStruct.try[i];
            }
        }
        return n;
    }
}
