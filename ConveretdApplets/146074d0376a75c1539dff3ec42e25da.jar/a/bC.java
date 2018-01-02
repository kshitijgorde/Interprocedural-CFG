// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.FontMetrics;
import java.awt.Label;
import java.io.RandomAccessFile;
import java.io.File;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.IOException;
import java.awt.Font;
import java.awt.Event;
import java.util.Vector;
import java.awt.Component;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.Choice;
import java.awt.TextField;

public final class bC extends bs
{
    private TextField q;
    private TextField w;
    private g y;
    private g u;
    private C w;
    private Choice q;
    private boolean e;
    private int q;
    private cV q;
    private Canvas q;
    private Canvas w;
    private TextField e;
    private TextField r;
    private g i;
    private bx q;
    private boolean r;
    
    public final bZ q() {
        final cL cl;
        (cl = new cL(-999, "")).w("");
        return cl;
    }
    
    public final void q(final bZ bz) {
        final cd cd = (cd)bz;
        this.q.setText(cd.getName());
        this.w.setText(cd.e());
        this.q.setBackground(new Color(cd.y()));
        this.r = true;
    }
    
    public final boolean q(final bZ bz) {
        final cd cd = (cd)bz;
        final String trim = this.q.getText().trim();
        final String trim2 = this.w.getText().trim();
        if (trim.length() == 0) {
            this.q.requestFocus();
            new b(super.q, eb.q("Note"), eb.q("You must provide a word to be censored."), super.q).setVisible(true);
            return false;
        }
        for (int i = 0; i < this.q.q(); ++i) {
            final cd cd2 = (cd)this.q.q(i);
            if ((bz.q() < 0 && trim.equalsIgnoreCase(cd2.getName())) || (bz.q() > 0 && trim.equalsIgnoreCase(cd2.getName()) && bz.q() != cd2.q())) {
                this.q.requestFocus();
                new b(super.q, eb.q("Note"), eb.q("Chatwatch with this name already exits."), super.q).setVisible(true);
                return false;
            }
        }
        cd.w(trim2);
        cd.u(this.w.getFont().getStyle());
        cd.a_(trim);
        cd.y(this.q.q());
        return true;
    }
    
    public final void q(final bw bw) {
        bw.q(eb.q("Replace:"), this.q);
        bw.q(eb.q("With:"), this.w);
        if (this.q.q(69)) {
            bw.q(eb.q("Style:"), new Component[] { this.q, this.q, this.w });
        }
        else {
            bw.q(eb.q("Style:"), this.q);
        }
        bw.q("", eb.q("Leave blank to replace with asterisks (***)."));
        bw.setBounds(100, 100, 400, 500);
    }
    
    public final void q() {
        final int selectedIndex = this.q.getSelectedIndex();
        if (super.q || selectedIndex != this.q || ((C)super.q.q(0)).q) {
            final Vector<cd> vector = new Vector<cd>();
            final Vector<cd> vector2 = new Vector<cd>();
            cd cd = null;
            for (int i = 0; i < this.q(); ++i) {
                final cd cd2;
                if ((cd2 = (cd)this.q(i)).q(63)) {
                    vector2.addElement(cd2);
                }
                else {
                    final cd cd3;
                    if ((cd3 = (cd)this.q.o.w(cd2.q())) == null || cd2.q(cd3) != 0) {
                        vector.addElement(cd2);
                    }
                    else if (cd == null) {
                        cd = cd2;
                    }
                }
            }
            if (vector.size() == 0) {
                vector.addElement(cd);
            }
            this.w(vector2);
            this.q(vector);
            super.q = false;
        }
    }
    
    private void q(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final int selectedIndex = this.q.getSelectedIndex();
        final es es;
        (es = new es(17237265, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final cd cd = vector.elementAt(i);
            es.q(i, cd.q());
            es.q(i, 0, cd.q());
            es.q(i, 1, cd.y());
            es.q(i, 0, cd.getName());
            es.q(i, 1, cd.w());
        }
        es.q(-1, 60, selectedIndex == 1 && !this.e);
        es.q(-1, 61, selectedIndex == 0 && !this.e);
        es.q(-1, 62, selectedIndex == 0 && this.e);
        es.q(-1, 59, this.e);
        super.q.q(es);
    }
    
    private void w(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final es es;
        (es = new es(17237266, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final cd cd = vector.elementAt(i);
            es.q(i, cd.q());
            es.q(i, 0, cd.q());
        }
        super.q.q(es);
    }
    
    public final void w() {
        super.w();
        final dW o = super.q.o;
        dW.q();
        try {
            for (int i = 0; i < super.q.o.q(); ++i) {
                this.e(new cL((cd)super.q.o.q(i)));
            }
        }
        finally {
            final dW o2 = super.q.o;
            dW.w();
        }
        if (this.e) {
            this.q.enable();
            if (es.q(super.q.q, 62)) {
                this.q.select(0);
            }
            else {
                this.q.select(1);
            }
        }
        else {
            final long n;
            if (es.q(n = (super.q.w | super.q.q), 62)) {
                this.q.select(0);
                this.q.disable();
            }
            else {
                this.q.enable();
                if (es.q(n, 61)) {
                    this.q.select(0);
                }
                else if (es.q(n, 60)) {
                    this.q.select(1);
                }
                else {
                    this.q.select(2);
                }
            }
        }
        this.q = this.q.getSelectedIndex();
    }
    
    public final void w(final bZ bz) {
        super.w(bz);
        if (bz == null) {
            this.i.e();
            return;
        }
        this.i.q();
    }
    
    public final boolean q(final Event event) {
        switch (event.id) {
            case 1001: {
                final Font font;
                final int style = (font = this.w.getFont()).getStyle();
                if (event.target == this.q) {
                    final TextField w = this.w;
                    final String family = font.getFamily();
                    int n;
                    switch (n = style) {
                        case 3: {
                            n = 2;
                            break;
                        }
                        case 2: {
                            n = 3;
                            break;
                        }
                        case 1: {
                            n = 0;
                            break;
                        }
                        case 0: {
                            n = 1;
                            break;
                        }
                    }
                    w.setFont(new Font(family, n, font.getSize()));
                }
                if (event.target == this.w) {
                    final TextField w2 = this.w;
                    final String name = font.getName();
                    int n2;
                    switch (n2 = style) {
                        case 3: {
                            n2 = 1;
                            break;
                        }
                        case 2: {
                            n2 = 0;
                            break;
                        }
                        case 1: {
                            n2 = 3;
                            break;
                        }
                        case 0: {
                            n2 = 2;
                            break;
                        }
                    }
                    w2.setFont(new Font(name, n2, font.getSize()));
                    break;
                }
                break;
            }
            case 1004: {
                if (this.r && this.q != null) {
                    final Font font2 = this.w.getFont();
                    this.w.setFont(new Font(font2.getFamily(), this.q.q.u(), font2.getSize()));
                    this.r = false;
                    break;
                }
                break;
            }
        }
        return false;
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 402: {
                if (event.target == this.e || event.target == this.r) {
                    final String text = this.e.getText();
                    final String text2 = this.r.getText();
                    final String s = text;
                    this.q.y();
                    final String trim = s.trim();
                    final String trim2 = text2.trim();
                    for (int i = 0; i < super.q.o.q(); ++i) {
                        final cd cd;
                        if (dV.q((cd = (cd)this.w(((cd)super.q.o.q(i)).q())).getName(), trim) && dV.q(cd.e(), trim2)) {
                            this.q.e(cd);
                        }
                        else {
                            this.q.q((aF)cd);
                        }
                    }
                    this.q.w();
                }
            }
            case 1001: {
                if (event.target == this.y) {
                    try {
                        this.t();
                    }
                    catch (IOException ex) {}
                }
                if (event.target == this.u) {
                    try {
                        this.y();
                    }
                    catch (IOException ex2) {}
                }
                if (event.target == this.i) {
                    final Vector q = this.q.q();
                    final Vector<cd> vector = new Vector<cd>();
                    final Vector<bV> vector2 = new Vector<bV>();
                    final int w = this.q.p.w();
                    for (int j = 0; j < q.size(); ++j) {
                        final cd cd2 = q.elementAt(j);
                        this.q.q((aF)cd2);
                        cd2.i(63);
                        vector.addElement(cd2);
                        final bV bv = new bV(w + j, cd2.getName());
                        vector2.addElement(bv);
                        if (this.q != null) {
                            this.q.e(bv);
                        }
                    }
                    this.w(vector);
                    bx.q(vector2, this.q);
                }
                return super.handleEvent(event);
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    private void t() {
        final Frame frame = new Frame();
        String string = "";
        cd cd = null;
        final FileDialog fileDialog;
        (fileDialog = new FileDialog(frame, eb.q("Select file to import."), 0)).show();
        new StringBuffer().append(fileDialog.getDirectory()).append(fileDialog.getFile());
        final File file;
        if ((file = new File(fileDialog.getDirectory(), fileDialog.getFile())).exists()) {
            final RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            try {
                randomAccessFile.seek(0L);
                while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
                    final char c;
                    switch (c = (char)randomAccessFile.read()) {
                        case '\t':
                        case '\uffff': {
                            continue;
                        }
                        case ';': {
                            cd = new cL(-1, string.trim());
                            string = "";
                            continue;
                        }
                        case '\n':
                        case '\f':
                        case '\r': {
                            final String trim;
                            if ((trim = string.trim()).length() != 0) {
                                cd.w(trim);
                            }
                            string = "";
                            this.e(cd);
                            continue;
                        }
                        default: {
                            string += c;
                            continue;
                        }
                    }
                }
            }
            catch (IOException ex) {}
            finally {
                randomAccessFile.close();
            }
        }
        super.q = true;
    }
    
    private void y() {
        final Frame frame = new Frame();
        final String property = System.getProperty("line.separator");
        final FileDialog fileDialog;
        (fileDialog = new FileDialog(frame, eb.q("Select file to save as."), 1)).show();
        new StringBuffer().append(fileDialog.getDirectory()).append(fileDialog.getFile());
        final RandomAccessFile randomAccessFile = new RandomAccessFile(new File(fileDialog.getDirectory(), fileDialog.getFile()), "rw");
        try {
            randomAccessFile.seek(0L);
            for (int i = 0; i < super.q.q(); ++i) {
                final cL cl = (cL)super.q.q(i);
                final String string = cl.q("name") + " ; " + cl.q("replace") + property;
                byte[] array;
                try {
                    array = string.getBytes();
                }
                catch (Exception ex) {
                    array = string.getBytes();
                }
                randomAccessFile.write(array);
            }
        }
        catch (IOException ex2) {
            try {
                randomAccessFile.close();
            }
            catch (IOException ex3) {}
        }
        finally {
            try {
                randomAccessFile.close();
            }
            catch (IOException ex4) {}
        }
        super.q = true;
    }
    
    public bC(final cV q, final boolean e, final bx q2) {
        super(q, eb.q("ChatWatch"), eb.q("ChatWatch"));
        this.r = true;
        this.q = q2;
        this.q = new TextField(20);
        this.w = new TextField(20);
        this.y = new g(80, 20);
        this.u = new g(80, 20);
        (this.q = new Choice()).setForeground(Color.black);
        this.q = q;
        this.e = e;
        if (e) {
            this.q.addItem(eb.q("On For All Sites"));
            this.q.addItem(eb.q("ChatMaster decides"));
        }
        else {
            this.q.addItem(eb.q("Always On"));
            this.q.addItem(eb.q("Default On"));
            this.q.addItem(eb.q("Default Off"));
        }
        this.e = new TextField(10);
        this.r = new TextField(10);
        this.i = new g(eb.q("Move to bad"));
        this.q(eb.q("ChatWatch Mode:"), new Component[] { this.q, new Label(eb.q("Name:")), this.e, new Label(eb.q("Replace")), this.r, this.q.q(72) ? this.i : new Label(""), new Label("") });
        if (!this.q.y) {
            this.y.q(eb.q("Import"));
            this.u.q(eb.q("Export"));
            final FontMetrics fontMetrics;
            int stringWidth = (fontMetrics = this.y.getFontMetrics(this.y.getFont())).stringWidth(eb.q("Export"));
            final int stringWidth2;
            if ((stringWidth2 = fontMetrics.stringWidth(eb.q("Import"))) > stringWidth) {
                stringWidth = stringWidth2;
            }
            stringWidth += 20;
            this.y.resize(stringWidth, 20);
            this.u.resize(stringWidth, 20);
            this.q("", this.y, this.u);
        }
        final String q3 = eb.q("Replace:");
        this.q(this.w = new C(q3.substring(0, q3.length() - 1), "replace"));
        this.w.w(false);
        super.q.w(50);
        super.w.w(120);
        final C c;
        (c = new C(eb.q("View"), "showtousers")).r(true);
        this.q(c, 0);
        c.w(50);
        c.w(false);
        new bD(this).start();
        this.r.setVisible(true);
        this.r.e();
        this.t.setVisible(true);
        this.t.e();
        this.i.e();
        this.q.w(true);
    }
    
    static Canvas q(final bC bc) {
        return bc.w;
    }
    
    static Canvas q(final bC bc, final Canvas w) {
        return bc.w = w;
    }
    
    static cV q(final bC bc) {
        return bc.q;
    }
    
    static Canvas w(final bC bc) {
        return bc.q;
    }
    
    static Canvas w(final bC bc, final Canvas q) {
        return bc.q = q;
    }
}
