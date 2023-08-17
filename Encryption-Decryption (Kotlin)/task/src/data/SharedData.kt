package encryptdecrypt.data

/**
 * Data class representing the parameters for string encryption and decryption.
 *
 * @property operation The operation to perform ("enc" for encryption, "dec" for decryption).
 * @property encryptionKey The key used for character shifting during encryption or decryption.
 * @property inputData The string to be encrypted or decrypted.
 * @property inputFilePath The path to the input file for reading data.
 * @property outputFilePath The path to the output file for writing data.
 * @property algorithmType The algorithm to be used, "shift" or "unicode"
 */
data class SharedData(
    val operation: String,
    val encryptionKey: Int,
    val inputData: String,
    val inputFilePath: String,
    val outputFilePath: String,
    val algorithmType: String
)