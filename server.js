const express = require('express'); // Импортируем Express для создания сервера
const bodyParser = require('body-parser'); // Для парсинга данных в запросах
const cors = require('cors'); // Для обеспечения кросс-доменных запросов
const app = express(); // Создаем экземпляр приложения Express
const PORT = 3000; // Порт для сервера

app.use(cors()); // Включаем CORS
app.use(bodyParser.json()); // Для парсинга JSON
app.use(bodyParser.urlencoded({ extended: true })); // Для парсинга urlencoded данных

let courses = []; // Массив для хранения курсов
let userStatistics = {}; // Объект для хранения статистики пользователей

// Эндпоинт для получения всех курсов
app.get('/courses', (req, res) => {
    res.json(courses); // Отправляем массив курсов в ответе
});

// Эндпоинт для создания нового курса
app.post('/courses', (req, res) => {
    const newCourse = req.body; // Получаем новый курс из тела запроса
    courses.push(newCourse); // Добавляем курс в массив
    res.status(201).json(newCourse); // Отправляем созданный курс в ответе
});

// Эндпоинт для получения статистики пользователя
app.get('/statistics/:userId', (req, res) => {
    const userId = req.params.userId; // Получаем ID пользователя из параметров
    res.json(userStatistics[userId] || { createdCourses: 0, completedCourses: 0 }); // Возвращаем статистику или 0
});

// Эндпоинт для обновления статистики пользователя
app.post('/statistics/:userId', (req, res) => {
    const userId = req.params.userId; // Получаем ID пользователя из параметров
    const stats = req.body; // Получаем статистику из тела запроса
    userStatistics[userId] = { // Сохраняем статистику для пользователя
        createdCourses: stats.createdCourses || 0,
        completedCourses: stats.completedCourses || 0,
    };
    res.status(200).json(userStatistics[userId]); // Отправляем обновленную статистику в ответе
});

// Запускаем сервер
app.listen(PORT, () => {
    console.log(`Сервер запущен на http://localhost:${PORT}`); // Логируем информацию о запуске сервера
});
