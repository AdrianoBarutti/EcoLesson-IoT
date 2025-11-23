-- V4__popular_banco.sql

-- Funções
insert into funcao(nome) values('ALUNO');
insert into funcao(nome) values('PROFESSOR');

-- Usuários: senhas já hashadas
insert into t_usuarios (nome, email_usuario, senha, cadastro, cpf)
values ('João', 'admin@ecolesson.com', '$2a$12$YcxBeQKXPK.06QNckf.YMeGVm8h.EazFMyURfIDFRHc554uvM3v9K', CURRENT_DATE, '111.222.333-44');

insert into t_usuarios (nome, email_usuario, senha, cadastro, cpf)
values ('Prof. Vitão', 'professor@ecolesson.com', '$2a$12$Tuw/JbBKk0racEOCUMkF7OjR1Vgy4XH3VJZgzKScJgiddUyiXgFBe', CURRENT_DATE, '222.333.444-55');

-- Relacionamentos de usuário e função
insert into usuario_funcao_tab(id_usuario, id_funcao) values(1,1); -- admin = ALUNO
insert into usuario_funcao_tab(id_usuario, id_funcao) values(2,2); -- professor = PROFESSOR

-- Cursos de sustentabilidade (cada insert em linha separada)
insert into t_curso (nome_curso, descricao, qt_horas, id_professor) values
('Gestão de Resíduos Recicláveis', 'Aprenda como implementar práticas eficazes de reciclagem em ambientes urbanos e empresariais.', 20, 2);

insert into t_curso (nome_curso, descricao, qt_horas, id_professor) values
('Educação Ambiental', 'Curso introdutório sobre conscientização, preservação ambiental e biodiversidade.', 25, 2);

insert into t_curso (nome_curso, descricao, qt_horas, id_professor) values
('Energia Solar e Renovável', 'Como instalar e gerir painéis solares e tecnologias renováveis com foco em sustentabilidade.', 30, 2);

insert into t_curso (nome_curso, descricao, qt_horas, id_professor) values
('Tecnologias Verdes e Cidades Sustentáveis', 'Soluções tecnológicas para cidades mais verdes e inteligentes.', 32, 2);

insert into t_curso (nome_curso, descricao, qt_horas, id_professor) values
('Sustentabilidade Corporativa', 'Estratégias ESG para empresas inovadoras e responsáveis.', 28, 2);

insert into t_curso (nome_curso, descricao, qt_horas, id_professor) values
('Compostagem Urbana', 'Metodologias e práticas para destinação correta de resíduos orgânicos em grandes cidades.', 18, 2);

insert into t_curso (nome_curso, descricao, qt_horas, id_professor) values
('Gestão da Água e Saneamento', 'Como conservar e gerenciar recursos hídricos de modo sustentável.', 22, 2);

insert into t_curso (nome_curso, descricao, qt_horas, id_professor) values
('Desenvolvimento Sustentável Local', 'Práticas de planejamento urbano para comunidades que respeitam o meio ambiente.', 24, 2);

-- Certificados
insert into t_certificado (dt_emissao, descricao, codigo_validacao, id_usuario, id_curso)
values (CURRENT_DATE, 'Certificado de conclusão do curso Gestão de Resíduos Recicláveis', 'VAL123456', 1, 1);

insert into t_certificado (dt_emissao, descricao, codigo_validacao, id_usuario, id_curso)
values (CURRENT_DATE, 'Certificado de conclusão do curso Educação Ambiental', 'VAL987654', 2, 2);

-- Atualização de senha (ok deixar)
UPDATE t_usuarios
SET senha = '$2a$12$YcxBeQKXPK.06QNckf.YMeGVm8h.EazFMyURfIDFRHc554uvM3v9K'
WHERE email_usuario = 'professor@ecolesson.com';
