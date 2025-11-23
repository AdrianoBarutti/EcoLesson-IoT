package br.com.fiap.universidade_fiap.service;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.HealthComponent;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.MeterRegistry;

@Service
public class ActuatorService {

	@Autowired
	private MeterRegistry registry;

	@Autowired
	private HealthEndpoint healthEndpoint;

	public HealthComponent getHealth() {
		return healthEndpoint.health();
	}

	public String getUsoCPU() {
		double cpuUsage = registry.get("system.cpu.usage").gauge().value();
		return new DecimalFormat("#.##").format(cpuUsage * 100);
	}

	public String getUsoMemoriaJVM() {
		double jvmMemUsed = registry.get("jvm.memory.used").gauge().value();
		return new DecimalFormat("#.##").format(jvmMemUsed / (1024 * 1024));
	}

	public String getMaxMemoriaJVM() {
		double jvmMemMax = registry.get("jvm.memory.max").gauge().value();
		return new DecimalFormat("#.##").format(jvmMemMax / (1024 * 1024 * 1024));
	}

	public String getUptimeMin() {
		double uptimeMin = registry.get("process.uptime").gauge().value();
		return new DecimalFormat("#.##").format(uptimeMin / 60);
	}

	public String getUptimeHoras() {
		double uptimeHoras = registry.get("process.uptime").gauge().value();
		return new DecimalFormat("#.##").format(uptimeHoras / 3600);
	}

}
