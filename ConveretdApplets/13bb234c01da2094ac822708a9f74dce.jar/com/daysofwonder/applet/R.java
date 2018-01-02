// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.Rectangle;
import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.Font;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.TextEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.FocusEvent;
import java.awt.event.TextListener;
import java.awt.event.ActionListener;
import java.awt.FontMetrics;
import java.util.Vector;

public class R extends aI
{
    ao a;
    private Vector i;
    private Vector j;
    private StringBuffer k;
    private int l;
    private int m;
    private int n;
    private FontMetrics o;
    private int p;
    private int q;
    private int r;
    private boolean s;
    private boolean t;
    private int u;
    private int v;
    private boolean w;
    private int x;
    private char y;
    
    public synchronized void a(final ActionListener actionListener) {
        this.i.addElement(actionListener);
    }
    
    public synchronized void a(final TextListener textListener) {
        this.j.addElement(textListener);
    }
    
    private void c(final FocusEvent focusEvent) {
        try {
            this.a(focusEvent);
        }
        catch (Throwable t) {
            this.a(t);
        }
    }
    
    private void d(final FocusEvent focusEvent) {
        try {
            this.b(focusEvent);
        }
        catch (Throwable t) {
            this.a(t);
        }
    }
    
    private void c(final KeyEvent keyEvent) {
        try {
            this.a(keyEvent);
        }
        catch (Throwable t) {
            this.a(t);
        }
    }
    
    private void d(final KeyEvent keyEvent) {
        try {
            this.b(keyEvent);
        }
        catch (Throwable t) {
            this.a(t);
        }
    }
    
    private void f(final MouseEvent mouseEvent) {
        try {
            this.a(mouseEvent);
        }
        catch (Throwable t) {
            this.a(t);
        }
    }
    
    private void g(final MouseEvent mouseEvent) {
        try {
            this.b(mouseEvent);
        }
        catch (Throwable t) {
            this.a(t);
        }
    }
    
    public boolean a() {
        return this.t;
    }
    
    public synchronized void b() {
        try {
            final Clipboard systemClipboard = this.b.getToolkit().getSystemClipboard();
            final StringSelection stringSelection = new StringSelection(this.h());
            systemClipboard.setContents(stringSelection, stringSelection);
        }
        catch (Exception ex) {}
    }
    
    public synchronized void c() {
        try {
            if (!this.o()) {
                return;
            }
            final Clipboard systemClipboard = this.b.getToolkit().getSystemClipboard();
            final String h = this.h();
            this.d();
            final StringSelection stringSelection = new StringSelection(h);
            systemClipboard.setContents(stringSelection, stringSelection);
        }
        catch (Exception ex) {}
    }
    
    private synchronized void w() {
        if (this.u > -1 && this.v > -1) {
            this.d();
            return;
        }
        if (this.l != this.k.length()) {
            this.k = new StringBuffer(this.k.toString().substring(0, this.l)).append(new StringBuffer(this.k.toString().substring(this.l + 1)).toString());
        }
        this.g();
        this.l();
    }
    
    public synchronized void d() {
        if (this.u == -1 || this.v == -1) {
            return;
        }
        int l;
        int n;
        if (this.u < this.v) {
            l = this.u;
            n = this.v;
        }
        else {
            n = this.u;
            l = this.v;
        }
        final String string = this.k.toString();
        this.k = new StringBuffer(string.substring(0, l) + string.substring(n));
        this.l = l;
        this.H();
        this.g();
        this.l();
    }
    
    public synchronized void a(final Graphics graphics) {
        final int height = this.c.height;
        final int width = this.c.width;
        graphics.setFont(this.s());
        if (this.w) {
            graphics.setColor(this.r());
        }
        else {
            graphics.setColor(Color.gray);
        }
        graphics.fillRect(0, 0, width, height);
        this.c(graphics);
        if (this.o()) {
            graphics.setColor(this.q());
        }
        else {
            graphics.setColor(Color.lightGray);
        }
        final int length = this.k.length();
        if (length > this.m) {
            this.m = length;
        }
        final int stringWidth = this.o.stringWidth(this.k.toString().substring(0, this.r));
        final int n = height - this.q;
        if (this.y != '\0') {
            String string = "";
            for (int i = 0; i < this.k.length(); ++i) {
                string += this.y;
            }
            graphics.drawString(string, -(this.r * this.o.charWidth(this.y)) + 4, n);
        }
        else {
            graphics.drawString(this.k.toString(), -stringWidth + 4, n);
        }
        this.b(graphics);
        graphics.setColor(Color.black);
        graphics.drawLine(0, 0, width - 1, 0);
        graphics.drawLine(0, 0, 0, height - 1);
        graphics.drawLine(1, 1, width - 2, 1);
        graphics.drawLine(1, 1, 1, height - 2);
        graphics.setColor(Color.gray);
        graphics.drawLine(width - 2, 0, width - 2, height - 2);
        graphics.drawLine(0, height - 2, width - 2, height - 2);
        graphics.setColor(Color.white);
        graphics.drawLine(width - 1, 0, width - 1, height - 1);
        graphics.drawLine(0, height - 1, width - 1, height - 1);
    }
    
    public synchronized void b(final Graphics graphics) {
        if (!this.t) {
            return;
        }
        final int n = this.n + 0 + 2;
        final int n2 = this.l - this.r;
        final int stringWidth = this.o.stringWidth("a");
        String s = "";
        if (this.y != '\0') {
            for (int i = 0; i < this.k.length(); ++i) {
                s += this.y;
            }
        }
        else {
            s = this.k.toString();
        }
        final int stringWidth2 = this.o.stringWidth(s.substring(this.r, this.l));
        if (this.s) {
            graphics.drawLine(stringWidth2 + 4, n - this.o.getHeight(), stringWidth2 + 4, n);
        }
        else {
            graphics.drawLine(stringWidth2 + 4, n, stringWidth2 + stringWidth + 5, n);
        }
    }
    
    public synchronized void c(final Graphics graphics) {
        int n;
        int n2;
        if (this.u > this.v) {
            n = this.v;
            n2 = this.u;
        }
        else {
            n = this.u;
            n2 = this.v;
        }
        if (n < 0 || n2 < 0) {
            return;
        }
        graphics.setColor(Color.lightGray);
        String s = "";
        if (this.y != '\0') {
            for (int i = 0; i < this.k.length(); ++i) {
                s += this.y;
            }
        }
        else {
            s = this.k.toString();
        }
        int stringWidth = 0;
        if (n > this.r) {
            stringWidth = this.o.stringWidth(s.substring(this.r, n));
        }
        stringWidth += 4;
        graphics.fillRect(stringWidth, 0, this.o.stringWidth(s.substring(this.r, n2)) + 4 - stringWidth, this.p);
    }
    
    public synchronized void e() {
        this.l = this.k.length();
        final int n = this.p().width / this.o.charWidth('a');
        if (this.l - this.r > n) {
            this.a(this.l - n);
        }
        this.l();
    }
    
    private int a(final char c, final int n, final StringBuffer sb) {
        int n2;
        for (n2 = n; n2 > 0 && sb.charAt(n2) != c; --n2) {}
        return n2;
    }
    
    private int b(final char c, final int n, final StringBuffer sb) {
        int n2 = n;
        if (n2 >= sb.length()) {
            n2 = sb.length() - 1;
        }
        while (n2 > 0 && sb.charAt(n2) == c) {
            --n2;
        }
        return n2;
    }
    
    public void f() {
        final Vector y = this.y();
        final ActionEvent actionEvent = new ActionEvent(this, 0, "");
        for (int i = 0; i < y.size(); ++i) {
            y.elementAt(i).actionPerformed(actionEvent);
        }
    }
    
    public void g() {
        final Vector x = this.x();
        final TextEvent textEvent = new TextEvent(this, 0);
        for (int i = 0; i < x.size(); ++i) {
            x.elementAt(i).textValueChanged(textEvent);
        }
    }
    
    private synchronized Vector x() {
        return (Vector)this.j.clone();
    }
    
    private synchronized Vector y() {
        return (Vector)this.i.clone();
    }
    
    public synchronized String h() {
        if (this.v == -1 || this.u == -1) {
            return "";
        }
        int n;
        int n2;
        if (this.u < this.v) {
            n = this.u;
            n2 = this.v;
        }
        else {
            n = this.v;
            n2 = this.u;
        }
        return this.k.toString().substring(n, n2);
    }
    
    public synchronized String i() {
        return this.k.toString();
    }
    
    private void a(final Throwable t) {
    }
    
    private synchronized void z() {
        this.a(this.l = 0);
        this.l();
    }
    
    private void A() {
        this.b.addKeyListener(this.a);
        this.b.addMouseListener(this.a);
        this.b.addMouseMotionListener(this.a);
    }
    
    private void B() {
        try {
            this.a(new Font("monospaced", 0, 12));
            this.b(Color.white);
            this.b("NiceTextField");
            this.A();
        }
        catch (Throwable t) {
            this.a(t);
        }
        this.k = new StringBuffer("");
        this.G();
    }
    
    private synchronized void a(final char c) {
        if (this.s) {
            this.k.insert(this.l, c);
        }
        else if (this.l < this.k.length()) {
            this.k.setCharAt(this.l, c);
        }
        else {
            this.k.insert(this.l, c);
        }
        ++this.l;
        final int n = this.p().width / this.o.charWidth('a');
        if (this.l - this.r > n) {
            this.a(this.l - n);
        }
        this.g();
        this.l();
    }
    
    private synchronized void c(final String s) {
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != '\n') {
                if (this.s) {
                    this.k.insert(this.l, s.charAt(i));
                }
                else if (this.l < this.k.length()) {
                    this.k.setCharAt(this.l, s.charAt(i));
                }
                else {
                    this.k.insert(this.l, s.charAt(i));
                }
                ++this.l;
            }
        }
        final int n = this.p().width / this.o.charWidth('a');
        if (this.l - this.r > n) {
            this.a(this.l - n);
        }
        this.g();
        this.l();
    }
    
    private synchronized void C() {
        if (this.l == 0) {
            return;
        }
        --this.l;
        final int n = this.p().width / this.o.charWidth('a');
        if (this.l < this.r) {
            this.a(this.l);
        }
        this.l();
    }
    
    private synchronized void D() {
        if (this.l >= this.k.length() - 1) {
            return;
        }
        if (this.k.charAt(this.l) == ' ') {
            int l;
            for (l = this.l; l < this.k.length() - 1 && this.k.charAt(l) == ' '; ++l) {}
            this.l = l;
        }
        else {
            int i;
            for (i = this.l; i < this.k.length() - 1 && this.k.charAt(i) != ' '; ++i) {}
            this.l = i;
            int j;
            for (j = this.l; j < this.k.length() - 1 && this.k.charAt(j) == ' '; ++j) {}
            this.l = j;
        }
        final int n = this.p().width / this.o.charWidth('a');
        if (this.l - this.r > n) {
            this.a(this.l - n);
        }
        this.l();
    }
    
    public void a(final FocusEvent focusEvent) {
        this.t = true;
        this.l();
    }
    
    public void b(final FocusEvent focusEvent) {
        this.t = false;
        this.l();
    }
    
    public synchronized void a(final KeyEvent keyEvent) {
        if (!this.o()) {
            return;
        }
        keyEvent.getKeyChar();
        switch (keyEvent.getKeyCode()) {
            case 8: {
                this.C();
                if (this.w) {
                    this.w();
                    break;
                }
                break;
            }
            case 37: {
                if (keyEvent.isControlDown()) {
                    this.E();
                    break;
                }
                if (keyEvent.isShiftDown()) {
                    if (this.u == -1) {
                        this.u = this.l;
                    }
                    this.C();
                    this.v = this.l;
                    break;
                }
                this.H();
                this.C();
                break;
            }
            case 39: {
                if (keyEvent.isControlDown()) {
                    this.D();
                    break;
                }
                if (keyEvent.isShiftDown()) {
                    if (this.u == -1) {
                        this.u = this.l;
                    }
                    this.F();
                    this.v = this.l;
                    break;
                }
                this.H();
                this.F();
                break;
            }
            case 38: {}
            case 36: {
                if (keyEvent.isShiftDown() && this.u == -1) {
                    this.u = this.l;
                }
                this.z();
                if (keyEvent.isShiftDown()) {
                    this.v = this.l;
                    break;
                }
                this.H();
                break;
            }
            case 35: {
                if (keyEvent.isShiftDown()) {
                    if (this.u == -1) {
                        this.u = this.l;
                    }
                    this.e();
                    this.v = this.l;
                    break;
                }
                this.e();
                this.H();
                break;
            }
            case 10: {
                this.f();
                keyEvent.consume();
                break;
            }
            case 127: {
                System.out.println("delete " + keyEvent.isControlDown());
                if (keyEvent.isControlDown() && this.w) {
                    this.c();
                    break;
                }
                if (this.w) {
                    this.w();
                    break;
                }
                break;
            }
            case 155: {
                System.out.println("insert " + keyEvent.isControlDown() + " " + keyEvent.isShiftDown());
                if (keyEvent.isControlDown()) {
                    this.b();
                    break;
                }
                if (keyEvent.isShiftDown() && this.w) {
                    this.j();
                    break;
                }
                if (this.w) {
                    this.a(!this.s);
                    break;
                }
                break;
            }
            case 33: {}
            case 34: {}
            case 9: {}
            case 17: {}
            case 18: {}
        }
    }
    
    public synchronized void b(final KeyEvent keyEvent) {
        if (!this.o()) {
            return;
        }
        if (keyEvent.isConsumed()) {
            return;
        }
        final char keyChar = keyEvent.getKeyChar();
        keyEvent.getKeyCode();
        if (keyChar != '\uffff') {
            final int modifiers = keyEvent.getModifiers();
            if ((modifiers & 0x8) == (modifiers & 0x2) && keyChar >= ' ' && keyChar != '\u007f' && this.w) {
                this.d();
                this.a(keyChar);
            }
        }
        keyEvent.consume();
    }
    
    public synchronized void a(final MouseEvent mouseEvent) {
        if (!this.o()) {
            return;
        }
        final int n = mouseEvent.getX() - this.c.x;
        if (n > this.p().width) {
            this.F();
        }
        if (n < 0) {
            this.C();
        }
        String s = "";
        if (this.y != '\0') {
            for (int i = 0; i < this.k.length(); ++i) {
                s += this.y;
            }
        }
        else {
            s = this.k.toString();
        }
        this.l = s.length();
        while (this.l > 0 && this.o.stringWidth(s.substring(this.r, this.l)) >= n) {
            --this.l;
        }
        this.v = this.l;
        this.l();
    }
    
    public synchronized void b(final MouseEvent mouseEvent) {
        if (!this.o()) {
            return;
        }
        this.b.requestFocus();
        this.t = true;
        final int n = mouseEvent.getX() - this.c.x;
        String s = "";
        if (this.y != '\0') {
            for (int i = 0; i < this.k.length(); ++i) {
                s += this.y;
            }
        }
        else {
            s = this.k.toString();
        }
        this.l = s.length();
        while (this.l > 0 && this.o.stringWidth(s.substring(this.r, this.l)) >= n) {
            --this.l;
        }
        this.u = this.l;
        this.v = -1;
        this.l();
    }
    
    public void j() {
        if (!this.o()) {
            return;
        }
        if (!this.w) {
            return;
        }
        this.d();
        final Transferable contents = this.b.getToolkit().getSystemClipboard().getContents(this);
        try {
            this.c((String)contents.getTransferData(DataFlavor.stringFlavor));
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private synchronized void E() {
        if (this.l == 0) {
            return;
        }
        if (this.l == this.k.length() || this.k.charAt(this.l) == ' ') {
            this.l = this.b(' ', this.l, this.k);
            if (this.l == 0) {
                this.a(0);
                this.l();
                return;
            }
            this.l = this.a(' ', this.l, this.k);
            if (this.l == 0) {
                this.a(0);
                this.l();
                return;
            }
            ++this.l;
        }
        else {
            this.l = this.a(' ', this.l, this.k);
            if (this.l == 0) {
                this.a(0);
                this.l();
                return;
            }
            this.l = this.b(' ', this.l, this.k);
            if (this.l == 0) {
                this.a(0);
                this.l();
                return;
            }
            this.l = this.a(' ', this.l, this.k);
            if (this.l == 0) {
                this.a(0);
                this.l();
                return;
            }
            ++this.l;
        }
        if (this.l < this.r) {
            this.a(this.l);
        }
        this.l();
    }
    
    public synchronized void b(final ActionListener actionListener) {
        this.i.removeElement(actionListener);
    }
    
    public synchronized void b(final TextListener textListener) {
        this.j.removeElement(textListener);
    }
    
    private synchronized void a(final int r) {
        final int r2 = this.r;
        this.r = r;
    }
    
    private synchronized void F() {
        if (this.l >= this.k.length()) {
            return;
        }
        ++this.l;
        final int n = this.p().width / this.o.charWidth('a');
        if (this.l - this.r > n) {
            this.a(this.l - n);
        }
        this.l();
    }
    
    public void a(final boolean s) {
        this.s = s;
        this.l();
    }
    
    public synchronized void a(final String s) {
        this.k = new StringBuffer(s);
        this.l = 0;
        this.m = 0;
        this.r = 0;
        this.u = -1;
        this.v = -1;
        this.l();
    }
    
    private void G() {
        final int n = 0;
        this.a(this.s());
        this.o = this.b(this.s());
        this.n = this.o.getLeading() + this.o.getAscent() + n;
        this.p = this.o.getHeight() + n;
        this.q = this.o.getMaxDescent() + 1;
    }
    
    private synchronized void H() {
        this.u = -1;
        this.v = -1;
    }
    
    public R(final Component component, final Rectangle rectangle) {
        super(component, rectangle);
        this.a = new ao(this);
        this.i = new Vector();
        this.j = new Vector();
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = true;
        this.t = false;
        this.u = -1;
        this.v = -1;
        this.w = true;
        this.x = 30;
        this.y = '\0';
        this.B();
    }
    
    public void k() {
        super.k();
        this.b.removeKeyListener(this.a);
        this.b.removeMouseListener(this.a);
        this.b.removeMouseMotionListener(this.a);
        this.b = null;
    }
}
