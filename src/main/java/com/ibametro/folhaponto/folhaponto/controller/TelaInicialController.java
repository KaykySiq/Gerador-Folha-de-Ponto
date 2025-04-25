package com.ibametro.folhaponto.folhaponto.controller;

import com.ibametro.folhaponto.folhaponto.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.application.Application;
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

public class TelaInicialController implements Initializable {

    @FXML
    private ComboBox<String> nomeField;

    @FXML
    private TextField matriculaField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomeField.setOnShowing(event -> carregarFuncionarios());

        nomeField.setOnAction(event -> preencherMatricula());
    }

    public void carregarFuncionarios() {

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
}
