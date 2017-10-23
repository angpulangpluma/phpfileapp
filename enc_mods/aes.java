/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enc_mods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Marienne Lopez
 * @source aesencryption.net - Java AES encryption example
 */
public class aes {
    
    private static final int AES_Key_Size = 256;
    private byte[] key;
    
    private SecretKeySpec secretkey;
    private Cipher cipher;
    
    public void setKey(){
        try{
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(AES_Key_Size);
        SecretKey aeskey = kgen.generateKey();
        key = aeskey.getEncoded();
        secretkey = new SecretKeySpec(key, "AES");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public Cipher getCipher(){
        return this.cipher;
    }
    
    public SecretKeySpec getKey(){
        return this.secretkey;
    }
    
    public void saveKey(String fileloc){
        File output = new File(fileloc);
        //save key to file
    }
    
    public String getDecryptedString(String str){
        String decrypted = "";
        try{
            cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretkey);
            decrypted = new String(cipher.doFinal(Base64.decodeBase64(str)));
        }catch(Exception e){
            e.printStackTrace();
        }
        return decrypted;
    }
    
    public String getEncryptedString(String str){
        String encrypted = "";
        if (secretkey==null){
            System.out.println("no key");
        } else{
            try{
                cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
                cipher.init(Cipher.ENCRYPT_MODE, secretkey);
                encrypted = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return encrypted;
    }
    
    /**
    * Decrypts an AES key from a file using an RSA private key
    */
    public void loadKey(File in, File privateKeyFile){
        try {    
        // read private key to be used to decrypt the AES key
        byte[] encodedKey = new byte[(int)privateKeyFile.length()];
        new FileInputStream(privateKeyFile).read(encodedKey);

        // create private key
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(encodedKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey pk = kf.generatePrivate(privateKeySpec);

        // read AES key
        cipher.init(Cipher.DECRYPT_MODE, pk);
        key = new byte[AES_Key_Size/8];
        CipherInputStream is = new CipherInputStream(new FileInputStream(in), cipher);
        is.read(key);
        secretkey = new SecretKeySpec(key, "AES");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Encrypts the AES key to a file using an RSA public key
     */
    public void saveKey(File out, File publicKeyFile){
        try {
        // read public key to be used to encrypt the AES key
        byte[] encodedKey = new byte[(int)publicKeyFile.length()];
        new FileInputStream(publicKeyFile).read(encodedKey);

        // create public key
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encodedKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey pk = kf.generatePublic(publicKeySpec);

        // write AES key
        cipher.init(Cipher.ENCRYPT_MODE, pk);
        CipherOutputStream os = new CipherOutputStream(new FileOutputStream(out), cipher);
        os.write(key);
        os.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
