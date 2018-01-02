// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.binding;

import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xpath.expression.ExactExpressionListener;
import org.xmodel.xpath.expression.IContext;
import org.xidget.ifeature.IPointsFeature;
import org.xmodel.xpath.expression.ExpressionListener;
import java.util.Iterator;
import java.util.List;
import org.xidget.IXidget;
import org.xmodel.xpath.expression.IExpressionListener;
import org.xidget.ifeature.IBindFeature;
import org.xmodel.xpath.expression.IExpression;
import org.xmodel.Xlate;
import org.xidget.config.TagException;
import org.xidget.config.ifeature.IXidgetFeature;
import org.xidget.config.TagProcessor;
import java.util.HashMap;
import org.xidget.chart.Point;
import org.xmodel.IModelObject;
import java.util.Map;
import org.xidget.config.ITagHandler;

public class PointsTagHandler implements ITagHandler
{
    private Map<IModelObject, Point> map;
    
    public PointsTagHandler() {
        this.map = new HashMap<IModelObject, Point>();
    }
    
    @Override
    public boolean enter(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) throws TagException {
        final IXidgetFeature xidgetFeature = tagHandler.getFeature(IXidgetFeature.class);
        if (xidgetFeature == null) {
            throw new TagException("Parent tag handler must have an IXidgetFeature.");
        }
        final IXidget xidget = xidgetFeature.getXidget();
        final IExpression childGet = Xlate.childGet(modelObject, "list", (IExpression)null);
        if (childGet != null) {
            xidget.getFeature(IBindFeature.class).addBindingAfterChildren(new XidgetBinding(childGet, new PointNodeListener(xidget, this.createCoordinateExpressions(modelObject), Xlate.childGet(modelObject, "label", (IExpression)null))));
        }
        return false;
    }
    
    private IExpression[] createCoordinateExpressions(final IModelObject modelObject) {
        final List<IModelObject> children = modelObject.getChildren("coord");
        final IExpression[] array = new IExpression[children.size()];
        int n = 0;
        final Iterator<IModelObject> iterator = children.iterator();
        while (iterator.hasNext()) {
            array[n++] = Xlate.get((IModelObject)iterator.next(), (IExpression)null);
        }
        return array;
    }
    
    @Override
    public void exit(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) throws TagException {
    }
    
    @Override
    public boolean filter(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) {
        return true;
    }
    
    @Override
    public <T> T getFeature(final Class<T> clazz) {
        return null;
    }
    
    private class PointCoordListener extends ExpressionListener
    {
        private IXidget xidget;
        private IPointsFeature feature;
        private int coordinate;
        
        public PointCoordListener(final IXidget xidget, final int coordinate) {
            this.xidget = xidget;
            this.coordinate = coordinate;
        }
        
        @Override
        public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
            this.update(context, Xlate.get(expression.queryFirst(context), 0.0));
        }
        
        @Override
        public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
            this.update(context, Xlate.get(expression.queryFirst(context), 0.0));
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
            this.update(context, n);
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
            if (s != null) {
                this.update(context, s);
            }
        }
        
        @Override
        public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
            if (o != null) {
                if (o instanceof Double) {
                    this.update(array[0], (double)o);
                }
                else {
                    this.update(array[0], o.toString());
                }
            }
        }
        
        private void update(final IContext context, final String s) {
            try {
                this.update(context, Double.parseDouble(s));
            }
            catch (Exception ex) {}
        }
        
        private void update(final IContext context, final double n) {
            if (this.feature == null) {
                this.feature = this.xidget.getFeature(IPointsFeature.class);
            }
            final Point point = PointsTagHandler.this.map.get(context.getObject());
            if (point == null) {
                return;
            }
            this.feature.update(point, this.coordinate, n);
        }
    }
    
    private class PointLabelListener extends ExpressionListener
    {
        private IXidget xidget;
        private IPointsFeature feature;
        
        public PointLabelListener(final IXidget xidget) {
            this.xidget = xidget;
        }
        
        @Override
        public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> list) {
            this.update(context, Xlate.get(expression.queryFirst(context), ""));
        }
        
        @Override
        public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list) {
            this.update(context, Xlate.get(expression.queryFirst(context), ""));
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final double n, final double n2) {
            this.update(context, new StringBuilder().append(n).toString());
        }
        
        @Override
        public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
            if (s != null) {
                this.update(context, s);
            }
        }
        
        @Override
        public void notifyValue(final IExpression expression, final IContext[] array, final IModelObject modelObject, final Object o, final Object o2) {
            if (o != null) {
                this.update(array[0], o.toString());
            }
        }
        
        private void update(final IContext context, final String s) {
            if (this.feature == null) {
                this.feature = this.xidget.getFeature(IPointsFeature.class);
            }
            final Point point = PointsTagHandler.this.map.get(context.getObject());
            if (point == null) {
                return;
            }
            this.feature.update(point, s);
        }
    }
    
    private class PointNodeListener extends ExactExpressionListener
    {
        private IXidget xidget;
        private IExpression[] coordExprs;
        private PointCoordListener[] coordListeners;
        private IExpression labelExpr;
        private PointLabelListener labelListener;
        
        public PointNodeListener(final IXidget xidget, final IExpression[] coordExprs, final IExpression labelExpr) {
            this.xidget = xidget;
            this.coordExprs = coordExprs;
            this.labelExpr = labelExpr;
            this.coordListeners = new PointCoordListener[coordExprs.length];
            for (int i = 0; i < this.coordListeners.length; ++i) {
                this.coordListeners[i] = new PointCoordListener(xidget, i);
            }
            this.labelListener = new PointLabelListener(xidget);
        }
        
        @Override
        public void notifyInsert(final IExpression expression, final IContext context, final List<IModelObject> list, final int n, final int n2) {
            final IPointsFeature pointsFeature = this.xidget.getFeature(IPointsFeature.class);
            for (int i = 0; i < n2; ++i) {
                final IModelObject modelObject = list.get(n + i);
                final Point point = new Point();
                PointsTagHandler.this.map.put(modelObject, point);
                final StatefulContext statefulContext = new StatefulContext(context, modelObject);
                point.coords = new double[this.coordExprs.length];
                for (int j = 0; j < point.coords.length; ++j) {
                    point.coords[j] = this.coordExprs[j].evaluateNumber(statefulContext);
                    this.coordExprs[j].addListener(statefulContext, this.coordListeners[j]);
                }
                if (this.labelExpr != null) {
                    point.label = this.labelExpr.evaluateString(statefulContext);
                    this.labelExpr.addListener(statefulContext, this.labelListener);
                }
                pointsFeature.add(n + i, point);
            }
        }
        
        @Override
        public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> list, final int n, final int n2) {
            final IPointsFeature pointsFeature = this.xidget.getFeature(IPointsFeature.class);
            for (int i = 0; i < n2; ++i) {
                pointsFeature.remove(n);
            }
        }
    }
}
