package in.gilsondev.todolist.web.rest;

import in.gilsondev.todolist.domain.Project;
import in.gilsondev.todolist.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller de Projetos com os
 * endpoints de acesso a funcionalidade.
 *
 * @author Gilson Filho
 */
@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectResource {

    public static final String ID_PROJECT = "{id}";

    private static final String ID_PROJECT_VARIABLE = "id";

    final ProjectService projectService;

    /**
     * Endpoint para listar todos os projetos cadastrados
     *
     * @return Objeto {@link ResponseEntity<List<Project>>} com a lista de projetos
     */
    @GetMapping
    public ResponseEntity<List<Project>> getProjects() {
        return ResponseEntity.ok(projectService.findAll());
    }


    /**
     * Endpoint para retornar o projeto consultado pelo ID
     *
     * @return Objeto {@link ResponseEntity<Project>} com o projeto encontrado
     */
    @GetMapping(path = "/" + ID_PROJECT)
    public ResponseEntity<Project> getProject(@PathVariable(ID_PROJECT_VARIABLE) Long id) {
        Optional<Project> project = projectService.findById(id);

        if(project.isPresent()) {
            return ResponseEntity.ok(project.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint para criar um novo projeto
     *
     * @return Objeto {@link ResponseEntity<Project>} com o projeto cadastrado
     */
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.save(project));
    }

    /**
     * Endpoint para atualizar o projeto selecionado
     *
     * @return Objeto {@link ResponseEntity} com status 200 de atualizado
     */
    @PutMapping
    public ResponseEntity updateProject(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.save(project));
    }

    /**
     * Endpoint para remover o projeto selecionado
     *
     * @return Objeto {@link ResponseEntity} com status 204 de nenhum conteudo existente
     * para esse tipo de recurso.
     */
    @DeleteMapping(path = "/" + ID_PROJECT)
    public ResponseEntity removeProject(@PathVariable(ID_PROJECT_VARIABLE) Long id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
