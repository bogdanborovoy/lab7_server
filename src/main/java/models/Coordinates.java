package models;

import java.io.Serializable;
import java.util.Objects;

/**
 * Класс координат
 * @author bogdanborovoy
 */
public class Coordinates implements Serializable {
    private Integer x; //Поле не может быть null
    private int y;

    /**
     * Конструктор с параметрами
     * @param x координата X (не может быть null)
     * @param y координата Y
     */
    public Coordinates(Integer x, int y) {
        setX(x);
        setY(y);
    }

    /**
     * Пустой конструктор
     */
    public Coordinates() {

    }

    /**
     * Получить координату X
     * @return значение X
     */
    public Integer getX() {
        return x;
    }

    /**
     * Получить координату Y
     * @return значение Y
     */
    public int getY() {
        return y;
    }

    /**
     * Установить координату X
     * @param x значение X (не может быть null)
     */
    public void setX(Integer x) {
        if (x == null) {
            throw new IllegalArgumentException("Поле не может быть null");
        }
        this.x = x;
    }

    /**
     * Установить координату Y
     * @param y значение Y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Преобразование в строку
     * @return строковое представление координат
     */
    @Override
    public String toString() {
        return "("+ getX() + ", " + getY()+") ";
    }

    /**
     * Вычисление хэш-кода
     * @return хэш-код объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Проверка на равенство объектов
     * @param object объект для сравнения
     * @return true, если объекты равны, иначе false
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {return true;}
        if (object == null || getClass() != object.getClass()) {return false;}
        Coordinates that = (Coordinates) object;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }
}
