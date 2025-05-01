document.addEventListener('DOMContentLoaded', function() {
    fetch('/profile/data')
        .then(response => {
            if (!response.ok) {
                throw new Error('Помилка отримання даних профілю');
            }
            return response.json();
        })
        .then(user => {
            if (user) {
                document.getElementById('user-name').textContent = user.name || '';
                document.getElementById('user-surname').textContent = user.surname || '';
                document.getElementById('user-last-name').textContent = user.lastName || '';
                document.getElementById('user-age').textContent = user.age || '';
                document.getElementById('user-email').textContent = user.email || '';
                // Тут ви можете додати логіку для відображення іншої інформації, якщо вона є у вашому об'єкті user
            } else {
                // Обробка випадку, коли дані користувача не отримано
                document.getElementById('user-block').textContent = 'Інформація про користувача недоступна.';
            }
        })
        .catch(error => {
            console.error('Помилка:', error);
            document.getElementById('user-block').textContent = 'Виникла помилка при завантаженні даних.';
        });
});