package models;

/**
 * Перечисление типов ближнего оружия
 * @author bogdanborovoy
 */
public enum MeleeWeapon {
    /**
     * Цепной меч
     */
    CHAIN_SWORD("CHAIN_SWORD"),

    /**
     * Силовой меч
     */
    POWER_SWORD("POWER_SWORD"),

    /**
     * Жнец
     */
    MANREAPER("MANREAPER"),

    /**
     * Молниевый коготь
     */
    LIGHTING_CLAW("LIGHTING_CLAW"),

    /**
     * Силовой кулак
     */
    POWER_FIST("POWER_FIST");

    MeleeWeapon(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
    /**
     * Выводит все возможные значения перечисления MeleeWeapon.
     */
    public static void printValues() {
        for (MeleeWeapon value : MeleeWeapon.values()) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
    private final String name;

}