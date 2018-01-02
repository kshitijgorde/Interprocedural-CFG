// 
// Decompiled by Procyon v0.5.30
// 

package pclient.ady;

import pclient.shd.Config;
import javax.swing.JComponent;
import com.pchat.sc.MsgOptions;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class PcSessComp extends JPanel implements ActionListener
{
    private static final String ACT_SEND = "send";
    private static final String ACT_BOLD = "BD";
    private static final String ACT_ITA = "ITA";
    private static final String ACT_SMILE = "SM";
    private static final String ACT_COLOR = "CL";
    private static final String ACT_SOUND = "sd";
    protected String thirdParty;
    private String thisUser;
    protected JTextArea textInput;
    private JToggleButton boldToggle;
    private JToggleButton italicToggle;
    private JLabel topLabel;
    private JLabel typingLabel;
    private JLabel clockLabel;
    private JLabel infoLabel;
    private String partyAvatar;
    private boolean isActive;
    private boolean isIconed;
    private String Non_Empty;
    private long partyTyping;
    private boolean typingErased;
    private long typingGap;
    private long myTyping;
    private long creationTime;
    
    public PcSessComp(final PcConsole pcConsole, final String s, final String s2) {
        this.topLabel = null;
        this.partyAvatar = null;
        this.isActive = false;
        this.isIconed = false;
        this.Non_Empty = " ";
        this.partyTyping = 0L;
        this.typingErased = true;
        this.typingGap = 5000L;
        this.myTyping = 0L;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        actionEvent.getActionCommand();
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void receivedPrivate(final String s, final String s2, final String s3, final String s4, final MsgOptions msgOptions) {
    }
    
    protected void handlePostReceived(final String s) {
    }
    
    public void receivedSelfPrivate(final String s, final String s2, final String s3, final String s4, final MsgOptions msgOptions) {
    }
    
    public void joinLeave(final String s, final boolean b) {
    }
    
    protected void showTyping() {
    }
    
    protected void handleShowTyping() {
    }
    
    protected void eraseTyping() {
    }
    
    protected void handleEraseTyping() {
    }
    
    protected void displayText(final String s, final MsgOptions msgOptions) {
    }
    
    private void actSend() {
    }
    
    private MsgOptions genOptions(final JComponent component) {
        return new MsgOptions();
    }
    
    private void sendTyping() {
    }
    
    protected Config getConf() {
        return null;
    }
    
    public void pickFocus() {
    }
    
    private String getInfo(final String s, final String s2, final String s3) {
        return null;
    }
    
    private void updateClock() {
    }
    
    private String getMinutes(final long n) {
        final long n2 = n / 1000L;
        final long n3 = n2 / 3600L;
        final long n4 = (n2 - n3 * 3600L) / 60L;
        String s = "" + n3;
        String s2 = "" + n4;
        if (n3 < 10L) {
            s = "0" + s;
        }
        if (n4 < 10L) {
            s2 = "0" + s2;
        }
        return s + ":" + s2 + " (hh:mm)";
    }
    
    private void checkAvatarIgnore() {
    }
    
    private void buildUI() {
    }
}
