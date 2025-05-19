package helpers;

import models.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import commands.Command;

import java.io.*;
import java.sql.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс управления коллекцией космических кораблей
 * @author bogdanborovoy
 */
public class CollectionManager implements Serializable {

    /**
     * Компаратор для сравнения элементов коллекции по ID.
     */
    public static class IDComparator implements Comparator<SpaceMarine>, Serializable {
        /**
         * Сравнивает два объекта SpaceMarine по их ID.
         *
         * @param o1 Первый объект SpaceMarine
         * @param o2 Второй объект SpaceMarine
         * @return Результат сравнения ID
         */
        public int compare(SpaceMarine o1, SpaceMarine o2) {
            return Long.compare(o1.getId(), o2.getId());
        }
    }

    /**
     * Коллекция космических десантников.
     */
    public TreeSet<SpaceMarine> spaceMarines;

    /**
     * Дата создания коллекции.
     */
    private ZonedDateTime creationDate;

    /**
     * Конструктор по умолчанию.
     * Инициализирует коллекцию и устанавливает дату создания.
     */
    public CollectionManager() {
        spaceMarines = new TreeSet<>(new IDComparator());
        setCreationDate();
    }

    /**
     * Возвращает дату создания коллекции.
     *
     * @return Дата создания коллекции
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Устанавливает дату создания коллекции.
     */
    public void setCreationDate() {
        this.creationDate = ZonedDateTime.now();
    }

    /**
     * Выводит справку по доступным командам.
     *
     * @param invoker Инвокер, содержащий команды
     * @return Строка с описанием всех команд
     */
    public String help(Invoker invoker) {
        String output = invoker.getCommands().values().stream().map(Command::descr).collect(Collectors.joining("\n"));
        System.out.println(output);
        return output;
    }

    /**
     * Выводит информацию о коллекции.
     *
     * @return Строка с информацией о коллекции
     */
    public String info() {
        StringBuilder output = new StringBuilder();

        String type = spaceMarines.getClass().getName().split("\\.")[2];
        output.append("Тип коллекции: ").append(type).append("\n");

        String date = DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm").format(this.getCreationDate());
        output.append("Время инициализации: ").append(date).append("\n");

        String size = String.valueOf(spaceMarines.size());
        output.append("Количество элементов: ").append(size).append("\n");
        System.out.println(output);
        return output.toString();
    }

    /**
     * Выводит все элементы коллекции в строковом представлении.
     *
     * @param spaceMarines Коллекция космических десантников
     * @return Строка с элементами коллекции
     */
    public String show(NavigableSet<SpaceMarine> spaceMarines) {
        String output = spaceMarines.stream().map(spaceMarine -> spaceMarine.toString()).collect(Collectors.joining("\n"));
        if (output.isEmpty()) {
            output = "Коллекция пустая";
        }
        System.out.println(output);
        return output;
    }

    public SpaceMarine add(String[] args){
        for(int i = 0; i < 8; i++) {
            args[i] = args[i].trim();
        }

        String name = args[0];
        Coordinates coordinates = new Coordinates(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        double health = Double.parseDouble(args[3]);
        int heartCount = Integer.parseInt(args[4]);
        AstartesCategory category = AstartesCategory.valueOf(args[5]);
        MeleeWeapon meleeWeapon = MeleeWeapon.valueOf(args[6]);
        Chapter chapter = new Chapter(args[7], Integer.parseInt(args[8]));
        SpaceMarine spaceMarine = new SpaceMarine(name, coordinates, health, heartCount, category, meleeWeapon, chapter);
        spaceMarines.add(spaceMarine);
        System.out.println("Элемент добавлен");
        return spaceMarine;
    }
    public void add(SpaceMarine spaceMarine) {
        spaceMarines.add(spaceMarine);
    }

    /**
     * Обновляет значение элемента коллекции по его ID.
     */
    public void updateID(SpaceMarine sm) {
        boolean found = false;
        Iterator<SpaceMarine> iterator = spaceMarines.iterator();
        while (iterator.hasNext()) {
            SpaceMarine spaceMarine = iterator.next();
            if (spaceMarine.getId() == sm.getId()) {
                iterator.remove();
                spaceMarines.add(sm);
                found = true;
            }
        }
        if (found) {
            System.out.println("Элемент найден и обновлен");
        }
        else {
            System.out.println("Элемент не найден");
        }

    }



    public void removeById(Long id) {
        boolean removed = spaceMarines.removeIf(spaceMarine -> spaceMarine.getId() == id);
        if (removed) {
            System.out.println("Элемент " + id + " удален");
        }
        else {
            System.out.println("Элемент " + id + " не найден");
        }
    }
    /**
     * Удаляет элемент из коллекции по его ID.
     */
    public void removeById(Scanner sc) {
        Long id1 = null;
        while (id1 == null) {
            try {
                System.out.println("Введите id: ");
                id1 = Long.parseLong(sc.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("ID должен быть в формате long");
            }
        }
        long id = id1;

        removeById(id);
    }

    /**
     * Очищает коллекцию.
     */
    public void clear() {
        spaceMarines.clear();
    }

//    /**
//     * Сохраняет коллекцию в файл.
//     *
//     * @param spaceMarines Коллекция космических десантников
//     */
//    public void save(NavigableSet<SpaceMarine> spaceMarines) {
//        //export SAVE_IN="/home/studs/s465267/lab5/files/spacemarines.csv"
//        String fileName = "/Users/bogdanborovoy/Desktop/lab7_server/src/main/resources/files/spacemarines.csv";
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
//            for (SpaceMarine spaceMarine : spaceMarines) {
//                bw.write(spaceMarine.toString());
//                bw.newLine();
//            }
//            //System.out.println("Коллекция сохранена в файл " + fileName);
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//            System.out.println("Файл не найден");
//        }
//        catch (NullPointerException e) {
//            System.out.println("Файл не указан");
//        }
//    }
    /**
     * Сохраняет коллекцию в базу данных.
     *
     * @param serverCollection Коллекция космических десантников
     */
    public void save(HashMap<String, TreeSet<SpaceMarine>> serverCollection){
        try {

            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/studs", "s465267", "xUaQIKx4AivXYHbx");
            Statement truncate = connection.createStatement();
            truncate.executeUpdate("truncate table spacemarines");
            truncate.close();
            Statement resetSequence = connection.createStatement();
            resetSequence.executeUpdate("ALTER SEQUENCE SpaceMarinesIdSeq RESTART WITH 1");
            resetSequence.close();
            for (String username : serverCollection.keySet()) {
                var collection = serverCollection.get(username);
                if (collection == null) {
                    continue;
                }
                Statement statement = connection.createStatement();
                String query = "insert into spacemarines values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(query);

                for (SpaceMarine spaceMarine : collection) {
                    Statement getIdFromSequence = connection.createStatement();
                    ResultSet resultSet = getIdFromSequence.executeQuery("SELECT nextval('SpaceMarinesIdSeq')");
                    resultSet.next();


                    ps.setLong(1, resultSet.getLong(1));
                    ps.setString(2, spaceMarine.getName());
                    ps.setInt(3, spaceMarine.getCoordinates().getX());
                    ps.setInt(4, spaceMarine.getCoordinates().getY());
                    ps.setTimestamp(5, Timestamp.from(spaceMarine.getCreationDate().toInstant()));
                    ps.setDouble(6, spaceMarine.getHealth());
                    ps.setInt(7, spaceMarine.getHeartCount());
                    ps.setString(8, spaceMarine.getCategory().toString());
                    ps.setString(9, spaceMarine.getMeleeWeapon().toString());
                    ps.setString(10, spaceMarine.getChapter().getName());
                    ps.setInt(11, spaceMarine.getChapter().getMarinesCount());
                    ps.setString(12, username);
                    ps.executeUpdate();
                    ps.clearParameters();
                }
//                ResultSet resultSet = statement.executeQuery("select * from spacemarines");
//                while (resultSet.next()) {
//                    for (int i = 1; i < 12; i++) {
//                        System.out.println(resultSet.getString(i));
//
//                    }
//
//                }
                statement.close();
                ps.close();

            }
            connection.close();
            //            String query = "insert into spacemarines values (id, 'kjdajdk', 2, 2, '2007-03-01', 2, 2, 'HELIX', 'MANREAPER', 'chapter', 2)";

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Завершает программу (без сохранения в файл).
     */
    public void exit() {
        System.exit(0);
    }

    /**
     * Добавляет новый элемент в коллекцию, если его значение превышает
     * значение наибольшего элемента этой коллекции.
     *
     * @param spaceMarine Элемент для добавления
     */
    public void addIfMax(SpaceMarine spaceMarine) {
        if (spaceMarines.isEmpty()) {
            System.out.println("Коллекция пустая");
        } else {
            Iterator<SpaceMarine> iterator = spaceMarines.iterator();
            SpaceMarine lastElement = iterator.next();
            while (iterator.hasNext()) {
                lastElement = iterator.next();
            }
            if (lastElement.getId() > spaceMarine.getId()) {
                spaceMarines.add(spaceMarine);
                System.out.println("Элемент добавлен");
            } else {
                System.out.println("ID элемента не превышает значение наибольшего элемента этой коллекции");
                System.out.println("Элемент не был добавлен");
            }
        }
    }

    /**
     * Удаляет из коллекции все элементы, превышающие заданный.
     *
     * @param spaceMarine Элемент для сравнения
     */
    public void removeGreater(SpaceMarine spaceMarine) {
        NavigableSet<SpaceMarine> lowerSpaceMarines = new TreeSet<>(spaceMarines.tailSet(spaceMarine, false));
        int count = lowerSpaceMarines.size();
        spaceMarines.removeAll(lowerSpaceMarines);

        System.out.println(count + " элемент(а) удалено");
    }

    /**
     * Удаляет из коллекции все элементы, меньшие, чем заданный.
     *
     * @param spaceMarine Элемент для сравнения
     */
    public void removeLower(SpaceMarine spaceMarine) {
        SortedSet<SpaceMarine> lowerSpaceMarines = new TreeSet<>(spaceMarines.headSet(spaceMarine));
        int count = lowerSpaceMarines.size();
        spaceMarines.removeAll(lowerSpaceMarines);
        System.out.println(count + " элемент(а) удалено");
    }

    /**
     * Выводит количество элементов, значение поля heartCount которых больше заданного.
     */
    public void countGreaterThanHeartCount(String args) {
        try {
            int heartCount = Integer.parseInt(args);
            long count = spaceMarines.stream()
                    .filter(spaceMarine -> spaceMarine.getHeartCount() > heartCount)
                    .count();

            System.out.println("В коллекции " + count + " элементов, значение поля heartCount которых больше заданного");
        }
        catch (NumberFormatException e) {
            System.out.println("Количество сердец должно быть целым числом");

        }

    }

    /**
     * Выводит элементы, значение поля name которых начинается с заданной подстроки.
     */
    public void filterStartsWithName(String name) {
        spaceMarines.stream()
                .filter(spaceMarine -> spaceMarine.getName().toLowerCase().startsWith(name.toLowerCase()))
                .forEach(System.out::println);
    }

    public void executeScript(String script, Invoker invoker) {

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(script))) {
            InputStreamReader isr = new InputStreamReader(bis);
            CSVReader csvReader = new CSVReader(isr);
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                for (int i = 0; i < nextRecord.length; i++) {
                    String name = nextRecord[i].trim();
                    String[] tokens = name.split(" ");
                    Command command = invoker.getCommands().get(tokens[0]);
                    if (tokens.length > 1) {
                        String[] args = Arrays.copyOfRange(tokens, 1, tokens.length);
                        command.passValue(args);
                    }
                    command.setInteractive(false);
                    System.out.println("Выполняется команда "+name);
                    invoker.runCommand(command);
                    command.setInteractive(true);
                }
            }
            System.out.println("Скрипт выполнен");
            isr.close();
        } catch (IOException | CsvValidationException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Выводит значения поля health всех элементов в порядке возрастания.
     *
     * @param spaceMarines Коллекция космических десантников
     * @return Список значений health
     */
    public List<Double> printFieldAscendingHealth(NavigableSet<SpaceMarine> spaceMarines) {
        List<Double> health_list = spaceMarines.stream()
                .map(spaceMarine -> spaceMarine.getHealth())
                .sorted()
                .peek(health -> System.out.print(health + " ")).toList();
        return health_list;
    }
    public String showAllSpaceMarines(NavigableSet<SpaceMarine> spaceMarines) {
        return show(spaceMarines);
    }
}