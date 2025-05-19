package commands;

import models.SpaceMarine;
import helpers.CollectionManager;

/**
 * Класс CountGreaterThanHeartCountCommand
 * выводит количество элементов,
 * значение поля heartCount которых больше заданного
 * @author bogdanborovoy
 */
public class CountGreaterThanHeartCountCommand implements Command {
    CollectionManager cm;
    private boolean interactive;
    String[] value;
    SpaceMarine spaceMarine;
    public boolean isInteractive() {
        return interactive;
    }
    public void setInteractive(boolean interactive) {
        this.interactive = interactive;
    }
    @Override
    public void passSpaceMarine(SpaceMarine spaceMarine) {
        this.spaceMarine = spaceMarine;
    }
    @Override
    public void passValue(String[] value) {
        this.value = value;
    }

    /**
     * Конструктор класса CountGreaterThanHeartCountCommand.
     *
     * @param cm Менеджер коллекции, который будет использоваться для выполнения команды
     */
    public CountGreaterThanHeartCountCommand(CollectionManager cm) {
        this.cm = cm;
    }

    /**
     * Метод для выполнения команды вывода количества элементов,
     * значение поля heartCount которых больше заданного.
     */
    public void execute() {
        cm.countGreaterThanHeartCount(value[0]);
    }

    /**
     * Метод для получения описания команды.
     *
     * @return Описание команды в виде строки
     */
    public String descr() {
        return "count_greater_than_heart_count : вывести количество элементов, " +
                "значение поля heartCount которых больше заданного";
    }
}