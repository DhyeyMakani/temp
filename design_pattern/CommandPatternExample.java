// step 1: command interface
interface Command {
    void execute();
}

// step 2: receiver - the actual TV that performs actions
class TV {
    void turnOn() {
        System.out.println("TV is ON");
    }

    void turnOff() {
        System.out.println("TV is OFF");
    }
}

// step 3: concrete Command Classes
class TurnOnCommand implements Command {
    private TV tv;

    public TurnOnCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.turnOn();
    }
}

class TurnOffCommand implements Command {
    private TV tv;

    public TurnOffCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.turnOff();
    }
}

// step 4: invoker - remote that calls the command
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}

// step 5: main class
public class CommandPatternExample {
    public static void main(String[] args) {
        TV myTV = new TV();

        Command turnOn = new TurnOnCommand(myTV);
        Command turnOff = new TurnOffCommand(myTV);

        RemoteControl remote = new RemoteControl();

        // turn on
        remote.setCommand(turnOn);
        remote.pressButton();

        // turn off
        remote.setCommand(turnOff);
        remote.pressButton();
    }
}