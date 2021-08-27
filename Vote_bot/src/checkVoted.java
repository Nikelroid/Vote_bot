import java.util.ArrayList;

public class checkVoted {
    public boolean check(ArrayList<ArrayList<String>> users,String username,int option) {

        return users.get(option).contains(username);

    }
}
