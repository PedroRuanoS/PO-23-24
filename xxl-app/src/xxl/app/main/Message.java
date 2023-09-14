package xxl.app.main;

/**
 * Messages.
 */
interface Message {

    /**
     * @return string with "file not found" message.
     */
    static String fileNotFound() {
        return "O ficheiro não existe.";
    }

    /**
     * @param filename the missing file
     * @return string with "file not found" message (more elaborate).
     */
    static String fileNotFound(String filename) {
        return "O ficheiro '" + filename + "' não existe.";
    }

    /**
     * @param cause the original problem
     * @return string with problem description.
     */
    static String problemOpeningFile(Exception cause) {
        return "Problema ao abrir ficheiro: " + cause.getMessage();
    }

}
