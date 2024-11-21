document.getElementById('saver-veteran').addEventListener('click', function() {
    const imageUrl2 = document.getElementById('image-veteran').src; 
    const link2 = document.createElement('a'); 
    link2.href = imageUrl2;
    link2.download = './img-veteran/section5-schedule.png'; 
    link2.click(); 
});
