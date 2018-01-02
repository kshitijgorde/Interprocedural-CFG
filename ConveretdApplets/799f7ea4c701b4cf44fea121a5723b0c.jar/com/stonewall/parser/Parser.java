// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import java.io.FileInputStream;
import java.net.URL;
import org.jdom.Content;
import java.io.InputStream;
import org.jdom.Attribute;
import org.jdom.xpath.XPath;
import java.util.Collection;
import java.util.Iterator;
import org.jdom.output.XMLOutputter;
import org.jdom.output.Format;
import java.io.BufferedReader;
import com.stonewall.parser.jdom.SAXBuilder;
import java.io.Reader;
import java.util.ArrayList;
import org.xmodel.log.Log;
import org.jdom.Element;
import java.util.List;
import com.stonewall.parser.interpreter.Dispatcher;
import org.jdom.Document;
import org.jdom.Namespace;

public class Parser
{
    public static final String version = "1.8";
    static final String propertyPrefix = "org.parser";
    static final Namespace namespace;
    private String text;
    private final ParserState state;
    private Document specification;
    private Dispatcher dispatcher;
    private ClassLoader classloader;
    private List<Rule> rules;
    private Dictionary<Element> procedures;
    private Document model;
    private PathStore pathStore;
    private PatternStore patternStore;
    public static Log log;
    
    static {
        namespace = Namespace.getNamespace("p", "http://org.parser/ns/parser");
        Parser.log = Log.getLog(Parser.class);
    }
    
    public Parser() {
        this.state = new ParserState();
        this.dispatcher = new Dispatcher();
        this.classloader = this.getClass().getClassLoader();
        this.rules = new ArrayList<Rule>();
        this.procedures = new Dictionary<Element>();
        this.model = new Document();
        this.pathStore = new PathStore();
        this.patternStore = new PatternStore();
        this.model.setRootElement(new Element("model"));
    }
    
    public void init(final Reader reader) throws Exception {
        this.rules.clear();
        this.model.getRootElement().removeContent();
        final SAXBuilder builder = new SAXBuilder();
        builder.addFilter(Parser.namespace);
        this.importSpecification(builder.build(reader));
    }
    
    public Document parse(final Reader reader) throws Exception {
        final BufferedReader br = new BufferedReader(reader);
        final long start = System.currentTimeMillis();
        this.preProcess();
        while (true) {
            this.text = br.readLine();
            if (this.text == null) {
                break;
            }
            this.process(this.text);
        }
        this.postProcess();
        final long duration = System.currentTimeMillis() - start;
        Parser.log.info("parser v1.8 : interpreter [M20091220_01], parsing complete: " + duration + " (ms)");
        return this.model;
    }
    
    public void classloader(final ClassLoader loader) {
        this.classloader = loader;
    }
    
    public ClassLoader classloader() {
        return this.classloader;
    }
    
    public Document model() {
        return this.model;
    }
    
    public Dictionary<Element> procedures() {
        return this.procedures;
    }
    
    public void dispatcher(final Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }
    
    public Dispatcher dispatcher() {
        return this.dispatcher;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        final Format format = Format.getPrettyFormat();
        final XMLOutputter p = new XMLOutputter(format);
        Element root = this.specification.getRootElement();
        sb.append("\nSPECIFICATION:\n");
        sb.append(p.outputString(root));
        root = this.model.getRootElement();
        sb.append("\nMODEL:\n");
        sb.append(p.outputString(root));
        return sb.toString();
    }
    
    String text() {
        return this.text;
    }
    
    ParserState state() {
        return this.state;
    }
    
    PathStore pathStore() {
        return this.pathStore;
    }
    
    PatternStore patternStore() {
        return this.patternStore;
    }
    
    Namespace namespace() {
        return Parser.namespace;
    }
    
    private void preProcess() {
        for (final Rule r : this.rules) {
            if (r.pre()) {
                r.apply();
            }
        }
    }
    
    private void postProcess() {
        for (final Rule r : this.rules) {
            if (r.post()) {
                r.apply();
            }
        }
    }
    
    private void process(final String text) {
        Parser.log.debug("processing (input) line:\n" + text);
        Parser.log.debug("matching rules, state='" + this.state.current() + "'");
        for (final Rule r : this.rules) {
            if (r.basic() && r.match(text)) {
                r.apply();
                return;
            }
        }
        Parser.log.debug("***no rule(s) matched, skipped**");
    }
    
    private void importSpecification(final Document spec) throws Exception {
        this.specification = spec;
        final Element root = spec.getRootElement();
        this.processImports(spec);
        this.importNamespaces(root);
        this.pathStore.add(Parser.namespace);
        this.importProperties();
        this.importModel();
        this.importRules();
        this.importProcedures();
        Parser.log.debug(this);
    }
    
    private void importModel() throws Exception {
        final Element root = this.model.getRootElement();
        root.setNamespace(this.specification.getRootElement().getNamespace());
        final XPath xpath = this.pathStore.get("//p:model");
        for (final Element e : xpath.selectNodes((Object)this.specification)) {
            root.addContent((Collection)e.cloneContent());
        }
    }
    
    private void importRules() throws Exception {
        final XPath xpath = this.pathStore.get("//p:rule");
        for (final Element e : xpath.selectNodes((Object)this.specification)) {
            final Rule r = new Rule(this, e);
            this.rules.add(r);
            this.state.add(r.state());
        }
    }
    
    private void importProcedures() throws Exception {
        final XPath xpath = this.pathStore.get("//p:procedure");
        for (final Element e : xpath.selectNodes((Object)this.specification)) {
            final String name = e.getAttributeValue("name");
            if (name.indexOf(46) > 0) {
                this.procedures.put(name, e);
                Parser.log.debug("global procedure \"" + name + "\" added");
            }
        }
    }
    
    private void processImports(final Document spec) throws Exception {
        final XPath xpath = this.pathStore.get("//p:import");
        final String rpath = System.getProperty("org.parser.import.resource.path");
        for (final Element e : xpath.selectNodes((Object)spec)) {
            final Attribute attr = e.getAttributes().get(0);
            ImportMethod method = ImportMethod.valueOf(attr.getName());
            String src = attr.getValue();
            if (rpath != null && method == ImportMethod.resource) {
                method = ImportMethod.file;
                src = String.valueOf(rpath) + "/" + src.substring(0, src.lastIndexOf(46)).replace('.', '/') + src.substring(src.lastIndexOf(46));
            }
            Parser.log.debug("importing: " + method + ":" + src);
            final InputStream istr = this.open(method, src);
            this.importDocument(e, src, istr);
            istr.close();
        }
    }
    
    private void importDocument(final Element e, final String src, final InputStream istr) throws Exception {
        final SAXBuilder builder = new SAXBuilder();
        builder.addFilter(Parser.namespace);
        final Document d = builder.build(src, istr);
        this.processImports(d);
        final Element root = d.getRootElement();
        final Element parent = e.getParentElement();
        this.importContent(parent, root, parent.indexOf((Content)e));
        parent.removeContent((Content)e);
    }
    
    private void importContent(final Element parent, final Element root, final int index) {
        if (root.getName().equals("parser")) {
            parent.addContent(index, (Collection)root.cloneContent());
        }
        else {
            parent.addContent(index, root.detach());
        }
    }
    
    private void importProperties() throws Exception {
        final XPath xpath = this.pathStore.get("//p:property");
        for (final Element e : xpath.selectNodes((Object)this.specification)) {
            final String key = e.getAttributeValue("name");
            final String value = e.getTextTrim();
            System.setProperty(key, value);
        }
    }
    
    private void importNamespaces(final Element node) throws Exception {
        final Namespace ns = node.getNamespace();
        if (ns.getPrefix().length() > 0 && ns.getURI().length() > 0) {
            if (ns.getPrefix().equals("p")) {
                if (!ns.getURI().equals(Parser.namespace.getURI())) {
                    Parser.log.error("parser namespace mismatch found: " + ns);
                }
            }
            else {
                this.pathStore.add(ns);
            }
        }
        for (final Element child : node.getChildren()) {
            this.importNamespaces(child);
        }
    }
    
    private InputStream open(final ImportMethod method, final String src) throws Exception {
        InputStream istr = null;
        switch (method) {
            case url: {
                istr = new URL(src).openStream();
                break;
            }
            case file: {
                istr = new FileInputStream(src);
                break;
            }
            case resource: {
                istr = this.classloader.getResourceAsStream(src);
                if (istr == null) {
                    final String msg = "resource (" + src + "): not-found";
                    throw new Exception(msg);
                }
                break;
            }
        }
        return istr;
    }
    
    enum ImportMethod
    {
        url("url", 0), 
        file("file", 1), 
        resource("resource", 2);
        
        private ImportMethod(final String s, final int n) {
        }
    }
}
