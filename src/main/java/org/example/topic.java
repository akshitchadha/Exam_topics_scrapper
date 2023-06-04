package org.example;

import java.util.Comparator;

public class topic implements Comparable<topic>{


    String topic;
    Integer number;

    public topic(String topic, Integer number) {
        this.topic = topic;
        this.number = number;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public int compareTo(topic o) {
        if(number==o.getNumber())
            return 0;
        else if(number >o.getNumber())
            return 1;
        else
            return -1;
    }
}

