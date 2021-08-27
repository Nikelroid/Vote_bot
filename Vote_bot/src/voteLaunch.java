import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.util.ArrayList;

public class voteLaunch {
    private static final Logger logger = LogManager.getLogger(voteLaunch.class);
    jsonCreator jsonCreator = new jsonCreator();
    textBuilder textBuilder = new textBuilder();
    checkVoted checkVoted = new checkVoted();
    submitVote submitVote = new submitVote();
    public static ArrayList<voteObject> Votes = new ArrayList<>();
    public JSONObject vote(String text,String user,String group) {
        JSONObject jsonOutput = new JSONObject();

        if (text.equals("/vote")){
            Votes.add(new voteObject(group));
            logger.info("output json create object generated");
            return jsonCreator.get("Bot started. reply this message " +
                    "question in this format  q[YOUR QUESTION]",group);
        }else if(text.startsWith("/q")){
            for (int i = 0; i < Votes.size(); i++) {
                if (Votes.get(i).getGroupName().equals(group) && Votes.get(i).getQuestion()==null
                && !Votes.get(i).isCreated()){
                    Votes.get(i).setQuestion(text.substring(3,text.length()-1));
                    logger.info("output json question generated");
                    return jsonCreator.get("Question submitted. reply this message " +
                            "options in this format  o[OPTION]",group);
                }
            }
        }else if(text.startsWith("/o")){
            for (int i = 0; i < Votes.size(); i++) {
                if (Votes.get(i).getGroupName().equals(group) && Votes.get(i).getQuestion()!=null
                && !Votes.get(i).isCreated()){
                    Votes.get(i).addOptions(text.substring(3,text.length()-1));
                    Votes.get(i).addUser(new ArrayList<>());
                    logger.info("output json options generated");
                    if (Votes.get(i).getOptionsSize()==1) {
                        return jsonCreator.get("Option submitted. reply another option " +
                                "in the same format", group);
                    }else{
                        return jsonCreator.get("Option submitted. reply another option " +
                                "in the same format or reply \"create\" command and vote by" +
                                "  /ANSWER", group);
                    }
                }
            }
        }else if(text.equals("/create")){
            for (int i = 0; i < Votes.size(); i++) {
                if (Votes.get(i).getGroupName().equals(group) && Votes.get(i).getQuestion()!=null
                && !Votes.get(i).isCreated() && Votes.get(i).getOptionsSize()>1){
                    Votes.get(i).setCreated(true);
                    String options = textBuilder.builder(Votes.get(i).getOptions(),
                            Votes.get(i).getUsers());
                    return jsonCreator.get("Vote: "+Votes.get(i).getQuestion()
                            + "  options: "+options, group);
                }
            }
        }else if(text.startsWith("/")){
            for (int i = 0; i < Votes.size(); i++) {
                if (Votes.get(i).getGroupName().equals(group) && Votes.get(i).getQuestion() != null
                        && Votes.get(i).isCreated()) {

                    try {
                        int response = Integer.parseInt(text.substring(1))-1;
                        if (checkVoted.check(Votes.get(i).getUsers(), user, response)) {
                            return jsonCreator.get("You voted to this option before", group);
                        } else {
                            submitVote.set(Votes.get(i).getUsers(), user, response, i);
                        }
                    }catch (Exception e){
                            e.printStackTrace();
                        jsonOutput.put("text", "Can't define message!")
                                .put("user", group);
                        logger.info("output json generated");
                        return jsonOutput;
                    }

                    String options = textBuilder.builder(Votes.get(i).getOptions(),
                            Votes.get(i).getUsers());
                    return jsonCreator.get("Vote: "+Votes.get(i).getQuestion()
                            + "  options: "+options, group);
                }
            }
        }
        jsonOutput.put("text", "Can't define message!")
                .put("user", group);
        logger.info("output json generated");
        return jsonOutput;
    }
}
