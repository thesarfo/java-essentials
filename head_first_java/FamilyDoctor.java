package head_first_java;

// this class inherits from the Doctor class
public class FamilyDoctor extends Doctor {
    boolean makesHouseCalls;

    void giveAdvice(){
        System.out.println("Giving homespun advice");
    }
}
