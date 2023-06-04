package org.example;

public class Questioin implements Comparable<Questioin> {

   private  String link;
   private Integer questionnumber ;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    private String topic;

    public Questioin(String link, int questionnumber, String topic) {
        this.link = link;
        this.questionnumber = questionnumber;
        this.topic = topic;
    }


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getQuestionnumber() {
        return questionnumber;
    }

    public void setQuestionnumber(int questionnumber) {
        this.questionnumber = questionnumber;
    }

    @Override
    public int compareTo(Questioin o) {
        if(questionnumber==o.questionnumber)
            return 0;
        else if(questionnumber>o.questionnumber)
            return 1;
        else
            return -1;
    }
}

