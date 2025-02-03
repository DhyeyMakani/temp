// step 1: create an abstract handler(base class)
abstract class LeaveHandler {
    protected LeaveHandler nextHandler;  // next handler in the chain

    public void setNextHandler(LeaveHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void processLeave(int days);
}

// step 2: create concrete handlers
class TeamLead extends LeaveHandler {
    public void processLeave(int days) {
        if (days <= 2) {
            System.out.println("team lead approved " + days + " days leave.");
        } else if (nextHandler != null) {
            nextHandler.processLeave(days);  // pass to next handler
        }
    }
}

class Manager extends LeaveHandler {
    public void processLeave(int days) {
        if (days <= 5) {
            System.out.println("manager approved " + days + " days leave.");
        } else if (nextHandler != null) {
            nextHandler.processLeave(days);
        }
    }
}

class Director extends LeaveHandler {
    public void processLeave(int days) {
        System.out.println("director approved " + days + " days leave.");
    }
}

// step 3: test the chain of responsibility
public class ChainOfResponsibilityExample {
    public static void main(String[] args) {
        LeaveHandler teamLead = new TeamLead();
        LeaveHandler manager = new Manager();
        LeaveHandler director = new Director();

        // setting up the chain
        teamLead.setNextHandler(manager);
        manager.setNextHandler(director);

        // test cases
        teamLead.processLeave(1);  // handled by team lead
        teamLead.processLeave(3);  // passed to manager
        teamLead.processLeave(7);  // passed to director
    }
}