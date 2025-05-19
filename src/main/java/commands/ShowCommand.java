package commands;

import models.SpaceMarine;
import helpers.CollectionManager;

/**
 * Класс ShowCommand
 * выводит в стандартный поток вывода все элементы коллекции в строковом представлении
 * @author bogdanborovoy
 */
public class ShowCommand implements Command {
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

    }

    /**
     * Конструктор класса ShowCommand.
     *
     * @param cm Менеджер коллекции, который будет использоваться для вывода элементов
     */
    public ShowCommand(CollectionManager cm) {
        this.cm = cm;
    }

    /**
     * Метод для выполнения команды вывода всех элементов коллекции.
     */
    public void execute() {
        cm.show(cm.spaceMarines);
    }

    /**
     * Метод для получения описания команды.
     *
     * @return Описание команды в виде строки
     */
    public String descr() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}