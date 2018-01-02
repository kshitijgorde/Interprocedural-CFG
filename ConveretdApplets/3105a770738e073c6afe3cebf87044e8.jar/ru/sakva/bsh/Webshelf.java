// 
// Decompiled by Procyon v0.5.30
// 

package ru.sakva.bsh;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ComponentListener;
import java.util.Hashtable;
import java.awt.Button;

public class Webshelf extends Shelf
{
    int n;
    String docFrame;
    Button b;
    
    public void init() {
        Shelf.icons = new Hashtable();
        this.getParams();
        final String parameter;
        if ((parameter = this.getParameter("frame")) != null) {
            this.docFrame = new String(parameter);
        }
        this.addComponentListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public void start() {
        this.refresh();
    }
    
    public synchronized String getBooksCount() {
        if (super.books == null) {
            return "0";
        }
        return Integer.toString(super.books.length);
    }
    
    public void setDocFrame(final String s) {
        this.docFrame = new String(s);
    }
    
    public void select(final int n, final int n2) {
        if (super.books == null || n < 0 || n >= super.books.length) {
            return;
        }
        final Book book = super.books[n];
        if (book != null) {
            book.select(!book.selected());
            if (book.selected()) {
                try {
                    this.getAppletContext().showDocument(new URL(this.getDocumentBase(), ((WebBook)book).ref), this.docFrame);
                }
                catch (Exception ex) {}
            }
            for (int i = 0; i < super.books.length; ++i) {
                if (i != n) {
                    super.books[i].select(false);
                }
            }
        }
        this.refresh();
    }
    
    public void reserve(final int n) {
        super.books = new WebBook[n];
        for (int i = 0; i < n; super.books[i++] = new WebBook()) {}
    }
    
    public void reserve(final String s) {
        this.reserve(Integer.parseInt(s));
        this.n = 0;
    }
    
    public void putBook(final String s, final String s2) {
        if (this.n < super.books.length) {
            super.books[this.n++] = new WebBook(s, s2);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x4) == 0x0) {
            this.select(this.getBookIndex(mouseEvent.getX(), mouseEvent.getY()), mouseEvent.getModifiers());
            this.refresh();
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public Webshelf() {
        this.docFrame = "_parent";
    }
}
