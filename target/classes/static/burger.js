const burger = document.getElementById('burger');
const menu = document.getElementById('showMenu');

burger.addEventListener('click', open);
let counter = 0;
function open(){
    counter += 1;
    if(counter % 2 == 1){
        menu.style.display = 'flex';
    }
    else{
        menu.style.display = 'none';
    }
}

document.getElementById('save-button').addEventListener('click', function() {
    const imageUrl = document.getElementById('image-military').src; 
    const link = document.createElement('a'); 
    link.href = imageUrl;
    link.download = './img-military/section5-schedule.png'; 
    link.click(); 
});