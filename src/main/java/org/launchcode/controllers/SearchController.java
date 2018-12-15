package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {
    static HashMap<String, String> columnChoices = new HashMap<>();

    public SearchController() {

        columnChoices.put("core_competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position_type", "Position Type");
        columnChoices.put("all", "All");

    }

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")// method = RequestMethod.POST
    public String results(Model model,@RequestParam String searchType, @RequestParam String searchTerm) {
        if (searchType.equals("all")&& searchTerm.isEmpty()){
            ArrayList<HashMap<String, String>> jobs = JobData.findAll();
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("jobs", jobs);
            return "search";
        }
        if (searchType.equals("all")&& !searchTerm.isEmpty()){
            ArrayList<HashMap<String, String>> jobs = JobData.findByValue(searchTerm);
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("jobs", jobs);
            return "search";
        }
        else {
            ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("jobs", jobs);

            return "search";
        }
    }
}
