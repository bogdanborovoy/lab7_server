package commands;

import helpers.CollectionManager;
import models.SpaceMarine;

public class ShowAllSpaceMarinesCommand implements Command{
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
    public ShowAllSpaceMarinesCommand(CollectionManager cm) {
        this.cm = cm;
    }

    /**
     * Метод для выполнения команды вывода всех элементов коллекции.
     */
    public void execute() {

    }

    /**
     * Метод для получения описания команды.
     *
     * @return Описание команды в виде строки
     */
    public String descr() {
        return "show_all_space_marines : вывести в стандартный поток вывода элементы всех пользователей в строковом представлении";
    }
}
