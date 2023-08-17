package encryptdecrypt.encrypter

import encryptdecrypt.data.SharedData

/**
 * Manages the encryption and decryption of data, which can be either a file or a string.
 *
 * @param sharedData The data shared between different components.
 */
enum class DataType {
    FILE, STRING
}

class DataEncryptionManager(private val data: SharedData) {
    private val fileEncryptorDecryptor = FileEncryptorDecryptor(data)
    private val stringEncryptorDecryptor = StringEncryptorDecryptor(data)

    /**
     * Executes the appropriate encryption or decryption operation based on the type of input data.
     */
    fun performEncryptionOrDecryption() {
        val dataType = determineDataType()
        when (dataType) {
            DataType.FILE -> fileEncryptorDecryptor.processFile()
            DataType.STRING -> stringEncryptorDecryptor.processString()
        }
    }

    /**
     * Determines the type of input data based on the provided shared data.
     *
     * @return The data type, either [DataType.FILE] or [DataType.STRING].
     */
    private fun determineDataType(): DataType {
        return if (data.inputFilePath.isNotEmpty()) {
            DataType.FILE
        } else {
            DataType.STRING
        }
    }
}