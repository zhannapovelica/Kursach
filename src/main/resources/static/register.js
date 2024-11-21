const regButton = document.getElementById('reg-btn');

regButton.addEventListener('click', regFun);

function regFun() {
    const name = document.getElementById('name').value;
    const surname = document.getElementById('surname').value;
    const lastName = document.getElementById('last-name').value;
    const age = document.getElementById('age').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    fetch("/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            name: name,
            surname: surname,
            lastName: lastName,
            age: age,
            email: email,
            password: password
        })
    
    }).then(data => {
        document.getElementById('name').value = '';
        document.getElementById('surname').value = '';
        document.getElementById('last-name').value = '';
        document.getElementById('age').value = '';
        document.getElementById('email').value = '';
        document.getElementById('password').value = '';
    
        alert('Ви успішно зареєстровані!');
        })
}