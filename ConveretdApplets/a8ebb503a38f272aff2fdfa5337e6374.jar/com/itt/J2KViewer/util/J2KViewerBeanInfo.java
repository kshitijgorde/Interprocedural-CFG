// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class J2KViewerBeanInfo extends SimpleBeanInfo
{
    Class beanClass;
    static /* synthetic */ Class class$com$itt$J2KViewer$J2KViewerBean;
    
    public J2KViewerBeanInfo() {
        this.beanClass = ((J2KViewerBeanInfo.class$com$itt$J2KViewer$J2KViewerBean == null) ? (J2KViewerBeanInfo.class$com$itt$J2KViewer$J2KViewerBean = class$("com.itt.J2KViewer.J2KViewerBean")) : J2KViewerBeanInfo.class$com$itt$J2KViewer$J2KViewerBean);
    }
    
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            final PropertyDescriptor propertyDescriptor = new PropertyDescriptor("allowChangeQuality", this.beanClass, "isAllowChangeQuality", "setAllowChangeQuality");
            final PropertyDescriptor propertyDescriptor2 = new PropertyDescriptor("allowPan", this.beanClass, "isAllowPan", "setAllowPan");
            final PropertyDescriptor propertyDescriptor3 = new PropertyDescriptor("allowZoom", this.beanClass, "isAllowZoom", "setAllowZoom");
            final PropertyDescriptor propertyDescriptor4 = new PropertyDescriptor("uponLoadDoAutoDRA", this.beanClass, "isUponLoadDoAutoDRA", "setUponLoadDoAutoDRA");
            final PropertyDescriptor propertyDescriptor5 = new PropertyDescriptor("defaultDRAType", this.beanClass, "getDefaultDRAType", "setDefaultDRAType");
            final PropertyDescriptor propertyDescriptor6 = new PropertyDescriptor("enableIgnoreValDRA", this.beanClass, "isEnableIgnoreValDRA", "setEnableIgnoreValDRA");
            final PropertyDescriptor propertyDescriptor7 = new PropertyDescriptor("ignoreValDRA", this.beanClass, "getIgnoreValueDRA", "setIgnoreValueDRA");
            final PropertyDescriptor propertyDescriptor8 = new PropertyDescriptor("defaultStretchPercent", this.beanClass, "getDefaultPercentStretch", "setDefaultPercentStretch");
            final PropertyDescriptor propertyDescriptor9 = new PropertyDescriptor("applicationWidth", this.beanClass, null, "setApplicationWidth");
            final PropertyDescriptor propertyDescriptor10 = new PropertyDescriptor("applicationHeight", this.beanClass, null, "setApplicationHeight");
            final PropertyDescriptor propertyDescriptor11 = new PropertyDescriptor("bytesTransferred", this.beanClass, "getBytesTransferred", null);
            final PropertyDescriptor propertyDescriptor12 = new PropertyDescriptor("currentDimensions", this.beanClass, "getCurrentDimensions", null);
            final PropertyDescriptor propertyDescriptor13 = new PropertyDescriptor("discardedZoomLevels", this.beanClass, "getDiscardedZoomLevels", "setDiscardedZoomLevels");
            final PropertyDescriptor propertyDescriptor14 = new PropertyDescriptor("maxDiscardedZoomLevels", this.beanClass, "getMaxDiscardedZoomLevels", null);
            final PropertyDescriptor propertyDescriptor15 = new PropertyDescriptor("qualityLayers", this.beanClass, "getQualityLayers", "setQualityLayers");
            final PropertyDescriptor propertyDescriptor16 = new PropertyDescriptor("totalBytes", this.beanClass, "getTotalBytes", null);
            final PropertyDescriptor propertyDescriptor17 = new PropertyDescriptor("totalDimensions", this.beanClass, "getTotalDimensions", null);
            final PropertyDescriptor propertyDescriptor18 = new PropertyDescriptor("maxQualityLayers", this.beanClass, "getMaxQualityLayers", null);
            final PropertyDescriptor propertyDescriptor19 = new PropertyDescriptor("maxZoomOutLevel", this.beanClass, "getMaxZoomOutLevel", "setMaxZoomOutLevel");
            final PropertyDescriptor propertyDescriptor20 = new PropertyDescriptor("loginId", this.beanClass, "getLoginId", "setLoginId");
            final PropertyDescriptor propertyDescriptor21 = new PropertyDescriptor("loginPwd", this.beanClass, "getLoginPwd", "setLoginPwd");
            final PropertyDescriptor propertyDescriptor22 = new PropertyDescriptor("imageURL", this.beanClass, "getImageURL", "setImageURL");
            final PropertyDescriptor propertyDescriptor23 = new PropertyDescriptor("open", this.beanClass, "isOpen", null);
            final PropertyDescriptor propertyDescriptor24 = new PropertyDescriptor("chippingServiceURL", this.beanClass, null, "setChippingServiceURL");
            final PropertyDescriptor propertyDescriptor25 = new PropertyDescriptor("conversionOptions", this.beanClass, null, "setChippingConversionOptions");
            final PropertyDescriptor propertyDescriptor26 = new PropertyDescriptor("targetResolutionOptions", this.beanClass, null, "setChippingTargetResolutionOptions");
            final PropertyDescriptor propertyDescriptor27 = new PropertyDescriptor("enableChipping", this.beanClass, null, "setEnableChipping");
            final PropertyDescriptor propertyDescriptor28 = new PropertyDescriptor("chippingServiceType", this.beanClass, null, "setChippingServiceType");
            final PropertyDescriptor propertyDescriptor29 = new PropertyDescriptor("showChipWarning", this.beanClass, null, "setShowChipWarning");
            final PropertyDescriptor propertyDescriptor30 = new PropertyDescriptor("chipperOutputDirectory", this.beanClass, "getChipperOutputDirectory", "setChipperOutputDirectory");
            final PropertyDescriptor propertyDescriptor31 = new PropertyDescriptor("showToolBar", this.beanClass, "isShowToolBar", "setShowToolBar");
            final PropertyDescriptor propertyDescriptor32 = new PropertyDescriptor("showContextMenu", this.beanClass, "isShowContextMenu", "setShowContextMenu");
            final PropertyDescriptor propertyDescriptor33 = new PropertyDescriptor("showScrollbars", this.beanClass, "isShowScrollbars", "setShowScrollbars");
            final PropertyDescriptor propertyDescriptor34 = new PropertyDescriptor("showGeoLocationPanel", this.beanClass, null, "setShowGeoLocationPanel");
            final PropertyDescriptor propertyDescriptor35 = new PropertyDescriptor("showStatusPanel", this.beanClass, null, "setShowStatusPanel");
            final PropertyDescriptor propertyDescriptor36 = new PropertyDescriptor("showDynamicRangePanel", this.beanClass, null, "setShowDynamicRangePanel");
            final PropertyDescriptor propertyDescriptor37 = new PropertyDescriptor("showSecurityBanner", this.beanClass, "isShowSecurityBanner", "setShowSecurityBanner");
            final PropertyDescriptor propertyDescriptor38 = new PropertyDescriptor("showCopyrightBanner", this.beanClass, "isShowCopyrightBanner", "setShowCopyrightBanner");
            final PropertyDescriptor propertyDescriptor39 = new PropertyDescriptor("securitySegment", this.beanClass, "getSecuritySegment", "setSecuritySegment");
            final PropertyDescriptor propertyDescriptor40 = new PropertyDescriptor("showSplitPane", this.beanClass, "isShowSplitPane", "setShowSplitPane");
            final PropertyDescriptor propertyDescriptor41 = new PropertyDescriptor("showOverviewImage", this.beanClass, "isShowOverviewImage", "setShowOverviewImage");
            final PropertyDescriptor propertyDescriptor42 = new PropertyDescriptor("lensColor", this.beanClass, null, "setLensColor");
            final PropertyDescriptor propertyDescriptor43 = new PropertyDescriptor("gotoColor", this.beanClass, null, "setGotoColor");
            final PropertyDescriptor propertyDescriptor44 = new PropertyDescriptor("overviewColor", this.beanClass, null, "setOverviewColor");
            final PropertyDescriptor propertyDescriptor45 = new PropertyDescriptor("lensType", this.beanClass, null, "setLensType");
            final PropertyDescriptor propertyDescriptor46 = new PropertyDescriptor("lensRadius", this.beanClass, null, "setLensRadius");
            final PropertyDescriptor propertyDescriptor47 = new PropertyDescriptor("lensMagnification", this.beanClass, null, "setLensMagnification");
            final PropertyDescriptor propertyDescriptor48 = new PropertyDescriptor("qualityLayersPercentToDownload", this.beanClass, null, "setQualityLayersPercentToDownload");
            final PropertyDescriptor propertyDescriptor49 = new PropertyDescriptor("overviewQualityLayersPercentToDownload", this.beanClass, null, "setOverviewQualityLayersPercentToDownload");
            final PropertyDescriptor propertyDescriptor50 = new PropertyDescriptor("defaultProjection", this.beanClass, null, "setDefaultProjection");
            final PropertyDescriptor propertyDescriptor51 = new PropertyDescriptor("cacheDir", this.beanClass, null, "setCacheDir");
            final PropertyDescriptor propertyDescriptor52 = new PropertyDescriptor("cachingIsEnabled", this.beanClass, null, "setCachingIsEnabled");
            final PropertyDescriptor propertyDescriptor53 = new PropertyDescriptor("cacheClearOnAppExit", this.beanClass, null, "setCacheClearOnAppExit");
            final PropertyDescriptor propertyDescriptor54 = new PropertyDescriptor("sslIsEnabled", this.beanClass, null, "setSslEnabled");
            final PropertyDescriptor propertyDescriptor55 = new PropertyDescriptor("northArrowImage", this.beanClass, null, "setNorthArrowImage");
            final PropertyDescriptor propertyDescriptor56 = new PropertyDescriptor("transmissionLength", this.beanClass, null, "setTransmissionLength");
            propertyDescriptor19.setDisplayName("maxZoomOutLevel");
            propertyDescriptor19.setShortDescription("maxZoomOutLevel");
            return new PropertyDescriptor[] { propertyDescriptor, propertyDescriptor2, propertyDescriptor3, propertyDescriptor4, propertyDescriptor6, propertyDescriptor7, propertyDescriptor9, propertyDescriptor10, propertyDescriptor11, propertyDescriptor12, propertyDescriptor13, propertyDescriptor22, propertyDescriptor24, propertyDescriptor25, propertyDescriptor26, propertyDescriptor27, propertyDescriptor28, propertyDescriptor30, propertyDescriptor14, propertyDescriptor23, propertyDescriptor15, propertyDescriptor31, propertyDescriptor16, propertyDescriptor17, propertyDescriptor18, propertyDescriptor19, propertyDescriptor20, propertyDescriptor21, propertyDescriptor32, propertyDescriptor33, propertyDescriptor34, propertyDescriptor35, propertyDescriptor36, propertyDescriptor37, propertyDescriptor38, propertyDescriptor29, propertyDescriptor40, propertyDescriptor39, propertyDescriptor42, propertyDescriptor43, propertyDescriptor44, propertyDescriptor45, propertyDescriptor46, propertyDescriptor47, propertyDescriptor48, propertyDescriptor49, propertyDescriptor8, propertyDescriptor50, propertyDescriptor51, propertyDescriptor52, propertyDescriptor53, propertyDescriptor54, propertyDescriptor55, propertyDescriptor5, propertyDescriptor56, propertyDescriptor41 };
        }
        catch (IntrospectionException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
