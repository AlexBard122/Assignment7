package accidentpack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class ReportHelper {
	
	/**
	 * @author abard
	 * counts the number of reports on and after a given date in a given state
	 * @param state
	 * @param date
	 * @param report
	 * @return int
	 */
	public static int countReports(String state, LocalDate date, TreeMap<String, TreeMap<LocalDate, List<report>>> report) {
		TreeMap<LocalDate,List<report>> map = report.get(state);
		if(map == null) {
			return 0;
		}else {
			int count = map.tailMap(date).size();
			return count;
		}
	}
	
	/**
	 * @author abard
	 * calculates elapsed time and converts it to seconds
	 * @param time1
	 * @param time2
	 * @return String
	 */
	public static String convertTime(long time1, long time2) {
		long elapsedTime = time2 - time1;
		double elapsedTimeSeconds;
		elapsedTimeSeconds = elapsedTime / 1000000000;
		return String.valueOf(elapsedTimeSeconds);
	}
	
    
    /**
     * @author Devin C
     * Reads lines from a csv file and converts them to report objects
     * @param filename the path to the csv file
     * @return a map of states to tree maps of accidents
     */
    public static TreeMap<String, TreeMap<LocalDate, List<report>>> readAccidentReports(String filename) {
        TreeMap<String, TreeMap<LocalDate, List<report>>> stateAccidentsMap = new TreeMap<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine(); // Skip header line
            int count = 0;
            int limit = 1000000;
            while ((line = br.readLine()) != null && count < limit) {
                report accidentReport = readfile(line);
                String state = accidentReport.getState();
                LocalDate startDate = accidentReport.getStartTime();
                
                // Check if the state already exists in the outer map
                if (!stateAccidentsMap.containsKey(state)) {
                    // If not, create a new TreeMap for the state
                    stateAccidentsMap.put(state, new TreeMap<>());
                }
                
                // Get the TreeMap for the current state
                TreeMap<LocalDate, List<report>> stateAccidents = stateAccidentsMap.get(state);
                
                // Check if the date already exists in the inner map
                if (!stateAccidents.containsKey(startDate)) {
                    // If not, create a new ArrayList for the date
                    stateAccidents.put(startDate, new ArrayList<>());
                }
                
                // Get the ArrayList corresponding to the date
                List<report> accidentsOnDate = stateAccidents.get(startDate);
                // Add the accident report to the list
                accidentsOnDate.add(accidentReport);
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return stateAccidentsMap;
    }
    
    /**
     * @author abard & Devin C
     * Reads lines from a csv file and converts them to report objects
     * @param line the line being read into a report object
     */
    private static report readfile(String line) {
        String[] items = line.split(",");
        String id = items[0];
        int severity = Integer.parseInt(items[1]);
        LocalDate startTime = dateConvert(items[2]);
        LocalDate endTime = dateConvert(items[3]);
        String street = items[4];
        String city = items[5];
        String county = items[6];
        String state = items[7];
        int temperature = Integer.parseInt(items[8].split("\\.")[0]);
        int humidity = Integer.parseInt(items[9].split("\\.")[0]);
        int visibility = Integer.parseInt(items[10].split("\\.")[0]);
        String weatherCondition = items[11];
        boolean crossing = Boolean.parseBoolean(items[12]);
        boolean sunrise = items[13].equals("Night")?true:false;
        report r = new report(id, severity, startTime, endTime, street, city, county, state,
                temperature, humidity, visibility, weatherCondition, crossing, sunrise);
        return r;
    }
    
    //  Taken from Dr. Behrooz Mansouri
    
    //  Create a formatter with the specific date-time format
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     * @author Dr. Behrooz Mansouri
     * This method takes in the string representation of dateTime and return LocalDate object
     * @param dateTimeString
     * @return
     */
    public static LocalDate dateConvert(String dateTimeString)
    {
        // for some of the instances the after seconds there are 0s; this line will remove them
        dateTimeString = dateTimeString.split("\\.")[0];

        // Parse the string using the formatter
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(dateTimeString, formatter);
          } catch (Exception e) {
            // Handle parsing exception, e.g., invalid format, invalid date
            System.err.println("Error parsing date-time string: " + e.getMessage());
            localDate = null;
          }
        return localDate;
    }

}
