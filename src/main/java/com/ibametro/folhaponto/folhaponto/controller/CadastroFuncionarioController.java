package com.ibametro.folhaponto.folhaponto.controller;

import com.ibametro.folhaponto.folhaponto.services.FuncionarioService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CadastroFuncionarioController {

    @FXML private TextField nomeField;
    @FXML private TextField matriculaField;

    private FuncionarioService controller = new FuncionarioService();

    private FormController formController;
    private ListaFuncionariosController listaFuncionariosController;

    public void setListaFuncionariosController(ListaFuncionariosController listaFuncionariosController) {
        this.listaFuncionariosController = listaFuncionariosController;
    }

    public void setFormController(FormController formController) {
        this.formController = formController;
    }

    @FXML
    private void salvarFuncionario() {
        String nome = nomeField.getText();
        String matricula = matriculaField.getText();

        controller.cadastrarFuncionario(nome, matricula);
        listaFuncionariosController.carregarFuncionarios();
        formController.recarregarFuncionarios();
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
