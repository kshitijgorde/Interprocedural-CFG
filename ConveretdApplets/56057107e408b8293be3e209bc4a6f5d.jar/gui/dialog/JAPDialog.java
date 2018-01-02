// 
// Decompiled by Procyon v0.5.30
// 

package gui.dialog;

import java.awt.event.FocusEvent;
import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import platform.AbstractOS;
import java.net.MalformedURLException;
import anon.crypto.AbstractX509AlternativeName;
import java.net.URL;
import javax.swing.JButton;
import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.FocusListener;
import java.awt.Event;
import java.awt.MenuComponent;
import java.awt.Font;
import javax.accessibility.AccessibleContext;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.event.ComponentListener;
import javax.swing.JMenuBar;
import javax.swing.JRootPane;
import javax.swing.JLayeredPane;
import javax.swing.JTextPane;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.Cursor;
import java.awt.Color;
import javax.swing.BorderFactory;
import java.awt.Insets;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JCheckBox;
import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;
import gui.JAPHelpContext;
import gui.JAPHtmlMultiLineLabel;
import logging.LogHolder;
import logging.LogType;
import javax.swing.Icon;
import anon.util.JAPMessages;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import java.util.EventListener;
import javax.swing.JOptionPane;
import java.util.Enumeration;
import java.awt.Window;
import java.awt.Component;
import javax.swing.JDialog;
import gui.GUIUtils;
import java.util.Vector;
import java.util.Hashtable;
import java.awt.image.ImageObserver;
import java.awt.MenuContainer;
import javax.swing.RootPaneContainer;
import javax.swing.WindowConstants;
import javax.accessibility.Accessible;

public class JAPDialog implements Accessible, WindowConstants, RootPaneContainer, MenuContainer, ImageObserver, IDialogOptions
{
    public static final String XML_ATTR_OPTIMIZED_FORMAT = "dialogOptFormat";
    public static final int FORMAT_GOLDEN_RATIO_PHI = 0;
    public static final int FORMAT_DEFAULT_SCREEN = 1;
    public static final int FORMAT_WIDE_SCREEN = 2;
    private static final double[] FORMATS;
    public static final String MSG_ERROR_UNKNOWN;
    public static final String MSG_TITLE_INFO;
    public static final String MSG_TITLE_CONFIRMATION;
    public static final String MSG_TITLE_WARNING;
    public static final String MSG_TITLE_ERROR;
    public static final String MSG_ERROR_UNDISPLAYABLE;
    public static final String MSG_BTN_PROCEED;
    public static final String MSG_BTN_RETRY;
    private static final int NUMBER_OF_HEURISTIC_ITERATIONS = 6;
    private static int m_optimizedFormat;
    private static Hashtable ms_registeredDialogs;
    private static boolean ms_bConsoleOnly;
    private boolean m_bLocationSetManually;
    private boolean m_bModal;
    private boolean m_bBlockParentWindow;
    private int m_defaultCloseOperation;
    private Vector m_windowListeners;
    private Vector m_componentListeners;
    private DialogWindowAdapter m_dialogWindowAdapter;
    private boolean m_bForceApplicationModality;
    private boolean m_bDisposed;
    private boolean m_bCatchCancel;
    private GUIUtils.WindowDocker m_docker;
    private final Object SYNC_DOCK;
    private JDialog m_internalDialog;
    private Component m_parentComponent;
    private Window m_parentWindow;
    private boolean m_bOnTop;
    static /* synthetic */ Class class$gui$dialog$JAPDialog;
    static /* synthetic */ Class class$java$awt$event$WindowListener;
    static /* synthetic */ Class class$java$lang$Class;
    static /* synthetic */ Class class$javax$swing$JDialog;
    static /* synthetic */ Class class$java$awt$Container;
    
    public static void setConsoleOnly(final boolean ms_bConsoleOnly) {
        JAPDialog.ms_bConsoleOnly = ms_bConsoleOnly;
        if (JAPDialog.ms_bConsoleOnly) {
            final Vector<JAPDialog> vector;
            synchronized (JAPDialog.ms_registeredDialogs) {
                vector = new Vector<JAPDialog>(JAPDialog.ms_registeredDialogs.size());
                final Enumeration<JAPDialog> elements = JAPDialog.ms_registeredDialogs.elements();
                while (elements.hasMoreElements()) {
                    vector.addElement(elements.nextElement());
                }
            }
            final Enumeration<JAPDialog> elements2 = vector.elements();
            while (elements2.hasMoreElements()) {
                elements2.nextElement().dispose();
            }
            vector.removeAllElements();
        }
    }
    
    public static boolean isConsoleOnly() {
        return JAPDialog.ms_bConsoleOnly;
    }
    
    public JAPDialog(final Component component, final String s, final boolean b) {
        this(component, s, b, false);
    }
    
    public JAPDialog(final Component component, final String s) {
        this(component, s, true);
    }
    
    public JAPDialog(final JAPDialog japDialog, final String s, final boolean b) {
        this(getInternalDialog(japDialog), s, b);
    }
    
    public JAPDialog(final JAPDialog japDialog, final String s) {
        this(getInternalDialog(japDialog), s);
    }
    
    private JAPDialog(final Component parentComponent, final String s, final boolean modal, final boolean bForceApplicationModality) {
        this.m_bLocationSetManually = false;
        this.m_bBlockParentWindow = false;
        this.m_windowListeners = new Vector();
        this.m_componentListeners = new Vector();
        this.m_bDisposed = false;
        this.m_bCatchCancel = false;
        this.SYNC_DOCK = new Object();
        this.m_bOnTop = false;
        this.m_parentComponent = parentComponent;
        this.m_bForceApplicationModality = bForceApplicationModality;
        this.m_internalDialog = new JOptionPane().createDialog(parentComponent, s);
        if (this.m_parentComponent == null) {
            this.m_parentComponent = this.m_internalDialog.getParent();
        }
        this.m_internalDialog.getContentPane().removeAll();
        this.m_internalDialog.setResizable(true);
        this.m_internalDialog.setModal(false);
        this.m_internalDialog.setDefaultCloseOperation(0);
        this.setDefaultCloseOperation(2);
        EventListener[] array;
        try {
            array = (EventListener[])((JAPDialog.class$javax$swing$JDialog == null) ? (JAPDialog.class$javax$swing$JDialog = class$("javax.swing.JDialog")) : JAPDialog.class$javax$swing$JDialog).getMethod("getListeners", (JAPDialog.class$java$lang$Class == null) ? (JAPDialog.class$java$lang$Class = class$("java.lang.Class")) : JAPDialog.class$java$lang$Class).invoke(this.m_internalDialog, (JAPDialog.class$java$awt$event$WindowListener == null) ? (JAPDialog.class$java$awt$event$WindowListener = class$("java.awt.event.WindowListener")) : JAPDialog.class$java$awt$event$WindowListener);
        }
        catch (Exception ex) {
            array = null;
        }
        for (int n = 0; array != null && n < array.length; ++n) {
            this.m_internalDialog.removeWindowListener((WindowListener)array[n]);
        }
        this.m_dialogWindowAdapter = new DialogWindowAdapter();
        this.m_internalDialog.addWindowListener(this.m_dialogWindowAdapter);
        this.m_parentWindow = GUIUtils.getParentWindow(this.getParentComponent());
        this.setModal(modal);
        JAPDialog.ms_registeredDialogs.put(this, this);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosed(final WindowEvent windowEvent) {
                JAPDialog.ms_registeredDialogs.remove(JAPDialog.this);
                JAPDialog.this.removeWindowListener(this);
            }
        });
    }
    
    public static void setOptimizedFormat(int optimizedFormat) {
        if (optimizedFormat < 0 || optimizedFormat >= JAPDialog.FORMATS.length) {
            optimizedFormat = JAPDialog.FORMATS.length - 1;
        }
        JAPDialog.m_optimizedFormat = optimizedFormat;
    }
    
    public static int getOptimizedFormat() {
        return JAPDialog.m_optimizedFormat;
    }
    
    public static double getOptimizedFormatInternal(int n) {
        if (n < 0 || n >= JAPDialog.FORMATS.length) {
            n = JAPDialog.FORMATS.length - 1;
        }
        return JAPDialog.FORMATS[n];
    }
    
    public static double getOptimizedFormatDelta(final Window window) {
        return window.getSize().height * getOptimizedFormatInternal(JAPDialog.m_optimizedFormat) - window.getSize().width;
    }
    
    public static double getOptimizedFormatDelta(final JAPDialog japDialog) {
        return japDialog.getSize().height * getOptimizedFormatInternal(JAPDialog.m_optimizedFormat) - japDialog.getSize().width;
    }
    
    public static void showMessageDialog(final JAPDialog japDialog, final String s) {
        showMessageDialog(getInternalDialog(japDialog), s);
    }
    
    public static void showMessageDialog(final JAPDialog japDialog, final String s, final ILinkedInformation linkedInformation) {
        showMessageDialog(getInternalDialog(japDialog), s, linkedInformation);
    }
    
    public static void showMessageDialog(final Component component, final String s) {
        showMessageDialog(component, s, JAPMessages.getString(JAPDialog.MSG_TITLE_INFO), (Icon)null);
    }
    
    public static void showMessageDialog(final Component component, final String s, final ILinkedInformation linkedInformation) {
        showMessageDialog(component, s, JAPMessages.getString(JAPDialog.MSG_TITLE_INFO), null, linkedInformation);
    }
    
    public static void showMessageDialog(final JAPDialog japDialog, final String s, final String s2) {
        showMessageDialog(getInternalDialog(japDialog), s, s2);
    }
    
    public static void showMessageDialog(final JAPDialog japDialog, final String s, final String s2, final ILinkedInformation linkedInformation) {
        showMessageDialog(getInternalDialog(japDialog), s, s2, linkedInformation);
    }
    
    public static void showMessageDialog(final Component component, final String s, final String s2) {
        showMessageDialog(component, s, s2, (Icon)null);
    }
    
    public static void showMessageDialog(final Component component, final String s, final String s2, final ILinkedInformation linkedInformation) {
        showMessageDialog(component, s, s2, null, linkedInformation);
    }
    
    public static void showMessageDialog(final JAPDialog japDialog, final String s, final Icon icon) {
        showMessageDialog(getInternalDialog(japDialog), s, icon);
    }
    
    public static void showMessageDialog(final JAPDialog japDialog, final String s, final Icon icon, final ILinkedInformation linkedInformation) {
        showMessageDialog(getInternalDialog(japDialog), s, icon, linkedInformation);
    }
    
    public static void showMessageDialog(final Component component, final String s, final Icon icon) {
        showMessageDialog(component, s, JAPMessages.getString(JAPDialog.MSG_TITLE_INFO), icon);
    }
    
    public static void showMessageDialog(final Component component, final String s, final Icon icon, final ILinkedInformation linkedInformation) {
        showMessageDialog(component, s, JAPMessages.getString(JAPDialog.MSG_TITLE_INFO), icon, linkedInformation);
    }
    
    public static void showMessageDialog(final JAPDialog japDialog, final String s, final String s2, final Icon icon) {
        showMessageDialog(getInternalDialog(japDialog), s, s2, icon);
    }
    
    public static void showMessageDialog(final JAPDialog japDialog, final String s, final String s2, final Icon icon, final ILinkedInformation linkedInformation) {
        showMessageDialog(getInternalDialog(japDialog), s, s2, icon, linkedInformation);
    }
    
    public static void showMessageDialog(final Component component, final String s, final String s2, final Icon icon) {
        showMessageDialog(component, s, s2, icon, null);
    }
    
    public static void showMessageDialog(final Component component, final String s, String string, final Icon icon, final ILinkedInformation linkedInformation) {
        if (string == null) {
            string = JAPMessages.getString(JAPDialog.MSG_TITLE_CONFIRMATION);
        }
        showConfirmDialog(component, s, string, -1, 1, icon, linkedInformation);
    }
    
    public static void showWarningDialog(final JAPDialog japDialog, final String s) {
        showWarningDialog(japDialog, s, null, null);
    }
    
    public static void showWarningDialog(final Component component, final String s) {
        showWarningDialog(component, s, null, null);
    }
    
    public static void showWarningDialog(final JAPDialog japDialog, final String s, final String s2) {
        showWarningDialog(japDialog, s, s2, null);
    }
    
    public static void showWarningDialog(final Component component, final String s, final String s2) {
        showWarningDialog(component, s, s2, null);
    }
    
    public static void showWarningDialog(final JAPDialog japDialog, final String s, final String s2, final ILinkedInformation linkedInformation) {
        showWarningDialog(getInternalDialog(japDialog), s, s2, linkedInformation);
    }
    
    public static void showWarningDialog(final JAPDialog japDialog, final String s, final ILinkedInformation linkedInformation) {
        showWarningDialog(getInternalDialog(japDialog), s, null, linkedInformation);
    }
    
    public static void showWarningDialog(final Component component, final String s, final ILinkedInformation linkedInformation) {
        showWarningDialog(component, s, null, linkedInformation);
    }
    
    public static void showWarningDialog(final Component component, final String s, String string, final ILinkedInformation linkedInformation) {
        if (string == null) {
            string = JAPMessages.getString(JAPDialog.MSG_TITLE_WARNING);
        }
        showConfirmDialog(component, s, string, -1, 2, null, linkedInformation);
    }
    
    public static int showConfirmDialog(final JAPDialog japDialog, final String s, final String s2, final int n, final int n2, final Icon icon) {
        return showConfirmDialog(japDialog, s, s2, n, n2, icon, null);
    }
    
    public static int showConfirmDialog(final Component component, final String s, final String s2, final int n, final int n2, final Icon icon) {
        return showConfirmDialog(component, s, s2, n, n2, icon, null);
    }
    
    public static int showConfirmDialog(final JAPDialog japDialog, final String s, final String s2, final int n, final int n2, final Icon icon, final ILinkedInformation linkedInformation) {
        return showConfirmDialog(getInternalDialog(japDialog), s, s2, n, n2, icon, linkedInformation);
    }
    
    public static int showConfirmDialog(final Component component, final String s, final String s2, final int n, final int n2, final Icon icon, final ILinkedInformation linkedInformation) {
        return showConfirmDialog(component, s, s2, new Options(n), n2, icon, linkedInformation);
    }
    
    public static int showConfirmDialog(final Component component, final String s, final Options options, final int n) {
        return showConfirmDialog(component, s, null, options, n, null, null);
    }
    
    public static int showConfirmDialog(final Component component, final String s, final Options options, final int n, final ILinkedInformation linkedInformation) {
        return showConfirmDialog(component, s, null, options, n, null, linkedInformation);
    }
    
    public static int showConfirmDialog(final Component component, final String s, final String s2, final Options options, final int n, final ILinkedInformation linkedInformation) {
        return showConfirmDialog(component, s, s2, options, n, null, linkedInformation);
    }
    
    public static int showConfirmDialog(final Component component, String s, String string, final Options options, final int n, final Icon icon, ILinkedInformation linkedInformation) {
        JAPHelpContext.IHelpContext helpContext = null;
        boolean applicationModalityForced = false;
        boolean onTop = false;
        boolean modal = true;
        boolean closeWindowActive = true;
        String yesOKText = null;
        String cancelText = null;
        String noText = null;
        final Vector vector = new Vector<String>();
        String tooltipText = null;
        if (JAPDialog.ms_bConsoleOnly) {
            LogHolder.log(1, LogType.GUI, s);
            return Integer.MIN_VALUE;
        }
        if (options.getYesOKText() != null && options.getYesOKText().trim().length() > 0) {
            yesOKText = options.getYesOKText();
        }
        if (options.getNoText() != null && options.getNoText().trim().length() > 0) {
            noText = options.getNoText();
        }
        if (options.getCancelText() != null && options.getCancelText().trim().length() > 0) {
            cancelText = options.getCancelText();
        }
        if (s == null) {
            s = "";
        }
        String s2;
        s = (s2 = JAPHtmlMultiLineLabel.removeHTMLHEADAndBODYTags(s));
        if (string == null) {
            string = JAPMessages.getString(JAPDialog.MSG_TITLE_CONFIRMATION);
        }
        if (linkedInformation != null) {
            applicationModalityForced = linkedInformation.isApplicationModalityForced();
            onTop = linkedInformation.isOnTop();
            modal = linkedInformation.isModal();
            closeWindowActive = linkedInformation.isCloseWindowActive();
            tooltipText = linkedInformation.getTooltipText();
            if (linkedInformation instanceof JAPHelpContext.IHelpContext) {
                helpContext = (JAPHelpContext.IHelpContext)linkedInformation;
                if (linkedInformation.getType() == 0) {
                    linkedInformation = null;
                }
            }
        }
        String removeTagsAndNewLines;
        if (linkedInformation != null && linkedInformation.getMessage() != null && linkedInformation.getMessage().trim().length() > 0) {
            removeTagsAndNewLines = JAPHtmlMultiLineLabel.removeTagsAndNewLines(linkedInformation.getMessage());
            if (linkedInformation.getType() != 3 && linkedInformation.getType() != 4) {
                s2 = s2 + "<br><a href=\"\">" + removeTagsAndNewLines + "</a>";
            }
        }
        else {
            removeTagsAndNewLines = null;
        }
        final JAPDialog japDialog = new JAPDialog(component, string, true, applicationModalityForced);
        final DialogContentPane dialogContentPane = new DialogContentPane(japDialog, new DialogContentPane.Layout(null, n, icon), new DialogContentPaneOptions(options.getOptionType(), helpContext));
        if (dialogContentPane.getButtonHelp() != null) {
            vector.addElement(dialogContentPane.getButtonHelp().getText());
        }
        if (dialogContentPane.getButtonYesOK() != null) {
            if (yesOKText != null) {
                dialogContentPane.getButtonYesOK().setText(yesOKText);
            }
            vector.addElement(dialogContentPane.getButtonYesOK().getText());
        }
        if (dialogContentPane.getButtonCancel() != null) {
            if (cancelText != null) {
                dialogContentPane.getButtonCancel().setText(cancelText);
            }
            vector.addElement(dialogContentPane.getButtonCancel().getText());
        }
        if (dialogContentPane.getButtonNo() != null) {
            if (noText != null) {
                dialogContentPane.getButtonNo().setText(noText);
            }
            vector.addElement(dialogContentPane.getButtonNo().getText());
        }
        if (!options.isDrawFocusEnabled()) {
            dialogContentPane.getButtonNo().setFocusPainted(false);
            dialogContentPane.getButtonYesOK().setFocusPainted(false);
            dialogContentPane.getButtonCancel().setFocusPainted(false);
        }
        dialogContentPane.setDefaultButtonOperation(2);
        try {
            if (!SwingUtilities.isEventDispatchThread()) {
                SwingUtilities.invokeAndWait(new Runnable() {
                    public void run() {
                        final JAPHtmlMultiLineLabel japHtmlMultiLineLabel = new JAPHtmlMultiLineLabel("Text");
                        japHtmlMultiLineLabel.setText(japHtmlMultiLineLabel.getText());
                        japHtmlMultiLineLabel.revalidate();
                    }
                });
            }
        }
        catch (InterruptedException ex5) {}
        catch (InvocationTargetException ex6) {}
        JAPHtmlMultiLineLabel japHtmlMultiLineLabel;
        try {
            japHtmlMultiLineLabel = new JAPHtmlMultiLineLabel("Text");
        }
        catch (NullPointerException ex) {
            if (Thread.currentThread().isInterrupted()) {
                return Integer.MIN_VALUE;
            }
            throw ex;
        }
        if (japHtmlMultiLineLabel.getPreferredSize().width == 0 || japHtmlMultiLineLabel.getPreferredSize().height == 0) {
            LogHolder.log(0, LogType.GUI, "Dialog label size is invalid! This dialog might not show any label!");
        }
        JAPHtmlMultiLineLabel japHtmlMultiLineLabel2;
        try {
            japHtmlMultiLineLabel2 = new JAPHtmlMultiLineLabel(s2);
            japHtmlMultiLineLabel2.setFontStyle(0);
        }
        catch (NullPointerException ex2) {
            if (Thread.currentThread().isInterrupted()) {
                return Integer.MIN_VALUE;
            }
            throw ex2;
        }
        final PreferredWidthBoxPanel contentPane = new PreferredWidthBoxPanel();
        if (removeTagsAndNewLines != null && (linkedInformation.getType() == 3 || linkedInformation.getType() == 4)) {
            final JCheckBox checkBox = new JCheckBox("Text");
            checkBox.setFont(japHtmlMultiLineLabel2.getFont());
            contentPane.add(checkBox);
        }
        contentPane.add(japHtmlMultiLineLabel2);
        dialogContentPane.setContentPane(contentPane);
        dialogContentPane.updateDialog();
        final JComponent component2 = (JComponent)japDialog.getContentPane();
        Dimension preferredSize = null;
        Icon messageIcon = icon;
        if (messageIcon == null) {
            messageIcon = DialogContentPane.getMessageIcon(n);
        }
        final String[] array = new String[vector.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = vector.elementAt(i).toString();
        }
        final JDialog dialog = new JOptionPane("", n, options.getOptionType(), messageIcon, array).createDialog(component, string);
        final Dimension dimension = new Dimension(dialog.getContentPane().getSize());
        dialog.dispose();
        dimension.setSize(dimension.width / 2, dimension.height);
        int width;
        try {
            final Window parentWindow = GUIUtils.getParentWindow(component);
            if (parentWindow == null) {
                return Integer.MIN_VALUE;
            }
            width = parentWindow.getSize().width;
        }
        catch (NullPointerException ex3) {
            if (Thread.currentThread().isInterrupted()) {
                return Integer.MIN_VALUE;
            }
            throw ex3;
        }
        if (width < dimension.width * 4) {
            width = dimension.width * 4;
        }
        Math.min(component2.getWidth(), width);
        double n2 = Double.MAX_VALUE;
        int min;
        int max = min = Math.min(500, component2.getWidth());
        int n3 = 0;
        final int width2 = japHtmlMultiLineLabel2.getMinimumSize().width;
        boolean b = true;
        for (int j = 0; j < 6; ++j) {
            final PreferredWidthBoxPanel contentPane2 = new PreferredWidthBoxPanel();
            contentPane2.add(component2);
            contentPane2.setPreferredWidth(max);
            japDialog.setContentPane(contentPane2);
            japDialog.pack();
            japHtmlMultiLineLabel2.setPreferredWidth(japHtmlMultiLineLabel2.getWidth());
            japDialog.pack();
            if (contentPane2.getHeight() < dimension.height) {
                LogHolder.log(5, LogType.GUI, "Dialog height was too small.");
                contentPane2.setPreferredHeigth(dimension.height);
                japDialog.pack();
            }
            final int width3 = contentPane2.getWidth();
            final double optimizedFormatDelta = getOptimizedFormatDelta(japDialog);
            int n4;
            if (Math.abs(optimizedFormatDelta) < Math.abs(n2) && (j == 0 || japHtmlMultiLineLabel2.getSize().width >= width2)) {
                preferredSize = new Dimension(contentPane2.getSize());
                n2 = optimizedFormatDelta;
                min = width3;
                n4 = (int)(width3 + n2 / 2.0);
                if (japHtmlMultiLineLabel2.getSize().width < width2) {
                    ++n3;
                }
                else {
                    b = false;
                    n3 = 0;
                }
            }
            else {
                if (japHtmlMultiLineLabel2.getSize().width < width2) {
                    n4 = (int)(width3 + (width2 - japHtmlMultiLineLabel2.getSize().width + n3 + 1.0));
                }
                else {
                    n4 = min + (int)(n2 / (3.0 * (n3 + 1.0)));
                }
                ++n3;
            }
            max = Math.max(n4, dimension.width);
            if (max == min) {
                break;
            }
        }
        if (b) {
            LogHolder.log(3, LogType.GUI, "Auto-formatting of dialog failed!");
        }
        final PreferredWidthBoxPanel contentPane3 = new PreferredWidthBoxPanel();
        JAPHtmlMultiLineLabel japHtmlMultiLineLabel3;
        try {
            japHtmlMultiLineLabel3 = new JAPHtmlMultiLineLabel("<font color=#000000>" + s + "</font>");
            japHtmlMultiLineLabel3.setFontStyle(0);
        }
        catch (NullPointerException ex4) {
            if (Thread.currentThread().isInterrupted()) {
                return Integer.MIN_VALUE;
            }
            throw ex4;
        }
        contentPane3.add(japHtmlMultiLineLabel3);
        if (removeTagsAndNewLines != null) {
            Accessible accessible;
            if (linkedInformation.getType() == 2) {
                final JTextPane selectableAndResizeableLabel = GUIUtils.createSelectableAndResizeableLabel(contentPane3);
                selectableAndResizeableLabel.setText(removeTagsAndNewLines);
                selectableAndResizeableLabel.setFont(japHtmlMultiLineLabel3.getFont());
                selectableAndResizeableLabel.setMargin(new Insets(0, 0, 0, 0));
                selectableAndResizeableLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 1, 0));
                selectableAndResizeableLabel.setForeground(Color.blue);
                selectableAndResizeableLabel.setCursor(Cursor.getPredefinedCursor(12));
                accessible = selectableAndResizeableLabel;
                ((Component)accessible).addMouseListener(new LinkedInformationClickListener(linkedInformation));
            }
            else if (linkedInformation.getType() == 3 || linkedInformation.getType() == 4) {
                accessible = new JCheckBox(removeTagsAndNewLines, linkedInformation.getType() == 3);
                ((JComponent)accessible).setFont(japHtmlMultiLineLabel3.getFont());
                ((JCheckBox)accessible).addItemListener(new LinkedInformationClickListener(linkedInformation));
            }
            else {
                accessible = new JAPHtmlMultiLineLabel("<a href=\"\">" + removeTagsAndNewLines + "</a>");
                ((Component)accessible).addMouseListener(new LinkedInformationClickListener(linkedInformation));
                ((Component)accessible).setCursor(Cursor.getPredefinedCursor(12));
            }
            ((JComponent)accessible).setToolTipText(tooltipText);
            contentPane3.add((Component)accessible);
        }
        dialogContentPane.setContentPane(contentPane3);
        dialogContentPane.setDefaultButton(options.getDefaultButton());
        dialogContentPane.updateDialog();
        ((JComponent)japDialog.getContentPane()).setPreferredSize(preferredSize);
        japDialog.pack();
        if (n2 != getOptimizedFormatDelta(japDialog)) {
            LogHolder.log(3, LogType.GUI, "Calculated dialog size differs from real size!");
        }
        LogHolder.log(5, LogType.GUI, "Dialog golden ratio delta: " + getOptimizedFormatDelta(japDialog));
        japDialog.setResizable(false);
        if (closeWindowActive) {
            japDialog.setDefaultCloseOperation(2);
        }
        else {
            japDialog.setDefaultCloseOperation(0);
        }
        japDialog.addWindowListener(new SimpleDialogButtonFocusWindowAdapter(dialogContentPane));
        japDialog.m_bOnTop = onTop;
        if (!modal) {
            japDialog.setModal(false);
        }
        japDialog.setVisible(true);
        return dialogContentPane.getButtonValue();
    }
    
    public static boolean showYesNoDialog(final JAPDialog japDialog, final String s) {
        return showYesNoDialog(getInternalDialog(japDialog), s);
    }
    
    public static boolean showYesNoDialog(final JAPDialog japDialog, final String s, final ILinkedInformation linkedInformation) {
        return showYesNoDialog(getInternalDialog(japDialog), s, linkedInformation);
    }
    
    public static boolean showYesNoDialog(final Component component, final String s) {
        return showYesNoDialog(component, s, (String)null);
    }
    
    public static boolean showYesNoDialog(final Component component, final String s, final ILinkedInformation linkedInformation) {
        return showYesNoDialog(component, s, null, linkedInformation);
    }
    
    public static boolean showYesNoDialog(final JAPDialog japDialog, final String s, final String s2) {
        return showYesNoDialog(getInternalDialog(japDialog), s, s2);
    }
    
    public static boolean showYesNoDialog(final JAPDialog japDialog, final String s, final String s2, final ILinkedInformation linkedInformation) {
        return showYesNoDialog(getInternalDialog(japDialog), s, s2, linkedInformation);
    }
    
    public static boolean showYesNoDialog(final Component component, final String s, final String s2) {
        return showYesNoDialog(component, s, s2, null);
    }
    
    public static boolean showYesNoDialog(final Component component, final String s, String string, final ILinkedInformation linkedInformation) {
        if (string == null) {
            string = JAPMessages.getString(JAPDialog.MSG_TITLE_CONFIRMATION);
        }
        return 0 == showConfirmDialog(component, s, string, 0, 3, null, linkedInformation);
    }
    
    public static int showConfirmDialog(final JAPDialog japDialog, final String s, final int n, final int n2, final Icon icon) {
        return showConfirmDialog(getInternalDialog(japDialog), s, null, n, n2, icon, null);
    }
    
    public static int showConfirmDialog(final Component component, final String s, final int n, final int n2, final Icon icon) {
        return showConfirmDialog(component, s, null, n, n2, icon, null);
    }
    
    public static int showConfirmDialog(final JAPDialog japDialog, final String s, final int n, final int n2) {
        return showConfirmDialog(getInternalDialog(japDialog), s, null, n, n2, null, null);
    }
    
    public static int showConfirmDialog(final Component component, final String s, final int n, final int n2) {
        return showConfirmDialog(component, s, null, n, n2, null, null);
    }
    
    public static int showConfirmDialog(final JAPDialog japDialog, final String s, final int n, final int n2, final ILinkedInformation linkedInformation) {
        return showConfirmDialog(getInternalDialog(japDialog), s, null, n, n2, null, linkedInformation);
    }
    
    public static int showConfirmDialog(final Component component, final String s, final int n, final int n2, final ILinkedInformation linkedInformation) {
        return showConfirmDialog(component, s, null, n, n2, null, linkedInformation);
    }
    
    public static int showConfirmDialog(final JAPDialog japDialog, final String s, final String s2, final int n, final int n2) {
        return showConfirmDialog(getInternalDialog(japDialog), s, s2, n, n2, null, null);
    }
    
    public static int showConfirmDialog(final Component component, final String s, final String s2, final int n, final int n2) {
        return showConfirmDialog(component, s, s2, n, n2, null, null);
    }
    
    public static int showConfirmDialog(final JAPDialog japDialog, final String s, final String s2, final int n, final int n2, final ILinkedInformation linkedInformation) {
        return showConfirmDialog(getInternalDialog(japDialog), s, s2, n, n2, null, linkedInformation);
    }
    
    public static int showConfirmDialog(final Component component, final String s, final String s2, final int n, final int n2, final ILinkedInformation linkedInformation) {
        return showConfirmDialog(component, s, s2, n, n2, null, linkedInformation);
    }
    
    public static void showErrorDialog(final JAPDialog japDialog, final String s, final int n) {
        showErrorDialog(japDialog, s, n, (Throwable)null);
    }
    
    public static void showErrorDialog(final Component component, final String s, final String s2, final int n, final ILinkedInformation linkedInformation) {
        showErrorDialog(component, s, s2, n, null, linkedInformation);
    }
    
    public static void showErrorDialog(final Component component, final String s, final int n, final ILinkedInformation linkedInformation) {
        showErrorDialog(component, s, null, n, null, linkedInformation);
    }
    
    public static void showErrorDialog(final Component component, final String s, final int n) {
        showErrorDialog(component, s, n, (Throwable)null);
    }
    
    public static void showErrorDialog(final Component component, final String s, final int n, final String s2) {
        showErrorDialog(component, s, s2, n, null, null);
    }
    
    public static void showErrorDialog(final JAPDialog japDialog, final String s, final int n, final String s2) {
        showErrorDialog(getInternalDialog(japDialog), s, s2, n, null, null);
    }
    
    public static void showErrorDialog(final JAPDialog japDialog, final String s, final int n, final ILinkedInformation linkedInformation) {
        showErrorDialog(getInternalDialog(japDialog), s, null, n, null, null);
    }
    
    public static void showErrorDialog(final Component component, final String s, final String s2, final int n) {
        showErrorDialog(component, s, s2, n, null, null);
    }
    
    public static void showErrorDialog(final JAPDialog japDialog, final int n, final Throwable t) {
        showErrorDialog(getInternalDialog(japDialog), null, null, n, t);
    }
    
    public static void showErrorDialog(final Component component, final int n, final Throwable t) {
        showErrorDialog(component, null, null, n, t);
    }
    
    public static void showErrorDialog(final JAPDialog japDialog, final String s, final int n, final Throwable t) {
        showErrorDialog(getInternalDialog(japDialog), s, n, t);
    }
    
    public static void showErrorDialog(final Component component, final String s, final int n, final Throwable t) {
        showErrorDialog(component, s, null, n, t);
    }
    
    public static void showErrorDialog(final JAPDialog japDialog, final String s, final String s2, final int n, final Throwable t) {
        showErrorDialog(getInternalDialog(japDialog), s, s2, n, t);
    }
    
    public static void showErrorDialog(final Component component, final String s, final String s2, final int n, final Throwable t) {
        showErrorDialog(component, s, s2, n, t, null);
    }
    
    public static void showErrorDialog(final Component component, String s, String string, int gui, final Throwable t, final ILinkedInformation linkedInformation) {
        boolean b = false;
        s = retrieveErrorMessage(s, t);
        if (s == null) {
            s = JAPMessages.getString(JAPDialog.MSG_ERROR_UNKNOWN);
            b = true;
        }
        if (!LogType.isValidLogType(gui)) {
            gui = LogType.GUI;
        }
        LogHolder.log(3, gui, s, true);
        if (t != null) {
            if (b) {
                LogHolder.log(3, gui, t);
            }
            else {
                LogHolder.log(6, gui, t);
            }
        }
        try {
            if (string == null) {
                string = JAPMessages.getString(JAPDialog.MSG_TITLE_ERROR);
            }
            showConfirmDialog(component, s, string, -1, 0, null, linkedInformation);
        }
        catch (Exception ex) {
            LogHolder.log(2, LogType.GUI, JAPMessages.getString(JAPDialog.MSG_ERROR_UNDISPLAYABLE));
            LogHolder.log(2, LogType.GUI, ex);
        }
    }
    
    public static String retrieveErrorMessage(String message, final Throwable t) {
        if (message == null || message.trim().length() == 0) {
            if (t == null || t.getMessage() == null) {
                message = null;
            }
            else {
                message = t.getMessage();
                if (message == null || message.trim().length() == 0) {
                    message = null;
                }
            }
        }
        return message;
    }
    
    public Component getGlassPane() {
        return this.m_internalDialog.getGlassPane();
    }
    
    public JLayeredPane getLayeredPane() {
        return this.m_internalDialog.getLayeredPane();
    }
    
    public JRootPane getRootPane() {
        return this.m_internalDialog.getRootPane();
    }
    
    public final Container getContentPane() {
        return this.m_internalDialog.getContentPane();
    }
    
    public void setContentPane(final Container contentPane) {
        this.m_internalDialog.setContentPane(contentPane);
    }
    
    public void setGlassPane(final Component glassPane) {
        this.m_internalDialog.setGlassPane(glassPane);
    }
    
    public void setLayeredPane(final JLayeredPane layeredPane) {
        this.m_internalDialog.setLayeredPane(layeredPane);
    }
    
    public final Component getParentComponent() {
        return this.m_parentComponent;
    }
    
    public final Window getOwner() {
        return this.m_parentWindow;
    }
    
    public void doClosingOnContentPaneCancel(final boolean bCatchCancel) {
        this.m_bCatchCancel = bCatchCancel;
    }
    
    public boolean isClosingOnContentPaneCancel() {
        return this.m_bCatchCancel;
    }
    
    public void setName(final String name) {
        this.m_internalDialog.setName(name);
    }
    
    public String getName() {
        return this.m_internalDialog.getName();
    }
    
    public void setEnabled(final boolean enabled) {
        this.m_internalDialog.setEnabled(enabled);
    }
    
    public void setAlwaysOnTop(final boolean bOnTop) {
        if (!this.isVisible()) {
            this.m_bOnTop = bOnTop;
        }
    }
    
    public boolean isVisible() {
        return this.m_internalDialog.isVisible();
    }
    
    public void setVisible(final boolean b) {
        this.setVisible(b, true);
    }
    
    public final void setVisible(final boolean visibleInternal, final boolean b) {
        if (this.isDisposed()) {
            return;
        }
        if (visibleInternal && !this.m_bLocationSetManually && !this.isVisible()) {
            if (b) {
                GUIUtils.setLocationRelativeTo(this.getParentComponent(), this.m_internalDialog);
            }
            else {
                GUIUtils.positionRightUnderWindow(this.m_internalDialog, this.getOwner());
            }
        }
        final String name = this.m_internalDialog.getName();
        String name2 = null;
        final boolean alwaysOnTop = GUIUtils.isAlwaysOnTop(this.getOwner());
        if (this.m_bOnTop || alwaysOnTop) {
            GUIUtils.setAlwaysOnTop(this.getOwner(), false);
            this.getOwner().toBack();
            name2 = this.getOwner().getName();
            this.m_internalDialog.setName(name2);
            GUIUtils.setAlwaysOnTop(this.m_internalDialog, true);
        }
        this.setVisibleInternal(visibleInternal);
        if (this.m_bOnTop || alwaysOnTop) {
            final String name3 = this.m_internalDialog.getName();
            this.m_internalDialog.setName("JAP " + Double.toString(Math.random()));
            GUIUtils.setAlwaysOnTop(this.getOwner(), alwaysOnTop);
            GUIUtils.setAlwaysOnTop(this.m_internalDialog, false);
            if (name != null && name2 != null && name3 != null && name3.equals(name2)) {
                this.m_internalDialog.setName(name);
            }
        }
    }
    
    public void setTitle(final String title) {
        this.m_internalDialog.setTitle(title);
    }
    
    public String getTitle() {
        return this.m_internalDialog.getTitle();
    }
    
    public void setJMenuBar(final JMenuBar jMenuBar) {
        this.m_internalDialog.setJMenuBar(jMenuBar);
    }
    
    public JMenuBar getJMenuBar() {
        return this.m_internalDialog.getJMenuBar();
    }
    
    public void toFront() {
        this.m_internalDialog.toFront();
    }
    
    public void toBack() {
        this.m_internalDialog.toBack();
    }
    
    public final void setModal(final boolean b) {
        if (this.m_bForceApplicationModality) {
            this.m_bModal = false;
            this.m_internalDialog.setModal(b);
        }
        else if (!this.isVisible()) {
            this.m_bModal = b;
        }
    }
    
    public boolean isModal() {
        if (this.m_bForceApplicationModality) {
            return this.m_internalDialog.isModal();
        }
        return this.m_bModal;
    }
    
    public boolean isEnabled() {
        return this.m_internalDialog.isEnabled();
    }
    
    public boolean isResizable() {
        return this.m_internalDialog.isResizable();
    }
    
    public boolean isDisposed() {
        return this.m_bDisposed;
    }
    
    public final void dispose() {
        this.m_bDisposed = true;
        if (this.m_bBlockParentWindow) {
            this.m_bBlockParentWindow = false;
            this.m_parentWindow.setEnabled(true);
            if (this.m_parentWindow.isVisible()) {
                this.m_parentWindow.setVisible(true);
            }
        }
        this.m_internalDialog.setVisible(false);
        this.m_internalDialog.dispose();
        synchronized (this.SYNC_DOCK) {
            if (this.m_docker != null) {
                this.m_docker.finalize();
                this.m_docker = null;
            }
        }
        synchronized (this.m_internalDialog.getTreeLock()) {
            final Enumeration<WindowListener> elements = this.m_windowListeners.elements();
            while (elements.hasMoreElements()) {
                final Thread thread = new Thread(new Runnable() {
                    private final /* synthetic */ WindowListener val$currrentListener = elements.nextElement();
                    private final /* synthetic */ JAPDialog this$0;
                    
                    public void run() {
                        SwingUtilities.invokeLater(new Runnable() {
                            private final /* synthetic */ JAPDialog$6 this$1 = this$1;
                            
                            public void run() {
                                this.this$1.val$currrentListener.windowClosed(new WindowEvent(this.this$1.this$0.m_internalDialog, 202));
                            }
                        });
                    }
                });
                thread.setDaemon(true);
                thread.start();
            }
            this.m_windowListeners.removeAllElements();
            final Enumeration<ComponentListener> elements2 = ((Vector)this.m_componentListeners.clone()).elements();
            while (elements2.hasMoreElements()) {
                this.removeComponentListener(elements2.nextElement());
            }
            this.m_componentListeners.removeAllElements();
            this.m_internalDialog.removeWindowListener(this.m_dialogWindowAdapter);
            this.m_dialogWindowAdapter = null;
            this.m_internalDialog.getContentPane().removeAll();
            this.m_internalDialog.getRootPane().removeAll();
            this.m_internalDialog.getLayeredPane().removeAll();
            this.m_internalDialog.getTreeLock().notifyAll();
        }
    }
    
    public void validate() {
        this.m_internalDialog.validate();
    }
    
    public void requestFocus() {
        this.m_internalDialog.requestFocus();
    }
    
    public final Dimension getSize() {
        return this.m_internalDialog.getSize();
    }
    
    public final Dimension getPreferredSize() {
        return this.m_internalDialog.getPreferredSize();
    }
    
    public final void setSize(final int n, final int n2) {
        this.m_internalDialog.setSize(n, n2);
    }
    
    public final void setLocation(final Point location) {
        this.m_bLocationSetManually = true;
        this.m_internalDialog.setLocation(location);
    }
    
    public final void setLocationCenteredOn(final Component component) {
        this.m_bLocationSetManually = true;
        GUIUtils.setLocationRelativeTo(component, this.m_internalDialog);
    }
    
    public final void setLocationCenteredOnParent() {
        this.m_bLocationSetManually = true;
        GUIUtils.setLocationRelativeTo(this.getParentComponent(), this.m_internalDialog);
    }
    
    public final void setLocationCenteredOnOwner() {
        this.setLocationCenteredOn(this.m_parentWindow);
    }
    
    public final Rectangle getScreenBounds() {
        return GUIUtils.getCurrentScreen(this.m_internalDialog).getBounds();
    }
    
    public void setDockable(final boolean b) {
        synchronized (this.SYNC_DOCK) {
            if (this.m_docker == null && b) {
                this.m_docker = new GUIUtils.WindowDocker(this.m_internalDialog);
            }
            else if (this.m_docker != null && !b) {
                this.m_docker.finalize();
                this.m_docker = null;
            }
        }
    }
    
    public void resetAutomaticLocation(final boolean bLocationSetManually) {
        this.m_bLocationSetManually = bLocationSetManually;
    }
    
    public final void setLocationCenteredOnScreen() {
        this.m_bLocationSetManually = true;
        GUIUtils.centerOnScreen(this.m_internalDialog);
    }
    
    public final void setLocationRelativeToOwner() {
        this.m_bLocationSetManually = true;
        GUIUtils.positionRightUnderWindow(this.m_internalDialog, this.getOwner());
    }
    
    public void restoreLocation(final Point point) {
        if (GUIUtils.restoreLocation(this.m_internalDialog, point)) {
            this.m_bLocationSetManually = true;
        }
    }
    
    public void restoreSize(final Dimension dimension) {
        GUIUtils.restoreSize(this.m_internalDialog, dimension);
    }
    
    public void moveToUpRightCorner() {
        GUIUtils.moveToUpRightCorner(this.m_internalDialog);
    }
    
    public final void setLocation(final int n, final int n2) {
        this.m_bLocationSetManually = true;
        this.m_internalDialog.setLocation(n, n2);
    }
    
    public final void setSize(final Dimension size) {
        this.m_internalDialog.setSize(size);
    }
    
    public void setResizable(final boolean resizable) {
        this.m_internalDialog.setResizable(resizable);
    }
    
    public final Point getLocation() {
        return this.m_internalDialog.getLocation();
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        return this.m_internalDialog.imageUpdate(image, n, n2, n3, n4, n5);
    }
    
    public final AccessibleContext getAccessibleContext() {
        return this.m_internalDialog.getAccessibleContext();
    }
    
    public Font getFont() {
        return this.m_internalDialog.getFont();
    }
    
    public void remove(final MenuComponent menuComponent) {
        this.m_internalDialog.remove(menuComponent);
    }
    
    public boolean postEvent(final Event event) {
        return this.m_internalDialog.postEvent(event);
    }
    
    public final void setDefaultCloseOperation(final int defaultCloseOperation) {
        this.m_defaultCloseOperation = defaultCloseOperation;
    }
    
    public final int getDefaultCloseOperation() {
        return this.m_defaultCloseOperation;
    }
    
    public final void addWindowListener(final WindowListener windowListener) {
        if (windowListener != null) {
            synchronized (this.m_internalDialog.getTreeLock()) {
                this.m_windowListeners.addElement(windowListener);
            }
        }
    }
    
    public final void addComponentListener(final ComponentListener componentListener) {
        synchronized (this.m_internalDialog.getTreeLock()) {
            if (componentListener != null && !this.m_componentListeners.contains(componentListener)) {
                this.m_componentListeners.addElement(componentListener);
                this.m_internalDialog.addComponentListener(componentListener);
            }
        }
    }
    
    public final void removeComponentListener(final ComponentListener componentListener) {
        synchronized (this.m_internalDialog.getTreeLock()) {
            this.m_componentListeners.removeElement(componentListener);
            this.m_internalDialog.removeComponentListener(componentListener);
        }
    }
    
    public final void removeWindowListener(final WindowListener windowListener) {
        synchronized (this.m_internalDialog.getTreeLock()) {
            this.m_windowListeners.removeElement(windowListener);
        }
    }
    
    public final void pack() {
        this.m_internalDialog.pack();
    }
    
    public Insets getInsets() {
        return this.m_internalDialog.getInsets();
    }
    
    void doWindowClosing() {
        this.m_dialogWindowAdapter.windowClosing(new WindowEvent(this.m_internalDialog, 201));
    }
    
    private static Window getInternalDialog(final JAPDialog japDialog) {
        if (japDialog == null) {
            return null;
        }
        return japDialog.m_internalDialog;
    }
    
    private static boolean requestFocusForFirstFocusableComponent(final Container container) {
        try {
            ((JAPDialog.class$java$awt$Container == null) ? (JAPDialog.class$java$awt$Container = class$("java.awt.Container")) : JAPDialog.class$java$awt$Container).getMethod("isFocusable", (Class[])null).invoke(container, (Object[])null);
            return true;
        }
        catch (Exception ex) {
            for (int i = 0; i < container.getComponentCount(); ++i) {
                if (container.getComponent(i) instanceof Container && requestFocusForFirstFocusableComponent((Container)container.getComponent(i))) {
                    return true;
                }
                if (container.getComponent(i).isFocusTraversable()) {
                    container.getComponent(i).requestFocus();
                    return true;
                }
            }
            return false;
        }
    }
    
    private void setVisibleInternal(final boolean b) {
        if (this.isVisible() && this.m_bBlockParentWindow && !b) {
            this.m_parentWindow.setEnabled(true);
            if (this.m_parentWindow.isVisible()) {
                this.m_parentWindow.setVisible(true);
            }
        }
        this.m_bBlockParentWindow = (b && this.m_bModal);
        if (this.m_bBlockParentWindow) {
            this.m_parentWindow.setEnabled(false);
        }
        if (this.m_bForceApplicationModality) {
            this.m_internalDialog.setVisible(b);
            return;
        }
        synchronized (this.m_internalDialog.getTreeLock()) {
            this.m_internalDialog.setVisible(b);
            if (b) {
                if (this.getContentPane() != null && this.getContentPane().isVisible()) {
                    this.getContentPane().setVisible(false);
                    this.getContentPane().setVisible(true);
                }
                this.m_internalDialog.toFront();
            }
            this.m_internalDialog.getTreeLock().notifyAll();
        }
        if (b) {
            requestFocusForFirstFocusableComponent(this.m_internalDialog.getContentPane());
            final JButton defaultButton = this.m_internalDialog.getRootPane().getDefaultButton();
            if (defaultButton != null) {
                defaultButton.requestFocus();
            }
        }
        if (this.m_bBlockParentWindow) {
            final Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        final BlockedWindowDeactivationAdapter blockedWindowDeactivationAdapter = new BlockedWindowDeactivationAdapter();
                        JAPDialog.this.m_parentWindow.addWindowListener(blockedWindowDeactivationAdapter);
                        JAPDialog.this.m_parentWindow.addFocusListener(blockedWindowDeactivationAdapter);
                        if (SwingUtilities.isEventDispatchThread()) {
                            final EventQueue systemEventQueue = JAPDialog.this.m_internalDialog.getToolkit().getSystemEventQueue();
                            while (JAPDialog.this.isVisible()) {
                                final AWTEvent nextEvent = systemEventQueue.getNextEvent();
                                if (JAPDialog.this.m_bBlockParentWindow && JAPDialog.this.m_parentWindow.isEnabled()) {
                                    JAPDialog.this.m_parentWindow.setEnabled(false);
                                }
                                Class<?> forName;
                                try {
                                    forName = Class.forName("java.awt.ActiveEvent");
                                }
                                catch (ClassNotFoundException ex3) {
                                    forName = null;
                                }
                                final Object source = nextEvent.getSource();
                                if (source == JAPDialog.this.m_internalDialog) {
                                    if (nextEvent instanceof WindowEvent) {
                                        if (((WindowEvent)nextEvent).getID() == 201) {
                                            JAPDialog.this.m_dialogWindowAdapter.windowClosing((WindowEvent)nextEvent);
                                            continue;
                                        }
                                    }
                                    else if (!(nextEvent instanceof KeyEvent) || JAPDialog.this.getRootPane().getDefaultButton() != null) {}
                                }
                                if (forName != null && forName.isInstance(nextEvent)) {
                                    forName.getMethod("dispatch", (Class[])null).invoke(nextEvent, (Object[])null);
                                }
                                else if (source instanceof Component) {
                                    if (source == JAPDialog.this.getParentComponent() && nextEvent instanceof WindowEvent && ((WindowEvent)nextEvent).getID() == 201) {
                                        continue;
                                    }
                                    try {
                                        ((JDialog)source).dispatchEvent(nextEvent);
                                    }
                                    catch (IllegalMonitorStateException ex) {
                                        LogHolder.log(5, LogType.GUI, ex);
                                    }
                                }
                                else {
                                    if (!(source instanceof MenuComponent)) {
                                        continue;
                                    }
                                    ((MenuComponent)source).dispatchEvent(nextEvent);
                                }
                            }
                        }
                        else {
                            synchronized (JAPDialog.this.m_internalDialog.getTreeLock()) {
                                while (JAPDialog.this.isVisible()) {
                                    try {
                                        JAPDialog.this.m_internalDialog.getTreeLock().wait();
                                    }
                                    catch (InterruptedException ex4) {
                                        break;
                                    }
                                }
                                JAPDialog.this.m_internalDialog.getTreeLock().notifyAll();
                            }
                        }
                        JAPDialog.this.m_parentWindow.removeWindowListener(blockedWindowDeactivationAdapter);
                        JAPDialog.this.m_parentWindow.removeFocusListener(blockedWindowDeactivationAdapter);
                    }
                    catch (Exception ex2) {
                        LogHolder.log(2, LogType.GUI, ex2);
                    }
                }
            };
            if (SwingUtilities.isEventDispatchThread()) {
                runnable.run();
            }
            else {
                try {
                    SwingUtilities.invokeAndWait(runnable);
                }
                catch (InterruptedException ex2) {
                    this.setVisible(false);
                }
                catch (Exception ex) {
                    LogHolder.log(2, LogType.GUI, ex);
                }
            }
            if (!this.m_parentWindow.isEnabled()) {
                this.m_bBlockParentWindow = false;
                this.m_parentWindow.setEnabled(!JAPDialog.ms_bConsoleOnly);
                if (this.m_parentWindow.isVisible()) {
                    this.m_parentWindow.setVisible(true);
                }
            }
            if (JAPDialog.ms_bConsoleOnly) {
                this.m_parentWindow.setEnabled(false);
            }
        }
        synchronized (this.m_internalDialog.getTreeLock()) {
            this.m_internalDialog.getTreeLock().notifyAll();
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
        FORMATS = new double[] { (1.0 + Math.sqrt(5.0)) / 2.0, 1.3333333333333333, 1.7777777777777777 };
        MSG_ERROR_UNKNOWN = ((JAPDialog.class$gui$dialog$JAPDialog == null) ? (JAPDialog.class$gui$dialog$JAPDialog = class$("gui.dialog.JAPDialog")) : JAPDialog.class$gui$dialog$JAPDialog).getName() + "_errorUnknown";
        MSG_TITLE_INFO = ((JAPDialog.class$gui$dialog$JAPDialog == null) ? (JAPDialog.class$gui$dialog$JAPDialog = class$("gui.dialog.JAPDialog")) : JAPDialog.class$gui$dialog$JAPDialog).getName() + "_titleInfo";
        MSG_TITLE_CONFIRMATION = ((JAPDialog.class$gui$dialog$JAPDialog == null) ? (JAPDialog.class$gui$dialog$JAPDialog = class$("gui.dialog.JAPDialog")) : JAPDialog.class$gui$dialog$JAPDialog).getName() + "_titleConfirmation";
        MSG_TITLE_WARNING = ((JAPDialog.class$gui$dialog$JAPDialog == null) ? (JAPDialog.class$gui$dialog$JAPDialog = class$("gui.dialog.JAPDialog")) : JAPDialog.class$gui$dialog$JAPDialog).getName() + "_titleWarning";
        MSG_TITLE_ERROR = ((JAPDialog.class$gui$dialog$JAPDialog == null) ? (JAPDialog.class$gui$dialog$JAPDialog = class$("gui.dialog.JAPDialog")) : JAPDialog.class$gui$dialog$JAPDialog).getName() + "_titleError";
        MSG_ERROR_UNDISPLAYABLE = ((JAPDialog.class$gui$dialog$JAPDialog == null) ? (JAPDialog.class$gui$dialog$JAPDialog = class$("gui.dialog.JAPDialog")) : JAPDialog.class$gui$dialog$JAPDialog).getName() + "_errorUndisplayable";
        MSG_BTN_PROCEED = ((JAPDialog.class$gui$dialog$JAPDialog == null) ? (JAPDialog.class$gui$dialog$JAPDialog = class$("gui.dialog.JAPDialog")) : JAPDialog.class$gui$dialog$JAPDialog).getName() + "_proceed";
        MSG_BTN_RETRY = ((JAPDialog.class$gui$dialog$JAPDialog == null) ? (JAPDialog.class$gui$dialog$JAPDialog = class$("gui.dialog.JAPDialog")) : JAPDialog.class$gui$dialog$JAPDialog).getName() + "_retry";
        JAPDialog.m_optimizedFormat = 2;
        JAPDialog.ms_registeredDialogs = new Hashtable();
        JAPDialog.ms_bConsoleOnly = false;
    }
    
    public interface ILinkedInformation
    {
        public static final String MSG_MORE_INFO = ((JAPDialog$9.class$gui$dialog$JAPDialog$ILinkedInformation == null) ? (JAPDialog$9.class$gui$dialog$JAPDialog$ILinkedInformation = JAPDialog$9.class$("gui.dialog.JAPDialog$ILinkedInformation")) : JAPDialog$9.class$gui$dialog$JAPDialog$ILinkedInformation).getName() + "_moreInfo";
        public static final int TYPE_DEFAULT = 0;
        public static final int TYPE_LINK = 1;
        public static final int TYPE_SELECTABLE_LINK = 2;
        public static final int TYPE_CHECKBOX_TRUE = 3;
        public static final int TYPE_CHECKBOX_FALSE = 4;
        
        String getMessage();
        
        void clicked(final boolean p0);
        
        int getType();
        
        boolean isApplicationModalityForced();
        
        boolean isOnTop();
        
        boolean isModal();
        
        boolean isCloseWindowActive();
        
        String getTooltipText();
    }
    
    public static class LinkedCheckBox extends LinkedHelpContext
    {
        public static final String MSG_REMEMBER_ANSWER;
        public static final String MSG_DO_NOT_SHOW_AGAIN;
        private String m_strMessage;
        private boolean m_bDefault;
        private boolean m_bState;
        static /* synthetic */ Class class$gui$dialog$JAPDialog$LinkedCheckBox;
        
        public LinkedCheckBox(final boolean b) {
            this(b, (JAPHelpContext.IHelpContext)null);
        }
        
        public LinkedCheckBox(final boolean b, final JAPHelpContext.IHelpContext helpContext) {
            this(JAPMessages.getString(LinkedCheckBox.MSG_DO_NOT_SHOW_AGAIN), b, helpContext);
        }
        
        public LinkedCheckBox(final boolean b, final String s) {
            this(JAPMessages.getString(LinkedCheckBox.MSG_DO_NOT_SHOW_AGAIN), b, s);
        }
        
        public LinkedCheckBox(final String s, final boolean b) {
            this(s, b, (JAPHelpContext.IHelpContext)null);
        }
        
        public LinkedCheckBox(final String s, final boolean b, final String s2) {
            this(s, b, new JAPHelpContext.IHelpContext() {
                private final /* synthetic */ String val$a_strHelpContext = val$a_strHelpContext;
                
                public String getHelpContext() {
                    return this.val$a_strHelpContext;
                }
                
                public Component getHelpExtractionDisplayContext() {
                    return null;
                }
            });
        }
        
        public LinkedCheckBox(final String strMessage, final boolean bDefault, final JAPHelpContext.IHelpContext helpContext) {
            super(helpContext);
            this.m_strMessage = strMessage;
            this.m_bDefault = bDefault;
            this.m_bState = this.m_bDefault;
        }
        
        public String getMessage() {
            return this.m_strMessage;
        }
        
        public void clicked(final boolean bState) {
            this.m_bState = bState;
        }
        
        public final boolean getState() {
            return this.m_bState;
        }
        
        public final int getType() {
            if (this.m_bDefault) {
                return 3;
            }
            return 4;
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
            MSG_REMEMBER_ANSWER = ((LinkedCheckBox.class$gui$dialog$JAPDialog$LinkedCheckBox == null) ? (LinkedCheckBox.class$gui$dialog$JAPDialog$LinkedCheckBox = class$("gui.dialog.JAPDialog$LinkedCheckBox")) : LinkedCheckBox.class$gui$dialog$JAPDialog$LinkedCheckBox).getName() + "_rememberAnswer";
            MSG_DO_NOT_SHOW_AGAIN = ((LinkedCheckBox.class$gui$dialog$JAPDialog$LinkedCheckBox == null) ? (LinkedCheckBox.class$gui$dialog$JAPDialog$LinkedCheckBox = class$("gui.dialog.JAPDialog$LinkedCheckBox")) : LinkedCheckBox.class$gui$dialog$JAPDialog$LinkedCheckBox).getName() + "_doNotShowAgain";
        }
    }
    
    public static class LinkedURLCheckBox extends LinkedCheckBox implements JAPHelpContext.IURLHelpContext
    {
        private URL m_url;
        private String m_message;
        
        public LinkedURLCheckBox(final boolean b, final URL url, final String message) {
            super(b, new JAPHelpContext.IURLHelpContext() {
                private final /* synthetic */ String val$a_message = val$a_message;
                
                public String getURLMessage() {
                    return this.val$a_message;
                }
                
                public URL getHelpURL() {
                    return url;
                }
                
                public String getHelpContext() {
                    return url.toString();
                }
                
                public Component getHelpExtractionDisplayContext() {
                    return null;
                }
            });
            if (url == null) {
                throw new NullPointerException("URL is null!");
            }
            if (message == null) {
                throw new NullPointerException("URL message is null!");
            }
            this.m_url = url;
            this.m_message = message;
        }
        
        public String getURLMessage() {
            return this.m_message;
        }
        
        public URL getHelpURL() {
            return this.m_url;
        }
    }
    
    public static class LinkedInformation extends LinkedInformationAdapter
    {
        private String m_message;
        private String m_eMail;
        private URL m_url;
        
        public LinkedInformation(final String s) {
            this(s, null);
        }
        
        public LinkedInformation(final String eMail, final String message) {
            this.m_message = message;
            if (AbstractX509AlternativeName.isValidEMail(eMail)) {
                this.m_eMail = eMail;
                if (this.m_message == null) {
                    this.m_message = this.m_eMail;
                }
            }
            else {
                try {
                    this.m_url = new URL(eMail);
                    if (this.m_message == null) {
                        this.m_message = this.m_url.toString();
                    }
                }
                catch (MalformedURLException ex) {}
            }
        }
        
        public final int getType() {
            return 1;
        }
        
        public final void clicked(final boolean b) {
            if (this.m_eMail != null) {
                AbstractOS.getInstance().openEMail(this.m_eMail);
            }
            else if (this.m_url != null) {
                AbstractOS.getInstance().openURL(this.m_url);
            }
        }
        
        public final String getMessage() {
            return this.m_message;
        }
    }
    
    public static class LinkedInformationAdapter implements ILinkedInformation
    {
        public String getTooltipText() {
            return null;
        }
        
        public String getMessage() {
            return null;
        }
        
        public void clicked(final boolean b) {
        }
        
        public int getType() {
            return 0;
        }
        
        public boolean isApplicationModalityForced() {
            return false;
        }
        
        public boolean isOnTop() {
            return false;
        }
        
        public boolean isModal() {
            return true;
        }
        
        public boolean isCloseWindowActive() {
            return true;
        }
    }
    
    public abstract static class AbstractLinkedURLAdapter extends LinkedInformationAdapter
    {
        private static final String MAILTO = "mailto:";
        
        public abstract URL getUrl();
        
        public String getTooltipText() {
            if (this.getUrl() != null) {
                String s = this.getUrl().toString();
                if (s.toLowerCase().startsWith("mailto:") && s.length() > "mailto:".length()) {
                    s = s.substring("mailto:".length(), s.length());
                }
                return s;
            }
            return null;
        }
        
        public String getMessage() {
            return this.getTooltipText();
        }
        
        public void clicked(final boolean b) {
            AbstractOS.getInstance().openURL(this.getUrl());
        }
        
        public final int getType() {
            return 1;
        }
        
        public boolean isApplicationModalityForced() {
            return false;
        }
    }
    
    public static class LinkedHelpContext extends LinkedInformationAdapter implements JAPHelpContext.IHelpContext
    {
        private JAPHelpContext.IHelpContext m_helpContext;
        
        public LinkedHelpContext(final JAPHelpContext.IHelpContext helpContext) {
            this.m_helpContext = helpContext;
        }
        
        public LinkedHelpContext(final String s) {
            this.m_helpContext = new JAPHelpContext.IHelpContext() {
                public String getHelpContext() {
                    return s;
                }
                
                public Component getHelpExtractionDisplayContext() {
                    return null;
                }
            };
        }
        
        public final String getHelpContext() {
            if (this.m_helpContext == null) {
                return null;
            }
            return this.m_helpContext.getHelpContext();
        }
        
        public Component getHelpExtractionDisplayContext() {
            if (this.m_helpContext == null) {
                return null;
            }
            return this.m_helpContext.getHelpExtractionDisplayContext();
        }
        
        public String getMessage() {
            return null;
        }
        
        public void clicked(final boolean b) {
        }
        
        public int getType() {
            return 0;
        }
        
        public final boolean isApplicationModalityForced() {
            return false;
        }
    }
    
    public static class Options
    {
        private int m_optionType;
        
        public Options(final int optionType) {
            this.m_optionType = optionType;
        }
        
        public final int getOptionType() {
            return this.m_optionType;
        }
        
        public int getDefaultButton() {
            if (this.m_optionType == -2147483647 || this.m_optionType == 2 || this.m_optionType == 1) {
                return 1;
            }
            if (this.m_optionType == 0) {
                return 3;
            }
            return -1;
        }
        
        public boolean isDrawFocusEnabled() {
            return true;
        }
        
        public String getYesOKText() {
            return null;
        }
        
        public String getNoText() {
            return null;
        }
        
        public String getCancelText() {
            return null;
        }
    }
    
    private class DialogWindowAdapter implements WindowListener
    {
        public void windowOpened(final WindowEvent windowEvent) {
            final Vector vector = (Vector)JAPDialog.this.m_windowListeners.clone();
            for (int i = 0; i < vector.size(); ++i) {
                vector.elementAt(i).windowOpened(windowEvent);
            }
        }
        
        public void windowIconified(final WindowEvent windowEvent) {
            final Vector vector = (Vector)JAPDialog.this.m_windowListeners.clone();
            for (int i = 0; i < vector.size(); ++i) {
                vector.elementAt(i).windowIconified(windowEvent);
            }
        }
        
        public void windowDeiconified(final WindowEvent windowEvent) {
            final Vector vector = (Vector)JAPDialog.this.m_windowListeners.clone();
            for (int i = 0; i < vector.size(); ++i) {
                vector.elementAt(i).windowDeiconified(windowEvent);
            }
        }
        
        public void windowDeactivated(final WindowEvent windowEvent) {
            final Vector vector = (Vector)JAPDialog.this.m_windowListeners.clone();
            for (int i = 0; i < vector.size(); ++i) {
                vector.elementAt(i).windowDeactivated(windowEvent);
            }
        }
        
        public void windowActivated(final WindowEvent windowEvent) {
            final Vector vector = (Vector)JAPDialog.this.m_windowListeners.clone();
            for (int i = 0; i < vector.size(); ++i) {
                vector.elementAt(i).windowActivated(windowEvent);
            }
        }
        
        public void windowClosed(final WindowEvent windowEvent) {
        }
        
        public void windowClosing(final WindowEvent windowEvent) {
            if (JAPDialog.this.isEnabled()) {
                if (JAPDialog.this.getDefaultCloseOperation() == 2) {
                    try {
                        JAPDialog.this.dispose();
                    }
                    catch (IllegalMonitorStateException ex) {
                        LogHolder.log(7, LogType.GUI, ex);
                    }
                }
                else if (JAPDialog.this.getDefaultCloseOperation() == 1) {
                    JAPDialog.this.setVisible(false);
                }
                else if (!JAPDialog.this.isVisible()) {
                    JAPDialog.this.m_internalDialog.setVisible(true);
                    LogHolder.log(6, LogType.GUI, "Fixed old JRE dialog closing bug.");
                }
                final Vector vector = (Vector)JAPDialog.this.m_windowListeners.clone();
                for (int i = 0; i < vector.size(); ++i) {
                    vector.elementAt(i).windowClosing(windowEvent);
                }
            }
        }
    }
    
    private static class SimpleDialogButtonFocusWindowAdapter extends WindowAdapter
    {
        private DialogContentPane m_contentPane;
        
        public SimpleDialogButtonFocusWindowAdapter(final DialogContentPane contentPane) {
            this.m_contentPane = contentPane;
        }
        
        public void windowOpened(final WindowEvent windowEvent) {
            if (this.m_contentPane.getButtonCancel() != null) {
                this.m_contentPane.getButtonCancel().requestFocus();
            }
            else if (this.m_contentPane.getButtonNo() != null) {
                this.m_contentPane.getButtonNo().requestFocus();
            }
            else if (this.m_contentPane.getButtonYesOK() != null) {
                this.m_contentPane.getButtonYesOK().requestFocus();
            }
            else if (this.m_contentPane.getButtonHelp() != null) {
                this.m_contentPane.getButtonHelp().requestFocus();
            }
        }
    }
    
    private static class LinkedInformationClickListener extends MouseAdapter implements ItemListener
    {
        private ILinkedInformation m_linkedInformation;
        
        public LinkedInformationClickListener(final ILinkedInformation linkedInformation) {
            this.m_linkedInformation = linkedInformation;
        }
        
        public void mouseClicked(final MouseEvent mouseEvent) {
            this.m_linkedInformation.clicked(false);
        }
        
        public void itemStateChanged(final ItemEvent itemEvent) {
            this.m_linkedInformation.clicked(((JCheckBox)itemEvent.getSource()).isSelected());
        }
    }
    
    private static class PreferredWidthBoxPanel extends JPanel
    {
        private static final long serialVersionUID = 1L;
        private int m_preferredWidth;
        private int m_preferredHeigth;
        
        public PreferredWidthBoxPanel() {
            this.m_preferredWidth = 0;
            this.m_preferredHeigth = 0;
            this.setLayout(new BoxLayout(this, 1));
        }
        
        public void setPreferredWidth(final int preferredWidth) {
            this.m_preferredHeigth = 0;
            this.m_preferredWidth = preferredWidth;
        }
        
        public void setPreferredHeigth(final int preferredHeigth) {
            this.m_preferredHeigth = preferredHeigth;
            this.m_preferredWidth = 0;
        }
        
        public Dimension getPreferredSize() {
            if (this.m_preferredWidth <= 0 && this.m_preferredHeigth <= 0) {
                return super.getPreferredSize();
            }
            if (this.m_preferredWidth > 0) {
                return new Dimension(this.m_preferredWidth, super.getPreferredSize().height);
            }
            return new Dimension(super.getPreferredSize().width, this.m_preferredHeigth);
        }
    }
    
    private class BlockedWindowDeactivationAdapter extends WindowAdapter implements FocusListener
    {
        public void windowActivated(final WindowEvent windowEvent) {
            this.deactivate(windowEvent.getWindow());
        }
        
        public void focusGained(final FocusEvent focusEvent) {
            this.deactivate((Window)focusEvent.getComponent());
        }
        
        public void focusLost(final FocusEvent focusEvent) {
        }
        
        private void deactivate(final Window window) {
            if (JAPDialog.this.m_bBlockParentWindow) {
                JAPDialog.this.toFront();
                if (window.isEnabled()) {
                    window.setEnabled(false);
                }
            }
        }
    }
}
