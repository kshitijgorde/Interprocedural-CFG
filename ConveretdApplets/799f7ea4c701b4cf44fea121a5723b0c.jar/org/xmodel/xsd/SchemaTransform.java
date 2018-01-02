// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xsd;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmodel.Reference;
import org.xmodel.ModelAlgorithms;
import java.util.List;
import org.xmodel.Xlate;
import org.xmodel.ModelObject;
import java.util.HashMap;
import org.xmodel.xpath.XPath;
import org.xmodel.IModelObject;
import java.util.Map;
import org.xmodel.xpath.expression.IExpression;

public class SchemaTransform
{
    private IExpression J;
    private IExpression F;
    private IExpression L;
    private IExpression G;
    private IExpression K;
    private IExpression B;
    private IExpression C;
    private Map<String, IModelObject> A;
    private Map<String, IModelObject> H;
    private Map<String, IModelObject> E;
    private Map<String, IModelObject> I;
    private boolean D;
    
    public SchemaTransform() {
        this(false);
    }
    
    public SchemaTransform(final boolean d) {
        this.J = XPath.createExpression("xs:complexType[ @name = $name]");
        this.F = XPath.createExpression("xs:simpleType[ @name = $name]");
        this.L = XPath.createExpression("xs:group[ @name = $ref]");
        this.G = XPath.createExpression("xs:sequence | xs:all | xs:choice | xs:group");
        this.K = XPath.createExpression("ancestor::xs:element/@default");
        this.B = XPath.createExpression(".//reference:type");
        this.C = XPath.createExpression(".//reference:element");
        this.A = new HashMap<String, IModelObject>();
        this.H = new HashMap<String, IModelObject>();
        this.E = new HashMap<String, IModelObject>();
        this.I = new HashMap<String, IModelObject>();
        this.D = d;
        this.A();
    }
    
    public IModelObject transform(final IModelObject modelObject) throws SchemaException {
        final ModelObject modelObject2 = new ModelObject("schema");
        final Iterator<IModelObject> iterator = this.C(modelObject).iterator();
        while (iterator.hasNext()) {
            modelObject2.addChild(iterator.next());
        }
        final HashMap<String, IModelObject> hashMap = (HashMap<String, IModelObject>)new HashMap<Object, IModelObject>();
        for (final IModelObject modelObject3 : this.B(modelObject)) {
            if (!modelObject3.getType().startsWith("reference:type")) {
                hashMap.put(Xlate.get(modelObject3, "name", ""), modelObject3);
            }
            modelObject2.addChild(modelObject3);
        }
        for (final IModelObject modelObject4 : this.B.query(modelObject2, null)) {
            final IModelObject parent = modelObject4.getParent();
            if (parent != null) {
                final String value = Xlate.get(modelObject4, "type", "");
                IModelObject modelObject5 = this.E.get(value);
                if (modelObject5 == null) {
                    modelObject5 = this.E.get(value.replaceAll("^[^:]+:", ""));
                }
                final IModelObject a = this.A(modelObject5);
                ModelAlgorithms.copyAttributes(modelObject4, a);
                modelObject4.removeFromParent();
                parent.addChild(a);
                hashMap.put(Xlate.get(a, "name", ""), a);
            }
        }
        for (final IModelObject modelObject6 : this.C.query(modelObject2, null)) {
            final IModelObject parent2 = modelObject6.getParent();
            if (parent2 != null) {
                final String value2 = Xlate.get(modelObject6, "name", "");
                final IModelObject modelObject7 = hashMap.get(value2);
                if (modelObject7 == null) {
                    throw new SchemaException("Unable to resolve global element: " + value2);
                }
                modelObject6.removeFromParent();
                parent2.addChild(new Reference(modelObject7));
            }
        }
        return modelObject2;
    }
    
    private IModelObject D(final IModelObject modelObject, final IModelObject modelObject2) throws SchemaException {
        final IModelObject firstChild = modelObject2.getFirstChild("xs:restriction");
        final IModelObject a = this.A(modelObject, Xlate.get(firstChild, "base", ""));
        IModelObject cloneTree;
        if (a == null) {
            cloneTree = new ModelObject("type");
        }
        else {
            cloneTree = a.cloneTree();
        }
        this.C(modelObject, cloneTree, firstChild);
        return cloneTree;
    }
    
    private List<IModelObject> C(final IModelObject modelObject) throws SchemaException {
        final ArrayList<IModelObject> list = new ArrayList<IModelObject>();
        final Iterator<IModelObject> iterator = modelObject.getChildren("xs:complexType").iterator();
        while (iterator.hasNext()) {
            list.add(this.C(modelObject, iterator.next()));
        }
        return list;
    }
    
    private IModelObject C(final IModelObject modelObject, final IModelObject modelObject2) throws SchemaException {
        final String value = Xlate.get(modelObject2, "name", (String)null);
        if (value != null) {
            final IModelObject modelObject3 = this.E.get(value);
            if (modelObject3 != null) {
                return modelObject3;
            }
        }
        final ModelObject modelObject4 = new ModelObject("element");
        modelObject4.setAttribute("type", value);
        if (value != null) {
            this.E.put(value, modelObject4);
        }
        this.E(modelObject, modelObject4, modelObject2);
        final IModelObject firstChild = modelObject2.getFirstChild("xs:simpleContent");
        if (firstChild != null) {
            this.F(modelObject, modelObject4, firstChild);
        }
        final IModelObject firstChild2 = modelObject2.getFirstChild("xs:complexContent");
        if (firstChild2 != null) {
            this.F(modelObject, modelObject4, firstChild2);
        }
        return modelObject4;
    }
    
    private IModelObject B(final IModelObject modelObject, final IModelObject modelObject2, final IModelObject modelObject3) throws SchemaException {
        if (modelObject2.isType("xs:group")) {
            return this.B(modelObject, this.E(modelObject, modelObject2), modelObject3);
        }
        final IModelObject createChild = modelObject3.getCreateChild("children");
        ModelObject modelObject4;
        if (modelObject2.isType("xs:sequence")) {
            modelObject4 = new ModelObject(this.D ? "set" : "list");
        }
        else if (modelObject2.isType("xs:choice")) {
            modelObject4 = new ModelObject("choice");
        }
        else if (modelObject2.isType("xs:all")) {
            modelObject4 = new ModelObject("set");
        }
        else if (modelObject2.isType("xs:any")) {
            modelObject4 = new ModelObject("any");
        }
        else {
            if (modelObject2.isType("xs:annotation")) {
                return null;
            }
            if (modelObject2.isType("xs:documentation")) {
                return null;
            }
            throw new SchemaException("Element is not a sac: " + modelObject2);
        }
        final String value = Xlate.get(modelObject2, "minOccurs", (String)null);
        if (value != null) {
            modelObject4.setAttribute("min", value);
        }
        final String value2 = Xlate.get(modelObject2, "maxOccurs", (String)null);
        if (value2 != null) {
            modelObject4.setAttribute("max", value2);
        }
        for (final IModelObject modelObject5 : modelObject2.getChildren()) {
            if (modelObject5.isType("xs:element")) {
                createChild.addChild(this.A(modelObject, modelObject5));
                String value3 = Xlate.get(modelObject5, "name", (String)null);
                if (value3 == null) {
                    value3 = Xlate.get(modelObject5, "ref", (String)null);
                }
                final ModelObject modelObject6 = new ModelObject("child");
                modelObject6.setValue(value3);
                modelObject4.addChild(modelObject6);
                final String value4 = Xlate.get(modelObject5, "minOccurs", (String)null);
                if (value4 != null) {
                    modelObject6.setAttribute("min", value4);
                }
                final String value5 = Xlate.get(modelObject5, "maxOccurs", (String)null);
                if (value5 == null) {
                    continue;
                }
                modelObject6.setAttribute("max", value5);
            }
            else {
                final IModelObject b = this.B(modelObject, modelObject5, modelObject3);
                if (b == null) {
                    continue;
                }
                if (b.getType().equals(modelObject4.getType())) {
                    IModelObject[] array;
                    for (int length = (array = b.getChildren().toArray(new IModelObject[0])).length, i = 0; i < length; ++i) {
                        modelObject4.addChild(array[i]);
                    }
                }
                else {
                    modelObject4.addChild(b);
                }
            }
        }
        return modelObject4;
    }
    
    private List<IModelObject> B(final IModelObject modelObject) throws SchemaException {
        final ArrayList<IModelObject> list = new ArrayList<IModelObject>();
        final Iterator<IModelObject> iterator = modelObject.getChildren("xs:element").iterator();
        while (iterator.hasNext()) {
            final IModelObject a = this.A(modelObject, iterator.next());
            Xlate.set(a, "global", true);
            list.add(a);
        }
        return list;
    }
    
    private IModelObject A(final IModelObject modelObject, final IModelObject modelObject2) throws SchemaException {
        final String value = Xlate.get(modelObject2, "ref", (String)null);
        if (value != null) {
            final ModelObject modelObject3 = new ModelObject("reference:element");
            modelObject3.setAttribute("name", value);
            return modelObject3;
        }
        final String value2 = Xlate.get(modelObject2, "name", (String)null);
        final String value3 = Xlate.get(modelObject2, "type", (String)null);
        if (value3 != null) {
            if (this.B(modelObject, value3) != null) {
                final ModelObject modelObject4 = new ModelObject("reference:type");
                modelObject4.setAttribute("name", value2);
                modelObject4.setAttribute("type", value3);
                return modelObject4;
            }
            final IModelObject a = this.A(modelObject, value3);
            if (a == null) {
                throw new SchemaException("Unable to resolve type: " + value3);
            }
            final ModelObject modelObject5 = new ModelObject("element");
            modelObject5.setAttribute("name", value2);
            modelObject5.getCreateChild("value").addChild(a.cloneTree());
            final String value4 = Xlate.get(modelObject2, "default", (String)null);
            if (value4 != null) {
                modelObject5.getCreateChild("default").setValue(value4);
            }
            return modelObject5;
        }
        else {
            final IModelObject firstChild = modelObject2.getFirstChild("xs:simpleType");
            if (firstChild != null) {
                final IModelObject d = this.D(modelObject, firstChild);
                final ModelObject modelObject6 = new ModelObject("element");
                modelObject6.setAttribute("name", value2);
                final IModelObject createChild = modelObject6.getCreateChild("value");
                createChild.addChild(d);
                final String value5 = Xlate.get(modelObject2, "default", (String)null);
                if (value5 != null) {
                    createChild.getCreateChild("default").setValue(value5);
                }
                return modelObject6;
            }
            final IModelObject firstChild2 = modelObject2.getFirstChild("xs:complexType");
            if (firstChild2 != null) {
                final IModelObject c = this.C(modelObject, firstChild2);
                c.setAttribute("name", value2);
                final String value6 = Xlate.get(modelObject2, "default", (String)null);
                if (value6 != null) {
                    final IModelObject firstChild3 = c.getFirstChild("value");
                    if (firstChild3 == null) {
                        throw new SchemaException("Attempt to override default value when value is not defined: " + modelObject2);
                    }
                    firstChild3.getCreateChild("default").setValue(value6);
                }
                return c;
            }
            final ModelObject modelObject7 = new ModelObject("element");
            modelObject7.setAttribute("name", value2);
            return modelObject7;
        }
    }
    
    private IModelObject A(final IModelObject modelObject) {
        final ModelObject modelObject2 = new ModelObject("element");
        final Iterator<IModelObject> iterator = modelObject.getChildren().iterator();
        while (iterator.hasNext()) {
            modelObject2.addChild(new Reference(iterator.next()));
        }
        return modelObject2;
    }
    
    private IModelObject B(final IModelObject modelObject, final IModelObject modelObject2) throws SchemaException {
        final String value = Xlate.get(modelObject2, "ref", (String)null);
        if (value == null) {
            final ModelObject modelObject3 = new ModelObject("attribute");
            modelObject3.setAttribute("name", Xlate.get(modelObject2, "name", (String)null));
            modelObject3.setAttribute("default", Xlate.get(modelObject2, "default", (String)null));
            modelObject3.setAttribute("use", Xlate.get(modelObject2, "use", "optional"));
            final String value2 = Xlate.get(modelObject2, "type", (String)null);
            if (value2 != null) {
                modelObject3.addChild(this.A(modelObject, value2).cloneTree());
                return modelObject3;
            }
            final IModelObject firstChild = modelObject2.getFirstChild("xs:simpleType");
            if (firstChild != null) {
                modelObject3.addChild(this.D(modelObject, firstChild));
            }
            return modelObject3;
        }
        else {
            final IModelObject modelObject4 = this.H.get(value);
            if (modelObject4 == null) {
                throw new SchemaException("Global attribute not found: " + value);
            }
            return modelObject4.cloneTree();
        }
    }
    
    private IModelObject E(final IModelObject modelObject, final IModelObject modelObject2) throws SchemaException {
        final String value = Xlate.get(modelObject2, "name", (String)null);
        if (value != null) {
            return this.G.queryFirst(modelObject2);
        }
        final String value2 = Xlate.get(modelObject2, "ref", (String)null);
        if (value2 == null) {
            throw new SchemaException("Group does not define a name or reference another group: " + modelObject2);
        }
        this.L.setVariable("ref", value2);
        final IModelObject queryFirst = this.L.queryFirst(modelObject);
        if (queryFirst == null) {
            throw new SchemaException("Global group is undefined: " + value);
        }
        return this.E(modelObject, queryFirst);
    }
    
    private IModelObject A(final IModelObject modelObject, final String s) throws SchemaException {
        final IModelObject modelObject2 = this.I.get(s);
        if (modelObject2 != null) {
            return modelObject2;
        }
        IModelObject d = this.A.get(s);
        if (d != null) {
            return d;
        }
        this.F.setVariable("name", s);
        final IModelObject queryFirst = this.F.queryFirst(modelObject);
        if (queryFirst != null) {
            d = this.D(modelObject, queryFirst);
            d.setAttribute("name", s);
            this.A.put(s, d);
        }
        return d;
    }
    
    private Reference B(final IModelObject modelObject, final String s) throws SchemaException {
        final IModelObject modelObject2 = this.E.get(s);
        if (modelObject2 != null) {
            return new Reference(modelObject2);
        }
        final String replaceAll = s.replaceAll("^[^:]+:", "");
        final IModelObject modelObject3 = this.E.get(replaceAll);
        if (modelObject3 != null) {
            return new Reference(modelObject3);
        }
        this.J.setVariable("name", s);
        IModelObject modelObject4 = this.J.queryFirst(modelObject);
        if (modelObject4 == null) {
            this.J.setVariable("name", replaceAll);
            modelObject4 = this.J.queryFirst(modelObject);
        }
        if (modelObject4 != null) {
            return new Reference(this.C(modelObject, modelObject4));
        }
        return null;
    }
    
    private void F(final IModelObject modelObject, final IModelObject modelObject2, final IModelObject modelObject3) throws SchemaException {
        final IModelObject firstChild = modelObject3.getFirstChild("xs:restriction");
        if (firstChild != null) {
            this.D(modelObject, modelObject2, firstChild);
        }
        final IModelObject firstChild2 = modelObject3.getFirstChild("xs:extension");
        if (firstChild2 != null) {
            this.A(modelObject, modelObject2, firstChild2);
        }
    }
    
    private void D(final IModelObject modelObject, final IModelObject modelObject2, final IModelObject modelObject3) throws SchemaException {
        final String value = Xlate.get(modelObject3, "base", "");
        final IModelObject a = this.A(modelObject, value);
        if (a != null) {
            final IModelObject createChild = modelObject2.getCreateChild("value").getCreateChild("type");
            this.C(modelObject, a, modelObject3);
            createChild.addChild(a.cloneTree());
        }
        else {
            final Reference b = this.B(modelObject, value);
            if (b != null) {
                final IModelObject firstChild = b.getFirstChild("value");
                if (firstChild != null) {
                    modelObject2.addChild(new Reference(firstChild));
                }
                final IModelObject firstChild2 = b.getFirstChild("attributes");
                if (firstChild2 != null) {
                    modelObject2.addChild(new Reference(firstChild2));
                }
            }
            else {
                this.C(modelObject, modelObject2.getCreateChild("value").getCreateChild("type"), modelObject3);
            }
        }
    }
    
    private void C(final IModelObject modelObject, final IModelObject modelObject2, final IModelObject modelObject3) throws SchemaException {
        final String value = Xlate.get(modelObject3, "base", "");
        if (this.B(value)) {
            final IModelObject createChild = modelObject2.getCreateChild("string");
            if (modelObject3 == null) {
                return;
            }
            final String value2 = Xlate.get(modelObject3.getFirstChild("xs:minLength"), "value", (String)null);
            if (value2 != null) {
                createChild.getCreateChild("min").setValue(value2);
            }
            final String value3 = Xlate.get(modelObject3.getFirstChild("xs:maxLength"), "value", (String)null);
            if (value3 != null) {
                createChild.getCreateChild("max").setValue(value3);
            }
            final String value4 = Xlate.get(modelObject3.getFirstChild("xs:length"), "value", (String)null);
            if (value4 != null) {
                createChild.getCreateChild("min").setValue(value4);
                createChild.getCreateChild("max").setValue(value4);
            }
        }
        else if (this.C(value)) {
            final IModelObject createChild2 = modelObject2.getCreateChild("number");
            if (modelObject3 == null) {
                return;
            }
            final String value5 = Xlate.get(modelObject3.getFirstChild("xs:minInclusive"), "value", (String)null);
            if (value5 != null) {
                createChild2.getCreateChild("min").setValue(value5);
            }
            final String value6 = Xlate.get(modelObject3.getFirstChild("xs:minExclusive"), "value", (String)null);
            if (value6 != null) {
                final IModelObject createChild3 = createChild2.getCreateChild("min");
                createChild3.setAttribute("exclusive", "true");
                createChild3.setValue(value6);
            }
            final String value7 = Xlate.get(modelObject3.getFirstChild("xs:maxInclusive"), "value", (String)null);
            if (value7 != null) {
                createChild2.getCreateChild("max").setValue(value7);
            }
            final String value8 = Xlate.get(modelObject3.getFirstChild("xs:maxExclusive"), "value", (String)null);
            if (value8 != null) {
                final IModelObject createChild4 = createChild2.getCreateChild("max");
                createChild4.setAttribute("exclusive", "true");
                createChild4.setValue(value8);
            }
            final String value9 = Xlate.get(modelObject3.getFirstChild("xs:totalDigits"), "value", (String)null);
            if (value9 != null) {
                createChild2.getCreateChild("integer").setValue(value9);
            }
        }
        else if (this.A(value)) {
            modelObject2.getCreateChild("boolean");
            if (modelObject3 == null) {
                return;
            }
        }
        final List<IModelObject> children = modelObject3.getChildren("xs:enumeration");
        if (children.size() > 0) {
            final IModelObject createChild5 = modelObject2.getCreateChild("enum");
            for (final IModelObject modelObject4 : children) {
                final ModelObject modelObject5 = new ModelObject("value");
                modelObject5.setValue(Xlate.get(modelObject4, "value", ""));
                createChild5.addChild(modelObject5);
            }
        }
        final IModelObject firstChild = modelObject3.getFirstChild("xs:pattern");
        if (firstChild != null) {
            modelObject2.getCreateChild("pattern").setValue(Xlate.get(firstChild, "value", ""));
        }
    }
    
    private void A(final IModelObject modelObject, final IModelObject modelObject2, final IModelObject modelObject3) throws SchemaException {
        final String value = Xlate.get(modelObject3, "base", "");
        final IModelObject a = this.A(modelObject, value);
        if (a != null) {
            final IModelObject createChild = modelObject2.getCreateChild("value");
            createChild.addChild(a.cloneTree());
            final String value2 = Xlate.get(this.K.queryFirst(modelObject3), (String)null);
            if (value2 != null) {
                Xlate.set(createChild.getCreateChild("default"), value2);
            }
            this.E(modelObject, modelObject2, modelObject3);
        }
        else {
            final Reference b = this.B(modelObject, value);
            if (b != null) {
                final IModelObject firstChild = b.getFirstChild("attributes");
                if (firstChild != null) {
                    modelObject2.addChild(firstChild.cloneTree());
                }
                final IModelObject firstChild2 = b.getFirstChild("children");
                if (firstChild2 != null) {
                    modelObject2.addChild(firstChild2.cloneTree());
                }
                this.E(modelObject, modelObject2, modelObject3);
                final IModelObject firstChild3 = b.getFirstChild("constraint");
                if (firstChild3 != null) {
                    final IModelObject cloneTree = firstChild3.cloneTree();
                    final IModelObject child = cloneTree.getChild(0);
                    final IModelObject firstChild4 = modelObject2.getFirstChild("constraint");
                    if (firstChild4 != null) {
                        final IModelObject child2 = firstChild4.getChild(0);
                        if (!child2.getType().equals(child.getType())) {
                            throw new SchemaException("Extension model must match base model: extension=" + modelObject2 + ", base=" + value);
                        }
                        final IModelObject[] array = child.getChildren().toArray(new IModelObject[0]);
                        for (int i = 0; i < array.length; ++i) {
                            child2.addChild(array[i], i);
                        }
                    }
                    else {
                        modelObject2.addChild(cloneTree);
                    }
                }
                else if (modelObject2.getFirstChild("constraint") != null) {
                    modelObject2.getCreateChild("constraint").addChild(modelObject2.getFirstChild("constraint").getChild(0));
                }
            }
        }
    }
    
    private void E(final IModelObject modelObject, final IModelObject modelObject2, final IModelObject modelObject3) throws SchemaException {
        final IModelObject queryFirst = this.G.queryFirst(modelObject3);
        if (queryFirst != null) {
            final IModelObject b = this.B(modelObject, queryFirst, modelObject2);
            if (b != null) {
                modelObject2.getCreateChild("constraint").addChild(b);
            }
        }
        IModelObject createChild = null;
        for (final IModelObject modelObject4 : modelObject3.getChildren("xs:attribute")) {
            if (createChild == null) {
                createChild = modelObject2.getCreateChild("attributes");
            }
            final IModelObject b2 = this.B(modelObject, modelObject4);
            if (b2 != null) {
                createChild.addChild(b2);
            }
        }
    }
    
    private boolean B(final String s) {
        return s.equals("xs:string") || s.equals("xs:token") || s.equals("xs:normalizedString");
    }
    
    private boolean C(final String s) {
        return s.equals("xs:int") || s.equals("xs:long") || s.equals("xs:short") || s.contains("nteger") || s.contains("decimal") || s.startsWith("unsigned");
    }
    
    private boolean A(final String s) {
        return s.equals("xs:boolean");
    }
    
    private void A() {
        final ModelObject modelObject = new ModelObject("type");
        modelObject.getCreateChild("string");
        this.I.put("xs:string", modelObject);
        this.I.put("xs:token", modelObject);
        this.I.put("xs:normalizedString", modelObject);
        this.I.put("xs:anyURI", modelObject);
        this.I.put("xs:base64Binary", modelObject);
        this.I.put("xs:hexBinary", modelObject);
        final ModelObject modelObject2 = new ModelObject("type");
        modelObject2.getCreateChild("boolean");
        this.I.put("xs:boolean", modelObject2);
        final ModelObject modelObject3 = new ModelObject("type");
        final IModelObject createChild = modelObject3.getCreateChild("number");
        Xlate.set(createChild.getCreateChild("min"), (byte)(-128));
        Xlate.set(createChild.getCreateChild("max"), (byte)127);
        this.I.put("xs:byte", modelObject3);
        final ModelObject modelObject4 = new ModelObject("type");
        final IModelObject createChild2 = modelObject4.getCreateChild("number");
        Xlate.set(createChild2.getCreateChild("min"), (short)(-32768));
        Xlate.set(createChild2.getCreateChild("max"), (short)32767);
        this.I.put("xs:short", modelObject4);
        final ModelObject modelObject5 = new ModelObject("type");
        final IModelObject createChild3 = modelObject5.getCreateChild("number");
        Xlate.set(createChild3.getCreateChild("min"), Integer.MIN_VALUE);
        Xlate.set(createChild3.getCreateChild("max"), Integer.MAX_VALUE);
        this.I.put("xs:int", modelObject5);
        final ModelObject modelObject6 = new ModelObject("type");
        modelObject6.getCreateChild("number");
        this.I.put("xs:integer", modelObject6);
        final ModelObject modelObject7 = new ModelObject("type");
        final IModelObject createChild4 = modelObject7.getCreateChild("number");
        Xlate.set(createChild4.getCreateChild("min"), Long.MIN_VALUE);
        Xlate.set(createChild4.getCreateChild("max"), Long.MAX_VALUE);
        this.I.put("xs:long", modelObject7);
        final ModelObject modelObject8 = new ModelObject("type");
        Xlate.set(modelObject8.getCreateChild("number").getCreateChild("max"), -1);
        this.I.put("xs:negativeInteger", modelObject8);
        final ModelObject modelObject9 = new ModelObject("type");
        Xlate.set(modelObject9.getCreateChild("number").getCreateChild("min"), 1);
        this.I.put("xs:positiveInteger", modelObject9);
        final ModelObject modelObject10 = new ModelObject("type");
        Xlate.set(modelObject10.getCreateChild("number").getCreateChild("min"), 0);
        this.I.put("xs:nonNegativeInteger", modelObject10);
        final ModelObject modelObject11 = new ModelObject("type");
        Xlate.set(modelObject11.getCreateChild("number").getCreateChild("max"), 0);
        this.I.put("xs:nonPositiveInteger", modelObject11);
        final ModelObject modelObject12 = new ModelObject("type");
        final IModelObject createChild5 = modelObject12.getCreateChild("number");
        final BigInteger value = BigInteger.valueOf(2L);
        final BigInteger multiply = BigInteger.valueOf(Long.MAX_VALUE).multiply(value);
        createChild5.getCreateChild("min").setValue(BigInteger.ZERO);
        createChild5.getCreateChild("max").setValue(multiply);
        this.I.put("xs:unsignedLong", modelObject12);
        final ModelObject modelObject13 = new ModelObject("type");
        final IModelObject createChild6 = modelObject13.getCreateChild("number");
        final BigInteger multiply2 = BigInteger.valueOf(2147483647L).multiply(value);
        createChild6.getCreateChild("min").setValue(BigInteger.ZERO);
        createChild6.getCreateChild("max").setValue(multiply2);
        this.I.put("xs:unsignedLong", modelObject13);
        final ModelObject modelObject14 = new ModelObject("type");
        final IModelObject createChild7 = modelObject14.getCreateChild("number");
        final BigInteger multiply3 = BigInteger.valueOf(32767L).multiply(value);
        createChild7.getCreateChild("min").setValue(BigInteger.ZERO);
        createChild7.getCreateChild("max").setValue(multiply3);
        this.I.put("xs:unsignedShort", modelObject14);
        final ModelObject modelObject15 = new ModelObject("type");
        final IModelObject createChild8 = modelObject15.getCreateChild("number");
        final BigInteger multiply4 = BigInteger.valueOf(127L).multiply(value);
        createChild8.getCreateChild("min").setValue(BigInteger.ZERO);
        createChild8.getCreateChild("max").setValue(multiply4);
        this.I.put("xs:unsignedByte", modelObject15);
        final ModelObject modelObject16 = new ModelObject("type");
        modelObject16.getCreateChild("number").getCreateChild("float");
        this.I.put("xs:decimal", modelObject16);
        final ModelObject modelObject17 = new ModelObject("type");
        final IModelObject createChild9 = modelObject17.getCreateChild("number");
        createChild9.getCreateChild("float");
        Xlate.set(createChild9.getCreateChild("min"), Float.MIN_VALUE);
        Xlate.set(createChild9.getCreateChild("max"), Float.MAX_VALUE);
        this.I.put("xs:float", modelObject17);
        final ModelObject modelObject18 = new ModelObject("type");
        final IModelObject createChild10 = modelObject18.getCreateChild("number");
        createChild10.getCreateChild("float");
        Xlate.set(createChild10.getCreateChild("min"), Double.MIN_VALUE);
        Xlate.set(createChild10.getCreateChild("max"), Double.MAX_VALUE);
        this.I.put("xs:double", modelObject18);
        final ModelObject modelObject19 = new ModelObject("type");
        modelObject19.getCreateChild("string");
        this.I.put("xs:time", modelObject19);
        this.I.put("xs:date", modelObject19);
        this.I.put("xs:dateTime", modelObject19);
        this.I.put("xs:duration", modelObject19);
        this.I.put("xs:gDay", modelObject19);
        this.I.put("xs:gMonth", modelObject19);
        this.I.put("xs:gMonthDay", modelObject19);
        this.I.put("xs:gYear", modelObject19);
        this.I.put("xs:gYearMonth", modelObject19);
    }
}
