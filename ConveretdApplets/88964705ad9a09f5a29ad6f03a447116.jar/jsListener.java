import java.awt.event.MouseEvent;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.awt.Container;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import javax.swing.SwingUtilities;
import java.awt.Font;
import javax.swing.Icon;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.CardLayout;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.util.Timer;
import java.io.File;
import java.awt.event.MouseListener;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class jsListener extends JPanel implements ActionListener, ChangeListener, MouseListener, IAXEventListener
{
    public static String embedCall;
    Boolean checkPass;
    Boolean isMuted;
    private static final String DEFAULT_STATUS_STRING = "Welcome to Starphone!";
    private static final int FONTSIZE = 4;
    private final int minWidth = 150;
    private final int minHeight = 200;
    private final int maxWidth = 400;
    private final int maxHeight = 620;
    private final int numOffers = 5;
    private static String pw1;
    private static File clientImgPath;
    private static String numDialed;
    private static String chatDialed;
    private static String chatDialedPin;
    private static Timer callTimer;
    private static int callDuration;
    public static boolean isCallActive;
    public static int callNumber;
    static final boolean shouldFill = true;
    static final boolean shouldWeightX = true;
    static final boolean RIGHT_TO_LEFT = false;
    static boolean isPlaying;
    ImageIcon bgImage;
    ImageIcon playImageIcon;
    ImageIcon endImageIcon;
    ImageIcon muteImageIcon;
    ImageIcon loudImageIcon;
    ImageIcon talkrImageIcon;
    ImageIcon playImageTemp;
    JPanel password;
    JPanel mainHolder;
    JLabel statusLabel;
    JSlider speakerSlider;
    JPanel main;
    JPanel leftPanel;
    JPanel centerPanel;
    JPanel rightPanel;
    JButton talkr_btn;
    JButton mute_btn;
    JButton loud_btn;
    JButton play_btn;
    JButton end_btn;
    JButton pass_btn;
    JLabel passwordLabel;
    JPasswordField passwordField;
    
    public jsListener(final String chatName) {
        this.checkPass = false;
        this.isMuted = false;
        (this.mainHolder = new JPanel(new CardLayout())).setBackground(Color.BLACK);
        (this.password = new JPanel()).setLayout(new GridBagLayout());
        this.password.setBackground(Color.BLACK);
        (this.passwordLabel = new JLabel("This chat requires a password:")).setForeground(Color.WHITE);
        this.passwordField = new JPasswordField(16);
        (this.pass_btn = new JButton("Submit")).setName("pass_btn");
        this.pass_btn.addMouseListener(this);
        final GridBagConstraints d = new GridBagConstraints();
        d.gridx = 0;
        d.gridy = 0;
        this.password.add(this.passwordLabel, d);
        d.gridx = 0;
        d.gridy = 1;
        this.password.add(this.passwordField, d);
        d.gridx = 0;
        d.gridy = 2;
        this.password.add(this.pass_btn, d);
        this.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());
        jsListener.embedCall = "Chat=" + chatName;
        this.playImageIcon = createImageIcon("images/smallPlay_btn.jpg", "play btn");
        this.playImageTemp = createImageIcon("images/smallPlay_btn.jpg", "play btn");
        this.endImageIcon = createImageIcon("images/smallStop_btn.jpg", "stop btn");
        this.talkrImageIcon = createImageIcon("images/talkrLogo_btn.jpg", "talkr btn");
        this.muteImageIcon = createImageIcon("images/stopMute_btn.jpg", "mute btn");
        this.loudImageIcon = createImageIcon("images/stopUnmute_btn.jpg", "loud btn");
        final ImageIcon north = createImageIcon("images/smallTop.jpg", "north");
        final ImageIcon south = createImageIcon("images/smallbot.jpg", "south");
        final ImageIcon west = createImageIcon("images/smallLeft.jpg", "west");
        final ImageIcon east = createImageIcon("images/smallRight.jpg", "east");
        (this.talkr_btn = new JButton(this.talkrImageIcon)).setOpaque(false);
        this.talkr_btn.setBackground(Color.BLACK);
        this.talkr_btn.setBorderPainted(false);
        this.talkr_btn.setContentAreaFilled(false);
        this.talkr_btn.setName("talkr_btn");
        this.talkr_btn.addMouseListener(this);
        (this.play_btn = new JButton(this.playImageIcon)).setOpaque(false);
        this.play_btn.setBackground(Color.BLACK);
        this.play_btn.setBorderPainted(false);
        this.play_btn.setContentAreaFilled(false);
        this.play_btn.setName("play_btn");
        this.play_btn.addMouseListener(this);
        this.play_btn.setSize(this.play_btn.getMinimumSize());
        (this.end_btn = new JButton(this.endImageIcon)).setOpaque(false);
        this.end_btn.setBackground(Color.BLACK);
        this.end_btn.setBorderPainted(false);
        this.end_btn.setContentAreaFilled(false);
        this.end_btn.setName("end_btn");
        this.end_btn.addMouseListener(this);
        (this.mute_btn = new JButton(this.muteImageIcon)).setOpaque(false);
        this.mute_btn.setBackground(Color.BLACK);
        this.mute_btn.setBorderPainted(false);
        this.mute_btn.setContentAreaFilled(false);
        this.mute_btn.setName("mute_btn");
        this.mute_btn.addMouseListener(this);
        (this.loud_btn = new JButton(this.loudImageIcon)).setOpaque(false);
        this.loud_btn.setBackground(Color.BLACK);
        this.loud_btn.setBorderPainted(false);
        this.loud_btn.setContentAreaFilled(false);
        this.loud_btn.setName("loud_btn");
        this.loud_btn.addMouseListener(this);
        final JLabel northHolder = new JLabel(north);
        final JLabel southHolder = new JLabel(south);
        final JLabel westHolder = new JLabel(west);
        final JLabel eastHolder = new JLabel(east);
        (this.main = new JPanel(new BorderLayout())).setBackground(Color.BLACK);
        (this.leftPanel = new JPanel(new BorderLayout())).setBackground(Color.BLACK);
        (this.centerPanel = new JPanel()).setLayout(new BorderLayout());
        this.centerPanel.setBackground(Color.BLACK);
        (this.rightPanel = new JPanel(new BorderLayout())).setBackground(Color.BLACK);
        this.leftPanel.add(this.play_btn);
        this.mute_btn.setMaximumSize(this.mute_btn.getMinimumSize());
        this.rightPanel.add(this.mute_btn, "North");
        this.rightPanel.add(this.talkr_btn, "South");
        this.rightPanel.setMaximumSize(this.rightPanel.getMinimumSize());
        this.rightPanel.setPreferredSize(this.rightPanel.getMinimumSize());
        this.leftPanel.setMaximumSize(this.leftPanel.getMinimumSize());
        this.leftPanel.setPreferredSize(this.leftPanel.getMinimumSize());
        this.leftPanel.setSize(59, 67);
        final JLabel chatNameLabel = new JLabel(Starphone.chatName);
        chatNameLabel.setFont(Font.decode("Helvetica-Bold-14"));
        chatNameLabel.setToolTipText(Starphone.chatName);
        (this.statusLabel = new JLabel("0 live")).setFont(Font.decode("Helvetica-Bold-10"));
        chatNameLabel.setForeground(Color.WHITE);
        this.statusLabel.setForeground(Color.WHITE);
        this.centerPanel.add(chatNameLabel, "North");
        this.centerPanel.add(this.statusLabel, "Center");
        this.centerPanel.setPreferredSize(this.centerPanel.getMaximumSize());
        this.main.add(this.leftPanel, "West");
        this.main.add(this.centerPanel, "Center");
        this.main.add(this.rightPanel, "East");
        this.add(northHolder, "North");
        this.add(southHolder, "South");
        this.add(westHolder, "West");
        this.add(eastHolder, "East");
        this.mainHolder.add(this.main, "mainpanel");
        this.mainHolder.add(this.password, "password");
        this.add(this.mainHolder, "Center");
        this.validate();
    }
    
    public static void updatePhoneStatusLabel(final String s) {
    }
    
    public static void indicateRinging(final String s, final short num) {
        Starphone.setCallActive(true);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                jsListener.callNumber = num;
                schedThread.nowPlayingTones = true;
                Starphone.callAlert(num);
            }
        });
    }
    
    public void answer(final int callNo) {
        jsListener.callNumber = callNo;
    }
    
    public void register(final String s) {
    }
    
    public void hangup(final int callNo) {
    }
    
    public void dial() {
    }
    
    public void hold() {
    }
    
    public void mute(final int callNo, final boolean b) {
    }
    
    public void text(final String s) {
        String data = s.substring(1);
        switch (s.charAt(0)) {
            case '0': {
                final StringTokenizer st = new StringTokenizer(data, "&");
                try {
                    String url = st.nextToken();
                    final String text = st.nextToken();
                    final String tooltip = st.nextToken();
                    if (!url.startsWith("http://")) {
                        url = "http://" + url;
                    }
                }
                catch (NoSuchElementException nsee) {
                    nsee.printStackTrace();
                }
                break;
            }
            case '1': {
                this.processRegistrationResponse(data);
                break;
            }
            case '2': {
                if (data.subSequence(0, 7).equals("Chat:OK")) {
                    final CardLayout cl = (CardLayout)this.mainHolder.getLayout();
                    cl.show(this.mainHolder, "mainpanel");
                    break;
                }
                if (data.subSequence(0, 11).equals("Chat:ENOPIN")) {
                    this.checkPass = true;
                    this.testPass();
                    break;
                }
                if (data.subSequence(0, 12).equals("Chat:EBADPIN")) {
                    this.passwordLabel.setText("Invalid Password");
                    break;
                }
                break;
            }
            case '4': {
                if (data.charAt(0) == '-') {
                    this.statusLabel.setText("User count error");
                    break;
                }
                if (!Character.isLetterOrDigit(data.charAt(data.length() - 1))) {
                    data = data.substring(0, data.length() - 1);
                }
                this.statusLabel.setText(data + " live");
                break;
            }
        }
    }
    
    private static void specialCompare(final String s1, final String s2) {
        final int min = Math.min(s1.length(), s2.length());
        final int max = Math.max(s1.length(), s2.length());
        final boolean s1Longer = s1.length() > s2.length();
        for (int i = 0; i < min; ++i) {
            System.out.println(i + "  " + (int)s1.charAt(i) + "  " + (int)s2.charAt(i));
            if (s1.charAt(i) != s2.charAt(i)) {
                return;
            }
        }
        for (int i = min; i < max; ++i) {
            if (s1Longer) {}
        }
    }
    
    private String sanitizeString(final String s) {
        String ret = "";
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != '\0') {
                ret += s.charAt(i);
            }
        }
        return ret;
    }
    
    public void processRegistrationResponse(final String s) {
    }
    
    protected static ImageIcon createImageIcon(final String path, final String description) {
        final URL imgURL = Starphone.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        }
        System.err.println("Couldn't find file: " + path);
        return null;
    }
    
    public void actionPerformed(final ActionEvent e) {
        if (e.getActionCommand().equals("Answer")) {
            Starphone.userAnswer((short)jsListener.callNumber);
            jsListener.isCallActive = true;
            schedThread.nowPlayingTones = false;
        }
        else if (e.getActionCommand().equals("Hangup")) {
            this.statusLabel.setText("<html><font size=4>Hanging up " + jsListener.numDialed + "...</html>");
            if (jsListener.callNumber != -1) {
                Starphone.userHangup(jsListener.callNumber, 16, "Normal Clearing");
                jsListener.callNumber = -1;
                jsListener.isCallActive = false;
            }
        }
        else if (e.getActionCommand().equals("Mute")) {
            Starphone.setMute(jsListener.callNumber, true);
        }
        else if (e.getActionCommand().equals("Unmute")) {
            Starphone.setMute(jsListener.callNumber, false);
        }
        else if (e.getActionCommand().matches("[*#\\d]")) {
            if (this.statusLabel.getText().indexOf("html") > -1) {
                this.statusLabel.setText("");
            }
            this.statusLabel.setText(this.statusLabel.getText() + e.getActionCommand());
        }
        else {
            if (this.statusLabel.getText().equals("Welcome to Starphone!")) {
                this.statusLabel.setText("");
            }
            this.statusLabel.setText(this.statusLabel.getText() + e.getActionCommand());
        }
    }
    
    public void stateChanged(final ChangeEvent e) {
        if (jsListener.callNumber < 0) {
            return;
        }
        if (e.getSource().equals(this.speakerSlider)) {
            Starphone.setSpeaker(jsListener.callNumber, ((JSlider)e.getSource()).getValue());
        }
    }
    
    public void mouseClicked(final MouseEvent e) {
        if (e.getComponent().getName() == "play_btn" && e.getButton() == 1) {
            if (!jsListener.isPlaying) {
                this.playImageIcon.setImage(this.endImageIcon.getImage());
                String s = new String(jsListener.embedCall);
                if (s.startsWith("Chat=")) {
                    jsListener.chatDialed = s.substring(5);
                    jsListener.chatDialedPin = Starphone.readConf(s.substring(5));
                    if (!jsListener.chatDialedPin.equals("")) {
                        s = s + "&Pin=" + jsListener.chatDialedPin;
                    }
                }
                if (jsListener.callNumber != -1) {
                    Starphone.userHangup(jsListener.callNumber, 16, "Normal Clearing");
                    jsListener.callNumber = -1;
                    jsListener.isCallActive = false;
                }
                jsListener.isPlaying = true;
                this.testPass();
            }
            else {
                this.playImageIcon.setImage(this.playImageTemp.getImage());
                Starphone.userHangup(jsListener.callNumber, 16, "Normal Clearing");
                jsListener.callNumber = -1;
                jsListener.isPlaying = false;
            }
        }
        else if (e.getComponent().getName() == "mute_btn" && e.getButton() == 1) {
            if (jsListener.callNumber != -1) {
                int speakNum = 0;
                if (!this.isMuted) {
                    this.mute_btn.setIcon(this.loudImageIcon);
                }
                else {
                    this.mute_btn.setIcon(this.muteImageIcon);
                    speakNum = 50;
                }
                Starphone.setSpeaker(jsListener.callNumber, speakNum);
                this.isMuted = !this.isMuted;
            }
        }
        else if (e.getComponent().getName() == "loud_btn" && e.getButton() == 1) {
            Starphone.setMute(jsListener.callNumber, false);
        }
        else if (e.getComponent().getName() == "talkr_btn" && e.getButton() == 1) {
            final String urlStr = new String("http://new.talkr.com/?c=" + Starphone.chatName);
            BareBonesBrowserLaunch.openURL(urlStr);
        }
        else if (e.getComponent().getName() == "pass_btn" && e.getButton() == 1) {
            final String s = new String(this.passwordField.getPassword());
            final String temp = "Chat=" + Starphone.chatName + "&Pin=" + s;
            Starphone.genCall(temp);
        }
        else {
            System.out.println("Unhandled mouse click received from " + e.getSource());
            System.out.println(e.getComponent().getClass().toString());
        }
    }
    
    public void testPass() {
        if (!this.checkPass) {
            Starphone.genCall("Chat=" + Starphone.chatName);
        }
        else {
            final CardLayout cl = (CardLayout)this.mainHolder.getLayout();
            cl.show(this.mainHolder, "password");
        }
    }
    
    public void mousePressed(final MouseEvent e) {
    }
    
    public void mouseReleased(final MouseEvent e) {
    }
    
    public void mouseEntered(final MouseEvent e) {
    }
    
    public void mouseExited(final MouseEvent e) {
    }
    
    static {
        jsListener.embedCall = "Chat=";
        jsListener.pw1 = null;
        jsListener.clientImgPath = null;
        jsListener.numDialed = "";
        jsListener.chatDialed = "";
        jsListener.chatDialedPin = "";
        jsListener.callTimer = null;
        jsListener.callDuration = 0;
        jsListener.isCallActive = false;
        jsListener.callNumber = -1;
        jsListener.isPlaying = false;
    }
}
