package classes;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

/**
 * Класс Space Marine
 * @author bogdanborovoy
 */
public class SpaceMarine implements Serializable {
    /**
     * Уникальный идентификатор космического десантника.
     * Значение поля должно быть больше 0, уникальным и генерироваться автоматически.
     */
    private long id;

    /**
     * Имя космического десантника.
     * Поле не может быть null, строка не может быть пустой.
     */
    private String name;

    /**
     * Координаты космического десантника.
     * Поле не может быть null.
     */
    private Coordinates coordinates;

    /**
     * Дата создания записи о космическом десантнике.
     * Поле не может быть null, значение генерируется автоматически.
     */
    private ZonedDateTime creationDate;

    /**
     * Показатель здоровья космического десантника.
     * Значение поля должно быть больше 0.
     */
    private double health;

    /**
     * Количество сердец космического десантника.
     * Значение поля должно быть больше 0, максимальное значение: 3.
     */
    private int heartCount;

    /**
     * Категория космического десантника.
     * Поле не может быть null.
     */
    private AstartesCategory category;

    /**
     * Тип оружия ближнего боя.
     * Поле не может быть null.
     */
    private MeleeWeapon meleeWeapon;

    /**
     * Орден космического десантника.
     * Поле не может быть null.
     */
    private Chapter chapter;

    /**
     * Конструктор по умолчанию.
     * Автоматически генерирует id и дату создания.
     */
    public SpaceMarine() {
        setId();
        setCreationDate();
    }

    /**
     * Конструктор с параметрами.
     *
     * @param name Имя космического десантника
     * @param coordinates Координаты космического десантника
     * @param health Показатель здоровья
     * @param heartCount Количество сердец
     * @param category Категория космического десантника
     * @param meleeWeapon Тип оружия ближнего боя
     * @param chapter Орден космического десантника
     */

    public SpaceMarine(String name, Coordinates coordinates,
                       double health, int heartCount, AstartesCategory category,
                       MeleeWeapon meleeWeapon, Chapter chapter) {
        this.setId();
        this.setName(name);
        this.setCoordinates(coordinates);
        this.setCreationDate();
        this.setHealth(health);
        this.setHeartCount(heartCount);
        this.setCategory(category);
        this.setMeleeWeapon(meleeWeapon);
        this.setChapter(chapter);
    }

    /**
     * Возвращает уникальный идентификатор космического десантника.
     *
     * @return Уникальный идентификатор
     */
    public long getId() {
        return id;
    }

    /**
     * Возвращает имя космического десантника.
     *
     * @return Имя космического десантника
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает координаты космического десантника.
     *
     * @return Координаты
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Возвращает дату создания записи о космическом десантнике.
     *
     * @return Дата создания
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Возвращает показатель здоровья космического десантника.
     *
     * @return Показатель здоровья
     */
    public double getHealth() {
        return health;
    }

    /**
     * Возвращает количество сердец космического десантника.
     *
     * @return Количество сердец
     */
    public int getHeartCount() {
        return heartCount;
    }

    /**
     * Возвращает категорию космического десантника.
     *
     * @return Категория
     */
    public AstartesCategory getCategory() {
        return category;
    }

    /**
     * Возвращает тип оружия ближнего боя.
     *
     * @return Тип оружия
     */
    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }

    /**
     * Возвращает орден космического десантника.
     *
     * @return Орден
     */
    public Chapter getChapter() {
        return chapter;
    }

    /**
     * Устанавливает уникальный идентификатор космического десантника.
     * Значение генерируется автоматически.
     */
    public void setId() {
        this.id = 0;
        while (id == 0) {
            this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        }
    }

    /**
     * Устанавливает имя космического десантника.
     *
     * @param name Имя космического десантника
     * @throws IllegalArgumentException Если имя равно null или пустой строке
     */
    public void setName(String name) {
        if (name == null) {
            System.out.println("Поле не может быть null");
        }
        if (name.trim().isEmpty()) {
            System.out.println("Строка не может быть пустой");

        }
        else {
            this.name = name;
        }
    }

    /**
     * Устанавливает координаты космического десантника.
     *
     * @param coordinates Координаты
     * @throws IllegalArgumentException Если координаты равны null
     */
    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Поле не может быть null");
        }
        this.coordinates = coordinates;
    }

    /**
     * Устанавливает дату создания записи о космическом десантнике.
     * Значение генерируется автоматически.
     */
    public void setCreationDate() {
        this.creationDate = null;
        while (this.creationDate == null) {
            this.creationDate = ZonedDateTime.now();
        }
    }

    /**
     * Устанавливает показатель здоровья космического десантника.
     *
     * @param health Показатель здоровья
     * @throws IllegalArgumentException Если значение меньше или равно 0
     */
    public void setHealth(double health) {
        if (health <= 0) {
            throw new IllegalArgumentException("Значение поля должно быть больше 0");
        }
        this.health = health;
    }

    /**
     * Устанавливает количество сердец космического десантника.
     *
     * @param heartCount Количество сердец
     * @throws IllegalArgumentException Если значение меньше или равно 0, или больше 3
     */
    public void setHeartCount(int heartCount) {
        if (heartCount <= 0) {
            throw new IllegalArgumentException("Значение поля должно быть больше 0");
        }
        if (heartCount > 3) {
            throw new IllegalArgumentException("Максимальное значение поля: 3");
        }
        this.heartCount = heartCount;
    }

    /**
     * Устанавливает категорию космического десантника.
     *
     * @param category Категория
     * @throws IllegalArgumentException Если категория равна null
     */
    public void setCategory(AstartesCategory category) {
        if (category == null) {
            throw new IllegalArgumentException("Поле не может быть null");
        }
        this.category = category;
    }

    /**
     * Устанавливает тип оружия ближнего боя.
     *
     * @param meleeWeapon Тип оружия
     * @throws IllegalArgumentException Если тип оружия равен null
     */
    public void setMeleeWeapon(MeleeWeapon meleeWeapon) {
        if (meleeWeapon == null) {
            throw new IllegalArgumentException("Поле не может быть null");
        }
        this.meleeWeapon = meleeWeapon;
    }

    /**
     * Устанавливает орден космического десантника.
     *
     * @param chapter Орден
     * @throws IllegalArgumentException Если орден равен null
     */
    public void setChapter(Chapter chapter) {
        if (chapter == null) {
            throw new IllegalArgumentException("Поле не может быть null");
        }
        this.chapter = chapter;
    }

    /**
     * Возвращает строковое представление объекта SpaceMarine.
     *
     * @return Строковое представление
     */
    @Override
    public String toString() {
        return "Космический десантник " + "id: " + getId() + " " + "имя: " + getName() + " " +
                "координаты: " + getCoordinates() + " " + "время создания: " +
                DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm").format(this.getCreationDate()) + " " +
                "показатель здоровья: " + getHealth() + " " + "количество сердец: " + getHeartCount() + " " +
                "тип десантника: " + getCategory() + " " + "тип оружия: " +
                getMeleeWeapon() + " " + "орден: " + getChapter().getName() + " " +
                "количество десантников: " + getChapter().getMarinesCount();
    }

    /**
     * Возвращает хэш-код объекта SpaceMarine.
     *
     * @return Хэш-код
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, health, heartCount, category, meleeWeapon, chapter);
    }

    /**
     * Сравнивает объект SpaceMarine с другим объектом.
     *
     * @param object Объект для сравнения
     * @return true, если объекты равны, иначе false
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        SpaceMarine spaceMarine = (SpaceMarine) object;
        return spaceMarine.hashCode() == this.hashCode();
    }
}