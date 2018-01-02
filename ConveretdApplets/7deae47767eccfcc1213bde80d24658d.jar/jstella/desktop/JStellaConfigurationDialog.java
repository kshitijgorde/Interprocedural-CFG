// 
// Decompiled by Procyon v0.5.30
// 

package jstella.desktop;

import javax.swing.event.ListSelectionEvent;
import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import javax.swing.JOptionPane;
import jstella.runner.JStellaRunnerUtil;
import java.util.Collection;
import java.io.File;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.AbstractButton;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;
import java.awt.Frame;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import jstella.runner.InputMaster;
import java.util.List;
import java.util.Map;
import javax.swing.JDialog;

public class JStellaConfigurationDialog extends JDialog
{
    private static final long serialVersionUID = 694311415146652231L;
    private Map<String, String> myNewConfiguration;
    private ConfigControlTableModel myTableModel;
    private List<InputMaster.InputControlBinder> myControlItemList;
    private InputMaster.InputControlBinder myCurrentControlItem;
    private int myControlItemKeyCode;
    private InputMaster.VirtualControlTask[] myVirtualControlItemArray;
    private JButton ButtonAddControlItem;
    private JButton ButtonBrowseDefaultROMDirectory;
    private JButton ButtonBrowseDefaultStateDirectory;
    private JButton ButtonBrowseRepositoryDirectory;
    private JButton ButtonCancel;
    private JButton ButtonControlItemCancel;
    private JButton ButtonControlItemOK;
    private JButton ButtonDefaultControlItems;
    private JButton ButtonDeleteControlItem;
    private ButtonGroup ButtonGroupStartup;
    private JButton ButtonSaveConfig;
    private JButton ButtonUserDataDirectory;
    private JComboBox ComboBoxDefaultScreen;
    private JComboBox ComboBoxVirtualItem;
    private JDialog DialogControlItem;
    private JFileChooser FCMain;
    private JLabel LabelControlSpacer;
    private JLabel LabelDefaultROMDirectory;
    private JLabel LabelDefaultScreen;
    private JLabel LabelDefaultStateDirectory;
    private JLabel LabelRepositoryDirectory;
    private JLabel LabelStartupMode;
    private JLabel LabelUserDataDirectory;
    private JLabel LabelUserKey;
    private JLabel LabelVirtualItem;
    private JMenuItem MIAddControlItem;
    private JMenuItem MIDeleteControlItem;
    private JPopupMenu PUControls;
    private JPanel PanelControlItemCenter;
    private JPanel PanelControlItemSouth;
    private JPanel PanelControls;
    private JPanel PanelFileLocations;
    private JPanel PanelMisc;
    private JPanel PanelSouth;
    private JPanel PanelStartupMode;
    private JRadioButton RBStartupClassic;
    private JRadioButton RBStartupDefault;
    private JRadioButton RBStartupRepository;
    private JScrollPane SPControls;
    private JTextField TFDefaultROMDirectory;
    private JTextField TFDefaultStateDirectory;
    private JTextField TFRepositoryDirectory;
    private JTextField TFUserDataDirectory;
    private JTextField TFUserKey;
    private JTabbedPane TabPaneCenter;
    private JTable TableControls;
    
    public JStellaConfigurationDialog(final Frame parent, final boolean modal) {
        super(parent, modal);
        this.myNewConfiguration = null;
        this.myTableModel = new ConfigControlTableModel();
        this.myControlItemList = new ArrayList<InputMaster.InputControlBinder>();
        this.myCurrentControlItem = null;
        this.myControlItemKeyCode = 0;
        this.myVirtualControlItemArray = InputMaster.VirtualControlTask.values();
        this.initComponents();
        this.TableControls.getSelectionModel().addListSelectionListener(new ConfigControlTableSelectionListener());
        this.ComboBoxDefaultScreen.setModel(new ComboBoxDefaultScreenModel());
    }
    
    private void initComponents() {
        this.FCMain = new JFileChooser();
        this.LabelDefaultStateDirectory = new JLabel();
        this.TFDefaultStateDirectory = new JTextField();
        this.ButtonBrowseDefaultStateDirectory = new JButton();
        this.ButtonBrowseRepositoryDirectory = new JButton();
        this.TFRepositoryDirectory = new JTextField();
        this.LabelRepositoryDirectory = new JLabel();
        this.PUControls = new JPopupMenu();
        this.MIAddControlItem = new JMenuItem();
        this.MIDeleteControlItem = new JMenuItem();
        this.DialogControlItem = new JDialog();
        this.PanelControlItemCenter = new JPanel();
        this.LabelUserKey = new JLabel();
        this.TFUserKey = new JTextField();
        this.LabelVirtualItem = new JLabel();
        this.ComboBoxVirtualItem = new JComboBox();
        this.PanelControlItemSouth = new JPanel();
        this.ButtonControlItemOK = new JButton();
        this.ButtonControlItemCancel = new JButton();
        this.ButtonGroupStartup = new ButtonGroup();
        this.PanelSouth = new JPanel();
        this.ButtonSaveConfig = new JButton();
        this.ButtonCancel = new JButton();
        this.TabPaneCenter = new JTabbedPane();
        this.PanelFileLocations = new JPanel();
        this.LabelUserDataDirectory = new JLabel();
        this.TFUserDataDirectory = new JTextField();
        this.ButtonUserDataDirectory = new JButton();
        this.LabelDefaultROMDirectory = new JLabel();
        this.TFDefaultROMDirectory = new JTextField();
        this.ButtonBrowseDefaultROMDirectory = new JButton();
        this.PanelControls = new JPanel();
        this.SPControls = new JScrollPane();
        this.TableControls = new JTable();
        this.ButtonAddControlItem = new JButton();
        this.ButtonDeleteControlItem = new JButton();
        this.LabelControlSpacer = new JLabel();
        this.ButtonDefaultControlItems = new JButton();
        this.PanelMisc = new JPanel();
        this.LabelDefaultScreen = new JLabel();
        this.ComboBoxDefaultScreen = new JComboBox();
        this.PanelStartupMode = new JPanel();
        this.LabelStartupMode = new JLabel();
        this.RBStartupDefault = new JRadioButton();
        this.RBStartupRepository = new JRadioButton();
        this.RBStartupClassic = new JRadioButton();
        this.FCMain.setFileSelectionMode(1);
        this.LabelDefaultStateDirectory.setFont(new Font("Tahoma", 1, 11));
        this.LabelDefaultStateDirectory.setHorizontalAlignment(4);
        this.LabelDefaultStateDirectory.setText("Default Saved Game Directory:");
        this.TFDefaultStateDirectory.setText("C:\\");
        this.ButtonBrowseDefaultStateDirectory.setText("Browse");
        this.ButtonBrowseDefaultStateDirectory.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaConfigurationDialog.this.ButtonBrowseDefaultStateDirectoryActionPerformed(evt);
            }
        });
        this.ButtonBrowseRepositoryDirectory.setText("Browse");
        this.ButtonBrowseRepositoryDirectory.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaConfigurationDialog.this.ButtonBrowseRepositoryDirectoryActionPerformed(evt);
            }
        });
        this.TFRepositoryDirectory.setText("C:\\");
        this.LabelRepositoryDirectory.setFont(new Font("Tahoma", 1, 11));
        this.LabelRepositoryDirectory.setHorizontalAlignment(4);
        this.LabelRepositoryDirectory.setText("Repository Directory:");
        this.MIAddControlItem.setText("Add");
        this.MIAddControlItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaConfigurationDialog.this.addControlItemActionPerformed(evt);
            }
        });
        this.PUControls.add(this.MIAddControlItem);
        this.MIDeleteControlItem.setText("Delete");
        this.MIDeleteControlItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaConfigurationDialog.this.deleteControlItemActionPerformed(evt);
            }
        });
        this.PUControls.add(this.MIDeleteControlItem);
        this.DialogControlItem.setTitle("Add control");
        this.PanelControlItemCenter.setLayout(new GridBagLayout());
        this.LabelUserKey.setHorizontalAlignment(4);
        this.LabelUserKey.setText("User key :");
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 13;
        gridBagConstraints.weightx = 40.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelControlItemCenter.add(this.LabelUserKey, gridBagConstraints);
        this.TFUserKey.setHorizontalAlignment(2);
        this.TFUserKey.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent evt) {
                JStellaConfigurationDialog.this.TFUserKeyKeyPressed(evt);
            }
            
            public void keyTyped(final KeyEvent evt) {
                JStellaConfigurationDialog.this.TFUserKeyKeyTyped(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 200.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelControlItemCenter.add(this.TFUserKey, gridBagConstraints);
        this.LabelVirtualItem.setHorizontalAlignment(4);
        this.LabelVirtualItem.setText("Virtual action :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.weightx = 40.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelControlItemCenter.add(this.LabelVirtualItem, gridBagConstraints);
        this.ComboBoxVirtualItem.setModel(new DefaultComboBoxModel<String>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 2;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelControlItemCenter.add(this.ComboBoxVirtualItem, gridBagConstraints);
        this.DialogControlItem.getContentPane().add(this.PanelControlItemCenter, "Center");
        this.ButtonControlItemOK.setText("OK");
        this.ButtonControlItemOK.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaConfigurationDialog.this.ButtonControlItemOKActionPerformed(evt);
            }
        });
        this.PanelControlItemSouth.add(this.ButtonControlItemOK);
        this.ButtonControlItemCancel.setText("Cancel");
        this.ButtonControlItemCancel.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaConfigurationDialog.this.ButtonControlItemCancelActionPerformed(evt);
            }
        });
        this.PanelControlItemSouth.add(this.ButtonControlItemCancel);
        this.DialogControlItem.getContentPane().add(this.PanelControlItemSouth, "South");
        this.setDefaultCloseOperation(2);
        this.setTitle("JStella configuration");
        this.ButtonSaveConfig.setText("Save");
        this.ButtonSaveConfig.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaConfigurationDialog.this.ButtonSaveConfigActionPerformed(evt);
            }
        });
        this.PanelSouth.add(this.ButtonSaveConfig);
        this.ButtonCancel.setText("Cancel");
        this.ButtonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaConfigurationDialog.this.ButtonCancelActionPerformed(evt);
            }
        });
        this.PanelSouth.add(this.ButtonCancel);
        this.getContentPane().add(this.PanelSouth, "South");
        this.PanelFileLocations.setLayout(new GridBagLayout());
        this.LabelUserDataDirectory.setFont(new Font("Tahoma", 1, 11));
        this.LabelUserDataDirectory.setHorizontalAlignment(4);
        this.LabelUserDataDirectory.setText("JStella Data Directory:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelFileLocations.add(this.LabelUserDataDirectory, gridBagConstraints);
        this.TFUserDataDirectory.setText("C:\\");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.fill = 2;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelFileLocations.add(this.TFUserDataDirectory, gridBagConstraints);
        this.ButtonUserDataDirectory.setText("Browse");
        this.ButtonUserDataDirectory.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaConfigurationDialog.this.ButtonUserDataDirectoryActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelFileLocations.add(this.ButtonUserDataDirectory, gridBagConstraints);
        this.LabelDefaultROMDirectory.setFont(new Font("Tahoma", 1, 11));
        this.LabelDefaultROMDirectory.setHorizontalAlignment(4);
        this.LabelDefaultROMDirectory.setText("Default ROM Directory:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelFileLocations.add(this.LabelDefaultROMDirectory, gridBagConstraints);
        this.TFDefaultROMDirectory.setText("C:\\");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.fill = 2;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelFileLocations.add(this.TFDefaultROMDirectory, gridBagConstraints);
        this.ButtonBrowseDefaultROMDirectory.setText("Browse");
        this.ButtonBrowseDefaultROMDirectory.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaConfigurationDialog.this.ButtonBrowseDefaultROMDirectoryActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelFileLocations.add(this.ButtonBrowseDefaultROMDirectory, gridBagConstraints);
        this.TabPaneCenter.addTab("File locations", this.PanelFileLocations);
        this.PanelControls.setLayout(new GridBagLayout());
        this.TableControls.setComponentPopupMenu(this.PUControls);
        this.TableControls.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null }, { null, null, null, null } }, new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
        this.SPControls.setViewportView(this.TableControls);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 100.0;
        this.PanelControls.add(this.SPControls, gridBagConstraints);
        this.ButtonAddControlItem.setText("Add");
        this.ButtonAddControlItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaConfigurationDialog.this.addControlItemActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 2;
        this.PanelControls.add(this.ButtonAddControlItem, gridBagConstraints);
        this.ButtonDeleteControlItem.setText("Delete");
        this.ButtonDeleteControlItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaConfigurationDialog.this.deleteControlItemActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = 2;
        this.PanelControls.add(this.ButtonDeleteControlItem, gridBagConstraints);
        this.LabelControlSpacer.setText(" ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weighty = 100.0;
        this.PanelControls.add(this.LabelControlSpacer, gridBagConstraints);
        this.ButtonDefaultControlItems.setText("Add default");
        this.ButtonDefaultControlItems.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaConfigurationDialog.this.ButtonDefaultControlItemsActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        this.PanelControls.add(this.ButtonDefaultControlItems, gridBagConstraints);
        this.TabPaneCenter.addTab("Controls", this.PanelControls);
        this.PanelMisc.setLayout(new GridBagLayout());
        this.LabelDefaultScreen.setFont(new Font("Tahoma", 1, 11));
        this.LabelDefaultScreen.setText("Default Screen :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 100.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelMisc.add(this.LabelDefaultScreen, gridBagConstraints);
        this.ComboBoxDefaultScreen.setModel(new DefaultComboBoxModel<String>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelMisc.add(this.ComboBoxDefaultScreen, gridBagConstraints);
        this.PanelStartupMode.setLayout(new GridBagLayout());
        this.LabelStartupMode.setFont(new Font("Tahoma", 1, 11));
        this.LabelStartupMode.setText("Startup mode:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelStartupMode.add(this.LabelStartupMode, gridBagConstraints);
        this.ButtonGroupStartup.add(this.RBStartupDefault);
        this.RBStartupDefault.setSelected(true);
        this.RBStartupDefault.setText("Default");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = 17;
        this.PanelStartupMode.add(this.RBStartupDefault, gridBagConstraints);
        this.ButtonGroupStartup.add(this.RBStartupRepository);
        this.RBStartupRepository.setText("Repository mode");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = 17;
        this.PanelStartupMode.add(this.RBStartupRepository, gridBagConstraints);
        this.ButtonGroupStartup.add(this.RBStartupClassic);
        this.RBStartupClassic.setText("Classic mode");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = 17;
        this.PanelStartupMode.add(this.RBStartupClassic, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 100.0;
        this.PanelMisc.add(this.PanelStartupMode, gridBagConstraints);
        this.TabPaneCenter.addTab("Misc", this.PanelMisc);
        this.getContentPane().add(this.TabPaneCenter, "Center");
        this.pack();
    }
    
    private void ButtonDefaultControlItemsActionPerformed(final ActionEvent evt) {
        this.addDefaultControlItems();
    }
    
    private void TFUserKeyKeyTyped(final KeyEvent evt) {
        evt.consume();
    }
    
    private void TFUserKeyKeyPressed(final KeyEvent evt) {
        this.setCurrentVKCode(evt.getKeyCode());
    }
    
    private void deleteControlItemActionPerformed(final ActionEvent evt) {
        this.deleteControlItem();
    }
    
    private void ButtonControlItemOKActionPerformed(final ActionEvent evt) {
        this.myCurrentControlItem = new InputMaster.InputControlBinder(this.myControlItemKeyCode, this.myVirtualControlItemArray[this.ComboBoxVirtualItem.getSelectedIndex()]);
        this.DialogControlItem.setVisible(false);
    }
    
    private void ButtonControlItemCancelActionPerformed(final ActionEvent evt) {
        this.myCurrentControlItem = null;
        this.DialogControlItem.setVisible(false);
    }
    
    private void addControlItemActionPerformed(final ActionEvent evt) {
        this.addControlItem();
    }
    
    private void ButtonBrowseDefaultStateDirectoryActionPerformed(final ActionEvent evt) {
        this.browseForDefaultStateDirectory();
    }
    
    private void ButtonSaveConfigActionPerformed(final ActionEvent evt) {
        this.saveConfiguration();
        this.setVisible(false);
    }
    
    private void ButtonCancelActionPerformed(final ActionEvent evt) {
        this.setVisible(false);
    }
    
    private void ButtonBrowseDefaultROMDirectoryActionPerformed(final ActionEvent evt) {
        this.browseForDefaultROMDirectory();
    }
    
    private void ButtonBrowseRepositoryDirectoryActionPerformed(final ActionEvent evt) {
        this.browseForRepositoryDirectory();
    }
    
    private void ButtonUserDataDirectoryActionPerformed(final ActionEvent evt) {
        this.browseForUserDataDirectory();
    }
    
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JStellaConfigurationDialog(new JFrame(), true).setVisible(true);
            }
        });
    }
    
    private void browseForDefaultROMDirectory() {
        final File zCurrentDir = new File(this.TFDefaultROMDirectory.getText());
        if (zCurrentDir.exists() && zCurrentDir.isDirectory()) {
            this.FCMain.setCurrentDirectory(zCurrentDir);
        }
        else {
            this.FCMain.setCurrentDirectory(null);
        }
        this.FCMain.setFileSelectionMode(1);
        final int zResult = this.FCMain.showOpenDialog(this);
        if (zResult == 0) {
            this.TFDefaultROMDirectory.setText(this.FCMain.getSelectedFile().getAbsolutePath());
        }
    }
    
    private void browseForUserDataDirectory() {
        final File zCurrentDir = new File(this.TFUserDataDirectory.getText());
        if (zCurrentDir.exists() && zCurrentDir.isDirectory()) {
            this.FCMain.setCurrentDirectory(zCurrentDir);
        }
        else {
            this.FCMain.setCurrentDirectory(null);
        }
        this.FCMain.setFileSelectionMode(1);
        final int zResult = this.FCMain.showOpenDialog(this);
        if (zResult == 0) {
            this.TFUserDataDirectory.setText(this.FCMain.getSelectedFile().getAbsolutePath());
        }
    }
    
    private void browseForDefaultStateDirectory() {
        final File zCurrentDir = new File(this.TFDefaultStateDirectory.getText());
        if (zCurrentDir.exists() && zCurrentDir.isDirectory()) {
            this.FCMain.setCurrentDirectory(zCurrentDir);
        }
        else {
            this.FCMain.setCurrentDirectory(null);
        }
        this.FCMain.setFileSelectionMode(1);
        final int zResult = this.FCMain.showOpenDialog(this);
        if (zResult == 0) {
            this.TFDefaultStateDirectory.setText(this.FCMain.getSelectedFile().getAbsolutePath());
        }
    }
    
    private void browseForRepositoryDirectory() {
        final File zCurrentDir = new File(this.TFRepositoryDirectory.getText());
        if (zCurrentDir.exists() && zCurrentDir.isDirectory()) {
            this.FCMain.setCurrentDirectory(zCurrentDir);
        }
        else {
            this.FCMain.setCurrentDirectory(null);
        }
        this.FCMain.setFileSelectionMode(1);
        final int zResult = this.FCMain.showOpenDialog(this);
        if (zResult == 0) {
            this.TFRepositoryDirectory.setText(this.FCMain.getSelectedFile().getAbsolutePath());
        }
    }
    
    private void updateControlOptions() {
        final boolean zDeleteControlEnabled = this.TableControls.getSelectedRow() != -1;
        this.MIDeleteControlItem.setEnabled(zDeleteControlEnabled);
        this.ButtonDeleteControlItem.setEnabled(zDeleteControlEnabled);
    }
    
    private void setCurrentVKCode(final int aVKCode) {
        this.myControlItemKeyCode = aVKCode;
        this.TFUserKey.setText("" + KeyEvent.getKeyText(aVKCode));
    }
    
    private void deleteControlItem() {
        if (this.TableControls.getSelectedRow() != -1) {
            this.myControlItemList.remove(this.TableControls.getSelectedRow());
            this.myTableModel.updateTable();
        }
    }
    
    private void addDefaultControlItems() {
        for (int i = 0; i < InputMaster.DEFAULT_CONTROL_BINDERS.length; ++i) {
            if (!this.myControlItemList.contains(InputMaster.DEFAULT_CONTROL_BINDERS[i])) {
                this.myControlItemList.add(InputMaster.DEFAULT_CONTROL_BINDERS[i]);
            }
        }
        this.myTableModel.updateTable();
    }
    
    private void addControlItem() {
        this.DialogControlItem.setModal(true);
        this.myCurrentControlItem = null;
        this.setCurrentVKCode(32);
        this.ComboBoxVirtualItem.setSelectedIndex(0);
        this.DialogControlItem.setLocationRelativeTo(this);
        this.DialogControlItem.setSize(350, 175);
        this.DialogControlItem.setVisible(true);
        if (this.myCurrentControlItem != null) {
            this.myControlItemList.add(this.myCurrentControlItem);
        }
        this.myCurrentControlItem = null;
        this.myTableModel.updateTable();
    }
    
    private void createControlItems(final Map<String, String> aCurrentCfg) {
        this.myControlItemList.clear();
        this.myControlItemList.addAll(InputMaster.convertConfigMapToControlBinderList(aCurrentCfg));
    }
    
    private Map<String, String> showConfigurationDialog(final Map<String, String> aCurrentCfg) {
        if (aCurrentCfg != null) {
            if (aCurrentCfg.containsKey("jstella.dir.userdata")) {
                this.TFUserDataDirectory.setText("" + aCurrentCfg.get("jstella.dir.userdata"));
            }
            else {
                this.TFUserDataDirectory.setText("");
            }
            if (aCurrentCfg.containsKey("jstella.dir.roms")) {
                this.TFDefaultROMDirectory.setText("" + aCurrentCfg.get("jstella.dir.roms"));
            }
            else {
                this.TFDefaultROMDirectory.setText("");
            }
            if (aCurrentCfg.containsKey("jstella.dir.states")) {
                this.TFDefaultStateDirectory.setText("" + aCurrentCfg.get("jstella.dir.states"));
            }
            else {
                this.TFDefaultStateDirectory.setText("");
            }
            if (aCurrentCfg.containsKey("jstella.dir.repository")) {
                this.TFRepositoryDirectory.setText("" + aCurrentCfg.get("jstella.dir.repository"));
            }
            else {
                this.TFRepositoryDirectory.setText("");
            }
            if (JStellaRunnerUtil.containsAffirmativeValue(aCurrentCfg, "jstella.repositorymode")) {
                this.RBStartupRepository.setSelected(true);
            }
            else if (JStellaRunnerUtil.containsNegativeValue(aCurrentCfg, "jstella.repositorymode")) {
                this.RBStartupClassic.setSelected(true);
            }
            else {
                this.RBStartupDefault.setSelected(true);
            }
        }
        this.createControlItems(aCurrentCfg);
        final String zDefaultScreenValue = aCurrentCfg.get("jstella.defaultscreen");
        if ("snow".equals(zDefaultScreenValue)) {
            this.ComboBoxDefaultScreen.setSelectedIndex(1);
        }
        else {
            this.ComboBoxDefaultScreen.setSelectedIndex(0);
        }
        this.TableControls.setModel(this.myTableModel);
        this.ComboBoxVirtualItem.setModel(new ComboBoxVirtualItemModel());
        this.updateControlOptions();
        this.setVisible(true);
        return this.myNewConfiguration;
    }
    
    private String getDefaultScreen() {
        final int zSelected = this.ComboBoxDefaultScreen.getSelectedIndex();
        if (zSelected == 1) {
            return "snow";
        }
        return "testpattern";
    }
    
    private boolean testDirectory(final String aDirPath) {
        final boolean zReturn = false;
        return zReturn;
    }
    
    private boolean storeDirectory(final String aConfigKey, final String aProposedDir) {
        boolean zReturn = true;
        if (aProposedDir != null && !aProposedDir.equals("")) {
            final File zDir = new File(aProposedDir);
            if (zDir.exists() && zDir.isDirectory()) {
                this.myNewConfiguration.put(aConfigKey, aProposedDir);
            }
            else {
                JOptionPane.showMessageDialog(this, "Error: " + aProposedDir + " is not a valid directory", "Error", 0);
                zReturn = false;
            }
        }
        else {
            this.myNewConfiguration.remove(aConfigKey);
        }
        return zReturn;
    }
    
    private void saveConfiguration() {
        this.myNewConfiguration = new HashMap<String, String>();
        this.storeDirectory("jstella.dir.userdata", this.TFUserDataDirectory.getText());
        this.storeDirectory("jstella.dir.roms", this.TFDefaultROMDirectory.getText());
        this.storeDirectory("jstella.dir.states", this.TFDefaultStateDirectory.getText());
        this.storeDirectory("jstella.dir.repository", this.TFRepositoryDirectory.getText());
        if (this.RBStartupRepository.isSelected()) {
            this.myNewConfiguration.put("jstella.repositorymode", "true");
        }
        else if (this.RBStartupClassic.isSelected()) {
            this.myNewConfiguration.put("jstella.repositorymode", "false");
        }
        this.myNewConfiguration.put("jstella.defaultscreen", this.getDefaultScreen());
        InputMaster.addControlBinderListToConfigMap(this.myControlItemList, this.myNewConfiguration);
    }
    
    public static Map<String, String> runJStellaConfiguration(final Frame aParent, final Map<String, String> aCurrentConfig) {
        final JStellaConfigurationDialog zConfigDlg = new JStellaConfigurationDialog(aParent, true);
        zConfigDlg.setLocationRelativeTo(aParent);
        final Map<String, String> zReturn = zConfigDlg.showConfigurationDialog(aCurrentConfig);
        return zReturn;
    }
    
    private class ConfigControlTableModel extends AbstractTableModel
    {
        private static final long serialVersionUID = 6161758458839471687L;
        
        public Object getValueAt(final int rowIndex, final int columnIndex) {
            if (columnIndex == 0) {
                return JStellaConfigurationDialog.this.myControlItemList.get(rowIndex).getActualKeyText();
            }
            return JStellaConfigurationDialog.this.myControlItemList.get(rowIndex).getTarget().toString();
        }
        
        public int getRowCount() {
            return JStellaConfigurationDialog.this.myControlItemList.size();
        }
        
        public int getColumnCount() {
            return 2;
        }
        
        public void updateTable() {
            this.fireTableStructureChanged();
        }
        
        public String getColumnName(final int column) {
            if (column == 0) {
                return "User key";
            }
            return "Virtual control action";
        }
    }
    
    private class ConfigControlTableSelectionListener implements ListSelectionListener
    {
        public void valueChanged(final ListSelectionEvent e) {
            JStellaConfigurationDialog.this.updateControlOptions();
        }
    }
    
    private class ComboBoxVirtualItemModel extends DefaultComboBoxModel
    {
        private static final long serialVersionUID = 8724220572078108104L;
        
        public int getSize() {
            return JStellaConfigurationDialog.this.myVirtualControlItemArray.length;
        }
        
        public Object getElementAt(final int index) {
            return JStellaConfigurationDialog.this.myVirtualControlItemArray[index];
        }
    }
    
    private class ComboBoxDefaultScreenModel extends DefaultComboBoxModel
    {
        private static final long serialVersionUID = -4278329446634907147L;
        
        public int getSize() {
            return 2;
        }
        
        public Object getElementAt(final int index) {
            if (index == 0) {
                return "Color Test Pattern";
            }
            return "Television Snow";
        }
    }
}
