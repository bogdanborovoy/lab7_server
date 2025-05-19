package helpers;

import commands.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс Invoker отвечает за управление и выполнение команд.
 * Он хранит команды в словаре и позволяет их выполнять по имени.
 * @author bogdanborovoy
 */
public class Invoker implements Serializable {
    private boolean interactive;
    private static final long serialVersionUID = -9183266173581498246L;
    CollectionManager cm;
    public Invoker(CollectionManager receiver) {
        cm = receiver;
        this.setCommand("help", new HelpCommand(receiver, this));
        this.setCommand("info", new InfoCommand(receiver));
        this.setCommand("show", new ShowCommand(receiver));
        this.setCommand("add", new AddCommand(receiver));
        this.setCommand("update_id", new UpdateIDCommand(receiver));
        this.setCommand("remove_by_id", new RemoveByIDCommand(receiver));
        this.setCommand("clear", new ClearCommand(receiver));
//        invoker.setCommand("save", new SaveCommand(receiver));
        this.setCommand("execute_script", new ExecuteScriptCommand(receiver, this));
        this.setCommand("exit", new ExitCommand(receiver));
        this.setCommand("add_if_max", new AddIfMaxCommand(receiver));
        this.setCommand("remove_greater", new RemoveGreaterCommand(receiver));
        this.setCommand("remove_lower", new RemoveLowerCommand(receiver));
        this.setCommand("count_greater_than_heart_count", new CountGreaterThanHeartCountCommand(receiver));
        this.setCommand("filter_starts_with_name", new FilterStartsWithNameCommand(receiver));
        this.setCommand("print_field_ascending_health", new PrintFieldAscendingHealthCommand(receiver));
        this.setCommand("show_all_space_marines",new ShowAllSpaceMarinesCommand(receiver));
    }
    /**
     * Словарь команд, где ключ — название команды, а значение — сама команда.
     */
    Map<String, Command> commands = new HashMap<>();

    /**
     * Добавляет команду в словарь.
     *
     * @param name Название команды
     * @param command Команда, которая будет выполнена
     */
    public void setCommand(String name, Command command) {
        commands.put(name, command);
    }

    /**
     * Возвращает словарь команд.
     *
     * @return Словарь команд, где ключ — название команды, а значение — сама команда
     */
    public Map<String, Command> getCommands() {
        return commands;
    }



    /**
     * Выполняет команду, переданную в качестве параметра.
     *
     * @param command Команда для выполнения
     */
    public void runCommand(Command command) {
        if (command != null) {
            command.execute();
        } else {
            System.out.println("Команда не найдена, повторите попытку");
        }
    }

    /**
     * Выполняет команду по её названию.
     *
     * @param name Название команды
     */
    public void runCommand(String name) {
        Command command = commands.get(name);
        if (command != null) {
            command.execute();
        } else {
            System.out.println("Команда не найдена, повторите попытку");
        }
    }
    public void runCommand(String[] value) {
        String name = value[0];

        String [] args = Arrays.copyOfRange(value, 1, value.length);
        Command command = commands.get(value[0]);
        if (command != null)
        {
            command.passValue(args);
            command.execute();
        } else {
            System.out.println("Команда не найдена, повторите попытку");
        }


    }
    public void runCommand(Command command, String args){
        if (command != null) {
            command.passValue(args.split(" "));
            command.execute();

        } else {
            System.out.println("Команда не найдена, повторите попытку");
        }
    }

}