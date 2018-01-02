// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.FontMetrics;
import java.io.RandomAccessFile;
import java.io.File;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.IOException;
import java.awt.Font;
import java.awt.Event;
import java.util.Vector;
import java.awt.Label;
import java.awt.Component;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.Choice;
import java.awt.TextField;

public final class ci extends G
{
    private TextField q;
    private TextField w;
    private ad t;
    private ad y;
    private bV w;
    private Choice q;
    private boolean e;
    private int q;
    ap q;
    Canvas q;
    Canvas w;
    private boolean r;
    
    public final bp q() {
        final aa aa;
        (aa = new aa(-999, "")).q("");
        return aa;
    }
    
    public final void q(final bp bp) {
        final bw bw = (bw)bp;
        this.q.setText(bw.a);
        this.w.setText(bw.r());
        this.q.setBackground(new Color(bw.w()));
        this.r = true;
    }
    
    public final boolean q(final bp bp) {
        final bw bw = (bw)bp;
        final String trim = this.q.getText().trim();
        final String trim2 = this.w.getText().trim();
        if (trim.length() == 0) {
            this.q.requestFocus();
            new dd(super.q, be.w("Note"), be.w("You must provide a word to be censored."), super.q).setVisible(true);
            return false;
        }
        for (int i = 0; i < this.q.q; ++i) {
            final bw bw2 = (bw)this.q.q(i);
            if ((bp.s < 0 && trim.equalsIgnoreCase(bw2.a)) || (bp.s > 0 && trim.equalsIgnoreCase(bw2.a) && bp.s != bw2.s)) {
                this.q.requestFocus();
                new dd(super.q, be.w("Note"), be.w("Chatwatch with this name already exits."), super.q).setVisible(true);
                return false;
            }
        }
        bw.q(trim2);
        bw.a(this.w.getFont().getStyle());
        bw.a = trim;
        bw.p(this.q.q());
        return true;
    }
    
    public final void q(final dK dk) {
        dk.q(be.w("Replace:"), this.q, 0);
        dk.q(be.w("With:"), this.w, 0);
        if ((dN.w() && dN.q) || (dN.q() && dN.w)) {
            dk.q(be.w("Style:"), new Component[] { this.q, this.q, this.w });
        }
        else {
            dk.q(be.w("Style:"), this.q, 0);
        }
        final String s = "";
        final String w = be.w("Leave blank to replace with asterisks (***).");
        final String s2 = s;
        final Label label;
        (label = new Label(w)).setFont(cb.r);
        dk.q(s2, label, 0);
        dk.setBounds(100, 100, 400, 500);
    }
    
    public final void q() {
        final int selectedIndex = this.q.getSelectedIndex();
        if (super.w || selectedIndex != this.q || ((bV)super.q.q(0)).q) {
            final Vector<bw> vector = new Vector<bw>();
            final Vector<bw> vector2 = new Vector<bw>();
            bw bw = null;
            for (int i = 0; i < this.q(); ++i) {
                final bw bw2;
                if ((bw2 = (bw)this.q(i)).q(63)) {
                    vector2.addElement(bw2);
                }
                else {
                    final bw bw3;
                    if ((bw3 = (bw)this.q.j.w(bw2.s)) == null || bw2.q(bw3) != 0) {
                        vector.addElement(bw2);
                    }
                    else if (bw == null) {
                        bw = bw2;
                    }
                }
            }
            if (vector.size() == 0) {
                vector.addElement(bw);
            }
            this.w(vector2);
            this.q(vector);
            super.w = false;
        }
    }
    
    private void q(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final int selectedIndex = this.q.getSelectedIndex();
        final dI di;
        (di = new dI(17237265, vector.size())).w = -1;
        di.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final bw bw = vector.elementAt(i);
            di.q(i, bw.w());
            di.q(i, 0, bw.s);
            di.q(i, 1, bw.w());
            di.q(i, 0, bw.a);
            di.q(i, 1, bw.e());
        }
        di.q(-1, 60, selectedIndex == 1 && !this.e);
        di.q(-1, 61, selectedIndex == 0 && !this.e);
        di.q(-1, 62, selectedIndex == 0 && this.e);
        di.q(-1, 59, this.e);
        super.q.o(di);
    }
    
    private void w(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final dI di;
        (di = new dI(17237266, vector.size())).w = -1;
        di.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final bw bw = vector.elementAt(i);
            di.q(i, bw.w());
            di.q(i, 0, bw.s);
        }
        super.q.o(di);
    }
    
    public final void w() {
        super.w();
        try {
            for (int i = 0; i < super.q.j.q; ++i) {
                this.e(new aa((bw)super.q.j.q(i)));
            }
        }
        finally {}
        if (this.e) {
            this.q.enable();
            if (dI.q(super.q.u, 62)) {
                this.q.select(0);
            }
            else {
                this.q.select(1);
            }
        }
        else {
            final long n;
            if (dI.q(n = (super.q.i | super.q.u), 62)) {
                this.q.select(0);
                this.q.disable();
            }
            else {
                this.q.enable();
                if (dI.q(n, 61)) {
                    this.q.select(0);
                }
                else if (dI.q(n, 60)) {
                    this.q.select(1);
                }
                else {
                    this.q.select(2);
                }
            }
        }
        this.q = this.q.getSelectedIndex();
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
                    this.w.setFont(new Font(font2.getFamily(), this.q.q.e(), font2.getSize()));
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
            case 1001: {
                if (event.target == this.t) {
                    try {
                        this.t();
                    }
                    catch (IOException ex) {}
                }
                if (event.target == this.y) {
                    try {
                        this.y();
                    }
                    catch (IOException ex2) {}
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
        bw bw = null;
        final FileDialog fileDialog;
        (fileDialog = new FileDialog(frame, be.w("Select file to import."), 0)).show();
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
                            bw = new aa(-1, string.trim());
                            string = "";
                            continue;
                        }
                        case '\n':
                        case '\f':
                        case '\r': {
                            final String trim;
                            if ((trim = string.trim()).length() != 0) {
                                bw.q(trim);
                            }
                            string = "";
                            this.e(bw);
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
        super.w = true;
    }
    
    private void y() {
        final Frame frame = new Frame();
        final String property = System.getProperty("line.separator");
        final FileDialog fileDialog;
        (fileDialog = new FileDialog(frame, be.w("Select file to save as."), 1)).show();
        new StringBuffer().append(fileDialog.getDirectory()).append(fileDialog.getFile());
        final RandomAccessFile randomAccessFile = new RandomAccessFile(new File(fileDialog.getDirectory(), fileDialog.getFile()), "rw");
        try {
            randomAccessFile.seek(0L);
            for (int i = 0; i < super.q.q; ++i) {
                final aa aa = (aa)super.q.q(i);
                final String string = aa.q("name") + " ; " + aa.q("replace") + property;
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
        super.w = true;
    }
    
    public ci(final ap q, final boolean e) {
        super(q, be.w("ChatWatch"), be.w("ChatWatch"));
        this.r = true;
        this.q = new TextField(20);
        this.w = new TextField(20);
        this.t = new ad(80, 20);
        this.y = new ad(80, 20);
        (this.q = new Choice()).setForeground(Color.black);
        this.q = q;
        this.e = e;
        if (e) {
            this.q.addItem(be.w("On For All Sites"));
            this.q.addItem(be.w("ChatMaster decides"));
        }
        else {
            this.q.addItem(be.w("Always On"));
            this.q.addItem(be.w("Default On"));
            this.q.addItem(be.w("Default Off"));
        }
        this.q(be.w("ChatWatch Mode:"), this.q);
        if (!this.q.y) {
            this.t.q(be.w("Import"));
            this.y.q(be.w("Export"));
            final FontMetrics fontMetrics;
            int stringWidth = (fontMetrics = this.t.getFontMetrics(this.t.getFont())).stringWidth(be.w("Export"));
            final int stringWidth2;
            if ((stringWidth2 = fontMetrics.stringWidth(be.w("Import"))) > stringWidth) {
                stringWidth = stringWidth2;
            }
            stringWidth += 20;
            this.t.resize(stringWidth, 20);
            this.y.resize(stringWidth, 20);
            this.q("", this.t, this.y);
        }
        final String w = be.w("Replace:");
        this.q(this.w = new bV(w.substring(0, w.length() - 1), "replace"));
        this.w.w(false);
        super.q.w(50);
        super.w.w(120);
        final bV bv;
        (bv = new bV(be.w("View"), "showtousers")).r(true);
        this.q(bv, 0);
        bv.w(50);
        bv.w(false);
        new dC(this).start();
        this.e.setVisible(true);
        this.e.e();
        this.r.setVisible(true);
        this.r.e();
    }
}
