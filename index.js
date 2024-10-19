const welcomeContainer = document.getElementById('welcomeContainer');
const courseFormContainer = document.getElementById('courseFormContainer');
const exploreContainer = document.getElementById('exploreContainer');
const statisticsContainer = document.getElementById('statisticsContainer');
const courseInfoContainer = document.getElementById('courseInfoContainer');
const courseList = document.getElementById('courseList');
const usernameDisplay = document.getElementById('username');
const createdCoursesCounter = document.getElementById('createdCourses');
const completedCoursesCounter = document.getElementById('completedCourses');

let createdCoursesCount = 0;
let completedCoursesCount = 0;

let userId = null;
let username = null;

const urlParams = new URLSearchParams(window.location.search);

// Извлечение user_id и username из URL
function getUserInfo() {
    userId = urlParams.get('user_id');
    username = urlParams.get('username');
    return { userId: userId || 'Неизвестный ID', username: username || 'Неизвестный пользователь' };
}

// Загружаем статистику из localStorage
function loadStatistics() {
    const statistics = JSON.parse(localStorage.getItem('courseStatistics'));
    if (statistics) {
        createdCoursesCount = statistics.createdCourses || 0;
        completedCoursesCount = statistics.completedCourses || 0;
    }
    updateStatistics(); // Обновляем отображение статистики
}

// Сохраняем статистику в localStorage
function saveStatistics() {
    const statistics = {
        createdCourses: createdCoursesCount,
        completedCourses: completedCoursesCount,
    };
    localStorage.setItem('courseStatistics', JSON.stringify(statistics));
}

// Обновляем статистику на экране
function updateStatistics() {
    createdCoursesCounter.innerText = createdCoursesCount;
    completedCoursesCounter.innerText = completedCoursesCount;
}

// Загружаем данные при загрузке страницы
window.onload = () => {
    const telegram = window.Telegram.WebApp;

    if (telegram) {
        telegram.ready(); // Убедимся, что Web App готов

        // Получаем информацию о пользователе
        const userInfo = getUserInfo();
        usernameDisplay.innerText = userInfo.username !== 'Неизвестный пользователь' ? 'Пользователь: ' + userInfo.username : 'Пользователь не найден';
        loadStatistics(); // Загружаем статистику
    } else {
        console.error("Telegram Web App не доступен.");
    }

    // Для отладки
    console.log(`User ID: ${userId}, Username: ${username}`);
};

// Загрузка курсов
function loadCourses() {
    courseList.innerHTML = '';

    const courses = [
        { id: 1, title: 'Курс по Программированию для Начинающих', description: 'Этот курс познакомит вас с основами программирования на языке Python.', hashtags: '#Python, #Программирование, #Начинающие' },
        { id: 2, title: 'Курс по Математике', description: 'Изучите основы алгебры и геометрии.', hashtags: '#Математика, #Алгебра' },
        { id: 3, title: 'Курс по Английскому языку', description: 'Начните говорить на английском!', hashtags: '#Английский, #Языки' },
        { id: 4, title: 'Курс по Физике', description: 'Понимание физических явлений в нашей жизни.', hashtags: '#Физика' }
    ];

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
}

// Создание нового курса
function createCourse() {
    const courseName = document.getElementById('courseName').value;
    const courseDescription = document.getElementById('courseDescription').value;
    const hashtags = document.getElementById('hashtags').value;
    const links = document.getElementById('links').value;
    const selectedTopic = document.getElementById('selectedTopic').innerText;

    const newCourse = {
        title: courseName,
        description: courseDescription,
        hashtags: hashtags,
        links: links,
        topic: selectedTopic,
    };

    createdCoursesCount++;
    courseList.innerHTML += `
        <div class="course-item">
            <h3>${newCourse.topic}: ${newCourse.title}</h3>
            <button class="btn" onclick="showCourseDetails(${createdCoursesCount})">Подробнее</button>
        </div>
    `;

    courseFormContainer.style.display = 'none';
    welcomeContainer.style.display = 'block';
    updateStatistics(); // Обновляем статистику
}

// Показ деталей курса
function showCourseDetails(courseId) {
    const courseDetails = {
        1: { title: 'Курс по Программированию для Начинающих', description: 'Этот курс познакомит вас с основами программирования на языке Python. Вы научитесь писать простые программы.', links: 'Ссылка на видео' },
        //можно еще добавить
    };

    const course = courseDetails[courseId];
    if (course) {
        document.getElementById('courseInfoTitle').innerText = course.title;
        document.getElementById('courseInfoDescription').innerText = course.description;
        document.getElementById('courseInfoLinks').innerText = course.links;

        // Скрыть другие контейнеры и показать контейнер информации о курсе
        exploreContainer.style.display = 'none';
        courseInfoContainer.style.display = 'block';
    } else {
        alert('Курс не найден');
    }
}

// Завершение курса
document.getElementById('markAsDone').addEventListener('click', () => {
    completedCoursesCount++;
    saveStatistics(); // Сохраняем статистику
    courseInfoContainer.style.display = 'none';
    statisticsContainer.style.display = 'block';
    completedCoursesCounter.innerText = completedCoursesCount;
});

// Переход к исследованию курсов
document.getElementById('exploreCourses').addEventListener('click', () => {
    welcomeContainer.style.display = 'none';
    exploreContainer.style.display = 'block';
    loadCourses();
});

// Переход к созданию курса
document.getElementById('createCourse').addEventListener('click', () => {
    welcomeContainer.style.display = 'none';
    courseFormContainer.style.display = 'block';
});

// Обновление статистики
function updateStatistics() {
    createdCoursesCounter.innerText = createdCoursesCount;
    completedCoursesCounter.innerText = completedCoursesCount;
}

// Отмена создания курса
document.getElementById('cancel').addEventListener('click', () => {
    courseFormContainer.style.display = 'none';
    welcomeContainer.style.display = 'block';
});

// Закрытие раздела исследований
document.getElementById('closeExplore').addEventListener('click', () => {
    exploreContainer.style.display = 'none';
    welcomeContainer.style.display = 'block';
});

// Закрытие статистики
document.getElementById('closeStatistics').addEventListener('click', () => {
    statisticsContainer.style.display = 'none';
    welcomeContainer.style.display = 'block';
});

// Отмена просмотра информации о курсе
document.getElementById('cancelCourseInfo').addEventListener('click', () => {
    courseInfoContainer.style.display = 'none';
    welcomeContainer.style.display = 'block';
});

// Выбор темы курса
document.getElementById('chooseTopic').addEventListener('click', () => {
    document.getElementById('topicList').style.display = 'block';
});

// Добавление выбранной темы
document.querySelectorAll('#topicList li').forEach(item => {
    item.addEventListener('click', () => {
        const selectedTopic = item.getAttribute('data-topic');
        document.getElementById('selectedTopic').innerText = selectedTopic;
        document.getElementById('topicList').style.display = 'none';
    });
});

// Отправка формы для создания курса
document.getElementById('courseForm').addEventListener('submit', (event) => {
    event.preventDefault();
    createCourse();
});

// Информация о нас
const contactUsBtn = document.getElementById('contactUsBtn');
const contactContainer = document.getElementById('contactContainer');
const developerCard = document.getElementById('developerCard');
const developerName = document.getElementById('developerName');
const developerBio = document.getElementById('developerBio');
const prevBtn = document.getElementById('prevBtn');
const nextBtn = document.getElementById('nextBtn');
const sliderCounter = document.getElementById('sliderCounter');
const closeContact = document.getElementById('closeContact');

// Раздел для разработчиков
const developers = [
    { name: 'Имя фамилия', bio: 'навыки' },
    { name: 'Имя фамилия', bio: 'навыки' },
    { name: 'Имя фамилия', bio: 'навыки' },
    { name: 'Имя фамилия', bio: 'навыки' }
];

let currentSlide = 0;

function updateSlide() {
    developerName.innerText = developers[currentSlide].name;
    developerBio.innerText = developers[currentSlide].bio;
    sliderCounter.innerText = `${currentSlide + 1}/${developers.length}`;
}

// Переключение слайдов
nextBtn.addEventListener('click', () => {
    currentSlide = (currentSlide + 1) % developers.length;
    updateSlide();
});

prevBtn.addEventListener('click', () => {
    currentSlide = (currentSlide - 1 + developers.length) % developers.length;
    updateSlide();
});

// Открытие и закрытие слайдера
contactUsBtn.addEventListener('click', () => {
    welcomeContainer.style.display = 'none';
    contactContainer.style.display = 'block';
    updateSlide();
});

closeContact.addEventListener('click', () => {
    contactContainer.style.display = 'none';
    welcomeContainer.style.display = 'block';
});
