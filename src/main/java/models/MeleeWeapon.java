package classes;

/**
 * Перечисление типов ближнего оружия
 * @author bogdanborovoy
 */
public enum MeleeWeapon {
    /**
     * Цепной меч
     */
    CHAIN_SWORD,

    /**
     * Силовой меч
     */
    POWER_SWORD,

    /**
     * Жнец
     */
    MANREAPER,

    /**
     * Молниевый коготь
     */
    LIGHTING_CLAW,

    /**
     * Силовой кулак
     */
    POWER_FIST;

    /**
     * Выводит все возможные значения перечисления MeleeWeapon.
     */
    public static void printValues() {
        for (MeleeWeapon value : MeleeWeapon.values()) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}