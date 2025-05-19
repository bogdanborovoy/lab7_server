package commands;

import models.SpaceMarine;
import helpers.CollectionManager;

/**
 * Класс RemoveLowerCommand
 * удаляет из коллекции все элементы, меньшие, чем заданный
 * @author bogdanborovoy
 */
public class RemoveLowerCommand implements Command {
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
    public void passValue(String[] value) {

    }
    public void passSpaceMarine(SpaceMarine spaceMarine) {
        this.spaceMarine = spaceMarine;
    }

    /**
     * Конструктор класса RemoveLowerCommand.
     *
     * @param cm Менеджер коллекции, который будет использоваться для удаления элементов
     */
    public RemoveLowerCommand(CollectionManager cm) {
        this.cm = cm;
    }

    /**
     * Метод для выполнения команды удаления элементов, меньших, чем заданный.
     */
    public void execute() {
//        Scanner sc = new Scanner(System.in);
//        String[] args = sc.nextLine().split(", ");
//
//        if (interactive) {
//            System.out.println("Создание элемента");
        cm.removeLower(spaceMarine);
//        }
//        else {
//            cm.removeLower(cm.add(args));
//        }
    }

    /**
     * Метод для получения описания команды.
     *
     * @return Описание команды в виде строки
     */
    public String descr() {
        return "remove_lower : удалить из коллекции все элементы, меньшие, чем заданный";
    }
}