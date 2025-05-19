package commands;

import models.SpaceMarine;
import helpers.CollectionManager;
import helpers.Invoker;

/**
 * Класс HelpCommand
 * выводит справку по доступным командам
 * @author bogdanborovoy
 */
public class HelpCommand implements Command {
    Invoker invoker;
    CollectionManager cm;
    String[] value;
    SpaceMarine spaceMarine;
    private boolean interactive;
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
     * Конструктор класса HelpCommand.
     *
     * @param cm Менеджер коллекции, который будет использоваться для вывода справки
     * @param invoker Инвокер, который будет использоваться для получения списка команд
     */
    public HelpCommand(CollectionManager cm, Invoker invoker) {
        this.cm = cm;
        this.invoker = invoker;
    }

    /**
     * Метод для выполнения команды вывода справки по доступным командам.
     */
    public void execute() {
        cm.help(invoker);
    }

    /**
     * Метод для получения описания команды.
     *
     * @return Описание команды в виде строки
     */
    public String descr() {
        return "help : вывести справку по доступным командам";
    }
}