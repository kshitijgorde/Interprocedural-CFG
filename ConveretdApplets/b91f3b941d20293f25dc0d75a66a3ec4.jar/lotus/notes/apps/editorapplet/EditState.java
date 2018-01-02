// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editorapplet;

import java.awt.Panel;
import lotus.notes.apps.editor.RTEdit;

class EditState
{
    public RTEdit editor;
    public Panel panel;
    
    EditState(final RTEdit editor, final Panel panel) {
        this.editor = editor;
        this.panel = panel;
    }
}
