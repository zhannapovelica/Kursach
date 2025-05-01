let isLarge = false;
const botikElements = document.querySelectorAll('.botik');
const iframeElement = document.getElementById('fr'); // Отримуємо елемент iframe

document.addEventListener('DOMContentLoaded', function() {
    botikElements.forEach(element => {
        element.addEventListener('click', () => {
            if (iframeElement) {
                if (isLarge) {
                    iframeElement.style.width = '120px';
                    iframeElement.style.height = '80px';
                } else {
                    iframeElement.style.width = '460px';
                    iframeElement.style.height = '700px';
                }
                isLarge = !isLarge;
            } else {
                console.error('Не знайдено iframe з ID "fr".');
            }
            if (iframeElement && iframeElement.contentWindow && iframeElement.contentWindow.document) {
                const togglerInsideIframe = iframeElement.contentWindow.document.querySelector("#chatbot-toggler");
                if (togglerInsideIframe) {
                    togglerInsideIframe.click();
                    console.log('Симульовано клік на #chatbot-toggler всередині iframe');
                } else {
                    console.error('Не знайдено елемент #chatbot-toggler всередині iframe.');
                }
            } else {
                console.error('Не вдалося отримати доступ до contentWindow або document iframe.');
            }
        });
    });
});