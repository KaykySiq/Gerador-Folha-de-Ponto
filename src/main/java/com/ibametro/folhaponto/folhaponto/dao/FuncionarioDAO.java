package com.ibametro.folhaponto.folhaponto.dao;

import com.ibametro.folhaponto.folhaponto.domain.Funcionario;
import com.ibametro.folhaponto.folhaponto.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class FuncionarioDAO {

    public void novoFuncionario(Funcionario funcionario) {
        EntityManager em = JpaUtil.getEntityManager();  // Inicialize o EntityManager aqui
        EntityTransaction et = em.getTransaction();

        try {
            et.begin();
            em.persist(funcionario);
            et.commit();
            System.out.println("Funcionário cadastrado com sucesso!");
        } catch (Exception e) {
            if (et.isActive()) et.rollback();
            System.out.println("Erro: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void excluirFuncionario(Long id) {
        EntityManager em = JpaUtil.getEntityManager();  // Inicialize o EntityManager aqui
        EntityTransaction et = em.getTransaction();

        try {
            et.begin();

            Funcionario funcionario = em.find(Funcionario.class, id);

            if (funcionario != null) {
                em.remove(funcionario);
                et.commit();
                System.out.println("Funcionário removido com sucesso!");
            } else {
                System.out.println("Funcionário não encontrado.");
            }
        } catch (Exception e) {
            if (et.isActive()) et.rollback();
            System.out.println("Erro: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void atualizarFuncionario(Funcionario funcionario) {
        EntityManager em = JpaUtil.getEntityManager();  // Inicialize o EntityManager aqui
        EntityTransaction et = em.getTransaction();

        try {
            et.begin();

            Funcionario funcionarioExistente = em.find(Funcionario.class, funcionario.getId());

            if (funcionarioExistente != null) {
                funcionarioExistente.setNome(funcionario.getNome());
                funcionarioExistente.setMatricula(funcionario.getMatricula());

                em.merge(funcionarioExistente);
                et.commit();
                System.out.println("Dados atualizados!");
            } else {
                System.out.println("Funcionário não encontrado.");
            }
        } catch (Exception e) {
            if (et.isActive()) et.rollback();
            System.out.println("Erro: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
