const login = document.getElementById('login-confirm');
const userBlock = document.getElementById('user-block');
const loginBlock = document.getElementById('login-block');

login.addEventListener('click', loginFun);

function loginFun() {
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    fetch("/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            email: email,
            password: password
        })

    }).then(response => response.json())
        .then(user => {
            console.log('User object received:', user);
            document.getElementById('user-name').textContent = user.name;
            document.getElementById('user-surname').textContent = user.surname;
            document.getElementById('user-last-name').textContent = user.lastName;
            document.getElementById('user-age').textContent = user.age;
            document.getElementById('user-email').textContent = user.email;
            userBlock.style.display = 'flex';
            loginBlock.style.display = 'none';
        })

}