// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.view;

import java.awt.event.KeyListener;
import borland.jbcl.model.ItemEditSite;
import java.awt.Rectangle;
import java.awt.Component;
import borland.jbcl.util.Variant;
import borland.jbcl.model.ToggleItemEditor;

public class BooleanToggleItemEditor implements ToggleItemEditor
{
    protected int type;
    protected boolean state;
    
    public BooleanToggleItemEditor() {
        this.type = 1;
        this.state = false;
    }
    
    public Object getValue() {
        switch (this.type) {
            case 1: {
                return new Boolean(this.state);
            }
            case 2: {
                final Variant v = new Variant();
                v.setBoolean(this.state);
                return v;
            }
            case 3: {
                return String.valueOf(this.state);
            }
            case 4: {
                return new Integer(this.state ? 1 : 0);
            }
            default: {
                return new Boolean(false);
            }
        }
    }
    
    public Component getComponent() {
        return null;
    }
    
    public void startEdit(final Object data, final Rectangle bounds, final ItemEditSite site) {
        this.toggle(data);
        site.safeEndEdit(true);
    }
    
    public void changeBounds(final Rectangle bounds) {
    }
    
    public boolean canPost() {
        return true;
    }
    
    public void endEdit(final boolean post) {
    }
    
    public void addKeyListener(final KeyListener l) {
    }
    
    public void removeKeyListener(final KeyListener l) {
    }
    
    protected void toggle(final Object data) {
        if (data instanceof Boolean) {
            this.type = 1;
            this.state = !(boolean)data;
        }
        else if (data instanceof Variant) {
            this.type = 2;
            if (((Variant)data).getType() == 11) {
                this.state = !((Variant)data).getBoolean();
            }
            else {
                this.state = !Boolean.valueOf(data.toString());
            }
        }
        else if (data instanceof String) {
            this.type = 3;
            this.state = !Boolean.valueOf((String)data);
        }
        else if (data instanceof Integer) {
            this.type = 4;
            this.state = data.equals(new Integer(0));
        }
    }
}
