package br.com.unipac.gerenciadoratividadesapp.services;

import br.com.unipac.gerenciadoratividadesapp.models.Grupo;
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

    @Transactional(readOnly = true)
    public Optional<Tarefa> buscarPorId(Long id) {
        return tarefaRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Tarefa> buscarTodos() {
        return tarefaRepository.findAll();
    }

    @Transactional
    public void deletarPorId(Long id) {
        tarefaRepository.deleteById(id);
    }

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

    @Transactional(readOnly = true)
    public List<Tarefa> buscarPorGrupo(Grupo grupo) {
        return tarefaRepository.findByGrupo(grupo);
    }

    @Transactional
    public void marcarConcluida(Long id) {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(id);
        if (optionalTarefa.isPresent()) {
            Tarefa tarefa = optionalTarefa.get();
            tarefa.setIsConcluida(true);
            tarefa.setIsRemovida(false);
            tarefaRepository.save(tarefa);
        } else {
            throw new RuntimeException("Tarefa não encontrada para o ID: " + id);
        }
    }

    @Transactional
    public void desmarcarConcluida(Long id) {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(id);
        if (optionalTarefa.isPresent()) {
            Tarefa tarefa = optionalTarefa.get();
            tarefa.setIsConcluida(false);
            tarefa.setIsRemovida(false);
            tarefaRepository.save(tarefa);
        } else {
            throw new RuntimeException("Tarefa não encontrada para o ID: " + id);
        }
    }

    public void marcarRemovida(Long id) {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(id);
        if (optionalTarefa.isPresent()) {
            Tarefa tarefa = optionalTarefa.get();
            tarefa.setIsRemovida(true);
            tarefaRepository.save(tarefa);
        } else {
            throw new RuntimeException("Tarefa não encontrada para o ID: " + id);
        }
    }

    public void desmarcarRemovida(Long id) {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(id);
        if (optionalTarefa.isPresent()) {
            Tarefa tarefa = optionalTarefa.get();
            tarefa.setIsRemovida(false);
            tarefaRepository.save(tarefa);
        } else {
            throw new RuntimeException("Tarefa não encontrada para o ID: " + id);
        }
    }

    @Transactional(readOnly = true)
    public List<Tarefa> buscarPorGrupoRemovidas(Grupo grupo) {
        return tarefaRepository.findByGrupoAndIsRemovida(grupo, true);
    }

    @Transactional(readOnly = true)
    public List<Tarefa> buscarPorGrupoConcluidas(Grupo grupo) {
        return tarefaRepository.findByGrupoAndIsConcluida(grupo, true);
    }



}
