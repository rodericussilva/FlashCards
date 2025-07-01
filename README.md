# 📚 Flashcards App

Um aplicativo educativo de flashcards para Android que ajuda usuários a aprender vocabulário de forma visual e interativa.  
O app permite navegar por imagens com seus respectivos nomes, alternar entre texto e imagem, marcar favoritos com o objetivo de retornar rapidamente e treinar a memorização do vocabulário favoritado.

## ✨ Funcionalidades

- Mostrar flashcards com imagens e palavras em diferentes categorias:
  - Animais
  - Roupas
  - Profissões
  - Frutas
  - Cores
- Alternar entre **imagem** e **texto** tocando no flashcard
- Marcar/desmarcar flashcards como favoritos
- Navegar por uma lista separada de flashcards favoritos por idioma
- Suporte a múltiplos idiomas (internacionalização)
- Interface intuitiva e amigável

## 📸 Capturas de tela

> ...

## 🛠️ Tecnologias utilizadas

- **Java**
- **Android SDK**
- **SharedPreferences** (para armazenar favoritos)
- **Resources & Locale** (para suporte a múltiplos idiomas)

## 🗂️ Estrutura do Projeto
```
app/
├── java/com/example/flashcards/
│ ├── MainActivity.java
│ ├── FavoritesActivity.java
│ ├── CategoriesActivity.java
│ ├── FlashcardActivity.java
│ ├── Flashcard.java
│ ├── LocaleHelper.java
├── res/
│ ├── layout/
│ │ ├── activity_flashcards.xml
│ │ ├── activity_favorites.xml
│ │ ├── activity_flashcards.xml
│ │ └── activity_main.xml
│ ├── drawable/
│ │ └── (imagens dos flashcards)
│ ├── values/
│ │ └── strings.xml (padrão)
│ ├── values-es/
│ │ └── strings.xml
│ ├── values-fr/
│ │ └── strings.xml
│ ├── values-pt/
│ │ └── strings.xml
└── AndroidManifest.xml
```
## 🌍 Suporte a idiomas
O app suporta vários idiomas. O idioma atual é detectado automaticamente pelo LocaleHelper.
Para adicionar ou modificar traduções, edite os arquivos:
```
res/values/strings.xml (padrão - inglês)
res/values-es/strings.xml
res/values-fr/strings.xml
res/values-pt/strings.xml
```
## 🙋‍♂️ Autor
Desenvolvido por Rodrigo Silva
📧 Contato: rodericus@aluno.ufc.br
