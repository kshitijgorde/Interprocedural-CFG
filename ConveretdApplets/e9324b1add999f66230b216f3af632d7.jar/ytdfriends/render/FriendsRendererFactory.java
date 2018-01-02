// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.render;

import edu.berkeley.guir.prefuse.NodeItem;
import edu.berkeley.guir.prefuse.EdgeItem;
import edu.berkeley.guir.prefuse.AggregateItem;
import ytdfriends.DecoratorItem;
import edu.berkeley.guir.prefuse.VisualItem;
import edu.berkeley.guir.prefuse.render.ImageFactory;
import edu.berkeley.guir.prefuse.render.PolygonRenderer;
import edu.berkeley.guir.prefuse.render.Renderer;
import edu.berkeley.guir.prefuse.Display;
import ytdfriends.FriendsPanel;
import edu.berkeley.guir.prefuse.render.RendererFactory;

public class FriendsRendererFactory implements RendererFactory
{
    private double scaleThreshold;
    private FriendsPanel fPanel;
    private Display display;
    private FriendsImageRenderer imageRenderer;
    private FriendsImageRenderer imageRenderer2;
    private FriendsImageRenderer compareRenderer;
    private Renderer edgeRenderer;
    private PolygonRenderer polyRenderer;
    private AuraRenderer auraRenderer;
    
    public FriendsRendererFactory(final FriendsPanel fPanel) {
        this.scaleThreshold = 3.0;
        this.fPanel = fPanel;
        this.display = fPanel.getDisplay();
        (this.imageRenderer2 = new FriendsImageRenderer()).setMaxImageDimensions(150, 150);
        this.imageRenderer2.setImageSize(0.2);
        this.imageRenderer2.setHorizontalPadding(2);
        this.imageRenderer = new FriendsImageRenderer();
        final ImageFactory bw1 = this.imageRenderer.getBlackAndWhiteImageFactory();
        final ImageFactory if2 = this.imageRenderer2.getImageFactory();
        final ImageFactory bw2 = this.imageRenderer2.getBlackAndWhiteImageFactory();
        this.imageRenderer.setImageFactory((ImageFactory)new SharingImageFactory(if2, bw1, bw2));
        this.imageRenderer.setMaxImageDimensions(30, 30);
        this.imageRenderer.setHorizontalPadding(2);
        (this.compareRenderer = new FriendsImageRenderer() {
            public int getRenderType(final VisualItem item) {
                int rt = super.getRenderType(item);
                if (rt == 0 || rt == 1) {
                    rt = 2;
                }
                return rt;
            }
        }).setDrawImages(false);
        this.compareRenderer.setRoundedCorner(8, 8);
        this.compareRenderer.setHorizontalPadding(2);
        this.edgeRenderer = (Renderer)new FriendsEdgeRenderer();
        this.polyRenderer = new PolygonRenderer(1);
        this.auraRenderer = new AuraRenderer(fPanel);
    }
    
    public void setScaleThreshold(final double scale) {
        this.scaleThreshold = scale;
    }
    
    public Renderer getRenderer(final VisualItem item) {
        if (item instanceof DecoratorItem) {
            return (Renderer)this.auraRenderer;
        }
        if (item instanceof AggregateItem) {
            if (this.fPanel.isXRayMode()) {
                this.polyRenderer.setRenderType(1);
            }
            else {
                this.polyRenderer.setRenderType(3);
            }
            return (Renderer)this.polyRenderer;
        }
        if (item instanceof EdgeItem) {
            return this.edgeRenderer;
        }
        if (!(item instanceof NodeItem)) {
            return null;
        }
        if (this.fPanel.isXRayMode()) {
            return (Renderer)this.compareRenderer;
        }
        return (Renderer)this.imageRenderer;
    }
    
    public void setDrawImages(final boolean s) {
        this.imageRenderer.setDrawImages(s);
    }
}
