package io.javamain.Coronavirustracker;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronavirusdataService {

		private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
		
		private List<LocationStatsModel> allStats = new ArrayList<>();
		
		@PostConstruct
		@Scheduled(cron = "* * 1 * * *")
		public void fetchVirusData() throws IOException, InterruptedException
		{
			 List<LocationStatsModel> newStats = new ArrayList<>();
			 HttpClient client = HttpClient.newHttpClient();
			 HttpRequest request = HttpRequest.newBuilder()
					 .uri(URI.create(VIRUS_DATA_URL))
			 	     .build();
			 HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
			 	
			 StringReader csvBodyReader = new StringReader(httpResponse.body());
			 
			 Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvBodyReader);
			 
			 for (CSVRecord record : records) {
				 LocationStatsModel locationstat = new LocationStatsModel();
				 locationstat.setState(record.get("Province/State"));
				 locationstat.setCountry(record.get("Country/Region"));
				 int latestCases = Integer.parseInt(record.get(record.size() -1));
				 int prevDayCases = Integer.parseInt(record.get(record.size() -2));
				 locationstat.setLatestTotalCases(latestCases);
				 locationstat.setDiffFromPrevDay(latestCases - prevDayCases);
				  newStats.add(locationstat);
			 }
			 
			 this.allStats = newStats;
			 
		}

		public List<LocationStatsModel> getAllStats() {
			return this.allStats;
		}
}
