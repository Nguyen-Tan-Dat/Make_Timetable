import model.Excel;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class MainTest {
    public static void main1(String[] args) {
        Excel e = new Excel();
        var l = e.getMuleData();
        for (var i : l)
            System.out.println(i);
    }

    public static void main(String[] args) throws IOException {
        String fileName = "src/main/resources/DCT1.docx";
        try (XWPFDocument doc = new XWPFDocument(Files.newInputStream(Paths.get(fileName)))) {
//            XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(doc);
//            String docText = xwpfWordExtractor.getText();
//            System.out.println(docText);
            Iterator<IBodyElement> docElementsIterator = doc.getBodyElementsIterator();
            //Iterate through the list and check for table element type
            while (docElementsIterator.hasNext()) {
                IBodyElement docElement = docElementsIterator.next();
                String elementName=docElement.getElementType().name();
                if (elementName.equalsIgnoreCase("table")) {
                    List<XWPFTable> xwpfTableList = docElement.getBody().getTables();
                    for (XWPFTable xwpfTable : xwpfTableList) {
                        System.out.println("Total Rows : " + xwpfTable.getNumberOfRows());
                        for (int i = 0; i < xwpfTable.getRows().size(); i++) {
                            try {
                                var column1 = xwpfTable.getRow(i).getCell(0).getText();
                                Integer.parseInt(column1);
                                StringBuilder sb = new StringBuilder(column1 + "\t|\t");
                                for (int j = 1; j < xwpfTable.getRow(i).getTableCells().size(); j++) {
                                    String data = xwpfTable.getRow(i).getCell(j).getText();
                                    for (int k = 1; data.isEmpty(); k++) {
                                        data = xwpfTable.getRow(i - k).getCell(j).getText();
                                    }
                                    sb.append(data).append("\t|\t");
                                }
                                System.out.println(sb);
                            } catch (Exception ignored) {
                            }
                        }
                    }
                }
            }
        }
    }
}  