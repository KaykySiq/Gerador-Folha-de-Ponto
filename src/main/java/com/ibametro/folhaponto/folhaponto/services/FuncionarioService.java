package com.ibametro.folhaponto.folhaponto.services;

import com.ibametro.folhaponto.folhaponto.dao.FuncionarioDAO;
import com.ibametro.folhaponto.folhaponto.domain.Funcionario;

public class FuncionarioService {

    private FuncionarioDAO dao = new FuncionarioDAO();

    public void cadastrarFuncionario(String nome, String matricula) {
        if (nome.isEmpty() || matricula.isEmpty()) {
            System.out.println("Dados inválidos");
            return;
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setMatricula(matricula);

        dao.novoFuncionario(funcionario);
    }

    public void removerFuncionario(Long id) {
        if (id == null) {
            System.out.println("ID Inválido");
            return;
        }

        dao.excluirFuncionario(id);
    }

    public void atualizarFuncionario(String nome, String matricula) {
        if (nome.isEmpty() || matricula.isEmpty()) {
            System.out.println("Dados inválidos");
            return;
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setMatricula(matricula);

        dao.atualizarFuncionario(funcionario);
    }
}
