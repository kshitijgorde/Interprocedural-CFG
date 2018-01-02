// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.xpath;

import org.xmodel.xpath.XPath;
import java.util.HashMap;
import org.xmodel.IModelObject;
import java.util.List;
import org.xmodel.xpath.expression.LocalContext;
import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.ExpressionListener;
import org.xmodel.xpath.expression.IExpression;
import com.stonewall.cornerstone.security.FeatureAccess;
import java.util.Map;
import org.xmodel.xpath.expression.IExpressionListener;
import com.stonewall.cornerstone.utility.ILog;
import org.xmodel.xpath.function.Function;

public class SecurityFunction extends Function implements ILog
{
    public static final String name = "cm:security";
    final IExpressionListener listener;
    private static Map<String, FeatureAccess> featureAccessMap;
    private static Map<String, IExpression> mutexPaths;
    private static Map<String, IExpression> permissionPaths;
    
    public SecurityFunction() {
        this.listener = new ExpressionListener() {
            @Override
            public void notifyChange(final IExpression expression, final IContext context, final boolean newValue) {
                SecurityFunction.this.getParent().notifyChange(SecurityFunction.this, context.getParent());
            }
        };
    }
    
    @Override
    public String getName() {
        return "cm:security";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.BOOLEAN;
    }
    
    @Override
    public boolean evaluateBoolean(final IContext context) throws ExpressionException {
        this.assertArgs(1, 2);
        this.assertType(context, 0, IExpression.ResultType.STRING);
        this.buildSecurityPaths();
        final String action = this.getArgument(0).evaluateString(context);
        final FeatureAccess access = SecurityFunction.featureAccessMap.get(action);
        final IExpression mutexExpr = SecurityFunction.mutexPaths.get(action);
        if (mutexExpr == null) {
            throw new ExpressionException(this, "Unrecognized security action: " + action);
        }
        final IExpression permissionExpr = SecurityFunction.permissionPaths.get(action);
        if (permissionExpr == null) {
            throw new ExpressionException(this, "Unrecognized security action: " + action);
        }
        final LocalContext local = new LocalContext(context);
        this.assignVariable(access.getVariableName(), local);
        return mutexExpr.evaluateBoolean(local) && permissionExpr.evaluateBoolean(local);
    }
    
    @Override
    public void bind(final IContext context) {
        this.buildSecurityPaths();
        final String action = this.getArgument(0).evaluateString(context);
        final FeatureAccess access = SecurityFunction.featureAccessMap.get(action);
        final IExpression mutexExpr = SecurityFunction.mutexPaths.get(action);
        if (mutexExpr == null) {
            throw new ExpressionException(this, "Unrecognized security action: " + action);
        }
        final IExpression permissionExpr = SecurityFunction.permissionPaths.get(action);
        if (permissionExpr == null) {
            throw new ExpressionException(this, "Unrecognized security action: " + action);
        }
        final LocalContext local = new LocalContext(context);
        this.assignVariable(access.getVariableName(), local);
        mutexExpr.addListener(local, this.listener);
        permissionExpr.addListener(local, this.listener);
        super.bind(local);
    }
    
    @Override
    public void unbind(final IContext context) {
        this.buildSecurityPaths();
        final String action = this.getArgument(0).evaluateString(context);
        final FeatureAccess access = SecurityFunction.featureAccessMap.get(action);
        final IExpression mutexExpr = SecurityFunction.mutexPaths.get(action);
        if (mutexExpr == null) {
            throw new ExpressionException(this, "Unrecognized security action: " + action);
        }
        final IExpression permissionExpr = SecurityFunction.permissionPaths.get(action);
        if (permissionExpr == null) {
            throw new ExpressionException(this, "Unrecognized security action: " + action);
        }
        final LocalContext local = new LocalContext(context);
        this.assignVariable(access.getVariableName(), local);
        mutexExpr.removeListener(local, this.listener);
        permissionExpr.removeListener(local, this.listener);
        super.unbind(local);
    }
    
    private void assignVariable(final String variable, final LocalContext context) {
        final IExpression arg = this.getArgument(1);
        if (arg == null) {
            return;
        }
        switch (arg.getType()) {
            case NODES: {
                context.set(variable, arg.evaluateNodes(context));
                break;
            }
            default: {
                context.set(variable, arg.evaluateString(context));
                break;
            }
        }
    }
    
    @Override
    public void notifyAdd(final IExpression expression, final IContext context, final List<IModelObject> nodes) {
        if (expression == this.getArgument(1)) {
            final String action = this.getArgument(0).evaluateString(context);
            final FeatureAccess access = SecurityFunction.featureAccessMap.get(action);
            this.assignVariable(access.getVariableName(), (LocalContext)context);
            this.getParent().notifyChange(this, context.getParent());
        }
    }
    
    @Override
    public void notifyRemove(final IExpression expression, final IContext context, final List<IModelObject> nodes) {
        if (expression == this.getArgument(1)) {
            final String action = this.getArgument(0).evaluateString(context);
            final FeatureAccess access = SecurityFunction.featureAccessMap.get(action);
            this.assignVariable(access.getVariableName(), (LocalContext)context);
            this.getParent().notifyChange(this, context.getParent());
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String newValue, final String oldValue) {
        if (expression == this.getArgument(1)) {
            final String action = this.getArgument(0).evaluateString(context);
            final FeatureAccess access = SecurityFunction.featureAccessMap.get(action);
            this.assignVariable(access.getVariableName(), (LocalContext)context);
            this.getParent().notifyChange(this, context.getParent());
        }
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context) {
        if (expression == this.getArgument(1)) {
            final String action = this.getArgument(0).evaluateString(context);
            final FeatureAccess access = SecurityFunction.featureAccessMap.get(action);
            this.assignVariable(access.getVariableName(), (LocalContext)context);
            this.getParent().notifyChange(this, context.getParent());
        }
    }
    
    @Override
    public void notifyValue(final IExpression expression, final IContext[] contexts, final IModelObject object, final Object newValue, final Object oldValue) {
        if (expression == this.getArgument(1)) {
            final String action = this.getArgument(0).evaluateString(contexts[0]);
            final FeatureAccess access = SecurityFunction.featureAccessMap.get(action);
            this.assignVariable(access.getVariableName(), (LocalContext)contexts[0]);
            this.getParent().notifyChange(this, contexts[0].getParent());
        }
    }
    
    @Override
    public boolean requiresValueNotification(final IExpression argument) {
        return true;
    }
    
    private void buildSecurityPaths() {
        if (SecurityFunction.mutexPaths == null) {
            SecurityFunction.featureAccessMap = new HashMap<String, FeatureAccess>();
            SecurityFunction.mutexPaths = new HashMap<String, IExpression>();
            SecurityFunction.permissionPaths = new HashMap<String, IExpression>();
            final FeatureAccess.Type[] types = FeatureAccess.Type.values();
            FeatureAccess.Type[] array;
            for (int length = (array = types).length, i = 0; i < length; ++i) {
                final FeatureAccess.Type type = array[i];
                final FeatureAccess featureAccess = new FeatureAccess(type);
                SecurityFunction.featureAccessMap.put(type.name(), featureAccess);
                final String mutexSpec = "count( collection('core')/security/mutexes/" + featureAccess.getMutexPath() + ") = 0";
                final IExpression mutexExpr = XPath.createExpression(mutexSpec);
                SecurityFunction.mutexPaths.put(type.name(), mutexExpr);
                final String permissionSpec = "count( for $roles in collection('core')/user/en:user/en:roles/en:role return collection('core')/security/roles/en:role[ @id = $roles]/en:permissions/" + featureAccess.getPermissionPath() + ") > 0";
                final IExpression permissionExpr = XPath.createExpression(permissionSpec);
                SecurityFunction.permissionPaths.put(type.name(), permissionExpr);
            }
        }
    }
}
