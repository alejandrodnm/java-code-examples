package codeexamples.designpatterns.creational.factorymethod;

import com.google.common.io.BaseEncoding;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

interface EncryptionAlgorithm {
  String encrypt(final String p0);

  public static class MD5EncryptionAlgorithm implements EncryptionAlgorithm {
    @Override
    public String encrypt(final String plaintext) {
      try {
        final var md = MessageDigest.getInstance("MD5");
        md.update(plaintext.getBytes());
        final var digest = md.digest();
        return BaseEncoding.base16().encode(digest);
      } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
        return "";
      }
    }
  }
}
