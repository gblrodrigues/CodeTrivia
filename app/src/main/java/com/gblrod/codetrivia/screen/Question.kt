package com.gblrod.codetrivia.screen

data class Question(
    val text: String,
    val options: List<String>,
    val indexCorrect: Int
)

val questions = listOf(
    Question(
        text = "Quem criou o primeiro programa de computador da história?",
        options = listOf("Alan Turing", "Ada Lovelace", "Linus Torvalds", "Albert Einstein"),
        indexCorrect = 1
    ),

    Question(
        text = "Qual linguagem de programação é executada diretamente nos navegadores?",
        options = listOf("Kotlin", "Java", "Python", "JavaScript"),
        indexCorrect = 3
    ),

    Question(
        text = "O que significa a sigla HTML?",
        options = listOf(
            "Hyper Trainer Marking Language",
            "Hyper Text Markup Language",
            "High Text Machine Language",
            "Home Tool Markup Language"
        ),
        indexCorrect = 1
    ),

    Question(
        text = "Qual estrutura de controle repete um bloco de código enquanto uma condição permanece verdadeira?",
        options = listOf("if","for", "while", "switch"),
        indexCorrect = 2
    ),

    Question(
        text = "Qual é a função principal do Git?",
        options = listOf(
            "Criar banco de dados",
            "Gerenciar versões de código",
            "Compilar programas",
            "Executar servidores"
        ),
        indexCorrect = 1
    ),

    Question(
        text = "Qual a linguagem oficial do Android?",
        options = listOf("Kotlin","Lua", "C#", "JavaScript"),
        indexCorrect = 0
    )
).shuffled()