import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ParsingZIPTest {
    private ClassLoader cl = ParsingZIPTest.class.getClassLoader();

    @Test
    void ParsingZipForPDF() throws Exception {
        try (InputStream is = cl.getResourceAsStream("TestFile/HW_10.zip");
             ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zs.getNextEntry()) != null) {
                if (entry.getName().equals("HW_10.pdf")) {
                    PDF pdfFile = new PDF(zs);
                    Assertions.assertEquals("Hello World! My name is Qa Engineer", pdfFile.text);
                }
            }
        }
    }

    @Test
    void ParsingZipForCSV() throws Exception {
        try (InputStream is = cl.getResourceAsStream("TestFile/HW_10.zip");
             ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zs.getNextEntry()) != null) {
                if (entry.getName().equals("HW_10.csv")) {
                    InputStreamReader isr = new InputStreamReader(zs);
                    CSVReader csvFile = new CSVReader(isr);
                    List<String[]> content = csvFile.readAll();
                    Assertions.assertArrayEquals(new String[]{"Hello World!", "Im is Devops"}, content.get(1));
                }
            }
        }
    }

    @Test
    void ParsingZipForXLSX() throws Exception {
        try (InputStream is = cl.getResourceAsStream("TestFile/HW_10.zip");
             ZipInputStream zs = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zs.getNextEntry()) != null) {
                if (entry.getName().equals("HW_10.xlsx")) {
                    XLS xlsFile = new XLS(zs);
                    Assertions.assertEquals(xlsFile.excel.getSheetAt(0).getRow(0).getCell(1).getStringCellValue(), "Hello World! My name is Qa Engineer");
                }
            }
        }
    }
}
