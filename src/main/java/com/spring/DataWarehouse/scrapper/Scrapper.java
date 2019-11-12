package com.spring.DataWarehouse.scrapper;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.w3c.dom.html.HTMLElement;

import java.net.URLEncoder;
import java.util.List;

public class Scrapper {
    private static String searchQuery = "Jan Kowalski" ;

    public void scrap(){

//        WebClient client = new WebClient();
//        client.getOptions().setCssEnabled(false);
//        client.getOptions().setJavaScriptEnabled(false);
//        try {
//            String searchUrl = "https://newyork.craigslist.org/search/sss?sort=rel&query=" + URLEncoder.encode(searchQuery, "UTF-8");
//            HtmlPage page = client.getPage(searchUrl);
//            List<HTMLElement> items = (List<HtmlElement>) page.getByXPath();
//            if(!items.isEmpty()){
//                for(HTMLElement element: items){
//
//                }
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
    }
}

