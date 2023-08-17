package encryptdecrypt.encrypter

import encryptdecrypt.data.SharedData
import java.io.File
import java.io.FileNotFoundException

enum class OutputDestination {
    FILE, STANDARD_OUTPUT
}

/**
 * This class handles the encryption and decryption of data from a file.
 *
 * @param sharedData The shared data containing input and output file paths.
 */
class FileEncryptorDecryptor(private val sharedData: SharedData) : EncryptionManager(sharedData) {

    /**
     * Processes the input file and performs encryption or decryption based on the chosen algorithm.
     */
    fun processFile() {
        val inputFilePath = sharedData.inputFilePath
        val inputFile = File(inputFilePath)
        val outputDestination = determineOutputDestination()
        val processedLines = processLines(inputFile)
        outputProcessedData(processedLines, outputDestination)
    }

    /**
     * Processes the lines in the input file.
     *
     * @param inputFile The input file to be processed.
     * @return A list of processed strings.
     */
    private fun processLines(inputFile: File): MutableList<String> {
        val processedLines = mutableListOf<String>()
        return try {
            inputFile.forEachLine { line ->
                val processedLine = performEncryptionDecryption(line)
                processedLines.add(processedLine)
            }
            processedLines
        } catch (e: FileNotFoundException) {
            println("Error : File doesn't exist.")
            mutableListOf()
        }
    }

    /**
     * Determines the output destination based on the output file path provided in shared data.
     *
     * @return The chosen output destination.
     */
    private fun determineOutputDestination(): OutputDestination {
        return if (sharedData.outputFilePath.isNotEmpty()) {
            OutputDestination.FILE
        } else {
            OutputDestination.STANDARD_OUTPUT
        }
    }

    /**
     * Outputs the processed data to either a file or the standard output.
     *
     * @param processedLines The list of processed strings to be output.
     * @param outputDestination The chosen output destination.
     */
    private fun outputProcessedData(processedLines: MutableList<String>, outputDestination: OutputDestination) {
        when (outputDestination) {
            OutputDestination.FILE -> saveToFile(processedLines)
            OutputDestination.STANDARD_OUTPUT -> printToScreen(processedLines)
        }
    }

    /**
     * Saves the processed data to a file.
     *
     * @param processedLines The list of processed strings to be saved.
     */
    private fun saveToFile(processedLines: MutableList<String>) {
        val outputFilePath = sharedData.outputFilePath
        val outputFile = File(outputFilePath)
        val content = processedLines.joinToString("\n")
        outputFile.writeText(content)
    }

    /**
     * Prints the processed data to the standard output.
     *
     * @param processedLines The list of processed strings to be printed.
     */
    private fun printToScreen(processedLines: MutableList<String>) {
        processedLines.forEach { line ->
            println(line)
        }
    }
}