package com.spring.DataWarehouse.controller;

import com.spring.DataWarehouse.service.ScrapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api")
@org.springframework.web.bind.annotation.RestController
public class ScrapperController {
    private final ScrapperService scrapperService;

    @Autowired
    public ScrapperController(ScrapperService scrapperService) {
        this.scrapperService = scrapperService;
    }

    @PostMapping(value = "/extract")
    public ResponseEntity startScrapping() {
        return new ResponseEntity<>(this.scrapperService.runScrapping(), HttpStatus.OK);
    }

    @PostMapping(value = "/raceFile")
    public ResponseEntity writeToFile() {

        return new ResponseEntity<>(this.scrapperService.writeRacesToCsvFile(),HttpStatus.OK);
    }

    @PostMapping(value = "/personFiles")
    public ResponseEntity personWriteToFile() {

        return new ResponseEntity<>(this.scrapperService.personWriteToCsvFile(),HttpStatus.OK);
    }

    @PostMapping(value = "/transform")
    public ResponseEntity startTransform() {
        this.scrapperService.transform();
        return new ResponseEntity<>(this.scrapperService.transform(),HttpStatus.OK);
    }

    @PostMapping(value = "/load")
    public ResponseEntity startLoad() {
        this.scrapperService.saveRaces();
        return new ResponseEntity<>(this.scrapperService.saveRaces(),HttpStatus.OK);
    }

    @GetMapping(value = "/races")
    public ResponseEntity getAll() {
        return new ResponseEntity<>(scrapperService.getRaceList(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/races")
    public ResponseEntity removeAll() {
        scrapperService.removeAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
