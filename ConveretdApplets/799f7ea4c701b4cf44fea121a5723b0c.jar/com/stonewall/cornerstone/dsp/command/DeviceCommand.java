// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.command;

import org.xmodel.xml.IXmlIO;
import com.stonewall.cornerstone.utility.ModelBuilder;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.Context;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import org.xmodel.Element;
import org.xmodel.Xlate;
import org.xmodel.xpath.XPath;
import org.xmodel.log.Log;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.IModelObject;

public abstract class DeviceCommand
{
    protected IModelObject root;
    private static IExpression precedingExpr;
    public static final Log log;
    
    static {
        DeviceCommand.precedingExpr = XPath.createExpression("preceding-sibling::*");
        log = Log.getLog(DeviceCommand.class);
    }
    
    static DeviceCommand createCommand(final IModelObject o) {
        final Type type = Type.valueOf(Xlate.get(o, "type", ""));
        switch (type) {
            case cli: {
                return new CliCommand(o);
            }
            case jms: {
                return new JmsCommand(o);
            }
            case xmlrpc: {
                return new XmlrpcCommand(o);
            }
            case http: {
                return new HttpCommand(o);
            }
            default: {
                return null;
            }
        }
    }
    
    public DeviceCommand(final String tag, final Type type) {
        (this.root = new Element(tag)).setAttribute("class", this.getClass().getName());
        this.root.setAttribute("type", type.name());
        this.init();
    }
    
    public DeviceCommand(final IModelObject o) {
        this.root = o;
    }
    
    public Type getType() {
        return Type.valueOf(Xlate.get(this.root, "type", ""));
    }
    
    public void setParser(final String clazz) {
        final IModelObject o = new Element("en:parser");
        o.setValue(clazz);
        this.root.addChild(o);
    }
    
    public String getParser() {
        return Xlate.get(this.root.getFirstChild("en:parser"), (String)null);
    }
    
    public List<String> getExpect() {
        final List<String> expects = new ArrayList<String>();
        for (final IModelObject o : this.root.getFirstChild("en:expects").getChildren()) {
            expects.add(Xlate.get(o, (String)null));
        }
        return expects;
    }
    
    public void addExpect(final String s) {
        final IModelObject expects = this.root.getFirstChild("en:expects");
        final IModelObject expect = new Element("en:expect");
        expect.setValue(s);
        expects.addChild(expect);
    }
    
    public void setResult(final Result result) {
        final IModelObject o = this.root.getFirstChild("en:result");
        if (o != null) {
            this.root.removeChild(o);
        }
        this.root.addChild(result.getRoot());
    }
    
    public Result getResult() {
        return new Result(this.root.getFirstChild("en:result"));
    }
    
    public void setOnSuccess(final IModelObject action) {
        final IModelObject o = new Element("en:onSuccess");
        this.root.addChild(o);
        o.addChild(action);
    }
    
    public void setOnFailure(final IModelObject action) {
        final IModelObject o = new Element("en:onFailure");
        this.root.addChild(o);
        o.addChild(action);
    }
    
    public void setCacheName(final String name) {
        final IModelObject o = new Element("en:cacheName");
        this.root.addChild(o);
        o.setValue(name);
    }
    
    public IModelObject getCompleteAction() {
        final Result result = this.getResult();
        IModelObject a = null;
        if (result.succeeded()) {
            a = this.root.getFirstChild("en:onSuccess");
        }
        else {
            a = this.root.getFirstChild("en:onFailure");
        }
        if (a != null) {
            return a.getChild(0);
        }
        return null;
    }
    
    public void addFailurePattern(final String p) {
        final IModelObject patterns = this.root.getFirstChild("en:failurePatterns");
        final IModelObject pattern = new Element("en:pattern");
        pattern.setValue(p);
        patterns.addChild(pattern);
    }
    
    public void setResponse(String s) {
        s = this.strip(s);
        this.getResult().setAttachment(s);
        this.determineStatus();
    }
    
    public void setResponse(final IModelObject o) {
        this.getResult().setAttachment(o);
        this.determineStatus();
    }
    
    public void fail(final String s) {
        this.fail(Result.FailureType.execution, s);
    }
    
    public void fail(final Result.FailureType type, final String s) {
        this.getResult().setStatus(Result.Status.failed);
        this.getResult().setFailure(type, s);
    }
    
    public void succeed() {
        this.getResult().succeed();
    }
    
    public void setEnableExpression(final String expr) {
        IModelObject enable = this.root.getFirstChild("en:enable");
        if (enable == null) {
            enable = new Element("en:enable");
            this.root.addChild(enable);
        }
        enable.setValue(expr);
    }
    
    public boolean isEnabled() {
        final String enable = Xlate.get(this.root.getFirstChild("en:enable"), (String)null);
        if (enable != null) {
            final IExpression expr = XPath.createExpression(enable);
            return expr.evaluateBoolean(new Context(this.root));
        }
        final IModelObject preceding = DeviceCommand.precedingExpr.queryFirst(new Context(this.root));
        if (preceding == null) {
            return true;
        }
        final DeviceCommand command = createCommand(preceding);
        return command.getResult().succeeded();
    }
    
    protected abstract void determineStatus();
    
    private String strip(String s) {
        s = s.replace('\0', ' ');
        s = s.replace('\b', ' ');
        s = s.replace('\u0007', ' ');
        s = s.replace('\u0001', ' ');
        s = s.replace('\u0002', ' ');
        s = s.replace('\u0003', ' ');
        s = s.replace('\u001b', ' ');
        s = s.trim();
        return s;
    }
    
    protected void init() {
        this.root.addChild(new Element("en:expects"));
        this.root.addChild(new Element("en:failurePatterns"));
        this.setResult(new Result());
    }
    
    @Override
    public String toString() {
        final ModelBuilder builder = new ModelBuilder();
        return builder.writeModel(this.root, IXmlIO.Style.printable);
    }
    
    public IModelObject getRoot() {
        return this.root;
    }
    
    public enum Type
    {
        jms("jms", 0), 
        cli("cli", 1), 
        xmlrpc("xmlrpc", 2), 
        http("http", 3);
        
        private Type(final String s, final int n) {
        }
    }
}
