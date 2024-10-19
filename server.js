const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const path = require('path');
const app = express();
const PORT = 3000;

app.use(cors());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.use(express.static(path.join(__dirname, "Bot Front script"))); // Замените 'ваша_папка_с_файлами' на папку, где находится ваш index.html

let courses = [];
let userStatistics = {};

app.get('/courses', (req, res) => {
    res.json(courses);
});

app.post('/courses', (req, res) => {
    const newCourse = {
        id: courses.length + 1,
        title: req.body.title,
        description: req.body.description,
        hashtags: req.body.hashtags,
    };
    courses.push(newCourse);
    res.status(201).json(newCourse);
});

app.delete('/courses/:id', (req, res) => {
    const courseId = parseInt(req.params.id);
    courses = courses.filter(course => course.id !== courseId);
    res.status(200).send('Курс удалён');
});

app.get('/statistics/:userId', (req, res) => {
    const userId = req.params.userId;
    if (!userStatistics[userId]) {
        userStatistics[userId] = { createdCourses: 0, completedCourses: 0 };
    }
    res.json(userStatistics[userId]);
});

app.post('/statistics/:userId', (req, res) => {
    const userId = req.params.userId;
    if (!userStatistics[userId]) {
        userStatistics[userId] = { createdCourses: 0, completedCourses: 0 };
    }
    userStatistics[userId] = req.body;
    res.status(200).send('Статистика обновлена');
});

app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});
