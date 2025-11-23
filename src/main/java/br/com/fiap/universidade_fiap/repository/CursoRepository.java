package br.com.fiap.universidade_fiap.repository;

import br.com.fiap.universidade_fiap.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    // Cursos disponíveis para um aluno (ainda não certificados)
    @Query("SELECT c FROM Curso c WHERE c.idCurso NOT IN " +
           "(SELECT cert.curso.idCurso FROM Certificado cert WHERE cert.usuario.idUsuario = :usuarioId)")
    List<Curso> findCursosDisponiveisParaUsuario(@Param("usuarioId") Long usuarioId);

    // Cursos ministrados por um professor
    @Query("SELECT c FROM Curso c WHERE c.professor.idUsuario = :professorId")
    List<Curso> findCursosMinistradosPorProfessor(@Param("professorId") Long professorId);
}
