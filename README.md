Projeto Biblioteca com Sockets

Descrição

Este projeto foi desenvolvido para representar um servidor utilizando sockets em Java 17, com a funcionalidade de controlar um registro/cadastro de livros de uma biblioteca. As funcionalidades incluem:

Listagem dos livros
Aluguel e devolução de livros
Cadastro de livros
Os livros são armazenados em um arquivo JSON e as operações realizadas pelos usuários são refletidas neste arquivo, que atua como uma "base de dados" da biblioteca.

Funcionalidades

Listagem dos livros: Exibe a lista de livros disponíveis na biblioteca.
Aluguel de livros: Permite o aluguel de livros disponíveis.
Devolução de livros: Permite a devolução de livros alugados.
Cadastro de livros: Adiciona novos livros ao sistema.
Estrutura dos Livros

Os livros são representados por uma classe que contém os seguintes atributos:

Autor: Nome do autor do livro.
Nome: Título do livro.
Gênero: Gênero do livro.
Número de exemplares: Quantidade de exemplares disponíveis na biblioteca.

Tecnologias Utilizadas

Java 17
Sockets para comunicação entre cliente e servidor
Arquivo JSON para armazenamento dos dados dos livros

Pré-requisitos

Java 17 instalado
Maven instalado
