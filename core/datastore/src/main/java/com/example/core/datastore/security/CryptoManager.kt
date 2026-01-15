package com.example.core.datastore.security

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

/**
 * Manages cryptographic operations such as encryption and decryption.
 * Currently, this class initializes the Android KeyStore for secure key management.
 */
class CryptoManager {

    companion object {
        private const val KEY_ALIAS = "secret_key_alias"
        private const val ALGORITHM = KeyProperties.KEY_ALGORITHM_AES

        private const val BLOC_MODE = KeyProperties.BLOCK_MODE_CBC
        private const val PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
        private const val TRANSFORMATION = "$ALGORITHM/$BLOC_MODE/$PADDING"

        private const val IV_SIZE = 16 // AES block size in bytes
    }


    /* Initialize the Android KeyStore instance */
    private val keyStore = KeyStore.getInstance("AndroidKeyStore").apply {
            load(null)
        }

    private fun newCipher() = Cipher.getInstance(TRANSFORMATION)

    private fun getSecretKey(): SecretKey {
        return (keyStore.getEntry(KEY_ALIAS, null) as? KeyStore.SecretKeyEntry)?.secretKey
            ?: createKey()
    }

    private fun createKey(): SecretKey {
        return KeyGenerator
            .getInstance(ALGORITHM)
            .apply {
                init(
                    KeyGenParameterSpec.Builder(
                        KEY_ALIAS,
                        KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                    )
                        .setBlockModes(BLOC_MODE)
                        .setEncryptionPaddings(PADDING)
                        .setUserAuthenticationRequired(false)
                        .setRandomizedEncryptionRequired(true)
                        .build()
                )
            }.generateKey()
    }


    /**
     * Encrypts the given plain text using the stored secret key.
     */
    fun encrypt(plainText: String): String {
        try {
            val cipher = newCipher() ?: return ""
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey())
            val iv = cipher.iv
            val encryptedBytes = cipher.doFinal(plainText.toByteArray())

            // Combine IV and encrypted bytes
            val combined = ByteArray(iv.size + encryptedBytes.size)
            System.arraycopy(iv, 0, combined, 0, iv.size)
            System.arraycopy(encryptedBytes, 0, combined, iv.size, encryptedBytes.size)

            return Base64.encodeToString(combined, Base64.NO_WRAP)
        } catch (e: Exception) { throw e }
    }

    /**
     * Decrypts the given encrypted text using the stored secret key.
     */
    fun decrypt(encryptedText: String): String {
        try {
            val combined = Base64.decode(encryptedText, Base64.NO_WRAP)
            val iv = combined.sliceArray(0 until IV_SIZE)
            val encryptedBytes = combined.sliceArray(IV_SIZE until combined.size)

            val cipher = newCipher() ?: return ""
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(), IvParameterSpec(iv))
            val decryptedBytes = cipher.doFinal(encryptedBytes)

            return String(decryptedBytes)
        } catch (e: Exception) { throw e }
    }
}