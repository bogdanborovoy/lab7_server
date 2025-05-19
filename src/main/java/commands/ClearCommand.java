package commands;

import models.SpaceMarine;
import helpers.CollectionManager;

/**
 * Класс ClearCommand
 * очищает коллекцию
 * @author bogdanborovoy
 */
public class ClearCommand implements Command {
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
     * Конструктор класса ClearCommand.
     *
     * @param cm Менеджер коллекции, который будет использоваться для очистки
     */
    public ClearCommand(CollectionManager cm) {
        this.cm = cm;
    }

    /**
     * Метод для выполнения команды очистки коллекции.
     */
    public void execute() {
        cm.clear();
    }

    /**
     * Метод для получения описания команды.
     *
     * @return Описание команды в виде строки
     */
    public String descr() {
        return "clear : очистить коллекцию";
    }
}