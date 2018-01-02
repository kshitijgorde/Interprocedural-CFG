// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.overlays;

import javax.swing.SwingUtilities;
import com.masystem.beergame.ui.animation.NodeAnimation;
import com.masystem.beergame.ui.animation.AlphaAnimation;
import com.masystem.beergame.MouseBlocker;
import com.masystem.beergame.ui.graphics.StretchableImage;
import com.masystem.beergame.ui.scene.Node;
import com.masystem.beergame.ui.graphics.GraphicsTools;
import java.awt.Color;
import com.masystem.beergame.resource.ResourceManager;
import java.awt.Font;
import com.masystem.beergame.ui.component.ImageNode;
import com.masystem.beergame.ui.beergamecomponents.BeerGameTextButton;
import com.masystem.beergame.ui.beergamecomponents.BeerGameTextLabel;
import com.masystem.beergame.ui.component.Panel;

public abstract class MessageOverlay extends Panel
{
    private BeerGameTextLabel headlineLabel;
    private BeerGameTextLabel messageLabel;
    private BeerGameTextButton rightButton;
    private Panel content;
    protected ImageNode screenshot;
    private Thread thread;
    private boolean open;
    private boolean taskFinished;
    private boolean animationFinished;
    protected final boolean autoFinish;
    
    public MessageOverlay() {
        this(null, null);
    }
    
    private MessageOverlay(final String s, final String s2) {
        this(null, null, null, true);
    }
    
    public MessageOverlay(final String s, final String s2, final String s3) {
        this(s, s2, s3, true);
    }
    
    public MessageOverlay(final String s, final String s2, final String s3, final boolean autoFinish) {
        this.content = new Panel();
        if (s != null) {
            (this.headlineLabel = new BeerGameTextLabel(s)).setFont((Font)ResourceManager.getResource("OverlayBigFont"));
            this.headlineLabel.setTextColor(Color.WHITE);
        }
        if (s2 != null) {
            (this.messageLabel = new BeerGameTextLabel(s2)).setFont((Font)ResourceManager.getResource("OverlaySmallFont"));
            this.messageLabel.setTextColor(Color.WHITE);
        }
        if (s3 != null) {
            (this.rightButton = new BeerGameTextButton(s3)).setFont((Font)ResourceManager.getResource("BigFont"));
        }
        this.autoFinish = autoFinish;
    }
    
    public final void setMessage(final String text, final String text2) {
        if (this.headlineLabel != null) {
            this.headlineLabel.setText(text);
        }
        if (this.messageLabel != null) {
            this.messageLabel.setText(text2);
        }
    }
    
    public final void setRightButtonLabel(final String text) {
        if (this.rightButton != null) {
            this.rightButton.setText(text);
            this.rightButton.setVisible(true);
        }
    }
    
    public final BeerGameTextButton getRightButton() {
        return this.rightButton;
    }
    
    @Override
    public void onSetup() {
        final boolean visible = this.isVisible();
        this.setVisible(false);
        final int n = (int)this.parent.getWidth();
        final int n2 = (int)this.parent.getHeight();
        final ImageNode imageNode = new ImageNode(GraphicsTools.createScaled(GraphicsTools.blur(GraphicsTools.createScaled(this.parent.toImage(), n / 8, n2 / 8), 2, 2, 0.5), n, n2));
        this.setVisible(visible);
        this.addChild(imageNode);
        (this.thread = new Thread(new Runnable() {
            @Override
            public final void run() {
                MessageOverlay.this.perform();
                if (MessageOverlay.this.autoFinish) {
                    MessageOverlay.this.setTaskFinished(true);
                }
            }
        })).setDaemon(true);
        this.thread.setName(this.getClass() + "-" + this.thread.getName());
        this.thread.start();
        this.addChild(this.content);
        if (this.headlineLabel != null) {
            this.content.addChild(this.headlineLabel);
            StretchableImage.vertical(this.headlineLabel, 32, this, 17);
        }
        if (this.messageLabel != null) {
            this.content.addChild(this.messageLabel);
            StretchableImage.vertical(this.messageLabel, 16, this, 17);
        }
        if (this.rightButton != null) {
            this.content.addChild(this.rightButton);
            StretchableImage.vertical(this.rightButton, 32, this, 32, this, -0.05f);
            StretchableImage.horizontal(this.rightButton, 2, this, 2, this, -0.04f);
        }
    }
    
    public final synchronized void open() {
        if (!this.open) {
            this.open = true;
            MouseBlocker.block();
            final AlphaAnimation alphaAnimation;
            (alphaAnimation = new AlphaAnimation(this, 0.0f, 0.99999f)).setListener(new NodeAnimation.Adapter() {
                @Override
                public final void onAnimationEnd() {
                    MessageOverlay.this.setOpaque(true);
                    MessageOverlay.this.setAnimationFinished(true);
                    MouseBlocker.unblock();
                    final MessageOverlay this$0 = MessageOverlay.this;
                }
            });
            alphaAnimation.start();
        }
    }
    
    public final synchronized void close() {
        if (this.open) {
            this.open = false;
            MouseBlocker.block();
            this.onClosing();
            this.setOpaque(false);
            final AlphaAnimation alphaAnimation;
            (alphaAnimation = new AlphaAnimation(this, 0.99999f, 0.0f)).setListener(new NodeAnimation.Adapter() {
                @Override
                public final void onAnimationEnd() {
                    MouseBlocker.unblock();
                    MessageOverlay.this.onClosed();
                }
            });
            alphaAnimation.start();
        }
    }
    
    public final void prepareOverlayUpdate() {
        this.screenshot = new ImageNode(this.toImage());
    }
    
    public final void updateOverlayContents() {
        MouseBlocker.block();
        this.addChild(this.screenshot);
        final AlphaAnimation alphaAnimation;
        (alphaAnimation = new AlphaAnimation(this.screenshot, 1.0f, 0.0f)).setListener(new NodeAnimation.Adapter() {
            @Override
            public final void onAnimationEnd() {
                MessageOverlay.this.removeChild(MessageOverlay.this.screenshot);
                MouseBlocker.unblock();
            }
        });
        alphaAnimation.start();
    }
    
    public final synchronized void setAnimationFinished(final boolean b) {
        this.animationFinished = true;
        this.notifyFinished();
    }
    
    public final synchronized void setTaskFinished(final boolean b) {
        this.taskFinished = true;
        this.notifyFinished();
    }
    
    private void notifyFinished() {
        if (this.taskFinished && this.animationFinished) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public final void run() {
                    MessageOverlay.this.finish();
                }
            });
        }
    }
    
    protected final void perform() {
        this.onPerform();
    }
    
    protected final void finish() {
        this.onFinish();
    }
    
    protected void onClosing() {
    }
    
    protected void onClosed() {
    }
    
    protected abstract void onPerform();
    
    protected abstract void onFinish();
}
