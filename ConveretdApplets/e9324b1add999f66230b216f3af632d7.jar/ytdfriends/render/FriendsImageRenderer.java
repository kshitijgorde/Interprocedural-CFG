// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.render;

import ytdfriends.FriendsLib;
import java.awt.Image;
import edu.berkeley.guir.prefuse.graph.Entity;
import edu.berkeley.guir.prefuse.focus.FocusSet;
import edu.berkeley.guir.prefuse.FocusManager;
import edu.berkeley.guir.prefuse.ItemRegistry;
import edu.berkeley.guir.prefuse.VisualItem;
import edu.berkeley.guir.prefuse.render.ImageFactory;
import java.awt.BasicStroke;
import edu.berkeley.guir.prefuse.render.TextImageItemRenderer;

public class FriendsImageRenderer extends TextImageItemRenderer
{
    private static final String PHOTO_FIELD = "photo";
    private static final String NAME_FIELD = "name";
    private BasicStroke stroke1;
    private BasicStroke stroke2;
    private ImageFactory m_bwimages;
    
    public FriendsImageRenderer() {
        this.stroke1 = new BasicStroke(1.0f);
        this.stroke2 = new BasicStroke(2.0f);
        this.setImageAttributeName("photo");
        this.setTextAttributeName("name");
        this.m_bwimages = new ImageFactory();
        this.setRoundedCorner(8, 8);
        this.setHorizontalPadding(4);
        this.setVerticalPadding(1);
    }
    
    public ImageFactory getBlackAndWhiteImageFactory() {
        return this.m_bwimages;
    }
    
    public int getRenderType(final VisualItem item) {
        final ItemRegistry registry = item.getItemRegistry();
        final FocusManager fm = registry.getFocusManager();
        final FocusSet focusSet = fm.getDefaultFocusSet();
        final FocusSet clickSet = fm.getFocusSet((Object)"clicked");
        final FocusSet mouseSet = fm.getFocusSet((Object)"moused");
        final FocusSet searchSet = fm.getFocusSet((Object)"search");
        final Entity entity = item.getEntity();
        final boolean fs = focusSet.contains(entity);
        final boolean cs = clickSet.contains(entity);
        final boolean ms = mouseSet.contains(entity);
        if (ms || item.isHighlighted()) {
            return 3;
        }
        if ((fs || cs) && searchSet.size() > 0) {
            return 1;
        }
        if (fs || cs) {
            return 3;
        }
        if (item.isHighlighted()) {
            return 2;
        }
        return 0;
    }
    
    protected Image getImage(final VisualItem item) {
        if (!this.m_showImages) {
            return null;
        }
        final String imageLoc = this.getImageLocation(item);
        final int[] hvalue = (int[])item.getVizAttribute("highlightValue");
        final int val = (hvalue == null) ? 0 : hvalue[0];
        if (val >= 0) {
            return (imageLoc == null) ? null : this.m_images.getImage(imageLoc);
        }
        return null;
    }
    
    public BasicStroke getStroke(final VisualItem item) {
        if (item.isHighlighted() && !item.isFocus() && FriendsLib.getHighlightValue(item) > 1) {
            return this.stroke1;
        }
        if (item.getSize() > 1.0) {
            return new BasicStroke((int)Math.ceil(2.0 * item.getSize()));
        }
        return this.stroke2;
    }
    
    public void setDrawImages(final boolean s) {
        super.setShowImages(s);
    }
    
    public void setMaxImageDimensions(final int width, final int height) {
        this.m_bwimages.setMaxImageDimensions(width, height);
        super.setMaxImageDimensions(width, height);
    }
}
