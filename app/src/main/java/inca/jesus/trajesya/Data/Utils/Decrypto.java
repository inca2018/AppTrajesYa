package inca.jesus.trajesya.Data.Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Decrypto {
    private String iv = "fedcba9876543210";
    private String key = null;
    private Cipher cipher = null;
    private SecretKeySpec keySpec = null;
    private IvParameterSpec ivSpec = null;

    public Decrypto(String key) throws Exception {
        this.key = key;

// Make sure the key length should be 16
        int len = this.key.length();
        if(len < 16) {
            int addSpaces = 16 - len;
            for (int i = 0; i < addSpaces; i++) {
                this.key = this.key + " ";
            }
        } else {
            this.key = this.key.substring(0, 16);
        }
        this.keySpec = new SecretKeySpec(this.key.getBytes(), "AES");
        this.ivSpec = new IvParameterSpec(iv.getBytes());
        this.cipher = Cipher.getInstance("AES/CBC/NoPadding");
    }

    public byte[] hexToBytes(String str) {
        if (str == null) {
            return null;
        } else if (str.length() < 2) {
            return null;
        } else {
            int len = str.length() / 2;
            byte[] buffer = new byte[len];
            for (int i = 0; i < len; i++) {
                buffer[i] = (byte) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
            }
            return buffer;
        }
    }

    public String decrypt(String encrData) throws Exception {
        this.cipher.init(Cipher.DECRYPT_MODE, this.keySpec, this.ivSpec);
        byte[] outText = this.cipher.doFinal(hexToBytes(encrData));

        String decrData = new String(outText).trim();
        return decrData;
    }


}
