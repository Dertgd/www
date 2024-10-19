const welcomeContainer = document.getElementById('welcomeContainer');
const courseFormContainer = document.getElementById('courseFormContainer');
const exploreContainer = document.getElementById('exploreContainer');
const statisticsContainer = document.getElementById('statisticsContainer');
const courseInfoContainer = document.getElementById('courseInfoContainer');
const courseList = document.getElementById('courseList');
const createdCoursesCounter = document.getElementById('createdCourses');
const completedCoursesCounter = document.getElementById('completedCourses');

let createdCoursesCount = 0;
let completedCoursesCount = 0;

const urlParams = new URLSearchParams(window.location.search);
let userId = urlParams.get('user_id') || 'Неизвестный ID';
let username = urlParams.get('username') || 'Неизвестный пользователь';

function loadStatistics() {
    fetch(`/statistics/${userId}`) // Запрос на получение статистики с сервера
        .then(response => response.json())
        .then(data => {
            createdCoursesCount = data.createdCourses || 0;
            completedCoursesCount = data.completedCourses || 0;
            updateStatistics();
        });
}

function saveStatistics() {
    const statistics = {
        createdCourses: createdCoursesCount,
        completedCourses: completedCoursesCount,
    };
    fetch(`/statistics/${userId}`, { // Отправляем статистику на сервер
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(statistics),
    });
}

function loadCourses() {
    courseList.innerHTML = '';

    fetch('/courses') // Запрос на получение курсов с сервера
        .then(response => response.json())
        .then(courses => {
            courses.forEach(course => {
                const courseItem = document.createElement('div');
                courseItem.className = 'course-item';
                courseItem.innerHTML = `
                    <h3>${course.title}</h3>
                    <p>Описание: ${course.description}</p>
                    <p>Хэштеги: ${course.hashtags}</p>
                    <button class="btn" onclick="showCourseDetails(${course.id})">Подробнее</button>
                `;
                courseList.appendChild(courseItem);
            });

            if (courses.length === 0) {
                const listMessage = document.createElement('div');
                listMessage.innerText = 'Список пока пуст.';
                courseList.appendChild(listMessage);
            }
        });
}

function createCourse() {
    const courseName = document.getElementById('courseName').value;
    const courseDescription = document.getElementById('courseDescription').value;
    const hashtags = document.getElementById('hashtags').value;
    const selectedTopic = document.getElementById('selectedTopic').innerText;

    const newCourse = {
        title: courseName,
        description: courseDescription,
        hashtags: hashtags,
        topic: selectedTopic,
    };

    fetch('/courses', { // Отправляем новый курс на сервер
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(newCourse),
    })
        .then(response => response.json())
        .then(course => {
            createdCoursesCount++;
            loadCourses(); // Перезагружаем курсы после добавления
            saveStatistics(); 
            updateStatistics();
        });

    courseFormContainer.style.display = 'none';
    welcomeContainer.style.display = 'block';
}

function showCourseDetails(courseId) {
    const courseDetails = {
        1: { title: 'Курс по Программированию для Начинающих', description: 'Этот курс познакомит вас с основами программирования на языке Python.', links: 'Ссылка на видео' },
    };

    const course = courseDetails[courseId];
    if (course) {
        document.getElementById('courseInfoTitle').innerText = course.title;
        document.getElementById('courseInfoDescription').innerText = course.description;
        document.getElementById('courseInfoLinks').innerText = course.links;

        exploreContainer.style.display = 'none';
        courseInfoContainer.style.display = 'block';
    } else {
        alert('Курс не найден');
    }
}

document.getElementById('markAsDone').addEventListener('click', () => {
    completedCoursesCount++;
    saveStatistics();
    courseInfoContainer.style.display = 'none';
    statisticsContainer.style.display = 'block';
    completedCoursesCounter.innerText = completedCoursesCount;
});

document.getElementById('viewStatistics').addEventListener('click', () => {
    updateStatistics(); 
    welcomeContainer.style.display = 'none';
    statisticsContainer.style.display = 'block';
});

document.getElementById('exploreCourses').addEventListener('click', () => {
    welcomeContainer.style.display = 'none';
    exploreContainer.style.display = 'block';
    loadCourses();
});

document.getElementById('createCourse').addEventListener('click', () => {
    welcomeContainer.style.display = 'none';
    courseFormContainer.style.display = 'block';
});

document.getElementById('cancel').addEventListener('click', () => {
    courseFormContainer.style.display = 'none';
    welcomeContainer.style.display = 'block';
});

document.getElementById('closeExplore').addEventListener('click', () => {
    exploreContainer.style.display = 'none';
    welcomeContainer.style.display = 'block';
});

document.getElementById('closeStatistics').addEventListener('click', () => {
    statisticsContainer.style.display = 'none';
    welcomeContainer.style.display = 'block';
});

document.getElementById('cancelCourseInfo').addEventListener('click', () => {
    courseInfoContainer.style.display = 'none';
    welcomeContainer.style.display = 'block';
});

document.getElementById('chooseTopic').addEventListener('click', () => {
    document.getElementById('topicList').style.display = 'block';
});

document.querySelectorAll('#topicList li').forEach(item => {
    item.addEventListener('click', () => {
        const selectedTopic = item.getAttribute('data-topic');
        document.getElementById('selectedTopic').innerText = selectedTopic;
        document.getElementById('topicList').style.display = 'none';
    });
});

document.getElementById('courseForm').addEventListener('submit', (event) => {
    event.preventDefault();
    createCourse();
});

window.onload = () => {
    loadStatistics(); 
    updateStatistics();
};

const contactUsBtn = document.getElementById('contactUsBtn');
const contactContainer = document.getElementById('contactContainer');
const developerName = document.getElementById('developerName');
const developerBio = document.getElementById('developerBio');
const prevBtn = document.getElementById('prevBtn');
const nextBtn = document.getElementById('nextBtn');
const sliderCounter = document.getElementById('sliderCounter');

const developers = [
    { name: 'Абдукашев Линар', bio: 'Страстный программист с опытом работы более года в Python и полгода в веб-разработке на HTML, CSS и JavaScript. Владею основами React и умею работать с базами данных MySQL, SQL и Firebase. Стремлюсь к постоянному обучению и созданию эффективных решений.' },
    { name: 'Карпов Влад', bio: 'навыки' },
    { name: 'Волков Александр', bio: 'Креативный дизайнер с опытом работы в Figma, Photoshop и Illustrator, развивающий навыки на протяжении года. В процессе изучения C++ на среднем уровне, стремлюсь к сочетанию дизайна и программирования для создания интуитивно понятных интерфейсов.' },
    { name: 'Даниил', bio: 'навыки' }
];

let currentSlide = 0;

function updateSlide() {
    developerName.innerText = developers[currentSlide].name;
    developerBio.innerText = developers[currentSlide].bio;
    sliderCounter.innerText = `${currentSlide + 1}/${developers.length}`;
}

nextBtn.addEventListener('click', () => {
    currentSlide = (currentSlide + 1) % developers.length;
    updateSlide();
});

prevBtn.addEventListener('click', () => {
    currentSlide = (currentSlide - 1 + developers.length) % developers.length;
    updateSlide();
});

contactUsBtn.addEventListener('click', () => {
    welcomeContainer.style.display = 'none';
    contactContainer.style.display = 'block';
    updateSlide();
});

document.getElementById('closeContact').addEventListener('click', () => {
    contactContainer.style.display = 'none';
    welcomeContainer.style.display = 'block';
});

function updateStatistics() {
    createdCoursesCounter.innerText = createdCoursesCount;
    completedCoursesCounter.innerText = completedCoursesCount;
}
