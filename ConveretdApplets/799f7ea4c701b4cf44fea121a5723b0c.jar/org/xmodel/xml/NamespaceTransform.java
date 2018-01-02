// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xml;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import org.xmodel.Xlate;
import org.xmodel.external.IExternalReference;
import org.xml.sax.Attributes;
import org.xmodel.ModelAlgorithms;
import org.xmodel.IModelObject;
import org.xmodel.ModelObjectFactory;
import java.util.HashMap;
import java.util.Map;
import org.xmodel.IModelObjectFactory;

public class NamespaceTransform implements IModelObjectFactory
{
    private Map<String, String> C;
    private IModelObjectFactory B;
    private StringBuilder A;
    
    public NamespaceTransform() {
        this.C = new HashMap<String, String>();
        this.B = new ModelObjectFactory();
        this.A = new StringBuilder();
    }
    
    public void setFactory(final IModelObjectFactory b) {
        this.B = b;
    }
    
    public void setDefaultNamespace(final String s) {
        this.C.put("", s);
    }
    
    public void addNamespace(final String s, final String s2) {
        this.C.put(s2, s);
    }
    
    @Override
    public IModelObject createClone(final IModelObject modelObject) {
        final String b = this.B(modelObject.getType(), modelObject);
        if (b == null) {
            throw new IllegalArgumentException("Object namespace not found: " + modelObject);
        }
        final IModelObject object = this.B.createObject(null, this.A(b, modelObject.getType()));
        ModelAlgorithms.copyAttributes(modelObject, object);
        return object;
    }
    
    @Override
    public IModelObject createObject(final IModelObject modelObject, String a) {
        final String b = this.B(a, modelObject);
        if (b == null) {
            throw new IllegalArgumentException("Object namespace not found: " + modelObject);
        }
        a = this.A(b, a);
        return this.B.createObject(modelObject, a);
    }
    
    @Override
    public IModelObject createObject(final IModelObject modelObject, final Attributes attributes, String a) {
        String s = this.B(a, modelObject);
        if (s == null) {
            s = this.B(a, attributes);
        }
        if (s == null) {
            throw new IllegalArgumentException("Object namespace not found: " + modelObject);
        }
        a = this.A(s, a);
        return this.B.createObject(modelObject, a);
    }
    
    @Override
    public IExternalReference createExternalObject(final IModelObject modelObject, final String s) {
        throw new UnsupportedOperationException();
    }
    
    private String B(final String s, final Attributes attributes) {
        final String a = this.A(this.A(s), attributes);
        return (a != null) ? this.C.get(a) : null;
    }
    
    private String B(final String s, final IModelObject modelObject) {
        final String a = this.A(this.A(s), modelObject);
        return (a != null) ? this.C.get(a) : null;
    }
    
    private String A(final String s) {
        final int index = s.indexOf(":");
        return (index >= 0) ? s.substring(0, index) : "";
    }
    
    private String A(final String s, final Attributes attributes) {
        for (int i = 0; i < attributes.getLength(); ++i) {
            final String qName = attributes.getQName(i);
            if (qName.startsWith("xmlns") && ((qName.length() > 6) ? qName.substring(6) : "").equals(s)) {
                return attributes.getValue(i);
            }
        }
        return null;
    }
    
    private String A(final String s, final IModelObject modelObject) {
        for (IModelObject parent = modelObject; parent != null; parent = parent.getParent()) {
            for (final String s2 : parent.getAttributeNames()) {
                if (s2.startsWith("xmlns") && ((s2.length() > 6) ? s2.substring(6) : "").equals(s)) {
                    return Xlate.get(parent, s2, "");
                }
            }
        }
        return null;
    }
    
    private String A(final String s, final String s2) {
        this.A.setLength(0);
        if (s.length() > 0) {
            this.A.append(s);
            this.A.append(':');
        }
        final int index = s2.indexOf(":");
        this.A.append((index >= 0) ? s2.substring(index + 1) : s2);
        return this.A.toString();
    }
    
    public static void main(final String[] array) throws Exception {
        final String s = "<xx:root xmlns:xx='http://www.xx.com'>  <xx:tag>    <yy:tag xmlns:yy='http://www.yy.com'>      <yy:tag/>    </yy:tag>  </xx:tag></xx:root>";
        final NamespaceTransform factory = new NamespaceTransform();
        factory.addNamespace("aa", "http://www.xx.com");
        factory.addNamespace("bb", "http://www.yy.com");
        final XmlIO xmlIO = new XmlIO();
        xmlIO.setFactory(factory);
        System.out.println(xmlIO.write(xmlIO.read(s)));
    }
}
