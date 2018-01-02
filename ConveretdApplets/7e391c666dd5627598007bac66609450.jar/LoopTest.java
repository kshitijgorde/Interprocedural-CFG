// 
// Decompiled by Procyon v0.5.30
// 

public class LoopTest
{
    public static final boolean DEBUG = true;
    
    public static int LargestNumber_While() {
        final int[] array = { 0 };
        int count = 0;
        int i = 0;
        int largest = array[i];
        while (++i < array.length) {
            ++count;
            if (array[i] > largest) {
                largest = array[i];
            }
        }
        System.out.println("largest = " + largest);
        System.out.println("count = " + count);
        return largest;
    }
    
    public static int LargestNumber_Repeat() {
        final int[] array = { 0 };
        int count = 0;
        int i = 0;
        int largest = array[i];
        do {
            ++count;
            if (array[i] > largest) {
                largest = array[i];
            }
        } while (++i < array.length);
        System.out.println("largest = " + largest);
        System.out.println("count = " + count);
        return largest;
    }
    
    public static int Euclid_While(int a, int b) {
        int count = 0;
        for (int r = a % b; (r = a % b) != 0; a = b, b = r) {
            ++count;
        }
        System.out.println("GCD = " + b);
        System.out.println("count = " + count);
        return b;
    }
    
    public static int Euclid_Repeat(int a, int b) {
        int count = 0;
        int r = a % b;
        if (r == 0) {
            return b;
        }
        do {
            ++count;
            a = b;
            b = r;
        } while ((r = a % b) != 0);
        System.out.println("GCD = " + b);
        System.out.println("count = " + count);
        return b;
    }
}
