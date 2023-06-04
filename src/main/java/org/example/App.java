package org.example;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
       Scrapper scrapper=new Scrapper("google");
      scrapper.sortquestionwise(scrapper.
              CollectQuestionsTopicwise(scrapper.
                      extractQuestionLinks(scrapper.
                              getAllpagesLinks(scrapper.getNumberOFPages()))));
    }
}
