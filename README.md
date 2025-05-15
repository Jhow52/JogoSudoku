# 🧩 Sudoku com Modo Rascunho — Java Swing

Este é um jogo de Sudoku feito em **Java**, utilizando **Swing** para interface gráfica e lógica de jogo orientada a objetos. O projeto possui **modo rascunho**, que permite inserir números pequenos como sugestões nas células — perfeito para aprender e praticar **POO**, **eventos**, e **Java 2D Graphics**.

---

## 📌 Funcionalidades

- ✅ Tabuleiro 9x9 com setores 3x3
- ✅ Preenchimento de números pelo usuário
- ✅ Modo **rascunho**: insere números pequenos como sugestões
- ✅ Verificação de erros no tabuleiro
- ✅ Finalização do jogo com alerta de sucesso ou erro
- ✅ Reset do jogo com confirmação
- ✅ Interface Swing com botões intuitivos

---

## 📂 Estrutura de Classes

| Classe                  | Descrição |
|------------------------|-----------|
| `App`                  | Inicia o jogo e chama `MainScreen` |
| `MainScreen`           | Monta a interface principal e os botões |
| `BoardService`         | Contém regras do jogo e controle dos espaços |
| `Space`                | Representa cada célula do tabuleiro |
| `NumberText`           | Campo de entrada para números normais |
| `DraftableNumberText`  | Extensão de `NumberText` com desenho de rascunhos |
| `SudokuSector`         | Painel que representa um bloco 3x3 |
| `NotifierService`      | Serviço que publica eventos para observadores |
| `EventEnum`            | Enum que representa os tipos de evento (ex: CLEAR_SPACE) |

---

## 🧠 POO Aplicado

✔️ **Encapsulamento**  
Atributos privados com acesso via getters/setters protegidos.

✔️ **Herança**  
`DraftableNumberText` herda de `NumberText`.

✔️ **Polimorfismo**  
Sobrescrita do método `paintComponent()` para desenhar os rascunhos.

✔️ **Abstração**  
`BoardService` isola toda a lógica do jogo da interface.

✔️ **Eventos (Observer Pattern)**  
Campos do tabuleiro se inscrevem para receber atualizações globais como "reset".

## 🛠 Tecnologias Utilizadas

- **Java 17+**
- **Swing** (GUI)
- **Java 2D Graphics**
- **POO** (Orientação a Objetos)
- **MVC simplificado**
- **Enum + Events**

---