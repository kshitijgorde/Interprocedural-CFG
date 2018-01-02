// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer.saalplan;

import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import java.io.Serializable;

public class ReCaptchaDetails implements Serializable
{
    private String challengeId;
    private int[] challengeImageData;
    private int challengeImageHeight;
    private int challengeImageWidth;
    private String psig;
    
    public ReCaptchaDetails(final String challengeId, final int[] challengeImageData, final int challengeImageWidth, final int challengeImageHeight, final String psig) {
        this.challengeId = challengeId;
        this.challengeImageData = challengeImageData;
        this.challengeImageWidth = challengeImageWidth;
        this.challengeImageHeight = challengeImageHeight;
        this.psig = psig;
    }
    
    public String getChallengeId() {
        return this.challengeId;
    }
    
    public Image getChallengeImage() {
        if (this.challengeImageData == null) {
            return null;
        }
        final MemoryImageSource mis = new MemoryImageSource(this.challengeImageWidth, this.challengeImageHeight, this.challengeImageData, 0, this.challengeImageWidth);
        final Toolkit tk = Toolkit.getDefaultToolkit();
        return tk.createImage(mis);
    }
    
    public String getPsig() {
        return this.psig;
    }
}
