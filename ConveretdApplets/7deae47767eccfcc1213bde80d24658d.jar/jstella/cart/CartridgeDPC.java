// 
// Decompiled by Procyon v0.5.30
// 

package jstella.cart;

import jstella.core.JSSystem;
import java.util.Random;

public class CartridgeDPC extends Cartridge
{
    private static final long serialVersionUID = -7081138710236297651L;
    private static final int[] f;
    private static final int[] musicAmplitudes;
    private Random myRandomGenerator;
    int myCurrentBank;
    int[] myProgramImage;
    int[] myDisplayImage;
    int[] myImageCopy;
    int[] myTops;
    int[] myBottoms;
    int[] myCounters;
    int[] myFlags;
    boolean[] myMusicMode;
    int myRandomNumber;
    int mySystemCycles;
    double myFractionalClocks;
    
    public CartridgeDPC(final int[] image) {
        this.myRandomGenerator = new Random();
        this.myProgramImage = new int[8192];
        this.myDisplayImage = new int[2048];
        this.myImageCopy = new int[10495];
        this.myTops = new int[8];
        this.myBottoms = new int[8];
        this.myCounters = new int[8];
        this.myFlags = new int[8];
        this.myMusicMode = new boolean[3];
        this.myRandomNumber = 0;
        this.mySystemCycles = 0;
        this.myFractionalClocks = 0.0;
        this.myImageCopy = Cartridge.copyImage(image);
        for (int addr = 0; addr < 8192; ++addr) {
            this.myProgramImage[addr] = image[addr];
        }
        for (int addr = 0; addr < 2048; ++addr) {
            this.myDisplayImage[addr] = image[8192 + addr];
        }
        for (int i = 0; i < 8; ++i) {
            final int[] myTops = this.myTops;
            final int n = i;
            final int[] myBottoms = this.myBottoms;
            final int n2 = i;
            final int[] myCounters = this.myCounters;
            final int n3 = i;
            final int[] myFlags = this.myFlags;
            final int n4 = i;
            final boolean b = false;
            myCounters[n3] = (myFlags[n4] = (b ? 1 : 0));
            myTops[n] = (myBottoms[n2] = (b ? 1 : 0));
        }
        final boolean[] myMusicMode = this.myMusicMode;
        final int n5 = 0;
        final boolean[] myMusicMode2 = this.myMusicMode;
        final int n6 = 1;
        final boolean[] myMusicMode3 = this.myMusicMode;
        final int n7 = 2;
        final boolean b2 = false;
        myMusicMode3[n7] = b2;
        myMusicMode[n5] = (myMusicMode2[n6] = b2);
        this.myRandomNumber = 1;
        this.mySystemCycles = 0;
        this.myFractionalClocks = 0.0;
    }
    
    public String name() {
        return "CartridgeDPC";
    }
    
    public void reset() {
        this.mySystemCycles = this.mySystem.getCycles();
        this.myFractionalClocks = 0.0;
        this.setCurrentBank(1);
    }
    
    public void systemCyclesReset() {
        final int cycles = this.mySystem.getCycles();
        this.mySystemCycles -= cycles;
    }
    
    public void install(final JSSystem system) {
        this.mySystem = system;
        this.addIndirectAccess(8184, 8192);
        this.addIndirectAccess(4096, 4224);
        this.setCurrentBank(1);
    }
    
    private boolean bool(final int aValue) {
        return aValue != 0;
    }
    
    private void clockRandomNumberGenerator() {
        final int bit = CartridgeDPC.f[(this.myRandomNumber >> 3 & 0x7) | (this.bool(this.myRandomNumber & 0x80) ? 8 : 0)];
        this.myRandomNumber = ((this.myRandomNumber << 1 | bit) & 0xFF);
    }
    
    private void updateMusicModeDataFetchers() {
        final int cycles = this.mySystem.getCycles() - this.mySystemCycles;
        this.mySystemCycles = this.mySystem.getCycles();
        final double clocks = 15750.0 * cycles / 1193191.66666667 + this.myFractionalClocks;
        final int wholeClocks = (int)clocks;
        this.myFractionalClocks = clocks - wholeClocks;
        if (wholeClocks <= 0) {
            return;
        }
        for (int x = 5; x <= 7; ++x) {
            if (this.myMusicMode[x - 5]) {
                final int top = this.myTops[x] + 1;
                int newLow = this.myCounters[x] & 0xFF;
                if (this.myTops[x] != 0) {
                    newLow -= wholeClocks % top;
                    if (newLow < 0) {
                        newLow += top;
                    }
                }
                else {
                    newLow = 0;
                }
                if (newLow <= this.myBottoms[x]) {
                    this.myFlags[x] = 0;
                }
                else if (newLow <= this.myTops[x]) {
                    this.myFlags[x] = 255;
                }
                this.myCounters[x] = ((this.myCounters[x] & 0x700) | newLow);
            }
        }
    }
    
    public int peek(final int address) {
        final int zNewAddress = address & 0xFFF;
        this.clockRandomNumberGenerator();
        if (zNewAddress < 64) {
            int result = 0;
            final int index = zNewAddress & 0x7;
            final int function = zNewAddress >> 3 & 0x7;
            if ((this.myCounters[index] & 0xFF) == this.myTops[index]) {
                this.myFlags[index] = 255;
            }
            else if ((this.myCounters[index] & 0xFF) == this.myBottoms[index]) {
                this.myFlags[index] = 0;
            }
            switch (function) {
                case 0: {
                    if (index < 4) {
                        result = this.myRandomNumber;
                        break;
                    }
                    this.updateMusicModeDataFetchers();
                    int i = 0;
                    if (this.myMusicMode[0] && this.bool(this.myFlags[5])) {
                        i |= 0x1;
                    }
                    if (this.myMusicMode[1] && this.bool(this.myFlags[6])) {
                        i |= 0x2;
                    }
                    if (this.myMusicMode[2] && this.bool(this.myFlags[7])) {
                        i |= 0x4;
                    }
                    result = CartridgeDPC.musicAmplitudes[i];
                    break;
                }
                case 1: {
                    result = this.myDisplayImage[2047 - this.myCounters[index]];
                    break;
                }
                case 2: {
                    result = (this.myDisplayImage[2047 - this.myCounters[index]] & this.myFlags[index]);
                    break;
                }
                case 7: {
                    result = this.myFlags[index];
                    break;
                }
                default: {
                    result = 0;
                    break;
                }
            }
            if (index < 5 || (index >= 5 && !this.myMusicMode[index - 5])) {
                this.myCounters[index] = (this.myCounters[index] - 1 & 0x7FF);
            }
            return result;
        }
        switch (zNewAddress) {
            case 4088: {
                this.setCurrentBank(0);
                break;
            }
            case 4089: {
                this.setCurrentBank(1);
                break;
            }
        }
        return this.myProgramImage[this.myCurrentBank * 4096 + zNewAddress];
    }
    
    public void poke(final int address, final int value) {
        assert value < 256 && value >= 0;
        final int zNewAddress = address & 0xFFF;
        this.clockRandomNumberGenerator();
        if (zNewAddress >= 64 && zNewAddress < 128) {
            final int index = zNewAddress & 0x7;
            final int function = zNewAddress >> 3 & 0x7;
            switch (function) {
                case 0: {
                    this.myTops[index] = value;
                    this.myFlags[index] = 0;
                    break;
                }
                case 1: {
                    this.myBottoms[index] = value;
                    break;
                }
                case 2: {
                    if (index >= 5 && this.myMusicMode[index - 5]) {
                        this.myCounters[index] = ((this.myCounters[index] & 0x700) | this.myTops[index]);
                        break;
                    }
                    this.myCounters[index] = ((this.myCounters[index] & 0x700) | value);
                    break;
                }
                case 3: {
                    this.myCounters[index] = ((value & 0x7) << 8 | (this.myCounters[index] & 0xFF));
                    if (index >= 5) {
                        this.myMusicMode[index - 5] = this.bool(value & 0x10);
                        break;
                    }
                    break;
                }
                case 6: {
                    this.myRandomNumber = 1;
                    break;
                }
            }
        }
        else {
            switch (zNewAddress) {
                case 4088: {
                    this.setCurrentBank(0);
                    break;
                }
                case 4089: {
                    this.setCurrentBank(1);
                    break;
                }
            }
        }
    }
    
    protected void setCurrentBank(final int bank) {
        if (this.myBankLocked) {
            return;
        }
        this.myCurrentBank = bank;
        this.addDirectPeekAccess(4224, 8184, this.myProgramImage, 4095, this.myCurrentBank * 4096);
    }
    
    protected int getCurrentBank() {
        return this.myCurrentBank;
    }
    
    protected int bankCount() {
        return 2;
    }
    
    public boolean patch(int address, final int value) {
        address = (char)(address & 0xFFF);
        this.myProgramImage[this.myCurrentBank * 4096 + address] = value;
        return true;
    }
    
    public int[] getImage() {
        for (int i = 0; i < 8192; ++i) {
            this.myImageCopy[i] = this.myProgramImage[i];
        }
        for (int i = 0; i < 2048; ++i) {
            this.myImageCopy[i + 8192] = this.myDisplayImage[i];
        }
        return this.myImageCopy;
    }
    
    static {
        f = new int[] { 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1 };
        musicAmplitudes = new int[] { 0, 4, 5, 9, 6, 10, 11, 15 };
    }
}
