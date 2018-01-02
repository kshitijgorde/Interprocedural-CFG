// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import org.xmodel.xml.IXmlIO;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.URL;
import org.xmodel.IModelObject;
import org.xmodel.IModelObjectFactory;
import org.xmodel.ElementFactory;
import java.util.HashMap;
import org.xmodel.log.Log;
import java.util.Map;
import org.xmodel.xml.XmlIO;

public class ModelBuilder extends XmlIO
{
    private Validation validation;
    private long start;
    private static Map<String, String> schemas;
    static final Log log;
    
    static {
        (ModelBuilder.schemas = new HashMap<String, String>()).put("en", "http://www.stonewallnetworks.com/ns/entity http://schema.stonewallnetworks.com/ns/entity/entities.xsd");
        ModelBuilder.schemas.put("evt", "http://www.stonewallnetworks.com/ns/event http://schema.stonewallnetworks.com/ns/event/event.xsd");
        ModelBuilder.schemas.put("rmi", "http://www.stonewallnetworks.com/ns/rmi http://schema.stonewallnetworks.com/ns/rmi/rmi.xsd");
        ModelBuilder.schemas.put("rp", "http://www.stonewallnetworks.com/ns/report http://schema.stonewallnetworks.com/ns/report/reports.xsd");
        log = Log.getLog(ModelBuilder.class);
    }
    
    public ModelBuilder() {
        this.setFactory(new ElementFactory());
    }
    
    public ModelBuilder(final Validation validation) {
        this();
        this.validation = validation;
    }
    
    public void setValidation(final Validation validation) {
        this.validation = validation;
    }
    
    public IModelObject buildModel(final String content) throws Exception {
        this.start();
        final IModelObject o = this.read(content);
        this.validate(o);
        this.end();
        return o;
    }
    
    public IModelObject buildModel(final URL url) throws Exception {
        this.start();
        final IModelObject o = this.read(url);
        this.validate(o);
        this.end();
        return o;
    }
    
    public IModelObject buildModel(final InputStream content) throws Exception {
        this.start();
        final IModelObject o = this.read(content);
        this.validate(o);
        this.end();
        return o;
    }
    
    public void writeModel(final IModelObject model, final OutputStream out, final IXmlIO.Style style) {
        try {
            this.setOutputStyle(style);
            this.fixSchema(model);
            this.write(model, out);
        }
        catch (Exception e) {
            ModelBuilder.log.error(this, e);
        }
    }
    
    public String writeModel(final IModelObject model, final IXmlIO.Style style) {
        try {
            this.setOutputStyle(style);
            this.fixSchema(model);
            return this.write(model);
        }
        catch (Exception e) {
            ModelBuilder.log.error(this, e);
            return null;
        }
    }
    
    private void fixSchema(final IModelObject o) {
    }
    
    public void validate(final IModelObject o) throws Exception {
        final Validation validationType = this.getValidation();
        if (!validationType.equals(Validation.none)) {
            final boolean valid = true;
            if (!valid && validationType.equals(Validation.fail)) {
                o.setAttribute("valid", "false");
            }
        }
    }
    
    private void start() {
        this.start = System.currentTimeMillis();
    }
    
    private void end() {
        final long finish = System.currentTimeMillis();
        ModelBuilder.log.debug("ModelBuilder - finished without validation: " + (finish - this.start) + " (ms)");
    }
    
    private Validation getValidation() {
        if (this.validation == null) {
            this.validation = this.getDefaultValidation();
        }
        return this.validation;
    }
    
    private Validation getDefaultValidation() {
        return Validation.valueOf(System.getProperty("cornerstone.document.validation", "process"));
    }
    
    public enum Validation
    {
        none("none", 0), 
        process("process", 1), 
        fail("fail", 2);
        
        private Validation(final String s, final int n) {
        }
    }
}
