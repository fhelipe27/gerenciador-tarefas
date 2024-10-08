package br.com.unipac.gerenciadoratividadesapp.services;

import br.com.unipac.gerenciadoratividadesapp.models.Conquista;
import br.com.unipac.gerenciadoratividadesapp.models.Grupo;
import br.com.unipac.gerenciadoratividadesapp.models.GrupoConquista;
import br.com.unipac.gerenciadoratividadesapp.models.Tarefa;
import br.com.unipac.gerenciadoratividadesapp.repositories.ConquistaRepository;
import br.com.unipac.gerenciadoratividadesapp.repositories.GrupoConquistaRepository;
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

    private final GrupoConquistaRepository grupoConquistaRepository;

    private final ConquistaRepository conquistaRepository;

    @Transactional
    public Tarefa salvarTarefa(Tarefa tarefa) {
        try {
            Tarefa novaTarefa = tarefaRepository.save(tarefa);

            // Verifica se é a primeira tarefa do grupo
            Grupo grupo = tarefa.getGrupo();
            List<Tarefa> tarefasDoGrupo = tarefaRepository.findByGrupo(grupo);

            if (tarefasDoGrupo.size() == 1) {
                // Buscar a conquista "Primeiro Passo"
                Conquista primeiraTarefaConquista = conquistaRepository.findByNome("Primeiro Passo").orElse(null);

                if (primeiraTarefaConquista != null) {
                    // Verificar se a conquista já existe para o grupo
                    GrupoConquista grupoConquista = grupoConquistaRepository.findByGrupoAndConquista(grupo, primeiraTarefaConquista);

                    if (grupoConquista == null) {
                        // Criar novo registro se não existir
                        grupoConquista = new GrupoConquista();
                        grupoConquista.setGrupo(grupo);
                        grupoConquista.setConquista(primeiraTarefaConquista);
                    }

                    grupoConquista.setConquistaConcluida(true);
                    grupoConquistaRepository.save(grupoConquista);
                }
            }

            return novaTarefa;
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
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(id);
        if (optionalTarefa.isPresent()) {
            Tarefa tarefa = optionalTarefa.get();
            Grupo grupo = tarefa.getGrupo();

            // Remover a tarefa
            tarefaRepository.delete(tarefa);

            // Verifica se é a primeira tarefa removida permanentemente pelo grupo
            List<Tarefa> tarefasRestantesDoGrupo = tarefaRepository.findByGrupo(grupo);

            if (tarefasRestantesDoGrupo.size() == 0) {
                // Buscar a conquista "Limpeza Definitiva"
                Conquista limpezaDefinitivaConquista = conquistaRepository.findByNome("Limpeza Definitiva").orElse(null);

                if (limpezaDefinitivaConquista != null) {
                    // Verificar se a conquista já existe para o grupo
                    GrupoConquista grupoConquista = grupoConquistaRepository.findByGrupoAndConquista(grupo, limpezaDefinitivaConquista);

                    if (grupoConquista == null) {
                        // Criar novo registro se não existir
                        grupoConquista = new GrupoConquista();
                        grupoConquista.setGrupo(grupo);
                        grupoConquista.setConquista(limpezaDefinitivaConquista);
                    }

                    grupoConquista.setConquistaConcluida(true);
                    grupoConquistaRepository.save(grupoConquista);
                }
            }
        } else {
            throw new RuntimeException("Tarefa não encontrada para o ID: " + id);
        }
    }


    @Transactional
    public Tarefa editarPorId(Long id, Tarefa tarefaAtualizada) {
        try {
            Optional<Tarefa> optionalTarefa = tarefaRepository.findById(id);

            if (optionalTarefa.isPresent()) {
                Tarefa tarefaExistente = optionalTarefa.get();

                // Verifica se é a primeira vez que o grupo está editando uma tarefa
                Grupo grupo = tarefaExistente.getGrupo();
                List<Tarefa> tarefasEditadas = tarefaRepository.findByGrupo(grupo).stream()
                        .filter(t -> !t.getIdTarefa().equals(tarefaExistente.getIdTarefa()) &&
                                (t.getIsConcluida() || t.getIsRemovida()))
                        .toList();

                if (tarefasEditadas.size() == 0) {
                    // Buscar a conquista "Aprimorando"
                    Conquista aprimorandoConquista = conquistaRepository.findByNome("Aprimorando").orElse(null);

                    if (aprimorandoConquista != null) {
                        // Verificar se a conquista já existe para o grupo
                        GrupoConquista grupoConquista = grupoConquistaRepository.findByGrupoAndConquista(grupo, aprimorandoConquista);

                        if (grupoConquista == null) {
                            // Criar novo registro se não existir
                            grupoConquista = new GrupoConquista();
                            grupoConquista.setGrupo(grupo);
                            grupoConquista.setConquista(aprimorandoConquista);
                        }

                        grupoConquista.setConquistaConcluida(true);
                        grupoConquistaRepository.save(grupoConquista);
                    }
                }

                // Atualiza os campos da tarefa existente com os valores da tarefa atualizada
                tarefaExistente.setNome(tarefaAtualizada.getNome());
                tarefaExistente.setResponsavel(tarefaAtualizada.getResponsavel());
                tarefaExistente.setDescricao(tarefaAtualizada.getDescricao());
                tarefaExistente.setDataInicio(tarefaAtualizada.getDataInicio());
                tarefaExistente.setDataFinal(tarefaAtualizada.getDataFinal());
                tarefaExistente.setStatus(tarefaAtualizada.getStatus());

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

            // Verifica se é a primeira tarefa concluída pelo grupo
            Grupo grupo = tarefa.getGrupo();
            List<Tarefa> tarefasConcluidasDoGrupo = tarefaRepository.findByGrupoAndIsConcluida(grupo, true);

            // Lógica para a conquista "Missão Cumprida"
            if (tarefasConcluidasDoGrupo.size() == 1) {
                // Buscar a conquista "Missão Cumprida"
                Conquista missaoCumpridaConquista = conquistaRepository.findByNome("Missão Cumprida").orElse(null);

                if (missaoCumpridaConquista != null) {
                    // Verificar se a conquista já existe para o grupo
                    GrupoConquista grupoConquista = grupoConquistaRepository.findByGrupoAndConquista(grupo, missaoCumpridaConquista);

                    if (grupoConquista == null) {
                        // Criar novo registro se não existir
                        grupoConquista = new GrupoConquista();
                        grupoConquista.setGrupo(grupo);
                        grupoConquista.setConquista(missaoCumpridaConquista);
                    }

                    grupoConquista.setConquistaConcluida(true);
                    grupoConquistaRepository.save(grupoConquista);
                }
            }

            // Lógica para a conquista "Em Alta Performance"
            if (tarefasConcluidasDoGrupo.size() == 10) {
                // Buscar a conquista "Em Alta Performance"
                Conquista altaPerformanceConquista = conquistaRepository.findByNome("Em Alta Performance").orElse(null);

                if (altaPerformanceConquista != null) {
                    // Verificar se a conquista já existe para o grupo
                    GrupoConquista grupoConquista = grupoConquistaRepository.findByGrupoAndConquista(grupo, altaPerformanceConquista);

                    if (grupoConquista == null) {
                        // Criar novo registro se não existir
                        grupoConquista = new GrupoConquista();
                        grupoConquista.setGrupo(grupo);
                        grupoConquista.setConquista(altaPerformanceConquista);
                    }

                    grupoConquista.setConquistaConcluida(true);
                    grupoConquistaRepository.save(grupoConquista);
                }
            }

            if (tarefasConcluidasDoGrupo.size() == 50) {
                // Buscar a conquista "Produtividade Avançada"
                Conquista produtividadeAvancadaConquista = conquistaRepository.findByNome("Produtividade Avançada").orElse(null);

                if (produtividadeAvancadaConquista != null) {
                    // Verificar se a conquista já existe para o grupo
                    GrupoConquista grupoConquista = grupoConquistaRepository.findByGrupoAndConquista(grupo, produtividadeAvancadaConquista);

                    if (grupoConquista == null) {
                        // Criar novo registro se não existir
                        grupoConquista = new GrupoConquista();
                        grupoConquista.setGrupo(grupo);
                        grupoConquista.setConquista(produtividadeAvancadaConquista);
                    }

                    grupoConquista.setConquistaConcluida(true);
                    grupoConquistaRepository.save(grupoConquista);
                }
            }

            if (tarefasConcluidasDoGrupo.size() == 100) {
                // Buscar a conquista "Mestre das Tarefas"
                Conquista mesteDasTarefasConquista = conquistaRepository.findByNome("Mestre das Tarefas").orElse(null);

                if (mesteDasTarefasConquista != null) {
                    // Verificar se a conquista já existe para o grupo
                    GrupoConquista grupoConquista = grupoConquistaRepository.findByGrupoAndConquista(grupo, mesteDasTarefasConquista);

                    if (grupoConquista == null) {
                        // Criar novo registro se não existir
                        grupoConquista = new GrupoConquista();
                        grupoConquista.setGrupo(grupo);
                        grupoConquista.setConquista(mesteDasTarefasConquista);
                    }

                    grupoConquista.setConquistaConcluida(true);
                    grupoConquistaRepository.save(grupoConquista);
                }
            }
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

    @Transactional
    public void marcarRemovida(Long id) {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(id);
        if (optionalTarefa.isPresent()) {
            Tarefa tarefa = optionalTarefa.get();
            tarefa.setIsRemovida(true);
            tarefaRepository.save(tarefa);

            // Verifica se é a primeira tarefa removida pelo grupo
            Grupo grupo = tarefa.getGrupo();
            List<Tarefa> tarefasRemovidasDoGrupo = tarefaRepository.findByGrupoAndIsRemovida(grupo, true);

            if (tarefasRemovidasDoGrupo.size() == 1) {
                // Buscar a conquista "Descarte Inteligente"
                Conquista descarteInteligenteConquista = conquistaRepository.findByNome("Descarte Inteligente").orElse(null);

                if (descarteInteligenteConquista != null) {
                    // Verificar se a conquista já existe para o grupo
                    GrupoConquista grupoConquista = grupoConquistaRepository.findByGrupoAndConquista(grupo, descarteInteligenteConquista);

                    if (grupoConquista == null) {
                        // Criar novo registro se não existir
                        grupoConquista = new GrupoConquista();
                        grupoConquista.setGrupo(grupo);
                        grupoConquista.setConquista(descarteInteligenteConquista);
                    }

                    grupoConquista.setConquistaConcluida(true);
                    grupoConquistaRepository.save(grupoConquista);
                }
            }
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
