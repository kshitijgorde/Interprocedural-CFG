// 
// Decompiled by Procyon v0.5.30
// 

package com.syynx.lissi.menus;

import java.awt.Insets;
import prefuse.data.Tuple;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import prefuse.controls.ControlAdapter;
import java.awt.geom.Rectangle2D;
import prefuse.visual.tuple.TableVisualItem;
import prefuse.action.GroupAction;
import java.util.Iterator;
import prefuse.data.expression.Predicate;
import prefuse.data.util.Sort;
import prefuse.action.layout.Layout;
import prefuse.controls.AnchorUpdateControl;
import prefuse.action.RepaintAction;
import prefuse.action.Action;
import prefuse.action.ActionList;
import java.awt.Font;
import prefuse.util.ColorLib;
import prefuse.action.assignment.ColorAction;
import prefuse.render.RendererFactory;
import prefuse.render.Renderer;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.LabelRenderer;
import prefuse.controls.Control;
import prefuse.data.tuple.TupleSet;
import prefuse.Visualization;
import prefuse.visual.VisualItem;
import java.awt.event.ActionListener;
import com.syynx.lissi.RadialGraphApplet;
import prefuse.data.Table;
import prefuse.data.Schema;
import com.syynx.lissi.Colors;
import prefuse.Display;

public class FisheyeMenu extends Display implements Colors
{
    public static final String ITEMS = "items";
    public static final String ITEM = "item";
    public static final String LABEL = "label";
    public static final String ACTION = "action";
    public static final String ID = "ID";
    protected static final Schema ITEM_SCHEMA;
    private Table m_items;
    private double m_maxHeight;
    private double m_scale;
    private SyynxFisheyeDistortion feye;
    private RadialGraphApplet owner;
    private ListControlAdapter lca;
    private CallbackAction cbAction;
    
    static {
        (ITEM_SCHEMA = new Schema()).addColumn("label", String.class);
        FisheyeMenu.ITEM_SCHEMA.addColumn("ID", String.class);
        FisheyeMenu.ITEM_SCHEMA.addColumn("action", ActionListener.class);
        FisheyeMenu.ITEM_SCHEMA.addColumn("item", VisualItem.class);
    }
    
    public FisheyeMenu(final RadialGraphApplet owner) {
        super(new Visualization());
        this.m_items = FisheyeMenu.ITEM_SCHEMA.instantiate();
        this.m_maxHeight = 630.0;
        this.m_scale = 1.0;
        this.m_scale = 1.0;
        this.owner = owner;
        this.cbAction = new CallbackAction(owner);
        this.lca = new ListControlAdapter();
    }
    
    public void run(final String action) {
        this.m_vis.run(action);
    }
    
    public void init(final TupleSet nodes) {
        this.m_vis.reset();
        this.removeControlListener(this.lca);
        this.m_vis.addTable("items", this.m_items);
        final LabelRenderer renderer = new LabelRenderer("label");
        renderer.setHorizontalPadding(0);
        renderer.setVerticalPadding(1);
        renderer.setHorizontalAlignment(0);
        this.m_vis.setRendererFactory(new DefaultRendererFactory(renderer));
        this.setHighQuality(true);
        this.addControlListener(this.lca);
        final ColorAction colors = new ColorAction("items", VisualItem.TEXTCOLOR);
        colors.setDefaultColor(ColorLib.color(FisheyeMenu.colorPanelFrame));
        colors.add("hover()", ColorLib.rgb(255, 0, 0));
        colors.add("_highlight", ColorLib.setAlpha(ColorLib.color(FisheyeMenu.colorPanelFrame), 40));
        double h = 0.0;
        double mw = 0.0;
        int c = 0;
        final Iterator iter = nodes.tuples();
        while (iter.hasNext()) {
            final VisualItem item = iter.next();
            final Font font = new Font("Sans Serif", 0, 12);
            item.setFont(font);
            item.setSize(1.0);
            ++c;
            mw = Math.max(item.getBounds().getWidth(), mw);
            h += item.getBounds().getHeight();
        }
        final double scale = (h > this.m_maxHeight) ? (this.m_maxHeight / h) : 1.0;
        final ActionList init = new ActionList();
        init.add(new VerticalLineLayout(this.m_maxHeight));
        init.add(colors);
        init.add(new RepaintAction());
        this.m_vis.putAction("init", init);
        final ActionList distort = new ActionList();
        distort.add(this.feye = new SyynxFisheyeDistortion(0.0, 2.0 - 2.0 * scale));
        distort.add(colors);
        distort.add(new VisibiltyFilter());
        distort.add(new RepaintAction());
        this.m_vis.putAction("distort", distort);
        this.addControlListener(new AnchorUpdateControl(this.feye, "distort"));
        this.m_items.clear();
        final String[] sortCriteria = { "name" };
        final Iterator i = nodes.tuples(null, new Sort(sortCriteria));
        VisualItem vi = null;
        while (i.hasNext()) {
            vi = i.next();
            this.addMenuItem(vi.getString("name"), vi.getString("id"), vi, this.cbAction);
        }
        this.m_vis.run("init");
    }
    
    public void addMenuItem(final String name, final String id, final VisualItem item, final ActionListener listener) {
        final int row = this.m_items.addRow();
        this.m_items.set(row, "label", name);
        this.m_items.set(row, "ID", id);
        this.m_items.set(row, "action", listener);
        this.m_items.set(row, "item", item);
    }
    
    public class VisibiltyFilter extends GroupAction
    {
        public void run(final double frac) {
            final Iterator i = this.m_vis.items("items");
            final double h = 0.0;
            while (i.hasNext()) {
                final TableVisualItem tvi = i.next();
                final Rectangle2D b = tvi.getBounds();
                tvi.setX(this.m_vis.getDisplay(0).getWidth() - tvi.getBounds().getWidth() - 5.0);
                tvi.setHighlighted(((VisualItem)tvi.get("item")).isHighlighted());
            }
        }
    }
    
    public class ListControlAdapter extends ControlAdapter
    {
        public void itemClicked(final VisualItem item, final MouseEvent e) {
            final ActionListener al = (ActionListener)item.get("action");
            al.actionPerformed(new ActionEvent(item, e.getID(), "click", e.getWhen(), e.getModifiers()));
            System.out.println(item.toString());
        }
        
        public void itemEntered(final VisualItem item, final MouseEvent e) {
            final ActionListener al = (ActionListener)item.get("action");
            al.actionPerformed(new ActionEvent(item, e.getID(), "hover", e.getWhen(), e.getModifiers()));
        }
        
        public void itemExited(final VisualItem item, final MouseEvent e) {
            final ActionListener al = (ActionListener)item.get("action");
            al.actionPerformed(new ActionEvent(item, e.getID(), "leave", e.getWhen(), e.getModifiers()));
        }
    }
    
    public static class CallbackAction extends AbstractAction
    {
        RadialGraphApplet owner;
        
        public CallbackAction(final RadialGraphApplet owner) {
            this.owner = owner;
        }
        
        public void actionPerformed(final ActionEvent e) {
            if (e.getActionCommand() == "click") {
                this.owner.setFocus((Tuple)e.getSource());
            }
            if (e.getActionCommand() == "hover") {
                this.owner.setHover((Tuple)e.getSource());
            }
            if (e.getActionCommand() == "leave") {
                this.owner.clearHover((Tuple)e.getSource());
            }
        }
    }
    
    public class VerticalLineLayout extends Layout
    {
        private double m_maxHeight;
        
        public VerticalLineLayout(final double maxHeight) {
            this.m_maxHeight = 646.0;
            this.m_maxHeight = maxHeight;
        }
        
        public void run(final double frac) {
            double w = 0.0;
            double h = 0.0;
            double mw = 0.0;
            int c = 0;
            Iterator iter = this.m_vis.items();
            while (iter.hasNext()) {
                final VisualItem item = iter.next();
                final Font font = new Font("Sans Serif", 0, 12);
                item.setFont(font);
                item.setSize(1.0);
                ++c;
                mw = Math.max(item.getBounds().getWidth(), mw);
                h += item.getBounds().getHeight();
            }
            final double scale = (h > this.m_maxHeight) ? (this.m_maxHeight / h) : 1.0;
            final Display d = this.m_vis.getDisplay(0);
            final Insets ins = d.getInsets();
            h = ins.top + 10;
            double y = 0.0;
            double x = 0.0;
            iter = this.m_vis.items();
            while (iter.hasNext()) {
                final VisualItem item2 = iter.next();
                item2.setSize(scale);
                item2.setEndSize(scale);
                final Rectangle2D b = item2.getBounds();
                w = Math.max(w, b.getWidth());
                x = d.getWidth() - b.getWidth() - 5.0;
                final double ih = b.getHeight();
                y = h + ih / 2.0;
                this.setX(item2, null, x);
                this.setY(item2, null, y);
                h += ih;
            }
        }
    }
}
