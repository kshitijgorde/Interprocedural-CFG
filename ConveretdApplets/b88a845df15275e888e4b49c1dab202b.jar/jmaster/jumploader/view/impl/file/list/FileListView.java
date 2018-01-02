// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.file.list;

import java.awt.event.KeyEvent;
import jmaster.jumploader.model.api.file.IFile;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import jmaster.jumploader.model.api.file.IFileBrowser;
import jmaster.jumploader.view.api.file.list.IFileListViewListener;
import java.io.File;
import java.awt.GridBagConstraints;
import jmaster.util.swing.GUIHelper;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Component;
import jmaster.jumploader.view.api.main.IMainView;
import jmaster.jumploader.model.api.B;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import jmaster.jumploader.model.api.file.IFileBrowserListener;
import jmaster.jumploader.view.api.file.list.IFileListView;
import jmaster.jumploader.view.impl.GenericView;

public class FileListView extends GenericView implements IFileListView, IFileBrowserListener, MouseListener, KeyListener
{
    private static final long e = 8268631162146549122L;
    public static final String PREFIX = "fileListView";
    private FileSystemView d;
    private JPanel b;
    private JLabel c;
    private JTextField Z;
    private FileList a;
    private JScrollPane _;
    
    public FileListView(final B b, final IMainView mainView) {
        super(b);
        this.d = FileSystemView.getFileSystemView();
        this.b = new JPanel();
        this.c = new JLabel();
        this.Z = new JTextField();
        this.a = new FileList(b, mainView);
        this._ = new JScrollPane(this.a);
        this.A(this, "fileListView", null);
        this.A(this.c, "fileListView", "lblLocation");
        this.A(this.Z, "fileListView", "txtLocation");
        b.G().addListener(this);
        this.a.addMouseListener(this);
        this.a.addKeyListener(this);
        this.Z.addKeyListener(this);
        final GridBagConstraints gbc = this.I.newGBC();
        int n = 0;
        this.b.setLayout(new GridBagLayout());
        this.b.add(this.c, this.A(gbc, 0, 0, 1, 1, 0, 0, 0, 18, GUIHelper.INSETS4));
        this.b.add(this.Z, this.A(gbc, 1, 0, 1, 1, 1, 0, 2, 18, GUIHelper.INSETS4));
        this.add(this.b, this.A(gbc, 0, n++, 1, 1, 1, 0, 2, 18, GUIHelper.INSETS0));
        this.add(this._, this.A(gbc, 0, n++, 1, 1, 1, 1, 1, 18, GUIHelper.INSETS0));
        this.updateView();
    }
    
    public JScrollPane getFileBrowserListScroll() {
        return this._;
    }
    
    public FileList getFileList() {
        return this.a;
    }
    
    public FileSystemView getFileSystemView() {
        return this.d;
    }
    
    public JLabel getLblLocation() {
        return this.c;
    }
    
    public JPanel getLocationPanel() {
        return this.b;
    }
    
    public JTextField getTxtLocation() {
        return this.Z;
    }
    
    private void A(final File file) {
        for (int i = 0; i < this.H.C(); ++i) {
            ((IFileListViewListener)this.H.A(i)).fileListViewPathChanged(this, file);
        }
    }
    
    private void B(final File file) {
        for (int i = 0; i < this.H.C(); ++i) {
            ((IFileListViewListener)this.H.A(i)).fileListViewFileDblClicked(this, file);
        }
    }
    
    public void fileBrowserLocationChanged(final IFileBrowser fileBrowser) {
        final File path = fileBrowser.getPath();
        if (this.d.isFileSystem(path)) {
            this.Z.setText(path.getAbsolutePath());
        }
        else {
            this.Z.setText(this.d.getSystemDisplayName(path));
        }
    }
    
    public void fileBrowserLocationFilesListed(final IFileBrowser fileBrowser) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (SwingUtilities.isLeftMouseButton(mouseEvent) && mouseEvent.getClickCount() == 2) {
            final IFile file = (IFile)this.a.getItem(mouseEvent.getPoint());
            if (file != null && (file.isDirectory() || file.isDrive() || !file.isFileSystem())) {
                this.A(file.getFile());
            }
            else {
                this.B(file.getFile());
            }
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getSource().equals(this.a) && 8 == keyEvent.getKeyCode()) {
            final File path = this.F.G().getPath();
            if (path != null) {
                final File parentFile = path.getParentFile();
                if (parentFile != null) {
                    this.A(parentFile);
                }
            }
        }
        if (keyEvent.getSource().equals(this.Z) && 10 == keyEvent.getKeyCode()) {
            final File fileObject = this.d.createFileObject(this.Z.getText());
            if (fileObject.exists()) {
                this.A(fileObject);
            }
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void destroy() {
    }
    
    public String getPath() {
        return this.Z.getText();
    }
    
    public void updateView() {
        if (!jmaster.util.C.B.A()) {
            jmaster.util.C.B.A(this);
            return;
        }
        this.b.setVisible(this.F.H().isFileListViewLocationBarVisible());
    }
}
