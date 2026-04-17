package org.example.adminbackend.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 엑셀 다운로드 유틸 클래스
 *
 * Apache POI 기반
 */
public final class ExcelUtils {

    private ExcelUtils() {
    }

    /**
     * 엑셀 파일 다운로드
     *
     * @param fileName 파일명
     * @param headers 헤더
     * @param data 데이터
     * @param response HttpServletResponse
     */
    public static void downloadExcel(
            String fileName,
            List<String> headers,
            List<List<Object>> data,
            HttpServletResponse response
    ) {

        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet =
                    workbook.createSheet("Sheet1");

            createHeader(sheet, headers);

            createData(sheet, data);

            setResponseHeader(fileName, response);

            workbook.write(response.getOutputStream());

        } catch (IOException e) {

            throw new RuntimeException("엑셀 다운로드 실패", e);

        }

    }

    private static void createHeader(
            Sheet sheet,
            List<String> headers
    ) {

        Row row = sheet.createRow(0);

        for (int i = 0; i < headers.size(); i++) {

            Cell cell = row.createCell(i);
            cell.setCellValue(headers.get(i));

        }

    }

    private static void createData(
            Sheet sheet,
            List<List<Object>> data
    ) {

        for (int i = 0; i < data.size(); i++) {

            Row row =
                    sheet.createRow(i + 1);

            List<Object> rowData =
                    data.get(i);

            for (int j = 0; j < rowData.size(); j++) {

                Cell cell =
                        row.createCell(j);

                Object value =
                        rowData.get(j);

                cell.setCellValue(
                        value != null
                                ? value.toString()
                                : ""
                );

            }

        }

    }

    private static void setResponseHeader(
            String fileName,
            HttpServletResponse response
    ) throws IOException {

        String encodedFileName =
                URLEncoder.encode(
                        fileName,
                        StandardCharsets.UTF_8
                );

        response.setContentType(
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        );

        response.setHeader(
                "Content-Disposition",
                "attachment; filename=" + encodedFileName + ".xlsx"
        );

    }

}