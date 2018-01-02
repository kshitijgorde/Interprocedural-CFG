// 
// Decompiled by Procyon v0.5.30
// 

package prefuse;

import prefuse.util.PrefuseConfig;
import java.awt.geom.Rectangle2D;
import prefuse.render.Renderer;
import prefuse.activity.Activity;
import prefuse.action.Action;
import prefuse.data.expression.Expression;
import prefuse.data.expression.parser.ExpressionParser;
import prefuse.util.collections.CompositeIterator;
import java.util.Collections;
import prefuse.visual.expression.VisiblePredicate;
import prefuse.data.Node;
import java.util.Iterator;
import prefuse.data.tuple.CompositeTupleSet;
import prefuse.data.Tuple;
import prefuse.visual.expression.ValidatedPredicate;
import prefuse.visual.tuple.TableDecoratorItem;
import prefuse.visual.AggregateTable;
import prefuse.visual.VisualTree;
import prefuse.visual.tuple.TableEdgeItem;
import prefuse.data.tuple.TupleManager;
import prefuse.visual.tuple.TableNodeItem;
import prefuse.util.PrefuseLib;
import prefuse.visual.VisualItem;
import prefuse.visual.VisualGraph;
import prefuse.data.Schema;
import prefuse.visual.VisualTable;
import prefuse.data.Graph;
import prefuse.data.Tree;
import prefuse.data.Table;
import prefuse.data.expression.Predicate;
import prefuse.visual.VisualTupleSet;
import prefuse.data.tuple.TupleSet;
import prefuse.data.tuple.DefaultTupleSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import prefuse.render.DefaultRendererFactory;
import java.util.ArrayList;
import prefuse.render.RendererFactory;
import prefuse.activity.ActivityMap;
import java.util.Map;

public class Visualization
{
    public static final String ALL_ITEMS;
    public static final String FOCUS_ITEMS;
    public static final String SELECTED_ITEMS;
    public static final String SEARCH_ITEMS;
    private Map m_visual;
    private Map m_source;
    private Map m_focus;
    private ActivityMap m_actions;
    private RendererFactory m_renderers;
    private ArrayList m_displays;
    
    public Visualization() {
        this.m_actions = new ActivityMap();
        this.m_renderers = new DefaultRendererFactory();
        this.m_visual = new LinkedHashMap();
        this.m_source = new HashMap();
        this.m_focus = new HashMap();
        this.m_displays = new ArrayList();
        this.addFocusGroup(Visualization.FOCUS_ITEMS, new DefaultTupleSet());
        this.addFocusGroup(Visualization.SELECTED_ITEMS, new DefaultTupleSet());
    }
    
    public synchronized VisualTupleSet add(final String s, final TupleSet set) {
        return this.add(s, set, null);
    }
    
    public synchronized VisualTupleSet add(final String s, final TupleSet set, final Predicate predicate) {
        if (set instanceof Table) {
            return this.addTable(s, (Table)set, predicate);
        }
        if (set instanceof Tree) {
            return this.addTree(s, (Tree)set, predicate);
        }
        if (set instanceof Graph) {
            return this.addGraph(s, (Graph)set, predicate);
        }
        throw new IllegalArgumentException("Unsupported TupleSet type.");
    }
    
    protected void checkGroupExists(final String s) {
        if (this.m_visual.containsKey(s) || this.m_focus.containsKey(s)) {
            throw new IllegalArgumentException("Group name '" + s + "' already in use");
        }
    }
    
    protected void addDataGroup(final String s, final VisualTupleSet set, final TupleSet set2) {
        this.checkGroupExists(s);
        this.m_visual.put(s, set);
        if (set2 != null) {
            this.m_source.put(s, set2);
        }
    }
    
    public synchronized VisualTable addTable(final String s) {
        final VisualTable visualTable = new VisualTable(this, s);
        this.addDataGroup(s, visualTable, null);
        return visualTable;
    }
    
    public synchronized VisualTable addTable(final String s, final Schema schema) {
        final VisualTable visualTable = new VisualTable(this, s, schema);
        this.addDataGroup(s, visualTable, null);
        return visualTable;
    }
    
    public synchronized VisualTable addTable(final String s, final Table table) {
        return this.addTable(s, table, (Predicate)null);
    }
    
    public synchronized VisualTable addTable(final String s, final Table table, final Predicate predicate) {
        final VisualTable visualTable = new VisualTable(table, this, s, predicate);
        this.addDataGroup(s, visualTable, table);
        return visualTable;
    }
    
    public synchronized VisualTable addTable(final String s, final Table table, final Schema schema) {
        return this.addTable(s, table, null, schema);
    }
    
    public synchronized VisualTable addTable(final String s, final Table table, final Predicate predicate, final Schema schema) {
        final VisualTable visualTable = new VisualTable(table, this, s, predicate, schema);
        this.addDataGroup(s, visualTable, table);
        return visualTable;
    }
    
    public synchronized VisualTable addTable(final VisualTable visualTable) {
        this.addDataGroup(visualTable.getGroup(), visualTable, visualTable.getParentTable());
        visualTable.setVisualization(this);
        return visualTable;
    }
    
    public synchronized VisualGraph addGraph(final String s, final Graph graph) {
        return this.addGraph(s, graph, null);
    }
    
    public synchronized VisualGraph addGraph(final String s, final Graph graph, final Predicate predicate) {
        return this.addGraph(s, graph, predicate, VisualItem.SCHEMA, VisualItem.SCHEMA);
    }
    
    public synchronized VisualGraph addGraph(final String group, final Graph graph, final Predicate predicate, final Schema schema, final Schema schema2) {
        this.checkGroupExists(group);
        final String groupName = PrefuseLib.getGroupName(group, Graph.NODES);
        final String groupName2 = PrefuseLib.getGroupName(group, Graph.EDGES);
        final VisualTable addTable = this.addTable(groupName, graph.getNodeTable(), predicate, schema);
        final VisualTable addTable2 = this.addTable(groupName2, graph.getEdgeTable(), predicate, schema2);
        final VisualGraph visualGraph = new VisualGraph(addTable, addTable2, graph.isDirected(), graph.getNodeKeyField(), graph.getEdgeSourceField(), graph.getEdgeTargetField());
        visualGraph.setVisualization(this);
        visualGraph.setGroup(group);
        this.addDataGroup(group, visualGraph, graph);
        final TupleManager tupleManager = new TupleManager(addTable, visualGraph, TableNodeItem.class);
        final TupleManager tupleManager2 = new TupleManager(addTable2, visualGraph, TableEdgeItem.class);
        addTable.setTupleManager(tupleManager);
        addTable2.setTupleManager(tupleManager2);
        visualGraph.setTupleManagers(tupleManager, tupleManager2);
        return visualGraph;
    }
    
    public synchronized VisualTree addTree(final String s, final Tree tree) {
        return this.addTree(s, tree, null);
    }
    
    public synchronized VisualTree addTree(final String s, final Tree tree, final Predicate predicate) {
        return this.addTree(s, tree, predicate, VisualItem.SCHEMA, VisualItem.SCHEMA);
    }
    
    public synchronized VisualTree addTree(final String group, final Tree tree, final Predicate predicate, final Schema schema, final Schema schema2) {
        this.checkGroupExists(group);
        final String groupName = PrefuseLib.getGroupName(group, Graph.NODES);
        final String groupName2 = PrefuseLib.getGroupName(group, Graph.EDGES);
        final VisualTable addTable = this.addTable(groupName, tree.getNodeTable(), predicate, schema);
        final VisualTable addTable2 = this.addTable(groupName2, tree.getEdgeTable(), predicate, schema2);
        final VisualTree visualTree = new VisualTree(addTable, addTable2, tree.getNodeKeyField(), tree.getEdgeSourceField(), tree.getEdgeTargetField());
        visualTree.setVisualization(this);
        visualTree.setGroup(group);
        this.addDataGroup(group, visualTree, tree);
        final TupleManager tupleManager = new TupleManager(addTable, visualTree, TableNodeItem.class);
        final TupleManager tupleManager2 = new TupleManager(addTable2, visualTree, TableEdgeItem.class);
        addTable.setTupleManager(tupleManager);
        addTable2.setTupleManager(tupleManager2);
        visualTree.setTupleManagers(tupleManager, tupleManager2);
        return visualTree;
    }
    
    public synchronized AggregateTable addAggregates(final String s) {
        return this.addAggregates(s, VisualItem.SCHEMA);
    }
    
    public synchronized AggregateTable addAggregates(final String s, final Schema schema) {
        final AggregateTable aggregateTable = new AggregateTable(this, s, schema);
        this.addDataGroup(s, aggregateTable, null);
        return aggregateTable;
    }
    
    public synchronized VisualTable addDerivedTable(final String s, final String s2, final Predicate predicate, final Schema schema) {
        final VisualTable visualTable = new VisualTable((Table)this.getGroup(s2), this, s, predicate, schema);
        this.addDataGroup(s, visualTable, this.getSourceData(s2));
        return visualTable;
    }
    
    public synchronized VisualTable addDecorators(final String s, final String s2) {
        return this.addDecorators(s, s2, (Predicate)null);
    }
    
    public synchronized VisualTable addDecorators(final String s, final String s2, final Schema schema) {
        return this.addDecorators(s, s2, null, schema);
    }
    
    public synchronized VisualTable addDecorators(final String s, final String s2, final Predicate predicate) {
        final VisualTable addDerivedTable = this.addDerivedTable(s, s2, predicate, VisualItem.SCHEMA);
        addDerivedTable.setTupleManager(new TupleManager(addDerivedTable, null, TableDecoratorItem.class));
        return addDerivedTable;
    }
    
    public synchronized VisualTable addDecorators(final String s, final String s2, final Predicate predicate, final Schema schema) {
        final VisualTable addDerivedTable = this.addDerivedTable(s, s2, predicate, schema);
        addDerivedTable.setTupleManager(new TupleManager(addDerivedTable, null, TableDecoratorItem.class));
        return addDerivedTable;
    }
    
    public synchronized boolean removeGroup(final String s) {
        final TupleSet focusGroup = this.getFocusGroup(s);
        if (focusGroup != null) {
            final Iterator tuples = focusGroup.tuples(ValidatedPredicate.TRUE);
            while (tuples.hasNext()) {
                tuples.next().setValidated(false);
            }
            focusGroup.clear();
            this.m_focus.remove(s);
            return true;
        }
        final TupleSet visualGroup = this.getVisualGroup(s);
        if (visualGroup == null) {
            return false;
        }
        final TupleSet[] array = new TupleSet[this.m_focus.size()];
        this.m_focus.values().toArray(array);
        final Iterator tuples2 = visualGroup.tuples();
        while (tuples2.hasNext()) {
            final VisualItem visualItem = tuples2.next();
            for (int i = 0; i < array.length; ++i) {
                array[i].removeTuple(visualItem);
            }
            visualItem.setValidated(false);
        }
        if (visualGroup instanceof CompositeTupleSet) {
            final Iterator setNames = ((CompositeTupleSet)visualGroup).setNames();
            while (setNames.hasNext()) {
                final String groupName = PrefuseLib.getGroupName(s, setNames.next());
                this.m_visual.remove(groupName);
                this.m_source.remove(groupName);
            }
        }
        this.m_visual.remove(s);
        this.m_source.remove(s);
        return true;
    }
    
    public synchronized void reset() {
        final Iterator<Map.Entry<K, TupleSet>> iterator = this.m_focus.entrySet().iterator();
        while (iterator.hasNext()) {
            iterator.next().getValue().clear();
        }
        this.m_visual.clear();
        this.m_source.clear();
    }
    
    public TupleSet getSourceData(final String s) {
        return this.m_source.get(s);
    }
    
    public TupleSet getSourceData(final VisualTupleSet set) {
        return this.m_source.get(set.getGroup());
    }
    
    public Tuple getSourceTuple(final VisualItem visualItem) {
        final String group = visualItem.getGroup();
        if (this.getSourceData(group) == null) {
            return null;
        }
        int n = visualItem.getRow();
        Table table;
        VisualTable visualTable;
        for (table = visualItem.getTable(); table instanceof VisualTable; table = visualTable.getParentTable()) {
            visualTable = (VisualTable)table;
            n = visualTable.getParentRow(n);
        }
        if (PrefuseLib.getChildGroup(group) == null) {
            return table.getTuple(n);
        }
        final Graph graph = (Graph)this.getSourceData(PrefuseLib.getParentGroup(group));
        if (table == graph.getNodeTable()) {
            return graph.getNode(n);
        }
        return graph.getEdge(n);
    }
    
    public VisualItem getVisualItem(final String s, final Tuple tuple) {
        final TupleSet visualGroup = this.getVisualGroup(s);
        VisualTable visualTable;
        if (visualGroup instanceof VisualTable) {
            visualTable = (VisualTable)visualGroup;
        }
        else {
            if (!(visualGroup instanceof Graph)) {
                return null;
            }
            final Graph graph = (Graph)visualGroup;
            visualTable = (VisualTable)((tuple instanceof Node) ? graph.getNodeTable() : graph.getEdgeTable());
        }
        final int childRow = visualTable.getChildRow(tuple.getRow());
        return (childRow < 0) ? null : visualTable.getItem(childRow);
    }
    
    public TupleSet getGroup(final String s) {
        TupleSet set = this.getVisualGroup(s);
        if (set == null) {
            set = this.getFocusGroup(s);
        }
        return set;
    }
    
    public boolean isInGroup(final VisualItem visualItem, final String s) {
        if (Visualization.ALL_ITEMS.equals(s)) {
            return true;
        }
        if (visualItem.getGroup() == s) {
            return true;
        }
        final TupleSet group = this.getGroup(s);
        return group != null && group.containsTuple(visualItem);
    }
    
    public void addFocusGroup(final String s) {
        this.checkGroupExists(s);
        this.m_focus.put(s, new DefaultTupleSet());
    }
    
    public void addFocusGroup(final String s, final TupleSet set) {
        this.checkGroupExists(s);
        this.m_focus.put(s, set);
    }
    
    public int size(final String s) {
        final TupleSet group = this.getGroup(s);
        return (group == null) ? 0 : group.getTupleCount();
    }
    
    public TupleSet getVisualGroup(final String s) {
        return this.m_visual.get(s);
    }
    
    public TupleSet getFocusGroup(final String s) {
        return this.m_focus.get(s);
    }
    
    public void invalidate(final String s) {
        final Iterator items = this.items(ValidatedPredicate.TRUE);
        while (items.hasNext()) {
            items.next().setValidated(false);
        }
    }
    
    public void invalidateAll() {
        this.invalidate(Visualization.ALL_ITEMS);
    }
    
    public Iterator visibleItems() {
        return this.items(VisiblePredicate.TRUE);
    }
    
    public Iterator visibleItems(final String s) {
        return this.items(s, VisiblePredicate.TRUE);
    }
    
    public Iterator items() {
        return this.items((Predicate)null);
    }
    
    public Iterator items(final Predicate predicate) {
        final int size = this.m_visual.size();
        if (size == 0) {
            return Collections.EMPTY_LIST.iterator();
        }
        if (size == 1) {
            return this.items(this.m_visual.keySet().iterator().next(), predicate);
        }
        final CompositeIterator compositeIterator = new CompositeIterator(this.m_visual.size());
        final Iterator<String> iterator = this.m_visual.keySet().iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final String s = iterator.next();
            if (!PrefuseLib.isChildGroup(s)) {
                compositeIterator.setIterator(n++, this.items(s, predicate));
            }
        }
        return compositeIterator;
    }
    
    public Iterator items(final String s) {
        return this.items(s, (Predicate)null);
    }
    
    public Iterator items(final String s, final String s2) {
        final Expression parse = ExpressionParser.parse(s2);
        if (!(parse instanceof Predicate) || ExpressionParser.getError() != null) {
            return Collections.EMPTY_LIST.iterator();
        }
        return this.items(s, (Predicate)parse);
    }
    
    public Iterator items(final String s, final Predicate predicate) {
        if (Visualization.ALL_ITEMS.equals(s)) {
            return this.items(predicate);
        }
        final TupleSet group = this.getGroup(s);
        return (group == null) ? Collections.EMPTY_LIST.iterator() : group.tuples(predicate);
    }
    
    public void setValue(final String s, final Predicate predicate, final String s2, final Object o) {
        final Iterator items = this.items(s, predicate);
        while (items.hasNext()) {
            items.next().set(s2, o);
        }
    }
    
    public void setVisible(final String s, final Predicate predicate, final boolean visible) {
        final Iterator items = this.items(s, predicate);
        while (items.hasNext()) {
            items.next().setVisible(visible);
        }
    }
    
    public void setInteractive(final String s, final Predicate predicate, final boolean interactive) {
        final Iterator items = this.items(s, predicate);
        while (items.hasNext()) {
            items.next().setInteractive(interactive);
        }
    }
    
    public Action putAction(final String s, final Action action) {
        action.setVisualization(this);
        this.m_actions.put(s, action);
        return action;
    }
    
    public Action getAction(final String s) {
        return (Action)this.m_actions.get(s);
    }
    
    public Activity run(final String s) {
        return this.m_actions.run(s);
    }
    
    public Activity runAfter(final String s, final long n) {
        return this.m_actions.runAt(s, System.currentTimeMillis() + n);
    }
    
    public Activity runAt(final String s, final long n) {
        return this.m_actions.runAt(s, n);
    }
    
    public Activity runAfter(final String s, final String s2) {
        return this.m_actions.runAfter(s, s2);
    }
    
    public Activity alwaysRunAfter(final String s, final String s2) {
        return this.m_actions.alwaysRunAfter(s, s2);
    }
    
    public Activity cancel(final String s) {
        return this.m_actions.cancel(s);
    }
    
    public void setRendererFactory(final RendererFactory renderers) {
        this.invalidateAll();
        this.m_renderers = renderers;
    }
    
    public RendererFactory getRendererFactory() {
        return this.m_renderers;
    }
    
    public Renderer getRenderer(final VisualItem visualItem) {
        if (visualItem.getVisualization() != this) {
            throw new IllegalArgumentException("Input item not a member of this visualization.");
        }
        return this.m_renderers.getRenderer(visualItem);
    }
    
    public synchronized void repaint() {
        final Iterator items = this.items(ValidatedPredicate.FALSE);
        while (items.hasNext()) {
            items.next().validateBounds();
        }
        for (int i = 0; i < this.m_displays.size(); ++i) {
            this.getDisplay(i).repaint();
        }
    }
    
    public Rectangle2D getBounds(final String s) {
        return this.getBounds(s, new Rectangle2D.Double());
    }
    
    public Rectangle2D getBounds(final String s, final Rectangle2D rectangle2D) {
        final Iterator visibleItems = this.visibleItems(s);
        if (visibleItems.hasNext()) {
            rectangle2D.setRect(visibleItems.next().getBounds());
        }
        while (visibleItems.hasNext()) {
            Rectangle2D.union(visibleItems.next().getBounds(), rectangle2D, rectangle2D);
        }
        return rectangle2D;
    }
    
    public int getDisplayCount() {
        return this.m_displays.size();
    }
    
    void addDisplay(final Display display) {
        this.m_displays.add(display);
    }
    
    public Display getDisplay(final int n) {
        return this.m_displays.get(n);
    }
    
    boolean removeDisplay(final Display display) {
        return this.m_displays.remove(display);
    }
    
    public void damageReport(final VisualItem visualItem, final Rectangle2D rectangle2D) {
        for (int i = 0; i < this.m_displays.size(); ++i) {
            final Display display = this.getDisplay(i);
            if (display.getPredicate().getBoolean(visualItem)) {
                display.damageReport(rectangle2D);
            }
        }
    }
    
    static {
        ALL_ITEMS = PrefuseConfig.get("visualization.allItems");
        FOCUS_ITEMS = PrefuseConfig.get("visualization.focusItems");
        SELECTED_ITEMS = PrefuseConfig.get("visualization.selectedItems");
        SEARCH_ITEMS = PrefuseConfig.get("visualization.searchItems");
    }
}
