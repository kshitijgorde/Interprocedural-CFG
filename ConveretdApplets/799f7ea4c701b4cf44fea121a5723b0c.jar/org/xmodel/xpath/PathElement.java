// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath;

import org.xmodel.xpath.expression.SubContext;
import org.xmodel.xpath.expression.Context;
import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.NullContext;
import org.xmodel.xpath.expression.H;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.util.Fifo;
import org.xmodel.PrecedingIterator;
import java.util.Collections;
import org.xmodel.FollowingIterator;
import org.xmodel.BreadthFirstIterator;
import java.util.Iterator;
import java.util.zip.CRC32;
import java.util.ArrayList;
import java.util.List;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.IPath;
import org.xmodel.log.Log;
import org.xmodel.G;
import org.xmodel.E;
import org.xmodel.B;

public class PathElement implements B, E
{
    int M;
    String L;
    G J;
    private static Log K;
    
    static {
        PathElement.K = Log.getLog("org.xmodel.xml");
    }
    
    public PathElement(final int m) {
        this.M = m;
    }
    
    public PathElement(final int m, final G j) {
        this.M = m;
        this.J = j;
    }
    
    public PathElement(final int m, final String l) {
        this.M = m;
        this.L = l;
    }
    
    public PathElement(final int m, final String l, final G j) {
        this.M = m;
        this.L = l;
        this.J = j;
    }
    
    public void setParent(final IPath path) {
        if (this.J != null) {
            this.J.A(path);
        }
    }
    
    @Override
    public int axis() {
        return this.M;
    }
    
    @Override
    public void setAxis(final int m) {
        this.M = m;
    }
    
    @Override
    public boolean hasAxis(final int n) {
        return (this.M & n) != 0x0;
    }
    
    @Override
    public String type() {
        return this.L;
    }
    
    @Override
    public G predicate() {
        return this.J;
    }
    
    @Override
    public List<IModelObject> query(final IContext context, IModelObject modelObject, List<IModelObject> list) {
        int size = 0;
        if (list == null) {
            list = new ArrayList<IModelObject>();
        }
        else {
            size = list.size();
        }
        if ((this.M & 0x1) != 0x0) {
            final int size2 = list.size();
            this.K(modelObject.getRoot(), this.L, list);
            final int size3 = list.size();
            if (size3 == size2) {
                return list;
            }
            modelObject = (IModelObject)list.get(size3 - 1);
        }
        if ((this.M & 0x2) != 0x0) {
            this.M(modelObject, this.L, list);
        }
        if ((this.M & 0x4) != 0x0) {
            this.D(modelObject, this.L, list);
        }
        if ((this.M & 0x20) != 0x0) {
            this.F(modelObject, this.L, list);
        }
        if ((this.M & 0x100) != 0x0) {
            this.E(modelObject, this.L, list);
        }
        if ((this.M & 0x200) != 0x0) {
            this.A(modelObject, this.L, list);
        }
        if ((this.M & 0x400) != 0x0) {
            this.J(modelObject, this.L, list);
        }
        if ((this.M & 0x800) != 0x0) {
            this.I(modelObject, this.L, list);
        }
        if ((this.M & 0x40) != 0x0) {
            this.H(modelObject, this.L, list);
        }
        if ((this.M & 0x8) != 0x0) {
            this.B(modelObject, this.L, list);
        }
        if ((this.M & 0x10) != 0x0) {
            this.G(modelObject, this.L, list);
        }
        if ((this.M & 0x80) != 0x0) {
            this.L(modelObject, this.L, list);
        }
        this.A(context, list, size);
        return list;
    }
    
    @Override
    public List<IModelObject> query(final IContext context, final List<IModelObject> list, List<IModelObject> list2) {
        if (list2 == null) {
            list2 = new ArrayList<IModelObject>();
        }
        for (int size = list.size(), i = 0; i < size; ++i) {
            this.query(context, list.get(i), list2);
        }
        return list2;
    }
    
    @Override
    public boolean evaluate(final IContext context, final IPath path, final IModelObject modelObject) {
        return this.A(modelObject, this.L) && (this.J == null || this.J.A(context, path, modelObject));
    }
    
    @Override
    public int hashCode() {
        final CRC32 crc32 = new CRC32();
        crc32.update(this.M);
        crc32.update(this.J.hashCode());
        if (this.L != null) {
            crc32.update(this.L.getBytes());
        }
        return (int)crc32.getValue();
    }
    
    private final List<IModelObject> M(final IModelObject modelObject, final String s, List<IModelObject> list) {
        if (list == null) {
            list = new ArrayList<IModelObject>(2);
        }
        if (this.A(modelObject, s)) {
            list.add(modelObject);
        }
        return list;
    }
    
    private final List<IModelObject> D(final IModelObject modelObject, final String s, List<IModelObject> k) {
        for (IModelObject modelObject2 = modelObject.getParent(); modelObject2 != null; modelObject2 = modelObject2.getParent()) {
            k = this.K(modelObject2, s, k);
        }
        return k;
    }
    
    private final List<IModelObject> B(final IModelObject modelObject, final String s, List<IModelObject> k) {
        final IModelObject parent = modelObject.getParent();
        if (parent != null) {
            k = this.K(parent, s, k);
        }
        return k;
    }
    
    private final List<IModelObject> G(final IModelObject modelObject, final String s, List<IModelObject> list) {
        if (s != null) {
            if (s.equals("text()")) {
                return this.A(modelObject, list);
            }
            if (s.equals("node()")) {
                List<IModelObject> list2 = this.C(modelObject, null, this.A(modelObject, this.L(modelObject, null, list)));
                final Iterator<IModelObject> iterator = modelObject.getChildren().iterator();
                while (iterator.hasNext()) {
                    list2 = this.K(iterator.next(), null, list2);
                }
                return list2;
            }
            if (s.startsWith("processing-instruction")) {
                return this.C(modelObject, s, list);
            }
            if (s.indexOf("*") < 0) {
                if (list == null) {
                    list = new ArrayList<IModelObject>();
                }
                for (final IModelObject modelObject2 : modelObject.getChildren(s)) {
                    if (modelObject2.getType().charAt(0) != '?' && this.A(modelObject2, s)) {
                        list.add(modelObject2);
                    }
                }
                return list;
            }
            if (list == null) {
                list = new ArrayList<IModelObject>();
            }
            for (final IModelObject modelObject3 : modelObject.getChildren()) {
                if (modelObject3.getType().charAt(0) != '?' && this.A(modelObject3, s)) {
                    list.add(modelObject3);
                }
            }
        }
        else {
            if (list == null) {
                list = new ArrayList<IModelObject>();
            }
            for (final IModelObject modelObject4 : modelObject.getChildren()) {
                final String type = modelObject4.getType();
                if ((type.length() == 0 || type.charAt(0) != '?') && this.A(modelObject4, s)) {
                    list.add(modelObject4);
                }
            }
        }
        return list;
    }
    
    private final List<IModelObject> F(final IModelObject modelObject, final String s, List<IModelObject> k) {
        final BreadthFirstIterator breadthFirstIterator = new BreadthFirstIterator(modelObject);
        while (breadthFirstIterator.hasNext()) {
            final IModelObject next = breadthFirstIterator.next();
            if (next != modelObject) {
                k = this.K(next, s, k);
            }
        }
        return k;
    }
    
    private final List<IModelObject> E(IModelObject next, final String s, List<IModelObject> k) {
        final FollowingIterator followingIterator = new FollowingIterator(next);
        while (followingIterator.hasNext()) {
            next = followingIterator.next();
            k = this.K(next, s, k);
        }
        return k;
    }
    
    private final List<IModelObject> A(final IModelObject modelObject, final String s, List<IModelObject> emptyList) {
        if (modelObject instanceof AttributeNode) {
            if (emptyList == null) {
                emptyList = Collections.emptyList();
            }
            return emptyList;
        }
        if (emptyList == null) {
            emptyList = new ArrayList<IModelObject>(5);
        }
        final List<IModelObject> children = modelObject.getParent().getChildren();
        for (int i = children.indexOf(modelObject) + 1; i < children.size(); ++i) {
            emptyList.add(children.get(i));
        }
        return emptyList;
    }
    
    private final List<IModelObject> J(IModelObject next, final String s, List<IModelObject> k) {
        final PrecedingIterator precedingIterator = new PrecedingIterator(next);
        while (precedingIterator.hasNext()) {
            next = precedingIterator.next();
            k = this.K(next, s, k);
        }
        return k;
    }
    
    private final List<IModelObject> I(final IModelObject modelObject, final String s, List<IModelObject> emptyList) {
        final IModelObject parent = modelObject.getParent();
        if (parent == null || modelObject instanceof AttributeNode) {
            if (emptyList == null) {
                emptyList = Collections.emptyList();
            }
            return emptyList;
        }
        if (emptyList == null) {
            emptyList = new ArrayList<IModelObject>(5);
        }
        final List<IModelObject> children = parent.getChildren();
        for (int i = children.indexOf(modelObject) - 1; i >= 0; --i) {
            final IModelObject modelObject2 = children.get(i);
            if (this.A(modelObject2, s)) {
                emptyList.add(modelObject2);
            }
        }
        return emptyList;
    }
    
    private final List<IModelObject> H(IModelObject modelObject, final String s, List<IModelObject> g) {
        int size = (g != null) ? g.size() : 0;
        final Fifo<IModelObject> fifo = new Fifo<IModelObject>();
        fifo.push(modelObject);
        while (!fifo.empty()) {
            modelObject = fifo.pop();
            g = this.G(modelObject, s, g);
            for (int i = size; i < g.size(); ++i) {
                modelObject = g.get(i);
                fifo.push(modelObject);
            }
            size = g.size();
        }
        return g;
    }
    
    private final List<IModelObject> L(final IModelObject modelObject, String s, List<IModelObject> list) {
        if (list == null) {
            list = new ArrayList<IModelObject>(5);
        }
        if (s != null) {
            if (s.endsWith(")") && s.equals("text()")) {
                s = "";
            }
            if (modelObject.getAttribute(s) != null) {
                list.add(modelObject.getAttributeNode(s));
            }
        }
        else {
            for (final String s2 : modelObject.getAttributeNames()) {
                if (s2.length() > 0) {
                    list.add(modelObject.getAttributeNode(s2));
                }
            }
        }
        return list;
    }
    
    private final List<IModelObject> A(final IModelObject modelObject, List<IModelObject> list) {
        if (list == null) {
            list = new ArrayList<IModelObject>(2);
        }
        if (modelObject.getValue() != null) {
            list.add(modelObject.getAttributeNode(""));
        }
        return list;
    }
    
    private final List<IModelObject> C(final IModelObject modelObject, final String s, List<IModelObject> list) {
        if (list == null) {
            list = new ArrayList<IModelObject>(2);
        }
        int n = (s != null) ? s.indexOf("'", 22) : -1;
        if (n < 0) {
            for (final IModelObject modelObject2 : modelObject.getChildren()) {
                if (modelObject2.getType().charAt(0) == '?') {
                    list.add(modelObject2);
                }
            }
        }
        else {
            ++n;
            final String string = "?" + s.substring(n, s.indexOf("'", n));
            for (final IModelObject modelObject3 : modelObject.getChildren()) {
                if (modelObject3.getType().equals(string)) {
                    list.add(modelObject3);
                }
            }
        }
        return list;
    }
    
    private final List<IModelObject> K(final IModelObject modelObject, final String s, List<IModelObject> list) {
        if (list == null) {
            list = new ArrayList<IModelObject>(5);
        }
        if (s != null) {
            if (s.equals("text()")) {
                list = this.A(modelObject, list);
            }
            else if (s.equals("node()")) {
                list = this.C(modelObject, null, this.A(modelObject, this.L(modelObject, null, list)));
                list.add(modelObject);
            }
            else {
                if (s.startsWith("processing-instruction")) {
                    return this.C(modelObject, s, list);
                }
                if (this.A(modelObject, s)) {
                    list.add(modelObject);
                }
            }
        }
        else {
            list.add(modelObject);
        }
        return list;
    }
    
    private final boolean A(final IModelObject modelObject, final String s) {
        if (s == null) {
            return true;
        }
        final int length = s.length();
        if (s.charAt(length - 1) == ')') {
            return s.charAt(0) != 't' || (modelObject instanceof AttributeNode && ((AttributeNode)modelObject).F.length() == 0);
        }
        final String type = modelObject.getType();
        boolean b = true;
        int n;
        int n2;
        for (n = 0, n2 = 0; n < type.length() && n2 < length; ++n) {
            final char char1 = type.charAt(n);
            final char char2 = s.charAt(n2);
            if (char1 == ':') {
                b = false;
            }
            if (!b && char2 == '*') {
                return true;
            }
            if (!b || char2 != '*') {
                ++n2;
            }
            if (char2 != '*' && char1 != char2) {
                return false;
            }
        }
        return n == type.length() && n2 == length;
    }
    
    private final void A(final IContext context, final List<IModelObject> list, final int n) {
        if (this.J == null) {
            return;
        }
        for (final IExpression expression : ((IExpression)this.J).getArguments()) {
            if (expression.getType() == IExpression.ResultType.NUMBER) {
                if (!(expression instanceof H)) {
                    continue;
                }
                try {
                    final int n2 = (int)expression.evaluateNumber(NullContext.getInstance()) - 1 + n;
                    if (n2 < n) {
                        throw new ExpressionException(expression, "position values begin with 1");
                    }
                    if (n2 < list.size()) {
                        final IModelObject modelObject = list.get(n2);
                        list.clear();
                        list.add(modelObject);
                    }
                    else {
                        list.clear();
                    }
                }
                catch (ExpressionException ex) {
                    PathElement.K.exception(ex);
                    list.clear();
                }
            }
            else {
                int size = list.size();
                for (int i = n; i < size; ++i) {
                    try {
                        IContext context2;
                        if (context == null) {
                            context2 = new Context(list.get(i), i + 1, size);
                        }
                        else {
                            context2 = new SubContext(context, list.get(i), i + 1, size);
                        }
                        if (!expression.evaluateBoolean(context2)) {
                            list.set(i, null);
                        }
                    }
                    catch (ExpressionException ex2) {
                        PathElement.K.exception(ex2);
                        list.set(i, null);
                    }
                }
                for (int j = n; j < size; ++j) {
                    if (list.get(j) == null) {
                        list.remove(j--);
                        --size;
                    }
                }
            }
        }
    }
    
    @Override
    public B clone() {
        return this.clone(this.M);
    }
    
    public B clone(final int n) {
        return new PathElement(n, this.L, (this.J != null) ? ((G)this.J.clone()) : null);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof B) {
            final B b = (B)o;
            return b.axis() == this.M && (b.type() == null || this.L == null || b.type().equals(this.L)) && ((b.type() != null && this.L != null) || b.type() == this.L) && b.predicate() == this.J;
        }
        return false;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        switch (this.M) {
            case 128: {
                sb.append("@");
                sb.append((this.L == null) ? "*" : this.L);
                break;
            }
            case 16: {
                sb.append((this.L == null) ? "*" : this.L);
                break;
            }
            case 64: {
                sb.append("nested::");
                sb.append((this.L == null) ? "*" : this.L);
                break;
            }
            case 32: {
                sb.append("//");
                sb.append((this.L == null) ? "*" : this.L);
                break;
            }
            case 8: {
                if (this.L != null) {
                    sb.append("parent::");
                    sb.append(this.L);
                    break;
                }
                sb.append("..");
                break;
            }
            case 1: {
                sb.append('/');
                sb.append((this.L == null) ? "*" : this.L);
                break;
            }
            case 2: {
                if (this.L != null) {
                    sb.append("self::");
                    sb.append(this.L);
                    break;
                }
                sb.append(".");
                break;
            }
            case 4: {
                sb.append("ancestor::");
                sb.append((this.L == null) ? "*" : this.L);
                break;
            }
            case 66: {
                sb.append("nested-or-self::");
                sb.append((this.L == null) ? "*" : this.L);
                break;
            }
            case 34: {
                sb.append("descendant-or-self::");
                sb.append((this.L == null) ? "*" : this.L);
                break;
            }
            case 6: {
                sb.append("ancestor-or-self::");
                sb.append((this.L == null) ? "*" : this.L);
                break;
            }
            case 256: {
                sb.append("following::");
                sb.append((this.L == null) ? "*" : this.L);
                break;
            }
            case 512: {
                sb.append("following-sibling::");
                sb.append((this.L == null) ? "*" : this.L);
                break;
            }
            case 1024: {
                sb.append("preceding::");
                sb.append((this.L == null) ? "*" : this.L);
                break;
            }
            case 2048: {
                sb.append("preceding-sibling::");
                sb.append((this.L == null) ? "*" : this.L);
                break;
            }
        }
        if (this.J != null) {
            sb.append(this.J.toString());
        }
        return sb.toString();
    }
}
