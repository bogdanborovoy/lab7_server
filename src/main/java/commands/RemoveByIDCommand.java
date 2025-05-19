package commands;

import models.SpaceMarine;
import helpers.CollectionManager;

import java.util.Scanner;

/**
 * Класс RemoveByIDCommand
 * удаляет элемент из коллекции по его id
 * @author bogdanborovoy
 */
public class RemoveByIDCommand implements Command {
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
     * Конструктор класса RemoveByIDCommand.
     *
     * @param cm Менеджер коллекции, который будет использоваться для удаления элемента по id
     */
    public RemoveByIDCommand(CollectionManager cm) {
        this.cm = cm;
    }

    /**
     * Метод для выполнения команды удаления элемента по id.
     */
    public void execute() {
        if (interactive) {
            Scanner sc = new Scanner(System.in);
            cm.removeById(sc);
        }
        else {
            if (value != null) {
                cm.removeById(Long.valueOf(value[0]));
            }
            else {
                System.out.println("ID не был передан");

            }
        }

    }

    /**
     * Метод для получения описания команды.
     *
     * @return Описание команды в виде строки
     */
    public String descr() {
        return "remove_by_id : удалить элемент из коллекции по его id";
    }
}