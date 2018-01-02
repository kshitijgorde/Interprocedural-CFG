// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import de.muntjak.tinylookandfeel.table.SortableTableData;
import javax.swing.table.AbstractTableModel;
import java.util.Date;
import java.text.DateFormat;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.MouseAdapter;
import java.awt.Graphics;
import javax.swing.Icon;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.DefaultListCellRenderer;
import javax.swing.filechooser.FileFilter;
import javax.swing.AbstractListModel;
import javax.swing.plaf.basic.BasicDirectoryModel;
import java.awt.ComponentOrientation;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.TableHeaderUI;
import javax.swing.table.TableColumnModel;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import javax.swing.LookAndFeel;
import javax.swing.TransferHandler;
import java.awt.Rectangle;
import java.awt.Point;
import javax.swing.table.TableCellEditor;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.DefaultCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.ListModel;
import javax.swing.text.Position;
import javax.swing.plaf.ActionMapUIResource;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.SwingUtilities;
import java.util.Locale;
import javax.swing.filechooser.FileSystemView;
import java.beans.PropertyChangeListener;
import sun.awt.shell.ShellFolder;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.Box;
import javax.swing.UIManager;
import de.muntjak.tinylookandfeel.borders.TinyToolButtonBorder;
import javax.swing.ListCellRenderer;
import java.awt.event.ActionListener;
import javax.swing.ComboBoxModel;
import java.awt.Component;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;
import javax.swing.JFileChooser;
import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import java.io.File;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.awt.event.FocusListener;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicFileChooserUI;

public class TinyFileChooserUI extends BasicFileChooserUI
{
    public static final String IS_FILE_CHOOSER_BUTTON_KEY = "JFileChooser.isFileChooserButton";
    private boolean doScrolling;
    private JPanel centerPanel;
    private TinyDirectoryModel directoryModel;
    private JLabel lookInLabel;
    private JComboBox directoryComboBox;
    private DirectoryComboBoxModel directoryComboBoxModel;
    private Action directoryComboBoxAction;
    private FilterComboBoxModel filterComboBoxModel;
    private JTextField fileNameTextField;
    private JToggleButton listViewButton;
    private JToggleButton detailsViewButton;
    private JPanel listViewPanel;
    private JPanel detailsViewPanel;
    private JPanel currentViewPanel;
    private FocusListener editorFocusListener;
    private boolean useShellFolder;
    private ListSelectionModel listSelectionModel;
    private JList list;
    private JTable detailsTable;
    private DetailsTableModel detailsTableModel;
    private JButton approveButton;
    private JButton cancelButton;
    private JPanel buttonPanel;
    private JPanel bottomPanel;
    private JComboBox filterComboBox;
    private static final Dimension hstrut1;
    private static final Dimension hstrut4;
    private static final Dimension hstrut11;
    private static final Dimension vstrut5;
    private static final Insets shrinkwrap;
    private static int PREF_WIDTH;
    private static int PREF_HEIGHT;
    private static Dimension PREF_SIZE;
    private static int MIN_WIDTH;
    private static int MIN_HEIGHT;
    private static Dimension MIN_SIZE;
    private static int LIST_PREF_WIDTH;
    private static int LIST_PREF_HEIGHT;
    private static Dimension LIST_PREF_SIZE;
    private static final int COLUMN_FILENAME = 0;
    private static final int COLUMN_FILESIZE = 1;
    private static final int COLUMN_FILETYPE = 2;
    private static final int COLUMN_FILEDATE = 3;
    private static final int COLUMN_COLCOUNT = 4;
    private int[] COLUMN_WIDTHS;
    private int lookInLabelMnemonic;
    private String lookInLabelText;
    private String saveInLabelText;
    private int fileNameLabelMnemonic;
    private String fileNameLabelText;
    private int filesOfTypeLabelMnemonic;
    private String filesOfTypeLabelText;
    private String upFolderToolTipText;
    private String upFolderAccessibleName;
    private String homeFolderToolTipText;
    private String homeFolderAccessibleName;
    private String newFolderToolTipText;
    private String newFolderAccessibleName;
    private String listViewButtonToolTipText;
    private String listViewButtonAccessibleName;
    private String detailsViewButtonToolTipText;
    private String detailsViewButtonAccessibleName;
    private String fileNameHeaderText;
    private String fileSizeHeaderText;
    private String fileTypeHeaderText;
    private String fileDateHeaderText;
    private String fileAttrHeaderText;
    int lastIndex;
    File editFile;
    int editX;
    JTextField editCell;
    static final int space = 10;
    static /* synthetic */ Class class$java$io$File;
    static /* synthetic */ Class class$java$util$Date;
    static /* synthetic */ Class class$java$lang$Long;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$java$lang$Comparable;
    static /* synthetic */ Class class$java$lang$Object;
    
    public static ComponentUI createUI(final JComponent component) {
        return new TinyFileChooserUI((JFileChooser)component);
    }
    
    public TinyFileChooserUI(final JFileChooser fileChooser) {
        super(fileChooser);
        this.doScrolling = true;
        this.directoryModel = null;
        this.directoryComboBoxAction = new DirectoryComboBoxAction();
        this.editorFocusListener = new FocusAdapter() {
            public void focusLost(final FocusEvent focusEvent) {
                if (!focusEvent.isTemporary()) {
                    TinyFileChooserUI.this.applyEdit();
                }
            }
        };
        this.COLUMN_WIDTHS = new int[] { 150, 75, 130, 100 };
        this.lookInLabelMnemonic = 0;
        this.lookInLabelText = null;
        this.saveInLabelText = null;
        this.fileNameLabelMnemonic = 0;
        this.fileNameLabelText = null;
        this.filesOfTypeLabelMnemonic = 0;
        this.filesOfTypeLabelText = null;
        this.upFolderToolTipText = null;
        this.upFolderAccessibleName = null;
        this.homeFolderToolTipText = null;
        this.homeFolderAccessibleName = null;
        this.newFolderToolTipText = null;
        this.newFolderAccessibleName = null;
        this.listViewButtonToolTipText = null;
        this.listViewButtonAccessibleName = null;
        this.detailsViewButtonToolTipText = null;
        this.detailsViewButtonAccessibleName = null;
        this.fileNameHeaderText = null;
        this.fileSizeHeaderText = null;
        this.fileTypeHeaderText = null;
        this.fileDateHeaderText = null;
        this.fileAttrHeaderText = null;
        this.lastIndex = -1;
        this.editFile = null;
        this.editX = 20;
        this.editCell = null;
    }
    
    public void uninstallComponents(final JFileChooser fileChooser) {
        fileChooser.removeAll();
        this.bottomPanel = null;
        this.buttonPanel = null;
    }
    
    public void installComponents(final JFileChooser fileChooser) {
        final FileSystemView fileSystemView = fileChooser.getFileSystemView();
        fileChooser.setBorder(new EmptyBorder(12, 12, 11, 11));
        fileChooser.setLayout(new BorderLayout(0, 11));
        final JPanel panel = new JPanel(new BorderLayout(11, 0));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, 0));
        panel.add(panel2, "East");
        fileChooser.add(panel, "North");
        (this.lookInLabel = new JLabel(this.lookInLabelText)).setDisplayedMnemonic(this.lookInLabelMnemonic);
        panel.add(this.lookInLabel, "Before");
        (this.directoryComboBox = new JComboBox()).putClientProperty("JComboBox.lightweightKeyboardNavigation", "Lightweight");
        this.lookInLabel.setLabelFor(this.directoryComboBox);
        this.directoryComboBoxModel = this.createDirectoryComboBoxModel(fileChooser);
        this.directoryComboBox.setModel(this.directoryComboBoxModel);
        this.directoryComboBox.addActionListener(this.directoryComboBoxAction);
        this.directoryComboBox.setRenderer(this.createDirectoryComboBoxRenderer(fileChooser));
        this.directoryComboBox.setAlignmentX(0.0f);
        this.directoryComboBox.setAlignmentY(0.0f);
        this.directoryComboBox.setMaximumRowCount(8);
        panel.add(this.directoryComboBox, "Center");
        final TinyToolButtonBorder border = new TinyToolButtonBorder();
        final Insets margin = new Insets(2, 2, 2, 2);
        final JButton button = new JButton(this.upFolderIcon);
        button.putClientProperty("JFileChooser.isFileChooserButton", Boolean.TRUE);
        button.setOpaque(false);
        button.setText(null);
        button.setIcon(this.upFolderIcon);
        button.setToolTipText(this.upFolderToolTipText);
        button.getAccessibleContext().setAccessibleName(this.upFolderAccessibleName);
        button.setAlignmentX(0.0f);
        button.setAlignmentY(0.5f);
        button.setMargin(margin);
        button.addActionListener(this.getChangeToParentDirectoryAction());
        button.setBorder(border);
        panel2.add(button);
        final File homeDirectory = fileSystemView.getHomeDirectory();
        String toolTipText = this.homeFolderToolTipText;
        if (fileSystemView.isRoot(homeDirectory)) {
            toolTipText = this.getFileView(fileChooser).getName(homeDirectory);
        }
        final JButton button2 = new JButton(this.homeFolderIcon);
        button2.putClientProperty("JFileChooser.isFileChooserButton", Boolean.TRUE);
        button2.setToolTipText(toolTipText);
        button2.getAccessibleContext().setAccessibleName(this.homeFolderAccessibleName);
        button2.setAlignmentX(0.0f);
        button2.setAlignmentY(0.5f);
        button2.setMargin(margin);
        button2.addActionListener(this.getGoHomeAction());
        button2.setBorder(border);
        panel2.add(button2);
        if (!UIManager.getBoolean("FileChooser.readOnly")) {
            final JButton button3 = new JButton(this.newFolderIcon);
            button3.putClientProperty("JFileChooser.isFileChooserButton", Boolean.TRUE);
            button3.setText(null);
            button3.setToolTipText(this.newFolderToolTipText);
            button3.getAccessibleContext().setAccessibleName(this.newFolderAccessibleName);
            button3.setAlignmentX(0.0f);
            button3.setAlignmentY(0.5f);
            button3.setMargin(margin);
            button3.addActionListener(this.getNewFolderAction());
            button3.setBorder(border);
            panel2.add(button3);
        }
        panel2.add(Box.createRigidArea(TinyFileChooserUI.hstrut1));
        final ButtonGroup buttonGroup = new ButtonGroup();
        final ViewButtonListener viewButtonListener = new ViewButtonListener(fileChooser);
        (this.listViewButton = new JToggleButton(this.listViewIcon)).putClientProperty("JFileChooser.isFileChooserButton", Boolean.TRUE);
        this.listViewButton.setToolTipText(this.listViewButtonToolTipText);
        this.listViewButton.getAccessibleContext().setAccessibleName(this.listViewButtonAccessibleName);
        this.listViewButton.setSelected(true);
        this.listViewButton.setAlignmentX(0.0f);
        this.listViewButton.setAlignmentY(0.5f);
        this.listViewButton.setMargin(new Insets(4, 2, 5, 2));
        this.listViewButton.addActionListener(viewButtonListener);
        this.listViewButton.setBorder(border);
        panel2.add(this.listViewButton);
        panel2.add(Box.createRigidArea(TinyFileChooserUI.hstrut1));
        buttonGroup.add(this.listViewButton);
        (this.detailsViewButton = new JToggleButton(this.detailsViewIcon)).putClientProperty("JFileChooser.isFileChooserButton", Boolean.TRUE);
        this.detailsViewButton.setToolTipText(this.detailsViewButtonToolTipText);
        this.detailsViewButton.getAccessibleContext().setAccessibleName(this.detailsViewButtonAccessibleName);
        this.detailsViewButton.setAlignmentX(0.0f);
        this.detailsViewButton.setAlignmentY(0.5f);
        this.detailsViewButton.setMargin(new Insets(4, 2, 5, 2));
        this.detailsViewButton.addActionListener(viewButtonListener);
        this.detailsViewButton.setBorder(border);
        panel2.add(this.detailsViewButton);
        buttonGroup.add(this.detailsViewButton);
        this.useShellFolder = false;
        final File[] roots = fileSystemView.getRoots();
        if (roots != null && roots.length == 1) {
            final File[] array = (File[])ShellFolder.get("fileChooserComboBoxFolders");
            if (array != null && array.length > 0 && roots[0] == array[0]) {
                this.useShellFolder = true;
            }
        }
        this.centerPanel = new JPanel(new BorderLayout());
        this.listViewPanel = this.createList(fileChooser);
        this.listSelectionModel = this.list.getSelectionModel();
        this.listViewPanel.setPreferredSize(TinyFileChooserUI.LIST_PREF_SIZE);
        this.centerPanel.add(this.listViewPanel, "Center");
        this.currentViewPanel = this.listViewPanel;
        this.centerPanel.add(this.getAccessoryPanel(), "After");
        final JComponent accessory = fileChooser.getAccessory();
        if (accessory != null) {
            this.getAccessoryPanel().add(accessory);
        }
        fileChooser.add(this.centerPanel, "Center");
        final JPanel bottomPanel = this.getBottomPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, 1));
        fileChooser.add(bottomPanel, "South");
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, 2));
        bottomPanel.add(panel3);
        bottomPanel.add(Box.createRigidArea(TinyFileChooserUI.vstrut5));
        final AlignedLabel alignedLabel = new AlignedLabel(this.fileNameLabelText);
        alignedLabel.setDisplayedMnemonic(this.fileNameLabelMnemonic);
        panel3.add(alignedLabel);
        panel3.add(this.fileNameTextField = new JTextField() {
            public Dimension getMaximumSize() {
                return new Dimension(32767, super.getPreferredSize().height);
            }
        });
        alignedLabel.setLabelFor(this.fileNameTextField);
        this.fileNameTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(final FocusEvent focusEvent) {
                if (!TinyFileChooserUI.this.getFileChooser().isMultiSelectionEnabled()) {
                    TinyFileChooserUI.this.listSelectionModel.clearSelection();
                }
            }
        });
        if (fileChooser.isMultiSelectionEnabled()) {
            this.setFileName(this.fileNameString(fileChooser.getSelectedFiles()));
        }
        else {
            this.setFileName(this.fileNameString(fileChooser.getSelectedFile()));
        }
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, 2));
        bottomPanel.add(panel4);
        final AlignedLabel alignedLabel2 = new AlignedLabel(this.filesOfTypeLabelText);
        alignedLabel2.setDisplayedMnemonic(this.filesOfTypeLabelMnemonic);
        panel4.add(alignedLabel2);
        fileChooser.addPropertyChangeListener(this.filterComboBoxModel = this.createFilterComboBoxModel());
        alignedLabel2.setLabelFor(this.filterComboBox = new JComboBox(this.filterComboBoxModel));
        this.filterComboBox.setRenderer(this.createFilterComboBoxRenderer());
        panel4.add(this.filterComboBox);
        this.getButtonPanel().setLayout(new ButtonAreaLayout());
        (this.approveButton = new JButton(this.getApproveButtonText(fileChooser))).addActionListener(this.getApproveSelectionAction());
        this.approveButton.setToolTipText(this.getApproveButtonToolTipText(fileChooser));
        this.getButtonPanel().add(this.approveButton);
        (this.cancelButton = new JButton(this.cancelButtonText)).setToolTipText(this.cancelButtonToolTipText);
        this.cancelButton.addActionListener(this.getCancelSelectionAction());
        this.getButtonPanel().add(this.cancelButton);
        if (fileChooser.getControlButtonsAreShown()) {
            this.addControlButtons();
        }
        groupLabels(new AlignedLabel[] { alignedLabel, alignedLabel2 });
    }
    
    protected JPanel getButtonPanel() {
        if (this.buttonPanel == null) {
            this.buttonPanel = new JPanel();
        }
        return this.buttonPanel;
    }
    
    protected JPanel getBottomPanel() {
        if (this.bottomPanel == null) {
            this.bottomPanel = new JPanel();
        }
        return this.bottomPanel;
    }
    
    protected void installStrings(final JFileChooser fileChooser) {
        super.installStrings(fileChooser);
        final Locale locale = fileChooser.getLocale();
        this.lookInLabelMnemonic = UIManager.getInt("FileChooser.lookInLabelMnemonic");
        this.lookInLabelText = UIManager.getString("FileChooser.lookInLabelText", locale);
        this.saveInLabelText = UIManager.getString("FileChooser.saveInLabelText", locale);
        this.fileNameLabelMnemonic = UIManager.getInt("FileChooser.fileNameLabelMnemonic");
        this.fileNameLabelText = UIManager.getString("FileChooser.fileNameLabelText", locale);
        this.filesOfTypeLabelMnemonic = UIManager.getInt("FileChooser.filesOfTypeLabelMnemonic");
        this.filesOfTypeLabelText = UIManager.getString("FileChooser.filesOfTypeLabelText", locale);
        this.upFolderToolTipText = UIManager.getString("FileChooser.upFolderToolTipText", locale);
        this.upFolderAccessibleName = UIManager.getString("FileChooser.upFolderAccessibleName", locale);
        this.homeFolderToolTipText = UIManager.getString("FileChooser.homeFolderToolTipText", locale);
        this.homeFolderAccessibleName = UIManager.getString("FileChooser.homeFolderAccessibleName", locale);
        this.newFolderToolTipText = UIManager.getString("FileChooser.newFolderToolTipText", locale);
        this.newFolderAccessibleName = UIManager.getString("FileChooser.newFolderAccessibleName", locale);
        this.listViewButtonToolTipText = UIManager.getString("FileChooser.listViewButtonToolTipText", locale);
        this.listViewButtonAccessibleName = UIManager.getString("FileChooser.listViewButtonAccessibleName", locale);
        this.detailsViewButtonToolTipText = UIManager.getString("FileChooser.detailsViewButtonToolTipText", locale);
        this.detailsViewButtonAccessibleName = UIManager.getString("FileChooser.detailsViewButtonAccessibleName", locale);
        this.fileNameHeaderText = UIManager.getString("FileChooser.fileNameHeaderText", locale);
        this.fileSizeHeaderText = UIManager.getString("FileChooser.fileSizeHeaderText", locale);
        this.fileTypeHeaderText = UIManager.getString("FileChooser.fileTypeHeaderText", locale);
        this.fileDateHeaderText = UIManager.getString("FileChooser.fileDateHeaderText", locale);
        this.fileAttrHeaderText = UIManager.getString("FileChooser.fileAttrHeaderText", locale);
    }
    
    protected void installListeners(final JFileChooser fileChooser) {
        super.installListeners(fileChooser);
        SwingUtilities.replaceUIActionMap(fileChooser, this.getActionMap());
    }
    
    protected ActionMap getActionMap() {
        return this.createActionMap();
    }
    
    protected ActionMap createActionMap() {
        final AbstractAction abstractAction = new AbstractAction() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (TinyFileChooserUI.this.editFile != null) {
                    TinyFileChooserUI.this.cancelEdit();
                }
                else {
                    TinyFileChooserUI.this.getFileChooser().cancelSelection();
                }
            }
            
            public boolean isEnabled() {
                return TinyFileChooserUI.this.getFileChooser().isEnabled();
            }
        };
        final ActionMapUIResource actionMapUIResource = new ActionMapUIResource();
        actionMapUIResource.put("approveSelection", this.getApproveSelectionAction());
        actionMapUIResource.put("cancelSelection", abstractAction);
        actionMapUIResource.put("Go Up", this.getChangeToParentDirectoryAction());
        return actionMapUIResource;
    }
    
    protected JPanel createList(final JFileChooser fileChooser) {
        final JPanel panel = new JPanel(new BorderLayout());
        (this.list = new JList() {
            public int getNextMatch(final String s, final int n, final Position.Bias bias) {
                final ListModel<Object> model = this.getModel();
                final int size = model.getSize();
                if (s == null || n < 0 || n >= size) {
                    throw new IllegalArgumentException();
                }
                final boolean b = bias == Position.Bias.Backward;
                int n2 = n;
                while (true) {
                    if (b) {
                        if (n2 < 0) {
                            break;
                        }
                    }
                    else if (n2 >= size) {
                        break;
                    }
                    if (fileChooser.getName(model.getElementAt(n2)).regionMatches(true, 0, s, 0, s.length())) {
                        return n2;
                    }
                    n2 += (b ? -1 : 1);
                }
                return -1;
            }
        }).setCellRenderer(new FileRenderer());
        this.list.setLayoutOrientation(1);
        this.list.setVisibleRowCount(-1);
        if (fileChooser.isMultiSelectionEnabled()) {
            this.list.setSelectionMode(2);
        }
        else {
            this.list.setSelectionMode(0);
        }
        this.list.setModel(this.getModel());
        this.list.addListSelectionListener(this.createListSelectionListener(fileChooser));
        this.list.addMouseListener(this.createDoubleClickListener(fileChooser, this.list));
        this.list.addMouseListener(this.createSingleClickListener(fileChooser, this.list));
        this.getModel().addListDataListener(new ListDataListener() {
            public void contentsChanged(final ListDataEvent listDataEvent) {
                new DelayedSelectionUpdater();
            }
            
            public void intervalAdded(final ListDataEvent listDataEvent) {
                new DelayedSelectionUpdater();
            }
            
            public void intervalRemoved(final ListDataEvent listDataEvent) {
            }
        });
        panel.add(new JScrollPane(this.list), "Center");
        return panel;
    }
    
    protected JPanel createDetailsView(final JFileChooser fileChooser) {
        final JPanel panel = new JPanel(new BorderLayout());
        this.detailsTableModel = new DetailsTableModel(fileChooser);
        (this.detailsTable = new JTable((TableModel)this.detailsTableModel) {
            protected boolean processKeyBinding(final KeyStroke keyStroke, final KeyEvent keyEvent, final int n, final boolean b) {
                if (keyEvent.getKeyCode() == 27 && this.getCellEditor() == null) {
                    fileChooser.dispatchEvent(keyEvent);
                    return true;
                }
                return super.processKeyBinding(keyStroke, keyEvent, n, b);
            }
        }).setComponentOrientation(fileChooser.getComponentOrientation());
        this.detailsTable.setAutoResizeMode(0);
        this.detailsTable.setShowGrid(false);
        this.detailsTable.setSelectionModel(this.listSelectionModel);
        this.detailsTable.putClientProperty("JTable.autoStartsEdit", Boolean.FALSE);
        this.detailsTable.setRowHeight(Math.max(this.detailsTable.getFont().getSize(), 15) + 3);
        final TableColumnModel columnModel = this.detailsTable.getColumnModel();
        final TableColumn[] array = new TableColumn[4];
        final boolean startsWith = System.getProperty("os.name").startsWith("Windows");
        for (int i = 0; i < 4; ++i) {
            (array[i] = columnModel.getColumn(i)).setPreferredWidth(this.COLUMN_WIDTHS[i]);
        }
        if (!startsWith) {
            columnModel.removeColumn(array[2]);
        }
        final TableHeaderUI ui = this.detailsTable.getTableHeader().getUI();
        if (ui instanceof TinyTableHeaderUI) {
            ((TinyTableHeaderUI)ui).sortColumns(new int[] { 0 }, new int[] { 1 }, this.detailsTable);
            if (startsWith) {
                ((TinyTableHeaderUI)ui).setHorizontalAlignments(new int[] { 10, 11, 10, 10 });
            }
            else {
                ((TinyTableHeaderUI)ui).setHorizontalAlignments(new int[] { 10, 11, 10 });
            }
        }
        final DetailsTableCellRenderer detailsTableCellRenderer = new DetailsTableCellRenderer(fileChooser);
        this.detailsTable.setDefaultRenderer((TinyFileChooserUI.class$java$io$File == null) ? (TinyFileChooserUI.class$java$io$File = class$("java.io.File")) : TinyFileChooserUI.class$java$io$File, detailsTableCellRenderer);
        this.detailsTable.setDefaultRenderer((TinyFileChooserUI.class$java$util$Date == null) ? (TinyFileChooserUI.class$java$util$Date = class$("java.util.Date")) : TinyFileChooserUI.class$java$util$Date, detailsTableCellRenderer);
        this.detailsTable.setDefaultRenderer((TinyFileChooserUI.class$java$lang$Long == null) ? (TinyFileChooserUI.class$java$lang$Long = class$("java.lang.Long")) : TinyFileChooserUI.class$java$lang$Long, detailsTableCellRenderer);
        this.detailsTable.setDefaultRenderer((TinyFileChooserUI.class$java$lang$String == null) ? (TinyFileChooserUI.class$java$lang$String = class$("java.lang.String")) : TinyFileChooserUI.class$java$lang$String, detailsTableCellRenderer);
        this.detailsTable.setDefaultRenderer((TinyFileChooserUI.class$java$lang$Object == null) ? (TinyFileChooserUI.class$java$lang$Object = class$("java.lang.Object")) : TinyFileChooserUI.class$java$lang$Object, detailsTableCellRenderer);
        final JTextField textField = new JTextField();
        textField.addFocusListener(this.editorFocusListener);
        array[0].setCellEditor(new DefaultCellEditor(textField) {
            public boolean isCellEditable(final EventObject eventObject) {
                if (eventObject instanceof MouseEvent) {
                    final MouseEvent mouseEvent = (MouseEvent)eventObject;
                    final int rowAtPoint = TinyFileChooserUI.this.detailsTable.rowAtPoint(mouseEvent.getPoint());
                    return mouseEvent.getClickCount() == 1 && TinyFileChooserUI.this.detailsTable.isRowSelected(rowAtPoint);
                }
                return super.isCellEditable(eventObject);
            }
            
            public Component getTableCellEditorComponent(final JTable table, final Object o, final boolean b, final int n, final int n2) {
                final Component tableCellEditorComponent = super.getTableCellEditorComponent(table, o, b, n, n2);
                if (o instanceof File) {
                    textField.setText(fileChooser.getName((File)o));
                    textField.requestFocus();
                    textField.selectAll();
                }
                return tableCellEditorComponent;
            }
        });
        final JList list = new JList(this.detailsTableModel.listModel) {
            JTable table = TinyFileChooserUI.this.detailsTable;
            
            public int locationToIndex(final Point point) {
                return this.table.rowAtPoint(point);
            }
            
            public Rectangle getCellBounds(final int n, final int n2) {
                return this.table.getCellRect(n, 0, false).union(this.table.getCellRect(n2, 0, false));
            }
            
            public Object getSelectedValue() {
                return this.table.getValueAt(this.table.getSelectedRow(), 0);
            }
            
            public Component add(final Component component) {
                if (component instanceof JTextField) {
                    return this.table.add(component);
                }
                return super.add(component);
            }
            
            public void repaint() {
                if (this.table != null) {
                    this.table.repaint();
                }
            }
            
            public TransferHandler getTransferHandler() {
                if (this.table != null) {
                    return this.table.getTransferHandler();
                }
                return super.getTransferHandler();
            }
            
            public void setTransferHandler(final TransferHandler transferHandler) {
                if (this.table != null) {
                    this.table.setTransferHandler(transferHandler);
                }
                else {
                    super.setTransferHandler(transferHandler);
                }
            }
            
            public boolean getDragEnabled() {
                if (this.table != null) {
                    return this.table.getDragEnabled();
                }
                return super.getDragEnabled();
            }
            
            public void setDragEnabled(final boolean b) {
                if (this.table != null) {
                    this.table.setDragEnabled(b);
                }
                else {
                    super.setDragEnabled(b);
                }
            }
        };
        list.setSelectionModel(this.listSelectionModel);
        this.detailsTable.addMouseListener(this.createDoubleClickListener(fileChooser, list));
        final JScrollPane scrollPane = new JScrollPane(this.detailsTable);
        scrollPane.setComponentOrientation(fileChooser.getComponentOrientation());
        LookAndFeel.installColors(scrollPane.getViewport(), "Table.background", "Table.foreground");
        scrollPane.addComponentListener(new ComponentAdapter() {
            public void componentResized(final ComponentEvent componentEvent) {
                final JScrollPane scrollPane = (JScrollPane)componentEvent.getComponent();
                TinyFileChooserUI.this.fixNameColumnWidth(scrollPane.getViewport().getSize().width);
                scrollPane.removeComponentListener(this);
            }
        });
        panel.add(scrollPane, "Center");
        return panel;
    }
    
    private void fixNameColumnWidth(final int n) {
        final TableColumn column = this.detailsTable.getColumnModel().getColumn(0);
        final int width = this.detailsTable.getPreferredSize().width;
        if (width < n) {
            column.setPreferredWidth(column.getPreferredWidth() + n - width);
        }
    }
    
    public ListSelectionListener createListSelectionListener(final JFileChooser fileChooser) {
        return new SelectionListener() {
            public void valueChanged(final ListSelectionEvent listSelectionEvent) {
                if (!listSelectionEvent.getValueIsAdjusting()) {
                    final JFileChooser fileChooser = TinyFileChooserUI.this.getFileChooser();
                    final FileSystemView fileSystemView = fileChooser.getFileSystemView();
                    final JList list = (JList)listSelectionEvent.getSource();
                    if (fileChooser.isMultiSelectionEnabled()) {
                        File[] selectedFiles = null;
                        final Object[] selectedValues = list.getSelectedValues();
                        if (selectedValues != null) {
                            if (selectedValues.length == 1 && ((File)selectedValues[0]).isDirectory() && fileChooser.isTraversable((File)selectedValues[0]) && (fileChooser.getFileSelectionMode() == 0 || !fileSystemView.isFileSystem((File)selectedValues[0]))) {
                                TinyFileChooserUI.this.setDirectorySelected(true);
                                TinyFileChooserUI.this.setDirectory((File)selectedValues[0]);
                            }
                            else {
                                selectedFiles = new File[selectedValues.length];
                                int n = 0;
                                for (int i = 0; i < selectedValues.length; ++i) {
                                    final File file = (File)selectedValues[i];
                                    if ((fileChooser.isFileSelectionEnabled() && file.isFile()) || (fileChooser.isDirectorySelectionEnabled() && fileSystemView.isFileSystem(file) && file.isDirectory())) {
                                        selectedFiles[n++] = file;
                                    }
                                }
                                if (n == 0) {
                                    selectedFiles = null;
                                }
                                else if (n < selectedValues.length) {
                                    final File[] array = new File[n];
                                    System.arraycopy(selectedFiles, 0, array, 0, n);
                                    selectedFiles = array;
                                }
                                TinyFileChooserUI.this.setDirectorySelected(false);
                            }
                        }
                        fileChooser.setSelectedFiles(selectedFiles);
                    }
                    else {
                        final File selectedFile = list.getSelectedValue();
                        if (selectedFile != null && selectedFile.isDirectory() && fileChooser.isTraversable(selectedFile) && (fileChooser.getFileSelectionMode() == 0 || !fileSystemView.isFileSystem(selectedFile))) {
                            TinyFileChooserUI.this.setDirectorySelected(true);
                            TinyFileChooserUI.this.setDirectory(selectedFile);
                            fileChooser.setSelectedFile(null);
                        }
                        else {
                            TinyFileChooserUI.this.setDirectorySelected(false);
                            if (selectedFile != null) {
                                fileChooser.setSelectedFile(selectedFile);
                            }
                        }
                    }
                }
            }
        };
    }
    
    private MouseListener createSingleClickListener(final JFileChooser fileChooser, final JList list) {
        return new SingleClickListener(list);
    }
    
    private int getEditIndex() {
        return this.lastIndex;
    }
    
    private void setEditIndex(final int lastIndex) {
        this.lastIndex = lastIndex;
    }
    
    private void resetEditIndex() {
        this.lastIndex = -1;
    }
    
    private void cancelEdit() {
        if (this.editFile != null) {
            this.editFile = null;
            this.list.remove(this.editCell);
            this.centerPanel.repaint();
        }
        else if (this.detailsTable != null && this.detailsTable.isEditing()) {
            this.detailsTable.getCellEditor().cancelCellEditing();
        }
    }
    
    private void editFileName(final int n) {
        if (UIManager.getBoolean("FileChooser.readOnly")) {
            return;
        }
        this.ensureIndexIsVisible(n);
        if (this.listViewPanel.isVisible()) {
            this.editFile = (File)this.getModel().getElementAt(n);
            final Rectangle cellBounds = this.list.getCellBounds(n, n);
            if (this.editCell == null) {
                (this.editCell = new JTextField()).addActionListener(new EditActionListener());
                this.editCell.addFocusListener(this.editorFocusListener);
                this.editCell.setNextFocusableComponent(this.list);
            }
            this.list.add(this.editCell);
            this.editCell.setText(this.getFileChooser().getName(this.editFile));
            if (this.list.getComponentOrientation().isLeftToRight()) {
                this.editCell.setBounds(this.editX + cellBounds.x, cellBounds.y, cellBounds.width - this.editX, cellBounds.height);
            }
            else {
                this.editCell.setBounds(cellBounds.x, cellBounds.y, cellBounds.width - this.editX, cellBounds.height);
            }
            this.editCell.requestFocus();
            this.editCell.selectAll();
        }
        else if (this.detailsViewPanel.isVisible()) {
            this.detailsTable.editCellAt(n, 0);
        }
    }
    
    private void applyEdit() {
        if (this.editFile != null && this.editFile.exists()) {
            final JFileChooser fileChooser = this.getFileChooser();
            final String name = fileChooser.getName(this.editFile);
            final String name2 = this.editFile.getName();
            final String trim = this.editCell.getText().trim();
            if (!trim.equals(name)) {
                String string = trim;
                final int length = name2.length();
                final int length2 = name.length();
                if (length > length2 && name2.charAt(length2) == '.') {
                    string = trim + name2.substring(length2);
                }
                final FileSystemView fileSystemView = fileChooser.getFileSystemView();
                final File fileObject = fileSystemView.createFileObject(this.editFile.getParentFile(), string);
                if (!fileObject.exists() && this.getModel().renameFile(this.editFile, fileObject) && fileSystemView.isParent(fileChooser.getCurrentDirectory(), fileObject)) {
                    if (fileChooser.isMultiSelectionEnabled()) {
                        fileChooser.setSelectedFiles(new File[] { fileObject });
                    }
                    else {
                        fileChooser.setSelectedFile(fileObject);
                    }
                }
            }
        }
        if (this.detailsTable != null && this.detailsTable.isEditing()) {
            this.detailsTable.getCellEditor().stopCellEditing();
        }
        this.cancelEdit();
    }
    
    public void uninstallUI(final JComponent component) {
        component.removePropertyChangeListener(this.filterComboBoxModel);
        this.cancelButton.removeActionListener(this.getCancelSelectionAction());
        this.approveButton.removeActionListener(this.getApproveSelectionAction());
        this.fileNameTextField.removeActionListener(this.getApproveSelectionAction());
        super.uninstallUI(component);
    }
    
    public Dimension getPreferredSize(final JComponent component) {
        final int width = TinyFileChooserUI.PREF_SIZE.width;
        final Dimension preferredLayoutSize = component.getLayout().preferredLayoutSize(component);
        if (preferredLayoutSize != null) {
            return new Dimension((preferredLayoutSize.width < width) ? width : preferredLayoutSize.width, (preferredLayoutSize.height < TinyFileChooserUI.PREF_SIZE.height) ? TinyFileChooserUI.PREF_SIZE.height : preferredLayoutSize.height);
        }
        return new Dimension(width, TinyFileChooserUI.PREF_SIZE.height);
    }
    
    public Dimension getMinimumSize(final JComponent component) {
        return TinyFileChooserUI.MIN_SIZE;
    }
    
    public Dimension getMaximumSize(final JComponent component) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
    
    void setFileSelected() {
        if (this.getFileChooser().isMultiSelectionEnabled() && !this.isDirectorySelected()) {
            final File[] selectedFiles = this.getFileChooser().getSelectedFiles();
            final Object[] selectedValues = this.list.getSelectedValues();
            for (int i = 0; i < selectedValues.length; ++i) {
                boolean b = false;
                for (int j = 0; j < selectedFiles.length; ++j) {
                    if (selectedFiles[j].equals(selectedValues[i])) {
                        b = true;
                        break;
                    }
                }
                if (!b) {
                    final int index = this.getModel().indexOf(selectedValues[i]);
                    if (index >= 0) {
                        this.listSelectionModel.removeSelectionInterval(index, index);
                    }
                }
            }
            for (int k = 0; k < selectedFiles.length; ++k) {
                boolean b2 = false;
                for (int l = 0; l < selectedValues.length; ++l) {
                    if (selectedFiles[k].equals(selectedValues[l])) {
                        b2 = true;
                        break;
                    }
                }
                if (!b2) {
                    final int index2 = this.getModel().indexOf(selectedFiles[k]);
                    if (index2 >= 0) {
                        this.listSelectionModel.addSelectionInterval(index2, index2);
                    }
                }
            }
        }
        else {
            final JFileChooser fileChooser = this.getFileChooser();
            File file;
            if (this.isDirectorySelected()) {
                file = this.getDirectory();
            }
            else {
                file = fileChooser.getSelectedFile();
            }
            final int index3;
            if (file != null && (index3 = this.getModel().indexOf(file)) >= 0) {
                this.listSelectionModel.setSelectionInterval(index3, index3);
                this.ensureIndexIsVisible(index3);
            }
            else {
                this.listSelectionModel.clearSelection();
            }
        }
    }
    
    private String fileNameString(final File file) {
        if (file == null) {
            return null;
        }
        final JFileChooser fileChooser = this.getFileChooser();
        if (fileChooser.isDirectorySelectionEnabled() && !fileChooser.isFileSelectionEnabled()) {
            return file.getPath();
        }
        return file.getName();
    }
    
    private String fileNameString(final File[] array) {
        final StringBuffer sb = new StringBuffer();
        for (int n = 0; array != null && n < array.length; ++n) {
            if (n > 0) {
                sb.append(" ");
            }
            if (array.length > 1) {
                sb.append("\"");
            }
            sb.append(this.fileNameString(array[n]));
            if (array.length > 1) {
                sb.append("\"");
            }
        }
        return sb.toString();
    }
    
    private void doSelectedFileChanged(final PropertyChangeEvent propertyChangeEvent) {
        this.applyEdit();
        final File file = (File)propertyChangeEvent.getNewValue();
        final JFileChooser fileChooser = this.getFileChooser();
        if (file != null && ((fileChooser.isFileSelectionEnabled() && !file.isDirectory()) || (file.isDirectory() && fileChooser.isDirectorySelectionEnabled()))) {
            this.setFileName(this.fileNameString(file));
            this.setFileSelected();
        }
    }
    
    private void doSelectedFilesChanged(final PropertyChangeEvent propertyChangeEvent) {
        this.applyEdit();
        final File[] array = (File[])propertyChangeEvent.getNewValue();
        final JFileChooser fileChooser = this.getFileChooser();
        if (array != null && array.length > 0 && (array.length > 1 || fileChooser.isDirectorySelectionEnabled() || !array[0].isDirectory())) {
            this.setFileName(this.fileNameString(array));
            this.setFileSelected();
        }
    }
    
    private void doDirectoryChanged(final PropertyChangeEvent propertyChangeEvent) {
        final JFileChooser fileChooser = this.getFileChooser();
        final FileSystemView fileSystemView = fileChooser.getFileSystemView();
        this.applyEdit();
        this.resetEditIndex();
        this.clearIconCache();
        this.listSelectionModel.clearSelection();
        this.ensureIndexIsVisible(0);
        final File currentDirectory = fileChooser.getCurrentDirectory();
        if (currentDirectory != null) {
            this.directoryComboBoxModel.addItem(currentDirectory);
            this.getNewFolderAction().setEnabled(currentDirectory.canWrite());
            this.getChangeToParentDirectoryAction().setEnabled(!fileSystemView.isRoot(currentDirectory));
            if (fileChooser.isDirectorySelectionEnabled() && !fileChooser.isFileSelectionEnabled()) {
                if (fileSystemView.isFileSystem(currentDirectory)) {
                    this.setFileName(currentDirectory.getPath());
                }
                else {
                    this.setFileName(null);
                }
            }
        }
    }
    
    private void doFilterChanged(final PropertyChangeEvent propertyChangeEvent) {
        this.applyEdit();
        this.resetEditIndex();
        this.clearIconCache();
        this.listSelectionModel.clearSelection();
    }
    
    private void doFileSelectionModeChanged(final PropertyChangeEvent propertyChangeEvent) {
        this.applyEdit();
        this.resetEditIndex();
        this.clearIconCache();
        this.listSelectionModel.clearSelection();
        final JFileChooser fileChooser = this.getFileChooser();
        final File currentDirectory = fileChooser.getCurrentDirectory();
        if (currentDirectory != null && fileChooser.isDirectorySelectionEnabled() && !fileChooser.isFileSelectionEnabled() && fileChooser.getFileSystemView().isFileSystem(currentDirectory)) {
            this.setFileName(currentDirectory.getPath());
        }
        else {
            this.setFileName(null);
        }
    }
    
    private void doMultiSelectionChanged(final PropertyChangeEvent propertyChangeEvent) {
        if (this.getFileChooser().isMultiSelectionEnabled()) {
            this.listSelectionModel.setSelectionMode(2);
        }
        else {
            this.listSelectionModel.setSelectionMode(0);
            this.listSelectionModel.clearSelection();
            this.getFileChooser().setSelectedFiles(null);
        }
    }
    
    private void doAccessoryChanged(final PropertyChangeEvent propertyChangeEvent) {
        if (this.getAccessoryPanel() != null) {
            if (propertyChangeEvent.getOldValue() != null) {
                this.getAccessoryPanel().remove((Component)propertyChangeEvent.getOldValue());
            }
            final JComponent component = (JComponent)propertyChangeEvent.getNewValue();
            if (component != null) {
                this.getAccessoryPanel().add(component, "Center");
            }
        }
    }
    
    private void doApproveButtonTextChanged(final PropertyChangeEvent propertyChangeEvent) {
        final JFileChooser fileChooser = this.getFileChooser();
        this.approveButton.setText(this.getApproveButtonText(fileChooser));
        this.approveButton.setToolTipText(this.getApproveButtonToolTipText(fileChooser));
    }
    
    private void doDialogTypeChanged(final PropertyChangeEvent propertyChangeEvent) {
        final JFileChooser fileChooser = this.getFileChooser();
        this.approveButton.setText(this.getApproveButtonText(fileChooser));
        this.approveButton.setToolTipText(this.getApproveButtonToolTipText(fileChooser));
        if (fileChooser.getDialogType() == 1) {
            this.lookInLabel.setText(this.saveInLabelText);
        }
        else {
            this.lookInLabel.setText(this.lookInLabelText);
        }
    }
    
    private void doApproveButtonMnemonicChanged(final PropertyChangeEvent propertyChangeEvent) {
    }
    
    private void doControlButtonsChanged(final PropertyChangeEvent propertyChangeEvent) {
        if (this.getFileChooser().getControlButtonsAreShown()) {
            this.addControlButtons();
        }
        else {
            this.removeControlButtons();
        }
    }
    
    public PropertyChangeListener createPropertyChangeListener(final JFileChooser fileChooser) {
        return new PropertyChangeListener() {
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                final String propertyName = propertyChangeEvent.getPropertyName();
                if (propertyName.equals("SelectedFileChangedProperty")) {
                    TinyFileChooserUI.this.doSelectedFileChanged(propertyChangeEvent);
                }
                else if (propertyName.equals("SelectedFilesChangedProperty")) {
                    TinyFileChooserUI.this.doSelectedFilesChanged(propertyChangeEvent);
                }
                else if (propertyName.equals("directoryChanged")) {
                    TinyFileChooserUI.this.doDirectoryChanged(propertyChangeEvent);
                }
                else if (propertyName.equals("fileFilterChanged")) {
                    TinyFileChooserUI.this.doFilterChanged(propertyChangeEvent);
                }
                else if (propertyName.equals("fileSelectionChanged")) {
                    TinyFileChooserUI.this.doFileSelectionModeChanged(propertyChangeEvent);
                }
                else if (propertyName.equals("MultiSelectionEnabledChangedProperty")) {
                    TinyFileChooserUI.this.doMultiSelectionChanged(propertyChangeEvent);
                }
                else if (propertyName.equals("AccessoryChangedProperty")) {
                    TinyFileChooserUI.this.doAccessoryChanged(propertyChangeEvent);
                }
                else if (propertyName.equals("ApproveButtonTextChangedProperty") || propertyName.equals("ApproveButtonToolTipTextChangedProperty")) {
                    TinyFileChooserUI.this.doApproveButtonTextChanged(propertyChangeEvent);
                }
                else if (propertyName.equals("DialogTypeChangedProperty")) {
                    TinyFileChooserUI.this.doDialogTypeChanged(propertyChangeEvent);
                }
                else if (propertyName.equals("ApproveButtonMnemonicChangedProperty")) {
                    TinyFileChooserUI.this.doApproveButtonMnemonicChanged(propertyChangeEvent);
                }
                else if (propertyName.equals("ControlButtonsAreShownChangedProperty")) {
                    TinyFileChooserUI.this.doControlButtonsChanged(propertyChangeEvent);
                }
                else if (propertyName.equals("CancelSelection")) {
                    TinyFileChooserUI.this.applyEdit();
                }
                else if (propertyName.equals("componentOrientation")) {
                    final ComponentOrientation componentOrientation = (ComponentOrientation)propertyChangeEvent.getNewValue();
                    final JFileChooser fileChooser = (JFileChooser)propertyChangeEvent.getSource();
                    if (componentOrientation != propertyChangeEvent.getOldValue()) {
                        fileChooser.applyComponentOrientation(componentOrientation);
                    }
                    if (TinyFileChooserUI.this.detailsTable != null) {
                        TinyFileChooserUI.this.detailsTable.setComponentOrientation(componentOrientation);
                        TinyFileChooserUI.this.detailsTable.getParent().getParent().setComponentOrientation(componentOrientation);
                    }
                }
                else if (propertyName.equals("ancestor") && propertyChangeEvent.getOldValue() == null && propertyChangeEvent.getNewValue() != null) {
                    TinyFileChooserUI.this.fileNameTextField.selectAll();
                    TinyFileChooserUI.this.fileNameTextField.requestFocus();
                }
            }
        };
    }
    
    protected void createModel() {
        if (this.directoryModel != null) {
            this.directoryModel.invalidateFileCache();
        }
        this.directoryModel = new TinyDirectoryModel(this.getFileChooser());
    }
    
    public BasicDirectoryModel getModel() {
        return this.directoryModel;
    }
    
    protected void removeControlButtons() {
        this.getBottomPanel().remove(this.getButtonPanel());
    }
    
    protected void addControlButtons() {
        this.getBottomPanel().add(this.getButtonPanel());
    }
    
    private void ensureIndexIsVisible(final int n) {
        if (n >= 0) {
            this.list.ensureIndexIsVisible(n);
            if (this.detailsTable != null) {
                final Rectangle cellRect = this.detailsTable.getCellRect(n, 0, true);
                final Rectangle cellRect2 = this.detailsTable.getCellRect(n, this.detailsTable.getColumnCount() - 1, true);
                cellRect.x = (cellRect.x + cellRect2.x + cellRect2.width) / 2;
                cellRect.width = 1;
                this.detailsTable.scrollRectToVisible(cellRect);
            }
        }
    }
    
    public void ensureFileIsVisible(final JFileChooser fileChooser, final File file) {
        if (!this.doScrolling) {
            return;
        }
        this.ensureIndexIsVisible(this.getModel().indexOf(file));
    }
    
    public void rescanCurrentDirectory(final JFileChooser fileChooser) {
        this.getModel().validateFileCache();
    }
    
    public String getFileName() {
        if (this.fileNameTextField != null) {
            return this.fileNameTextField.getText();
        }
        return null;
    }
    
    public void setFileName(final String text) {
        if (this.fileNameTextField != null) {
            this.fileNameTextField.setText(text);
        }
    }
    
    protected void setDirectorySelected(final boolean directorySelected) {
        super.setDirectorySelected(directorySelected);
        final JFileChooser fileChooser = this.getFileChooser();
        if (directorySelected) {
            this.approveButton.setText(this.directoryOpenButtonText);
            this.approveButton.setToolTipText(this.directoryOpenButtonToolTipText);
        }
        else {
            this.approveButton.setText(this.getApproveButtonText(fileChooser));
            this.approveButton.setToolTipText(this.getApproveButtonToolTipText(fileChooser));
        }
    }
    
    public String getDirectoryName() {
        return null;
    }
    
    public void setDirectoryName(final String s) {
    }
    
    protected DirectoryComboBoxRenderer createDirectoryComboBoxRenderer(final JFileChooser fileChooser) {
        return new DirectoryComboBoxRenderer();
    }
    
    protected DirectoryComboBoxModel createDirectoryComboBoxModel(final JFileChooser fileChooser) {
        return new DirectoryComboBoxModel();
    }
    
    protected FilterComboBoxRenderer createFilterComboBoxRenderer() {
        return new FilterComboBoxRenderer();
    }
    
    protected FilterComboBoxModel createFilterComboBoxModel() {
        return new FilterComboBoxModel();
    }
    
    public void valueChanged(final ListSelectionEvent listSelectionEvent) {
        final File selectedFile = this.getFileChooser().getSelectedFile();
        if (!listSelectionEvent.getValueIsAdjusting() && selectedFile != null && !this.getFileChooser().isTraversable(selectedFile)) {
            this.setFileName(this.fileNameString(selectedFile));
        }
    }
    
    protected JButton getApproveButton(final JFileChooser fileChooser) {
        return this.approveButton;
    }
    
    private static void groupLabels(final AlignedLabel[] array) {
        for (int i = 0; i < array.length; ++i) {
            array[i].group = array;
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        hstrut1 = new Dimension(1, 1);
        hstrut4 = new Dimension(4, 1);
        hstrut11 = new Dimension(11, 1);
        vstrut5 = new Dimension(1, 5);
        shrinkwrap = new Insets(2, 2, 2, 2);
        TinyFileChooserUI.PREF_WIDTH = 500;
        TinyFileChooserUI.PREF_HEIGHT = 326;
        TinyFileChooserUI.PREF_SIZE = new Dimension(TinyFileChooserUI.PREF_WIDTH, TinyFileChooserUI.PREF_HEIGHT);
        TinyFileChooserUI.MIN_WIDTH = 500;
        TinyFileChooserUI.MIN_HEIGHT = 326;
        TinyFileChooserUI.MIN_SIZE = new Dimension(TinyFileChooserUI.MIN_WIDTH, TinyFileChooserUI.MIN_HEIGHT);
        TinyFileChooserUI.LIST_PREF_WIDTH = 405;
        TinyFileChooserUI.LIST_PREF_HEIGHT = 183;
        TinyFileChooserUI.LIST_PREF_SIZE = new Dimension(TinyFileChooserUI.LIST_PREF_WIDTH, TinyFileChooserUI.LIST_PREF_HEIGHT);
    }
    
    private class AlignedLabel extends JLabel
    {
        private AlignedLabel[] group;
        private int maxWidth;
        
        AlignedLabel(final String s) {
            super(s);
            this.maxWidth = 0;
            this.setAlignmentX(0.0f);
        }
        
        public Dimension getPreferredSize() {
            return new Dimension(this.getMaxWidth() + 11, super.getPreferredSize().height);
        }
        
        private int getMaxWidth() {
            if (this.maxWidth == 0 && this.group != null) {
                int max = 0;
                for (int i = 0; i < this.group.length; ++i) {
                    max = Math.max(this.group[i].getSuperPreferredWidth(), max);
                }
                for (int j = 0; j < this.group.length; ++j) {
                    this.group[j].maxWidth = max;
                }
            }
            return this.maxWidth;
        }
        
        private int getSuperPreferredWidth() {
            return super.getPreferredSize().width;
        }
    }
    
    private static class ButtonAreaLayout implements LayoutManager
    {
        private int hGap;
        private int topMargin;
        
        private ButtonAreaLayout() {
            this.hGap = 5;
            this.topMargin = 17;
        }
        
        public void addLayoutComponent(final String s, final Component component) {
        }
        
        public void layoutContainer(final Container container) {
            final Component[] components = container.getComponents();
            if (components != null && components.length > 0) {
                final int length = components.length;
                final Dimension[] array = new Dimension[length];
                final Insets insets = container.getInsets();
                final int n = insets.top + this.topMargin;
                int max = 0;
                for (int i = 0; i < length; ++i) {
                    array[i] = components[i].getPreferredSize();
                    max = Math.max(max, array[i].width);
                }
                int left;
                int n2;
                if (container.getComponentOrientation().isLeftToRight()) {
                    left = container.getSize().width - insets.left - max;
                    n2 = this.hGap + max;
                }
                else {
                    left = insets.left;
                    n2 = -(this.hGap + max);
                }
                for (int j = length - 1; j >= 0; --j) {
                    components[j].setBounds(left, n, max, array[j].height);
                    left -= n2;
                }
            }
        }
        
        public Dimension minimumLayoutSize(final Container container) {
            if (container != null) {
                final Component[] components = container.getComponents();
                if (components != null && components.length > 0) {
                    final int length = components.length;
                    int max = 0;
                    final Insets insets = container.getInsets();
                    final int n = this.topMargin + insets.top + insets.bottom;
                    final int n2 = insets.left + insets.right;
                    int max2 = 0;
                    for (int i = 0; i < length; ++i) {
                        final Dimension preferredSize = components[i].getPreferredSize();
                        max = Math.max(max, preferredSize.height);
                        max2 = Math.max(max2, preferredSize.width);
                    }
                    return new Dimension(n2 + length * max2 + (length - 1) * this.hGap, n + max);
                }
            }
            return new Dimension(0, 0);
        }
        
        public Dimension preferredLayoutSize(final Container container) {
            return this.minimumLayoutSize(container);
        }
        
        public void removeLayoutComponent(final Component component) {
        }
    }
    
    protected class DirectoryComboBoxAction extends AbstractAction
    {
        protected DirectoryComboBoxAction() {
            super("DirectoryComboBoxAction");
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            TinyFileChooserUI.this.getFileChooser().setCurrentDirectory((File)TinyFileChooserUI.this.directoryComboBox.getSelectedItem());
        }
    }
    
    protected class FilterComboBoxModel extends AbstractListModel implements ComboBoxModel, PropertyChangeListener
    {
        protected FileFilter[] filters;
        
        protected FilterComboBoxModel() {
            this.filters = TinyFileChooserUI.this.getFileChooser().getChoosableFileFilters();
        }
        
        public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
            final String propertyName = propertyChangeEvent.getPropertyName();
            if (propertyName == "ChoosableFileFilterChangedProperty") {
                this.filters = (FileFilter[])propertyChangeEvent.getNewValue();
                this.fireContentsChanged(this, -1, -1);
            }
            else if (propertyName == "fileFilterChanged") {
                this.fireContentsChanged(this, -1, -1);
            }
        }
        
        public void setSelectedItem(final Object o) {
            if (o != null) {
                TinyFileChooserUI.this.getFileChooser().setFileFilter((FileFilter)o);
                TinyFileChooserUI.this.setFileName(null);
                this.fireContentsChanged(this, -1, -1);
            }
        }
        
        public Object getSelectedItem() {
            final FileFilter fileFilter = TinyFileChooserUI.this.getFileChooser().getFileFilter();
            boolean b = false;
            if (fileFilter != null) {
                for (int i = 0; i < this.filters.length; ++i) {
                    if (this.filters[i] == fileFilter) {
                        b = true;
                    }
                }
                if (!b) {
                    TinyFileChooserUI.this.getFileChooser().addChoosableFileFilter(fileFilter);
                }
            }
            return TinyFileChooserUI.this.getFileChooser().getFileFilter();
        }
        
        public int getSize() {
            if (this.filters != null) {
                return this.filters.length;
            }
            return 0;
        }
        
        public Object getElementAt(final int n) {
            if (n > this.getSize() - 1) {
                return TinyFileChooserUI.this.getFileChooser().getFileFilter();
            }
            if (this.filters != null) {
                return this.filters[n];
            }
            return null;
        }
    }
    
    public class FilterComboBoxRenderer extends DefaultListCellRenderer
    {
        public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
            super.getListCellRendererComponent(list, o, n, b, b2);
            if (o != null && o instanceof FileFilter) {
                this.setText(((FileFilter)o).getDescription());
            }
            return this;
        }
    }
    
    protected class DirectoryComboBoxModel extends AbstractListModel implements ComboBoxModel
    {
        Vector directories;
        int[] depths;
        File selectedDirectory;
        JFileChooser chooser;
        FileSystemView fsv;
        
        public DirectoryComboBoxModel() {
            this.directories = new Vector();
            this.depths = null;
            this.selectedDirectory = null;
            this.chooser = TinyFileChooserUI.this.getFileChooser();
            this.fsv = this.chooser.getFileSystemView();
            final File currentDirectory = TinyFileChooserUI.this.getFileChooser().getCurrentDirectory();
            if (currentDirectory != null) {
                this.addItem(currentDirectory);
            }
        }
        
        private void addItem(final File file) {
            if (file == null) {
                return;
            }
            this.directories.clear();
            File[] roots;
            if (TinyFileChooserUI.this.useShellFolder) {
                roots = (File[])ShellFolder.get("fileChooserComboBoxFolders");
            }
            else {
                roots = this.fsv.getRoots();
            }
            this.directories.addAll(Arrays.asList(roots));
            File canonicalFile;
            try {
                canonicalFile = file.getCanonicalFile();
            }
            catch (IOException ex) {
                canonicalFile = file;
            }
            try {
                File file2;
                final ShellFolder selectedItem = (ShellFolder)(file2 = ShellFolder.getShellFolder(canonicalFile));
                final Vector<ShellFolder> vector = new Vector<ShellFolder>(10);
                do {
                    vector.addElement((ShellFolder)file2);
                } while ((file2 = file2.getParentFile()) != null);
                for (int size = vector.size(), i = 0; i < size; ++i) {
                    final ShellFolder shellFolder = vector.get(i);
                    if (this.directories.contains(shellFolder)) {
                        final int index = this.directories.indexOf(shellFolder);
                        for (int j = i - 1; j >= 0; --j) {
                            this.directories.insertElementAt(vector.get(j), index + i - j);
                        }
                        break;
                    }
                }
                this.calculateDepths();
                this.setSelectedItem(selectedItem);
            }
            catch (FileNotFoundException ex2) {
                this.calculateDepths();
            }
        }
        
        private void calculateDepths() {
            this.depths = new int[this.directories.size()];
            for (int i = 0; i < this.depths.length; ++i) {
                final File parentFile = this.directories.get(i).getParentFile();
                this.depths[i] = 0;
                if (parentFile != null) {
                    for (int j = i - 1; j >= 0; --j) {
                        if (parentFile.equals(this.directories.get(j))) {
                            this.depths[i] = this.depths[j] + 1;
                            break;
                        }
                    }
                }
            }
        }
        
        public int getDepth(final int n) {
            return (this.depths != null && n >= 0 && n < this.depths.length) ? this.depths[n] : 0;
        }
        
        public void setSelectedItem(final Object o) {
            this.selectedDirectory = (File)o;
            this.fireContentsChanged(this, -1, -1);
        }
        
        public Object getSelectedItem() {
            return this.selectedDirectory;
        }
        
        public int getSize() {
            return this.directories.size();
        }
        
        public Object getElementAt(final int n) {
            return this.directories.elementAt(n);
        }
    }
    
    class IndentIcon implements Icon
    {
        Icon icon;
        int depth;
        
        IndentIcon() {
            this.icon = null;
            this.depth = 0;
        }
        
        public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
            if (component.getComponentOrientation().isLeftToRight()) {
                this.icon.paintIcon(component, graphics, n + this.depth * 10, n2);
            }
            else {
                this.icon.paintIcon(component, graphics, n, n2);
            }
        }
        
        public int getIconWidth() {
            return this.icon.getIconWidth() + this.depth * 10;
        }
        
        public int getIconHeight() {
            return this.icon.getIconHeight();
        }
    }
    
    class DirectoryComboBoxRenderer extends DefaultListCellRenderer
    {
        IndentIcon ii;
        
        DirectoryComboBoxRenderer() {
            this.ii = new IndentIcon();
        }
        
        public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
            super.getListCellRendererComponent(list, o, n, b, b2);
            if (o == null) {
                this.setText("");
                return this;
            }
            final File file = (File)o;
            this.setText(TinyFileChooserUI.this.getFileChooser().getName(file));
            this.ii.icon = TinyFileChooserUI.this.getFileChooser().getIcon(file);
            this.ii.depth = TinyFileChooserUI.this.directoryComboBoxModel.getDepth(n);
            this.setIcon(this.ii);
            return this;
        }
    }
    
    protected class FileRenderer extends DefaultListCellRenderer
    {
        public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
            super.getListCellRendererComponent(list, o, n, b, b2);
            final File file = (File)o;
            this.setText(TinyFileChooserUI.this.getFileChooser().getName(file));
            final Icon icon = TinyFileChooserUI.this.getFileChooser().getIcon(file);
            this.setIcon(icon);
            if (b) {
                TinyFileChooserUI.this.editX = icon.getIconWidth() + 4;
            }
            return this;
        }
    }
    
    class EditActionListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            TinyFileChooserUI.this.applyEdit();
        }
    }
    
    protected class SingleClickListener extends MouseAdapter
    {
        JList list;
        
        public SingleClickListener(final JList list) {
            this.list = list;
        }
        
        public void mouseClicked(final MouseEvent mouseEvent) {
            if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
                if (mouseEvent.getClickCount() == 1) {
                    final JFileChooser fileChooser = TinyFileChooserUI.this.getFileChooser();
                    final int locationToIndex = this.list.locationToIndex(mouseEvent.getPoint());
                    if ((!fileChooser.isMultiSelectionEnabled() || fileChooser.getSelectedFiles().length <= 1) && locationToIndex >= 0 && this.list.isSelectedIndex(locationToIndex) && TinyFileChooserUI.this.getEditIndex() == locationToIndex && TinyFileChooserUI.this.editFile == null) {
                        TinyFileChooserUI.this.editFileName(locationToIndex);
                    }
                    else if (locationToIndex >= 0) {
                        TinyFileChooserUI.this.setEditIndex(locationToIndex);
                    }
                    else {
                        TinyFileChooserUI.this.resetEditIndex();
                    }
                }
                else {
                    TinyFileChooserUI.this.resetEditIndex();
                }
            }
        }
    }
    
    private class DelayedSelectionUpdater implements Runnable
    {
        DelayedSelectionUpdater() {
            SwingUtilities.invokeLater(this);
        }
        
        public void run() {
            TinyFileChooserUI.this.setFileSelected();
        }
    }
    
    class DetailsTableCellRenderer extends DefaultTableCellRenderer
    {
        JFileChooser chooser;
        DateFormat df;
        
        DetailsTableCellRenderer(final JFileChooser chooser) {
            this.chooser = chooser;
            this.df = DateFormat.getDateTimeInstance(3, 3, chooser.getLocale());
        }
        
        public Component getTableCellRendererComponent(final JTable table, final Object o, final boolean b, final boolean b2, final int n, final int n2) {
            final int modelIndex = table.getColumnModel().getColumn(n2).getModelIndex();
            if (modelIndex == 1) {
                super.setHorizontalAlignment(11);
            }
            else {
                super.setHorizontalAlignment(10);
            }
            final Component tableCellRendererComponent = super.getTableCellRendererComponent(table, o, b & modelIndex == 0, false, n, n2);
            if (tableCellRendererComponent.getPreferredSize().width > table.getColumnModel().getColumn(n2).getWidth()) {
                super.setToolTipText(this.getText());
            }
            else {
                super.setToolTipText(null);
            }
            return tableCellRendererComponent;
        }
        
        public void setValue(final Object value) {
            this.setIcon(null);
            if (value == null) {
                this.setText("");
            }
            else if (value instanceof File) {
                final File file = (File)value;
                this.setText(this.chooser.getName(file));
                this.setIcon(this.chooser.getIcon(file));
            }
            else if (value instanceof Date) {
                this.setText((value == null) ? "" : this.df.format((Date)value));
            }
            else if (value instanceof Long) {
                final long n = (long)value / 1024L;
                if (n < 1024L) {
                    this.setText(((n == 0L) ? 1L : n) + " KB");
                }
                else {
                    final long n2 = n / 1024L;
                    if (n2 < 1024L) {
                        this.setText(n2 + " MB");
                    }
                    else {
                        this.setText(n2 / 1024L + " GB");
                    }
                }
            }
            else {
                super.setValue(value);
            }
        }
    }
    
    class DetailsTableModel extends AbstractTableModel implements ListDataListener, SortableTableData
    {
        String[] columnNames;
        JFileChooser chooser;
        ListModel listModel;
        Comparator fileAttributeComparator;
        Comparator fileDateComparator;
        Comparator fileNameComparator;
        Comparator fileSizeComparator;
        Comparator fileTypeComparator;
        int sortingDirection;
        int[] sortColumns;
        int[] sortDirections;
        
        DetailsTableModel(final JFileChooser chooser) {
            this.columnNames = new String[] { " " + TinyFileChooserUI.this.fileNameHeaderText, TinyFileChooserUI.this.fileSizeHeaderText, " " + TinyFileChooserUI.this.fileTypeHeaderText, " " + TinyFileChooserUI.this.fileDateHeaderText, " " + TinyFileChooserUI.this.fileAttrHeaderText };
            this.chooser = chooser;
            (this.listModel = TinyFileChooserUI.this.getModel()).addListDataListener(this);
        }
        
        public int getRowCount() {
            return this.listModel.getSize();
        }
        
        public int getColumnCount() {
            return 4;
        }
        
        public String getColumnName(final int n) {
            return this.columnNames[n];
        }
        
        public Class getColumnClass(final int n) {
            switch (n) {
                case 0: {
                    return (TinyFileChooserUI.class$java$io$File == null) ? (TinyFileChooserUI.class$java$io$File = TinyFileChooserUI.class$("java.io.File")) : TinyFileChooserUI.class$java$io$File;
                }
                case 3: {
                    return (TinyFileChooserUI.class$java$util$Date == null) ? (TinyFileChooserUI.class$java$util$Date = TinyFileChooserUI.class$("java.util.Date")) : TinyFileChooserUI.class$java$util$Date;
                }
                case 1: {
                    return (TinyFileChooserUI.class$java$lang$Long == null) ? (TinyFileChooserUI.class$java$lang$Long = TinyFileChooserUI.class$("java.lang.Long")) : TinyFileChooserUI.class$java$lang$Long;
                }
                case 2: {
                    return (TinyFileChooserUI.class$java$lang$String == null) ? (TinyFileChooserUI.class$java$lang$String = TinyFileChooserUI.class$("java.lang.String")) : TinyFileChooserUI.class$java$lang$String;
                }
                default: {
                    return super.getColumnClass(n);
                }
            }
        }
        
        public Object getValueAt(final int n, final int n2) {
            final File file = this.listModel.getElementAt(n);
            switch (n2) {
                case 0: {
                    return file;
                }
                case 1: {
                    if (!file.exists() || file.isDirectory()) {
                        return null;
                    }
                    return new Long(file.length());
                }
                case 2: {
                    if (!file.exists()) {
                        return null;
                    }
                    return this.chooser.getFileSystemView().getSystemTypeDescription(file);
                }
                case 3: {
                    if (!file.exists() || this.chooser.getFileSystemView().isFileSystemRoot(file)) {
                        return null;
                    }
                    final long lastModified = file.lastModified();
                    return (lastModified == 0L) ? null : new Date(lastModified);
                }
                default: {
                    return null;
                }
            }
        }
        
        public void setValueAt(final Object o, final int n, final int n2) {
            if (n2 == 0) {
                final JFileChooser fileChooser = TinyFileChooserUI.this.getFileChooser();
                final File file = (File)this.getValueAt(n, n2);
                if (file != null) {
                    final String name = fileChooser.getName(file);
                    final String name2 = file.getName();
                    final String trim = ((String)o).trim();
                    if (!trim.equals(name)) {
                        String string = trim;
                        final int length = name2.length();
                        final int length2 = name.length();
                        if (length > length2 && name2.charAt(length2) == '.') {
                            string = trim + name2.substring(length2);
                        }
                        final FileSystemView fileSystemView = fileChooser.getFileSystemView();
                        final File fileObject = fileSystemView.createFileObject(file.getParentFile(), string);
                        if (!fileObject.exists() && TinyFileChooserUI.this.getModel().renameFile(file, fileObject) && fileSystemView.isParent(fileChooser.getCurrentDirectory(), fileObject)) {
                            if (fileChooser.isMultiSelectionEnabled()) {
                                fileChooser.setSelectedFiles(new File[] { fileObject });
                            }
                            else {
                                fileChooser.setSelectedFile(fileObject);
                            }
                        }
                    }
                }
            }
        }
        
        public boolean isCellEditable(final int n, final int n2) {
            return n2 == 0 && !UIManager.getBoolean("FileChooser.readOnly");
        }
        
        public void contentsChanged(final ListDataEvent listDataEvent) {
            ((DetailsTableModel)TinyFileChooserUI.this.detailsTable.getModel()).sortColumns(TinyFileChooserUI.this.detailsTable);
        }
        
        public void intervalAdded(final ListDataEvent listDataEvent) {
            ((DetailsTableModel)TinyFileChooserUI.this.detailsTable.getModel()).sortColumns(TinyFileChooserUI.this.detailsTable);
        }
        
        public void intervalRemoved(final ListDataEvent listDataEvent) {
            ((DetailsTableModel)TinyFileChooserUI.this.detailsTable.getModel()).sortColumns(TinyFileChooserUI.this.detailsTable);
        }
        
        public void invalidateCachedFiles() {
        }
        
        public boolean isColumnSortable(final int n) {
            return ((TinyFileChooserUI.class$java$lang$Comparable == null) ? (TinyFileChooserUI.class$java$lang$Comparable = TinyFileChooserUI.class$("java.lang.Comparable")) : TinyFileChooserUI.class$java$lang$Comparable).isAssignableFrom(this.getColumnClass(n));
        }
        
        public boolean supportsMultiColumnSort() {
            return false;
        }
        
        public void sortColumns(final int[] array, final int[] array2, final JTable table) {
            this.sortColumns = new int[array.length];
            this.sortDirections = new int[array2.length];
            System.arraycopy(array, 0, this.sortColumns, 0, array.length);
            System.arraycopy(array2, 0, this.sortDirections, 0, array2.length);
            this.sortColumns(table);
        }
        
        private void sortColumns(final JTable table) {
            final Vector fileCache = ((TinyDirectoryModel)this.listModel).getFileCache();
            final int[] selectedColumns = table.getSelectedColumns();
            final int[] array = new int[table.getSelectedRowCount()];
            final Vector vector = new Vector<SelectedFile>(fileCache.size());
            for (int size = fileCache.size(), i = 0; i < size; ++i) {
                vector.add(new SelectedFile(fileCache.get(i), table.isRowSelected(i)));
            }
            if (this.sortColumns.length == 0) {
                this.sortingDirection = 1;
                if (this.fileNameComparator == null) {
                    this.fileNameComparator = this.createFileNameComparator();
                }
                Collections.sort((List<Object>)vector, this.fileNameComparator);
            }
            else {
                this.sortingDirection = ((this.sortDirections[0] == 1) ? 1 : -1);
                switch (this.sortColumns[0]) {
                    case 0: {
                        if (this.fileNameComparator == null) {
                            this.fileNameComparator = this.createFileNameComparator();
                        }
                        Collections.sort((List<Object>)vector, this.fileNameComparator);
                        break;
                    }
                    case 1: {
                        if (this.fileSizeComparator == null) {
                            this.fileSizeComparator = this.createFileSizeComparator();
                        }
                        Collections.sort((List<Object>)vector, this.fileSizeComparator);
                        break;
                    }
                    case 2: {
                        if (this.fileTypeComparator == null) {
                            this.fileTypeComparator = this.createFileTypeComparator();
                        }
                        Collections.sort((List<Object>)vector, this.fileTypeComparator);
                        break;
                    }
                    case 3: {
                        if (this.fileDateComparator == null) {
                            this.fileDateComparator = this.createFileDateComparator();
                        }
                        Collections.sort((List<Object>)vector, this.fileDateComparator);
                        break;
                    }
                }
            }
            fileCache.clear();
            int n = 0;
            int n2 = 0;
            for (final SelectedFile selectedFile : vector) {
                fileCache.add(selectedFile.file);
                if (selectedFile.selected) {
                    array[n2++] = n;
                }
                ++n;
            }
            this.fireTableDataChanged();
            TinyFileChooserUI.this.doScrolling = false;
            for (int j = 0; j < array.length; ++j) {
                table.addRowSelectionInterval(array[j], array[j]);
            }
            for (int k = 0; k < selectedColumns.length; ++k) {
                table.addColumnSelectionInterval(selectedColumns[k], selectedColumns[k]);
            }
            TinyFileChooserUI.this.doScrolling = true;
        }
        
        Comparator createFileNameComparator() {
            return new Comparator() {
                private final /* synthetic */ DetailsTableModel this$1 = this$1;
                
                public int compare(final Object o, final Object o2) {
                    final SelectedFile selectedFile = (SelectedFile)o;
                    final SelectedFile selectedFile2 = (SelectedFile)o2;
                    if (selectedFile.isDirectory() == selectedFile2.isDirectory()) {
                        return selectedFile.getName().compareTo(selectedFile2.getName()) * this.this$1.sortingDirection;
                    }
                    if (selectedFile.isDirectory()) {
                        return -1 * this.this$1.sortingDirection;
                    }
                    return 1 * this.this$1.sortingDirection;
                }
            };
        }
        
        Comparator createFileSizeComparator() {
            return new Comparator() {
                private final /* synthetic */ DetailsTableModel this$1 = this$1;
                
                public int compare(final Object o, final Object o2) {
                    return ((SelectedFile)o).getSize().compareTo(((SelectedFile)o2).getSize()) * this.this$1.sortingDirection;
                }
            };
        }
        
        Comparator createFileTypeComparator() {
            return new Comparator() {
                private final /* synthetic */ DetailsTableModel this$1 = this$1;
                
                public int compare(final Object o, final Object o2) {
                    final SelectedFile selectedFile = (SelectedFile)o;
                    final SelectedFile selectedFile2 = (SelectedFile)o2;
                    if (selectedFile.isDirectory() == selectedFile2.isDirectory()) {
                        return selectedFile.getType().compareTo(selectedFile2.getType()) * this.this$1.sortingDirection;
                    }
                    if (selectedFile.isDirectory()) {
                        return -1 * this.this$1.sortingDirection;
                    }
                    return 1 * this.this$1.sortingDirection;
                }
            };
        }
        
        Comparator createFileDateComparator() {
            return new Comparator() {
                private final /* synthetic */ DetailsTableModel this$1 = this$1;
                
                public int compare(final Object o, final Object o2) {
                    return ((SelectedFile)o).getDate().compareTo(((SelectedFile)o2).getDate()) * this.this$1.sortingDirection;
                }
            };
        }
        
        private class SelectedFile
        {
            File file;
            boolean selected;
            String name;
            Long size;
            Long date;
            String type;
            Boolean isDirectory;
            Boolean exists;
            Boolean isFileSystemRoot;
            
            SelectedFile(final File file, final boolean selected) {
                this.file = file;
                this.selected = selected;
            }
            
            public Long getDate() {
                if (this.date == null) {
                    if (this.exists() && !this.isFileSystemRoot()) {
                        this.date = new Long(this.file.lastModified());
                    }
                    else {
                        this.date = new Long(0L);
                    }
                }
                return this.date;
            }
            
            public String getName() {
                if (this.name == null) {
                    this.name = DetailsTableModel.this.chooser.getName(this.file);
                    if (this.name == null) {
                        this.name = "";
                    }
                    else {
                        this.name = this.name.toLowerCase();
                    }
                }
                return this.name;
            }
            
            public Long getSize() {
                if (this.size == null) {
                    if (this.exists() && !this.isDirectory()) {
                        this.size = new Long(this.file.length());
                    }
                    else {
                        this.size = new Long(0L);
                    }
                }
                return this.size;
            }
            
            public String getType() {
                if (this.type == null) {
                    if (this.exists()) {
                        this.type = DetailsTableModel.this.chooser.getFileSystemView().getSystemTypeDescription(this.file);
                    }
                    else {
                        this.type = "";
                    }
                }
                return this.type;
            }
            
            public boolean isDirectory() {
                if (this.isDirectory == null) {
                    this.isDirectory = new Boolean(this.exists() && this.file.isDirectory());
                }
                return this.isDirectory;
            }
            
            public boolean exists() {
                if (this.exists == null) {
                    this.exists = new Boolean(this.file.exists());
                }
                return this.exists;
            }
            
            public boolean isFileSystemRoot() {
                if (this.isFileSystemRoot == null) {
                    this.isFileSystemRoot = new Boolean(DetailsTableModel.this.chooser.getFileSystemView().isFileSystemRoot(this.file));
                }
                return this.isFileSystemRoot;
            }
        }
    }
    
    class ViewButtonListener implements ActionListener
    {
        JFileChooser fc;
        
        ViewButtonListener(final JFileChooser fc) {
            this.fc = fc;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JToggleButton toggleButton = (JToggleButton)actionEvent.getSource();
            final JPanel access$100 = TinyFileChooserUI.this.currentViewPanel;
            if (toggleButton == TinyFileChooserUI.this.detailsViewButton) {
                if (TinyFileChooserUI.this.detailsViewPanel == null) {
                    TinyFileChooserUI.this.detailsViewPanel = TinyFileChooserUI.this.createDetailsView(this.fc);
                    TinyFileChooserUI.this.detailsViewPanel.setPreferredSize(TinyFileChooserUI.LIST_PREF_SIZE);
                }
                TinyFileChooserUI.this.currentViewPanel = TinyFileChooserUI.this.detailsViewPanel;
            }
            else {
                TinyFileChooserUI.this.currentViewPanel = TinyFileChooserUI.this.listViewPanel;
            }
            if (TinyFileChooserUI.this.currentViewPanel != access$100) {
                TinyFileChooserUI.this.centerPanel.remove(access$100);
                TinyFileChooserUI.this.centerPanel.add(TinyFileChooserUI.this.currentViewPanel, "Center");
                TinyFileChooserUI.this.centerPanel.revalidate();
                TinyFileChooserUI.this.centerPanel.repaint();
            }
        }
    }
}
