const btn = document.getElementById('chatbot-toggler');
let isLarge = false;

btn.addEventListener('click', () => {
    window.parent.postMessage(isLarge ? 'small' : 'large', '*');
    isLarge = !isLarge;
});


