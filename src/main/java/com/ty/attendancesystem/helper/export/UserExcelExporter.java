package com.ty.attendancesystem.helper.export;

import com.ty.attendancesystem.model.User;
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

public class UserExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<User> users;

    public UserExcelExporter(List<User> users) {
        this.users = users;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Users");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Id", style);
        createCell(row, 1, "Username", style);
        createCell(row, 2, "Email", style);
        createCell(row, 3, "Phone", style);
        createCell(row, 4, "Address", style);
        createCell(row, 5, "Fullname", style);
        createCell(row, 6, "Birthday", style);
        createCell(row,7,"Role_Id",style);

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

        for (User course : users) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, course.getId(), style);
            createCell(row, columnCount++, course.getUsername(), style);
            createCell(row, columnCount++, course.getEmail(), style);
            createCell(row, columnCount++, course.getPhone(), style);
            createCell(row, columnCount++, course.getAddress(), style);
            createCell(row, columnCount++, course.getFullName(), style);
            createCell(row, columnCount++, course.getBirthday().toString(), style);
            createCell(row,columnCount++,course.getRoles().iterator().next().getId(),style);
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
