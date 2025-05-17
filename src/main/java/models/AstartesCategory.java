package classes;

/**
 * Перечисление типов космических десантников
 * @author bogdanborovoy
 */
public enum AstartesCategory {
    /**
     * Тактический десантник
     */
    TACTICAL,

    /**
     * Терминатор
     */
    TERMINATOR,

    /**
     * Капеллан
     */
    CHAPLAIN,

    /**
     * Хеликс
     */
    HELIX,

    /**
     * Аптекарь
     */
    APOTHECARY;

    /**
     * Выводит все возможные значения перечисления AstartesCategory.
     */
    public static void printValues() {
        for (AstartesCategory value : AstartesCategory.values()) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}