// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xaction;

import org.xmodel.xpath.XPath;
import java.util.regex.Matcher;
import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.DepthFirstIterator;
import org.xmodel.IModel;
import org.xmodel.xpath.variable.IVariableScope;
import java.util.Iterator;
import java.util.List;
import org.xmodel.caching.AnnotationTransform;
import org.xmodel.ModelAlgorithms;
import org.xmodel.xsd.Schema;
import java.util.ArrayList;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.IModelObject;
import org.xmodel.Xlate;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.IModelObjectFactory;
import java.util.regex.Pattern;

public class CreateAction extends GuardedAction
{
    private final Pattern \u0124;
    private IModelObjectFactory \u0120;
    private String \u0122;
    private IExpression \u0125;
    private IExpression \u011f;
    private IExpression \u0126;
    private IExpression \u0123;
    private IExpression \u0121;
    
    public CreateAction() {
        this.\u0124 = Pattern.compile("[{]([^}]+)[}]");
    }
    
    @Override
    public void configure(final XActionDocument xActionDocument) {
        super.configure(xActionDocument);
        final IModelObject root = xActionDocument.getRoot();
        this.\u0122 = Conventions.getVarName(root, false, "assign");
        this.\u0125 = Xlate.get(root, "collection", (IExpression)null);
        this.\u011f = Xlate.get(root, "parent", (IExpression)null);
        this.\u0126 = Xlate.get(root, "name", (IExpression)null);
        this.\u0123 = Xlate.get(root, "schema", (IExpression)null);
        this.\u0120 = this.getFactory(root);
        this.\u0121 = Xlate.get(root, "annotated", (IExpression)null);
    }
    
    public Object[] doAction(final IContext parentContext) {
        final XActionDocument document = this.getDocument();
        final IModelObject modelObject = (this.\u011f != null) ? this.\u011f.queryFirst(parentContext) : null;
        final ArrayList<IModelObject> list = new ArrayList<IModelObject>(1);
        if (this.\u0126 != null) {
            final String evaluateString = this.\u0126.evaluateString(parentContext);
            if (evaluateString.length() == 0) {
                throw new IllegalArgumentException("Element type name is empty: " + this);
            }
            list.add(this.\u0120.createObject(modelObject, evaluateString));
        }
        if (this.\u0123 != null) {
            list.add(Schema.createDocument(this.\u0123.queryFirst(parentContext), this.\u0120, Xlate.get(document.getRoot().getFirstChild("schema"), "optional", false)));
        }
        final Iterator<IModelObject> iterator = document.getRoot().getChildren().iterator();
        while (iterator.hasNext()) {
            final IModelObject cloneTree = ModelAlgorithms.cloneTree(iterator.next(), this.\u0120);
            this.B(parentContext, cloneTree);
            list.add(cloneTree);
        }
        if (this.\u0121 == null || this.\u0121.evaluateBoolean(parentContext)) {
            for (int i = 0; i < list.size(); ++i) {
                final IModelObject modelObject2 = list.get(i);
                final AnnotationTransform annotationTransform = new AnnotationTransform();
                annotationTransform.setFactory(this.\u0120);
                annotationTransform.setParentContext(parentContext);
                annotationTransform.setClassLoader(document.getClassLoader());
                list.set(i, annotationTransform.transform(modelObject2));
            }
        }
        if (this.\u0122 != null) {
            final IVariableScope scope = parentContext.getScope();
            if (scope != null) {
                scope.set(this.\u0122, list);
            }
        }
        if (modelObject != null) {
            final Iterator<Object> iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                modelObject.addChild(iterator2.next());
            }
        }
        final String s = (this.\u0125 != null) ? this.\u0125.evaluateString(parentContext) : null;
        if (s != null && list.size() > 0) {
            final IModel model = list.get(0).getModel();
            final Iterator<Object> iterator3 = list.iterator();
            while (iterator3.hasNext()) {
                model.addRoot(s, iterator3.next());
            }
        }
        return null;
    }
    
    private void B(final IContext context, final IModelObject modelObject) {
        final DepthFirstIterator depthFirstIterator = new DepthFirstIterator(modelObject);
        while (depthFirstIterator.hasNext()) {
            final IModelObject next = depthFirstIterator.next();
            for (final String s : next.getAttributeNames()) {
                next.setAttribute(s, this.D(context, Xlate.get(next, s, "")));
            }
        }
    }
    
    private Object D(final IContext context, final String s) {
        final StringBuilder sb = new StringBuilder();
        final Matcher matcher = this.\u0124.matcher(s);
        int n = 0;
        while (matcher.find()) {
            final int start = matcher.start();
            final int end = matcher.end();
            if (start > 0 && s.charAt(start - 1) == '\\') {
                sb.append(s, n, start - 1);
                sb.append(s, start, end);
            }
            else {
                sb.append(s, n, start);
                try {
                    sb.append(this.C(context, matcher.group(1)));
                }
                catch (ExpressionException ex) {
                    this.getDocument().error("Syntax error in template expression: " + matcher.group(), ex);
                }
            }
            n = end;
        }
        sb.append(s, n, s.length());
        return sb.toString();
    }
    
    private Object C(final IContext context, final String s) {
        final IExpression expression = XPath.createExpression(s);
        if (expression.getType(context) == IExpression.ResultType.NODES) {
            final IModelObject queryFirst = expression.queryFirst(context);
            return (queryFirst != null) ? queryFirst.getValue() : "";
        }
        return expression.evaluateString(context);
    }
}
