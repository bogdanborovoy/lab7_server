package server;

import commands.Command;
import exceptions.WrongPasswordException;
import helpers.CollectionManager;
import helpers.Invoker;
import models.SpaceMarine;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import request.Request;
import response.Response;
import users.DatabaseManager;
import users.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;


public class Server {
    ServerSocket serverSocket;
    //    private ObjectOutputStream oos;
//    private ObjectInputStream ois;
    CollectionManager cm;
    HashMap<String, TreeSet<SpaceMarine>> serverCollectionManager;
    Invoker invoker;
    private static final Logger logger = LogManager.getLogger(Server.class);
    Scanner scanner;
    private HashMap<String, User> users;
    User currentUser;
    //    private HashMap<User, Socket> userSockets = new HashMap<>();
    CollectionManager currentUserCollectionManager;
    TreeSet<SpaceMarine> currentUserSpaceMarines;
    private NavigableSet<SpaceMarine> allSpaceMarines;
    DatabaseManager databaseManager;
    HashMap<String, String> passwords;


    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        cm = new CollectionManager();
        scanner = new Scanner(System.in);
        startConsoleInputHandler();
        databaseManager = new DatabaseManager();
        serverCollectionManager = databaseManager.getSpaceMarines();
        allSpaceMarines = Collections.synchronizedNavigableSet(new TreeSet<>(new CollectionManager.IDComparator()));
        for (TreeSet<SpaceMarine> spaceMarines : serverCollectionManager.values()) {
            allSpaceMarines.addAll(spaceMarines);
        }
        users = new HashMap<>();
        passwords = databaseManager.retrievePasswords();
        for (String username : passwords.keySet()) {
            User user = new User(username, passwords.get(username));
            users.put(username, user);
        }

    }
    public void sendObject(ObjectOutputStream oos, String message) throws IOException {
        Response response = new Response(message);
        oos.writeObject(response);
        oos.flush();
        oos.close();
    }
    public String receiveObject(ObjectInputStream ois) {
        String response = "";
        logger.info("Обработка команды от пользователя");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldSystemOut = System.out;
        System.setOut(ps);

        try {

            Request request = (Request) ois.readObject();
            String username = request.getUsername();
            String password = request.getPassword();
            if (passwords.containsKey(username)) {
                String passwordDB = passwords.get(username);
                currentUser = users.get(username);
                User user = new User(username, password);
                password = user.getPassword();
                if(!password.equals(passwordDB)) {
                    throw new WrongPasswordException();
                }

                currentUserCollectionManager = users.get(username).getCm();
                currentUserSpaceMarines = serverCollectionManager.get(username);
                if (currentUserSpaceMarines == null) {
                    currentUserSpaceMarines = currentUserCollectionManager.spaceMarines;
                }
                currentUserCollectionManager.spaceMarines = currentUserSpaceMarines;


            }
            else {
                currentUser = new User(username, password);
                databaseManager.addPassword(username, currentUser.getPassword());
                users.put(username, currentUser);
                currentUserCollectionManager = currentUser.getCm();
                currentUserSpaceMarines = currentUserCollectionManager.spaceMarines;
                passwords.put(username, currentUser.getPassword());
            }
            invoker = currentUser.getInvoker();

            String[] args = request.getArgs();
            if (request.getSpaceMarine() != null) {
                Command command = invoker.getCommands().get(request.getArgs()[0]);
                command.passSpaceMarine(request.getSpaceMarine());
                invoker.runCommand(command);
            }
            else if (args[0].equals("show_all_space_marines")){
                response = cm.showAllSpaceMarines(allSpaceMarines);

            }
            else {
                invoker.runCommand(args);
            }
            System.out.flush();

            System.setOut(oldSystemOut);
            response = baos.toString();



        } catch (IOException e) {
            logger.info("Конец потока данных");
//            receiveObject(socket);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (WrongPasswordException e) {
            response = "Неверный пароль";
        }
        return response;

    }
    public void connect() {
        try {
            Socket socket = serverSocket.accept();
            logger.info("Подключился пользователь");
            logger.info(Thread.activeCount());
            handleClient(socket);

        }
        catch (IOException e) {
            logger.info("Пользователь отключился");
        }

    }

    public void handleClient(Socket socket) {
        new Thread(() -> {
            try {
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                String message = receiveObject(ois);
                sendObject(oos, message);
                serverCollectionManager.put(currentUser.getUsername(), currentUserSpaceMarines);
                allSpaceMarines.addAll(currentUserSpaceMarines);

            } catch (Exception e) {
                logger.info("Пользователь отключился");
            }
        }).start();


    }
    public void save(){
        cm.save(serverCollectionManager);
    }
    private void startConsoleInputHandler() {
        new Thread(() -> {
            while (true) {
                String input = scanner.nextLine();
                if ("save".equalsIgnoreCase(input)) {
                    save();
                    System.exit(0);

                }
            }
        }).start();
    }

    public static void main(String[] args) throws IOException {
        int port = 3535;
        Server server = new Server(port);
        logger.info("Начало работы сервера");

        while (true) {
            server.connect();
        }
    }
}
