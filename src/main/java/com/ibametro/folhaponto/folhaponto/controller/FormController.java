package com.ibametro.folhaponto.folhaponto.controller;

import com.ibametro.folhaponto.folhaponto.model.enums.Mes;
import com.ibametro.folhaponto.folhaponto.util.JpaUtil;
import jakarta.persistence.EntityManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FormController implements Initializable {

    @FXML private ComboBox<String> nomeField;
    @FXML private TextField matriculaField;
    @FXML private ComboBox<String> mesComboBox;
    @FXML private TextField anoField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        funcionarios();
        carregarMeses();

        nomeField.setOnAction(event -> preencherMatricula());
    }

    public void funcionarios() {

        if (!nomeField.getItems().isEmpty()) return;

        EntityManager em = JpaUtil.getEntityManager();
        try {
            List<String> nomes = em.createQuery("SELECT f.nome FROM Funcionario f", String.class)
                    .getResultList();
            nomeField.getItems().addAll(nomes);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void recarregarFuncionarios() {
        nomeField.getItems().clear();
        funcionarios();
    }

    public void preencherMatricula() {

        String nomeSelecionado = nomeField.getValue();
        if (nomeSelecionado == null) return;

        EntityManager em = JpaUtil.getEntityManager();
        try {
            String matricula = em.createQuery("SELECT f.matricula FROM Funcionario f WHERE f.nome = :nome", String.class)
                    .setParameter("nome", nomeSelecionado)
                    .getSingleResult();

            matriculaField.setText(matricula);
        } catch (Exception e) {
            e.printStackTrace();
            matriculaField.setText("");
        } finally {
            em.close();
        }
    }

    public Mes meses() {
        String mesSelecionado = mesComboBox.getValue();
        if (mesSelecionado == null) return null;

        for (Mes mes : Mes.values()) {
            if (mes.getMesRef().equals(mesSelecionado)){
                return mes;
            }
        }
        return null;
    }

    public void carregarMeses() {

        for (Mes meses : Mes.values()) {
            mesComboBox.getItems().add(meses.getMesRef());
        }

    }

    public void abrirListaFuncionarios() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/ibametro/folhaponto/folhaponto/view/lista-funcionarios.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 650, 550);

            ListaFuncionariosController controller = fxmlLoader.getController();
            controller.setFormController(this);

            Stage stage = new Stage();
            stage.setTitle("Lista de Funcionarios");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void abrirCadastroFuncionario() {
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/ibametro/folhaponto/folhaponto/view/lista-funcionarios.fxml"));
//            Scene scene = new Scene(fxmlLoader.load(), 650, 550);
//
//            CadastroFuncionarioController controller = fxmlLoader.getController();
//            controller.setFormController(this);
//
//            Stage stage = new Stage();
//            stage.setTitle("Cadastro de Funcionário");
//            stage.setScene(scene);
//            stage.show();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
