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
    
    public void cameraConnected(final boolean b) {
    }
    
    public void paint(final Graphics graphics) {
        final int height = this.size().height;
        final int width = this.size().width;
        graphics.setColor(Color.darkGray);
        graphics.drawLine(0, height - 2, width, height - 2);
        graphics.drawLine(0, 0, width, 0);
        graphics.setColor(Color.white);
        graphics.drawLine(0, height - 1, width, height - 1);
        graphics.drawLine(0, 1, width, 1);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.fineButton.raised(true);
        this.roughButton.raised(true);
        if (this.changeSizeReady && event.target instanceof WvImageButton) {
            final WvImageButton wvImageButton = (WvImageButton)event.target;
            if (!wvImageButton.isEnabled()) {
                return false;
            }
            synchronized (this) {
                if (wvImageButton == this.fineButton) {
                    --this.sizeIndex;
                }
                else {
                    ++this.sizeIndex;
                }
                this.sizeIndex = Math.max(0, Math.min(this.sizeVector.size() - 1, this.sizeIndex));
                this.fineButton.enable(this.sizeIndex > 0);
                this.roughButton.enable(this.sizeIndex < this.sizeVector.size() - 1);
                final String s = this.sizeVector.elementAt(this.sizeIndex);
                final String syncSendCommand = this.wvDispatcher.syncSendCommand("ChangeImageSize".trim() + "?image_size=" + s);
                this.wvDispatcher.putObject("image_size", "image_size=" + s);
                if (syncSendCommand != null) {
                    this.changeSizeReady = false;
                }
            }
        }
        return true;
    }
    
    public void disconnect(final int n) {
        this.fineButton.disable();
        this.roughButton.disable();
        this.disable();
    }
    
    public Dimension minimumSize() {
        return this.size;
    }
    
    public void videoConnected(final boolean b) {
        if (b) {
            this.getCaptureSizeInfo();
            this.enable();
            this.changeSizeReady = true;
        }
    }
    
    public synchronized void imageSizeChanged(final String s) {
        int i = 0;
        while (i < this.sizeVector.size()) {
            if (((String)this.sizeVector.elementAt(i)).equalsIgnoreCase(s)) {
                if (i != this.sizeIndex) {
                    this.wvDispatcher.putObject("image_size", "image_size=" + s);
                    this.sizeIndex = i;
                    this.fineButton.enable(this.sizeIndex > 0);
                    this.roughButton.enable(this.sizeIndex < this.sizeVector.size() - 1);
                    break;
                }
                break;
            }
            else {
                ++i;
            }
        }
        this.sizeLabel.setText(" " + s);
        this.changeSizeReady = true;
    }
    
    public void cameraPower(final boolean b) {
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (event.target instanceof WvImageButton) {
            final WvImageButton wvImageButton = (WvImageButton)event.target;
            if (!wvImageButton.isEnabled()) {
                return false;
            }
            wvImageButton.raised(false);
        }
        return true;
    }
    
    public Dimension preferredSize() {
        return this.size;
    }
    
    public WvToolBarPanel(final WvDispatcher wvDispatcher, final int n) {
        this.rect = new Rectangle[][] { { new Rectangle(349, 45, 19, 18), new Rectangle(369, 45, 19, 18) }, { new Rectangle(389, 45, 19, 18), new Rectangle(409, 45, 19, 18) } };
        this.sizeVector = new Vector();
        this.sizeLabel = new Label("         ");
        this.wvDispatcher = wvDispatcher;
        this.size = new Dimension(Math.max(n, 240) + 100, 32);
        this.roughButton = new WvImageButton(wvDispatcher, this.rect[0], true);
        this.fineButton = new WvImageButton(wvDispatcher, this.rect[1], true);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(5, 10, 5, 0);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.fill = 0;
        layout.setConstraints(this.roughButton, gridBagConstraints);
        this.add(this.roughButton);
        gridBagConstraints.insets = new Insets(5, 1, 5, 0);
        gridBagConstraints.gridx = 1;
        layout.setConstraints(this.fineButton, gridBagConstraints);
        this.add(this.fineButton);
        gridBagConstraints.insets = new Insets(5, 10, 5, 0);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 2;
        layout.setConstraints(this.sizeLabel, gridBagConstraints);
        this.add(this.sizeLabel);
        this.roughButton.disable();
        this.fineButton.disable();
    }
    
    private void getCaptureSizeInfo() {
        this.sizeVector.removeAllElements();
        final String syncSendCommand = this.wvDispatcher.syncSendCommand("GetCameraServerInfo".trim() + "?item=image_sizes");
        if (syncSendCommand == null) {
            return;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(syncSendCommand, "\r\n=");
        while (stringTokenizer.hasMoreTokens()) {
            if (stringTokenizer.nextToken().equals("image_size")) {
                this.sizeVector.addElement(stringTokenizer.nextToken());
            }
        }
        final int size = this.sizeVector.size();
        if (size > 0) {
            final int[] array = new int[size];
            for (int i = 0; i < size; ++i) {
                final String s = this.sizeVector.elementAt(i);
                array[i] = Integer.parseInt(s.substring(0, s.indexOf("x")));
            }
            for (int j = 0; j < size - 1; ++j) {
                for (int k = size - 1; k > j; --k) {
                    if (array[k - 1] < array[k]) {
                        final String s2 = this.sizeVector.elementAt(k);
                        this.sizeVector.setElementAt(this.sizeVector.elementAt(k - 1), k);
                        this.sizeVector.setElementAt(s2, k - 1);
                    }
                }
            }
        }
        String nextToken = "320";
        final String syncSendCommand2 = this.wvDispatcher.syncSendCommand("GetVideoInfo".trim());
        if (syncSendCommand2 == null) {
            return;
        }
        final StringTokenizer stringTokenizer2 = new StringTokenizer(syncSendCommand2, "\r\n=");
        while (stringTokenizer2.hasMoreTokens()) {
            if (stringTokenizer2.nextToken().equals("image_width")) {
                nextToken = stringTokenizer2.nextToken();
                break;
            }
        }
        for (int l = 0; l < this.sizeVector.size(); ++l) {
            final String s3 = this.sizeVector.elementAt(l);
            if (s3.startsWith(nextToken)) {
                this.sizeIndex = l;
                this.wvDispatcher.putObject("image_size", "image_size=" + s3);
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
