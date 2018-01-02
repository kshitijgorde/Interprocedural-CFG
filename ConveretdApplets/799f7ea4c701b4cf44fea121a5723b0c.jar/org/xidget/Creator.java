// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget;

import org.xidget.config.ifeature.IXidgetFeature;
import org.xmodel.xpath.expression.IContext;
import org.xidget.ifeature.ILayoutFeature;
import org.xidget.ifeature.IWidgetCreationFeature;
import org.xidget.ifeature.IBindFeature;
import org.xidget.config.TagException;
import java.util.Collection;
import java.util.Collections;
import org.xmodel.xpath.expression.StatefulContext;
import java.util.Stack;
import java.util.Iterator;
import org.xidget.ifeature.IFocusFeature;
import org.xmodel.IModelObject;
import org.xidget.xpath.ParseDateFunction;
import org.xidget.xpath.FormatDateFunction;
import org.xidget.xpath.ValidateXPathFunction;
import org.xidget.xpath.IsFolderFunction;
import org.xidget.xpath.FontsFunction;
import org.xidget.xpath.FileExistsFunction;
import org.xmodel.xpath.function.Function;
import org.xidget.xpath.CapitalizeFunction;
import org.xmodel.xpath.function.FunctionFactory;
import org.xidget.binding.table.SubTableTagHandler;
import org.xidget.binding.tree.SubTreeTagHandler;
import org.xidget.binding.KeyTagHandler;
import org.xidget.binding.ScriptTagHandler;
import org.xidget.binding.SkipTagHandler;
import org.xidget.binding.FontSizeBindingRule;
import org.xidget.binding.FontStyleBindingRule;
import org.xidget.binding.FontFamilyBindingRule;
import org.xidget.binding.table.ShowGridBindingRule;
import org.xidget.binding.slider.PrecisionBindingRule;
import org.xidget.binding.slider.MaximumBindingRule;
import org.xidget.binding.slider.MinimumBindingRule;
import org.xidget.binding.VAlignBindingRule;
import org.xidget.binding.HAlignBindingRule;
import org.xidget.binding.VisibleBindingRule;
import org.xidget.binding.TriggerTagHandler;
import org.xidget.binding.TooltipBindingRule;
import org.xidget.binding.table.ColumnTitleBindingRule;
import org.xidget.binding.TitleBindingRule;
import org.xidget.binding.SpacingBindingRule;
import org.xidget.binding.SourceTagHandler;
import org.xidget.binding.SetTagHandler;
import org.xidget.binding.SelectionTagHandler;
import org.xidget.binding.table.RowSetBindingRule;
import org.xidget.binding.PointsTagHandler;
import org.xidget.binding.OutsideMarginsBindingRule;
import org.xidget.binding.InsideMarginsBindingRule;
import org.xidget.binding.LabelBindingRule;
import org.xidget.binding.IconBindingRule;
import org.xidget.binding.GetTagHandler;
import org.xidget.binding.ForegroundBindingRule;
import org.xidget.binding.FontTagHandler;
import org.xidget.binding.EnableBindingRule;
import org.xidget.binding.EditableBindingRule;
import org.xidget.binding.ContextTagHandler;
import org.xidget.binding.ChoicesTagHandler;
import org.xidget.binding.BoundsBindingRule;
import org.xidget.config.ITagHandler;
import org.xidget.binding.IBindingRule;
import org.xidget.binding.BindingTagHandler;
import org.xidget.binding.BackgroundBindingRule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xidget.config.TagProcessor;

public final class Creator
{
    private static ThreadLocal<Creator> instances;
    private static Class<? extends IToolkit> toolkitClass;
    private IToolkit toolkit;
    private TagProcessor processor;
    private Map<Object, IXidget> map;
    private List<IXidget> roots;
    
    static {
        Creator.instances = new ThreadLocal<Creator>();
    }
    
    protected Creator() {
        this.processor = new TagProcessor();
        this.map = new HashMap<Object, IXidget>();
        this.roots = new ArrayList<IXidget>(1);
        this.processor.addHandler("bgcolor", new BindingTagHandler(new BackgroundBindingRule()));
        this.processor.addHandler("bounds", new BindingTagHandler(new BoundsBindingRule()));
        this.processor.addHandler("choices", new ChoicesTagHandler());
        this.processor.addHandler("context", new ContextTagHandler());
        this.processor.addHandler("editable", new BindingTagHandler(new EditableBindingRule()));
        this.processor.addHandler("enable", new BindingTagHandler(new EnableBindingRule()));
        this.processor.addHandler("font", new FontTagHandler());
        this.processor.addHandler("fgcolor", new BindingTagHandler(new ForegroundBindingRule()));
        this.processor.addHandler("get", new GetTagHandler());
        this.processor.addHandler("image", new BindingTagHandler(new IconBindingRule()));
        this.processor.addHandler("label", new BindingTagHandler(new LabelBindingRule()));
        this.processor.addHandler("margins", new BindingTagHandler(new InsideMarginsBindingRule()));
        this.processor.addHandler("insideMargins", new BindingTagHandler(new InsideMarginsBindingRule()));
        this.processor.addHandler("outsideMargins", new BindingTagHandler(new OutsideMarginsBindingRule()));
        this.processor.addHandler("points", new PointsTagHandler());
        this.processor.addHandler("rows", new BindingTagHandler(new RowSetBindingRule()));
        this.processor.addHandler("selection", new SelectionTagHandler());
        this.processor.addHandler("set", new SetTagHandler());
        this.processor.addHandler("source", new SourceTagHandler());
        this.processor.addHandler("spacing", new BindingTagHandler(new SpacingBindingRule()));
        this.processor.addHandler("title", new BindingTagHandler(new TitleBindingRule()));
        this.processor.addHandler("title", new BindingTagHandler(new ColumnTitleBindingRule(), true));
        this.processor.addHandler("tooltip", new BindingTagHandler(new TooltipBindingRule()));
        this.processor.addHandler("trigger", new TriggerTagHandler());
        this.processor.addHandler("visible", new BindingTagHandler(new VisibleBindingRule()));
        this.processor.addHandler("halign", new BindingTagHandler(new HAlignBindingRule()));
        this.processor.addHandler("valign", new BindingTagHandler(new VAlignBindingRule()));
        this.processor.addHandler("min", new BindingTagHandler(new MinimumBindingRule()));
        this.processor.addHandler("minimum", new BindingTagHandler(new MinimumBindingRule()));
        this.processor.addHandler("max", new BindingTagHandler(new MaximumBindingRule()));
        this.processor.addHandler("maximum", new BindingTagHandler(new MaximumBindingRule()));
        this.processor.addHandler("precision", new BindingTagHandler(new PrecisionBindingRule()));
        this.processor.addAttributeHandler("min", new BindingTagHandler(new MinimumBindingRule()));
        this.processor.addAttributeHandler("minimum", new BindingTagHandler(new MinimumBindingRule()));
        this.processor.addAttributeHandler("max", new BindingTagHandler(new MaximumBindingRule()));
        this.processor.addAttributeHandler("maximum", new BindingTagHandler(new MaximumBindingRule()));
        this.processor.addAttributeHandler("precision", new BindingTagHandler(new PrecisionBindingRule()));
        this.processor.addHandler("showGrid", new BindingTagHandler(new ShowGridBindingRule()));
        this.processor.addAttributeHandler("bgcolor", new BindingTagHandler(new BackgroundBindingRule()));
        this.processor.addAttributeHandler("bounds", new BindingTagHandler(new BoundsBindingRule()));
        this.processor.addAttributeHandler("context", new ContextTagHandler());
        this.processor.addAttributeHandler("fgcolor", new BindingTagHandler(new ForegroundBindingRule()));
        this.processor.addAttributeHandler("family", new BindingTagHandler(new FontFamilyBindingRule("font")));
        this.processor.addAttributeHandler("style", new BindingTagHandler(new FontStyleBindingRule("font")));
        this.processor.addAttributeHandler("size", new BindingTagHandler(new FontSizeBindingRule("font")));
        this.processor.addAttributeHandler("image", new BindingTagHandler(new IconBindingRule()));
        this.processor.addAttributeHandler("label", new BindingTagHandler(new LabelBindingRule()));
        this.processor.addAttributeHandler("margins", new BindingTagHandler(new InsideMarginsBindingRule()));
        this.processor.addAttributeHandler("insideMargins", new BindingTagHandler(new InsideMarginsBindingRule()));
        this.processor.addAttributeHandler("outsideMargins", new BindingTagHandler(new OutsideMarginsBindingRule()));
        this.processor.addAttributeHandler("source", new SourceTagHandler());
        this.processor.addAttributeHandler("spacing", new BindingTagHandler(new SpacingBindingRule()));
        this.processor.addAttributeHandler("title", new BindingTagHandler(new TitleBindingRule()));
        this.processor.addAttributeHandler("title", new BindingTagHandler(new ColumnTitleBindingRule(), true));
        this.processor.addAttributeHandler("halign", new BindingTagHandler(new HAlignBindingRule()));
        this.processor.addAttributeHandler("valign", new BindingTagHandler(new VAlignBindingRule()));
        this.processor.addHandler("functions", new SkipTagHandler());
        this.processor.addHandler("onPress", new ScriptTagHandler());
        this.processor.addHandler("onOpen", new ScriptTagHandler());
        this.processor.addHandler("onClose", new ScriptTagHandler());
        this.processor.addHandler("onShow", new ScriptTagHandler());
        this.processor.addHandler("onHide", new ScriptTagHandler());
        this.processor.addHandler("onDrag", new ScriptTagHandler());
        this.processor.addHandler("onDrop", new ScriptTagHandler());
        this.processor.addHandler("onClick", new ScriptTagHandler());
        this.processor.addHandler("onDoubleClick", new ScriptTagHandler());
        this.processor.addHandler("onFocusGain", new ScriptTagHandler());
        this.processor.addHandler("onFocusLost", new ScriptTagHandler());
        this.processor.addHandler("onDisplay", new ScriptTagHandler());
        this.processor.addHandler("onSet", new ScriptTagHandler());
        this.processor.addHandler("onGet", new ScriptTagHandler());
        this.processor.addHandler("onSelect", new ScriptTagHandler());
        this.processor.addHandler("onDeselect", new ScriptTagHandler());
        this.processor.addHandler("onKey", new KeyTagHandler());
        this.processor.addHandler("tree", new SubTreeTagHandler());
        this.processor.addHandler("table", new SubTableTagHandler());
        this.registerCustomXPaths();
    }
    
    public static void setToolkitClass(final Class<? extends IToolkit> toolkitClass) {
        Creator.toolkitClass = toolkitClass;
    }
    
    public static IToolkit getToolkit() {
        final Creator instance = getInstance();
        if (instance.toolkit == null) {
            try {
                (instance.toolkit = (IToolkit)Creator.toolkitClass.newInstance()).configure(instance.processor);
            }
            catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return instance.toolkit;
    }
    
    public TagProcessor getTagProcessor() {
        throw new InternalError("Badly shrinked");
    }
    
    private void registerCustomXPaths() {
        FunctionFactory.getInstance().register("xi:capitalize", CapitalizeFunction.class);
        FunctionFactory.getInstance().register("xi:file-exists", FileExistsFunction.class);
        FunctionFactory.getInstance().register("xi:fonts", FontsFunction.class);
        FunctionFactory.getInstance().register("xi:is-folder", IsFolderFunction.class);
        FunctionFactory.getInstance().register("xi:validate-xpath", ValidateXPathFunction.class);
        FunctionFactory.getInstance().register("xi:format-date", FormatDateFunction.class);
        FunctionFactory.getInstance().register("xi:parse-date", ParseDateFunction.class);
    }
    
    public void register(final Object o, final IXidget xidget) {
        this.map.put(o, xidget);
        if (xidget.getParent() == null && !this.roots.contains(xidget)) {
            this.roots.add(xidget);
        }
    }
    
    public IXidget getXidget(final Object o) {
        return this.map.get(o);
    }
    
    public List<IXidget> getActiveHierarchies() {
        return this.roots;
    }
    
    public IXidget findXidget(final IModelObject modelObject) {
        final List<IXidget> activeHierarchies = this.getActiveHierarchies();
        final IXidget focus = getToolkit().getFeature(IFocusFeature.class).getFocus();
        if (focus != null) {
            activeHierarchies.add(0, focus);
        }
        final Iterator<IXidget> iterator = activeHierarchies.iterator();
        while (iterator.hasNext()) {
            final IXidget xidget = this.findXidget(iterator.next(), modelObject);
            if (xidget != null) {
                return xidget;
            }
        }
        return null;
    }
    
    public List<IXidget> findXidgets(final IModelObject modelObject) {
        throw new InternalError("Badly shrinked");
    }
    
    private IXidget findXidget(final IXidget xidget, final IModelObject modelObject) {
        final Stack<IXidget> stack = new Stack<IXidget>();
        stack.push(xidget);
        while (!stack.empty()) {
            final IXidget xidget2 = stack.pop();
            if (xidget2.getConfig() == modelObject) {
                return xidget2;
            }
            for (final IXidget xidget3 : xidget2.getChildren()) {
                if (xidget3 != xidget2) {
                    stack.push(xidget3);
                }
            }
        }
        return null;
    }
    
    public List<IXidget> create(final IXidget xidget, final StatefulContext statefulContext) throws TagException {
        final List<IXidget> list = (xidget != null) ? this.parse(xidget, statefulContext) : this.parse(statefulContext);
        if (list.size() == 0) {
            return Collections.emptyList();
        }
        final Iterator<IXidget> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.build(iterator.next());
        }
        if (xidget == null) {
            this.roots.addAll(list);
        }
        return list;
    }
    
    public List<IXidget> create(final IXidget xidget, final StatefulContext statefulContext, final StatefulContext statefulContext2) throws TagException {
        final List<IXidget> create = this.create(xidget, statefulContext);
        final Iterator<IXidget> iterator = create.iterator();
        while (iterator.hasNext()) {
            iterator.next().getFeature(IBindFeature.class).bind(statefulContext2);
        }
        return create;
    }
    
    public void destroy(final IXidget xidget) {
        final IBindFeature bindFeature = xidget.getFeature(IBindFeature.class);
        StatefulContext[] array;
        for (int length = (array = bindFeature.getBoundContexts().toArray(new StatefulContext[0])).length, i = 0; i < length; ++i) {
            bindFeature.unbind(array[i]);
        }
        xidget.getFeature(IWidgetCreationFeature.class).destroyWidgets();
        final IXidget parent = xidget.getParent();
        if (parent != null) {
            parent.getChildren().remove(xidget);
        }
    }
    
    public void build(final IXidget xidget) {
        final Stack<IXidget> stack = new Stack<IXidget>();
        stack.push(xidget);
        while (!stack.empty()) {
            final IXidget xidget2 = stack.pop();
            final IWidgetCreationFeature widgetCreationFeature = xidget2.getFeature(IWidgetCreationFeature.class);
            if (widgetCreationFeature != null) {
                widgetCreationFeature.createWidgets();
                Object[] lastWidgets;
                for (int length = (lastWidgets = widgetCreationFeature.getLastWidgets()).length, i = 0; i < length; ++i) {
                    this.register(lastWidgets[i], xidget2);
                }
            }
            final List<IXidget> children = xidget2.getChildren();
            for (int j = children.size() - 1; j >= 0; --j) {
                stack.push(children.get(j));
            }
        }
    }
    
    public IXidget rebuild(IXidget xidget) throws TagException {
        final StatefulContext boundContext = xidget.getFeature(IBindFeature.class).getBoundContext();
        if (boundContext == null) {
            return null;
        }
        final ILayoutFeature layoutFeature = xidget.getParent().getFeature(ILayoutFeature.class);
        if (layoutFeature != null) {
            layoutFeature.invalidate();
        }
        this.destroy(xidget);
        final IModelObject config = xidget.getConfig();
        TagException ex = null;
        try {
            this.processor.process(new ParentTagHandler(xidget.getParent()), new StatefulContext(boundContext, config));
        }
        catch (TagException ex2) {
            ex = ex2;
        }
        xidget = this.findXidget(config);
        this.build(xidget);
        xidget.getFeature(IBindFeature.class).bind(boundContext);
        if (ex != null) {
            throw ex;
        }
        return xidget;
    }
    
    public List<IXidget> parse(final StatefulContext statefulContext) throws TagException {
        final List<Object> process = this.processor.process(statefulContext);
        final ArrayList list = new ArrayList<IXidget>(process.size());
        final Iterator<IXidget> iterator = process.iterator();
        while (iterator.hasNext()) {
            list.add((IXidget)iterator.next());
        }
        return (List<IXidget>)list;
    }
    
    public List<IXidget> parse(final IXidget xidget, final StatefulContext statefulContext) throws TagException {
        final ArrayList<Object> list = new ArrayList<Object>(xidget.getChildren());
        this.processor.process(new ParentTagHandler(xidget), statefulContext);
        final ArrayList<IXidget> list2 = new ArrayList<IXidget>(xidget.getChildren());
        list2.removeAll(list);
        return list2;
    }
    
    public static Creator getInstance() {
        Creator creator = Creator.instances.get();
        if (creator == null) {
            creator = new Creator();
            Creator.instances.set(creator);
        }
        return creator;
    }
    
    private class ParentTagHandler implements ITagHandler, IXidgetFeature
    {
        private IXidget xidget;
        
        public ParentTagHandler(final IXidget xidget) {
            this.xidget = xidget;
        }
        
        @Override
        public boolean filter(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) {
            return true;
        }
        
        @Override
        public boolean enter(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) throws TagException {
            return false;
        }
        
        @Override
        public void exit(final TagProcessor tagProcessor, final ITagHandler tagHandler, final IModelObject modelObject) throws TagException {
        }
        
        @Override
        public IXidget getXidget() {
            return this.xidget;
        }
        
        @Override
        public <T> T getFeature(final Class<T> clazz) {
            if (clazz == IXidgetFeature.class) {
                return (T)this;
            }
            return null;
        }
    }
}
