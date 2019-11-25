package com.spring.DataWarehouse.scrapper;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.FileWriter;
import java.net.URLEncoder;
import java.util.List;

public class Scrapper {

    private HtmlPage scrapPageBySearchQuery(String searchQuery) throws Exception{
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        String searchUrl = "https://" + URLEncoder.encode(searchQuery, "UTF-8");
        HtmlPage page = client.getPage(searchUrl);
        return page;
    }

//    void scrapTimeForPerson(){
//        try{
//            FileWriter csvWriter = new FileWriter("timeForPerson.csv");
//            HtmlPage page = scrapPageBySearchQuery();
//            List<HtmlElement> htmlItems = (List<HtmlElement>) page.getByXPath();
//            if(!htmlItems.isEmpty()){
//                for(HtmlElement htmlElement: htmlItems){
//                    csvWriter.append();
//                    csvWriter.append("\n");
//                }
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
}