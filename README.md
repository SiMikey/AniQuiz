# 🎌 AnimeQuiz App

**AnimeQuiz** is an Android application that lets users test their anime knowledge through fun and engaging multiple-choice quizzes. It features a variety of questions across different anime series and characters. The app is developed using **Java** in **Android Studio** and uses a **local XAMPP server with MySQL** as the backend for storing quiz data.

---

## 📱 Features

- 🎯 Multiple-choice anime quiz questions
- 🗃 Questions and answers are stored and retrieved from a MySQL database via PHP
- 📈 Score tracking and result display
- 🧠 Randomized question selection
- 🌙 Simple and anime-themed UI/UX

---

## 🛠 Built With

- **Android Studio** (Java)
- **XAMPP (Apache, MySQL, PHP)**
- **XML** for UI design
- **Volley/HttpURLConnection** for API requests (if used)

---

## 🧪 How It Works

1. **The app sends HTTP requests** to PHP scripts hosted on your local XAMPP server.
2. **PHP scripts query the MySQL database** and return quiz questions as JSON.
3. **The Android app parses the JSON** and displays the questions to users.
4. Scores are calculated based on correct answers and shown at the end.

---

## 🚀 Getting Started

### 📦 Prerequisites

- Android Studio (Arctic Fox or later)
- XAMPP installed (Apache & MySQL running)
- Emulator or real Android device
- PHP & MySQL setup for API

### 🔧 Setup Instructions

#### 💻 Backend (XAMPP)

1. Start **Apache** and **MySQL** from XAMPP control panel.
2. Place your PHP API files in `htdocs/animequiz/`.
3. Import the database SQL file (if available) into **phpMyAdmin**.
4. Update IP address or `localhost` in Android code to match your system/server.

#### 📱 Android App

1. Clone this repo or download the source code.

   ```bash
   git clone https://github.com/your-username/AnimeQuizApp.git
