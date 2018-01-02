// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.pages.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.masystem.beergame.ui.animation.NodeAnimation;
import com.masystem.beergame.ui.scene.Node;
import com.masystem.beergame.ui.animation.AlphaAnimation;
import java.util.ArrayDeque;
import javax.swing.Timer;
import java.util.Queue;
import com.masystem.beergame.ui.beergamecomponents.BeerGameTextLabel;

public final class NotificationLabel extends BeerGameTextLabel
{
    private final Queue<Notification> notificationList;
    private Notification currentNotification;
    private String currentMessage;
    private Timer timer;
    
    public NotificationLabel() {
        this.notificationList = new ArrayDeque<Notification>();
    }
    
    public final void setMessage(final String currentMessage) {
        synchronized (this.notificationList) {
            this.currentMessage = currentMessage;
            this.notificationList.offer(new Notification(currentMessage, -1));
            if (this.currentNotification == null) {
                this.displayNextNotification();
            }
        }
    }
    
    protected final void displayNextNotification() {
        synchronized (this.notificationList) {
            this.currentNotification = this.notificationList.poll();
            if (this.currentNotification != null) {
                final AlphaAnimation alphaAnimation;
                (alphaAnimation = new AlphaAnimation(this, 0.99999f, 0.0f)).setListener(new NodeAnimation.Adapter() {
                    @Override
                    public final void onAnimationEnd() {
                        NotificationLabel.this.setText(NotificationLabel.this.currentNotification.getText());
                        NotificationLabel.access$100(NotificationLabel.this);
                    }
                });
                alphaAnimation.start();
            }
            else if (this.currentMessage != null) {
                this.notificationList.offer(new Notification(this.currentMessage, -1));
                this.displayNextNotification();
            }
        }
    }
    
    static /* synthetic */ void access$100(NotificationLabel notificationLabel) {
        (notificationLabel = notificationLabel).setAlpha(1.0f);
        final AlphaAnimation alphaAnimation;
        (alphaAnimation = new AlphaAnimation(notificationLabel, 0.0f, 0.99999f)).setListener(new NodeAnimation.Adapter() {
            @Override
            public final void onAnimationEnd() {
                synchronized (NotificationLabel.this.notificationList) {
                    if (NotificationLabel.this.currentNotification.getDuration() == -1) {
                        NotificationLabel.this.timer = new Timer(1000, new ActionListener() {
                            @Override
                            public final void actionPerformed(final ActionEvent actionEvent) {
                                synchronized (NotificationLabel.this.notificationList) {
                                    if (NotificationLabel.this.notificationList.size() > 0) {
                                        NotificationLabel.this.displayNextNotification();
                                    }
                                    else {
                                        NotificationLabel.access$002(NotificationLabel.this, null);
                                    }
                                }
                            }
                        });
                    }
                    else {
                        NotificationLabel.this.timer = new Timer(NotificationLabel.this.currentNotification.getDuration(), new ActionListener() {
                            @Override
                            public final void actionPerformed(final ActionEvent actionEvent) {
                                NotificationLabel.this.displayNextNotification();
                            }
                        });
                    }
                    NotificationLabel.this.timer.setRepeats(false);
                    NotificationLabel.this.timer.start();
                }
            }
        });
        alphaAnimation.start();
    }
    
    static /* synthetic */ Notification access$002(final NotificationLabel notificationLabel, final Notification notification) {
        return notificationLabel.currentNotification = null;
    }
    
    public static final class Notification
    {
        private final String text;
        private final int duration;
        
        public Notification(final String text, final int n) {
            this.text = text;
            this.duration = -1;
        }
        
        public final String getText() {
            return this.text;
        }
        
        public final int getDuration() {
            return this.duration;
        }
    }
}
