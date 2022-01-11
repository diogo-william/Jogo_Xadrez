package tabuleiro;

public class ConsoleCor {

    public static final String RESET ="\033[0m";

    public static final String YELLOW = "\033[0;33m"; // Yellow

    public static final String LIGHT_BLUE = "\033[38;2;120;172;255m"; // Light Blue

    public static final String RED = "\033[0;31m";  // Red

    public static final String Limpa = "\033[H\033[2J"; // Limpa



    public static void limpaTela()
    {
        System.out.print(Limpa);
        System.out.flush();


    }


    public static void reseta()
    {
        System.out.print(RESET);
        System.out.flush();

    }





}
