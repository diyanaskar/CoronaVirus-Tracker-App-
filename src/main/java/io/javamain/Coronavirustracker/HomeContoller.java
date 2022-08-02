package io.javamain.Coronavirustracker;

import io.javamain.Coronavirustracker.CoronavirusdataService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeContoller {
	
	@Autowired
	CoronavirusdataService coronavirusdataservice;
	
	@GetMapping("/")
	public String home(Model model, String s, String o) {
		List<LocationStatsModel> allStats = coronavirusdataservice.getAllStats();
		int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		model.addAttribute( s= "locationStats", allStats);
		model.addAttribute( s= "totalReportedCases", totalReportedCases);
		model.addAttribute( s= "totalNewCases", totalNewCases);
		return "home";
	}

}
