// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import com.stonewall.cornerstone.utility.Namespaces;
import org.xmodel.Xlate;
import org.xmodel.Element;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.xpath.XPath;
import com.stonewall.cornerstone.db.Predefined;
import java.util.HashMap;
import java.util.Map;
import org.xmodel.log.Log;
import org.xmodel.IModelObject;

public class Protocol
{
    private IModelObject root;
    protected static final Log log;
    private static Map<Type, IPService> services;
    
    static {
        log = Log.getLog(Protocol.class);
        Protocol.services = new HashMap<Type, IPService>();
        try {
            final IModelObject root = Predefined.load(EntityFactory.EntityType.ipService);
            Type[] values;
            for (int length = (values = Type.values()).length, i = 0; i < length; ++i) {
                final Type type = values[i];
                final IExpression path = XPath.createExpression("./en:ipService[en:name=$name]");
                path.setVariable("name", type.serviceName());
                final IModelObject e = path.queryFirst(root);
                if (e != null) {
                    final IPService svc = new IPService(e);
                    Protocol.services.put(type, svc);
                }
            }
        }
        catch (Exception e2) {
            Protocol.log.error("Problem creating services", e2);
        }
    }
    
    public Protocol(final IModelObject e) {
        this.root = e;
    }
    
    public Protocol(final Type type) {
        this.root = new Element(type.getQualifiedName());
    }
    
    public Type getType() {
        return Type.valueOf(this.root);
    }
    
    public String getUsername() {
        return Xlate.get(this.root.getFirstChild("en:username"), (String)null);
    }
    
    public void setUsername(final String value) {
        final IModelObject pass = new Element("en:username");
        pass.setValue(value);
        pass.setAttribute("encrypted", "false");
        this.root.addChild(pass);
    }
    
    public String getPassword() {
        return Xlate.get(this.root.getFirstChild("en:password"), (String)null);
    }
    
    public void setPassword(final String value) {
        final IModelObject pass = new Element("en:password");
        pass.setValue(value);
        pass.setAttribute("encrypted", "false");
        this.root.addChild(pass);
    }
    
    public String getPublic() {
        return Xlate.get(this.root.getFirstChild("en:public"), (String)null);
    }
    
    public void setPublic(final String value) {
        this.root.getCreateChild("en:public").setValue(value);
    }
    
    public String getPrivate() {
        return Xlate.get(this.root.getFirstChild("en:private"), (String)null);
    }
    
    public void setPrivate(final String value) {
        this.root.getCreateChild("en:private").setValue(value);
    }
    
    public String getVersion() {
        return Xlate.get(this.root.getFirstChild("en:version"), (String)null);
    }
    
    public void setVersion(final String value) {
        this.root.getCreateChild("en:version").setValue(value);
    }
    
    public ProtocolServer getServer() {
        final IModelObject server = this.root.getFirstChild("en:protocolServer");
        if (server == null) {
            return null;
        }
        return new ProtocolServer(server);
    }
    
    public void setServer(final IModelObject value) {
        this.root.addChild(value);
    }
    
    public int getPort() {
        IModelObject port = this.root.getFirstChild("en:port");
        if (port == null) {
            final IPService svc = Protocol.services.get(this.getType());
            this.setPort(svc.getDestination().sp());
            port = this.root.getFirstChild("en:port");
        }
        return Xlate.get(port, 0);
    }
    
    public void setPort(final int value) {
        this.root.getCreateChild("en:port").setValue(String.valueOf(value));
    }
    
    public String getAdminPwd() {
        return Xlate.get(this.root.getFirstChild("en:adminPwd"), (String)null);
    }
    
    public void setAdminPwd(final String value) {
        final IModelObject pass = new Element("en:adminPwd");
        pass.setValue(value);
        pass.setAttribute("encrypted", "false");
        this.root.addChild(pass);
    }
    
    public IPService getService() {
        return Protocol.services.get(this.getType());
    }
    
    public IModelObject getRoot() {
        return this.root;
    }
    
    public enum Type
    {
        telnet("telnet", 0, "TELNET"), 
        ssh("ssh", 1, "SSH"), 
        http("http", 2, "HTTP"), 
        xmlrpc("xmlrpc", 3, "XML_RPC"), 
        cpmi("cpmi", 4, "JMS_SSL");
        
        private final String serviceName;
        
        private Type(final String s, final int n, final String serviceName) {
            this.serviceName = serviceName;
        }
        
        public String serviceName() {
            return this.serviceName;
        }
        
        public String getQualifiedName() {
            return Namespaces.enns.getQualifiedName(this.name());
        }
        
        public static Type valueOf(final IModelObject o) {
            final String tag = o.getType();
            return valueOf(Namespaces.enns.getUnqualifiedName(tag));
        }
    }
}
