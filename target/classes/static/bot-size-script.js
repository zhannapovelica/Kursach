document.addEventListener('DOMContentLoaded', () => {

    window.addEventListener('message', (event) => {
        if (event.data === 'large') {
            document.getElementById('fr').style.width = '460px';
            document.getElementById('fr').style.height = '700px';
        } else if (event.data === 'small') {
            document.getElementById('fr').style.width = '120px';
            document.getElementById('fr').style.height = '80px';
        }
    });
});


