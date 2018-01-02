// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.action;

import edu.berkeley.guir.prefuse.graph.Node;
import edu.berkeley.guir.prefuse.EdgeItem;
import ytdfriends.FriendsLib;
import edu.berkeley.guir.prefuse.AggregateItem;
import ytdfriends.DecoratorItem;
import edu.berkeley.guir.prefuse.VisualItem;
import edu.berkeley.guir.prefuse.FocusManager;
import edu.berkeley.guir.prefuse.ItemRegistry;
import java.awt.Paint;
import edu.berkeley.guir.prefuse.util.ColorLib;
import edu.berkeley.guir.prefuse.util.ColorMap;
import java.awt.Color;
import edu.berkeley.guir.prefusex.community.CommunitySet;
import edu.berkeley.guir.prefuse.focus.FocusSet;
import edu.berkeley.guir.prefuse.action.assignment.ColorFunction;

public class FriendsBrowsingColorFunction extends ColorFunction
{
    private FocusSet mouseSet;
    private FocusSet profileSet;
    private CommunitySet communitySet;
    private Color focusColor;
    private Color highlight1;
    private Color highlight2;
    private Color highlight3;
    private Color border1;
    private Color border2;
    private Color border3;
    private Color grayOut;
    private Color invisible;
    private ColorMap communityColor;
    private ColorMap fadedCommColor;
    private ColorMap highlightMap;
    private ColorMap borderMap;
    
    public FriendsBrowsingColorFunction() {
        this.focusColor = ColorLib.getColor(200, 0, 0);
        this.highlight1 = ColorLib.getColor(255, 142, 102);
        this.highlight2 = ColorLib.getColor(255, 192, 152);
        this.highlight3 = ColorLib.getColor(255, 242, 202);
        this.border1 = this.highlight1.darker();
        this.border2 = this.highlight1.darker();
        this.border3 = this.highlight1.darker();
        this.grayOut = ColorLib.getColor(225, 225, 225);
        this.invisible = ColorLib.getColor(243, 243, 243);
        this.highlightMap = new ColorMap(new Paint[] { this.highlight1, this.highlight2, this.highlight3 }, 0.0, 2.0);
        this.borderMap = new ColorMap(new Paint[] { this.border1, this.border2, this.border3 }, 0.0, 2.0);
    }
    
    public void updateCommunityMap(final CommunitySet community) {
        if (community == null || community.size() == 0) {
            this.communityColor = null;
            this.fadedCommColor = null;
        }
        else {
            final int maxc = community.getMaxCommunityCount();
            this.communityColor = new ColorMap(ColorMap.getCategoryMap(maxc, 0.6f, 0.4f, 1.0f, 125), 0.0, (double)(maxc - 1));
            this.fadedCommColor = new ColorMap(ColorMap.getCategoryMap(maxc, 0.3f, 0.2f, 1.0f, 125), 0.0, (double)(maxc - 1));
            System.out.println("max comm count = " + community.getMaxCommunityCount());
        }
    }
    
    public void run(final ItemRegistry registry, final double frac) {
        final FocusManager fmanager = registry.getFocusManager();
        this.mouseSet = fmanager.getFocusSet((Object)"moused");
        this.profileSet = fmanager.getFocusSet((Object)"clicked");
        this.communitySet = (CommunitySet)fmanager.getFocusSet((Object)"community");
        super.run(registry, frac);
        this.mouseSet = null;
        this.communitySet = null;
    }
    
    public Paint getColor(final VisualItem item) {
        if (item instanceof DecoratorItem) {
            return ColorLib.getColor(127, 137, 164, 125);
        }
        if (item instanceof AggregateItem) {
            return Color.WHITE;
        }
        if (this.profileSet.contains(item.getEntity())) {
            return this.focusColor;
        }
        final int hvalue = FriendsLib.getHighlightValue(item);
        if (item.isHighlighted() && hvalue > 0) {
            final Boolean val = (Boolean)item.getVizAttribute("invert");
            final boolean invert = val != null && val;
            final ColorMap cmap = invert ? this.highlightMap : this.borderMap;
            return cmap.getColor((double)hvalue);
        }
        if (hvalue < 0 && !this.mouseSet.contains(item.getEntity())) {
            return (item instanceof EdgeItem) ? this.invisible : this.grayOut;
        }
        if (item instanceof EdgeItem) {
            return Color.LIGHT_GRAY;
        }
        return Color.BLACK;
    }
    
    public Paint getFillColor(final VisualItem item) {
        if (item instanceof DecoratorItem) {
            final VisualItem dec = ((DecoratorItem)item).getDecorated();
            if (FriendsLib.getHighlightValue(dec) < 0) {
                return ColorLib.getColor(220, 220, 255, 125);
            }
            return ColorLib.getColor(127, 137, 164, 125);
        }
        else if (item instanceof AggregateItem) {
            if (this.communityColor == null) {
                return null;
            }
            final ColorMap cmap = item.isHighlighted() ? this.communityColor : this.fadedCommColor;
            final int idx = this.communitySet.getCommunity((Node)item.getEntity());
            return cmap.getColor((double)idx);
        }
        else {
            final int hvalue = FriendsLib.getHighlightValue(item);
            if (item.isHighlighted() && hvalue >= 0) {
                final Boolean val = (Boolean)item.getVizAttribute("invert");
                final boolean invert = val != null && val;
                final ColorMap cmap2 = invert ? this.borderMap : this.highlightMap;
                return cmap2.getColor((double)hvalue);
            }
            return Color.WHITE;
        }
    }
}
