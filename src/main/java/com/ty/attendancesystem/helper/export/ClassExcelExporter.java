package com.ty.attendancesystem.helper.export;

import com.ty.attendancesystem.model.Class;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ClassExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Class> classes;

    public ClassExcelExporter(List<Class> classes) {
        this.classes = classes;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Classes");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Class Id", style);
        createCell(row, 1, "Course Id", style);
        createCell(row, 2, "Teacher Id", style);
        createCell(row, 3, "Start date", style);
        createCell(row, 4, "End date", style);
        createCell(row, 5, "Number student", style);
        createCell(row, 6, "Status", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Class clazz : classes) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, clazz.getId(), style);
            createCell(row, columnCount++, clazz.getCourse().getId(), style);
            createCell(row, columnCount++, clazz.getTeacher().getId(), style);
            createCell(row, columnCount++, clazz.getStartDate().toString(), style);
            createCell(row, columnCount++, clazz.getEndDate().toString(), style);
            createCell(row, columnCount++, clazz.getNumberStudent(), style);
            createCell(row, columnCount++, clazz.getStatus(), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
