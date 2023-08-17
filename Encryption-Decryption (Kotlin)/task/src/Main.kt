package encryptdecrypt

import encryptdecrypt.data.CommandLineParser
import encryptdecrypt.data.SharedData
import encryptdecrypt.encrypter.DataEncryptionManager

/**
 * Entry point of the program.
 *
 * @param args The command-line arguments provided to the program.
 */
fun main(args: Array<String>) {
    // Parse and sort the command-line arguments for better organization.
    val sortedArgs = CommandLineParser(args).parseArguments()

    // Extract data from sortedArgs to initialize shared data.
    val data = SharedData(
        operation = sortedArgs[0],           // The type of operation: "enc" or "dec".
        encryptionKey = sortedArgs[1].toInt(),  // The encryption key as an integer.
        inputData = sortedArgs[2],           // The input data to be processed.
        inputFilePath = sortedArgs[3],       // The path to the input file.
        outputFilePath = sortedArgs[4],       // The path to the output file.
        algorithmType = sortedArgs[5]          // The algorithm to be used.
    )

    // Create an instance of DataEncryptionManager using the shared data.
    val dataEncryptionManager = DataEncryptionManager(data)

    // Perform the encryption or decryption based on the provided operation.
    dataEncryptionManager.performEncryptionOrDecryption()
}