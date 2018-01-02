// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.imagetools;

import com.itt.J2KViewer.exception.InvalidDRAException;
import com.itt.J2KViewer.util.ViewerConst;
import com.itt.J2KViewer.controller.ViewCentral;
import com.itt.J2KViewer.util.Log;

public class DRAManager
{
    public static final int BAND_RED = 0;
    public static final int BAND_GREEN = 1;
    public static final int BAND_BLUE = 2;
    private static Log log;
    private ViewCentral viewCentral;
    private int[] redHistogram;
    private int[] greenHistogram;
    private int[] blueHistogram;
    private double[] redPercentCumulHisto;
    private double[] greenPercentCumulHisto;
    private double[] bluePercentCumulHisto;
    private int redCount;
    private int greenCount;
    private int blueCount;
    private int[] redDataRange;
    private int[] greenDataRange;
    private int[] blueDataRange;
    private float fDraStretchMinR;
    private float fDraStretchMinRPrev;
    private float fDraStretchMaxR;
    private float fDraStretchMaxRPrev;
    private float fDraStretchScaleR;
    private float fDraStretchScaleRPrev;
    private float fDraStretchShiftR;
    private float fDraStretchShiftRPrev;
    private float fDraStretchMinG;
    private float fDraStretchMinGPrev;
    private float fDraStretchMaxG;
    private float fDraStretchMaxGPrev;
    private float fDraStretchScaleG;
    private float fDraStretchScaleGPrev;
    private float fDraStretchShiftG;
    private float fDraStretchShiftGPrev;
    private float fDraStretchMinB;
    private float fDraStretchMinBPrev;
    private float fDraStretchMaxB;
    private float fDraStretchMaxBPrev;
    private float fDraStretchScaleB;
    private float fDraStretchScaleBPrev;
    private float fDraStretchShiftB;
    private float fDraStretchShiftBPrev;
    private double stretchPercent;
    private double stretchPercentPrev;
    private float bitDepthMax;
    private float bitDepthMin;
    private int bitDepth;
    private boolean isSigned;
    private int ignoreValue;
    private int ignoreValuePrev;
    private boolean useIgnoreValueBool;
    private boolean useIgnoreValueBoolPrev;
    private int autoStretchType;
    private String[] stretchTypes;
    static /* synthetic */ Class class$com$itt$J2KViewer$imagetools$DRAManager;
    
    public DRAManager(final ViewCentral viewCentral) {
        this.viewCentral = null;
        this.redHistogram = null;
        this.greenHistogram = null;
        this.blueHistogram = null;
        this.redPercentCumulHisto = null;
        this.greenPercentCumulHisto = null;
        this.bluePercentCumulHisto = null;
        this.redCount = 0;
        this.greenCount = 0;
        this.blueCount = 0;
        this.redDataRange = new int[] { 0, 0 };
        this.greenDataRange = new int[] { 0, 0 };
        this.blueDataRange = new int[] { 0, 0 };
        this.fDraStretchMinR = 0.0f;
        this.fDraStretchMinRPrev = 0.0f;
        this.fDraStretchMaxR = 0.0f;
        this.fDraStretchMaxRPrev = 0.0f;
        this.fDraStretchScaleR = 0.0f;
        this.fDraStretchScaleRPrev = 0.0f;
        this.fDraStretchShiftR = 0.0f;
        this.fDraStretchShiftRPrev = 0.0f;
        this.fDraStretchMinG = 0.0f;
        this.fDraStretchMinGPrev = 0.0f;
        this.fDraStretchMaxG = 0.0f;
        this.fDraStretchMaxGPrev = 0.0f;
        this.fDraStretchScaleG = 0.0f;
        this.fDraStretchScaleGPrev = 0.0f;
        this.fDraStretchShiftG = 0.0f;
        this.fDraStretchShiftGPrev = 0.0f;
        this.fDraStretchMinB = 0.0f;
        this.fDraStretchMinBPrev = 0.0f;
        this.fDraStretchMaxB = 0.0f;
        this.fDraStretchMaxBPrev = 0.0f;
        this.fDraStretchScaleB = 0.0f;
        this.fDraStretchScaleBPrev = 0.0f;
        this.fDraStretchShiftB = 0.0f;
        this.fDraStretchShiftBPrev = 0.0f;
        this.stretchPercent = 0.0;
        this.stretchPercentPrev = 0.0;
        this.bitDepthMax = 0.0f;
        this.bitDepthMin = 0.0f;
        this.bitDepth = 8;
        this.isSigned = false;
        this.ignoreValue = 0;
        this.ignoreValuePrev = 0;
        this.useIgnoreValueBool = false;
        this.useIgnoreValueBoolPrev = false;
        this.autoStretchType = 1;
        this.viewCentral = viewCentral;
        (this.stretchTypes = ViewerConst.stretchTypes)[2] = this.viewCentral.getPropertyManager().getDefaultDRAPercent() + "% " + "Percent";
    }
    
    public void setAutoStretchType(final int autoStretchType) {
        this.autoStretchType = autoStretchType;
    }
    
    public int getAutoStretchType() {
        return this.autoStretchType;
    }
    
    public String[] getStretchTypes() {
        return this.stretchTypes;
    }
    
    public synchronized void setHistograms(final int[] redHistogram, final int[] greenHistogram, final int[] blueHistogram) {
        this.redHistogram = redHistogram;
        this.greenHistogram = greenHistogram;
        this.blueHistogram = blueHistogram;
        this.redPercentCumulHisto = new double[this.redHistogram.length];
        this.redCount = 0;
        this.greenCount = 0;
        this.blueCount = 0;
        for (int i = 0; i < this.redHistogram.length; ++i) {
            this.redCount += this.redHistogram[i];
            this.redPercentCumulHisto[i] = this.redCount;
        }
        for (int j = 0; j < this.redPercentCumulHisto.length; ++j) {
            this.redPercentCumulHisto[j] /= this.redCount;
        }
        if (this.greenHistogram != null) {
            this.greenPercentCumulHisto = new double[this.greenHistogram.length];
            for (int k = 0; k < this.greenHistogram.length; ++k) {
                this.greenCount += this.greenHistogram[k];
                this.greenPercentCumulHisto[k] = this.greenCount;
            }
            for (int l = 0; l < this.greenPercentCumulHisto.length; ++l) {
                this.greenPercentCumulHisto[l] /= this.greenCount;
            }
        }
        if (this.blueHistogram != null) {
            this.bluePercentCumulHisto = new double[this.blueHistogram.length];
            for (int n = 0; n < this.blueHistogram.length; ++n) {
                this.blueCount += this.blueHistogram[n];
                this.bluePercentCumulHisto[n] = this.blueCount;
            }
            for (int n2 = 0; n2 < this.bluePercentCumulHisto.length; ++n2) {
                this.bluePercentCumulHisto[n2] /= this.blueCount;
            }
        }
    }
    
    public int adjust(final int n, final int n2) {
        int n3 = n;
        if (this.viewCentral.getPropertyManager().isShowRGB()) {
            switch (n2) {
                case 0: {
                    if (n <= (int)this.fDraStretchMinR) {
                        n3 = 0;
                        break;
                    }
                    if (n >= (int)this.fDraStretchMaxR) {
                        n3 = 255;
                        break;
                    }
                    n3 = (int)(n * this.fDraStretchScaleR + this.fDraStretchShiftR);
                    break;
                }
                case 1: {
                    if (n <= (int)this.fDraStretchMinG) {
                        n3 = 0;
                        break;
                    }
                    if (n >= (int)this.fDraStretchMaxG) {
                        n3 = 255;
                        break;
                    }
                    n3 = (int)(n * this.fDraStretchScaleG + this.fDraStretchShiftG);
                    break;
                }
                case 2: {
                    if (n <= (int)this.fDraStretchMinB) {
                        n3 = 0;
                        break;
                    }
                    if (n >= (int)this.fDraStretchMaxB) {
                        n3 = 255;
                        break;
                    }
                    n3 = (int)(n * this.fDraStretchScaleB + this.fDraStretchShiftB);
                    break;
                }
            }
        }
        else if (n <= (int)this.fDraStretchMinR) {
            n3 = 0;
        }
        else if (n >= (int)this.fDraStretchMaxR) {
            n3 = 255;
        }
        else {
            n3 = (int)(n * this.fDraStretchScaleR + this.fDraStretchShiftR);
        }
        return n3;
    }
    
    public void setDataRange(final int n, final int n2, final int n3) {
        switch (n) {
            case 0: {
                this.redDataRange[0] = n2;
                this.redDataRange[1] = n3;
                break;
            }
            case 1: {
                this.greenDataRange[0] = n2;
                this.greenDataRange[1] = n3;
                break;
            }
            case 2: {
                this.blueDataRange[0] = n2;
                this.blueDataRange[1] = n3;
                break;
            }
        }
    }
    
    public int[] getDataRange(final int n) {
        int[] array = null;
        switch (n) {
            case 0: {
                array = this.redDataRange;
                break;
            }
            case 1: {
                array = this.greenDataRange;
                break;
            }
            case 2: {
                array = this.blueDataRange;
                break;
            }
        }
        return array;
    }
    
    public void init() {
        this.bitDepth = this.viewCentral.getPropertyManager().getDataPrecision();
        this.isSigned = this.viewCentral.getPropertyManager().isDataSigned();
        this.fDraStretchMinR = 0.0f;
        this.fDraStretchMinRPrev = this.fDraStretchMinR;
        this.fDraStretchMaxR = (1 << this.bitDepth) - 1;
        this.fDraStretchMaxRPrev = this.fDraStretchMaxR;
        this.fDraStretchMinG = 0.0f;
        this.fDraStretchMinGPrev = this.fDraStretchMinG;
        this.fDraStretchMaxG = (1 << this.bitDepth) - 1;
        this.fDraStretchMaxGPrev = this.fDraStretchMaxG;
        this.fDraStretchMinB = 0.0f;
        this.fDraStretchMinBPrev = this.fDraStretchMinB;
        this.fDraStretchMaxB = (1 << this.bitDepth) - 1;
        this.fDraStretchMaxBPrev = this.fDraStretchMaxB;
        this.fDraStretchScaleR = 255.0f / this.fDraStretchMaxR;
        this.fDraStretchScaleRPrev = this.fDraStretchScaleR;
        this.fDraStretchScaleG = 255.0f / this.fDraStretchMaxG;
        this.fDraStretchScaleGPrev = this.fDraStretchScaleG;
        this.fDraStretchScaleB = 255.0f / this.fDraStretchMaxB;
        this.fDraStretchScaleBPrev = this.fDraStretchScaleB;
        this.fDraStretchShiftR = -1.0f * this.fDraStretchScaleR * this.fDraStretchMinR;
        this.fDraStretchShiftRPrev = this.fDraStretchShiftR;
        this.fDraStretchShiftG = -1.0f * this.fDraStretchScaleG * this.fDraStretchMinG;
        this.fDraStretchShiftGPrev = this.fDraStretchShiftG;
        this.fDraStretchShiftB = -1.0f * this.fDraStretchScaleB * this.fDraStretchMinB;
        this.fDraStretchShiftBPrev = this.fDraStretchShiftB;
        if (this.isSigned) {
            this.bitDepthMax = this.fDraStretchMaxR / 2.0f;
            this.bitDepthMin = -this.bitDepthMax - 1.0f;
        }
        else {
            this.bitDepthMax = this.fDraStretchMaxR;
            this.bitDepthMin = 0.0f;
        }
        this.stretchPercent = 0.0;
        this.stretchPercentPrev = 0.0;
    }
    
    public double getStretchPercent() {
        return this.stretchPercent;
    }
    
    public void setStretchPercent(final double stretchPercent) {
        this.stretchPercent = stretchPercent;
    }
    
    public float getMaxValue() {
        return this.bitDepthMax;
    }
    
    public float getMinValue() {
        return this.bitDepthMin;
    }
    
    public void reset() {
        this.init();
        this.viewCentral.getPropertyManager().setNewDRA(true);
    }
    
    public void undo() {
        final float fDraStretchMinR = this.fDraStretchMinR;
        this.fDraStretchMinR = this.fDraStretchMinRPrev;
        this.fDraStretchMinRPrev = fDraStretchMinR;
        final float fDraStretchMaxR = this.fDraStretchMaxR;
        this.fDraStretchMaxR = this.fDraStretchMaxRPrev;
        this.fDraStretchMaxRPrev = fDraStretchMaxR;
        final float fDraStretchScaleR = this.fDraStretchScaleR;
        this.fDraStretchScaleR = this.fDraStretchScaleRPrev;
        this.fDraStretchScaleRPrev = fDraStretchScaleR;
        final float fDraStretchShiftR = this.fDraStretchShiftR;
        this.fDraStretchShiftR = this.fDraStretchShiftRPrev;
        this.fDraStretchShiftRPrev = fDraStretchShiftR;
        final float fDraStretchMinG = this.fDraStretchMinG;
        this.fDraStretchMinG = this.fDraStretchMinGPrev;
        this.fDraStretchMinGPrev = fDraStretchMinG;
        final float fDraStretchMaxG = this.fDraStretchMaxG;
        this.fDraStretchMaxG = this.fDraStretchMaxGPrev;
        this.fDraStretchMaxGPrev = fDraStretchMaxG;
        final float fDraStretchScaleG = this.fDraStretchScaleG;
        this.fDraStretchScaleG = this.fDraStretchScaleGPrev;
        this.fDraStretchScaleGPrev = fDraStretchScaleG;
        final float fDraStretchShiftG = this.fDraStretchShiftG;
        this.fDraStretchShiftG = this.fDraStretchShiftGPrev;
        this.fDraStretchShiftGPrev = fDraStretchShiftG;
        final float fDraStretchMinB = this.fDraStretchMinB;
        this.fDraStretchMinB = this.fDraStretchMinBPrev;
        this.fDraStretchMinBPrev = fDraStretchMinB;
        final float fDraStretchMaxB = this.fDraStretchMaxB;
        this.fDraStretchMaxB = this.fDraStretchMaxBPrev;
        this.fDraStretchMaxBPrev = fDraStretchMaxB;
        final float fDraStretchScaleB = this.fDraStretchScaleB;
        this.fDraStretchScaleB = this.fDraStretchScaleBPrev;
        this.fDraStretchScaleBPrev = fDraStretchScaleB;
        final float fDraStretchShiftB = this.fDraStretchShiftB;
        this.fDraStretchShiftB = this.fDraStretchShiftBPrev;
        this.fDraStretchShiftBPrev = fDraStretchShiftB;
        final double stretchPercent = this.stretchPercent;
        this.stretchPercent = this.stretchPercentPrev;
        this.stretchPercentPrev = stretchPercent;
        final int ignoreValue = this.ignoreValue;
        this.ignoreValue = this.ignoreValuePrev;
        this.ignoreValuePrev = ignoreValue;
        final boolean useIgnoreValueBool = this.useIgnoreValueBool;
        this.useIgnoreValueBool = this.useIgnoreValueBoolPrev;
        this.useIgnoreValueBoolPrev = useIgnoreValueBool;
        this.viewCentral.getPropertyManager().setNewDRA(true);
    }
    
    public void apply(final int n) {
        switch (n) {
            case 0: {
                this.reset();
                break;
            }
            case 1: {
                this.preferredStretch();
                break;
            }
            case 2: {
                this.percentStretch(this.viewCentral.getPropertyManager().getDefaultDRAPercent());
                break;
            }
            case 3: {
                this.t1_t4Stretch();
                break;
            }
        }
    }
    
    public void auto() {
        switch (this.autoStretchType) {
            case 0: {
                this.reset();
                break;
            }
            case 1: {
                this.preferredStretch();
                break;
            }
            case 2: {
                this.percentStretch(this.viewCentral.getPropertyManager().getDefaultDRAPercent());
                break;
            }
            case 3: {
                this.t1_t4Stretch();
                break;
            }
        }
    }
    
    public synchronized void auto(final double currentDRAPercent) {
        this.stretchPercent = currentDRAPercent / 100.0;
        this.viewCentral.getPropertyManager().setCurrentDRAPercent(currentDRAPercent);
        int n = (int)this.bitDepthMin;
        int n2 = (int)this.bitDepthMax;
        int n3 = (int)this.bitDepthMin;
        int n4 = (int)this.bitDepthMax;
        int n5 = (int)this.bitDepthMin;
        int n6 = (int)this.bitDepthMax;
        int n7 = 0;
        for (int i = 0; i < this.redHistogram.length; ++i) {
            n7 += this.redHistogram[i];
        }
        int n10;
        int n9;
        int n8 = n9 = (n10 = (int)(n7 * this.stretchPercent));
        if (this.useIgnoreValue()) {
            n10 = (int)(this.redCount * this.stretchPercent);
            n8 = (int)(this.greenCount * this.stretchPercent);
            n9 = (int)(this.blueCount * this.stretchPercent);
        }
        int n11 = 0;
        for (int j = 0; j < this.redHistogram.length; ++j) {
            n11 += this.redHistogram[j];
            if (n11 >= n10) {
                n = j + (int)this.bitDepthMin;
                break;
            }
        }
        int n12 = 0;
        for (int k = this.redHistogram.length - 1; k >= 0; --k) {
            n12 += this.redHistogram[k];
            if (n12 >= n10) {
                n2 = k + (int)this.bitDepthMin;
                break;
            }
        }
        if (this.viewCentral.getPropertyManager().isShowRGB()) {
            int n13 = 0;
            for (int l = 0; l < this.greenHistogram.length; ++l) {
                n13 += this.greenHistogram[l];
                if (n13 >= n8) {
                    n3 = l + (int)this.bitDepthMin;
                    break;
                }
            }
            int n14 = 0;
            for (int n15 = this.greenHistogram.length - 1; n15 >= 0; --n15) {
                n14 += this.greenHistogram[n15];
                if (n14 >= n8) {
                    n4 = n15 + (int)this.bitDepthMin;
                    break;
                }
            }
            int n16 = 0;
            for (int n17 = 0; n17 < this.blueHistogram.length; ++n17) {
                n16 += this.blueHistogram[n17];
                if (n16 >= n9) {
                    n5 = n17 + (int)this.bitDepthMin;
                    break;
                }
            }
            int n18 = 0;
            for (int n19 = this.blueHistogram.length - 1; n19 >= 0; --n19) {
                n18 += this.blueHistogram[n19];
                if (n18 >= n9) {
                    n6 = n19 + (int)this.bitDepthMin;
                    break;
                }
            }
        }
        if (this.viewCentral.getPropertyManager().isShowRGB()) {
            if (n < n2 && n3 < n4 && n5 < n6) {
                try {
                    if (n != this.fDraStretchMinR || n3 != this.fDraStretchMinG || n5 != this.fDraStretchMinB || n2 != this.fDraStretchMaxR || n4 != this.fDraStretchMaxG || n6 != this.fDraStretchMaxB) {
                        this.setStretchMin(0, n);
                        this.setStretchMax(0, n2);
                        this.setStretchMin(1, n3);
                        this.setStretchMax(1, n4);
                        this.setStretchMin(2, n5);
                        this.setStretchMax(2, n6);
                        this.viewCentral.getPropertyManager().setNewDRA(true);
                    }
                }
                catch (InvalidDRAException ex) {
                    DRAManager.log.error("Invalid DRA value: ", ex);
                }
            }
            else {
                DRAManager.log.info("Auto DRA had invalid values. Will retry.");
            }
        }
        else if (n < n2) {
            try {
                if (n != this.fDraStretchMinR || n2 != this.fDraStretchMaxR) {
                    this.setStretchMin(0, n);
                    this.setStretchMax(0, n2);
                    this.setStretchMin(1, n3);
                    this.setStretchMax(1, n4);
                    this.setStretchMin(2, n5);
                    this.setStretchMax(2, n6);
                    this.viewCentral.getPropertyManager().setNewDRA(true);
                }
            }
            catch (InvalidDRAException ex2) {
                DRAManager.log.error("Invalid DRA value: ", ex2);
            }
        }
        else {
            DRAManager.log.info("Auto DRA had invalid values. Will retry.");
        }
    }
    
    public synchronized void percentStretch(final double currentDRAPercent) {
        this.stretchPercent = currentDRAPercent / 100.0;
        this.viewCentral.getPropertyManager().setCurrentDRAPercent(currentDRAPercent);
        int n = (int)this.bitDepthMin;
        int n2 = (int)this.bitDepthMax;
        int n3 = (int)this.bitDepthMin;
        int n4 = (int)this.bitDepthMax;
        int n5 = (int)this.bitDepthMin;
        int n6 = (int)this.bitDepthMax;
        int n7 = 0;
        if (this.redPercentCumulHisto != null) {
            for (int i = 0; i < this.redHistogram.length; ++i) {
                n7 += this.redHistogram[i];
            }
            int n10;
            int n9;
            int n8 = n9 = (n10 = (int)(n7 * this.stretchPercent));
            if (this.useIgnoreValue()) {
                n10 = (int)(this.redCount * this.stretchPercent);
                n8 = (int)(this.greenCount * this.stretchPercent);
                n9 = (int)(this.blueCount * this.stretchPercent);
            }
            int n11 = 0;
            for (int j = 0; j < this.redHistogram.length; ++j) {
                n11 += this.redHistogram[j];
                if (n11 >= n10) {
                    n = j + (int)this.bitDepthMin;
                    break;
                }
            }
            int n12 = 0;
            for (int k = this.redHistogram.length - 1; k >= 0; --k) {
                n12 += this.redHistogram[k];
                if (n12 >= n10) {
                    n2 = k + (int)this.bitDepthMin;
                    break;
                }
            }
            if (this.viewCentral.getPropertyManager().isShowRGB()) {
                int n13 = 0;
                for (int l = 0; l < this.greenHistogram.length; ++l) {
                    n13 += this.greenHistogram[l];
                    if (n13 >= n8) {
                        n3 = l + (int)this.bitDepthMin;
                        break;
                    }
                }
                int n14 = 0;
                for (int n15 = this.greenHistogram.length - 1; n15 >= 0; --n15) {
                    n14 += this.greenHistogram[n15];
                    if (n14 >= n8) {
                        n4 = n15 + (int)this.bitDepthMin;
                        break;
                    }
                }
                int n16 = 0;
                for (int n17 = 0; n17 < this.blueHistogram.length; ++n17) {
                    n16 += this.blueHistogram[n17];
                    if (n16 >= n9) {
                        n5 = n17 + (int)this.bitDepthMin;
                        break;
                    }
                }
                int n18 = 0;
                for (int n19 = this.blueHistogram.length - 1; n19 >= 0; --n19) {
                    n18 += this.blueHistogram[n19];
                    if (n18 >= n9) {
                        n6 = n19 + (int)this.bitDepthMin;
                        break;
                    }
                }
            }
            if (this.viewCentral.getPropertyManager().isShowRGB()) {
                if (n < n2 && n3 < n4 && n5 < n6) {
                    try {
                        if (n != this.fDraStretchMinR || n3 != this.fDraStretchMinG || n5 != this.fDraStretchMinB || n2 != this.fDraStretchMaxR || n4 != this.fDraStretchMaxG || n6 != this.fDraStretchMaxB) {
                            this.setStretchMin(0, n);
                            this.setStretchMax(0, n2);
                            this.setStretchMin(1, n3);
                            this.setStretchMax(1, n4);
                            this.setStretchMin(2, n5);
                            this.setStretchMax(2, n6);
                            this.viewCentral.getPropertyManager().setNewDRA(true);
                        }
                    }
                    catch (InvalidDRAException ex) {
                        DRAManager.log.error("Invalid DRA value: ", ex);
                    }
                }
                else {
                    DRAManager.log.info("Auto DRA had invalid values. Will retry.");
                }
            }
            else if (n < n2) {
                try {
                    if (n != this.fDraStretchMinR || n2 != this.fDraStretchMaxR) {
                        this.setStretchMin(0, n);
                        this.setStretchMax(0, n2);
                        this.setStretchMin(1, n3);
                        this.setStretchMax(1, n4);
                        this.setStretchMin(2, n5);
                        this.setStretchMax(2, n6);
                        this.viewCentral.getPropertyManager().setNewDRA(true);
                    }
                }
                catch (InvalidDRAException ex2) {
                    DRAManager.log.error("Invalid DRA value: ", ex2);
                }
            }
            else {
                DRAManager.log.info("Auto DRA had invalid values. Will retry.");
            }
        }
    }
    
    public void t1_t4Stretch() {
        final float n = 0.02f;
        final float n2 = 0.001f;
        final float n3 = 0.99f;
        final float n4 = 0.001f;
        float n5 = this.redDataRange[0];
        float n6 = this.redDataRange[1];
        float n7 = this.blueDataRange[0];
        float n8 = this.blueDataRange[1];
        float n9 = this.greenDataRange[0];
        float n10 = this.greenDataRange[1];
        final int n11 = this.redDataRange[1] - this.redDataRange[0];
        final int n12 = this.blueDataRange[1] - this.blueDataRange[0];
        final int n13 = this.greenDataRange[1] - this.greenDataRange[0];
        if (this.redPercentCumulHisto != null) {
            int i = 0;
            while (i < this.redPercentCumulHisto.length) {
                if (this.redPercentCumulHisto[i] > n) {
                    final float n14 = i - n11 * n2;
                    if (n14 >= n5) {
                        n5 = n14;
                        break;
                    }
                    break;
                }
                else {
                    ++i;
                }
            }
            int j = this.redPercentCumulHisto.length - 1;
            while (j >= 0) {
                if (this.redPercentCumulHisto[j] < n3) {
                    final float n15 = j + n11 * n4;
                    if (n15 <= n6) {
                        n6 = n15;
                        break;
                    }
                    break;
                }
                else {
                    --j;
                }
            }
            if (this.viewCentral.getPropertyManager().isShowRGB()) {
                int k = 0;
                while (k < this.bluePercentCumulHisto.length) {
                    if (this.bluePercentCumulHisto[k] > n) {
                        final float n16 = k - n12 * n2;
                        if (n16 >= n7) {
                            n7 = n16;
                            break;
                        }
                        break;
                    }
                    else {
                        ++k;
                    }
                }
                int l = this.bluePercentCumulHisto.length - 1;
                while (l >= 0) {
                    if (this.bluePercentCumulHisto[l] < n3) {
                        final float n17 = l + n12 * n4;
                        if (n17 <= n8) {
                            n8 = n17;
                            break;
                        }
                        break;
                    }
                    else {
                        --l;
                    }
                }
                int n18 = 0;
                while (n18 < this.greenPercentCumulHisto.length) {
                    if (this.greenPercentCumulHisto[n18] > n) {
                        final float n19 = n18 - n13 * n2;
                        if (n19 >= n9) {
                            n9 = n19;
                            break;
                        }
                        break;
                    }
                    else {
                        ++n18;
                    }
                }
                int n20 = this.greenPercentCumulHisto.length - 1;
                while (n20 >= 0) {
                    if (this.greenPercentCumulHisto[n20] < n3) {
                        final float n21 = n20 + n13 * n4;
                        if (n21 <= n10) {
                            n10 = n21;
                            break;
                        }
                        break;
                    }
                    else {
                        --n20;
                    }
                }
            }
            try {
                if (this.viewCentral.getPropertyManager().isShowRGB()) {
                    this.setStretchMin(0, n5);
                    this.setStretchMax(0, n6);
                    this.setStretchMin(2, n7);
                    this.setStretchMax(2, n8);
                    this.setStretchMin(1, n9);
                    this.setStretchMax(1, n10);
                }
                else {
                    this.setStretchMin(0, n5);
                    this.setStretchMax(0, n6);
                }
                this.viewCentral.getPropertyManager().setNewDRA(true);
            }
            catch (InvalidDRAException ex) {
                DRAManager.log.error("Invalid DRA value: ", ex);
            }
        }
    }
    
    public void preferredStretch() {
        final float n = 0.025f;
        final float n2 = 0.99f;
        final float n3 = 0.1f;
        final float n4 = 0.5f;
        float n5 = this.redDataRange[0];
        float n6 = this.redDataRange[1];
        float n7 = this.blueDataRange[0];
        float n8 = this.blueDataRange[1];
        float n9 = this.greenDataRange[0];
        float n10 = this.greenDataRange[1];
        final int n11 = this.redDataRange[1] - this.redDataRange[0];
        final int n12 = this.blueDataRange[1] - this.blueDataRange[0];
        final int n13 = this.greenDataRange[1] - this.greenDataRange[0];
        float n14 = n5;
        float n15 = n6;
        if (this.redPercentCumulHisto != null) {
            for (int i = 0; i < this.redPercentCumulHisto.length; ++i) {
                if (this.redPercentCumulHisto[i] > n) {
                    n14 = i;
                    break;
                }
            }
            for (int j = this.redPercentCumulHisto.length - 1; j >= 0; --j) {
                if (this.redPercentCumulHisto[j] < n2) {
                    n15 = j;
                    break;
                }
            }
            final float n16 = n14 - n3 * (n15 - n14);
            final float n17 = n15 + n4 * (n15 - n14);
            if (n16 >= n5) {
                n5 = n16;
            }
            if (n17 <= n6) {
                n6 = n17;
            }
            if (this.viewCentral.getPropertyManager().isShowRGB()) {
                float n18 = n7;
                float n19 = n8;
                for (int k = 0; k < this.bluePercentCumulHisto.length; ++k) {
                    if (this.bluePercentCumulHisto[k] > n) {
                        n18 = k;
                        break;
                    }
                }
                for (int l = this.bluePercentCumulHisto.length - 1; l >= 0; --l) {
                    if (this.bluePercentCumulHisto[l] < n2) {
                        n19 = l;
                        break;
                    }
                }
                final float n20 = n18 - n3 * (n19 - n18);
                final float n21 = n19 + n4 * (n19 - n18);
                if (n20 >= n7) {
                    n7 = n20;
                }
                if (n21 <= n8) {
                    n8 = n21;
                }
                float n22 = n9;
                float n23 = n10;
                for (int n24 = 0; n24 < this.greenPercentCumulHisto.length; ++n24) {
                    if (this.greenPercentCumulHisto[n24] > n) {
                        n22 = n24;
                        break;
                    }
                }
                for (int n25 = this.greenPercentCumulHisto.length - 1; n25 >= 0; --n25) {
                    if (this.greenPercentCumulHisto[n25] < n2) {
                        n23 = n25;
                        break;
                    }
                }
                final float n26 = n22 - n3 * (n23 - n22);
                final float n27 = n23 + n4 * (n23 - n22);
                if (n26 >= n9) {
                    n9 = n26;
                }
                if (n27 <= n10) {
                    n10 = n27;
                }
            }
            try {
                if (this.viewCentral.getPropertyManager().isShowRGB()) {
                    this.setStretchMin(0, n5);
                    this.setStretchMax(0, n6);
                    this.setStretchMin(2, n7);
                    this.setStretchMax(2, n8);
                    this.setStretchMin(1, n9);
                    this.setStretchMax(1, n10);
                }
                else {
                    this.setStretchMin(0, n5);
                    this.setStretchMax(0, n6);
                }
                this.viewCentral.getPropertyManager().setNewDRA(true);
            }
            catch (InvalidDRAException ex) {
                DRAManager.log.error("Invalid DRA value: ", ex);
            }
        }
    }
    
    public int getStretchMinAsInt(final int n) {
        return (int)this.getStretchMin(n);
    }
    
    public float getStretchMin(final int n) {
        switch (n) {
            case 0: {
                return this.fDraStretchMinR;
            }
            case 1: {
                return this.fDraStretchMinG;
            }
            case 2: {
                return this.fDraStretchMinB;
            }
            default: {
                DRAManager.log.warn("getStretchMin received invalid band argument: " + n);
                return -1.0f;
            }
        }
    }
    
    public int getStretchMaxAsInt(final int n) {
        return (int)this.getStretchMax(n);
    }
    
    public float getStretchMax(final int n) {
        switch (n) {
            case 0: {
                return this.fDraStretchMaxR;
            }
            case 1: {
                return this.fDraStretchMaxG;
            }
            case 2: {
                return this.fDraStretchMaxB;
            }
            default: {
                DRAManager.log.warn("getStretchMax received invalid band argument: " + n);
                return -1.0f;
            }
        }
    }
    
    private void validateValue(final float n) throws InvalidDRAException {
        if (n < this.getMinValue()) {
            throw new InvalidDRAException("Value must be greater than " + (int)this.getMinValue());
        }
        if (n > this.getMaxValue()) {
            throw new InvalidDRAException("Value must be less than " + (int)this.getMaxValue());
        }
    }
    
    public void setStretchMin(final int n, final float fDraStretchMinB) throws InvalidDRAException {
        this.validateValue(fDraStretchMinB);
        switch (n) {
            case 0: {
                this.fDraStretchMinRPrev = this.fDraStretchMinR;
                this.fDraStretchMinR = fDraStretchMinB;
                this.fDraStretchScaleRPrev = this.fDraStretchScaleR;
                this.fDraStretchScaleR = 255.0f / (this.fDraStretchMaxR - this.fDraStretchMinR);
                this.fDraStretchShiftRPrev = this.fDraStretchShiftR;
                this.fDraStretchShiftR = -1.0f * this.fDraStretchScaleR * this.fDraStretchMinR;
                break;
            }
            case 1: {
                this.fDraStretchMinGPrev = this.fDraStretchMinG;
                this.fDraStretchMinG = fDraStretchMinB;
                this.fDraStretchScaleGPrev = this.fDraStretchScaleG;
                this.fDraStretchScaleG = 255.0f / (this.fDraStretchMaxG - this.fDraStretchMinG);
                this.fDraStretchShiftGPrev = this.fDraStretchShiftG;
                this.fDraStretchShiftG = -1.0f * this.fDraStretchScaleG * this.fDraStretchMinG;
                break;
            }
            case 2: {
                this.fDraStretchMinBPrev = this.fDraStretchMinB;
                this.fDraStretchMinB = fDraStretchMinB;
                this.fDraStretchScaleBPrev = this.fDraStretchScaleB;
                this.fDraStretchScaleB = 255.0f / (this.fDraStretchMaxB - this.fDraStretchMinB);
                this.fDraStretchShiftBPrev = this.fDraStretchShiftB;
                this.fDraStretchShiftB = -1.0f * this.fDraStretchScaleB * this.fDraStretchMinB;
                break;
            }
            default: {
                DRAManager.log.warn("setStretchMin received invalid band argument: " + n);
                break;
            }
        }
    }
    
    public void setStretchMax(final int n, final float fDraStretchMaxB) throws InvalidDRAException {
        this.validateValue(fDraStretchMaxB);
        switch (n) {
            case 0: {
                this.fDraStretchMaxRPrev = this.fDraStretchMaxR;
                this.fDraStretchMaxR = fDraStretchMaxB;
                this.fDraStretchScaleR = 255.0f / (this.fDraStretchMaxR - this.fDraStretchMinR);
                this.fDraStretchShiftR = -1.0f * this.fDraStretchScaleR * this.fDraStretchMinR;
                break;
            }
            case 1: {
                this.fDraStretchMaxGPrev = this.fDraStretchMaxG;
                this.fDraStretchMaxG = fDraStretchMaxB;
                this.fDraStretchScaleG = 255.0f / (this.fDraStretchMaxG - this.fDraStretchMinG);
                this.fDraStretchShiftG = -1.0f * this.fDraStretchScaleG * this.fDraStretchMinG;
                break;
            }
            case 2: {
                this.fDraStretchMaxBPrev = this.fDraStretchMaxB;
                this.fDraStretchMaxB = fDraStretchMaxB;
                this.fDraStretchScaleB = 255.0f / (this.fDraStretchMaxB - this.fDraStretchMinB);
                this.fDraStretchShiftB = -1.0f * this.fDraStretchScaleB * this.fDraStretchMinB;
                break;
            }
            default: {
                DRAManager.log.warn("setStretchMax received invalid band argument: " + n);
                break;
            }
        }
    }
    
    public void setIgnoreValue(final int ignoreValue) {
        this.ignoreValue = ignoreValue;
    }
    
    public int getIgnoreValue() {
        return this.ignoreValue;
    }
    
    public void useIgnoreValue(final boolean useIgnoreValueBool) {
        this.useIgnoreValueBool = useIgnoreValueBool;
    }
    
    public boolean useIgnoreValue() {
        return this.useIgnoreValueBool;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        DRAManager.log = new Log((DRAManager.class$com$itt$J2KViewer$imagetools$DRAManager == null) ? (DRAManager.class$com$itt$J2KViewer$imagetools$DRAManager = class$("com.itt.J2KViewer.imagetools.DRAManager")) : DRAManager.class$com$itt$J2KViewer$imagetools$DRAManager);
    }
}
