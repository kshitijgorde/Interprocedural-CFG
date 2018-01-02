// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.Arrays;
import javax.swing.JOptionPane;
import java.io.FileNotFoundException;
import java.awt.Dimension;
import java.io.File;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.util.List;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.Component;
import javax.swing.ImageIcon;
import java.io.FileFilter;
import java.awt.dnd.DropTarget;

public class ay extends DropTarget
{
    private static boolean a;
    private static w b;
    private static int c;
    private static FileFilter d;
    private static boolean e;
    private static ImageIcon f;
    private static /* synthetic */ boolean g;
    
    public static void a(final w b, final int c, final FileFilter d, final ImageIcon f) {
        if (!ay.g && b == null) {
            throw new AssertionError();
        }
        if (!ay.g && c <= 0) {
            throw new AssertionError();
        }
        ay.b = b;
        ay.c = c;
        ay.d = d;
        ay.a = true;
        ay.f = f;
    }
    
    public static void a() {
        ay.e = false;
    }
    
    public static void b() {
        ay.e = true;
    }
    
    public ay(final Component component, final DropTargetListener dropTargetListener) {
        super(component, null);
        if (!ay.g && !ay.a) {
            throw new AssertionError();
        }
    }
    
    public void drop(final DropTargetDropEvent dropTargetDropEvent) {
        if (!ay.e) {
            return;
        }
        final Transferable transferable;
        if (!(transferable = dropTargetDropEvent.getTransferable()).isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
            dropTargetDropEvent.rejectDrop();
            return;
        }
        dropTargetDropEvent.acceptDrop(dropTargetDropEvent.getDropAction());
        try {
            new Thread(new aF(this, (List)transferable.getTransferData(DataFlavor.javaFileListFlavor))).start();
        }
        catch (UnsupportedFlavorException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex2) {
            ex2.printStackTrace();
        }
    }
    
    private void a(List iterator) {
        iterator = ((List<File>)iterator).iterator();
        while (iterator.hasNext()) {
            final File file;
            if (!(file = iterator.next()).isDirectory()) {
                final String path = file.getPath();
                if (!ay.d.accept(file)) {
                    continue;
                }
                final String s = path;
                Label_0337: {
                    String s2;
                    try {
                        ImageIcon f;
                        boolean b;
                        if ((f = new ImageIcon(V.b(s))).getImageLoadStatus() == 4) {
                            f = ay.f;
                            b = true;
                        }
                        else {
                            aA.a(s, new Dimension(f.getIconWidth(), f.getIconHeight()));
                            b = false;
                        }
                        ay.b.a(s, V.a(f, new Dimension(ay.c, ay.c), Z.a), b);
                        break Label_0337;
                    }
                    catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                        s2 = G.c("DROP_FILE_NOT_FOUND") + s;
                    }
                    catch (IOException ex2) {
                        ex2.printStackTrace();
                        s2 = G.c("ZOOM_IO_ERROR");
                    }
                    catch (am am) {
                        am.printStackTrace();
                        s2 = G.c("ZOOM_UNSUPPORTED_FORMAT");
                    }
                    catch (M m) {
                        m.printStackTrace();
                        s2 = G.c("FILE_TOO_LARGE");
                    }
                    catch (ah ah) {
                        ah.printStackTrace();
                        s2 = G.c("FILE_TOO_LARGE");
                    }
                    final String c = G.c("DROP_COULD_NOT_ADD_PHOTO");
                    final String s3 = s2;
                    final String s4 = c;
                    if (!ay.g && (s4 == null || s4.length() <= 0)) {
                        throw new AssertionError();
                    }
                    if (!ay.g && (s3 == null || s3.length() <= 0)) {
                        throw new AssertionError();
                    }
                    JOptionPane.showMessageDialog(this.getComponent(), s3, s4, 0);
                }
                System.out.println("acquired by drag&drop: " + au.a(path));
            }
            else {
                this.a(Arrays.asList(file.listFiles()));
            }
        }
    }
    
    static {
        ay.g = !ay.class.desiredAssertionStatus();
        ay.a = false;
        ay.e = true;
    }
}
