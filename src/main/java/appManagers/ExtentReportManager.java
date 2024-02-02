package appManagers;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

	
	public static ExtentReports createExtentReport() {
        // Get current project path
        String projectPath = System.getProperty("user.dir");

        // Create Reports folder if it doesn't exist
        File reportsDir = new File(projectPath + "/Reports");
        if (!reportsDir.exists()) {
            reportsDir.mkdir();
        }

        // Generate file name with date and time
        String fileName = "Report_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".html";

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(projectPath + "/Reports/" + fileName);
        // Create ExtentReports instance with path and file name
        
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        return extent;
    }
	
}
