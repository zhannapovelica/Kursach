document.getElementById('decline').onclick = function() {
    document.getElementById('declineModal').style.display = 'block';
};

document.getElementById('closeModal').onclick = function() {
    document.getElementById('declineModal').style.display = 'none';
};

// Закрытие при клике вне формы
window.onclick = function(event) {
    if (event.target == document.getElementById('declineModal')) {
        document.getElementById('declineModal').style.display = 'none';
    }
};