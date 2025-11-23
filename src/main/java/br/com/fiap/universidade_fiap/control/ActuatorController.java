package br.com.fiap.universidade_fiap.control;


import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.universidade_fiap.service.ActuatorService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ActuatorController {
	
	private final RestTemplate template = new RestTemplate();
	
	@Autowired
	private ActuatorService actuatorService;
	
	@GetMapping("/controle/telemetria2")
	public ModelAndView popularTelemetria2(HttpServletRequest req) {
		
		ModelAndView mv = new ModelAndView("/controle/telemetria2");
		
		mv.addObject("uri", req.getRequestURI());
		mv.addObject("health", actuatorService.getHealth());
		mv.addObject("uso_jvm", actuatorService.getUsoMemoriaJVM());
		mv.addObject("uso_cpu", actuatorService.getUsoCPU());
		mv.addObject("max_jvm", actuatorService.getMaxMemoriaJVM());
		mv.addObject("uptime_minutos", actuatorService.getUptimeMin());
		mv.addObject("uptime_horas", actuatorService.getUptimeHoras());
		
		return mv;
		
	}
	
	@GetMapping("/controle/telemetria")
	public ModelAndView popularTelemetria(HttpServletRequest req) {
		
		ModelAndView mv = new ModelAndView("/controle/telemetria");
		
		Map uso_cpu = 
		template.getForObject
		("http://localhost:8080/actuator/metrics/system.cpu.usage", Map.class);
		Map uso_jvm = 
				template.getForObject
				("http://localhost:8080/actuator/metrics/jvm.memory.used", Map.class);
		Map max_jvm =
				template.getForObject
				("http://localhost:8080/actuator/metrics/jvm.memory.max", Map.class);
		
		List<Map<String,Object>> medidas_cpu = 
				(List<Map<String,Object>>) uso_cpu.get("measurements");
		Double double_uso_cpu = ((Number) medidas_cpu.get(0).get("value")).doubleValue();
		
		
		mv.addObject("uso_cpu", new DecimalFormat("#.##").format(double_uso_cpu * 100));
		
		List<Map<String,Object>> medidas_uso_jvm = 
				(List<Map<String,Object>>) uso_jvm.get("measurements");
		Double double_uso_jvm = ((Number) medidas_uso_jvm.get(0).get("value")).doubleValue();
		
		mv.addObject("uso_jvm", new DecimalFormat("#.##").format(double_uso_jvm / (1024 * 1024)));
		
		List<Map<String,Object>> medidas_max_jvm = 
				(List<Map<String,Object>>) max_jvm.get("measurements");
		Double double_max_jvm = ((Number) medidas_max_jvm.get(0).get("value")).doubleValue();
		
		mv.addObject("max_jvm", new DecimalFormat("#.##").format(double_max_jvm / (1024*1024*1024)));
		mv.addObject("uri", req.getRequestURI());
		
		return mv;
		
	}

}
