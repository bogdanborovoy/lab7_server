package commands;

import models.SpaceMarine;

import java.io.Serializable;

/**
 * Шаблон команд
 * @author bogdanborovoy
 */

public interface Command extends Serializable {
    /**
     * Шаблон реализации команды
     */
    void execute();

    /**
     * Описание команды
     * @return Описание команды в виде строки
     */
    String descr();

    void setInteractive(boolean b);

    void passValue(String[] value);
    void passSpaceMarine(SpaceMarine spaceMarine);
}
