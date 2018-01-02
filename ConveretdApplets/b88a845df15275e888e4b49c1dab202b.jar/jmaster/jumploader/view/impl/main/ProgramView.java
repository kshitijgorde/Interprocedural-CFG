// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.main;

import java.awt.Graphics;
import jmaster.jumploader.view.api.upload.IUploadView;
import javax.accessibility.Accessible;
import jmaster.jumploader.model.api.config.ViewConfig;
import java.awt.GridBagConstraints;
import jmaster.util.swing.GUIHelper;
import java.awt.event.ComponentListener;
import jmaster.util.swing.split.SplitInitializer;
import javax.swing.JSplitPane;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import jmaster.jumploader.view.impl.file.list.FileListView;
import jmaster.jumploader.view.impl.file.tree.FileTreeView;
import jmaster.jumploader.view.api.main.IMainView;
import jmaster.jumploader.model.api.B;
import jmaster.jumploader.view.api.file.list.IFileListView;
import jmaster.jumploader.view.api.file.tree.IFileTreeView;
import jmaster.jumploader.view.impl.upload.UploadView;
import jmaster.jumploader.view.api.main.IProgramView;
import jmaster.jumploader.view.impl.GenericView;

public class ProgramView extends GenericView implements IProgramView
{
    private static final long \u00db = -905562582839221084L;
    private UploadView \u00d9;
    private IFileTreeView \u00d8;
    private IFileListView \u00d6;
    private ThumbnailManagerProgressView \u00da;
    
    public ProgramView(final B b, final IMainView mainView) {
        super(b);
        this.\u00d9 = new UploadView(b, mainView);
        if (!b.H().isDisableLocalFileSystem()) {
            this.\u00d8 = new FileTreeView(b);
            this.\u00d6 = new FileListView(b, mainView);
        }
        this.\u00da = new ThumbnailManagerProgressView(b, mainView);
    }
    
    public void updateView() {
        this.removeAll();
        this.setLayout(new GridBagLayout());
        final GridBagConstraints gbc = this.I.newGBC();
        final ViewConfig h = this.F.H();
        int n = 0;
        Accessible \u00f9 = this.\u00d9;
        Accessible \u00f92 = this.\u00d9;
        if (h.isMainViewFileListViewVisible()) {
            final JSplitPane splitPane = new JSplitPane(0, (Component)this.\u00d6, this.\u00d9);
            splitPane.setOneTouchExpandable(true);
            splitPane.setResizeWeight(h.getMainViewFileListViewHeightPercent() / 100.0);
            splitPane.addComponentListener(new SplitInitializer(h.getMainViewFileListViewHeightPercent() / 100.0));
            \u00f92 = splitPane;
            \u00f9 = splitPane;
        }
        if (h.isMainViewFileTreeViewVisible()) {
            final JSplitPane splitPane2 = new JSplitPane(1, (Component)this.\u00d8, (Component)\u00f92);
            splitPane2.setOneTouchExpandable(true);
            splitPane2.setResizeWeight(h.getMainViewFileTreeViewWidthPercent() / 100.0);
            splitPane2.addComponentListener(new SplitInitializer(h.getMainViewFileTreeViewWidthPercent() / 100.0));
            \u00f9 = splitPane2;
        }
        this.add((Component)\u00f9, this.A(gbc, 0, n++, 1, 1, 1, 1, 1, 18, GUIHelper.INSETS0));
        this.revalidate();
    }
    
    public void destroy() {
        this.\u00d9.destroy();
        this.\u00d9 = null;
        if (this.\u00d6 != null) {
            try {
                this.\u00d6.destroy();
                this.\u00d6 = null;
            }
            catch (Exception ex) {
                this.E.E(ex, ex);
            }
        }
        if (this.\u00d8 != null) {
            try {
                this.\u00d8.destroy();
                this.\u00d8 = null;
            }
            catch (Exception ex2) {
                this.E.E(ex2, ex2);
            }
        }
        super.destroy();
    }
    
    public IFileTreeView getFileTreeView() {
        return this.\u00d8;
    }
    
    public IFileListView getFileListView() {
        return this.\u00d6;
    }
    
    public IUploadView getUploadView() {
        return this.\u00d9;
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        if (!this.\u00da.isInitialized()) {
            this.\u00da.init();
        }
    }
}
