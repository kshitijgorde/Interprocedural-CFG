import java.awt.Event;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.applet.Applet;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Point;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class LiveApplet extends WvAppletTemplate
{
    private WvCardLayoutPanel wvCardLayoutPanel;
    private WvPanoramaCanvas wvPanoramaCanvas;
    private WvPanoramaButton wvPanoramaButton;
    private Frame wvPanoramaFrame;
    private WvLiveCanvas wvLiveCanvas;
    
    public void postInit() {
        int i = 320;
        int j = 240;
        String s;
        if ((s = this.getParameter("video_width")) != null) {
            try {
                final int k = Integer.parseInt(s);
                i = Math.min(768, Math.max(80, k));
                j = i * 3 / 4;
            }
            catch (Exception ex) {}
        }
        if ((s = this.getParameter("click_action")) != null) {
            super.wvDispatcher.putObject("click_action", s);
        }
        if ((s = this.getParameter("auto_connect")) != null) {
            super.wvDispatcher.putObject("auto_connect", new Boolean(!s.equalsIgnoreCase("off")));
        }
        if ((s = this.getParameter("no_connect_msg")) != null) {
            super.wvDispatcher.putObject("no_connect_msg", s);
        }
        if ((s = this.getParameter("purejava")) != null) {
            super.wvDispatcher.putObject("bPureJava", new Boolean(s.equalsIgnoreCase("on")));
        }
        if ((s = this.getParameter("connect_msg")) != null) {
            super.wvDispatcher.putObject("connect_msg", s);
        }
        if ((s = this.getParameter("error_msg")) != null) {
            super.wvDispatcher.putObject("error_msg", s);
        }
        if ((s = this.getParameter("disconnect_msg")) != null) {
            super.wvDispatcher.putObject("disconnect_msg", s);
        }
        if ((s = this.getParameter("too_many_msg")) != null) {
            super.wvDispatcher.putObject("too_many_msg", s);
        }
        if ((s = this.getParameter("no_control_msg")) != null) {
            super.wvDispatcher.putObject("no_control_msg", s);
        }
        if ((s = this.getParameter("camera_off_msg")) != null) {
            super.wvDispatcher.putObject("camera_off_msg", s);
        }
        if ((s = this.getParameter("no_privilege_msg")) != null) {
            super.wvDispatcher.putObject("no_privilege_msg", s);
        }
        if ((s = this.getParameter("push_control_msg")) != null) {
            super.wvDispatcher.putObject("push_control_msg", s);
        }
        if ((s = this.getParameter("alt_push_control_msg")) != null) {
            super.wvDispatcher.putObject("alt_push_control_msg", s);
        }
        if ((s = this.getParameter("cam_switch_msg")) != null) {
            super.wvDispatcher.putObject("cam_switch_msg", s);
        }
        if ((s = this.getParameter("font_size")) != null) {
            try {
                final Integer integer = new Integer(s);
                super.wvDispatcher.putObject("font_size", integer);
            }
            catch (NumberFormatException ex2) {}
        }
        if ((s = this.getParameter("overlay_font_color")) != null) {
            try {
                final int l = Integer.parseInt(s.substring(1, 3), 16);
                final int i2 = Integer.parseInt(s.substring(3, 5), 16);
                final int j2 = Integer.parseInt(s.substring(5, 7), 16);
                super.wvDispatcher.putObject("font_color", new Color(l, i2, j2));
            }
            catch (Exception ex3) {}
        }
        String s2 = null;
        if ((s = this.getParameter("capture_size")) != null && ("640x480".equals(s) || "320x240".equals(s) || "160x120".equals(s) || "80x60".equals(s) || "768x576".equals(s) || "384x288".equals(s) || "192x144".equals(s) || "96x72".equals(s))) {
            s2 = s;
        }
        super.wvDispatcher.putObject("captureSize", s2);
        String s3 = null;
        Point point = null;
        if ((s = this.getParameter("superimpose")) != null) {
            try {
                final StringTokenizer stringtokenizer = new StringTokenizer(s, ", ");
                s3 = stringtokenizer.nextToken();
                final int k2 = Integer.parseInt(stringtokenizer.nextToken());
                final int l2 = Integer.parseInt(stringtokenizer.nextToken());
                point = new Point(k2, l2);
            }
            catch (Exception ex4) {}
        }
        final String[] as = { this.getParameter("open_image"), this.getParameter("close_image"), s3 };
        final Image[] aimage = new Image[as.length];
        final MediaTracker mediatracker = new MediaTracker(this);
        for (int i3 = 0; i3 < as.length; ++i3) {
            if (as[i3] != null) {
                mediatracker.addImage(aimage[i3] = this.getImage(this.getCodeBase(), as[i3]), i3);
            }
        }
        try {
            while (!mediatracker.checkAll()) {
                mediatracker.waitForAll();
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        for (int j3 = 0; j3 < as.length; ++j3) {
            if (aimage[j3] != null && mediatracker.isErrorID(j3)) {
                aimage[j3] = null;
            }
        }
        if (aimage[0] != null) {
            super.wvDispatcher.putObject("openImage", aimage[0]);
        }
        if (aimage[1] != null) {
            super.wvDispatcher.putObject("closeImage", aimage[1]);
        }
        if (aimage[2] != null) {
            super.wvDispatcher.putObject("imposeImage", aimage[2]);
        }
        if (point != null) {
            super.wvDispatcher.putObject("imposePos", point);
        }
        final boolean flag = false;
        if ((s = this.getParameter("open_image_time")) != null) {
            try {
                final int k3 = (int)Math.max(0L, Math.min(30000L, Long.parseLong(s)));
                super.wvDispatcher.putObject("openImageTime", new Integer(k3));
            }
            catch (Exception exception2) {
                System.out.println(this + " " + exception2);
            }
        }
        super.wvDispatcher.putObject("hostname", this.getParameter("hostname"));
        super.wvDispatcher.putObject("comment", this.getParameter("comment"));
        for (int l3 = 0; l3 < WvLiveCanvas.overlayName.length; ++l3) {
            final String s4 = "show_" + WvLiveCanvas.overlayName[l3];
            final String s5 = this.getParameter(s4);
            if (s5 != null && s5.equalsIgnoreCase("on")) {
                super.wvDispatcher.putObject(s4, new Boolean(true));
            }
        }
        super.wvDispatcher.initVideoState();
        if ("false".equalsIgnoreCase(this.getParameter("controllable"))) {
            super.wvDispatcher.putObject("controllable", new Boolean(false));
            this.wvLiveCanvas = new WvLiveCanvas(super.wvDispatcher, this, i, j);
            super.wvDispatcher.addWvEventListener(this.wvLiveCanvas);
            final GridBagLayout gridbaglayout = new GridBagLayout();
            final GridBagConstraints gridbagconstraints = new GridBagConstraints();
            this.setLayout(gridbaglayout);
            gridbagconstraints.fill = 0;
            gridbagconstraints.anchor = 10;
            gridbaglayout.setConstraints(this.wvLiveCanvas, gridbagconstraints);
            this.add(this.wvLiveCanvas);
            return;
        }
        boolean flag2 = true;
        if ("embed".equalsIgnoreCase(this.getParameter("gui_type"))) {
            flag2 = false;
        }
        final Rectangle[][] arectangle = { { new Rectangle(0, 0, 450, 40) }, { new Rectangle(0, 45, 80, 27) }, { new Rectangle(90, 45, 20, 20), new Rectangle(110, 45, 20, 20) }, { new Rectangle(130, 45, 18, 18), new Rectangle(150, 45, 18, 18) }, { new Rectangle(170, 45, 18, 18), new Rectangle(190, 45, 18, 18) }, { new Rectangle(231, 44, 22, 22), new Rectangle(261, 44, 22, 22), new Rectangle(291, 44, 22, 22), new Rectangle(321, 44, 22, 22) } };
        Image image = null;
        final String s6 = this.getParameter("controller_style");
        super.wvDispatcher.putObject("controllpresetonly", s6);
        if ((s = this.getParameter("bg_image")) != null) {
            try {
                WvDebug.println(this.getCodeBase() + s);
                image = this.getImage(this.getCodeBase(), s);
                mediatracker.addImage(image, 0);
                while (!mediatracker.checkAll()) {
                    mediatracker.waitForAll();
                }
                if (image.getWidth(null) <= 0 || image.getHeight(null) <= 0) {
                    WvDebug.println("background image did not load.");
                    image = null;
                }
            }
            catch (Exception exception3) {
                System.out.println(this + " " + exception3);
                image = null;
            }
        }
        final WvCameraChoice wvcamerachoice = new WvCameraChoice(super.wvDispatcher);
        this.wvCardLayoutPanel = new WvCardLayoutPanel(super.wvDispatcher);
        super.wvDispatcher.addWvEventListener(wvcamerachoice);
        super.wvDispatcher.addWvEventListener(this.wvCardLayoutPanel);
        if ("none".equalsIgnoreCase(s6)) {
            this.wvLiveCanvas = new WvLiveCanvas(super.wvDispatcher, this, i, j);
            super.wvDispatcher.addWvEventListener(this.wvLiveCanvas);
            final GridBagLayout gridbaglayout2 = new GridBagLayout();
            final GridBagConstraints gridbagconstraints2 = new GridBagConstraints();
            this.setLayout(gridbaglayout2);
            gridbagconstraints2.fill = 0;
            gridbagconstraints2.anchor = 10;
            gridbaglayout2.setConstraints(this.wvLiveCanvas, gridbagconstraints2);
            this.add(this.wvLiveCanvas);
            final WvTimeIndicator wvtimeindicator = new WvTimeIndicator(super.wvDispatcher);
            super.wvDispatcher.addWvEventListener(wvtimeindicator);
            return;
        }
        if ("preset_only".equalsIgnoreCase(s6)) {
            WvImageButton wvimagebutton;
            if (image == null) {
                wvimagebutton = new WvImageButton(super.wvDispatcher, arectangle[1], false);
            }
            else {
                wvimagebutton = new WvImageButton(super.wvDispatcher, image, false, null);
            }
            final Image image2 = super.wvDispatcher.getIconImage(arectangle[0][0]);
            final WvControlButton wvcontrolbutton = new WvControlButton(super.wvDispatcher, image2);
            this.wvLiveCanvas = new WvLiveCanvas(super.wvDispatcher, this, i, j);
            final WvTimeIndicator wvtimeindicator2 = new WvTimeIndicator(super.wvDispatcher);
            final WvToolBarPanel wvtoolbarpanel = new WvToolBarPanel(super.wvDispatcher, i);
            final WvCaptureButton wvcapturebutton1 = new WvCaptureButton(super.wvDispatcher, arectangle[2], this);
            super.wvDispatcher.addWvEventListener(this.wvLiveCanvas);
            super.wvDispatcher.addWvEventListener(wvtimeindicator2);
            super.wvDispatcher.addWvEventListener(wvcontrolbutton);
            super.wvDispatcher.addWvEventListener(wvtoolbarpanel);
            super.wvDispatcher.addWvEventListener(wvcapturebutton1);
            final GridBagLayout gridbaglayout3 = new GridBagLayout();
            final GridBagConstraints gridbagconstraints3 = new GridBagConstraints();
            Point point2 = new Point(335, 0);
            s = this.getParameter("image_offset");
            if (image != null && s != null) {
                try {
                    final StringTokenizer stringtokenizer2 = new StringTokenizer(s, ", ");
                    final int i4 = Integer.parseInt(stringtokenizer2.nextToken());
                    final int k4 = Integer.parseInt(stringtokenizer2.nextToken());
                    point2 = new Point(i4, k4);
                }
                catch (Exception ex5) {}
            }
            final WvImagePanel wvimagepanel = new WvImagePanel(image, point2);
            wvimagepanel.setLayout(gridbaglayout3);
            if ("on".equalsIgnoreCase(this.getParameter("show_toolbar"))) {
                gridbagconstraints3.insets = new Insets(7, 0, 0, 0);
                gridbagconstraints3.gridx = 0;
                gridbagconstraints3.gridy = 0;
                gridbagconstraints3.gridwidth = 1;
                gridbagconstraints3.gridheight = 1;
                gridbagconstraints3.fill = 0;
                gridbagconstraints3.anchor = 10;
                gridbaglayout3.setConstraints(wvtoolbarpanel, gridbagconstraints3);
                this.add(wvtoolbarpanel);
            }
            gridbagconstraints3.insets = new Insets(7, 7, 0, 0);
            gridbagconstraints3.gridx = 0;
            gridbagconstraints3.gridy = 0;
            if (!"off".equalsIgnoreCase(this.getParameter("show_capture"))) {
                gridbagconstraints3.gridwidth = 2;
                gridbagconstraints3.gridheight = 1;
            }
            else {
                gridbagconstraints3.gridwidth = 1;
                gridbagconstraints3.gridheight = 1;
            }
            gridbagconstraints3.weightx = 0.0;
            gridbagconstraints3.fill = 2;
            gridbagconstraints3.anchor = 10;
            gridbaglayout3.setConstraints(this.wvLiveCanvas, gridbagconstraints3);
            wvimagepanel.add(this.wvLiveCanvas);
            if (image == null && !"off".equalsIgnoreCase(this.getParameter("show_logo"))) {
                gridbagconstraints3.insets = new Insets(7, 7, 0, 0);
                gridbagconstraints3.gridx = gridbagconstraints3.gridwidth;
                gridbagconstraints3.gridy = 0;
                gridbagconstraints3.gridwidth = 1;
                gridbagconstraints3.gridheight = 1;
                gridbagconstraints3.fill = 2;
                gridbagconstraints3.anchor = 11;
                gridbaglayout3.setConstraints(wvimagebutton, gridbagconstraints3);
                wvimagepanel.add(wvimagebutton);
            }
            int j4 = 0;
            if (!"off".equalsIgnoreCase(this.getParameter("show_capture"))) {
                gridbagconstraints3.insets = new Insets(7, 7, 0, 0);
                gridbagconstraints3.gridx = j4++;
                gridbagconstraints3.gridy = 2;
                gridbagconstraints3.gridwidth = 1;
                gridbagconstraints3.gridheight = 1;
                gridbagconstraints3.fill = 2;
                gridbagconstraints3.anchor = 13;
                gridbaglayout3.setConstraints(wvcapturebutton1, gridbagconstraints3);
                wvimagepanel.add(wvcapturebutton1);
            }
            gridbagconstraints3.insets = new Insets(7, 7, 0, 0);
            gridbagconstraints3.gridx = j4++;
            gridbagconstraints3.gridy = 2;
            gridbagconstraints3.gridwidth = 1;
            gridbagconstraints3.gridheight = 1;
            gridbagconstraints3.fill = 2;
            gridbagconstraints3.anchor = 10;
            gridbaglayout3.setConstraints(this.wvCardLayoutPanel, gridbagconstraints3);
            wvimagepanel.add(this.wvCardLayoutPanel);
            gridbagconstraints3.insets = new Insets(7, 7, 0, 0);
            gridbagconstraints3.gridx = j4;
            gridbagconstraints3.gridy = 2;
            gridbagconstraints3.anchor = 10;
            gridbaglayout3.setConstraints(wvcontrolbutton, gridbagconstraints3);
            wvimagepanel.add(wvcontrolbutton);
            gridbagconstraints3.insets = new Insets(7, 7, 0, 0);
            gridbagconstraints3.gridx = j4;
            gridbagconstraints3.gridy = 0;
            gridbagconstraints3.anchor = 15;
            gridbaglayout3.setConstraints(wvtimeindicator2, gridbagconstraints3);
            wvimagepanel.add(wvtimeindicator2);
            this.setLayout(gridbaglayout3);
            gridbagconstraints3.gridx = 0;
            gridbagconstraints3.gridy = 1;
            gridbagconstraints3.fill = 0;
            gridbagconstraints3.anchor = 10;
            gridbaglayout3.setConstraints(wvimagepanel, gridbagconstraints3);
            this.add(wvimagepanel);
            return;
        }
        WvImageButton wvimagebutton2;
        if (image == null) {
            wvimagebutton2 = new WvImageButton(super.wvDispatcher, arectangle[1], false);
        }
        else {
            wvimagebutton2 = new WvImageButton(super.wvDispatcher, image, false, new Dimension(80, 27));
        }
        final Image image3 = super.wvDispatcher.getIconImage(arectangle[0][0]);
        final WvControlButton wvcontrolbutton2 = new WvControlButton(super.wvDispatcher, image3);
        final WvCaptureButton wvcapturebutton2 = new WvCaptureButton(super.wvDispatcher, arectangle[2], this);
        final WvImageButton wvimagebutton3 = new WvImageButton(super.wvDispatcher, arectangle[3], false);
        final WvImageButton wvimagebutton4 = new WvImageButton(super.wvDispatcher, arectangle[4], false);
        final WvBackLightButton wvbacklightbutton = new WvBackLightButton(super.wvDispatcher, arectangle[5]);
        this.wvLiveCanvas = new WvLiveCanvas(super.wvDispatcher, this, i, j);
        final WvTimeIndicator wvtimeindicator3 = new WvTimeIndicator(super.wvDispatcher);
        this.wvPanoramaButton = new WvPanoramaButton();
        final WvScrollbar wvscrollbar = new WvScrollbar(super.wvDispatcher, 0, 0);
        final WvScrollbar wvscrollbar2 = new WvScrollbar(super.wvDispatcher, 1, 1);
        final WvScrollbar wvscrollbar3 = new WvScrollbar(super.wvDispatcher, 1, 2);
        final WvToolBarPanel wvtoolbarpanel2 = new WvToolBarPanel(super.wvDispatcher, i);
        this.wvPanoramaCanvas = new WvPanoramaCanvas(super.wvDispatcher);
        super.wvDispatcher.addWvEventListener(this.wvLiveCanvas);
        super.wvDispatcher.addWvEventListener(wvtimeindicator3);
        super.wvDispatcher.addWvEventListener(wvcontrolbutton2);
        super.wvDispatcher.addWvEventListener(wvbacklightbutton);
        super.wvDispatcher.addWvEventListener(this.wvPanoramaButton);
        super.wvDispatcher.addWvEventListener(wvimagebutton3);
        super.wvDispatcher.addWvEventListener(wvimagebutton4);
        super.wvDispatcher.addWvEventListener(wvscrollbar);
        super.wvDispatcher.addWvEventListener(wvscrollbar2);
        super.wvDispatcher.addWvEventListener(wvscrollbar3);
        super.wvDispatcher.addWvEventListener(wvcapturebutton2);
        super.wvDispatcher.addWvEventListener(this.wvPanoramaCanvas);
        super.wvDispatcher.addWvEventListener(wvtoolbarpanel2);
        GridBagLayout gridbaglayout4 = new GridBagLayout();
        GridBagConstraints gridbagconstraints4 = new GridBagConstraints();
        final Panel panel = new Panel();
        panel.setLayout(gridbaglayout4);
        gridbagconstraints4.insets = new Insets(7, 7, 0, 0);
        gridbagconstraints4.gridx = 0;
        gridbagconstraints4.gridy = 0;
        gridbagconstraints4.gridwidth = 1;
        gridbagconstraints4.gridheight = 1;
        gridbagconstraints4.weightx = 0.0;
        gridbagconstraints4.fill = 0;
        gridbagconstraints4.anchor = 17;
        final Label label = new Label(super.wvDispatcher.isEnglish() ? "Camera :" : "\u30ab\u30e1\u30e9 :");
        gridbaglayout4.setConstraints(label, gridbagconstraints4);
        panel.add(label);
        gridbagconstraints4.gridx = 1;
        gridbagconstraints4.weightx = 1.0;
        gridbagconstraints4.fill = 2;
        gridbagconstraints4.anchor = 10;
        gridbaglayout4.setConstraints(wvcamerachoice, gridbagconstraints4);
        panel.add(wvcamerachoice);
        gridbaglayout4 = new GridBagLayout();
        gridbagconstraints4 = new GridBagConstraints();
        final Panel panel2 = new Panel();
        panel2.setLayout(gridbaglayout4);
        gridbagconstraints4.insets = new Insets(0, 7, 0, 0);
        gridbagconstraints4.gridx = 0;
        gridbagconstraints4.gridy = 0;
        gridbagconstraints4.gridwidth = 1;
        gridbagconstraints4.gridheight = 1;
        gridbagconstraints4.weightx = 0.0;
        gridbagconstraints4.weighty = 0.0;
        gridbagconstraints4.fill = 0;
        gridbagconstraints4.anchor = 18;
        gridbaglayout4.setConstraints(wvimagebutton3, gridbagconstraints4);
        panel2.add(wvimagebutton3);
        gridbagconstraints4.gridx = 0;
        gridbagconstraints4.gridy = 1;
        gridbagconstraints4.anchor = 16;
        gridbaglayout4.setConstraints(wvimagebutton4, gridbagconstraints4);
        panel2.add(wvimagebutton4);
        gridbagconstraints4.insets = new Insets(0, 0, 0, 0);
        gridbagconstraints4.gridx = 1;
        gridbagconstraints4.gridy = 0;
        gridbagconstraints4.gridwidth = 1;
        gridbagconstraints4.gridheight = 2;
        gridbagconstraints4.weightx = 0.0;
        gridbagconstraints4.weighty = 1.0;
        gridbagconstraints4.fill = 3;
        gridbagconstraints4.anchor = 10;
        gridbaglayout4.setConstraints(wvscrollbar3, gridbagconstraints4);
        panel2.add(wvscrollbar3);
        gridbagconstraints4.insets = new Insets(7, 0, 0, 7);
        gridbagconstraints4.gridx = 0;
        gridbagconstraints4.gridy = 2;
        gridbagconstraints4.gridwidth = 1;
        gridbagconstraints4.gridheight = 1;
        gridbagconstraints4.weightx = 0.0;
        gridbagconstraints4.weighty = 0.0;
        gridbagconstraints4.fill = 0;
        gridbagconstraints4.anchor = 13;
        gridbaglayout4.setConstraints(wvbacklightbutton, gridbagconstraints4);
        panel2.add(wvbacklightbutton);
        if (flag2) {
            gridbagconstraints4.insets = new Insets(7, 0, 0, 0);
            gridbagconstraints4.gridx = 1;
            gridbagconstraints4.gridy = 2;
            gridbagconstraints4.anchor = 17;
            gridbaglayout4.setConstraints(this.wvPanoramaButton, gridbagconstraints4);
            if (!"off".equalsIgnoreCase(this.getParameter("show_panobutton"))) {
                panel2.add(this.wvPanoramaButton);
            }
        }
        gridbagconstraints4.insets = new Insets(7, 0, 0, 0);
        gridbagconstraints4.gridx = 0;
        gridbagconstraints4.gridy = 3;
        gridbagconstraints4.gridwidth = 2;
        gridbagconstraints4.gridheight = 1;
        gridbagconstraints4.anchor = 10;
        gridbaglayout4.setConstraints(wvtimeindicator3, gridbagconstraints4);
        panel2.add(wvtimeindicator3);
        gridbagconstraints4.insets = new Insets(7, 0, 0, 0);
        gridbagconstraints4.gridx = 0;
        gridbagconstraints4.gridy = 4;
        gridbagconstraints4.gridwidth = 2;
        gridbagconstraints4.gridheight = 2;
        gridbaglayout4.setConstraints(wvcontrolbutton2, gridbagconstraints4);
        panel2.add(wvcontrolbutton2);
        gridbaglayout4 = new GridBagLayout();
        gridbagconstraints4 = new GridBagConstraints();
        Point point3 = new Point(335, 0);
        s = this.getParameter("image_offset");
        if (image != null && s != null) {
            try {
                final StringTokenizer stringtokenizer3 = new StringTokenizer(s, ", ");
                final int l4 = Integer.parseInt(stringtokenizer3.nextToken());
                final int i5 = Integer.parseInt(stringtokenizer3.nextToken());
                point3 = new Point(l4, i5);
            }
            catch (Exception ex6) {}
        }
        final WvImagePanel wvimagepanel2 = new WvImagePanel(image, point3);
        wvimagepanel2.setLayout(gridbaglayout4);
        gridbagconstraints4.insets = new Insets(7, 7, 0, 0);
        gridbagconstraints4.gridx = 0;
        gridbagconstraints4.gridy = 0;
        gridbagconstraints4.gridwidth = 2;
        gridbagconstraints4.gridheight = 1;
        gridbagconstraints4.weightx = 0.0;
        gridbagconstraints4.weighty = 0.0;
        gridbagconstraints4.fill = 2;
        gridbagconstraints4.anchor = 17;
        gridbaglayout4.setConstraints(panel, gridbagconstraints4);
        wvimagepanel2.add(panel);
        gridbagconstraints4.insets = new Insets(7, 7, 0, 0);
        gridbagconstraints4.gridx = 4;
        gridbagconstraints4.gridy = 0;
        gridbagconstraints4.gridwidth = 2;
        gridbagconstraints4.gridheight = 1;
        gridbagconstraints4.fill = 0;
        gridbagconstraints4.anchor = 10;
        if (image == null && !"off".equalsIgnoreCase(this.getParameter("show_logo"))) {
            gridbaglayout4.setConstraints(wvimagebutton2, gridbagconstraints4);
            wvimagepanel2.add(wvimagebutton2);
        }
        gridbagconstraints4.insets = new Insets(7, 7, 0, 0);
        gridbagconstraints4.gridx = 0;
        gridbagconstraints4.gridy = 1;
        gridbagconstraints4.gridwidth = 2;
        gridbagconstraints4.gridheight = 1;
        gridbagconstraints4.fill = 0;
        gridbagconstraints4.anchor = 10;
        gridbaglayout4.setConstraints(this.wvLiveCanvas, gridbagconstraints4);
        wvimagepanel2.add(this.wvLiveCanvas);
        gridbagconstraints4.insets = new Insets(0, 7, 0, 0);
        gridbagconstraints4.gridx = 0;
        gridbagconstraints4.gridy = 2;
        gridbagconstraints4.gridwidth = 2;
        gridbagconstraints4.gridheight = 1;
        gridbagconstraints4.fill = 2;
        gridbagconstraints4.anchor = 10;
        gridbaglayout4.setConstraints(wvscrollbar, gridbagconstraints4);
        wvimagepanel2.add(wvscrollbar);
        gridbagconstraints4.insets = new Insets(7, 0, 0, 0);
        gridbagconstraints4.gridx = 3;
        gridbagconstraints4.gridy = 1;
        gridbagconstraints4.gridwidth = 1;
        gridbagconstraints4.gridheight = 1;
        gridbagconstraints4.fill = 3;
        gridbaglayout4.setConstraints(wvscrollbar2, gridbagconstraints4);
        wvimagepanel2.add(wvscrollbar2);
        gridbagconstraints4.insets = new Insets(7, 7, 0, 0);
        gridbagconstraints4.gridx = 0;
        gridbagconstraints4.gridy = 4;
        gridbagconstraints4.gridwidth = 1;
        gridbagconstraints4.gridheight = 1;
        gridbagconstraints4.anchor = 17;
        gridbagconstraints4.fill = 0;
        gridbaglayout4.setConstraints(wvcapturebutton2, gridbagconstraints4);
        if (!"off".equalsIgnoreCase(this.getParameter("show_capture"))) {
            wvimagepanel2.add(wvcapturebutton2);
        }
        gridbagconstraints4.gridx = 1;
        gridbagconstraints4.gridy = 4;
        gridbagconstraints4.gridwidth = 3;
        gridbagconstraints4.gridheight = 1;
        gridbagconstraints4.anchor = 10;
        gridbagconstraints4.fill = 2;
        gridbaglayout4.setConstraints(this.wvCardLayoutPanel, gridbagconstraints4);
        wvimagepanel2.add(this.wvCardLayoutPanel);
        gridbagconstraints4.gridx = 4;
        gridbagconstraints4.gridy = 1;
        gridbagconstraints4.gridwidth = 1;
        gridbagconstraints4.gridheight = 5;
        gridbagconstraints4.fill = 3;
        gridbaglayout4.setConstraints(panel2, gridbagconstraints4);
        wvimagepanel2.add(panel2);
        gridbaglayout4 = new GridBagLayout();
        gridbagconstraints4 = new GridBagConstraints();
        this.setLayout(gridbaglayout4);
        gridbagconstraints4.insets = new Insets(7, 0, 0, 0);
        if ("on".equalsIgnoreCase(this.getParameter("show_toolbar"))) {
            gridbagconstraints4.gridx = 0;
            gridbagconstraints4.gridy = 0;
            gridbagconstraints4.gridwidth = 1;
            gridbagconstraints4.gridheight = 1;
            gridbagconstraints4.fill = 0;
            gridbagconstraints4.anchor = 10;
            gridbaglayout4.setConstraints(wvtoolbarpanel2, gridbagconstraints4);
            this.add(wvtoolbarpanel2);
            gridbagconstraints4.insets = new Insets(0, 0, 0, 0);
        }
        gridbagconstraints4.gridx = 0;
        gridbagconstraints4.gridy = 1;
        gridbagconstraints4.fill = 0;
        gridbagconstraints4.anchor = 10;
        gridbaglayout4.setConstraints(wvimagepanel2, gridbagconstraints4);
        this.add(wvimagepanel2);
        if (flag2) {
            (this.wvPanoramaFrame = new WvPanoramaFrame()).add("West", this.wvPanoramaCanvas);
            return;
        }
        gridbagconstraints4.insets = new Insets(7, 7, 0, 0);
        gridbagconstraints4.gridx = 0;
        gridbagconstraints4.gridy = 2;
        gridbagconstraints4.gridwidth = 1;
        gridbagconstraints4.gridheight = 1;
        gridbagconstraints4.weightx = 1.0;
        gridbagconstraints4.weighty = 1.0;
        gridbagconstraints4.fill = 0;
        gridbagconstraints4.anchor = 10;
        gridbaglayout4.setConstraints(this.wvPanoramaCanvas, gridbagconstraints4);
        this.add(this.wvPanoramaCanvas);
    }
    
    public boolean mouseUp(final Event event, final int i, final int j) {
        if (event.target instanceof WvPanoramaButton && this.wvPanoramaFrame != null) {
            this.wvPanoramaFrame.pack();
            this.wvPanoramaFrame.show();
            this.wvPanoramaFrame.toFront();
            this.wvPanoramaFrame.repaint();
        }
        return false;
    }
    
    public void postStop() {
        if (this.wvPanoramaFrame != null) {
            this.wvPanoramaFrame.hide();
            this.wvPanoramaFrame.dispose();
        }
    }
    
    public void setHostname(final String s) {
        super.wvDispatcher.putObject("hostname", s);
        this.wvLiveCanvas.setHostnameString(s);
    }
    
    public void setComment(final String s) {
        super.wvDispatcher.putObject("comment", s);
        this.wvLiveCanvas.setCommentString(s);
    }
    
    public boolean handleEvent(final Event event) {
        boolean flag = false;
        if (!(event.target instanceof WvControlButton)) {
            switch (event.id) {
                case 501:
                case 601:
                case 602:
                case 603:
                case 604:
                case 605:
                case 1001: {
                    flag = true;
                    break;
                }
            }
        }
        if (event.target == this.wvPanoramaButton) {
            flag = false;
        }
        if (flag && this.wvCardLayoutPanel != null) {
            this.wvCardLayoutPanel.userTriedToControl(event);
        }
        return super.handleEvent(event);
    }
}
