//����� � https://bootstrap-4.ru/docs/5.0/forms/validation/
function validateForm() {
    'use strict'
    // �������� ��� �����, � ������� �� ����� ��������� ���������������� ����� �������� Bootstrap
    const forms = document.querySelectorAll('.needs-validation');
    const genre = document.getElementById("genre");
    // �������������� �� ��� � �������������� ��������
    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }
                // TODO: �������� �� ���� (��� ���� ������)
                // if (genre.value === 'default') {
                //     alert("����������, �������� ����!");
                //     event.preventDefault()
                //     event.stopPropagation()
                //     return false;
                // }
                form.classList.add('was-validated')
            }, false)
        })
}
