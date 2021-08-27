import java.util.ArrayList;

public class textBuilder {
    public String builder(ArrayList<String>options ,ArrayList<ArrayList<String>> users ) {
        StringBuilder optionsBuilder = new StringBuilder();

        optionsBuilder.append(1).append("-").
                append(options.get(0)).append(" >(").append(users.get(0).size()).append(") ");

        for (int j = 1; j < options.size(); j++) {
            optionsBuilder.append(" | ").append(j+1).append("-").
                    append(options.get(j)).append(" >(").append(users.get(j).size()).append(") ");
        }

        return optionsBuilder.toString();
    }
}
