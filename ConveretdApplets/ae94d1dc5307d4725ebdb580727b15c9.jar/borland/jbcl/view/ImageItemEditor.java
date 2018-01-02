// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.view;

import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Component;
import java.io.InputStream;
import borland.jbcl.model.ItemEditSite;
import borland.jbcl.util.Variant;
import borland.jbcl.model.ItemEditor;
import java.awt.Canvas;

public class ImageItemEditor extends Canvas implements ItemEditor
{
    private Variant variant;
    private ItemEditSite site;
    
    public ImageItemEditor() {
        (this.variant = new Variant()).setBinaryStream(null);
    }
    
    public Component getComponent() {
        return this;
    }
    
    public void startEdit(final Object data, final Rectangle bounds, final ItemEditSite editSite) {
        this.site = editSite;
        Component component;
        for (component = ((this.site != null) ? this.site.getSiteComponent() : this); component != null && !(component instanceof Frame); component = component.getParent()) {}
        if (component instanceof Frame) {
            final FileDialog dialog = new FileDialog((Frame)component, Res.getString(4), 0);
            dialog.setDirectory(null);
            dialog.show();
            final String dir = dialog.getDirectory();
            final String file = dialog.getFile();
            if (dir != null && file != null) {
                final String name = String.valueOf(dir).concat(String.valueOf(file));
                try {
                    final FileInputStream stream = new FileInputStream(name);
                    final byte[] bytes = byteArrayFromStream(stream);
                    this.variant.setBinaryStream(new ByteArrayInputStream(bytes));
                    this.site.safeEndEdit(true);
                }
                catch (Exception ex) {}
            }
            else {
                this.site.safeEndEdit(false);
            }
            return;
        }
        throw new RuntimeException(Res.getString(0));
    }
    
    private static byte[] byteArrayFromStream(final InputStream s) throws IOException {
        final int len = s.available();
        final byte[] buf = new byte[len];
        int n;
        for (int count = 0; count < len; count += n) {
            n = s.read(buf, count, len - count);
            if (n < 0) {
                return null;
            }
        }
        return buf;
    }
    
    public Object getValue() {
        return this.variant.getBinaryStream();
    }
    
    public void changeBounds(final Rectangle bounds) {
    }
    
    public boolean canPost() {
        return true;
    }
    
    public void endEdit(final boolean posted) {
    }
    
    public void addKeyListener(final KeyListener l) {
    }
    
    public void removeKeyListener(final KeyListener l) {
    }
}
