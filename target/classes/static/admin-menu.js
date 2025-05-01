document.addEventListener('DOMContentLoaded', function() {
    console.log('DOM is fully loaded and parsed');
    const headers = document.querySelectorAll('.tog');
    console.log('Found ' + headers.length + ' elements with class "section-header"');
    headers.forEach(header => {
        console.log('Adding event listener to:', header);
        header.addEventListener('click', function() {
            console.log('Clicked on:', this);
            toggleMenu(this);
        });
    });
});

function toggleMenu(header) {
    const submenu = header.nextElementSibling;
    console.log('Toggling submenu for:', header, 'submenu is:', submenu);
    if (submenu) {
        submenu.style.display = submenu.style.display === 'block' ? 'none' : 'block';
    } else {
        console.log('Submenu not found for:', header);
    }
}