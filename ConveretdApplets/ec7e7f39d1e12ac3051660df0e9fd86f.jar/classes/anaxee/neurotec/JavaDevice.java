// 
// Decompiled by Procyon v0.5.30
// 

package classes.anaxee.neurotec;

import com.neurotec.util.NReadOnlyCollection;
import com.neurotec.biometrics.standards.FMRecord;
import com.neurotec.biometrics.NFImpressionType;
import com.neurotec.biometrics.NFExtractor;
import com.neurotec.biometrics.NFPosition;
import com.neurotec.images.NImageFormat;
import java.util.Iterator;
import com.neurotec.devices.NDevice;
import com.neurotec.licensing.NLicense;
import java.util.EnumSet;
import com.neurotec.devices.NDeviceType;
import com.neurotec.biometrics.NFTemplate;
import com.neurotec.biometrics.standards.BdifStandard;
import com.neurotec.images.NGrayscaleImage;
import com.neurotec.images.NImage;
import com.neurotec.biometrics.tools.NfiqQuality;
import java.nio.ByteBuffer;
import java.util.Vector;
import com.neurotec.devices.NFScanner;
import com.neurotec.devices.NDeviceManager;

public class JavaDevice
{
    boolean is_mode_debug;
    static final String extractor_license = "Biometrics.FingerExtraction";
    static final String device_license = "Devices.FingerScanners";
    static final String bss_license = "Biometrics.Standards.Fingers";
    NDeviceManager device_manager;
    NFScanner device;
    Vector<String> device_names;
    boolean is_extractor_license_obtained;
    boolean is_device_license_obtained;
    boolean is_device_manager_initialized;
    boolean is_device_list_populated;
    boolean is_device_selected;
    boolean is_bss_license_obtained;
    int device_count;
    int image_width;
    int image_height;
    ByteBuffer raw_image;
    NfiqQuality NFIQ;
    NImage n_image;
    NGrayscaleImage n_grayscale_image;
    int quality_percent;
    boolean is_scanned;
    String biometric_mode;
    int time_out;
    private BdifStandard standard;
    ByteBuffer scanned_buffer;
    ByteBuffer image_display_buffer;
    NFTemplate nf_packed_template;
    boolean gui_use_mode_enrollment;
    boolean isNeurotecLicenseObtained;
    
    public void setUseDebugMode(final boolean is_mode_debug) {
        this.is_mode_debug = is_mode_debug;
    }
    
    public JavaDevice() {
        this.is_mode_debug = false;
        this.device_manager = null;
        this.device = null;
        this.device_names = new Vector<String>();
        this.is_extractor_license_obtained = false;
        this.is_device_license_obtained = false;
        this.is_device_manager_initialized = false;
        this.is_device_list_populated = false;
        this.is_device_selected = false;
        this.is_bss_license_obtained = false;
        this.device_count = 0;
        this.image_width = 0;
        this.image_height = 0;
        this.raw_image = null;
        this.n_image = null;
        this.n_grayscale_image = null;
        this.quality_percent = 0;
        this.is_scanned = false;
        this.biometric_mode = null;
        this.time_out = 5;
        this.standard = BdifStandard.ISO;
        this.scanned_buffer = null;
        this.image_display_buffer = null;
        this.nf_packed_template = null;
        this.gui_use_mode_enrollment = false;
        this.isNeurotecLicenseObtained = false;
        if (this.is_mode_debug) {
            System.out.println("in side of con of javadevice");
        }
        this.nf_packed_template = new NFTemplate();
    }
    
    public boolean init() {
        this.device_manager = new NDeviceManager(EnumSet.of(NDeviceType.FINGER_SCANNER));
        if (this.device_manager == null) {
            return false;
        }
        this.is_device_manager_initialized = true;
        this.is_device_list_populated = false;
        return true;
    }
    
    public void refresh() {
        if (this.device_manager != null) {
            this.device_manager.refresh();
        }
    }
    
    private boolean obtainDeviceLicense() throws Exception {
        return NLicense.obtainComponents("/local", 5000, "Devices.FingerScanners") && (this.is_device_license_obtained = true);
    }
    
    private void releaseDeviceLicense() {
        NLicense.releaseComponents("Devices.FingerScanners");
        this.is_device_license_obtained = false;
    }
    
    private boolean obtainExtractorLicense() {
        try {
            if (NLicense.obtainComponents("/local", 5000, "Biometrics.FingerExtraction")) {
                this.is_extractor_license_obtained = true;
            }
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    private void releaseExtractorLicense() {
        NLicense.releaseComponents("Biometrics.FingerExtraction");
        this.is_extractor_license_obtained = false;
    }
    
    private boolean obtainBSSLicense() {
        try {
            if (NLicense.obtainComponents("/local", 5000, "Biometrics.Standards.Fingers")) {
                this.is_bss_license_obtained = true;
            }
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    private void releaseBSSLicense() {
        NLicense.releaseComponents("Biometrics.Standards.Fingers");
        this.is_bss_license_obtained = false;
    }
    
    public boolean checkConnectedDevices() {
        if (!this.is_device_manager_initialized) {
            return false;
        }
        this.refresh();
        this.device_count = this.device_manager.getDevices().size();
        if (this.device_count == 0) {
            return false;
        }
        if (this.device_count > 0) {
            this.device_names.size();
            this.device_names.clear();
            if (this.is_mode_debug) {
                System.out.print("devices list no " + this.device_names.size());
            }
            for (final NDevice nDevice : this.device_manager.getDevices()) {
                this.device_names.add(nDevice.getMake() + " " + nDevice.getModel());
            }
        }
        if (this.device_count >= 1) {
            this.selectDevice(0);
        }
        return this.is_device_list_populated = true;
    }
    
    public boolean selectDevice(final int index) {
        if (index >= this.device_count) {
            return false;
        }
        if (!this.is_device_manager_initialized) {
            return false;
        }
        this.refresh();
        this.device = ((NReadOnlyCollection<NFScanner>)this.device_manager.getDevices()).get(index);
        return this.device != null && (this.is_device_selected = true);
    }
    
    public String getDeviceName(final int n) {
        if (!this.is_device_list_populated) {
            return null;
        }
        if (n >= this.device_count) {
            return null;
        }
        return this.device_names.elementAt(n);
    }
    
    private ByteBuffer scanImage(final int n, final String s) throws Exception {
        if (!this.obtainDeviceLicense()) {
            this.isNeurotecLicenseObtained = false;
            return null;
        }
        if (!this.is_device_selected) {
            return null;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        final NImage capture = this.device.capture(n * 1000);
        final long currentTimeMillis2 = System.currentTimeMillis();
        if (capture == null) {
            this.releaseDeviceLicense();
            if (currentTimeMillis2 - currentTimeMillis > 100L) {
                this.isNeurotecLicenseObtained = true;
                if (this.is_mode_debug) {
                    System.out.println("time expired");
                }
            }
            else {
                this.isNeurotecLicenseObtained = false;
                if (this.is_mode_debug) {
                    System.out.println("license not there");
                }
            }
            return null;
        }
        this.isNeurotecLicenseObtained = true;
        this.n_grayscale_image = capture.toGrayscale();
        this.releaseDeviceLicense();
        this.image_height = capture.getHeight();
        this.image_width = capture.getWidth();
        this.raw_image = ByteBuffer.allocateDirect(this.image_height * this.image_width);
        this.raw_image = capture.getPixels();
        if (this.is_mode_debug) {
            System.out.println("stride is " + capture.getStride());
        }
        if ("bmp".equalsIgnoreCase(s)) {
            return capture.save(NImageFormat.Bmp);
        }
        if ("jpeg".equalsIgnoreCase(s) || "jpg".equalsIgnoreCase(s)) {
            return capture.save(NImageFormat.Jpeg);
        }
        if ("png".equalsIgnoreCase(s)) {
            return capture.save(NImageFormat.Png);
        }
        if ("ihead".equalsIgnoreCase(s)) {
            return capture.save(NImageFormat.IHead);
        }
        if ("jpeg2k".equalsIgnoreCase(s)) {
            return capture.save(NImageFormat.Jpeg2K);
        }
        if ("tiff".equalsIgnoreCase(s)) {
            return capture.save(NImageFormat.Tiff);
        }
        if ("wsq".equalsIgnoreCase(s)) {
            return capture.save(NImageFormat.Wsq);
        }
        return this.raw_image;
    }
    
    private ByteBuffer extractPackedTemplate(final ByteBuffer byteBuffer, final int n, final int n2) {
        return null;
    }
    
    private ByteBuffer extractPackedTemplate(final NFPosition position) {
        if (this.raw_image == null) {
            return null;
        }
        if (this.is_mode_debug) {
            System.out.println("raw_img has array " + this.raw_image.hasArray() + "is direct" + this.raw_image.isDirect());
        }
        else {
            this.raw_image.hasArray();
            this.raw_image.isDirect();
        }
        boolean b = false;
        if (!this.is_extractor_license_obtained) {
            if (!this.obtainExtractorLicense()) {
                this.isNeurotecLicenseObtained = false;
                return null;
            }
            b = true;
            this.isNeurotecLicenseObtained = true;
        }
        if (!this.gui_use_mode_enrollment) {
            this.nf_packed_template = null;
            this.nf_packed_template = new NFTemplate();
        }
        final NFExtractor nfExtractor = new NFExtractor();
        try {
            final NFExtractor.NFExtractorResult extract = nfExtractor.extract(this.n_grayscale_image, position, NFImpressionType.LIVE_SCAN_PLAIN);
            this.nf_packed_template.getRecords().add(extract.getRecord().save());
            this.quality_percent = extract.getRecord().getQuality();
        }
        catch (Exception ex) {
            if (this.is_mode_debug) {
                System.out.println("inside catch of packed template");
            }
            if (b) {
                this.releaseExtractorLicense();
            }
            ex.printStackTrace();
            return null;
        }
        if (b) {
            this.releaseExtractorLicense();
        }
        return this.nf_packed_template.save();
    }
    
    private ByteBuffer extractISOTemplate(final ByteBuffer byteBuffer, final int n, final int n2) {
        return null;
    }
    
    private ByteBuffer extractISOTemplate(final NFPosition nfPosition) {
        if (!this.is_bss_license_obtained) {
            if (!this.obtainBSSLicense()) {
                this.isNeurotecLicenseObtained = false;
                return null;
            }
            this.is_bss_license_obtained = true;
            this.isNeurotecLicenseObtained = true;
        }
        final ByteBuffer packedTemplate = this.extractPackedTemplate(nfPosition);
        if (packedTemplate == null) {
            return null;
        }
        final NFTemplate nfTemplate = new NFTemplate(packedTemplate);
        if (nfTemplate == null) {
            return null;
        }
        final FMRecord fmRecord = new FMRecord(nfTemplate, this.standard);
        if (fmRecord == null) {
            return null;
        }
        final ByteBuffer save = fmRecord.save();
        this.releaseBSSLicense();
        return save;
    }
    
    private ByteBuffer convertToISOImage(final NFPosition nfPosition) {
        return null;
    }
    
    public void setTimeOut(final int time_out) {
        this.time_out = time_out;
    }
    
    public void setBiometricMode(final String biometric_mode) {
        if (biometric_mode != null) {
            this.biometric_mode = biometric_mode;
        }
    }
    
    public void setUseMode(final String s) {
        if (s.trim().equalsIgnoreCase("verification")) {
            this.gui_use_mode_enrollment = false;
        }
        else {
            this.gui_use_mode_enrollment = true;
        }
    }
    
    public void scan(final NFPosition nfPosition) throws Exception {
        this.scanned_buffer = null;
        if (this.biometric_mode != null) {
            this.image_display_buffer = this.scanImage(this.time_out, "bmp");
            if (this.image_display_buffer != null) {
                if (this.biometric_mode.equalsIgnoreCase("BMP_IMAGE")) {
                    this.scanned_buffer = this.image_display_buffer;
                }
                if (this.biometric_mode.equalsIgnoreCase("ISO_IMAGE")) {
                    this.scanned_buffer = this.convertToISOImage(nfPosition);
                }
                if (this.biometric_mode.equalsIgnoreCase("ISO_TEMPLATE")) {
                    this.scanned_buffer = this.extractISOTemplate(nfPosition);
                }
                if (this.biometric_mode.equalsIgnoreCase("PACKED_TEMPLATE")) {
                    this.scanned_buffer = this.extractPackedTemplate(nfPosition);
                }
                if (this.scanned_buffer != null) {
                    this.is_scanned = true;
                }
            }
            else {
                this.is_scanned = false;
            }
        }
    }
    
    public void setNeurotecLicenseObtained(final boolean isNeurotecLicenseObtained) {
        this.isNeurotecLicenseObtained = isNeurotecLicenseObtained;
    }
    
    public boolean getNeurotecLicenseObtained() {
        return this.isNeurotecLicenseObtained;
    }
    
    public ByteBuffer getImageDisplayData() {
        return this.image_display_buffer;
    }
    
    public ByteBuffer getData() {
        return this.scanned_buffer;
    }
    
    public boolean isScanned() {
        return this.is_scanned;
    }
    
    public int getImageHeight() {
        return this.image_height;
    }
    
    public int getImageWidth() {
        return this.image_width;
    }
    
    public int getImageQuality() {
        switch (this.NFIQ) {
            case POOR: {
                return 1;
            }
            case FAIR: {
                return 2;
            }
            case GOOD: {
                return 3;
            }
            case VERY_GOOD: {
                return 4;
            }
            case EXCELLENT: {
                return 5;
            }
            default: {
                return 0;
            }
        }
    }
    
    public void setScannedStatus(final boolean is_scanned) {
        this.is_scanned = is_scanned;
    }
}
