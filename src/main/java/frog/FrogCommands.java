package frog;

public class FrogCommands{

    public static FrogCommand jumpRightCommand(Frog frog, int steps) {
        return new FrogCommand(){
            boolean undo = false;

            @Override
            public boolean isUndo() {
                return undo;
            }

            @Override
            public boolean doit() {
                return frog.jump(steps);
            }

            @Override
            public boolean undo() {
                undo = true;
                return frog.jump(Integer.valueOf(-steps));
            }
        };
    }

    public static FrogCommand jumpLeftCommand(Frog frog, int steps) {

        return new FrogCommand(){
            boolean undo = false;

            @Override
            public boolean isUndo() {
                return undo;
            }

            @Override
            public boolean doit() {
                return frog.jump(Integer.valueOf(steps));
            }

            @Override
            public boolean undo() {
                undo = true;
                return frog.jump(Integer.valueOf(-steps));
            }

        };
    }

}
