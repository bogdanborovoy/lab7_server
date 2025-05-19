package models;

/**
 * Перечисление типов космических десантников
 * @author bogdanborovoy
 */
public enum AstartesCategory {
    /**
     * Тактический десантник
     */
    TACTICAL("TACTICAL"),

    /**
     * Терминатор
     */
    TERMINATOR("TERMINATOR"),

    /**
     * Капеллан
     */
    CHAPLAIN("CHAPLAIN"),

    /**
     * Хеликс
     */
    HELIX("HELIX"),

    /**
     * Аптекарь
     */
    APOTHECARY("APOTHECARY");
    private final String name;
    AstartesCategory(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
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