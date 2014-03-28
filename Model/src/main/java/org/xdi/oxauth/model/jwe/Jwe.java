package org.xdi.oxauth.model.jwe;

import org.xdi.oxauth.model.crypto.signature.RSAPrivateKey;
import org.xdi.oxauth.model.exception.InvalidJweException;
import org.xdi.oxauth.model.exception.InvalidJwtException;
import org.xdi.oxauth.model.jwt.JwtClaims;
import org.xdi.oxauth.model.jwt.JwtHeader;

/**
 * @author Javier Rojas Blum Date: 12.03.2012
 */
public class Jwe {

    private JwtHeader header;
    private JwtClaims claims;

    private String encodedHeader;
    private String encodedEncryptedKey;
    private String encodedInitializationVector;
    private String encodedCiphertext;
    private String encodedIntegrityValue;

    public Jwe() {
        header = new JwtHeader();
        claims = new JwtClaims();

        encodedHeader = null;
        encodedEncryptedKey = null;
        encodedInitializationVector = null;
        encodedCiphertext = null;
        encodedIntegrityValue = null;
    }

    public JwtHeader getHeader() {
        return header;
    }

    public void setHeader(JwtHeader header) {
        this.header = header;
    }

    public JwtClaims getClaims() {
        return claims;
    }

    public void setClaims(JwtClaims claims) {
        this.claims = claims;
    }

    public String getEncodedHeader() {
        return encodedHeader;
    }

    public void setEncodedHeader(String encodedHeader) {
        this.encodedHeader = encodedHeader;
    }

    public String getEncodedEncryptedKey() {
        return encodedEncryptedKey;
    }

    public void setEncodedEncryptedKey(String encodedEncryptedKey) {
        this.encodedEncryptedKey = encodedEncryptedKey;
    }

    public String getEncodedInitializationVector() {
        return encodedInitializationVector;
    }

    public void setEncodedInitializationVector(String encodedInitializationVector) {
        this.encodedInitializationVector = encodedInitializationVector;
    }

    public String getEncodedCiphertext() {
        return encodedCiphertext;
    }

    public void setEncodedCiphertext(String encodedCiphertext) {
        this.encodedCiphertext = encodedCiphertext;
    }

    public String getEncodedIntegrityValue() {
        return encodedIntegrityValue;
    }

    public void setEncodedIntegrityValue(String encodedIntegrityValue) {
        this.encodedIntegrityValue = encodedIntegrityValue;
    }

    public String getAdditionalAuthenticatedData() {
        String additionalAuthenticatedData = encodedHeader + "."
                + encodedEncryptedKey + "."
                + encodedInitializationVector;

        return additionalAuthenticatedData;
    }

    public static Jwe parse(String encodedJwe, RSAPrivateKey rsaPrivateKey, byte[] sharedSymmetricKey) throws InvalidJweException, InvalidJwtException {
        Jwe jwe = null;

        if (rsaPrivateKey != null) {
            JweDecrypter jweDecrypter = new JweDecrypterImpl(rsaPrivateKey);
            jwe = jweDecrypter.decrypt(encodedJwe);
        } else if (sharedSymmetricKey != null) {
            JweDecrypter jweDecrypter = new JweDecrypterImpl(sharedSymmetricKey);
            jwe = jweDecrypter.decrypt(encodedJwe);
        }

        return jwe;
    }

    @Override
    public String toString() {
        return encodedHeader + "."
                + encodedEncryptedKey + "."
                + encodedInitializationVector + "."
                + encodedCiphertext + "."
                + encodedIntegrityValue;
    }
}