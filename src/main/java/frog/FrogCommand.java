package frog;

public interface FrogCommand {
    boolean doit();
    boolean undo();
    boolean isUndo();
}
