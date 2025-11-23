# 🤖 EcoLesson-IoT - Sistema Educacional com IA Generativa

Sistema web desenvolvido em Java Spring Boot com integração de **Inteligência Artificial Generativa** para gestão educacional, demonstrando aplicação prática de Deep Learning e IA em contexto real.

## 📋 Sobre o Projeto

O EcoLesson-IoT é uma aplicação que integra **IA Generativa (OpenAI GPT)** ao sistema de gestão educacional, permitindo:

- **Assistente de IA integrado** para suporte educacional
- Geração de conteúdo educacional personalizado
- Análise e processamento de dados com IA
- Interface web funcional que consome resultados do modelo de IA
- Integração com outras disciplinas (Desenvolvimento Web)

## 🎯 Objetivo da Disciplina

Este projeto foi desenvolvido para a disciplina **DISRUPTIVE ARCHITECTURES: IOT, IOB & GENERATIVE IA**, demonstrando:

- ✅ Implementação de **API de IA Generativa** (OpenAI GPT via Spring AI)
- ✅ Integração real entre IA e aplicação web
- ✅ Interface funcional que consome resultados do modelo de IA
- ✅ Aplicação prática de **Prompt Engineering**
- ✅ Integração interdisciplinar com Desenvolvimento Web

## 🚀 Tecnologias Utilizadas

### Inteligência Artificial
- **Spring AI 0.8.1** - Framework de IA para Spring Boot
- **OpenAI GPT** - Modelo de linguagem generativa
- **OpenAiChatClient** - Cliente para integração com OpenAI API

### Backend
- **Java 17**
- **Spring Boot 3.5.4**
- **Spring Data JPA** - Persistência de dados
- **Spring Security** - Autenticação e autorização
- **Thymeleaf** - Engine de templates
- **Flyway** - Migrations de banco de dados

### Banco de Dados
- **Oracle Database** - Banco de dados principal

### Mensageria
- **Apache Kafka (Redpanda)** - Sistema de mensageria assíncrona
- **RabbitMQ** - Message broker

### Monitoramento
- **Spring Boot Actuator** - Métricas e monitoramento

### Ferramentas
- **Maven** - Gerenciamento de dependências
- **Docker Compose** - Orquestração de serviços

## 👥 Integrantes

- **Adriano Barutti** - RM: 556760
- **Vitor Kenzo** - RM: 557245

## 🧠 Funcionalidades de IA Generativa

### 1. Assistente de IA para Cursos
- Chat integrado na página de detalhes do curso
- Respostas contextuais baseadas em prompts personalizados
- Suporte educacional em tempo real

### 2. Integração com Fluxo Educacional
- IA integrada ao contexto de cursos
- Geração de respostas personalizadas por curso
- Interface web que consome resultados da API de IA

### 3. Prompt Engineering
- Prompts contextuais para melhorar respostas da IA
- Personalização baseada no contexto do curso
- Otimização de interações com o modelo

## 📦 Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- Java 17 ou superior
- Maven 3.6+
- Oracle Database (ou acesso ao banco Oracle da FIAP)
- Docker e Docker Compose (para serviços de mensageria)
- **Chave de API da OpenAI** (obrigatória para funcionalidades de IA)

## 🔧 Configuração

### 1. Clone o repositório

```bash
git clone https://github.com/AdrianoBarutti/EcoLesson-IoT.git
cd EcoLesson-IoT
```

### 2. Configure o banco de dados

Edite o arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

### 3. Configure a chave da API OpenAI ⚠️ OBRIGATÓRIO

A funcionalidade de IA **requer** uma chave válida da OpenAI:

```properties
spring.ai.openai.api-key=SUA_CHAVE_OPENAI
```

**Como obter:**
1. Acesse [https://platform.openai.com/api-keys](https://platform.openai.com/api-keys)
2. Crie uma conta ou faça login
3. Gere uma nova chave de API
4. Cole a chave no arquivo `application.properties`

### 4. Inicie os serviços de mensageria

```bash
docker-compose up -d
```

Isso iniciará:
- **Redpanda** (Kafka) na porta `9092`
- **RabbitMQ** na porta `5672` (AMQP) e `15672` (Web Console)

## ▶️ Executando a Aplicação

### Opção 1: Maven Wrapper

```bash
./mvnw spring-boot:run
```

No Windows:
```bash
mvnw.cmd spring-boot:run
```

### Opção 2: Maven

```bash
mvn spring-boot:run
```

### Opção 3: Executar o JAR

```bash
mvn clean package
java -jar target/universidade-fiap-0.0.1-SNAPSHOT.jar
```

A aplicação estará disponível em: **http://localhost:8080**

## 🎮 Como Usar a Funcionalidade de IA

### 1. Acesse a aplicação
- Faça login no sistema
- Navegue até um curso

### 2. Use o Assistente de IA
- Na página de detalhes do curso, você encontrará um campo de texto
- Digite sua pergunta sobre o curso
- Clique em "Perguntar IA"
- A resposta gerada pela OpenAI será exibida na interface

### 3. Exemplo de Uso
```
Pergunta: "Quais são os principais tópicos deste curso?"
Resposta: [Gerada pela IA baseada no contexto do curso]
```

## 🔌 Integração de IA

### Endpoint da API de IA

```java
POST /enviar_mensagem_spring_ai_personalizado
```

**Parâmetros:**
- `pergunta` (String) - Pergunta do usuário
- `idCurso` (Long) - ID do curso para contexto

**Resposta:**
- Retorna a resposta gerada pela OpenAI
- Exibida na interface web via Thymeleaf

### Código de Integração

```java
@Controller
public class SpringAIController {
    @Autowired
    private OpenAiChatClient chatClient;
    
    @PostMapping("/enviar_mensagem_spring_ai_personalizado")
    public ModelAndView enviarPerguntaOpenAI(
        @RequestParam("pergunta") String pergunta,
        @RequestParam("idCurso") Long idCurso) {
        // Integração com OpenAI
        String resposta = chatClient.call(pergunta);
        // Retorna para a interface web
        return modelAndView;
    }
}
```

## 📊 Arquitetura da Integração IA

```
┌─────────────────┐
│  Interface Web  │
│   (Thymeleaf)   │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ SpringAIController│
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ OpenAiChatClient│
│  (Spring AI)    │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│   OpenAI API    │
│   (GPT Model)   │
└─────────────────┘
```

## 📝 Requisitos Técnicos Atendidos

### ✅ API de IA Generativa
- [x] Integração com OpenAI GPT via Spring AI
- [x] Geração de texto baseada em prompts
- [x] Implementação de Prompt Engineering
- [x] Modelo pré-treinado (GPT da OpenAI)

### ✅ Integração com Outras Disciplinas
- [x] Interface web funcional (Spring Boot + Thymeleaf)
- [x] Consumo de resultados da IA na interface
- [x] Integração com sistema de gestão educacional
- [x] API REST para comunicação

### ✅ Funcionalidades Implementadas
- [x] Chat de IA integrado ao contexto de cursos
- [x] Respostas personalizadas baseadas em contexto
- [x] Interface web que exibe resultados em tempo real
- [x] Integração com fluxo educacional existente

## 🎯 Critérios de Avaliação

### Cumprimento dos Requisitos Técnicos (até 60 pontos)
- ✅ Implementação técnica da solução em Deep Learning
- ✅ Funcionamento da IA (OpenAI GPT)
- ✅ Integração da API (Spring AI)
- ✅ Documentação do modelo

### Integração entre IA e Outras Disciplinas (até 20 pontos)
- ✅ Integração efetiva com Desenvolvimento Web
- ✅ Interface funcional que consome resultados da IA
- ✅ Coerência na arquitetura geral do sistema

### Boas Práticas de Código (até 10 pontos)
- ✅ Código organizado e documentado
- ✅ README explicativo com instruções
- ✅ Comentários no código

### Apresentação (até 10 pontos)
- ⏳ Vídeo demonstrando funcionalidade da IA
- ⏳ Demonstração da integração interdisciplinar

## 🔐 Segurança

- Autenticação baseada em Spring Security
- Senhas criptografadas
- Controle de acesso baseado em roles
- **IMPORTANTE:** Mantenha sua chave de API da OpenAI segura e não a compartilhe publicamente

## 📄 Estrutura do Projeto

```
EcoLesson-IoT/
├── src/
│   └── main/
│       ├── java/
│       │   └── br/com/fiap/universidade_fiap/
│       │       ├── control/
│       │       │   └── SpringAIController.java  # Controller de IA
│       │       ├── service/
│       │       └── ...
│       └── resources/
│           ├── templates/
│           │   ├── curso/
│           │   │   └── detalhe.html  # Interface com IA
│           │   └── ia/
│           │       └── mensagem_personalizada.html
│           └── application.properties
├── pom.xml  # Dependências incluindo Spring AI
└── README.md
```

## 🧪 Testando a Integração de IA

1. **Inicie a aplicação**
2. **Faça login** no sistema
3. **Acesse um curso** qualquer
4. **Digite uma pergunta** no campo de texto
5. **Clique em "Perguntar IA"**
6. **Verifique a resposta** gerada pela OpenAI

## 📚 Documentação Adicional

- [Spring AI Documentation](https://docs.spring.io/spring-ai/reference/)
- [OpenAI API Documentation](https://platform.openai.com/docs)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)

## 🤝 Contribuindo

Este é um projeto acadêmico desenvolvido para a disciplina **DISRUPTIVE ARCHITECTURES: IOT, IOB & GENERATIVE IA**.

---

**Desenvolvido com ❤️ por Adriano Barutti (RM: 556760) e Vitor Kenzo (RM: 557245)**

**FIAP - Disruptive Architectures: IoT, IOB & Generative IA**
