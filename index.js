document.getElementById('createCourse').addEventListener('click', function() {
    document.getElementById('welcomeContainer').style.display = 'none';
    document.getElementById('courseFormContainer').style.display = 'block';
});
document.getElementById('cancel').addEventListener('click', function() {
    document.getElementById('courseFormContainer').style.display = 'none';
    document.getElementById('welcomeContainer').style.display = 'block';
});
document.getElementById('exploreCourses').addEventListener('click', function() {
    document.getElementById('welcomeContainer').style.display = 'none';
    document.getElementById('exploreContainer').style.display = 'flex';
});
document.getElementById('closeExplore').addEventListener('click', function() {
    document.getElementById('exploreContainer').style.display = 'none';
    document.getElementById('welcomeContainer').style.display = 'block';
});
//логикa поиска по названию или хэштегу
document.getElementById('searchButton').addEventListener('click', function() {
    const searchQuery = document.getElementById('searchCourse').value;
    console.log('Ищем курс по:', searchQuery);
    // Элемент инпута и кнопки поиска
const searchInput = document.getElementById('searchCourse');
const searchButton = document.getElementById('searchButton');
const courseList = document.getElementById('courseList');

// Слушатель события для кнопки поиска
searchButton.addEventListener('click', function () {
    const query = searchInput.value.trim();

    if (query) {
        // Отправляем запрос на сервер для фильтрации курсов
        fetch(`/api/search-courses?q=${encodeURIComponent(query)}`)
            .then(response => response.json())
            .then(data => {
                // Очищаем список курсов
                courseList.innerHTML = '';

                if (data.length > 0) {
                    // Добавляем отфильтрованные курсы в список
                    data.forEach(course => {
                        const courseItem = document.createElement('div');
                        courseItem.classList.add('course-item');
                        courseItem.textContent = `${course.name} - ${course.description}`;
                        courseList.appendChild(courseItem);
                    });
                } else {
                    // Если курсов нет, показываем сообщение
                    courseList.innerHTML = '<p>Список пуст</p>';
                }
            })
            .catch(error => {
                console.error('Ошибка при получении списка курсов:', error);
            });
    } else {
        // Если поле поиска пустое, очищаем список и выводим сообщение
        courseList.innerHTML = '<p>Список пуст</p>';
    }
});

});
document.getElementById('courseForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const courseName = document.getElementById('courseName').value;
    const courseDescription = document.getElementById('courseDescription').value;
    const hashtags = document.getElementById('hashtags').value;
    const links = document.getElementById('links').value;

    console.log('Название курса:', courseName);
    console.log('Описание курса:', courseDescription);
    console.log('Хэштеги:', hashtags);
    console.log('Ссылки:', links);

    document.getElementById('courseForm').reset();
});


const textarea = document.getElementById('courseDescription');

textarea.addEventListener('input', () => {
    textarea.style.height = 'auto';
    textarea.style.height = Math.min(textarea.scrollHeight, 300) + 'px'; // Максимальная высота 300px
});
