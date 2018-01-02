// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.impl.C;

import jmaster.jumploader.model.api.config.UploaderConfig;
import jmaster.jumploader.model.impl.upload.E;
import jmaster.util.swing.GUIHelper;
import jmaster.util.log.A;

public class B
{
    public static final String S = "uploader.errorFileNotFound";
    public static final String N = "uploader.errorFileTooLengthy";
    public static final String G = "uploader.errorFileTooSmall";
    public static final String K = "uploader.errorTooManyFiles";
    public static final String H = "uploader.errorFileNamePatternMismatch";
    public static final String P = "uploader.errorFilesLengthQuota";
    public static final String R = "uploader.errorDirectoriesNotAllowed";
    public static final String O = "uploader.errorDuplicateFileNotAllowed";
    public static final String U = "uploader.errorBadServerResponse";
    public static final String T = "uploader.errorNetworkFailure";
    public static final String V = "uploader.errorAborted";
    public static final String _ = "uploader.errorFileAccessFailure";
    public static final String Y = "uploader.errorUnexpectedFailure";
    public static final String D = "uploader.errorNotImage";
    public static final String A = "uploader.errorImageTooSmall";
    public static final String M = "uploader.errorImageTooBig";
    public static final String C = "uploader.errorMimeTypePatternMismatch";
    public static final String L = "\\$\\{file.path\\}";
    public static final String W = "\\$\\{file.name\\}";
    public static final String X = "\\$\\{file.length\\}";
    public static final String B = "\\$\\{file.index\\}";
    public static final String I = "\\$\\{uc.maxLength\\}";
    public static final String Z = "\\$\\{uc.minFileLength\\}";
    public static final String F = "\\$\\{uc.maxFiles\\}";
    public static final String E = "\\$\\{uc.maxFileLength\\}";
    protected A J;
    protected GUIHelper Q;
    
    public B() {
        this.J = jmaster.util.log.B.getInstance().getLog(this.getClass());
        this.Q = GUIHelper.getInstance();
    }
    
    public String A(final E e, final UploaderConfig uploaderConfig, final jmaster.jumploader.model.impl.upload.B b, final String s) {
        String s2 = this.Q.getProperty().Q(s);
        if (b != null) {
            s2 = s2.replaceAll("\\$\\{file.path\\}", A(b.getPath())).replaceAll("\\$\\{file.name\\}", A(b.getName())).replaceAll("\\$\\{file.length\\}", A(this.Q.getLengthFormatted(b.getLength()))).replaceAll("\\$\\{file.index\\}", A("" + b.getIndex()));
        }
        if (uploaderConfig != null) {
            s2 = s2.replaceAll("\\$\\{uc.maxLength\\}", A(this.Q.getLengthFormatted(uploaderConfig.getMaxLength()))).replaceAll("\\$\\{uc.maxFiles\\}", A("" + uploaderConfig.getMaxFiles())).replaceAll("\\$\\{uc.maxFileLength\\}", A(this.Q.getLengthFormatted(uploaderConfig.getMaxFileLength()))).replaceAll("\\$\\{uc.minFileLength\\}", A(this.Q.getLengthFormatted(uploaderConfig.getMinFileLength())));
        }
        return s2;
    }
    
    public static String A(final String s) {
        if (s.indexOf(92) == -1 && s.indexOf(36) == -1) {
            return s;
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '\\') {
                sb.append('\\');
                sb.append('\\');
            }
            else if (char1 == '$') {
                sb.append('\\');
                sb.append('$');
            }
            else {
                sb.append(char1);
            }
        }
        return sb.toString();
    }
}
