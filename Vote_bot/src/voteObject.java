import java.util.ArrayList;

public class voteObject {
    private String groupName;
    private String question =null;
    private ArrayList<String> options =  new ArrayList<>();
    private ArrayList<ArrayList<String>> users =  new ArrayList<>();
    private boolean created = false;

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public voteObject(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public int getOptionsSize() {
       return (this.options.size());
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public void addOptions(String response) {
        ArrayList<String> tempOptions = getOptions();
        tempOptions.add(response);
        setOptions(tempOptions);
    }

    public ArrayList<ArrayList<String>> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<ArrayList<String>> users) {
        this.users = users;
    }
    public void addUser(ArrayList<String> response) {
        ArrayList<ArrayList<String>> tempUser = getUsers();
        tempUser.add(response);
        setUsers(tempUser);
    }
}
