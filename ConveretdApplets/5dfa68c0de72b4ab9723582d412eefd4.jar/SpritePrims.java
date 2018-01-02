import java.util.Hashtable;
import java.text.DecimalFormat;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

// 
// Decompiled by Procyon v0.5.30
// 

class SpritePrims extends Primitives
{
    static final String[] primlist;
    
    public String[] primlist() {
        return SpritePrims.primlist;
    }
    
    public Object dispatch(final int n, final Object[] array, final LContext lContext) {
        final Sprite who = lContext.who;
        switch (n) {
            case 0: {
                return (who == null) ? new Object[0] : who;
            }
            case 1: {
                lContext.who = ((array[0] instanceof Sprite) ? ((Sprite)array[0]) : null);
                return null;
            }
            case 2: {
                return new Double((who == null) ? 0.0 : who.x);
            }
            case 3: {
                return new Double((who == null) ? 0.0 : who.y);
            }
            case 4: {
                who.x = Logo.aDouble(array[0], lContext);
                return null;
            }
            case 5: {
                who.y = Logo.aDouble(array[0], lContext);
                return null;
            }
            case 6: {
                return new Double(who.screenX());
            }
            case 7: {
                return new Double(who.screenY());
            }
            case 8: {
                return new Double(who.screenX() + who.outImage().getWidth(null));
            }
            case 9: {
                return new Double(who.screenY() + who.outImage().getHeight(null));
            }
            case 10: {
                who.setscreenX(Logo.aDouble(array[0], lContext));
                return null;
            }
            case 11: {
                who.setscreenY(Logo.aDouble(array[0], lContext));
                return null;
            }
            case 12: {
                return new Double(who.outImage().getWidth(null));
            }
            case 13: {
                return new Double(who.outImage().getHeight(null));
            }
            case 14: {
                return who.costume;
            }
            case 15: {
                who.setcostume(array[0], array[1], array[2], lContext);
                who.costumeChanged();
                return null;
            }
            case 16: {
                return new Double(who.scale);
            }
            case 17: {
                who.setscale(array[0], lContext);
                return null;
            }
            case 18: {
                return this.prim_heading(lContext);
            }
            case 19: {
                who.rotationDegrees = Logo.aDouble(array[0], lContext) % 360.0;
                who.costumeChanged();
                return null;
            }
            case 20: {
                return new Double(who.rotationstyle);
            }
            case 21: {
                who.rotationstyle = Logo.anInt(array[0], lContext);
                who.costumeChanged();
                return null;
            }
            case 22: {
                who.show();
                return null;
            }
            case 23: {
                who.hide();
                return null;
            }
            case 24: {
                who.inval();
                return null;
            }
            case 25: {
                return this.prim_containsPoint(array[0], array[1], lContext);
            }
            case 26: {
                return new Double(who.alpha);
            }
            case 27: {
                who.setalpha(array[0], lContext);
                return null;
            }
            case 28: {
                return new Double(who.color);
            }
            case 29: {
                who.color = Logo.aDouble(array[0], lContext);
                who.filterChanged = true;
                return null;
            }
            case 30: {
                return new Double(who.brightness);
            }
            case 31: {
                who.brightness = Logo.aDouble(array[0], lContext);
                who.filterChanged = true;
                return null;
            }
            case 32: {
                return new Double(who.fisheye);
            }
            case 33: {
                who.fisheye = Logo.aDouble(array[0], lContext);
                who.filterChanged = true;
                return null;
            }
            case 34: {
                return new Double(who.whirl);
            }
            case 35: {
                who.whirl = Logo.aDouble(array[0], lContext);
                who.filterChanged = true;
                return null;
            }
            case 36: {
                return new Double(who.mosaic);
            }
            case 37: {
                who.mosaic = Logo.aDouble(array[0], lContext);
                who.filterChanged = true;
                return null;
            }
            case 38: {
                return new Double(who.pixelate);
            }
            case 39: {
                who.pixelate = Logo.aDouble(array[0], lContext);
                who.filterChanged = true;
                return null;
            }
            case 40: {
                Toolkit.getDefaultToolkit().beep();
                return null;
            }
            case 41: {
                return SoundPlayer.startSound(array[0], who, lContext);
            }
            case 42: {
                return new Boolean(SoundPlayer.isSoundPlaying(array[0]));
            }
            case 43: {
                SoundPlayer.stopSound(array[0]);
                return null;
            }
            case 44: {
                SoundPlayer.stopSoundsForApplet(lContext);
                return null;
            }
            case 45: {
                who.setPenDown(Logo.aBoolean(array[0], lContext));
                return null;
            }
            case 46: {
                if (array[0] instanceof Color) {
                    who.setPenColor((Color)array[0]);
                }
                return null;
            }
            case 47: {
                return new Double(who.penSize);
            }
            case 48: {
                who.penSize = Logo.anInt(array[0], lContext);
                return null;
            }
            case 49: {
                return new Double(who.penHue);
            }
            case 50: {
                who.setPenHue(Logo.aDouble(array[0], lContext));
                return null;
            }
            case 51: {
                return new Double(who.penShade);
            }
            case 52: {
                who.setPenShade(Logo.aDouble(array[0], lContext));
                return null;
            }
            case 53: {
                lContext.canvas.clearPenTrails();
                return null;
            }
            case 54: {
                lContext.canvas.stampCostume(who);
                return null;
            }
            case 55: {
                return this.prim_newcolor(array[0], array[1], array[2], lContext);
            }
            case 56: {
                return new Boolean(who.touchingSprite(array[0], lContext));
            }
            case 57: {
                return new Boolean(who.touchingColor(array[0], lContext));
            }
            case 58: {
                return new Boolean(who.colorTouchingColor(array[0], array[1], lContext));
            }
            case 59: {
                return new Boolean(array[0] instanceof Sprite && ((Sprite)array[0]).isShowing());
            }
            case 60: {
                who.talkbubble(array[0], false, true, lContext);
                return null;
            }
            case 61: {
                who.talkbubble(array[0], false, false, lContext);
                return null;
            }
            case 62: {
                who.updateBubble();
                return null;
            }
            case 63: {
                return new Boolean(array[0] instanceof Watcher);
            }
            case 64: {
                this.prim_setWatcherXY(array[0], array[1], array[2], lContext);
                return null;
            }
            case 65: {
                this.prim_setWatcherColorAndLabel(array[0], array[1], array[2], lContext);
                return null;
            }
            case 66: {
                this.prim_setWatcherSliderMinMax(array[0], array[1], array[2], lContext);
                return null;
            }
            case 67: {
                this.prim_setWatcherMode(array[0], array[1], lContext);
                return null;
            }
            case 68: {
                this.prim_setWatcherText(array[0], array[1], lContext);
                return null;
            }
            case 69: {
                return new Boolean(who.isDraggable);
            }
            case 70: {
                who.isDraggable = Logo.aBoolean(array[0], lContext);
                return null;
            }
            case 71: {
                ((Watcher)array[0]).show();
                return null;
            }
            case 72: {
                ((Watcher)array[0]).hide();
                return null;
            }
            case 73: {
                return this.prim_listContents(array[0], lContext);
            }
            case 74: {
                return this.prim_hasKey(array[0], array[1], lContext);
            }
            case 75: {
                return new Boolean(array[0] instanceof ListWatcher);
            }
            case 76: {
                return this.prim_newListWatcher(lContext);
            }
            case 77: {
                this.prim_setListWatcherXY(array[0], array[1], array[2], lContext);
                return null;
            }
            case 78: {
                this.prim_setListWatcherWidthHeight(array[0], array[1], array[2], lContext);
                return null;
            }
            case 79: {
                this.prim_setListWatcherLabel(array[0], array[1], lContext);
                return null;
            }
            case 80: {
                this.prim_setListWatcherList(array[0], array[1], lContext);
                return null;
            }
            case 81: {
                this.prim_highlightListWatcherIndex(array[0], array[1], lContext);
                return null;
            }
            case 82: {
                this.prim_clearListWatcherHighlights(array[0], lContext);
                return null;
            }
            case 83: {
                who.talkbubble(array[0], true, true, lContext);
                return null;
            }
            case 84: {
                lContext.canvas.showAskPrompt(Logo.aString(array[0], lContext));
                return null;
            }
            case 85: {
                lContext.canvas.hideAskPrompt();
                return null;
            }
            case 86: {
                return new Boolean(lContext.canvas.askPromptShowing());
            }
            case 87: {
                return lContext.canvas.lastAnswer;
            }
            case 88: {
                return new Boolean(array[0] instanceof Sprite && ((Sprite)array[0]).isVisible());
            }
            case 89: {
                return this.prim_newVarWatcher(array[0], lContext);
            }
            case 90: {
                return this.prim_watcherX(array[0], lContext);
            }
            case 91: {
                return this.prim_watcherY(array[0], lContext);
            }
            default: {
                return null;
            }
        }
    }
    
    Object prim_heading(final LContext lContext) {
        double n = lContext.who.rotationDegrees % 360.0;
        if (n > 180.0) {
            n -= 360.0;
        }
        return new Double(n);
    }
    
    Object prim_containsPoint(final Object o, final Object o2, final LContext lContext) {
        if (lContext.who == null) {
            return new Boolean(false);
        }
        return new Boolean(lContext.who.containsPoint(Logo.anInt(o, lContext) + 241, 206 - Logo.anInt(o2, lContext)));
    }
    
    Color prim_newcolor(final Object o, final Object o2, final Object o3, final LContext lContext) {
        return new Color(Logo.anInt(o, lContext), Logo.anInt(o2, lContext), Logo.anInt(o3, lContext));
    }
    
    void prim_setWatcherXY(final Object o, final Object o2, final Object o3, final LContext lContext) {
        if (!(o instanceof Watcher)) {
            return;
        }
        final Watcher watcher = (Watcher)o;
        watcher.inval();
        watcher.box.x = Logo.anInt(o2, lContext);
        watcher.box.y = Logo.anInt(o3, lContext);
        watcher.inval();
    }
    
    void prim_setWatcherColorAndLabel(final Object o, final Object o2, final Object o3, final LContext lContext) {
        if (!(o instanceof Watcher)) {
            return;
        }
        final Watcher watcher = (Watcher)o;
        watcher.inval();
        watcher.readout.color = (Color)o2;
        watcher.label = (String)o3;
        watcher.inval();
    }
    
    void prim_setWatcherSliderMinMax(final Object o, final Object o2, final Object o3, final LContext lContext) {
        if (!(o instanceof Watcher)) {
            return;
        }
        final Watcher watcher = (Watcher)o;
        watcher.sliderMin = Logo.aDouble(o2, lContext);
        watcher.sliderMax = Logo.aDouble(o3, lContext);
        watcher.isDiscrete = (Math.round(watcher.sliderMin) == watcher.sliderMin && Math.round(watcher.sliderMax) == watcher.sliderMax);
    }
    
    void prim_setWatcherMode(final Object o, final Object o2, final LContext lContext) {
        if (!(o instanceof Watcher)) {
            return;
        }
        final Watcher watcher = (Watcher)o;
        final int anInt = Logo.anInt(o2, lContext);
        watcher.inval();
        watcher.mode = Math.max(0, Math.min(anInt, 3));
        watcher.inval();
    }
    
    void prim_setWatcherText(final Object o, final Object o2, final LContext lContext) {
        if (!(o instanceof Watcher)) {
            return;
        }
        final Watcher watcher = (Watcher)o;
        String contents = Logo.prs(o2);
        if (o2 instanceof Double) {
            final double doubleValue = (double)o2;
            final double abs = Math.abs(doubleValue);
            DecimalFormat decimalFormat = new DecimalFormat("0.#");
            if (abs < 1.0) {
                decimalFormat = new DecimalFormat("0.######");
            }
            if (abs < 1.0E-5 || abs >= 1000000.0) {
                decimalFormat = new DecimalFormat("0.###E0");
            }
            if (abs == 0.0) {
                decimalFormat = new DecimalFormat("0.#");
            }
            contents = decimalFormat.format(doubleValue);
        }
        if (contents.equals(watcher.readout.contents)) {
            return;
        }
        watcher.inval();
        watcher.readout.contents = contents;
        watcher.inval();
    }
    
    Object prim_listContents(final Object o, final LContext lContext) {
        if (!(o instanceof Object[])) {
            Logo.error("argument must be a list", lContext);
            return "";
        }
        final Object[] array = (Object[])o;
        if (array.length == 0) {
            return "";
        }
        final StringBuffer sb = new StringBuffer(1000);
        boolean b = false;
        for (int i = 0; i < array.length; ++i) {
            Object prs = array[i];
            if (!(prs instanceof String)) {
                prs = Logo.prs(prs);
            }
            if (((String)prs).length() > 1) {
                b = true;
            }
        }
        for (int j = 0; j < array.length; ++j) {
            Object prs2 = array[j];
            if (!(prs2 instanceof String)) {
                prs2 = Logo.prs(prs2);
            }
            sb.append(prs2);
            if (b) {
                sb.append(" ");
            }
        }
        if (b) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
    
    Object prim_hasKey(final Object o, final Object o2, final LContext lContext) {
        final Hashtable hashtable = lContext.props.get(o);
        if (hashtable == null) {
            return new Boolean(false);
        }
        return new Boolean(hashtable.containsKey(o2));
    }
    
    Object prim_newListWatcher(final LContext lContext) {
        final ListWatcher listWatcher = new ListWatcher(lContext);
        final Object[] sprites = new Object[lContext.canvas.sprites.length + 1];
        for (int i = 0; i < lContext.canvas.sprites.length; ++i) {
            sprites[i] = lContext.canvas.sprites[i];
        }
        sprites[sprites.length - 1] = listWatcher;
        lContext.canvas.sprites = sprites;
        return listWatcher;
    }
    
    void prim_setListWatcherXY(final Object o, final Object o2, final Object o3, final LContext lContext) {
        if (!(o instanceof ListWatcher)) {
            return;
        }
        final ListWatcher listWatcher = (ListWatcher)o;
        listWatcher.inval();
        listWatcher.box.x = Logo.anInt(o2, lContext);
        listWatcher.box.y = Logo.anInt(o3, lContext);
        listWatcher.inval();
    }
    
    void prim_setListWatcherWidthHeight(final Object o, final Object o2, final Object o3, final LContext lContext) {
        if (!(o instanceof ListWatcher)) {
            return;
        }
        final ListWatcher listWatcher = (ListWatcher)o;
        listWatcher.inval();
        listWatcher.box.w = Logo.anInt(o2, lContext);
        listWatcher.box.h = Logo.anInt(o3, lContext);
        listWatcher.scrollBarHeight = listWatcher.box.h - 23 - 20;
        listWatcher.pane.w = listWatcher.box.w;
        listWatcher.inval();
    }
    
    void prim_setListWatcherLabel(final Object o, final Object o2, final LContext lContext) {
        if (!(o instanceof ListWatcher)) {
            return;
        }
        final ListWatcher listWatcher = (ListWatcher)o;
        listWatcher.inval();
        listWatcher.listTitle = Logo.aString(o2, lContext);
        listWatcher.inval();
    }
    
    void prim_setListWatcherList(final Object o, final Object o2, final LContext lContext) {
        if (!(o instanceof ListWatcher)) {
            return;
        }
        if (!(o2 instanceof Object[])) {
            return;
        }
        final ListWatcher listWatcher = (ListWatcher)o;
        listWatcher.setList((Object[])o2);
        listWatcher.inval();
    }
    
    void prim_highlightListWatcherIndex(final Object o, final Object o2, final LContext lContext) {
        if (!(o instanceof ListWatcher)) {
            return;
        }
        final ListWatcher listWatcher = (ListWatcher)o;
        listWatcher.highlightIndex(Logo.anInt(o2, lContext));
        listWatcher.inval();
    }
    
    void prim_clearListWatcherHighlights(final Object o, final LContext lContext) {
        if (!(o instanceof ListWatcher)) {
            return;
        }
        final ListWatcher listWatcher = (ListWatcher)o;
        listWatcher.clearHighlights();
        listWatcher.inval();
    }
    
    Watcher prim_newVarWatcher(final Object o, final LContext lContext) {
        return new Watcher(lContext);
    }
    
    Object prim_watcherX(final Object o, final LContext lContext) {
        if (o instanceof Watcher) {
            return new Double(((Watcher)o).box.x);
        }
        if (o instanceof ListWatcher) {
            return new Double(((ListWatcher)o).box.x);
        }
        return new Double(0.0);
    }
    
    Object prim_watcherY(final Object o, final LContext lContext) {
        if (o instanceof Watcher) {
            return new Double(((Watcher)o).box.y);
        }
        if (o instanceof ListWatcher) {
            return new Double(((ListWatcher)o).box.y);
        }
        return new Double(0.0);
    }
    
    static {
        primlist = new String[] { "who", "0", "talkto", "1", "xpos", "0", "ypos", "0", "setx", "1", "sety", "1", "%left", "0", "%top", "0", "%right", "0", "%bottom", "0", "%setleft", "1", "%settop", "1", "%w", "0", "%h", "0", "costume", "0", "setcostume", "3", "%scale", "0", "setscale", "1", "heading", "0", "setheading", "1", "rotationstyle", "0", "setrotationstyle", "1", "show", "0", "hide", "0", "changed", "0", "containsPoint?", "2", "alpha", "0", "setalpha", "1", "color", "0", "setcolor", "1", "brightness", "0", "setbrightness", "1", "fisheye", "0", "setfisheye", "1", "whirl", "0", "setwhirl", "1", "mosaic", "0", "setmosaic", "1", "pixelate", "0", "setpixelate", "1", "beep", "0", "startSound", "1", "isSoundPlaying?", "1", "stopSound", "1", "stopAllSounds", "0", "setPenDown", "1", "setPenColor", "1", "penSize", "0", "setPenSize", "1", "penHue", "0", "setPenHue", "1", "penShade", "0", "setPenShade", "1", "clearPenTrails", "0", "stampCostume", "0", "newcolor", "3", "touchingSprite?", "1", "touchingColor?", "1", "colorTouchingColor?", "2", "isShowing", "1", "talkbubble", "1", "thinkbubble", "1", "updateBubble", "0", "watcher?", "1", "setWatcherXY", "3", "setWatcherColorAndLabel", "3", "setWatcherSliderMinMax", "3", "setWatcherMode", "2", "setWatcherText", "2", "isDraggable", "0", "setDraggable", "1", "showWatcher", "1", "hideWatcher", "1", "listContents", "1", "hasKey", "2", "listWatcher?", "1", "newListWatcher", "0", "setListWatcherXY", "3", "setListWatcherWidthHeight", "3", "setListWatcherLabel", "2", "setListWatcherList", "2", "highlightListWatcherIndex", "2", "clearListWatcherHighlights", "1", "askbubble", "1", "showAskPrompt", "1", "hideAskPrompt", "0", "askPromptShowing?", "0", "lastAnswer", "0", "isVisible", "1", "newVarWatcher", "1", "watcherX", "1", "watcherY", "1" };
    }
}
