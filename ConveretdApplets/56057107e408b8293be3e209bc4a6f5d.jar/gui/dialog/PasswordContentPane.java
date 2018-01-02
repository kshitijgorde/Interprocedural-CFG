// 
// Decompiled by Procyon v0.5.30
// 

package gui.dialog;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import anon.util.Util;
import java.awt.Color;
import logging.LogType;
import java.awt.event.ComponentListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Insets;
import java.awt.LayoutManager;
import gui.GUIUtils;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JMenuItem;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import anon.util.JAPMessages;
import javax.swing.JPopupMenu;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import anon.util.IMiscPasswordReader;

public class PasswordContentPane extends DialogContentPane implements IMiscPasswordReader, IWizardSuitable
{
    public static final int PASSWORD_NEW = 1;
    public static final int PASSWORD_ENTER = 2;
    public static final int PASSWORD_CHANGE = 3;
    public static final int NO_MINIMUM_LENGTH = 0;
    public static final String MSG_ENTER_LBL;
    private static final int FIELD_LENGTH = 15;
    private static final String MSG_TOO_SHORT;
    private static final String MSG_CAPS_LOCK_PRESSED;
    private static final String MSG_WRONG_PASSWORD;
    public static final String MSG_ENTER_PASSWORD_TITLE;
    private static final String MSG_CONFIRM_LBL;
    private static final String MSG_ENTER_OLD_LBL;
    private static final String MSG_ENTER_NEW_LBL;
    private static final String MSG_PASSWORDS_DONT_MATCH;
    private static final String MSG_INSERT_FROM_CLIP;
    private JPasswordField m_textOldPasswd;
    private JPasswordField m_textNewPasswd;
    private JPasswordField m_textConfirmPasswd;
    private char[] m_passwd;
    private char[] m_oldPasswd;
    private int m_type;
    private int m_minLength;
    private JLabel m_lblNew1;
    private JLabel m_lblNew2;
    private JLabel m_lblOld;
    private JPopupMenu m_popup;
    private JPasswordField m_currentPopup;
    static /* synthetic */ Class class$gui$dialog$PasswordContentPane;
    
    public PasswordContentPane(final JAPDialog japDialog, final int n, final String s, final int n2) {
        this(japDialog, null, n, s, n2);
    }
    
    public PasswordContentPane(final JAPDialog japDialog, final int n, final String s) {
        this(japDialog, null, n, s, 0);
    }
    
    public PasswordContentPane(final JAPDialog japDialog, final DialogContentPane dialogContentPane, final int n, final String s) {
        this(japDialog, dialogContentPane, n, s, 0);
    }
    
    public PasswordContentPane(final JAPDialog japDialog, final DialogContentPane dialogContentPane, final int type, final String s, int minLength) {
        super(japDialog, (s != null) ? s : "", new Layout(JAPMessages.getString(PasswordContentPane.MSG_ENTER_PASSWORD_TITLE), 3), new DialogContentPaneOptions(2, dialogContentPane));
        this.m_passwd = null;
        this.m_oldPasswd = null;
        this.m_popup = new JPopupMenu();
        this.setDefaultButtonOperation(266);
        if (type < 1 || type > 3) {
            throw new IllegalArgumentException("Unknown type!");
        }
        this.m_type = type;
        if (minLength < 0) {
            minLength = 0;
        }
        this.m_minLength = minLength;
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final JMenuItem menuItem = new JMenuItem(JAPMessages.getString(PasswordContentPane.MSG_INSERT_FROM_CLIP));
        final MouseAdapter mouseAdapter = new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                if (GUIUtils.isMouseButton(mouseEvent, 8) || GUIUtils.isMouseButton(mouseEvent, 4)) {
                    PasswordContentPane.this.m_currentPopup = (JPasswordField)mouseEvent.getComponent();
                    PasswordContentPane.this.m_popup.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
                }
            }
        };
        this.getContentPane().setLayout(layout);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        this.m_popup = new JPopupMenu();
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                final Transferable contents = GUIUtils.getSystemClipboard().getContents(this);
                if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    try {
                        PasswordContentPane.this.m_currentPopup.setText((String)contents.getTransferData(DataFlavor.stringFlavor));
                    }
                    catch (Exception ex) {}
                }
            }
        });
        this.m_popup.add(menuItem);
        if (type == 3) {
            layout.setConstraints(this.m_lblOld = new JLabel(this.getOldPasswordLabel()), gridBagConstraints);
            this.getContentPane().add(this.m_lblOld);
            (this.m_textOldPasswd = new JPasswordField(15)).setEchoChar('*');
            this.m_textOldPasswd.addMouseListener(mouseAdapter);
            gridBagConstraints.gridx = 1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.fill = 2;
            layout.setConstraints(this.m_textOldPasswd, gridBagConstraints);
            this.getContentPane().add(this.m_textOldPasswd);
        }
        if (type == 3 || type == 1) {
            this.m_lblNew1 = new JLabel(this.getNewPasswordLabel());
            gridBagConstraints.gridx = 0;
            final GridBagConstraints gridBagConstraints2 = gridBagConstraints;
            ++gridBagConstraints2.gridy;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.fill = 0;
            this.getContentPane().add(this.m_lblNew1, gridBagConstraints);
            (this.m_textNewPasswd = new JPasswordField(15)).setEchoChar('*');
            this.m_textNewPasswd.addMouseListener(mouseAdapter);
            gridBagConstraints.fill = 2;
            gridBagConstraints.gridx = 1;
            gridBagConstraints.weightx = 1.0;
            layout.setConstraints(this.m_textNewPasswd, gridBagConstraints);
            this.getContentPane().add(this.m_textNewPasswd);
        }
        if (type == 2) {
            this.m_lblNew2 = new JLabel(JAPMessages.getString(PasswordContentPane.MSG_ENTER_LBL));
        }
        else {
            this.m_lblNew2 = new JLabel(JAPMessages.getString(PasswordContentPane.MSG_CONFIRM_LBL));
        }
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridx = 0;
        final GridBagConstraints gridBagConstraints3 = gridBagConstraints;
        ++gridBagConstraints3.gridy;
        gridBagConstraints.weightx = 0.0;
        this.getContentPane().add(this.m_lblNew2, gridBagConstraints);
        (this.m_textConfirmPasswd = new JPasswordField(15)).setEchoChar('*');
        this.m_textConfirmPasswd.addMouseListener(mouseAdapter);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 2;
        layout.setConstraints(this.m_textConfirmPasswd, gridBagConstraints);
        this.getContentPane().add(this.m_textConfirmPasswd);
        final CapsLockAdapter capsLockAdapter = new CapsLockAdapter();
        this.m_textConfirmPasswd.addKeyListener(capsLockAdapter);
        if (this.m_textNewPasswd != null) {
            this.m_textNewPasswd.addKeyListener(capsLockAdapter);
        }
        if (this.m_textOldPasswd != null) {
            this.m_textOldPasswd.addKeyListener(capsLockAdapter);
        }
        this.addComponentListener(new SetFocusComponentAdapter());
    }
    
    public String readPassword(final Object o) {
        final CheckError[] updateDialog = this.updateDialog();
        if (updateDialog != null && updateDialog.length > 0) {
            return null;
        }
        this.setButtonValue(-1);
        this.showDialog();
        if (o != null) {
            this.printStatusMessage(o.toString());
        }
        if (this.getButtonValue() != 0 || this.getPassword() == null) {
            return null;
        }
        return new String(this.getPassword());
    }
    
    public char[] getPassword() {
        if (this.getButtonValue() != 0) {
            return null;
        }
        if (this.m_passwd == null) {
            return new char[] { '\0' };
        }
        return this.m_passwd;
    }
    
    public boolean isAutomaticFocusSettingEnabled() {
        return false;
    }
    
    public String getNewPasswordLabel() {
        return JAPMessages.getString(PasswordContentPane.MSG_ENTER_NEW_LBL);
    }
    
    public String getOldPasswordLabel() {
        return JAPMessages.getString(PasswordContentPane.MSG_ENTER_OLD_LBL);
    }
    
    public char[] getOldPassword() {
        if (!this.hasValidValue()) {
            return null;
        }
        if (this.m_oldPasswd == null) {
            return new char[] { '\0' };
        }
        return this.m_oldPasswd;
    }
    
    public char[] getComparedPassword() {
        return null;
    }
    
    public CheckError[] checkYesOK() {
        CheckError[] array = new CheckError[0];
        if (this.m_type == 1 || this.m_type == 3) {
            if (this.m_minLength > 0 && (this.m_textNewPasswd.getPassword() == null || this.m_textNewPasswd.getPassword().length < this.m_minLength)) {
                array = new CheckError[] { new CheckError(JAPMessages.getString(PasswordContentPane.MSG_TOO_SHORT, new Integer(this.m_minLength)), LogType.GUI) {
                        public void doErrorAction() {
                            PasswordContentPane.this.m_lblNew1.setForeground(Color.red);
                        }
                        
                        public void undoErrorAction() {
                            PasswordContentPane.this.m_lblNew1.setForeground(new JLabel().getForeground());
                        }
                    } };
            }
            if (!Util.arraysEqual(this.m_textConfirmPasswd.getPassword(), this.m_textNewPasswd.getPassword())) {
                if (array.length == 1) {
                    array = new CheckError[] { null, array[0] };
                }
                else {
                    array = new CheckError[] { null };
                }
                array[0] = new CheckError(JAPMessages.getString(PasswordContentPane.MSG_PASSWORDS_DONT_MATCH), LogType.GUI) {
                    public void doErrorAction() {
                        PasswordContentPane.this.m_lblNew1.setForeground(Color.red);
                        PasswordContentPane.this.m_lblNew2.setForeground(Color.red);
                    }
                    
                    public void undoErrorAction() {
                        PasswordContentPane.this.m_lblNew1.setForeground(new JLabel().getForeground());
                        PasswordContentPane.this.m_lblNew2.setForeground(new JLabel().getForeground());
                    }
                };
            }
            else {
                this.m_passwd = this.m_textNewPasswd.getPassword();
            }
        }
        else if (this.m_type == 2) {
            this.m_passwd = this.m_textConfirmPasswd.getPassword();
        }
        if (this.m_type == 3) {
            if (this.getComparedPassword() != null && !Util.arraysEqual(this.getComparedPassword(), this.m_textOldPasswd.getPassword())) {
                if (array.length == 1) {
                    array = new CheckError[] { null, array[0] };
                }
                else if (array.length == 2) {
                    array = new CheckError[] { null, array[0], array[1] };
                }
                else {
                    array = new CheckError[] { null };
                }
                array[0] = new CheckError(JAPMessages.getString(PasswordContentPane.MSG_WRONG_PASSWORD), LogType.GUI) {
                    public void doErrorAction() {
                        PasswordContentPane.this.m_lblOld.setForeground(Color.red);
                    }
                    
                    public void undoErrorAction() {
                        PasswordContentPane.this.m_lblOld.setForeground(new JLabel().getForeground());
                    }
                };
            }
            else {
                this.m_oldPasswd = this.m_textOldPasswd.getPassword();
            }
        }
        return array;
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
        MSG_ENTER_LBL = ((PasswordContentPane.class$gui$dialog$PasswordContentPane == null) ? (PasswordContentPane.class$gui$dialog$PasswordContentPane = class$("gui.dialog.PasswordContentPane")) : PasswordContentPane.class$gui$dialog$PasswordContentPane).getName() + "_enterPasswordLabel";
        MSG_TOO_SHORT = ((PasswordContentPane.class$gui$dialog$PasswordContentPane == null) ? (PasswordContentPane.class$gui$dialog$PasswordContentPane = class$("gui.dialog.PasswordContentPane")) : PasswordContentPane.class$gui$dialog$PasswordContentPane).getName() + "_tooShort";
        MSG_CAPS_LOCK_PRESSED = ((PasswordContentPane.class$gui$dialog$PasswordContentPane == null) ? (PasswordContentPane.class$gui$dialog$PasswordContentPane = class$("gui.dialog.PasswordContentPane")) : PasswordContentPane.class$gui$dialog$PasswordContentPane).getName() + "_pressedCapsLock";
        MSG_WRONG_PASSWORD = ((PasswordContentPane.class$gui$dialog$PasswordContentPane == null) ? (PasswordContentPane.class$gui$dialog$PasswordContentPane = class$("gui.dialog.PasswordContentPane")) : PasswordContentPane.class$gui$dialog$PasswordContentPane).getName() + "_wrongPassword";
        MSG_ENTER_PASSWORD_TITLE = ((PasswordContentPane.class$gui$dialog$PasswordContentPane == null) ? (PasswordContentPane.class$gui$dialog$PasswordContentPane = class$("gui.dialog.PasswordContentPane")) : PasswordContentPane.class$gui$dialog$PasswordContentPane).getName() + "_title";
        MSG_CONFIRM_LBL = ((PasswordContentPane.class$gui$dialog$PasswordContentPane == null) ? (PasswordContentPane.class$gui$dialog$PasswordContentPane = class$("gui.dialog.PasswordContentPane")) : PasswordContentPane.class$gui$dialog$PasswordContentPane).getName() + "_confirmPasswordLabel";
        MSG_ENTER_OLD_LBL = ((PasswordContentPane.class$gui$dialog$PasswordContentPane == null) ? (PasswordContentPane.class$gui$dialog$PasswordContentPane = class$("gui.dialog.PasswordContentPane")) : PasswordContentPane.class$gui$dialog$PasswordContentPane).getName() + "_enterOldPasswordLabel";
        MSG_ENTER_NEW_LBL = ((PasswordContentPane.class$gui$dialog$PasswordContentPane == null) ? (PasswordContentPane.class$gui$dialog$PasswordContentPane = class$("gui.dialog.PasswordContentPane")) : PasswordContentPane.class$gui$dialog$PasswordContentPane).getName() + "_enterNewPasswordLabel";
        MSG_PASSWORDS_DONT_MATCH = ((PasswordContentPane.class$gui$dialog$PasswordContentPane == null) ? (PasswordContentPane.class$gui$dialog$PasswordContentPane = class$("gui.dialog.PasswordContentPane")) : PasswordContentPane.class$gui$dialog$PasswordContentPane).getName() + "_passwordsDontMatch";
        MSG_INSERT_FROM_CLIP = ((PasswordContentPane.class$gui$dialog$PasswordContentPane == null) ? (PasswordContentPane.class$gui$dialog$PasswordContentPane = class$("gui.dialog.PasswordContentPane")) : PasswordContentPane.class$gui$dialog$PasswordContentPane).getName() + "_insertFromClipboard";
    }
    
    private class CapsLockAdapter extends KeyAdapter
    {
        private int m_messageID;
        static /* synthetic */ Class class$java$awt$Toolkit;
        
        private CapsLockAdapter() {
            this.m_messageID = 0;
        }
        
        public void keyPressed(final KeyEvent keyEvent) {
            boolean booleanValue = false;
            try {
                booleanValue = (boolean)((CapsLockAdapter.class$java$awt$Toolkit == null) ? (CapsLockAdapter.class$java$awt$Toolkit = class$("java.awt.Toolkit")) : CapsLockAdapter.class$java$awt$Toolkit).getMethod("getLockingKeyState", Integer.TYPE).invoke(PasswordContentPane.this.getContentPane().getToolkit(), new Integer(20));
            }
            catch (Exception ex) {}
            if (booleanValue) {
                this.m_messageID = PasswordContentPane.this.printErrorStatusMessage(JAPMessages.getString(PasswordContentPane.MSG_CAPS_LOCK_PRESSED), LogType.GUI);
            }
            else {
                PasswordContentPane.this.clearStatusMessage(this.m_messageID);
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
    }
    
    private class SetFocusComponentAdapter extends ComponentAdapter
    {
        public void componentShown(final ComponentEvent componentEvent) {
            if (PasswordContentPane.this.m_type == 3) {
                PasswordContentPane.this.m_textOldPasswd.requestFocus();
            }
            else if (PasswordContentPane.this.m_type == 1) {
                PasswordContentPane.this.m_textNewPasswd.requestFocus();
            }
            else {
                PasswordContentPane.this.m_textConfirmPasswd.requestDefaultFocus();
            }
        }
    }
}
