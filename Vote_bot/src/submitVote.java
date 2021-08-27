import java.util.ArrayList;

public class submitVote {
    public void set(ArrayList<ArrayList<String>> users, String username, int option, int voteCount) {

        for (int i = 0; i < users.size(); i++) {
            users.get(i).remove(username);
        }
            users.get(option).add(username);
        voteLaunch.Votes.get(voteCount).setUsers(users);
    }
}
