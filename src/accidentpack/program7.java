/**
 * 
 */
package accidentpack;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author abard
 *
 */
public class program7 {

	/**
	 * @author abard
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//argument for input file
		String filePath = args[0]; 		// EX: accidents_small_sample.csv
		
		//arguments for user input
		String state = args[1]; 		// EX: IL
		String stringDate = args[2];	// EX: 2022-09-08
		LocalDate date = ReportHelper.dateConvert(args[2].concat(" 00:00:00")); 
		
		//task1 helper method 
		TreeMap<String, myAVL> report = createTreeMap(filePath);
		
		//task4 helper method
		countTotalRotations(report);
				
				
		//task2 helper method
		countReports(state, stringDate, date, report);
		
	}
	/**
	 * @author abard
	 * counts the reports in the given treemap for a given state on and after a given date
	 * @param state
	 * @param stringDate
	 * @param date
	 * @param report
	 */
	private static void countReports(String state, String stringDate, LocalDate date,
			TreeMap<String, myAVL> report) {
		long time1;
		long time2;
		String processTime;
		//counts number of reports in a given state on and after a given date
		time1 = System.nanoTime();
		int count = ReportHelper.countReportsAVL(state, date, report);
		time2 = System.nanoTime();
		processTime = ReportHelper.convertTime(time1, time2);
		System.out.println(count + " Reports are available for " + state + " on and after the date " + stringDate);
		System.out.println(processTime + " Seconds to calculate the number of reports");
	}

	/**
	 * @author abard
	 * creates a treemap and prints the time it takes to do so
	 * @param filePath
	 */
	private static TreeMap<String, myAVL> createTreeMap(String filePath) {
		long time1;
		long time2;
		String processTime;
		//creates treemaps for sorting the input file's reports
		time1 = System.nanoTime();
		TreeMap<String, myAVL> report = ReportHelper.readAccidentReports(filePath);
		time2 = System.nanoTime();
		processTime = ReportHelper.convertTime(time1, time2);
		System.out.println(processTime + " Seconds to build the AVL trees");		
		
		return report;
	}
	
	
	 /**
	 * @author Devin C
	 * Counts the total number of left and right rotations in all AVL trees attached to the TreeMap.
	 * @param report The TreeMap containing AVL trees.
	 */
	private static void countTotalRotations(TreeMap<String, myAVL> report) {
	    int totalLeftRotations = 0;
	    int totalRightRotations = 0;

	    // Iterate through TreeMap entries
	    for (Map.Entry<String, myAVL> entry : report.entrySet()) {
	        myAVL avlTree = entry.getValue();
	        totalLeftRotations += avlTree.getLeftRotationCount();
	        totalRightRotations += avlTree.getRightRotationCount();
	    }

	    System.out.println("Total number of left rotations in all AVL trees: " + totalLeftRotations);
	    System.out.println("Total number of right rotations in all AVL trees: " + totalRightRotations);
	}
}
