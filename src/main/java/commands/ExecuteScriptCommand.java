package commands;

import models.SpaceMarine;
import helpers.CollectionManager;
import helpers.Invoker;

/**
 * Класс ExecuteScriptCommand
 * считывает и исполняет скрипт из указанного файла
 * (в скрипте содержатся команды в таком же виде,
 * в котором их вводит пользователь в интерактивном режиме)
 * @author bogdanborovoy
 */
public class ExecuteScriptCommand implements Command {
    CollectionManager cm;
    Invoker invoker;
    String[] value;

    private boolean interactive;
    public boolean isInteractive() {
        return interactive;
    }
    public void setInteractive(boolean interactive) {
        this.interactive = interactive;
    }

    @Override
    public void passValue(String[] value) {
        this.value = value;
    }

    @Override
    public void passSpaceMarine(SpaceMarine spaceMarine) {

    }

    /**
     * Конструктор класса ExecuteScriptCommand.
     *
     * @param cm Менеджер коллекции, который будет использоваться для выполнения скрипта
     * @param invoker Инвокер, который будет использоваться для выполнения команд из скрипта
     */
    public ExecuteScriptCommand(CollectionManager cm, Invoker invoker) {
        this.cm = cm;
        this.invoker = invoker;
    }

    /**
     * Метод для выполнения команды выполнения скрипта.
     */
    public void execute() {
        cm.executeScript(value[0], invoker);
    }

    /**
     * Метод для получения описания команды.
     *
     * @return Описание команды в виде строки
     */
    public String descr() {
        return "execute_script : считать и исполнить скрипт из указанного csv файла. " +
                "В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в " +
                "интерактивном режиме";
    }
}