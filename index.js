document.getElementById('createCourse').addEventListener('click', function() {
    document.getElementById('welcomeContainer').style.display = 'none';
    document.getElementById('courseFormContainer').style.display = 'block';
});

document.getElementById('cancel').addEventListener('click', function() {
    document.getElementById('courseFormContainer').style.display = 'none';
    document.getElementById('welcomeContainer').style.display = 'block';
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
