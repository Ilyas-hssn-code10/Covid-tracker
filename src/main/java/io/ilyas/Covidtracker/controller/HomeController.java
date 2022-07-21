package io.ilyas.Covidtracker.controller;

import io.ilyas.Covidtracker.models.LocationStats;
import io.ilyas.Covidtracker.services.CovidDataServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CovidDataServices covidDataServices;
    @GetMapping("/")

    public String home(Model model){
       List<LocationStats> allStats=covidDataServices.getAllStats();
       int totalReportedCases=allStats.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
        int totalNewCases=allStats.stream().mapToInt(stat->stat.getDiffFromPrevDay()).sum();
       model.addAttribute("locationStats",covidDataServices.getAllStats());
       model.addAttribute("totalReportedCases",totalReportedCases);
        model.addAttribute("totalNewCases",totalNewCases);
        return "home";
    }
}
