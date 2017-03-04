package in.gilsondev.todolist.service;

import in.gilsondev.todolist.domain.Project;
import in.gilsondev.todolist.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service da funcionalidade de Projetos
 *
 * @author Gilson Filho
 */
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    /**
     * Lista todos os projetos no sistema
     *
     * @return uma lista de {@link Project}
     */
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    /**
     * Salve o projeto na base de dados
     *
     * @param project Dados do projeto a ser cadastrado
     * @return projeto recém inserido
     */
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    /**
     * Pesquisa o projeto pelo ID
     *
     * @param id Identificador do Projeto
     * @return Um objeto {@link Optional<Project>} com o resultado da consulta
     */
    public Optional<Project> findById(Long id) {
        return Optional.of(projectRepository.findOne(id));
    }

    /**
     * Remove o projeto a partir do seu ID
     *
     * @param id Identificador do Projeto
     */
    public void delete(Long id) {
        projectRepository.delete(id);
    }
}
