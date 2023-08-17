package encryptdecrypt.encrypter

import encryptdecrypt.data.ALPHABET_SIZE
import encryptdecrypt.data.SharedData
/**
 * A class that provides letter shifting encryption and decryption functionalities.
 *
 * @property data The shared data container that holds the operation type and encryption key.
 */
open class EncryptionManager(private val data: SharedData) {

    /**
     * Shifts the letters of the input string using a given key and returns the encrypted/decrypted string.
     *
     * @param string The input string to be shifted.
     * @return The encrypted/decrypted string.
     */
    open fun performEncryptionDecryption(string: String): String {
        val operation = data.operation
        val key = data.encryptionKey
        val newString = StringBuilder()

        for (char in string) {
            val shiftedChar: Char = if (data.algorithmType != "shift")
                unicodeEncryptionDecryption(char, key, operation)
            else
                shiftingEncryptionDecryption(char, key, operation)
            newString.append(shiftedChar)
        }

        return newString.toString()
    }

    /**
     * Shifts a character by a given key using the specified operation.
     *
     * @param char The character to be shifted.
     * @param key The shifting key.
     * @param operation The operation to perform ("enc" for encryption, "dec" for decryption).
     * @return The shifted character.
     */
    private fun unicodeEncryptionDecryption(char: Char, key: Int, operation: String): Char {
        return when (operation) {
            "enc" -> (char.code + key).toChar() // Shift the character code for encryption.
            "dec" -> (char.code - key).toChar() // Shift the character code for decryption.
            else -> 'a' // Return 'a' for unknown operations.
        }
    }

    /**
     * Shifts a single character by a given key while preserving its case and wrapping around the alphabet.
     *
     * @param char The character to be shifted.
     * @param key The shifting key.
     * @param operation The operation to perform: "enc" for encryption, "dec" for decryption.
     * @return The shifted character.
     */
    private fun shiftingEncryptionDecryption(char: Char, key: Int, operation: String): Char {
        if (!char.isLetter()) {
            return char // Return unchanged if not a letter.
        }

        val isUpperCase = char.isUpperCase()
        val baseOffset = if (isUpperCase) 'A' else 'a'

        val shift = if (operation == "enc") key else -key
        val shiftedChar = (char.code - baseOffset.code + shift + ALPHABET_SIZE) % ALPHABET_SIZE + baseOffset.code
        /* Example calculation:
             For encryption:
             char.code = 97 ('a'), key = 1, baseOffset = 97
             97 - 97 + 1 + 26 % 26 = 1
             1 + 97 = 98 ('b')

             For decryption:
             char.code = 98 ('b'), key = 1, baseOffset = 97
             98 - 97 - 1 + 26 % 26 = 0
             0 + 97 = 97 ('a')
        */

        return shiftedChar.toChar()
    }



}