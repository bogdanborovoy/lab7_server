package request;

import models.*;

import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class AddRequest implements Serializable {

    public SpaceMarine run(Scanner sc) {
        SpaceMarine spaceMarine = new SpaceMarine();
        spaceMarine.setId();
        spaceMarine.setCreationDate();
        try {
            while (spaceMarine.getName() == null) {
                try {
                    System.out.println("Введите имя: ");
                    spaceMarine.setName(sc.nextLine());
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            Coordinates coordinates = new Coordinates();

            while (coordinates.getX() == null) {
                try {
                    System.out.println("Введите координаты по x: ");
                    Integer x = Integer.parseInt(sc.nextLine());
                    coordinates.setX(x);
                } catch (NumberFormatException e) {
                    System.out.println("Координаты должны быть целым числом от -2147483648 до 2147483647");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            while (coordinates.getY() == 0) {
                try {
                    System.out.println("Введите координаты по y: ");
                    int y = Integer.parseInt(sc.nextLine());
                    coordinates.setY(y);
                    if (y == 0) {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Координаты должны быть целым числом от -2147483648 до 2147483647");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            spaceMarine.setCoordinates(coordinates);

            while (spaceMarine.getHealth() == 0) {
                try {
                    System.out.println("Введите показатель здоровья: ");
                    double health = Double.parseDouble(sc.nextLine());
                    spaceMarine.setHealth(health);
                } catch (NumberFormatException e) {
                    System.out.println("Показатель здоровья должен быть числом от 0 до 2147483647");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            while (spaceMarine.getHeartCount() == 0) {
                try {
                    System.out.println("Введите количество сердец: ");
                    int heartCount = Integer.parseInt(sc.nextLine());
                    spaceMarine.setHeartCount(heartCount);
                } catch (NumberFormatException e) {
                    System.out.println("Количество сердец должно быть целым числом от 0 до 3");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            System.out.print("Выберите тип десантника: ");
            while (spaceMarine.getCategory() == null) {
                try {
                    AstartesCategory.printValues();
                    String line = sc.nextLine().toUpperCase();
                    spaceMarine.setCategory(AstartesCategory.valueOf(line));
                } catch (IllegalArgumentException e) {
                    System.out.println("Выберите тип из предложенных");
                }
            }

            System.out.print("Выберите тип оружия: ");
            while (spaceMarine.getMeleeWeapon() == null) {
                try {
                    MeleeWeapon.printValues();
                    String line = sc.nextLine().toUpperCase();
                    spaceMarine.setMeleeWeapon(MeleeWeapon.valueOf(line));
                } catch (IllegalArgumentException e) {
                    System.out.println("Выберите тип из предложенных");
                }
            }

            Chapter chapter = new Chapter();
            while (chapter.getName() == null) {
                try {
                    System.out.println("Введите название ордена: ");
                    chapter.setName(sc.nextLine());
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            while (chapter.getMarinesCount() == null) {
                try {
                    System.out.println("Введите количество десантников: ");
                    Integer marinesCount = Integer.parseInt(sc.nextLine());
                    chapter.setMarinesCount(marinesCount);
                } catch (NumberFormatException e) {
                    System.out.println("Введите целое число от 0 до 1000");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            spaceMarine.setChapter(chapter);
//            spaceMarines.add(spaceMarine);
            System.out.println("Космический десантник создан");

        }
        catch (
                NoSuchElementException e) {
            System.exit(0);
        }
        return spaceMarine;
    }

}
