import java.util.StringTokenizer;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Event;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Label;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class WvToolBarPanel extends Panel implements WvEventListener, WvUtilListener
{
    private Rectangle[][] rect;
    private WvDispatcher wvDispatcher;
    private WvImageButton fineButton;
    private WvImageButton roughButton;
    private boolean changeSizeReady;
    private Vector sizeVector;
    private int sizeIndex;
    private Label sizeLabel;
    private Dimension size;
    
    public void kickOff() {
        this.disable();
    }
    
    public void cameraConnected(final boolean flag) {
    }
    
    public void paint(final Graphics g) {
        final int i = this.size().height;
        final int j = this.size().width;
        g.setColor(Color.darkGray);
        g.drawLine(0, i - 2, j, i - 2);
        g.drawLine(0, 0, j, 0);
        g.setColor(Color.white);
        g.drawLine(0, i - 1, j, i - 1);
        g.drawLine(0, 1, j, 1);
    }
    
    public boolean mouseUp(final Event event, final int i, final int j) {
        this.fineButton.raised(true);
        this.roughButton.raised(true);
        if (this.changeSizeReady && event.target instanceof WvImageButton) {
            final WvImageButton wvimagebutton = (WvImageButton)event.target;
            if (!wvimagebutton.isEnabled()) {
                return false;
            }
            synchronized (this) {
                if (wvimagebutton == this.fineButton) {
                    --this.sizeIndex;
                }
                else {
                    ++this.sizeIndex;
                }
                this.sizeIndex = Math.max(0, Math.min(this.sizeVector.size() - 1, this.sizeIndex));
                this.fineButton.enable(this.sizeIndex > 0);
                this.roughButton.enable(this.sizeIndex < this.sizeVector.size() - 1);
                final String s = this.sizeVector.elementAt(this.sizeIndex);
                final String s2 = this.wvDispatcher.syncSendCommand(String.valueOf("ChangeImageSize".trim()) + "?image_size=" + s);
                this.wvDispatcher.putObject("image_size", "image_size=" + s);
                if (s2 != null) {
                    this.changeSizeReady = false;
                }
            }
        }
        return true;
    }
    
    public void disconnect(final int i) {
        this.fineButton.disable();
        this.roughButton.disable();
        this.disable();
    }
    
    public Dimension minimumSize() {
        return this.size;
    }
    
    public void videoConnected(final boolean flag) {
        if (flag) {
            this.getCaptureSizeInfo();
            this.enable();
            this.changeSizeReady = true;
        }
    }
    
    public synchronized void imageSizeChanged(final String s) {
        int i = 0;
        while (i < this.sizeVector.size()) {
            final String s2 = this.sizeVector.elementAt(i);
            if (!s2.equalsIgnoreCase(s)) {
                ++i;
            }
            else {
                if (i != this.sizeIndex) {
                    this.wvDispatcher.putObject("image_size", "image_size=" + s);
                    this.sizeIndex = i;
                    this.fineButton.enable(this.sizeIndex > 0);
                    this.roughButton.enable(this.sizeIndex < this.sizeVector.size() - 1);
                    break;
                }
                break;
            }
        }
        this.sizeLabel.setText(" " + s);
        this.changeSizeReady = true;
    }
    
    public void cameraPower(final boolean flag) {
    }
    
    public boolean mouseDown(final Event event, final int i, final int j) {
        if (event.target instanceof WvImageButton) {
            final WvImageButton wvimagebutton = (WvImageButton)event.target;
            if (!wvimagebutton.isEnabled()) {
                return false;
            }
            wvimagebutton.raised(false);
        }
        return true;
    }
    
    public Dimension preferredSize() {
        return this.size;
    }
    
    public WvToolBarPanel(final WvDispatcher wvdispatcher, final int i) {
        this.rect = new Rectangle[][] { { new Rectangle(349, 45, 19, 18), new Rectangle(369, 45, 19, 18) }, { new Rectangle(389, 45, 19, 18), new Rectangle(409, 45, 19, 18) } };
        this.sizeVector = new Vector();
        this.sizeLabel = new Label("         ");
        this.wvDispatcher = wvdispatcher;
        this.size = new Dimension(Math.max(i, 240) + 100, 32);
        this.roughButton = new WvImageButton(wvdispatcher, this.rect[0], true);
        this.fineButton = new WvImageButton(wvdispatcher, this.rect[1], true);
        final GridBagLayout gridbaglayout = new GridBagLayout();
        final GridBagConstraints gridbagconstraints = new GridBagConstraints();
        this.setLayout(gridbaglayout);
        gridbagconstraints.insets = new Insets(5, 10, 5, 0);
        gridbagconstraints.gridx = 0;
        gridbagconstraints.gridy = 0;
        gridbagconstraints.weightx = 0.0;
        gridbagconstraints.anchor = 17;
        gridbagconstraints.fill = 0;
        gridbaglayout.setConstraints(this.roughButton, gridbagconstraints);
        this.add(this.roughButton);
        gridbagconstraints.insets = new Insets(5, 1, 5, 0);
        gridbagconstraints.gridx = 1;
        gridbaglayout.setConstraints(this.fineButton, gridbagconstraints);
        this.add(this.fineButton);
        gridbagconstraints.insets = new Insets(5, 10, 5, 0);
        gridbagconstraints.gridx = 2;
        gridbagconstraints.weightx = 1.0;
        gridbagconstraints.fill = 2;
        gridbaglayout.setConstraints(this.sizeLabel, gridbagconstraints);
        this.add(this.sizeLabel);
        this.roughButton.disable();
        this.fineButton.disable();
    }
    
    private void getCaptureSizeInfo() {
        this.sizeVector.removeAllElements();
        String s = this.wvDispatcher.syncSendCommand(String.valueOf("GetCameraServerInfo".trim()) + "?item=image_sizes");
        if (s == null) {
            return;
        }
        final StringTokenizer stringtokenizer = new StringTokenizer(s, "\r\n=");
        while (stringtokenizer.hasMoreTokens()) {
            final String s2 = stringtokenizer.nextToken();
            if (s2.equals("image_size")) {
                this.sizeVector.addElement(stringtokenizer.nextToken());
            }
        }
        final int i = this.sizeVector.size();
        if (i > 0) {
            final int[] ai = new int[i];
            for (int j = 0; j < i; ++j) {
                String s3 = this.sizeVector.elementAt(j);
                final int j2 = s3.indexOf("x");
                s3 = s3.substring(0, j2);
                final int k1 = Integer.parseInt(s3);
                ai[j] = k1;
            }
            for (int l = 0; l < i - 1; ++l) {
                for (int i2 = i - 1; i2 > l; --i2) {
                    if (ai[i2 - 1] < ai[i2]) {
                        final String s4 = this.sizeVector.elementAt(i2);
                        this.sizeVector.setElementAt(this.sizeVector.elementAt(i2 - 1), i2);
                        this.sizeVector.setElementAt(s4, i2 - 1);
                    }
                }
            }
        }
        String s5 = "320";
        s = this.wvDispatcher.syncSendCommand("GetVideoInfo".trim());
        if (s == null) {
            return;
        }
        final StringTokenizer stringtokenizer2 = new StringTokenizer(s, "\r\n=");
        while (stringtokenizer2.hasMoreTokens()) {
            final String s6 = stringtokenizer2.nextToken();
            if (s6.equals("image_width")) {
                s5 = stringtokenizer2.nextToken();
                break;
            }
        }
        for (int m = 0; m < this.sizeVector.size(); ++m) {
            final String s7 = this.sizeVector.elementAt(m);
            if (s7.startsWith(s5)) {
                this.sizeIndex = m;
                this.wvDispatcher.putObject("image_size", "image_size=" + s7);
                break;
            }
        }
        this.fineButton.enable(this.sizeIndex > 0);
        this.roughButton.enable(this.sizeIndex < this.sizeVector.size() - 1);
        if (this.sizeVector.size() > 0) {
            this.sizeLabel.setText(" " + this.sizeVector.elementAt(this.sizeIndex));
            return;
        }
        this.sizeLabel.setText(" ");
    }
    
    public void connect(final String s) {
        this.repaint();
    }
}
