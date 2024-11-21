const start = document.getElementById("start");
const whatShowed = document.getElementById("whatShowed");
start.addEventListener("click", showTest);
function showTest() {
  whatShowed.style.display = "block";
}

function checkForm() {
  const form = document.getElementById("myForm");
  const inputs = form.querySelectorAll("input, textarea");
  let isFilled = true;

  inputs.forEach((input) => {
    if (!input.value.trim()) {
      isFilled = false;
    }
  });

  if (isFilled) {
    showModal();
  } else {
    alert("Будь ласка, заповніть усі поля!");
  }
}

document.getElementById('submit-btn').addEventListener('click', function () {
  const q1 = parseInt(document.getElementById('q1').value);
  const q2 = parseInt(document.getElementById('q2').value);
  const q3 = parseInt(document.getElementById('q3').value);
  const q4 = parseInt(document.getElementById('q4').value);
  const q5 = parseInt(document.getElementById('q5').value);
  const q6 = parseInt(document.getElementById('q6').value);
  const q7 = parseInt(document.getElementById('q7').value);
  const q8 = parseInt(document.getElementById('q8').value);
  const q9 = parseInt(document.getElementById('q9').value);
  const q10 = parseInt(document.getElementById('q10').value);
  const q11 = parseInt(document.getElementById('q11').value);
  const totalScore = q1 + q2 + q3 + q4 + q5 + q6 + q7 + q8 + q9 + q10 + q11;
  let resultMessage = '';
  if (totalScore <= 15) {
      resultMessage = 'Ви, здається, у гарному психологічному стані. Продовжуйте дбати про себе!';
  } else if (totalScore <= 28) {
      resultMessage = 'У вас можуть бути незначні ознаки стресу. Знайдіть час для відпочинку.';
  } else if (totalScore <= 33) {
      resultMessage = 'Ви відчуваєте сильний стрес. Рекомендуємо звернутися за підтримкою.';
  } else {
      resultMessage = 'Ваш стан викликає занепокоєння. Зверніться до професійного психолога.';
  }
  alert(resultMessage);
});



