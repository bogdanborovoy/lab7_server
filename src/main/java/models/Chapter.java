package models;

import java.io.Serializable;
import java.util.Objects;

/**
 * Класс ордена
 * @author bogdanborovoy
 */
public class Chapter implements Serializable {
    /**
     * Название ордена. Поле не может быть null, строка не может быть пустой.
     */
    private String name;

    /**
     * Количество морских пехотинцев. Поле не может быть null, значение должно быть больше 0, максимальное значение: 1000.
     */
    private Integer marinesCount;

    /**
     * Конструктор по умолчанию.
     */
    public Chapter() {}

    /**
     * Конструктор с параметрами.
     *
     * @param name Название ордена (не может быть null или пустым)
     * @param marinesCount Количество морских пехотинцев (не может быть null, должно быть больше 0 и не превышать 1000)
     */
    public Chapter(String name, Integer marinesCount) {
        setName(name);
        setMarinesCount(marinesCount);
    }

    /**
     * Возвращает название ордена.
     *
     * @return Название ордена
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает количество морских пехотинцев.
     *
     * @return Количество морских пехотинцев
     */
    public Integer getMarinesCount() {
        return marinesCount;
    }

    /**
     * Устанавливает название ордена.
     *
     * @param name Название ордена (не может быть null или пустым)
     * @throws IllegalArgumentException Если название равно null или пустой строке
     */
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Поле не может быть null");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Строка не может быть пустой");
        }
        this.name = name;
    }

    /**
     * Устанавливает количество морских пехотинцев.
     *
     * @param marinesCount Количество морских пехотинцев (не может быть null, должно быть больше 0 и не превышать 1000)
     * @throws IllegalArgumentException Если количество равно null, меньше или равно 0, или больше 1000
     */
    public void setMarinesCount(Integer marinesCount) {
        if (marinesCount == null) {
            throw new IllegalArgumentException("Поле не может быть null");
        }
        if (marinesCount <= 0) {
            throw new IllegalArgumentException("Значение поля должно быть больше 0");
        }
        if (marinesCount > 1000) {
            throw new IllegalArgumentException("Максимальное значение поля: 1000");
        }
        this.marinesCount = marinesCount;
    }

    /**
     * Возвращает строковое представление объекта Chapter.
     *
     * @return Строковое представление объекта
     */
    @Override
    public String toString() {
        return getName() + ", " + getMarinesCount();
    }

    /**
     * Возвращает хэш-код объекта Chapter.
     *
     * @return Хэш-код объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, marinesCount);
    }

    /**
     * Сравнивает объект Chapter с другим объектом.
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
        Chapter chapter = (Chapter) object;
        return this.name.equals(chapter.name) && this.marinesCount.equals(chapter.marinesCount);
    }
}
