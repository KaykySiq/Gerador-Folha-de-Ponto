package com.ibametro.folhaponto.folhaponto.controller;

import com.ibametro.folhaponto.folhaponto.domain.Funcionario;
import com.ibametro.folhaponto.folhaponto.services.FuncionarioService;
import com.ibametro.folhaponto.folhaponto.util.JpaUtil;
import jakarta.persistence.EntityManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ListaFuncionariosController {
    @FXML private TableView<Funcionario> tabelaFuncionarios;
    @FXML private TableColumn<Funcionario, String> colunaNome;
    @FXML private TableColumn<Funcionario, String> colunaMatricula;

    private FuncionarioService controller = new FuncionarioService();
    private EntityManager em;

    private FormController formController;

    public void setFormController(FormController formController) {
        this.formController = formController;
    }

    public void initialize() {
        em = JpaUtil.getEntityManager();

        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));

        carregarFuncionarios();
    }

    public void carregarFuncionarios() {
        List<Funcionario> funcionarios = em.createQuery("FROM Funcionario", Funcionario.class).getResultList();
        ObservableList<Funcionario> lista = FXCollections.observableArrayList(funcionarios);
        tabelaFuncionarios.setItems(lista);
    }

    @FXML
    private void removerFuncionario() {
        Funcionario funcionario = tabelaFuncionarios.getSelectionModel().getSelectedItem();

        controller.removerFuncionario(funcionario.getId());
        carregarFuncionarios();
        formController.recarregarFuncionarios();
    }

    public void abrirCadastroMatricula() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/ibametro/folhaponto/folhaponto/view/novo-funcionario.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 350, 250);

            CadastroFuncionarioController controller = fxmlLoader.getController();
            controller.setListaFuncionariosController(this);
            controller.setFormController(this.formController);

            Stage stage = new Stage();
            stage.setTitle("Cadastro de Funcionário");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
