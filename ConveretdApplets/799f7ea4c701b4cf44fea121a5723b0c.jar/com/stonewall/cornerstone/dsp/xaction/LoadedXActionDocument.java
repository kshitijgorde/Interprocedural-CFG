// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.xaction;

import org.xmodel.xpath.variable.IVariableScope;
import org.xmodel.xpath.expression.IContext;
import java.util.Iterator;
import org.xmodel.Xlate;
import java.io.InputStream;
import com.stonewall.cornerstone.utility.ModelBuilder;
import java.util.ArrayList;
import org.xmodel.IModelObject;
import org.xmodel.xpath.XPath;
import org.xmodel.log.Log;
import org.xmodel.xpath.expression.IExpression;
import com.stonewall.cornerstone.dsp.loader.Loader;
import org.xmodel.xaction.IXAction;
import java.util.List;
import org.xmodel.xaction.XActionDocument;

public class LoadedXActionDocument extends XActionDocument
{
    private List<IXAction> actions;
    private Loader loader;
    private static final IExpression actionExpr;
    public static final Log log;
    
    static {
        actionExpr = XPath.createExpression("*[ not( matches( name(), 'factory|package|failurePattern|expect'))]");
        log = Log.getLog(LoadedXActionDocument.class);
    }
    
    public LoadedXActionDocument(final XActionDocument parent, final Loader loader, final IModelObject root) throws Exception {
        this.actions = new ArrayList<IXAction>();
        this.init(parent, root, loader);
    }
    
    public LoadedXActionDocument(final XActionDocument parent, final Loader loader, final String name) throws Exception {
        this.actions = new ArrayList<IXAction>();
        try {
            final InputStream is = loader.getResourceAsStream(name);
            final ModelBuilder builder = new ModelBuilder();
            final IModelObject object = builder.buildModel(is);
            object.setAttribute("url", "file:///src/" + loader.getPath(name));
            this.init(parent, object, loader);
        }
        catch (Exception e) {
            LoadedXActionDocument.log.info("Cannot load document:" + name);
            throw e;
        }
    }
    
    private void init(final XActionDocument parent, final IModelObject root, final Loader loader) {
        this.loader = loader;
        this.setClassLoader(loader.getClass().getClassLoader());
        this.setRoot(root);
        this.addPackage("com.stonewall.cornerstone.dsp.xaction");
        this.addPackage("com.stonewall.cornerstone.xaction");
        if (parent != null) {
            for (final String pkgName : parent.getPackages()) {
                this.addPackage(pkgName);
            }
        }
        final List<IModelObject> packages = this.getRoot().getChildren("package");
        for (final IModelObject pkg : packages) {
            final String pkgName2 = Xlate.get(pkg, "");
            this.addPackage(pkgName2);
        }
    }
    
    public LoadedXActionDocument(final XActionDocument parent, final String sw, final String hw, final String name) throws Exception {
        this(parent, new Loader(sw, hw), name);
    }
    
    public boolean process(final IContext context) {
        final IModelObject root = this.getRoot();
        final IVariableScope scope = context.getScope();
        List<IModelObject> docs = (List<IModelObject>)scope.get("docs");
        if (docs == null) {
            docs = new ArrayList<IModelObject>();
            context.set("docs", docs);
        }
        docs.add(root);
        context.set("software", this.loader.getSoftware());
        context.set("hardware", this.loader.getHardware());
        if (this.actions.isEmpty()) {
            final IXAction rootAction = this.getAction(root);
            if (rootAction != null) {
                this.actions.add(rootAction);
            }
            else {
                final List<IModelObject> actionNodes = LoadedXActionDocument.actionExpr.query(root, null);
                for (final IModelObject actionNode : actionNodes) {
                    this.actions.add(this.getAction(actionNode));
                }
            }
        }
        for (final IXAction action : this.actions) {
            action.run(context);
        }
        docs.remove(root);
        return true;
    }
}
