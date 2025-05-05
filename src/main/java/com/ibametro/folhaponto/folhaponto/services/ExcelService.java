package com.ibametro.folhaponto.folhaponto.services;


import com.ibametro.folhaponto.folhaponto.util.DateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.YearMonth;
import java.util.List;

public class ExcelService {
    private static final int[] dayCells = {1, 2, 4, 5, 7};

    public static void saveChanges(File selectedFile, Workbook workbook, String nome, String matricula, String mes, int ano) {
        if (selectedFile != null && workbook != null) {
            try (FileOutputStream fos = new FileOutputStream(selectedFile)) {
                Sheet sheet = workbook.getSheetAt(0);

                // Localiza a linha 8 (índice 7, pois as linhas começam em 0)
                Row header = sheet.getRow(7); // Linha 8 no Excel = índice 7 no Apache POI

                // Atualiza o nome na célula 0 da linha 8
                Cell nomeCell = header.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                nomeCell.setCellValue(nome);

                // Atualiza a matrícula na célula 9 da linha 8
                Cell matriculaCell = header.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                matriculaCell.setCellValue(matricula);

                // Localiza a linha 11 (índice 10) para atualizar a carga horária
                Row workloadRow = sheet.getRow(10);

                if ("ESTAGIARIO".equals(matricula)) {
                    Cell workloadCell = workloadRow.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    workloadCell.setCellValue("30H");
                } else {
                    Cell workloadCell = workloadRow.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    workloadCell.setCellValue("40H");
                }
                Row monthRow = sheet.getRow(10);
                Cell monthCell = monthRow.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                monthCell.setCellValue(mes);

                cleanContent(sheet);
                updateDatesInSheet(sheet, mes, ano);

                workbook.write(fos);
                System.out.println("Arquivo salvo com sucesso!");
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Erro ao salvar o arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Nenhum arquivo selecionado ou Workbook não carregado.");
        }
    }

    public static void cleanContent(Sheet sheet) {
        int startRow = 15;

        for (int i = 0; i < 31; i++) {
            Row row = sheet.getRow(startRow + i);
            for (int col : dayCells) {
                Cell cell = row.getCell(col, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellValue("");
            }
        }
    }

    private static void updateDatesInSheet(Sheet sheet, String mes, int ano) {
        int month = DateUtil.getMonth(mes);
        YearMonth yearMonth = YearMonth.of(ano, month);

        int daysInMonth = yearMonth.lengthOfMonth();

        List<Integer> holidays = HolidayService.getHolidays(ano, mes);
    }
}
