# 🧁 Sistema de Padaria — Projeto POO2

Este projeto foi desenvolvido como parte da disciplina **Programação Orientada a Objetos II (POO2)**, com o objetivo de aplicar os conceitos de **Java Orientado a Objetos**, **Padrão MVC**, **JPA/Hibernate** e **interface gráfica com Swing** em um sistema completo de gestão de vendas de uma padaria.

---

## 🧩 Funcionalidades

O sistema permite o gerenciamento de clientes, produtos e vendas, oferecendo uma interface gráfica simples e intuitiva.

- Cadastro, listagem e exclusão de **clientes** e **funcionários**
- Cadastro e controle de **produtos**
- Criação e gerenciamento de **vendas**
- Cálculo automático do **valor total** e **pontuação de compra**
- Persistência dos dados via **JPA (Jakarta Persistence API)**
- Interface gráfica construída com **Java Swing**

---

## 🏗️ Arquitetura

O projeto segue o padrão **MVC (Model–View–Controller)**:

trabalhoPadaria/
├── src/main/java/
│ ├── Controller/ # Camada de controle — lógica de negócio e integração com DAO
│ ├── entidades/ # Classes de modelo (Cliente, Produto, Venda, etc.)
│ ├── views/ # Interface gráfica (Swing)
│ ├── trabalhopadaria/ # DAOs e persistência JPA
│ └── Excecoes/ # Classes de exceção personalizadas
└── resources/
└── META-INF/persistence.xml # Configuração do banco de dados (JPA)


---

## 🧰 Tecnologias Utilizadas

- **Java 17+**
- **Jakarta Persistence (JPA) / Hibernate**
- **Java Swing (Interface Gráfica)**
- **Maven** (gerenciamento de dependências)
- **NetBeans / IntelliJ IDEA / Eclipse** (ambiente de desenvolvimento)
- **Banco de Dados:** H2 / MySQL (configurável via `persistence.xml`)

---

## ⚙️ Como Executar o Projeto

### 1. Pré-requisitos

- Java JDK 17 ou superior instalado  
- Maven configurado (ou use o build automático do NetBeans)
- Uma IDE Java (recomendado: **NetBeans**)

### 2. Clonar o repositório

```bash
git clone https://github.com/MateusRissardi/poo2-padaria.git
3. Abrir o projeto na IDE
Abra o projeto pelo NetBeans (ou outra IDE com suporte a Maven).
Aguarde o carregamento das dependências Maven.

4. Configurar o banco de dados
Verifique o arquivo:

bash
Copiar código
src/main/resources/META-INF/persistence.xml
Ajuste as credenciais conforme o banco desejado (exemplo: MySQL ou H2 em memória).

5. Executar
Basta rodar o método main() da classe principal do projeto (geralmente dentro de trabalhopadaria ou na tela inicial).

🪄 Principais Classes
Pacote	Classe	Descrição
entidades	Produto, Cliente, Venda, Carrinho, Usuario	Modelos de dados persistentes
Controller	UsuarioController, VendaController	Controladores responsáveis pela lógica de negócio
views	TelaRegistrarVendaView, TelaAdicionarProdutoCarrinhoView, etc.	Interface gráfica Swing
trabalhopadaria	VendaDAO, ProdutoDAO, etc.	Classes de acesso ao banco (Data Access Objects)

🧾 Observações Importantes
O projeto foi estruturado de forma modular, permitindo fácil expansão e manutenção.

Todas as entidades utilizam anotações JPA para persistência.

O controller VendaController centraliza a lógica de criação e registro de vendas, comunicando-se diretamente com o VendaDAO.

O sistema exibe mensagens de erro e confirmação ao usuário através de JOptionPane.

🧑‍🎓 Finalidade Acadêmica
Este sistema foi desenvolvido para fins educacionais, com foco na prática dos conceitos de:

Encapsulamento e herança

Persistência de dados com JPA

Uso do padrão MVC

Integração entre camadas de interface, controle e modelo

🧩 Possíveis Extensões Futuras
Relatórios de vendas e faturamento

Controle de estoque em tempo real

Autenticação de usuários e níveis de acesso

Dashboard com gráficos de desempenho

📄 Licença
Este projeto é de uso acadêmico e livre para estudos e modificações, desde que citada a autoria original.