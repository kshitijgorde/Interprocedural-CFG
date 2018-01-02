// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xsd;

import java.util.regex.PatternSyntaxException;
import java.util.regex.Pattern;
import org.xmodel.ModelAlgorithms;
import org.xmodel.B;
import org.xmodel.xpath.PathElement;
import org.xmodel.CanonicalPath;
import org.xmodel.IPath;
import java.util.List;
import org.xmodel.BreadthFirstIterator;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Iterator;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.Context;
import org.xmodel.Xlate;
import org.xmodel.xml.XmlIO;
import org.xmodel.xml.XmlException;
import org.xmodel.xpath.XPath;
import java.net.URL;
import org.xmodel.log.Log;
import java.util.Set;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IExpression;

public class Xsd
{
    final IExpression K;
    final IExpression F;
    final IExpression N;
    final IExpression O;
    final IExpression H;
    final IExpression E;
    final IExpression B;
    final IExpression L;
    final IExpression I;
    final IExpression D;
    final IExpression C;
    private IModelObject J;
    private Set<String> M;
    private Set<String> A;
    private static Log G;
    
    static {
        Xsd.G = Log.getLog("org.xmodel.xsd");
    }
    
    public Xsd(final URL url) throws XmlException {
        this.K = XPath.createExpression("@type | */*/*/@base | */*/@base");
        this.F = XPath.createExpression("replace( name( ancestor-or-self::xs:schema/@*[ name() != 'targetNamespace' and . = $url]), '(^[^:=]+):?', '')");
        this.N = XPath.createExpression("ancestor-or-self::xs:schema//xs:element[ @name = $name] | ancestor-or-self::xs:schema//xs:attribute[ @name = $name]");
        this.O = XPath.createExpression("ancestor-or-self::xs:schema/xs:element[ @name = $name]");
        this.H = XPath.createExpression("ancestor::xs:element[ @name = $name]/self::*[ 1] | (for $c in ancestor::xs:complexType[ 1]/@name return   ancestor-or-self::xs:schema//xs:element[ @name = $name]/self::*[ @type = $c or */*/*[ @base = $c]])");
        this.E = XPath.createExpression("for $n in (@type | */*/*/@base | */*/@base) return ancestor-or-self::xs:schema/*[ matches( name(), 'xs:simpleType|xs:complexType') and @name = $n]");
        this.B = XPath.createExpression("(for $s in (@type | */xs:simpleContent/*/@base | xs:simpleType/*/@base) return   for $t in ancestor-or-self::xs:schema/*[ matches( name(), 'xs:simpleType|xs:complexType') and @name = $s]   return ($t/@type | $t//@base))| @type | */xs:simpleContent/*/@base | xs:simpleType/*/@base");
        this.L = XPath.createExpression("for $s in . return   if ( boolean( $s/xs:simpleType/*[ xs:enumeration])) then     $s/xs:simpleType/*[ xs:enumeration]   else     ancestor-or-self::xs:schema/xs:simpleType[ @name = ($s/@type | $s/*/*/*/@base)]/*[ xs:enumeration]");
        this.I = XPath.createExpression("for $s in .   return     if ( boolean( $s/xs:simpleType/*[ xs:pattern])) then       $s/xs:simpleType/*[ xs:pattern]     else       ancestor-or-self::xs:schema/xs:simpleType[ @name = ($s/@type | $s/*/*/*/@base)]/*[ xs:pattern]");
        this.D = XPath.createExpression("for $s in . return   if ( boolean( $s/xs:simpleType/xs:restriction)) then     $s/xs:simpleType/xs:restriction   else     ancestor-or-self::xs:schema/xs:simpleType[ @name = ($s/@type | $s/*/*/*/@base)]/xs:restriction");
        this.C = XPath.createExpression("descendant::xs:attribute[ @use = 'required'] |(for $n in (@type | */*/*/@base | */*/@base) return   ancestor-or-self::xs:schema/*[ matches( name(), 'xs:simpleType|xs:complexType') and @name = $n]//xs:attribute[ @use = 'required'])");
        this.J = this.load(url);
    }
    
    public Xsd(final IModelObject j) {
        this.K = XPath.createExpression("@type | */*/*/@base | */*/@base");
        this.F = XPath.createExpression("replace( name( ancestor-or-self::xs:schema/@*[ name() != 'targetNamespace' and . = $url]), '(^[^:=]+):?', '')");
        this.N = XPath.createExpression("ancestor-or-self::xs:schema//xs:element[ @name = $name] | ancestor-or-self::xs:schema//xs:attribute[ @name = $name]");
        this.O = XPath.createExpression("ancestor-or-self::xs:schema/xs:element[ @name = $name]");
        this.H = XPath.createExpression("ancestor::xs:element[ @name = $name]/self::*[ 1] | (for $c in ancestor::xs:complexType[ 1]/@name return   ancestor-or-self::xs:schema//xs:element[ @name = $name]/self::*[ @type = $c or */*/*[ @base = $c]])");
        this.E = XPath.createExpression("for $n in (@type | */*/*/@base | */*/@base) return ancestor-or-self::xs:schema/*[ matches( name(), 'xs:simpleType|xs:complexType') and @name = $n]");
        this.B = XPath.createExpression("(for $s in (@type | */xs:simpleContent/*/@base | xs:simpleType/*/@base) return   for $t in ancestor-or-self::xs:schema/*[ matches( name(), 'xs:simpleType|xs:complexType') and @name = $s]   return ($t/@type | $t//@base))| @type | */xs:simpleContent/*/@base | xs:simpleType/*/@base");
        this.L = XPath.createExpression("for $s in . return   if ( boolean( $s/xs:simpleType/*[ xs:enumeration])) then     $s/xs:simpleType/*[ xs:enumeration]   else     ancestor-or-self::xs:schema/xs:simpleType[ @name = ($s/@type | $s/*/*/*/@base)]/*[ xs:enumeration]");
        this.I = XPath.createExpression("for $s in .   return     if ( boolean( $s/xs:simpleType/*[ xs:pattern])) then       $s/xs:simpleType/*[ xs:pattern]     else       ancestor-or-self::xs:schema/xs:simpleType[ @name = ($s/@type | $s/*/*/*/@base)]/*[ xs:pattern]");
        this.D = XPath.createExpression("for $s in . return   if ( boolean( $s/xs:simpleType/xs:restriction)) then     $s/xs:simpleType/xs:restriction   else     ancestor-or-self::xs:schema/xs:simpleType[ @name = ($s/@type | $s/*/*/*/@base)]/xs:restriction");
        this.C = XPath.createExpression("descendant::xs:attribute[ @use = 'required'] |(for $n in (@type | */*/*/@base | */*/@base) return   ancestor-or-self::xs:schema/*[ matches( name(), 'xs:simpleType|xs:complexType') and @name = $n]//xs:attribute[ @use = 'required'])");
        this.J = j;
    }
    
    protected IModelObject load(final URL url) throws XmlException {
        final IModelObject read = new XmlIO().read(url);
        if (!read.isType("xs:schema")) {
            throw new XsdException("Document does not appear to be an XSD: " + url);
        }
        this.F.setVariable("url", Xlate.get(read, "targetNamespace", ""));
        final String evaluateString = this.F.evaluateString(new Context(read), "");
        if (evaluateString.length() > 0) {
            this.A(read, evaluateString);
        }
        final Iterator<IModelObject> iterator = read.getChildren("xs:include").iterator();
        while (iterator.hasNext()) {
            this.loadInclude(read, evaluateString, iterator.next());
        }
        read.removeChildren("xs:include");
        final Iterator<IModelObject> iterator2 = read.getChildren("xs:import").iterator();
        while (iterator2.hasNext()) {
            this.loadImport(read, iterator2.next());
        }
        read.removeChildren("xs:import");
        return read;
    }
    
    protected IModelObject load(final String s, final URL url) throws XmlException {
        final IModelObject read = new XmlIO().read(url);
        if (!read.isType("xs:schema")) {
            throw new XsdException("Document does not appear to be an XSD: " + url);
        }
        if (s.length() > 0) {
            this.A(read, s);
        }
        final Iterator<IModelObject> iterator = read.getChildren("xs:include").iterator();
        while (iterator.hasNext()) {
            this.loadInclude(read, s, iterator.next());
        }
        final Iterator<IModelObject> iterator2 = read.getChildren("xs:import").iterator();
        while (iterator2.hasNext()) {
            this.loadImport(read, iterator2.next());
        }
        return read;
    }
    
    protected void loadInclude(final IModelObject modelObject, final String s, final IModelObject modelObject2) throws XmlException {
        final String value = Xlate.get(modelObject2, "schemaLocation", "");
        if (this.M == null) {
            this.M = new HashSet<String>();
        }
        if (this.M.contains(value)) {
            return;
        }
        this.M.add(value);
        try {
            final IModelObject load = this.load(s, new URL(value));
            final Iterator<IModelObject> iterator = load.getChildren("xs:simpleType").iterator();
            while (iterator.hasNext()) {
                modelObject.addChild(iterator.next());
            }
            final Iterator<IModelObject> iterator2 = load.getChildren("xs:complexType").iterator();
            while (iterator2.hasNext()) {
                modelObject.addChild(iterator2.next());
            }
            final Iterator<IModelObject> iterator3 = load.getChildren("xs:group").iterator();
            while (iterator3.hasNext()) {
                modelObject.addChild(iterator3.next());
            }
            final Iterator<IModelObject> iterator4 = load.getChildren("xs:element").iterator();
            while (iterator4.hasNext()) {
                modelObject.addChild(iterator4.next());
            }
        }
        catch (MalformedURLException ex) {
            throw new XsdException("Unable to resolve include declaration with url: " + value, ex);
        }
    }
    
    protected void loadImport(final IModelObject modelObject, final IModelObject modelObject2) throws XmlException {
        final String value = Xlate.get(modelObject2, "schemaLocation", "");
        final String value2 = Xlate.get(modelObject2, "namespace", "");
        this.F.setVariable("url", value2);
        final String evaluateString = this.F.evaluateString(new Context(modelObject), "");
        final String string = String.valueOf(value) + "(" + value2 + ")";
        if (this.A == null) {
            this.A = new HashSet<String>();
        }
        if (this.A.contains(string)) {
            return;
        }
        this.A.add(string);
        try {
            final IModelObject load = this.load(evaluateString, new URL(value));
            final Iterator<IModelObject> iterator = load.getChildren("xs:simpleType").iterator();
            while (iterator.hasNext()) {
                modelObject.addChild(iterator.next());
            }
            final Iterator<IModelObject> iterator2 = load.getChildren("xs:complexType").iterator();
            while (iterator2.hasNext()) {
                modelObject.addChild(iterator2.next());
            }
            final Iterator<IModelObject> iterator3 = load.getChildren("xs:group").iterator();
            while (iterator3.hasNext()) {
                modelObject.addChild(iterator3.next());
            }
            final Iterator<IModelObject> iterator4 = load.getChildren("xs:element").iterator();
            while (iterator4.hasNext()) {
                modelObject.addChild(iterator4.next());
            }
        }
        catch (MalformedURLException ex) {
            throw new XsdException("Unable to resolve include declaration with url: " + value, ex);
        }
    }
    
    private void A(final IModelObject modelObject, final String s) {
        final BreadthFirstIterator breadthFirstIterator = new BreadthFirstIterator(modelObject);
        while (breadthFirstIterator.hasNext()) {
            final IModelObject next = breadthFirstIterator.next();
            if (next.isType("xs:element") || next.isType("xs:simpleType") || (next.isType("xs:complexType") | next.isType("xs:group"))) {
                final String value = Xlate.get(next, "name", (String)null);
                if (value != null && value.indexOf(":") < 0) {
                    next.setAttribute("name", String.valueOf(s) + ":" + value);
                }
                final String value2 = Xlate.get(next, "type", (String)null);
                if (value2 == null || value2.indexOf(":") >= 0) {
                    continue;
                }
                next.setAttribute("type", String.valueOf(s) + ":" + value2);
            }
            else {
                if (!next.isType("xs:restriction") && !next.isType("xs:extension")) {
                    continue;
                }
                final String value3 = Xlate.get(next, "base", (String)null);
                if (value3 == null || value3.indexOf(":") >= 0) {
                    continue;
                }
                next.setAttribute("base", String.valueOf(s) + ":" + value3);
            }
        }
    }
    
    public IModelObject getRoot() {
        return this.J;
    }
    
    public String getTargetNamespacePrefix() {
        this.F.setVariable("url", Xlate.get(this.J, "targetNamespace", ""));
        return this.F.evaluateString(new Context(this.J), "");
    }
    
    public String getElementType(final IModelObject modelObject) {
        return this.K.evaluateString(new Context(modelObject), "");
    }
    
    public IModelObject getTypeDeclaration(final IModelObject modelObject) {
        return this.E.queryFirst(modelObject);
    }
    
    public IModelObject getSimpleType(final IModelObject modelObject) {
        return this.B.queryFirst(modelObject);
    }
    
    public IModelObject getEnumerations(final IModelObject modelObject) {
        return this.L.queryFirst(modelObject);
    }
    
    public int minOccurences(final IModelObject modelObject) {
        return Xlate.get(modelObject, "minOccurs", 1);
    }
    
    public static int maxOccurences(final IModelObject modelObject) {
        if (Xlate.get(modelObject, "maxOccurs", "").equals("unbounded")) {
            return -1;
        }
        return Xlate.get(modelObject, "maxOccurs", 1);
    }
    
    public IModelObject getElementSchema(final IModelObject modelObject) {
        this.N.setVariable("name", modelObject.getType());
        for (final IModelObject modelObject2 : this.N.query(this.J, null)) {
            if (this.A(modelObject2, modelObject)) {
                return modelObject2;
            }
        }
        return null;
    }
    
    public IModelObject getElementSchema(final IPath path) {
        String type = path.getPathElement(path.length() - 1).type();
        if (type == null) {
            type = "*";
        }
        this.N.setVariable("name", type);
        for (final IModelObject modelObject : this.N.query(this.J, null)) {
            if (this.A(modelObject, path, path.length() - 1)) {
                return modelObject;
            }
        }
        return null;
    }
    
    public IModelObject getElementSchema(final IModelObject modelObject, final IPath path) {
        if (path.isAbsolute(null)) {
            return this.getElementSchema(path);
        }
        final CanonicalPath canonicalPath = new CanonicalPath();
        canonicalPath.addElement(new PathElement(16, modelObject.getType()));
        for (int i = 0; i < path.length(); ++i) {
            canonicalPath.addElement(path.getPathElement(i).clone());
        }
        return this.getElementSchema(canonicalPath);
    }
    
    public IModelObject getElementParentSchema(final IModelObject modelObject, final IModelObject modelObject2) {
        final IModelObject parent = modelObject2.getParent();
        if (parent == null) {
            return null;
        }
        this.H.setVariable("name", parent.getType());
        return this.H.queryFirst(modelObject);
    }
    
    public IModelObject getRootElementSchema(final IModelObject modelObject) {
        final IModelObject elementSchema = this.getElementSchema(modelObject);
        if (elementSchema == null) {
            return null;
        }
        final IModelObject b = this.B(elementSchema, modelObject);
        if (b == null) {
            return null;
        }
        this.O.setVariable("name", b.getID());
        return this.O.queryFirst(this.J);
    }
    
    public IModelObject getGlobalElementAncestor(final IModelObject modelObject) {
        final IModelObject elementSchema = this.getElementSchema(modelObject);
        if (elementSchema == null) {
            return null;
        }
        return this.B(elementSchema, modelObject);
    }
    
    public CanonicalPath getElementSchemaPath(final IModelObject modelObject, final boolean b) {
        final IModelObject globalElementAncestor = this.getGlobalElementAncestor(modelObject);
        if (globalElementAncestor == null) {
            return null;
        }
        final CanonicalPath relativePath = ModelAlgorithms.createRelativePath(globalElementAncestor, modelObject);
        if (b) {
            relativePath.addElement(0, new PathElement(1, relativePath.removeElement(0).type()));
        }
        return relativePath;
    }
    
    public void createRequiredAttributes(final IModelObject modelObject, final IModelObject modelObject2) {
        for (final IModelObject modelObject3 : this.C.query(modelObject, null)) {
            modelObject2.setAttribute(Xlate.get(modelObject3, "name", (String)null), this.generateValue(modelObject3));
        }
    }
    
    public String generateValue(final IModelObject modelObject) {
        final String value = Xlate.get(modelObject, "default", (String)null);
        if (value != null) {
            return value;
        }
        final String value2 = Xlate.get(this.getSimpleType(modelObject), "");
        if (value2.equals("xs:string") || value2.equals("xs:token") || value2.equals("xs:normalizedString")) {
            final IModelObject enumerations = this.getEnumerations(modelObject);
            if (enumerations == null) {
                return "";
            }
            final List<IModelObject> children = enumerations.getChildren("xs:enumeration");
            if (children.size() == 0) {
                return "";
            }
            return Xlate.get((IModelObject)children.get(0), "value", "");
        }
        else {
            if (!value2.equals("xs:int") && !value2.equals("xs:long") && !value2.equals("xs:short") && !value2.contains("nteger") && !value2.contains("decimal") && !value2.startsWith("unsigned")) {
                return "";
            }
            final IModelObject queryFirst = this.D.queryFirst(modelObject);
            if (queryFirst == null) {
                return null;
            }
            int n = 0;
            final IModelObject firstChild = queryFirst.getFirstChild("xs:maxInclusive");
            if (firstChild != null) {
                n = Xlate.get(firstChild, "value", 0);
            }
            final IModelObject firstChild2 = queryFirst.getFirstChild("xs:maxExclusive");
            if (firstChild2 != null) {
                n = Xlate.get(firstChild2, "value", 0);
            }
            final IModelObject firstChild3 = queryFirst.getFirstChild("xs:minInclusive");
            if (firstChild3 != null) {
                n = Xlate.get(firstChild3, "value", 0);
            }
            final IModelObject firstChild4 = queryFirst.getFirstChild("xs:minExclusive");
            if (firstChild4 != null) {
                n = Xlate.get(firstChild4, "value", 0);
            }
            return Integer.toString(n);
        }
    }
    
    private IModelObject B(final IModelObject modelObject, IModelObject parent) {
        this.H.setVariable("name", parent.getType());
        final List<IModelObject> query = this.H.query(modelObject, null);
        if (query.size() == 0) {
            return parent;
        }
        parent = parent.getParent();
        if (parent == null) {
            return null;
        }
        final Iterator<IModelObject> iterator = query.iterator();
        while (iterator.hasNext()) {
            final IModelObject b = this.B(iterator.next(), parent.getParent());
            if (b != null) {
                return b;
            }
        }
        return null;
    }
    
    private boolean A(final IModelObject modelObject, IModelObject parent) {
        this.H.setVariable("name", parent.getType());
        final List<IModelObject> query = this.H.query(modelObject, null);
        if (query.size() == 0) {
            return true;
        }
        parent = parent.getParent();
        if (parent == null) {
            return false;
        }
        final Iterator<IModelObject> iterator = query.iterator();
        while (iterator.hasNext()) {
            if (this.A(iterator.next(), parent.getParent())) {
                return true;
            }
        }
        return false;
    }
    
    private boolean A(final IModelObject modelObject, final IPath path, int n) {
        if (--n < 0) {
            return true;
        }
        String type = path.getPathElement(n).type();
        if (type == null) {
            type = "*";
        }
        this.H.setVariable("name", type);
        final List<IModelObject> query = this.H.query(modelObject, null);
        if (query.size() == 0) {
            return modelObject.getParent().isType("xs:schema");
        }
        final Iterator<IModelObject> iterator = query.iterator();
        while (iterator.hasNext()) {
            if (this.A(iterator.next(), path, n)) {
                return true;
            }
        }
        return false;
    }
    
    public IModelObject validate(final IModelObject modelObject, final String s) {
        final IModelObject simpleType = this.getSimpleType(modelObject);
        final String value = Xlate.get(simpleType, "");
        if (!validateType(value, s)) {
            return simpleType;
        }
        if (value.equals("xs:string") || value.equals("xs:token") || value.equals("xs:normalizedString")) {
            final IModelObject validateEnumeration = this.validateEnumeration(modelObject, s);
            if (validateEnumeration != null) {
                return validateEnumeration;
            }
            final IModelObject validateStringLength = this.validateStringLength(modelObject, s);
            if (validateStringLength != null) {
                return validateStringLength;
            }
            final IModelObject validateRegex = this.validateRegex(modelObject, s);
            if (validateRegex != null) {
                return validateRegex;
            }
            return null;
        }
        else {
            if (value.equals("xs:int") || value.equals("xs:long") || value.equals("xs:short") || value.contains("nteger") || value.contains("decimal") || value.startsWith("unsigned")) {
                return this.validateNumericRange(modelObject, s);
            }
            return null;
        }
    }
    
    public static boolean validateType(final String s, final String s2) {
        if (s.startsWith("xs:int")) {
            try {
                Integer.parseInt(s2);
            }
            catch (Exception ex) {
                return false;
            }
            return true;
        }
        if (s.equals("xs:positiveInteger")) {
            try {
                return Integer.parseInt(s2) > 0;
            }
            catch (Exception ex2) {
                return false;
            }
        }
        if (s.equals("xs:nonPositiveInteger")) {
            try {
                return Integer.parseInt(s2) <= 0;
            }
            catch (Exception ex3) {
                return false;
            }
        }
        if (s.equals("xs:negativeInteger")) {
            try {
                return Integer.parseInt(s2) < 0;
            }
            catch (Exception ex4) {
                return false;
            }
        }
        if (s.equals("xs:nonNegativeInteger")) {
            try {
                return Integer.parseInt(s2) >= 0;
            }
            catch (Exception ex5) {
                return false;
            }
        }
        if (s.equals("xs:decimal")) {
            try {
                Double.parseDouble(s2);
            }
            catch (Exception ex6) {
                return false;
            }
            return true;
        }
        if (s.equals("xs:byte")) {
            try {
                Byte.parseByte(s2);
            }
            catch (Exception ex7) {
                return false;
            }
            return true;
        }
        if (s.equals("xs:long")) {
            try {
                Long.parseLong(s2);
            }
            catch (Exception ex8) {
                return false;
            }
            return true;
        }
        if (s.equals("xs:short")) {
            try {
                Short.parseShort(s2);
            }
            catch (Exception ex9) {
                return false;
            }
            return true;
        }
        if (s.equals("xs:unsignedLong")) {
            try {
                return Long.parseLong(s2) >= 0L;
            }
            catch (Exception ex10) {
                return false;
            }
        }
        if (s.equals("xs:unsignedShort")) {
            try {
                return Short.parseShort(s2) >= 0;
            }
            catch (Exception ex11) {
                return false;
            }
        }
        if (s.equals("xs:unsignedInt")) {
            try {
                return Integer.parseInt(s2) >= 0;
            }
            catch (Exception ex12) {
                return false;
            }
        }
        if (s.equals("xs:unsignedByte")) {
            try {
                return Byte.parseByte(s2) >= 0;
            }
            catch (Exception ex13) {
                return false;
            }
        }
        return !s.equals("xs:boolean") || s2.equals("true") || s2.equals("false");
    }
    
    public IModelObject validateEnumeration(final IModelObject modelObject, final String s) {
        final IModelObject enumerations = this.getEnumerations(modelObject);
        if (enumerations == null) {
            return null;
        }
        final List<IModelObject> children = enumerations.getChildren("xs:enumeration");
        if (children.size() == 0) {
            return null;
        }
        final Iterator<IModelObject> iterator = children.iterator();
        while (iterator.hasNext()) {
            if (s.equals(Xlate.get((IModelObject)iterator.next(), "value", ""))) {
                return null;
            }
        }
        return enumerations;
    }
    
    public IModelObject validateNumericRange(final IModelObject modelObject, final String s) {
        try {
            final double double1 = Double.parseDouble(s);
            final IModelObject queryFirst = this.D.queryFirst(modelObject);
            if (queryFirst == null) {
                return null;
            }
            final IModelObject firstChild = queryFirst.getFirstChild("xs:minInclusive");
            if (firstChild != null && double1 < Xlate.get(firstChild, "value", 0)) {
                return firstChild;
            }
            final IModelObject firstChild2 = queryFirst.getFirstChild("xs:minExclusive");
            if (firstChild2 != null && double1 <= Xlate.get(firstChild2, "value", 0)) {
                return firstChild2;
            }
            final IModelObject firstChild3 = queryFirst.getFirstChild("xs:maxInclusive");
            if (firstChild3 != null && double1 > Xlate.get(firstChild3, "value", 0)) {
                return firstChild3;
            }
            final IModelObject firstChild4 = queryFirst.getFirstChild("xs:maxExclusive");
            if (firstChild4 != null && double1 >= Xlate.get(firstChild4, "value", 0)) {
                return firstChild4;
            }
            final IModelObject firstChild5 = queryFirst.getFirstChild("xs:totalDigits");
            if (firstChild5 != null && s.trim().length() > Xlate.get(firstChild5, "value", 0)) {
                return firstChild5;
            }
            return null;
        }
        catch (NumberFormatException ex) {
            Xsd.G.exception(ex);
            return null;
        }
    }
    
    public IModelObject validateStringLength(final IModelObject modelObject, final String s) {
        final IModelObject queryFirst = this.D.queryFirst(modelObject);
        if (queryFirst == null) {
            return null;
        }
        final int length = s.length();
        final IModelObject firstChild = queryFirst.getFirstChild("xs:length");
        if (firstChild != null && length != Xlate.get(firstChild, "value", 0)) {
            return firstChild;
        }
        final IModelObject firstChild2 = queryFirst.getFirstChild("xs:minLength");
        if (firstChild2 != null && length < Xlate.get(firstChild2, "value", 0)) {
            return firstChild2;
        }
        final IModelObject firstChild3 = queryFirst.getFirstChild("xs:maxLength");
        if (firstChild3 != null && length > Xlate.get(firstChild3, "value", 0)) {
            return firstChild3;
        }
        return null;
    }
    
    public IModelObject validateRegex(final IModelObject modelObject, final String s) {
        Pattern compile = (Pattern)modelObject.getAttribute("xm:pattern");
        if (compile == null) {
            try {
                final IModelObject queryFirst = this.I.queryFirst(modelObject);
                if (queryFirst == null) {
                    return null;
                }
                compile = Pattern.compile(Xlate.get(queryFirst.getFirstChild("xs:pattern"), "value", ""));
                modelObject.setAttribute("xm:pattern", compile);
            }
            catch (PatternSyntaxException ex) {
                Xsd.G.exception(ex);
                return null;
            }
        }
        if (!compile.matcher(s).find()) {
            return this.I.queryFirst(modelObject).getFirstChild("xs:pattern");
        }
        return null;
    }
    
    public static void main(final String[] array) throws Exception {
        System.out.println("->" + new Xsd(new URL("http://schema.stonewallnetworks.com/ns/entity/policy.xsd")).getElementSchema(XPath.createPath("en:site/en:securityPolicy/en:ruleSet/en:securityRule/en:name")));
    }
}
