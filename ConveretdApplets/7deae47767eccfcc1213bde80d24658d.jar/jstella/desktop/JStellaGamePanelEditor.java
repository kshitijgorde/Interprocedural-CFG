// 
// Decompiled by Procyon v0.5.30
// 

package jstella.desktop;

import javax.swing.table.AbstractTableModel;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import javax.swing.table.TableCellEditor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Clipboard;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.ImageWriteParam;
import java.util.Iterator;
import java.awt.image.RenderedImage;
import javax.imageio.IIOImage;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.ImageWriter;
import javax.imageio.ImageIO;
import java.io.OutputStream;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.FileInputStream;
import javax.swing.filechooser.FileFilter;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.EventQueue;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.Frame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JPopupMenu;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.io.File;
import jstella.runner.JStellaGame;
import java.util.List;
import javax.swing.JDialog;

public class JStellaGamePanelEditor extends JDialog
{
    private static final long serialVersionUID = 7645524221620242072L;
    public static final float IMAGE_COMPRESSION = 0.5f;
    public static final String IMAGE_OUTPUT_FORMAT = "image/jpeg";
    private static final int MINIMUM_WINDOW_HEIGHT_UPON_ADVANCED_EDIT = 480;
    private static boolean myAdvancedMode;
    private static JStellaGamePanelEditor myGamePanelEditor;
    private static List<String> myKeyList;
    private JStellaGame myCurrentGame;
    private byte[] myROMData;
    private byte[] myIconData;
    private boolean myAcceptChange;
    private AdvancedTableModel myTableModel;
    private static File myLastROMDir;
    private static File myLastGraphicsDir;
    private JButton ButtonCancel;
    private JButton ButtonLoadImage;
    private JButton ButtonLoadROM;
    private JButton ButtonOK;
    private JButton ButtonToggleAdvanced;
    private JLabel LabelGameIcon;
    private JLabel LabelGameIconText;
    private JLabel LabelGameTitle;
    private JLabel LabelROM;
    private JLabel LabelROMStatus;
    private JMenuBar MBMain;
    private JCheckBoxMenuItem MICBAdvancedMode;
    private JMenuItem MIClearIcon;
    private JMenuItem MIPasteIcon;
    private JMenu MenuEdit;
    private JMenuItem PMIPasteImage;
    private JPanel PanelCenter;
    private JPanel PanelEditorAdvanced;
    private JPanel PanelEditorMain;
    private JPanel PanelSouth;
    private JPopupMenu PopupIcon;
    private JScrollPane SPAdvanced;
    private JSeparator SeparatorEditA;
    private JTextField TFGameTitle;
    private JTable TableAdvanced;
    
    public JStellaGamePanelEditor(final Frame parent, final boolean modal) {
        super(parent, modal);
        this.myCurrentGame = null;
        this.myROMData = null;
        this.myIconData = null;
        this.myAcceptChange = false;
        this.myTableModel = null;
        this.initComponents();
        this.initAdvancedTable();
        this.setAdvancedMode(JStellaGamePanelEditor.myAdvancedMode);
    }
    
    private void initComponents() {
        this.PopupIcon = new JPopupMenu();
        this.PMIPasteImage = new JMenuItem();
        this.PanelSouth = new JPanel();
        this.ButtonOK = new JButton();
        this.ButtonCancel = new JButton();
        this.PanelCenter = new JPanel();
        this.PanelEditorMain = new JPanel();
        this.LabelGameTitle = new JLabel();
        this.TFGameTitle = new JTextField();
        this.LabelGameIconText = new JLabel();
        this.LabelGameIcon = new JLabel();
        this.ButtonLoadImage = new JButton();
        this.LabelROM = new JLabel();
        this.ButtonLoadROM = new JButton();
        this.LabelROMStatus = new JLabel();
        this.ButtonToggleAdvanced = new JButton();
        this.PanelEditorAdvanced = new JPanel();
        this.SPAdvanced = new JScrollPane();
        this.TableAdvanced = new JTable();
        this.MBMain = new JMenuBar();
        this.MenuEdit = new JMenu();
        this.MIPasteIcon = new JMenuItem();
        this.MIClearIcon = new JMenuItem();
        this.SeparatorEditA = new JSeparator();
        this.MICBAdvancedMode = new JCheckBoxMenuItem();
        this.PopupIcon.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuCanceled(final PopupMenuEvent evt) {
            }
            
            public void popupMenuWillBecomeInvisible(final PopupMenuEvent evt) {
            }
            
            public void popupMenuWillBecomeVisible(final PopupMenuEvent evt) {
                JStellaGamePanelEditor.this.PopupIconPopupMenuWillBecomeVisible(evt);
            }
        });
        this.PMIPasteImage.setText("Paste image");
        this.PMIPasteImage.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaGamePanelEditor.this.PMIPasteImageActionPerformed(evt);
            }
        });
        this.PopupIcon.add(this.PMIPasteImage);
        this.setDefaultCloseOperation(2);
        this.setTitle("JStella Menu Item Editor");
        this.ButtonOK.setText("OK");
        this.ButtonOK.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaGamePanelEditor.this.ButtonOKActionPerformed(evt);
            }
        });
        this.PanelSouth.add(this.ButtonOK);
        this.ButtonCancel.setText("Cancel");
        this.ButtonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaGamePanelEditor.this.ButtonCancelActionPerformed(evt);
            }
        });
        this.PanelSouth.add(this.ButtonCancel);
        this.getContentPane().add(this.PanelSouth, "South");
        this.PanelCenter.setLayout(new GridBagLayout());
        this.PanelEditorMain.setComponentPopupMenu(this.PopupIcon);
        this.PanelEditorMain.setLayout(new GridBagLayout());
        this.LabelGameTitle.setText("Game title:");
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 30.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelEditorMain.add(this.LabelGameTitle, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 200.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelEditorMain.add(this.TFGameTitle, gridBagConstraints);
        this.LabelGameIconText.setText("Game icon:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelEditorMain.add(this.LabelGameIconText, gridBagConstraints);
        this.LabelGameIcon.setComponentPopupMenu(this.PopupIcon);
        this.LabelGameIcon.setHorizontalAlignment(0);
        this.LabelGameIcon.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.LabelGameIcon.setInheritsPopupMenu(false);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weighty = 100.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelEditorMain.add(this.LabelGameIcon, gridBagConstraints);
        this.ButtonLoadImage.setText("Load icon");
        this.ButtonLoadImage.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaGamePanelEditor.this.ButtonLoadImageActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        this.PanelEditorMain.add(this.ButtonLoadImage, gridBagConstraints);
        this.LabelROM.setText("ROM:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelEditorMain.add(this.LabelROM, gridBagConstraints);
        this.ButtonLoadROM.setText("Load ROM");
        this.ButtonLoadROM.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaGamePanelEditor.this.ButtonLoadROMActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelEditorMain.add(this.ButtonLoadROM, gridBagConstraints);
        this.LabelROMStatus.setText("Loaded");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        this.PanelEditorMain.add(this.LabelROMStatus, gridBagConstraints);
        this.ButtonToggleAdvanced.setText("Advanced");
        this.ButtonToggleAdvanced.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaGamePanelEditor.this.ButtonToggleAdvancedActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        this.PanelEditorMain.add(this.ButtonToggleAdvanced, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 100.0;
        this.PanelCenter.add(this.PanelEditorMain, gridBagConstraints);
        this.PanelEditorAdvanced.setMinimumSize(new Dimension(23, 160));
        this.PanelEditorAdvanced.setLayout(new GridLayout(1, 0));
        this.SPAdvanced.setVerticalScrollBarPolicy(22);
        this.TableAdvanced.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null }, { null, null, null, null } }, new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
        this.TableAdvanced.setAutoResizeMode(4);
        this.SPAdvanced.setViewportView(this.TableAdvanced);
        this.PanelEditorAdvanced.add(this.SPAdvanced);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 100.0;
        this.PanelCenter.add(this.PanelEditorAdvanced, gridBagConstraints);
        this.getContentPane().add(this.PanelCenter, "Center");
        this.MenuEdit.setText("Edit");
        this.MIPasteIcon.setText("Paste image");
        this.MIPasteIcon.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaGamePanelEditor.this.MIPasteIconActionPerformed(evt);
            }
        });
        this.MenuEdit.add(this.MIPasteIcon);
        this.MIClearIcon.setText("Clear icon");
        this.MIClearIcon.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaGamePanelEditor.this.MIClearIconActionPerformed(evt);
            }
        });
        this.MenuEdit.add(this.MIClearIcon);
        this.MenuEdit.add(this.SeparatorEditA);
        this.MICBAdvancedMode.setSelected(true);
        this.MICBAdvancedMode.setText("Advanced mode");
        this.MICBAdvancedMode.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                JStellaGamePanelEditor.this.MICBAdvancedModeActionPerformed(evt);
            }
        });
        this.MenuEdit.add(this.MICBAdvancedMode);
        this.MBMain.add(this.MenuEdit);
        this.setJMenuBar(this.MBMain);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((screenSize.width - 532) / 2, (screenSize.height - 526) / 2, 532, 526);
    }
    
    private void ButtonOKActionPerformed(final ActionEvent evt) {
        this.storeGame();
        this.myAcceptChange = true;
        this.setVisible(false);
    }
    
    private void ButtonCancelActionPerformed(final ActionEvent evt) {
        this.updateAdvancedTable(false);
        this.setVisible(false);
    }
    
    private void ButtonLoadImageActionPerformed(final ActionEvent evt) {
        this.loadImage();
    }
    
    private void ButtonLoadROMActionPerformed(final ActionEvent evt) {
        this.loadROM();
    }
    
    private void PMIPasteImageActionPerformed(final ActionEvent evt) {
        this.pasteImage();
    }
    
    private void PopupIconPopupMenuWillBecomeVisible(final PopupMenuEvent evt) {
        this.PMIPasteImage.setEnabled(this.isImageOnClipboard());
    }
    
    private void MIPasteIconActionPerformed(final ActionEvent evt) {
        this.pasteImage();
    }
    
    private void MICBAdvancedModeActionPerformed(final ActionEvent evt) {
        this.setAdvancedMode(this.MICBAdvancedMode.isSelected());
    }
    
    private void ButtonToggleAdvancedActionPerformed(final ActionEvent evt) {
        this.setAdvancedMode(!JStellaGamePanelEditor.myAdvancedMode);
    }
    
    private void MIClearIconActionPerformed(final ActionEvent evt) {
        this.setGraphicData(null);
        this.updateGameIcon();
    }
    
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                final JStellaGamePanelEditor dialog = new JStellaGamePanelEditor(new JFrame(), true);
                dialog.addWindowListener(new WindowAdapter() {
                    public void windowClosing(final WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    
    private byte[] getGraphicData() {
        return this.myIconData;
    }
    
    private void setGraphicData(final byte[] aGraphicData) {
        this.myIconData = aGraphicData;
    }
    
    private void setAdvancedMode(final boolean aAdvancedMode) {
        JStellaGamePanelEditor.myAdvancedMode = aAdvancedMode;
        this.MICBAdvancedMode.setSelected(aAdvancedMode);
        this.PanelEditorAdvanced.setVisible(aAdvancedMode);
        this.ButtonToggleAdvanced.setText(aAdvancedMode ? "Simple" : "Advanced");
        this.updateAdvancedTable(true);
        this.PanelEditorAdvanced.revalidate();
        this.PanelEditorMain.revalidate();
        this.PanelCenter.validate();
    }
    
    private void updateGameIcon() {
        if (this.myIconData != null) {
            final ImageIcon zII = new ImageIcon(this.myIconData);
            this.LabelGameIcon.setIcon(zII);
        }
        else {
            this.LabelGameIcon.setIcon(null);
        }
        this.LabelGameIcon.repaint();
    }
    
    private void updateROM() {
        if (this.myROMData == null) {
            this.LabelROMStatus.setText("NO ROM SELECTED");
        }
        else {
            this.LabelROMStatus.setText("ROM selected");
        }
    }
    
    private void displayGame(final JStellaGame aGame) {
        this.myCurrentGame = aGame;
        this.TFGameTitle.setText(aGame.getGameTitle().trim());
        this.myROMData = aGame.getROMData();
        this.myIconData = aGame.getGraphicData();
        this.setAdvancedMode(JStellaGamePanelEditor.myAdvancedMode);
        this.updateGameIcon();
        this.updateROM();
        this.updateAdvancedTable(true);
        this.validate();
        this.repaint();
    }
    
    private static boolean areDifferent(final Object aObjA, final Object aObjB) {
        boolean zReturn = true;
        if (aObjA == aObjB) {
            zReturn = false;
        }
        else if (aObjA != null && aObjA.equals(aObjB)) {
            zReturn = false;
        }
        else if (aObjB != null && aObjB.equals(aObjA)) {
            zReturn = false;
        }
        return zReturn;
    }
    
    private void storeGame() {
        this.stopCellEditing();
        this.myCurrentGame.setGameTitle(this.TFGameTitle.getText().trim());
        final boolean zUpdateMD5 = false;
        if (areDifferent(this.myCurrentGame.getROMData(), this.myROMData)) {
            this.myCurrentGame.setROMData(this.myROMData, true);
        }
        this.myCurrentGame.setGraphicData(this.myIconData);
    }
    
    private void clearPreviousGame() {
        this.myCurrentGame = null;
        this.updateAdvancedTable(true);
    }
    
    public static boolean runGamePanelEditor(final Frame aParent, final JStellaGame aGame) {
        if (JStellaGamePanelEditor.myGamePanelEditor == null) {
            JStellaGamePanelEditor.myGamePanelEditor = new JStellaGamePanelEditor(aParent, true);
        }
        JStellaGamePanelEditor.myGamePanelEditor.clearPreviousGame();
        JStellaGamePanelEditor.myGamePanelEditor.myAcceptChange = false;
        JStellaGamePanelEditor.myGamePanelEditor.displayGame(aGame);
        JStellaGamePanelEditor.myGamePanelEditor.setVisible(true);
        return JStellaGamePanelEditor.myGamePanelEditor.myAcceptChange;
    }
    
    private static byte[] readByteArrayFromStream(final InputStream aStream) throws IOException {
        byte[] zReturn = null;
        final ByteArrayOutputStream zBAOS = new ByteArrayOutputStream();
        int zInt = 0;
        while ((zInt = aStream.read()) != -1) {
            zBAOS.write(zInt);
        }
        zBAOS.close();
        zReturn = zBAOS.toByteArray();
        return zReturn;
    }
    
    private void loadROM() {
        try {
            JStellaDesktop.configureFileBrowser(false, false, true, JStellaGamePanelEditor.myLastROMDir, null, JStellaDesktop.JSFileNameExtensionFilter.FILTER_ROMS);
            final int zResult = JStellaDesktop.getFileBrowser().showOpenDialog(this);
            if (zResult == 0) {
                final FileInputStream zFIS = new FileInputStream(JStellaDesktop.getFileBrowser().getSelectedFile());
                final byte[] zTempBuffer = readByteArrayFromStream(zFIS);
                zFIS.close();
                this.myROMData = zTempBuffer;
                this.updateROM();
                JStellaGamePanelEditor.myLastROMDir = JStellaDesktop.getFileBrowser().getCurrentDirectory();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void loadImage() {
        try {
            JStellaDesktop.configureFileBrowser(false, false, true, JStellaGamePanelEditor.myLastGraphicsDir, null, JStellaDesktop.JSFileNameExtensionFilter.FILTER_GRAPHICS);
            final int zResult = JStellaDesktop.getFileBrowser().showOpenDialog(this);
            if (zResult == 0) {
                final FileInputStream zFIS = new FileInputStream(JStellaDesktop.getFileBrowser().getSelectedFile());
                final byte[] zTempBuffer = readByteArrayFromStream(zFIS);
                zFIS.close();
                final ImageIcon zII = new ImageIcon(zTempBuffer);
                final byte[] zConvertedByteArray = convertImageToByteArray(zII.getImage(), "image/jpeg", 0.5f);
                this.myIconData = zConvertedByteArray;
                this.updateGameIcon();
                JStellaGamePanelEditor.myLastGraphicsDir = JStellaDesktop.getFileBrowser().getCurrentDirectory();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static BufferedImage getFittedImage(final Image aImage) {
        final int zImageWidth = aImage.getWidth(null);
        final int zImageHeight = aImage.getHeight(null);
        double zScale = 1.0;
        if (zImageWidth > 250 || zImageHeight > 250) {
            zScale = getFitScale(false, 250.0, 250.0, zImageWidth, zImageHeight);
        }
        final int zNewWidth = (int)(zImageWidth * zScale);
        final int zNewHeight = (int)(zImageHeight * zScale);
        final BufferedImage zBImage = new BufferedImage(zNewWidth, zNewHeight, 2);
        final Graphics2D z2D = zBImage.createGraphics();
        z2D.drawImage(aImage, 0, 0, zNewWidth, zNewHeight, 0, 0, zImageWidth, zImageHeight, null);
        z2D.dispose();
        return zBImage;
    }
    
    public static byte[] convertImageToByteArray(final Image aImage, final String aDesiredMIMEType, final float aCompressionFactor) {
        byte[] zReturnArray = null;
        try {
            final ByteArrayOutputStream zBAOS = new ByteArrayOutputStream();
            final BufferedImage zBImage = getFittedImage(aImage);
            streamWriteBufferedImage(zBAOS, zBImage, aDesiredMIMEType, aCompressionFactor);
            zReturnArray = zBAOS.toByteArray();
            zBAOS.close();
        }
        catch (IOException e) {
            System.out.println("Error :  Cannot write image to stream");
        }
        catch (IllegalStateException e2) {
            System.out.println("Error :  Cannot find proper writer");
        }
        return zReturnArray;
    }
    
    public static void streamWriteBufferedImage(final OutputStream aStream, final BufferedImage aImage, final String aMIMEType, final float aCompressionFactor) {
        try {
            final Iterator zWriters = ImageIO.getImageWritersByMIMEType(aMIMEType);
            if (!zWriters.hasNext()) {
                throw new IllegalStateException("mvError : Cannot find proper writer");
            }
            final ImageWriter zIWriter = zWriters.next();
            final ImageWriteParam zParam = zIWriter.getDefaultWriteParam();
            zParam.setCompressionMode(2);
            final List<BufferedImage> zThumbNails = null;
            final IIOImage iioImage = new IIOImage(aImage, zThumbNails, null);
            final ImageOutputStream zOut = ImageIO.createImageOutputStream(aStream);
            if (zParam.canWriteCompressed()) {
                zParam.setCompressionQuality(aCompressionFactor);
            }
            zIWriter.setOutput(zOut);
            zIWriter.write(null, iioImage, zParam);
            zOut.flush();
            zOut.close();
        }
        catch (IOException e) {
            System.out.println("Error :  Cannot write buffered image to stream");
        }
        catch (IllegalStateException e2) {
            System.out.println("Error :  Cannot find proper writer");
        }
    }
    
    public static double getFitScale(final boolean aOutsideScale, final double aBoundsWidth, final double aBoundsHeight, final double aInsiderWidth, final double aInsiderHeight) {
        final double zWidthScale = aBoundsWidth / aInsiderWidth;
        final double zHeightScale = aBoundsHeight / aInsiderHeight;
        if (!aOutsideScale) {
            return Math.min(zWidthScale, zHeightScale);
        }
        return Math.max(zWidthScale, zHeightScale);
    }
    
    private void initAdvancedTable() {
        this.myTableModel = new AdvancedTableModel();
        this.TableAdvanced.setModel(this.myTableModel);
        this.updateAdvancedTable(true);
    }
    
    private boolean isImageOnClipboard() {
        boolean zReturn = false;
        final Clipboard zCB = Toolkit.getDefaultToolkit().getSystemClipboard();
        if (zCB != null) {
            final DataFlavor[] zDFs = zCB.getAvailableDataFlavors();
            for (int i = 0; i < zDFs.length; ++i) {
                if (zDFs[i].isMimeTypeEqual("image/x-java-image")) {
                    zReturn = true;
                    break;
                }
            }
        }
        return zReturn;
    }
    
    private void pasteImage() {
        final Clipboard zCB = Toolkit.getDefaultToolkit().getSystemClipboard();
        if (zCB != null) {
            final DataFlavor[] zDFs = zCB.getAvailableDataFlavors();
            for (int i = 0; i < zDFs.length; ++i) {
                if (zDFs[i].isMimeTypeEqual("image/x-java-image")) {
                    try {
                        final Object zObj = zCB.getData(zDFs[i]);
                        if (zObj instanceof Image) {
                            final byte[] zImageBytes = convertImageToByteArray((Image)zObj, "image/jpeg", 0.5f);
                            this.myIconData = zImageBytes;
                            this.updateGameIcon();
                            System.out.println("Pasted image");
                        }
                    }
                    catch (UnsupportedFlavorException ex) {
                        ex.printStackTrace();
                    }
                    catch (IOException ex2) {
                        ex2.printStackTrace();
                    }
                }
            }
        }
    }
    
    private void stopCellEditing() {
        final int zColumn = this.TableAdvanced.getEditingColumn();
        if (zColumn > -1) {
            final TableCellEditor zCellEditor = this.TableAdvanced.getCellEditor();
            if (zCellEditor != null) {
                zCellEditor.stopCellEditing();
            }
        }
    }
    
    private void updateAdvancedTable(final boolean aContentHasChanged) {
        this.stopCellEditing();
        if (aContentHasChanged) {
            this.myTableModel.updateTable();
        }
        this.TableAdvanced.revalidate();
    }
    
    static {
        JStellaGamePanelEditor.myAdvancedMode = false;
        JStellaGamePanelEditor.myGamePanelEditor = null;
        Collections.sort(JStellaGamePanelEditor.myKeyList = new ArrayList<String>(JStellaGame.myKeyTranslations.keySet()));
        JStellaGamePanelEditor.myLastROMDir = new File("");
        JStellaGamePanelEditor.myLastGraphicsDir = new File("");
    }
    
    private class AdvancedTableModel extends AbstractTableModel
    {
        public int getRowCount() {
            return JStellaGamePanelEditor.myKeyList.size();
        }
        
        public int getColumnCount() {
            return 2;
        }
        
        public Object getValueAt(final int rowIndex, final int columnIndex) {
            Object zReturn = null;
            final String zKey = JStellaGamePanelEditor.myKeyList.get(rowIndex);
            if (columnIndex == 0) {
                zReturn = JStellaGame.myKeyTranslations.get(zKey);
            }
            else {
                if (JStellaGamePanelEditor.this.myCurrentGame != null) {
                    zReturn = JStellaGamePanelEditor.this.myCurrentGame.getGameConfig().get(zKey);
                }
                if (zReturn == null) {
                    zReturn = "";
                }
            }
            return zReturn;
        }
        
        public Class<?> getColumnClass(final int columnIndex) {
            return String.class;
        }
        
        public String getColumnName(final int column) {
            switch (column) {
                case 0: {
                    return "Config key";
                }
                default: {
                    return "Value";
                }
            }
        }
        
        public boolean isCellEditable(final int rowIndex, final int columnIndex) {
            return columnIndex != 0;
        }
        
        public void setValueAt(final Object aValue, final int rowIndex, final int columnIndex) {
            if (aValue instanceof String) {
                final String zNewString = (String)aValue;
                final String zKey = JStellaGamePanelEditor.myKeyList.get(rowIndex);
                JStellaGamePanelEditor.this.myCurrentGame.getGameConfig().put(zKey, zNewString);
            }
        }
        
        public void updateTable() {
            this.fireTableDataChanged();
        }
    }
}
