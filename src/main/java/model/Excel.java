package model;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Excel {
    private final JFileChooser fileChooser = new JFileChooser();

    public Excel() {}
    public ArrayList<Module> getMuleData(){
        ArrayList<Module> rs=new ArrayList<>();
        try {
            File file = new File("src/main/New Microsoft Excel Worksheet.xlsx");   //creating a new file instance
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
            Iterator<Row> itr = sheet.iterator();    //iterating over excel file
            while (itr.hasNext()) {
                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                if (cellIterator.next().getCellType() == CellType.NUMERIC) {
                    Module m=new Module();
                    m.setSubjectID((int) cellIterator.next().getNumericCellValue());
                    m.setSubjectName(cellIterator.next().getStringCellValue());
                    m.setNumberOfCredits((byte) cellIterator.next().getNumericCellValue());
                    cellIterator.next();
                    m.setLecturer(cellIterator.next().getStringCellValue());
                    m.setLecturerID((int) cellIterator.next().getNumericCellValue());
                    m.setGroup((byte) cellIterator.next().getNumericCellValue());
                    m.setTeam((byte) cellIterator.next().getNumericCellValue());
                    m.setSchoolDay((byte) cellIterator.next().getNumericCellValue());
                    m.setClassesStart((byte) cellIterator.next().getNumericCellValue());
                    m.setNumberOfLessons((byte)cellIterator.next().getNumericCellValue());
                    m.setClassroom(cellIterator.next().getStringCellValue());
                    m.setClassName(cellIterator.next().getStringCellValue());
                    rs.add(m);
//                    while (cellIterator.hasNext()) {
//                        Cell cell = cellIterator.next();
//                        switch (cell.getCellType()) {
//                            case STRING:    //field that represents string cell type
//                                System.out.print(cell.getStringCellValue() + "\t\t\t");
//                                break;
//                            case NUMERIC:    //field that represents number cell type
//                                System.out.print(cell.getNumericCellValue() + "\t\t\t");
//                                break;
//                            default:
//                        }
//                    }
//                    System.out.println("");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
