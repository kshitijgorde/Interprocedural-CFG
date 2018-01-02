// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.upload;

import jmaster.util.log.A;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import jmaster.jumploader.model.api.common.IListSelection;
import jmaster.jumploader.model.api.upload.IUploader;
import jmaster.jumploader.model.api.upload.IUploadFile;
import jmaster.jumploader.view.api.upload.IUploadViewListener;
import java.awt.GridBagConstraints;
import javax.swing.Icon;
import jmaster.jumploader.model.api.config.ViewConfig;
import java.awt.datatransfer.DataFlavor;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import java.awt.Component;
import jmaster.util.swing.GUIHelper;
import java.awt.LayoutManager;
import javax.swing.ImageIcon;
import java.net.URL;
import jmaster.jumploader.view.api.main.IMainView;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import jmaster.util.swing.input.ActionLabel;
import jmaster.jumploader.view.impl.upload.list.UploadFileList;
import jmaster.jumploader.model.api.common.IListSelectionListener;
import jmaster.jumploader.view.impl.upload.list.IUploadFileListListener;
import javax.swing.event.ListSelectionListener;
import jmaster.jumploader.model.api.upload.C;
import jmaster.jumploader.model.api.upload.B;
import java.awt.event.ActionListener;
import jmaster.jumploader.view.api.upload.IUploadView;
import jmaster.jumploader.view.impl.TitledView;

public class UploadView extends TitledView implements IUploadView, ActionListener, B, C, ListSelectionListener, IUploadFileListListener, IListSelectionListener
{
    private static final long \u0131 = 8268631162146549122L;
    public static final String PREFIX = "uploadView";
    private static final String \u0138 = "\\$\\{fileCount\\}";
    private static final String \u0129 = "\\$\\{filesLength\\}";
    private static final String \u014e = "\\$\\{selectedFileCount\\}";
    private static final String \u0133 = "\\$\\{selectedFilesLength\\}";
    private UploadFileList \u0139;
    private ActionLabel \u0145;
    private ActionLabel \u013c;
    private JLabel \u013d;
    private JMenuBar \u014b;
    private JMenuItem \u0146;
    private JMenuItem \u012e;
    private JMenuItem \u0130;
    private JMenu \u013e;
    private JMenuItem \u013a;
    private JMenuItem \u0134;
    private JMenuItem \u0137;
    private JMenuItem \u0142;
    private JMenuItem \u0143;
    private JLabel \u012c;
    private JLabel \u012d;
    private JLabel \u013f;
    private JLabel \u014d;
    private JPanel \u014f;
    private JPanel \u0147;
    private UploadProgressView \u012a;
    protected JFileChooser \u014a;
    private String \u0132;
    private String \u0128;
    private String \u0141;
    private String \u0127;
    private String \u012b;
    private String \u0136;
    private String \u013b;
    private JButton \u0144;
    private JButton \u012f;
    private JPanel \u0135;
    private JLabel \u014c;
    private JLabel \u0149;
    private JButton \u0140;
    private long \u0148;
    static /* synthetic */ Class class$javax$swing$JMenu;
    
    public UploadView(final jmaster.jumploader.model.api.B b, final IMainView mainView) {
        super(b);
        this.\u0145 = new ActionLabel();
        this.\u013c = new ActionLabel();
        this.\u013d = new JLabel();
        this.\u014b = new JMenuBar();
        this.\u0146 = new JMenuItem();
        this.\u012e = new JMenuItem();
        this.\u0130 = new JMenuItem();
        this.\u013e = new JMenu();
        this.\u013a = new JMenuItem();
        this.\u0134 = new JMenuItem();
        this.\u0137 = new JMenuItem();
        this.\u0142 = new JMenuItem();
        this.\u0143 = new JMenuItem();
        this.\u012c = new JLabel();
        this.\u012d = new JLabel();
        this.\u013f = new JLabel();
        this.\u014d = new JLabel();
        this.\u014f = new JPanel();
        this.\u0147 = new JPanel();
        this.\u0135 = new JPanel();
        this.\u014c = new JLabel();
        this.\u0149 = new JLabel();
        this.\u0140 = new JButton();
        this.\u0148 = 0L;
        this.\u0139 = new UploadFileList(b, mainView);
        this.\u012a = new UploadProgressView(b);
        this.A(this, "uploadView", null);
        this.A(this.\u0145, "uploadView", "lblStart");
        this.A(this.\u013c, "uploadView", "lblStop");
        this.A(this.\u013d, "uploadView", "lblListStatus");
        this.A(this.\u012c, "uploadView", "lblCountReady");
        this.A(this.\u012d, "uploadView", "lblCountUploading");
        this.A(this.\u013f, "uploadView", "lblCountFinished");
        this.A(this.\u014d, "uploadView", "lblCountFailed");
        this.A(this.\u014b, "uploadView", "menuBar");
        this.A(this.\u0146, "uploadView", "menuPaste");
        this.A(this.\u012e, "uploadView", "menuAdd");
        this.A(this.\u013e, "uploadView", "menuRemove");
        this.A(this.\u013a, "uploadView", "menuItemRemoveSelected");
        this.A(this.\u0134, "uploadView", "menuItemRemoveReady");
        this.A(this.\u0137, "uploadView", "menuItemRemoveFinished");
        this.A(this.\u0142, "uploadView", "menuItemRemoveFailed");
        this.A(this.\u0143, "uploadView", "menuItemRemoveAll");
        this.A(this.\u0130, "uploadView", "menuRetry");
        this.A(this.\u0135, "uploadView", "fileAddPanel");
        this.A(this.\u014c, "uploadView", "fileAddCaption");
        this.A(this.\u0149, "uploadView", "fileAddLabel");
        this.A(this.\u0140, "uploadView", "fileAddStopButton");
        final ViewConfig h = b.H();
        if (h.getUploadViewStartUploadButtonText() != null || h.getUploadViewStartUploadButtonImageUrl() != null) {
            (this.\u0144 = new JButton()).setText(h.getUploadViewStartUploadButtonText());
            if (h.getUploadViewStartUploadButtonImageUrl() != null) {
                Icon icon = null;
                try {
                    icon = new ImageIcon(new URL(h.getUploadViewStartUploadButtonImageUrl()));
                }
                catch (Exception ex) {
                    this.E.E("Failed to load image from url: " + h.getUploadViewStartUploadButtonImageUrl(), ex);
                }
                this.\u0144.setIcon(icon);
            }
            this.A(this.\u0144, null, "genericButton");
        }
        if (h.getUploadViewStopUploadButtonText() != null || h.getUploadViewStopUploadButtonImageUrl() != null) {
            (this.\u012f = new JButton()).setText(h.getUploadViewStopUploadButtonText());
            if (h.getUploadViewStopUploadButtonImageUrl() != null) {
                Icon icon2 = null;
                try {
                    icon2 = new ImageIcon(new URL(h.getUploadViewStopUploadButtonImageUrl()));
                }
                catch (Exception ex2) {
                    this.E.E("Failed to load image from url: " + h.getUploadViewStopUploadButtonImageUrl(), ex2);
                }
                this.\u012f.setIcon(icon2);
            }
            this.A(this.\u012f, null, "genericButton");
        }
        this.getUploader().addListener(this);
        this.getUploader().addAddListener(this);
        this.getUploader().getSelection().addListener(this);
        this.\u0145.addActionListener(this);
        if (this.\u0144 != null) {
            this.\u0144.addActionListener(this);
        }
        this.\u013c.addActionListener(this);
        if (this.\u012f != null) {
            this.\u012f.addActionListener(this);
        }
        this.\u0139.getSelectionModel().addListSelectionListener(this);
        this.\u0139.addListener(this);
        this.\u0146.addActionListener(this);
        this.\u012e.addActionListener(this);
        this.\u013a.addActionListener(this);
        this.\u0134.addActionListener(this);
        this.\u0137.addActionListener(this);
        this.\u0142.addActionListener(this);
        this.\u0143.addActionListener(this);
        this.\u0130.addActionListener(this);
        this.\u0140.addActionListener(this);
        final GridBagConstraints gbc = this.I.newGBC();
        this.\u0135.setLayout(this.I.newGBL());
        this.\u0135.add(this.\u014c, this.A(gbc, 0, 0, 1, 1, 0, 0, 0, 18, GUIHelper.INSETS4));
        this.\u0135.add(this.\u0149, this.A(gbc, 1, 0, 1, 1, 1, 0, 2, 18, GUIHelper.INSETS4));
        this.\u0135.add(this.\u0140, this.A(gbc, 2, 0, 1, 1, 0, 0, 0, 18, GUIHelper.INSETS4));
        this.\u014b.add(this.\u0146);
        this.\u014b.add(this.\u012e);
        this.\u014b.add(this.\u013e);
        this.\u013e.add(this.\u013a);
        this.\u013e.add(this.\u0134);
        this.\u013e.add(this.\u0137);
        this.\u013e.add(this.\u0142);
        this.\u013e.add(this.\u0143);
        this.\u014b.add(this.\u0130);
        final JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(this.I.newGBL());
        panel.add(new JLabel(), this.A(gbc, 0, 0, 1, 1, 1, 1, 1, 18, GUIHelper.INSETS0));
        this.\u014b.add(panel);
        this.\u014b.add(this.\u012c);
        this.\u014b.add(this.\u012d);
        this.\u014b.add(this.\u013f);
        this.\u014b.add(this.\u014d);
        this.\u014f.setLayout(this.I.newGBL());
        this.\u014f.add(this.\u013d, this.A(gbc, 0, 0, 1, 1, 1, 0, 2, 18, GUIHelper.INSETS4));
        this.\u014f.add(this.\u012a, this.A(gbc, 0, 1, 1, 1, 1, 1, 1, 18, GUIHelper.INSETS0));
        this.\u014f.add(new JLabel(), this.A(gbc, 0, 2, 1, 1, 1, 1, 1, 18, GUIHelper.INSETS0));
        this.\u0147.setLayout(this.I.newGBL());
        this.\u0147.add(this.M(), this.A(gbc, 0, 0, 1, 1, 0, 0, 0, 18, GUIHelper.INSETS8));
        this.\u0147.add(this.L(), this.A(gbc, 1, 0, 1, 1, 0, 0, 0, 18, GUIHelper.INSETS8));
        this.\u0147.add(this.\u014f, this.A(gbc, 2, 0, 1, 1, 1, 1, 1, 18, GUIHelper.INSETS0));
        final JPanel contentPanel = this.getContentPanel();
        contentPanel.add(this.\u0135, this.A(gbc, 0, h.getUploadViewMenuBarY() * 10 + 1, 1, 1, 1, 0, 2, 18, GUIHelper.INSETS0));
        contentPanel.add(this.\u014b, this.A(gbc, 0, h.getUploadViewMenuBarY() * 10, 1, 1, 1, 0, 2, 18, GUIHelper.INSETS0));
        contentPanel.add(new JScrollPane(this.\u0139), this.A(gbc, 0, h.getUploadViewListY() * 10, 1, 1, 1, 1, 1, 18, GUIHelper.INSETS0));
        contentPanel.add(this.\u0147, this.A(gbc, 0, h.getUploadViewControlPanelY() * 10, 1, 1, 1, 0, 2, 18, GUIHelper.INSETS0));
        final jmaster.util.B.B b2 = new jmaster.util.B.B();
        b2.A(new Runnable() {
            public void run() {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        Thread.sleep(500L);
                        UploadView.this.\u0146.setEnabled(Toolkit.getDefaultToolkit().getSystemClipboard().isDataFlavorAvailable(DataFlavor.javaFileListFlavor));
                    }
                }
                catch (InterruptedException ex2) {}
                catch (Exception ex) {
                    UploadView.this.E.E(ex, ex);
                }
            }
        });
        b2.A("uploadFileList_ClipboardMon");
        b2.A(true);
        b2.B(true);
        b.C().A(b2);
        this.updateView();
    }
    
    public void setMessageManyFiles(final String \u0142) {
        this.\u0141 = \u0142;
    }
    
    public void setMessageNoFiles(final String \u0133) {
        this.\u0132 = \u0133;
    }
    
    public void setMessageSelectedManyFiles(final String \u012b) {
        this.\u012b = \u012b;
    }
    
    public void setMessageSelectedSingleFile(final String \u0127) {
        this.\u0127 = \u0127;
    }
    
    public void setMessageSingleFile(final String \u0129) {
        this.\u0128 = \u0129;
    }
    
    public void setMessageUploadFinishedOk(final String \u0137) {
        this.\u0136 = \u0137;
    }
    
    public void setMessageUploadFinishedWithErrors(final String \u013c) {
        this.\u013b = \u013c;
    }
    
    public JFileChooser getFileChooser() {
        return this.\u014a;
    }
    
    public JPanel getInfoPanel() {
        return this.\u014f;
    }
    
    public JLabel getLblCountFailed() {
        return this.\u014d;
    }
    
    public JLabel getLblCountFinished() {
        return this.\u013f;
    }
    
    public JLabel getLblCountReady() {
        return this.\u012c;
    }
    
    public JLabel getLblCountUploading() {
        return this.\u012d;
    }
    
    public JLabel getLblListStatus() {
        return this.\u013d;
    }
    
    public ActionLabel getLblStart() {
        return this.\u0145;
    }
    
    public ActionLabel getLblStop() {
        return this.\u013c;
    }
    
    public UploadFileList getList() {
        return this.\u0139;
    }
    
    public JMenuItem getMenuAdd() {
        return this.\u012e;
    }
    
    public JMenuBar getMenuBar() {
        return this.\u014b;
    }
    
    public JMenuItem getMenuItemRemoveAll() {
        return this.\u0143;
    }
    
    public JMenuItem getMenuItemRemoveFailed() {
        return this.\u0142;
    }
    
    public JMenuItem getMenuItemRemoveFinished() {
        return this.\u0137;
    }
    
    public JMenuItem getMenuItemRemoveReady() {
        return this.\u0134;
    }
    
    public JMenuItem getMenuItemRemoveSelected() {
        return this.\u013a;
    }
    
    public JMenu getMenuRemove() {
        return this.\u013e;
    }
    
    public JMenuItem getMenuRetry() {
        return this.\u0130;
    }
    
    public String getMessageManyFiles() {
        return this.\u0141;
    }
    
    public String getMessageNoFiles() {
        return this.\u0132;
    }
    
    public String getMessageSelectedManyFiles() {
        return this.\u012b;
    }
    
    public String getMessageSelectedSingleFile() {
        return this.\u0127;
    }
    
    public String getMessageSingleFile() {
        return this.\u0128;
    }
    
    public String getMessageUploadFinishedOk() {
        return this.\u0136;
    }
    
    public String getMessageUploadFinishedWithErrors() {
        return this.\u013b;
    }
    
    public UploadProgressView getProgressView() {
        return this.\u012a;
    }
    
    public JPanel getCmdPanel() {
        return this.\u0147;
    }
    
    public JMenuItem getMenuPaste() {
        return this.\u0146;
    }
    
    private void N() {
        for (int i = 0; i < this.H.C(); ++i) {
            ((IUploadViewListener)this.H.A(i)).uploadViewStartAction(this);
        }
    }
    
    private void O() {
        for (int i = 0; i < this.H.C(); ++i) {
            ((IUploadViewListener)this.H.A(i)).uploadViewStopAction(this);
        }
    }
    
    private void D(final IUploadFile[] array) {
        for (int i = 0; i < this.H.C(); ++i) {
            ((IUploadViewListener)this.H.A(i)).uploadViewStopFilesAction(this, array);
        }
    }
    
    private void C(final IUploadFile[] array) {
        for (int i = 0; i < this.H.C(); ++i) {
            ((IUploadViewListener)this.H.A(i)).uploadViewRetryFilesAction(this, array);
        }
    }
    
    private void B(final IUploadFile[] array) {
        for (int i = 0; i < this.H.C(); ++i) {
            ((IUploadViewListener)this.H.A(i)).uploadViewRemoveFilesAction(this, array);
        }
    }
    
    public void fireAddFilesAction(final String[] array) {
        for (int i = 0; i < this.H.C(); ++i) {
            ((IUploadViewListener)this.H.A(i)).uploadViewAddFilesAction(this, array);
        }
    }
    
    public void uploaderFileAdditionEnabledChanged(final IUploader uploader) {
        this.updateView();
    }
    
    public void uploaderFileRemovalEnabledChanged(final IUploader uploader) {
        this.updateView();
    }
    
    public void uploaderFileAdded(final IUploader uploader, final IUploadFile uploadFile) {
        this.updateView();
    }
    
    public void uploaderFileRemoved(final IUploader uploader, final IUploadFile uploadFile) {
        this.updateView();
    }
    
    public void uploaderFileMoved(final IUploader uploader, final IUploadFile uploadFile, final int n) {
        this.updateView();
    }
    
    public void uploaderFileStatusChanged(final IUploader uploader, final IUploadFile uploadFile) {
        this.updateView();
    }
    
    public void uploaderFilesReset(final IUploader uploader) {
        this.updateView();
    }
    
    public void uploaderStatusChanged(final IUploader uploader) {
        if (uploader.isReady()) {
            if (uploader.getFileCountByStatus(3) > 0) {
                this.getWarningView().setText(this.\u013b);
            }
            else {
                this.getInfoView().setText(this.\u0136);
            }
        }
        else {
            this.getInfoView().setText(null);
            this.getWarningView().setText(null);
            this.getErrorView().setText(null);
        }
        this.updateView();
    }
    
    public void uploaderUploadEnabledChanged(final IUploader uploader) {
        this.updateView();
    }
    
    public void uploaderFileAddFailed(final IUploader uploader, final jmaster.jumploader.model.api.A.C c) {
    }
    
    public void uploaderFileUpdated(final IUploader uploader, final IUploadFile uploadFile) {
        this.updateView();
    }
    
    public void uploaderFileAddFinished(final IUploader uploader) {
        this.\u0148 = 0L;
        this.updateView();
    }
    
    public void uploaderFileAddStarted(final IUploader uploader) {
        this.\u0148 = System.currentTimeMillis();
        this.updateView();
    }
    
    public void listSelectionChanged(final IListSelection listSelection) {
        this.E.D("listSelectionChanged, indices=" + listSelection.getSelectedIndices());
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.M())) {
            this.N();
        }
        else if (actionEvent.getSource().equals(this.L())) {
            this.O();
        }
        else if (actionEvent.getSource().equals(this.\u013a)) {
            this.B(this.\u0139.getSelectedFiles());
        }
        else if (actionEvent.getSource().equals(this.\u0134)) {
            this.B(this.getUploader().getFilesByStatus(0));
        }
        else if (actionEvent.getSource().equals(this.\u0137)) {
            this.B(this.getUploader().getFilesByStatus(2));
        }
        else if (actionEvent.getSource().equals(this.\u0142)) {
            this.B(this.getUploader().getFilesByStatus(3));
        }
        else if (actionEvent.getSource().equals(this.\u0143)) {
            this.B(this.getUploader().getAllFiles());
        }
        if (actionEvent.getSource().equals(this.\u0130) && this.\u0130.isEnabled()) {
            this.C(this.getUploader().getFilesByStatus(3));
            this.\u0130.setSelected(false);
        }
        if (actionEvent.getSource().equals(this.\u012e) && this.\u012e.isEnabled()) {
            this.showOpenDialog();
        }
        if (actionEvent.getSource().equals(this.\u0146) && this.\u0146.isEnabled()) {
            this.\u0139.pasteFromClipoboard();
        }
        if (actionEvent.getSource().equals(this.\u0140)) {
            this.getUploader().stopAddingFiles();
        }
    }
    
    public void valueChanged(final ListSelectionEvent listSelectionEvent) {
        if (this.getUploader() != null) {
            this.getUploader().getSelection().setSelectedIndices(this.\u0139.getSelectedIndices());
            this.updateView();
        }
    }
    
    public void uflAddFilesAction(final UploadFileList list, final String[] array) {
        this.fireAddFilesAction(array);
    }
    
    public void uflRemoveFilesAction(final UploadFileList list, final IUploadFile[] array) {
        this.B(array);
    }
    
    public void uflRetryUploadAction(final UploadFileList list, final IUploadFile[] array) {
        this.C(array);
    }
    
    public void uflStopUploadAction(final UploadFileList list, final IUploadFile[] array) {
        this.D(array);
    }
    
    public void updateView() {
        if (this.J) {
            return;
        }
        if (!jmaster.util.C.B.A()) {
            jmaster.util.C.B.A(this);
            return;
        }
        final ViewConfig h = this.F.H();
        final IUploader uploader = this.getUploader();
        final boolean fileAdditionEnabled = uploader.isFileAdditionEnabled();
        final boolean fileRemovalEnabled = uploader.isFileRemovalEnabled();
        final int fileCount = uploader.getFileCount();
        final long filesLength = uploader.getFilesLength();
        final int fileCountByStatus = uploader.getFileCountByStatus(0);
        final int fileCountByStatus2 = uploader.getFileCountByStatus(1);
        final int fileCountByStatus3 = uploader.getFileCountByStatus(2);
        final int fileCountByStatus4 = uploader.getFileCountByStatus(3);
        final int[] selectedIndices = uploader.getSelection().getSelectedIndices();
        final boolean b = selectedIndices != null && selectedIndices.length > 0;
        final int n = b ? selectedIndices.length : 0;
        long n2 = 0L;
        final IUploadFile[] array = new IUploadFile[n];
        if (n > 0) {
            for (int i = 0; i < n; ++i) {
                array[i] = uploader.getFile(selectedIndices[i]);
                n2 += array[i].getLength();
            }
        }
        this.\u0135.setVisible(uploader.isAddingFiles() && System.currentTimeMillis() - this.\u0148 > 500L);
        final IUploadFile addFileCurrent = uploader.getAddFileCurrent();
        this.\u0149.setText((addFileCurrent == null) ? null : addFileCurrent.getPath());
        this.\u012a.setVisible(h.isUploadViewProgressPaneVisible() && uploader.isUploading());
        this.M().setVisible(h.isUploadViewStartActionAlwaysVisible() || (h.isUploadViewStartActionVisible() && uploader.isReady()));
        this.M().setEnabled(uploader.canStartUpload());
        this.L().setVisible(h.isUploadViewStopActionAlwaysVisible() || (h.isUploadViewStopActionVisible() && uploader.isUploading()));
        this.L().setEnabled(uploader.canStopUpload());
        this.\u012c.setVisible(h.isUploadViewFilesSummaryBarVisible());
        this.\u012c.setText("" + uploader.getFileCountByStatus(0));
        this.\u012d.setVisible(h.isUploadViewFilesSummaryBarVisible());
        this.\u012d.setText("" + uploader.getFileCountByStatus(1));
        this.\u013f.setVisible(h.isUploadViewFilesSummaryBarVisible());
        this.\u013f.setText("" + uploader.getFileCountByStatus(2));
        this.\u014d.setVisible(h.isUploadViewFilesSummaryBarVisible());
        this.\u014d.setText("" + uploader.getFileCountByStatus(3));
        final StringBuffer sb = new StringBuffer();
        if (fileCount == 0) {
            sb.append(this.A(this.\u0132, fileCount, filesLength, n, n2));
        }
        else {
            if (n > 0) {
                if (n == 1) {
                    sb.append(this.A(this.\u0127, fileCount, filesLength, n, n2));
                }
                else {
                    sb.append(this.A(this.\u012b, fileCount, filesLength, n, n2));
                }
                sb.append(", ");
            }
            if (fileCount == 1) {
                sb.append(this.A(this.\u0128, fileCount, filesLength, n, n2));
            }
            else {
                sb.append(this.A(this.\u0141, fileCount, filesLength, n, n2));
            }
        }
        this.\u013d.setVisible(h.isUploadViewListStatusVisible());
        this.\u013d.setText(sb.toString());
        this.\u014b.setVisible(h.isUploadViewMenuBarVisible());
        this.\u0146.setVisible(h.isUploadViewPasteActionVisible());
        this.\u012e.setVisible(h.isUploadViewAddActionVisible());
        this.\u012e.setEnabled(fileAdditionEnabled);
        this.\u013e.setVisible(h.isUploadViewRemoveActionVisible());
        this.\u013e.setEnabled(fileRemovalEnabled && fileCount > 0 && fileCount > fileCountByStatus2);
        this.\u013a.setEnabled(fileRemovalEnabled && b);
        this.\u0134.setEnabled(fileRemovalEnabled && fileCountByStatus > 0);
        this.\u0137.setEnabled(fileRemovalEnabled && fileCountByStatus3 > 0);
        this.\u0142.setEnabled(fileRemovalEnabled && fileCountByStatus4 > 0);
        this.\u0143.setEnabled(fileRemovalEnabled && fileCount > 0 && fileCount > fileCountByStatus2);
        this.\u0130.setVisible(h.isUploadViewRetryActionVisible());
        this.\u0130.setEnabled(fileCountByStatus4 > 0);
    }
    
    private String A(final String s, final int n, final long n2, final int n3, final long n4) {
        return s.replaceAll("\\$\\{fileCount\\}", "" + n).replaceAll("\\$\\{filesLength\\}", "" + this.I.getLengthFormatted(n2)).replaceAll("\\$\\{selectedFileCount\\}", "" + n3).replaceAll("\\$\\{selectedFilesLength\\}", "" + this.I.getLengthFormatted(n4));
    }
    
    public void hidePopup(final JMenu menu) {
        if (!jmaster.util.C.B.A()) {
            jmaster.util.C.B.C(this, "hidePopup", new Class[] { (UploadView.class$javax$swing$JMenu == null) ? (UploadView.class$javax$swing$JMenu = class$("javax.swing.JMenu")) : UploadView.class$javax$swing$JMenu }, new Object[] { menu });
            return;
        }
        menu.setPopupMenuVisible(false);
    }
    
    public void showOpenDialog() {
        if (!jmaster.util.C.B.A()) {
            jmaster.util.C.B.C(this, "showOpenDialog");
            return;
        }
        if (this.\u014a == null) {
            (this.\u014a = new JFileChooser()).setFileSelectionMode(this.F.B().isDirectoriesEnabled() ? 2 : 0);
            this.\u014a.setMultiSelectionEnabled(true);
            this.\u014a.setFileFilter(new UploadFileFilter(this.F));
        }
        if (this.\u014a.showOpenDialog(this) == 0) {
            final File[] selectedFiles = this.\u014a.getSelectedFiles();
            final String[] array = new String[selectedFiles.length];
            for (int i = 0; i < selectedFiles.length; ++i) {
                array[i] = selectedFiles[i].getAbsolutePath();
            }
            this.fireAddFilesAction(array);
        }
    }
    
    protected Component M() {
        return (Component)((this.\u0144 == null) ? this.\u0145 : this.\u0144);
    }
    
    protected Component L() {
        return (Component)((this.\u012f == null) ? this.\u013c : this.\u012f);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
