package crypto.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

class CryptoUtilsTest {
    @Test
    void encryptThenDecrypt_thenSuccess() throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {
        String input = "apple";
        String cipherText = CryptoUtils.encrypt(input);
        String plainText = CryptoUtils.decrypt(cipherText);
        Assertions.assertEquals(input, plainText);
    }
}