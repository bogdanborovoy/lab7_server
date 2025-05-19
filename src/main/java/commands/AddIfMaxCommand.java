package commands;

import models.SpaceMarine;
import helpers.CollectionManager;

/**
 * Класс AddIfMaxCommand
 * добавляет новый элемент в коллекцию,
 * если его значение превышает значение наибольшего элемента этой коллекции
 * @author bogdanborovoy
 */
public class AddIfMaxCommand implements Command {
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
     * Конструктор класса AddIfMaxCommand.
     *
     * @param cm Менеджер коллекции, который будет использоваться для добавления элемента
     */
    public AddIfMaxCommand(CollectionManager cm) {
        this.cm = cm;
    }

    /**
     * Метод для выполнения команды добавления элемента в коллекцию,
     * если его значение превышает значение наибольшего элемента этой коллекции.
     */
    public void execute() {
//        Scanner sc = new Scanner(System.in);
//        String[] args = sc.nextLine().split(", ");
//        if (value.length==0){
//            System.out.println("Создание элемента");
            cm.addIfMax(spaceMarine);
//        }
//        else{
//            cm.addIfMax(cm.add(args));
//        }
    }

    /**
     * Метод для получения описания команды.
     *
     * @return Описание команды в виде строки
     */
    public String descr() {
        return "add_if_max : добавить новый элемент в коллекцию, " +
                "если его значение превышает значение наибольшего элемента этой коллекции";
    }
}