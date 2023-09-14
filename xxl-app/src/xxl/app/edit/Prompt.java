package xxl.app.edit;

/**
 * Messages.
 */
interface Prompt {

    /** @return string with prompt for range. */
    static String address() {
        return "Especifique a gama (startline;startcol:endline;endcol): ";
    }

    /** @return string with prompt for content. */
    static String content() {
        return "Insira o conteúdo da célula: ";
    }

}