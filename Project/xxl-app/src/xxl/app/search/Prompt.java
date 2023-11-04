package xxl.app.search;

/**
 * Functions for prompt generation (self-explanatory).
 */
interface Prompt {

    static String searchValue() {
        return "Valor a procurar: ";
    }

    static String searchFunction() {
        return "Função a procurar: ";
    }

}
