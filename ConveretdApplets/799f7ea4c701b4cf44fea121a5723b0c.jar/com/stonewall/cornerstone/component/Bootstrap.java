// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.component;

import java.io.InputStream;
import java.io.FileInputStream;
import com.stonewall.cornerstone.utility.ProxySelector;
import java.util.Stack;
import org.xmodel.Xlate;
import java.util.ArrayList;
import com.stonewall.cornerstone.utility.Namespaces;
import java.util.StringTokenizer;
import java.io.OutputStream;
import org.xmodel.xml.IXmlIO;
import com.stonewall.cornerstone.utility.ModelBuilder;
import java.io.FileOutputStream;
import java.util.Iterator;
import org.xmodel.xpath.expression.IExpression;
import java.util.List;
import org.xmodel.xpath.XPath;
import com.stonewall.cornerstone.utility.Encrypted;
import org.xmodel.IModelObject;
import java.io.File;

public class Bootstrap
{
    private File file;
    private IModelObject document;
    private boolean validate;
    private boolean initlog;
    static final String[] required;
    
    static {
        required = new String[] { "cornerstone.server.type", "cornerstone.jms.hostname", "cornerstone.jms.port", "cornerstone.jms.sslport", "cornerstone.jms.username", "cornerstone.jms.password", "cornerstone.jms.timeout", "cornerstone.jms.retries", "cornerstone.rmi.timeout" };
    }
    
    public Bootstrap() {
        this.file = null;
        this.document = null;
        this.validate = true;
        this.initlog = true;
    }
    
    public Bootstrap(final IModelObject document) {
        this.file = null;
        this.document = null;
        this.validate = true;
        this.initlog = true;
        this.document = document;
    }
    
    public Bootstrap(final boolean validate, final boolean initlog) {
        this();
        this.validate = validate;
        this.initlog = initlog;
    }
    
    public void init() throws Exception {
        this.readSystemProperties();
        if (this.document == null) {
            this.read();
        }
        this.setProperties();
        if (this.validate) {
            this.validate();
        }
        this.netInit();
    }
    
    public void update() throws Exception {
        if (this.document == null) {
            this.read();
        }
        final Encrypted en = new Encrypted();
        final IExpression path = XPath.createExpression("//*[not(boolean(*))]");
        final List<IModelObject> list = path.query(this.document, null);
        for (final IModelObject o : list) {
            final String property = this.getPath(o);
            String value = System.getProperty(property);
            if (this.encrypted(o)) {
                en.setContent(value);
                value = en.encrypt();
            }
            o.setValue(value);
        }
    }
    
    public void store() throws Exception {
        if (this.file == null) {
            return;
        }
        if (this.document == null) {
            this.read();
        }
        final OutputStream ostr = new FileOutputStream(this.file);
        final ModelBuilder builder = new ModelBuilder();
        builder.writeModel(this.document, ostr, IXmlIO.Style.printable);
        ostr.close();
    }
    
    public IModelObject getDocument() {
        return this.document;
    }
    
    public void addProperty(final String name, final String value) {
        System.setProperty(name, value);
        IModelObject context = this.document;
        final StringTokenizer st = new StringTokenizer(name, ".");
        while (st.hasMoreTokens()) {
            final String t = st.nextToken();
            if (t.equals(Namespaces.cnns.getUnqualifiedName(context.getType()))) {
                continue;
            }
            final IModelObject child = context = context.getCreateChild(Namespaces.cnns.getQualifiedName(t));
        }
        context.setValue(value);
    }
    
    protected List<String> getRequiredProperties() {
        final List<String> list = new ArrayList<String>();
        String[] required;
        for (int length = (required = Bootstrap.required).length, i = 0; i < length; ++i) {
            final String p = required[i];
            list.add(p);
        }
        return list;
    }
    
    private void setProperties() throws Exception {
        final Encrypted en = new Encrypted();
        final IExpression path = XPath.createExpression("//*[not(boolean(*))]");
        final List<IModelObject> list = path.query(this.document, null);
        for (final IModelObject o : list) {
            final String property = this.getPath(o);
            String value = Xlate.get(o, "").trim();
            if (this.encrypted(o)) {
                en.setContent(value);
                value = en.decrypt();
                System.setProperty(String.valueOf(property) + ".encrypted", "true");
            }
            System.setProperty(property, value);
        }
    }
    
    private boolean encrypted(final IModelObject e) {
        final String encrypted = Xlate.get(e, "encrypted", (String)null);
        return encrypted != null && encrypted.equals("true");
    }
    
    private String getPath(IModelObject e) {
        final Stack<String> stack = new Stack<String>();
        stack.push(Namespaces.cnns.getUnqualifiedName(e.getType()));
        while (true) {
            final IModelObject p = e.getParent();
            if (p == null) {
                break;
            }
            stack.push(".");
            stack.push(Namespaces.cnns.getUnqualifiedName(p.getType()));
            e = p;
        }
        final StringBuilder sb = new StringBuilder();
        while (stack.size() > 0) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
    
    private void validate() throws Exception {
        for (final String p : this.getRequiredProperties()) {
            final String value = System.getProperty(p);
            if (value == null || value.length() == 0) {
                throw new IllegalArgumentException("Required (" + p + ") not defined");
            }
        }
    }
    
    private void netInit() throws Exception {
        final boolean proxyDisabled = Boolean.parseBoolean(System.getProperty("cornerstone.net.proxy.disabled"));
        if (proxyDisabled) {
            java.net.ProxySelector.setDefault(new ProxySelector());
        }
    }
    
    private void readSystemProperties() throws Exception {
    }
    
    private void read() throws Exception {
        final String key = "cornerstone.bootstrap";
        final String fname = System.getProperty(key);
        System.out.println("Reading bootstrap: " + key + "=" + fname);
        this.file = new File(fname);
        final ModelBuilder builder = new ModelBuilder();
        if (this.validate) {
            builder.setValidation(ModelBuilder.Validation.fail);
        }
        this.document = builder.buildModel(new FileInputStream(this.file));
    }
}
