package br.com.fiap.universidade_fiap.repository;

import br.com.fiap.universidade_fiap.model.Certificado;
import br.com.fiap.universidade_fiap.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CertificadoRepository extends JpaRepository<Certificado, Long> {

    // Busca certificados por usuário (para exibir no painel)
    @Query("SELECT cert FROM Certificado cert WHERE cert.usuario.idUsuario = :usuarioId")
    List<Certificado> findCertificadosPorUsuario(@Param("usuarioId") Long usuarioId);

    // Opcional: consulta booleana para evitar duplicados
    boolean existsByUsuarioAndCurso(Usuario usuario, br.com.fiap.universidade_fiap.model.Curso curso);

}
