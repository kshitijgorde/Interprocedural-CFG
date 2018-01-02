// 
// Decompiled by Procyon v0.5.30
// 

package classes.testing;

import java.nio.ByteBuffer;
import java.lang.management.ManagementFactory;
import classes.anaxee.neurotec.SaveImage;
import com.neurotec.biometrics.NFPosition;
import classes.anaxee.neurotec.JavaDevice;

class RunFromConsole
{
    public static void main(final String[] array) {
        System.out.println("normal java execution");
        final JavaDevice javaDevice = new JavaDevice();
        System.out.println("init status " + javaDevice.init());
        javaDevice.checkConnectedDevices();
        System.out.println("device : " + javaDevice.getDeviceName(0));
        javaDevice.setBiometricMode("BMP_IMAGE");
        javaDevice.scan(NFPosition.UNKNOWN);
        final ByteBuffer data = javaDevice.getData();
        final SaveImage saveImage = new SaveImage();
        saveImage.saveImageFromByteBuffer(data, "RawImage.bmp");
        javaDevice.setBiometricMode("PACKED_TEMPLATE");
        javaDevice.scan(NFPosition.UNKNOWN);
        saveImage.saveImageFromByteBuffer(javaDevice.getData(), "PackedTemplate.dat");
        javaDevice.setBiometricMode("ISO_TEMPLATE");
        javaDevice.scan(NFPosition.UNKNOWN);
        saveImage.saveImageFromByteBuffer(javaDevice.getData(), "kalyaniTem.dat");
        try {
            Thread.sleep(10000L);
            final String name = ManagementFactory.getRuntimeMXBean().getName();
            Runtime.getRuntime().exec("taskkill /f /pid " + name.substring(0, name.indexOf(64)));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
