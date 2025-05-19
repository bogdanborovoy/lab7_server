package commands;

import models.SpaceMarine;
import helpers.CollectionManager;

/**
 * Класс ExitCommand
 * завершает программу (без сохранения в файл)
 * @author bogdanborovoy
 */
public class ExitCommand implements Command {
    CollectionManager cm;
    String[] value;
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
     * Конструктор класса ExitCommand.
     *
     * @param cm Менеджер коллекции, который будет использоваться для завершения программы
     */
    public ExitCommand(CollectionManager cm) {
        this.cm = cm;
    }

    /**
     * Метод для выполнения команды завершения программы.
     */
    public void execute() {
        cm.exit();
    }

    /**
     * Метод для получения описания команды.
     *
     * @return Описание команды в виде строки
     */
    public String descr() {
        return "exit : завершить программу (без сохранения в файл)";
    }
}