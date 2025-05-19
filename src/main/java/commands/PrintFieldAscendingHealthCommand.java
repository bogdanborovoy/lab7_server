package commands;

import models.SpaceMarine;
import helpers.CollectionManager;

/**
 * Класс PrintFieldAscendingHealthCommand
 * выводит значения поля health всех элементов в порядке возрастания
 * @author bogdanborovoy
 */
public class PrintFieldAscendingHealthCommand implements Command {
    CollectionManager cm;
    private boolean interactive;
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

    }


    /**
     * Конструктор класса PrintFieldAscendingHealthCommand.
     *
     * @param cm Менеджер коллекции, который будет использоваться для получения данных о health
     */
    public PrintFieldAscendingHealthCommand(CollectionManager cm) {
        this.cm = cm;
    }

    /**
     * Метод для выполнения команды вывода значений поля health в порядке возрастания.
     */
    public void execute() {
        cm.printFieldAscendingHealth(cm.spaceMarines);
    }

    /**
     * Метод для получения описания команды.
     *
     * @return Описание команды в виде строки
     */
    public String descr() {
        return "print_field_ascending_health : вывести значения поля health " +
                "всех элементов в порядке возрастания";
    }
}