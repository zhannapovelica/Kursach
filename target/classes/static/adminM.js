const userRole = /*[[${session.user?.role ?: 'GUEST'}]]*/ 'GUEST';
const adminMenu = document.getElementById('admin-menu');
if (userRole === 'ADMIN') {
    adminMenu.style.display = 'block';
}
else {
    adminMenu.style.display = 'none';
}