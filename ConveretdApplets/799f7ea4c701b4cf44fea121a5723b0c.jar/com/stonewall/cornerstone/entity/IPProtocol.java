// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import org.xmodel.Element;
import java.util.Iterator;
import java.util.List;
import java.net.URL;
import org.xmodel.Xlate;
import com.stonewall.cornerstone.utility.ModelBuilder;
import java.util.HashMap;
import java.util.Map;
import org.xmodel.log.Log;
import org.xmodel.IModelObject;

public class IPProtocol
{
    private IModelObject root;
    public static IPProtocol Any;
    protected static final Log log;
    private static Map<Integer, IPProtocol> protocols;
    
    static {
        IPProtocol.Any = new IPProtocol(0, "Any");
        log = Log.getLog(IPProtocol.class);
        IPProtocol.protocols = new HashMap<Integer, IPProtocol>();
        try {
            final URL url = ClassLoader.getSystemResource("db/ipConstants.xml");
            final ModelBuilder builder = new ModelBuilder();
            final IModelObject root = builder.buildModel(url.openStream());
            final List<IModelObject> elements = root.getFirstChild("protocols").getChildren("protocol");
            for (final IModelObject e : elements) {
                IPProtocol.protocols.put(Xlate.get(e, "id", 0), new IPProtocol(e));
            }
        }
        catch (Exception e2) {
            IPProtocol.log.error("Problem creating protocol", e2);
        }
    }
    
    public static IPProtocol get(final int num) {
        return IPProtocol.protocols.get(num);
    }
    
    public static IPProtocol get(final String name) {
        for (final IPProtocol p : IPProtocol.protocols.values()) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }
    
    public IPProtocol(final int number, final String name) {
        (this.root = new Element("en:protocol")).setValue(name);
        this.root.setAttribute("id", String.valueOf(number));
    }
    
    public IPProtocol(final IModelObject e) {
        this.root = e;
    }
    
    public String getName() {
        return Xlate.get(this.root, (String)null);
    }
    
    public int getNumber() {
        return Xlate.get(this.root, "id", 0);
    }
    
    public boolean isAny() {
        return this.getNumber() == 0;
    }
}
