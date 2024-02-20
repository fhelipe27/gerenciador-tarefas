package br.com.unipac.gerenciadoratividadesapp.services;

import br.com.unipac.gerenciadoratividadesapp.models.Tarefa;
import br.com.unipac.gerenciadoratividadesapp.repositories.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    // função para salvar tarefa
    @Transactional
    public Tarefa salvarTarefa(Tarefa tarefa) {
        try {
            return tarefaRepository.save(tarefa);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Erro ao salvar a tarefa.", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar a tarefa", e);
        }
    }

    // função para achar por id
    @Transactional(readOnly = true)
    public Optional<Tarefa> buscarPorId(Long id) {
        try {
            return tarefaRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar a tarefa por ID", e);
        }
    }

    // função para achar todos
    @Transactional(readOnly = true)
    public List<Tarefa> buscarTodos() {
        try {
            return tarefaRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar todas as tarefas", e);
        }
    }

    // função para deletar por id
    @Transactional
    public void deletarPorId(Long id) {
        try {
            tarefaRepository.deleteById(id);
        } catch (Exception e) {
            String mensagemErro = "Erro ao deletar a tarefa por ID";
            throw new RuntimeException(mensagemErro, e);
        }
    }


    // função para editar por id
    @Transactional
    public Tarefa editarPorId(Long id, Tarefa tarefaAtualizada) {
        try {
            Optional<Tarefa> optionalTarefa = tarefaRepository.findById(id);

            if (optionalTarefa.isPresent()) {
                Tarefa tarefaExistente = optionalTarefa.get();

                // atualiza os campos do produto existente com os valores do produto atualizado
                tarefaExistente.setNome(tarefaAtualizada.getNome());
                tarefaExistente.setDescricao(tarefaAtualizada.getDescricao());
                tarefaExistente.setDataInicio(tarefaAtualizada.getDataInicio());
                tarefaExistente.setDataFinal(tarefaAtualizada.getDataFinal());

                return tarefaRepository.save(tarefaExistente);
            } else {
                throw new RuntimeException("Tarefa não encontrada para o ID: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao editar a tarefa por ID", e);
        }
    }
}