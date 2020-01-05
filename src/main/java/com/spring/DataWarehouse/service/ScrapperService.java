package com.spring.DataWarehouse.service;

import com.spring.DataWarehouse.model.Person;
import com.spring.DataWarehouse.model.Race;
import com.spring.DataWarehouse.repository.RaceRepository;
import com.spring.DataWarehouse.response.Statistic;
import com.spring.DataWarehouse.scrapper.Scrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScrapperService {

    private Scrapper scrapper;
    private List<Race> raceList;
    private RaceRepository raceRepository;

    @Autowired
    public ScrapperService(Scrapper scrapper, RaceRepository raceRepository) {
        this.scrapper = scrapper;
        this.raceRepository = raceRepository;
    }

    public Statistic runScrapping() {
        System.out.println("Started Scrapping ");
        this.raceList = scrapper.getRaces();
        System.out.println("Finished Scrapping ");
        return new Statistic("extract data",
                "Extracted " + raceList.size() + " races.");
    }

    public Statistic transform() {
        List<Person> personToRemove = new ArrayList<>();
        try {
            for (Race race : raceList) {
                long time = 0;
                for (Person person : race.getPersonList()) {
                    personToRemove = new ArrayList<>();
                    if (person.getTime().contains("—")) {
                        personToRemove.add(person);
                    } else {
                        LocalTime localTime = LocalTime.parse(person.getTime());
                        time = time + localTime.getSecond() + localTime.getMinute() * 60 + localTime.getHour() * 3600;
                    }
                }
                personToRemove.forEach(per-> race.getPersonList().remove(per));
                long averangeTime = time / race.getPersonList().size();
                long hours = averangeTime / 3600;
                averangeTime = averangeTime % 3600;
                long minutes = averangeTime / 60;
                long seconds = averangeTime % 60;
                race.setAverageTime(hours + ":" + minutes + ":" + seconds);
            }
            saveRaces();
        } catch (Exception e) {
            System.out.println("Error in transform ");
        }
        return new Statistic("transform data",
                "Transformed " + raceList.size() + " races.");
    }

    public Statistic saveRaces() {
        if (!raceList.isEmpty()) {

            for (Race race : raceList) {
                Optional<Race> foundedRace = raceRepository.findByName(race.getName());
                if (foundedRace.isPresent()) {
                    foundedRace.get().setAverageTime(race.getAverageTime());
                    raceRepository.save(foundedRace.get());
                } else {
                    raceRepository.save(race);
                }
            }
            return new Statistic("save data",
                    "Saved " + raceList.size() + " races.");

        }
        return new Statistic("save races",
                "Saved 0 races.");
    }

    public List<Race> getRaceList() {
        List<Race> raceList = new ArrayList<>();
        raceRepository.findAll().forEach(raceList::add);
        return raceList;
    }

    public void removeAll() {
        raceRepository.deleteAll();
    }

    public Statistic personWriteToCsvFile() {
        Integer personNumber =0;
        for (Race race : this.raceList) {
            try {
                FileWriter csvWriter = new FileWriter(new File(System.getProperty("user.dir") + "\\person\\" , race.getName() + ".csv"));
                csvWriter.append("Id");
                csvWriter.append(",");
                csvWriter.append("Name");
                csvWriter.append(",");
                csvWriter.append("Race");
                csvWriter.append(",");
                csvWriter.append("Time");
                csvWriter.append("\n");

                for (Person person : race.getPersonList()) {
                    csvWriter.append(String.valueOf(person.getId())).append(",").append(person.getName())
                            .append(",").append(person.getRace()).append(String.valueOf(','))
                            .append(person.getTime()).append("\n");
                    personNumber++;
                }
                csvWriter.flush();
                csvWriter.close();

            } catch (IOException e) {
                System.out.println("Error in saving to file ");
            }
        }
        return new Statistic("save parsed data", "Saved " + personNumber + " person in " + this.raceList.size() + " races.");
    }

    public Statistic writeRacesToCsvFile() {
        List<Race> raceList = getRaceList();
        try {
            FileWriter csvWriter = new FileWriter("Races.csv");
            csvWriter.append("Id");
            csvWriter.append(",");
            csvWriter.append("Name");
            csvWriter.append(",");
            csvWriter.append("Type");
            csvWriter.append(",");
            csvWriter.append("Link");
            csvWriter.append(",");
            csvWriter.append("AverageTime");
            csvWriter.append("\n");

            for (Race race : raceList) {
                csvWriter.append(race.getId() + "," + race.getName() + ","
                        + race.getType() + ',' + race.getLink() + ','
                        + race.getAverageTime() + "\n");
            }
            csvWriter.flush();
            csvWriter.close();

        } catch (IOException e) {
            System.out.println("Error in saving to file ");
        }
        return new Statistic("save races", "Saved " +  raceList.size() + " races.");

    }
}
