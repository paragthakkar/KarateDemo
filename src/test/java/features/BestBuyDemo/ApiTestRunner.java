package features.BestBuyDemo;

import org.apache.commons.io.FileUtils;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import net.masterthought.cucumber.ReportBuilder;
import org.junit.Test;
import net.masterthought.cucumber.Configuration;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ApiTestRunner {

    @Test
    public void testParallel() {
        Results results = Runner.path("classpath:features/BestBuyDemo").parallel(1);
        generateReport(results.getReportDir());
        assertTrue(results.getErrorMessages(), results.getFailCount() == 0);
    }

    public static void generateReport(String karateOutputPath) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);
        List<String> jsonPaths = new ArrayList(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        Configuration config = new Configuration(new File("target"), "demo");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }
}
