package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class Scrapper {

    private String examCompanyName = null;

    public Scrapper(String examCompanyName) {
        this.examCompanyName = examCompanyName;
    }

    public String getNumberOFPages() throws IOException {
        Document document = Jsoup.connect("https://www.examtopics.com/discussions/" + this.examCompanyName + "/").get();
        String numberOFPages = document.getElementsByClass("discussion-list-page-indicator").get(0).
                childNodes().get(5).childNodes().get(0).toString();
        return numberOFPages;
    }


    public List<Document> getAllpagesLinks(String numberOfPages) throws IOException {
        List<Document> scrappedDocuments = new ArrayList<>();
        for (int pageNo = 1; pageNo <= Integer.parseInt(numberOfPages); pageNo++) {
            Document document = Jsoup.connect("https://www.examtopics.com/discussions/" + this.examCompanyName + "/" + pageNo + "/").get();
            scrappedDocuments.add(document);
            System.out.println("web page " + pageNo + " added to scrapped list");
        }
        System.out.println("returning scrapped documents to extract questions link");
        return scrappedDocuments;
    }


    public List<String> extractQuestionLinks(List<Document> documents) {

        List<String> questions = new ArrayList<>();

        for (Document document : documents) {

            Elements elementsfromDocument = document.getElementsByClass("dicussion-title-container");

            for (Element element : elementsfromDocument) {
                String link = element.childNodes().get(1).childNodes().get(1).attributes().get("href");
                if (link.contains("architect")) {
                    questions.add("https://www.examtopics.com" + link);
                }

            }

        }

     //   System.out.println(questions);

        return questions;
    }


    public Map<String, List<Questioin>> CollectQuestionsTopicwise(List<String> unsortedQuestionList) {

        Map<String, List<Questioin>> multimap = new HashMap<>();
        String key = null;
        String questionnumberwithslash=null;
        String questionnumber=null;
        for (String link : unsortedQuestionList) {
            String[] arrays = link.split("-");
            key = arrays[5] + "-" + arrays[6];
            questionnumberwithslash=arrays[arrays.length-1];
            questionnumber=questionnumberwithslash.substring(0,questionnumberwithslash.length()-1);
            if (multimap.containsKey(key)) {
                multimap.get(key).add(new Questioin(link,Integer.parseInt(questionnumber),key));
            } else {
                List<Questioin> values = new ArrayList<Questioin>();
                values.add(new Questioin(link,Integer.parseInt(questionnumber),key));
                multimap.put(key, values);
            }
        }


        return multimap;
    }




    public void sortquestionwise(Map<String, List<Questioin>> stringListMap)
    {

        Set<String> strings = stringListMap.keySet();
        List<topic> arr = new ArrayList<>();

        for (String x:strings)
        {
            arr.add(new topic(x,Integer.parseInt(x.split("-")[1])));

        }

        Collections.sort(arr);


        for (topic key:arr)
        {

            List<Questioin> questioins = stringListMap.get(key.getTopic());
            Collections.sort(questioins);
            questioins.forEach(questioin -> System.out.println(questioin.getLink()));
        }


    }

}
