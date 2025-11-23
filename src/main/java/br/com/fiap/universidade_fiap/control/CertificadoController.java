package br.com.fiap.universidade_fiap.control;

import br.com.fiap.universidade_fiap.model.Certificado;
import br.com.fiap.universidade_fiap.repository.CertificadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CertificadoController {

    @Autowired
    private CertificadoRepository certificadoRepository;

    @GetMapping("/certificado/{id}")
    public String visualizarCertificado(@PathVariable("id") Long id, Model model) {
        Certificado certificado = certificadoRepository.findById(id).orElseThrow();
        model.addAttribute("certificado", certificado);
        return "certificado/detalhes";
    }
}
