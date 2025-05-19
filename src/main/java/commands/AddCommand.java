package commands;

import models.SpaceMarine;
import helpers.CollectionManager;
import helpers.Invoker;

/**
 * Класс AddCommand
 * добавляет новый элемент в коллекцию
 * @author bogdanborovoy
 */
public class AddCommand implements Command {
    CollectionManager cm;
    Invoker invoker;
    String[] value = null;
    private boolean interactive = true;
    SpaceMarine spaceMarine;

    /**
     * Конструктор класса AddCommand.
     *
     * @param receiver Менеджер коллекции, который будет использоваться для добавления элемента
     * @param
     */
    public AddCommand(CollectionManager receiver) {
        this.cm = receiver;
    }
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
    public CollectionManager getReceiver() {
        return cm;
    }
    /**
     * Метод для выполнения команды добавления элемента в коллекцию.
     */
    public void execute() {
        if (spaceMarine != null) {
            cm.add(spaceMarine);
        }
        else {
            cm.add(value);
        }
    }

    /**
     * Метод для получения описания команды.
     *
     * @return Описание команды в виде строки
     */
    public String descr() {
        return "add : добавить новый элемент в коллекцию";
    }
}