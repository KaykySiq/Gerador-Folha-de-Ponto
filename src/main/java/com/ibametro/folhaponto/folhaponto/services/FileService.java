package com.ibametro.folhaponto.folhaponto.services;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileService {
    private static File selectedFile;
    private static Workbook workbook;

    public static File getSelectedFile() {
        return selectedFile;
    }

    public static Workbook getWorkbook() {
        return workbook;
    }

    public static void selectFile(Stage stage) {

        if (selectedFile == null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Selecionar Folha de Ponto");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivos Excel", "*.xls", "*.xlsx"));

            selectedFile = fileChooser.showOpenDialog(stage);

            if (selectedFile == null) {
                System.out.println("Nenhum arquivo selecionado.");
                return;
            }
        }

        if (workbook == null) {
            try (FileInputStream fis = new FileInputStream(selectedFile)) {
                workbook = new HSSFWorkbook(fis);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Erro ao carregar arquivo: " + e.getMessage());
            }


        }
    }

    public static void openFile() {
        if (selectedFile != null) {
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    if (desktop.isSupported(Desktop.Action.OPEN)) {
                        desktop.open(selectedFile);
                        System.out.println("Sucesso.");
                    } else {
                        System.out.println("Ação não suportada.");
                    }
                } else {
                    System.out.println("Desktop nao suportado.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Erro: " + e.getMessage());
            }
        } else {
            System.out.println("Nenhum arquivo selecionado.");
        }
    }
}
