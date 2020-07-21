
public class Constants {
	final static String[] EMPLOYEE_TECH_COLUMNS = {"EmployeeID","First Name","Last Name","Hours Worked"};
	final static String[] EMPLOYEE_UTILITY_NAMES = {"EmployeeID","First Name","Last Name","Hours Worked"};
	final static String[] SCRAP_COLUMNS = {"Scrap Ticket ID #","Pounds"};
	final static String[] ROLL_COLUMNS = {"Sheet Code OR OPS Part # (NOT SIZE)", 
						   "Extrusion MO #", "Date", "Time", "Roll # or OPS Lot#", 
						   "Meters", "Lbs", "Total Shots", "Part Weight (LBS)"};
	
	final static String DB_URL = "jdbc:sqlite:FormingReport.db";
	final static int TABLE_ROW_HEIGHT = 20;
}