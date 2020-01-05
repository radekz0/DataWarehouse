package com.spring.DataWarehouse.scrapper;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.spring.DataWarehouse.model.Person;
import com.spring.DataWarehouse.model.Race;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Scrapper {
    public Scrapper() {
    }

    private HtmlPage scrapPageBySearchQuery(String searchQuery) throws Exception {
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        HtmlPage page = client.getPage(searchQuery);
        return page;
    }

    public List<Race> getRaces() {
        List<Race> racesUrls = getRaceUrl();
        for (Race race : racesUrls) {
            try {
                HtmlPage racePage = scrapPageBySearchQuery("https://enduhub.com/" + race.getLink());
                List<HtmlTable> htmlTables = racePage.getByXPath("//table");
                List<HtmlTableBody> bodies = htmlTables.get(0).getBodies();
                for(HtmlTableBody body: bodies){
                    race.setPersonList(parseTableBody(race.getName(), body));
                }
            } catch (Exception e) {
                System.out.println("Error in scrapping for race");
            }
        }
        return racesUrls;
    }

    private List<Person> parseTableBody( String race, HtmlTableBody body) {
        List<Person> personList = new ArrayList<>();
        DomNodeList<HtmlElement> rows = body.getElementsByTagName("tr");
        for(HtmlElement row: rows){
            HtmlTableRow htmlTableRow = (HtmlTableRow) row;
            if(htmlTableRow.getAttribute("class").contains("Zawody")){
                addPersonIfPossible(personList, race, htmlTableRow);
            }
        }
        return personList;
    }

    private void addPersonIfPossible(List<Person> personList, String race, HtmlTableRow htmlTableRow) {
        List<HtmlTableCell> cells = htmlTableRow.getCells();
        String name = null;
        String time = null;
        for(HtmlTableCell cell: cells){

            if(cell.getAttribute("class").contains("imie_nazwisko")){
                name = cell.asText();
            }
            if(cell.getAttribute("class").contains("wynik")){
                time = cell.asText();
            }

        }
        if(name!=null && time!=null) {
            personList.add(new Person(null,name,race,time));
        }
    }

    private List<Race> getRaceUrl() {
        List<Race>  racesUrls = new ArrayList<>();
        try {
            HtmlPage htmlPage = scrapPageBySearchQuery("https://enduhub.com/pl/lista/wszystkie/imprezy/");
            List<HtmlAnchor> htmlAnchors = htmlPage.getByXPath("//*[contains(text(),'ostatnia')]");

            int numberOfPages = Integer.parseInt(htmlAnchors.get(0).getHrefAttribute().replaceAll("\\D+", ""));
            int i = 1;
            while (i < 2) {
                HtmlPage htmlPage1 = scrapPageBySearchQuery("https://enduhub.com/pl/lista/wszystkie/imprezy/?page=" + i);
                List<HtmlTable> htmlTables = htmlPage1.getByXPath("//table[@class='enduevent']");
                if (!htmlTables.isEmpty()) {
                    for (HtmlTable table : htmlTables) {
                        //HtmlTableRow htmlElement = table.getFirstByXPath("//td[@class='name']");
                        getRacesUrlForTable(racesUrls, table);
                        racesUrls.get(0);
                    }
                }
                i++;
            }

        } catch (Exception e) {
            System.out.println("Error in scrapping");
            return new ArrayList<>();
        }
        return racesUrls;
    }

    private void getRacesUrlForTable(List<Race> races, HtmlTable table) {
        List<HtmlTableDataCell> htmlAnchors = table.getByXPath("//td[@class='name']");
        for (HtmlTableDataCell htmlTableDataCell : htmlAnchors) {
            Race race = new Race();
            HtmlAnchor anchor = (HtmlAnchor) htmlTableDataCell.getFirstChild();
            race.setName(anchor.asText());
            race.setLink(anchor.getHrefAttribute());
            HtmlTableRow htmlTableRow = (HtmlTableRow) htmlTableDataCell.getParentNode();
            Optional<HtmlTableCell> distance = htmlTableRow.getCells().stream().filter(htmlTableCell -> htmlTableCell.getAttribute("class").contains("distance")).findAny();
            distance.ifPresent(dis-> race.setType(dis.asText()));
            race.setLink(anchor.getHrefAttribute());
            races.add(race);
        }
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