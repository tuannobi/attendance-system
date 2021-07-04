package com.ty.attendancesystem.helper;

import com.ty.attendancesystem.model.*;
import com.ty.attendancesystem.model.Class;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] COURSE_HEADERs = { "Id", "Name"};
    static String[] SHEET = {"Course","Class","TimeTable"};

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Course> excelToCourses(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET[0]);
            Iterator<Row> rows = sheet.iterator();

            List<Course> tutorials = new ArrayList<Course>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Course Course = new Course();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            Course.setId(currentCell.getStringCellValue());
                            break;

                        case 1:
                            Course.setName(currentCell.getStringCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                tutorials.add(Course);
            }

            workbook.close();

            return tutorials;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<Class> excelToClasses(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET[1]);
            Iterator<Row> rows = sheet.iterator();

            List<Class> classes = new ArrayList<Class>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Class clazz = new Class();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            clazz.setId(currentCell.getStringCellValue());
                            break;
                        case 1:
                            clazz.setCourse(new Course(currentCell.getStringCellValue()));
                            break;
                        case 2:
                            clazz.setTeacher(new User(currentCell.getStringCellValue()));
                            break;
                        case 3:
                            clazz.setStartDate(currentCell.getLocalDateTimeCellValue().toLocalDate());
                            break;
                        case 4:
                            clazz.setEndDate(currentCell.getLocalDateTimeCellValue().toLocalDate());
                            break;
                        case 5:
                            clazz.setNumberStudent((long)currentCell.getNumericCellValue());
                            break;
                        case 6:
                            clazz.setStatus((int) currentCell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }

                    cellIdx++;
                }

                classes.add(clazz);
            }

            workbook.close();

            return classes;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<TimeTable> excelToTimeTable(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET[2]);
            Iterator<Row> rows = sheet.iterator();

            List<TimeTable> timeTables = new ArrayList<TimeTable>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                TimeTable timeTable = new TimeTable();
                TimeTableCourse timeTableCourse = new TimeTableCourse();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            timeTable.setUserId(currentCell.getStringCellValue());
                            break;
                        case 1:
                            timeTable.setYear((int)currentCell.getNumericCellValue());
                            break;
                        case 2:
                            timeTable.setSemester((int) currentCell.getNumericCellValue());
                            break;
                        case 3:
                            timeTable.setStatus((int) currentCell.getNumericCellValue());
                            break;
                        case 4:
                            timeTable.setDayOfWeek((int) currentCell.getNumericCellValue());
                            break;
                        case 5:
                            timeTableCourse.setClazz(new Class(currentCell.getStringCellValue()));
                            break;
                        case 6:
                            timeTableCourse.setStart((int) currentCell.getNumericCellValue());
                            break;
                        case 7:
                            timeTableCourse.setEnd((int) currentCell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }

                    cellIdx++;
                }
                timeTable.setTimeTableCourses(new ArrayList<>(Arrays.asList(timeTableCourse)));
                timeTables.add(timeTable);
            }

            workbook.close();

            return timeTables;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

//    public static List<TimeTableCourse> excelToTimeTableCourse(InputStream is) {
//        try {
//            Workbook workbook = new XSSFWorkbook(is);
//
//            Sheet sheet = workbook.getSheet(SHEET[2]);
//            Iterator<Row> rows = sheet.iterator();
//
//            List<TimeTableCourse> timeTableCourses = new ArrayList<TimeTableCourse>();
//
//            int rowNumber = 0;
//            while (rows.hasNext()) {
//                Row currentRow = rows.next();
//
//                // skip header
//                if (rowNumber == 0) {
//                    rowNumber++;
//                    continue;
//                }
//
//                Iterator<Cell> cellsInRow = currentRow.iterator();
//
//                TimeTableCourse timeTableCourse = new TimeTableCourse();
//
//                int cellIdx = 0;
//                while (cellsInRow.hasNext()) {
//                    Cell currentCell = cellsInRow.next();
//
//                    switch (cellIdx) {
//                        case 0:
//                            timeTableCourse.setClazz(new Class(currentCell.getStringCellValue()));
//                            break;
//                        case 1:
//                            timeTableCourse.setStart((int) currentCell.getNumericCellValue());
//                            break;
//                        case 2:
//                            timeTableCourse.setEnd((int) currentCell.getNumericCellValue());
//                            break;
//
//                        default:
//                            break;
//                    }
//
//                    cellIdx++;
//                }
//
//                timeTableCourses.add(timeTableCourse);
//            }
//
//            workbook.close();
//
//            return timeTableCourses;
//        } catch (IOException e) {
//            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
//        }
//    }
}
