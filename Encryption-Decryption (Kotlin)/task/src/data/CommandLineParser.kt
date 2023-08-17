package encryptdecrypt.data

/**
 * A class responsible for parsing and organizing command-line arguments.
 *
 * @param arguments The array of command-line arguments to be processed.
 */
class CommandLineParser(private val arguments: Array<String>) {

    /**
     * Sorts and extracts specific command-line arguments.
     *
     * @return A mutable list containing the sorted and extracted arguments.
     */
    fun parseArguments(): MutableList<String> {
        var encryptionMode = DEFAULT_ENCRYPTION_MODE
        var encryptionKey = DEFAULT_ENCRYPTION_KEY
        var inputData = DEFAULT_INPUT_DATA
        var inputFile = DEFAULT_INPUT_FILE
        var outputFile = DEFAULT_OUTPUT_FILE
        var algorithmType = DEFAULT_ALGORITHM

        // Store parsed and organized arguments.
        val parsedArguments = mutableListOf<String>()

        // Process command-line arguments.
        for (i in arguments.indices) {
            when (arguments[i]) {
                "-mode" -> encryptionMode = arguments[i + 1]
                "-key" -> encryptionKey = arguments[i + 1]
                "-data" -> inputData = arguments[i + 1]
                "-in" -> inputFile = arguments[i + 1]
                "-out" -> outputFile = arguments[i + 1]
                "-alg" -> algorithmType = arguments[i + 1]
                else -> continue
            }
        }

        // Add parsed arguments to the list.
        parsedArguments.add(encryptionMode)
        parsedArguments.add(encryptionKey)
        parsedArguments.add(inputData)
        parsedArguments.add(inputFile)
        parsedArguments.add(outputFile)
        parsedArguments.add(algorithmType)

        return parsedArguments
    }

    /*
    private fun argumentHasValidValue(argument: String): Boolean {
        return !argument.contains('-')
    }
*/

}