// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.jdom;

import java.io.FileReader;
import java.io.OutputStream;
import org.jdom.output.XMLOutputter;
import org.jdom.output.Format;
import java.io.Reader;
import java.net.URL;
import java.io.IOException;
import org.jdom.JDOMException;
import java.io.FileInputStream;
import org.jdom.Document;
import java.io.File;
import java.util.ArrayList;
import org.jdom.Namespace;
import java.util.List;

public class SAXBuilder extends org.jdom.input.SAXBuilder
{
    private List<Namespace> nsfilter;
    private final SAXHandler handler;
    
    public SAXBuilder() {
        this.nsfilter = new ArrayList<Namespace>();
        this.handler = new SAXHandler();
    }
    
    public Document build(final File f) throws JDOMException, IOException {
        final InputStream istr = new InputStream(f.getName(), new FileInputStream(f));
        this.handler.setInput(istr);
        this.handler.setFilter(this.nsfilter);
        final Document d = super.build((java.io.InputStream)istr);
        istr.close();
        return d;
    }
    
    public Document build(final java.io.InputStream in) throws JDOMException, IOException {
        return this.build("", in);
    }
    
    public Document build(final URL url) throws JDOMException, IOException {
        final InputStream istr = new InputStream(url.toString(), url.openStream());
        this.handler.setInput(istr);
        this.handler.setFilter(this.nsfilter);
        final Document d = super.build((java.io.InputStream)istr);
        istr.close();
        return d;
    }
    
    public Document build(final Reader reader) throws JDOMException, IOException {
        final com.stonewall.parser.jdom.Reader srdr = new com.stonewall.parser.jdom.Reader("", reader);
        this.handler.setInput(srdr);
        this.handler.setFilter(this.nsfilter);
        return super.build((Reader)srdr);
    }
    
    public Document build(final String fn, final java.io.InputStream in) throws JDOMException, IOException {
        final InputStream istr = new InputStream(fn, in);
        this.handler.setInput(istr);
        this.handler.setFilter(this.nsfilter);
        return super.build((java.io.InputStream)istr);
    }
    
    public static void main(final String[] args) throws Exception {
        final SAXBuilder b = new SAXBuilder();
        final XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
        System.out.println("======== FILE =========");
        final File f = new File("/home/jortel/Desktop/t.xml");
        out.output(b.build(f), (OutputStream)System.out);
        System.out.println("======== ISTR =========");
        final FileInputStream istr = new FileInputStream("/home/jortel/Desktop/t.xml");
        out.output(b.build(istr), (OutputStream)System.out);
        System.out.println("======== READER =========");
        final FileReader reader = new FileReader("/home/jortel/Desktop/t.xml");
        out.output(b.build(reader), (OutputStream)System.out);
        System.out.println("======== URL =========");
        final URL url = new URL("file:///home/jortel/Desktop/t.xml");
        out.output(b.build(url), (OutputStream)System.out);
        System.out.print("Done");
    }
    
    public void addFilter(final Namespace ns) {
        this.nsfilter.add(ns);
    }
    
    public List<Namespace> getFilters() {
        return this.nsfilter;
    }
    
    public void clearFilters() {
        this.nsfilter.clear();
    }
    
    protected SAXHandler createContentHandler() {
        return this.handler;
    }
}
