package codeexamples.designpatterns.creational.factorymethod;

public abstract class Encryptor {
  public String encryptAndStuff(final String plaintext) {
    final EncryptionAlgorithm encryptAlgorithm = this.getEncryptionAlgorithm();
    return encryptAlgorithm.encrypt(plaintext);
  }

  public abstract EncryptionAlgorithm getEncryptionAlgorithm();

  public static class MD5Encryptor extends Encryptor {
    @Override
    public EncryptionAlgorithm getEncryptionAlgorithm() {
      return new EncryptionAlgorithm.MD5EncryptionAlgorithm();
    }
  }
}
