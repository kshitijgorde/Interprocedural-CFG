// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui;

import java.awt.event.MouseEvent;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.awt.event.ItemEvent;
import java.awt.event.FocusEvent;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Component;
import java.awt.Color;
import java.awt.SystemColor;
import com.sun.java.swing.BoxLayout;
import com.objectbox.runner.model.JBAppletHolder;
import com.objectbox.runner.util.Hyperlink;
import java.net.URL;
import java.util.Enumeration;
import java.awt.Point;
import com.objectbox.runner.model.JBAppletProperties;
import com.objectbox.runner.model.JBProperties;
import com.objectbox.runner.util.JBLogger;
import java.awt.Frame;
import com.objectbox.runner.beans.AbstrTableModel;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import com.objectbox.jadvertise.JAdPanel;
import com.objectbox.gui.lwcomp.LWSeparator;
import com.objectbox.gui.lwcomp.DoubleBufferPanel;
import java.awt.CardLayout;
import java.util.Vector;
import java.awt.Label;
import com.roguewave.blend.progress.v2_0.ProgressBar;
import java.awt.BorderLayout;
import java.util.Hashtable;
import java.awt.Choice;
import com.objectbox.runner.util.AppletTagParser;
import java.awt.TextField;
import com.objectbox.gui.lwcomp.FlatButton;
import com.objectbox.runner.beans.TableView;
import com.objectbox.runner.model.NewAppTableModel;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.Panel;

public class JBSearchPanel extends Panel implements ActionListener, KeyListener
{
    private NewAppTableModel ivjNewAppTableModel;
    private TableView ivjNewAppTableView;
    private FlatButton ivjFlatButtonClear;
    private FlatButton ivjFlatButtonSaveApp;
    private FlatButton ivjFlatButtonStart;
    private FlatButton ivjFlatButtonStop;
    private TextField ivjTextFieldURL;
    private AppletTagParser atp;
    private FlatButton ivjFlatButtonTest;
    protected transient SavePropertiesListener aSavePropertiesListener;
    private Panel ivjPanelAddress;
    private Choice ivjChoiceSearchURL;
    private Hashtable siteHash;
    protected ThreadGroup threadgroup;
    private BorderLayout ivjSearchPanelBorderLayout;
    private Panel ivjPanelStatus;
    private ProgressBar ivjStatusbar;
    private Label ivjLabelStatus;
    private int locallevel;
    private int remotelevel;
    private FlatButton ivjFlatButtonOpenBrowser;
    private FlatButton ivjFlatButtonSaveAll;
    private Vector historyModel;
    private int maxHistoryLength;
    private int currentHistoryPosition;
    private int searchnr;
    private Panel ivjPanelSouth;
    private Panel ivjPanelCenter;
    private Panel ivjPanelMenuCarddeck;
    private Panel ivjPanelScroll;
    private Label ivjLabelFolder;
    private Label ivjLabelURL;
    private CardLayout ivjcardlayout;
    private FlatButton ivjFlatButtonManage;
    private Label ivjLabelFolderTx;
    private FlatButton ivjFlatButtonCopy;
    private FlatButton ivjFlatButtonCut;
    private FlatButton ivjFlatButtonDelete;
    private FlatButton ivjFlatButtonNewfolder;
    private FlatButton ivjFlatButtonPaste;
    private FlatButton ivjFlatButtonProperties;
    private FlatButton ivjFlatButtonRun;
    private FlatButton ivjFlatButtonSwitchToSearch;
    private FlatButton ivjFlatButtonUndo;
    private JBManagerPanel ivjJBManagerPanel;
    private CardLayout ivjCardLayoutCenter;
    private DoubleBufferPanel ivjDoubleBufferPanelMenuManage;
    private DoubleBufferPanel ivjDoubleBufferPanelMenuSearch;
    private DoubleBufferPanel ivjPanelLogo;
    private JBImageSpinner ivjJBImageSpinner1;
    private LWSeparator ivjLWSeparator1;
    private LWSeparator ivjLWSeparator2;
    private LWSeparator ivjLWSeparator3;
    private Object adspot_applet;
    private JAdPanel ivjJAdPanel;
    private FlatButton ivjFlatButtonBrowser;
    private LWSeparator ivjLWSeparator;
    
    public JBSearchPanel() {
        this.ivjNewAppTableModel = null;
        this.ivjNewAppTableView = null;
        this.ivjFlatButtonClear = null;
        this.ivjFlatButtonSaveApp = null;
        this.ivjFlatButtonStart = null;
        this.ivjFlatButtonStop = null;
        this.ivjTextFieldURL = null;
        this.atp = null;
        this.ivjFlatButtonTest = null;
        this.aSavePropertiesListener = null;
        this.ivjPanelAddress = null;
        this.ivjChoiceSearchURL = null;
        this.siteHash = null;
        this.threadgroup = new ThreadGroup("testrun");
        this.ivjSearchPanelBorderLayout = null;
        this.ivjPanelStatus = null;
        this.ivjStatusbar = null;
        this.ivjLabelStatus = null;
        this.locallevel = 10;
        this.remotelevel = 10;
        this.ivjFlatButtonOpenBrowser = null;
        this.ivjFlatButtonSaveAll = null;
        this.historyModel = null;
        this.maxHistoryLength = 10;
        this.currentHistoryPosition = 0;
        this.searchnr = 0;
        this.ivjPanelSouth = null;
        this.ivjPanelCenter = null;
        this.ivjPanelMenuCarddeck = null;
        this.ivjPanelScroll = null;
        this.ivjLabelFolder = null;
        this.ivjLabelURL = null;
        this.ivjcardlayout = null;
        this.ivjFlatButtonManage = null;
        this.ivjLabelFolderTx = null;
        this.ivjFlatButtonCopy = null;
        this.ivjFlatButtonCut = null;
        this.ivjFlatButtonDelete = null;
        this.ivjFlatButtonNewfolder = null;
        this.ivjFlatButtonPaste = null;
        this.ivjFlatButtonProperties = null;
        this.ivjFlatButtonRun = null;
        this.ivjFlatButtonSwitchToSearch = null;
        this.ivjFlatButtonUndo = null;
        this.ivjJBManagerPanel = null;
        this.ivjCardLayoutCenter = null;
        this.ivjDoubleBufferPanelMenuManage = null;
        this.ivjDoubleBufferPanelMenuSearch = null;
        this.ivjPanelLogo = null;
        this.ivjJBImageSpinner1 = null;
        this.ivjLWSeparator1 = null;
        this.ivjLWSeparator2 = null;
        this.ivjLWSeparator3 = null;
        this.adspot_applet = null;
        this.ivjJAdPanel = null;
        this.ivjFlatButtonBrowser = null;
        this.ivjLWSeparator = null;
        this.initialize();
    }
    
    public JBSearchPanel(final LayoutManager layoutManager) {
        super(layoutManager);
        this.ivjNewAppTableModel = null;
        this.ivjNewAppTableView = null;
        this.ivjFlatButtonClear = null;
        this.ivjFlatButtonSaveApp = null;
        this.ivjFlatButtonStart = null;
        this.ivjFlatButtonStop = null;
        this.ivjTextFieldURL = null;
        this.atp = null;
        this.ivjFlatButtonTest = null;
        this.aSavePropertiesListener = null;
        this.ivjPanelAddress = null;
        this.ivjChoiceSearchURL = null;
        this.siteHash = null;
        this.threadgroup = new ThreadGroup("testrun");
        this.ivjSearchPanelBorderLayout = null;
        this.ivjPanelStatus = null;
        this.ivjStatusbar = null;
        this.ivjLabelStatus = null;
        this.locallevel = 10;
        this.remotelevel = 10;
        this.ivjFlatButtonOpenBrowser = null;
        this.ivjFlatButtonSaveAll = null;
        this.historyModel = null;
        this.maxHistoryLength = 10;
        this.currentHistoryPosition = 0;
        this.searchnr = 0;
        this.ivjPanelSouth = null;
        this.ivjPanelCenter = null;
        this.ivjPanelMenuCarddeck = null;
        this.ivjPanelScroll = null;
        this.ivjLabelFolder = null;
        this.ivjLabelURL = null;
        this.ivjcardlayout = null;
        this.ivjFlatButtonManage = null;
        this.ivjLabelFolderTx = null;
        this.ivjFlatButtonCopy = null;
        this.ivjFlatButtonCut = null;
        this.ivjFlatButtonDelete = null;
        this.ivjFlatButtonNewfolder = null;
        this.ivjFlatButtonPaste = null;
        this.ivjFlatButtonProperties = null;
        this.ivjFlatButtonRun = null;
        this.ivjFlatButtonSwitchToSearch = null;
        this.ivjFlatButtonUndo = null;
        this.ivjJBManagerPanel = null;
        this.ivjCardLayoutCenter = null;
        this.ivjDoubleBufferPanelMenuManage = null;
        this.ivjDoubleBufferPanelMenuSearch = null;
        this.ivjPanelLogo = null;
        this.ivjJBImageSpinner1 = null;
        this.ivjLWSeparator1 = null;
        this.ivjLWSeparator2 = null;
        this.ivjLWSeparator3 = null;
        this.adspot_applet = null;
        this.ivjJAdPanel = null;
        this.ivjFlatButtonBrowser = null;
        this.ivjLWSeparator = null;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.getFlatButtonStart()) {
            this.connEtoC1(actionEvent);
        }
        if (actionEvent.getSource() == this.getTextFieldURL()) {
            this.connEtoC2(actionEvent);
        }
        if (actionEvent.getSource() == this.getFlatButtonClear()) {
            this.connEtoC3();
        }
        if (actionEvent.getSource() == this.getFlatButtonStop()) {
            this.connEtoC4();
        }
        if (actionEvent.getSource() == this.getFlatButtonSaveApp()) {
            this.connEtoC6(actionEvent);
        }
        if (actionEvent.getSource() == this.getFlatButtonTest()) {
            this.connEtoC8(actionEvent);
        }
        if (actionEvent.getSource() == this.getFlatButtonOpenBrowser()) {
            this.connEtoC10(actionEvent);
        }
        if (actionEvent.getSource() == this.getFlatButtonSaveAll()) {
            this.connEtoC11(actionEvent);
        }
        if (actionEvent.getSource() == this.getFlatButtonManage()) {
            this.connEtoM1(actionEvent);
        }
        if (actionEvent.getSource() == this.getFlatButtonSwitchToSearch()) {
            this.connEtoM2(actionEvent);
        }
        if (actionEvent.getSource() == this.getFlatButtonManage()) {
            this.connEtoM3(actionEvent);
        }
        if (actionEvent.getSource() == this.getFlatButtonSwitchToSearch()) {
            this.connEtoM4(actionEvent);
        }
        if (actionEvent.getSource() == this.getFlatButtonNewfolder()) {
            this.connEtoC5(actionEvent);
        }
        if (actionEvent.getSource() == this.getFlatButtonNewfolder()) {
            this.connEtoC7(actionEvent);
        }
        if (actionEvent.getSource() == this.getFlatButtonProperties()) {
            this.connEtoC9(actionEvent);
        }
        if (actionEvent.getSource() == this.getFlatButtonRun()) {
            this.connEtoC12();
        }
        if (actionEvent.getSource() == this.getFlatButtonCut()) {
            this.connEtoC13();
        }
        if (actionEvent.getSource() == this.getFlatButtonCopy()) {
            this.connEtoC15();
        }
        if (actionEvent.getSource() == this.getFlatButtonPaste()) {
            this.connEtoC16();
        }
        if (actionEvent.getSource() == this.getFlatButtonDelete()) {
            this.connEtoC17();
        }
        if (actionEvent.getSource() == this.getFlatButtonUndo()) {
            this.connEtoC18();
        }
        if (actionEvent.getSource() == this.getFlatButtonBrowser()) {
            this.connEtoC19();
        }
    }
    
    public void addSavePropertiesListener(final SavePropertiesListener savePropertiesListener) {
        this.aSavePropertiesListener = SavePropertiesEventMulticaster.add(this.aSavePropertiesListener, savePropertiesListener);
    }
    
    private void connEtoC1(final ActionEvent actionEvent) {
        try {
            this.flatButtonStart_ActionPerformed(actionEvent);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC10(final ActionEvent actionEvent) {
        try {
            this.flatButtonOpenBrowser_ActionPerformed();
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC11(final ActionEvent actionEvent) {
        try {
            this.flatButtonSaveAll_ActionPerformed(actionEvent);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC12() {
        try {
            this.flatButtonRun_Action();
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC13() {
        try {
            this.flatButtonCut_Action();
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC14(final KeyEvent keyEvent) {
        try {
            this.textFieldURL_KeyPressed(keyEvent);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC15() {
        try {
            this.flatButtonCopy_Action();
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC16() {
        try {
            this.flatButtonPaste_Action();
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC17() {
        try {
            this.flatButtonDelete_Action();
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC18() {
        try {
            this.flatButtonUndo_Action();
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC19() {
        try {
            this.flatButtonBrowser_Action();
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC2(final ActionEvent actionEvent) {
        try {
            this.flatButtonStart_ActionPerformed(actionEvent);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC3() {
        try {
            this.flatButtonClear_Action();
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC4() {
        try {
            this.flatButtonStop_Action();
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC5(final ActionEvent actionEvent) {
        try {
            this.flatButtonNewfolder_ActionPerformed(actionEvent);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC6(final ActionEvent actionEvent) {
        try {
            this.flatButtonSaveApp_ActionPerformed(actionEvent);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC7(final ActionEvent actionEvent) {
        try {
            this.flatButtonNewfolder_ActionPerformed1(actionEvent);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC8(final ActionEvent actionEvent) {
        try {
            this.flatButtonTest_ActionPerformed(actionEvent);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoC9(final ActionEvent actionEvent) {
        try {
            this.flatButtonProperties_ActionPerformed(actionEvent);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoM1(final ActionEvent actionEvent) {
        try {
            this.getcardlayout().next(this.getPanelMenuCarddeck());
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoM2(final ActionEvent actionEvent) {
        try {
            this.getcardlayout().next(this.getPanelMenuCarddeck());
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoM3(final ActionEvent actionEvent) {
        try {
            this.getCardLayoutCenter().next(this.getPanelCenter());
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connEtoM4(final ActionEvent actionEvent) {
        try {
            this.getCardLayoutCenter().next(this.getPanelCenter());
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connPtoP1SetTarget() {
        try {
            this.getNewAppTableView().setModel(this.getNewAppTableModel());
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connPtoP2SetTarget() {
        try {
            this.getPanelMenuCarddeck().setLayout(this.getcardlayout());
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    private void connPtoP3SetTarget() {
        try {
            this.getPanelCenter().setLayout(this.getCardLayoutCenter());
        }
        catch (Throwable t) {
            this.handleException(t);
        }
    }
    
    protected void fireOnSaveProperties(final SavePropertiesEvent savePropertiesEvent) {
        if (this.aSavePropertiesListener == null) {
            return;
        }
        this.aSavePropertiesListener.onSaveProperties(savePropertiesEvent);
    }
    
    public void flatButtonBrowser_Action() {
        final Object lookup = AppRegistry.getInstance().lookup("Manager");
        if (lookup != null) {
            ((JBManagerPanel)lookup).runCommand("Browser");
        }
    }
    
    public void flatButtonClear_Action() {
        this.getNewAppTableModel().setData(new Vector());
        this.getNewAppTableView().setModel(this.getNewAppTableModel());
        this.getNewAppTableView().setCurrent(0, this.getNewAppTableView().getNumberOfRows());
        this.getNewAppTableView().repaint();
    }
    
    public void flatButtonClose_ActionPerformed(final ActionEvent actionEvent) {
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof Frame); container = container.getParent()) {}
        ((Frame)container).setVisible(false);
    }
    
    public void flatButtonCopy_Action() {
        final Object lookup = AppRegistry.getInstance().lookup("Manager");
        if (lookup != null) {
            ((JBManagerPanel)lookup).runCommand("Copy");
        }
    }
    
    public void flatButtonCut_Action() {
        final Object lookup = AppRegistry.getInstance().lookup("Manager");
        if (lookup != null) {
            ((JBManagerPanel)lookup).runCommand("Cut");
        }
    }
    
    public void flatButtonDelete_Action() {
        final Object lookup = AppRegistry.getInstance().lookup("Manager");
        if (lookup != null) {
            ((JBManagerPanel)lookup).runCommand("Delete");
        }
    }
    
    public void flatButtonNewfolder_ActionPerformed(final ActionEvent actionEvent) {
        final Object lookup = AppRegistry.getInstance().lookup("Manager");
        if (lookup != null) {
            ((JBManagerPanel)lookup).runCommand("NewFolder");
        }
    }
    
    public void flatButtonNewfolder_ActionPerformed1(final ActionEvent actionEvent) {
        try {
            ((JBManagerPanel)AppRegistry.getInstance().lookup("Manager")).updateVisual();
        }
        catch (Throwable t) {
            JBLogger.log("flatButtonNewfolder_ActionPerformed: " + t);
        }
    }
    
    public void flatButtonOpenBrowser_ActionPerformed() {
        try {
            final JBProperties property = this.getNewAppTableModel().getProperty(this.getNewAppTableView().getCurrent().y);
            JBee.displayURL(String.valueOf(property.getProps().get("documentbase")) + (String)property.getProps().get("webpage"));
        }
        catch (Exception ex) {
            JBLogger.log("ex i flatButtonOpenBrowser " + ex);
        }
    }
    
    public void flatButtonPaste_Action() {
        final Object lookup = AppRegistry.getInstance().lookup("Manager");
        if (lookup != null) {
            ((JBManagerPanel)lookup).runCommand("Paste");
        }
    }
    
    public void flatButtonPause_Action() {
    }
    
    public void flatButtonProperties_ActionPerformed(final ActionEvent actionEvent) {
        final Object lookup = AppRegistry.getInstance().lookup("Manager");
        if (lookup != null) {
            ((JBManagerPanel)lookup).runCommand("Prefere");
        }
    }
    
    public void flatButtonRun_Action() {
        final Object lookup = AppRegistry.getInstance().lookup("Manager");
        if (lookup != null) {
            ((JBManagerPanel)lookup).runCommand("Run");
        }
    }
    
    public void flatButtonSaveAll_ActionPerformed(final ActionEvent actionEvent) {
        synchronized (this.getNewAppTableModel()) {
            try {
                final Object lookup = AppRegistry.getInstance().lookup("Manager");
                if (lookup == null) {
                    // monitorexit(this.getNewAppTableModel())
                    return;
                }
                final JBManagerPanel jbManagerPanel = (JBManagerPanel)lookup;
                final int numberOfRows = this.getNewAppTableModel().getNumberOfRows();
                final Vector<Vector<String>> vector = new Vector<Vector<String>>();
                for (int i = 1; i < numberOfRows; ++i) {
                    final JBProperties property = this.getNewAppTableModel().getProperty(i);
                    String s = ((Hashtable<K, String>)property.getProps()).get("code");
                    if (s.endsWith(".class")) {
                        s = s.substring(0, s.lastIndexOf("."));
                    }
                    if (s.indexOf(".") > 0) {
                        s = s.substring(s.lastIndexOf(".") + 1);
                    }
                    final Vector<String> vector2 = new Vector<String>();
                    vector2.addElement(s);
                    vector2.addElement((String)new JBAppletProperties(property));
                    vector.addElement(vector2);
                }
                jbManagerPanel.installAppList(vector);
            }
            catch (Throwable t) {
                JBLogger.log("Exception i saveAllAction..." + t);
            }
        }
        // monitorexit(this.getNewAppTableModel())
    }
    
    public void flatButtonSaveApp_ActionPerformed(final ActionEvent actionEvent) {
        try {
            synchronized (this.getNewAppTableModel()) {
                try {
                    final Object lookup = AppRegistry.getInstance().lookup("Manager");
                    if (lookup == null) {
                        // monitorexit(this.getNewAppTableModel())
                        return;
                    }
                    final JBManagerPanel jbManagerPanel = (JBManagerPanel)lookup;
                    this.getNewAppTableModel().getNumberOfRows();
                    final Vector vector = new Vector<Vector<String>>();
                    final Enumeration rowSelectionEnumeration = this.getNewAppTableView().getRowSelectionEnumeration();
                    while (rowSelectionEnumeration.hasMoreElements()) {
                        final JBProperties property = this.getNewAppTableModel().getProperty(rowSelectionEnumeration.nextElement().y);
                        String s = ((Hashtable<K, String>)property.getProps()).get("code");
                        if (s.endsWith(".class")) {
                            s = s.substring(0, s.lastIndexOf("."));
                        }
                        if (s.indexOf(".") > 0) {
                            s = s.substring(s.lastIndexOf(".") + 1);
                        }
                        final Vector<String> vector2 = new Vector<String>();
                        vector2.addElement(s);
                        vector2.addElement((String)new JBAppletProperties(property));
                        vector.addElement(vector2);
                    }
                    if (vector.size() > 0) {
                        jbManagerPanel.installAppList(vector);
                        // monitorexit(this.getNewAppTableModel())
                        return;
                    }
                }
                catch (Throwable t) {
                    JBLogger.log("Exception i saveAllAction..." + t);
                }
            }
            // monitorexit(this.getNewAppTableModel())
            final JBProperties property2 = this.getNewAppTableModel().getProperty(this.getNewAppTableView().getCurrent().y);
            String s2 = ((Hashtable<K, String>)property2.getProps()).get("code");
            if (s2.endsWith(".class")) {
                s2 = s2.substring(0, s2.lastIndexOf("."));
            }
            if (s2.indexOf(".") > 0) {
                s2 = s2.substring(s2.lastIndexOf(".") + 1);
            }
            final Object lookup2 = AppRegistry.getInstance().lookup("Manager");
            if (lookup2 != null) {
                ((JBManagerPanel)lookup2).installApp(s2, new JBAppletProperties(property2));
            }
        }
        catch (Throwable t2) {
            JBLogger.log("Exception i saveAppAction..." + t2);
        }
    }
    
    public void flatButtonStart_ActionPerformed(final ActionEvent actionEvent) {
        this.flatButtonStop_Action();
        if (!this.getJAdPanel().isAppletLoaded()) {
            this.getJAdPanel().loadApplet();
        }
        this.getJBImageSpinner1().toggle(true);
        boolean b = true;
        final String text = this.getTextFieldURL().getText();
        for (int size = this.historyModel.size(), i = 0; i < size; ++i) {
            if (this.historyModel.elementAt(i).toString().equals(text)) {
                b = false;
                break;
            }
        }
        if (b) {
            this.historyModel.insertElementAt(text, 0);
        }
        if (this.historyModel.size() > this.maxHistoryLength) {
            this.historyModel.removeElementAt(this.historyModel.size() - 1);
        }
        try {
            boolean b2 = false;
            this.updateStatus(0, 0, 0, "");
            URL url;
            if (text.toLowerCase().startsWith("http://") || text.toLowerCase().startsWith("file:")) {
                url = new URL(text);
            }
            else if (text.indexOf(".") > 0) {
                url = new URL("http://" + text);
            }
            else {
                url = new URL(String.valueOf("http://www.altavista.com/cgi-bin/query?text=yes&q=applet:") + text.replace(' ', '+'));
                b2 = true;
            }
            ++this.searchnr;
            final ThreadGroup threadGroup = new ThreadGroup("parsegroup" + this.searchnr);
            threadGroup.setMaxPriority(1);
            this.atp = new AppletTagParser(threadGroup, "start");
            if (b2) {
                this.atp.setPrefs(true, true, 5, 5, url);
            }
            else {
                if (this.getChoiceSearchURL().getSelectedItem() == "this page") {
                    this.atp.setPrefs(false, false, 1, 0, url);
                }
                if (this.getChoiceSearchURL().getSelectedItem() == "this site") {
                    this.atp.setPrefs(true, false, this.locallevel, 0, url);
                }
                if (this.getChoiceSearchURL().getSelectedItem() == "all links") {
                    this.atp.setPrefs(true, true, this.locallevel, this.remotelevel, url);
                }
            }
            final Hyperlink startURL = new Hyperlink();
            startURL.url = url;
            this.atp.setStartURL(startURL);
            this.atp.setHandle(this);
            this.atp.start();
        }
        catch (Exception ex) {
            JBLogger.log("Ex:  " + ex);
        }
    }
    
    public void flatButtonStop_Action() {
        (this.atp = new AppletTagParser()).kill();
        this.getJBImageSpinner1().toggle(false);
    }
    
    public void flatButtonTest_ActionPerformed(final ActionEvent actionEvent) {
        final JBProperties property = this.getNewAppTableModel().getProperty(this.getNewAppTableView().getCurrent().y);
        final JBAppletHolder jbAppletHolder = new JBAppletHolder();
        jbAppletHolder.setProperties(property);
        jbAppletHolder.setBeanRunner(new BeanRunner());
        jbAppletHolder.run(this.threadgroup);
    }
    
    public void flatButtonUndo_Action() {
        final Object lookup = AppRegistry.getInstance().lookup("Manager");
        if (lookup != null) {
            ((JBManagerPanel)lookup).runCommand("Undo");
        }
    }
    
    private CardLayout getcardlayout() {
        if (this.ivjcardlayout == null) {
            try {
                this.ivjcardlayout = new CardLayout();
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjcardlayout;
    }
    
    private CardLayout getCardLayoutCenter() {
        if (this.ivjCardLayoutCenter == null) {
            try {
                this.ivjCardLayoutCenter = new CardLayout();
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjCardLayoutCenter;
    }
    
    private Choice getChoiceSearchURL() {
        if (this.ivjChoiceSearchURL == null) {
            try {
                (this.ivjChoiceSearchURL = new Choice()).setName("ChoiceSearchURL");
                this.ivjChoiceSearchURL.add("this page");
                this.ivjChoiceSearchURL.add("this site");
                this.ivjChoiceSearchURL.add("all links");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjChoiceSearchURL;
    }
    
    private DoubleBufferPanel getDoubleBufferPanelMenuManage() {
        if (this.ivjDoubleBufferPanelMenuManage == null) {
            try {
                (this.ivjDoubleBufferPanelMenuManage = new DoubleBufferPanel()).setName("DoubleBufferPanelMenuManage");
                this.ivjDoubleBufferPanelMenuManage.setLayout(new BoxLayout(this.getDoubleBufferPanelMenuManage(), 0));
                this.ivjDoubleBufferPanelMenuManage.setBackground(SystemColor.control);
                this.ivjDoubleBufferPanelMenuManage.setHasframe(false);
                this.getDoubleBufferPanelMenuManage().add(this.getFlatButtonSwitchToSearch(), this.getFlatButtonSwitchToSearch().getName());
                this.getDoubleBufferPanelMenuManage().add(this.getFlatButtonNewfolder(), this.getFlatButtonNewfolder().getName());
                this.getDoubleBufferPanelMenuManage().add(this.getFlatButtonProperties(), this.getFlatButtonProperties().getName());
                this.getDoubleBufferPanelMenuManage().add(this.getFlatButtonRun(), this.getFlatButtonRun().getName());
                this.getDoubleBufferPanelMenuManage().add(this.getFlatButtonBrowser(), this.getFlatButtonBrowser().getName());
                this.ivjDoubleBufferPanelMenuManage.add(this.getLWSeparator1());
                this.getDoubleBufferPanelMenuManage().add(this.getFlatButtonCut(), this.getFlatButtonCut().getName());
                this.getDoubleBufferPanelMenuManage().add(this.getFlatButtonCopy(), this.getFlatButtonCopy().getName());
                this.getDoubleBufferPanelMenuManage().add(this.getFlatButtonPaste(), this.getFlatButtonPaste().getName());
                this.getDoubleBufferPanelMenuManage().add(this.getFlatButtonDelete(), this.getFlatButtonDelete().getName());
                this.getDoubleBufferPanelMenuManage().add(this.getFlatButtonUndo(), this.getFlatButtonUndo().getName());
                this.ivjDoubleBufferPanelMenuManage.setBackground(JBee.appcolor);
                this.ivjDoubleBufferPanelMenuManage.setHasframe(false);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjDoubleBufferPanelMenuManage;
    }
    
    private DoubleBufferPanel getDoubleBufferPanelMenuSearch() {
        if (this.ivjDoubleBufferPanelMenuSearch == null) {
            try {
                (this.ivjDoubleBufferPanelMenuSearch = new DoubleBufferPanel()).setName("DoubleBufferPanelMenuSearch");
                this.ivjDoubleBufferPanelMenuSearch.setLayout(new BoxLayout(this.getDoubleBufferPanelMenuSearch(), 0));
                this.ivjDoubleBufferPanelMenuSearch.setBackground(SystemColor.control);
                this.ivjDoubleBufferPanelMenuSearch.setHasframe(false);
                this.getDoubleBufferPanelMenuSearch().add(this.getFlatButtonManage(), this.getFlatButtonManage().getName());
                this.getDoubleBufferPanelMenuSearch().add(this.getFlatButtonStop(), this.getFlatButtonStop().getName());
                this.getDoubleBufferPanelMenuSearch().add(this.getFlatButtonStart(), this.getFlatButtonStart().getName());
                this.getDoubleBufferPanelMenuSearch().add(this.getFlatButtonTest(), this.getFlatButtonTest().getName());
                this.getDoubleBufferPanelMenuSearch().add(this.getFlatButtonSaveApp(), this.getFlatButtonSaveApp().getName());
                this.getDoubleBufferPanelMenuSearch().add(this.getFlatButtonSaveAll(), this.getFlatButtonSaveAll().getName());
                this.getDoubleBufferPanelMenuSearch().add(this.getFlatButtonClear(), this.getFlatButtonClear().getName());
                this.getDoubleBufferPanelMenuSearch().add(this.getFlatButtonOpenBrowser(), this.getFlatButtonOpenBrowser().getName());
                this.ivjDoubleBufferPanelMenuSearch.setHasframe(false);
                this.ivjDoubleBufferPanelMenuSearch.setBackground(JBee.appcolor);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjDoubleBufferPanelMenuSearch;
    }
    
    private FlatButton getFlatButtonBrowser() {
        if (this.ivjFlatButtonBrowser == null) {
            try {
                (this.ivjFlatButtonBrowser = new FlatButton()).setName("FlatButtonBrowser");
                this.ivjFlatButtonBrowser.setHasborder(false);
                this.ivjFlatButtonBrowser.setLabel("Browser");
                this.ivjFlatButtonBrowser.setFont(new Font("dialog", 0, 10));
                this.ivjFlatButtonBrowser.setFixedsize(new Dimension(60, 50));
                this.ivjFlatButtonBrowser.setEnabled(true);
                AppRegistry.getInstance().put("FlatButtonBrowser", this.ivjFlatButtonBrowser);
                this.ivjFlatButtonBrowser.setImageResource("/images/world.gif", 0);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjFlatButtonBrowser;
    }
    
    private FlatButton getFlatButtonClear() {
        if (this.ivjFlatButtonClear == null) {
            try {
                (this.ivjFlatButtonClear = new FlatButton()).setName("FlatButtonClear");
                this.ivjFlatButtonClear.setFixedsize(new Dimension(62, 50));
                this.ivjFlatButtonClear.setLabel("Clear list");
                this.ivjFlatButtonClear.setImageResource("/images/clear.gif", 0);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjFlatButtonClear;
    }
    
    private FlatButton getFlatButtonCopy() {
        if (this.ivjFlatButtonCopy == null) {
            try {
                (this.ivjFlatButtonCopy = new FlatButton()).setName("FlatButtonCopy");
                this.ivjFlatButtonCopy.setFont(new Font("dialog", 0, 10));
                this.ivjFlatButtonCopy.setFixedsize(new Dimension(43, 50));
                this.ivjFlatButtonCopy.setEnabled(true);
                this.ivjFlatButtonCopy.setLabel("Copy");
                AppRegistry.getInstance().put("FlatButtonCopy", this.ivjFlatButtonCopy);
                this.ivjFlatButtonCopy.setImageResource("/images/copy.gif", 0);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjFlatButtonCopy;
    }
    
    private FlatButton getFlatButtonCut() {
        if (this.ivjFlatButtonCut == null) {
            try {
                (this.ivjFlatButtonCut = new FlatButton()).setName("FlatButtonCut");
                this.ivjFlatButtonCut.setFont(new Font("dialog", 0, 10));
                this.ivjFlatButtonCut.setFixedsize(new Dimension(43, 50));
                this.ivjFlatButtonCut.setEnabled(true);
                this.ivjFlatButtonCut.setLabel("Cut");
                AppRegistry.getInstance().put("FlatButtonCut", this.ivjFlatButtonCut);
                this.ivjFlatButtonCut.setImageResource("/images/cut.gif", 0);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjFlatButtonCut;
    }
    
    private FlatButton getFlatButtonDelete() {
        if (this.ivjFlatButtonDelete == null) {
            try {
                (this.ivjFlatButtonDelete = new FlatButton()).setName("FlatButtonDelete");
                this.ivjFlatButtonDelete.setFont(new Font("dialog", 0, 10));
                this.ivjFlatButtonDelete.setFixedsize(new Dimension(43, 50));
                this.ivjFlatButtonDelete.setEnabled(true);
                this.ivjFlatButtonDelete.setLabel("Delete");
                AppRegistry.getInstance().put("FlatButtonDelete", this.ivjFlatButtonDelete);
                this.ivjFlatButtonDelete.setImageResource("/images/delete.gif", 0);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjFlatButtonDelete;
    }
    
    private FlatButton getFlatButtonManage() {
        if (this.ivjFlatButtonManage == null) {
            try {
                (this.ivjFlatButtonManage = new FlatButton()).setName("FlatButtonManage");
                this.ivjFlatButtonManage.setFixedsize(new Dimension(62, 50));
                this.ivjFlatButtonManage.setEnabled(true);
                this.ivjFlatButtonManage.setLabel("Admin");
                this.ivjFlatButtonManage.setHasborder(false);
                this.ivjFlatButtonManage.setImageResource("/images/admin.gif", 0);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjFlatButtonManage;
    }
    
    private FlatButton getFlatButtonNewfolder() {
        if (this.ivjFlatButtonNewfolder == null) {
            try {
                (this.ivjFlatButtonNewfolder = new FlatButton()).setName("FlatButtonNewfolder");
                this.ivjFlatButtonNewfolder.setFont(new Font("dialog", 0, 10));
                this.ivjFlatButtonNewfolder.setFixedsize(new Dimension(70, 50));
                this.ivjFlatButtonNewfolder.setEnabled(true);
                this.ivjFlatButtonNewfolder.setLabel("New Folder");
                AppRegistry.getInstance().put("FlatButtonNewFolder", this.ivjFlatButtonNewfolder);
                this.ivjFlatButtonNewfolder.setImageResource("/images/foldernew.gif", 0);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjFlatButtonNewfolder;
    }
    
    private FlatButton getFlatButtonOpenBrowser() {
        if (this.ivjFlatButtonOpenBrowser == null) {
            try {
                (this.ivjFlatButtonOpenBrowser = new FlatButton()).setName("FlatButtonOpenBrowser");
                this.ivjFlatButtonOpenBrowser.setFixedsize(new Dimension(62, 50));
                this.ivjFlatButtonOpenBrowser.setEnabled(true);
                this.ivjFlatButtonOpenBrowser.setLabel("Browser");
                this.ivjFlatButtonOpenBrowser.setImageResource("/images/world.gif", 0);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjFlatButtonOpenBrowser;
    }
    
    private FlatButton getFlatButtonPaste() {
        if (this.ivjFlatButtonPaste == null) {
            try {
                (this.ivjFlatButtonPaste = new FlatButton()).setName("FlatButtonPaste");
                this.ivjFlatButtonPaste.setFont(new Font("dialog", 0, 10));
                this.ivjFlatButtonPaste.setFixedsize(new Dimension(43, 50));
                this.ivjFlatButtonPaste.setEnabled(false);
                this.ivjFlatButtonPaste.setLabel("Paste");
                AppRegistry.getInstance().put("FlatButtonPaste", this.ivjFlatButtonPaste);
                this.ivjFlatButtonPaste.setImageResource("/images/paste.gif", 0);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjFlatButtonPaste;
    }
    
    private FlatButton getFlatButtonProperties() {
        if (this.ivjFlatButtonProperties == null) {
            try {
                (this.ivjFlatButtonProperties = new FlatButton()).setName("FlatButtonProperties");
                this.ivjFlatButtonProperties.setFont(new Font("dialog", 0, 10));
                this.ivjFlatButtonProperties.setFixedsize(new Dimension(70, 50));
                this.ivjFlatButtonProperties.setEnabled(true);
                this.ivjFlatButtonProperties.setLabel("Properties");
                AppRegistry.getInstance().put("FlatButtonProperties", this.ivjFlatButtonProperties);
                this.ivjFlatButtonProperties.setImageResource("/images/properties.gif", 0);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjFlatButtonProperties;
    }
    
    private FlatButton getFlatButtonRun() {
        if (this.ivjFlatButtonRun == null) {
            try {
                (this.ivjFlatButtonRun = new FlatButton()).setName("FlatButtonRun");
                this.ivjFlatButtonRun.setHasborder(false);
                this.ivjFlatButtonRun.setLabel("Run");
                this.ivjFlatButtonRun.setFont(new Font("dialog", 0, 10));
                this.ivjFlatButtonRun.setFixedsize(new Dimension(60, 50));
                this.ivjFlatButtonRun.setEnabled(true);
                AppRegistry.getInstance().put("FlatButtonRun", this.ivjFlatButtonRun);
                this.ivjFlatButtonRun.setImageResource("/images/run.gif", 0);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjFlatButtonRun;
    }
    
    private FlatButton getFlatButtonSaveAll() {
        if (this.ivjFlatButtonSaveAll == null) {
            try {
                (this.ivjFlatButtonSaveAll = new FlatButton()).setName("FlatButtonSaveAll");
                this.ivjFlatButtonSaveAll.setFixedsize(new Dimension(62, 50));
                this.ivjFlatButtonSaveAll.setEnabled(true);
                this.ivjFlatButtonSaveAll.setLabel("Save all");
                this.ivjFlatButtonSaveAll.setImageResource("/images/saveall.gif", 0);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjFlatButtonSaveAll;
    }
    
    private FlatButton getFlatButtonSaveApp() {
        if (this.ivjFlatButtonSaveApp == null) {
            try {
                (this.ivjFlatButtonSaveApp = new FlatButton()).setName("FlatButtonSaveApp");
                this.ivjFlatButtonSaveApp.setFixedsize(new Dimension(62, 50));
                this.ivjFlatButtonSaveApp.setEnabled(true);
                this.ivjFlatButtonSaveApp.setLabel("Save");
                this.ivjFlatButtonSaveApp.setImageResource("/images/save.gif", 0);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjFlatButtonSaveApp;
    }
    
    private FlatButton getFlatButtonStart() {
        if (this.ivjFlatButtonStart == null) {
            try {
                (this.ivjFlatButtonStart = new FlatButton()).setName("FlatButtonStart");
                this.ivjFlatButtonStart.setFixedsize(new Dimension(62, 50));
                this.ivjFlatButtonStart.setLabel("Search");
                this.ivjFlatButtonStart.setImageResource("/images/search.gif", 0);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjFlatButtonStart;
    }
    
    private FlatButton getFlatButtonStop() {
        if (this.ivjFlatButtonStop == null) {
            try {
                (this.ivjFlatButtonStop = new FlatButton()).setName("FlatButtonStop");
                this.ivjFlatButtonStop.setFont(new Font("dialog", 0, 12));
                this.ivjFlatButtonStop.setFixedsize(new Dimension(62, 50));
                this.ivjFlatButtonStop.setLabel("Stop");
                this.ivjFlatButtonStop.setImageResource("/images/stop.gif", 0);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjFlatButtonStop;
    }
    
    private FlatButton getFlatButtonSwitchToSearch() {
        if (this.ivjFlatButtonSwitchToSearch == null) {
            try {
                (this.ivjFlatButtonSwitchToSearch = new FlatButton()).setName("FlatButtonSwitchToSearch");
                this.ivjFlatButtonSwitchToSearch.setHasborder(false);
                this.ivjFlatButtonSwitchToSearch.setLabel("Back");
                this.ivjFlatButtonSwitchToSearch.setFont(new Font("dialog", 0, 10));
                this.ivjFlatButtonSwitchToSearch.setFixedsize(new Dimension(70, 50));
                this.ivjFlatButtonSwitchToSearch.setEnabled(true);
                this.ivjFlatButtonSwitchToSearch.setImageResource("/images/search.gif", 0);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjFlatButtonSwitchToSearch;
    }
    
    private FlatButton getFlatButtonTest() {
        if (this.ivjFlatButtonTest == null) {
            try {
                (this.ivjFlatButtonTest = new FlatButton()).setName("FlatButtonTest");
                this.ivjFlatButtonTest.setFixedsize(new Dimension(62, 50));
                this.ivjFlatButtonTest.setEnabled(true);
                this.ivjFlatButtonTest.setLabel("Test");
                this.ivjFlatButtonTest.setImageResource("/images/run.gif", 0);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjFlatButtonTest;
    }
    
    private FlatButton getFlatButtonUndo() {
        if (this.ivjFlatButtonUndo == null) {
            try {
                (this.ivjFlatButtonUndo = new FlatButton()).setName("FlatButtonUndo");
                this.ivjFlatButtonUndo.setVisible(false);
                this.ivjFlatButtonUndo.setLabel("Undo");
                this.ivjFlatButtonUndo.setFont(new Font("dialog", 0, 10));
                this.ivjFlatButtonUndo.setFixedsize(new Dimension(43, 50));
                this.ivjFlatButtonUndo.setEnabled(false);
                AppRegistry.getInstance().put("FlatButtonUndo", this.ivjFlatButtonUndo);
                this.ivjFlatButtonUndo.setImageResource("/images/undo.gif", 0);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjFlatButtonUndo;
    }
    
    private JAdPanel getJAdPanel() {
        if (this.ivjJAdPanel == null) {
            try {
                (this.ivjJAdPanel = new JAdPanel()).setName("JAdPanel");
                this.ivjJAdPanel.setUseimagecache(true);
                this.ivjJAdPanel.setAdsize(new Dimension(486, 60));
                this.ivjJAdPanel.setAdurl(new URL("http://www.objectbox.com/javabee/ads/jbeesearch.txt"));
                this.ivjJAdPanel.setCachedir(String.valueOf(JBee.getPreference("javabee_home")) + System.getProperty("file.separator") + "cache");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjJAdPanel;
    }
    
    private JBImageSpinner getJBImageSpinner1() {
        if (this.ivjJBImageSpinner1 == null) {
            try {
                (this.ivjJBImageSpinner1 = new JBImageSpinner()).setName("JBImageSpinner1");
                final Image imageResource = JBee.resources.getImageResource("/images/jblogo_still.gif");
                if (imageResource != null) {
                    this.ivjJBImageSpinner1.setStillImage(imageResource);
                }
                final Image imageResource2 = JBee.resources.getImageResource("/images/jblogo_anim.gif");
                if (imageResource2 != null) {
                    this.ivjJBImageSpinner1.setAnimatedImage(imageResource2);
                }
                this.ivjJBImageSpinner1.toggle(false);
                AppRegistry.getInstance().put("Spinner", this.ivjJBImageSpinner1);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjJBImageSpinner1;
    }
    
    private JBManagerPanel getJBManagerPanel() {
        if (this.ivjJBManagerPanel == null) {
            try {
                (this.ivjJBManagerPanel = new JBManagerPanel()).setName("JBManagerPanel");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjJBManagerPanel;
    }
    
    private Label getLabelFolder() {
        if (this.ivjLabelFolder == null) {
            try {
                (this.ivjLabelFolder = new Label()).setName("LabelFolder");
                this.ivjLabelFolder.setText("");
                this.ivjLabelFolder.setBackground(SystemColor.control);
                this.ivjLabelFolder.setForeground(Color.darkGray);
                this.ivjLabelFolder.setAlignment(0);
                this.ivjLabelFolder.setFont(new Font("dialog", 1, 14));
                AppRegistry.getInstance().put("LabelFolder", this.ivjLabelFolder);
                this.ivjLabelFolder.setBackground(JBee.appcolor);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLabelFolder;
    }
    
    private Label getLabelFolderTx() {
        if (this.ivjLabelFolderTx == null) {
            try {
                (this.ivjLabelFolderTx = new Label()).setName("LabelFolderTx");
                this.ivjLabelFolderTx.setText("Current folder");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLabelFolderTx;
    }
    
    private Label getLabelStatus() {
        if (this.ivjLabelStatus == null) {
            try {
                (this.ivjLabelStatus = new Label()).setName("LabelStatus");
                this.ivjLabelStatus.setText("Status");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLabelStatus;
    }
    
    private Label getLabelURL() {
        if (this.ivjLabelURL == null) {
            try {
                (this.ivjLabelURL = new Label()).setName("LabelURL");
                this.ivjLabelURL.setAlignment(0);
                this.ivjLabelURL.setText("URL or keyword");
                this.ivjLabelURL.setBackground(SystemColor.control);
                this.ivjLabelURL.setBackground(JBee.appcolor);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLabelURL;
    }
    
    private LWSeparator getLWSeparator() {
        if (this.ivjLWSeparator == null) {
            try {
                (this.ivjLWSeparator = new LWSeparator()).setName("LWSeparator");
                this.ivjLWSeparator.setDirection(true);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLWSeparator;
    }
    
    private LWSeparator getLWSeparator1() {
        if (this.ivjLWSeparator1 == null) {
            try {
                (this.ivjLWSeparator1 = new LWSeparator()).setName("LWSeparator1");
                this.ivjLWSeparator1.setPrefSize(new Dimension(10, 45));
                this.ivjLWSeparator1.setDirection(false);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLWSeparator1;
    }
    
    private LWSeparator getLWSeparator2() {
        if (this.ivjLWSeparator2 == null) {
            try {
                (this.ivjLWSeparator2 = new LWSeparator()).setName("LWSeparator2");
                this.ivjLWSeparator2.setDirection(true);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLWSeparator2;
    }
    
    private LWSeparator getLWSeparator3() {
        if (this.ivjLWSeparator3 == null) {
            try {
                (this.ivjLWSeparator3 = new LWSeparator()).setName("LWSeparator3");
                this.ivjLWSeparator3.setDirection(true);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjLWSeparator3;
    }
    
    private NewAppTableModel getNewAppTableModel() {
        if (this.ivjNewAppTableModel == null) {
            try {
                this.ivjNewAppTableModel = new NewAppTableModel();
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjNewAppTableModel;
    }
    
    private TableView getNewAppTableView() {
        if (this.ivjNewAppTableView == null) {
            try {
                (this.ivjNewAppTableView = new TableView()).setName("NewAppTableView");
                this.ivjNewAppTableView.setCellheight(18);
                this.ivjNewAppTableView.setBackground(Color.white);
                this.ivjNewAppTableView.setCellwidth(195);
                this.ivjNewAppTableView.setHeaderbackground(Color.orange);
                this.ivjNewAppTableView.setNumberOfRows(2);
                this.ivjNewAppTableView.setHeaderforeground(SystemColor.activeCaption);
                this.ivjNewAppTableView.setNumberOfcols(2);
                this.ivjNewAppTableView.setSelectmode(1);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjNewAppTableView;
    }
    
    private Panel getPanelAddress() {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
        if (this.ivjPanelAddress == null) {
            try {
                (this.ivjPanelAddress = new Panel()).setName("PanelAddress");
                this.ivjPanelAddress.setLayout(new GridBagLayout());
                this.ivjPanelAddress.setBackground(SystemColor.control);
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 2;
                gridBagConstraints.gridwidth = 1;
                gridBagConstraints.gridheight = 1;
                gridBagConstraints.fill = 2;
                gridBagConstraints.anchor = 17;
                gridBagConstraints.weightx = 0.1;
                gridBagConstraints.weighty = 0.0;
                gridBagConstraints.insets = new Insets(0, 5, 0, 0);
                this.getPanelAddress().add(this.getLabelURL(), gridBagConstraints);
                gridBagConstraints2.gridx = 1;
                gridBagConstraints2.gridy = 2;
                gridBagConstraints2.gridwidth = 1;
                gridBagConstraints2.gridheight = 1;
                gridBagConstraints2.fill = 2;
                gridBagConstraints2.anchor = 17;
                gridBagConstraints2.weightx = 1.0;
                gridBagConstraints2.weighty = 0.0;
                this.getPanelAddress().add(this.getTextFieldURL(), gridBagConstraints2);
                gridBagConstraints3.gridx = 3;
                gridBagConstraints3.gridy = 2;
                gridBagConstraints3.gridwidth = 1;
                gridBagConstraints3.gridheight = 1;
                gridBagConstraints3.fill = 2;
                gridBagConstraints3.anchor = 17;
                gridBagConstraints3.weightx = 0.15;
                gridBagConstraints3.weighty = 0.0;
                gridBagConstraints3.insets = new Insets(0, 5, 0, 5);
                this.getPanelAddress().add(this.getChoiceSearchURL(), gridBagConstraints3);
                gridBagConstraints4.gridx = 1;
                gridBagConstraints4.gridy = 4;
                gridBagConstraints4.gridwidth = 3;
                gridBagConstraints4.gridheight = 1;
                gridBagConstraints4.fill = 2;
                gridBagConstraints4.anchor = 10;
                gridBagConstraints4.weightx = 0.0;
                gridBagConstraints4.weighty = 0.0;
                gridBagConstraints4.insets = new Insets(5, 0, 5, 10);
                this.getPanelAddress().add(this.getLabelFolder(), gridBagConstraints4);
                gridBagConstraints5.gridx = 0;
                gridBagConstraints5.gridy = 4;
                gridBagConstraints5.gridwidth = 1;
                gridBagConstraints5.gridheight = 1;
                gridBagConstraints5.anchor = 17;
                gridBagConstraints5.weightx = 0.0;
                gridBagConstraints5.weighty = 0.0;
                gridBagConstraints5.insets = new Insets(5, 5, 5, 0);
                this.getPanelAddress().add(this.getLabelFolderTx(), gridBagConstraints5);
                gridBagConstraints6.gridx = 0;
                gridBagConstraints6.gridy = 0;
                gridBagConstraints6.gridwidth = 3;
                gridBagConstraints6.gridheight = 1;
                gridBagConstraints6.fill = 2;
                gridBagConstraints6.anchor = 10;
                gridBagConstraints6.weightx = 1.0;
                gridBagConstraints6.weighty = 1.0;
                this.getPanelAddress().add(this.getPanelMenuCarddeck(), gridBagConstraints6);
                gridBagConstraints7.gridx = 3;
                gridBagConstraints7.gridy = 0;
                gridBagConstraints7.gridwidth = 1;
                gridBagConstraints7.gridheight = 1;
                gridBagConstraints7.anchor = 13;
                gridBagConstraints7.weightx = 1.0;
                gridBagConstraints7.weighty = 1.0;
                gridBagConstraints7.insets = new Insets(5, 5, 5, 5);
                this.getPanelAddress().add(this.getPanelLogo(), gridBagConstraints7);
                gridBagConstraints8.gridx = 0;
                gridBagConstraints8.gridy = 1;
                gridBagConstraints8.gridwidth = 4;
                gridBagConstraints8.gridheight = 1;
                gridBagConstraints8.fill = 2;
                gridBagConstraints8.anchor = 10;
                gridBagConstraints8.weightx = 0.0;
                gridBagConstraints8.weighty = 0.0;
                this.getPanelAddress().add(this.getLWSeparator2(), gridBagConstraints8);
                gridBagConstraints9.gridx = 0;
                gridBagConstraints9.gridy = 3;
                gridBagConstraints9.gridwidth = 4;
                gridBagConstraints9.gridheight = 1;
                gridBagConstraints9.fill = 2;
                gridBagConstraints9.anchor = 10;
                gridBagConstraints9.weightx = 0.0;
                gridBagConstraints9.weighty = 0.0;
                this.getPanelAddress().add(this.getLWSeparator3(), gridBagConstraints9);
                this.getPanelAddress().setBackground(JBee.appcolor);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjPanelAddress;
    }
    
    private Panel getPanelCenter() {
        if (this.ivjPanelCenter == null) {
            try {
                (this.ivjPanelCenter = new Panel()).setName("PanelCenter");
                this.ivjPanelCenter.setLayout(new CardLayout());
                this.getPanelCenter().add(this.getPanelScroll(), this.getPanelScroll().getName());
                this.getPanelCenter().add(this.getJBManagerPanel(), this.getJBManagerPanel().getName());
                this.ivjPanelCenter.setBackground(JBee.appcolor);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjPanelCenter;
    }
    
    private DoubleBufferPanel getPanelLogo() {
        if (this.ivjPanelLogo == null) {
            try {
                (this.ivjPanelLogo = new DoubleBufferPanel()).setName("PanelLogo");
                this.ivjPanelLogo.setLayout(new BorderLayout());
                this.getPanelLogo().add(this.getJBImageSpinner1(), "Center");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjPanelLogo;
    }
    
    private Panel getPanelMenuCarddeck() {
        if (this.ivjPanelMenuCarddeck == null) {
            try {
                (this.ivjPanelMenuCarddeck = new Panel()).setName("PanelMenuCarddeck");
                this.ivjPanelMenuCarddeck.setLayout(new CardLayout());
                this.getPanelMenuCarddeck().add(this.getDoubleBufferPanelMenuSearch(), this.getDoubleBufferPanelMenuSearch().getName());
                this.getPanelMenuCarddeck().add(this.getDoubleBufferPanelMenuManage(), this.getDoubleBufferPanelMenuManage().getName());
                this.ivjPanelMenuCarddeck.setBackground(JBee.appcolor);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjPanelMenuCarddeck;
    }
    
    private Panel getPanelScroll() {
        if (this.ivjPanelScroll == null) {
            try {
                (this.ivjPanelScroll = new Panel()).setName("PanelScroll");
                this.ivjPanelScroll.setLayout(new BorderLayout());
                this.getPanelScroll().add(this.getNewAppTableView(), "Center");
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjPanelScroll;
    }
    
    private Panel getPanelSouth() {
        if (this.ivjPanelSouth == null) {
            try {
                (this.ivjPanelSouth = new Panel()).setName("PanelSouth");
                this.ivjPanelSouth.setLayout(new BorderLayout());
                this.ivjPanelSouth.setBackground(SystemColor.control);
                this.getPanelSouth().add(this.getPanelStatus(), "North");
                this.getPanelSouth().add(this.getJAdPanel(), "South");
                this.ivjPanelSouth.setBackground(JBee.appcolor);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjPanelSouth;
    }
    
    private Panel getPanelStatus() {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        if (this.ivjPanelStatus == null) {
            try {
                (this.ivjPanelStatus = new Panel()).setName("PanelStatus");
                this.ivjPanelStatus.setLayout(new GridBagLayout());
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 0;
                gridBagConstraints.gridwidth = 1;
                gridBagConstraints.gridheight = 1;
                gridBagConstraints.fill = 2;
                gridBagConstraints.anchor = 17;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.weighty = 1.0;
                gridBagConstraints.insets = new Insets(0, 10, 0, 10);
                this.getPanelStatus().add(this.getLabelStatus(), gridBagConstraints);
                gridBagConstraints2.gridx = 1;
                gridBagConstraints2.gridy = 0;
                gridBagConstraints2.gridwidth = 1;
                gridBagConstraints2.gridheight = 1;
                gridBagConstraints2.fill = 1;
                gridBagConstraints2.anchor = 10;
                gridBagConstraints2.weightx = 0.3;
                gridBagConstraints2.weighty = 1.0;
                gridBagConstraints2.ipady = -8;
                gridBagConstraints2.insets = new Insets(5, 0, 5, 15);
                this.getPanelStatus().add(this.getStatusbar(), gridBagConstraints2);
                gridBagConstraints3.gridx = 0;
                gridBagConstraints3.gridy = 1;
                gridBagConstraints3.gridwidth = 4;
                gridBagConstraints3.gridheight = 1;
                gridBagConstraints3.fill = 2;
                gridBagConstraints3.anchor = 10;
                gridBagConstraints3.weightx = 0.0;
                gridBagConstraints3.weighty = 0.0;
                this.getPanelStatus().add(this.getLWSeparator(), gridBagConstraints3);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjPanelStatus;
    }
    
    public int getParsegroupNr() {
        return this.searchnr;
    }
    
    private BorderLayout getSearchPanelBorderLayout() {
        BorderLayout borderLayout = null;
        try {
            borderLayout = new BorderLayout();
            borderLayout.setVgap(0);
            borderLayout.setHgap(0);
        }
        catch (Throwable t) {
            this.handleException(t);
        }
        return borderLayout;
    }
    
    private ProgressBar getStatusbar() {
        if (this.ivjStatusbar == null) {
            try {
                (this.ivjStatusbar = new ProgressBar()).setName("Statusbar");
                this.ivjStatusbar.setBorderThickness(1);
                this.ivjStatusbar.setBarColor(Color.orange);
                this.ivjStatusbar.setForeground(SystemColor.activeCaption);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjStatusbar;
    }
    
    private TextField getTextFieldURL() {
        if (this.ivjTextFieldURL == null) {
            try {
                (this.ivjTextFieldURL = new TextField()).setName("TextFieldURL");
                this.ivjTextFieldURL.setText("http://");
                this.ivjTextFieldURL.setBackground(Color.white);
                this.ivjTextFieldURL.setColumns(10);
            }
            catch (Throwable t) {
                this.handleException(t);
            }
        }
        return this.ivjTextFieldURL;
    }
    
    private void handleException(final Throwable t) {
    }
    
    public void init() {
        String line = "";
        try {
            this.historyModel = new Vector(10);
            String s = JBee.getPreference("javabee_home");
            if (!s.endsWith(System.getProperty("file.separator"))) {
                s = String.valueOf(s) + System.getProperty("file.separator");
            }
            final String string = String.valueOf(s) + "history.log";
            try {
                final DataInputStream dataInputStream = new DataInputStream(new FileInputStream(string));
                while (line != null) {
                    line = dataInputStream.readLine();
                    if (line != null) {
                        this.historyModel.addElement(line);
                        JBLogger.log(line);
                    }
                }
            }
            catch (Exception ex) {
                JBLogger.log("Ex. i JBSearchPanel.init " + ex.toString());
                if (this.historyModel.size() == 0) {
                    this.historyModel.addElement("http://www.objectbox.com/javabee/javalinks.htm");
                }
            }
        }
        catch (Exception ex2) {
            JBLogger.log("Ex in JBSearch init: " + ex2);
        }
    }
    
    private void initConnections() {
        this.getFlatButtonStart().addActionListener(this);
        this.getTextFieldURL().addActionListener(this);
        this.getFlatButtonClear().addActionListener(this);
        this.getFlatButtonStop().addActionListener(this);
        this.getFlatButtonSaveApp().addActionListener(this);
        this.getFlatButtonTest().addActionListener(this);
        this.getFlatButtonOpenBrowser().addActionListener(this);
        this.getFlatButtonSaveAll().addActionListener(this);
        this.getTextFieldURL().addKeyListener(this);
        this.getFlatButtonManage().addActionListener(this);
        this.getFlatButtonSwitchToSearch().addActionListener(this);
        this.getFlatButtonNewfolder().addActionListener(this);
        this.getFlatButtonProperties().addActionListener(this);
        this.getFlatButtonRun().addActionListener(this);
        this.getFlatButtonCut().addActionListener(this);
        this.getFlatButtonCopy().addActionListener(this);
        this.getFlatButtonPaste().addActionListener(this);
        this.getFlatButtonDelete().addActionListener(this);
        this.getFlatButtonUndo().addActionListener(this);
        this.getFlatButtonBrowser().addActionListener(this);
        this.connPtoP1SetTarget();
        this.connPtoP2SetTarget();
        this.connPtoP3SetTarget();
    }
    
    private void initialize() {
        this.init();
        this.setName("SearchPanel");
        this.setLayout(this.getSearchPanelBorderLayout());
        this.setBackground(new Color(162, 162, 180));
        this.setSize(648, 482);
        this.add(this.getPanelSouth(), "South");
        this.add(this.getPanelAddress(), "North");
        this.add(this.getPanelCenter(), "Center");
        this.initConnections();
        this.setBackground(JBee.appcolor);
        this.getNewAppTableView().autoFitAll();
        this.startSniffer();
        (this.atp = new AppletTagParser()).setNoGoList();
        this.atp = null;
        AppRegistry.getInstance().put("SearchManagePanel", this);
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getSource() == this.getTextFieldURL()) {
            this.connEtoC14(keyEvent);
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void list1_FocusLost(final FocusEvent focusEvent) {
    }
    
    public void list1_ItemStateChanged(final ItemEvent itemEvent) {
    }
    
    public static void main(final String[] array) {
        try {
            Frame frame;
            try {
                frame = (Frame)Class.forName("com.ibm.uvm.abt.edit.TestFrame").newInstance();
            }
            catch (Throwable t) {
                frame = new Frame();
            }
            final JBSearchPanel jbSearchPanel = new JBSearchPanel();
            frame.add("Center", jbSearchPanel);
            frame.setSize(jbSearchPanel.getSize());
            frame.setVisible(true);
        }
        catch (Throwable t2) {
            JBLogger.log("Exception occurred in main() of java.awt.Panel");
        }
    }
    
    public void removeSavePropertiesListener(final SavePropertiesListener savePropertiesListener) {
        this.aSavePropertiesListener = SavePropertiesEventMulticaster.remove(this.aSavePropertiesListener, savePropertiesListener);
    }
    
    public void saveHistory() {
        final int size = this.historyModel.size();
        try {
            String s = JBee.getPreference("javabee_home");
            if (!s.endsWith(System.getProperty("file.separator"))) {
                s = String.valueOf(s) + System.getProperty("file.separator");
            }
            final String string = String.valueOf(s) + "history.log";
            try {
                final DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(string));
                for (int i = 0; i < size; ++i) {
                    final String trim = this.historyModel.elementAt(i).trim();
                    if (trim.length() > 0) {
                        dataOutputStream.writeBytes(String.valueOf(trim) + "\n");
                        JBLogger.log(trim);
                    }
                }
                dataOutputStream.close();
            }
            catch (Exception ex) {
                JBLogger.log("Ex in saving history: " + ex);
            }
        }
        catch (Exception ex2) {
            JBLogger.log("Ex in saving history: " + ex2);
        }
    }
    
    public void setFolder(final String text) {
        this.getLabelFolder().setText(text);
    }
    
    private void startSniffer() {
        ((Thread)new JBSearchPanel$1.Sniffer(this)).start();
    }
    
    public void textFieldURL_KeyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 40 | keyEvent.getKeyCode() == 38) {
            if (keyEvent.getKeyCode() == 40) {
                ++this.currentHistoryPosition;
            }
            else {
                --this.currentHistoryPosition;
            }
            if (this.currentHistoryPosition > this.historyModel.size() | this.currentHistoryPosition < 0) {
                this.currentHistoryPosition = 0;
            }
            this.getTextFieldURL().setText(this.historyModel.elementAt(this.currentHistoryPosition));
            return;
        }
        if (!(keyEvent.isActionKey() | keyEvent.getKeyCode() == 127 | keyEvent.getKeyCode() == 8 | keyEvent.getKeyCode() == 10 | keyEvent.getKeyCode() == 16 | keyEvent.getKeyCode() == 17 | keyEvent.getKeyCode() == 18)) {
            final String text = this.getTextFieldURL().getText();
            for (int size = this.historyModel.size(), i = 0; i < size; ++i) {
                if (this.historyModel.elementAt(i).toString().startsWith(text)) {
                    final int caretPosition = this.getTextFieldURL().getCaretPosition();
                    this.getTextFieldURL().setText(this.historyModel.elementAt(i).toString());
                    this.getTextFieldURL().setSelectionStart(caretPosition);
                    this.getTextFieldURL().setSelectionEnd(this.getTextFieldURL().getText().length());
                    keyEvent.consume();
                    break;
                }
            }
        }
    }
    
    public void thumbNailComponent1_MousePressed(final MouseEvent mouseEvent) {
        JBee.displayURL("http://www.objectbox.com");
    }
    
    public void updateAppletList(final Vector vector) {
        if (vector != null) {
            this.getNewAppTableModel().addData(vector);
            this.getNewAppTableView().setModel(this.getNewAppTableModel());
            this.getNewAppTableView().autoFitAll();
        }
    }
    
    public void updateStatus(final int n, final int n2, final int n3, final String s) {
        this.getLabelStatus().setText("Status:  Checked: " + new Integer(n).toString() + " pages. Found " + n3 + " applets and " + new Integer(n2).toString() + " links.");
        int progress = 0;
        if (n2 > 0) {
            progress = (int)(n / n2 * 100.0);
        }
        if (progress >= 0 && progress <= 100) {
            this.getStatusbar().setProgress(progress);
        }
        if (progress == 100) {
            this.getJBImageSpinner1().toggle(false);
        }
        else {
            this.getJBImageSpinner1().toggle(true);
        }
        this.getStatusbar().repaint();
    }
    
    static TextField access$getTextFieldURL(final JBSearchPanel jbSearchPanel) {
        return jbSearchPanel.getTextFieldURL();
    }
}
