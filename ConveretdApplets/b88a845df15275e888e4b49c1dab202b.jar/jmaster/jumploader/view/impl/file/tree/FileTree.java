// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.file.tree;

import java.util.regex.Pattern;
import java.io.File;
import javax.swing.tree.TreePath;
import jmaster.jumploader.model.api.file.IFileBrowser;
import java.awt.event.KeyEvent;
import jmaster.util.swing.filetree.FileTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import jmaster.jumploader.model.api.config.ViewConfig;
import javax.swing.TransferHandler;
import jmaster.jumploader.view.impl.file.tree.dnd.FileTreeTransferHandler;
import jmaster.jumploader.model.api.B;
import jmaster.util.log.A;
import jmaster.jumploader.model.api.file.IFileBrowserListener;
import java.io.FileFilter;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class FileTree extends jmaster.util.swing.filetree.FileTree implements MouseListener, ActionListener, KeyListener, FileFilter, IFileBrowserListener
{
    private static final long Y = 2742688657779462599L;
    protected A X;
    private B W;
    private FileTreeMenu V;
    
    public FileTree(final B w) {
        this.X = jmaster.util.log.B.getInstance().getLog(this.getClass());
        this.W = w;
        jmaster.util.C.B.A(this, "init");
    }
    
    public void init() {
        final ViewConfig h = this.W.H();
        this.V = new FileTreeMenu();
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.V.getItemRefresh().addActionListener(this);
        this.W.G().addListener(this);
        this.setDragEnabled(true);
        this.setTransferHandler(new FileTreeTransferHandler());
        this.A(ViewConfig.getFilesFromPropertyString(h.getFileTreeViewRootFiles()));
        this.getFileTreeCellRenderer().setRenderFileLength(h.isFileTreeViewShowFileLength());
        super.getFileTreeModel().setFileFilter(this);
        this.expandRow(0);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.A(mouseEvent);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.A(mouseEvent);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.V.getItemRefresh())) {
            final Object lastPathComponent = this.getSelectionPath().getLastPathComponent();
            if (lastPathComponent != null && lastPathComponent instanceof FileTreeNode) {
                this.refresh();
            }
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 116) {
            this.refresh();
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void fileBrowserLocationChanged(final IFileBrowser fileBrowser) {
        this.navigateTo(fileBrowser.getPath());
    }
    
    public void fileBrowserLocationFilesListed(final IFileBrowser fileBrowser) {
    }
    
    private void A(final MouseEvent mouseEvent) {
        if (mouseEvent.isPopupTrigger()) {
            final int rowForLocation = this.getRowForLocation(mouseEvent.getX(), mouseEvent.getY());
            if (rowForLocation != -1) {
                final TreePath pathForRow = this.getPathForRow(rowForLocation);
                if (!this.isPathSelected(pathForRow)) {
                    this.setSelectionPath(pathForRow);
                }
                if (pathForRow.getLastPathComponent() instanceof FileTreeNode) {
                    this.V.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
                }
            }
        }
    }
    
    public boolean accept(final File file) {
        final ViewConfig h = this.W.H();
        boolean b = !file.isHidden() || h.isFileTreeViewShowHiddenFiles();
        if (b && file.isFile()) {
            if (!h.isFileTreeViewShowFiles()) {
                b = false;
            }
            else if (h.getFileNamePattern() != null && !Pattern.matches(h.getFileNamePattern(), file.getName())) {
                b = false;
            }
        }
        return b;
    }
}
