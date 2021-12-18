package frog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Frog frog = new Frog();
    static List<FrogCommand> commands = new ArrayList<>();

    static int curCommand = -1;
    static int position = 0;
    static int[] field = new int[10];

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {

                System.out.println("Введите команду");
                String input = scanner.nextLine();

                if (input.equals("0")) {
                    break;
                }

                createCommand(input);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void createCommand(String input) {

        if (input.equals("<<")) {

            if (curCommand < 0) {
                System.out.println("Нечего отменять!");
            } else {
                commands.get(curCommand).undo();
                curCommand--;
            }

        } else if (input.equals("!!")) {

            if (commands.size() == 0) {
                System.out.println("Нечего повторять!");
            } else {
                commands.get(curCommand).doit();
                curCommand++;
            }

        } else if (input.equals(">>")) {

            FrogCommand res = getLastUndo();
            if (res != null) {
                res.doit();
                curCommand++;
            }

        } else {

            if (curCommand != commands.size() - 1) {
                commands.stream().forEach(frogCommand -> {
                    if(frogCommand.isUndo()){
                        commands.remove(frogCommand);
                    };
                } );
            }

            FrogCommand cmd;
            if (input.contains("+")){
                cmd = FrogCommands.jumpRightCommand(frog, Integer.parseInt(input));
            }else if (input.contains("-")){
                cmd = FrogCommands.jumpLeftCommand(frog, Integer.parseInt(input));
            }else {
                System.out.println("Не удалось определить действие");
                return;
            }

            curCommand++;
            commands.add(cmd);
            boolean res = cmd.doit();
            if (!res){
                System.out.println("Не получилось прыгнуть, попробуйте снова!");
                return;
            }

        }

        printField();

    }

    private static FrogCommand getLastUndo() {

        for (int i = commands.size() - 1; i >= 0 ; i--) {
            FrogCommand command = commands.get(i);
            if(command.isUndo()){
                return command;
            }
        }

        return null;

    }

    private static void printField() {

        field[position] = 0;
        field[frog.getPosition()] = 1;
        position = frog.getPosition();

        Arrays.stream(field).forEach(System.out::print);

        System.out.println();

    }

}
