// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.render;

import prefuse.visual.EdgeItem;
import prefuse.data.Tuple;
import prefuse.visual.VisualItem;
import prefuse.data.expression.parser.ExpressionParser;
import prefuse.data.expression.Predicate;
import prefuse.util.PredicateChain;

public class DefaultRendererFactory implements RendererFactory
{
    private PredicateChain m_chain;
    private Renderer m_itemRenderer;
    private Renderer m_edgeRenderer;
    
    public DefaultRendererFactory() {
        this(new ShapeRenderer());
    }
    
    public DefaultRendererFactory(final Renderer renderer) {
        this(renderer, new EdgeRenderer());
    }
    
    public DefaultRendererFactory(final Renderer itemRenderer, final Renderer edgeRenderer) {
        this.m_chain = new PredicateChain();
        this.m_itemRenderer = itemRenderer;
        this.m_edgeRenderer = edgeRenderer;
    }
    
    public void setDefaultRenderer(final Renderer itemRenderer) {
        this.m_itemRenderer = itemRenderer;
    }
    
    public Renderer getDefaultRenderer() {
        return this.m_itemRenderer;
    }
    
    public void setDefaultEdgeRenderer(final Renderer edgeRenderer) {
        this.m_edgeRenderer = edgeRenderer;
    }
    
    public Renderer getDefaultEdgeRenderer() {
        return this.m_edgeRenderer;
    }
    
    public void add(final Predicate predicate, final Renderer renderer) {
        this.m_chain.add(predicate, renderer);
    }
    
    public void add(final String s, final Renderer renderer) {
        this.add((Predicate)ExpressionParser.parse(s), renderer);
    }
    
    public Renderer getRenderer(final VisualItem visualItem) {
        final Renderer renderer = (Renderer)this.m_chain.get(visualItem);
        if (renderer != null) {
            return renderer;
        }
        if (visualItem instanceof EdgeItem) {
            return this.m_edgeRenderer;
        }
        return this.m_itemRenderer;
    }
}
