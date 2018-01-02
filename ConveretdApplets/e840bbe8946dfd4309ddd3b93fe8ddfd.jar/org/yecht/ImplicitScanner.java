// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht;

public class ImplicitScanner
{
    public static String typeIdToUri(final String type_id) {
        byte[] data = null;
        try {
            data = type_id.getBytes("ISO8859-1");
        }
        catch (Exception ex) {}
        final byte[] dx = new byte[data.length + 1];
        System.arraycopy(data, 0, dx, 0, data.length);
        dx[data.length] = 0;
        data = dx;
        int cursor = 0;
        final int limit = data.length;
        int marker = -1;
        int gotoPoint = -1;
        byte yych = 0;
        while (gotoPoint != -2) {
            final int currentGoto = gotoPoint;
            gotoPoint = -2;
            switch (currentGoto) {
                case -1: {
                    yych = data[cursor];
                    switch (yych) {
                        case 0: {
                            gotoPoint = 2;
                            continue;
                        }
                        case 33: {
                            gotoPoint = 6;
                            continue;
                        }
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                        case 65:
                        case 66:
                        case 67:
                        case 68:
                        case 69:
                        case 70:
                        case 71:
                        case 72:
                        case 73:
                        case 74:
                        case 75:
                        case 76:
                        case 77:
                        case 78:
                        case 79:
                        case 80:
                        case 81:
                        case 82:
                        case 83:
                        case 84:
                        case 85:
                        case 86:
                        case 87:
                        case 88:
                        case 89:
                        case 90:
                        case 95:
                        case 97:
                        case 98:
                        case 99:
                        case 100:
                        case 101:
                        case 102:
                        case 103:
                        case 104:
                        case 105:
                        case 106:
                        case 107:
                        case 108:
                        case 109:
                        case 110:
                        case 111:
                        case 112:
                        case 113:
                        case 114:
                        case 115:
                        case 117:
                        case 118:
                        case 119:
                        case 121:
                        case 122: {
                            gotoPoint = 8;
                            continue;
                        }
                        case 116: {
                            gotoPoint = 3;
                            continue;
                        }
                        case 120: {
                            gotoPoint = 5;
                            continue;
                        }
                        default: {
                            gotoPoint = 9;
                            continue;
                        }
                    }
                    break;
                }
                case 2: {
                    cursor = marker;
                    gotoPoint = 4;
                    continue;
                }
                case 3: {
                    yych = data[marker = ++cursor];
                    switch (yych) {
                        case 44: {
                            gotoPoint = 14;
                            continue;
                        }
                        case 45: {
                            gotoPoint = 10;
                            continue;
                        }
                        case 46: {
                            gotoPoint = 15;
                            continue;
                        }
                        case 47: {
                            gotoPoint = 16;
                            continue;
                        }
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                        case 65:
                        case 66:
                        case 67:
                        case 68:
                        case 69:
                        case 70:
                        case 71:
                        case 72:
                        case 73:
                        case 74:
                        case 75:
                        case 76:
                        case 77:
                        case 78:
                        case 79:
                        case 80:
                        case 81:
                        case 82:
                        case 83:
                        case 84:
                        case 85:
                        case 86:
                        case 87:
                        case 88:
                        case 89:
                        case 90:
                        case 95:
                        case 98:
                        case 99:
                        case 100:
                        case 101:
                        case 102:
                        case 103:
                        case 104:
                        case 105:
                        case 106:
                        case 107:
                        case 108:
                        case 109:
                        case 110:
                        case 111:
                        case 112:
                        case 113:
                        case 114:
                        case 115:
                        case 116:
                        case 117:
                        case 118:
                        case 119:
                        case 120:
                        case 121:
                        case 122: {
                            gotoPoint = 12;
                            continue;
                        }
                        case 97: {
                            gotoPoint = 44;
                            continue;
                        }
                        default: {
                            gotoPoint = 4;
                            continue;
                        }
                    }
                    break;
                }
                case 4: {
                    return Parser.taguri("yaml.org,2002", type_id);
                }
                case 5: {
                    yych = data[marker = ++cursor];
                    switch (yych) {
                        case 44:
                        case 46:
                        case 47:
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                        case 65:
                        case 66:
                        case 67:
                        case 68:
                        case 69:
                        case 70:
                        case 71:
                        case 72:
                        case 73:
                        case 74:
                        case 75:
                        case 76:
                        case 77:
                        case 78:
                        case 79:
                        case 80:
                        case 81:
                        case 82:
                        case 83:
                        case 84:
                        case 85:
                        case 86:
                        case 87:
                        case 88:
                        case 89:
                        case 90:
                        case 95:
                        case 97:
                        case 98:
                        case 99:
                        case 100:
                        case 101:
                        case 102:
                        case 103:
                        case 104:
                        case 105:
                        case 106:
                        case 107:
                        case 108:
                        case 109:
                        case 110:
                        case 111:
                        case 112:
                        case 113:
                        case 114:
                        case 115:
                        case 116:
                        case 117:
                        case 118:
                        case 119:
                        case 120:
                        case 121:
                        case 122: {
                            gotoPoint = 13;
                            continue;
                        }
                        case 45: {
                            gotoPoint = 34;
                            continue;
                        }
                        default: {
                            gotoPoint = 4;
                            continue;
                        }
                    }
                    break;
                }
                case 6: {
                    ++cursor;
                    return Parser.xprivate(type_id.substring(1));
                }
                case 8: {
                    yych = data[marker = ++cursor];
                    switch (yych) {
                        case 44: {
                            gotoPoint = 14;
                            continue;
                        }
                        case 45: {
                            gotoPoint = 10;
                            continue;
                        }
                        case 46: {
                            gotoPoint = 15;
                            continue;
                        }
                        case 47: {
                            gotoPoint = 16;
                            continue;
                        }
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                        case 65:
                        case 66:
                        case 67:
                        case 68:
                        case 69:
                        case 70:
                        case 71:
                        case 72:
                        case 73:
                        case 74:
                        case 75:
                        case 76:
                        case 77:
                        case 78:
                        case 79:
                        case 80:
                        case 81:
                        case 82:
                        case 83:
                        case 84:
                        case 85:
                        case 86:
                        case 87:
                        case 88:
                        case 89:
                        case 90:
                        case 95:
                        case 97:
                        case 98:
                        case 99:
                        case 100:
                        case 101:
                        case 102:
                        case 103:
                        case 104:
                        case 105:
                        case 106:
                        case 107:
                        case 108:
                        case 109:
                        case 110:
                        case 111:
                        case 112:
                        case 113:
                        case 114:
                        case 115:
                        case 116:
                        case 117:
                        case 118:
                        case 119:
                        case 120:
                        case 121:
                        case 122: {
                            gotoPoint = 12;
                            continue;
                        }
                        default: {
                            gotoPoint = 4;
                            continue;
                        }
                    }
                    break;
                }
                case 9: {
                    yych = data[++cursor];
                    gotoPoint = 4;
                    continue;
                }
                case 10: {
                    ++cursor;
                    yych = data[cursor];
                }
                case 11: {
                    switch (yych) {
                        case 45: {
                            gotoPoint = 10;
                            continue;
                        }
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                        case 65:
                        case 66:
                        case 67:
                        case 68:
                        case 69:
                        case 70:
                        case 71:
                        case 72:
                        case 73:
                        case 74:
                        case 75:
                        case 76:
                        case 77:
                        case 78:
                        case 79:
                        case 80:
                        case 81:
                        case 82:
                        case 83:
                        case 84:
                        case 85:
                        case 86:
                        case 87:
                        case 88:
                        case 89:
                        case 90:
                        case 95:
                        case 97:
                        case 98:
                        case 99:
                        case 100:
                        case 101:
                        case 102:
                        case 103:
                        case 104:
                        case 105:
                        case 106:
                        case 107:
                        case 108:
                        case 109:
                        case 110:
                        case 111:
                        case 112:
                        case 113:
                        case 114:
                        case 115:
                        case 116:
                        case 117:
                        case 118:
                        case 119:
                        case 120:
                        case 121:
                        case 122: {
                            gotoPoint = 12;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 12: {
                    ++cursor;
                    yych = data[cursor];
                }
                case 13: {
                    switch (yych) {
                        case 44: {
                            gotoPoint = 14;
                            continue;
                        }
                        case 45: {
                            gotoPoint = 10;
                            continue;
                        }
                        case 46: {
                            gotoPoint = 15;
                            continue;
                        }
                        case 47: {
                            gotoPoint = 16;
                            continue;
                        }
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                        case 65:
                        case 66:
                        case 67:
                        case 68:
                        case 69:
                        case 70:
                        case 71:
                        case 72:
                        case 73:
                        case 74:
                        case 75:
                        case 76:
                        case 77:
                        case 78:
                        case 79:
                        case 80:
                        case 81:
                        case 82:
                        case 83:
                        case 84:
                        case 85:
                        case 86:
                        case 87:
                        case 88:
                        case 89:
                        case 90:
                        case 95:
                        case 97:
                        case 98:
                        case 99:
                        case 100:
                        case 101:
                        case 102:
                        case 103:
                        case 104:
                        case 105:
                        case 106:
                        case 107:
                        case 108:
                        case 109:
                        case 110:
                        case 111:
                        case 112:
                        case 113:
                        case 114:
                        case 115:
                        case 116:
                        case 117:
                        case 118:
                        case 119:
                        case 120:
                        case 121:
                        case 122: {
                            gotoPoint = 12;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 14: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57: {
                            gotoPoint = 22;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 15: {
                    ++cursor;
                    yych = data[cursor];
                    switch (yych) {
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                        case 65:
                        case 66:
                        case 67:
                        case 68:
                        case 69:
                        case 70:
                        case 71:
                        case 72:
                        case 73:
                        case 74:
                        case 75:
                        case 76:
                        case 77:
                        case 78:
                        case 79:
                        case 80:
                        case 81:
                        case 82:
                        case 83:
                        case 84:
                        case 85:
                        case 86:
                        case 87:
                        case 88:
                        case 89:
                        case 90:
                        case 95:
                        case 97:
                        case 98:
                        case 99:
                        case 100:
                        case 101:
                        case 102:
                        case 103:
                        case 104:
                        case 105:
                        case 106:
                        case 107:
                        case 108:
                        case 109:
                        case 110:
                        case 111:
                        case 112:
                        case 113:
                        case 114:
                        case 115:
                        case 116:
                        case 117:
                        case 118:
                        case 119:
                        case 120:
                        case 121:
                        case 122: {
                            gotoPoint = 18;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 16: {
                    ++cursor;
                    return Parser.taguri(type_id.substring(0, cursor - 1) + "." + "yaml.org,2002", type_id.substring(cursor));
                }
                case 18: {
                    ++cursor;
                    yych = data[cursor];
                    switch (yych) {
                        case 44: {
                            gotoPoint = 14;
                            continue;
                        }
                        case 45: {
                            gotoPoint = 20;
                            continue;
                        }
                        case 46: {
                            gotoPoint = 15;
                            continue;
                        }
                        case 47: {
                            gotoPoint = 16;
                            continue;
                        }
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                        case 65:
                        case 66:
                        case 67:
                        case 68:
                        case 69:
                        case 70:
                        case 71:
                        case 72:
                        case 73:
                        case 74:
                        case 75:
                        case 76:
                        case 77:
                        case 78:
                        case 79:
                        case 80:
                        case 81:
                        case 82:
                        case 83:
                        case 84:
                        case 85:
                        case 86:
                        case 87:
                        case 88:
                        case 89:
                        case 90:
                        case 95:
                        case 97:
                        case 98:
                        case 99:
                        case 100:
                        case 101:
                        case 102:
                        case 103:
                        case 104:
                        case 105:
                        case 106:
                        case 107:
                        case 108:
                        case 109:
                        case 110:
                        case 111:
                        case 112:
                        case 113:
                        case 114:
                        case 115:
                        case 116:
                        case 117:
                        case 118:
                        case 119:
                        case 120:
                        case 121:
                        case 122: {
                            gotoPoint = 18;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 20: {
                    ++cursor;
                    yych = data[cursor];
                    switch (yych) {
                        case 45: {
                            gotoPoint = 20;
                            continue;
                        }
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                        case 65:
                        case 66:
                        case 67:
                        case 68:
                        case 69:
                        case 70:
                        case 71:
                        case 72:
                        case 73:
                        case 74:
                        case 75:
                        case 76:
                        case 77:
                        case 78:
                        case 79:
                        case 80:
                        case 81:
                        case 82:
                        case 83:
                        case 84:
                        case 85:
                        case 86:
                        case 87:
                        case 88:
                        case 89:
                        case 90:
                        case 95:
                        case 97:
                        case 98:
                        case 99:
                        case 100:
                        case 101:
                        case 102:
                        case 103:
                        case 104:
                        case 105:
                        case 106:
                        case 107:
                        case 108:
                        case 109:
                        case 110:
                        case 111:
                        case 112:
                        case 113:
                        case 114:
                        case 115:
                        case 116:
                        case 117:
                        case 118:
                        case 119:
                        case 120:
                        case 121:
                        case 122: {
                            gotoPoint = 18;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 22: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57: {
                            gotoPoint = 23;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 23: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57: {
                            gotoPoint = 24;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 24: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57: {
                            gotoPoint = 25;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 25: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 45: {
                            gotoPoint = 26;
                            continue;
                        }
                        case 47: {
                            gotoPoint = 27;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 26: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57: {
                            gotoPoint = 29;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 27: {
                    ++cursor;
                    return Parser.taguri(type_id.substring(0, cursor - 1), type_id.substring(cursor));
                }
                case 29: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57: {
                            gotoPoint = 30;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 30: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 45: {
                            gotoPoint = 31;
                            continue;
                        }
                        case 47: {
                            gotoPoint = 27;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 31: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57: {
                            gotoPoint = 32;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 32: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57: {
                            gotoPoint = 33;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 33: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 47: {
                            gotoPoint = 27;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 34: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 112: {
                            gotoPoint = 35;
                            continue;
                        }
                        default: {
                            gotoPoint = 11;
                            continue;
                        }
                    }
                    break;
                }
                case 35: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 44: {
                            gotoPoint = 14;
                            continue;
                        }
                        case 46: {
                            gotoPoint = 15;
                            continue;
                        }
                        case 47: {
                            gotoPoint = 16;
                            continue;
                        }
                        case 114: {
                            gotoPoint = 36;
                            continue;
                        }
                        default: {
                            gotoPoint = 11;
                            continue;
                        }
                    }
                    break;
                }
                case 36: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 44: {
                            gotoPoint = 14;
                            continue;
                        }
                        case 46: {
                            gotoPoint = 15;
                            continue;
                        }
                        case 47: {
                            gotoPoint = 16;
                            continue;
                        }
                        case 105: {
                            gotoPoint = 37;
                            continue;
                        }
                        default: {
                            gotoPoint = 11;
                            continue;
                        }
                    }
                    break;
                }
                case 37: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 44: {
                            gotoPoint = 14;
                            continue;
                        }
                        case 46: {
                            gotoPoint = 15;
                            continue;
                        }
                        case 47: {
                            gotoPoint = 16;
                            continue;
                        }
                        case 118: {
                            gotoPoint = 38;
                            continue;
                        }
                        default: {
                            gotoPoint = 11;
                            continue;
                        }
                    }
                    break;
                }
                case 38: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 44: {
                            gotoPoint = 14;
                            continue;
                        }
                        case 46: {
                            gotoPoint = 15;
                            continue;
                        }
                        case 47: {
                            gotoPoint = 16;
                            continue;
                        }
                        case 97: {
                            gotoPoint = 39;
                            continue;
                        }
                        default: {
                            gotoPoint = 11;
                            continue;
                        }
                    }
                    break;
                }
                case 39: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 44: {
                            gotoPoint = 14;
                            continue;
                        }
                        case 46: {
                            gotoPoint = 15;
                            continue;
                        }
                        case 47: {
                            gotoPoint = 16;
                            continue;
                        }
                        case 116: {
                            gotoPoint = 40;
                            continue;
                        }
                        default: {
                            gotoPoint = 11;
                            continue;
                        }
                    }
                    break;
                }
                case 40: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 44: {
                            gotoPoint = 14;
                            continue;
                        }
                        case 46: {
                            gotoPoint = 15;
                            continue;
                        }
                        case 47: {
                            gotoPoint = 16;
                            continue;
                        }
                        case 101: {
                            gotoPoint = 41;
                            continue;
                        }
                        default: {
                            gotoPoint = 11;
                            continue;
                        }
                    }
                    break;
                }
                case 41: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 44: {
                            gotoPoint = 14;
                            continue;
                        }
                        case 46: {
                            gotoPoint = 15;
                            continue;
                        }
                        case 47: {
                            gotoPoint = 16;
                            continue;
                        }
                        case 58: {
                            gotoPoint = 42;
                            continue;
                        }
                        default: {
                            gotoPoint = 11;
                            continue;
                        }
                    }
                    break;
                }
                case 42: {
                    ++cursor;
                    return type_id;
                }
                case 44: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 44: {
                            gotoPoint = 14;
                            continue;
                        }
                        case 46: {
                            gotoPoint = 15;
                            continue;
                        }
                        case 47: {
                            gotoPoint = 16;
                            continue;
                        }
                        case 103: {
                            gotoPoint = 45;
                            continue;
                        }
                        default: {
                            gotoPoint = 11;
                            continue;
                        }
                    }
                    break;
                }
                case 45: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 44: {
                            gotoPoint = 14;
                            continue;
                        }
                        case 46: {
                            gotoPoint = 15;
                            continue;
                        }
                        case 47: {
                            gotoPoint = 16;
                            continue;
                        }
                        case 58: {
                            gotoPoint = 46;
                            continue;
                        }
                        default: {
                            gotoPoint = 11;
                            continue;
                        }
                    }
                    break;
                }
                case 46: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 44:
                        case 45:
                        case 46: {
                            gotoPoint = 2;
                            continue;
                        }
                        default: {
                            gotoPoint = 48;
                            continue;
                        }
                    }
                    break;
                }
                case 47: {
                    ++cursor;
                    yych = data[cursor];
                }
                case 48: {
                    switch (yych) {
                        case 44: {
                            gotoPoint = 51;
                            continue;
                        }
                        case 45: {
                            gotoPoint = 49;
                            continue;
                        }
                        case 46: {
                            gotoPoint = 52;
                            continue;
                        }
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                        case 65:
                        case 66:
                        case 67:
                        case 68:
                        case 69:
                        case 70:
                        case 71:
                        case 72:
                        case 73:
                        case 74:
                        case 75:
                        case 76:
                        case 77:
                        case 78:
                        case 79:
                        case 80:
                        case 81:
                        case 82:
                        case 83:
                        case 84:
                        case 85:
                        case 86:
                        case 87:
                        case 88:
                        case 89:
                        case 90:
                        case 95:
                        case 97:
                        case 98:
                        case 99:
                        case 100:
                        case 101:
                        case 102:
                        case 103:
                        case 104:
                        case 105:
                        case 106:
                        case 107:
                        case 108:
                        case 109:
                        case 110:
                        case 111:
                        case 112:
                        case 113:
                        case 114:
                        case 115:
                        case 116:
                        case 117:
                        case 118:
                        case 119:
                        case 120:
                        case 121:
                        case 122: {
                            gotoPoint = 47;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 49: {
                    ++cursor;
                    yych = data[cursor];
                    switch (yych) {
                        case 45: {
                            gotoPoint = 49;
                            continue;
                        }
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                        case 65:
                        case 66:
                        case 67:
                        case 68:
                        case 69:
                        case 70:
                        case 71:
                        case 72:
                        case 73:
                        case 74:
                        case 75:
                        case 76:
                        case 77:
                        case 78:
                        case 79:
                        case 80:
                        case 81:
                        case 82:
                        case 83:
                        case 84:
                        case 85:
                        case 86:
                        case 87:
                        case 88:
                        case 89:
                        case 90:
                        case 95:
                        case 97:
                        case 98:
                        case 99:
                        case 100:
                        case 101:
                        case 102:
                        case 103:
                        case 104:
                        case 105:
                        case 106:
                        case 107:
                        case 108:
                        case 109:
                        case 110:
                        case 111:
                        case 112:
                        case 113:
                        case 114:
                        case 115:
                        case 116:
                        case 117:
                        case 118:
                        case 119:
                        case 120:
                        case 121:
                        case 122: {
                            gotoPoint = 47;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 51: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57: {
                            gotoPoint = 57;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 52: {
                    ++cursor;
                    yych = data[cursor];
                    switch (yych) {
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                        case 65:
                        case 66:
                        case 67:
                        case 68:
                        case 69:
                        case 70:
                        case 71:
                        case 72:
                        case 73:
                        case 74:
                        case 75:
                        case 76:
                        case 77:
                        case 78:
                        case 79:
                        case 80:
                        case 81:
                        case 82:
                        case 83:
                        case 84:
                        case 85:
                        case 86:
                        case 87:
                        case 88:
                        case 89:
                        case 90:
                        case 95:
                        case 97:
                        case 98:
                        case 99:
                        case 100:
                        case 101:
                        case 102:
                        case 103:
                        case 104:
                        case 105:
                        case 106:
                        case 107:
                        case 108:
                        case 109:
                        case 110:
                        case 111:
                        case 112:
                        case 113:
                        case 114:
                        case 115:
                        case 116:
                        case 117:
                        case 118:
                        case 119:
                        case 120:
                        case 121:
                        case 122: {
                            gotoPoint = 53;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 53: {
                    ++cursor;
                    yych = data[cursor];
                    switch (yych) {
                        case 44: {
                            gotoPoint = 51;
                            continue;
                        }
                        case 45: {
                            gotoPoint = 55;
                            continue;
                        }
                        case 46: {
                            gotoPoint = 52;
                            continue;
                        }
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                        case 65:
                        case 66:
                        case 67:
                        case 68:
                        case 69:
                        case 70:
                        case 71:
                        case 72:
                        case 73:
                        case 74:
                        case 75:
                        case 76:
                        case 77:
                        case 78:
                        case 79:
                        case 80:
                        case 81:
                        case 82:
                        case 83:
                        case 84:
                        case 85:
                        case 86:
                        case 87:
                        case 88:
                        case 89:
                        case 90:
                        case 95:
                        case 97:
                        case 98:
                        case 99:
                        case 100:
                        case 101:
                        case 102:
                        case 103:
                        case 104:
                        case 105:
                        case 106:
                        case 107:
                        case 108:
                        case 109:
                        case 110:
                        case 111:
                        case 112:
                        case 113:
                        case 114:
                        case 115:
                        case 116:
                        case 117:
                        case 118:
                        case 119:
                        case 120:
                        case 121:
                        case 122: {
                            gotoPoint = 53;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 55: {
                    ++cursor;
                    yych = data[cursor];
                    switch (yych) {
                        case 45: {
                            gotoPoint = 55;
                            continue;
                        }
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                        case 65:
                        case 66:
                        case 67:
                        case 68:
                        case 69:
                        case 70:
                        case 71:
                        case 72:
                        case 73:
                        case 74:
                        case 75:
                        case 76:
                        case 77:
                        case 78:
                        case 79:
                        case 80:
                        case 81:
                        case 82:
                        case 83:
                        case 84:
                        case 85:
                        case 86:
                        case 87:
                        case 88:
                        case 89:
                        case 90:
                        case 95:
                        case 97:
                        case 98:
                        case 99:
                        case 100:
                        case 101:
                        case 102:
                        case 103:
                        case 104:
                        case 105:
                        case 106:
                        case 107:
                        case 108:
                        case 109:
                        case 110:
                        case 111:
                        case 112:
                        case 113:
                        case 114:
                        case 115:
                        case 116:
                        case 117:
                        case 118:
                        case 119:
                        case 120:
                        case 121:
                        case 122: {
                            gotoPoint = 53;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 57: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57: {
                            gotoPoint = 58;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 58: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57: {
                            gotoPoint = 59;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 59: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57: {
                            gotoPoint = 60;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 60: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 45: {
                            gotoPoint = 61;
                            continue;
                        }
                        case 58: {
                            gotoPoint = 62;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 61: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57: {
                            gotoPoint = 64;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 62: {
                    ++cursor;
                    return type_id;
                }
                case 64: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57: {
                            gotoPoint = 65;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 65: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 45: {
                            gotoPoint = 66;
                            continue;
                        }
                        case 58: {
                            gotoPoint = 62;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 66: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57: {
                            gotoPoint = 67;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 67: {
                    yych = data[++cursor];
                    switch (yych) {
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                        case 56:
                        case 57: {
                            gotoPoint = 68;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                case 68: {
                    ++cursor;
                    switch (yych = data[cursor]) {
                        case 58: {
                            gotoPoint = 62;
                            continue;
                        }
                        default: {
                            gotoPoint = 2;
                            continue;
                        }
                    }
                    break;
                }
                default: {
                    continue;
                }
            }
        }
        return null;
    }
}
