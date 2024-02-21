package sklepanjeTesti;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ReportBackuper {
	
	public void backUpReport() {
		
		
		
		 String sourceFilePath = System.getProperty("user.dir") + "/reporti/SklepanjeReport.html";
	     String backupFilePath = System.getProperty("user.dir") + "/reporti/BackupSklepanjeReport.html";
	     
	        try {
	            Path sourcePath = Paths.get(sourceFilePath);
	            Path backupPath = Paths.get(backupFilePath);

	         // Check if the source file is empty or has less data than the backup file
	            long sourceFileSize = Files.size(sourcePath);
	            long backupFileSize = Files.size(backupPath);
	            if (sourceFileSize == 0 || sourceFileSize < backupFileSize) {
	                System.out.println("Source file is empty or has less data than the backup file. Skipping backup.");
	                return;
	            }
	            
	            // Create a backup by copying the contents of the source file
	            Files.copy(sourcePath, backupPath, StandardCopyOption.REPLACE_EXISTING);

	            System.out.println("HTML file backup created successfully.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }						     
	}
}
