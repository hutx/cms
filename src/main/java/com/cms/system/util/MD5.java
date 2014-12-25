package com.cms.system.util;

import java.security.MessageDigest;

public class MD5 {

    public static String getMD5(String value) {
        return getMD5(value, null);
    }

    public static String getMD5(String s, String s1) {
        byte abyte0[] = null;
        byte abyte1[] = null;
        if (s == null)
            return null;
        abyte0 = s.getBytes();
        if (s1 != null)
            abyte1 = s1.getBytes();
        String s2 = "";
        try {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            messagedigest.update(abyte0);
            if (s1 != null)
                messagedigest.update(abyte1);
            s2 = toHex(messagedigest.digest());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return s2;
    }

    private static String toHex(byte abyte0[]) {
        StringBuffer stringbuffer = new StringBuffer();
        for (int i = 0; i < abyte0.length; i++) {
            String s = Integer.toHexString(abyte0[i] & 0xff);
            if (s.length() < 2) {
                stringbuffer.append("0");
            }
            stringbuffer.append(s);
        }

        return stringbuffer.toString();
    }

}
