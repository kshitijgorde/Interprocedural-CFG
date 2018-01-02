// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import java.io.Writer;
import java.io.OutputStream;
import java.io.Reader;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.Action;
import javax.swing.JEditorPane;
import java.io.Serializable;

public abstract class EditorKit implements Cloneable, Serializable
{
    public abstract Object clone();
    
    public abstract Caret createCaret();
    
    public abstract Document createDefaultDocument();
    
    public void deinstall(final JEditorPane editorPane) {
    }
    
    public abstract Action[] getActions();
    
    public abstract String getContentType();
    
    public abstract ViewFactory getViewFactory();
    
    public void install(final JEditorPane editorPane) {
    }
    
    public abstract void read(final InputStream p0, final Document p1, final int p2) throws IOException, BadLocationException;
    
    public abstract void read(final Reader p0, final Document p1, final int p2) throws IOException, BadLocationException;
    
    public abstract void write(final OutputStream p0, final Document p1, final int p2, final int p3) throws IOException, BadLocationException;
    
    public abstract void write(final Writer p0, final Document p1, final int p2, final int p3) throws IOException, BadLocationException;
}
