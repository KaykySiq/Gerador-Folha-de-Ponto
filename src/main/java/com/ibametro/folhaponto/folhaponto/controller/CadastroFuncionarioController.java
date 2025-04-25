package com.ibametro.folhaponto.folhaponto.controller;

import com.ibametro.folhaponto.folhaponto.service.FuncionarioService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CadastroFuncionarioController {

    @FXML private TextField nomeField;
    @FXML private TextField matriculaField;
    @FXML private TextField idField;

    private FuncionarioService controller = new FuncionarioService();

    @FXML
    private void salvarFuncionario() {
        String nome = nomeField.getText();
        String matricula = matriculaField.getText();

        controller.cadastrarFuncionario(nome, matricula);
    }

    @FXML
    private void removerFuncionario() {
        try {
            Long id = Long.parseLong(idField.getText());

            controller.removerFuncionario(id);
        } catch (NumberFormatException e) {
            System.out.println("Id invalido");
        }
    }

    @FXML
    private void atualizarFuncionario() {
        try {
            String nome = nomeField.getText();
            String matricula = matriculaField.getText();

            controller.atualizarFuncionario(nome, matricula);
        } catch (Exception e) {
            System.out.println("Err: " + e.getMessage());
        }
    }
}
